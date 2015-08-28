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

import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.web.struts.BaseAction;

public class SelectPePdModelAction extends BaseAction {

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
		String key_word = (String) dynaBean.get("key_word");
		String cls_id = (String) dynaBean.get("cls_id");
		String selects = (String) dynaBean.get("selects");
		String selected_pd_ids = (String) dynaBean.get("selected_pd_ids");
		String cus_sn = (String) dynaBean.get("cus_sn");
		String stockOrSell = (String) dynaBean.get("stockOrSell");
	
//		BasePdClazz cls = new BasePdClazz();
		request.setAttribute("clsList", super.getBasePdClazzList());
		
		PePdModel entity = new PePdModel();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(selects)) {
			entity.getMap().put("selects", selects);
			entity.getMap().put("OrderByMd", 1);
			request.setAttribute("selectList", super.getFacade().getPePdModelService().getPePdModelList(entity));
		}
		
		
		if(StringUtils.isNotBlank(stockOrSell)&&stockOrSell.equals("stock")){//初始库存调用
			KonkaStockDetails stock = new KonkaStockDetails();
			stock.setR3_code(cus_sn);
			List<KonkaStockDetails> stockList = getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(stock);
			if(0 != stockList.size()){
				String[] pd_ids=new String[stockList.size()];
		    	for(int i = 0 ; i<stockList.size() ; i ++){
		    		pd_ids[i] = stockList.get(i).getPd_id().toString();
		    	}
		    	if(StringUtils.isNotBlank(selects)){
		    		selects = selects +","+ StringUtils.join(pd_ids, ",");
		    	}else if(StringUtils.isBlank(selects)){
		    		selects = StringUtils.join(pd_ids, ",");
		    	}
			}
		}
		
//		PeProdUser ui = new PeProdUser();// 当前用户信息
//		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
//		if(StringUtils.isNotBlank(stockOrSell)&&stockOrSell.equals("sell")){
	//		KonkaSell sell = new KonkaSell();
	//		sell.setCus_sn(cus_sn);
	//		List<KonkaSell> sellList = getFacade().getKonkaSellService().getKonkaSellList(sell);
	//		
	//		if(0!= sellList.size()){
	//			KonkaSellDetails sellDetails = new KonkaSellDetails();
	//			sellDetails.setS_id(sellList.get(0).getS_id());
	//		    sellDetails.getMap().put("state",1);
	//			List<KonkaSellDetails> sellDetailsList = getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(sellDetails);
	//		    if(0!= sellDetailsList.size()){
	//		    	String[] pd_ids=new String[sellDetailsList.size()];
	//		    	for(int i = 0 ; i<sellDetailsList.size() ; i ++){
	//		    		pd_ids[i] = sellDetailsList.get(i).getPd_id().toString();
	//		    	}
	//		    	selects = selects + StringUtils.join(pd_ids, ",");
	//		    }
	//		}
//		}
		
//		entity.getMap().put("user_id", ui.getId());
		if(StringUtils.isNotBlank(selected_pd_ids)){
			if(StringUtils.isNotBlank(selects)){
				selects = selects+","+selected_pd_ids;
			}else{
				selects = selected_pd_ids;
			}
		}
		request.getSession().setAttribute("selects", selects);
		
		request.setAttribute("entityList", super.getDeptLinkProduct(request, response, selects, key_word, cls_id, null));
				
		return mapping.findForward("list");
	}
		
	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String cls_id = (String) dynaBean.get("cls_id");
		String selects = (String)request.getSession().getAttribute("selects");

		StringBuffer sb = new StringBuffer("[");

		List<PePdModel> entityList = super.getDeptLinkProduct(request, response, selects, key_word, cls_id,null);
		for (PePdModel t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getPd_id()) + "\",");
			sb.append("\"name\":\""+ StringEscapeUtils.escapeJavaScript(t.getMd_name()) + "\"},");
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}
}
