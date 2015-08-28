package com.ebiz.mmt.web.struts.jxcnokey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.domain.UserInfo;

/**
 * @author Li,Ka
 * @version 2011-11-23
 */
@SuppressWarnings("unused")
public class JxcPdModelAnalysisAction extends BaseJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();
		String start_date = (String) dynaBean.get("sell_date_ge");
		String end_date = (String) dynaBean.get("sell_date_le");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isBlank(start_date)) {
			map.put("start_date", start_date);
		}
		if (!StringUtils.isBlank(end_date)) {
			map.put("end_date", end_date);
		}

		if (StringUtils.isBlank(start_date) && StringUtils.isBlank(end_date)) {
			start_date = getFristDateOfMonth();
			end_date = getTody();
			map.put("start_date", start_date);
			map.put("end_date", end_date);

		}

		map.put("shop_id", shop_id);
		List<JxcSellBillDetails> entityList = super.getFacade().getJxcSellBillDetailsService().getJxcPdModelAnalysis(
				map);

		for (JxcSellBillDetails s : entityList) {
			BigDecimal sales_revenue = (BigDecimal) s.getMap().get("sales_revenue");
			BigDecimal cost_sales = (BigDecimal) s.getMap().get("cost_sales");
			Double maori = sales_revenue.doubleValue() - cost_sales.doubleValue();
			Double maori_rate = maori / sales_revenue.doubleValue() * 100;
			s.getMap().put("maori", maori);
			s.getMap().put("maori_rate", maori_rate);
		}
		dynaBean.set("sell_date_ge", start_date);
		dynaBean.set("sell_date_le", end_date);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public String getTody() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date.getTime());
		return today;
	}

	public Date getDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 得到本月第一天
	 * 
	 * @return
	 */
	public String getFristDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		return result;
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		Long shop_id = user.getEntp_shop().getShop_id();
		if (StringUtils.isNotBlank(add_date_lt) && StringUtils.isNotBlank(add_date_gt)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start_date", add_date_lt);
			map.put("end_date", add_date_gt);
			map.put("shop_id", shop_id);
			List<JxcSellBillDetails> list = super.getFacade().getJxcSellBillDetailsService().getJxcPdModelAnalysis(map);
			createExcelFileForList(request, response, list, add_date_gt, add_date_lt);
		} else {
			super.renderJavaScript(response, "alert('请选择日期！');history.back();");
		}
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBillDetails> list, String add_date_gt, String add_date_lt) throws RowsExceededException,
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
			WritableSheet sheet = wwb.createSheet("分型号汇总毛利分析报表", 0);
			int row = 0;// 行
			// int column = 0;// 列

			sheet.mergeCells(0, row, 7, row);// 合并单元格(左列，左行，右列，右行)
												// 合并第row行，从第0列到第7列
			String info0 = "分型号汇总毛利分析报表";
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatCenter);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 7, row);
			String info1 = "查询时间：".concat(add_date_lt).concat(" 至  ").concat(add_date_gt);
			Label label = new Label(0, row, info1);
			label.setCellFormat(cellFormatRight);
			sheet.addCell(label);

			row++;
			sheet.mergeCells(0, row, 0, row);
			Label label1 = new Label(0, row, "型号");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);
			sheet.mergeCells(1, row, 1, row);
			Label label2 = new Label(1, row, "品类");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(2, row, 2, row);
			Label label3 = new Label(2, row, "品牌");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(3, row, 3, row);
			Label label4 = new Label(3, row, "商品数量");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(4, row, 4, row);
			Label label5 = new Label(4, row, "销售成本");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(5, row, 5, row);
			Label label6 = new Label(5, row, "销售收入");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(6, row, 6, row);
			Label label7 = new Label(6, row, "毛利");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);
			sheet.mergeCells(7, row, 7, row);
			Label label8 = new Label(7, row, "毛利率");
			label8.setCellFormat(cellFormatCenter);
			sheet.addCell(label8);

			row++;
			for (int i = 0; i < list.size(); i++) {
				JxcSellBillDetails entity = list.get(i);
				String pd_name = (String) entity.getMap().get("pd_name");
				sheet.addCell(new Label(0, row, pd_name));

				String pd_type_name = (String) entity.getMap().get("pd_type_name");
				sheet.addCell(new Label(1, row, pd_type_name));

				String brand_name = (String) entity.getMap().get("brand_name");
				sheet.addCell(new Label(2, row, brand_name));

				BigDecimal count = (BigDecimal) entity.getMap().get("count");
				if (count != null) {
					sheet.addCell(new jxl.write.Number(3, row, count.doubleValue(), wc));
				}

				BigDecimal sales_revenue = (BigDecimal) entity.getMap().get("sales_revenue"); // 销售收入
				BigDecimal cost_sales = (BigDecimal) entity.getMap().get("cost_sales"); // 销售成本
				Double maori = sales_revenue.doubleValue() - cost_sales.doubleValue();
				Double maori_rate = maori / sales_revenue.doubleValue();

				sheet.addCell(new jxl.write.Number(4, row, cost_sales.doubleValue(), wcf));
				sheet.addCell(new jxl.write.Number(5, row, sales_revenue.doubleValue(), wcf));
				sheet.addCell(new jxl.write.Number(6, row, maori, wcf));
				sheet.addCell(new jxl.write.Number(7, row, maori_rate, pa));

				row++;
			}

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
		//String type = (String) dynaBean.get("type");
		String keySeq = (String) dynaBean.get("keySeq");
		//String stock_bill_id = (String) dynaBean.get("stock_bill_id");

		//String supplier_id = (String) dynaBean.get("supplier_id");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		StdEntpMain stdEntpMain = super.getStdEntpMainByShopId(user.getEntp_shop().getShop_id());
		request.setAttribute("userName", stdEntpMain.getLinkman());
		request.setAttribute("entpName", stdEntpMain.getEntp_name());
		request.setAttribute("tel", stdEntpMain.getTel());
		request.setAttribute("add_date_gt", add_date_gt);
		request.setAttribute("add_date_lt", add_date_lt);
		
		Long shop_id = user.getEntp_shop().getShop_id();
		if (StringUtils.isNotBlank(add_date_lt) && StringUtils.isNotBlank(add_date_gt)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start_date", add_date_lt);
			map.put("end_date", add_date_gt);
			map.put("shop_id", shop_id);
			List<JxcSellBillDetails> entityList = super.getFacade().getJxcSellBillDetailsService()
					.getJxcPdModelAnalysis(map);
			for (JxcSellBillDetails s : entityList) {
				BigDecimal sales_revenue = (BigDecimal) s.getMap().get("sales_revenue");
				BigDecimal cost_sales = (BigDecimal) s.getMap().get("cost_sales");
				Double maori = sales_revenue.doubleValue() - cost_sales.doubleValue();
				Double maori_rate = maori / sales_revenue.doubleValue() * 100;
				s.getMap().put("maori", maori);
				s.getMap().put("maori_rate", maori_rate);
			}
			request.setAttribute("querydate", queryDate(add_date_lt, add_date_gt));
			request.setAttribute("entityList", entityList);
		}
		return new ActionForward("/../jxcnokey/JxcPdModelAnalysis/_print_fxhmlfx_list.jsp");
	}
}
