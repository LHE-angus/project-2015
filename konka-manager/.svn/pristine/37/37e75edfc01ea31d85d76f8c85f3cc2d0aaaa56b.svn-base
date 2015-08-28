package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaShopVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-12-14
 */
public class KonkaR3VisitAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		// 查询条件
		String keyword = (String) dynaBean.get("keyword");
		String code_like = (String) dynaBean.get("code_like");
		String visit_status = (String) dynaBean.get("shop_visit_status");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// 添加权限
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long user_dept_id = userInfo.getDept_id();
		if (ui.getDept_id() != null && ui.getDept_id() > user_dept_id) {
			user_dept_id = ui.getDept_id();
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_le_60 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 60L) {
				role_id_ge_40_le_60 = true;
			}
		}
		
		KonkaR3Shop entity = new KonkaR3Shop();
		KonkaDept dept = new KonkaDept();
		if (role_id_ge_30_le_39) {// 若登录用户属于分公司
			dept.setDept_id(ui.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_name_like", "1");
			}
		} else if (role_id_ge_40_le_60) {
			dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_name_like", "1");
			}
		}
		
		entity.getMap().put("ywy_id", userInfo.getId());
		entity.getMap().put("keyword", keyword);
		entity.getMap().put("code_like", code_like);
		if(StringUtils.isNotBlank(visit_status)){
			entity.getMap().put("visit_status_"+visit_status, visit_status);
		}	
	
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
		for (int i = 0; i < entityList.size(); i++) {
			KonkaR3Shop r3 = entityList.get(i);
			KonkaShopVisit visit = new KonkaShopVisit();
			visit.setShop_id(r3.getId());
			visit.getMap().put("is_last", true);
			visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);
			if (visit != null) {
				r3.getMap().put("visit_count", visit.getVisit_count());
				r3.getMap().put("visit_status", visit.getVisit_status());
				r3.getMap().put("visit_date", visit.getVisit_date());
			}else{
				r3.getMap().put("visit_count", 0);
			}
		}
		
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}
	
	public ActionForward visit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String shop_id = (String)dynaBean.get("shop_id");	
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setId(Long.valueOf(shop_id));
		//r3.setIs_match(1L);
		r3 = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3);
		
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		KonkaShopVisit visit = new KonkaShopVisit();
		visit.setUser_id(peProdUser.getId());
		
		if(StringUtils.isNotBlank(shop_id)){
			visit.setShop_id(Long.valueOf(shop_id));
		}
		
		Long recordCount = super.getFacade().getKonkaShopVisitService().getKonkaShopVisitCount(visit);
		pager.init(recordCount, 10, pager.getRequestPage());
		visit.getRow().setFirst(pager.getFirstRow());
		visit.getRow().setCount(pager.getRowCount());
		List<KonkaShopVisit> visitList= super.getFacade().getKonkaShopVisitService().getKonkaShopVisitPaginatedList(visit);
		request.setAttribute("visitList", visitList);
		request.setAttribute("visit_count",recordCount);
		request.setAttribute("shop_name", r3.getCustomer_name());
		
		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String)dynaBean.get("shop_id");
		String visit_note = (String)dynaBean.get("visit_note");
		String visit_status = (String)dynaBean.get("visit_status");
		String visit_id = (String)dynaBean.get("visit_id");
		String visit_count = (String)dynaBean.get("visit_count");
		String shop_name = (String)dynaBean.get("shop_name");		
		
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaShopVisit shopVisit = new KonkaShopVisit();
		// 拜访网点的业务员信息
		shopVisit.setUser_id(peProdUser.getId());
		shopVisit.setUser_name(peProdUser.getUser_name());
		// 拜访记录信息
		shopVisit.setVisit_note(visit_note);
		shopVisit.setVisit_status(Long.valueOf(visit_status));
		if(StringUtils.isNotBlank(visit_id)){
			// 修改拜访记录
			shopVisit.setId(Long.valueOf(visit_id));
			super.getFacade().getKonkaShopVisitService().modifyKonkaShopVisit(shopVisit);
			super.saveMessage(request, "entity.updated");
			dynaBean.set("visit_note", "");
			dynaBean.set("visit_status", "");
		}else{
			// 新增拜访记录
			shopVisit.setShop_id(Long.valueOf(shop_id));
			shopVisit.setVisit_count(Long.valueOf(visit_count));
			shopVisit.setShop_name(shop_name);
			super.getFacade().getKonkaShopVisitService().createKonkaShopVisit(shopVisit);	
			
			super.saveMessage(request, "entity.inserted");
			
			dynaBean.set("visit_note", "");
			dynaBean.set("visit_status", "");	
			
		}
		dynaBean.set("visit_id", "");

		return visit(mapping, form,request,response);
	}

	public ActionForward ajaxSelectVisitById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String visit_id = (String) dynaBean.get("visit_id");		
		KonkaShopVisit visit = new KonkaShopVisit();
		
		if(StringUtils.isNotBlank(visit_id)){
			visit.setId(Long.valueOf(visit_id));
		}
		visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);
		
		StringBuffer jsonBuffer = new StringBuffer();
	    if(visit != null) {
				jsonBuffer.append("{\"ajax_status\":\"1\",");
				jsonBuffer.append("\"id\":\"").append(visit.getId()).append("\",");
				jsonBuffer.append("\"shop_name\":\"").append(visit.getShop_name()).append("\",");
				jsonBuffer.append("\"visit_status\":\"").append(visit.getVisit_status()).append("\",");
				jsonBuffer.append("\"visit_count\":\"").append(visit.getVisit_count()).append("\",");
				String reg[] = {"\t","\r","\n"};
				String rep[] = {"t","r","n"};
				jsonBuffer.append("\"visit_note\":\"").append(replaceSpecialCharacter(visit.getVisit_note(),reg,rep)).append("\"");
				jsonBuffer.append("}");
		}else{
			jsonBuffer.append("{\"ajax_status\":\"0\"}");
		}
		logger.info("JSON Output : [{}]", jsonBuffer.toString());

		super.render(response, jsonBuffer.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
	
	/* 
	 * 替换特殊字符  \t \r \n 等 
	 *
	 * */
	public String replaceSpecialCharacter(String str,String reg[],String rep[]) {
		for(int i = 0;i<reg.length;i++){
			Pattern p = Pattern.compile(reg[i]);
			Matcher m = p.matcher(str);
			str = m.replaceAll("#"+rep[i]+"#");
		}
		return str;
	}
}
