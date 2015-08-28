package com.ebiz.mmt.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListVerify {

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("456");
		list.add("555");
		list.add("123");
		list.add("444");
		list.add("123");
		list.add("444");

		// 输出原 List 的内容
		for (int i = 0; i < list.size(); i++) {
			//System.out.printf("%2d --> %s%n", i, list.get(i));
		}
		//System.out.println("=============");

		// 输出查找重复元素的内容
		int[] indexArray = reduplicateIndex(list, "000");
		for (int index : indexArray) {
			//System.out.printf("%2d --> %s%n", index, list.get(index));
		}
	}

	public static <T> int[] reduplicateIndex(List<T> list, T str) throws Exception {
		List<T> tmp = new ArrayList<T>(list);
		int[] index = new int[Collections.frequency(list, str)];
		int start = tmp.indexOf(str);
		int end = tmp.lastIndexOf(str);
		int i = 0;
		if (start < 0) {
			throw new Exception("数组中不存在 " + str + " 元素！");
		}
		index[i] = start;
		while (start != end) {
			index[++i] = end;
			tmp = tmp.subList(0, end);
			end = tmp.lastIndexOf(str);
		}
		Arrays.sort(index);
		return index;
	}
}
