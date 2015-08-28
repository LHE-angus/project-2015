package com.ebiz.mmt.web.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFExcel {

	public void exportExcel(List<Object> ds, OutputStream out) {
		exportExcel(null, ds, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<Object> ds, OutputStream out) {
		exportExcel(headers, ds, out, "yyyy-MM-dd");
	}

	private void exportExcel(String[] headers, Collection<Object> ds, OutputStream out, String pattern) {

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
				Field[] fields = null;
				Object obj = tempds.get(s);
				fields = obj.getClass().getDeclaredFields();
				row = sheet.createRow(s + 1);
				for (int i = 0; i < fields.length; i++) {
					Field f = fields[i];
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
					HSSFCell cell = row.createCell(i);
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

}