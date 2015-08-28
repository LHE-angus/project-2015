package com.ebiz.mmt.web.struts.customer;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.mmt.domain.GiftInfo;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleType;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-12-31
 */
public class IndexsAction extends BaseAction {

	/**
	 * 客户管理首页查询数据
	 * @author Liang Houen
	 * @since 2015-7-8
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initCustomerIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
		//当前用户信息
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if(null!=shop){
			//有效门店数量
			KonkaR3Store store = new KonkaR3Store();
			store.setR3_code(shop.getR3_code());
			store.setIs_del(0);
			allmap.put("store_num", super.getFacade().getKonkaR3StoreService().getKonkaR3StoreCount(store));
			
			//网点数量
			JBasePartner jbp = new JBasePartner();
			jbp.setC_id(shop.getId());
			jbp.setPartner_type(1);
			jbp.setPartner_obj(1);
			jbp.setIs_del(0);
			Long a_num = super.getFacade().getJBasePartnerService().getJBasePartnerCount(jbp);
			allmap.put("agent_num", a_num);
			
			if(a_num>0){
				StringBuffer sb = new StringBuffer("其中：");
				String level = "";
				for(int i=1; i<5; i++){
					switch (i) {
					case 1:
						level="一级";
						break;
					case 2:
						level="二级";
						break;
					case 3:
						level="三级";
						break;
					case 4:
						level="四级";
						break;
					default:
						break;
					}
					Long temp = super.getFacade().getJBasePartnerService().getAgentsByLevel(shop.getId(),Long.parseLong(i+""));
					if(temp>0){
						sb.append(level+"网点&nbsp;"+temp+"&nbsp;个，");
					}
				}
				allmap.put("agent_details", sb.substring(0, sb.length()-1)+"。");
			}
			
			//供应商数量
			JBasePartner partner = new JBasePartner();
			partner.setC_id(shop.getId());
			partner.setPartner_type(0);
			partner.setIs_del(0);
			allmap.put("partner_num", super.getFacade().getJBasePartnerService().getJBasePartnerCount(partner));
			
			//顾客数量
			ConsumerInfo con = new ConsumerInfo();
			con.setCust_id(shop.getId());
			con.setIs_del(0);
			allmap.put("consumer_num", super.getFacade().getConsumerInfoService().getConsumerInfoCount(con));
		}
		
		//资讯列表
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		entity.getMap().put("article_mod_id_in", new Long[] { 960110L, 960112L });
		entity.setIs_del(0L);
		entity.setStates(1L);

		entity.getMap().put("article_type_id_in", new Long[] { 1030L, 1060L, 1070L, -1L });
		entity.getMap().put("cust_id", user.getCust_id().toString());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(5);
		List<KonkaPeArticleInfo> entityList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedList(entity);
		
		if(null!=entityList){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			for (KonkaPeArticleInfo konkaPeArticleInfo : entityList) {
				if (null!=konkaPeArticleInfo.getArticle_type_id()) {
					KonkaPeArticleType kt = new KonkaPeArticleType();
					kt.setId(konkaPeArticleInfo.getArticle_type_id());
					kt = super.getFacade().getKonkaPeArticleTypeService().getKonkaPeArticleType(kt);
					if (kt != null) {
						konkaPeArticleInfo.getMap().put("type_name", kt.getType_name());
					} else {
						konkaPeArticleInfo.getMap().put("type_name", "通知");
					}
					
				}
				
				konkaPeArticleInfo.getMap().put("pub_date", dateformat.format(konkaPeArticleInfo.getPub_date()));
			}
		}
		allmap.put("GroupPeInfoList", entityList);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 客户端-销售管理首页月度销售数据查询
	 * @author Liang Houen
	 * @since 2015-7-17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward salesInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap allmap = new HashMap();
		
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		
		/**Begin  查询月日度销售数据*/
		HashMap map = new HashMap();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String now = dateformat.format(calendar.getTime());//当前日期
		String[] arrs = now.split("-");
		allmap.put("year", arrs[0]);
		allmap.put("month", Integer.parseInt(arrs[1]));
		map.put("I_C_ID", ui.getCust_id().toString());
		map.put("I_YEAR", arrs[0]);
		map.put("I_MONTH", arrs[1]);
		map.put("I_R3_CODE", shop.getR3_code());
		List<HashMap> result = super.getFacade().getJBillService().getSaleInfoByDate(map);
		
