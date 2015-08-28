package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
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
import com.ebiz.mmt.web.struts.m.admin.MobileBaseAction;

public class CustomerManageAction extends MobileBaseAction {

	SimpleDateFormat _ft = new SimpleDateFormat("yyyy-MM-dd");

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
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		Long count = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(
				entity);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		String outStr = "{htmlData:'";
		if (count > 0) {
			List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopPaginatedList(entity);
			super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

			outStr += "<ul data-role=\"listview\" data-inset=\"false\" data-theme=\"b\" data-divider-theme=\"a\">";
			for (KonkaR3Shop _k : entityList) {
				outStr += "<li data-role=\"list-divider\">客户名称："
						+ _k.getCustomer_name();
				if (_k.getIs_match() == 1) {
					outStr += "<span class=\"ui-li-count\">已匹配</span>";
					outStr += "</li>";
					outStr += "<li>";
					outStr += "<a data-ajax=\"false\" href=\"CustomerManage_View.html?id="
							+ _k.getId() + "\">";
					outStr += "<p style=\"padding-bottom:5px;padding-top:5px;\">R3编码：<strong>"
							+ _k.getR3_code() + "</strong></p>";
					outStr += "<p>合并编码：<strong>"
							+ ((StringUtils.isEmpty(_k.getMerge_code_2010())) ? ""
									: _k.getMerge_code_2010())
							+ "</strong></p>";
					outStr += "</a>";
					outStr += "</li>";
				} else if (_k.getIs_match() == 0) {
					outStr += "<span class=\"ui-li-count\">未匹配</span>";
					outStr += "</li>";
					outStr += "<li>";
					outStr += "<p style=\"padding-bottom:5px;padding-top:5px;\">R3编码：<strong>"
							+ _k.getR3_code() + "</strong></p>";
					outStr += "</li>";
				}
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
		createMobileSysOperLog(request, "KonkaR3Shop", 711010l, "711010", "查询",
				"手机端-客户管理查询-列表");
		return null;
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

		String outStr = "{htmlData:'";
		if (entity != null) {
			String mmt_shop_id = entity.getMap().get("mmt_shop_id").toString();

			EntpShop entpShop = new EntpShop();
			entpShop.setShop_id(Long.valueOf(mmt_shop_id));
			entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);
			String baseProvince = "";
			if (entpShop != null) {
				BaseProvinceList baseProvinceList = new BaseProvinceList();
				baseProvinceList
						.setP_index(Long.valueOf(entpShop.getP_index()));
				List<BaseProvinceList> baseProvinceListList = super.getFacade()
						.getBaseProvinceListService()
						.getBaseProvinceListParentList(baseProvinceList);

				for (BaseProvinceList _b : baseProvinceListList) {
					baseProvince += _b.getP_name() + " ";
				}
			}

			request.setAttribute("entpShop", entpShop);
			outStr += "<ul data-role=\"listview\" data-inset=\"false\" data-theme=\"b\" data-divider-theme=\"a\">"
					+ "<li data-role=\"list-divider\">R3网点信息</li>"
					+ "<li><span>R3网点名称（客户名称）："
					+ entity.getCustomer_name()
					+ "</span></li>"
					+ "<li><span>所在区域名称："
					+ entity.getArea_name()
					+ "</span></li>"
					+ "<li><span>分公司所在地名称："
					+ entity.getBranch_area_name()
					+ "</span></li>"
					+ "<li><span>客户类型："
					+ entity.getCustomer_type()
					+ "</span></li>"
					+ "<li><span>交易状态："
					+ (entity.getStatus() == 1 ? "有交易"
							: (entity.getStatus() == 2 ? "无交易" : ""))
					+ "</span></li>"
					+ "<li><span>R3编码："
					+ entity.getR3_code()
					+ "</span></li>"
					+ "<li><span>经办名称："
					+ entity.getHandle_name()
					+ "</span></li>"
					+ "<li><span>合并客户编码："
					+ entity.getCustomer_code()
					+ "</span></li>"
					+ "<li><span>备注："
					+ entity.getR3_desc()
					+ "</span></li>"
					+ "<li><span>2010合并编码："
					+ entity.getR3_desc()
					+ "</span></li>"
					+ "<li><span>导入日期："
					+ _ft.format(entity.getImport_date())
					+ "</span></li>"
					+ "<li><span>导入人姓名："
					+ entity.getMap().get("import_user_name")
					+ "</span></li>"
					+ "<li><span>备注："
					+ entity.getR3_desc()
					+ "</span></li>"
					+ "<li data-role=\"list-divider\">匹配买卖提网点信息</li>"
					+ "<li><span>匹配买卖提网点名称："
					+ entity.getMap().get("mmt_shop_name")
					+ "</span></li>"
					+ "<li><span>地区邮编："
					+ entpShop.getPost_code()
					+ "</span></li>"
					+ "<li><span>所属地区："
					+ baseProvince
					+ "</span></li>"
					+ "<li><span>网点地址："
					+ entpShop.getStreet_addr()
					+ "</span></li>"
					+ "<li><span>联系人："
					+ entpShop.getLink_user()
					+ "</span></li>"
					+ "<li><span>联系电话："
					+ entpShop.getLink_phone()
					+ "</span></li>"
					+ "<li><span>网点级别："
					+ (entpShop.getShop_level() == 0 ? "普通网点" : (entpShop
							.getShop_level() == 1 ? "铜牌网点" : (entpShop
							.getShop_level() == 2 ? "金牌网点" : "")))
					+ "</span></li>" + "</ul>";
		} else if (entity == null) {
			outStr += "数据错误！此网点未匹配上买卖提网点，请解除匹配后重新匹配买卖提网点。";
		}
		outStr += "'}";
		outStr = outStr.replace("null", "");
		super.renderText(response, outStr);
		// //System.out.print(outStr);

		// 写日志
		createMobileSysOperLog(request, "KonkaR3Shop", Long.valueOf(id),
				"711010", "查询", "手机端-客户管理查询-详情");
		return null;
	}
}
