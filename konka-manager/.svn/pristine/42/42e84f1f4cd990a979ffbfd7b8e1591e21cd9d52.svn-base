package com.ebiz.mmt.web.struts.manager.zmd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxLogistics;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,ShangLong
 * @version 2011-08-03
 */
public class LogisticsTemplateImportAction extends BaseZmdAction {

	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		ctxDir = ctxDir + "files/template/excel/data_template.xls";
		File file = new File(ctxDir);
		int i = 0;

		BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload;charset=GBK");
		response.addHeader("Content-Disposition", "attachment;filename=" + "data_template.xls");

		OutputStream out = response.getOutputStream();
		// response.flushBuffer();

		byte[] b = new byte[4096];
		while ((i = br.read(b)) > 0)
			out.write(b, 0, i);

		out.flush();
		out.close();

		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward importData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());

		MultipartRequestHandler handler = form.getMultipartRequestHandler();
		Hashtable fileh = handler.getFileElements();
		if (fileh.size() == 0) {
			return this.unspecified(mapping, form, request, response);
		}
		List<Integer> invalidRows = new ArrayList<Integer>();
		List<Integer> repeatRows = new ArrayList<Integer>();
		int numberOfValidRows = 0;

		for (Enumeration e = fileh.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();

			FormFile formFile = (FormFile) fileh.get(key);

			if (formFile.getContentType().indexOf("/vnd.ms-excel") == -1) {
				saveError(request, "konka.xx.logistics.excel.no");
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append("/zmd/KonkaXxLogistics.do?method=list");
				pathBuffer.append("&mod_id=" + mod_id);
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				return forward;
			}
			InputStream ins = formFile.getInputStream();

			// 此处的输入流应该是从文件上传中得到
			// FileInputStream fis = new FileInputStream("D:\\test.xls");
			HSSFWorkbook wb = new HSSFWorkbook(ins);
			int sheetsCount = wb.getNumberOfSheets();

			if (sheetsCount < 1) {
				saveError(request, "konka.xx.logistics.excel.sheet.no");
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append("/zmd/KonkaXxLogistics.do?method=list");
				pathBuffer.append("&mod_id=" + mod_id);
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				return forward;
			}

			// 读第一个sheet
			HSSFSheet sheet = wb.getSheetAt(0);

			// 总行数，包括空白行
			int rows = sheet.getLastRowNum();

			HSSFRow firstRow = sheet.getRow(1);

			if (null == firstRow || rows < 1) {
				continue;
			}

			// 第一行的列数
			int headerCells = sheet.getRow(0).getPhysicalNumberOfCells();

			// 第一行列数不等于4
			if (headerCells != 4) {
				continue;
			}

			for (int r = 1; r < rows + 1; r++) {
				try {
					HSSFRow row = sheet.getRow(r);
					if (row == null || 0 == row.getPhysicalNumberOfCells()) {
						continue;
					}

					KonkaXxLogistics entity = new KonkaXxLogistics();
					if (kDept != null)
						entity.setDept_id(kDept.getDept_id());
					entity.setIs_del(0);

					// 0.发货地区编码
					HSSFCell cell0 = row.getCell(0);

					String value0 = null;
					switch (cell0.getCellType()) {

					case HSSFCell.CELL_TYPE_NUMERIC:
						value0 = String.valueOf((long) cell0.getNumericCellValue());
						break;

					case HSSFCell.CELL_TYPE_STRING:
						value0 = cell0.getRichStringCellValue().getString();
						break;
					default:
					}
					if (!GenericValidator.isLong(replaceBlank(value0))) {
						throw new Exception();
					}
					entity.setP_index_s(Long.valueOf(replaceBlank(value0)));

					// 1.收货地区编码
					HSSFCell cell1 = row.getCell(1);

					String value1 = null;
					switch (cell1.getCellType()) {

					case HSSFCell.CELL_TYPE_NUMERIC:
						value1 = String.valueOf((long) cell1.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING:
						value1 = cell1.getRichStringCellValue().getString();
						break;
					default:
					}
					if (!GenericValidator.isLong(replaceBlank(value1))) {
						throw new Exception();
					}
					entity.setP_index_e(Long.valueOf(replaceBlank(value1)));

					// 2.产品型号
					HSSFCell cell2 = row.getCell(2);

					String value2 = null;
					switch (cell2.getCellType()) {

					case HSSFCell.CELL_TYPE_NUMERIC:
						value2 = String.valueOf((long) cell2.getNumericCellValue());
						break;

					case HSSFCell.CELL_TYPE_STRING:
						value2 = cell2.getRichStringCellValue().getString();
						break;
					default:
					}
					if (StringUtils.isEmpty(replaceBlank(value2))) {
						throw new Exception();
					}
					entity.setMd_name(replaceBlank(value2));

					Long recordCount = super.getFacade().getKonkaXxLogisticsService().getKonkaXxLogisticsCount(entity);

					// 查询数据是否存在
					if (recordCount > 0L) {
						repeatRows.add(r + 1);
						continue;
					}

					// 3.物流费用
					HSSFCell cell3 = row.getCell(3);

					String value3 = null;
					switch (cell3.getCellType()) {

					case HSSFCell.CELL_TYPE_NUMERIC:
						value3 = String.valueOf(cell3.getNumericCellValue());
						break;

					case HSSFCell.CELL_TYPE_STRING:
						value3 = cell3.getRichStringCellValue().getString();
						break;
					default:
					}
					if (StringUtils.isEmpty(replaceBlank(value3))) {
						throw new Exception();
					}
					entity.setFee(new BigDecimal(Double.valueOf(replaceBlank(value3))));

					// 增加新的数据到购买记录表中
					super.getFacade().getKonkaXxLogisticsService().createKonkaXxLogistics(entity);
					numberOfValidRows++;

				} catch (Exception ne) {
					invalidRows.add(r + 1);
				}
			}
			ins.close();
		}

		int numberOfInvalidRows = invalidRows.size();
		int numberOfRepeatRows = repeatRows.size();
		String invalideRowsIndex = null;
		String repeatRowsIndex = null;
		if (numberOfInvalidRows > 0) {
			for (Integer i : invalidRows) {
				invalideRowsIndex = invalideRowsIndex + "," + i;
			}
			invalideRowsIndex = invalideRowsIndex.substring(invalideRowsIndex.indexOf(",") + 1);
		} else {
			invalideRowsIndex = " ";
		}
		if (numberOfRepeatRows > 0) {
			for (Integer i : repeatRows) {
				repeatRowsIndex = repeatRowsIndex + "," + i;
			}
			repeatRowsIndex = repeatRowsIndex.substring(repeatRowsIndex.indexOf(",") + 1);
		} else {
			repeatRowsIndex = " ";
		}

		if (numberOfInvalidRows == 0 && numberOfRepeatRows == 0) {
			String[] messages = new String[] { numberOfValidRows + "", (numberOfInvalidRows + numberOfRepeatRows) + "" };
			super.saveMessage(request, "konka.xx.logistics.up.data.message_3", messages);
		} else if (numberOfInvalidRows == 0) {
			String[] messages = new String[] { numberOfValidRows + "", numberOfRepeatRows + "", repeatRowsIndex };
			super.saveMessage(request, "konka.xx.logistics.up.data.message_2", messages);
		} else if (numberOfRepeatRows == 0) {
			String[] messages = new String[] { numberOfValidRows + "", numberOfInvalidRows + "", invalideRowsIndex };
			super.saveMessage(request, "konka.xx.logistics.up.data.message_1", messages);
		} else {
			String[] messages = new String[] { numberOfValidRows + "", (numberOfRepeatRows + numberOfInvalidRows) + "",
					invalideRowsIndex, repeatRowsIndex };
			super.saveMessage(request, "konka.xx.logistics.up.data.message_0", messages);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/zmd/KonkaXxLogistics.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	private String replaceBlank(String string) {
		if (null == string) {
			return null;
		}
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(string);
		String after = m.replaceAll("");
		return after;
	}

}
