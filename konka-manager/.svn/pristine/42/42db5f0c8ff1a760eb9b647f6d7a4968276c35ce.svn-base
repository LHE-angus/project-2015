package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
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

import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-11
 */

public class EcBindingPdAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_id = (String) dynaBean.get("goods_id");
		String binding_type = (String) dynaBean.get("binding_type");
		String goods_name_like = (String) dynaBean.get("goods_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBindingPd entity = new EcBindingPd();
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(goods_id);
		}
		if (StringUtils.isNotBlank(goods_name_like)) {
			entity.getMap().put("goods_name_like", goods_name_like);
		}
		if (StringUtils.isNotBlank(binding_type)) {
			entity.setBinding_type(Integer.valueOf(binding_type));
		}

		Long recordCount = super.getFacade().getEcBindingPdService().getEcBindingPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcBindingPd> entityList = super.getFacade().getEcBindingPdService().getEcBindingPdPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		List<KonkaBcompPd> pdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(pd);

		request.setAttribute("pdList", pdList);

		dynaBean.set("own_sys", "1");
		dynaBean.set("binding_type", "0");

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBindingPd ec = new EcBindingPd();
		ec.setId(Long.valueOf(id));
		ec = super.getFacade().getEcBindingPdService().getEcBindingPd(ec);
		super.copyProperties(form, ec);

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String goods_id = (String) dynaBean.get("goods_id");
		String binding_type = (String) dynaBean.get("binding_type");

		if (StringUtils.isBlank(id)) {
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}

			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBindingPd ec = new EcBindingPd();
		super.copyProperties(ec, form);

		KonkaBcompPd kbp = new KonkaBcompPd();
		if (StringUtils.isNotBlank(goods_id)) {
			kbp.setId(Long.valueOf(goods_id));
			kbp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kbp);
		}
		ec.setAdd_u_id(user.getId());

		if (StringUtils.isEmpty(id)) {
			if (binding_type.equals("0")) {
				ec.setGoods_id(null);
				super.getFacade().getEcBindingPdService().createEcBindingPd(ec);
				super.saveMessage(request, "entity.inserted");
			} else if (binding_type.equals("1")) {
				ec.setGoods_id(goods_id);
				ec.setMain_pic(kbp.getMain_pic());
				super.getFacade().getEcBindingPdService().createEcBindingPd(ec);
				super.saveMessage(request, "entity.inserted");
			}
		} else {
			super.getFacade().getEcBindingPdService().modifyEcBindingPd(ec);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(ec.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		if (GenericValidator.isLong(id)) {
			EcBindingPd ec = new EcBindingPd();
			ec.setId(Long.valueOf(id));
			ec = super.getFacade().getEcBindingPdService().getEcBindingPd(ec);
			ec.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, ec);
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.getMap().put("is_upself", true);
		List<KonkaBcompPd> pdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(pd);

		request.setAttribute("pdList", pdList);
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBindingPd ec = new EcBindingPd();
		ec.setId(Long.valueOf(id));
		super.getFacade().getEcBindingPdService().removeEcBindingPd(ec);
		saveMessage(request, "entity.deleted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward selectEcBinding(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String own_sys = (String) dynaBean.get("own_sys");

		// 查询已经关联的服务，新增没有，修改的时候可能有
		List<Long> had_binding_idList = new ArrayList<Long>();
		if (GenericValidator.isLong(goods_id)) {
			EcBcompBinding ebb = new EcBcompBinding();
			ebb.setGoods_id(Long.valueOf(goods_id));
			List<EcBcompBinding> ebbList = super.getFacade().getEcBcompBindingService().getEcBcompBindingList(ebb);
			if (null != ebbList && 0 != ebbList.size()) {
				for (EcBcompBinding ecBcompBinding : ebbList) {
					had_binding_idList.add(ecBcompBinding.getBinding_id());
				}
			}
		}

		EcBindingPd entity = new EcBindingPd();
		entity.setBinding_type(0);
		if (GenericValidator.isInt(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}

		if (null != had_binding_idList && 0 != had_binding_idList.size()) {
			// 查询绑定的服务
			entity.getMap().put("id_in", had_binding_idList);
			List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(entity);
			request.setAttribute("hadEntityList", ecBindingPdList);

			// 查询未绑定的服务
			entity.getMap().put("id_in", null);
			entity.getMap().put("id_not_in", had_binding_idList);
			List<EcBindingPd> ecBindingPdList1 = super.getFacade().getEcBindingPdService().getEcBindingPdList(entity);
			request.setAttribute("entityList", ecBindingPdList1);
		} else {
			request.setAttribute("hadEntityList", null);

			List<EcBindingPd> ecBindingPdList1 = super.getFacade().getEcBindingPdService().getEcBindingPdList(entity);
			request.setAttribute("entityList", ecBindingPdList1);
		}

		return new ActionForward("/../manager/spgl/EcBindingPd/selectEcBinding.jsp");
	}

	public ActionForward selectEcBindingForPd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String own_sys = (String) dynaBean.get("own_sys");

		// 查询已经关联的服务，新增没有，修改的时候可能有
		List<Long> had_binding_idList = new ArrayList<Long>();
		if (GenericValidator.isLong(goods_id)) {
			EcBcompBinding ebb = new EcBcompBinding();
			ebb.setGoods_id(Long.valueOf(goods_id));
			List<EcBcompBinding> ebbList = super.getFacade().getEcBcompBindingService().getEcBcompBindingList(ebb);
			if (null != ebbList && 0 != ebbList.size()) {
				for (EcBcompBinding ecBcompBinding : ebbList) {
					had_binding_idList.add(ecBcompBinding.getBinding_id());
				}
			}
		}

		EcBindingPd entity = new EcBindingPd();
		entity.setBinding_type(1);
		if (GenericValidator.isInt(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}

		if (null != had_binding_idList && 0 != had_binding_idList.size()) {
			// 查询绑定的服务
			entity.getMap().put("id_in", had_binding_idList);
			List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(entity);
			request.setAttribute("hadEntityList", ecBindingPdList);

			// 查询未绑定的服务
			entity.getMap().put("id_in", null);
			entity.getMap().put("id_not_in", had_binding_idList);
			List<EcBindingPd> ecBindingPdList1 = super.getFacade().getEcBindingPdService().getEcBindingPdList(entity);
			request.setAttribute("entityList", ecBindingPdList1);
		} else {
			request.setAttribute("hadEntityList", null);

			List<EcBindingPd> ecBindingPdList1 = super.getFacade().getEcBindingPdService().getEcBindingPdList(entity);
			request.setAttribute("entityList", ecBindingPdList1);
		}

		return new ActionForward("/../manager/spgl/EcBindingPd/selectEcBindingForPd.jsp");
	}

}
