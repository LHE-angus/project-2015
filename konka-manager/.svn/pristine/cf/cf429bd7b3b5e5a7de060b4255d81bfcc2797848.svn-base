package com.ebiz.mmt.web.struts.jxcnokey;

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
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.domain.UserInfo;

/**
 * @author Ren Zhong
 * @version 2011-4-20
 */
@SuppressWarnings("unused")
public class JxcSellDataAnalysisAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_id = (String) dynaBean.get("brand_id");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date now = new Date();
		Calendar n = Calendar.getInstance();
		n.setTime(now);
		Integer n_year = n.get(Calendar.YEAR);
		request.setAttribute("years", n_year);

		if (StringUtils.isBlank(year)) {
			year = String.valueOf(n.get(Calendar.YEAR));
		}
		if (StringUtils.isBlank(month)) {
			month = String.valueOf(n.get(Calendar.MONTH));
		}

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			
			request.setAttribute("not_validate_record", "true");
			UserInfo user = super.getUserInfoFromWebService(request, response);
			if (null == user) {
				return null;
			}
			dynaBean.set("keySeq", keySeq);
			Long shop_id = user.getEntp_shop().getShop_id();

			// 取当月第一天
			Calendar c = Calendar.getInstance();
			c.set(Integer.valueOf(year), Integer.valueOf(month), 1);
			String add_date_st = df.format(c.getTime());

			// 取当月最后一天
			Calendar d = Calendar.getInstance();
			d.set(Integer.valueOf(year), Integer.valueOf(month) + 1, 1);
			Date dd = d.getTime();
			Date dd_last = DateUtils.addDays(dd, -1);
			String add_date_en = df.format(dd_last);

			// 取上月时间
			Date date_st = DateUtils.parseDate(add_date_st, new String[] { "yyyy-MM-dd" });// 当前月第一天
			// Date date_en = DateUtils.parseDate(add_date_en, new
			// String[]{"yyyy-MM-dd"});//当前月最后一天
			Date last_month_st_d = DateUtils.addMonths(date_st, -1);
			String last_month_st = df.format(last_month_st_d);
			Date last_month_en_d = DateUtils.addDays(date_st, -1);
			String last_month_en = df.format(last_month_en_d);

			// 取上年时间
			Calendar e = Calendar.getInstance();
			e.set(Integer.valueOf(year) - 1, Integer.valueOf(month), 1);
			String last_year_st = df.format(e.getTime());

			Calendar f = Calendar.getInstance();
			f.set(Integer.valueOf(year) - 1, Integer.valueOf(month) + 1, 1);
			Date ff = f.getTime();
			Date ff_last = DateUtils.addDays(ff, -1);
			String last_year_en = df.format(ff_last);

			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_st);
			entity.getMap().put("add_date_en", add_date_en);
			entity.getMap().put("last_month_st", last_month_st);
			entity.getMap().put("last_month_en", last_month_en);
			entity.getMap().put("last_year_st", last_year_st);
			entity.getMap().put("last_year_en", last_year_en);
			entity.getMap().put("pd_type", pd_type);
			entity.getMap().put("brand_id", brand_id);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService().getJxcDataAnalysisResultForList(
					entity);
			if (null != list && list.size() > 0) {
				for (JxcSellBillDetails jsbd : list) {
					Double count_rate = null;
					BigDecimal b_count = (BigDecimal) jsbd.getMap().get("b_count");
					if (null != jsbd.getMap().get("tot_count")) {
						BigDecimal tot_count = (BigDecimal) jsbd.getMap().get("tot_count");
						if (tot_count.doubleValue() != 0) {
							count_rate = b_count.doubleValue() / tot_count.doubleValue(); // 当月销量占比
						}
					}
					jsbd.getMap().put("count_rate", count_rate);

					Double money_rate = null;
					BigDecimal b_m = (BigDecimal) jsbd.getMap().get("b_m");
					if (null != jsbd.getMap().get("tot_money")) {
						BigDecimal tot_money = (BigDecimal) jsbd.getMap().get("tot_money");
						if (tot_money.doubleValue() != 0) {
							money_rate = b_m.doubleValue() / tot_money.doubleValue(); // 当月销额占比
						}
					}
					jsbd.getMap().put("money_rate", money_rate);

					// 销量环比
					Double hb_count_rate = null;
					BigDecimal last_month_count = (BigDecimal) jsbd.getMap().get("last_month_count");
					if (last_month_count.doubleValue() != 0) {
						hb_count_rate = (b_count.doubleValue() - last_month_count.doubleValue())
								/ last_month_count.doubleValue();
					}
					jsbd.getMap().put("hb_count_rate", hb_count_rate);

					// 销量同比
					Double tb_count_rate = null;
					BigDecimal last_year_count = (BigDecimal) jsbd.getMap().get("last_year_count");
					if (last_year_count.doubleValue() != 0) {
						tb_count_rate = (b_count.doubleValue() - last_year_count.doubleValue())
								/ last_year_count.doubleValue();
					}
					jsbd.getMap().put("tb_count_rate", tb_count_rate);

					// 销售额环比
					Double hb_money_rate = null;
					BigDecimal last_month_money = (BigDecimal) jsbd.getMap().get("last_month_money");
					if (last_month_money.doubleValue() != 0) {
						hb_money_rate = (b_m.doubleValue() - last_month_money.doubleValue())
								/ last_month_money.doubleValue();
					}
					jsbd.getMap().put("hb_money_rate", hb_money_rate);

					// 销售额同比
					Double tb_money_rate = null;
					BigDecimal last_year_money = (BigDecimal) jsbd.getMap().get("last_year_money");
					if (last_year_money.doubleValue() != 0) {
						tb_money_rate = (b_m.doubleValue() - last_year_money.doubleValue())
								/ last_year_money.doubleValue();
					}
					jsbd.getMap().put("tb_money_rate", tb_money_rate);
				}
			}
			request.setAttribute("search", "search");
			request.setAttribute("entityList", list);
		}

		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		dynaBean.set("year", year);
		dynaBean.set("month", month);
		return mapping.findForward("list");
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException, Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String pd_type = (String) dynaBean.get("pd_type");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Long shop_id = user.getEntp_shop().getShop_id();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			// 取当月第一天
			Calendar c = Calendar.getInstance();
			c.set(Integer.valueOf(year), Integer.valueOf(month), 1);
			String add_date_st = df.format(c.getTime());

			// 取当月最后一天
			Calendar d = Calendar.getInstance();
			d.set(Integer.valueOf(year), Integer.valueOf(month) + 1, 1);
			Date dd = d.getTime();
			Date dd_last = DateUtils.addDays(dd, -1);
			String add_date_en = df.format(dd_last);

			// 取上月时间
			Date date_st = DateUtils.parseDate(add_date_st, new String[] { "yyyy-MM-dd" });// 当前月第一天
			// Date date_en = DateUtils.parseDate(add_date_en, new
			// String[]{"yyyy-MM-dd"});//当前月最后一天
			Date last_month_st_d = DateUtils.addMonths(date_st, -1);
			String last_month_st = df.format(last_month_st_d);
			Date last_month_en_d = DateUtils.addDays(date_st, -1);
			String last_month_en = df.format(last_month_en_d);

			// 取上年时间
			Calendar e = Calendar.getInstance();
			e.set(Integer.valueOf(year) - 1, Integer.valueOf(month), 1);
			String last_year_st = df.format(e.getTime());

			Calendar f = Calendar.getInstance();
			f.set(Integer.valueOf(year) - 1, Integer.valueOf(month) + 1, 1);
			Date ff = f.getTime();
			Date ff_last = DateUtils.addDays(ff, -1);
			String last_year_en = df.format(ff_last);

			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_st);
			entity.getMap().put("add_date_en", add_date_en);
			entity.getMap().put("last_month_st", last_month_st);
			entity.getMap().put("last_month_en", last_month_en);
			entity.getMap().put("last_year_st", last_year_st);
			entity.getMap().put("last_year_en", last_year_en);
			entity.getMap().put("pd_type", pd_type);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService().getJxcDataAnalysisResultForList(
					entity);
			createExcelFileForList(request, response, list, year, month);
		} else {
			super.renderJavaScript(response, "alert('请选择日期并提交！');history.back();");
		}
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBillDetails> list, String year, String month) throws RowsExceededException, WriteException,
			IOException {
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
			WritableSheet sheet = wwb.createSheet("销售数据统计分析报表", 0);
			int row = 0;// 行
			// int column = 0;// 列

			sheet.mergeCells(0, row, 14, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			String info0 = "销售数据统计分析报表";
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatCenter);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 14, row);
			String info1 = ("查询时间：").concat(year).concat("年").concat(String.valueOf(Integer.valueOf(month) + 1))
					.concat("月");
			Label label = new Label(0, row, info1);
			label.setCellFormat(cellFormatRight);
			sheet.addCell(label);

			row++;
			sheet.mergeCells(0, row, 2, row);
			Label label1 = new Label(0, row, "基本信息");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);
			sheet.mergeCells(3, row, 6, row);
			Label label2 = new Label(3, row, "当月销售情况");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(7, row, 14, row);
			Label label3 = new Label(7, row, "环比、同比销售分析统计");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);

			row++;
			sheet.mergeCells(0, row, 0, row + 1);
			Label label4 = new Label(0, row, "序号");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(1, row, 1, row + 1);
			Label label5 = new Label(1, row, "产品类型");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(2, row, 2, row + 1);
			Label label6 = new Label(2, row, "品牌");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(3, row, 3, row + 1);
			Label label7 = new Label(3, row, "当月销量");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);
			sheet.mergeCells(4, row, 4, row + 1);
			Label label8 = new Label(4, row, "当月销额");
			label8.setCellFormat(cellFormatCenter);
			sheet.addCell(label8);
			sheet.mergeCells(5, row, 5, row + 1);
			Label label9 = new Label(5, row, "销量占比");
			label9.setCellFormat(cellFormatCenter);
			sheet.addCell(label9);
			sheet.mergeCells(6, row, 6, row + 1);
			Label label10 = new Label(6, row, "销额占比");
			label10.setCellFormat(cellFormatCenter);
			sheet.addCell(label10);

			sheet.mergeCells(7, row, 10, row);
			Label label11 = new Label(7, row, "销售量");
			label11.setCellFormat(cellFormatCenter);
			sheet.addCell(label11);
			sheet.mergeCells(11, row, 14, row);
			Label label12 = new Label(11, row, "销售额");
			label12.setCellFormat(cellFormatCenter);
			sheet.addCell(label12);

			row++;
			sheet.mergeCells(7, row, 7, row);
			Label label13 = new Label(7, row, "上月");
			label13.setCellFormat(cellFormatCenter);
			sheet.addCell(label13);
			sheet.mergeCells(8, row, 8, row);
			Label label14 = new Label(8, row, "环比");
			label14.setCellFormat(cellFormatCenter);
			sheet.addCell(label14);
			sheet.mergeCells(9, row, 9, row);
			Label label15 = new Label(9, row, "上年同期");
			label15.setCellFormat(cellFormatCenter);
			sheet.addCell(label15);
			sheet.mergeCells(10, row, 10, row);
			Label label16 = new Label(10, row, "同比");
			label16.setCellFormat(cellFormatCenter);
			sheet.addCell(label16);
			sheet.mergeCells(11, row, 11, row);
			Label label17 = new Label(11, row, "上月");
			label17.setCellFormat(cellFormatCenter);
			sheet.addCell(label17);
			sheet.mergeCells(12, row, 12, row);
			Label label18 = new Label(12, row, "环比");
			label18.setCellFormat(cellFormatCenter);
			sheet.addCell(label18);
			sheet.mergeCells(13, row, 13, row);
			Label label19 = new Label(13, row, "上年同期");
			label19.setCellFormat(cellFormatCenter);
			sheet.addCell(label19);
			sheet.mergeCells(14, row, 14, row);
			Label label20 = new Label(14, row, "同比");
			label20.setCellFormat(cellFormatCenter);
			sheet.addCell(label20);

			row++;
			for (int i = 0; i < list.size(); i++) {
				JxcSellBillDetails entity = list.get(i);

				sheet.addCell(new Label(0, row, String.valueOf(i + 1)));

				String pd_type_name = entity.getPd_type_name();
				sheet.addCell(new Label(1, row, pd_type_name));

				String brand_name = entity.getBrand_name();
				sheet.addCell(new Label(2, row, brand_name));

				Double count_rate = null;
				BigDecimal b_count = (BigDecimal) entity.getMap().get("b_count"); // 当月销量
				if (null != entity.getMap().get("tot_count")) {
					BigDecimal tot_count = (BigDecimal) entity.getMap().get("tot_count"); // 当月总销量
					if (tot_count.doubleValue() != 0) {
						count_rate = b_count.doubleValue() / tot_count.doubleValue(); // 当月销量占比
					}
				}
				sheet.addCell(new jxl.write.Number(3, row, b_count.doubleValue(), wc));
				if (count_rate != null) {
					sheet.addCell(new jxl.write.Number(5, row, count_rate, pa));
				} else {
					sheet.addCell(new Label(5, row, "--"));
				}

				Double money_rate = null;
				BigDecimal b_m = (BigDecimal) entity.getMap().get("b_m"); // 当月销售额
				if (null != entity.getMap().get("tot_money")) {
					BigDecimal tot_money = (BigDecimal) entity.getMap().get("tot_money"); // 当月总销售额
					if (tot_money.doubleValue() != 0) {
						money_rate = b_m.doubleValue() / tot_money.doubleValue(); // 当月销额占比
					}
				}
				sheet.addCell(new jxl.write.Number(4, row, b_m.doubleValue(), wcf));
				if (money_rate != null) {
					sheet.addCell(new jxl.write.Number(6, row, money_rate, pa));
				} else {
					sheet.addCell(new Label(6, row, "--"));
				}

				// 销量环比
				Double hb_count_rate = null;
				BigDecimal last_month_count = (BigDecimal) entity.getMap().get("last_month_count");
				if (last_month_count.doubleValue() != 0) {
					hb_count_rate = (b_count.doubleValue() - last_month_count.doubleValue())
							/ last_month_count.doubleValue();
				}
				sheet.addCell(new jxl.write.Number(7, row, last_month_count.doubleValue(), wc));
				if (hb_count_rate != null) {
					sheet.addCell(new jxl.write.Number(8, row, hb_count_rate, pa));
				} else {
					sheet.addCell(new Label(8, row, "--"));
				}

				// 销量同比
				Double tb_count_rate = null;
				BigDecimal last_year_count = (BigDecimal) entity.getMap().get("last_year_count");
				if (last_year_count.doubleValue() != 0) {
					tb_count_rate = (b_count.doubleValue() - last_year_count.doubleValue())
							/ last_year_count.doubleValue();
				}
				sheet.addCell(new jxl.write.Number(9, row, last_year_count.doubleValue(), wc));
				if (tb_count_rate != null) {
					sheet.addCell(new jxl.write.Number(10, row, tb_count_rate, pa));
				} else {
					sheet.addCell(new Label(10, row, "--"));
				}

				// 销售额环比
				Double hb_money_rate = null;
				BigDecimal last_month_money = (BigDecimal) entity.getMap().get("last_month_money");
				if (last_month_money.doubleValue() != 0) {
					hb_money_rate = (b_m.doubleValue() - last_month_money.doubleValue())
							/ last_month_money.doubleValue();
				}
				sheet.addCell(new jxl.write.Number(11, row, last_month_money.doubleValue(), wcf));
				if (hb_money_rate != null) {
					sheet.addCell(new jxl.write.Number(12, row, hb_money_rate, pa));
				} else {
					sheet.addCell(new Label(12, row, "--"));
				}

				// 销售额同比
				Double tb_money_rate = null;
				BigDecimal last_year_money = (BigDecimal) entity.getMap().get("last_year_money");
				if (last_year_money.doubleValue() != 0) {
					tb_money_rate = (b_m.doubleValue() - last_year_money.doubleValue()) / last_year_money.doubleValue();
				}
				sheet.addCell(new jxl.write.Number(13, row, last_year_money.doubleValue(), wcf));
				if (tb_money_rate != null) {
					sheet.addCell(new jxl.write.Number(14, row, tb_money_rate, pa));
				} else {
					sheet.addCell(new Label(14, row, "--"));
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

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		
		String pd_type = (String) dynaBean.get("pd_type");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Long shop_id = user.getEntp_shop().getShop_id();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date now = new Date();
		Calendar n = Calendar.getInstance();
		n.setTime(now);
		Integer n_year = n.get(Calendar.YEAR);
		request.setAttribute("years", n_year);

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			// 取当月第一天
			Calendar c = Calendar.getInstance();
			c.set(Integer.valueOf(year), Integer.valueOf(month), 1);
			String add_date_st = df.format(c.getTime());

			// 取当月最后一天
			Calendar d = Calendar.getInstance();
			d.set(Integer.valueOf(year), Integer.valueOf(month) + 1, 1);
			Date dd = d.getTime();
			Date dd_last = DateUtils.addDays(dd, -1);
			String add_date_en = df.format(dd_last);

			// 取上月时间
			Date date_st = DateUtils.parseDate(add_date_st, new String[] { "yyyy-MM-dd" });// 当前月第一天
			// Date date_en = DateUtils.parseDate(add_date_en, new
			// String[]{"yyyy-MM-dd"});//当前月最后一天
			Date last_month_st_d = DateUtils.addMonths(date_st, -1);
			String last_month_st = df.format(last_month_st_d);
			Date last_month_en_d = DateUtils.addDays(date_st, -1);
			String last_month_en = df.format(last_month_en_d);

			// 取上年时间
			Calendar e = Calendar.getInstance();
			e.set(Integer.valueOf(year) - 1, Integer.valueOf(month), 1);
			String last_year_st = df.format(e.getTime());

			Calendar f = Calendar.getInstance();
			f.set(Integer.valueOf(year) - 1, Integer.valueOf(month) + 1, 1);
			Date ff = f.getTime();
			Date ff_last = DateUtils.addDays(ff, -1);
			String last_year_en = df.format(ff_last);

			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_st);
			entity.getMap().put("add_date_en", add_date_en);
			entity.getMap().put("last_month_st", last_month_st);
			entity.getMap().put("last_month_en", last_month_en);
			entity.getMap().put("last_year_st", last_year_st);
			entity.getMap().put("last_year_en", last_year_en);
			entity.getMap().put("pd_type", pd_type);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService()
					.getJxcDataAnalysisResultForList(entity);
			if (null != list && list.size() > 0) {
				for (JxcSellBillDetails jsbd : list) {
					Double count_rate = null;
					BigDecimal b_count = (BigDecimal) jsbd.getMap().get("b_count");
					if (null != jsbd.getMap().get("tot_count")) {
						BigDecimal tot_count = (BigDecimal) jsbd.getMap().get("tot_count");
						if (tot_count.doubleValue() != 0) {
							count_rate = b_count.doubleValue() / tot_count.doubleValue(); // 当月销量占比
						}
					}
					jsbd.getMap().put("count_rate", count_rate);

					Double money_rate = null;
					BigDecimal b_m = (BigDecimal) jsbd.getMap().get("b_m");
					if (null != jsbd.getMap().get("tot_money")) {
						BigDecimal tot_money = (BigDecimal) jsbd.getMap().get("tot_money");
						if (tot_money.doubleValue() != 0) {
							money_rate = b_m.doubleValue() / tot_money.doubleValue(); // 当月销额占比
						}
					}
					jsbd.getMap().put("money_rate", money_rate);

					// 销量环比
					Double hb_count_rate = null;
					BigDecimal last_month_count = (BigDecimal) jsbd.getMap().get("last_month_count");
					if (last_month_count.doubleValue() != 0) {
						hb_count_rate = (b_count.doubleValue() - last_month_count.doubleValue())
								/ last_month_count.doubleValue();
					}
					jsbd.getMap().put("hb_count_rate", hb_count_rate);

					// 销量同比
					Double tb_count_rate = null;
					BigDecimal last_year_count = (BigDecimal) jsbd.getMap().get("last_year_count");
					if (last_year_count.doubleValue() != 0) {
						tb_count_rate = (b_count.doubleValue() - last_year_count.doubleValue())
								/ last_year_count.doubleValue();
					}
					jsbd.getMap().put("tb_count_rate", tb_count_rate);

					// 销售额环比
					Double hb_money_rate = null;
					BigDecimal last_month_money = (BigDecimal) jsbd.getMap().get("last_month_money");
					if (last_month_money.doubleValue() != 0) {
						hb_money_rate = (b_m.doubleValue() - last_month_money.doubleValue())
								/ last_month_money.doubleValue();
					}
					jsbd.getMap().put("hb_money_rate", hb_money_rate);

					// 销售额同比
					Double tb_money_rate = null;
					BigDecimal last_year_money = (BigDecimal) jsbd.getMap().get("last_year_money");
					if (last_year_money.doubleValue() != 0) {
						tb_money_rate = (b_m.doubleValue() - last_year_money.doubleValue())
								/ last_year_money.doubleValue();
					}
					jsbd.getMap().put("tb_money_rate", tb_money_rate);
				}
			}
			request.setAttribute("search", "search");
			request.setAttribute("querydate", ("查询时间：").concat(year).concat("年").concat(
					String.valueOf(Integer.valueOf(month) + 1)).concat("月"));
			request.setAttribute("entityList", list);
		}
		return new ActionForward("/../jxcnokey/JxcSellDataAnalysis/_print_xssjtjfx_list.jsp");
	}
}
