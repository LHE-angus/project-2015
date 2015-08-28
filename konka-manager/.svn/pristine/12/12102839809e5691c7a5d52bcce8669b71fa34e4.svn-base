package com.ebiz.mmt.web.struts.manager.paragon;

import java.text.SimpleDateFormat;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonManinfo;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @desc 完美终端--直销员信息管理
 * @author Dou，QingRen
 * @datetime 2012-2-16 16:05:13
 */
public class KonkaParagonManinfoAction extends BaseAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "seller_id", "method"));
		String fixdate = (String) dynaBean.get("fixdate");
		Date now = new Date();
		
		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMM");
		if (StringUtils.isEmpty(fixdate)) {
			fixdate = _ft.format(now);
		}
		dynaBean.set("fixdate", fixdate);
//		
//		KonkaParagonShowinfo konkaParagonShowinfo = new KonkaParagonShowinfo();
//		List<KonkaParagonShowinfo> shopCodeList = getFacade().getKonkaParagonShowinfoService().selectDistinctShopCode(konkaParagonShowinfo);
//		request.setAttribute("shopCodeList", shopCodeList);
		
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		
		//登录人信息
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		PeProdUser userinfo = new PeProdUser();
		userinfo.setId(ui.getId());
		userinfo = super.getFacade().getPeProdUserService().getPeProdUser(userinfo);
		
		DynaBean dynaBean = (DynaBean) form;
		String seller_name_like = (String) dynaBean.get("seller_name_like");
		String show_shop_code_like = (String) dynaBean.get("show_shop_code_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String channel_name_like = (String) dynaBean.get("channel_name_like");
		String part_company_id = (String) dynaBean.get("part_company_id");
		String area_id = (String) dynaBean.get("area_id");
		String fixdate = (String) dynaBean.get("fixdate");
		
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		super.copyProperties(form, userinfo);
		KonkaParagonManinfo entity = new KonkaParagonManinfo();
		entity.getMap().put("seller_name_like", seller_name_like);
		entity.getMap().put("show_shop_code_like", show_shop_code_like);
		entity.getMap().put("shop_name_like", shop_name_like);
		
		if(StringUtils.isNotBlank(channel_name_like)){
			entity.getMap().put("channel_name_like", channel_name_like);
		}
		if(StringUtils.isNotBlank(part_company_id)){
			entity.getMap().put("part_company_id", part_company_id);
		}
		if(StringUtils.isNotBlank(area_id)){
			entity.getMap().put("area_id", area_id);
		}
		if(StringUtils.isNotBlank(fixdate)){
			entity.setFixdate(fixdate);
		}
		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0l);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		if(userinfo!=null){
			if(userinfo.getMap().get("dept_type").toString().equals("3")){
				entity.getMap().put("part_company_id_maninfo", new Long(userinfo.getMap().get("par_dept_ids").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("4")){
				entity.getMap().put("part_company_id_maninfo", new Long(userinfo.getMap().get("par_id").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("5")){
				KonkaDept konka_dept_5 = new KonkaDept();
				konka_dept_5.setDept_id(new Long(userinfo.getMap().get("par_id").toString()));
				konka_dept_5=super.getFacade().getKonkaDeptService().getKonkaDept(konka_dept_5);
				entity.getMap().put("part_company_id_maninfo", konka_dept_5.getPar_id());
			}
		}
		
		Long recordCount = getFacade().getKonkaParagonManinfoService().getKonkaParagonManinfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonManinfo> entityList = getFacade().getKonkaParagonManinfoService().getKonkaParagonManinfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaParagonManinfo entity = new KonkaParagonManinfo();
		super.copyProperties(entity, form);

		if (null == entity.getSeller_id()) {
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			entity.setAddman(peProdUser.getId());
			entity.setAddtime(new Date());
			getFacade().getKonkaParagonManinfoService().createKonkaParagonManinfo(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaParagonManinfoService().modifyKonkaParagonManinfo(entity);
			saveMessage(request, "entity.updated");
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
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String seller_id = (String) dynaBean.get("seller_id");

		if (GenericValidator.isLong(seller_id)) {
			KonkaParagonManinfo entity = new KonkaParagonManinfo();
			entity.setSeller_id(new Long(seller_id));
			entity = getFacade().getKonkaParagonManinfoService().getKonkaParagonManinfo(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			KonkaParagonShowinfo showInfo = new KonkaParagonShowinfo();
			showInfo.setShow_shop_code(entity.getShow_shop_code());
			showInfo = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfo(showInfo);
			dynaBean.set("show_shop_name", showInfo.getShow_shop_name());
			
			entity.setQueryString(super.serialize(request, "seller_id", "method"));
			super.copyProperties(form, entity);
			
//			KonkaParagonShowinfo konkaParagonShowinfo = new KonkaParagonShowinfo();
//			konkaParagonShowinfo.setIf_seller(1);//有直销员的门店
//			List<KonkaParagonShowinfo> shopCodeList = getFacade().getKonkaParagonShowinfoService().selectDistinctShopCode(konkaParagonShowinfo);
//			request.setAttribute("shopCodeList", shopCodeList);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", seller_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String seller_id = (String) dynaBean.get("seller_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(seller_id) && GenericValidator.isLong(seller_id)) {
			KonkaParagonManinfo entity = new KonkaParagonManinfo();
			entity.setSeller_id(new Long(seller_id));
			getFacade().getKonkaParagonManinfoService().removeKonkaParagonManinfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonManinfo entity = new KonkaParagonManinfo();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaParagonManinfoService().removeKonkaParagonManinfo(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "seller_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String seller_id = (String) dynaBean.get("seller_id");

		if (GenericValidator.isLong(seller_id)) {
			KonkaParagonManinfo entity = new KonkaParagonManinfo();
			entity.setSeller_id(new Long(seller_id));
			entity = getFacade().getKonkaParagonManinfoService().getKonkaParagonManinfo(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", seller_id);
			return mapping.findForward("list");
		}
	}}