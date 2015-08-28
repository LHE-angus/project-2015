package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;

public class CustomerManageAction extends MobileBaseAction {

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
		String keyword = (String) dynaBean.get("keyword");
		String code_like = (String) dynaBean.get("code_like");
		String is_match = (String) dynaBean.get("is_match");

		request.setAttribute("keyword", keyword);
		request.setAttribute("code_like", code_like);
		request.setAttribute("is_match", is_match);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);

		// 添加权限
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		Long user_dept_id = userInfo.getDept_id();
		if (ui.getDept_id() != null && ui.getDept_id() > user_dept_id) {
			user_dept_id = ui.getDept_id();
		}

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userInfo.getId());
		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(
				peRoleUser);

		dynaBean.set("match", is_match);
		KonkaR3Shop entity = new KonkaR3Shop();

		super.copyProperties(entity, form);

		entity.setIs_del(new Long(0));
		if (!GenericValidator.isLong(is_match)) {
			entity.getMap().put("is_assign", "true");
		} else {
			entity.getMap().put("is_assign", "true");
			entity.setIs_match(Long.valueOf(is_match));

		}
		entity.setIs_del(new Long(0));

		KonkaDept dept = new KonkaDept();
		if (peRoleUser.getRole_id() >= 10 && peRoleUser.getRole_id() <= 29) {// 管理员和事业部
			dynaBean.set("key", "true");
		}
		if (null != peRoleUser.getRole_id()
				&& (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 39)) {// 若登录用户属于分公司
			dept.setDept_id(ui.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap()
						.put("branch_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_name_like", "1");
			}
			dynaBean.set("addBatchPopedom", null);
		} else if (peRoleUser.getRole_id() >= 40
				&& peRoleUser.getRole_id() < 60) {
			entity.getMap().put("konka_dept_id", ui.getDept_id());
			dynaBean.set("addBatchPopedom", null);
		} else if (peRoleUser.getRole_id() == 60) {
			entity.getMap().put("ywy_user_id", ui.getId());
		} else {
			dynaBean.set("addBatchPopedom", 1);
		}

		entity.getMap().put("keyword", keyword);
		entity.getMap().put("code_like", code_like);

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
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		Long count = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(
				entity);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopPaginatedList(entity);
			super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

			request.setAttribute("entityList", entityList);
		}
		// 写日志
		createMobileSysOperLog(request, "KonkaR3Shop", 711010l, "711010", "查询",
				"手机端-客户管理查询-列表");
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity.getMap().put("is_assign", 1);
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(
				entity);

		if (entity != null) {
			String mmt_shop_id = entity.getMap().get("mmt_shop_id").toString();

			EntpShop entpShop = new EntpShop();
			entpShop.setShop_id(Long.valueOf(mmt_shop_id));
			entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);
			if (entpShop != null) {
				BaseProvinceList baseProvinceList = new BaseProvinceList();
				baseProvinceList
						.setP_index(Long.valueOf(entpShop.getP_index()));
				List<BaseProvinceList> baseProvinceListList = super.getFacade()
						.getBaseProvinceListService()
						.getBaseProvinceListParentList(baseProvinceList);

				request.setAttribute("baseProvinceListList",
						baseProvinceListList);
			}
			request.setAttribute("entpShop", entpShop);
		} else if (entity == null) {
			String msg = "数据错误！此网点未匹配上买卖提网点，请解除匹配后重新匹配买卖提网点。";
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		super.copyProperties(form, entity);
		// 写日志
		createMobileSysOperLog(request, "KonkaR3Shop", Long.valueOf(id), "711010",
				"查询", "手机端-客户管理查询-详情");
		return mapping.findForward("view");
	}
}
