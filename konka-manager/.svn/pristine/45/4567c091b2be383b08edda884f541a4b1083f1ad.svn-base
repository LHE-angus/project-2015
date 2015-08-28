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
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;
import com.ebiz.mmt.domain.KonkaParagonShowt;
import com.ebiz.mmt.domain.KonkaParagonShowversion;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonShowtAction extends BaseAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "showt_id", "method"));
		
		String fixdate = (String) dynaBean.get("fixdate");
		Date now = new Date();
		
		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMM");
		if (StringUtils.isEmpty(fixdate)) {
			fixdate = _ft.format(now);
		}
		dynaBean.set("fixdate", fixdate);
		//取形象版本
		KonkaParagonShowversion version = new KonkaParagonShowversion();
		List<KonkaParagonShowversion> versionList = super.getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionList(version);
		request.setAttribute("versionList", versionList);
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		//登录人信息
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		PeProdUser userinfo = new PeProdUser();
		userinfo.setId(ui.getId());
		userinfo = super.getFacade().getPeProdUserService().getPeProdUser(userinfo);
		
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String shop_order_like = (String) dynaBean.get("shop_order_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		
		String channel_name_like = (String) dynaBean.get("channel_name_like");
		String part_company_id = (String) dynaBean.get("part_company_id");
		String area_id = (String) dynaBean.get("area_id");
		String fixdate = (String) dynaBean.get("fixdate");
		
		KonkaParagonShowt entity = new KonkaParagonShowt();
		
		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		super.copyProperties(form, userinfo);
		if(StringUtils.isNotBlank(shop_order_like)){
			entity.setShow_shop_code(shop_order_like);
		}
		if(StringUtils.isNotBlank(fixdate)){
			entity.setFixdate(fixdate);
		}
		entity.getMap().put("shop_name_like", shop_name_like);
		entity.getMap().put("channel_name_like", channel_name_like);
		entity.getMap().put("part_company_id", part_company_id);
		entity.getMap().put("area_id", area_id);
		if(userinfo!=null){
			if(userinfo.getMap().get("dept_type").toString().equals("3")){
				entity.getMap().put("part_company_id_showt", new Long(userinfo.getMap().get("par_dept_ids").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("4")){
				entity.getMap().put("part_company_id_showt", new Long(userinfo.getMap().get("par_id").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("5")){
				KonkaDept konka_dept_5 = new KonkaDept();
				konka_dept_5.setDept_id(new Long(userinfo.getMap().get("par_id").toString()));
				konka_dept_5=super.getFacade().getKonkaDeptService().getKonkaDept(konka_dept_5);
				entity.getMap().put("part_company_id_showt", konka_dept_5.getPar_id());
			}
		}
		
		Long recordCount = getFacade().getKonkaParagonShowtService().getKonkaParagonShowtCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowt> entityList = getFacade().getKonkaParagonShowtService().getKonkaParagonShowtPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaParagonShowt entity = new KonkaParagonShowt();
		super.copyProperties(entity, form);

		if (null == entity.getShowt_id()) {
			
			entity.setAddman(userInfo.getId());
			entity.setAddtime(new Date());
			
			getFacade().getKonkaParagonShowtService().createKonkaParagonShowt(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaParagonShowtService().modifyKonkaParagonShowt(entity);
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
		String showt_id = (String) dynaBean.get("showt_id");
		//取形象版本
		KonkaParagonShowversion version = new KonkaParagonShowversion();
		List<KonkaParagonShowversion> versionList = super.getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversionList(version);
		request.setAttribute("versionList", versionList);
		
		if (GenericValidator.isLong(showt_id)) {
			KonkaParagonShowt entity = new KonkaParagonShowt();
			entity.setShowt_id(new Long(showt_id));
			entity = getFacade().getKonkaParagonShowtService().getKonkaParagonShowt(entity);
			
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			if(entity.getVersion_id()!=0&&entity.getVersion_id()!=null){
				KonkaParagonShowversion showVersion = new KonkaParagonShowversion();
				showVersion.setVersion_id(entity.getVersion_id());
				showVersion = super.getFacade().getKonkaParagonShowversionService().getKonkaParagonShowversion(showVersion);
				dynaBean.set("version_name", showVersion.getVersion_name());
			}
			KonkaParagonShowinfo showInfo = new KonkaParagonShowinfo();
			showInfo.setShow_shop_code(entity.getShow_shop_code());
			showInfo = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfo(showInfo);
			dynaBean.set("show_shop_name", showInfo.getShow_shop_name());
			
			entity.setQueryString(super.serialize(request, "showt_id", "method"));
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", showt_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String showt_id = (String) dynaBean.get("showt_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(showt_id) && GenericValidator.isLong(showt_id)) {
			KonkaParagonShowt entity = new KonkaParagonShowt();
			entity.setShowt_id(new Long(showt_id));
			getFacade().getKonkaParagonShowtService().removeKonkaParagonShowt(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonShowt entity = new KonkaParagonShowt();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaParagonShowtService().removeKonkaParagonShowt(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "showt_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String showt_id = (String) dynaBean.get("showt_id");

		if (GenericValidator.isLong(showt_id)) {
			KonkaParagonShowt entity = new KonkaParagonShowt();
			entity.setShowt_id(new Long(showt_id));
			entity = getFacade().getKonkaParagonShowtService().getKonkaParagonShowt(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", showt_id);
			return mapping.findForward("list");
		}
	}
	
	public ActionForward viewShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String show_shop_code = (String) dynaBean.get("show_shop_code");
		
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		
		if(show_shop_code!=null){
			
			entity.setShow_shop_code(show_shop_code);
			entity = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoForView(entity);

			KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
			entity1.setShow_shop_code(entity.getShow_shop_code());
			entity1 = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(entity1);
			
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(entity1, entity1);
			super.copyProperties(form, entity);
			return new ActionForward("/paragon/KonkaParagonShowinfo/view.jsp");
		}else {
			return mapping.findForward("list");
		}
	}
}