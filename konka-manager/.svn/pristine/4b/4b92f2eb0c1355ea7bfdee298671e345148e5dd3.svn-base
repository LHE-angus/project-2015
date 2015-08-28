package com.ebiz.mmt.web.struts.manager.admin;

import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Cheng,Bing
 * @version 2011-10-15
 */
public class KonkaBranchShopGSiteAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return mapping.findForward("list");
	}

	public void ajaxGetPIndexNamesStr(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String p_index = (String) dynaBean.get("p_index");
		p_index = GenericValidator.isLong(p_index) ? p_index : "0";

		String rendText = getPIndexName(p_index, " ");
		if (null == rendText) {
			rendText = "0";
		}
		super.render(response, rendText, "text/x-json;charset=UTF-8");
		return;
	}

	public void ajaxGetEntpShopJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String search_txt = (String) dynaBean.get("search_txt");

		String lat_max = (String) dynaBean.get("lat_max");
		String lng_max = (String) dynaBean.get("lng_max");
		String lat_min = (String) dynaBean.get("lat_min");
		String lng_min = (String) dynaBean.get("lng_min");

		String is_rural = (String) dynaBean.get("is_rural");
		String is_otn = (String) dynaBean.get("is_otn");
		String is_sall = (String) dynaBean.get("is_sall");
		String is_callb = (String) dynaBean.get("is_callb");
		String is_maint = (String) dynaBean.get("is_maint");

		String firstRow = (String) dynaBean.get("firstRow");
		String pageSize = (String) dynaBean.get("pageSize");

		if (StringUtils.isNotBlank(search_txt)) {
			search_txt = URLDecoder.decode(search_txt, Constants.SYS_ENCODING);
		}

		EntpShop entpShop = new EntpShop();
		entpShop.setState(0);
		entpShop.setIs_auth(1);// 买卖提认证通过的商铺

		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");

		entpShop.setP_index(null);
		if (StringUtils.isNotBlank(country)) {
			entpShop.getMap().put("p_index_par", country);
		} else if (StringUtils.isNotBlank(city)) {
			entpShop.getMap().put("p_index_par", city);
		} else if (StringUtils.isNotBlank(province)) {
			entpShop.getMap().put("p_index_par", province);
		}

		if (GenericValidator.isInt(is_rural)) {
			entpShop.setIs_rural(Integer.valueOf(is_rural));
		}
		if (GenericValidator.isInt(is_otn)) {
			entpShop.setIs_otn(Integer.valueOf(is_otn));
		}
		if (GenericValidator.isInt(is_sall)) {
			entpShop.setIs_sall(Integer.valueOf(is_sall));
		}
		if (GenericValidator.isInt(is_callb)) {
			entpShop.setIs_callb(Integer.valueOf(is_callb));
		}
		if (GenericValidator.isInt(is_maint)) {
			entpShop.setIs_maint(Integer.valueOf(is_maint));
		}

		entpShop.getMap().put("g_lng_ge", lng_min);
		entpShop.getMap().put("g_lng_le", lng_max);
		entpShop.getMap().put("g_lat_ge", lat_min);
		entpShop.getMap().put("g_lat_le", lat_max);
		entpShop.getMap().put("search_txt_like", search_txt);

		//entpShop.getMap().put("otherDBUser", "chea_fill");	
		
		// 全网点内查找(直营网点)		
		entpShop.getMap().put("shop_of_konka", "konka");	
	    // 直营网点 个数
		Long count = getFacade().getEntpShopService().getEntpShopCount(entpShop);
		entpShop.getRow().setFirst(Integer.valueOf(firstRow) - 1);
		entpShop.getRow().setCount(Integer.valueOf(pageSize));
		List<EntpShop> entpShopList = getFacade().getEntpShopService().getEntpShopPaginatedList(entpShop);
				
		StringBuffer jsonBuffer = new StringBuffer();
		for (EntpShop es : entpShopList) {
			jsonBuffer.append("{");
			jsonBuffer.append("\"shop_id\":\"").append(es.getShop_id()).append("\",");
			jsonBuffer.append("\"shop_name\":\"").append(replaceSpecialCharacter(es.getShop_name(),"\t|\r|\n")).append("\",");
			jsonBuffer.append("\"online_qq\":\"").append(es.getOnline_qq()).append("\",");
			jsonBuffer.append("\"is_rural\":\"").append(es.getIs_rural()).append("\",");
			jsonBuffer.append("\"is_otn\":\"").append(es.getIs_otn()).append("\",");
			jsonBuffer.append("\"is_sall\":\"").append(es.getIs_sall()).append("\",");
			jsonBuffer.append("\"is_callb\":\"").append(es.getIs_callb()).append("\",");
			jsonBuffer.append("\"shop_level\":\"").append(es.getShop_level()).append("\",");
			jsonBuffer.append("\"is_maint\":\"").append(es.getIs_maint()).append("\",");
			jsonBuffer.append("\"g_is_audit\":\"").append(es.getG_is_audit()).append("\",");
			jsonBuffer.append("\"g_lat\":\"").append(es.getG_lat()).append("\",");
			jsonBuffer.append("\"g_lng\":\"").append(es.getG_lng()).append("\"");
			
			// 直营网点
		    jsonBuffer.append(",\"is_R3Shop\":\"").append(1).append("\"");
	
			jsonBuffer.append("},");
		}
		String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
		StringBuffer json = new StringBuffer("{");
		json.append("\"list\" : [").append(listStr).append("],");
		json.append("\"count\":\"").append(count).append("\"");
		json.append("}");
		logger.info("JSON Output : [{}]", json.toString());

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return;
	}
	
	public void ajaxGetEntpShopInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    //DynaBean dynaBean = (DynaBean) form;
		//String shop_id = (String) dynaBean.get("shop_id");

		// 判断商铺 ID
		StringBuffer sb = new StringBuffer("{'result':'false'}");
		super.render(response, sb.toString(), "text/x-json;charset=UTF-8");
		return;
	}
	
	/* 
	 * 替换特殊字符  \t \r \n 等 
	 *
	 * */
	public String replaceSpecialCharacter(String str,String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}
}
