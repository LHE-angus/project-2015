package com.ebiz.mmt.web.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.MobileUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcRebatesAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		MobileUser mu = (MobileUser) session.getAttribute("mobile_user");

		if (null == mu) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String rebates_status = (String) dynaBean.get("rebates_status");

		PshowOrdeDetails entity = new PshowOrdeDetails();

		entity.getMap().put("user_id", mu.getUser_id());
		entity.getMap().put("state_in", new Integer[] { 60 });
		entity.getMap().put("order_from", 1);
		request.setAttribute("rebates", super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsSumRebates(
		        entity));

		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
			dynaBean.set("rebates_status", rebates_status);
		}

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForRebatesCount(entity);

		// 分页
		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		int pageCount = (int) Math.ceil((recordCount + pageSize - 1) / pageSize);

		dynaBean.set("recordCount", recordCount);

		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);
			dynaBean.set("pageCount", pageCount);
			if (currentPage > pageCount) {
				super.renderJavaScript(response, "window.onload=function(){alert('当前页已是最后一页！');history.back();}");
				return null;
			}
			List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeDetailsForRebatesPaginatedList(entity);
			for (PshowOrdeDetails pshowOrdeDetails : entityList) {
				KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
				konkaBcompPd.setId(pshowOrdeDetails.getPd_id());
				konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
				pshowOrdeDetails.getMap().put("pd_sn", konkaBcompPd.getPd_sn());
			}
			request.setAttribute("entityList", entityList);
		}

		dynaBean.set("user_id", mu.getUser_id());
		dynaBean.set("userpass", mu.getUserpass());
		return new ActionForward("/mobile/EcRebates/list.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		EcUser ui = new EcUser();
		String username = "";
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getEcUserService().getEcUser(ui);
		} else {
			username = (String) request.getParameter("username");
			ui = checkUser2(username, userpass);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！" + username);
			return null;
		}
		String msg = "对不起，发生错误！";
		int i = 0;

		EcUser user = new EcUser();
		user.setId(Long.valueOf(user_id));
		user = super.getFacade().getEcUserService().getEcUser(user);

		String type = (String) dynaBean.get("type");
		if (id != null && !"".equals(id)) {
			if ("2".equals(type)) {
				i = super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetailsRebates(new Long(id), type,
				        user);
			} else if ("3".equals(type)) {
				i = super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetailsRebates(new Long(id), type,
				        user);
			}
		}
		if (i != 0) {
			msg = "操作成功！";
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(super.getCtxPath(request));
		pathBuffer.append("/Skip.do?username=" + username);
		pathBuffer.append("&user_id=" + user_id);
		pathBuffer.append("&userpass=" + userpass);
		pathBuffer.append("&mod_id=" + 10004);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		logger.info("url-------------->" + pathBuffer.toString());
		return forward;
	}
}