		StringBuffer days = new StringBuffer();
		StringBuffer moneys = new StringBuffer();
		StringBuffer prices = new StringBuffer();
		for(int i=0; i<result.size(); i++){
			days.append(result.get(i).get("SALE_DATE")+",");
			moneys.append(result.get(i).get("SALE_MONEY")+",");
			prices.append(result.get(i).get("SALE_AVERAGE")+",");
		}
		allmap.put("days", "["+days.toString().substring(0, days.length()-1)+"]");
		allmap.put("moneys", "["+moneys.toString().substring(0, moneys.length()-1)+"]");
		allmap.put("prices", "["+prices.toString().substring(0, prices.length()-1)+"]");
		/**end  查询月日度销售数据*/
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 销售管理首页任务完成情况
	 * @author Liang Houen
	 * @since 2015-7-21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTargetAndData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
		//当前用户信息
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		/**Begin  查询首页顶部任务完成情况数据*/
		JBill jbill = new JBill();
		jbill.setC_id(ui.getCust_id());
		jbill.getMap().put("r3_code", shop.getR3_code());
		HashMap map = super.getFacade().getJBillService().getInfoForSales(jbill);
		DecimalFormat decimalFormat1 = new DecimalFormat("##,###,##0.00");
		DecimalFormat decimalFormat2 = new DecimalFormat("##,###,##0");
		double year_rate = 0;
		if(Double.parseDouble(map.get("SALES_YEAR").toString())!=0 && Double.parseDouble(map.get("TARGET_YEAR").toString())!=0){
			year_rate = Double.parseDouble(map.get("SALES_YEAR").toString())/Double.parseDouble(map.get("TARGET_YEAR").toString())*100;
		}
		double month_rate = 0;
		if(Double.parseDouble(map.get("SALES_MONTH").toString())!=0 && Double.parseDouble(map.get("TARGET_MONTH").toString())!=0){
			month_rate = Double.parseDouble(map.get("SALES_MONTH").toString())/Double.parseDouble(map.get("TARGET_MONTH").toString())*100;
		}
		double average_yesterday = 0;
		double average_today = 0;
		if(Double.parseDouble(map.get("SALES_MONEY_YESTERDAY").toString())!=0 && Double.parseDouble(map.get("SALES_NUM_YESTERDAY").toString())!=0){
			average_yesterday = Double.parseDouble(map.get("SALES_MONEY_YESTERDAY").toString())/Double.parseDouble(map.get("SALES_NUM_YESTERDAY").toString());
		}
		if(Double.parseDouble(map.get("SALES_MONEY_TODAY").toString())!=0 && Double.parseDouble(map.get("SALES_NUM_TODAY").toString())!=0){
			average_today = Double.parseDouble(map.get("SALES_MONEY_TODAY").toString())/Double.parseDouble(map.get("SALES_NUM_TODAY").toString());
		}
		
		allmap.put("year_rate", decimalFormat1.format(year_rate));
		allmap.put("target_year", decimalFormat1.format(map.get("TARGET_YEAR")));
		allmap.put("sales_year", decimalFormat1.format(map.get("SALES_YEAR")));
		allmap.put("month_rate", decimalFormat1.format(month_rate));
		allmap.put("target_month", decimalFormat1.format(map.get("TARGET_MONTH")));
		allmap.put("sales_month", decimalFormat1.format(map.get("SALES_MONTH")));
		allmap.put("sales_money_yesterday", decimalFormat1.format(map.get("SALES_MONEY_YESTERDAY")));
		allmap.put("sales_num_yesterday", decimalFormat2.format(map.get("SALES_NUM_YESTERDAY")));
		allmap.put("sales_money_today", decimalFormat1.format(map.get("SALES_MONEY_TODAY")));
		allmap.put("sales_num_today", decimalFormat2.format(map.get("SALES_NUM_TODAY")));
		allmap.put("average_yesterday", decimalFormat1.format(average_yesterday));
		allmap.put("average_today", decimalFormat1.format(average_today));
		/**end  查询首页顶部任务完成情况数据*/
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 销售管理首页各机型销售列表数据
	 * @author Liang Houen
	 * @since 2015-7-21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSalesListData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
		//当前用户信息
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		HashMap map = new HashMap();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String now = dateformat.format(calendar.getTime());//当前日期
		String[] arrs = now.split("-");
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		map.put("I_C_ID", ui.getCust_id().toString());
		map.put("I_R3_CODE", shop.getR3_code());
		map.put("I_YEAR", arrs[0]);
		map.put("I_MONTH", arrs[1]);
		List<HashMap> relist = super.getFacade().getJBillService().getSaleForMonthList(map);
		allmap.put("salesList", relist);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 客户端-进货管理首页当年，当月，当日进货数据查询
	 * @author Liang Houen
	 * @since 2015-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward buyInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		if (null == ui || null == ui.getCust_id()) {
			super.renderJavaScript(response, "alert('用户信息有误，请联系相关人员处理！');history.back();");
			return null;
		}
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		
		/**Begin  进货数据*/
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String now = dateformat.format(calendar.getTime());//当前日期
		String[] arrs = now.split("-");
		HashMap map = new HashMap();
		map.put("r3_id", shop.getId());
		HashMap reMap = super.getFacade().getJBillService().getBuyInfo(map);
		
