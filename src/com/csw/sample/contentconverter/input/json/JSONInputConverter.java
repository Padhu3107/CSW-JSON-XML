package com.csw.sample.contentconverter.input.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.stream.JsonParsingException;

import com.csw.sample.contentconverter.ContentConverter;

/**
 * JSON implementation of Converter.
 * Provides necessary implementations for JSON specific input format
 */
public abstract class JSONInputConverter implements ContentConverter {

	public static final String INPUT_FORMAT = "JSON";

	public static final char FLOWER_BRACE_START = '{';
	public static final char FLOWER_BRACE_END = '}';
	public static final char SQUARE_BRACE_START = '[';
	public static final char SQUARE_BRACE_END = ']';
	public static final char NAME_VALUE_SEPERATOR = ':';
	public static final char OBJECT_SEPERATOR = ',';
	public static final char DOUBLE_QUOTE = '"';
	public static final char SINGLE_QUOTE = '\'';
	
	public static final String NULL_VALUE = "null";
	public static final String BOOLEAN_TRUE = "true";
	public static final String BOOLEAN_FALSE = "false";
	
	/**
	 * Validates input JSON data before doing conversion using GSON API.
	 * @param inpJson - Input JSON data to be validated
	 * @return boolean denotes valid data if evaluated to true
	 */
	public boolean validateJSONInput(String inpJson) {
		boolean isValid = true;
		try{
			JsonReader reader = Json.createReader(new FileReader(new File(inpJson)));
			JsonStructure structure = reader.read();
		} catch(FileNotFoundException fnf){
			fnf.printStackTrace();
			isValid = false;
		}
		catch(JsonParsingException jse){
			jse.printStackTrace();
			isValid = false;
		}
		return isValid;
	}
}
