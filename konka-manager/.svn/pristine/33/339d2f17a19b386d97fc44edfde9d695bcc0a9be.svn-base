package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author hu hao
 * @version 2012-1-11
 */
public class KonkaXxCustomerInfoAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_sex = (String) dynaBean.get("buyer_sex");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		PeProdUser user_id = super.getSessionUserInfo(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxSellBill entity = new KonkaXxSellBill();

		if (StringUtils.isNotBlank(town)) {
			entity.getMap().put("p_index_par", town);
		} else if (StringUtils.isNotBlank(country)) {
			entity.getMap().put("p_index_par", country);
		} else if (StringUtils.isNotBlank(city)) {
			entity.getMap().put("p_index_par", city);
		} else if (StringUtils.isNotBlank(province)) {
			entity.getMap().put("p_index_par", province);
		}

		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_sex)) {
			entity.setBuyer_sex(Integer.valueOf(buyer_sex));
		}
		
		// 判断查询权限 start
		Boolean role_id_eq_400 = false;
		Boolean role_id_300_btw_390 = false;
		
		List<PeRoleUser> peRoleInfoList = getPeRoleList(user_id.getId());
		if(peRoleInfoList.size() > 0){
			for(PeRoleUser temp:peRoleInfoList){
				if(temp.getRole_id() == 400){
					role_id_eq_400 = true;
				}
				if(temp.getRole_id() >= 300 && temp.getRole_id()<390){
					role_id_300_btw_390 = true;
				}
			}
		}
		
		if (role_id_eq_400) { // 专卖店管理员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_id.getId());
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			}
		} 
		if(role_id_300_btw_390){ // 分公司管理员
			entity.setDept_id(getKonkaDeptForFgs(user_id.getDept_id()).getDept_id());
		}
		//end

		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillPaginatedList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (KonkaXxSellBill temp : entityList) {
				if (temp.getBuyer_p_index() != null) {

					BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
					baseProvinceListFour.setP_index(temp.getBuyer_p_index());
					List<BaseProvinceListFour> baseProvinceListFoursList = super.getFacade()
							.getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceListFour);
					String p_name = baseProvinceListFoursList.get(0).getFull_name();
					temp.getMap().put("p_index_name", p_name);
				}
			}
		}
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

}
