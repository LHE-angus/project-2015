package com.ebiz.mmt.web.struts.manager.admin;

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

import com.ebiz.mmt.domain.PePosition;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-09-26
 */
public class PePositionAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		// 查询条件
		String position_name_like = (String) dynaBean.get("position_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String is_leader = (String) dynaBean.get("is_leader");
		Pager pager = (Pager) dynaBean.get("pager");
		
		// 职位
		PePosition entity = new PePosition();

		if (StringUtils.isNotBlank(is_del) && GenericValidator.isLong(is_del)) {
			entity.setIs_del(Integer.parseInt(is_del));
		}
		
		if (StringUtils.isNotBlank(is_leader) && GenericValidator.isLong(is_leader)) {
			entity.setIs_leader(Integer.parseInt(is_leader));
		}	
		
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		if (null == PeProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else {
			if (PeProdUser.getUser_type() == 1) {
				entity.setAdd_e_user_id(PeProdUser.getId());
			} else if (PeProdUser.getUser_type() == 0) {
				entity.getMap().put("par_user_id", PeProdUser.getId());
			}
		}
		if (StringUtils.isNotBlank(position_name_like)) {
			entity.getMap().put("position_name_like", position_name_like);
		}

		Long recordCount = super.getFacade().getPePositionService().getPePositionWithNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PePosition> pePositionList = super.getFacade().getPePositionService().getPePositionWithNamePaginatedList(
				entity);

		request.setAttribute("entityList", pePositionList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String position_id = (String) dynaBean.get("position_id");

		if (!GenericValidator.isLong(position_id)) {
			saveError(request, "errors.long", new String[] { position_id });
			return mapping.findForward("list");
		}

		PePosition entity = new PePosition();
		entity.setPosition_id(Long.valueOf(position_id));
		entity = super.getFacade().getPePositionService().getPePosition(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { position_id });
			return mapping.findForward("list");
		}

		entity.setQueryString(super.serialize(request, "position_id", "mod_id"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String position_id = (String) dynaBean.get("position_id");

		if (!GenericValidator.isLong(position_id)) {
			saveError(request, "errors.long", new String[] { position_id });
			return mapping.findForward("list");
		}

		PePosition entity = new PePosition();
		entity.setPosition_id(Long.valueOf(position_id));
		entity = super.getFacade().getPePositionService().getPePosition(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { position_id });
			return mapping.findForward("list");
		}
		request.setAttribute("entity", entity);
		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String position_id = (String) dynaBean.get("position_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");
		
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeProdUser SxPeProdUser = new PeProdUser();
		SxPeProdUser.setIs_del(0);
		SxPeProdUser.setUser_name(PeProdUser.getUser_name());
		SxPeProdUser = super.getFacade().getPeProdUserService().getPeProdUser(SxPeProdUser);
	
		// 是否存在相同的职位名称
		PePosition pePosition = new PePosition();
		String name = (String) dynaBean.get("position_name");
		pePosition.setEntp_id(SxPeProdUser.getProd_entp_id());
		pePosition.setPosition_name(name);
		if (StringUtils.isNotBlank(position_id)) {
			pePosition.getMap().put("position_id_not_eq", position_id);
		}
		pePosition.getRow().setCount(2);
		pePosition.setIs_del(0);
		List<PePosition> pePositionList = super.getFacade().getPePositionService().getPePositionList(pePosition);
		if (null != pePositionList && pePositionList.size() > 0) {
			String msg = super.getMessage(request, "prod.position.name.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 保存职位信息
		PePosition entity = new PePosition();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setAdd_e_user_id(SxPeProdUser.getId());
		entity.setEntp_id(SxPeProdUser.getProd_entp_id());
		if (StringUtils.isBlank(position_id)) {
			super.getFacade().getPePositionService().createPePosition(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setPosition_id(Long.valueOf(position_id));
			super.getFacade().getPePositionService().modifyPePosition(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			//response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String position_id = (String) dynaBean.get("position_id");

		if (!StringUtils.isBlank(position_id) && GenericValidator.isLong(position_id)) {
			// 判断该职位是否存在用户
			PeProdUser ppu = new PeProdUser();
			ppu.setPosition_id(Long.valueOf(position_id));
			ppu.setIs_del(0);
			ppu.getRow().setCount(2);
			Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(ppu);
			if (count > 0) {
				String msg = super.getMessage(request, "prod.position.name.is.have");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			PePosition entity = new PePosition();
			entity.setPosition_id(new Long(position_id));
			entity.setIs_del(1);
			getFacade().getPePositionService().modifyPePosition(entity);
		}

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "position_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
