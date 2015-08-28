package com.ebiz.mmt.web.struts.member;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
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
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcAuctionBuy;
import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.CrcUtil;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Tudp
 * @version 2014-07-14
 */
public class AuctionAction extends BaseMemberAction {
	
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
		EcAuctionMain entity=new EcAuctionMain(); 	 
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		Pager pager = (Pager) dynaBean.get("pager"); 
		Long recordCount=super.getFacade().getEcAuctionMainService().getEcAuctionMainCount(entity);
		
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcAuctionMain> entityList=super.getFacade().getEcAuctionMainService().getEcAuctionMainPaginatedList(entity);				
		request.setAttribute("entityList", entityList);
		
				// 新闻资讯
		KonkaPeArticleInfo konkaPeArticleInfo = new KonkaPeArticleInfo();
		konkaPeArticleInfo.setArticle_type_id(2000L);
		konkaPeArticleInfo.setArticle_mod_id(Long.valueOf(905701));
		konkaPeArticleInfo.setIs_del(0L);
		konkaPeArticleInfo.setStates(1L);
		konkaPeArticleInfo.getRow().setCount(5);
		List<KonkaPeArticleInfo> konkaPeArticleInfoList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(konkaPeArticleInfo);
		request.setAttribute("konkaPeArticleInfoList", konkaPeArticleInfoList);
				
