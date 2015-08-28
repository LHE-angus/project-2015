package com.ebiz.mmt.web.struts.zxmall;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.EcBcompPdUp;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcDispatchArea;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcSelfArea;
import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class LzPdBuyAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		String p_index = super.getPIndexByRequest(request);
		logger.info("----p_index--->{}", p_index);

		// 得到当前请求的地址
		request.setAttribute("p_full_name", super.getPIndexName(p_index, ""));

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
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

		// 查询省份信息
		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_level(1);
		bplf.setDel_mark(0);
		List<BaseProvinceListFour> bplfList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(bplf);
		for (BaseProvinceListFour t : bplfList) {
			if (StringUtils.substring(t.getP_index().toString(), 0, 2).equals(StringUtils.substring(p_index, 0, 2))) {
				request.setAttribute("province_name", t.getP_name());
				request.setAttribute("province_index", t.getP_index());
			}
		}
		request.setAttribute("baseProvinceListFourList", bplfList);

		List<KonkaBcompPd> entityList = super.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        new Long(p_index), 5, null, ecUser.getDept_id(), ecUser.getC_id(), new Integer[] { 8 });

		if (null != entityList && entityList.size() > 0) {
			for (KonkaBcompPd t : entityList) {
				EcBcompPdUp ep = new EcBcompPdUp();
				ep.setBcomp_pd_id(t.getId());
				ep.setOwn_sys(ecUser.getUser_type());
				if (ecUser.getUser_type().intValue() == 2) {
					ep.setAdded_dept_id(ecUser.getDept_id());
				}
				List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);

				if (ecBcompPdUpList != null && ecBcompPdUpList.size() > 0) {

					// 取限时抢购商品价格的结束时间作为抢购结束时间
					EcGoodsPrice ecg = new EcGoodsPrice();
					ecg.setGoods_id(t.getId());
					ecg.setOwn_sys(1);
					ecg.setPrice_type(0);
					ecg = super.getFacade().getEcGoodsPriceService().getEcGoodsPrice(ecg);

					if (ecg != null && ecg.getStart_time() != null && ecg.getEnd_time() != null) {
						t.getMap().put("start_time", ecg.getStart_time());
						t.getMap().put("end_time", ecg.getEnd_time());
					}

					Calendar c = Calendar.getInstance();
					// c.setTime(ecBcompPdUpList.get(0).getDown_time());
					if (ecg != null && ecg.getEnd_time() != null) {
						c.setTime(ecg.getEnd_time());// 
					}
					// t.setTimeRemains(c.getTimeInMillis());//

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());

					t.setTimeRemains(c.getTimeInMillis() - calendar.getTimeInMillis());// 离抢购结束还剩多久

					// 已付款数量
					int num = 0;
					PshowOrdeDetails pd = new PshowOrdeDetails();
					pd.setPd_id(t.getId());
					pd.getMap().put("state_in", new Integer[] { 5, 10, 20, 30, 40, 50, 60 });
					List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService()
					        .getPshowOrdeDetailsForPayList(pd);
					if (null != pdList && pdList.size() > 0) {
						for (PshowOrdeDetails pshowOrdeDetails : pdList) {
							num = num + pshowOrdeDetails.getNum().intValue();
						}
					}
					t.getMap().put("buy_num", num);

				}
			}
		}

		request.setAttribute("entityList", entityList);

		// request.setAttribute("today", calendar.getTimeInMillis());

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		String p_index = super.getPIndexByRequest(request);
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
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		if (GenericValidator.isLong(goods_id)) {
			KonkaBcompPd entity = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request), ecUser
			        .getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array, ecUser.getDept_id(),
			        ecUser.getCust_id());

			if (null != entity) {
				EcBcompPdUp ep = new EcBcompPdUp();
				ep.setBcomp_pd_id(new Long(goods_id));
				ep.setOwn_sys(ecUser.getUser_type());
				if (ecUser.getUser_type().intValue() == 2) {
					ep.setAdded_dept_id(ecUser.getDept_id());
				}
				List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);
				// Calendar c = Calendar.getInstance();
				if (ecBcompPdUpList != null && ecBcompPdUpList.size() > 0) {
					EcGoodsPrice ecg = new EcGoodsPrice();
					ecg.setGoods_id(Long.valueOf(goods_id));
					ecg.setOwn_sys(1);
					ecg.setPrice_type(0);
					ecg = super.getFacade().getEcGoodsPriceService().getEcGoodsPrice(ecg);

					if (ecg != null && ecg.getStart_time() != null && ecg.getEnd_time() != null) {
						entity.getMap().put("start_time", ecg.getStart_time());
						entity.getMap().put("end_time", ecg.getEnd_time());
					}

					Calendar c = Calendar.getInstance();
					// c.setTime(ecBcompPdUpList.get(0).getDown_time());
					if (ecg != null && ecg.getEnd_time() != null) {
						c.setTime(ecg.getEnd_time());// 
					}// 
					// t.setTimeRemains(c.getTimeInMillis());//

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());

					entity.setTimeRemains(c.getTimeInMillis() - calendar.getTimeInMillis());// 离抢购结束还剩多久

					// 已付款数量
					int num = 0;
					PshowOrdeDetails pd = new PshowOrdeDetails();
					pd.setPd_id(entity.getId());
					pd.getMap().put("state_in", new Integer[] { 0, 5, 10, 20, 30, 40, 50, 60 });
					List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService()
					        .getPshowOrdeDetailsForPayList(pd);
					if (null != pdList && pdList.size() > 0) {
						for (PshowOrdeDetails pshowOrdeDetails : pdList) {
							num = num + pshowOrdeDetails.getNum().intValue();
						}
					}
					entity.getMap().put("buy_num", num);

				}
			}
			request.setAttribute("konkaBcompPd", entity);

			// Calendar calendar = Calendar.getInstance();
			// calendar.setTime(new Date());
			// request.setAttribute("today", calendar.getTimeInMillis());

			List<KonkaBcompPd> pd_list_cate5 = getKonkaBcompTopListForQg(ecUser.getUser_type(), p_index_array, 5, 1, ecUser
			        .getDept_id(), ecUser.getC_id(), null);
			request.setAttribute("pd_list_cate5", pd_list_cate5);

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

		} else {
			this.saveError(request, "errors.long", new String[] { goods_id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
			        + super.getCtxPath(request) + "/zxmall/login.do';}");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String buy_num = (String) dynaBean.get("buy_num");

		Date now = new Date();
		long now_date = now.getTime();

		// 取全国仓库库存
		EcStocks es = new EcStocks();
		es.setGoods_id(Long.valueOf(goods_id));
		List<EcStocks> esList = super.getFacade().getEcStocksService().getEcStocksList(es);
		int count = 0;
		if (null != esList && esList.size() > 0) {
			for (EcStocks ecStocks : esList) {
				EcBaseStore ebs = new EcBaseStore();
				ebs.setStore_id(ecStocks.getStore_id());
				ebs = super.getFacade().getEcBaseStoreService().getEcBaseStore(ebs);
				if (null != ebs && null != ebs.getOwn_sys() && null != ebs.getStore_type())
					if (ebs.getOwn_sys().intValue() == 1 && ebs.getStore_type().intValue() == 0) {
						count = ecStocks.getStocks().intValue();
					}
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('对不起，该商品已被抢购完！');location.href='"
			        + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
			return null;
		}
		logger.info("count--->" + count);

		if (count <= 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('对不起，该商品已被抢购完！');location.href='"
			        + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
			return null;
		}

		// 取上下架时间
		EcGoodsPrice ecg = new EcGoodsPrice();
		ecg.setGoods_id(Long.valueOf(goods_id));
		ecg.setOwn_sys(1);
		ecg.setPrice_type(0);
		List<EcGoodsPrice> ecList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceList(ecg);

		if (null != ecList && ecList.size() > 0) {
			ecg = ecList.get(0);
			if (ecg.getStart_time().getTime() > now_date) {
				super.renderJavaScript(response, "window.onload=function(){alert('对不起，还没到抢购时间！');location.href='"
				        + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
				return null;
			}
			if (ecg.getEnd_time().getTime() < now_date) {
				super.renderJavaScript(response, "window.onload=function(){alert('对不起，抢购已经结束！');location.href='"
				        + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
				return null;
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('网络异常，请稍后重试！');location.href='"
			        + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
			return null;
		}

		if (GenericValidator.isLong(goods_id)) {
			// 取当前请求数据的用户所在地
			String p_index = super.getPIndexByRequest(request);
			request.setAttribute("p_index", p_index);

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
			logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

			// 查询商品详细信息
			KonkaBcompPd konkaBcompPd = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request), ecUser
			        .getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array, ecUser.getDept_id(),
			        ecUser.getC_id());

			if (null == konkaBcompPd) {
				String msg = super.getMessage(request, "ec.bcomp.pd.none");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 购买数量
			konkaBcompPd.getMap().put("buy_num", buy_num);

			request.setAttribute("konkaBcompPd", konkaBcompPd);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Long today = calendar.getTimeInMillis();
			EcBcompPdUp ep = new EcBcompPdUp();
			ep.setBcomp_pd_id(new Long(goods_id));
			ep.setOwn_sys(ecUser.getUser_type());
			if (ecUser.getUser_type().intValue() == 2) {
				ep.setAdded_dept_id(ecUser.getDept_id());
			}
			List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);
			if (ecBcompPdUpList != null && ecBcompPdUpList.size() > 0) {
				calendar.setTime(ecBcompPdUpList.get(0).getDown_time());
			}
			Long down_time = calendar.getTimeInMillis();

			if (today.compareTo(down_time) == 1) {
				String msg = super.getMessage(request, "ec.bcomp.pd.down");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

		} else {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		return mapping.findForward("input");

	}

	public ActionForward stepTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
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
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
			        + super.getCtxPath(request) + "/zxmall/login.do';}");
			return null;
		}

		// 取当前请求数据的用户所在地
		if (StringUtils.isEmpty(p_index))
			p_index = super.getPIndexByRequest(request);
		request.setAttribute("p_index", p_index);
		request.setAttribute("p_name", super.getPIndexName(p_index, ""));
		logger.info("----p_index--->{}", p_index);

		EcSelfArea esa = new EcSelfArea();
		// 取当前用户所在市下面的自提点
		BaseProvinceListFour bf1 = new BaseProvinceListFour();
		bf1.setP_level(2);
		bf1.getMap().put("pindex", p_index);
		List<BaseProvinceListFour> bfList1 = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourForCityList(bf1);
		if (null != bfList1 && bfList1.size() > 0) {
			bf1 = bfList1.get(0);
		}
		esa.setPar_pindex(bf1.getP_index());
		List<EcSelfArea> esaList = super.getFacade().getEcSelfAreaService().getEcSelfAreaList(esa);
		request.setAttribute("esaList", esaList);
		logger.info("自提点----------》" + esaList.size());

		EcDispatchArea epa = new EcDispatchArea();
		epa.setPar_pindex(Long.valueOf(p_index));
		List<EcDispatchArea> psList = super.getFacade().getEcDispatchAreaService().getEcDispatchAreaList(epa);
		request.setAttribute("psList", psList);

		// 循环处理数据
		List<Map<String, String>> json = new ArrayList<Map<String, String>>();
		List<KonkaBcompPd> konkaBcompPdList = new ArrayList<KonkaBcompPd>();
		String stores = "";
		BigDecimal all_rebates = new BigDecimal("0.0");
		Integer is_pj = new Integer(0);
		Integer is_hdfk = new Integer(0);// 货到付款 非电视 不能使用货到付款+样机专区也不能使用货到付款
		List<String> ids = new ArrayList<String>();
		List<String> pro_types = new ArrayList<String>();
		for (String id : goods_ids) {
			logger.info("--goods_id--->{}", id);
			Map<String, String> map = new HashMap<String, String>();
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(Long.valueOf(id));

			// 查询商品是否在PINDEX以外，收取额外费用
			EcRuleGoods ecg = new EcRuleGoods();
			ecg.setGoods_id(Long.valueOf(id));
			List<EcRuleGoods> ecgList = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(ecg);

			BigDecimal rule_price = new BigDecimal("0.00");
			if (null != ecgList && ecgList.size() > 0) {
				List<EcRule> egList = new ArrayList<EcRule>();
				for (EcRuleGoods ecRuleGoods : ecgList) {
					EcRule er = new EcRule();
					er.setRule_type(4);// 限制费用
					er.setId(ecRuleGoods.getRule_id());
					if (ecUser.getUser_type().intValue() == 2) {
						er.setDept_id(ecUser.getDept_id());
						er.setOwn_sys(2);
					} else if (ecUser.getUser_type().intValue() == 1) {
						er.setOwn_sys(1);
					}
					er = super.getFacade().getEcRuleService().getEcRule(er);
					if (null != er) {
						egList.add(er);
					}
				}
				if (null != egList && egList.size() > 0) {
					// 根据当前用户的pindex找到所在的省份
					BaseProvinceListFour bf = new BaseProvinceListFour();
					bf.setP_level(1);
					bf.getMap().put("pindex", p_index);
					List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
					        .getBaseProvinceListFourForProvinceList(bf);
					if (null != bfList && bfList.size() > 0) {
						bf = bfList.get(0);
					}

					// 把包含该pindex的rule_price放到list
					List<Double> list = new ArrayList<Double>();
					for (EcRule ec : egList) {
						if (ec.getIs_price().intValue() == 1 && ec.getInfo_state().intValue() == 1
						        && null != ec.getRule_area_allow() && !"".equals(ec.getRule_area_allow())) {
							if (ec.getRule_area_allow().contains(bf.getP_index().toString())) {
								list.add(ec.getRule_price().doubleValue());
							}
						}
					}

					// 对list进行排序，从大到小
					if (null != list && list.size() > 0) {
						for (int i = 0; i < list.size() - 1; i++) {
							for (int j = 1; j < list.size() - i; j++) {
								Double a;
								if ((list.get(j - 1)).compareTo(list.get(j)) < 0) { // 比较两个整数的大小
									a = list.get(j - 1);
									list.set((j - 1), list.get(j));
									list.set(j, a);
								}
							}
						}
						rule_price = rule_price.add(new BigDecimal(list.get(0).toString()));// 取最大的一个
					}
				}

				// 判断是否超出限购
				Integer num = 0;
				List<EcRule> egList1 = new ArrayList<EcRule>();
				for (EcRuleGoods ecRuleGoods : ecgList) {
					Date date = new Date();
					EcRule er = new EcRule();
					er.setRule_type(1);// 限制数量
					er.setId(ecRuleGoods.getRule_id());
					if (ecUser.getUser_type().intValue() == 2) {
						er.setDept_id(ecUser.getDept_id());
						er.setOwn_sys(2);
					} else if (ecUser.getUser_type().intValue() == 1) {
						er.setOwn_sys(1);
					}
					er = super.getFacade().getEcRuleService().getEcRule(er);
					if (null != er && er.getRule_start_date().getTime() <= date.getTime()
					        && er.getRule_end_date().getTime() > date.getTime()) {
						egList1.add(er);
					}
				}

				if (null != egList1 && egList1.size() > 0) {

					EcRule ecRule = egList1.get(0);
					// 限购数量+状态启用+最大购买数量
					if (null != ecRule && ecRule.getRule_type() == 1 && ecRule.getInfo_state() == 1
					        && ecRule.getIs_num() == 1 && ecRule.getRule_num_max() > 0) {
						PshowOrder p = new PshowOrder();
						p.setOrder_user_id(ecUser.getId());
						p.getMap().put("limit_state", true);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						p.getMap().put("add_time_start", sdf.format(ecRule.getRule_start_date()) + " 00:00:00");
						p.getMap().put("add_time_end", sdf.format(ecRule.getRule_end_date()) + " 23:59:59");
						List<PshowOrder> pshowList = super.getFacade().getPshowOrderService().getPshowOrderList(p);
						if (null != pshowList && pshowList.size() > 0) {
							for (PshowOrder pshowOrder : pshowList) {
								PshowOrdeDetails pod = new PshowOrdeDetails();
								pod.setOrder_id(pshowOrder.getId());
								List<PshowOrdeDetails> podList = super.getFacade().getPshowOrdeDetailsService()
								        .getPshowOrdeDetailsList(pod);
								if (null != podList && podList.size() > 0) {
									for (PshowOrdeDetails pshowOrdeDetails : podList) {
										if (pshowOrdeDetails.getPd_id().intValue() == Long.valueOf(id).intValue()) {
											num = num.intValue() + pshowOrdeDetails.getNum().intValue();
										}
									}
								}
							}
							String buy_num = (String) dynaBean.get("buy_num_" + id);
							if ((num.intValue() + Integer.valueOf(buy_num).intValue()) > ecRule.getRule_num_max()
							        .intValue()) {
								super.renderJavaScript(response, "window.onload=function(){alert('该商品每人每月只能购买"
								        + ecRule.getRule_num_max() + "台');history.back();}");
								return null;
							}
						} else {
							String buy_num = (String) dynaBean.get("buy_num_" + id);
							if ((num.intValue() + Integer.valueOf(buy_num).intValue()) > ecRule.getRule_num_max()
							        .intValue()) {
								super.renderJavaScript(response, "window.onload=function(){alert('该商品每人每月只能购买"
								        + ecRule.getRule_num_max() + "台');history.back();}");
								return null;
							}
						}

					}
					if (null != ecRule && ecRule.getRule_type() == 1 && ecRule.getInfo_state() == 1
					        && ecRule.getIs_num() == 1 && ecRule.getRule_num_min() > 0) {
						String buy_num = (String) dynaBean.get("buy_num_" + id);
						if (Integer.valueOf(buy_num).intValue() < ecRule.getRule_num_min().intValue()) {
							super.renderJavaScript(response, "window.onload=function(){alert('该商品至少购买"
							        + ecRule.getRule_num_min() + "台');history.back();}");
							return null;
						}

					}

				}

			}

			// 优惠价格
			BigDecimal rule_price_2 = new BigDecimal("0.00");
			if (null != ecgList && ecgList.size() > 0) {
				List<EcRule> egList = new ArrayList<EcRule>();
				for (EcRuleGoods ecRuleGoods : ecgList) {
					EcRule er = new EcRule();
					er.setRule_type(3);// 优惠费用
					er.setId(ecRuleGoods.getRule_id());
					if (ecUser.getUser_type().intValue() == 2) {
						er.setDept_id(ecUser.getDept_id());
						er.setOwn_sys(2);
					} else if (ecUser.getUser_type().intValue() == 1) {
						er.setOwn_sys(1);
					}
					er = super.getFacade().getEcRuleService().getEcRule(er);
					if (null != er) {
						egList.add(er);
					}
				}
				if (null != egList && egList.size() > 0) {
					// 根据当前用户的pindex找到所在的省份
					BaseProvinceListFour bf = new BaseProvinceListFour();
					bf.setP_level(1);
					bf.getMap().put("pindex", p_index);
					List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
					        .getBaseProvinceListFourForProvinceList(bf);
					if (null != bfList && bfList.size() > 0) {
						bf = bfList.get(0);
					}

					// 把包含该pindex的rule_price放到list
					List<Double> list = new ArrayList<Double>();
					for (EcRule ec : egList) {
						if (ec.getIs_price().intValue() == 1 && ec.getInfo_state().intValue() == 1
						        && null != ec.getRule_area_allow() && !"".equals(ec.getRule_area_allow())) {
							if (ec.getRule_area_allow().contains(bf.getP_index().toString())) {
								list.add(ec.getRule_price().doubleValue());
							}
						}
					}

					// 对list进行排序，从大到小
					if (null != list && list.size() > 0) {
						for (int i = 0; i < list.size() - 1; i++) {
							for (int j = 1; j < list.size() - i; j++) {
								Double a;
								if ((list.get(j - 1)).compareTo(list.get(j)) < 0) { // 比较两个整数的大小

									a = list.get(j - 1);
									list.set((j - 1), list.get(j));
									list.set(j, a);
								}
							}
						}
						rule_price_2 = rule_price_2.add(new BigDecimal(list.get(0).toString()));// 取最大的一个
					}
				}

			}

			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
			// Integer is_hdfk = new Integer(0);// 货到付款 非电视
			// 不能使用货到付款+样机专区也不能使用货到付款
			if ((konkaBcompPd != null && konkaBcompPd.getProd_type().intValue() != 0 && konkaBcompPd.getProd_type()
			        .intValue() != 10)
			        || (konkaBcompPd.getProd_type().intValue() == 0 && konkaBcompPd.getLabel_of_cate() == 9)) {
				is_hdfk = new Integer(1);
			}

			if (konkaBcompPd != null && null != konkaBcompPd.getProd_type()) {
				pro_types.add(konkaBcompPd.getProd_type().toString());
			}

			if (konkaBcompPd != null && konkaBcompPd.getProd_type().intValue() != 10) {
				is_pj = new Integer(1);
			} else {
				request.setAttribute("pj", new Integer(1));
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
			if ("".equals(stores)) {
				stores = map.get("store_id");
			} else {
				stores += "," + map.get("store_id");
			}
			Integer buy_num = new Integer(dynaBean.get("buy_num_" + id).toString());
			BigDecimal price = new BigDecimal(dynaBean.get("price_" + id).toString());

			konkaBcompPd.getMap().put("rule_price", rule_price);
			konkaBcompPd.getMap().put("rule_price_2", rule_price_2);
			if (rule_price.doubleValue() > 0) {
				ids.add(konkaBcompPd.getPd_sn());
			}

			// 查询绑定服务
			EcBindingPd ecBindingPd = new EcBindingPd();
			ecBindingPd.setBinding_type(0);
			ecBindingPd.getMap().put("goods_id", konkaBcompPd.getId());
			List<EcBindingPd> ecBindingPdListForService = getFacade().getEcBindingPdService()
			        .getEcBindingPdWithGoodsIdAndTypeList(ecBindingPd);
			konkaBcompPd.setEcBindingPdListForService(ecBindingPdListForService);

			json.add(map);
			// 查询商品佣金、返利
			EcBcompPdRebates ecBcompPdRebates = new EcBcompPdRebates();
			ecBcompPdRebates.setIs_del(0);
			ecBcompPdRebates.setGoods_id(konkaBcompPd.getId());
			ecBcompPdRebates.setOwn_sys(ecUser.getUser_type());
			if (ecUser.getUser_type().intValue() == 2) {
				ecBcompPdRebates.setDept_id(ecUser.getDept_id());
				ecBcompPdRebates.setDept_id(ecUser.getCust_id());
			}
			ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);
			if (ecBcompPdRebates != null && ecBcompPdRebates.getB_value() != null
			        && ecBcompPdRebates.getB_value().floatValue() > 0.00001) {
				konkaBcompPd.setEcBcompPdRebates(ecBcompPdRebates);
				request.setAttribute("is_rebate", "1");

				if (ecBcompPdRebates.getB_type().intValue() == 0) { // 按比例
					all_rebates = all_rebates.add(price.multiply(new BigDecimal(buy_num)).multiply(
					        ecBcompPdRebates.getB_value()).divide(new BigDecimal("100")).setScale(2,
					        BigDecimal.ROUND_HALF_UP));
				}
				if (ecBcompPdRebates.getB_type().intValue() == 1) { // 按固定
					all_rebates = all_rebates.add(new BigDecimal(buy_num).multiply(ecBcompPdRebates.getB_value())
					        .setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
			konkaBcompPdList.add(konkaBcompPd);
		}
		String msg = "";
		if (null != ids && ids.size() > 0) {
			String pd_sn = StringUtils.join(ids.toArray(), ",");
			msg = "商品" + pd_sn + "不在广东范围内，需要收取物流费";
		}
		request.setAttribute("msg", msg);

		logger.info("is_hdfk-------->" + is_hdfk);
		request.setAttribute("is_hdfk", is_hdfk);
		request.setAttribute("is_pj", is_pj);

		// 彩电显示是否需要安装调试
		if (pro_types.contains("0") || pro_types.contains("10")) {
			request.setAttribute("is_az", "0");
		} else {
			request.setAttribute("is_az", "1");
		}

		request.setAttribute("stores", stores);
		request.setAttribute("all_rebates", all_rebates);

		logger.info("----json-->{}", JSON.toJSONString(json));
		logger.info("---DESPlus-json-->{}", new DESPlus().encrypt(URLEncoder.encode(JSON.toJSONString(json), "UTF-8")));
		// 将需要购买的商品数据加密，然后到前台
		request.setAttribute("buy_json_object", new DESPlus().encrypt(URLEncoder.encode(JSON.toJSONString(json),
		        "UTF-8")));
		request.setAttribute("konkaBcompPdList", konkaBcompPdList);

		HttpSession session = request.getSession();
		String touch = (String) session.getAttribute("touch");
		if ("1".equals(touch)) {
			// 查询收货地址
			EcUserAddrs ecUserAddrs = new EcUserAddrs();
			ecUserAddrs.getRow().setCount(2);
			ecUserAddrs.setUser_id(ecUser.getId());
			ecUserAddrs.setRel_region(Long.valueOf(p_index));
			List<EcUserAddrs> ecUserAddrsList = super.getFacade().getEcUserAddrsService().getEcUserAddrsList(
			        ecUserAddrs);
			if (null == ecUserAddrsList || 0 == ecUserAddrsList.size()) { // 县区不存在直接查所在市
				ecUserAddrs.setRel_region(null);
				ecUserAddrs.getMap().put("rel_city_like", StringUtils.substring(p_index, 0, 4));
				ecUserAddrsList = super.getFacade().getEcUserAddrsService().getEcUserAddrsList(ecUserAddrs);
			}

			if (null != ecUserAddrsList && 0 != ecUserAddrsList.size()) {
				request.setAttribute("ecUserAddrs", ecUserAddrsList.get(0));
				request.setAttribute("ecUserAddrsList", ecUserAddrsList);
			}
		}
		return new ActionForward("/../zxmall/LzPdBuy/step_two.jsp");
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
			        + super.getCtxPath(request) + "/zxmall/login.do';}");
			return null;
		}

		// 交易流水号
		Date now = new Date();
		String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")
		        + StringUtils.substring(String.valueOf(now.getTime()), 7);

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
		pshowOrder.setBuyer_addr("康佳集团");
		pshowOrder.setBuyer_mp(rel_phone);
		pshowOrder.setBuyer_tel(rel_tel);
		// pshowOrder.setRemark("");
		pshowOrder.setBuyer_zip(rel_zip);
		pshowOrder.setAdd_date(now);

		if (StringUtils.isNotBlank(deliver_time)) {
			pshowOrder.setDeliver_time(Integer.valueOf(deliver_time));
		} else {
			pshowOrder.setDeliver_time(1);
		}
		if (StringUtils.isNotBlank(deliver_way)) {
			pshowOrder.setDeliver_way(Integer.valueOf(deliver_way));
		} else {
			pshowOrder.setDeliver_way(0);
		}
		// 自提点
		if (StringUtils.isNotBlank(is_zt)) {
			pshowOrder.setIs_self(Integer.valueOf(is_zt));
		} else {
			pshowOrder.setIs_self(0);
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
		if (deliver_is_call != null) {
			pshowOrder.setDeliver_is_call(Integer.valueOf(deliver_is_call));
		} else {
			pshowOrder.setDeliver_is_call(0);
		}
		if (GenericValidator.isInt(bill_is_add)) {
			pshowOrder.setBill_is_add(Integer.valueOf(bill_is_add));
		} else {
			pshowOrder.setBill_is_add(0);
		}
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
		for (int i = 0; i < json.size(); i++) {
			JSONObject obj = json.getJSONObject(i);
			Long goods_id = obj.getLong("goods_id"); // 商品ID
			Long buy_num = obj.getLong("buy_num"); // 购买数量
			BigDecimal price = obj.getBigDecimal("price"); // 购买单价
			String service_ids = obj.getString("service_ids"); // 订购的增值服务
			String store_id = obj.getString("store_id"); // 仓库ID

			// 查询商品信息
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(goods_id);
			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);

			// 查询商品是否在PINDEX以外，收取额外费用
			EcRuleGoods ecg = new EcRuleGoods();
			ecg.setGoods_id(Long.valueOf(goods_id));
			List<EcRuleGoods> ecgList = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(ecg);

			BigDecimal rule_price = new BigDecimal("0.00");
			if (null != ecgList && ecgList.size() > 0) {
				List<EcRule> egList = new ArrayList<EcRule>();
				for (EcRuleGoods ecRuleGoods : ecgList) {
					EcRule er = new EcRule();
					er.setRule_type(4);// 限制费用
					er.setId(ecRuleGoods.getRule_id());
					if (ecUser.getUser_type().intValue() == 2) {
						er.setDept_id(ecUser.getDept_id());
						er.setOwn_sys(2);
					} else if (ecUser.getUser_type().intValue() == 1) {
						er.setOwn_sys(1);
					}
					er = super.getFacade().getEcRuleService().getEcRule(er);
					if (null != er) {
						egList.add(er);
					}
				}
				if (null != egList && egList.size() > 0) {

					// 根据当前用户的pindex找到所在的省份
					BaseProvinceListFour bf = new BaseProvinceListFour();
					bf.setP_level(1);
					bf.getMap().put("pindex", p_index);
					List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
					        .getBaseProvinceListFourForProvinceList(bf);
					if (null != bfList && bfList.size() > 0) {
						bf = bfList.get(0);
					}

					// 把包含该pindex的rule_price放到list
					List<Double> list = new ArrayList<Double>();
					for (EcRule ec : egList) {
						if (ec.getIs_price().intValue() == 1 && ec.getInfo_state().intValue() == 1
						        && null != ec.getRule_area_allow() && !"".equals(ec.getRule_area_allow())) {
							if (ec.getRule_area_allow().contains(bf.getP_index().toString())) {
								// rule_price =
								// rule_price.add(ec.getRule_price());
								// break;
								list.add(ec.getRule_price().doubleValue());
							}
						}
					}

					// 对list进行排序，从大到小
					if (null != list && list.size() > 0) {
						for (int k = 0; k < list.size() - 1; k++) {
							for (int j = 1; j < list.size() - k; j++) {
								Double a;
								if ((list.get(j - 1)).compareTo(list.get(j)) < 0) { // 比较两个整数的大小

									a = list.get(j - 1);
									list.set((j - 1), list.get(j));
									list.set(j, a);
								}
							}
						}
						rule_price = rule_price.add(new BigDecimal(list.get(0).toString()));// 取最大的一个
					}

				}

			}

			BigDecimal rule_price_2 = new BigDecimal("0.00");
			if (null != ecgList && ecgList.size() > 0) {
				List<EcRule> egList = new ArrayList<EcRule>();
				for (EcRuleGoods ecRuleGoods : ecgList) {
					EcRule er = new EcRule();
					er.setRule_type(3);// 优惠费用
					er.setId(ecRuleGoods.getRule_id());
					if (ecUser.getUser_type().intValue() == 2) {
						er.setDept_id(ecUser.getDept_id());
						er.setOwn_sys(2);
					} else if (ecUser.getUser_type().intValue() == 1) {
						er.setOwn_sys(1);
					}
					er = super.getFacade().getEcRuleService().getEcRule(er);
					if (null != er) {
						egList.add(er);
					}
				}
				if (null != egList && egList.size() > 0) {

					// 根据当前用户的pindex找到所在的省份
					BaseProvinceListFour bf = new BaseProvinceListFour();
					bf.setP_level(1);
					bf.getMap().put("pindex", p_index);
					List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
					        .getBaseProvinceListFourForProvinceList(bf);
					if (null != bfList && bfList.size() > 0) {
						bf = bfList.get(0);
					}

					// 把包含该pindex的rule_price放到list
					List<Double> list = new ArrayList<Double>();
					for (EcRule ec : egList) {
						if (ec.getIs_price().intValue() == 1 && ec.getInfo_state().intValue() == 1
						        && null != ec.getRule_area_allow() && !"".equals(ec.getRule_area_allow())) {
							if (ec.getRule_area_allow().contains(bf.getP_index().toString())) {
								list.add(ec.getRule_price().doubleValue());
							}
						}
					}

					// 对list进行排序，从大到小
					if (null != list && list.size() > 0) {
						for (int k = 0; k < list.size() - 1; k++) {
							for (int j = 1; j < list.size() - k; j++) {
								Double a;
								if ((list.get(j - 1)).compareTo(list.get(j)) < 0) { // 比较两个整数的大小

									a = list.get(j - 1);
									list.set((j - 1), list.get(j));
									list.set(j, a);
								}
							}
						}
						rule_price_2 = rule_price_2.add(new BigDecimal(list.get(0).toString()));// 取最大的一个
					}

				}

			}

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
			// 银卡会员获取奖励积分=付款金额*50
			if (ecUser.getEcBaseCardLevel() != null && ecUser.getEcBaseCardLevel().getCard_type_discount() != null) {
				x = ecUser.getEcBaseCardLevel().getCard_type_discount();
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
			// 处理积分
			if (null != konkaBcompPd.getIntegral() && konkaBcompPd.getIntegral().longValue() > 0) {
				if (konkaBcompPd.getIntegral_type().intValue() == 0) { // 按比例
					pod.setIntegral(price.multiply(new BigDecimal(buy_num)).multiply(
					        new BigDecimal(konkaBcompPd.getIntegral())).multiply(x).divide(new BigDecimal("10000"))
					        .setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if (konkaBcompPd.getIntegral_type().intValue() == 1) { // 按固定
					pod.setIntegral(new BigDecimal(buy_num).multiply(new BigDecimal(konkaBcompPd.getIntegral()))
					        .multiply(x).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			} else {
				pod.setIntegral(price.multiply(new BigDecimal(buy_num)).multiply(x).divide(new BigDecimal("100"))
				        .setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			// 处理返利
			if (null != ecBcompPdRebates) {
				if (ecBcompPdRebates.getB_type().intValue() == 0) { // 按比例
					pod.setRebates(price.multiply(new BigDecimal(buy_num)).multiply(ecBcompPdRebates.getB_value())
					        .divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if (ecBcompPdRebates.getB_type().intValue() == 1) { // 按固定
					pod.setRebates(new BigDecimal(buy_num).multiply(ecBcompPdRebates.getB_value()).setScale(2,
					        BigDecimal.ROUND_HALF_UP));
				}
			}
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
		if (payPrice.doubleValue() < 0.01) {
			payPrice = new BigDecimal("0");
			pshowOrder.setState(5);
		}
		pshowOrder.setDedu_price(deduPrice);
		pshowOrder.setPay_price(payPrice);// 订单付款金额=订单总价-抵扣金额

		// 初始化结束

		// 建立订单信息
		super.getFacade().getPshowOrderService().createPshowOrderWithDetails(pshowOrder);
		logger.info("----------------------------->Order success");

		// 清空购物车信息
		Cookie cookie = new Cookie("SHOPING_CAR_COOKIE", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		EcShoppingCart ecShoppingCart = new EcShoppingCart();
		ecShoppingCart.setUser_id(ecUser.getId());
		ecShoppingCart.setOwn_sys(ecUser.getUser_type());
		getFacade().getEcShoppingCartService().removeEcShoppingCartWithGoodsIdAndUserId(ecShoppingCart);
		logger.info("----------------------------->Shopping car empty success");

		if (payPrice.doubleValue() < 0.001 || pshowOrder.getPay_way() == 0 || pshowOrder.getPay_way() == 1) {
			super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)
			        + "/zxmall/center/Orders.do?';}");
			return null;
		}
		super.renderJavaScript(response, "window.onload=function(){location.href='" + super.getCtxPath(request)
		        + "/zxmall/Payment.do?trade_index=" + trade_index + "&pay_way=" + pshowOrder.getPay_way() + "';}");
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		if (null == ecUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String p_index = super.getPIndexByRequest(request);
		logger.info("----p_index--->{}", p_index);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = new Long[4];
		p_index_array[0] = -1L;
		p_index_array[1] = -1L;
		p_index_array[2] = -1L;
		BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
		baseProvinceListFour.getRow().setCount(10);
		baseProvinceListFour.setP_index(Long.valueOf(p_index));
		List<BaseProvinceListFour> baseProvinceListFourList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourParentList(baseProvinceListFour);
		for (BaseProvinceListFour tt : baseProvinceListFourList) {
			if (tt.getP_level() == 1)
				p_index_array[0] = tt.getP_index();
			if (tt.getP_level() == 2)
				p_index_array[1] = tt.getP_index();
			if (tt.getP_level() == 3)
				p_index_array[2] = tt.getP_index();
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String[] pd_id = (String[]) dynaBean.get("pd_id");
		String[] pd_name = (String[]) dynaBean.get("pd_name");
		String[] pd_num = (String[]) dynaBean.get("pd_num");

		PshowOrder entity = new PshowOrder();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(id)) {

			// 新增

			// -----------------平台订单表--------------------
			entity.setOrder_type(5);// 订单类型：0-默认，5-限时抢购，6-团购
			entity.setOrder_from(ecUser.getUser_type());
			entity.setUuid(UUID.randomUUID().toString());// 通用唯一识别码
			entity.setTrade_index("");// 交易流水号 需要生产规则
			entity.setDept_id(ecUser.getDept_id());
			entity.setState(0);
			entity.setOrder_user_id(ecUser.getId());
			entity.setOrder_user_name(ecUser.getReal_name());
			entity.setIs_del(0);

			BigDecimal total_price = new BigDecimal("0");
			// -----------------平台订单明细表--------------------
			List<PshowOrdeDetails> pshowOrdeDetailsList = new ArrayList<PshowOrdeDetails>();

			// -----------------平台订单明细表--------------------

			// 计算多个商品的订单总金额
			if (null != pd_id && pd_id.length > 0 && null != pd_name && pd_name.length > 0 && null != pd_num
			        && pd_num.length > 0 && pd_id.length == pd_name.length && pd_id.length == pd_num.length) {
				for (int i = 0; i < pd_id.length; i++) {
					if (GenericValidator.isLong(pd_id[i])) {

						// 取全国仓库库存
						EcStocks es = new EcStocks();
						es.setGoods_id(Long.valueOf(pd_id[i]));
						List<EcStocks> esList = super.getFacade().getEcStocksService().getEcStocksList(es);
						int count = 0;
						if (null != esList && esList.size() > 0) {
							for (EcStocks ecStocks : esList) {
								EcBaseStore ebs = new EcBaseStore();
								ebs.setStore_id(ecStocks.getStore_id());
								ebs = super.getFacade().getEcBaseStoreService().getEcBaseStore(ebs);
								if (null != ebs && null != ebs.getOwn_sys() && null != ebs.getStore_type())
									if (ebs.getOwn_sys().intValue() == 1 && ebs.getStore_type().intValue() == 0) {
										count = ecStocks.getStocks().intValue();
									}
							}

						} else {
							super.renderJavaScript(response,
							        "window.onload=function(){alert('对不起，该商品已被抢购完！');location.href='"
							                + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
							return null;
						}
						logger.info("count--->" + count);

						if (count <= 0) {
							super.renderJavaScript(response,
							        "window.onload=function(){alert('对不起，该商品已被抢购完！');location.href='"
							                + super.getCtxPath(request) + "/zxmall/PshowOrderPanicBuying.do';}");
							return null;
						}

						PshowOrdeDetails p = new PshowOrdeDetails();
						p.setPd_id(new Long(pd_id[i]));
						p.setPd_name(pd_name[i]);
						p.setNum(new Long(pd_num[i]));
						p.setState(0);

						List<EcGoodsPrice> ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsId(ecUser
						        .getUser_type(), new Long(pd_id[i]), p_index_array, ecUser.getDept_id(), ecUser
						        .getC_id());
						if (null != ecGoodsPriceList && ecGoodsPriceList.size() > 0) {
							Calendar today = Calendar.getInstance();
							today.setTime(new Date());

							EcGoodsPrice price_3 = new EcGoodsPrice();// 地市区域价格
							EcGoodsPrice price_2 = new EcGoodsPrice();// 分公司价格
							EcGoodsPrice price_0 = new EcGoodsPrice();// 区域价格

							boolean flag_3 = false;
							boolean flag_2 = false;
							boolean flag_0 = false;

							for (EcGoodsPrice e : ecGoodsPriceList) {
								Calendar start_time = Calendar.getInstance();
								start_time.setTime(e.getStart_time());
								Calendar end_time = Calendar.getInstance();
								end_time.setTime(e.getEnd_time());
								if (start_time.compareTo(today) < 0 && today.compareTo(end_time) < 0) {
									if (e.getPrice_type() == 3) {
										price_3 = e;
										flag_3 = true;
									} else if (e.getPrice_type() == 2) {
										price_2 = e;
										flag_2 = true;
									} else if (e.getPrice_type() == 0) {
										price_0 = e;
										flag_0 = true;
									}
								}
							}

							if (flag_3) {
								total_price = total_price.add(price_3.getPrice().multiply(new BigDecimal(p.getNum())));
								p.setPd_id2(price_3.getId());
								p.setPrice(price_3.getPrice());
								p.setTotal_price(price_3.getPrice().multiply(new BigDecimal(p.getNum())));
							} else if (flag_2) {
								total_price = total_price.add(price_2.getPrice().multiply(new BigDecimal(p.getNum())));
								p.setPd_id2(price_2.getId());
								p.setPrice(price_2.getPrice());
								p.setTotal_price(price_2.getPrice().multiply(new BigDecimal(p.getNum())));
							} else if (flag_0) {
								total_price = total_price.add(price_0.getPrice().multiply(new BigDecimal(p.getNum())));
								p.setPd_id2(price_0.getId());
								p.setPrice(price_0.getPrice());
								p.setTotal_price(price_0.getPrice().multiply(new BigDecimal(p.getNum())));
							}

						}
						pshowOrdeDetailsList.add(p);
					}
				}
			}
			entity.setTotal_price(total_price);
			// -----------------平台订单表--------------------
			// entity.setPshowOrdeDetailsList(pshowOrdeDetailsList);
			// super.getFacade().getPshowOrderService().createPshowOrder(entity);

			// saveMessage(request, "entity.inserted");

		} else {
			// 修改

			// entity.setId(Long.valueOf(id));

			// super.getFacade().getPshowOrderService().modifyPshowOrder(entity);
			// saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}
}