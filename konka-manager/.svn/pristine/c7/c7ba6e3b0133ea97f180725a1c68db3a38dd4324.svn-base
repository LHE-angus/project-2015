package com.ebiz.mmt.web.struts.m.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.mmt.domain.KonkaSellDetails;

/**
 * @author Wang Hao
 */
public class SalesReportsAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}

	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.getMap().put("cxy_id_eq", super.getSessionUserInfo(request).getId());
		List<KonkaR3Shop> konkaR3ShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
		
		request.setAttribute("baseList", konkaR3ShopList);
		request.setAttribute("kpmList", getDeptLinkProduct(request, response,null,null,null,null));
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// 网点
		String id = (String) dynaBean.get("select-choice-1");
		// 型号
		String pd_id = (String) dynaBean.get("select-choice-2");
		// 单价
		String price = (String) dynaBean.get("sales_price");
		// 销量
		String count = (String) dynaBean.get("sales_count");
		//时间
		String date = (String) dynaBean.get("date");
		
		KonkaSell konkaSell =new KonkaSell();
		KonkaR3Shop  shop =new KonkaR3Shop();
		shop.setId(new Long(id));
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		
		String title =  shop.getCustomer_name() + "   " + date;
		konkaSell.setTitle(title);
		konkaSell.setCus_sn(shop.getR3_code());
		konkaSell.setCus_name(shop.getCustomer_name());
		konkaSell.setState(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		if(StringUtils.isNotBlank(date)){
			konkaSell.setSell_date(simpleDateFormat.parse(date));
		}
		Long s_id = super.getFacade().getKonkaSellService().createKonkaSell(konkaSell);
		
		KonkaSellDetails konkaSellDetails = new KonkaSellDetails();
		konkaSellDetails.setPd_id(new Long(pd_id));
		konkaSellDetails.setS_id(s_id);
		konkaSellDetails.setSell_count(new Long(count));
		konkaSellDetails.setSell_money(new BigDecimal(price));
		super.getFacade().getKonkaSellDetailsService().createKonkaSellDetails(konkaSellDetails);
		
		super.renderText(response, "success");
		return null;
	}
}
