package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 获取代理商列表
 * 
 * @author Wang Hao
 */
public class SelectAgentsListAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String keyword = (String) dynaBean.get("keyword");

		Pager pager = (Pager) dynaBean.get("pager");
		//是否为代理商
		String no_dls = (String) dynaBean.get("no_dls");
		
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3Shop entity = new KonkaR3Shop();
		if(StringUtils.isBlank(no_dls)){
			entity.getMap().put("is_agents", "true"); // 代理商
		}
		entity.getMap().put("keyword", keyword);

		// 取当前用户所在区域		
//		String area_limit = (String) dynaBean.get("area_limit");
//		if(area_limit != null && "1".equals(area_limit)){
//			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
//			KonkaDept konkaDept = getSuperiorForDeptType(ui.getDept_id(),3);	
//			entity.setBranch_area_name(konkaDept.getDept_name());
//		}
		//根据网点分配取登录人可以管理的代理商
		
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L) {
				role_id_ge_20 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}
		
		if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
			entity.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
			request.setAttribute("role_type", 1);
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			entity.getMap().put("dept_id", -1);
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		
		Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
}