package com.ebiz.mmt.web.struts.wap.center;

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

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.web.struts.wap.BaseMemberAction;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcUserAddrsAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		EcUserAddrs entity = new EcUserAddrs();// 取list
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setUser_id(ecUser.getId());
		List<EcUserAddrs> entityList = super.getFacade().getEcUserAddrsService().getEcUserAddrsList(entity);
		request.setAttribute("entityList", entityList);

		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {// 如果id不为空，取详细信息
			entity = new EcUserAddrs();
			entity.setOwn_sys(ecUser.getUser_type());
			entity.setUser_id(ecUser.getId());
			entity.setId(new Long(id));
			entity = super.getFacade().getEcUserAddrsService().getEcUserAddrs(entity);
			String province = "";
			String city = "";
			String country = "";
			String town = "";
			town = "" + entity.getRel_region();
			if (entity.getRel_region() != null) {
				country = entity.getRel_region().toString().substring(0, 6);
				city = entity.getRel_region().toString().substring(0, 4) + "00";
				province = entity.getRel_region().toString().substring(0, 2) + "0000";
			}
			request.setAttribute("province", province);
			request.setAttribute("city", city);
			request.setAttribute("country", country);
			request.setAttribute("town", town);

		}
		super.copyProperties(form, entity);

		return mapping.findForward("list");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcUserAddrs entity = new EcUserAddrs();// 取list 
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {// 如果id不为空，取详细信息
			entity = new EcUserAddrs();
			entity.setOwn_sys(ecUser.getUser_type());
			entity.setUser_id(ecUser.getId());
			entity.setId(new Long(id));
			entity = super.getFacade().getEcUserAddrsService().getEcUserAddrs(entity);
			String province = "";
			String city = "";
			String country = "";
			String town = "";
			town = "" + entity.getRel_region();
			if (entity.getRel_region() != null) {
				country = entity.getRel_region().toString().substring(0, 6);
				city = entity.getRel_region().toString().substring(0, 4) + "00";
				province = entity.getRel_region().toString().substring(0, 2) + "0000";
			}
			request.setAttribute("province", province);
			request.setAttribute("city", city);
			request.setAttribute("country", country);
			request.setAttribute("town", town);

		}
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		return mapping.findForward("input");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		DynaBean dynaBean = (DynaBean) form;

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		EcUserAddrs entity = new EcUserAddrs();
		super.copyProperties(entity, form);
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setUser_id(ecUser.getId());
		if (!StringUtils.isBlank(province) && GenericValidator.isLong(province)) {
			entity.setRel_province(new Long(province));
		}
		if (!StringUtils.isBlank(city) && GenericValidator.isLong(city)) {
			entity.setRel_city(new Long(city));
		}
		if (!StringUtils.isBlank(town) && GenericValidator.isLong(town)) {
			entity.setRel_region(new Long(town));
		} else {
			entity.setRel_region(new Long(country));
		}

		if (entity.getDefault_addr() != null && entity.getDefault_addr().intValue() == 1) {// 如果当前地址为默认，设置其他地址为非默认地址
			EcUserAddrs ecUserAddrs = new EcUserAddrs();
			ecUserAddrs.setUser_id(ecUser.getId());
			ecUserAddrs.setOwn_sys(ecUser.getUser_type());
			super.getFacade().getEcUserAddrsService().modifyEcUserAddrsForDefaultAddr(entity);
		} else {
			entity.setDefault_addr(new Integer(0));
		}

		if (entity.getId() != null) {
			super.getFacade().getEcUserAddrsService().modifyEcUserAddrs(entity);
			// super.saveMessage(request, "entity.updated");
		} else {
			// 限制添加收货地址数量10个
			EcUserAddrs addrs = new EcUserAddrs();
			addrs.setUser_id(ecUser.getId());
			addrs.setOwn_sys(ecUser.getUser_type());
			Long c = super.getFacade().getEcUserAddrsService().getEcUserAddrsCount(addrs);
			if (c.intValue() > 9) {
				String msg = "最多添加10个收货地址！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			entity.setOwn_sys(ecUser.getUser_type());
			super.getFacade().getEcUserAddrsService().createEcUserAddrs(entity);
			// super.saveMessage(request, "entity.inserted");
		}
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcUserAddrs entity = new EcUserAddrs();
			entity.setId(new Long(id));
			super.getFacade().getEcUserAddrsService().removeEcUserAddrs(entity);
		}
		return mapping.findForward("success");
	}

	public ActionForward defaultaddr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcUserAddrs entity = new EcUserAddrs();
			entity.setId(new Long(id));
			entity.setUser_id(ecUser.getId());
			entity.setDefault_addr(new Integer(1));
			entity.setOwn_sys(ecUser.getUser_type());
			super.getFacade().getEcUserAddrsService().modifyEcUserAddrsForDefaultAddr(entity);
		}
		return mapping.findForward("success");
	}
}
