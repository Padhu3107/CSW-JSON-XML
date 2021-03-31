package com.csw.sample.contentconverter.output.xml;

import javax.json.JsonValue;
import javax.xml.stream.XMLStreamWriter;

import com.csw.sample.contentconverter.ContentConverterException;

/**
 * This is XML to JSON specific converter defines the method 
 * to create XML file.
 *
 */
public interface XMLJSONConverterI {
	
	public final String OUTPUT_FORMAT = "XML";
	public final String OBJECT_ELEMENT = "object";
	public final String ARRAY_ELEMENT = "array";
	public final String STRING_ELEMENT = "string";
	public final String NUMBER_ELEMENT = "number";
	public final String BOOLEAN_ELEMENT = "boolean";
	public final String NULL_ELEMENT = "null";
	public final String ATTRIBUTE_KEYNAME = "name";
	
	/**
	 * Converts JSON to XML
	 * @return 
	 * @throws ContentConverterException
	 */
	void convertJSONtoXML(String inpJson, String outXml) throws ContentConverterException;
	
//	/**
//	 * Creates XML document
//	 * @return 
//	 * @throws ContentConverterException
//	 */
//	XMLStreamWriter createXmlDoc(String outXml) throws ContentConverterException;
//
//	/**
//	 * Create XML element
//	 * @param elementName - Name of the element tag in XML
//	 * @throws ContentConverterException
//	 */
//	void startElement(String elementName,XMLStreamWriter xmlStreamWriter) throws ContentConverterException;
//	
//	/**
//	 * Create XML attribute of an element
//	 * @param attributeName - Attribute name
//	 * @param attributeValue - Attribute Value
//	 * @throws ContentConverterException
//	 */
//	void createAttribute(String attributeName, String attributeValue,XMLStreamWriter xmlStreamWriter) throws ContentConverterException;
//	
//	/**
//	 * Create element content
//	 * @param text - XML content inside the element
//	 * @throws ContentConverterException
//	 */
//	void createCharacters(String text,XMLStreamWriter xmlStreamWriter) throws ContentConverterException;
//	
//	/**
//	 * Closes the element tag
//	 * @throws ContentConverterException
//	 */
//	void endElement(XMLStreamWriter xmlStreamWriter) throws ContentConverterException;
//	
//	/**
//	 * Completes XML document creation
//	 * @throws ContentConverterException
//	 */
//	void endXmlDoc(XMLStreamWriter xmlStreamWriter) throws ContentConverterException;
//	
//	/**
//	 * Parses JSON structure to create XML
//	 */
//	void navigateJsonTree(JsonValue tree, String key, XMLStreamWriter xmlStreamWriter) throws ContentConverterException;
}
