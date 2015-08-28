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
import com.ebiz.mmt.domain.MobileUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author tudp
 * @version 2013-11-23
 */
public class SkipAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String user_id = (String) request.getParameter("user_id");
		String username = (String) request.getParameter("username");
		String userpass = (String) request.getParameter("userpass");
		String mod_id = (String) request.getParameter("mod_id");// 手机端根据mod_id跳转不同的action 
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
		session.setAttribute(Constants.EPP_USER, ui);
		MobileUser mu = new MobileUser();
		mu.setUser_id(ui.getId().toString());
		mu.setUsername(ui.getUser_name());
		mu.setUserpass(userpass);
		mu.setMod_id(mod_id);
		session.setAttribute("mobile_user", mu); 
		if(ui.getUser_type().intValue()==1){
			if(new DESPlus().encrypt(userpass).equals(ui.getPass_word())){
				session.setAttribute("touch", null);
			}else{
				session.setAttribute("touch", 1);
			}
		}else{
			session.setAttribute("touch", null);
		}
		String ctx = super.getCtxPath(request);
		String url = "";
		if (mod_id.equals("10001")) {// 正在进行中订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=1";
		} else if (mod_id.equals("10005")) {// 已完成订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=2";
		} else if (mod_id.equals("10006")) {// 已取消订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=3";
		} else if (mod_id.equals("10002")) {// 积分页面
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", user_id);
			request.setAttribute("username", username);
			request.setAttribute("userpass", userpass);
			return new ActionForward("/mobile/Skip/form2.jsp");
		} else if (mod_id.equals("10003")) {// 商品佣金查询页面
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", user_id);
			request.setAttribute("username", username);
			request.setAttribute("userpass", userpass);
			return new ActionForward("/mobile/Skip/form2.jsp");
		} else if (mod_id.equals("10004")) {// 我的佣金查询页面
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", user_id);
			request.setAttribute("username", username);
			request.setAttribute("userpass", userpass);
			return new ActionForward("/mobile/Skip/form2.jsp");
		} else if (mod_id.equals("20001")) {// 产品详情
			String goods_id = (String) request.getParameter("goods_id");// 手机端根据mod_id跳转不同的action
			url = ctx + "/wap/PdShow.do?goods_id=" + goods_id;
		} else if (mod_id.equals("20002")) {// 产品详情
			url = ctx + "/wap/ShoppingCar.do";
		} else if (mod_id.equals("20003")) {// 会员中心
			url = ctx + "/wap/center/";
		}else if (mod_id.equals("20004")) {//用户注册
			url = ctx + "/wap/center/RegUser.do";
		}else{
			url = ctx + "/wap/center/";
		}
		response.sendRedirect(url);
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		DynaBean lazyForm = (DynaBean) form;

		String user_name = (String) lazyForm.get("user_name");
		String password = (String) lazyForm.get("password");
		String mod_id = (String) lazyForm.get("mod_id");

		String msg = null;
		if (StringUtils.isBlank(user_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		if (StringUtils.isBlank(password)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");

			return null;
		}

		DESPlus des = new DESPlus();
		EcUser ec = new EcUser();
		ec.setUser_name(user_name);
		ec.setPass_word(des.encrypt(password));
		List<EcUser> ecList = super.getFacade().getEcUserService().getEcUserList(ec);
		if (null == ecList || ecList.size() == 0) {
			ec = new EcUser();
			ec.setUser_name(user_name);
			ec.setPay_pwd(des.encrypt(password));
			ecList = super.getFacade().getEcUserService().getEcUserList(ec);
		}
		if (null == ecList || ecList.size() == 0) {			
			msg = "用户名或者密码错误";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		if (ecList.size() > 1) {
			msg = "用户名重复";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		EcUser entity = ecList.get(0);
		MobileUser mu = new MobileUser();
		mu.setUser_id(entity.getId().toString());
		mu.setUsername(user_name);
		mu.setUserpass(password);
		mu.setMod_id(mod_id);
		session.setAttribute("mobile_user", mu);
		session.setAttribute(Constants.EPP_USER, entity);
		if(entity.getUser_type().intValue()==1){
			if(new DESPlus().encrypt(password).equals(entity.getPass_word())){
				session.setAttribute("touch", null);
			}else{
				session.setAttribute("touch", 1);
			}
		}else{
			session.setAttribute("touch", null);
		}
		String ctx = super.getCtxPath(request);
		String url = "";
		if (mod_id.equals("10001")) {// 正在进行中订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=1";
		} else if (mod_id.equals("10005")) {// 已完成订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=2";
		} else if (mod_id.equals("10006")) {// 已取消订单
			url = ctx + "/wap/center/Orders.do?method=list&orderState=3";
		} else if (mod_id.equals("10002")) {// 积分页面
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", mu.getUser_id());
			request.setAttribute("username", mu.getUsername());
			request.setAttribute("userpass", mu.getUserpass());
			return new ActionForward("/mobile/Skip/form2.jsp");
			// url = ctx + "/EcUserScoreRec.do";
		} else if (mod_id.equals("10003")) {// 商品佣金查询页面
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", mu.getUser_id());
			request.setAttribute("username", mu.getUsername());
			request.setAttribute("userpass", mu.getUserpass());
			return new ActionForward("/mobile/Skip/form2.jsp");
			// url = ctx + "/EcBcompPdRebates.do";
		} else if (mod_id.equals("10004")) {// 我的佣金查询页面
			request.setAttribute("mod_id", mod_id);
			request.setAttribute("user_id", mu.getUser_id());
			request.setAttribute("username", mu.getUsername());
			request.setAttribute("userpass", mu.getUserpass());
			return new ActionForward("/mobile/Skip/form2.jsp");
			// url = ctx + "/EcRebates.do";
		} else if (mod_id.equals("20001")) {// 产品详情
			String goods_id =  request.getParameter("goods_id");// 手机端根据mod_id跳转不同的action
			if(StringUtils.isBlank(goods_id)){
				url = ctx + "/wap/center/";
			}else{
				url = ctx + "/wap/PdShow.do?goods_id=" + goods_id;
			}
		} else if (mod_id.equals("20002")) {// 产品详情
			url = ctx + "/wap/ShoppingCar.do";
		}else if (mod_id.equals("20003")) {// 会员中心
			url = ctx + "/wap/center/";
		}else if (mod_id.equals("20004")) {//用户注册
			url = ctx + "/wap/center/RegUser.do";
		}else{
			url = ctx + "/wap/center/";
		}
		response.sendRedirect(url);
		return null;
	}

}
