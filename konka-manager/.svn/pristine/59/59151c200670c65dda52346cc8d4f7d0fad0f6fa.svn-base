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
import com.ebiz.mmt.domain.BranchAssign;
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
public class KonkaR3VisitRecordAction extends BaseAction {

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
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
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
		
		entity.getMap().put("ywy_not_null", 1);
		entity.getMap().put("ywy_name_like", ywy_name_like);
		entity.getMap().put("keyword", shop_name_like);
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
			}
			
			BranchAssign t = new BranchAssign();
			t.setKonka_r3_id(r3.getId());
			List<BranchAssign> baList = super.getFacade().getBranchAssignService().getBranchAssignList(t);		
			t = baList.get(0);
					
			PeProdUser ywy = new PeProdUser();
			ywy.setId(t.getUser_id());
			ywy = super.getFacade().getPeProdUserService().getPeProdUser(ywy);
			r3.getMap().put("ywy_name", ywy.getUser_name());
		}
		
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward visit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String shop_id = (String) dynaBean.get("shop_id");

		KonkaShopVisit visit = new KonkaShopVisit();
		visit.setShop_id(Long.valueOf(shop_id));

		if (StringUtils.isNotBlank(shop_id)) {
			visit.setShop_id(Long.valueOf(shop_id));
		}

		Long recordCount = super.getFacade().getKonkaShopVisitService().getKonkaShopVisitCount(visit);
		pager.init(recordCount, 10, pager.getRequestPage());
		visit.getRow().setFirst(pager.getFirstRow());
		visit.getRow().setCount(pager.getRowCount());
		List<KonkaShopVisit> visitList = super.getFacade().getKonkaShopVisitService()
				.getKonkaShopVisitPaginatedList(visit);
		request.setAttribute("visitList", visitList);

		return mapping.findForward("view");
	}

	public ActionForward ajaxSelectVisitById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String visit_id = (String) dynaBean.get("visit_id");
		KonkaShopVisit visit = new KonkaShopVisit();

		if (StringUtils.isNotBlank(visit_id)) {
			visit.setId(Long.valueOf(visit_id));
		}
		visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);

		StringBuffer jsonBuffer = new StringBuffer();
		if (visit != null) {
			jsonBuffer.append("{\"ajax_status\":\"1\",");
			jsonBuffer.append("\"id\":\"").append(visit.getId()).append("\",");
			jsonBuffer.append("\"user_name\":\"").append(visit.getUser_name()).append("\",");
			jsonBuffer.append("\"shop_name\":\"").append(visit.getShop_name()).append("\",");
			jsonBuffer.append("\"visit_status\":\"").append(visit.getVisit_status()).append("\",");
			jsonBuffer.append("\"visit_count\":\"").append(visit.getVisit_count()).append("\",");
			String reg[] = { "\t", "\r", "\n" };
			String rep[] = { "t", "r", "n" };
			jsonBuffer.append("\"visit_note\":\"").append(replaceSpecialCharacter(visit.getVisit_note(), reg, rep))
					.append("\"");
			jsonBuffer.append("}");
		} else {
			jsonBuffer.append("{\"ajax_status\":\"0\"}");
		}
		logger.info("JSON Output : [{}]", jsonBuffer.toString());

		super.render(response, jsonBuffer.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	/*
	 * 替换特殊字符 \t \r \n 等
	 */
	public String replaceSpecialCharacter(String str, String reg[], String rep[]) {
		for (int i = 0; i < reg.length; i++) {
			Pattern p = Pattern.compile(reg[i]);
			Matcher m = p.matcher(str);
			str = m.replaceAll("#" + rep[i] + "#");
		}
		return str;
	}
}
