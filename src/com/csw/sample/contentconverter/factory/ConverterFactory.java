package com.csw.sample.contentconverter.factory;

import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_01_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_02_DESC;

import com.csw.sample.contentconverter.ContentConverterException;
import com.csw.sample.contentconverter.input.json.JSONInputConverter;
import com.csw.sample.contentconverter.output.xml.XMLJSONConverterI;
import com.csw.sample.contentconverter.output.xml.XMLJSONConverterImpl;

/**
 * Converter Factory which provides objects to perform conversion 
 * based on input format and desired output format.
 */
public class ConverterFactory {

	/**
	 * This method provides converter object based on input data format and output format
	 * @param inpFmt - Provides input data format
	 * @param outFmt - Provides output data format
	 * @return ConverterI - Object of type Converter which performs conversion based on formats.
	 */
	public XMLJSONConverterI createXMLJSONConverter(String inpFmt, String outFmt) throws ContentConverterException {
		XMLJSONConverterI converter = null;
		if(JSONInputConverter.INPUT_FORMAT.equalsIgnoreCase(inpFmt)) {
			if(XMLJSONConverterImpl.OUTPUT_FORMAT.equalsIgnoreCase(outFmt)) {
				converter = new XMLJSONConverterImpl();
			} else {
				throw new ContentConverterException(ERROR_01_DESC);
			}
		} else {
			throw new ContentConverterException(ERROR_02_DESC);
		}
		return converter;
	}

}
