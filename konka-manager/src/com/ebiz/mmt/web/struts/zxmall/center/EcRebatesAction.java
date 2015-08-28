package com.ebiz.mmt.web.struts.zxmall.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

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

		Pager pager = (Pager) dynaBean.get("pager");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String rebates_status = (String) dynaBean.get("rebates_status");

		PshowOrdeDetails entity = new PshowOrdeDetails();

		entity.getMap().put("user_id", ecUser.getId());
		entity.getMap().put("state_in", new Integer[] { 60 });
		entity.getMap().put("order_from", ecUser.getUser_type());
		request.setAttribute("rebates", super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsSumRebates(
		        entity));

		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		entity.getMap().put("rebates", "1");
		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForRebatesCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForRebatesPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 判断该用户的个人信息 银行卡信息是否填写完整，如果不完整，需要弹出对话框填写
		if (null == ecUser.getBank_account() || null == ecUser.getBank_account_name() || null == ecUser.getBank_name()) {
			dynaBean.set("is_ws", "0");
			dynaBean.set("bank_account", ecUser.getBank_account());
			dynaBean.set("bank_account_name", ecUser.getBank_account_name());
			dynaBean.set("bank_name", ecUser.getBank_name());
		} else {
			dynaBean.set("is_ws", "1");
		}
		dynaBean.set("user_id", ecUser.getId());

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String msg = "对不起，发生错误！";
		int i = 0;

		HttpSession session = request.getSession();
		EcUser user = (EcUser) session.getAttribute("zxmall");
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
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
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href=\""
		        + super.getCtxPath(request) + "/zxmall/center/EcRebates.do\";}");
		return null;
	}

	public ActionForward ajaxSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		int i = 0;
		HttpSession session = request.getSession();
		EcUser user = (EcUser) session.getAttribute("zxmall");
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String bank_name = (String) dynaBean.get("bank_name");
		String bank_account_name = (String) dynaBean.get("bank_account_name");
		String bank_account = (String) dynaBean.get("bank_account");

		// 账户中心用户登录验证
		if (user.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/zxmall/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}

		EcUser ee = new EcUser();
		ee.setId(Long.valueOf(user_id));
		ee.setBank_account(bank_account);
		ee.setBank_account_name(bank_account_name);
		ee.setBank_name(bank_name);
		i = super.getFacade().getEcUserService().modifyEcUser(ee);

		if (i != 0) {
			super.renderText(response, "1");
		} else {
			super.renderText(response, "0");
		}
		return null;
	}
}