		DecimalFormat decimalFormat1 = new DecimalFormat("##,###,##0.00");
		DecimalFormat decimalFormat2 = new DecimalFormat("##,###,##0");
		double year_aver = 0;
		if(Double.parseDouble(reMap.get("YEAR_NUM").toString())!=0 && Double.parseDouble(reMap.get("YEAR_MONEY").toString())!=0){
			year_aver = Double.parseDouble(reMap.get("YEAR_MONEY").toString())/Double.parseDouble(reMap.get("YEAR_NUM").toString());
		}
		double month_aver = 0;
		if(Double.parseDouble(reMap.get("MONTH_MONEY").toString())!=0 && Double.parseDouble(reMap.get("MONTH_NUM").toString())!=0){
			month_aver = Double.parseDouble(reMap.get("MONTH_MONEY").toString())/Double.parseDouble(reMap.get("MONTH_NUM").toString());
		}
		double today_aver = 0;
		if(Double.parseDouble(reMap.get("TODAY_MONEY").toString())!=0 && Double.parseDouble(reMap.get("TODAY_NUM").toString())!=0){
			today_aver = Double.parseDouble(reMap.get("TODAY_MONEY").toString())/Double.parseDouble(reMap.get("TODAY_NUM").toString());
		}
		HashMap allmap = new HashMap();
		allmap.put("year_money", decimalFormat1.format(reMap.get("YEAR_MONEY")));
		allmap.put("year_num", decimalFormat2.format(reMap.get("YEAR_NUM")));
		allmap.put("year_aver", decimalFormat1.format(year_aver));
		allmap.put("month_money", decimalFormat1.format(reMap.get("MONTH_MONEY")));
		allmap.put("month_num", decimalFormat2.format(reMap.get("MONTH_NUM")));
		allmap.put("month_aver", decimalFormat1.format(month_aver));
		allmap.put("today_money", decimalFormat1.format(reMap.get("TODAY_MONEY")));
		allmap.put("today_num", decimalFormat2.format(reMap.get("TODAY_NUM")));
		allmap.put("today_aver", decimalFormat1.format(today_aver));
		/**end  进货数据*/
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 进货管理首页各月份进货图数据
	 * @author Liang Houen
	 * @since 2015-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMonthsInData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
		//当前用户信息
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		HashMap map = new HashMap();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String now = dateformat.format(calendar.getTime());//当前日期
		String[] arrs = now.split("-");
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		
		//当年月度进货金额
		StringBuffer moneys = new StringBuffer();
		HashMap mmap = new HashMap();
		mmap.put("year", arrs[0]);
		mmap.put("r3_id", shop.getId());
		for(int i=1;i<=12;i++){
			if(i<10){
				mmap.put("month", "0"+i);
			}else{
				mmap.put("month", i);
			}
			moneys.append(super.getFacade().getJBillService().getMonthMoney(mmap)+",");
		}
		allmap.put("moneys", "["+moneys.toString().substring(0, moneys.length()-1)+"]");
		allmap.put("year", arrs[0]);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 销售管理首页各机型进货数据列表
	 * @author Liang Houen
	 * @since 2015-7-21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMonthInData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
		//当前用户信息
		PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		HashMap map = new HashMap();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String now = dateformat.format(calendar.getTime());//当前日期
		String[] arrs = now.split("-");
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(ui.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		map.put("I_C_ID", ui.getCust_id().toString());
		map.put("I_R3_CODE", shop.getR3_code());
		map.put("I_YEAR", arrs[0]);
		map.put("I_MONTH", arrs[1]);
		List<HashMap> relist = super.getFacade().getJBillService().getMonthInDataList(map);
		allmap.put("inlist", relist);

		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
}
