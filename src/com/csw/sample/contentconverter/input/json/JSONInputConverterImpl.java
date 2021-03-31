package com.csw.sample.contentconverter.input.json;

import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_01_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_02_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_03_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_04_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_05_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_06_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_07_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_09_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_10_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.INFO_11_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.OUT_FILE_NAME;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_108_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_109_DESC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.csw.sample.contentconverter.ContentConverterException;
import com.csw.sample.contentconverter.factory.ConverterFactory;

/**
 * Implementation of JSON input data converter processor.
 * This obtains input data and calls factory method to convert it into desired format.
 */
public class JSONInputConverterImpl extends JSONInputConverter {

	private final static Scanner scanner = new Scanner(System.in);
	
	/**
	 * This calls required methods to do the conversion including pre processing, post processing methods.
	 */
	@Override
	public void doConversionProcess() {
		//String inpDat = getInputData();
		String[] inpDat = getInputData();
		String outFmt = getOutputFormat();
		boolean isValid = true;
		isValid = validateJSONInput(inpDat[0]);
		if(!isValid) {
			System.out.println(INFO_01_DESC);
		} else {
			try {
				convertInputContent(inpDat[0],inpDat[1],outFmt);
				printConvertedOutput(inpDat[1]);
			} catch(ContentConverterException ex){
				ex.printStackTrace();
				System.out.println(INFO_02_DESC);
			}
		}
		
	}
	
	/**
	 * This gets the input details either as input message or file details.
	 */
	@Override
	public String[] getInputData() {
		String inpDat[] = new String[2];
		String inpFilName = null;
		String outFilName = null;
		while(true){
			System.out.println(INFO_05_DESC);
			inpFilName = scanner.nextLine();
			if(inpFilName == null || inpFilName.trim().length() == 0 ){
				System.out.println(INFO_03_DESC);
			} else {
				System.out.println(INFO_09_DESC);
				if(new File(inpFilName).isFile()) {
					inpDat[0] = inpFilName;
					break;
				} else {
					System.out.println(INFO_10_DESC);
					//return inpDat;
				}
			}
		}
		while(true){
			System.out.println(INFO_06_DESC);
			outFilName = scanner.nextLine();
			if(outFilName == null || outFilName.trim().length() == 0 ){
				System.out.println(INFO_03_DESC);
			} else {
				inpDat[1] = outFilName;
				break;
			}
		}
		return inpDat;
	}

	/**
	 * This gets the output format from user to which content has to be converted.
	 */
	@Override
	public String getOutputFormat() {
		String outFmt = null;
		while(true){
			System.out.println(INFO_06_DESC);
			//outFmt = scanner.nextLine();
			outFmt = XML_OUTPUT_FORMAT;
			if(outFmt == null || outFmt.trim().length() == 0 ){
				System.out.println(INFO_04_DESC);
			} else {
				break;
			}
		}
		return outFmt;
	}
	
	/**
	 * This calls the factory method based on input and output format to do conversion.
	 * @param inpDat - Input Data to be converted
	 * @param outFmt - Desired format of the converted input
	 * @return String - Converted data
	 */
	@Override
	public void convertInputContent(String inpFilName, String outFilName, String outFmt)throws ContentConverterException {
		ConverterFactory converter = new ConverterFactory();
		converter.createXMLJSONConverter(INPUT_FORMAT, outFmt).convertJSONtoXML(inpFilName,outFilName);
	}

	/**
	 * Prints the converted content.
	 */
	@Override
	public void printConvertedOutput(String outFile) {
		System.out.println(INFO_07_DESC);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(outFile)));
			String eachLine = reader.readLine();
			while(eachLine != null) {
				System.out.println(eachLine);
				eachLine = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println(ERROR_108_DESC);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(ERROR_109_DESC);
		}
		/*System.out.println(INFO_07_DESC);
		System.out.println(INFO_08_DESC);
		System.out.println(outDat);
		System.out.println(INFO_08_DESC);*/
	}

	
}
