package com.ebiz.mmt.web.struts.shop.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcGiftOrde;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcGiftOrdeAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if(ecUser.getUser_type().intValue()==1){
			String shop = (String)session.getAttribute("shop");
			if(!"1".equals(shop)){
				String ctx = super.getCtxPath(request);
				String url=ctx+"/shop/center/Skip.do";
				response.sendRedirect(url);
				return null; 
			}
		} 
		Pager pager = (Pager) dynaBean.get("pager"); 
		String start_date=(String) dynaBean.get("start_date");
		String end_date=(String) dynaBean.get("end_date");
		EcGiftOrde entity=new EcGiftOrde(); 		 
		 
		entity.setOrder_user_id(ecUser.getId()); 
		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);	
		
		Long recordCount=super.getFacade().getEcGiftOrdeService().getEcGiftOrdeCount(entity);
				
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcGiftOrde> entityList=super.getFacade().getEcGiftOrdeService().getEcGiftOrdePaginatedList(entity);				
		request.setAttribute("entityList", entityList);
	  
		return mapping.findForward("list");
	} 
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		EcGiftOrde entity = new EcGiftOrde();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		entity = super.getFacade().getEcGiftOrdeService().getEcGiftOrde(entity); 
		super.copyProperties(form, entity);

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getBuyer_p_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);
			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		return mapping.findForward("view");
	}
}
