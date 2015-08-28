package com.ebiz.mmt.web.struts.jxc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
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

import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.JxcSzDetails;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.ssi.web.struts.bean.Pager;

public class JxcPayAction extends BaseJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Pager pager = (Pager) dynaBean.get("pager");

		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String supplier_id = (String) dynaBean.get("supplier_id");

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		jxcSupplier.setIs_del(0);
		List<JxcSupplier> listJxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		request.setAttribute("listJxcSupplier", listJxcSupplier);

		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}
		request.setAttribute("add_date_gt", add_date_gt);
		request.setAttribute("add_date_lt", add_date_lt);
		request.setAttribute("supplier_id", supplier_id);

		List<JxcSupplier> resultList = null;
		JxcSupplier entity = new JxcSupplier();
		entity.setShop_id(user.getStdEntpMain().getShop_id());
		if (StringUtils.isNotEmpty(supplier_id)) {
			entity.setId(Long.valueOf(supplier_id));
		}
		entity.setIs_del(0);

		Long recordCount = super.getFacade().getJxcSupplierService().getJxcSupplierCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		resultList = super.getFacade().getJxcSupplierService().getJxcSupplierPaginatedList(entity);
		for (int i = 0; i < resultList.size(); i++) {

			Long supplier_id1 = resultList.get(i).getId();
			// 初始期初应付款
			BigDecimal m0 = resultList.get(i).getInit_pay();

			// 开始日期前进货单,收支单
			JxcStockBill jxcStockBill1 = new JxcStockBill();
			JxcSzDetails jxcSzDetails1 = new JxcSzDetails();
			jxcStockBill1.setShop_id(resultList.get(i).getShop_id());
			jxcStockBill1.setSupplier_id(supplier_id1);
			jxcSzDetails1.setSz_obj_id(supplier_id1);
			jxcSzDetails1.setShop_id(resultList.get(i).getShop_id());
			jxcStockBill1.getMap().put("add_date_lt_0", add_date_gt);
			jxcSzDetails1.getMap().put("add_date_lt_0", add_date_gt);
			jxcStockBill1 = super.getFacade().getJxcStockBillService().getJxcStockBillWithMoney(jxcStockBill1);
			jxcSzDetails1 = super.getFacade().getJxcSzDetailsService().getJxcSzDetailsWithMoney(jxcSzDetails1);

			// 开始日期之前，所有进货单总的应付款总和
			BigDecimal yf1 = new BigDecimal(0.0);
			if (jxcStockBill1.getMap().get("yf_pay") != null) {
				yf1 = (BigDecimal) jxcStockBill1.getMap().get("yf_pay");
			}

			// 开始日期之前，所有进货单总的实付款总和
			BigDecimal sf1 = new BigDecimal(0.0);
			if (jxcStockBill1.getMap().get("sf_pay") != null) {
				sf1 = (BigDecimal) jxcStockBill1.getMap().get("sf_pay");
			}

			// 开始日期之前，所有收支单总金额(支出)
			BigDecimal sk1 = new BigDecimal(0.0);
			if (jxcStockBill1.getMap().get("sz_pay") != null) {
				sk1 = (BigDecimal) jxcStockBill1.getMap().get("sz_pay");
			}

			// 开始日期到结束日期的进货单，收支单
			JxcStockBill jxcStockBill2 = new JxcStockBill();
			JxcSzDetails jxcSzDetails2 = new JxcSzDetails();
			jxcStockBill2.setSupplier_id(supplier_id1);
			jxcSzDetails2.setSz_obj_id(supplier_id1);
			jxcStockBill2.setShop_id(resultList.get(i).getShop_id());
			jxcSzDetails2.setShop_id(resultList.get(i).getShop_id());
			jxcStockBill2.getMap().put("add_date_lt", add_date_lt);
			jxcStockBill2.getMap().put("add_date_gt", add_date_gt);
			jxcSzDetails2.getMap().put("add_date_lt", add_date_lt);
			jxcSzDetails2.getMap().put("add_date_gt", add_date_gt);

			jxcStockBill2 = super.getFacade().getJxcStockBillService().getJxcStockBillWithMoney(jxcStockBill2);
			jxcSzDetails2 = super.getFacade().getJxcSzDetailsService().getJxcSzDetailsWithMoney(jxcSzDetails2);
			// 开始日期到结束日期，所有进货单总的应付款总和
			BigDecimal yf2 = new BigDecimal(0.0);
			if (jxcStockBill2.getMap().get("yf_pay") != null) {
				yf2 = (BigDecimal) jxcStockBill2.getMap().get("yf_pay");
			}

			// 开始日期到结束日期，所有进货单总的实付款总和
			BigDecimal sf2 = new BigDecimal(0.0);
			if (jxcStockBill2.getMap().get("sf_pay") != null) {
				sf2 = (BigDecimal) jxcStockBill2.getMap().get("sf_pay");
			}

			// 开始日期到结束日期，所有收支单总金额（支出）
			BigDecimal sk2 = new BigDecimal(0.0);
			if (jxcSzDetails2.getMap().get("sz_pay") != null) {
				sk2 = (BigDecimal) jxcSzDetails2.getMap().get("sz_pay");
			}

			// 期初应付款：M0 + YF1 - SF1-SK1
			BigDecimal qc_pay = m0.add(yf1).subtract(sf1).subtract(sk1);

			// 增加应付款：YF2
			BigDecimal zj_pay = yf2;

			// 支付应付款：SF2+SK2
			BigDecimal zf_pay = sf2.add(sk2);

			// 期末应付款：期初应付款+增加应付款-支付应付款
			BigDecimal qm_pay = qc_pay.add(zj_pay).subtract(zf_pay);

			resultList.get(i).setQc_pay(qc_pay);
			resultList.get(i).setZj_pay(zj_pay);
			resultList.get(i).setZf_pay(zf_pay);
			resultList.get(i).setQm_pay(qm_pay);
		}
		request.setAttribute("resultList", resultList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setShop_id(user.getStdEntpMain().getShop_id());
		jxcSupplier.setIs_del(0);
		List<JxcSupplier> listJxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplierList(jxcSupplier);
		request.setAttribute("listJxcSupplier", listJxcSupplier);
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		request.setAttribute("today", today);

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		String sz_sn = "FK" + dateString;
		request.setAttribute("sz_sn", sz_sn);
		String supplier_id = (String) dynaBean.get("supplier_id");
		JxcSupplier entity = new JxcSupplier();
		if (StringUtils.isNotEmpty(supplier_id)) {
			entity.setId(Long.valueOf(supplier_id));
			entity = super.getFacade().getJxcSupplierService().getJxcSupplier(entity);
		}
		request.setAttribute("entity", entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		String sz_obj_id = (String) dynaBean.get("sz_obj_id");
		String busi_date = (String) dynaBean.get("busi_date");
		String money = (String) dynaBean.get("money");
		String summary = (String) dynaBean.get("summary");
		String sz_sn = (String) dynaBean.get("sz_sn");
		JxcSzDetails jxcSzDetails = new JxcSzDetails();
		JxcSupplier jxcSupplier = new JxcSupplier();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		jxcSzDetails.setBusi_date(format.parse(busi_date));
		jxcSzDetails.setIs_del(0);
		jxcSzDetails.setShop_id(user.getStdEntpMain().getShop_id());
		jxcSzDetails.setShop_p_index(Long.valueOf(user.getStdEntpMain().getP_index()));
		jxcSzDetails.setSz_type(0);
		jxcSzDetails.setSz_obj_id(Long.valueOf(sz_obj_id));
		jxcSzDetails.setMoney(new BigDecimal(money));
		jxcSzDetails.setSummary(summary);
		jxcSzDetails.setSz_sn(sz_sn);

		jxcSupplier.setId(Long.valueOf(sz_obj_id));
		jxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);
		BigDecimal m1 = jxcSupplier.getCur_pay();
		jxcSupplier.setCur_pay(m1.subtract(new BigDecimal(money)));

		jxcSupplier.getMap().put("jxcSzDetails", jxcSzDetails);
		super.getFacade().getJxcSupplierService().modifyJxcSupplier(jxcSupplier);
		saveMessage(request, "entity.inserted");

		return new ActionForward("/manager/JxcPay.do?method=list&keySeq=" + keySeq, true);
	}

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		String supplier_id = (String) dynaBean.get("supplier_id");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");

		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setId(Long.valueOf(supplier_id));
		jxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);
		request.setAttribute("jxcSupplier", jxcSupplier);

		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setSupplier_id(Long.valueOf(supplier_id));
		jxcStockBill.setShop_id(user.getStdEntpMain().getShop_id());
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);

		List<JxcStockBill> jxcStockBillList = super.getFacade().getJxcStockBillService().getJxcStockBillListForSZMX(
				jxcStockBill);

		// 初始期初应付款
		BigDecimal m0 = jxcSupplier.getInit_pay();

		// 开始日期前进货单,收支单
		JxcStockBill jxcStockBill1 = new JxcStockBill();
		JxcSzDetails jxcSzDetails1 = new JxcSzDetails();
		jxcStockBill1.setShop_id(jxcSupplier.getShop_id());
		jxcStockBill1.setSupplier_id(Long.valueOf(supplier_id));
		jxcSzDetails1.setSz_obj_id(Long.valueOf(supplier_id));
		jxcSzDetails1.setShop_id(jxcSupplier.getShop_id());
		jxcStockBill1.getMap().put("add_date_lt_0", add_date_gt);
		jxcSzDetails1.getMap().put("add_date_lt_0", add_date_gt);
		jxcStockBill1 = super.getFacade().getJxcStockBillService().getJxcStockBillWithMoney(jxcStockBill1);
		jxcSzDetails1 = super.getFacade().getJxcSzDetailsService().getJxcSzDetailsWithMoney(jxcSzDetails1);

		// 开始日期之前，所有进货单总的应付款总和
		BigDecimal yf1 = new BigDecimal(0.0);
		if (jxcStockBill1.getMap().get("yf_pay") != null) {
			yf1 = (BigDecimal) jxcStockBill1.getMap().get("yf_pay");
		}

		// 开始日期之前，所有进货单总的实付款总和
		BigDecimal sf1 = new BigDecimal(0.0);
		if (jxcStockBill1.getMap().get("sf_pay") != null) {
			sf1 = (BigDecimal) jxcStockBill1.getMap().get("sf_pay");
		}

		// 开始日期之前，所有收支单总金额(支出)
		BigDecimal sk1 = new BigDecimal(0.0);
		if (jxcStockBill1.getMap().get("sz_pay") != null) {
			sk1 = (BigDecimal) jxcStockBill1.getMap().get("sz_pay");
		}
		// 期初应付款：M0 + YF1 - SF1-SK1
		BigDecimal qc_pay = m0.add(yf1).subtract(sf1).subtract(sk1);

		BigDecimal zj_pay = new BigDecimal(0);
		BigDecimal zf_pay = new BigDecimal(0);
		BigDecimal qm_pay = new BigDecimal(0);

		for (JxcStockBill jsb : jxcStockBillList) {
			zj_pay = zj_pay.add(jsb.getPay_money());
			zf_pay = zf_pay.add(jsb.getPaid_money());
			jsb.getMap().put("qmAmount", qc_pay.add(zj_pay).subtract(zf_pay));
		}
		qm_pay = qc_pay.add(zj_pay).subtract(zf_pay);
		request.setAttribute("qc_pay", qc_pay);
		request.setAttribute("zj_pay", zj_pay);
		request.setAttribute("zf_pay", zf_pay);
		request.setAttribute("qm_pay", qm_pay);
		request.setAttribute("add_date_lt", add_date_lt);
		request.setAttribute("add_date_gt", add_date_gt);
		request.setAttribute("list", jxcStockBillList);
		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcPay/detail.jsp");
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String supplier_id = (String) dynaBean.get("supplier_id");
		String querydate = queryDate(add_date_gt, add_date_lt);
		String userName = user.getStdEntpMain().getLinkman();
		String tel = user.getStdEntpMain().getTel();
		List<JxcSupplier> resultList = null;
		JxcSupplier entity = new JxcSupplier();
		entity.setShop_id(user.getStdEntpMain().getShop_id());
		if (StringUtils.isNotEmpty(supplier_id)) {
			entity.setId(Long.valueOf(supplier_id));
		}
		resultList = super.getFacade().getJxcSupplierService().getJxcSupplierList(entity);
		for (int i = 0; i < resultList.size(); i++) {

			Long supplier_id1 = resultList.get(i).getId();
			// 初始期初应付款
			BigDecimal m0 = resultList.get(i).getInit_pay();

			// 开始日期前进货单,收支单
			JxcStockBill jxcStockBill1 = new JxcStockBill();
			JxcSzDetails jxcSzDetails1 = new JxcSzDetails();
			jxcStockBill1.setShop_id(resultList.get(i).getShop_id());
			jxcStockBill1.setSupplier_id(supplier_id1);
			jxcSzDetails1.setSz_obj_id(supplier_id1);
			jxcSzDetails1.setShop_id(resultList.get(i).getShop_id());
			jxcStockBill1.getMap().put("add_date_lt_0", add_date_gt);
			jxcSzDetails1.getMap().put("add_date_lt_0", add_date_gt);
			jxcStockBill1 = super.getFacade().getJxcStockBillService().getJxcStockBillWithMoney(jxcStockBill1);
			jxcSzDetails1 = super.getFacade().getJxcSzDetailsService().getJxcSzDetailsWithMoney(jxcSzDetails1);

			// 开始日期之前，所有进货单总的应付款总和
			BigDecimal yf1 = new BigDecimal(0.0);
			if (jxcStockBill1.getMap().get("yf_pay") != null) {
				yf1 = (BigDecimal) jxcStockBill1.getMap().get("yf_pay");
			}

			// 开始日期之前，所有进货单总的实付款总和
			BigDecimal sf1 = new BigDecimal(0.0);
			if (jxcStockBill1.getMap().get("sf_pay") != null) {
				sf1 = (BigDecimal) jxcStockBill1.getMap().get("sf_pay");
			}

			// 开始日期之前，所有收支单总金额(支出)
			BigDecimal sk1 = new BigDecimal(0.0);
			if (jxcStockBill1.getMap().get("sz_pay") != null) {
				sk1 = (BigDecimal) jxcStockBill1.getMap().get("sz_pay");
			}

			// 开始日期到结束日期的进货单，收支单
			JxcStockBill jxcStockBill2 = new JxcStockBill();
			JxcSzDetails jxcSzDetails2 = new JxcSzDetails();
			jxcStockBill2.setSupplier_id(supplier_id1);
			jxcSzDetails2.setSz_obj_id(supplier_id1);
			jxcStockBill2.setShop_id(resultList.get(i).getShop_id());
			jxcSzDetails2.setShop_id(resultList.get(i).getShop_id());
			jxcStockBill2.getMap().put("add_date_lt", add_date_lt);
			jxcStockBill2.getMap().put("add_date_gt", add_date_gt);
			jxcSzDetails2.getMap().put("add_date_lt", add_date_lt);
			jxcSzDetails2.getMap().put("add_date_gt", add_date_gt);

			jxcStockBill2 = super.getFacade().getJxcStockBillService().getJxcStockBillWithMoney(jxcStockBill2);
			jxcSzDetails2 = super.getFacade().getJxcSzDetailsService().getJxcSzDetailsWithMoney(jxcSzDetails2);
			// 开始日期到结束日期，所有进货单总的应付款总和
			BigDecimal yf2 = new BigDecimal(0.0);
			if (jxcStockBill2.getMap().get("yf_pay") != null) {
				yf2 = (BigDecimal) jxcStockBill2.getMap().get("yf_pay");
			}

			// 开始日期到结束日期，所有进货单总的实付款总和
			BigDecimal sf2 = new BigDecimal(0.0);
			if (jxcStockBill2.getMap().get("sf_pay") != null) {
				sf2 = (BigDecimal) jxcStockBill2.getMap().get("sf_pay");
			}

			// 开始日期到结束日期，所有收支单总金额（支出）
			BigDecimal sk2 = new BigDecimal(0.0);
			if (jxcSzDetails2.getMap().get("sz_pay") != null) {
				sk2 = (BigDecimal) jxcSzDetails2.getMap().get("sz_pay");
			}

			// 期初应付款：M0 + YF1 - SF1-SK1
			BigDecimal qc_pay = m0.add(yf1).subtract(sf1).subtract(sk1);

			// 增加应付款：YF2
			BigDecimal zj_pay = yf2;

			// 支付应付款：SF2+SK2
			BigDecimal zf_pay = sf2.add(sk2);

			// 期末应付款：期初应付款+增加应付款-支付应付款
			BigDecimal qm_pay = qc_pay.add(zj_pay).subtract(zf_pay);

			resultList.get(i).setQc_pay(qc_pay);
			resultList.get(i).setZj_pay(zj_pay);
			resultList.get(i).setZf_pay(zf_pay);
			resultList.get(i).setQm_pay(qm_pay);
		}
		createExcelFile(request, response, querydate, resultList, userName, tel, add_date_gt, add_date_lt);
		return null;
	}

	private void createExcelFile(HttpServletRequest request, HttpServletResponse response, String querydate,
			List<JxcSupplier> list, String userName, String tel, String add_date_gt, String add_date_lt)
			throws IOException, RowsExceededException, WriteException {
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
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行, 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(userName.concat("付款清单"), 0);
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 5, row);// 合并单元格(左列，左行，右列，右行) 合并第row行，从第0列到第6列
			String info0 = "联系人：".concat(userName).concat("  联系电话：").concat(tel);
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 5, row);
			Label label1 = new Label(0, row, "付款清单");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 5, row);
			String s = queryDate(add_date_gt, add_date_lt);
			label1 = new Label(0, row, s);
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			String[] title = { "行号", "供应商名称", "期初应付款", "增加应付款", "支付应付款", "期末应付款" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			// 填充产品价格
			for (int i = 0; i < list.size(); i++) {
				column = 0;
				JxcSupplier jxcSupplier = list.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 1));// 行号
				sheet.addCell(new Label(column++, row, jxcSupplier.getName()));// 供应商名称
				sheet.addCell(new jxl.write.Number(column++, row, jxcSupplier.getQc_pay().doubleValue(), wcf));// 期初应付款
				sheet.addCell(new jxl.write.Number(column++, row, jxcSupplier.getZj_pay().doubleValue(), wcf));// 增加应付款
				sheet.addCell(new jxl.write.Number(column++, row, jxcSupplier.getZf_pay().doubleValue(), wcf));// 支付应付款
				sheet.addCell(new jxl.write.Number(column++, row, jxcSupplier.getQm_pay().doubleValue(), wcf));// 期末应付款
				row++;
			}

			row++;
			sheet.mergeCells(0, row, 5, row);
			String info = "签收人：";
			Label label = new Label(0, row, info);
			label.setCellFormat(cellFormatCenter);
			sheet.addCell(label);
			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		// String ctx = getCtxPath(request);
		// super.renderJavaScript(response, "window.location.href='" + ctx + "/files/download_excel/" + sFileName +
		// "';");

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

	public ActionForward toExcel1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, RowsExceededException, WriteException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		String supplier_id = (String) dynaBean.get("supplier_id");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String querydate = queryDate(add_date_gt, add_date_lt);
		String userName = user.getStdEntpMain().getLinkman();
		String tel = user.getStdEntpMain().getTel();
		JxcSupplier jxcSupplier = new JxcSupplier();
		jxcSupplier.setId(Long.valueOf(supplier_id));
		jxcSupplier = super.getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);

		JxcStockBill jxcStockBill = new JxcStockBill();
		jxcStockBill.setSupplier_id(Long.valueOf(supplier_id));
		jxcStockBill.setShop_id(user.getStdEntpMain().getShop_id());
		jxcStockBill.getMap().put("add_date_lt", add_date_lt);
		jxcStockBill.getMap().put("add_date_gt", add_date_gt);

		List<JxcStockBill> jxcStockBillList = super.getFacade().getJxcStockBillService().getJxcStockBillListForSZMX(
				jxcStockBill);

		// 初始期初应付款
		BigDecimal m0 = jxcSupplier.getInit_pay();

		// 开始日期前进货单,收支单
		JxcStockBill jxcStockBill1 = new JxcStockBill();
		JxcSzDetails jxcSzDetails1 = new JxcSzDetails();
		jxcStockBill1.setShop_id(jxcSupplier.getShop_id());
		jxcStockBill1.setSupplier_id(Long.valueOf(supplier_id));
		jxcSzDetails1.setSz_obj_id(Long.valueOf(supplier_id));
		jxcSzDetails1.setShop_id(jxcSupplier.getShop_id());
		jxcStockBill1.getMap().put("add_date_lt_0", add_date_gt);
		jxcSzDetails1.getMap().put("add_date_lt_0", add_date_gt);
		jxcStockBill1 = super.getFacade().getJxcStockBillService().getJxcStockBillWithMoney(jxcStockBill1);
		jxcSzDetails1 = super.getFacade().getJxcSzDetailsService().getJxcSzDetailsWithMoney(jxcSzDetails1);

		// 开始日期之前，所有进货单总的应付款总和
		BigDecimal yf1 = new BigDecimal(0.0);
		if (jxcStockBill1.getMap().get("yf_pay") != null) {
			yf1 = (BigDecimal) jxcStockBill1.getMap().get("yf_pay");
		}

		// 开始日期之前，所有进货单总的实付款总和
		BigDecimal sf1 = new BigDecimal(0.0);
		if (jxcStockBill1.getMap().get("sf_pay") != null) {
			sf1 = (BigDecimal) jxcStockBill1.getMap().get("sf_pay");
		}

		// 开始日期之前，所有收支单总金额(支出)
		BigDecimal sk1 = new BigDecimal(0.0);
		if (jxcStockBill1.getMap().get("sz_pay") != null) {
			sk1 = (BigDecimal) jxcStockBill1.getMap().get("sz_pay");
		}
		// 期初应付款：M0 + YF1 - SF1-SK1
		BigDecimal qc_pay = m0.add(yf1).subtract(sf1).subtract(sk1);

		BigDecimal zj_pay = new BigDecimal(0);
		BigDecimal zf_pay = new BigDecimal(0);
		BigDecimal qm_pay = new BigDecimal(0);

		for (JxcStockBill jsb : jxcStockBillList) {
			zj_pay = zj_pay.add(jsb.getPay_money());
			zf_pay = zf_pay.add(jsb.getPaid_money());
			jsb.getMap().put("qmAmount", qc_pay.add(zj_pay).subtract(zf_pay));
		}
		qm_pay = qc_pay.add(zj_pay).subtract(zf_pay);
		createExcelFile1(request, response, userName, tel, querydate, jxcStockBillList, qc_pay, zj_pay, zf_pay, qm_pay,
				jxcSupplier);
		return null;
	}

	private void createExcelFile1(HttpServletRequest request, HttpServletResponse response, String userName,
			String tel, String querydate, List<JxcStockBill> jxcStockBillList, BigDecimal qcPay, BigDecimal zjPay,
			BigDecimal zfPay, BigDecimal qmPay, JxcSupplier jxcSupplier) throws IOException, RowsExceededException,
			WriteException {
		if (jxcStockBillList == null || jxcStockBillList.size() < 0) {
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
			WritableCellFormat wcf = new jxl.write.WritableCellFormat(new jxl.write.NumberFormat("￥0.00"));
			// 创建一个可写入的工作表
			// Label(column,row,content)其中column代表单元格的第column+1列，第row+1行, 单元格的内容是content
			WritableSheet sheet = wwb.createSheet(userName.concat("进货单列表"), 0);
			int row = 0;// 行
			int column = 0;// 列

			sheet.mergeCells(0, row, 6, row);// 合并单元格(左列，左行，右列，右行) 合并第row行，从第0列到第7列
			String info0 = "联系人：".concat(userName).concat("  联系电话：").concat(tel);
			Label label0 = new Label(0, row, info0);
			label0.setCellFormat(cellFormatRight);
			sheet.addCell(label0);

			row++;
			sheet.mergeCells(0, row, 6, row);
			Label label1 = new Label(0, row, "付款明细");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			label1 = new Label(0, row, "供应商" + jxcSupplier.getName() + "的明细账");
			label1.setCellFormat(cellFormatCenter);
			sheet.addCell(label1);

			row++;
			sheet.mergeCells(0, row, 6, row);
			label1 = new Label(0, row, querydate);
			label1.setCellFormat(cellFormatLeft);
			sheet.addCell(label1);

			row++;
			String[] title = { "行号", "业务日期", "业务编号", "摘要", "增加应付款", "支付应付款", "期末应付款" };
			for (int i = 0; i < title.length; i++) {
				sheet.addCell(new Label(i, row, title[i]));
			}

			row++;
			sheet.addCell(new jxl.write.Number(0, row, 1));
			sheet.mergeCells(1, row, 2, row);
			sheet.addCell(new Label(3, row, "期初结存"));
			sheet.mergeCells(4, row, 5, row);
			sheet.addCell(new jxl.write.Number(6, row, qcPay.doubleValue(), wcf));

			row++;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < jxcStockBillList.size(); i++) {
				column = 0;
				JxcStockBill jxcStockBill = jxcStockBillList.get(i);
				sheet.addCell(new jxl.write.Number(column++, row, i + 2));// 行号
				sheet.addCell(new Label(column++, row, format.format(jxcStockBill.getIn_date())));// 业务日期
				sheet.addCell(new Label(column++, row, jxcStockBill.getSn()));// 业务编号
				if (1 == Integer.parseInt(jxcStockBill.getMap().get("sm").toString())) {
					sheet.addCell(new Label(column++, row, "购进货品"));
				}
				if (0 == Integer.parseInt(jxcStockBill.getMap().get("sm").toString())) {
					sheet.addCell(new Label(column++, row, "付欠款/预付款"));
				}
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBill.getPay_money().doubleValue(), wcf));// 进货金额
				sheet.addCell(new jxl.write.Number(column++, row, jxcStockBill.getPaid_money().doubleValue(), wcf));// 进货金额
				sheet.addCell(new jxl.write.Number(column++, row, ((BigDecimal) jxcStockBill.getMap().get("qmAmount"))
						.doubleValue(), wcf));// 进货金额
				row++;
			}

			row++;
			sheet.addCell(new Label(0, row, "合计"));
			sheet.mergeCells(1, row, 3, row);
			sheet.addCell(new jxl.write.Number(4, row, zjPay.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(5, row, zfPay.doubleValue(), wcf));
			sheet.addCell(new jxl.write.Number(6, row, qmPay.doubleValue(), wcf));

			// 从内存中写入文件中
			wwb.write();
			// 关闭资源，释放内存
			wwb.close();
		}
		// String ctx = getCtxPath(request);
		// super.renderJavaScript(response, "window.location.href='" + ctx + "/files/download_excel/" + sFileName +
		// "';");

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
