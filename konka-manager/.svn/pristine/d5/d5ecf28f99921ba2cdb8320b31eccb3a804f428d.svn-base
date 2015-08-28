package com.ebiz.mmt.web.struts.zxmall;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseCardApply;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-12
 */
public class RegisterNewAction extends BaseAction {
	private static final String DEFAULT_PASSWORD = "~~@^_^@~~";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");
		String pass_word = (String) dynaBean.get("pass_word");
		String real_name = (String) dynaBean.get("real_name");
		String user_name = (String) dynaBean.get("user_name");
		String link_phone = (String) dynaBean.get("link_phone");
		String email = (String) dynaBean.get("email");
		String pay_pwd = (String) dynaBean.get("pay_pwd");
		String link_code = (String) dynaBean.get("link_code");

		if (StringUtils.isBlank(pay_pwd) || StringUtils.isBlank(pass_word) || StringUtils.isBlank(user_name)
		        || StringUtils.isBlank(real_name) || StringUtils.isBlank(link_phone) || StringUtils.isBlank(email)) {
			String msg = "数据填写不完整!";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		if (StringUtils.trim(pass_word).endsWith(StringUtils.trim(pay_pwd))) {
			String msg = "登录密码和支付密码不能相同!";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		
		EcUser linkUser = new EcUser();
		linkUser.setLink_code(link_code);
		linkUser.getMap().put("is_par_user", "1");
		linkUser =super.getFacade().getEcUserService().getEcUser(linkUser);
		if(linkUser==null){
			String msg = "关联码不存在!";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		EcUser ee = new EcUser();
		ee.setUser_name(user_name);
		Long cc = super.getFacade().getEcUserService().getEcUserCount(ee);
		if (cc > 0) {
			String msg = "该用户名已经被占用！!";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		DESPlus des = new DESPlus();

		EcUser entity = new EcUser();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setIs_del(0);
		entity.setIs_act(0);
		entity.setPosition_id(0L);
		entity.setLogin_count(0L);
		entity.setIs_allowed(1);
		entity.setIs_chapter(0);
		entity.setAudit_date(new Date());
		entity.setChargeman("注册会员，自动审核");
		entity.setAdd_user_id(0L);
		entity.setPass_word(des.encrypt(pass_word));
		entity.setPay_pwd(des.encrypt(pay_pwd));
		entity.setDept_id(0L);
		entity.setIs_epp_fgs(0);
		entity.setUser_type(2);
		entity.setPlat_sys(0);
		entity.setLast_login_time(new Date());
		entity.setOrder_value(0);
		entity.setIs_xx_user(0);
		
		
		EcBaseCardApply ec = new EcBaseCardApply();
		Long temp = super.getFacade().getEcBaseCardApplyService().getApplyCardNoCount(ec);
		entity.getMap().put("cust_name", "ZBHYCN" + temp);
		entity.getMap().put("cust_code", "ZBHYCD" + temp);
		//注册会员客户信息取上级
		entity.setLink_user_name(linkUser.getUser_name());
		entity.setCust_id(linkUser.getCust_id()); 
		entity.setDept_id(linkUser.getDept_id());
		entity.setIs_epp_fgs(linkUser.getIs_epp_fgs());
		entity.setPlat_sys(linkUser.getPlat_sys()); 
		
		super.getFacade().getEcUserService().createEcUserForZc(entity);
		String msg = "恭喜您注册成功！";
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
		        + super.getCtxPath(request) + "/zxmall/login.do';}");
		return null;

	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		EcUser entity = new EcUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		Long count = super.getFacade().getEcUserService().getEcUserCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}