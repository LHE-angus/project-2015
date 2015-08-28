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

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class SelectKonkaEntpShopAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
//		String province= (String) dynaBean.get("province");
//		String city= (String) dynaBean.get("city");
//		String country = (String) dynaBean.get("country");
		String keyword = (String) dynaBean.get("keyword");
		String link_user_like = (String) dynaBean.get("link_user_like");
		String link_phone_like = (String) dynaBean.get("link_phone_like");
		
		Pager pager = (Pager) dynaBean.get("pager");
		
		PeProdUser ui = (PeProdUser)request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaDept dept = new KonkaDept();
		dept.setDept_id(ui.getDept_id());
		dept=getFacade().getKonkaDeptService().getKonkaDept(dept);
		

		MmtEntpShop entity = new MmtEntpShop();
		
//		if (GenericValidator.isLong(country)) {
//			entity.getMap().put("p_index", new Long(country));
//		} else if (GenericValidator.isLong(city)) {
//			entity.getMap().put("p_index", new Long(city));
//		} else if (GenericValidator.isLong(province)) {
//			entity.getMap().put("p_index", new Long(province));
//		}

		if (keyword != null) {
			entity.getMap().put("key_word",keyword);
		}
		entity.getMap().put("link_user_like", link_user_like);
		entity.getMap().put("link_phone_like", link_phone_like);
		
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30_le_59 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_30_le_59 = true;
			}
		}
		
		if(role_id_ge_30_le_59){
			if(StringUtils.isNotBlank(dept.getM_areas_ids())){
				entity.getMap().put("m_areas_ids", dept.getM_areas_ids());
			}else{
				entity.getMap().put("m_areas_ids", 1);
			}
		}

		Long recordCount = super.getFacade().getMmtEntpShopService().getMmtEntpShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		

		List<MmtEntpShop> entityList = getFacade().getMmtEntpShopService().getMmtEntpShopPaginatedListForSelect(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			EntpShop entity = new EntpShop();
			entity.setShop_id(new Long(id));
			entity = getFacade().getEntpShopService().getEntpShop(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			BaseProvinceList baseProvinceList = new BaseProvinceList();
			baseProvinceList.setP_index(Long.valueOf(entity.getP_index()));
			List<BaseProvinceList> baseProvinceListList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListParentList(baseProvinceList);

			request.setAttribute("baseProvinceListList", baseProvinceListList);

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}