package com.ebiz.mmt.web.struts.jxc;

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
import com.ebiz.mmt.domain.KonkaJxcHhRecord;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,Peng
 * @version 2011-10-18
 */
public class JxcHhApplyAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		super.copyProperties(entity, form);

		entity.setIs_del(0);
		entity.setShop_id(user.getStdEntpMain().getShop_id());

		Long recordCount = getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecordCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaJxcHhRecord> entityList = getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecordPaginatedList(
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

		dynaBean.set("own_sys", "1");
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String pd_id = (String) dynaBean.get("pd_id");
		String brand_id = (String) dynaBean.get("brand_id");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		if (StringUtils.isBlank(pd_id)) {
			String msg = "请选择产品型号！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
		super.copyProperties(entity, form);

		entity.setShop_id(user.getStdEntpMain().getShop_id());
		entity.setHh_is_confirm(0);
		entity.setIs_del(0);

		JxcPd jxcPd = new JxcPd();
		jxcPd.setId(Long.valueOf(pd_id));
		jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
		if (null == jxcPd) {
			String msg = "产品不存在，不能确认！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		entity.setPd_name(jxcPd.getName());
		Long hh_count = entity.getHh_count();
		Long cur_count = jxcPd.getCount();// 当前库存
		if (cur_count.intValue() < hh_count.intValue()) {
			String msg = "当期库存为[" + cur_count + "]，小于换货数量[" + hh_count + "]，请先进货，否侧不能进行换货操作！";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}
		
		// 非康佳产品直接换货成功
		if (StringUtils.isNotBlank(brand_id) && !String.valueOf(Constants.KONKA_BRAND_ID).equals(brand_id)) {
			entity.setIs_audit(1);
			entity.setApproval_date(new Date());
			entity.setAudit_reason("非康佳产品直接换货成功！");
			entity.setHh_is_confirm(1);
			entity.setHh_confirm_date(new Date());
			entity.setAudit_user_id(user.getStdEntpMain().getShop_id());
		}

		BasePdType basePdType = new BasePdType();
		basePdType.setPd_type(Long.valueOf(pd_type_id));
		basePdType = getFacade().getBasePdTypeService().getBasePdType(basePdType);
		if (basePdType != null) {// 大类名称
			entity.setPd_type_name(basePdType.getPd_name());
		}
		entity.setPd_name(jxcPd.getName());

		super.getFacade().getKonkaJxcHhRecordService().createKonkaJxcHhRecord(entity);
		saveMessage(request, "entity.inserted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
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
			KonkaJxcHhRecord entity = new KonkaJxcHhRecord();

			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);
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
		String keySeq = (String) dynaBean.get("keySeq");

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
			entity.setId(Long.valueOf(id));
			entity.setIs_del(1);
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(user.getStdEntpMain().getShop_id());
			entity.setDel_date(new Date());
			entity.setDel_user_id(user.getStdEntpMain().getShop_id());
			super.getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);
			saveMessage(request, "entity.deleted");
		} else {
			for (String pk : pks) {
				KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
				entity.setId(Long.valueOf(pk));
				entity.setIs_del(1);
				entity.setUpdate_date(new Date());
				entity.setUpdate_user_id(user.getStdEntpMain().getShop_id());
				entity.setDel_date(new Date());
				entity.setDel_user_id(user.getStdEntpMain().getShop_id());
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

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcHhRecord entity = new KonkaJxcHhRecord();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaJxcHhRecordService().getKonkaJxcHhRecord(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setHh_is_confirm(1);
			getFacade().getKonkaJxcHhRecordService().modifyKonkaJxcHhRecord(entity);

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			// dynaBean.set("add_date", DateFormatUtils.format(entity.getAdd_date(), "yyyy-MM-dd"));

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
		String keySeq = (String) dynaBean.get("keySeq");

		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}

		if (StringUtils.isBlank(brand_id) || StringUtils.isBlank(pd_type) || StringUtils.isBlank(own_sys)) {
			return null;
		}

		JxcPd jxcPd = new JxcPd();
		jxcPd.setOwn_sys(Integer.valueOf(own_sys));
		jxcPd.setShop_id(user.getStdEntpMain().getShop_id());
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