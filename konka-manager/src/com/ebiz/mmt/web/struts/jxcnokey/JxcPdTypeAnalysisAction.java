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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.UserInfo;

/**
 * @author Li,Ka
 * @version 2011-11-22
 */
@SuppressWarnings("unused")
public class JxcPdTypeAnalysisAction extends BaseJxcAction {
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
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_id = (String) dynaBean.get("brand_id");

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
			UserInfo user = super.getUserInfoFromWebService(request, response);
			if (null == user) {
				return null;
			}
			dynaBean.set("keySeq", keySeq);
			Long shop_id = user.getEntp_shop().getShop_id();

			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_st);
			entity.getMap().put("add_date_en", add_date_en);
			entity.getMap().put("pd_type", pd_type);
			entity.getMap().put("brand_id", brand_id);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService()
					.getJxcPdTypeAnalysisResultForList(entity);
			if (null != list && list.size() > 0) {
				for (JxcSellBillDetails jsbd : list) {
					BigDecimal xssr = (BigDecimal) jsbd.getMap().get("xssr");
					BigDecimal xscb = (BigDecimal) jsbd.getMap().get("xscb");
					Double ml = xssr.doubleValue() - xscb.doubleValue();
					Double rate = ml / xssr.doubleValue();
					jsbd.getMap().put("ml", ml);
					jsbd.getMap().put("rate", rate);
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

		dynaBean.set("add_date_st", add_date_st);
		dynaBean.set("add_date_en", add_date_en);
		return mapping.findForward("list");
	}
	
	/** 导出excel：先将查询结果写入到excel中，上传到服务器。然后页面直接下载 */
	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String pd_type = (String) dynaBean.get("pd_type");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");

		Long shop_id = user.getEntp_shop().getShop_id();
		if (StringUtils.isNotBlank(add_date_lt) && StringUtils.isNotBlank(add_date_gt)) {
			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_lt);
			entity.getMap().put("add_date_en", add_date_gt);
			entity.getMap().put("pd_type", pd_type);
			// entity.getMap().put("brand_id", brand_id);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService()
					.getJxcPdTypeAnalysisResultForList(entity);
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
		//检查或者创建上传excel文件目录
		File fDownloadExcel = new File(SystemPath + "files/download_excel/");
		if (!fDownloadExcel.exists()) {
			FileUtils.forceMkdir(fDownloadExcel);
		}
		// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
		wwb = Workbook.createWorkbook(new File(SystemPath + "files/download_excel/" + sFileName));
		if (wwb != null) {
			WritableCellFormat cellFormatRight = new WritableCellFormat();
			//定义常用单元格位置
			cellFormatRight.setAlignment(Alignment.RIGHT);
			WritableCellFormat cellFormatCenter = new WritableCellFormat();
			cellFormatCenter.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormatLeft = new WritableCellFormat();
			cellFormatLeft.setAlignment(Alignment.LEFT);
			//定义常用单元格格式
			WritableCellFormat wc = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("0"));
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			WritableCellFormat pa = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("0.00%"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet("分品类汇总毛利分析报表", 0);
			int row = 0;// 行
			// int column = 0;// 列

			sheet.mergeCells(0, row, 6, row);// 合并单元格(左列，左行，右列，右行)
												// 合并第row行，从第0列到第7列
			String info0 = "分品类汇总毛利分析报表";
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatCenter);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 6, row);
			String info1 = "时间：".concat(add_date_lt).concat(" 至  ").concat(add_date_gt);
			Label label = new Label(0, row, info1);
			label.setCellFormat(cellFormatRight);
			sheet.addCell(label);

			row++;
			sheet.mergeCells(0, row, 0, row);
			Label label1 = new Label(0, row, "产品类型");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);
			sheet.mergeCells(1, row, 1, row);
			Label label2 = new Label(1, row, "品牌");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(2, row, 2, row);
			Label label3 = new Label(2, row, "商品数量");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(3, row, 3, row);
			Label label4 = new Label(3, row, "销售成本");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(4, row, 4, row);
			Label label5 = new Label(4, row, "销售收入");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(5, row, 5, row);
			Label label6 = new Label(5, row, "毛利");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(6, row, 6, row);
			Label label7 = new Label(6, row, "毛利率");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);

			row++;
			for (int i = 0; i < list.size(); i++) {
				JxcSellBillDetails entity = list.get(i);
				String pd_type_name = entity.getPd_type_name();
				sheet.addCell(new Label(0, row, pd_type_name));

				String brand_name = entity.getBrand_name();
				sheet.addCell(new Label(1, row, brand_name));

				BigDecimal xssl = (BigDecimal) entity.getMap().get("xssl");
				if (xssl != null) {
					sheet.addCell(new jxl.write.Number(2, row, xssl.doubleValue(), wc));
				}

				BigDecimal xscb = (BigDecimal) entity.getMap().get("xscb");
				if (xscb != null) {
					sheet.addCell(new jxl.write.Number(3, row, xscb.doubleValue(), wcf));
				}

				BigDecimal xssr = (BigDecimal) entity.getMap().get("xssr");
				if (xssr != null) {
					sheet.addCell(new jxl.write.Number(4, row, xssr.doubleValue(), wcf));
				}

				Double ml = 0d;
				Double rate = 0d;
				if (xscb != null && xssr != null) {
					ml = xssr.doubleValue() - xscb.doubleValue();
					rate = ml / xssr.doubleValue();
					sheet.addCell(new jxl.write.Number(5, row, ml, wcf));
					sheet.addCell(new jxl.write.Number(6, row, rate, pa));
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
		
//		if ("fplmlfxlist".equalsIgnoreCase(type)) {
		String pd_type = (String) dynaBean.get("pd_type");
		Long shop_id = user.getEntp_shop().getShop_id();
		if (StringUtils.isNotBlank(add_date_lt) && StringUtils.isNotBlank(add_date_gt)) {
			JxcSellBillDetails entity = new JxcSellBillDetails();
			entity.getMap().put("shop_id", shop_id);
			entity.getMap().put("add_date_st", add_date_lt);
			entity.getMap().put("add_date_en", add_date_gt);
			entity.getMap().put("pd_type", pd_type);
			List<JxcSellBillDetails> list = getFacade().getJxcSellBillDetailsService()
					.getJxcPdTypeAnalysisResultForList(entity);
			if (null != list && list.size() > 0) {
				for (JxcSellBillDetails jsbd : list) {
					BigDecimal xssr = (BigDecimal) jsbd.getMap().get("xssr");
					BigDecimal xscb = (BigDecimal) jsbd.getMap().get("xscb");
					Double ml = xssr.doubleValue() - xscb.doubleValue();
					Double rate = ml / xssr.doubleValue();
					jsbd.getMap().put("ml", ml);
					jsbd.getMap().put("rate", rate);
				}
			}
			request.setAttribute("querydate", queryDate(add_date_lt, add_date_gt));
			request.setAttribute("entityList", list);
		} else {
			request.setAttribute("close", "close");
		}
		return new ActionForward("/../jxcnokey/JxcPdTypeAnalysis/_print_fplmlfx_list.jsp");
//		}
//		return null;
	}
}
