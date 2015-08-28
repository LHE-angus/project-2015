package com.ebiz.mmt.web.struts.touch;

import java.util.ArrayList;
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
import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcGift;
import com.ebiz.mmt.domain.EcGiftContent;
import com.ebiz.mmt.domain.EcGiftOrde;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class EcGiftAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
		if(ecUser.getUser_type().intValue()==1){
			String touch = (String)session.getAttribute("touch");
			if(!"1".equals(touch)){
				String ctx = super.getCtxPath(request);
				String url=ctx+"/touch/center/Skip.do";
				response.sendRedirect(url);
				return null;  
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/touch/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				}
		 long l=0L;
		 if(ecUser.getUser_type().intValue()==2){
			 l=100000L;
		 }

		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L+l); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String need_integral_type = (String) dynaBean.get("need_integral_type");
		String pd_sn_or_pd_name_like = (String) dynaBean.get("pd_sn_or_pd_name_like");
 
		if (null == ecUser) {
			// String msg = super.getMessage(request, "user.session.isEmpty");
			// super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			// return null;
		} else {
			Long surplus_integral = 0L;// 剩余积分
			surplus_integral = getIntegralByUserId(ecUser.getId());// 公用的剩余积分查询方法
			request.setAttribute("surplus_integral", surplus_integral);
		}

		EcGift entity = new EcGift();

		if (StringUtils.isNotBlank(pd_sn_or_pd_name_like)) {
			entity.getMap().put("pd_sn_or_pd_name_like", pd_sn_or_pd_name_like);
		}
		if (StringUtils.isNotBlank(need_integral_type) && "1".equals(need_integral_type)) {
			entity.getMap().put("need_integral_ge", "0");
			entity.getMap().put("need_integral_lt", "10000");
			request.setAttribute("need_integral_name", "0-10000");
		} else if (StringUtils.isNotBlank(need_integral_type) && "2".equals(need_integral_type)) {
			entity.getMap().put("need_integral_ge", "10000");
			entity.getMap().put("need_integral_lt", "80000");
			request.setAttribute("need_integral_name", "10000-80000");
		} else if (StringUtils.isNotBlank(need_integral_type) && "3".equals(need_integral_type)) {
			entity.getMap().put("need_integral_ge", "80000");
			entity.getMap().put("need_integral_lt", "500000");
			request.setAttribute("need_integral_name", "80000-500000");
		} else if (StringUtils.isNotBlank(need_integral_type) && "4".equals(need_integral_type)) {
			entity.getMap().put("need_integral_ge", "500000");
			entity.getMap().put("need_integral_lt", "900000000");
			request.setAttribute("need_integral_name", "500000-900000000");
		}

		// 所属系统：1-工卡，2-触网，3-会员
		entity.setOwn_sys(ecUser.getUser_type());;
		// 状态：0-已停用 1-正常 -1-已删除
		entity.setState(1);
		// 只查询上架积分商品
		entity.getMap().put("is_upself", "true");
		Long recordCount = super.getFacade().getEcGiftService().getEcGiftCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcGift> entityList = super.getFacade().getEcGiftService().getEcGiftPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		EcGift e = new EcGift();
		// 所属系统：1-工卡，2-触网，3-会员
		e.setOwn_sys(ecUser.getUser_type());;
		// 状态：0-已停用 1-正常 -1-已删除
		e.setState(1);
		// 只查询上架积分商品
		e.getMap().put("is_upself", "true");
		e.getMap().put("map.order_by_sale_num_desc", "true");
		e.getRow().setCount(10);
		List<EcGift> ec_gift_list_top_10 = super.getFacade().getEcGiftService().getEcGiftList(e);
		request.setAttribute("ec_gift_list_top_10", ec_gift_list_top_10);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String gift_id = (String) dynaBean.get("gift_id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/touch/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				}
		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request);
		request.setAttribute("p_index", p_index);
		logger.info("----p_index--->{}", p_index);
		
		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
		
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}	
		 long l=0L;
		 if(ecUser.getUser_type().intValue()==2){
			 l=100000L;
		 }
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L+l); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		if (GenericValidator.isLong(gift_id)) {

			EcGift entity = new EcGift();
			entity.setId(Long.valueOf(gift_id));

			entity = super.getFacade().getEcGiftService().getEcGift(entity);

			if (null != entity) {
				String main_pic = entity.getMain_pic();

				if (StringUtils.isNotBlank(main_pic)) {
					String main_pic_file = StringUtils.split(main_pic, ",")[0];
					dynaBean.set("main_pic_file", main_pic_file); // 主图

					List<String> list = new ArrayList<String>(); // 附图list
					String[] picArr = StringUtils.split(main_pic, ",");
					if (null != picArr && picArr.length > 0) {
						for (int i = 0; i < picArr.length; i++) {
							if (!StringUtils.equals(main_pic_file, picArr[i])) {
								list.add(picArr[i]);
							}
						}
					}
					request.setAttribute("picList", list);
				}
			}

			request.setAttribute("ecGift", entity);

			// 回显产品描述、产品规格、售后服务
			EcGiftContent ec = new EcGiftContent();
			ec.setKbp_id(Long.valueOf(gift_id));
			List<EcGiftContent> ecList = super.getFacade().getEcGiftContentService().getEcGiftContentList(ec);
			request.setAttribute("konkaBcompPdContentList", ecList);

			// 浏览次数加1
			EcGift e_count = new EcGift();
			e_count.setId(new Long(gift_id));
			e_count.setView_counts(entity.getView_counts() + 1L);
			super.getFacade().getEcGiftService().modifyEcGift(e_count);
		} else {
			this.saveError(request, "errors.long", new String[] { gift_id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		// 销量排行前5
		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5, ecUser.getDept_id(),ecUser.getCust_id());
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);
		return mapping.findForward("view");
	}

	public ActionForward stepTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request);

		String gift_id = (String) dynaBean.get("gift_id");

		// 判断是不是有积分商品
		if (!GenericValidator.isLong(gift_id)) {
			String msg = super.getMessage(request, "ec.gift.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response,
					"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
							+ "/touch/login.do';}");
			return null;
		}

		Long surplus_integral = 0L;// 剩余积分
		surplus_integral = getIntegralByUserId(ecUser.getId());// 公用的剩余积分查询方法

		EcGift entity = new EcGift();
		entity.setId(Long.valueOf(gift_id));

		entity = super.getFacade().getEcGiftService().getEcGift(entity);

		List<Map<String, String>> json = new ArrayList<Map<String, String>>();
		if (null != entity) {
			if (surplus_integral.compareTo(entity.getNeed_integral()) == -1) {
				String msg = super.getMessage(request, "ec.gift.pd.no.integral");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			String main_pic = entity.getMain_pic();

			if (StringUtils.isNotBlank(main_pic)) {
				String main_pic_file = StringUtils.split(main_pic, ",")[0];
				dynaBean.set("main_pic_file", main_pic_file); // 主图

			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("gift_id", entity.getId().toString());
			map.put("pd_name", entity.getPd_name());
			map.put("need_integral", entity.getNeed_integral().toString());
			map.put("buy_num", "1");
			json.add(map);
		}
		// 将需要购买的商品数据加密，然后到前台
		request.setAttribute("buy_json_object", new DESPlus().encrypt(JSON.toJSONString(json)));
		request.setAttribute("ecGift", entity);

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
		

		return new ActionForward("/../touch/EcGift/step_two.jsp");
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
		// String bill_is_add = (String) dynaBean.get("bill_is_add");
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
			String msg = super.getMessage(request, "ec.gift.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response,
					"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
							+ "/touch/login.do';}");
			return null;
		}

		if (!GenericValidator.isInt(deliver_way)) {
			String msg = super.getMessage(request, "ec.deliver.way.lost");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 交易流水号
		Date now = new Date();
		String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")
				+ StringUtils.substring(String.valueOf(now.getTime()), 7);

		EcGiftOrde ecGiftOrde = new EcGiftOrde();
		ecGiftOrde.setOrder_from(1);
		ecGiftOrde.setTrade_index(trade_index);
		ecGiftOrde.setState(10);// 已兑换
		ecGiftOrde.setOrder_user_id(ecUser.getId());
		ecGiftOrde.setOrder_user_name(ecUser.getUser_name());
		ecGiftOrde.setBuyer_name(rel_name);
		ecGiftOrde.setBuyer_addr(rel_addr);
		ecGiftOrde.setBuyer_zip(rel_zip);
		ecGiftOrde.setBuyer_tel(rel_tel);
		ecGiftOrde.setBuyer_mp(rel_phone);
		ecGiftOrde.setOpr_dept_id(0L);// 下单时，处理部门为总部
		ecGiftOrde.setIs_del(0);
		ecGiftOrde.setAdd_date(now);
		ecGiftOrde.setDeliver_way(new Integer(deliver_way));
		ecGiftOrde.setBuyer_p_index(new Long(p_index));
		if (GenericValidator.isInt(deliver_time)) {
			ecGiftOrde.setDeliver_time(new Integer(deliver_time));
		}
		if (GenericValidator.isInt(deliver_is_call)) {
			ecGiftOrde.setDeliver_is_call(new Integer(deliver_is_call));
		}

		JSONArray json = JSON.parseArray((new DESPlus().decrypt(buy_json_object)));
		if (json != null && json.size() > 0) {
			JSONObject obj = json.getJSONObject(0);
			Long gift_id = obj.getLong("gift_id"); // 商品ID
			// Long buy_num = obj.getLong("buy_num"); // 购买数量
			// String pd_name = obj.getString("pd_name"); // 商品名称
			Long need_integral = obj.getLong("need_integral"); // 兑换积分
			ecGiftOrde.setGift_id(gift_id);
			ecGiftOrde.setIntegral(need_integral);
		} else {
			String msg = super.getMessage(request, "ec.gift.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		EcGift ecGift = new EcGift();
		ecGift.setId(ecGiftOrde.getGift_id());
		ecGift = super.getFacade().getEcGiftService().getEcGift(ecGift);
		// 积分商品添加部门id
		if (ecGift != null && !ecGift.getDept_sn().equals("-1")) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(ecGift.getDept_sn());
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
			if (null != konkaDeptList && konkaDeptList.size() > 0) {
				dept = konkaDeptList.get(0);
				ecGiftOrde.setDept_id(dept.getDept_id());
			}
		}

		// 用户积分记录操作表
		EcUserScoreRec e = new EcUserScoreRec();
		e.setUser_id(ecUser.getId());
		e.setUser_name(ecUser.getUser_name());
		e.setOpr_type(1);
		e.setScore(ecGiftOrde.getIntegral().intValue());
		e.setAll_score(ecGiftOrde.getIntegral().intValue());
		e.setOpr_id(ecUser.getId());
		e.setNote("\u5151\u6362\u5546\u54c1\uff0c\u4f7f\u7528\u79ef\u5206");// 兑换商品，使用积分
		ecGiftOrde.setEcUserScoreRec(e);

		super.getFacade().getEcGiftOrdeService().createEcGiftOrdeIncludeStore(ecGiftOrde);
		logger.info("----------------------------->Order success");

		if (ecGift != null) {
			// 兑换数量+1
			EcGift e_sale_num = new EcGift();
			Long sale_num = ecGift.getSale_num();
			e_sale_num.setId(ecGiftOrde.getGift_id());
			e_sale_num.setSale_num(sale_num + 1L);
			super.getFacade().getEcGiftService().modifyEcGift(e_sale_num);
		}

		String msg = super.getMessage(request, "ec.gift.order.success");
		super.renderJavaScript(response,
				"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
						+ "/touch/EcGift.do';}");
		return null;
	}
}