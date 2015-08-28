package com.ebiz.mmt.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author Wu,Yang
 * @version 2010-06-13
 * @desc 去除字符串中的空格,回车,换行符,制表符
 */
public class ReplaceBlankUtils {

	public static String getReplaceBlankString(String str) {
		String regex = "\\s*|\t|\r|\n";
		Pattern p = Pattern.compile(regex);
		if (StringUtils.isNotBlank(str)) {
			Matcher m = p.matcher(str);
			String afterString = m.replaceAll("");
			return afterString;
		}
		return null;
	}

	public static void main(String[] args) {
		String s = ReplaceBlankUtils.getReplaceBlankString("I am Hello ok, \n new line wuyang!");
		// //System.out.print(s);
	}

}
