package com.ebiz.mmt.domain.support;

import org.json.JSONObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author XingXiuDong
 * @date 2012-08-30
 */
public class TableUtils {
	private static XStream xstream;
	
	static {
		xstream = new XStream(new DomDriver());
		xstream.alias("table", Table.class);
		xstream.alias("td", Td.class);
		xstream.alias("tr", Tr.class);
		xstream.alias("thead", Thead.class);
		xstream.alias("tbody", Tbody.class);
	}

	public static String toJson(Table t) {
		JSONObject json = new JSONObject(t);
		return json.toString();
	}
	
	public static String toXml(Table t) {
		return xstream.toXML(t);
	}

}
