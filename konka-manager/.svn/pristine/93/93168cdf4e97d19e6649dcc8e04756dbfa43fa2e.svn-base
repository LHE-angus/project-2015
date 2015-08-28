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
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-09-30
 * @version 2011-10-19 (变更)
 */
public class KonkaShopDevelopCountAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String s_date=(String)dynaBean.get("s_date");
		String e_date=(String)dynaBean.get("e_date");
			
		KonkaShopDevelop  entity = new KonkaShopDevelop();
		//开始开拓时间
		if (StringUtils.isNotBlank(s_date)) {
			entity.getMap().put("s_date", s_date);
		}

		entity.setDevelop_status(Long.valueOf(0));
		Long recordCount0 = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(entity);
		request.setAttribute("recordCount0", recordCount0);
	
		entity.setDevelop_status(Long.valueOf(1));
		Long recordCount1 = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(entity);
		request.setAttribute("recordCount1", recordCount1);
	
		//结束开拓时间
		if (StringUtils.isNotBlank(e_date)) {
		    entity.getMap().put("e_date", e_date);
		}
		
		entity.getMap().put("is_r3", "true"); //
		entity.setDevelop_status(Long.valueOf(2));
	    Long recordCount3=super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(entity);
	    request.setAttribute("recordCount3", recordCount3);
 
	    entity.getMap().put("is_r3", null);
	    entity.getMap().put("is_jxs", "true"); //
	    entity.setDevelop_status(Long.valueOf(2));
	    Long recordCount4=super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(entity);
	    request.setAttribute("recordCount4", recordCount4);
		
		return mapping.findForward("list");
	}
	
	public ActionForward itemList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String develop_status=(String) dynaBean.get("develop_status");
		String status=(String) dynaBean.get("status");
		String s_date=(String)dynaBean.get("s_date");
		String e_date=(String)dynaBean.get("e_date");
		Pager pager = (Pager) dynaBean.get("pager");
		KonkaShopDevelop  entity = new KonkaShopDevelop(); 
		
		// 网点名
		if (StringUtils.isNotBlank(shop_name_like)) {
			entity.getMap().put("shop_name_like", shop_name_like);
		}
		//开始开拓时间
		if (StringUtils.isNotBlank(s_date)) {
			entity.getMap().put("s_date", s_date);
		}
		//结束开拓时间
		if("2".equals(develop_status)){
			if (StringUtils.isNotBlank(e_date)) {
			    entity.getMap().put("e_date", e_date);
			}			
		}

		if (StringUtils.isNotBlank(develop_status)) {
			entity.setDevelop_status(Long.valueOf(develop_status));
		}
		if (status != null && status != "") {
			if (StringUtils.equals(status, "1")) {
				entity.getMap().put("is_r3", "true");
			}
			if (StringUtils.equals(status, "2")) {
				entity.getMap().put("is_jxs", "true");
			}
		}
	
		Long recordCount = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaShopDevelop> shopDevelopList = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopPaginatedList(entity);

		request.setAttribute("entityList", shopDevelopList);
		return new ActionForward("/admin/KonkaShopDevelopCount/itemList.jsp");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(shop_id)) {
			saveError(request, "errors.long", new String[] { shop_id });
			return mapping.findForward("itemList");
		}

		KonkaShopDevelop  entity = new KonkaShopDevelop(); 
		entity.setId(Long.valueOf(shop_id));

		entity = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelop(entity);
	
		request.setAttribute("entity", entity);
		
		KonkaDept kd=new KonkaDept();
		kd.setDept_id(entity.getDept_id());
		kd=super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		request.setAttribute("kd", kd);
		
		if(entity.getR3_id() != null){
			KonkaR3Shop r3=new KonkaR3Shop();
			r3.setId(entity.getR3_id());
			r3=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3);
			request.setAttribute("r3", r3);
		}
		
		if(entity.getJxs_id() != null){
			KonkaR3Shop ked = new KonkaR3Shop();
			ked.setId(entity.getJxs_id());
			ked=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ked);
			request.setAttribute("ked", ked);
		}
		
		return mapping.findForward("view");
	}
}
