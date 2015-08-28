package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaShopVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-12-15
 */
public class KonkaDLSVisitAction extends BaseAction {

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
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String code_like = (String) dynaBean.get("code_like");
		String keyword = (String) dynaBean.get("keyword");
		String visit_status = (String) dynaBean.get("shop_visit_status");
		
		KonkaR3Shop R3Shop = getKonkaR3ShopForSelect(mapping, form, request, response, null);
		R3Shop.getMap().put("is_agents", true);

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30_le_39 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}
		}

		if (role_id_ge_30_le_39) { // 分公司登陆
			R3Shop.getMap().put("dept_id", userInfo.getDept_id());
			R3Shop.getMap().put("branch_area_name_like", null);
			R3Shop.getMap().put("branch_name_like", null);
			R3Shop.getMap().put("is_fengongsi", null);// is_fengongsi 语句用的是or，执行结果有问题
			R3Shop.getMap().put("is_jyb", "true");// 与is_fengongsi 的语句是一样的，但用的是and，结果应该是正确的
		}

		if (GenericValidator.isLong(fgs_dept_id)) {
			R3Shop.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			R3Shop.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			R3Shop.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			R3Shop.getMap().put("ywy_user_id", ywy_user_id);
		}
		
		R3Shop.getMap().put("ywy_id", userInfo.getId());
		R3Shop.getMap().put("keyword", keyword);
		R3Shop.getMap().put("code_like", code_like);
		if(StringUtils.isNotBlank(visit_status)){
			R3Shop.getMap().put("visit_status_"+visit_status, visit_status);
		}
		
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(R3Shop);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		R3Shop.getRow().setFirst(pager.getFirstRow());
		R3Shop.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(R3Shop);

		for(int i=0; i < entityList.size(); i++){			
			KonkaR3Shop r3 = entityList.get(i);
			
			KonkaShopVisit visit = new KonkaShopVisit();
			visit.setShop_id(r3.getId());
			visit.getMap().put("is_last", true);
			visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);
			if (visit != null) {
				r3.getMap().put("visit_count", visit.getVisit_count());
				r3.getMap().put("visit_status", visit.getVisit_status());
				r3.getMap().put("visit_date", visit.getVisit_date());
			}

			List<BranchAssign> list = super.getBranchAssignList(1, r3.getId(), null, null, null, null, null);
			if (list.size() > 0) {
				for (BranchAssign branchAssign : list) {
					if (branchAssign.getUser_id() != null) { // 业务员
						PeProdUser ywy = new PeProdUser();
						ywy.setId(branchAssign.getUser_id());
						ywy = this.getFacade().getPeProdUserService().getPeProdUser(ywy);
						// 可以拜访的代理商，业务员一定not null
						r3.getMap().put("ywy_name", ywy.getReal_name());
						r3.getMap().put("ywy_id", ywy.getId());
					}
				}
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
