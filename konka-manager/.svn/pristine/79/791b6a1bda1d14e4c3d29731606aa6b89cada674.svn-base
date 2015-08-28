package com.ebiz.mmt.web.struts.manager.admin;

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

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Cheng,Bing
 * @version：2011-09-24
 */
public class CustomerUsersAction extends BaseAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward listSelectZmd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String name_like = (String) dynaBean.get("name_like");
		String cust_id = (String) dynaBean.get("cust_id");
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (StringUtils.isBlank(zmd_id)) {
			return null;
		}

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		request.setAttribute("konkaXxZmd", zmd);

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(Long.valueOf(cust_id));
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
		request.setAttribute("konkaR3Shop", s);

		if (null != s && GenericValidator.isLong(s.getCustomer_type())) {
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(10);
			kc.setC_index(Long.valueOf(s.getCustomer_type()));
			kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

			request.setAttribute("konkaCategory", kc);
		}

		PeProdUser entity = new PeProdUser();
		super.copyProperties(entity, form);
		entity.getMap().put("name_like", name_like);
		entity.setCust_id(Long.valueOf(cust_id));

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);

		for (PeProdUser cur : entityList) {
			KonkaXxZmd _zmd = new KonkaXxZmd();
			_zmd.getMap().put("user_id_eq", cur.getId());
			_zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(_zmd);

			if (_zmd != null) {
				logger.info("*************************************{}", _zmd.getZmd_id());
			}
			cur.getMap().put("konkaXxZmd", _zmd);
		}

		request.setAttribute("entityList", entityList);

		return new ActionForward("/admin/CustomerUsers/list_select_zmd.jsp");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String name_like = (String) dynaBean.get("name_like");
		String cust_id = (String) dynaBean.get("cust_id");
		String is_del = (String) dynaBean.get("is_del");

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(Long.valueOf(cust_id));
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
		request.setAttribute("konkaR3Shop", s);

		if (null != s && GenericValidator.isLong(s.getCustomer_type())) {
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(10);
			kc.setC_index(Long.valueOf(s.getCustomer_type()));
			kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

			request.setAttribute("konkaCategory", kc);
		}

		PeProdUser entity = new PeProdUser();
		super.copyProperties(entity, form);
		entity.getMap().put("name_like", name_like);
		entity.setCust_id(Long.valueOf(cust_id));

		if (StringUtils.isBlank(is_del)) {
			entity.setIs_del(0);
		}

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward saveZmdUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");
		String user_id = (String) dynaBean.get("user_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(cust_id) || !GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(user_id)) {
			return null;
		}

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(Long.valueOf(cust_id));
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
		if (null == s) {
			return null;
		}

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		if (null == zmd) {
			return null;
		}

		PeProdUser user = new PeProdUser();
		user.setId(Long.valueOf(user_id));
		user = super.getFacade().getPeProdUserService().getPeProdUser(user);
		if (null == user) {
			return null;
		}

		user.setCust_id(Long.valueOf(cust_id));
		user.setZmd_id(Long.valueOf(zmd_id));
		super.getFacade().getPeProdUserService().createZmdUserRelation(user);
		// PeProdUser PeProdUser = (PeProdUser)
		// super.getSessionAttribute(request, Constants.USER_INFO);
		super.saveMessage(request, "konka.xx.zmd.message.user.save.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/zmd/KonkaXxZmdAuditUserInfo.do?method=list");
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("cust_id=" + cust_id);
		pathBuffer.append("&").append("zmd_id=" + zmd_id);
		return new ActionForward(pathBuffer.toString(), true);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(Long.valueOf(cust_id));
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

		request.setAttribute("konkaR3Shop", s);

		// PeProdUser PeProdUser = (PeProdUser)
		// super.getSessionAttribute(request, Constants.USER_INFO);

		return mapping.findForward("input");
	}

	public ActionForward confirmZmdUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(cust_id) || !GenericValidator.isLong(zmd_id) || !GenericValidator.isLong(user_id)) {
			return null;
		}

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(Long.valueOf(cust_id));
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
		request.setAttribute("konkaR3Shop", s);

		KonkaXxZmd zmd = new KonkaXxZmd();
		zmd.setZmd_id(Long.valueOf(zmd_id));
		zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);
		request.setAttribute("konkaXxZmd", zmd);

		PeProdUser user = new PeProdUser();
		user.setId(Long.valueOf(user_id));
		user = super.getFacade().getPeProdUserService().getPeProdUser(user);
		request.setAttribute("peProdUser", user);

		return new ActionForward("/admin/CustomerUsers/zmd_confirm.jsp");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		// PeProdUser PeProdUser = (PeProdUser)
		// super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取登录用户 企业信息
		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		if (null == entity) {
			super.saveError(request, "errors.param");
			return this.list(mapping, form, request, response);
		}
		super.copyProperties(form, entity);

		// entity.setQueryString(super.serialize(request, "id", "mod_id",
		// "tree_param"));

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
					// request.setAttribute("province", p_index);
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "000000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "0000");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6) + "00");
					}
					// request.setAttribute("province",
					// StringUtils.substring(p_index, 0, 2) + "0000");
					// request.setAttribute("city", p_index);
				}
			}
		}

		dynaBean.set("zmd_id", zmd_id);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");

		if (!GenericValidator.isLong(user_id)) {
			return this.list(mapping, form, request, response);
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity = super.getFacade().getPeProdUserService().getPeProdUser(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		if (null != entity.getP_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getP_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
			        baseProvinceListFour);

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String pass_word_old = (String) dynaBean.get("pass_word_old");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String request_from = (String) dynaBean.get("request_from");
		String zmd_id = (String) dynaBean.get("zmd_id");
		// String public_pwd_old = (String) dynaBean.get("public_pwd_old");

		// 获取登录用户 企业信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		super.copyProperties(entity, form);

		entity.setProd_entp_id(peProdUser.getProd_entp_id());
		entity.setAdd_e_user_id(peProdUser.getId());

		// if (!GenericValidator.isLong(dept_id)) {
		// super.invalidOper(request, response, "errors.parm");
		// return null;
		// }

		// 人员居住地设定
		if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
			entity.setP_index(Long.valueOf(province));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
			entity.setP_index(Long.valueOf(city));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
		        && !GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(country));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
		        && GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(town));
		}
		// if (GenericValidator.isLong(role_id)) {// 职务角色
		// entity.getMap().put("role_id", Long.valueOf(role_id));
		// }

		if (!GenericValidator.isLong(user_id)) {// 创建用户
			PeProdUser prodUser = new PeProdUser();
			prodUser.setUser_name(entity.getUser_name());
			prodUser.setIs_del(0);
			Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(prodUser);
			if (count != 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('用户名\"" + entity.getUser_name()
				        + "\"与数据库中重复，请重新填写 ');history.back();}");
				return null;
			}
			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(entity.getPass_word()));
			Long id = super.getFacade().getPeProdUserService().createPeProdUser(entity);
			super.saveMessage(request, "entity.inserted");

			if ("zmd".equalsIgnoreCase(request_from)) {
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append("/admin/CustomerUsers.do?method=listSelectZmd");
				pathBuffer.append("&").append("mod_id=" + mod_id);
				pathBuffer.append("&").append("cust_id=" + cust_id);
				pathBuffer.append("&").append("zmd_id=" + zmd_id);
				pathBuffer.append("&").append("select_id=" + id);
				return new ActionForward(pathBuffer.toString(), true);
			}
		} else {
			entity.setId(Long.valueOf(user_id));

			if (!StringUtils.equals(pass_word_old, entity.getPass_word())) {
				DESPlus des = new DESPlus();
				entity.setPass_word(des.encrypt(entity.getPass_word()));
			}

			super.getFacade().getPeProdUserService().modifyPeProdUser(entity);
			// super.getFacade().getPeProdUserService().modifyPeProdUserWithMultiRoleUser(entity);
			super.saveMessage(request, "entity.updated");

			if ("zmd".equalsIgnoreCase(request_from)) {
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append("/zmd/KonkaXxZmdAuditUserInfo.do?method=list");
				pathBuffer.append("&").append("mod_id=" + mod_id);
				pathBuffer.append("&").append("cust_id=" + cust_id);
				pathBuffer.append("&").append("zmd_id=" + zmd_id);
				return new ActionForward(pathBuffer.toString(), true);
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("cust_id=" + cust_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(0);
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("cust_id=" + cust_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));
		entity.setIs_del(1);
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "konka.close.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("cust_id=" + cust_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward initPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(user_id)) {
			saveError(request, "errors.long", new String[] { user_id });
			return mapping.findForward("list");
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(Long.valueOf(user_id));

		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt("888888")); // 默认密码

		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "password.init.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("cust_id=" + cust_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "user_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateJobId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String job_id = (String) dynaBean.get("job_id");
		PeProdUser entity = new PeProdUser();
		entity.setJob_id(job_id);
		// entity.setIs_del(0);
		//Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (entityList.size() == 0) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (entityList.size() == 0) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward listUserForShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String position_id = (String) dynaBean.get("position_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String role_id = (String) dynaBean.get("role_id");

		// 获取登录用户 企业信息
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.setProd_entp_id(PeProdUser.getProd_entp_id());
		entity.setIs_del(0);

		if (GenericValidator.isLong(position_id)) {
			entity.setPosition_id(Long.valueOf(position_id));
		}
		if (GenericValidator.isLong(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (GenericValidator.isLong(role_id)) {
			entity.getMap().put("role_id", role_id);
		}
		// 没有分页，取不多于500条记录
		entity.getRow().setFirst(0);
		entity.getRow().setCount(500);

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService()
		        .getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/PeProdUser/list_user_for_show.jsp");
	}

	public ActionForward userLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean lazyForm = (DynaBean) form;
		String user_id = (String) lazyForm.get("user_id");

		String msg = null;
		if (StringUtils.isBlank(user_id)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(new Long(user_id));
		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {
			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		PeProdUser userInfo = getFacade().getPeProdUserService().getPeProdUser(entity);
		if (null != userInfo) {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.CUSTOMER_USER_INFO, userInfo);
		}
		super.renderJavaScript(response, "window.onload=function(){window.open('" + super.getCtxPath(request)
		        + "/customer/manager/Frames.do?method=index', '_blank');}");
		return null;
	}
}
