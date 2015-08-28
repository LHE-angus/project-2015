package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class SelectEntyShopByAreaAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/*
	 * 选网点 【根据省 或 地区集合 选择网点】
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String this_userid_dept = (String) dynaBean.get("this_userid_dept"); // 当前用户所在的部门
		String shop_type = (String) dynaBean.get("shop_type"); // 1直营网点 2经销网点

		if (StringUtils.isNotBlank(this_userid_dept) && StringUtils.isNotBlank(shop_type)) {
			request.setAttribute("entityList", this.getEntpShop(mapping, form, request, response));
		} else {
			request.setAttribute("entityList", this.getEntpShop(mapping, form, request, response));
		}

		return mapping.findForward("list");
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String this_userid_dept = (String) dynaBean.get("this_userid_dept"); // 当前用户所在的部门
		String shop_type = (String) dynaBean.get("shop_type"); // 1直营网点 2经销网点

		if (StringUtils.isNotBlank(this_userid_dept) && StringUtils.isNotBlank(shop_type)) {

			StringBuffer sb = new StringBuffer("[");
			@SuppressWarnings("unchecked")
			List<KonkaR3Shop> entityList = (List<KonkaR3Shop>) this.getEntpShop(mapping, form, request, response);

			for (KonkaR3Shop t : entityList) {
				sb.append("{\"id\":\"" + String.valueOf(t.getMap().get("mmt_shop_id")) + "\",");
				sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript((String) t.getMap().get("mmt_shop_name"))
						+ "\"},");
			}
			sb.append("{\"end\":\"\"}]");

			super.renderJson(response, sb.toString());
			return null;
		} else {
			StringBuffer sb = new StringBuffer("[");
			@SuppressWarnings("unchecked")
			List<MmtEntpShop> entityList = (List<MmtEntpShop>) this.getEntpShop(mapping, form, request, response);
			for (MmtEntpShop t : entityList) {
				sb.append("{\"id\":\"" + String.valueOf(t.getShop_id()) + "\",");
				sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getShop_name()) + "\"},");

			}
			sb.append("{\"end\":\"\"}]");
			super.renderJson(response, sb.toString());
			return null;
		}
	}

	public ActionForward toWindowFramePage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/../manager/admin/SelectEntyShopByArea/windowFrame.jsp");
	}

	public ActionForward getEntpShopForMsgAndArticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String receive_user_type = (String) dynaBean.get("receive_user_type");
		String selects = (String) dynaBean.get("selects");
		// String province = (String) dynaBean.get("province"); // 省
		String areas_ids = (String) dynaBean.get("areas_ids"); // 地区集合
		String mmt_shop_name = (String) dynaBean.get("mmt_shop_name"); // 搜索

		if ("1".equals(receive_user_type)) {// R3网点
			Pager pager = (Pager) dynaBean.get("pager");
			pager.setPageSize(50);
			dynaBean.set("not_selects", selects);
			dynaBean.set("mmt_shop_id_selects", "");
			dynaBean.set("selects", "");
			dynaBean.set("mmt_shop_name", mmt_shop_name);
			request.setAttribute("entityList", getKonkaR3MMTShopPaginatedList(mapping, form, request, response, null));

			if (StringUtils.isNotBlank(selects)) {
				dynaBean.set("mmt_shop_id_selects", selects);
				dynaBean.set("not_selects", "");
				dynaBean.set("mmt_shop_name", "");
				dynaBean.set("areas_ids", "");
				request.setAttribute("selectList",
						getKonkaR3MMTShopPaginatedList(mapping, form, request, response, null));
			}
			dynaBean.set("mmt_shop_name", mmt_shop_name);//前台回显
		} else if ("2".equals(receive_user_type) || "3".equals(receive_user_type)) {// 代理商 经销商
			// if (StringUtils.isBlank(areas_ids) && StringUtils.isNotBlank(province)) {// 没选择地区但选择了省，则取省内所有市的p_index
			// BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			// baseProvinceListFour.setPar_index(Long.valueOf(province));// 省代码
			//
			// StringBuffer sb = new StringBuffer();
			// List<BaseProvinceListFour> areaList = super.getFacade().getBaseProvinceListFourService()
			// .getBaseProvinceListFourList(baseProvinceListFour);
			// if (areaList.size() > 0) {
			// for (int i = 0; i < areaList.size(); i++) {
			// BaseProvinceListFour area = areaList.get(i);
			// if (i == 0) {
			// sb.append(area.getP_index());
			// } else {
			// sb.append(",").append(area.getP_index());
			// }
			// }
			// }
			// areas_ids = sb.toString();
			// } else {
			// super.invalidOper(request, response, "errors.parm");
			// return null;
			// }

			KonkaBranchCategory kbc = new KonkaBranchCategory();
			if ("2".equals(receive_user_type)) {// 代理商
				kbc.setCategory_id(10l);
			} else {// 经销商
				kbc.setCategory_id(20l);
			}
			kbc.getMap().put("areas_ids", areas_ids);
			kbc.getMap().put("not_selects", selects);
			kbc.getMap().put("mmt_shop_name", mmt_shop_name);
			List<KonkaBranchCategory> kbcList = getFacade().getKonkaBranchCategoryService()
					.getKonkaBranchCategoryListForMsgAndArticle(kbc);
			request.setAttribute("entityList", kbcList);

			if (StringUtils.isNotBlank(selects)) {
				KonkaBranchCategory _kbc = new KonkaBranchCategory();
				_kbc.setCategory_id(kbc.getCategory_id());
				_kbc.getMap().put("selects", selects);
				List<KonkaBranchCategory> _kbcList = getFacade().getKonkaBranchCategoryService()
						.getKonkaBranchCategoryListForMsgAndArticle(_kbc);
				request.setAttribute("selectList", _kbcList);
			}
		} else {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		return new ActionForward("/../manager/admin/SelectEntyShopByArea/listForMsgAndArticle.jsp");
	}

	public List<?> getEntpShop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String selects = (String) dynaBean.get("selects");
		String show_cur_dept_shop = (String) dynaBean.get("show_cur_dept_shop");
		String province = (String) dynaBean.get("province"); // 省
		String areas_ids = (String) dynaBean.get("areas_ids"); // 地区集合

		if (StringUtils.isNotBlank(show_cur_dept_shop)) {
			// TODO 此处可能调用不对
			KonkaR3Shop entity = super.getKonkaR3ShopForSelect(mapping, form, request, response, null);
			entity.setIs_match(1l);
			entity.getMap().put("not_null_mmt_shop_id", "true");
			if (StringUtils.isNotBlank(selects)) {
				KonkaR3Shop krs = new KonkaR3Shop();
				krs.getMap().put("selects", selects);
				krs.getRow().setFirst(0);
				krs.getRow().setCount(9999999);
				krs.getMap().put("is_assign", "true");// 获取限定条件网点分配
				request.setAttribute("selectList", getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(krs));
				entity.getMap().put("not_selects", selects);
			}

			entity.getMap().put("mmt_shop_name", key_word);
			entity.getRow().setFirst(0);
			entity.getRow().setCount(9999999);
			entity.getMap().put("is_assign", "true");// 获取限定条件网点分配
			List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
			return entityList;
		} else {
			MmtEntpShop entity = new MmtEntpShop();
			entity.getMap().put("is_wdqd", "true"); // 网点渠道管理 排除卖过康佳商品5.5W网点之后的网点
			if (StringUtils.isNotBlank(areas_ids)) { // 选择了地区集合
				entity.getMap().put("areas_ids4join", areas_ids);
			} else if (StringUtils.isNotBlank(province)) { // 选择了省
				StringBuffer sb = new StringBuffer();
				BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour(); // 市
				baseProvinceListFour.setPar_index(Long.valueOf(province));// 省代码

				// 判断当前用户是否是分公司
				PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(ui.getDept_id());
				konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				if (konkaDept != null && konkaDept.getDept_type() == 3) { // 是分公司
					baseProvinceListFour.getMap().put("many_p_index", konkaDept.getM_areas_ids());// 分公司管辖范围
				}

				List<BaseProvinceListFour> areaList = super.getFacade().getBaseProvinceListFourService()
						.getBaseProvinceListFourList(baseProvinceListFour);
				if (areaList.size() > 0) {
					for (int i = 0; i < areaList.size(); i++) {
						BaseProvinceListFour area = areaList.get(i);
						if (i == 0) {
							sb.append(area.getP_index());
						} else {
							sb.append(",").append(area.getP_index());
						}
					}
				}
				areas_ids = sb.toString();
				if (StringUtils.isNotBlank(sb.toString())) {
					entity.getMap().put("areas_ids4join", sb.toString());
				} else
					entity.getMap().put("areas_ids4join", "000000");
			}
			dynaBean.set("areas_ids", areas_ids);
			if (StringUtils.isNotBlank(selects)) {
				entity.getMap().put("selects", selects);
				request.setAttribute("selectList",
						super.getFacade().getMmtEntpShopService().getMmtEntpShopList(entity));
				entity.getMap().put("selects", null);
				entity.getMap().put("not_selects", selects);
			}
			if (key_word != null) {
				entity.getMap().put("key_word", key_word);
			}
			entity.getRow().setCount(500);
			List<MmtEntpShop> entityList = getFacade().getMmtEntpShopService().getMmtEntpShopList(entity);

			return entityList;
		}
	}
}
