package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-23
 */
public class KonkaR3ShopHomePageAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String customer_par_index = (String) dynaBean.get("customer_par_index");// 客户类型
		String customer_type = (String) dynaBean.get("customer_type");// 客户细分类型
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

		String r3_money_gt = (String) dynaBean.get("r3_money_gt");
		String r3_money_lt = (String) dynaBean.get("r3_money_lt");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		KonkaR3Shop entity = new KonkaR3Shop();

		if (zb && !fgs) {// session用户属于总部，不属于分公司

		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
		}

		if (!zb && !fgs) {// session用户不属于总部，也不属于分公司
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		entity.getMap().put("group_province", "true");
		if (StringUtils.isNotBlank(customer_par_index)) {
			entity.getMap().put("customer_par_index", customer_par_index);
		}
		if (StringUtils.isNotBlank(customer_type)) {
			entity.setCustomer_type(customer_type);
		}
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(r3_money_gt)) {
			entity.getMap().put("r3_money_gt", r3_money_gt);
		}
		if (StringUtils.isNotBlank(r3_money_lt)) {
			entity.getMap().put("r3_money_lt", r3_money_lt);
		}
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
		request.setAttribute("entityList", entityList);

		if (null != entityList && entityList.size() > 0) {
			KonkaR3Shop k = entityList.get(0);
			Integer max_num = Integer.valueOf(k.getMap().get("num").toString());
			max_num = (max_num / 100 + 1) * 100;
			request.setAttribute("max_num", max_num);
		} else {
			request.setAttribute("max_num", "100");
		}

		BaseProvinceListFour b = new BaseProvinceListFour();
		b.setDel_mark(0);
		b.setPar_index(0L);
		b.setP_level(1);
		List<BaseProvinceListFour> provinceList = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(b);
		request.setAttribute("provinceList", provinceList);

		// 客户类型
		KonkaCategory konkaCategory = new KonkaCategory();
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		String customer_par_index = (String) dynaBean.get("customer_par_index");// 客户类型
		String customer_type = (String) dynaBean.get("customer_type");// 客户细分类型
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

		String r3_money_gt = (String) dynaBean.get("r3_money_gt");
		String r3_money_lt = (String) dynaBean.get("r3_money_lt");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (!zb && !fgs) {// session用户不属于总部，也不属于分公司
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(town) && StringUtils.isNotBlank(country) && StringUtils.isNotBlank(city)
				&& StringUtils.isNotBlank(province)) {
			// 地市列表
			KonkaR3Shop entity = new KonkaR3Shop();
			entity.getMap().put("province", province);
			entity.getMap().put("group_city", "true");
			entity.getMap().put("province_ne", province);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_city = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_city", list_city);

			// 区县
			entity = new KonkaR3Shop();
			entity.getMap().put("city", city);
			entity.getMap().put("group_country", "true");
			entity.getMap().put("city_ne", city);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_country = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_country", list_country);

			// 乡镇
			entity = new KonkaR3Shop();
			entity.getMap().put("country", country);
			entity.getMap().put("group_town", "true");
			entity.getMap().put("country_ne", country);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_town = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_town", list_town);

			// 客户销售额列表
			entity = new KonkaR3Shop();
			entity.getMap().put("town", town);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopListByPIndex(entity);
			request.setAttribute("entityList", entityList);

		} else if (StringUtils.isNotBlank(country) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(province)) {
			// 地市列表
			KonkaR3Shop entity = new KonkaR3Shop();
			entity.getMap().put("province", province);
			entity.getMap().put("group_city", "true");
			entity.getMap().put("province_ne", province);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_city = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_city", list_city);

			// 区县
			entity = new KonkaR3Shop();
			entity.getMap().put("city", city);
			entity.getMap().put("group_country", "true");
			entity.getMap().put("city_ne", city);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_country = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_country", list_country);

			// 乡镇
			entity = new KonkaR3Shop();
			entity.getMap().put("country", country);
			entity.getMap().put("group_town", "true");
			entity.getMap().put("country_ne", country);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_town = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_town", list_town);

			// 客户销售额列表
			entity = new KonkaR3Shop();
			entity.getMap().put("country", country);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopListByPIndex(entity);
			request.setAttribute("entityList", entityList);
		} else if (StringUtils.isNotBlank(city) && StringUtils.isNotBlank(province)) {
			// 地市列表
			KonkaR3Shop entity = new KonkaR3Shop();
			entity.getMap().put("province", province);
			entity.getMap().put("group_city", "true");
			entity.getMap().put("province_ne", province);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_city = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_city", list_city);

			// 区县
			entity = new KonkaR3Shop();
			entity.getMap().put("city", city);
			entity.getMap().put("group_country", "true");
			entity.getMap().put("city_ne", city);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_country = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_country", list_country);

			// 客户销售额列表
			entity = new KonkaR3Shop();
			entity.getMap().put("city", city);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopListByPIndex(entity);
			request.setAttribute("entityList", entityList);
		} else if (StringUtils.isNotBlank(province)) {
			// 地市列表
			KonkaR3Shop entity = new KonkaR3Shop();
			entity.getMap().put("province", province);
			entity.getMap().put("group_city", "true");
			entity.getMap().put("province_ne", province);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> list_city = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByPIndex(entity);
			request.setAttribute("list_city", list_city);

			// 客户销售额列表
			entity = new KonkaR3Shop();
			entity.getMap().put("province", province);

			// -----------------添加搜索条件 start-----------------
			if (StringUtils.isNotBlank(customer_par_index)) {
				entity.getMap().put("customer_par_index", customer_par_index);
			}
			if (StringUtils.isNotBlank(customer_type)) {
				entity.setCustomer_type(customer_type);
			}
			if (StringUtils.isNotBlank(add_date_start)) {
				entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			}
			if (StringUtils.isNotBlank(add_date_end)) {
				entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			}
			if (StringUtils.isNotBlank(r3_money_gt)) {
				entity.getMap().put("r3_money_gt", r3_money_gt);
			}
			if (StringUtils.isNotBlank(r3_money_lt)) {
				entity.getMap().put("r3_money_lt", r3_money_lt);
			}
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				entity.getMap().put("dept_id", fgs_dept.getDept_id().toString());
			}
			// -----------------添加搜索条件 end-----------------
			List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopListByPIndex(entity);
			request.setAttribute("entityList", entityList);

		}

		return mapping.findForward("view");
	}
}