package com.ebiz.mmt.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.MobileUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author tudp
 * @version 2013-11-23
 */
public class Skip2Action extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String msg = "";
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		String user_id = (String) request.getParameter("user_id");
		String username = (String) request.getParameter("username");
		String userpass = (String) request.getParameter("userpass");
		String mod_id = (String) request.getParameter("mod_id");// 手机端根据mod_id跳转不同的action
		String pwd = (String) request.getParameter("pwd"); 

		EcUser ui = new EcUser();
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getEcUserService().getEcUser(ui);
			if(ui==null){
				 ui = new EcUser();
				 ui.setId(Long.valueOf(user_id));
				 ui.setPay_pwd(new DESPlus().encrypt(userpass));
				 ui = getFacade().getEcUserService().getEcUser(ui);
			}
		} else {
			ui = checkUser(username, userpass);
		}
		if (null == ui) {
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", user_id);
			request.setAttribute("username", username);
			request.setAttribute("userpass", userpass);
			return new ActionForward("/mobile/Skip/form.jsp");
		}

		if (StringUtils.isBlank(pwd)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");

		}
		if (ui.getUser_type().intValue() == 1) {
			DESPlus des = new DESPlus();
			pwd = des.encrypt(pwd);
			if (pwd != null && pwd.equals(ui.getPay_pwd())) {
				session.setAttribute("touch", "1");
			} else {
				msg = super.getMessage(request, "login.failed.password.invalid");
			}
		}
		if (!"".equals(msg)) {
			dynaBean.set("msg", msg);
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", user_id);
			request.setAttribute("username", username);
			request.setAttribute("userpass", userpass);
			return new ActionForward("/mobile/Skip/form2.jsp");
		}

		session.setAttribute(Constants.EPP_USER, ui);
		MobileUser mu = new MobileUser();
		mu.setUser_id(ui.getId().toString());
		mu.setUsername(ui.getUser_name());
		mu.setUserpass(userpass);
		mu.setMod_id(mod_id);
		session.setAttribute("mobile_user", mu); 

		String ctx = super.getCtxPath(request);
		String url = "";
		if (mod_id.equals("10001")) {// 正在进行中订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=1";
		} else if (mod_id.equals("10005")) {// 已完成订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=2";
		} else if (mod_id.equals("10006")) {// 已取消订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=3";
		} else if (mod_id.equals("10002")) {// 积分页面
			url = ctx + "/EcUserScoreRec.do";
		} else if (mod_id.equals("10003")) {// 商品佣金查询页面
			url = ctx + "/EcBcompPdRebates.do";
		} else if (mod_id.equals("10004")) {// 我的佣金查询页面
			url = ctx + "/EcRebates.do";
		} else if (mod_id.equals("20001")) {// 产品详情
			String goods_id = (String) request.getParameter("goods_id");// 手机端根据mod_id跳转不同的action
			url = ctx + "/wap/PdShow.do?goods_id=" + goods_id;
		} else if (mod_id.equals("20002")) {// 产品详情
			url = ctx + "/wap/ShoppingCar.do";
		}
		response.sendRedirect(url);
		return null;
	}

}
