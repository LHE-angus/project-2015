package com.ebiz.ssi.ckeditor;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jiang,JiaYong
 * @version 2011-11-11
 */
public class PropertiesLoader {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

	private static Properties properties = new Properties();

	static {
		InputStream in = PropertiesLoader.class.getResourceAsStream("/ckeditor.properties");

		if (in == null) {
			logger.info("ckeditor.properties not found");
		} else {
			if (!(in instanceof BufferedInputStream))
				in = new BufferedInputStream(in);
			try {
				properties.load(in);
				in.close();
				logger.debug("ckeditor.properties loaded");
			} catch (Exception e) {
				logger.error("Error while processing ckeditor.properties");
				throw new RuntimeException("Error while processing ckeditor.properties", e);
			}
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}
}