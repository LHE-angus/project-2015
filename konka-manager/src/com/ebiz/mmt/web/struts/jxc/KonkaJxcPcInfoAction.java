package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaJxcPcInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Guo,Jun
 * @version 2011-10-10
 */
public class KonkaJxcPcInfoAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
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
		String cls_id = (String) dynaBean.get("cls_id");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaJxcPcInfo entity = new KonkaJxcPcInfo();
		super.copyProperties(entity, form);

		entity.setPc_type(1);// 管理端盘存信息
		entity.setAdd_dept_id(userInfo.getDept_id());
		entity.setBrand_id(Constants.KONKA_BRAND_ID);
		if (StringUtils.isNotBlank(cls_id)) {
			entity.setPd_type_id(Long.valueOf(cls_id));
		}

		Long recordCount = getFacade().getKonkaJxcPcInfoService().getKonkaJxcPcInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaJxcPcInfo> entityList = getFacade().getKonkaJxcPcInfoService().getKonkaJxcPcInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 产品大类列表
		BasePdClazz pdClazz = new BasePdClazz();
		pdClazz.getMap().put("getOwnDeptCls", "ture");
		pdClazz.getMap().put("dept_id", userInfo.getDept_id());
		pdClazz.setIs_del(0);
		List<BasePdClazz> pdClazzList = getFacade().getBasePdClazzService().getBasePdClazzList(pdClazz);
		request.setAttribute("pdClazzList", pdClazzList);

		// 仓库信息列表
		KonkaJxcStoreInfo si = new KonkaJxcStoreInfo();
		si.setAdd_dept_id(userInfo.getDept_id());
		si.setIs_del(0);
		List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(si);
		request.setAttribute("storeList", storeList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "method"));
		dynaBean.set("pc_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		// 产品大类列表
		BasePdClazz pdClazz = new BasePdClazz();
		pdClazz.getMap().put("getOwnDeptCls", "ture");
		pdClazz.getMap().put("dept_id", userInfo.getDept_id());
		pdClazz.setIs_del(0);
		List<BasePdClazz> pdClazzList = getFacade().getBasePdClazzService().getBasePdClazzList(pdClazz);
		request.setAttribute("pdClazzList", pdClazzList);

		// 仓库信息列表
		KonkaJxcStoreInfo si = new KonkaJxcStoreInfo();
		si.setAdd_dept_id(userInfo.getDept_id());
		si.setIs_del(0);
		List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(si);
		request.setAttribute("storeList", storeList);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcPcInfo entity = new KonkaJxcPcInfo();

			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaJxcPcInfoService().getKonkaJxcPcInfo(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			// 仓库信息列表
			KonkaJxcStoreInfo si = new KonkaJxcStoreInfo();
			si.setAdd_dept_id(userInfo.getDept_id());
			si.setIs_del(0);
			List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(si);
			request.setAttribute("storeList", storeList);
			dynaBean.set("add_date", entity.getAdd_date());

			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String id = (String) dynaBean.get("id");
		String cls_id = (String) dynaBean.get("cls_id");
		String pd_id = (String) dynaBean.get("pd_id");
		String pc_date = (String) dynaBean.get("pc_date");

		KonkaJxcPcInfo entity = new KonkaJxcPcInfo();

		super.copyProperties(entity, form);
		entity.setPd_type_id(Long.valueOf(cls_id));

		// 产品大类名称
		BasePdClazz pdClazz = new BasePdClazz();
		pdClazz.setIs_del(0);
		pdClazz.setCls_id(Long.valueOf(cls_id));
		pdClazz = getFacade().getBasePdClazzService().getBasePdClazz(pdClazz);
		if (null != pdClazz) {
			entity.setPd_type_name(pdClazz.getCls_name());
		}

		// 型号名称
		PePdModel pdModle = new PePdModel();
		pdModle.setPd_id(Long.valueOf(pd_id));
		pdModle = getFacade().getPePdModelService().getPePdModel(pdModle);
		if (null != pdModle) {
			entity.setPd_name(pdModle.getMd_name());
		}

		if (null == entity.getId() || !GenericValidator.isLong(id)) {

			if (StringUtils.isBlank(pc_date)) {
				entity.setPc_date(new Date());
			}
			entity.setAdd_user_id(userInfo.getId());
			entity.setAdd_date(new Date());
			entity.setAdd_dept_id(userInfo.getDept_id());
			entity.setOwn_sys(0);// 默认买卖提
			entity.setBrand_id(Constants.KONKA_BRAND_ID);
			BaseBrandInfo temp = new BaseBrandInfo(entity.getBrand_id());
			temp = getFacade().getBaseBrandInfoService().getBaseBrandInfo(temp);
			if (null != temp) {
				entity.setBrand_name(temp.getBrand_name());
			}
			entity.setPc_type(1);
			entity.setIs_del(0);

			KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();// 管理端更新库存状态
			konkaJxcStoreState.setDept_id(entity.getAdd_dept_id());
			konkaJxcStoreState.setStore_id(entity.getStore_id());
			konkaJxcStoreState.setPd_id(entity.getPd_id());
			konkaJxcStoreState = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(konkaJxcStoreState);
			if (null == konkaJxcStoreState) {
				String msg = "该型号产品,沒有库存记录不能盘存！";
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
			konkaJxcStoreState.setPd_num(entity.getPc_num());
			entity.setKonkaJxcStoreState(konkaJxcStoreState);
			super.getFacade().getKonkaJxcPcInfoService().createKonkaJxcPcInfo(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaJxcPcInfoService().modifyKonkaJxcPcInfo(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaJxcPcInfo entity = new KonkaJxcPcInfo();
			entity.setId(new Long(id));
			super.getFacade().getKonkaJxcPcInfoService().removeKonkaJxcPcInfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaJxcPcInfo entity = new KonkaJxcPcInfo();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaJxcPcInfoService().removeKonkaJxcPcInfo(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward getPePdModle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String pd_big_type = (String) dynaBean.get("pd_big_type");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("getOwnDeptCls", "ture");
		pePdModel.getMap().put("dept_id", userInfo.getDept_id());
		pePdModel.getMap().put("cls_id", pd_big_type);
		List<PePdModel> ppmList = getFacade().getPePdModelService().getPePdModelList(pePdModel);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (PePdModel temp : ppmList) {
			sb.append("{\"pd_id\":\"").append(temp.getPd_id()).append("\",");
			sb.append("\"md_name\":\"").append(temp.getMd_name()).append("\"},");
		}
		sb.append("{\"x\":\"x\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	public ActionForward getStoreState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String pd_id = (String) dynaBean.get("pd_id");
		String store_id = (String) dynaBean.get("store_id");

		KonkaJxcStoreState kjss = new KonkaJxcStoreState();
		kjss.setBrand_id(Constants.KONKA_BRAND_ID);
		kjss.setPd_id(Long.valueOf(pd_id));
		kjss.setDept_id(userInfo.getDept_id());
		kjss.setStore_id(Long.valueOf(store_id));
		kjss = super.getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(kjss);
		Long count = -9l;

		if (null != kjss) {
			count = kjss.getPd_num();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("{\"pd_num\":\"").append(count).append("\"},");
		sb.append("{\"x\":\"x\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}
}