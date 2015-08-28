package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileAnnouncement;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.oa.BaseMmtoaAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-04-22
 */

public class DocIssueAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		return mapping.findForward("input");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_le_29 = false;
		boolean role_id_ge_30_lt_39 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() <= 29L) {
				role_id_le_29 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 39L) {
				role_id_ge_30_lt_39 = true;
			}
		}

		KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
		entity.getMap().put("title_like", title_like);
		
		// 角色判断处理
		if(role_id_le_29){ // 康佳总部
			
		} else if (role_id_ge_30_lt_39){ // 分公司
			entity.setSubcomp_id(userInfo.getDept_id());
		} else { // 业务员
			entity.setReceiver_id(userInfo.getId());
		}
		
		Long recordCount = getFacade().getKonkaMobileAnnouncementService().getKonkaMobileAnnouncementCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileAnnouncement> entityList = getFacade().getKonkaMobileAnnouncementService().getKonkaMobileAnnouncementPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		// resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
		super.copyProperties(entity, form);
		
		if (null == entity.getId()) {
			// 角色判断处理

			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(userInfo.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_ge_30_lt_39 = false;
			for (PeRoleUser peRoleUser: peRoleUserList) {
				if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 39L) {
					role_id_ge_30_lt_39 = true;
					break;
				}
			}
			
			if (role_id_ge_30_lt_39){ // 分公司
				entity.setSubcomp_id(userInfo.getDept_id());
				entity.setSubcomp_name(userInfo.getDepartment());
			}
			entity.setReport_id(userInfo.getId());
			entity.setReport_name(userInfo.getReal_name());
			entity.setReport_date(new Date());
			getFacade().getKonkaMobileAnnouncementService().createKonkaMobileAnnouncement(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileAnnouncementService().modifyKonkaMobileAnnouncement(entity);
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaMobileAnnouncementService().getKonkaMobileAnnouncement(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
			entity.setId(new Long(id));
			getFacade().getKonkaMobileAnnouncementService().removeKonkaMobileAnnouncement(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaMobileAnnouncementService().removeKonkaMobileAnnouncement(entity);
			
		}
		saveMessage(request, "entity.deleted");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaMobileAnnouncementService().getKonkaMobileAnnouncement(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");

	}
}