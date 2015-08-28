package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
import java.util.List;
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
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.mobile.admin.MobileBaseAction;

public class VisitPlanAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);

		// 查询条件
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String develop_status = (String) dynaBean.get("shop_develop_status");
		String visit_status = (String) dynaBean.get("shop_visit_status");
	
		SimpleDateFormat _ft = new SimpleDateFormat("yyyy-MM-dd");
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_MOBILE);

		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
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

		String page = (String) dynaBean.get("currentPage");
		String pagelimit = (String) dynaBean.get("pagelimit");
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isNotEmpty(page)) {
			currentPage = Integer.parseInt(page);
		}
		if (StringUtils.isNotEmpty(pagelimit)) {
			pageSize = Integer.parseInt(pagelimit);
		}
		shopDevelop.getRow().setFirst((currentPage - 1) * pageSize);
		shopDevelop.getRow().setCount(pageSize);
		Long count = getFacade().getKonkaShopDevelopService()
				.getKonkaShopDevelopCount(shopDevelop);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));

		String outStr = "{htmlData:'";
		if (count > 0) {
			List<KonkaShopDevelop> shop_List = super.getFacade()
					.getKonkaShopDevelopService()
					.getKonkaShopDevelopPaginatedList(shopDevelop);
			request.setAttribute("shop_List", shop_List);
			for (int i = 0; i < shop_List.size(); i++) {
				KonkaShopDevelop dev = shop_List.get(i);

				KonkaShopVisit visit = new KonkaShopVisit();
				visit.setUser_id(peProdUser.getId());
				visit.setShop_id(dev.getShop_id());
				visit.getMap().put("is_last", true);
				visit = super.getFacade().getKonkaShopVisitService()
						.getKonkaShopVisit(visit);
				if (visit != null) {
					dev.getMap().put("visit_count", visit.getVisit_count());
					dev.getMap().put("visit_status", visit.getVisit_status());
					dev.getMap().put("visit_date", visit.getVisit_date());
				}
			}
			outStr += "<ul data-role=\"listview\" data-inset=\"false\" data-theme=\"b\" data-divider-theme=\"a\">";
			for (KonkaShopDevelop _k : shop_List) {
				outStr += "<li data-role=\"list-divider\">网点名称:"
						+ _k.getShop_name() + "</li>";
				outStr += "<li>拜访次数:"
						+ (_k.getMap().get("visit_count") == null ? "" : _k
								.getMap().get("visit_count")) + "</li>";
				outStr += "<li>最后拜访时间:"
						+ (_k.getMap().get("visit_date") == null ? "" : _ft
								.format(_k.getMap().get("visit_date")))
						+ "</li>";
			}
			outStr += "</ul>',";
			outStr += "currentPage:" + currentPage + ",recordCount:" + count
					+ "}";
		} else {
			outStr += "',";
			outStr += "currentPage:" + currentPage + ",recordCount:" + count
					+ "}";
		}

		outStr = outStr.replace("null", "");
		super.renderText(response, outStr);
		// 写日志
		createMobileSysOperLog(request, "KonkaShopDevelop", 711030l, "711030",
				"查询", "手机端-拜访计划查询-列表");
		return null;
	}
}
