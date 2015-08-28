package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxNotice;
import com.ebiz.mmt.domain.KonkaXxNoticeContent;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-03-19
 */
public class KonkaXxNoticeAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		// 查询条件
		String notice_title_like = (String) dynaBean.get("notice_title_like");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String public_date_ge = (String) dynaBean.get("public_date_ge");
		String public_date_le = (String) dynaBean.get("public_date_le");
		String is_public = (String) dynaBean.get("is_public");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;//专卖店人员
		Boolean role_id_eq_400 = false;//专卖店店长
	

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if(temp.getRole_id() == 400){
					role_id_eq_400 = true;
				}
			}
		}
		
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxNotice entity = new KonkaXxNotice();

		if (role_id_eq_400) {
			String msg = getMessage(request, "errors.permission");
			saveDirectlyError(request, msg);
			return mapping.findForward("list");
		} else { // 分公司人员或总部
			entity.setAdd_user_id(ui.getId());
		}

		if (StringUtils.isNotBlank(notice_title_like)) {
			entity.getMap().put("notice_title_like", notice_title_like);
		}
		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (StringUtils.isNotBlank(public_date_ge)) {
			entity.getMap().put("public_date_ge", public_date_ge);
		}
		if (StringUtils.isNotBlank(public_date_le)) {
			entity.getMap().put("public_date_le", public_date_le);
		}
		if (GenericValidator.isInt(is_public)) {
			entity.setIs_public(Integer.parseInt(is_public));
		}

		entity.setIs_del(0);

		entity.getMap().put("add_date_desc", true);

		Long recordCount = getFacade().getKonkaXxNoticeService().getKonkaXxNoticeAndContentCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxNotice> entityList = getFacade().getKonkaXxNoticeService().getKonkaXxNoticeAndContentPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;//专卖店人员

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}
		
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		return mapping.findForward("input");
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
		String id = (String) dynaBean.get("id");
		String content = (String) dynaBean.get("content");

		KonkaXxNotice entity = new KonkaXxNotice();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(content)) {
			entity.getMap().put("content", content);
		} else {
			String msg = getMessage(request, "konka.xx.notice.content.null");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isBlank(id)) {
			entity.setAdd_user_id(super.getSessionUserInfo(request).getId());

			getFacade().getKonkaXxNoticeService().createKonkaXxNoticeAndContent(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaXxNoticeService().modifyKonkaXxNoticeAndContent(entity);
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

		if (GenericValidator.isLong(id)) {
			KonkaXxNotice entity = new KonkaXxNotice();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getKonkaXxNoticeService().getKonkaXxNotice(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			KonkaXxNoticeContent content = new KonkaXxNoticeContent();
			content.setNotice_id(Long.valueOf(id));

			List<KonkaXxNoticeContent> contents = super.getFacade().getKonkaXxNoticeContentService()
					.getKonkaXxNoticeContentList(content);

			if (null != contents && contents.size() > 0) {
				dynaBean.set("content", contents.get(0).getNotice_content());
			}

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaXxNotice entity = new KonkaXxNotice();
			entity.setId(Long.valueOf(id));
			entity.getRow().setFirst(0);
			entity.getRow().setCount(2);
			List<KonkaXxNotice> entityList = getFacade().getKonkaXxNoticeService()
					.getKonkaXxNoticeAndContentPaginatedList(entity);
			if (null == entityList || entityList.size() == 0) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entityList.get(0));

			KonkaXxNoticeContent content = new KonkaXxNoticeContent();
			content.setNotice_id(Long.valueOf(id));

			List<KonkaXxNoticeContent> contents = super.getFacade().getKonkaXxNoticeContentService()
					.getKonkaXxNoticeContentList(content);

			if (null != contents && contents.size() > 0) {
				dynaBean.set("content", contents.get(0).getNotice_content());
			}

			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward publish(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaXxNotice entity = new KonkaXxNotice();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaXxNoticeService().getKonkaXxNotice(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		KonkaXxNoticeContent content = new KonkaXxNoticeContent();
		content.setNotice_id(entity.getId());
		List<KonkaXxNoticeContent> contentList = super.getFacade().getKonkaXxNoticeContentService()
				.getKonkaXxNoticeContentList(content);

		if (null == contentList) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		entity.getMap().put("content", contentList.get(0).getId().toString());
		entity.setIs_public(1);
		entity.setPublic_date(new Date());
		entity.setPublic_user_id(super.getSessionUserInfo(request).getId());
		getFacade().getKonkaXxNoticeService().publishKonkaXxNotice(entity);

		saveMessage(request, "entity.fabu.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaXxNotice entity = new KonkaXxNotice();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaXxNoticeService().modifyKonkaXxNotice(entity);
		}
		saveMessage(request, "entity.deleted");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
