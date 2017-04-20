package cn.eshore.mismp.util.xmlparser;

import java.io.*;

import org.apache.log4j.*;
import org.dom4j.*;
import org.dom4j.io.*;


/**
 * <p>Title: AbstractXMLParser.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="AbstractXMLParser.java"><i>View Source</i></a></p>
 * @author zhuosf
 * @version 1.0
 */
public abstract class AbstractXMLParser {
	
	private static final Logger Log = Logger.getLogger(AbstractXMLParser.class);

	protected static final String PARAMS = "params";

	/**
	 * Convert Document to XML String.
	 * 
	 * @param Document
	 * @return String
	 */
	protected static String document2String(Document document) {
		String s = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat();
			format.setEncoding(XMLConst.ENCODING_GB18030);
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString(XMLConst.ENCODING_GB18030);
		} catch (Exception ex) {
			Log.error("Convert document to xml string exception! ", ex);
		}
		return s;
	}
	
	/** 
	 * Convert XML String to Document.
	 * 
	 * @param String
	 * @return Document
	 */
	protected static Document string2Document(String s) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			Log.error("Convert xml string to ducument exception! ", ex);
		}
		return document;
	}
	
}
