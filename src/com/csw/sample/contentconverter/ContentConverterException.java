package com.csw.sample.contentconverter;

/**
 * 
 * User defined exception for handling ContentConverter module
 *
 */
public class ContentConverterException extends Exception {
	
	/**
	 * Calls Super class without any arguments
	 */
	public ContentConverterException () {
		super();
	}
	
	/**
	 * Calls Super class with input error message
	 * @param errorMessage - Message which has been defined by the user while handling Exceptions
	 */
	public ContentConverterException (String errorMessage) {
		super(errorMessage);
	}
	/**
	 * Calls Super class with input error message of type StringBuilder useful in case of string concatenation
	 * @param errorMessage - Message which has been defined by the user while handling Exceptions
	 */
	public ContentConverterException(StringBuilder errorMessage) {
		super(errorMessage.toString());
	}
}
