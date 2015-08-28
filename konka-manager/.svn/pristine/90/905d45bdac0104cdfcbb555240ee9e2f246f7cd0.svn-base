package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 代理商网点 网点经营分析
 * 
 * @author Wang Hao
 */
public class AgentsOperateAnalysisAction extends BaseAction {

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
		String r3_code = (String) dynaBean.get("r3_code");
		String shop_id = (String) dynaBean.get("shop_id");

		KonkaR3Shop R3Shop = getKonkaR3ShopForSelect(mapping, form, request, response, null);
		R3Shop.setIs_match(1l);
		// R3Shop.getMap().put("is_agents", true);

		if (StringUtils.isNotEmpty(shop_id)) {
			R3Shop.getMap().put("mmt_shop_id", shop_id);
			R3Shop.getMap().put("is_agents", 1);
			R3Shop.getMap().put("is_assign", 1);
		} else {
			R3Shop.getMap().put("key", 1);

			PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(userInfo.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_30 = false;
			for (PeRoleUser peRoleUser: peRoleUserList) {
				if (peRoleUser.getRole_id() == 30L) {
					role_id_eq_30 = true;
					break;
				}
			}

			KonkaDept dept = new KonkaDept();

			if (role_id_eq_30) { // 分公司登陆
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				if (StringUtils.isNotBlank(dept.getM_areas_names())) {
					R3Shop.getMap().put("branch_name_like", dept.getM_areas_names());
				} else {
					R3Shop.getMap().put("branch_name_like", "1");
				}
				R3Shop.getMap().put("branch_area_name_like", null);
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
			if (r3_code != null) {
				R3Shop.setR3_code(r3_code);
			}
		}
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(R3Shop);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		R3Shop.getRow().setFirst(pager.getFirstRow());
		R3Shop.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(R3Shop);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));

		return mapping.findForward("list");
	}
}
