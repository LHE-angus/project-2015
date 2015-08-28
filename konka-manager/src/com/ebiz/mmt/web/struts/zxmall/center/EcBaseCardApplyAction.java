package com.ebiz.mmt.web.struts.zxmall.center;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseCardApply;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcBaseCardApplyAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		// 账户中心用户登录验证
				if (ecUser==null) {
					String ctx = super.getCtxPath(request);
					String url = ctx + "/zxmall/";
					response.sendRedirect(url);
					return null;
				}
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 2) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/zxmall/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		if (entity.getIs_own() == null) {
			entity.setIs_own(new Integer(0));
		}
		super.copyProperties(form, entity);

		EcBaseCardApply ec = new EcBaseCardApply();
		ec.setApply_user_name(ecUser.getUser_name());
		List<EcBaseCardApply> entityList = super.getFacade().getEcBaseCardApplyService().getEcBaseCardApplyList(ec);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return unspecified(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return unspecified(mapping, form, request, response);
		}
		resetToken(request);
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 2) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/zxmall/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}

		EcBaseCardApply entity = new EcBaseCardApply();
		super.copyProperties(entity, form);
		entity.setApply_date(new Date());
		if (null != ecUser.getDept_id())
			entity.setApply_dept(ecUser.getDept_id().toString());
		entity.setApply_real_name(ecUser.getReal_name());
		entity.setApply_user_name(ecUser.getUser_name());
		entity.setInfo_state(0);

		super.getFacade().getEcBaseCardApplyService().createEcBaseCardApply(entity);
		saveMessage(request, "entity.updated");

		super.renderJavaScript(response, "window.onload=function(){location.href=\"EcBaseCardApply.do\";}");
		return null;
	}
}
