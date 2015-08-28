package com.ebiz.mmt.web.struts.zxmall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.EcBcompPdUp;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class PshowOrderPanicBuyingAction extends BaseMemberAction {

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
		        new Long(p_index), 5, null, ecUser.getDept_id(), ecUser.getC_id(), null);

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

					Calendar c = Calendar.getInstance();
					// c.setTime(ecBcompPdUpList.get(0).getDown_time());
					if (ecg != null && ecg.getEnd_time() != null) {
						c.setTime(ecg.getEnd_time());// 
					}
					// t.setTimeRemains(c.getTimeInMillis());//

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());

					t.setTimeRemains(c.getTimeInMillis() - calendar.getTimeInMillis());// 离抢购结束还剩多久
				}
			}
		}

		request.setAttribute("entityList", entityList);

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

					Calendar c = Calendar.getInstance();
					// c.setTime(ecBcompPdUpList.get(0).getDown_time());
					if (ecg != null && ecg.getEnd_time() != null) {
						c.setTime(ecg.getEnd_time());// 
					}// 
					// t.setTimeRemains(c.getTimeInMillis());//

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());

					entity.setTimeRemains(c.getTimeInMillis() - calendar.getTimeInMillis());// 离抢购结束还剩多久

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