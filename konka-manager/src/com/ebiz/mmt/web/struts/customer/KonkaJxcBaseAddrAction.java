package com.ebiz.mmt.web.struts.customer;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaJxcBaseAddr;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,jia
 * @version 2014-03-22
 */
public class KonkaJxcBaseAddrAction extends BaseClientJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String user_name_like = (String) dynaBean.get("user_name_like");
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
		super.copyProperties(entity, dynaBean);
		entity.setIs_del(0);

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if (null != r3shop) {
			entity.setR3_id(r3shop.getId());
			entity.setR3_code(r3shop.getR3_code());
		}

		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		Long recordCount = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddrCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaJxcBaseAddr> entityList = super.getFacade().getKonkaJxcBaseAddrService()
				.getKonkaJxcBaseAddrPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		super.saveToken(request);
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
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
		super.copyProperties(entity, dynaBean);
		if (null != user) {
			entity.setLink_id(user.getId());
			KonkaR3Shop r3shop = new KonkaR3Shop();
			r3shop.setId(user.getCust_id());
			r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
			if (null != r3shop) {
				entity.setR3_id(r3shop.getId());
				entity.setR3_code(r3shop.getR3_code());
				entity.setUser_shop_name(r3shop.getShop_name());
				entity.setDept_id(super.getDeptIdByAgentAgentKonkaR3Id(r3shop.getId()));
			}
		}

		if (StringUtils.isNotBlank(province) && !StringUtils.isNotBlank(city)) {
			entity.setUser_p_index(Long.valueOf(province));
		}
		if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && !StringUtils.isNotBlank(country)) {
			entity.setUser_p_index(Long.valueOf(city));
		}
		if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(country)
				&& !StringUtils.isNotBlank(town)) {
			entity.setUser_p_index(Long.valueOf(country));
		}
		if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(country)
				&& StringUtils.isNotBlank(town)) {
			entity.setUser_p_index(Long.valueOf(town));
		}

		if (StringUtils.isNotBlank(id)) {// 修改
			super.getFacade().getKonkaJxcBaseAddrService().modifyKonkaJxcBaseAddr(entity);
			super.saveMessage(request, "entity.updated");
		} else {// 添加
			entity.setAdd_date(new Date());
			super.getFacade().getKonkaJxcBaseAddrService().createKonkaJxcBaseAddr(entity);
			super.saveMessage(request, "entity.inserted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddr(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}
		if (entity.getUser_p_index() != null && entity.getUser_p_index() != -1) {// 回显地区信息
			String p_index = entity.getUser_p_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
						dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
						dynaBean.set("country", p_index);
					} else if (p_index.length() == 8) {
						dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
						dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
						dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
						dynaBean.set("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					dynaBean.set("province", p_index);
				} else if (p_index.endsWith("00")) {
					dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
					dynaBean.set("city", p_index);
				}
			}
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddr(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
			entity.setId(Long.valueOf(id));
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			super.getFacade().getKonkaJxcBaseAddrService().modifyKonkaJxcBaseAddr(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			super.getFacade().getKonkaJxcBaseAddrService().modifyKonkaJxcBaseAddr(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward update_default(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		// 修改全部都为1
		KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
		if (null != user) {
			KonkaR3Shop r3shop = new KonkaR3Shop();
			r3shop.setId(user.getCust_id());
			r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
			if (null != r3shop) {
				konkaJxcBaseAddr.setR3_id(r3shop.getId());
				konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
			}

		}
		konkaJxcBaseAddr.setIs_del(0);
		List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService()
				.getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
		if (null != konkaJxcBaseAddrList && konkaJxcBaseAddrList.size() > 0) {
			for (KonkaJxcBaseAddr kba : konkaJxcBaseAddrList) {
				kba.setIs_default(1);
				super.getFacade().getKonkaJxcBaseAddrService().modifyKonkaJxcBaseAddr(kba);
			}
		}

		// 修改点击过的那一个
		KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
		super.copyProperties(entity, dynaBean);
		entity.setId(Long.valueOf(id));
		entity.setIs_default(0);
		super.getFacade().getKonkaJxcBaseAddrService().modifyKonkaJxcBaseAddr(entity);
		super.saveMessage(request, "entity.inserted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
