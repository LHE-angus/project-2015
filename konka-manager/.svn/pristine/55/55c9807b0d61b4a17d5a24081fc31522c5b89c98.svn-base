package com.ebiz.mmt.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtil {

	private static Logger log = LoggerFactory.getLogger(SerializeUtil.class);

	public static byte[] objectToByteArray(Object obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (IOException e) {
			log.info("对象序列化失败！\n" + e.getMessage());
			return null;
		}
	}

	public static Object byteArrayToObject(byte[] arr) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(arr);
			ObjectInputStream ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (IOException e) {
			log.info(e.getMessage());
			return null;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

}
