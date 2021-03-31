package com.csw.sample.contentconverter.output.xml;

import static com.csw.sample.contentconverter.constants.ContentConverterConstants.CAUSE_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_101_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_102_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_103_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_104_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_105_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_106_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.ERROR_107_DESC;
import static com.csw.sample.contentconverter.constants.ContentConverterConstants.OUT_FILE_NAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.csw.sample.contentconverter.ContentConverterException;

/**
 * Implementation of JSON input to XML file converter.
 * 
 */
public class XMLJSONConverterImpl implements XMLJSONConverterI {

	/**
	 * This creates XML document using StAX
	 * @throws ContentConverterException
	 */
	//@Override
	public XMLStreamWriter createXmlDoc(String outFile) throws ContentConverterException {
		XMLStreamWriter xmlStreamWriter = null;
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        try {
        	xmlStreamWriter =
			   xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(outFile), "UTF-8");
			xmlStreamWriter.writeStartDocument();
		} catch (XMLStreamException e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_102_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		} catch (Exception e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_102_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		}
		return xmlStreamWriter;
	}
	
	/**
	 * This creates XML element using StAX
	 * @throws ContentConverterException
	 */
	//@Override
	public void startElement(String elementName,XMLStreamWriter xmlStreamWriter) throws ContentConverterException {
		try {
			xmlStreamWriter.writeStartElement(elementName);
		} catch (XMLStreamException e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_103_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		}
	}
	
	/**
	 * This creates XML element attribute using StAX
	 * @throws ContentConverterException
	 */
	//@Override
	public void createAttribute(String attributeName, String attributeValue,XMLStreamWriter xmlStreamWriter) throws ContentConverterException {
		try {
			xmlStreamWriter.writeAttribute(attributeName, attributeValue);
		} catch (XMLStreamException e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_104_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		}
		
	}
	
	/**
	 * This creates XML element text using StAX
	 * @throws ContentConverterException
	 */
	//@Override
	public void createCharacters(String text,XMLStreamWriter xmlStreamWriter) throws ContentConverterException {
		try {
			xmlStreamWriter.writeCharacters(text);
		} catch (XMLStreamException e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_105_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		}
	}
	
	/**
	 * This completes XML element using StAX
	 * @throws ContentConverterException
	 */
	//@Override
	public void endElement(XMLStreamWriter xmlStreamWriter) throws ContentConverterException  {
		try {
			xmlStreamWriter.writeEndElement();
		} catch (XMLStreamException e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_106_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		}
		
	}

	/**
	 * This completes XML document using StAX
	 * @throws ContentConverterException
	 */
	//@Override
	public void endXmlDoc(XMLStreamWriter xmlStreamWriter) throws ContentConverterException {
		try {
			xmlStreamWriter.writeEndDocument();
			xmlStreamWriter.flush();
			xmlStreamWriter.close();
		} catch (XMLStreamException e) {
			//e.printStackTrace();
			throw new ContentConverterException(new StringBuilder(ERROR_107_DESC)
			.append(e.getMessage())
			.append(CAUSE_DESC)
			.append(e.getCause()));
		}
	}

	/**
	 * This reads input JSON and calls necessary method to complete XML document using StAX
	 * @param inpMsg - JSON Data
	 * @throws ContentConverterException
	 */
	@Override
	public void convertJSONtoXML(String inpJson, String outXml)throws ContentConverterException {
		File inpFile = new File(inpJson);
		BufferedReader reader = null;
		StringBuffer inpMsgBuf = null;
		String inpMsg = null;
		try {
			reader = new BufferedReader(new FileReader(inpFile));
			inpMsg = reader.readLine();
			inpMsgBuf = new StringBuffer();
			while(inpMsg != null){
				inpMsgBuf.append(inpMsg);
				inpMsg = reader.readLine();
			}
			inpMsg = inpMsgBuf.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(inpMsg == null || inpMsg.trim().length() == 0) {
			throw new ContentConverterException(new StringBuilder(ERROR_101_DESC));
		} else {
			inpMsg = inpMsg.trim();
		}
		XMLStreamWriter xmlStreamWriter = null;
		JsonReader jsonReader = Json.createReader(new StringReader (inpMsg));
		JsonStructure jsonStructure = jsonReader.read();
		xmlStreamWriter = createXmlDoc(outXml);
		navigateJsonTree(jsonStructure, null, xmlStreamWriter);
		endXmlDoc(xmlStreamWriter);
	}
	
	/**
	 * This iterates JSON structure and completes XML document using StAX
	 * @param inpMsg - JSON Data
	 * @throws ContentConverterException
	 */
	//@Override
	public void navigateJsonTree(JsonValue tree, String key, XMLStreamWriter xmlStreamWriter) throws ContentConverterException {
		   
			switch(tree.getValueType()) {
		      case OBJECT:
		         startElement(OBJECT_ELEMENT,xmlStreamWriter);
		         JsonObject object = (JsonObject) tree;
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
		         for (String name : object.keySet()) {
		        	 navigateJsonTree(object.get(name), name, xmlStreamWriter);
		         }
				 endElement(xmlStreamWriter);
		         break;
		         
		      case ARRAY:
				 startElement(ARRAY_ELEMENT,xmlStreamWriter);
		         JsonArray array = (JsonArray) tree;
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
		         for (JsonValue val : array) {
		        	 navigateJsonTree(val, null, xmlStreamWriter);
		         }
				 endElement(xmlStreamWriter);
		         break;
		         
		      case STRING:
				 startElement(STRING_ELEMENT,xmlStreamWriter);
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
		         JsonString string = (JsonString) tree;
		         createCharacters(string.getString(), xmlStreamWriter);
				 endElement(xmlStreamWriter);
		         break;
		         
		      case NUMBER:
				 startElement(NUMBER_ELEMENT,xmlStreamWriter);
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
		         JsonNumber num = (JsonNumber) tree;
		         createCharacters(num.toString(), xmlStreamWriter);
				 endElement(xmlStreamWriter);
		         break;
		         
		      case TRUE:
		    	 startElement(BOOLEAN_ELEMENT,xmlStreamWriter);
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
		         createCharacters("true", xmlStreamWriter);
				 endElement(xmlStreamWriter);
				 break;
				 
		      case FALSE:
		    	 startElement(BOOLEAN_ELEMENT,xmlStreamWriter);
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
		         createCharacters("false", xmlStreamWriter);
				 endElement(xmlStreamWriter);
				 break;
				 
		      case NULL:
		         startElement(NULL_ELEMENT,xmlStreamWriter);
		         if(key != null) {
		        	 createAttribute(ATTRIBUTE_KEYNAME, key,xmlStreamWriter);
		         }
				 endElement(xmlStreamWriter);
		         break;
		         
		       default:
		    	   break;
		   }
		}
}
