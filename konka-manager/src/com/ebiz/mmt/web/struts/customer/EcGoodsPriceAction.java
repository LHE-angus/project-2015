package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Pan,Gang
 * @version 2013-09-26
 */
public class EcGoodsPriceAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form; 
		String goods_id = (String) dynaBean.get("goods_id");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		//取dept_id
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop); 
		KonkaDept dept = new KonkaDept();
		if (null != shop && shop.getBranch_area_name_2() != null) {
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept); 
		}
		
		EcGoodsPrice entity = new EcGoodsPrice();
		if (GenericValidator.isLong(goods_id)&&dept!=null) {	
			entity.setGoods_id(new Long(goods_id));
			entity.setC_id(user.getCust_id()); 
			entity.setDept_id(dept.getDept_id());
			entity.setOwn_sys(new Integer(2)); 
			entity=super.getFacade().getEcGoodsPriceService().getEcGoodsPriceForCoustomerPrice(entity);
			if(entity==null){
				super.renderJavaScript(response, "alert('对不起，该产品分公司结算价格未维护！');history.back();");
				return null; 
			}
			super.copyProperties(form, entity);	       
		}else{
			return null;
		}
		return mapping.findForward("list");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		String goods_id = (String) dynaBean.get("goods_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String original_price = (String) dynaBean.get("original_price");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		//取dept_id
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop); 
		KonkaDept dept = new KonkaDept();
		if (null != shop && shop.getBranch_area_name_2() != null) {
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept); 
		}
		
		EcGoodsPrice entity = new EcGoodsPrice();
		if (GenericValidator.isLong(goods_id)&&dept!=null) {	
			entity.setGoods_id(new Long(goods_id));
			entity.setC_id(user.getCust_id()); 
			entity.setDept_id(dept.getDept_id());
			entity.setOwn_sys(new Integer(2)); 
			entity=super.getFacade().getEcGoodsPriceService().getEcGoodsPriceForCoustomerPrice(entity);
			if(entity==null&&original_price!=null){
				super.renderJavaScript(response, "alert('对不起，未找到该产品结算价格信息！');history.back();");
				return null;  
			}else{
				entity.setOriginal_price(new BigDecimal(original_price));
				if(entity.getC_id()!=null){
					entity.setC_id(user.getCust_id());
					super.getFacade().getEcGoodsPriceService().modifyEcGoodsPrice(entity);
				}else{
					entity.setId(null);
					entity.setC_id(user.getCust_id()); 
					entity.setPrice_type(new Integer(4));
					super.getFacade().getEcGoodsPriceService().createEcGoodsPrice(entity);
				}
				super.renderJavaScript(response, "alert('信息修改成功！');location.href='"+super.getCtxPath(request)+"/customer/manager/PdShow.do?own_sys=2&mod_id="+mod_id+"';");
				return null;  
			} 
		}else{
			return null;
		} 
	}
 
}
