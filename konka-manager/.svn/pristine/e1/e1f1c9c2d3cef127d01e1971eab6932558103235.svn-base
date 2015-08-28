package com.ebiz.mmt.web.struts.member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.EcLuckyBuy;
import com.ebiz.mmt.domain.EcLuckyMain;
import com.ebiz.mmt.domain.EcLuckyTime;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Tudp
 * @version 2014-07-14
 */
public class LuckyAction extends BaseMemberAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser"); 
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser"); 
		EcLuckyMain entity=new EcLuckyMain(); 	 
		entity.setLucky_type(new Integer(0));//抽奖活动
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		Pager pager = (Pager) dynaBean.get("pager"); 
		Long recordCount=super.getFacade().getEcLuckyMainService().getEcLuckyMainCount(entity);
		
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcLuckyMain> entityList=super.getFacade().getEcLuckyMainService().getEcLuckyMainPaginatedList(entity);				
		request.setAttribute("entityList", entityList);
		
				// 新闻资讯
				KonkaPeArticleInfo konkaPeArticleInfo = new KonkaPeArticleInfo();
				konkaPeArticleInfo.setArticle_type_id(2000L);
				konkaPeArticleInfo.setArticle_mod_id(Long.valueOf(905701));
				konkaPeArticleInfo.setIs_del(0L);
				konkaPeArticleInfo.setStates(1L);
				konkaPeArticleInfo.getRow().setCount(5);
				List<KonkaPeArticleInfo> konkaPeArticleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				        .getKonkaPeArticleInfoList(konkaPeArticleInfo);
				request.setAttribute("konkaPeArticleInfoList", konkaPeArticleInfoList);
				
				String p_index = super.getPIndexByRequest(request); 
				Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
				List<KonkaBcompPd> bcomp_pd_list_top_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,
				        ecUser.getDept_id(), ecUser.getCust_id(), null);
				request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String lucky_id = (String) dynaBean.get("lucky_id"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcLuckyMain entity = new EcLuckyMain();
		entity.setId(Long.valueOf(lucky_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		entity = super.getFacade().getEcLuckyMainService().getEcLuckyMain(entity);
		
		if(entity!=null){
			if(entity.getMain_pic()!=null){
				String[] picArray =entity.getMain_pic().split(",");
				entity.getMap().put("picArray", picArray);
			}
			request.setAttribute("entity", entity);
			
			//限制购买次数
			EcLuckyBuy ecLuckyBuy= new EcLuckyBuy();
			ecLuckyBuy.setUser_id(ecUser.getId());
			ecLuckyBuy.setLucky_id(entity.getId());
			ecLuckyBuy.getMap().put("is_today_buy","1");
			Long buy_times = super.getFacade().getEcLuckyBuyService().getEcLuckyBuyCount(ecLuckyBuy);
			request.setAttribute("buy_times", buy_times); 
			
			
			//今天抢购记录
			ecLuckyBuy= new EcLuckyBuy(); 
			ecLuckyBuy.setLucky_id(entity.getId());
			ecLuckyBuy.getMap().put("is_today_buy","1");
			Long buy_count = super.getFacade().getEcLuckyBuyService().getEcLuckyBuyCount(ecLuckyBuy);
			request.setAttribute("buy_count", buy_count); 
			
			EcLuckyTime ecLuckyTime = new EcLuckyTime();
			ecLuckyTime.setLucky_id(entity.getId());
			ecLuckyTime.setIs_act(new Integer(1));
			List<EcLuckyTime> ecLuckyTimeList = super.getFacade().getEcLuckyTimeService().getEcLuckyTimeList(ecLuckyTime); 
			Integer is_act=new Integer(0);
			if(ecLuckyTimeList!=null&&ecLuckyTimeList.size()>0){
				is_act =ecLuckyTimeList.size();
				ecLuckyTime =ecLuckyTimeList.get(0);
				
				Calendar c = Calendar.getInstance();
				if (ecLuckyTime != null && ecLuckyTime.getEnd_date() != null) {
					c.setTime(ecLuckyTime.getEnd_date());// 
				} 
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				Long timeRemains=c.getTimeInMillis() - calendar.getTimeInMillis();
				request.setAttribute("timeRemains",timeRemains); 

			} 
			request.setAttribute("is_act",is_act); 
			
			super.getFacade().getEcLuckyMainService().modifyEcLuckyMainViewCounts(entity);
			
			String p_index = super.getPIndexByRequest(request);
			Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));// 销量排行前5
			List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,ecUser.getDept_id(), ecUser.getCust_id(), null);
			request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);
			
		}else{
			return null;
		}
		return mapping.findForward("view");
	}

	public ActionForward stepTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request); 
		String lucky_id = (String) dynaBean.get("lucky_id"); 
		if (!GenericValidator.isLong(lucky_id)){
			String msg = "对不起，未找到当前活动";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/login.do';}");
			return null;
		}
		
		EcLuckyMain entity = new EcLuckyMain();
		entity.setId(Long.valueOf(lucky_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		entity = super.getFacade().getEcLuckyMainService().getEcLuckyMain(entity);

		List<Map<String, String>> json = new ArrayList<Map<String, String>>();
		if (null != entity) {		 
			String main_pic = entity.getMain_pic();
			if (StringUtils.isNotBlank(main_pic)) {
				String main_pic_file = StringUtils.split(main_pic, ",")[0];
				dynaBean.set("main_pic_file", main_pic_file); // 主图
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("lucky_id", entity.getId().toString());
			//map.put("pd_name", entity.getTitle());
			map.put("price", entity.getPrice().toString());
			if(entity.getIntegral()==null){
				entity.setIntegral(0);
			}
			map.put("integral", entity.getIntegral().toString());
			map.put("buy_num", "1");
			json.add(map);
		}
		// 将需要购买的商品数据加密，然后到前台
		request.setAttribute("buy_json_object", new DESPlus().encrypt(JSON.toJSONString(json)));
		request.setAttribute("ecLuckyMain", entity);

		// 查询收货地址
		EcUserAddrs ecUserAddrs = new EcUserAddrs();
		ecUserAddrs.getRow().setCount(2);
		ecUserAddrs.setUser_id(ecUser.getId());
		ecUserAddrs.setRel_region(Long.valueOf(p_index));
		request.setAttribute("p_name", super.getPIndexName(p_index, ""));
		request.setAttribute("p_index",p_index);
		List<EcUserAddrs> ecUserAddrsList = super.getFacade().getEcUserAddrsService().getEcUserAddrsList(ecUserAddrs);
		if (null == ecUserAddrsList || 0 == ecUserAddrsList.size()) { // 县区不存在直接查所在市
			ecUserAddrs.setRel_region(null);
			ecUserAddrs.getMap().put("rel_city_like", StringUtils.substring(p_index, 0, 4));
			ecUserAddrsList = super.getFacade().getEcUserAddrsService().getEcUserAddrsList(ecUserAddrs);
		}

		if (null != ecUserAddrsList && 0 != ecUserAddrsList.size()) {
			EcUserAddrs userAddrs =  ecUserAddrsList.get(0);
			request.setAttribute("ecUserAddrs", userAddrs);
			String province = "";
			String city = "";
			String country = "";
			String town = "";
			town = "" + userAddrs.getRel_region();
			if (userAddrs.getRel_region() != null) {
				country = userAddrs.getRel_region().toString().substring(0, 6);
				city = userAddrs.getRel_region().toString().substring(0, 4) + "00";
				province = userAddrs.getRel_region().toString().substring(0, 2) + "0000";
			}
			request.setAttribute("province", province);
			request.setAttribute("city", city);
			request.setAttribute("country", country);
			request.setAttribute("town", town);
		}
		return new ActionForward("/../member/Lucky/step_two.jsp");
	}

	public ActionForward createOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String buy_json_object = (String) dynaBean.get("buy_json_object");
		String rel_name = (String) dynaBean.get("rel_name");
		String rel_addr = (String) dynaBean.get("rel_addr");
		String rel_phone = (String) dynaBean.get("rel_phone");
		String rel_tel = (String) dynaBean.get("rel_tel");
		String rel_zip = (String) dynaBean.get("rel_zip");
		String deliver_way = (String) dynaBean.get("deliver_way");
		String deliver_time = (String) dynaBean.get("deliver_time");
		String deliver_is_call = (String) dynaBean.get("deliver_is_call");
		String bill_is_add = (String) dynaBean.get("bill_is_add");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request);		
		if (!StringUtils.isBlank(town) && GenericValidator.isLong(town)) {
			p_index =town;
		} else if (!StringUtils.isBlank(country) && GenericValidator.isLong(country)){
			p_index =country;
		}
		if (StringUtils.isBlank(buy_json_object)) {
			String msg = "对不起,发生错误";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/login.do';}");
			return null;
		}
		Long lucky_id=null;
		// 交易流水号
		Date now = new Date();
		String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")+ StringUtils.substring(String.valueOf(now.getTime()), 7); 
		JSONArray json = JSON.parseArray((new DESPlus().decrypt(buy_json_object)));
		if (json != null && json.size() > 0){
			JSONObject obj = json.getJSONObject(0);
			lucky_id = obj.getLong("lucky_id"); // 商品ID 
			EcLuckyMain entity = new EcLuckyMain();
			entity.setId(lucky_id);
			entity.setOwn_sys(ecUser.getUser_type());
			entity.setIs_pub(new Integer(1));
			entity.setIs_del(new Integer(0));
			entity =super.getFacade().getEcLuckyMainService().getEcLuckyMain(entity); 
			String msg="";
			if(entity!=null){
				//判断活动是否开始
				List<EcLuckyTime> ecLuckyTimeList = entity.getEcLuckyTimeList();
				EcLuckyTime ecLuckyTime =new EcLuckyTime();
				int is_act=0;
				int is_start=0;
				int is_end=0;
				for(int i=0;i<ecLuckyTimeList.size();i++){
					ecLuckyTime =new EcLuckyTime();
					ecLuckyTime =ecLuckyTimeList.get(i);
					if(ecLuckyTime.getIs_act().intValue()==1){
						is_act=1;
					}
					if(ecLuckyTime.getIs_start().intValue()==1){
						is_start++;
					}
					if(ecLuckyTime.getIs_end().intValue()==1){
						is_end++;
					}
				} 
				if(is_act==0){ 
					if(is_start>0){
						if(is_end>0){
							msg="本次活动已经结束,请等待下一次活动"; 
						}else{
							msg="活动已经结束"; 	
						}
					}else{
						if(is_end>0){
							msg="本次活动已经结束,请等待下一次活动"; 
						}else{
							msg="本次活动还未开始"; 	
						}
					} 
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
				//限制购买次数
				EcLuckyBuy ecLuckyBuy= new EcLuckyBuy();
				ecLuckyBuy.setLucky_id(lucky_id);
				ecLuckyBuy.setUser_id(ecUser.getId());
				//限制当天购买次数
				ecLuckyBuy.getMap().put("is_today_buy","1");
				Long count = super.getFacade().getEcLuckyBuyService().getEcLuckyBuyCount(ecLuckyBuy);
				if(count.intValue()>=entity.getLucky_num().intValue()){
					msg="对不起,您已经参与"+count.intValue()+"次了"; 	
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
				
				ecLuckyBuy= new EcLuckyBuy();
				ecLuckyBuy.setLucky_id(lucky_id);
				ecLuckyBuy.setTitle(entity.getTitle());
				ecLuckyBuy.setTrade_index(trade_index);
				ecLuckyBuy.setIs_lucky(0);
				ecLuckyBuy.setIntegral(entity.getIntegral());
				ecLuckyBuy.setPrice(entity.getPrice());
				ecLuckyBuy.setNum(1);
				ecLuckyBuy.setOwn_sys(ecUser.getUser_type());
				ecLuckyBuy.setReal_name(ecUser.getReal_name());
				ecLuckyBuy.setUser_id(ecUser.getId());
				ecLuckyBuy.setUser_name(ecUser.getUser_name());
				
				ecLuckyBuy.setRel_name(rel_name);
				ecLuckyBuy.setRel_addr(rel_addr);
				ecLuckyBuy.setRel_phone(rel_phone);
				ecLuckyBuy.setRel_tel(rel_tel);
				if(rel_zip!=null)
				ecLuckyBuy.setRel_zip(new Long(rel_zip));
				if(province!=null){
					ecLuckyBuy.setRel_province(new Long(province));
				}
				if(city!=null){
					ecLuckyBuy.setRel_city(new Long(city));
				}
				if(town!=null){
					ecLuckyBuy.setRel_region(new Long(town));
				}else if(country!=null) { 
					ecLuckyBuy.setRel_region(new Long(country));
				}
				if(entity.getGoods_id()!=null){
					//create order抽奖活动暂时不添加订单
				}
				//结果
				ecLuckyBuy.getMap().put("is_today_buy","1");
				String z =super.getFacade().getEcLuckyBuyService().createEcLuckyBuyForLucky(ecLuckyBuy);
				if("0".equals(z)){
					msg="活动还未开始";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}else if("-1".equals(z)){
					msg="您已经参与了该活动!";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
			}		 
		}else{
			String msg = "对不起,发生错误，请重新下单";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} 
		String msg = "订单提交成功，交易流水号:"+trade_index;
		super.renderJavaScript(response,
				"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request) + "/member/Lucky.do?method=view&lucky_id="+lucky_id+"';}");
		return null;
	}
	
	public ActionForward listBuy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String lucky_id = (String) dynaBean.get("lucky_id"); 
		String days = (String) dynaBean.get("days"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcLuckyMain entity = new EcLuckyMain();
		entity.setId(Long.valueOf(lucky_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		if(days!=null&&!"".equals(days)){
			entity.getMap().put("days", days);
		}
		entity = super.getFacade().getEcLuckyMainService().getEcLuckyMain(entity);
		if(entity.getMain_pic()!=null){
			String[] picArray =entity.getMain_pic().split(",");
			entity.getMap().put("picArray", picArray);
		}
		if(entity!=null){
			request.setAttribute("entity", entity); 
		}else{
			return null;
		}
		return new ActionForward("/../member/Lucky/buy_list.jsp");
	}
}