		String p_index = super.getPIndexByRequest(request); 
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
		List<KonkaBcompPd> bcomp_pd_list_top_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String auction_id = (String) dynaBean.get("auction_id"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcAuctionMain entity = new EcAuctionMain();
		entity.setId(Long.valueOf(auction_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		entity = super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity);
		
		if(entity!=null){
			if(entity.getMain_pic()!=null){
				String[] picArray =entity.getMain_pic().split(",");
				entity.getMap().put("picArray", picArray);
			}
			request.setAttribute("entity", entity);
			
			List<Map<String, String>> json = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("auction_id", entity.getId().toString()); 
			map.put("price", entity.getPrice().toString());
			if(entity.getIntegral()==null){
				entity.setIntegral(0);
			}
			map.put("integral", entity.getIntegral().toString());
			map.put("buy_num", "1");
			json.add(map);
			// 将需要购买的商品数据加密，然后到前台
			request.setAttribute("buy_json_object", new DESPlus().encrypt(JSON.toJSONString(json)));			
			
			//出价记录
			EcAuctionBuy ecAuctionBuy= new EcAuctionBuy();			 
			if(entity.getEcAuctionBuyList()!=null&&entity.getEcAuctionBuyList().size()>0){
				ecAuctionBuy=entity.getEcAuctionBuyList().get(0); 
				request.setAttribute("buy_price", ecAuctionBuy.getPrice());
				request.setAttribute("buy_count", entity.getEcAuctionBuyList().size());
				request.setAttribute("ecAuctionBuy", ecAuctionBuy);
			}else{
				request.setAttribute("buy_price",entity.getAuction_price());
				request.setAttribute("buy_count", "0");
			} 
			
			//获取活动开始 结束时间
			Calendar cEnd = Calendar.getInstance();
			Calendar cStart = Calendar.getInstance();
			if (entity.getAuction_end_time() != null && entity.getAuction_start_time() != null) {
				cEnd.setTime(entity.getAuction_end_time());// 
				cStart.setTime(entity.getAuction_start_time());// 
			} 
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(entity.getAuction_sysdate());
			if(entity.getDelay_num()!=null&&entity.getDelay_time()!=null&&entity.getDelay_num().intValue()>0&&entity.getDelay_time().intValue()>0){
				cEnd.add(13, entity.getDelay_num().intValue()*entity.getDelay_time().intValue());
			}
			Long timeRemains=cEnd.getTimeInMillis() - calendar.getTimeInMillis();
			Long timeRemainsStart=cStart.getTimeInMillis() - calendar.getTimeInMillis();
			cStart.setTime(entity.getAuction_start_time()); 
			if(timeRemainsStart.longValue()>0){
				timeRemains=cEnd.getTimeInMillis() - cStart.getTimeInMillis();
			}else{
				if(ecAuctionBuy!=null&&ecAuctionBuy.getUser_id()!=null&&timeRemains.longValue()<1){
					if(ecAuctionBuy.getUser_id().longValue()==ecUser.getId().longValue()){
						request.setAttribute("is_success","1");
					}else{
						request.setAttribute("is_success","0");	
					}
				}
			}
			request.setAttribute("timeRemains",timeRemains); 
			request.setAttribute("timeRemainsStart",timeRemainsStart); 			 
			
			super.getFacade().getEcAuctionMainService().modifyEcAuctionMainViewCounts(entity);
			
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
		String auction_id = (String) dynaBean.get("auction_id"); 
		if (!GenericValidator.isLong(auction_id)){
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
		
		EcAuctionMain entity = new EcAuctionMain();
		entity.setId(Long.valueOf(auction_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0));
		entity = super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity);
		
		
		//过滤非法请求
		EcAuctionBuy ecAuctionBuy=super.getFacade().getEcAuctionBuyService().getEcAuctionBuyForMaxPrice(Long.valueOf(auction_id));
		if(ecAuctionBuy!=null){
			if(ecAuctionBuy.getUser_id().longValue()!=ecUser.getId().longValue()){
				return null;
			}
			if(ecAuctionBuy.getPrice().floatValue()<entity.getAuction_price().floatValue()+entity.getAdd_price().floatValue()){
				return null;
			}
			//已经下单
			if(ecAuctionBuy.getIs_success()!=null&&ecAuctionBuy.getIs_success().intValue()==1){ 
				String msg = "您的订单已经生成，请到会员中心\"我的订单\"查询";
				super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/center/Orders.do';}");
				return null; 
			}
			request.setAttribute("goods_id", entity.getGoods_id());
			request.setAttribute("goods_name", entity.getGoods_name());
			request.setAttribute("goods_price", ecAuctionBuy.getPrice());
			
		}else{
			return null;
		}
		
		EcAuctionMain c = new EcAuctionMain();
		c.setId(Long.valueOf(auction_id));
		c.getMap().put("auction_order_time", "1");
		Long count =super.getFacade().getEcAuctionMainService().getEcAuctionMainCount(c);
		if(count.intValue()==0){ 
			String msg = "对不起,拍卖已经过期";
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');}");
			return null; 
		}

		List<Map<String, String>> json = new ArrayList<Map<String, String>>();
		if (null != entity) {		 
			String main_pic = entity.getMain_pic();
			if (StringUtils.isNotBlank(main_pic)) {
				String main_pic_file = StringUtils.split(main_pic, ",")[0];
				dynaBean.set("main_pic_file", main_pic_file); // 主图
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("auction_id", entity.getId().toString());
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
		request.setAttribute("ecAuctionMain", entity);

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
		return new ActionForward("/../member/Auction/step_two.jsp");
	}
	public ActionForward createOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String buy_json_object = (String) dynaBean.get("buy_json_object");
		String p_index = (String) dynaBean.get("p_index");
		String rel_name = (String) dynaBean.get("rel_name");
		String rel_addr = (String) dynaBean.get("rel_addr");
		String rel_phone = (String) dynaBean.get("rel_phone");
		String rel_tel = (String) dynaBean.get("rel_tel");
		String rel_zip = (String) dynaBean.get("rel_zip");
		String pay_way = (String) dynaBean.get("pay_way");
		String deliver_time = (String) dynaBean.get("deliver_time");
		String bill_is_add = (String) dynaBean.get("bill_is_add");
		String bill_type = (String) dynaBean.get("bill_type");
		String bill_head = (String) dynaBean.get("bill_head");
		String bill_company = (String) dynaBean.get("bill_company");
		String bill_content = (String) dynaBean.get("bill_content");
		String deliver_is_call = (String) dynaBean.get("deliver_is_call");
		String is_deduction = (String) dynaBean.get("is_deduction");
		String is_zt = (String) dynaBean.get("is_zt");// 判断是否自提
		String self_way = (String) dynaBean.get("self_way");// 自提点id
		String deliver_way = (String) dynaBean.get("deliver_way");
		String is_ps = (String) dynaBean.get("is_ps");// 是否二次配送
		String ps_id = (String) dynaBean.get("ps_id");// 二次配送点id
		String logistic_sn = (String) dynaBean.get("logistic_sn");

		String[] vouchers = request.getParameterValues("vouchers");

		String p_index1 = (String) dynaBean.get("p_index1");
		String p_index2 = (String) dynaBean.get("p_index2");
		if (p_index2 != null && !"".equals(p_index2)) {
			p_index = p_index2;
		} else if (p_index1 != null && !"".equals(p_index1)) {
			p_index = p_index1;
		}

		if (StringUtils.isBlank(buy_json_object)) {
			String msg = super.getMessage(request, "ec.bcomp.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (!GenericValidator.isInt(pay_way)) {
			String msg = super.getMessage(request, "ec.pay.way.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取当前请求数据的用户所在地
		if (StringUtils.isEmpty(p_index))
			p_index = super.getPIndexByRequest(request);
		logger.info("----p_index--->{}", p_index);

		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}

		// 交易流水号
		Date now = new Date();
		String trade_index = "";

		// 初始化数据 STARTING...
		PshowOrder pshowOrder = new PshowOrder();
		pshowOrder.setOrder_type(0);
		pshowOrder.setIs_del(0);
		pshowOrder.setPay_way(Integer.valueOf(pay_way)); // 0：货到付款 1：在线支付 2: 支付宝
		// 3:银联 4:财付通
		pshowOrder.setOrder_from(ecUser.getUser_type()); // 1-工卡，2-触网，3-会员
		pshowOrder.setUuid(UUID.randomUUID().toString());
		pshowOrder.setTrade_index(trade_index);
		pshowOrder.setState(0);
		// pshowOrder.setTotal_price(total_price);
		pshowOrder.setOrder_user_id(ecUser.getId());
		pshowOrder.setOrder_user_name(ecUser.getReal_name());
		pshowOrder.setBuyer_name(rel_name);
		pshowOrder.setBuyer_p_index(Long.valueOf(p_index));
		pshowOrder.setBuyer_addr(rel_addr);
		pshowOrder.setBuyer_mp(rel_phone);
		pshowOrder.setBuyer_tel(rel_tel);
		// pshowOrder.setRemark("");
		pshowOrder.setBuyer_zip(rel_zip);
		pshowOrder.setAdd_date(now);
		pshowOrder.setDeliver_time(Integer.valueOf(deliver_time));
		if (StringUtils.isNotBlank(deliver_way)) {
			pshowOrder.setDeliver_way(Integer.valueOf(deliver_way));
		}
		// 自提点
		if (StringUtils.isNotBlank(is_zt)) {
			pshowOrder.setIs_self(Integer.valueOf(is_zt));
		}
		if (StringUtils.isNotBlank(self_way)) {
			pshowOrder.setSelf_id(Long.valueOf(self_way));
		}
		// 二次配送点
		if (StringUtils.isNotBlank(is_ps)) {
			pshowOrder.setIs_ps(Integer.valueOf(is_ps));
		} else {
			pshowOrder.setIs_ps(0);
		}
		if (StringUtils.isNotBlank(ps_id)) {
			pshowOrder.setPs_id(Long.valueOf(ps_id));
		}
		// 备注信息
		if (StringUtils.isNotBlank(logistic_sn)) {
			pshowOrder.setLogistic_sn(logistic_sn);
		}

		if (deliver_is_call != null) {
			pshowOrder.setDeliver_is_call(Integer.valueOf(deliver_is_call));
		}
		if (GenericValidator.isInt(bill_is_add))
			pshowOrder.setBill_is_add(Integer.valueOf(bill_is_add));
		if (GenericValidator.isInt(bill_type))
			pshowOrder.setBill_type(Integer.valueOf(bill_type));
		if (GenericValidator.isInt(bill_head))
			pshowOrder.setBill_head(Integer.valueOf(bill_head));
		if (StringUtils.isNotEmpty(bill_company))
			pshowOrder.setBill_company(bill_company);
		if (GenericValidator.isInt(bill_content))
			pshowOrder.setBill_content(Integer.valueOf(bill_content));

		// 处理商品信息
		BigDecimal allPrice = new BigDecimal("0");
		logger.info("-----buy_json_object--->{}", buy_json_object);
		String str = new DESPlus().decrypt(buy_json_object);
		str = URLDecoder.decode(str, "UTF-8");
		JSONArray json = JSON.parseArray(str);
		logger.info("-----json--->{}", json.toString());
		BigDecimal allIntegral = new BigDecimal("0");
		BigDecimal all_rebate = new BigDecimal("0.0");
		List<PshowOrdeDetails> pshowOrdeDetailsList = new ArrayList<PshowOrdeDetails>();
		String title="";
		Long buy_id=null;
		Long auction_id=null;
		for (int i = 0; i < json.size(); i++) {
			JSONObject obj = json.getJSONObject(i);
			auction_id = obj.getLong("auction_id"); // 商品ID
			EcAuctionMain ecAuctionMain = new EcAuctionMain();
			ecAuctionMain.setId(auction_id);
			ecAuctionMain =super.getFacade().getEcAuctionMainService().getEcAuctionMain(ecAuctionMain);
			EcAuctionBuy ecAuctionBuy = super.getFacade().getEcAuctionBuyService().getEcAuctionBuyForMaxPrice(auction_id);
			//已经下单
			if(ecAuctionBuy.getIs_success()!=null&&ecAuctionBuy.getIs_success().intValue()==1){ 
				String msg = "您的订单已经生成，请到会员中心\"我的订单\"查询";
				super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/center/Orders.do';}");
				return null; 
			}
			title=ecAuctionMain.getTitle();
			trade_index=ecAuctionBuy.getTrade_index();
			buy_id=ecAuctionBuy.getId();
			Long buy_num = 1L;// obj.getLong("buy_num"); // 购买数量
			String pd_name = "";//obj.getString("pd_name"); // 商品名称
			BigDecimal price = ecAuctionBuy.getPrice();// obj.getBigDecimal("price"); // 购买单价
			String service_ids = "";//obj.getString("service_ids"); // 订购的增值服务
			String store_id ="61"; obj.getString("store_id"); // 仓库ID
			// 查询商品信息
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(ecAuctionMain.getGoods_id());
			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd); 

			// 查询商品是否在PINDEX以外，收取额外费用 
			BigDecimal rule_price = new BigDecimal("0.00"); 
			BigDecimal rule_price_2 = new BigDecimal("0.00");
			 
			// 查询商品佣金、返利
			EcBcompPdRebates ecBcompPdRebates = new EcBcompPdRebates();
			ecBcompPdRebates.setIs_del(0);
			ecBcompPdRebates.setGoods_id(konkaBcompPd.getId());
			ecBcompPdRebates.setOwn_sys(ecUser.getUser_type());
			if (ecUser.getUser_type().intValue() == 2) {
				ecBcompPdRebates.setDept_id(ecUser.getDept_id());
				ecBcompPdRebates.setC_id(ecUser.getCust_id());
			}
			ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);

			// 计算奖励积分
			BigDecimal x = new BigDecimal("50"); 

			// 订单明细
			PshowOrdeDetails pod = new PshowOrdeDetails();
			pod.setState(0);
			pod.setPd_id(konkaBcompPd.getId());
			pod.setPd_name(konkaBcompPd.getPd_name()+"（拍卖）");
			pod.setNum(buy_num);
			pod.setPrice(price);
			BigDecimal service_price = new BigDecimal("0.0");
			// 明细中的商品订购的增值服务
			if (StringUtils.isNotBlank(service_ids)) {
				List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = new ArrayList<EcBindingPdOrdeDetails>();
				String[] service_ids_array = StringUtils.split(service_ids, "|");
				for (String binding_pd_id : service_ids_array) {
					if (StringUtils.isBlank(binding_pd_id) || "null".equals(binding_pd_id))
						continue;
					// 查询绑定ID的绑定商品信息
					EcBindingPd ecBindingPd = new EcBindingPd();
					ecBindingPd.setId(Long.valueOf(binding_pd_id));
					ecBindingPd = super.getFacade().getEcBindingPdService().getEcBindingPd(ecBindingPd);
					if (null != ecBindingPd) {
						EcBindingPdOrdeDetails ecBindingPdOrdeDetails = new EcBindingPdOrdeDetails();
						ecBindingPdOrdeDetails.setTrade_index(trade_index);
						ecBindingPdOrdeDetails.setState(0);
						ecBindingPdOrdeDetails.setBinding_id(ecBindingPd.getId());
						ecBindingPdOrdeDetails.setGoods_name(ecBindingPd.getGoods_name());
						ecBindingPdOrdeDetails.setNum(buy_num);
						ecBindingPdOrdeDetails.setPrice(ecBindingPd.getPrice());
						ecBindingPdOrdeDetails.setTotal_price(ecBindingPdOrdeDetails.getPrice().multiply(
						        new BigDecimal(buy_num.toString())));
						ecBindingPdOrdeDetailsList.add(ecBindingPdOrdeDetails);
						service_price = service_price.add(ecBindingPdOrdeDetails.getTotal_price());
					}
				}
				pod.setEcBindingPdOrdeDetailsList(ecBindingPdOrdeDetailsList);
			}
		 

			pod.setTotal_price(pod.getPrice().multiply(new BigDecimal(pod.getNum().toString())).add(service_price).add(
			        rule_price.multiply(new BigDecimal(buy_num.toString()))).subtract(
			        rule_price_2.multiply(new BigDecimal(buy_num.toString()))).setScale(2, BigDecimal.ROUND_HALF_UP)); // 明细总价
			pod.setStore_id(Long.valueOf(store_id));
			
			pod.setRebates(new BigDecimal("0"));
			pod.setIntegral(new BigDecimal("0")); 
			// 应付金额非货到付款银行转账 配件商品不在线支付
			if (pshowOrder.getPay_way().intValue() != 0 || pshowOrder.getPay_way().intValue() != 1
			        || konkaBcompPd.getProd_type().intValue() != 10) {
				allPrice = allPrice.add(pod.getTotal_price()); // 计算订单总价
			}
			allIntegral = allIntegral.add(pod.getIntegral());// 计算订单总积分

			if (pod.getRebates() != null) {
				all_rebate = all_rebate.add(pod.getRebates());
				if ("1".equals(is_deduction)) {
					pod.setRebates_status(new Integer(4));
				} else {
					pod.setRebates_status(new Integer(0));
				}
			}
			pshowOrdeDetailsList.add(pod);
		}
		pshowOrder.setPshowOrdeDetailsList(pshowOrdeDetailsList);
		pshowOrder.setTotal_price(allPrice);
		pshowOrder.setIntegral(allIntegral);// 订单总奖励积分
		pshowOrder.setTrade_index(trade_index);
		if(pshowOrder.getRemark()!=null){
			title +=","+pshowOrder.getRemark();
		}
		pshowOrder.setRemark(title);

		BigDecimal deduPrice = new BigDecimal("0");// 抵扣金额
		if ("1".equals(is_deduction)) {
			deduPrice = all_rebate;
			pshowOrder.setIs_deduction(new Integer(0));
		}
		List<EcVouchers> ecVouchersList = new ArrayList<EcVouchers>();
		if (vouchers != null && vouchers.length > 0) {
			for (String vouchers_id : vouchers) {
				EcVouchers ecVouchers = new EcVouchers();
				ecVouchers.setId(new Long(vouchers_id));
				ecVouchers.setUser_id(ecUser.getId());
				ecVouchers = super.getFacade().getEcVouchersService().getEcVouchers(ecVouchers);
				if (ecVouchers != null) {
					deduPrice = deduPrice.add(ecVouchers.getPrice());
					ecVouchersList.add(ecVouchers);
				}
			}
			pshowOrder.setEcVouchersList(ecVouchersList);
		}

		BigDecimal payPrice = allPrice.subtract(deduPrice);// 订单付款金额=订单总价-抵扣金额
		if (payPrice.doubleValue() < 1.01) {
			payPrice = new BigDecimal("1.0"); 
		}
		pshowOrder.setDedu_price(deduPrice);
		pshowOrder.setPay_price(payPrice);// 订单付款金额=订单总价-抵扣金额
		
		EcAuctionMain c = new EcAuctionMain();
		c.setId(auction_id);
		c.getMap().put("auction_order_time", "1");
		Long count =super.getFacade().getEcAuctionMainService().getEcAuctionMainCount(c);
		if(count.intValue()==0){ 
			String msg = "对不起,拍卖已经过期";
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');}");
			return null; 
		}

		// 初始化结束
		// 建立订单信息
		super.getFacade().getPshowOrderService().createPshowOrderWithDetails(pshowOrder);  
		EcAuctionBuy ecAuctionBuy =new EcAuctionBuy();
		ecAuctionBuy.setIs_success(1);
		ecAuctionBuy.setId(buy_id);
		super.getFacade().getEcAuctionBuyService().modifyEcAuctionBuy(ecAuctionBuy);
		
		super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)
		        + "/member/Payment.do?trade_index=" + trade_index + "&pay_way=" + pshowOrder.getPay_way() + "';}");
		return null;
	}
	
	public ActionForward listBuy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String auction_id = (String) dynaBean.get("auction_id"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcAuctionMain entity = new EcAuctionMain();
		entity.setId(Long.valueOf(auction_id));
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_pub(new Integer(1));
		entity.setIs_del(new Integer(0)); 
		entity = super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity);
		if(entity!=null){
			request.setAttribute("entity", entity); 
		}else{
			return null;
		}
		return new ActionForward("/../member/Auction/buy_list.jsp");
	}
	
