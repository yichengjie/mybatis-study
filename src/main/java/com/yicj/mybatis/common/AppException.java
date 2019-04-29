package com.yicj.mybatis.common;

public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AppException(Throwable e) {
		super(e) ;
	}
	
	public AppException(String msg,Throwable e) {
		super(msg,e) ;
	}
}
