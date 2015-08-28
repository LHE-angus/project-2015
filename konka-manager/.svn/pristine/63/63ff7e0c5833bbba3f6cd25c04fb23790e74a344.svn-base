package com.ebiz.mmt.web.struts.jxcnokey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcPdType;
import com.ebiz.mmt.domain.JxcStockBill;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.KonkaJxcThRecord;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,Yang
 * @version 2011-10-15
 */
public class JxcThApplyAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
//		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");

		request.setAttribute("not_validate_record", "true");
//		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		super.copyProperties(entity, form);

		entity.setIs_del(0);
		entity.setShop_id(user.getEntp_shop().getShop_id());

		Long recordCount = getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaJxcThRecord> entityList = getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		// 产品大类列表
		BasePdType pdType = new BasePdType();
		pdType.setIs_model((short) 1);
		pdType.setDel_mark((short) 0);
		List<BasePdType> basePdTypeList = getFacade().getBasePdTypeService().getBasePdTypeList(pdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		dynaBean.set("in_date", today);

		// 产品大类列表
		BasePdType basePdType = new BasePdType();
		basePdType.setDel_mark(new Short((short) 0));
		basePdType.setIs_model(new Short((short) 1));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(basePdType);
		request.setAttribute("basePdTypeList", basePdTypeList);

		List<JxcPdType> JxcPdTypeList = getFacade().getJxcPdTypeService().getJxcPdTypeList(new JxcPdType());
		request.setAttribute("JxcPdTypeList", JxcPdTypeList);

		// 品牌只限制康佳
		dynaBean.set("brand_id", Constants.KONKA_BRAND_ID);

//		dynaBean.set("own_sys", "1");
		dynaBean.set("own_sys", "0");// 默认所属系统
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
//		String keySeq = (String) dynaBean.get("keySeq");
		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_id = (String) dynaBean.get("pd_id");
		String brand_id = (String) dynaBean.get("brand_id");

		request.setAttribute("not_validate_record", "true");
//		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		if (StringUtils.isBlank(pd_id)) {
			String msg = "请选择产品型号！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		super.copyProperties(entity, form);

		BasePdType basePdType = new BasePdType();
		basePdType.setPd_type(Long.valueOf(pd_type_id));
		basePdType = getFacade().getBasePdTypeService().getBasePdType(basePdType);
		if (basePdType != null) {// 大类名称
			entity.setPd_type_name(basePdType.getPd_name());
		}
		JxcPd jxcPd = new JxcPd();
		jxcPd.setId(Long.valueOf(pd_id));
		jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
		if (null == jxcPd) {
			String msg = "产品不存在，不能确认！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		entity.setPd_name(jxcPd.getName());
		Long th_count = entity.getTh_count();
		Long cur_count = jxcPd.getCount();// 当前库存
		if (cur_count.intValue() < th_count.intValue()) {
			String msg = "当期库存为[" + cur_count + "]，小于退货数量[" + th_count + "]，请先进货，否则不能进行退货操作！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		entity.setShop_id(user.getEntp_shop().getShop_id());
		entity.setTh_is_confirm(0);
		entity.setIs_del(0);

		boolean isNotKonka = false;
		// 非康佳产品直接退货成功
		if (StringUtils.isNotBlank(brand_id) && !String.valueOf(Constants.KONKA_BRAND_ID).equals(brand_id)) {
			entity.setIs_audit(1);
			entity.setApproval_date(new Date());
			entity.setAudit_reason("非康佳产品直接退货成功！");
			entity.setTh_is_confirm(1);
			entity.setTh_confirm_date(new Date());
			entity.setAudit_user_id(user.getEntp_shop().getShop_id());
			isNotKonka = true;
		}

		Long id = super.getFacade().getKonkaJxcThRecordService().createKonkaJxcThRecord(entity);
		saveMessage(request, "entity.inserted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		if (isNotKonka) {// 非康佳产品，审核通过后，直接到confirm方法
			pathBuffer.append("/JxcThApply.do?method=confirm");
			pathBuffer.append("&id=").append(id);
		} else {
			pathBuffer.append(mapping.findForward("success").getPath());
		}
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcThRecord entity = new KonkaJxcThRecord();

			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecord(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			// dynaBean.set("add_date", DateFormatUtils.format(entity.getAdd_date(), "yyyy-MM-dd"));

			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
//		String keySeq = (String) dynaBean.get("keySeq");

//		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaJxcThRecord entity = new KonkaJxcThRecord();
			entity.setId(Long.valueOf(id));
			entity.setIs_del(1);
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(user.getEntp_shop().getShop_id());
			entity.setDel_date(new Date());
			entity.setDel_user_id(user.getEntp_shop().getShop_id());
			super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
			saveMessage(request, "entity.deleted");
		} else {
			for (String pk : pks) {
				KonkaJxcThRecord entity = new KonkaJxcThRecord();
				entity.setId(Long.valueOf(pk));
				entity.setIs_del(1);
				entity.setUpdate_date(new Date());
				entity.setUpdate_user_id(user.getEntp_shop().getShop_id());
				entity.setDel_date(new Date());
				entity.setDel_user_id(user.getEntp_shop().getShop_id());
				super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
				saveMessage(request, "entity.deleted");
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
//		String keySeq = (String) dynaBean.get("keySeq");

//		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		if (GenericValidator.isLong(id)) {
			Long shop_id = user.getEntp_shop().getShop_id();
			Long shop_p_index = Long.valueOf(user.getEntp_shop().getP_index());
			KonkaJxcThRecord entity = new KonkaJxcThRecord();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecord(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			Long pd_id = entity.getPd_id();
			Long th_count = entity.getTh_count();

			JxcPd jxcPd = new JxcPd();
			jxcPd.setId(pd_id);
			jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
			if (null == jxcPd) {
				String msg = "产品不存在，不能确认！";
				renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
			Long cur_count = jxcPd.getCount();// 当前库存
			if (cur_count.intValue() < th_count.intValue()) {
				String msg = "当期库存为[" + cur_count + "]，小于退货数量[" + th_count + "]，请先进货，否侧不能进行确认操作！";
				renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
			entity.setTh_is_confirm(1);
			entity.setTh_confirm_date(new Date());

			/* 插入进货记录，插入一条数量为(退货数量*-1) BEGIN */
			List<JxcPd> listJxcPd = new ArrayList<JxcPd>();
			listJxcPd.add(jxcPd);// 添加产品

			Long count = th_count * -1;
			Double payMoney = jxcPd.getCur_cost_price().doubleValue() * count;
			JxcStockBill jxcStockBill = new JxcStockBill();
			jxcStockBill.setShop_id(user.getEntp_shop().getShop_id());
			jxcStockBill.setShop_p_index(super.getStdEntpMainByShopId(shop_id).getP_index().longValue());
			jxcStockBill.setAdd_user_id(user.getId());
			jxcStockBill.setSn("JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 设置进货单号
			jxcStockBill.setIn_date(new Date());// 设置进货日期
			jxcStockBill.setOpr_man(user.getUser_name());
			jxcStockBill.setPay_money(new BigDecimal(payMoney));
			jxcStockBill.setPaid_money(new BigDecimal(payMoney));
			jxcStockBill.setOwn_sys(6);// 6康佳进销存 退货
			jxcStockBill.setRemarks(Constants.JXC_TH_REMARK + ":[" + id + "]");
			jxcStockBill.setJxcPdList(listJxcPd);// 进货单设置产品

			JxcStockBillDetails jxcStockBillDetails = new JxcStockBillDetails();
			jxcStockBillDetails.setPd_id(jxcPd.getId());
			jxcStockBillDetails.setShop_id(shop_id);
			jxcStockBillDetails.setPd_type(jxcPd.getPd_type());
			jxcStockBillDetails.setPd_type_name(jxcPd.getPd_type_name());
			jxcStockBillDetails.setBrand_id(jxcPd.getBrand_id());
			jxcStockBillDetails.setBrand_name(jxcPd.getBrand_name());
			jxcStockBillDetails.setPd_name(jxcPd.getName());// 型号名称
			jxcStockBillDetails.setCount(count);
			jxcStockBillDetails.setPrice(jxcPd.getCur_cost_price());
			jxcStockBillDetails.setRemarks(Constants.JXC_TH_REMARK + ":[" + id + "]");
			List<JxcStockBillDetails> listJxcStockBillDetails = new ArrayList<JxcStockBillDetails>();
			listJxcStockBillDetails.add(jxcStockBillDetails);// 添加进货单明细
			jxcStockBill.setJxcStockBillDetailsList(listJxcStockBillDetails);

			JxcSupplier jxcSupplier = new JxcSupplier();
			JxcSupplier jxcSupplierNew = new JxcSupplier();
			jxcSupplier.setShop_id(shop_id);
			jxcSupplier.setName(Constants.JXC_TH_SUPPLIER_NAME);
			jxcSupplier.setIs_del(0);
			super.copyProperties(jxcSupplierNew, jxcSupplier);
			jxcSupplier = getFacade().getJxcSupplierService().getJxcSupplier(jxcSupplier);
			Long jxc_supplier_id = 0l;
			if (null != jxcSupplier) {
				jxc_supplier_id = jxcSupplier.getId();
			} else {// 如果找不到默认的退货供应商，添加一个新的
				jxcSupplierNew.setShop_p_index(shop_p_index);
				jxcSupplierNew.setAdd_date(new Date());
				jxcSupplierNew.setAdd_user_id(user.getId());
				jxc_supplier_id = getFacade().getJxcSupplierService().createJxcSupplier(jxcSupplierNew);
			}
			jxcStockBill.setSupplier_id(Long.valueOf(jxc_supplier_id));// 设置进货单供应商ID，防止选择康佳品牌时无此ID
			entity.setJxcStockBill(jxcStockBill);
			/* 进货记录，插入一条数量为(退货数量*-1) END */

			entity.getMap().put("shopConfirm", true);// 进销存网点退货确认
			getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			saveMessage(request, "entity.updated");

			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&");
			pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

	}

	public ActionForward getJxcPdList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String own_sys = (String) dynaBean.get("own_sys");
		String pd_type = (String) dynaBean.get("pd_type");
		String brand_id = (String) dynaBean.get("brand_id");
		String jxc_pd_type_id = (String) dynaBean.get("jxc_pd_type_id");
//		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
//		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		if (StringUtils.isBlank(brand_id) || StringUtils.isBlank(pd_type) || StringUtils.isBlank(own_sys)) {
			return null;
		}

		JxcPd jxcPd = new JxcPd();
		jxcPd.setOwn_sys(Integer.valueOf(own_sys));
		jxcPd.setShop_id(user.getEntp_shop().getShop_id());
		if (StringUtils.isNotBlank(jxc_pd_type_id)) {
			jxcPd.setJxc_pd_type_id(Long.valueOf(jxc_pd_type_id));
		}
		jxcPd.setPd_type(Long.valueOf(pd_type));
		jxcPd.setBrand_id(Long.valueOf(brand_id));

		List<JxcPd> list = getFacade().getJxcPdService().getJxcPdList(jxcPd);

		StringBuffer sb = new StringBuffer("[");
		for (JxcPd pm : list) {
			// String values =pm.getId()+ "@#" + pm.getCount();
			sb.append("{\"id\":\"" + pm.getId() + "\",");
			sb.append("\"name\":\"" + pm.getName() + "\"},");
		}
		sb.append("{\"end\":\"rz\"}]");

		super.renderJson(response, sb.toString());

		return null;
	}

}