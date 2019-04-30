package com.yicj.mybatis.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({
	@Signature(type=ResultSetHandler.class,method="handleResultSets",args=Statement.class)
})
public class Myinterceptor1 implements Interceptor{

	//拦截目标对象
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("拦截的对象是1："+invocation.getTarget());
		System.out.println("拦截方法是1："+invocation.getMethod().toString());
		System.out.println("拦截参数是1："+invocation.getArgs().length);
		//执行拦截对象真正的方法
		Object o=invocation.proceed();
		return o;
	}
	//包装目标对象 为目标对象创建动态代理
	@Override
	public Object plugin(Object target) {
		System.out.println("插件方法1--将要包装的目标对象1："+target);
		//为当前对象创建代理对象
		Object o=Plugin.wrap(target, this);
		return o;
	}
	//获取插件初始化参数
	@Override
	public void setProperties(Properties properties) {
		String value1=(String) properties.get("shuxing1");	
		String value2=(String) properties.get("shuxing2");
		System.out.println("插件初始化参数1："+value1+":"+value2);
	}
}
