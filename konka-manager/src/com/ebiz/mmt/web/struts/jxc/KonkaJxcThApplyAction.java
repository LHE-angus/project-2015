package com.ebiz.mmt.web.struts.jxc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaJxcThRecord;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author du,zhiming
 */
public class KonkaJxcThApplyAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {// 判断是否登录
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		PeProdUser peProdUser = super.getSessionUserInfo(request);
		if (null == peProdUser) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		// 产品大类
		// List<BasePdClazz> basePdClassList = super.getBasePdClazzListByDeptId(peProdUser.getDept_id());
		// request.setAttribute("basePdClassList", basePdClassList);

		PeRoleInfo role = super.getPeRoleInfoByUserId(peProdUser.getId());
		if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
				&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
			List<BasePdClazz> basePdClassList = super.getBaseAllPdClazzList();
			request.setAttribute("basePdClassList", basePdClassList);
		} else {
			// 产品类型（选择条件中授权的大类）
			BasePdClazz basePdClass = new BasePdClazz();
			basePdClass.setIs_del(0);
			basePdClass.getMap().put("getOwnDeptCls", "ture");
			basePdClass.getMap().put("dept_id", peProdUser.getDept_id());
			List<BasePdClazz> basePdClassList = super.getFacade().getBasePdClazzService().getBasePdClazzList(
					basePdClass);
			request.setAttribute("basePdClassList", basePdClassList);
		}

		// 所属仓库
		List<KonkaJxcStoreInfo> storeList = super.getStoreInfoListByDeptId(peProdUser.getDept_id());
		request.setAttribute("storeList", storeList);

		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		super.copyProperties(entity, dynaBean);
		entity.setIs_del(0);
		entity.setAdd_dept_id(peProdUser.getDept_id());
		entity.setBrand_id((Constants.KONKA_BRAND_ID));// 康佳
		Long recordCount = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaJxcThRecord> entityList = getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);
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
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		dynaBean.set("in_date", today);
		super.getModPopeDom(form, request);
		PeProdUser peProdUser = super.getSessionUserInfo(request);
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
		// 仓库
		List<KonkaJxcStoreInfo> storeList = super.getStoreInfoListByDeptId(peProdUser.getDept_id());
		request.setAttribute("storeList", storeList);
	

		return mapping.findForward("input");
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

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		dynaBean.set("add_user_id", peProdUser.getId());
		dynaBean.set("add_dept_id", peProdUser.getDept_id());
		String pd_big_type = request.getParameter("pd_big_type");
		// 仓库
		List<KonkaJxcStoreInfo> storeList = super.getStoreInfoListByDeptId(peProdUser.getDept_id());
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
		// 型号
		List<PePdModel> pePdModelList = super.getPePdModelListByDeptIdAndClsId(peProdUser.getDept_id(), new Long(
				pd_big_type));
		request.setAttribute("pePdModelList", pePdModelList);

		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecord(entity);
		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id"));

		super.copyProperties(form, entity);
		return mapping.findForward("input");
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
		PeProdUser peProdUser = super.getSessionUserInfo(request);
		if (null == peProdUser) {
			return null;
		}
		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		super.copyProperties(entity, dynaBean);

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
		entity.setBrand_id((Constants.KONKA_BRAND_ID));
		BaseBrandInfo brandInfo = new BaseBrandInfo();
		brandInfo = super.getBaseBrandInfo((Constants.KONKA_BRAND_ID));
		if (null != brandInfo) {
			entity.setBrand_name(brandInfo.getBrand_name());// 品牌名称 康佳
		}

		entity.setAdd_dept_id(peProdUser.getDept_id());// 添加人部门Id
		entity.setAdd_user_id(peProdUser.getId());// 用户id
		PeRoleInfo peRole = super.getPeRoleInfoByUserId(peProdUser.getId());
		if (null != peRole) {
			if (null != peRole.getRole_id() && GenericValidator.isLong(peRole.getRole_id().toString())) {
				entity.setAdd_user_type(peRole.getRole_id());// 添加用户类型
			}
		}
		entity.setAdd_user_name(peProdUser.getUser_name());
		if (null != peProdUser) {// 获取部门名称
			KonkaDept peDept = new KonkaDept();
			peDept = super.getKonkaDeptById(peProdUser.getDept_id());
			entity.setAdd_dept_name(peDept.getDept_name());
		}
		if (!GenericValidator.isLong(id)) {
			// entity.setShop_id(0L);
			super.getFacade().getKonkaJxcThRecordService().createKonkaJxcThRecord(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(peProdUser.getId());
			super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
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
			KonkaJxcThRecord entity = new KonkaJxcThRecord();
			entity.setId(Long.valueOf(id));
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			entity.setDel_user_id(peProdUser.getId());
			super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
			saveMessage(request, "entity.deleted");
		} else {
			for (String pk : pks) {
				KonkaJxcThRecord entity = new KonkaJxcThRecord();
				entity.setId(Long.valueOf(pk));
				entity.setIs_del(1);
				entity.setDel_date(new Date());
				entity.setDel_user_id(peProdUser.getId());
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

		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecord(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
		store.setIs_del(0);
		store.setId(entity.getTh_store_id_son());
		store = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(store);
		if (null != store) {
			dynaBean.set("store_name", store.getStore_name());
		}
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DecimalFormat df = new DecimalFormat("0.000000");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		entity.setIs_del(0);
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecord(entity);
		if (!GenericValidator.isLong(id) || null == entity) {
			saveMessage(request, "entity.missing");
			return this.list(mapping, form, request, response);
		}
		/********************* 验证库存 start **************************/
		KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
		konkaJxcStoreState = super.getKonkaJxcStoreStateNumByDeptAndStoreAndPd(peProdUser.getDept_id(), entity
				.getTh_store_id_son(), entity.getPd_id());
		if (null == konkaJxcStoreState) {
			String msg = "该产品库存不存在，不能确认！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		if (entity.getTh_type().intValue() == 0) {// 退货类型 为残次品
			if (null != konkaJxcStoreState.getPd_bad_num()
					&& (entity.getTh_count().intValue() > konkaJxcStoreState.getPd_bad_num().intValue())) {
				String msg = "退货数量大于残次品数，暂时不能确认请联系管理员!";
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}

			konkaJxcStoreState.setPd_bad_num(konkaJxcStoreState.getPd_bad_num() - entity.getTh_count());

		}
		if (entity.getTh_type().intValue() == 1) {// 退货类型 为正品
			if (null != konkaJxcStoreState.getPd_num()
					&& (entity.getTh_count().intValue() > konkaJxcStoreState.getPd_num().intValue())) {
				String msg = "退货数量大于产品数，请先进货!";
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
			Long pd_num = konkaJxcStoreState.getPd_num() - entity.getTh_count();
			// 更新实时成本价 实时成本价 =（当前库存 * 实时成本价 + 退货数量 * 退货价格）/ （当前库存 + 进货数量）
			Float f1 = konkaJxcStoreState.getPd_num() * konkaJxcStoreState.getCur_cost_price().floatValue();// 更新前库存总价
			Float f2 = entity.getTh_count() * entity.getPrice().floatValue();// 退货总价
			BigDecimal cur_cost_price = new BigDecimal(df.format(0));
			if (pd_num.intValue() != 0) {
				cur_cost_price = new BigDecimal(df.format((f1 - f2) / pd_num));
			}
			konkaJxcStoreState.setPd_num(pd_num);
			konkaJxcStoreState.setCur_cost_price(cur_cost_price);
		}
		entity.setKonkaJxcStoreState(konkaJxcStoreState);
		/********************* 验证库存 end **************************/
		entity.getMap().put("managerConfirm", "true");
		// super.copyProperties(entity, dynaBean);
		entity.setTh_confirm_date(new Date());
		entity.setTh_is_confirm(1);
		KonkaJxcStoreInfo konkaJxcStoreInfo = super.getKonkaStockById(entity.getTh_store_id_son());
		if (null != konkaJxcStoreInfo) {
			entity.getMap().put("storeName", konkaJxcStoreInfo.getStore_name());
		}
		entity.getMap().put("par_dept_id", peProdUser.getPar_dept_id());// 添加人上级部门Id进货记录使用
		super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
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

	public ActionForward getPdModle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String pd_big_type = (String) dynaBean.get("parentElementValue");

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
			sb.append("{\"date_id\":\"").append(temp.getPd_id()).append("\",");
			sb.append("\"date_desc\":\"").append(temp.getMd_name()).append("\"},");
		}
		sb.append("{\"x\":\"x\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

}
