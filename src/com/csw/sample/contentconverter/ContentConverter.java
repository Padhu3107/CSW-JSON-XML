package com.csw.sample.contentconverter;

/**
 * Interface for content converter from one format to another format
 */
public interface ContentConverter {

	public final static String XML_OUTPUT_FORMAT = "XML";
	/**
	 * Abstract method convert the data into desired format
	 */
	public void doConversionProcess();
	
	/**
	 * Abstract method to be implemented to obtain input message
	 * from the command line input via user or any other implementing API
	 * @return - Input data in any format
	 */
	public String[] getInputData();
	
	/**
	 * Abstract method to be implemented to obtain output format
	 * from the command line input via user or any other implementing API
	 * @return - Output format to which data has to be converted
	 */
	public String getOutputFormat();
	
	/**
	 * Abstract method to be implemented to convert
	 * input data obtained from the command line input via file or any other implementing API
	 * to the given output format
	 * @param inpMsg - Input data in any format
	 * @param outFmt - Output data format
	 * @throws ContentConverterException 
	 */
	public void convertInputContent(String inpFile, String outFile, String outFmt) throws ContentConverterException;
	
	/**
	 * Prints out the converted data
	 * @param outMsg- Converted data in the given output format
	 */
	public void printConvertedOutput(String outDat);
	
}
