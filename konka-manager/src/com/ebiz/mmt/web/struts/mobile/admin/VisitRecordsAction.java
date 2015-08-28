package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.ArrayList;
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
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;

public class VisitRecordsAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// 查询条件
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String shop_develop_status = (String) dynaBean
				.get("shop_develop_status");
		String shop_visit_status = (String) dynaBean.get("shop_visit_status");
		request.setAttribute("ywy_name_like", ywy_name_like);
		request.setAttribute("shop_name_like", shop_name_like);
		request.setAttribute("shop_develop_status", shop_develop_status);
		request.setAttribute("shop_visit_status", shop_visit_status);
		// 登录用户信息
		// 登录用户信息
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userInfo.getId());
		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(
				peRoleUser);
		if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 59) {
			dynaBean.set("dev_able", 1);
		}

		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		if(peRoleUser.getRole_id()>=10 && peRoleUser.getRole_id()<60){
			shopDevelop.getMap().put("is_dept", userInfo.getDept_id());
		}else if(peRoleUser.getRole_id()>=60){
			shopDevelop.setUser_id(userInfo.getId());
		}

		if (StringUtils.isNotBlank(ywy_name_like)) {
			shopDevelop.getMap().put("ywy_name_like", ywy_name_like);
		}

		if (StringUtils.isNotBlank(shop_name_like)) {
			shopDevelop.getMap().put("shop_name_like", shop_name_like);
		}

		if (StringUtils.isNotBlank(shop_develop_status)) {
			shopDevelop.setDevelop_status(Long.valueOf(shop_develop_status));
		}

		if (StringUtils.isNotBlank(shop_visit_status)) {
			if ("0".equals(shop_visit_status)) {
				shopDevelop.getMap().put("is_not_visit", shop_visit_status); // 未拜访条件，拜访记录中无该shop的记录
			} else {
				shopDevelop.getMap().put("visit", true); //
				shopDevelop.getMap().put("visit_status", shop_visit_status); //
			}
		}

		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		shopDevelop.getRow().setFirst((currentPage - 1) * pageSize);
		shopDevelop.getRow().setCount(pageSize);
		Long count = getFacade().getKonkaShopDevelopService()
				.getKonkaShopDevelopCount(shopDevelop);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaShopDevelop> entityList = getFacade()
					.getKonkaShopDevelopService()
					.getKonkaShopDevelopPaginatedList(shopDevelop);
			for (int i = 0; i < entityList.size(); i++) {
				KonkaShopDevelop dev = entityList.get(i);

				KonkaShopVisit visit = new KonkaShopVisit();
				visit.setShop_id(dev.getShop_id());
				visit.getMap().put("is_dept", userInfo.getDept_id());
				visit = super.getFacade().getKonkaShopVisitService()
						.getKonkaShopVisit(visit);
				KonkaShopVisit _visit = new KonkaShopVisit();
				_visit.setShop_id(dev.getShop_id());
				List<KonkaShopVisit> vList = new ArrayList<KonkaShopVisit>();
				vList = super.getFacade().getKonkaShopVisitService()
						.getKonkaShopVisitList(_visit);
				dev.setvList(vList);
				if (visit != null) {
					dev.getMap().put("visit_count", visit.getVisit_count());
					dev.getMap().put("visit_status", visit.getVisit_status());
					dev.getMap().put("visit_date", visit.getVisit_date());
				}
			}
			request.setAttribute("entityList", entityList);
		}
		// 写日志
		createMobileSysOperLog(request, "KonkaShopVisit", 711050l, "711050", "查询",
				"手机端-拜访记录查询-列表");
		return mapping.findForward("list");
	}
}
