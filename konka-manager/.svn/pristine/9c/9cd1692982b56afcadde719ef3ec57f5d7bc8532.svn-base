package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-06-17
 */
public class JBasePartnerAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String partner_name_like = (String) dynaBean.get("partner_name_like"); // 合作伙伴名称
		String link_name_like = (String) dynaBean.get("link_name_like"); // 联系人姓名
		String is_del = (String) dynaBean.get("is_del");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		JBasePartner entity = new JBasePartner();
		super.copyProperties(entity, form);
		
		entity.setC_id(user.getCust_id());
		if(StringUtils.isNotBlank(partner_name_like)){
			entity.getMap().put("partner_name_like", partner_name_like);
		}
		if(StringUtils.isNotBlank(link_name_like)){
			entity.getMap().put("link_name_like", link_name_like);
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}

		entity.setPartner_type(0);  //0-供应商    1-客户

		Long recordCount = super.getFacade().getJBasePartnerService().getJBasePartnerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBasePartner> entityList = super.getFacade().getJBasePartnerService().getJBasePartnerPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward list_select(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String partner_name_like = (String) dynaBean.get("partner_name_like"); // 合作伙伴名称
		String partner_type = (String) dynaBean.get("partner_type"); // 合作伙伴名称
		String is_del = (String) dynaBean.get("is_del");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		JBasePartner entity = new JBasePartner();
		entity.setC_id(user.getCust_id());
		entity.getMap().put("partner_name_like", partner_name_like);
		entity.getMap().put("partner_type_value", "1");

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}
		if (GenericValidator.isLong(partner_type))
			entity.setPartner_type(Integer.valueOf(partner_type));

		Long recordCount = super.getFacade().getJBasePartnerService().getJBasePartnerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JBasePartner> entityList = super.getFacade().getJBasePartnerService().getJBasePartnerPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../customer/JBasePartner/list_select.jsp");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity = super.getFacade().getJBasePartnerService().getJBasePartner(entity);
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);

		String sell_post_p_index = entity.getConsignee_p_index();
		if (null != sell_post_p_index) {
			String province = StringUtils.substring(sell_post_p_index, 0, 2).concat("0000");
			String city = StringUtils.substring(sell_post_p_index, 0, 4).concat("00");
			String country = StringUtils.substring(sell_post_p_index, 0, 6);
			String town = "";
			if (StringUtils.length(sell_post_p_index) == 8) {
				town = sell_post_p_index;
			}
			dynaBean.set("province", province);
			dynaBean.set("city", city);
			dynaBean.set("country", country);
			dynaBean.set("town", town);
		}
		String _p_index = entity.getArea_code();
		if (null != _p_index) {
			String _province = StringUtils.substring(_p_index, 0, 2).concat("0000");
			String _city = StringUtils.substring(_p_index, 0, 4).concat("00");
			String _country = StringUtils.substring(_p_index, 0, 6);
			String _town = "";
			if (StringUtils.length(_p_index) == 8) {
				_town = _p_index;
			}
			dynaBean.set("_province", _province);
			dynaBean.set("_city", _city);
			dynaBean.set("_country", _country);
			dynaBean.set("_town", _town);
		}

		Long partner_c_id = entity.getPartner_c_id();
		if (null != partner_c_id) {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(partner_c_id);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (null != s) {
				// 保证客户实体信息存在
				PeProdUser user = new PeProdUser();
				user.setCust_id(partner_c_id);
				List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				if (null != userList && userList.size() > 0) {
					request.setAttribute("partner_user_info", userList.get(0));
				}
			}
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String user_name = (String) dynaBean.get("user_name");
		String pass_word = (String) dynaBean.get("pass_word");
		String type1 = (String) dynaBean.get("type1");// type1=1 表示客户+组织/单位

		//收货地址
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String p_index = null;
		if (StringUtils.isNotBlank(town)) {
			p_index = town;
		} else if (StringUtils.isNotBlank(country)) {
			p_index = country;
		} else if (StringUtils.isNotBlank(city)) {
			p_index = city;
		} else {
			p_index = province;
		}
		//供应商地址
		String _province = (String) dynaBean.get("_province");
		String _city = (String) dynaBean.get("_city");
		String _country = (String) dynaBean.get("_country");
		String _town = (String) dynaBean.get("_town");
		String _p_index = null;
		if (StringUtils.isNotBlank(_town)) {
			_p_index = _town;
		} else if (StringUtils.isNotBlank(_country)) {
			_p_index = _country;
		} else if (StringUtils.isNotBlank(_city)) {
			_p_index = _city;
		} else {
			_p_index = _province;
		}

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JBasePartner entity = new JBasePartner();
		if (StringUtils.isNotBlank(user_name)) {
			entity.getMap().put("user_name", user_name);
		}
		if (StringUtils.isNotBlank(pass_word)) {
			entity.getMap().put("pass_word", pass_word);
		} else {
			entity.getMap().put("pass_word", 0);
		}
		super.copyProperties(entity, form);
		entity.setConsignee_p_index(p_index);   //收货人地区编码
		entity.setArea_code(_p_index);   //供应商地址地区编码

		logger.info("obj : {}" + BeanUtils.describe(entity));

		if (GenericValidator.isLong(partner_id)) {
			JBasePartner jBasePartner = new JBasePartner();
			jBasePartner.setPartner_id(Long.valueOf(partner_id));
			jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
			if ((jBasePartner.getPartner_type() != null && jBasePartner.getPartner_type().toString().indexOf('1') > -1)
					&& jBasePartner.getPartner_obj() == 1) {
				saveMessage(request, "entity.updated");
				super.getFacade().getKonkaR3ShopService().modifyKonkaR3ShopWithJBasePartner(entity);
			} else {
				saveMessage(request, "entity.updated");
				if (entity.getPartner_c_id() == null) {
					entity.getMap().put("set_partner_c_id_null", true);
				}
				super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);
			}
		} else {
			if (type1.equals("0")) {
				saveMessage(request, "entity.inserted");
				entity.setC_id(user.getCust_id());
				Long id = super.getFacade().getJBasePartnerService().createJBasePartner(entity);
				partner_id = String.valueOf(id);
			} else {
				saveMessage(request, "entity.inserted");
				entity.setC_id(user.getCust_id());
				Long id = super.getFacade().getKonkaR3ShopService().createKonkaR3ShopWithJBasePartner(entity);
				partner_id = String.valueOf(id);
			}
		}

		// 画面迁移从哪里来哪里去
		if (StringUtils.isNotBlank(returnUrl)) {
			returnUrl += (returnUrl.indexOf("?") == -1) ? "?" : "&";
			returnUrl += "partner_id=" + partner_id;
			super.toReturnUrl(returnUrl, response);
			return null;
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String user_name = (String) dynaBean.get("user_name");
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		// entity.setIs_del(0);
		Long count = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		super.getFacade().getJBasePartnerService().removeJBasePartner(entity);

		saveMessage(request, "konka.close.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String is_del = (String) dynaBean.get("is_del");
		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity.setIs_del(0);
		super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);

		saveMessage(request, "konka.restart.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&is_del=" + is_del);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/*
	 * 代理商下的网点
	 */
	public ActionForward jxslist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String jxs_name_like = (String) dynaBean.get("jxs_name_like");

		KonkaBranchCategory kbc = new KonkaBranchCategory();
		kbc.setKonka_r3_id(user.getCust_id());
		kbc.setCategory_id(20L);
		if (StringUtils.isNotBlank(jxs_name_like)) {
			kbc.getMap().put("jxs_name_like", jxs_name_like);
		}

		Long recordCount = super.getFacade().getKonkaBranchCategoryService().getKonkaBranchCategoryCount(kbc);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		kbc.getRow().setFirst(pager.getFirstRow());
		kbc.getRow().setCount(pager.getRowCount());
		List<KonkaBranchCategory> entityList = super.getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryPaginatedList(kbc);
		for (KonkaBranchCategory konkaBranchCategory : entityList) {
			KonkaR3Shop ks = new KonkaR3Shop();
			ks.setId(konkaBranchCategory.getJxs_r3_id());
			ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
			konkaBranchCategory.getMap().put("name", ks.getCustomer_name());
		}
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../customer/JBasePartner/jxslist.jsp");
	}

	/*
	 * 代理商下的网点
	 */
	public ActionForward getEntpInfoByR3Id(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_id = (String) dynaBean.get("r3_id");
		if (!GenericValidator.isLong(r3_id)) {
			return null;
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(r3_id));
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);
		super.renderJson(response, JSON.toJSONString(entity));
		logger.info("----->{}", JSON.toJSONString(entity));
		return null;
	}

	public void ajaxGetJBasePartner(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");
		if (!GenericValidator.isLong(partner_id)) {
			return;
		}
		JBasePartner jBasePartner = new JBasePartner();
		jBasePartner.setPartner_id(Long.valueOf(partner_id));
		jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
		super.renderJson(response, JSON.toJSONString(jBasePartner));
		return;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String partner_id = (String) dynaBean.get("partner_id");

		if (!GenericValidator.isLong(partner_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		JBasePartner entity = new JBasePartner();
		entity.setPartner_id(Long.valueOf(partner_id));
		entity = super.getFacade().getJBasePartnerService().getJBasePartner(entity);
		entity.setQueryString(super.serialize(request, "partner_id", "method"));
		super.copyProperties(form, entity);

		Long partner_c_id = entity.getPartner_c_id();
		if (null != partner_c_id) {
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(partner_c_id);
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (null != s) {
				// 保证客户实体信息存在
				PeProdUser user = new PeProdUser();
				user.setCust_id(partner_c_id);
				List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
				if (null != userList && userList.size() > 0) {
					request.setAttribute("partner_user_info", userList.get(0));
				}
			}
		}

		return mapping.findForward("view");
	}
}
