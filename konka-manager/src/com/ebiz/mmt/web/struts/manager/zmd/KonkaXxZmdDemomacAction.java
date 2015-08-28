package com.ebiz.mmt.web.struts.manager.zmd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdDemomac;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Yuan
 * @version 2012-01-09
 */
public class KonkaXxZmdDemomacAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		if (GenericValidator.isLong(zmd_id)) {
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(Long.valueOf(zmd_id));
			konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
			request.setAttribute("zmd_id", zmd_id);
		}

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setDept_id(dept_id);
		entity.setArc_state(20300);
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);
		request.setAttribute("zmdList", entityList);

		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setDept_id(dept_id);
		konkaXxPd.setAudit_state(Integer.valueOf(1));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		konkaXxPd.getMap().put("isSales", format.format(new Date()));
		// konkaXxPd.setPd_type(new Long(70400));
		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		request.setAttribute("konkaXxPdList", konkaXxPdList);
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String cls_id = (String) dynaBean.get("cls_id");
		String md_name = (String) dynaBean.get("md_name");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String state = (String) dynaBean.get("state");

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);

		// 用户角色
		PeProdUser user_id = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() < 40 && temp.getRole_id() >= 30)) {
					role_id_btw_300_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		KonkaXxPd entity = new KonkaXxPd();
		entity.getMap().put("cls_id_like", cls_id);
		entity.getMap().put("md_name_like", md_name);
		entity.getMap().put("zmd_sn", zmd_sn);
		entity.getMap().put("state", state);
		entity.getMap().put("state_is_not_null", true);
		if (role_id_btw_300_400) {
			entity.setDept_id(dept_id);
		}
		Long recordCount = getFacade().getKonkaXxPdService().getKonkaXxPdCountForDemo(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxPd> entityList = getFacade().getKonkaXxPdService().getKonkaXxPdPaginatedListForDemo(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("bpcList", getBasePdClazzList());

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setArc_state(20300);
		if (role_id_btw_300_400) {
			konkaXxZmd.setDept_id(dept_id);
		}
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		request.setAttribute("konkaXxZmdList", konkaXxZmdList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String zmd_id = (String) dynaBean.get("zmdList");
		String md_name = (String) dynaBean.get("konkaXxPdList");
		// String from_store = (String) dynaBean.get("konkaJxcStoreInfoList");
		// String[] stores = StringUtils.split(from_store, "-");

		KonkaXxZmdDemomac entity = new KonkaXxZmdDemomac();
		super.copyProperties(entity, form);
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setMd_name(md_name);
		// entity.setFrom_store(Long.valueOf(stores[0]));
		// entity.setFrom_store_name(stores[1]);

		KonkaXxZmdDemomac entity1 = new KonkaXxZmdDemomac();
		entity1.setZmd_id(entity.getZmd_id());
		entity1.setMd_name(entity.getMd_name());
		entity1.getMap().put("id_not_eq", entity.getId());
		List<KonkaXxZmdDemomac> entity1List = super.getFacade().getKonkaXxZmdDemomacService().getKonkaXxZmdDemomacList(
				entity1);
		if (null != entity1List && entity1List.size() > 0) {
			saveError(request, "konka.zmd.demo.mac.repit", entity.getMd_name());
			return this.list(mapping, form, request, response);
		}

		if (null == entity.getId()) {
			// 当前时间
			entity.setDist_date(new Date());
			// 当前用户
			entity.setDist_user_id(super.getSessionUserInfo(request).getId());
			getFacade().getKonkaXxZmdDemomacService().createKonkaXxZmdDemomac(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaXxZmdDemomacService().modifyKonkaXxZmdDemomac(entity);
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		Long dept_id = null;
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			dept_id = kDept.getDept_id();

		if (GenericValidator.isLong(id)) {
			KonkaXxZmdDemomac entity = new KonkaXxZmdDemomac();
			entity.setId(new Long(id));
			entity = getFacade().getKonkaXxZmdDemomacService().getKonkaXxZmdDemomac(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
			request.setAttribute("zmd_id", entity.getZmd_id());
			request.setAttribute("md_name", entity.getMd_name());
			request.setAttribute("store_name", entity.getFrom_store());
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(entity.getZmd_id());
			konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
			if (null != konkaXxZmd) {
				request.setAttribute("zmd_sn", konkaXxZmd.getZmd_sn());
			}

			KonkaXxZmd konkaXxZmd1 = new KonkaXxZmd();
			konkaXxZmd1.setDept_id(dept_id);
			konkaXxZmd1.setArc_state(20300);
			List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd1);
			request.setAttribute("zmdList", entityList);

			KonkaXxPd konkaXxPd1 = new KonkaXxPd();
			konkaXxPd1.setDept_id(dept_id);
			konkaXxPd1.setAudit_state(Integer.valueOf(1));
			// konkaXxPd1.setPd_type(new Long(70400));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			konkaXxPd1.getMap().put("isSales", format.format(new Date()));
			List<KonkaXxPd> konkaXxPdList1 = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd1);
			request.setAttribute("konkaXxPdList", konkaXxPdList1);

			KonkaJxcStoreInfo konkaJxcStoreInfo = new KonkaJxcStoreInfo();
			konkaJxcStoreInfo.setAdd_dept_id(dept_id);
			konkaJxcStoreInfo.setIs_del(0);
			List<KonkaJxcStoreInfo> konkaJxcStoreInfoList = super.getFacade().getKonkaJxcStoreInfoService()
					.getKonkaJxcStoreInfoList(konkaJxcStoreInfo);
			request.setAttribute("konkaJxcStoreInfoList", konkaJxcStoreInfoList);

			KonkaXxPd konkaXxPd = new KonkaXxPd();
			konkaXxPd.setMd_name(entity.getMd_name());
			konkaXxPd.setDept_id(dept_id);
			List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
			if (null != konkaXxPdList && konkaXxPdList.size() > 0) {
				request.setAttribute("dept_pd_id", konkaXxPdList.get(0).getDept_pd_id());
			}
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			if (null != entity.getUp_date()) {
				entity.getMap().put("up_date", format1.format(entity.getUp_date()));
			}
			if (null != entity.getDown_date()) {
				entity.getMap().put("down_date", format1.format(entity.getDown_date()));
			}
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward forwardToKonkaZmdList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.getMap().put("zmd_sn_like", zmd_sn_like);
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		entity.setArc_state(20300);
		Long recordCount = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(10);
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdDemomac/list_konkaxxzmd.jsp");

	}

	public ActionForward forwardToKonkapdlist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String md_name_like = (String) dynaBean.get("md_name_like");

		KonkaXxPd entity = new KonkaXxPd();
		entity.getMap().put("md_name_like", md_name_like);
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		entity.setPd_type(new Long(70400));
		Long recordCount = super.getFacade().getKonkaXxPdService().getKonkaXxPdCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(10);
		List<KonkaXxPd> entityList = super.getFacade().getKonkaXxPdService().getKonkaXxPdPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdDemomac/list_konkaxxpd.jsp");

	}

	public ActionForward forwardToKonkaStorelist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_name_like = (String) dynaBean.get("store_name_like");

		KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
		entity.getMap().put("store_name_like", store_name_like);
		KonkaDept kDept = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
		if (kDept != null)
			entity.setAdd_dept_id(kDept.getDept_id());
		entity.setIs_del(0);
		Long recordCount = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(10);
		List<KonkaJxcStoreInfo> entityList = super.getFacade().getKonkaJxcStoreInfoService()
				.getKonkaJxcStoreInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/zmd/KonkaXxZmdDemomac/list_konkastore.jsp");

	}
}
