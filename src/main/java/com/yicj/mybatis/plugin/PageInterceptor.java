package com.yicj.mybatis.plugin;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yicj.mybatis.entity.Page;

//@Intercepts(@Signature(type = StatementHandler.class, method = "prepare",args = {Connection.class}))
@Intercepts({
    @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class,
               RowBounds.class, ResultHandler.class }),
    @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	Logger logger = LoggerFactory.getLogger(PageInterceptor.class) ;
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private String pattern = "^.*page.*$";    // 需要进行分页操作的字符串正则表达式
    /**  数据库方言：目前只支持mysql、oracle、sqlServer；默认mysql */
    private String dialect = "mysql";

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
	
    //插件运行的代码，它将代替原有的方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
    	if (invocation.getTarget() instanceof StatementHandler) {
            return handleStatementHandler(invocation);
        }
        return invocation.proceed();
    }
    
    private Object handleStatementHandler(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
    	 StatementHandler handler = (StatementHandler) invocation.getTarget();
         MetaObject metaStatementHandler = MetaObject.forObject(handler, 
        		 DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY, null);
         RowBounds rowBounds = (RowBounds) metaStatementHandler
                 .getValue("delegate.rowBounds");
         if (rowBounds == null || (rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET && rowBounds
                 .getLimit() == RowBounds.NO_ROW_LIMIT)) {
             return invocation.proceed();
         }
         // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
         while (metaStatementHandler.hasGetter("h")) {
             Object object = metaStatementHandler.getValue("h");
             metaStatementHandler = MetaObject.forObject(object,
                     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,null);
         }
         // 分离最后一个代理对象的目标类
         while (metaStatementHandler.hasGetter("target")) {
             Object object = metaStatementHandler.getValue("target");
             metaStatementHandler = MetaObject.forObject(object,
                     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,null);
         }

         // 将mybatis的内存分页，调整为物理分页
         BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
         String sql = boundSql.getSql();
         // 重写sql
         String pageSql = PageSqlFactory.getPageSqlByDialect(this.dialect, sql, rowBounds);
         metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
         // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
         metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
         metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
         
         // 将执行权交给下一个拦截器
         return invocation.proceed();
	}

	/**
     * 拦截类型StatementHandler 
     */
    @Override
    public Object plugin(Object o) {
    	 if (Executor.class.isAssignableFrom(o.getClass())) {
             PageExecutor executor = new PageExecutor((Executor)o, pattern);
             return Plugin.wrap(executor, this);
         } else if (o instanceof StatementHandler) {
             return Plugin.wrap(o, this);
         }
         return o;
    }
    
    @Override
    public void setProperties(Properties properties) {
        
    }  
    
    
    class PageExecutor implements Executor{
    	
    	private Executor executor;
        
        private String pattern;
        
        public PageExecutor(Executor executor, String pattern) {
            this.executor = executor;
            this.pattern = pattern;
        }

        @Override
        public int update(MappedStatement ms, Object parameter) throws SQLException {
            return executor.update(ms, parameter);
        }

        @Override
        public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
                CacheKey cacheKey, BoundSql boundSql) throws SQLException {
            RowBounds rb = new RowBounds(rowBounds.getOffset(), rowBounds.getLimit());
            List<E> rows = executor.query(ms, parameter, rowBounds, resultHandler,
                    cacheKey, boundSql);
            return pageResolver(rows, ms, parameter, rb);
        }
        
        /**
         * 修改返回值类型
         * @param rows
         * @param ms
         * @param parameter
         * @param rowBounds
         * @return
         */
        private <E> List<E> pageResolver(List<E> rows, MappedStatement ms,
                Object parameter, RowBounds rowBounds) {
            String msid = ms.getId();
            // 如果需要分页查询，修改返回类型为Page对象
            if (msid.matches(pattern)) {
                int count = getCount(ms, parameter);
                int offset = rowBounds.getOffset();
                int pagesize = rowBounds.getLimit();
                return new Page<E>(offset/pagesize + 1, pagesize, count, rows);
            }
            return rows;
        }
        
        /**
         * 获取总数
         * @param ms
         * @param parameter
         * @return
         */
        private int getCount(MappedStatement ms, Object parameter) {
            BoundSql bsql = ms.getBoundSql(parameter);
            String sql = bsql.getSql();
            String countSql = getCountSql(sql);
            Connection connection = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                connection = ms.getConfiguration().getEnvironment().getDataSource()
                        .getConnection();
                stmt = connection.prepareStatement(countSql);
                setParameters(stmt, ms, bsql, parameter);
                rs = stmt.executeQuery();
                if (rs.next())
                    return rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }
        
        @SuppressWarnings("unchecked")
        private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                Object parameterObject) throws SQLException {
            ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (parameterMappings != null) {
                Configuration configuration = mappedStatement.getConfiguration();
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
                for (int i = 0; i < parameterMappings.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    if (parameterMapping.getMode() != ParameterMode.OUT) {
                        Object value;
                        String propertyName = parameterMapping.getProperty();
                        if (parameterObject == null) {
                            value = null;
                        } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                            value = parameterObject;
                        } else if (boundSql.hasAdditionalParameter(propertyName)) {
                            value = boundSql.getAdditionalParameter(propertyName);
                        } else {
                            value = metaObject == null ? null : metaObject.getValue(propertyName);
                        }
                        @SuppressWarnings("rawtypes")
                        TypeHandler typeHandler = parameterMapping.getTypeHandler();
                        if (typeHandler == null) {
                            throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
                                    + " of statement " + mappedStatement.getId());
                        }
                        typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                    }
                }
            }
        }
        
        private String getCountSql(String sql) {
            String countHql = " SELECT count(*) "
                    + removeSelect(removeOrders(sql));

            return countHql;
        }
        
        protected String removeOrders(String sql) {
            Pattern p = Pattern.compile("ORDER\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(sql);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                m.appendReplacement(sb, "");
            }
            m.appendTail(sb);
            return sb.toString();
        }
        
        // 去除sql语句中select子句
        private String removeSelect(String hql) {
            int beginPos = hql.toLowerCase().indexOf("from");
            if (beginPos < 0) {
                throw new IllegalArgumentException(" hql : " + hql + " must has a keyword 'from'");
            }
            return hql.substring(beginPos);
        }

        @Override
        public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
                throws SQLException {
            BoundSql boundSql = ms.getBoundSql(parameter);
            return query(ms, parameter, rowBounds, resultHandler,
                    executor.createCacheKey(ms, parameter, rowBounds, boundSql),
                    boundSql);
        }

        @Override
        public List<BatchResult> flushStatements() throws SQLException {
            return executor.flushStatements();
        }

        @Override
        public void commit(boolean required) throws SQLException {
            executor.commit(required);
        }

        @Override
        public void rollback(boolean required) throws SQLException {
            executor.rollback(required);
        }

        @Override
        public CacheKey createCacheKey(MappedStatement ms, Object parameterObject,
                RowBounds rowBounds, BoundSql boundSql) {
            return executor
                    .createCacheKey(ms, parameterObject, rowBounds, boundSql);
        }

        @Override
        public boolean isCached(MappedStatement ms, CacheKey key) {
            return executor.isCached(ms, key);
        }

        @Override
        public void clearLocalCache() {
            executor.clearLocalCache();
        }

        @Override
        public void deferLoad(MappedStatement ms, MetaObject resultObject,
                String property, CacheKey key, Class<?> targetType) {
            executor.deferLoad(ms, resultObject, property, key, targetType);
        }

        @Override
        public Transaction getTransaction() {
            return executor.getTransaction();
        }

        @Override
        public void close(boolean forceRollback) {
            executor.close(forceRollback);
        }

        @Override
        public boolean isClosed() {
            return executor.isClosed();
        }

    	@Override
    	public void setExecutorWrapper(Executor executor) {
    		
    	}
    }

    static class PageSqlFactory {
    	 /**
        * 根据数据库方言获取分页查询语句（目前只支持mysql、oracle、sqlServer；默认mysql）
        * @param dialect
        * @param originalSql
        * @param rowBounds
        * @return
        * @author Taocong
        * @date 2017年9月28日 下午4:12:57
        */
       public static String getPageSqlByDialect(String dialect, String originalSql, RowBounds rowBounds) {
           String pageSql = null;
           if (null == dialect || "mysql".equals(dialect)) {
               pageSql = originalSql + " LIMIT " + rowBounds.getOffset() + "," + rowBounds.getLimit();
           } else if ("oracle".equals(dialect)) {
               StringBuilder sqlBuilder = new StringBuilder();
               sqlBuilder.append("select * from ( select tmp_page.*, rownum row_id from ( ");
               sqlBuilder.append(originalSql);
               sqlBuilder.append(" ) tmp_page where rownum <= ");
               sqlBuilder.append(rowBounds.getLimit());
               sqlBuilder.append(" ) where row_id > ");
               sqlBuilder.append(rowBounds.getOffset());
               pageSql = sqlBuilder.toString();
           } else if ("sqlServer".equals(dialect)) {
               /* 查看sql中是否有排序规则：
                * 1.如果没有，按手动新增字段n（n=0）生成rown_number
                * 2.如果有，按指定的排序规则生成rown_number
                * 
                *  eg：
                *  1.select * from a where 1=1
                *  ->select * from (select row_number() over(ORDER BY n) as rownumber,* 
                *      from ( select top 20 n=0, * from a where 1=1)t )tt where rownumber> 0
                *
                *  2.select * from a where 1=1 ORDER BY b
                *  ->select * from (select row_number() over(ORDER BY b) as rownumber,* 
                *      from ( select top 20 n=0, * from a where 1=1 ORDER BY b)t )tt where rownumber> 0
                */
               // 找到sql中的排序
               // (由于indexof不能使用正则，所以先使用replaceAll对sql中的排序语法规范化;(?i)标识忽略大小写;\\s*表示空格出现一次或多次)
               int orderStartIndex = originalSql.replaceAll("(?i)ORDER\\s+BY", "ORDER BY").lastIndexOf("ORDER BY");
               String orderStr = "ORDER BY n";
               // 有排序，且是最外层的排序
               if (orderStartIndex != -1 && originalSql.lastIndexOf(")") < orderStartIndex) {
                   orderStr = originalSql.substring(orderStartIndex);
               }
               pageSql = originalSql.replaceFirst("(?i)select", "select * from (select row_number() over(" + orderStr
                       + ") as rownumber,* from ( select top " + (rowBounds.getOffset() + rowBounds.getLimit()) + " n=0,");
               pageSql += ")t )tt where rownumber> " + rowBounds.getOffset();
           }

           return pageSql;
       }
    }
}

