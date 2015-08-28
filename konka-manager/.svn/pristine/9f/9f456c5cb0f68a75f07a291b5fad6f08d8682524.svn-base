package com.ebiz.mmt.web.struts.jxc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Zhang,Xufeng
 * @version 2012-07-30
 * @节能惠民 月销售及安装/配送信息汇总表
 */
public class KonkaJxcJnhmEntpSellDetailsReportAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		// R3网点搜索条件
		List<KonkaR3Shop> konkaR3ShopForSearchList = super.getShopListByDeptId(user.getDept_id());
		request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);

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
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

		String yyyy = (String) dynaBean.get("yyyy");
		String mm = (String) dynaBean.get("mm");

		PeProdUser user = super.getSessionUserInfo(request);

		if (null == user) {
			return null;
		}
		// R3网点搜索条件
		List<KonkaR3Shop> konkaR3ShopForSearchList = super.getShopListByDeptId(user.getDept_id());
		request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);

		if (!GenericValidator.isLong(r3_shop_id)) {
			String msg = "对不起，请选择网点!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setR3_shop_id(Long.valueOf(r3_shop_id));
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
		if (null == konkaR3MmtMatch) {
			String msg = "对不起，该R3网点未匹配买卖提网点!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		Long mmt_shop_id = konkaR3MmtMatch.getMmt_shop_id();

		JxcSellBill entity = new JxcSellBill();
		super.copyProperties(entity, form);

		entity.setOwn_sys(0);
		// entity.setShop_id(Long.valueOf(shop_id));// 网点id
		entity.getMap().put("mmt_shop_id", mmt_shop_id);
		entity.getMap().put("year_month", yyyy + "-" + mm);
		entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		String customer_name = "";
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(r3_shop_id));
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			customer_name = konkaR3Shop.getCustomer_name();
		}
		request.setAttribute("customer_name", customer_name);

		List<JxcSellBill> entityList = getFacade().getJxcSellBillService().getJxcSellBillListForJnhm(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException, Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

		String yyyy = (String) dynaBean.get("yyyy");
		String mm = (String) dynaBean.get("mm");

		PeProdUser user = super.getSessionUserInfo(request);

		if (null == user) {
			return null;
		}
		// R3网点搜索条件
		List<KonkaR3Shop> konkaR3ShopForSearchList = super.getShopListByDeptId(user.getDept_id());
		request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);

		if (!GenericValidator.isLong(r3_shop_id)) {
			String msg = "对不起，请选择网点!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setR3_shop_id(Long.valueOf(r3_shop_id));
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
		if (null == konkaR3MmtMatch) {
			String msg = "对不起，该R3网点未匹配买卖提网点!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		Long mmt_shop_id = konkaR3MmtMatch.getMmt_shop_id();

		JxcSellBill entity = new JxcSellBill();
		super.copyProperties(entity, form);

		entity.setOwn_sys(0);
		// entity.setShop_id(Long.valueOf(shop_id));// 网点id
		entity.getMap().put("mmt_shop_id", mmt_shop_id);
		entity.getMap().put("year_month", yyyy + "-" + mm);
		entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		String customer_name = "";
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(r3_shop_id));
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			customer_name = konkaR3Shop.getCustomer_name();
		}
		request.setAttribute("customer_name", customer_name);

		List<JxcSellBill> entityList = getFacade().getJxcSellBillService().getJxcSellBillListForJnhm(entity);
		request.setAttribute("entityList", entityList);

		createExcelFileForList(request, response, entityList, yyyy + "-" + mm, customer_name);
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response,
			List<JxcSellBill> list, String year_month, String _customer_name) throws RowsExceededException,
			WriteException, IOException {
		if (list == null || list.size() < 0) {
			return;
		}

		WritableWorkbook wwb = null;
		String fileName = year_month + _customer_name + "销售及安装配送信息汇总表"
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
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行,
			// 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(year_month + _customer_name + "销售及安装配送信息汇总表", 0);
			int row = 0;// 行

			sheet.mergeCells(0, row, 14, row);// 合并单元格(左列，左行，右列，右行)
			String info00 = year_month + _customer_name + "销售及安装配送信息汇总表";
			Label label00 = new Label(0, row, info00);
			label00.setCellFormat(cellFormatCenter);
			sheet.addCell(label00);

			row++;
			sheet.mergeCells(0, row, 0, row);// 合并单元格(左列，左行，右列，右行)
			String info0 = "";
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatCenter);
			sheet.addCell(label0);

			sheet.mergeCells(1, row, 7, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			String info01 = "消费者信息";
			Label label01 = new Label(1, row, info01);
			label01.setCellFormat(cellFormatCenter);
			sheet.addCell(label01);

			sheet.mergeCells(8, row, 12, row);
			String info02 = "销售信息";
			Label label02 = new Label(6, row, info02);
			label02.setCellFormat(cellFormatCenter);
			sheet.addCell(label02);

			sheet.mergeCells(13, row, 14, row);
			String info03 = "终端经销商信息";
			Label label03 = new Label(11, row, info03);
			label03.setCellFormat(cellFormatCenter);
			sheet.addCell(label03);

			row++;
			sheet.mergeCells(0, row, 0, row);// 合并单元格(左列，左行，右列，右行)
			String info001 = "序号";
			Label label001 = new Label(0, row, info001);
			label001.setCellFormat(cellFormatCenter);
			sheet.addCell(label001);

			sheet.mergeCells(1, row, 1, row);
			Label label2 = new Label(1, row, "消费者名称");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(2, row, 2, row);
			Label label3 = new Label(2, row, "身份证信息");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(3, row, 3, row);
			Label label4 = new Label(3, row, "联系电话");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(4, row, 4, row);
			Label label5 = new Label(4, row, "详细地址");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);

			sheet.mergeCells(5, row, 5, row);
			Label label6 = new Label(5, row, "消费者实际所在地行政区划代码");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(6, row, 6, row);
			Label label7 = new Label(6, row, "消费者实际所在地行政区划代码对应名称");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);
			sheet.mergeCells(7, row, 7, row);
			Label label8 = new Label(7, row, "销售时间");
			label8.setCellFormat(cellFormatCenter);
			sheet.addCell(label8);
			sheet.mergeCells(8, row, 8, row);
			Label label9 = new Label(8, row, "企业名称");
			label9.setCellFormat(cellFormatCenter);
			sheet.addCell(label9);
			sheet.mergeCells(9, row, 9, row);
			Label label10 = new Label(9, row, "规格型号");
			label10.setCellFormat(cellFormatCenter);
			sheet.addCell(label10);
			sheet.mergeCells(10, row, 10, row);
			Label label11 = new Label(10, row, "产品唯一编码");
			label11.setCellFormat(cellFormatCenter);
			sheet.addCell(label11);
			sheet.mergeCells(11, row, 11, row);
			Label label12 = new Label(11, row, "销售价格（元）");
			label12.setCellFormat(cellFormatCenter);
			sheet.addCell(label12);
			sheet.mergeCells(12, row, 12, row);
			Label label13 = new Label(12, row, "发票号");
			label13.setCellFormat(cellFormatCenter);
			sheet.addCell(label13);
			sheet.mergeCells(13, row, 13, row);
			Label label14 = new Label(12, row, "终端销售机构名称");
			label14.setCellFormat(cellFormatCenter);
			sheet.addCell(label14);
			sheet.mergeCells(14, row, 14, row);
			Label label15 = new Label(14, row, "终端销售机构组织机构代码");
			label15.setCellFormat(cellFormatCenter);
			sheet.addCell(label15);

			row++;
			for (int i = 0; i < list.size(); i++) {
				Integer vsCount = i;
				sheet.addCell(new Label(0, row, vsCount.toString()));

				JxcSellBill entity = list.get(i);
				String customer_name = (String) entity.getMap().get("customer_name");
				sheet.addCell(new Label(1, row, customer_name));

				String customer_cus_idcard = (String) entity.getMap().get("customer_cus_idcard");
				sheet.addCell(new Label(2, row, customer_cus_idcard));

				String customer_tel = (String) entity.getMap().get("customer_tel");
				sheet.addCell(new Label(3, row, customer_tel));

				String customer_addr = (String) entity.getMap().get("customer_addr");
				sheet.addCell(new Label(4, row, customer_addr));

				String customer_shop_p_index = "";
				if (null != entity.getMap().get("customer_shop_p_index")) {
					customer_shop_p_index = new BigDecimal(entity.getMap().get("customer_shop_p_index").toString())
							.toString();
				}
				sheet.addCell(new Label(5, row, customer_shop_p_index));

				String customer_p_name = (String) entity.getMap().get("customer_p_name");
				sheet.addCell(new Label(6, row, customer_p_name));

				String sell_date = DateFormatUtils.format(entity.getSell_date(), "yyyy-MM-dd");
				sheet.addCell(new Label(7, row, sell_date));

				// String shop_name = (String) entity.getMap().get("shop_name");
				sheet.addCell(new Label(8, row, "康佳"));

				String pd_name = (String) entity.getJxcSellBillDetailsEntity().getPd_name();
				sheet.addCell(new Label(9, row, pd_name));

				String pd_unique_code = (String) entity.getJxcSellBillDetailsEntity().getPd_unique_code();
				sheet.addCell(new Label(10, row, pd_unique_code));

				sheet.addCell(new jxl.write.Number(11, row, entity.getPay_money().doubleValue(), wcf));

				String invoice_bh = (String) entity.getJxcSellBillDetailsEntity().getInvoice_bh();
				sheet.addCell(new Label(12, row, invoice_bh));

				String shop_name2 = (String) entity.getMap().get("shop_name");
				sheet.addCell(new Label(13, row, shop_name2));

				String org_sn = (String) entity.getMap().get("org_sn");
				sheet.addCell(new Label(14, row, org_sn));

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

}
