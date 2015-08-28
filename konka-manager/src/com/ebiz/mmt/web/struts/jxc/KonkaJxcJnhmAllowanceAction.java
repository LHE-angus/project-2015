package com.ebiz.mmt.web.struts.jxc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JnhmSelledPdCode;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Zhang,Xufeng
 * @version 2012-07-30
 * @节能惠民 补贴统计
 */
public class KonkaJxcJnhmAllowanceAction extends BaseJxcAction {

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

		// 默认日期
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		dynaBean.set("sell_date_eq", today);

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

		String sell_date_eq = (String) dynaBean.get("sell_date_eq");
		// String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

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

		JnhmSelledPdCode entity = new JnhmSelledPdCode();
		super.copyProperties(entity, form);

		// entity.setShop_id(Long.valueOf(shop_id));// 网点id
		entity.getMap().put("mmt_shop_id", mmt_shop_id);
		entity.getMap().put("sell_date_eq", sell_date_eq);
		// entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		// entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		String customer_name = "";
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(r3_shop_id));
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			customer_name = konkaR3Shop.getCustomer_name();
		}
		request.setAttribute("customer_name", customer_name);

		List<JnhmSelledPdCode> entityList = getFacade().getJnhmSelledPdCodeService().getJnhmSelledPdCodeListForJnhm(
				entity);
		request.setAttribute("entityList", entityList);
		// 统计：补贴金额
		BigDecimal total_allowance_money = new BigDecimal("0");
		for (JnhmSelledPdCode jnhmSelledPdCode : entityList) {
			total_allowance_money = total_allowance_money.add(null == jnhmSelledPdCode.getMap().get(
					"pd_allowance_money") ? new BigDecimal("0") : new BigDecimal(jnhmSelledPdCode.getMap().get(
					"pd_allowance_money").toString()));
		}
		request.setAttribute("total_allowance_money", total_allowance_money);

		return mapping.findForward("list");
	}

	public ActionForward toExcelForList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
										HttpServletResponse response) throws RowsExceededException, WriteException, IOException, Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String sell_date_eq = (String) dynaBean.get("sell_date_eq");
		// String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

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

		JnhmSelledPdCode entity = new JnhmSelledPdCode();
		super.copyProperties(entity, form);

		// entity.setShop_id(Long.valueOf(shop_id));// 网点id
		entity.getMap().put("mmt_shop_id", mmt_shop_id);
		entity.getMap().put("sell_date_eq", sell_date_eq);
		// entity.getMap().put("pd_type_id", pd_type_id);
		entity.getMap().put("pd_name_like", pd_name_like);
		// entity.getMap().put("brand_id", Constants.KONKA_BRAND_ID);

		String customer_name = "";
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(r3_shop_id));
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			customer_name = konkaR3Shop.getCustomer_name();
		}
		request.setAttribute("customer_name", customer_name);

		List<JnhmSelledPdCode> entityList = getFacade().getJnhmSelledPdCodeService().getJnhmSelledPdCodeListForJnhm(
				entity);
		request.setAttribute("entityList", entityList);
		// 统计：补贴金额
		BigDecimal total_allowance_money = new BigDecimal("0");
		for (JnhmSelledPdCode jnhmSelledPdCode : entityList) {
			total_allowance_money = total_allowance_money.add(null == jnhmSelledPdCode.getMap().get(
					"pd_allowance_money") ? new BigDecimal("0") : new BigDecimal(jnhmSelledPdCode.getMap().get(
					"pd_allowance_money").toString()));
		}
		request.setAttribute("total_allowance_money", total_allowance_money);

		createExcelFileForList(request, response, entityList, sell_date_eq, total_allowance_money, customer_name);
		return null;
	}

	private void createExcelFileForList(HttpServletRequest request, HttpServletResponse response,
										List<JnhmSelledPdCode> list, String sell_date_eq, BigDecimal total_allowance_money, String _customer_name)
			throws RowsExceededException, WriteException, IOException {
		if (list == null || list.size() < 0) {
			return;
		}

		WritableWorkbook wwb = null;
		String fileName = sell_date_eq + _customer_name + "节能惠民补贴统计"
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
			WritableSheet sheet = wwb.createSheet(sell_date_eq + _customer_name + "节能惠民补贴统计", 0);
			int row = 0;// 行

			sheet.mergeCells(0, row, 10, row);// 合并单元格(左列，左行，右列，右行)
			// 合并第row行，从第0列到第7列
			String info00 = sell_date_eq + _customer_name + "节能惠民补贴统计";
			Label label00 = new Label(0, row, info00);
			label00.setCellFormat(cellFormatCenter);
			sheet.addCell(label00);

			row++;
			sheet.mergeCells(0, row, 9, row);
			String info01 = ("销售日期：").concat(sell_date_eq);
			Label label01 = new Label(0, row, info01);
			label01.setCellFormat(cellFormatRight);
			sheet.addCell(label01);

			row++;
			sheet.mergeCells(0, row, 0, row);// 合并单元格(左列，左行，右列，右行)
			String info1 = "序号";
			Label label1 = new Label(0, row, info1);
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			sheet.mergeCells(1, row, 1, row);
			Label label2 = new Label(1, row, "产品唯一编码");
			label2.setCellFormat(cellFormatCenter);
			sheet.addCell(label2);
			sheet.mergeCells(2, row, 2, row);
			Label label3 = new Label(2, row, "产品型号");
			label3.setCellFormat(cellFormatCenter);
			sheet.addCell(label3);
			sheet.mergeCells(3, row, 3, row);
			Label label4 = new Label(3, row, "销售单号");
			label4.setCellFormat(cellFormatCenter);
			sheet.addCell(label4);
			sheet.mergeCells(4, row, 4, row);
			Label label5 = new Label(4, row, "销售企业");
			label5.setCellFormat(cellFormatCenter);
			sheet.addCell(label5);
			sheet.mergeCells(5, row, 5, row);
			Label label6 = new Label(5, row, "单价（元）");
			label6.setCellFormat(cellFormatCenter);
			sheet.addCell(label6);
			sheet.mergeCells(6, row, 6, row);
			Label label7 = new Label(6, row, "补贴金额（元）");
			label7.setCellFormat(cellFormatCenter);
			sheet.addCell(label7);
			sheet.mergeCells(7, row, 7, row);
			Label label8 = new Label(7, row, "购买人");
			label8.setCellFormat(cellFormatCenter);
			sheet.addCell(label8);
			sheet.mergeCells(8, row, 8, row);
			Label label9 = new Label(8, row, "联系电话");
			label9.setCellFormat(cellFormatCenter);
			sheet.addCell(label9);
			sheet.mergeCells(9, row, 9, row);
			Label label10 = new Label(9, row, "销售时间");
			label10.setCellFormat(cellFormatCenter);
			sheet.addCell(label10);

			row++;
			for (int i = 0; i < list.size(); i++) {
				Integer vsCount = i + 1;
				sheet.addCell(new Label(0, row, vsCount.toString()));

				JnhmSelledPdCode entity = list.get(i);
				String pd_unique_code = entity.getPd_unique_code();
				sheet.addCell(new Label(1, row, pd_unique_code));

				String pd_name = entity.getPd_name();
				sheet.addCell(new Label(2, row, pd_name));

				String sn = (String) entity.getMap().get("sn");
				sheet.addCell(new Label(3, row, sn));

				String shop_name = (String) entity.getMap().get("shop_name");
				sheet.addCell(new Label(4, row, shop_name));

				sheet.addCell(new jxl.write.Number(5, row, Double.valueOf(entity.getMap().get("pd_price").toString()),
						wcf));

				sheet.addCell(new jxl.write.Number(6, row, Double.valueOf(entity.getMap().get("pd_allowance_money")
						.toString()), wcf));

				String customer_name = (String) entity.getMap().get("customer_name");
				sheet.addCell(new Label(7, row, customer_name));

				String customer_tel = (String) entity.getMap().get("customer_tel");
				sheet.addCell(new Label(8, row, customer_tel));

				String sell_date = null;
				try {
					sell_date = DateFormatUtils.format(new SimpleDateFormat("yyyy-MM-dd").parse(entity.getMap().get(
							"sell_date").toString()), "yyyy-MM-dd");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				sheet.addCell(new Label(9, row, sell_date));

				row++;
			}

			sheet.mergeCells(0, row, 5, row);// 合并单元格(左列，左行，右列，右行)
			String info0 = "合计";
			Label label0 = new Label(0, row, info0);
			sheet.addCell(label0);

			sheet.addCell(new jxl.write.Number(6, row, total_allowance_money.doubleValue(), wcf));

			sheet.mergeCells(7, row, 9, row);// 合并单元格(左列，左行，右列，右行)
			String info03 = "";
			Label label03 = new Label(7, row, info03);
			sheet.addCell(label03);

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
