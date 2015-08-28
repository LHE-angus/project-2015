package com.ebiz.mmt.web.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFExcel2 {

	public void exportExcel(String[] exportFields, Collection<Object> ds, OutputStream out) {
		exportExcel(null, exportFields, ds, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, String[] exportFields, Collection<Object> ds, OutputStream out) {
		exportExcel(headers, exportFields, ds, out, "yyyy-MM-dd");
	}

	private void exportExcel(String[] headers, String[] exportFields, Collection<Object> ds, OutputStream out,
			String pattern) {

		if (exportFields == null) {
			throw new IllegalArgumentException("exportFields cannot be empty!");
		}

		List<Object> dslist = new ArrayList<Object>();
		Iterator<Object> d = ds.iterator();
		while (d.hasNext()) {
			dslist.add(d.next());
		}
		int total = ds.size();
		// 最大可写行数为65536,空一行给列头
		int sheetrows = 65535;
		int sheets = total / sheetrows;
		if (total % sheetrows != 0) {
			sheets = sheets + 1;
		}

		LinkedList<String> outFileds = new LinkedList<String>();
		for (int i = 0; i < exportFields.length; i++) {
			outFileds.add(exportFields[i]);
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		for (int r = 0; r < sheets; r++) {
			// 使用subList(from,to);
			int sindex = 0;
			int eindex = 0;
			sindex = r * sheetrows;
			eindex = (r + 1) * sheetrows;
			if (eindex > total) {
				eindex = total;
			}
			// start
			HSSFSheet sheet = workbook.createSheet("Sheet" + r);
			// 第一行总是空行
			HSSFRow row = sheet.createRow(0);
			if (headers != null && headers.length > 0) {
				for (int i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellValue(text);
				}
			}

			List<Object> tempds = dslist.subList(sindex, eindex);
			for (int s = 0; s < tempds.size(); s++) {
				row = sheet.createRow(s + 1);


				Object obj = tempds.get(s);
				Field[] fields = obj.getClass().getDeclaredFields();
				LinkedList<Field> rFields = new LinkedList<Field>();
				for (int i = 0; i < fields.length; i++) {
					String fname = fields[i].getName();
					if (this.strInList(fname, outFileds)) {
						rFields.add(fields[i]);
					}
				}

				for (int index = 0; index < rFields.size(); index++) {
					Field f = rFields.get(index);
					f.setAccessible(true);
					Object textValue = null;
					try {
						textValue = f.get(obj);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

					if (textValue instanceof Date) {
						Date date = (Date) textValue;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else {
						textValue = textValue == null ? "" : textValue;
					}
					HSSFCell cell = row.createCell(index);
					cell.setCellValue(String.valueOf(textValue));
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean strInList(String o, List<String> list) {
		if (o == null)
			return false;
		if (list == null || list.size() <= 0) {
			return false;
		}
		for (String s : list) {
			if (o.trim().equals(s.trim())) {
				return true;
			}
		}
		return false;
	}

}