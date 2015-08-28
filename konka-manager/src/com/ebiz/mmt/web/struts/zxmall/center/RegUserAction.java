package com.ebiz.mmt.web.struts.zxmall.center;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author tudp
 * @version 2013-12-30
 */
public class RegUserAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		// 账户中心用户登录验证
				if (ecUser==null) {
					String ctx = super.getCtxPath(request);
					String url = ctx + "/zxmall/";
					response.sendRedirect(url);
					return null;
				}
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		if (entity.getIs_act() != null && entity.getIs_act().intValue() == 1) {
			return mapping.findForward("list");
		} else {
			if (entity.getIs_act() != null && entity.getIs_act().intValue() == 0) {
				session.setAttribute("zxmall", entity);
			}
			request.setAttribute("is_act", entity.getIs_act());
			return mapping.findForward("view");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);

		if (entity.getIs_own() == null) {
			entity.setIs_own(new Integer(0));
		}
		if (entity.getIs_act() != null && entity.getIs_act().intValue() == 1) {
			entity.setPass_word(null);
			entity.setPay_pwd(null);
			// entity.setBank_name(null);
			// entity.setBank_account(null);
			// entity.setBank_account_name(null);
			// entity.setReal_name(null);

			// 地区回显
			if (entity.getP_index() != null && entity.getP_index() != -1) {
				String p_index = entity.getP_index().toString();
				if (StringUtils.isNotBlank(p_index)) {
					if (!p_index.endsWith("00")) {
						if (p_index.length() == 6) {
							request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
							request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
							request.setAttribute("country", p_index);
						} else if (p_index.length() == 8) {
							request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
							request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
							request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
							request.setAttribute("town", p_index);
						}
					} else if (p_index.endsWith("0000")) {
						if (p_index.length() == 6) {
							request.setAttribute("province", p_index);
						} else if (p_index.length() == 8) {
							request.setAttribute("province", p_index);
							request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						}
					} else if (p_index.endsWith("00")) {
						if (p_index.length() == 6) {
							request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
							request.setAttribute("city", p_index);
						} else if (p_index.length() == 8) {
							request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
							request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
							request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
						}
					}
				}
			}

			super.copyProperties(form, entity);

			if (null != entity && entity.getDept_id() != null) {

				EcGroup eg = new EcGroup();
				eg.setId(entity.getDept_id());
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					dynaBean.set("group_name", eg.getGroup_name());
				}

			}

			return mapping.findForward("input");
		} else {
			if (entity.getIs_act() != null && entity.getIs_act().intValue() == 0) {
				session.setAttribute("zxmall", entity);
			}
			request.setAttribute("is_act", entity.getIs_act());
			return mapping.findForward("view");
		}
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		if (entity.getIs_own() == null) {
			entity.setIs_own(new Integer(0));
		}
		if (entity.getIs_act() != null && entity.getIs_act().intValue() == 3) {
			entity.setBank_name(null);
			entity.setBank_account(null);
			entity.setBank_account_name(null);
			entity.setReal_name(null);
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		} else {
			if (entity.getIs_act() != null && entity.getIs_act().intValue() == 0) {
				session.setAttribute("zxmall", entity);
			}
			request.setAttribute("is_act", entity.getIs_act());
			return mapping.findForward("view");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		EcUser user = new EcUser();
		user.setId(ecUser.getId());
		user = super.getFacade().getEcUserService().getEcUser(user);
		if (user.getIs_act() != null && (user.getIs_act().intValue() == 0 || user.getIs_act().intValue() == 2)) {
			request.setAttribute("is_act", user.getIs_act());
			return mapping.findForward("view");
		}

		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		EcUser entity = new EcUser();
		super.copyProperties(entity, form);
		String msg = "";
		if (entity.getId().intValue() != ecUser.getId().intValue()) {
			msg = "用户已变更请重新登录";
		}
		if (entity.getReal_name() == null) {
			msg = "真实姓名错误";
		}
		/*
		 * else if (!entity.getReal_name().trim().equals(ecUser.getReal_name().trim())) { msg = "您的输入的姓名与会员卡号不匹配"; }
		 */
		if (entity.getUser_name() != null && !"".equals(entity.getUser_name())
				&& !user.getUser_name().equals(entity.getUser_name())) {
			EcUser t = new EcUser();
			t.setUser_name(entity.getUser_name());
			List<EcUser> ecUserList = super.getFacade().getEcUserService().getEcUserList(t);
			if (ecUserList != null && ecUserList.size() > 0) {
				msg = "该用户名已经存在";
			}
		}
		if (!"".equals(msg)) {
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "'); history.back();}");
			return null;
		}

		if (!StringUtils.isBlank(town) && GenericValidator.isLong(town)) {
			entity.setP_index(new Long(town));
		} else if (!StringUtils.isBlank(country) && GenericValidator.isLong(country)) {
			entity.setP_index(new Long(country));
		}

		entity.setId(ecUser.getId());
		entity.setIs_act(new Integer(2));
		entity.setAdd_date(new Date());
		DESPlus des = new DESPlus();
		if (entity.getPass_word() != null)
			entity.setPass_word(des.encrypt(entity.getPass_word()));
		if (entity.getPay_pwd() != null)
			entity.setPay_pwd(des.encrypt(entity.getPay_pwd()));
		super.getFacade().getEcUserService().modifyEcUser(entity);
		saveMessage(request, "entity.updated");

		super.renderJavaScript(response, "window.onload=function(){location.href=\"RegUser.do\";}");
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
}
