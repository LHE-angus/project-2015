package com.ebiz.mmt.web.struts.sfmall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-13
 */
public class PdShowAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		EcUser ecUser = super.getSfEcUser(request);

		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request);
		request.setAttribute("p_index", p_index);
		// 得到当前请求的地址
		request.setAttribute("p_full_name", super.getPIndexName(p_index, ""));

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		// 查询分公司
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

		// 查询商品详细信息
		KonkaBcompPd konkaBcompPd = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request), ecUser
		        .getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array, ecUser.getDept_id(),
		        ecUser.getCust_id());
		if (null == konkaBcompPd) {
			String msg = super.getMessage(request, "ec.bcomp.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 更新浏览量
		KonkaBcompPd t = new KonkaBcompPd();
		t.setId(konkaBcompPd.getId());
		t.setView_counts(1L);
		getFacade().getKonkaBcompPdService().modifyKonkaBcompPdForViewCountOrSaleNum(t);

		// 促销活动
		EcRuleGoods ecg = new EcRuleGoods();
		ecg.setGoods_id(Long.valueOf(goods_id));
		List<EcRuleGoods> ecgList1 = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(ecg);

		List<EcRuleGoods> ecgList = new ArrayList<EcRuleGoods>();
		for (EcRuleGoods ecRuleGoods : ecgList1) {
			EcRule ec = new EcRule();
			ec.setId(ecRuleGoods.getRule_id());
			ec = super.getFacade().getEcRuleService().getEcRule(ec);
			if (null != ec && ec.getInfo_state().intValue() == 1) {
				ecgList.add(ecRuleGoods);
			}
		}

		List<EcRule> ecRuleList = new ArrayList<EcRule>();
		Long num = 0L;
		if (null != ecgList && ecgList.size() > 0) {
			List<EcRule> egList = new ArrayList<EcRule>();
			List<EcRule> egList2 = new ArrayList<EcRule>();
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

				Date date = new Date();
				EcRule er2 = new EcRule();
				er2.setRule_type(1);// 限制数量
				er2.setId(ecRuleGoods.getRule_id());
				if (ecUser.getUser_type().intValue() == 2) {
					er2.setDept_id(ecUser.getDept_id());
					er2.setOwn_sys(2);
				} else if (ecUser.getUser_type().intValue() == 1) {
					er2.setOwn_sys(1);
				}
				er2 = super.getFacade().getEcRuleService().getEcRule(er2);
				if (null != er2 && er2.getRule_start_date().getTime() <= date.getTime()
				        && er2.getRule_end_date().getTime() > date.getTime()) {
					egList2.add(er2);
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
				List<EcRule> list = new ArrayList<EcRule>();
				for (EcRule ec : egList) {
					if (ec.getIs_price() == 1 && ec.getInfo_state() == 1 && null != ec.getRule_area_allow()
					        && !"".equals(ec.getRule_area_allow())) {
						if (ec.getRule_area_allow().contains(bf.getP_index().toString())) {
							list.add(ec);
						}
					}
				}

				logger.info("bf------------->" + bf);
				ecRuleList.add(list.get(0));

			}

			if (null != egList2 && egList2.size() > 0) {

				EcRule ecRule = egList2.get(0);
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
									if (pshowOrdeDetails.getPd_id().intValue() == Long.valueOf(goods_id).intValue()) {
										num += pshowOrdeDetails.getNum();
									}
								}
							}
						}
						request.setAttribute("exit_num", num);// 某人在某时间段已经购买该商品数量
						request.setAttribute("max_num", ecRule.getRule_num_max());
					} else {
						request.setAttribute("exit_num", num);// 没有购买
						request.setAttribute("max_num", ecRule.getRule_num_max());
					}
				}
				ecRuleList.add(ecRule);
			} else {
				request.setAttribute("exit_num", num);// 没有购买
				request.setAttribute("max_num", 100000);
			}

		}
		konkaBcompPd.setEcRuleList(ecRuleList);

		// 将数据存入 request 作用域
		request.setAttribute("konkaBcompPd", konkaBcompPd);

		// 销量排行前5
		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,
		        ecUser.getDept_id(), ecUser.getCust_id());
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);

		// // 取4个配件产品
		// if(konkaBcompPd.getProd_type()!=null&&konkaBcompPd.getProd_type().intValue()==0){
		// Integer[] prod_type=new Integer[]{10};
		// List<KonkaBcompPd> bcomp_pd_list_pj =
		// this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		// Long.valueOf(p_index), null,4,
		// ecUser.getDept_id(),ecUser.getCust_id(),prod_type);
		// request.setAttribute("bcomp_pd_list_pj", bcomp_pd_list_pj);
		// }
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @author Jiang,JiaYong
	 * @version 2013-09-16
	 * @desc 根据商品ID和地区信息得到商品详细信息包含库存，价格等
	 */
	public ActionForward ajaxGetKonkaBcompPdByGoodsIdAndPindex(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String p_index = (String) dynaBean.get("p_index");
		request.getSession().setAttribute("p_index", p_index);
		EcUser ecUser = super.getSfEcUser(request);

		// 关键参数丢失
		if (!GenericValidator.isLong(goods_id) || !GenericValidator.isLong(p_index)) {
			super.renderJson(response, "");
			return null;
		}

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		// 查询分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}

		// 查询价格
		List<EcGoodsPrice> ecpList = getEcGoodsPriceListWithPindexAndGoodsId(ecUser.getUser_type(), Long
		        .valueOf(goods_id), p_index_array, ecUser.getDept_id(), ecUser.getCust_id());

		// 查询库存
		List<EcStocks> esList = getEcStocksListWithPindexAndGoodsId(1, Long.valueOf(goods_id), p_index_array,
		        dept_sn_array);

		// 构建数据结构
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", goods_id);
		map.put("p_index", p_index);
		map.put("price", "");
		map.put("original_price", "");
		if (null != ecpList && 0 != ecpList.size()) {
			map.put("price", ecpList.get(0).getPrice());
			map.put("original_price", ecpList.get(0).getOriginal_price());
		}
		map.put("stocks", "");

		KonkaBcompPd pd = new KonkaBcompPd();// 获取r3库存
		pd.setId(new Long(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		int r3store_num = 0;
		if (pd.getProd_type() != null && pd.getProd_type().intValue() == 0) {
			try {
				if (pd != null) {
					r3store_num = super.getStockCount(super.getCtxPath(request), pd.getPd_sn()).intValue();
				}
			} catch (Exception e) {
			}
			;
		}
		if (null != esList && 0 != esList.size()) {
			r3store_num = esList.get(0).getStocks().intValue() + r3store_num;
			map.put("stocks", r3store_num);
		}

		// 查询快递到达天数
		if (null != esList && 0 != esList.size()) {
			EcBaseExpressReachDay eberd = new EcBaseExpressReachDay();
			eberd.setIs_del(0);
			eberd.getRow().setCount(10);
			eberd.setStore_id(esList.get(0).getStore_id());
			eberd.getMap().put("order_by_area_code_desc", true);
			eberd.getMap().put("area_code_in", new Long[] { p_index_array[3], p_index_array[2], p_index_array[1] }); // 查询地区、县、市倒序排列
			List<EcBaseExpressReachDay> eberdList = getFacade().getEcBaseExpressReachDayService()
			        .getEcBaseExpressReachDayList(eberd);
			if (0 != eberdList.size())
				map.put("max_reach_day", eberdList.get(0).getMax_reach_day());
		}
		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}
}
