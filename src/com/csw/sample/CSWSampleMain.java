package com.csw.sample;

import com.csw.sample.contentconverter.ContentConverter;
import com.csw.sample.contentconverter.input.json.JSONInputConverterImpl;

public class CSWSampleMain {

	public static void main(String args[]) {
		ContentConverter converter = new JSONInputConverterImpl();
		converter.doConversionProcess();
	}
}
