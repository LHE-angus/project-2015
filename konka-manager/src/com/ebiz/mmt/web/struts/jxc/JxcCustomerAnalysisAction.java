package com.ebiz.mmt.web.struts.jxc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.StdEntpUser;

/**
 * @author Li,Ka
 * @version 2011-11-23
 */
public class JxcCustomerAnalysisAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");
		String name_like = (String) dynaBean.get("name_like");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		Integer year = c.get(Calendar.YEAR);
		Integer month = c.get(Calendar.MONTH);
		Calendar e = Calendar.getInstance();
		e.set(year, month, 1);
		Date date_st = e.getTime();
		Date date_en = c.getTime();

		if (StringUtils.isBlank(add_date_st)) {
			add_date_st = df.format(date_st);
		}
		if (StringUtils.isBlank(add_date_en)) {
			add_date_en = df.format(date_en);
		}

		if (StringUtils.isNotBlank(add_date_st) && StringUtils.isNotBlank(add_date_en)) {
			
			request.setAttribute("not_validate_record", "true");
			StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
			if (null == user) {
				return null;
			}
			dynaBean.set("keySeq", keySeq);
			Long shop_id = user.getStdEntpMain().getShop_id();

			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_st);
			entity.getMap().put("add_date_en", add_date_en);
			entity.getMap().put("name_like", name_like);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService().getJxcCusAnalysisResultForList(
					entity);

			if (null != list && list.size() > 0) {
				for (JxcSellBillDetails jsbd : list) {
					BigDecimal xssr_cus = (BigDecimal) jsbd.getMap().get("xssr_cus");
					BigDecimal xscb_cus = (BigDecimal) jsbd.getMap().get("xscb_cus");
					Double ml = xssr_cus.doubleValue() - xscb_cus.doubleValue();
					Double rate = ml / xssr_cus.doubleValue();
					jsbd.getMap().put("ml", ml);
					jsbd.getMap().put("rate", rate);
				}
			}
			request.setAttribute("search", "search");
			request.setAttribute("entityList", list);
		}

		dynaBean.set("add_date_st", add_date_st);
		dynaBean.set("add_date_en", add_date_en);
		dynaBean.set("name_like", name_like);
		return mapping.findForward("list");
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException, Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = this.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		String add_date_st = (String) dynaBean.get("add_date_lt");
		String add_date_en = (String) dynaBean.get("add_date_gt");
		//String name_like = new String(request.getParameter("name_like").getBytes("ISO-8859-1"), "UTF-8");
		String name_like = new String(((String) dynaBean.get("name_like")).getBytes("ISO-8859-1"), "UTF-8");
		Long shop_id = user.getStdEntpMain().getShop_id();

		if (StringUtils.isNotBlank(add_date_st) && StringUtils.isNotBlank(add_date_en)) {
			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_st);
			entity.getMap().put("add_date_en", add_date_en);
			entity.getMap().put("name_like", name_like);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService().getJxcCusAnalysisResultForList(
					entity);
			createExcelFileForList(request, response, list, add_date_st, add_date_en);
		} else {
			super.renderJavaScript(response, "alert('请选择日期并提交！');history.back();");
		}
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBillDetails> list, String add_date_st, String add_date_en) throws RowsExceededException,
			WriteException, IOException {
		if (list == null || list.size() < 0) {
			return;
		}

		WritableWorkbook wwb = null;
		String sFileName = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 获取系统实际路径
		String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		File fDownloadExcel = new File(SystemPath + "files/download_excel/");
		if (!fDownloadExcel.exists()) {
			FileUtils.forceMkdir(fDownloadExcel);
		}
		// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		wwb = Workbook.createWorkbook(new File(SystemPath + "files/download_excel/" + sFileName));
		if (wwb != null) {
			WritableCellFormat cellFormatRight = new WritableCellFormat();
			cellFormatRight.setAlignment(Alignment.RIGHT);
			WritableCellFormat cellFormatCenter = new WritableCellFormat();
			cellFormatCenter.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormatLeft = new WritableCellFormat();
			cellFormatLeft.setAlignment(Alignment.LEFT);
			WritableCellFormat wc = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("0"));
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			WritableCellFormat pa = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("0.00%"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet("分销客户汇总毛利分析报表", 0);
			int row = 0;// 行
			// int column = 0;// 列

			sheet.mergeCells(0, row, 5, row);// 合并单元格(左列，左行，右列，右行)
												// 合并第row行，从第0列到第7列
			String info0 = "分销客户汇总毛利分析报表";
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatCenter);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 5, row);
			String info1 = "查询时间：".concat(add_date_st).concat(" 至  ").concat(add_date_en);
			Label label = new Label(0, row, info1);
			label.setCellFormat(cellFormatRight);
			sheet.addCell(label);

			row++;
			sheet.mergeCells(0, row, 0, row);
			Label label1 = new Label(0, row, "客户名称");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);
			sheet.mergeCells(1, row, 1, row);
			Label label2 = new Label(1, row, "商品数量");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(2, row, 2, row);
			Label label3 = new Label(2, row, "销售成本");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(3, row, 3, row);
			Label label4 = new Label(3, row, "销售收入");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(4, row, 4, row);
			Label label5 = new Label(4, row, "毛利");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(5, row, 5, row);
			Label label6 = new Label(5, row, "毛利率");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);

			row++;
			for (int i = 0; i < list.size(); i++) {
				JxcSellBillDetails entity = list.get(i);
				sheet.addCell(new Label(0, row, (String) entity.getMap().get("name")));

				BigDecimal xssl_cus = (BigDecimal) entity.getMap().get("xssl_cus");
				if (xssl_cus != null) {
					sheet.addCell(new jxl.write.Number(1, row, xssl_cus.doubleValue(), wc));
				} else {
					sheet.addCell(new Label(1, row, "0"));
				}

				BigDecimal xscb_cus = (BigDecimal) entity.getMap().get("xscb_cus");
				if (xscb_cus != null) {
					sheet.addCell(new jxl.write.Number(2, row, xscb_cus.doubleValue(), wcf));
				} else {
					sheet.addCell(new jxl.write.Number(2, row, 0, wcf));
				}

				BigDecimal xssr_cus = (BigDecimal) entity.getMap().get("xssr_cus");
				if (xssr_cus != null) {
					sheet.addCell(new jxl.write.Number(3, row, xssr_cus.doubleValue(), wcf));
				} else {
					sheet.addCell(new jxl.write.Number(3, row, 0, wcf));
				}

				Double ml = xssr_cus.doubleValue() - xscb_cus.doubleValue();
				sheet.addCell(new jxl.write.Number(4, row, ml, wcf));

				if (xssr_cus.doubleValue() != 0) {
					Double rate = ml / xssr_cus.doubleValue();
					sheet.addCell(new jxl.write.Number(5, row, rate, pa));
				} else {
					sheet.addCell(new Label(5, row, "--"));
				}

				row++;
			}

			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + sFileName);
		FileInputStream fileInputStream = new FileInputStream(SystemPath + "files/download_excel/" + sFileName);
		OutputStream out = response.getOutputStream();
		int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}
	
	/** 打印 */
	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");

		StdEntpUser user = this.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		request.setAttribute("userName", user.getStdEntpMain().getLinkman());
		request.setAttribute("entpName", user.getStdEntpMain().getEntp_name());
		request.setAttribute("tel", user.getStdEntpMain().getTel());
		request.setAttribute("add_date_gt", add_date_gt);
		request.setAttribute("add_date_lt", add_date_lt);
		
		//String name_like = new String(request.getParameter("name_like").getBytes("ISO-8859-1"), "UTF-8");
		String name_like = new String(((String) dynaBean.get("name_like")).getBytes("ISO-8859-1"), "UTF-8");
		//String name_like = (String) dynaBean.get("name_like");
		Long shop_id = user.getStdEntpMain().getShop_id();

		if (StringUtils.isNotBlank(add_date_lt) && StringUtils.isNotBlank(add_date_gt)) {
			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_lt);
			entity.getMap().put("add_date_en", add_date_gt);
			entity.getMap().put("name_like", name_like);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService()
					.getJxcCusAnalysisResultForList(entity);

			if (null != list && list.size() > 0) {
				for (JxcSellBillDetails jsbd : list) {
					BigDecimal xssr_cus = (BigDecimal) jsbd.getMap().get("xssr_cus");
					BigDecimal xscb_cus = (BigDecimal) jsbd.getMap().get("xscb_cus");
					Double ml = xssr_cus.doubleValue() - xscb_cus.doubleValue();
					Double rate = ml / xssr_cus.doubleValue();
					jsbd.getMap().put("ml", ml);
					jsbd.getMap().put("rate", rate);
				}
			}
			request.setAttribute("querydate", queryDate(add_date_lt, add_date_gt));
			request.setAttribute("search", "search");
			request.setAttribute("entityList", list);
		}
		return new ActionForward("/../jxc/JxcCustomerAnalysis/_print_fxkhmlfx_list.jsp");
	}
}
