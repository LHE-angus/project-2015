package com.ebiz.mmt.web.struts.manager.paragon;

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
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
/**
 * @desc 完美终端--门店管理
 * @author Xu，Wei
 * 
 */
public class KonkaParagonShowinfoAction extends BaseAction {
	
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0l);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "show_shop_id", "method"));
		return mapping.findForward("input");
	}
	//取得未分配业务员
	public ActionForward getSalesman(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String show_shop_id = (String) dynaBean.get("show_shop_id");
		
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		entity.setShow_shop_id(new Long(show_shop_id));
		entity = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoForView(entity);
		super.copyProperties(form, entity);
		dynaBean.set("show_shop_id", show_shop_id);
		
		return new ActionForward("/paragon/KonkaParagonShowinfo/getSalesman.jsp");
	}
	//取得未分配业务员
	public ActionForward editSalesman(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String show_shop_id = (String) dynaBean.get("show_shop_id");
		
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		entity.setShow_shop_id(new Long(show_shop_id));
		entity = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoForView(entity);
		super.copyProperties(form, entity);
		
		KonkaParagonShowmanre showmanre = new KonkaParagonShowmanre();
		showmanre.setShow_shop_code(entity.getShow_shop_code());
		showmanre = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(showmanre);
		super.copyProperties(form, showmanre);
		
		dynaBean.set("show_shop_id", show_shop_id);
		
		return new ActionForward("/paragon/KonkaParagonShowinfo/getSalesman.jsp");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		
		//登陆人信息
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
		
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		
		super.copyProperties(form, userinfo);
		
		if(StringUtils.isNotBlank(shop_order_like)){
			entity.setShow_shop_code(shop_order_like);
		}
		if(StringUtils.isNotBlank(shop_name_like)){
			entity.getMap().put("shop_name_like", shop_name_like);
		}
		if(StringUtils.isNotBlank(channel_name_like)){
			entity.getMap().put("channel_name_like", channel_name_like);
		}
		if(StringUtils.isNotBlank(part_company_id)){
			entity.setPart_company_id(Long.parseLong(part_company_id));
		}
		if(StringUtils.isNotBlank(area_id)){
			entity.setArea_id(Long.parseLong(area_id));
		}
		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		if(userinfo!=null){
			if(userinfo.getMap().get("dept_type").toString().equals("1") || userinfo.getMap().get("dept_type").toString().equals("2")){
				// 经办列表
				KonkaDept konka_dept1 = new KonkaDept();
				konka_dept1.getMap().put("is_jingban", 1);
				List<KonkaDept> deptInfo1List = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept1);
				request.setAttribute("deptInfo1List", deptInfo1List);
			}else if(userinfo.getMap().get("dept_type").toString().equals("3")){
				entity.setPart_company_id(new Long(userinfo.getMap().get("par_dept_ids").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("4")){
				entity.setPart_company_id(new Long(userinfo.getMap().get("par_id").toString()));
			}else if(userinfo.getMap().get("dept_type").toString().equals("5")){
				KonkaDept konka_dept_5 = new KonkaDept();
				konka_dept_5.setDept_id(new Long(userinfo.getMap().get("par_id").toString()));
				konka_dept_5=super.getFacade().getKonkaDeptService().getKonkaDept(konka_dept_5);
				entity.setPart_company_id(konka_dept_5.getPar_id());
			}
		}
		
		Long recordCount = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowinfo> entityList = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoPaginatedList(entity);
		for(int i=0;i<entityList.size();i++){
			Long area_id_like = entityList.get(0).getArea_id();
			Long par_company_id_like = entityList.get(0).getPart_company_id();
			if(entityList.get(i).getArea_id()==area_id_like){
				dynaBean.set("area_id_like", area_id_like.toString());
			}
			if (entityList.get(i).getPart_company_id().equals(par_company_id_like)) {
				dynaBean.set("par_company_id_like", par_company_id_like.toString());
			}
		}
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
		String show_shop_code = (String) dynaBean.get("show_shop_code");
		
		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		super.copyProperties(entity, form);
		
		if (null == entity.getShow_shop_id()) {
			KonkaParagonShowinfo showInfo = new KonkaParagonShowinfo();
			if(StringUtils.isNotBlank(show_shop_code)){
				showInfo.setShow_shop_code(show_shop_code.toUpperCase());
			}
			showInfo = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfo(showInfo);
			if(showInfo!=null){
				super.renderJavaScript(response,"window.onload=function(){alert('此门店代码已存在，请重新填写。');history.back();return false;}");
				return null;
			}else{
				if(StringUtils.isNotBlank(show_shop_code)){
					entity.setShow_shop_code(show_shop_code.toUpperCase());
				}
				entity.setAddman(userInfo.getId());
				entity.setAddtime(new Date());
				getFacade().getKonkaParagonShowinfoService().createKonkaParagonShowinfo(entity);
				saveMessage(request, "entity.inserted");
			}
		} else {
			
			getFacade().getKonkaParagonShowinfoService().modifyKonkaParagonShowinfo(entity);
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
	
	public ActionForward saveSales(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String show_shop_id = (String) dynaBean.get("show_shop_id");
		String user_id = (String) dynaBean.get("user_id");
		
		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaParagonShowmanre entity = new KonkaParagonShowmanre();
		
		super.copyProperties(entity, form);
		
		entity.setUser_id(new Long(user_id));
		
		KonkaParagonShowinfo showinfo = new KonkaParagonShowinfo();
		showinfo.setShow_shop_id(new Long(show_shop_id));
		showinfo = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoForView(showinfo);
		
		KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
		entity1.setShow_shop_code(showinfo.getShow_shop_code());
		entity1 = super.getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(entity1);
		
		entity.setAddman(userInfo.getId());
		entity.setAddtime(new Date());
		entity.setShow_shop_code(showinfo.getShow_shop_code());
		entity.setAddman(userInfo.getId());
		entity.setAddtime(new Date());
		
		if (null == entity1) {
			getFacade().getKonkaParagonShowmanreService().createKonkaParagonShowmanre(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setRe_id(entity1.getRe_id());
			getFacade().getKonkaParagonShowmanreService().modifyKonkaParagonShowmanre(entity);
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
		String show_shop_id = (String) dynaBean.get("show_shop_id");

		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		
		if (GenericValidator.isLong(show_shop_id)) {
			KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
			entity.setShow_shop_id(new Long(show_shop_id));
			entity = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfo(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "show_shop_id", "method"));
			super.copyProperties(form, entity);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", show_shop_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("show_shop_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
			entity.setShow_shop_id(new Long(id));
			entity = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfo(entity);
			
			KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
			entity1.setShow_shop_code(entity.getShow_shop_code());
			KonkaParagonShowmanre manre = super.getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(entity1);
			if(manre!=null){
				KonkaParagonShowmanre showmanre = new KonkaParagonShowmanre();
				showmanre.setRe_id(manre.getRe_id());
				getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(showmanre);
			}
			getFacade().getKonkaParagonShowinfoService().removeKonkaParagonShowinfo(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
			entity.getMap().put("pks", pks);
			for(int i =0;i<pks.length;i++){
				KonkaParagonShowinfo showinfo = new KonkaParagonShowinfo();
				showinfo.setShow_shop_id(new Long(pks[i]));
				showinfo = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfo(showinfo);
				
				KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
				entity1.setShow_shop_code(showinfo.getShow_shop_code());
				KonkaParagonShowmanre manre = super.getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(entity1);
				if(manre!=null){
					KonkaParagonShowmanre showmanre = new KonkaParagonShowmanre();
					showmanre.setRe_id(manre.getRe_id());
					getFacade().getKonkaParagonShowmanreService().removeKonkaParagonShowmanre(showmanre);
				}
			}
			getFacade().getKonkaParagonShowinfoService().removeKonkaParagonShowinfo(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String show_shop_id = (String) dynaBean.get("show_shop_id");

		if (GenericValidator.isLong(show_shop_id)) {
			KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
			entity.setShow_shop_id(new Long(show_shop_id));
			entity = getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoForView(entity);
			
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			
			KonkaParagonShowmanre entity1 = new KonkaParagonShowmanre();
			entity1.setShow_shop_code(entity.getShow_shop_code());
			entity1 = getFacade().getKonkaParagonShowmanreService().getKonkaParagonShowmanre(entity1);
			
			request.setAttribute("entity1", entity1);
			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", show_shop_id);
			return mapping.findForward("list");
		}
	}
}