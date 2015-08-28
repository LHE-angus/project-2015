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
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.KonkaShopVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-10-20
 */
public class KonkaShopVisitAction extends BaseAction {

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
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String develop_status = (String) dynaBean.get("shop_develop_status");
		String visit_status = (String) dynaBean.get("shop_visit_status");
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_lt_60 = false;
		boolean role_id_ge_60 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 60L) {
				role_id_ge_10_lt_60 = true;
			}
			if (peRoleUser.getRole_id() >= 60L) {
				role_id_ge_60 = true;
			}
		}

		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		if (role_id_ge_10_lt_60) {
			shopDevelop.getMap().put("is_dept", peProdUser.getDept_id());
		} else if (role_id_ge_60) {
			shopDevelop.setUser_id(peProdUser.getId());
		}

		if (StringUtils.isNotBlank(shop_name_like)) {
			shopDevelop.getMap().put("shop_name_like", shop_name_like);
		}

		shopDevelop.setUser_id(peProdUser.getId());

		if (StringUtils.isNotBlank(shop_name_like)) {
			shopDevelop.setShop_name(shop_name_like);
		}

		if (StringUtils.isNotBlank(develop_status)) {
			shopDevelop.setDevelop_status(Long.valueOf(develop_status));
		}

		if (StringUtils.isNotBlank(visit_status)) {
			if ("0".equals(visit_status)) {
				shopDevelop.getMap().put("is_not_visit", visit_status); // 未拜访条件，拜访记录中无该shop的记录
			} else {
				shopDevelop.getMap().put("visit", true); //
				shopDevelop.getMap().put("visit_status", visit_status); //
			}
		}

		Long recordCount = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(shopDevelop);
		pager.init(recordCount, 10, pager.getRequestPage());
		shopDevelop.getRow().setFirst(pager.getFirstRow());
		shopDevelop.getRow().setCount(pager.getRowCount());
		List<KonkaShopDevelop> shop_List = super.getFacade().getKonkaShopDevelopService()
				.getKonkaShopDevelopPaginatedList(shopDevelop);
		request.setAttribute("shop_List", shop_List);

		for (int i = 0; i < shop_List.size(); i++) {
			KonkaShopDevelop dev = shop_List.get(i);

			KonkaShopVisit visit = new KonkaShopVisit();
			visit.setUser_id(peProdUser.getId());
			visit.setShop_id(dev.getShop_id());
			visit.getMap().put("is_last", true);
			visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);
			if (visit != null) {
				dev.getMap().put("visit_count", visit.getVisit_count());
				dev.getMap().put("visit_status", visit.getVisit_status());
				dev.getMap().put("visit_date", visit.getVisit_date());
			}
		}

		return mapping.findForward("list");
	}

	public ActionForward visit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String shop_id = (String) dynaBean.get("shop_id");

		super.encodeCharacterForGetMethod(dynaBean, request);

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		shopDevelop.setUser_id(peProdUser.getId());
		shopDevelop.setShop_id(Long.valueOf(shop_id));
		shopDevelop = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelop(shopDevelop);

		KonkaShopVisit visit = new KonkaShopVisit();
		visit.setUser_id(peProdUser.getId());

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
		request.setAttribute("visit_count", recordCount);
		request.setAttribute("shop_name", shopDevelop.getShop_name());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");
		String visit_note = (String) dynaBean.get("visit_note");
		String remarks = (String) dynaBean.get("remarks");
		String visit_status = (String) dynaBean.get("visit_status");
		String visit_id = (String) dynaBean.get("visit_id");
		String visit_count = (String) dynaBean.get("visit_count");
		String shop_name = (String) dynaBean.get("shop_name");

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaShopVisit shopVisit = new KonkaShopVisit();
		// 拜访网点的业务员信息
		shopVisit.setUser_id(peProdUser.getId());
		shopVisit.setUser_name(peProdUser.getUser_name());
		// 拜访记录信息
		shopVisit.setVisit_note(visit_note);
		shopVisit.setRemarks(remarks);
		shopVisit.setVisit_status(Long.valueOf(visit_status));
		if (StringUtils.isNotBlank(visit_id)) {
			// 修改拜访记录
			shopVisit.setId(Long.valueOf(visit_id));
			super.getFacade().getKonkaShopVisitService().modifyKonkaShopVisit(shopVisit);
			super.saveMessage(request, "entity.updated");
			dynaBean.set("visit_note", "");
			dynaBean.set("visit_status", "");
		} else {
			// 新增拜访记录
			shopVisit.setShop_id(Long.valueOf(shop_id));
			shopVisit.setVisit_count(Long.valueOf(visit_count));
			shopVisit.setShop_name(shop_name);
			super.getFacade().getKonkaShopVisitService().createKonkaShopVisit(shopVisit);

			KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
			shopDevelop.setShop_id(Long.valueOf(shop_id));
			shopDevelop.setUser_id(peProdUser.getId());

			shopDevelop = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelop(shopDevelop);

			KonkaShopDevelop shop = new KonkaShopDevelop();
			shop.setId(shopDevelop.getId());
			shop.setDevelop_status(Long.valueOf("1"));
			super.getFacade().getKonkaShopDevelopService().modifyKonkaShopDevelop(shop);

			super.saveMessage(request, "entity.inserted");

			dynaBean.set("visit_note", "");
			dynaBean.set("remarks", "");
			dynaBean.set("visit_status", "");

		}
		dynaBean.set("visit_id", "");

		return visit(mapping, form, request, response);
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
			jsonBuffer.append("\"shop_name\":\"").append(visit.getShop_name()).append("\",");
			jsonBuffer.append("\"visit_status\":\"").append(visit.getVisit_status()).append("\",");
			jsonBuffer.append("\"visit_count\":\"").append(visit.getVisit_count()).append("\",");
			String reg[] = { "\t", "\r", "\n" };
			String rep[] = { "t", "r", "n" };
			jsonBuffer.append("\"visit_note\":\"").append(replaceSpecialCharacter(visit.getVisit_note(), reg, rep))
					.append("\",");
			jsonBuffer.append("\"remarks\":\"").append(replaceSpecialCharacter(visit.getRemarks(), reg, rep))
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
