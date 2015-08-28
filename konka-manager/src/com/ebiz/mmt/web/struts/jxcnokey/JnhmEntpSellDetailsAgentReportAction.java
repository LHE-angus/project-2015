package com.ebiz.mmt.web.struts.jxcnokey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.Constants;

/**
 * @author Zhang,Xufeng
 * @version 2012-07-30
 * @节能惠民 月销售机构出货信息汇总表
 */
public class JnhmEntpSellDetailsAgentReportAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		dynaBean.set("keySeq", keySeq);

		Date today = new Date();
		Date first_day_this_month = DateUtils.truncate(today, DateUtils.SEMI_MONTH);
		Date first_day_last_month = DateUtils.addMonths(first_day_this_month, -1);
		String yyyy = DateFormatUtils.format(first_day_last_month, "yyyy");
		String mm = DateFormatUtils.format(first_day_last_month, "MM");
		mm = StringUtils.leftPad(mm, 2, '0');

		dynaBean.set("yyyy", yyyy);
		dynaBean.set("mm", mm);

		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

		String yyyy = (String) dynaBean.get("yyyy");
		String mm = (String) dynaBean.get("mm");

		JxcSellBill entity = new JxcSellBill();
		super.copyProperties(entity, form);

		entity.setOwn_sys(0);
		entity.setShop_id(Long.valueOf(user.getEntp_shop().getShop_id()));// 网点id
		entity.getMap().put("year_month", yyyy + "-" + mm);
		entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		List<JxcSellBill> entityList = getFacade().getJxcSellBillService().getJxcSellBillListForJnhm(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("customer_name", user.getEntp_shop().getShop_name());

		return mapping.findForward("list");
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException, Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

		String yyyy = (String) dynaBean.get("yyyy");
		String mm = (String) dynaBean.get("mm");

		JxcSellBill entity = new JxcSellBill();
		super.copyProperties(entity, form);

		entity.setOwn_sys(0);
		entity.setShop_id(Long.valueOf(user.getEntp_shop().getShop_id()));// 网点id
		entity.getMap().put("year_month", yyyy + "-" + mm);
		entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		List<JxcSellBill> entityList = getFacade().getJxcSellBillService().getJxcSellBillListForJnhm(entity);
		request.setAttribute("entityList", entityList);

		createExcelFileForList(request, response, entityList, yyyy + "-" + mm, user.getEntp_shop().getShop_name());
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBill> list, String year_month, String _customer_name) throws RowsExceededException,
			WriteException, IOException {
		if (list == null || list.size() < 0) {
			return;
		}

		WritableWorkbook wwb = null;
		String fileName = year_month + _customer_name + "销售机构出货信息汇总表"
				+ DateFormatUtils.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		String sFileName = new String(fileName.getBytes(), "iso8859-1");
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
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(year_month + _customer_name + "销售机构出货信息汇总表", 0);
			int row = 0;// 行

			sheet.mergeCells(0, row, 7, row);// 合并单元格(左列，左行，右列，右行)
			String info00 = year_month + _customer_name + "销售机构出货信息汇总表";
			Label label00 = new Label(0, row, info00);
			label00.setCellFormat(cellFormatCenter);
			sheet.addCell(label00);

			row++;

			sheet.mergeCells(0, row, 0, row);// 合并单元格(左列，左行，右列，右行)
			String info1 = "终端销售机构名称";
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			sheet.mergeCells(1, row, 1, row);
			Label label2 = new Label(1, row, "终端销售机构组织机构代码");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(2, row, 2, row);
			Label label3 = new Label(2, row, "出货开发票日期");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(3, row, 3, row);
			Label label4 = new Label(3, row, "购货单位注册名称");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(4, row, 4, row);
			Label label5 = new Label(4, row, "购货单位组织机构代码");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);

			sheet.mergeCells(5, row, 5, row);
			Label label6 = new Label(5, row, "产品型号");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);

			sheet.mergeCells(6, row, 6, row);
			Label label7 = new Label(6, row, "产品唯一编码");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);

			sheet.mergeCells(7, row, 7, row);
			Label label8 = new Label(7, row, "增值税（销售）发票号");
			label8.setCellFormat(cellFormatCenter);
			sheet.addCell(label8);

			row++;
			for (int i = 0; i < list.size(); i++) {

				JxcSellBill entity = list.get(i);
				String shop_name = (String) entity.getMap().get("shop_name");
				sheet.addCell(new Label(0, row, shop_name));

				String org_sn = (String) entity.getMap().get("org_sn");
				sheet.addCell(new Label(1, row, org_sn));

				String ch_invoice_date = DateFormatUtils.format(entity.getJxcSellBillDetailsEntity()
						.getCh_invoice_date(), "yyyy-MM-dd");
				sheet.addCell(new Label(2, row, ch_invoice_date));

				String customer_name = (String) entity.getMap().get("customer_name");
				sheet.addCell(new Label(3, row, customer_name));

				String customer_cus_idcard = (String) entity.getMap().get("customer_cus_idcard");
				sheet.addCell(new Label(4, row, customer_cus_idcard));

				String pd_name = (String) entity.getJxcSellBillDetailsEntity().getPd_name();
				sheet.addCell(new Label(5, row, pd_name));

				String pd_unique_code = (String) entity.getJxcSellBillDetailsEntity().getPd_unique_code();
				sheet.addCell(new Label(6, row, pd_unique_code));

				String invoice_bh = (String) entity.getJxcSellBillDetailsEntity().getInvoice_bh();
				sheet.addCell(new Label(7, row, invoice_bh));

				row++;
			}

			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		response.setCharacterEncoding("utf-8");
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
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

		String yyyy = (String) dynaBean.get("yyyy");
		String mm = (String) dynaBean.get("mm");

		JxcSellBill entity = new JxcSellBill();
		super.copyProperties(entity, form);

		entity.setOwn_sys(0);
		entity.setShop_id(Long.valueOf(user.getEntp_shop().getShop_id()));// 网点id
		entity.getMap().put("year_month", yyyy + "-" + mm);
		entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		List<JxcSellBill> entityList = getFacade().getJxcSellBillService().getJxcSellBillListForJnhm(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("customer_name", user.getEntp_shop().getShop_name());

		return new ActionForward("/../jxcnokey/JnhmEntpSellDetailsAgentReport/_print_jnhm_agent_report_list.jsp");
	}

}
