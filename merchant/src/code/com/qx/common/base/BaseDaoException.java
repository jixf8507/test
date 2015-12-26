package com.qx.common.base;

/**
 * 
 * @author jixf
 * @2014-9-23
 */
public class BaseDaoException extends BaseException {

	private static final long serialVersionUID = 2993553478482931523L;

	public BaseDaoException() {
		super();
	}

	/**
	 * @param message
	 */
	public BaseDaoException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public BaseDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public BaseDaoException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

}
