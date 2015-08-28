package com.ebiz.mmt.web.struts.wap;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.CookieGenerator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.domain.EcUserFavotrites;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants; 
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.ShoppingCarJsonUtil;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-14
 */
public class ShoppingCarAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}

		// 从数据库中取购物车的信息，这个方法要求登陆所以COOKIE中的购物车信息登陆的时候同步
		EcShoppingCart ecShoppingCart = new EcShoppingCart();
		ecShoppingCart.setUser_id(ecUser.getId());
		List<EcShoppingCart> ecShoppingCartList = super.getFacade().getEcShoppingCartService().getEcShoppingCartList(ecShoppingCart);
		List<KonkaBcompPd> konkaBcompPdList = new ArrayList<KonkaBcompPd>();
		for (EcShoppingCart temp : ecShoppingCartList) {
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(temp.getGoods_id());
			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
			if (null == konkaBcompPd) {//如果未找到商品 则删除购物车
				EcShoppingCart s =new EcShoppingCart();
				s.setOwn_sys(ecUser.getUser_type());
				s.setId(temp.getId());
				super.getFacade().getEcShoppingCartService().removeEcShoppingCart(s);
				Cookie cookie = new Cookie("SHOPING_CAR_COOKIE",null); 
				cookie.setMaxAge(0);  
				response.addCookie(cookie);
				break; 
			} 
			
			konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]); // 处理主图
			konkaBcompPd.setEcShoppingCart(temp);	// 保存购物车

			// 保存选中的服务ID信息
			if (StringUtils.isNotBlank(temp.getService_ids())) {
				konkaBcompPd.setServiceIds(StringUtils.split(temp.getService_ids(), "|"));
				konkaBcompPd.getMap().put("service_ids", temp.getService_ids());
			}
			String p_index = temp.getP_index().toString();
			request.setAttribute("p_index", p_index);

			// 处理地区信息
			Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

			// 查询产品价格
			List<EcGoodsPrice> ecGoodsPriceList =null;
			if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{
				ecGoodsPriceList =	getEcGoodsPriceListWithPindexAndGoodsId(ecUser.getUser_type(),  konkaBcompPd.getId(), p_index_array, ecUser.getDept_id(),ecUser.getCust_id());
			}else{
				ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsIdNew(ecUser.getUser_type(),  konkaBcompPd.getId(), p_index_array, ecUser.getDept_id(),ecUser.getCust_id(),ecUser.getPlat_sys());
			}
			
			if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()){
				konkaBcompPd.setEcGoodsPrice(ecGoodsPriceList.get(0));
			}

			// 查询库存
			List<EcStocks> ecStocksList = null;
			if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{		
				ecStocksList = getEcStocksListWithPindexAndGoodsId(ecUser.getUser_type(), konkaBcompPd.getId(), p_index_array, 0L);
			}else{
				ecStocksList = getEcStocksListWithPindexAndGoodsIdNew(ecUser.getUser_type(),  konkaBcompPd.getId(), p_index_array, 0L,ecUser.getPlat_sys());
			}
			int r3store_num=0;
			if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{
				if(konkaBcompPd.getProd_type()!=null&&konkaBcompPd.getProd_type().intValue()==0){
					try{
						if(konkaBcompPd!=null){
							r3store_num=super.getStockCount(super.getCtxPath(request),konkaBcompPd.getPd_sn()).intValue();
						}
					}catch(Exception e){}; 
				}
			}
			
			if (null != ecStocksList && 0 != ecStocksList.size()){
				EcStocks ecStocks =new EcStocks();
				ecStocks=ecStocksList.get(0); 
				ecStocks.setStocks(new Long(ecStocks.getStocks().intValue()+r3store_num)); 
				konkaBcompPd.setEcStocks(ecStocks); 
			}

			// 查询绑定服务
			EcBindingPd ecBindingPd = new EcBindingPd();
			ecBindingPd.setBinding_type(0);
			ecBindingPd.getMap().put("goods_id", konkaBcompPd.getId());
			List<EcBindingPd> ecBindingPdListForService = getFacade().getEcBindingPdService().getEcBindingPdWithGoodsIdAndTypeList(ecBindingPd);
			konkaBcompPd.setEcBindingPdListForService(ecBindingPdListForService);

			// 查询绑定套餐
			ecBindingPd.setBinding_type(1);
			List<EcBindingPd> ecBindingPdListForPackages = getFacade().getEcBindingPdService().getEcBindingPdWithGoodsIdAndTypeList(
					ecBindingPd);
			konkaBcompPd.setEcBindingPdListForPackages(ecBindingPdListForPackages); 
			konkaBcompPdList.add(konkaBcompPd);
		}

		request.setAttribute("konkaBcompPdList", konkaBcompPdList);
		return new ActionForward("/../wap/ShoppingCar/step_one.jsp");
	}

	public ActionForward buyByGoodsId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String p_index = (String) dynaBean.get("p_index");
		String buy_num = (String) dynaBean.get("buy_num"); // 购买数量
		String price = (String) dynaBean.get("price"); // 购买单价
		String[] serviceIds = request.getParameterValues("service");
		request.getSession().setAttribute("p_index", p_index);
		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if ("".equals(price)||price==null) {
			String msg = super.getMessage(request, "ec.bcomp.pd.noprice");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');");
			return null;
		}

		// 取当前请求数据的用户所在地
		if (StringUtils.isEmpty(p_index)){
			p_index = super.getPIndexByRequest(request);
		}
		request.setAttribute("p_index", p_index);

		// 处理地区信息
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
		
		// 查询商品详细信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		konkaBcompPd.setId(Long.valueOf(goods_id));
		konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
		if (null == konkaBcompPd) {
			String msg = super.getMessage(request, "ec.bcomp.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 处理主图
		konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]);

		// 虚拟化购物车保存
		EcShoppingCart ecShoppingCart = new EcShoppingCart();
		ecShoppingCart.setGoods_id(Long.valueOf(goods_id));
		ecShoppingCart.setPd_num(Integer.valueOf(buy_num));
		ecShoppingCart.setPrice(new BigDecimal(price));
		ecShoppingCart.setMd_name(konkaBcompPd.getPd_name());
		ecShoppingCart.setOwn_sys(ecUser.getUser_type());; // 1 - 工卡
		ecShoppingCart.setImg_url(konkaBcompPd.getMain_pic());
		ecShoppingCart.setP_index(Long.valueOf(p_index));
		ecShoppingCart.setService_ids(StringUtils.join(serviceIds, "|"));
		ecShoppingCart.setUser_id(ecUser.getId());
		ecShoppingCart.setAdd_date(new Date());
		konkaBcompPd.setEcShoppingCart(ecShoppingCart);

		// 保存选中的服务ID信息
		konkaBcompPd.setServiceIds(serviceIds);
		konkaBcompPd.getMap().put("service_ids", StringUtils.join(serviceIds, "|"));
		
		// 取全部购物车
		EcShoppingCart esc = new EcShoppingCart();
		esc.setOwn_sys(ecUser.getUser_type());
		esc.setUser_id(ecUser.getId());
		List<EcShoppingCart> escList = getFacade().getEcShoppingCartService().getEcShoppingCartList(esc);
		boolean flag = false; //状态位标识需要的商品是否已经添加
		if (null != escList && 0 != escList.size()){
				for (EcShoppingCart cart : escList) {
					if (cart.getGoods_id().toString().equals(goods_id)) {
						flag = true; 
						cart.setPd_num(ecShoppingCart.getPd_num()); 
						cart.setMd_name(ecShoppingCart.getMd_name()); 
						cart.setPrice(ecShoppingCart.getPrice()); 
						cart.setImg_url(ecShoppingCart.getImg_url());
						cart.setService_ids(StringUtils.join(serviceIds, "|"));
					}
				}
		}
		if(!flag){
				escList.add(ecShoppingCart);
		}

		// 查询产品价格
		List<EcGoodsPrice> ecGoodsPriceList = null;
		if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{		
			ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsId(ecUser.getUser_type(), konkaBcompPd.getId(), p_index_array, ecUser.getDept_id(),ecUser.getCust_id());
		}else{
			ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsIdNew(ecUser.getUser_type(), konkaBcompPd.getId(), p_index_array, ecUser.getDept_id(),ecUser.getCust_id(),ecUser.getPlat_sys());
		}
		if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()){
			konkaBcompPd.setEcGoodsPrice(ecGoodsPriceList.get(0));
		}else{
			String msg = super.getMessage(request, "ec.bcomp.pd.noprice");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
	 
		// 查询库存
		List<EcStocks> ecStocksList =null;
		if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{				
			ecStocksList=getEcStocksListWithPindexAndGoodsId(ecUser.getUser_type(), konkaBcompPd.getId(), p_index_array, 0L);
		}else{
			ecStocksList=getEcStocksListWithPindexAndGoodsIdNew(ecUser.getUser_type(), konkaBcompPd.getId(), p_index_array, 0L,ecUser.getPlat_sys());
		}
		int r3store_num=0;
		if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{		
			if(konkaBcompPd.getProd_type()!=null&&konkaBcompPd.getProd_type().intValue()==0){
				try{
					if(konkaBcompPd!=null){
						r3store_num=super.getStockCount(super.getCtxPath(request),konkaBcompPd.getPd_sn()).intValue();
					}
				}catch(Exception e){}; 
			}
		}
		if (null != ecStocksList && 0 != ecStocksList.size()){
			EcStocks ecStocks =new EcStocks();
			ecStocks=ecStocksList.get(0); 
			ecStocks.setStocks(new Long(ecStocks.getStocks().intValue()+r3store_num)); 
			konkaBcompPd.setEcStocks(ecStocks); 
		}

		// 查询绑定服务
		EcBindingPd ecBindingPd = new EcBindingPd();
		ecBindingPd.setBinding_type(0);
		ecBindingPd.getMap().put("goods_id", konkaBcompPd.getId());
		List<EcBindingPd> ecBindingPdListForService = getFacade().getEcBindingPdService().getEcBindingPdWithGoodsIdAndTypeList(ecBindingPd);
		konkaBcompPd.setEcBindingPdListForService(ecBindingPdListForService);

		// 查询绑定套餐
		ecBindingPd.setBinding_type(1);
		List<EcBindingPd> ecBindingPdListForPackages = getFacade().getEcBindingPdService().getEcBindingPdWithGoodsIdAndTypeList(ecBindingPd);
		konkaBcompPd.setEcBindingPdListForPackages(ecBindingPdListForPackages);

		// 放入列表，兼容购物车
		List<KonkaBcompPd> konkaBcompPdList = new ArrayList<KonkaBcompPd>();
		konkaBcompPdList.add(konkaBcompPd);
		request.setAttribute("konkaBcompPdList", konkaBcompPdList);
		
		getFacade().getEcShoppingCartService().modifyOrCreateEcShoppingCart(escList);
		List<HashMap<String, String>> shoppingCarTableList = new ArrayList<HashMap<String, String>>(); 
		for (EcShoppingCart cart : escList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("goods_id", "" + cart.getGoods_id());
				map.put("md_name", "" + cart.getMd_name());
				map.put("buy_num", "" + cart.getPd_num());
				map.put("price", "" + cart.getPrice());
				map.put("img_url", "" + cart.getImg_url());
				map.put("p_index", "" + cart.getP_index());
				if( cart.getService_ids()!=null){
					map.put("service_ids", "" + cart.getService_ids());
				}else{
					map.put("service_ids", "");
				}
				shoppingCarTableList.add(map);
		} 
		Cookie cookie = new Cookie("SHOPING_CAR_COOKIE",null); 
		cookie.setMaxAge(0);  
		response.addCookie(cookie);  
		ShoppingCarJsonUtil shoppingCarUtil = new ShoppingCarJsonUtil();
		String shopingCarJson = shoppingCarUtil.toGoodsJsonsStr(shoppingCarTableList);
		CookieGenerator cGen = new CookieGenerator(); 
		cGen.setCookieMaxAge(60 * 60 * 24 * 14);
		cGen.setCookieName("SHOPING_CAR_COOKIE"); 
		cGen.addCookie(response, URLEncoder.encode(shopingCarJson, "UTF-8")); 
		return new ActionForward("/../wap/ShoppingCar/step_one.jsp");
	}

	public ActionForward stepTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");
		String[] goods_ids = request.getParameterValues("goods_id");

		// 判断是不是有商品
		if (0 == goods_ids.length) {
			String msg = super.getMessage(request, "ec.bcomp.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}

		// 取当前请求数据的用户所在地
		if (StringUtils.isEmpty(p_index))
			p_index = super.getPIndexByRequest(request);
		request.setAttribute("p_index", p_index);
		request.setAttribute("p_name", super.getPIndexName(p_index, ""));
		Integer is_hdfk=new Integer(0);//货到付款 非电视 不能使用货到付款
		// 循环处理数据
		List<Map<String, String>> json = new ArrayList<Map<String, String>>();
		List<KonkaBcompPd> konkaBcompPdList = new ArrayList<KonkaBcompPd>();
		String stores="";
		for (String id : goods_ids) {
			Map<String, String> map = new HashMap<String, String>();
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(Long.valueOf(id));
			konkaBcompPd=super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
			
			if(konkaBcompPd!=null&&konkaBcompPd.getProd_type().intValue()!=0){
				 is_hdfk=new Integer(1);
			}
			if (konkaBcompPd != null && konkaBcompPd.getProd_type().intValue() == 9) {
				request.setAttribute("is_dell", new Integer(1));
			}
			map.put("goods_id", id);
			konkaBcompPd.setPd_name((String) dynaBean.get("pd_name_" + id));
			map.put("pd_name", konkaBcompPd.getPd_name());
			konkaBcompPd.getMap().put("price", (String) dynaBean.get("price_" + id));
			map.put("price", (String) dynaBean.get("price_" + id));
			if (StringUtils.isNotBlank((String) dynaBean.get("service_ids_" + id)))
				konkaBcompPd.setServiceIds(StringUtils.split((String) dynaBean.get("service_ids_" + id), "|"));
			konkaBcompPd.getMap().put("service_ids", (String) dynaBean.get("service_ids_" + id));
			map.put("service_ids", (String) dynaBean.get("service_ids_" + id));
			konkaBcompPd.getMap().put("buy_num", (String) dynaBean.get("buy_num_" + id));
			map.put("buy_num", (String) dynaBean.get("buy_num_" + id));
			map.put("store_id", (String) dynaBean.get("store_id_" + id));
			if("".equals(stores)){
				stores = map.get("store_id");	
			}else{
				stores += ","+map.get("store_id");
			}
			// 查询绑定服务
			EcBindingPd ecBindingPd = new EcBindingPd();
			ecBindingPd.setBinding_type(0);
			ecBindingPd.getMap().put("goods_id", konkaBcompPd.getId());
			List<EcBindingPd> ecBindingPdListForService = getFacade().getEcBindingPdService().getEcBindingPdWithGoodsIdAndTypeList(
					ecBindingPd);
			konkaBcompPd.setEcBindingPdListForService(ecBindingPdListForService);

			json.add(map);
			konkaBcompPdList.add(konkaBcompPd);
		}
		request.setAttribute("stores", stores);
		request.setAttribute("is_hdfk", is_hdfk);
		// 将需要购买的商品数据加密，然后到前台
		request.setAttribute("buy_json_object",new DESPlus().encrypt(URLEncoder.encode(JSON.toJSONString(json),"UTF-8")));
		request.setAttribute("konkaBcompPdList", konkaBcompPdList); 
		 
		return new ActionForward("/../wap/ShoppingCar/step_two.jsp");
	}

	public ActionForward createOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)) {
			String msg = "请勿重复提交订单";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}
		resetToken(request);
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
		String logistic_sn = (String) dynaBean.get("logistic_sn");
		String[] vouchers = request.getParameterValues("vouchers");
		
		String p_index1 = (String) dynaBean.get("p_index1");
		String p_index2 = (String) dynaBean.get("p_index2");
		if(p_index2!=null&&!"".equals(p_index2)){
			p_index=p_index2;
		}else if(p_index1!=null&&!"".equals(p_index1)){
			p_index=p_index1;
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
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}

		// 交易流水号
		Date now = new Date();
		String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss") + StringUtils.substring(String.valueOf(now.getTime()), 7);

		// 初始化数据 STARTING...
		PshowOrder pshowOrder = new PshowOrder();
		pshowOrder.setOrder_type(0);
		pshowOrder.setIs_del(0);
		pshowOrder.setPay_way(Integer.valueOf(pay_way)); // 0：货到付款 1：在线支付 2: 支付宝 3:银联 4:财付通
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
		if(deliver_is_call!=null){
		pshowOrder.setDeliver_is_call(Integer.valueOf(deliver_is_call));
		}
		if (GenericValidator.isInt(bill_is_add))
			pshowOrder.setBill_is_add(Integer.valueOf(bill_is_add));
		if(GenericValidator.isInt(bill_type))
			pshowOrder.setBill_type(Integer.valueOf(bill_type));
		if(GenericValidator.isInt(bill_head))
			pshowOrder.setBill_head(Integer.valueOf(bill_head));
		if(StringUtils.isNotEmpty(bill_company))
			pshowOrder.setBill_company(bill_company);
		if(GenericValidator.isInt(bill_content))
			pshowOrder.setBill_content(Integer.valueOf(bill_content));
		// 备注信息
		if (StringUtils.isNotBlank(logistic_sn)) {
			pshowOrder.setLogistic_sn(logistic_sn);
		}
		// 处理商品信息
		BigDecimal allPrice = new BigDecimal("0");
		logger.info("-----buy_json_object--->{}",buy_json_object);
		String str=new DESPlus().decrypt(buy_json_object); 
		str=URLDecoder.decode(str,"UTF-8");
		JSONArray json = JSON.parseArray(str); 
		logger.info("-----json--->{}",json.toString());
		BigDecimal allIntegral = new BigDecimal("0");
		List<PshowOrdeDetails> pshowOrdeDetailsList = new ArrayList<PshowOrdeDetails>();
		for (int i = 0; i < json.size(); i++) {
			JSONObject obj = json.getJSONObject(i);
			Long goods_id = obj.getLong("goods_id"); // 商品ID
			Long buy_num = obj.getLong("buy_num"); // 购买数量
			String pd_name = obj.getString("pd_name"); // 商品名称
			BigDecimal price = obj.getBigDecimal("price"); // 购买单价
			String service_ids = obj.getString("service_ids"); // 订购的增值服务
			String store_id = obj.getString("store_id"); // 仓库ID

			// 查询商品信息
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(goods_id);
			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
			
			// 查询商品佣金、返利
			EcBcompPdRebates ecBcompPdRebates = new EcBcompPdRebates();
			ecBcompPdRebates.setIs_del(0);
			ecBcompPdRebates.setGoods_id(konkaBcompPd.getId());
			ecBcompPdRebates.setOwn_sys(ecUser.getUser_type());
			if(ecUser.getUser_type().intValue()==2){
				ecBcompPdRebates.setDept_id(ecUser.getDept_id());
				ecBcompPdRebates.setC_id(ecUser.getCust_id());
			}
			ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);
			//计算奖励积分
			BigDecimal x= new BigDecimal("50");
			//银卡会员获取奖励积分=付款金额*50 
			if(ecUser.getEcBaseCardLevel()!=null&&ecUser.getEcBaseCardLevel().getCard_type_discount()!=null){
			 x= ecUser.getEcBaseCardLevel().getCard_type_discount();
			} 
			
			// 订单明细
			PshowOrdeDetails pod = new PshowOrdeDetails();
			pod.setState(0);
			pod.setPd_id(konkaBcompPd.getId());
			pod.setPd_name(konkaBcompPd.getPd_name());
			pod.setNum(buy_num);
			pod.setPrice(price);
			BigDecimal service_price = new BigDecimal("0.0");
			// 明细中的商品订购的增值服务
			if (StringUtils.isNotBlank(service_ids)) {
				List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = new ArrayList<EcBindingPdOrdeDetails>();
				String[] service_ids_array = StringUtils.split(service_ids, "|");
				for (String binding_pd_id : service_ids_array) {
					if(StringUtils.isBlank(binding_pd_id) || "null".equals(binding_pd_id))
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
						ecBindingPdOrdeDetails.setTotal_price(ecBindingPdOrdeDetails.getPrice().multiply(new BigDecimal(buy_num.toString())));
						ecBindingPdOrdeDetailsList.add(ecBindingPdOrdeDetails);
						service_price = service_price.add(ecBindingPdOrdeDetails.getTotal_price());
					}
				}
				pod.setEcBindingPdOrdeDetailsList(ecBindingPdOrdeDetailsList);
			}
			
			pod.setTotal_price(pod.getPrice().multiply(new BigDecimal(pod.getNum().toString())).add(service_price).setScale(2, BigDecimal.ROUND_HALF_UP)); // 明细总价
			pod.setStore_id(Long.valueOf(store_id));
			// 处理积分
			if (null != konkaBcompPd.getIntegral()&& konkaBcompPd.getIntegral().longValue()>0) {
				if (konkaBcompPd.getIntegral_type() == 0) { // 按比例
					pod.setIntegral(price.multiply(new BigDecimal(buy_num)).multiply(new BigDecimal(konkaBcompPd.getIntegral())).multiply(x).divide(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if (konkaBcompPd.getIntegral_type() == 1) { // 按固定
					pod.setIntegral(new BigDecimal(buy_num).multiply(new BigDecimal(konkaBcompPd.getIntegral())).multiply(x).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}else{
				pod.setIntegral(price.multiply(new BigDecimal(buy_num)).multiply(x).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			// 处理返利
			if(null != ecBcompPdRebates){
				if (ecBcompPdRebates.getB_type() == 0){ // 按比例
					pod.setRebates(price.multiply(new BigDecimal(buy_num)).multiply(ecBcompPdRebates.getB_value()).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if (ecBcompPdRebates.getB_type() == 1){ // 按固定
					pod.setRebates(new BigDecimal(buy_num).multiply(ecBcompPdRebates.getB_value()).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			} 
			
			allPrice = allPrice.add(pod.getTotal_price()); // 计算订单总价
			allIntegral = allIntegral.add(pod.getIntegral());// 计算订单总积分 
			
			pshowOrdeDetailsList.add(pod);
		}
		pshowOrder.setPshowOrdeDetailsList(pshowOrdeDetailsList); 
		pshowOrder.setTotal_price(allPrice); 
		pshowOrder.setIntegral(allIntegral);//订单总奖励积分
		
		BigDecimal deduPrice = new BigDecimal("0");//抵扣金额
		List<EcVouchers> ecVouchersList= new ArrayList<EcVouchers>();
		if(vouchers!=null&&vouchers.length>0){
			for(String vouchers_id:vouchers){
				EcVouchers  ecVouchers =new EcVouchers();
				ecVouchers.setId(new Long(vouchers_id));
				ecVouchers.setUser_id(ecUser.getId());
				ecVouchers = super.getFacade().getEcVouchersService().getEcVouchers(ecVouchers);
				if(ecVouchers!=null) {
					deduPrice=deduPrice.add(ecVouchers.getPrice());		
					ecVouchersList.add(ecVouchers);
				}
			}
			pshowOrder.setEcVouchersList(ecVouchersList);
		}
		
		BigDecimal payPrice = allPrice.subtract(deduPrice);//订单付款金额=订单总价-抵扣金额
		if(payPrice.doubleValue()<1.01){
			payPrice = new BigDecimal("1.0");
			//pshowOrder.setState(5);
		}		
		pshowOrder.setDedu_price(deduPrice);		
		pshowOrder.setPay_price(payPrice);//订单付款金额=订单总价-抵扣金额 
		 if("2".equals(ecUser.getMap().get("web_type"))){
	    	pshowOrder.setState(0);
	    	pshowOrder.setPay_way(new Integer(9));
	    }
		// 初始化结束

		// 建立订单信息
		super.getFacade().getPshowOrderService().createPshowOrderWithDetails(pshowOrder);
		logger.info("----------------------------->Order success");
		
		// 清空购物车信息
		Cookie cookie = new Cookie("SHOPING_CAR_COOKIE",null); 
		cookie.setMaxAge(0);  
		response.addCookie(cookie);  
		EcShoppingCart ecShoppingCart = new EcShoppingCart();
		ecShoppingCart.setUser_id(ecUser.getId());
		ecShoppingCart.setOwn_sys(ecUser.getUser_type());	
		getFacade().getEcShoppingCartService().removeEcShoppingCartWithGoodsIdAndUserId(ecShoppingCart);
		logger.info("----------------------------->Shopping car empty success");
		
		if(payPrice.doubleValue()<0.001|| pshowOrder.getPay_way()==0 || pshowOrder.getPay_way()==1 || pshowOrder.getPay_way()==9){
			super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)+ "/wap/Payment.do?method=view&trade_index=" + trade_index + "';}");
			return null;
		}
		super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)+ "/wap/Payment.do?trade_index=" + trade_index + "&pay_way=" + pshowOrder.getPay_way() + "';}");
		return null;
	}

	public ActionForward ajaxAddCar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String pd_num = (String) dynaBean.get("buy_num");
		String price = (String) dynaBean.get("price");
		String md_name = java.net.URLDecoder.decode((String) dynaBean.get("md_name"), Constants.SYS_ENCODING);
		String img_url = (String) dynaBean.get("img_url");
		String p_index = (String) dynaBean.get("p_index");
		String service_ids = (String) dynaBean.get("service_ids");
		request.getSession().setAttribute("p_index", p_index); 
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();

		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}
		if ("".equals(price)||price==null) {
			String msg = super.getMessage(request, "ec.bcomp.pd.noprice");
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

		// 取全部购物才
		EcShoppingCart esc = new EcShoppingCart();
		esc.setOwn_sys(ecUser.getUser_type());;
		esc.setUser_id(ecUser.getId());
		List<EcShoppingCart> escList = getFacade().getEcShoppingCartService().getEcShoppingCartList(esc);
		boolean flag = false; // 状态位标识需要的商品是否已经添加
		if (null != escList && 0 != escList.size())
			for (EcShoppingCart ecShoppingCart : escList) {
				if (ecShoppingCart.getGoods_id().toString().equals(goods_id)) {
					flag = true;
					if (GenericValidator.isInt(pd_num))
						ecShoppingCart.setPd_num(Integer.valueOf(pd_num));
					ecShoppingCart.setMd_name(md_name);
					if (GenericValidator.isDouble(price))
						ecShoppingCart.setPrice(new BigDecimal(price));
					ecShoppingCart.setImg_url(img_url);
				}
			}

		// 添加的为新商品
		if (!flag) {
			if (null != escList && escList.size() >= 10) {
				String msg = super.getMessage(request, "ec.shopingcar.full");
				maps.put("status", "0");
				maps.put("msg", msg);
				super.renderJson(response, JSON.toJSONString(maps));
				return null;
			}

			// 加入购物车
			EcShoppingCart ecShoppingCart = new EcShoppingCart();
			ecShoppingCart.setGoods_id(Long.valueOf(goods_id));
			ecShoppingCart.setUser_id(ecUser.getId());
			if (GenericValidator.isInt(pd_num))
				ecShoppingCart.setPd_num(Integer.valueOf(pd_num));
			if (GenericValidator.isDouble(price))
				ecShoppingCart.setPrice(new BigDecimal(price));
			ecShoppingCart.setAdd_date(new Date());
			ecShoppingCart.setMd_name(md_name);
			ecShoppingCart.setOwn_sys(ecUser.getUser_type()); // 1 - 工卡
			ecShoppingCart.setImg_url(img_url);
			ecShoppingCart.setP_index(Long.valueOf(p_index));
			if(service_ids==null||"null".equals(service_ids)){
				service_ids="";
			}
			ecShoppingCart.setService_ids(service_ids);

			escList.add(ecShoppingCart);

			getFacade().getEcShoppingCartService().modifyOrCreateEcShoppingCart(escList);
		} else {
			getFacade().getEcShoppingCartService().modifyOrCreateEcShoppingCart(escList);
		}

		maps.put("status", "1");
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxDelCar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String goods_ids = (String) dynaBean.get("goods_ids"); 

		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();

		// 判断产品ID是否丢失
		if(goods_ids!=null&&!"".equals(goods_ids)){
			
		}else if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}
		Cookie cookie = new Cookie("SHOPING_CAR_COOKIE",null); 
		cookie.setMaxAge(0);  
		response.addCookie(cookie);  
		// 删除当前登录用户中的指定购物车

		EcShoppingCart esc = new EcShoppingCart();
		if (GenericValidator.isLong(goods_id)){
			esc = new EcShoppingCart();
			esc.setOwn_sys(ecUser.getUser_type());
			esc.setGoods_id(Long.valueOf(goods_id));
			esc.setUser_id(ecUser.getId());
			getFacade().getEcShoppingCartService().removeEcShoppingCartWithGoodsIdAndUserId(esc);
		}else if(goods_ids!=null){
			String[] ids=goods_ids.split(",");
			for(int i=0;i<ids.length;i++){
				if(GenericValidator.isLong(ids[i])){
				esc = new EcShoppingCart();
				esc.setOwn_sys(ecUser.getUser_type());
				esc.setGoods_id(Long.valueOf(ids[i]));
				esc.setUser_id(ecUser.getId());
				getFacade().getEcShoppingCartService().removeEcShoppingCartWithGoodsIdAndUserId(esc);
				}
			}		
		}
		
		EcShoppingCart shoppingCar = new EcShoppingCart();
		shoppingCar.setUser_id(ecUser.getId());
		shoppingCar.setOwn_sys(ecUser.getUser_type());
		List<EcShoppingCart> ecShoppingcarList = super.getFacade().getEcShoppingCartService().getEcShoppingCartList(shoppingCar);
		List<HashMap<String, String>> shoppingCarTableList = new ArrayList<HashMap<String, String>>();
		if (ecShoppingcarList != null && ecShoppingcarList.size() > 0) {
			for (EcShoppingCart ecShoppingCart : ecShoppingcarList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("goods_id", "" + ecShoppingCart.getGoods_id());
				map.put("md_name", "" + ecShoppingCart.getMd_name());
				map.put("buy_num", "" + ecShoppingCart.getPd_num());
				map.put("price", "" + ecShoppingCart.getPrice());
				map.put("img_url", "" + ecShoppingCart.getImg_url());
				map.put("p_index", "" + ecShoppingCart.getP_index());
				map.put("service_ids", "" + ecShoppingCart.getService_ids());
				shoppingCarTableList.add(map);
			}
		}
		ShoppingCarJsonUtil shoppingCarUtil = new ShoppingCarJsonUtil();
		String shopingCarJson = shoppingCarUtil.toGoodsJsonsStr(shoppingCarTableList);
		CookieGenerator cGen = new CookieGenerator(); 
		cGen.setCookieMaxAge(60 * 60 * 24 * 14);
		cGen.setCookieName("SHOPING_CAR_COOKIE"); 
		cGen.addCookie(response, URLEncoder.encode(shopingCarJson, "UTF-8"));
		
		maps.put("status", "1");
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxAddFavotrites(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String user_id = (String) dynaBean.get("user_id");
		String price = (String) dynaBean.get("price");
		String md_name = (String) dynaBean.get("md_name");
		String img_url = (String) dynaBean.get("img_url");
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();

		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

		// 从Session中取用户并判断
		if (!GenericValidator.isLong(user_id)) {
			String msg = super.getMessage(request, "ec.nologin");
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}

		// 查询关注的产品是否存在
		EcUserFavotrites temp = new EcUserFavotrites();
		temp.setUser_id(Long.valueOf(user_id));
		temp.setOwn_sys(ecUser.getUser_type());
		temp.setGoods_id(Long.valueOf(goods_id));
		temp = super.getFacade().getEcUserFavotritesService().getEcUserFavotrites(temp);
		BigDecimal _price = new BigDecimal(0.0);
		if(price!=null&&!"".equals(price)){
			_price = new BigDecimal(price);
		}
		if (null == temp) { // 不存在新增
			// 初始化数据并保持
			EcUserFavotrites euf = new EcUserFavotrites();
			euf.setUser_id(Long.valueOf(user_id));
			euf.setOwn_sys(ecUser.getUser_type());
			euf.setGoods_id(Long.valueOf(goods_id));
			euf.setAdd_date(new Date());
			euf.setMd_name(md_name);
			euf.setPrice(_price);
			euf.setImg_url(img_url);
			getFacade().getEcUserFavotritesService().createEcUserFavotrites(euf);
		} else { // 已存在修改
			temp.setAdd_date(new Date());
			temp.setMd_name(md_name);
			temp.setPrice(_price);
			temp.setImg_url(img_url);
			getFacade().getEcUserFavotritesService().modifyEcUserFavotrites(temp);
		}

		maps.put("status", "1");
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxExpress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String p_index = (String) dynaBean.get("p_index");
		String stores = (String) dynaBean.get("stores"); 
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();

		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(p_index)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			maps.put("status", "0");
			maps.put("msg", msg);
			super.renderJson(response, JSON.toJSONString(maps));
			return null;
		}
		String[] store = stores.split(",");
		maps.put("maxDay","0");
	    if(store!=null&&store.length>0){
	    	
	    	for(int i=0;i<store.length;i++){
	    	EcBaseExpressReachDay express = new EcBaseExpressReachDay();
			express.setArea_code(p_index);
			express.setStore_id(new Long(store[i]));
//			express.setMax_reach_day(-1);
//			Long c=super.getFacade().getEcBaseExpressReachDayService().getEcBaseExpressReachDayCount(express);
//			if(c.intValue()>0){
//			maps.put("status", "0");
//			maps.put("msg", super.getMessage(request, "konka.express.area.error"));
//			super.renderJson(response, JSON.toJSONString(maps));
//			return null;
//			}
			List<EcBaseExpressReachDay> list = super.getFacade().getEcBaseExpressReachDayService().getEcBaseExpressReachDayList(express);
				for(EcBaseExpressReachDay expressReachDay:list){
					if(expressReachDay.getMax_reach_day().intValue()==-1){
						maps.put("status", "0");
						maps.put("msg", super.getMessage(request, "konka.express.area.error"));
						super.renderJson(response, JSON.toJSONString(maps));
						return null;
					}else{
						maps.put("maxDay",""+expressReachDay.getMax_reach_day());
						maps.put("express_desc",""+expressReachDay.getDesc());
					}
				}
	    	} 
	    }
		 
		maps.put("status", "1");
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxAddr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("addr_id"); 
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();

	    EcUserAddrs ecUserAddr = new EcUserAddrs(); 
	    
	    ecUserAddr.setId(new Long(id));
	    ecUserAddr = super.getFacade().getEcUserAddrsService().getEcUserAddrs(ecUserAddr);
		if(ecUserAddr!=null){	
			maps.put("rel_name", ecUserAddr.getRel_name());
			maps.put("city", ecUserAddr.getRel_city().toString());
			maps.put("country", ecUserAddr.getRel_region().toString().substring(0,6));
			maps.put("region", ecUserAddr.getRel_region().toString());
			maps.put("rel_tel", ecUserAddr.getRel_tel());
			maps.put("rel_phone", ecUserAddr.getRel_phone());
			maps.put("rel_addr", ecUserAddr.getRel_addr());
			maps.put("rel_zip", ecUserAddr.getRel_zip()==null ?"":ecUserAddr.getRel_zip().toString());
			Long p_index  =ecUserAddr.getRel_city(); 
			BaseProvinceListFour p = new  BaseProvinceListFour();
			p.setP_index(p_index);
			p = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(p);
			
			if(p!=null){
				maps.put("p_name", p.getFull_name());
			}
			
		} 
		maps.put("status", "1");
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxScores(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pay_pwd = (String) dynaBean.get("pay_pwd"); 
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();
		String msg="";
		if(pay_pwd!=null&&pay_pwd.equals(ecUser.getMap().get("pay_pwd").toString())){
			Long score= getIntegralByUserId(ecUser.getId()); 
			maps.put("status", "0");
			maps.put("score", score.toString()); 
		}else{
			msg="支付密码错误";
			maps.put("status", "1");
		}
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward ajaxVouchers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		String pay_pwd = (String)session.getAttribute("pay_pwd");
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		// 存放数据
		Map<String, String> maps = new HashMap<String, String>();
		String msg="";
		if(pay_pwd!=null&&pay_pwd.equals(ecUser.getMap().get("pay_pwd").toString())){
		 EcVouchers voucher = new EcVouchers();
		 voucher.setUser_id(ecUser.getId());
		 voucher.setInfo_state(new Integer(0));
		 List<EcVouchers> list =super.getFacade().getEcVouchersService().getEcVouchersList(voucher);
		 super.renderJson(response, JSON.toJSONString(list));
		 return null;
		}
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
	
	public ActionForward checkPayPwd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pay_pwd = (String) dynaBean.get("password"); 
		String show = (String) dynaBean.get("show"); 
		if("1".equals(show)){
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		 
			if(pay_pwd!=null&&pay_pwd.equals(ecUser.getMap().get("pay_pwd").toString())){
				HttpSession session = request.getSession();
				session.setAttribute("pay_pwd", ecUser.getMap().get("pay_pwd").toString());
				super.renderHtml(response,"<script type=\"text/javascript\">if(window.opener != undefined)window.opener.returnValue='1';window.returnValue='1';window.close();</script>");
				return null;
			}else{
				super.renderHtml(response,"<script type=\"text/javascript\">alert('密码错误');window.history.back();</script>");
				return null;
			}
		}else{
			return new ActionForward("/../wap/ShoppingCar/checkpwd.jsp");
		}
		 
	}
}