	public ActionForward ajaxAddBuy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String buy_json_object = (String) dynaBean.get("buy_json_object"); 
		String price = (String) dynaBean.get("price"); 
		String msg="";
		Map<String, String> maps = new HashMap<String, String>();
		// 取当前请求数据的用户所在地  
		if (StringUtils.isBlank(buy_json_object)){
			 msg = "对不起,发生错误"; 
			 maps.put("status", "0");
			 maps.put("msg", msg.toString());
			 super.renderJson(response, JSON.toJSONString(maps));
			 return null;
		}
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			msg = super.getMessage(request, "ec.nologin"); 
			maps.put("status", "0");
			maps.put("msg", msg.toString());
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}
		Long auction_id=null; 
		JSONArray json = JSON.parseArray((new DESPlus().decrypt(buy_json_object))); 
		if (json != null && json.size() > 0){
			JSONObject obj = json.getJSONObject(0);
			auction_id = obj.getLong("auction_id"); // 商品ID 
			EcAuctionMain entity = new EcAuctionMain();
			entity.setId(auction_id);
			entity.setOwn_sys(ecUser.getUser_type());
			entity.setIs_pub(new Integer(1));
			entity.setIs_del(new Integer(0));
			entity =super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity); 
			if(entity!=null){
				//判断活动是否开始  
				if(entity.getIs_act().intValue()==0){
					msg="对不起,活动已经结束";
				}else{
					try{
						// 交易流水号
						Date now = new Date();
						String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")+ StringUtils.substring(String.valueOf(now.getTime()), 7); 
						EcAuctionBuy ecAuctionBuy= new EcAuctionBuy();
						ecAuctionBuy.setAuction_id(auction_id);
						ecAuctionBuy.setUser_id(ecUser.getId());  
						//限制出价金额 
						ecAuctionBuy= new EcAuctionBuy();
						ecAuctionBuy.setAuction_id(auction_id);
						ecAuctionBuy.setTitle(entity.getTitle());
						ecAuctionBuy.setTrade_index(trade_index);
						ecAuctionBuy.setIs_success(0);
						ecAuctionBuy.setIntegral(entity.getIntegral());
						ecAuctionBuy.setPrice(new BigDecimal(price));
						ecAuctionBuy.setNum(1);
						ecAuctionBuy.setOwn_sys(ecUser.getUser_type());
						ecAuctionBuy.setReal_name(ecUser.getReal_name());
						ecAuctionBuy.setUser_id(ecUser.getId());
						ecAuctionBuy.setUser_name(ecUser.getUser_name());   
						if(ecAuctionBuy.getPrice().floatValue()>entity.getPrice().floatValue()){
							msg = "出价金额大于会员价,建议您直接搜索该商品直接购买！";
							maps.put("status", "0");
							maps.put("msg", msg.toString());
							super.renderJson(response, JSON.toJSONString(maps));
							return null;
						}
						int z = super.getFacade().getEcAuctionBuyService().createEcAuctionBuyForAuction(ecAuctionBuy);
						if(z==1){
							msg = "恭喜,出价成功！";
							maps.put("status", "1");
							maps.put("msg", msg.toString());
							super.renderJson(response, JSON.toJSONString(maps));
							return null;
						}else if(z==0){
							msg = "对不起,活动已经结束";
						}else if(z==-1){
							msg="此次出价失败!"; 
						}  
					}catch(Exception e){
						msg = "对不起,发生错误";
					}
				}
			}else{
				msg = "对不起,活动已经结束";
			}
		}else{
			 msg = "对不起,发生错误，请重新进入当前页面"; 
		} 
		maps.put("status", "0");
		maps.put("msg", msg.toString());
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxGETMain(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String buy_json_object = (String) dynaBean.get("buy_json_object"); 
		String msg="";
		Map<String, Object> maps = new HashMap<String, Object>();
		// 取当前请求数据的用户所在地  
		if (StringUtils.isBlank(buy_json_object)){
			 msg = "对不起,发生错误"; 
			 maps.put("status", "0");
			 maps.put("msg", msg.toString());
			 super.renderJson(response, JSON.toJSONString(maps));
			 return null;
		}
		JSONArray json = JSON.parseArray((new DESPlus().decrypt(buy_json_object))); 
		Long auction_id =null;
		if (json != null && json.size() > 0){
			JSONObject obj = json.getJSONObject(0);
			auction_id = obj.getLong("auction_id"); // 商品ID 
		}
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		
		if(auction_id!=null){
			EcAuctionMain entity = new EcAuctionMain();
			entity.setId(Long.valueOf(auction_id));
			entity.setOwn_sys(ecUser.getUser_type());
			entity.setIs_pub(new Integer(1));
			entity.setIs_del(new Integer(0)); 
			entity = super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity);
			if(entity!=null){
				maps.put("status", "1");
				EcAuctionBuy ecAuctionBuy = new EcAuctionBuy(); 
				int buy_count=0;
				BigDecimal price = entity.getAuction_price();
				if(entity.getEcAuctionBuyList()!=null&&entity.getEcAuctionBuyList().size()>0){
					buy_count = entity.getEcAuctionBuyList().size();
					ecAuctionBuy = entity.getEcAuctionBuyList().get(0);
				}
				if(ecAuctionBuy!=null&&ecAuctionBuy.getId()!=null){
					 price = ecAuctionBuy.getPrice();
					 maps.put("auctionbuy", "1");
					 maps.put("auctionbuy_price", ecAuctionBuy.getPrice());
					 maps.put("auctionbuy_tradex_index", ecAuctionBuy.getTrade_index());
					 maps.put("auctionbuy_user_name", ecAuctionBuy.getUser_name());
					 maps.put("auctionbuy_add_date", CrcUtil.convertTimeToStr(ecAuctionBuy.getAdd_date(), "yyyy-MM-dd HH:mm:ss"));
				}else{
					maps.put("auctionbuy", "0");
				}
				
				
				Calendar cEnd = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(entity.getAuction_sysdate());
				cEnd.setTime(entity.getAuction_end_time());
				if(entity.getDelay_num()!=null&&entity.getDelay_time()!=null&&entity.getDelay_num().intValue()>0&&entity.getDelay_time().intValue()>0){
					cEnd.add(13, entity.getDelay_num().intValue()*entity.getDelay_time().intValue());
				}
				Long timeRemains=cEnd.getTimeInMillis() - calendar.getTimeInMillis();
				maps.put("timeRemains", timeRemains);
				maps.put("price", price);
				maps.put("add_price", entity.getAdd_price());
				maps.put("delay_num", entity.getDelay_num());
				maps.put("buy_count", buy_count);
				
				super.renderJson(response, JSON.toJSONString(maps));
				return null;
			}
		}
		return null;
	}
}