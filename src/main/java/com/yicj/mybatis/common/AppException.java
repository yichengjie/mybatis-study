package com.yicj.mybatis.common;

public class AppException extends RuntimeException {
	
	public AppException(Throwable e) {
		super(e) ;
	}
	
	public AppException(String msg,Throwable e) {
		super(msg,e) ;
	}
}
