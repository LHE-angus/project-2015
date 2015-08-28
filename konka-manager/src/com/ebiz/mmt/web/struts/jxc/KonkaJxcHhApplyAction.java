package com.ebiz.mmt.web.struts.jxc;

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

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcHhRecord;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.jxc.BaseJxcAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,Peng
 * @version 2011-10-18
 */
public class KonkaJxcHhApplyAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		dynaBean.set("add_user_id", peProdUser.getId());
		dynaBean.set("add_dept_id", peProdUser.getDept_id());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		dynaBean.set("in_date", today);

		// 仓库
		List<KonkaJxcStoreInfo> storeList = getStoreInfoListByDeptId(peProdUser.getDept_id());
		request.setAttribute("storeList", storeList);
		// 获取用户角色
		PeRoleInfo role = new PeRoleInfo();
		role = super.getPeRoleInfoByUserId(peProdUser.getId());
		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();

		if (null != role) {
			if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
					&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
				basePdClassList = super.getBaseAllPdClazzList();
				request.setAttribute("role_id_syb", "syb");
			} else {// 分公司
				basePdClassList = super.getBasePdClazzListByDeptId(peProdUser.getDept_id());
			}

		}
		request.setAttribute("basePdClassList", basePdClassList);

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		PeProdUser peProdUser = super.getSessionUserInfo(request);
		if (null == peProdUser) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		Pager pager = (Pager) dynaBean.get("pager");

		/*
		 * BasePdClazz basePdClass = new BasePdClazz(); basePdClass.setIs_del(0); List<BasePdClazz> basePdClassList =
		 * super.getFacade().getBasePdClazzService().getBasePdClazzList(basePdClass);
		 * request.setAttribute("basePdClassList", basePdClassList); BaseBrandInfo baseBrand = new BaseBrandInfo();
		 * List<BaseBrandInfo> baseBrandList =
		 * super.getFacade().getBaseBrandInfoService().getBaseBrandInfoList(baseBrand);
		 * request.setAttribute("baseBrandList", baseBrandList); PePdModel pePdModel = new PePdModel();
		 * pePdModel.setIs_del(0); List<PePdModel> pePdModelList =
		 * super.getFacade().getPePdModelService().getPePdModelList(pePdModel); request.setAttribute("pePdModelList",
		 * pePdModelList);
		 */

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		super.copyProperties(entity, dynaBean);
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		entity.setIs_del(0);
		entity.setAdd_dept_id(peProdUser.getDept_id());
		Long recordCount = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecordCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaJxcHhRecord> entityList = super.getFacade().getKonkaJxcHhRecordService()
				.getKonkaJxcHhRecordPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String pd_id = (String) dynaBean.get("pd_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		super.copyProperties(entity, dynaBean);

		entity.setIs_del(0);
		entity.setIs_audit(0);
		entity.setHh_is_confirm(0);
		entity.setAdd_user_name(peProdUser.getUser_name());
		if (null != peProdUser) {// 获取部门名称
			KonkaDept peDept = new KonkaDept();
			peDept = super.getKonkaDeptById(peProdUser.getDept_id());
			entity.setAdd_dept_name(peDept.getDept_name());
		}

		// 型号名称
		PePdModel pdModle = new PePdModel();
		pdModle = super.getPePdModel(Long.valueOf(pd_id));
		if (null == pdModle) {
			return null;
		}
		entity.setPd_name(pdModle.getMd_name());

		// 产品大类名称
		Long cls_id = pdModle.getCls_id();
		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz = super.getBasePdClazz(cls_id);
		if (null == basePdClazz) {
			return null;
		}
		entity.setPd_type_id(cls_id);
		entity.setPd_type_name(basePdClazz.getCls_name());

		// 品牌 康佳
		entity.setBrand_id(Constants.KONKA_BRAND_ID);
		BaseBrandInfo brandInfo = new BaseBrandInfo();
		brandInfo = super.getBaseBrandInfo(Constants.KONKA_BRAND_ID);
		if (null != brandInfo) {
			entity.setBrand_name(brandInfo.getBrand_name());// 品牌名称 康佳
		}
		if (!GenericValidator.isLong(id)) {// 创建用户
			// 审批人类型
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(peProdUser.getId());
			List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

			PeRoleInfo roleInfo = new PeRoleInfo();
			roleInfo.setIs_del(0);
			List<PeRoleInfo> roleInfoList = super.getFacade().getPeRoleInfoService().getPeRoleInfoList(roleInfo);
			for (PeRoleUser pr : roleUserList) {
				boolean flag = false;
				for (PeRoleInfo pri : roleInfoList) {
					if (pr.getRole_id().equals(pri.getRole_id())) {
						flag = true;
						entity.setAdd_user_type(pri.getRole_id());
						break;
					}
				}
				if (flag == true)
					break;
			}
			super.getFacade().getKonkaJxcHhRecordService().createKonkaJxcHhRecord(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);
			super.saveMessage(request, "entity.updated");
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);

		if (!GenericValidator.isLong(id) || null == entity) {
			return this.list(mapping, form, request, response);
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 仓库
		List<KonkaJxcStoreInfo> storeList = getStoreInfoListByDeptId(peProdUser.getDept_id());
		request.setAttribute("storeList", storeList);

		// 获取用户角色
		PeRoleInfo role = new PeRoleInfo();
		role = super.getPeRoleInfoByUserId(peProdUser.getId());
		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();

		if (null != role) {
			if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
					&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {
				basePdClassList = super.getBaseAllPdClazzList();
				request.setAttribute("role_id_syb", "syb");// 事业部
			} else {
				basePdClassList = super.getBasePdClazzListByDeptId(peProdUser.getDept_id());
			}

		}
		request.setAttribute("basePdClassList", basePdClassList);

		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
			entity.setId(Long.valueOf(id));
			entity.setIs_del(1);
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(peProdUser.getId());
			entity.setDel_date(new Date());
			entity.setDel_user_id(peProdUser.getId());
			super.getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);
			saveMessage(request, "entity.deleted");
		} else {
			for (String pk : pks) {
				KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
				entity.setId(Long.valueOf(pk));
				entity.setIs_del(1);
				entity.setUpdate_date(new Date());
				entity.setUpdate_user_id(peProdUser.getId());
				entity.setDel_date(new Date());
				entity.setDel_user_id(peProdUser.getId());
				super.getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);
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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
		if (null != entity.getHh_store_id_son()) {
			store.setIs_del(0);
			store.setId(entity.getHh_store_id_son());
			store = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(store);
			if (null != store) {
				dynaBean.set("store_name", store.getStore_name());
			}
		}
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		entity.setIs_del(0);
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);
		if (!GenericValidator.isLong(id) || null == entity) {
			return this.list(mapping, form, request, response);
		} else {
			super.copyProperties(entity, dynaBean);
			entity.setHh_confirm_date(new Date());
			entity.setHh_is_confirm(1);
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(peProdUser.getId());
			super.getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);
		}
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
	}

}