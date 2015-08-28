package com.ebiz.mmt.web.struts.jxc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.web.Constants;

public class KonkaJxcShopStatisticsByModelAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id_for_search = (String) dynaBean.get("pd_id");

		// 初始化列表页面的起始时间
		Date now = new Date();
		String add_date_st = DateFormatUtils.format(now, "yyyy-MM-") + "01";// 本月第一天
		String add_date_en = DateFormatUtils.format(now, "yyyy-MM-dd");// 当前日

		// ==1== 取规格
		Long prop_id = Constants.PROP_ID;// 屏幕尺寸
		PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
		pdModelPropsVal.setProp_id(prop_id);
		List<String> stringPropValueList = getFacade().getPdModelPropsValService().getPdModelPropsValGroupByList(
				pdModelPropsVal);

		List<PdModelPropsVal> pdModelPropsValList = new ArrayList<PdModelPropsVal>();
		// 总的合计信息
		BigDecimal qcjc_count_total = new BigDecimal(0);
		BigDecimal qcjc_money_total = new BigDecimal(0);
		BigDecimal rcjh_count_total = new BigDecimal(0);
		BigDecimal rcjh_money_total = new BigDecimal(0);
		BigDecimal rcch_count_total = new BigDecimal(0);
		BigDecimal rcch_money_total = new BigDecimal(0);
		BigDecimal qmjc_count_total = new BigDecimal(0);
		BigDecimal qmjc_money_total = new BigDecimal(0);

		List<Long> pd_ids = new ArrayList<Long>();// 取型号ids用于搜索条件

		// 取所有的网点的shop_id
		JxcPd jxcPdForShopId = new JxcPd();
		List<JxcPd> jxcPdForShopIdList = getFacade().getJxcPdService().getJxcPdList(jxcPdForShopId);

		for (String propValue : stringPropValueList) {
			// ==2== 取机型（管理端型号）
			PdModelPropsVal pdModelPropsVal2 = new PdModelPropsVal();
			pdModelPropsVal2.setProp_id(prop_id);
			pdModelPropsVal2.setProp_value(propValue);
			List<PdModelPropsVal> pdModelPropsValPdIdList = getFacade().getPdModelPropsValService()
					.getPdModelPropsValList(pdModelPropsVal2);// 管理端型号

			for (PdModelPropsVal pmpv : pdModelPropsValPdIdList) {
				pd_ids.add(pmpv.getPd_id());
			}

			if (StringUtils.isNotBlank(pd_id_for_search)) {
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_id(prop_id);
				pdModelPropsVal3.setProp_value(propValue);
				pdModelPropsVal3.setPd_id(new Long(pd_id_for_search));
				pdModelPropsValPdIdList = getFacade().getPdModelPropsValService().getPdModelPropsValList(
						pdModelPropsVal3);// 管理端型号
			}

			if (pdModelPropsValPdIdList.size() > 0) {
				for (int i = 0; i < pdModelPropsValPdIdList.size(); i++) {
					// 一个型号中所有网点的合计信息
					BigDecimal qcjc_count_all = new BigDecimal(0);
					BigDecimal qcjc_money_all = new BigDecimal(0);
					BigDecimal rcjh_count_all = new BigDecimal(0);
					BigDecimal rcjh_money_all = new BigDecimal(0);
					BigDecimal rcch_count_all = new BigDecimal(0);
					BigDecimal rcch_money_all = new BigDecimal(0);
					BigDecimal qmjc_count_all = new BigDecimal(0);
					BigDecimal qmjc_money_all = new BigDecimal(0);
					// 单价
					BigDecimal qcjc_price = new BigDecimal(0);
					BigDecimal rcjh_price = new BigDecimal(0);
					BigDecimal rcch_price = new BigDecimal(0);

					// 循环每一个网点取数据然后累加
					for (JxcPd jp : jxcPdForShopIdList) {
						// ==3== 网点对应的型号
						JxcPd jxcPdId = new JxcPd();
						jxcPdId.setShop_id(jp.getShop_id());
						jxcPdId.setOut_sys_id(pdModelPropsValPdIdList.get(i).getPd_id());
						jxcPdId = getFacade().getJxcPdService().getJxcPd(jxcPdId);

						// 一个型号中一个网点的合计信息
						BigDecimal qcjc_count_one = new BigDecimal(0);
						BigDecimal qcjc_money_one = new BigDecimal(0);
						BigDecimal rcjh_count_one = new BigDecimal(0);
						BigDecimal rcjh_money_one = new BigDecimal(0);
						BigDecimal rcch_count_one = new BigDecimal(0);
						BigDecimal rcch_money_one = new BigDecimal(0);
						BigDecimal qmjc_count_one = new BigDecimal(0);
						BigDecimal qmjc_money_one = new BigDecimal(0);

						if (null != jxcPdId) {

							// ==4== 取该型号对应的统计信息
							Long jxc_pd_id = jxcPdId.getId();

							// =====统计start================================================================
							JxcPd entity = new JxcPd();
							super.copyProperties(entity, form);
							entity.setShop_id(jp.getShop_id());
							// entity.getMap().put("date_st", add_date_st);
							entity.getMap().put("shop_id", jp.getShop_id());// 所有的网点中的一个
							entity.getMap().put("rcch_date_st", add_date_st);
							entity.getMap().put("rcch_date_en", add_date_en);
							entity.getMap().put("jxc_pd_id", jxc_pd_id);// 型号

							List<JxcPd> list = getFacade().getJxcPdService().getShopSellStatisticsList(entity);
							List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
							for (JxcPd jxcPd : list) {
								Long pd_id = jxcPd.getId();
								// 期初结存
								JxcPd qcjc_pd = new JxcPd();
								qcjc_pd.getMap().put("pd_id", pd_id);
								qcjc_pd.getMap().put("qcjc_date", add_date_st);
								qcjc_pd = getFacade().getJxcPdService().getQcjcForShopSellStatistics(qcjc_pd);
								BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
								BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
								if (qcjc_count == null) {
									qcjc_count = new BigDecimal("0");
								}
								if (qcjc_money == null) {
									qcjc_money = new BigDecimal("0");
								}
								jxcPd.setQcjc_pd(qcjc_pd);

								// 日常进货
								BigDecimal rcjh_count = (BigDecimal) jxcPd.getMap().get("jh_count");
								BigDecimal rcjh_sum_money = (BigDecimal) jxcPd.getMap().get("jh_sum_money");
								if (rcjh_count == null) {
									rcjh_count = new BigDecimal("0");
								}
								if (rcjh_sum_money == null) {
									rcjh_sum_money = new BigDecimal("0");
								}
								// 日常出货
								BigDecimal rcch_count = (BigDecimal) jxcPd.getMap().get("ch_count");
								BigDecimal rcch_sum_money = (BigDecimal) jxcPd.getMap().get("ch_sum_money");
								if (rcch_count == null) {
									rcch_count = new BigDecimal("0");
								}
								if (rcch_sum_money == null) {
									rcch_sum_money = new BigDecimal("0");
								}

								// 盘存、盘亏
								BigDecimal pc_count = (BigDecimal) jxcPd.getMap().get("pc_count");
								BigDecimal pc_sum_money = (BigDecimal) jxcPd.getMap().get("pc_sum_money");
								if (pc_count == null) {
									pc_count = new BigDecimal("0");
								}
								if (pc_sum_money == null) {
									pc_sum_money = new BigDecimal("0");
								}

								// 期末结存
								Long qmjc_count = Long.valueOf(qcjc_count.add(rcjh_count).add(pc_count).subtract(
										rcch_count).toString());
								Double qmjc_money = qcjc_money.doubleValue() + rcjh_sum_money.doubleValue()
										+ pc_sum_money.doubleValue() - rcch_sum_money.doubleValue();
								jxcPd.setQmjc_count(qmjc_count.doubleValue());
								jxcPd.setQmjc_money(qmjc_money);

								// 一个型号中的一个网点合计期初结存
								qcjc_count_one = qcjc_count_one.add(qcjc_count);
								qcjc_money_one = qcjc_count_one.add(qcjc_money);

								// 一个型号中的一个网点合计日常进货
								rcjh_count_one = rcjh_count_one.add(rcjh_count);
								rcjh_money_one = rcjh_money_one.add(rcjh_sum_money);

								// 一个型号中的一个网点合计日常出货
								rcch_count_one = rcch_count_one.add(rcch_count);
								rcch_money_one = rcch_money_one.add(rcch_sum_money);

								// 一个型号中的一个网点合计期末结存
								qmjc_count_one = qmjc_count_one.add(new BigDecimal(qmjc_count));
								qmjc_money_one = qmjc_money_one.add(new BigDecimal(qmjc_money));

								jxcPdList.add(jxcPd);
							}
							// ========统计end=====================================================================================
							// pdModelPropsValPdIdList.get(i).setJxcPdList(jxcPdList);// 每个型号对应的统计数据
						} else {
							// 数据全部设为0--页面处理
						}
						// 一个型号中的一个网点合计期初结存
						qcjc_count_all = qcjc_count_all.add(qcjc_count_one);
						qcjc_money_all = qcjc_count_all.add(qcjc_money_one);

						// 一个型号中的一个网点合计日常进货
						rcjh_count_all = rcjh_count_all.add(rcjh_count_one);
						rcjh_money_all = rcjh_money_all.add(rcjh_money_one);

						// 一个型号中的一个网点合计日常出货
						rcch_count_all = rcch_count_all.add(rcch_count_one);
						rcch_money_all = rcch_money_all.add(rcch_money_one);

						// 一个型号中的一个网点合计期末结存
						qmjc_count_all = qmjc_count_all.add(qmjc_count_one);
						qmjc_money_all = qmjc_money_all.add(qmjc_money_one);

						if (qcjc_count_all.doubleValue() != 0 && qcjc_money_all.doubleValue() != 0) {
							qcjc_price = qcjc_money_all.divide(qcjc_count_all, 2, BigDecimal.ROUND_HALF_UP);// 单价

						}
						if (rcjh_count_all.doubleValue() != 0 && rcjh_money_all.doubleValue() != 0) {
							rcjh_price = rcjh_money_all.divide(rcjh_count_all, 2, BigDecimal.ROUND_HALF_UP);// 单价
						}
						if (rcch_money_all.doubleValue() != 0 && rcch_count_all.doubleValue() != 0) {
							rcch_price = rcch_money_all.divide(rcch_count_all, 2, BigDecimal.ROUND_HALF_UP);// 单价
						}

					}
					// 一个型号的合计期初结存
					qcjc_count_total = qcjc_count_total.add(qcjc_count_all);
					qcjc_money_total = qcjc_money_total.add(qcjc_money_all);

					// 一个型号的合计日常进货
					rcjh_count_total = rcjh_count_total.add(rcjh_count_all);
					rcjh_money_total = rcjh_money_total.add(rcjh_money_all);

					// 一个型号的合计日常出货
					rcch_count_total = rcch_count_total.add(rcch_count_all);
					rcch_money_total = rcch_money_total.add(rcch_money_all);

					// 一个型号的合计期末结存
					qmjc_count_total = qmjc_count_total.add(qmjc_count_all);
					qmjc_money_total = qmjc_money_total.add(qmjc_money_all);

					// 每个型号对应的统计数据
					pdModelPropsValPdIdList.get(i).getMap().put("qcjc_count_all", qcjc_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("qcjc_money_all", qcjc_money_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcjh_count_all", rcjh_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcjh_money_all", rcjh_money_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcch_count_all", rcch_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcch_money_all", rcch_money_all);
					pdModelPropsValPdIdList.get(i).getMap().put("qmjc_count_all", qmjc_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("qmjc_money_all", qmjc_money_all);

					pdModelPropsValPdIdList.get(i).getMap().put("qcjc_price", qcjc_price);
					pdModelPropsValPdIdList.get(i).getMap().put("rcjh_price", rcjh_price);
					pdModelPropsValPdIdList.get(i).getMap().put("rcch_price", rcch_price);

				}
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_value(propValue);// 规格
				pdModelPropsVal3.setPdModelPropsValList(pdModelPropsValPdIdList);// 型号
				pdModelPropsValList.add(pdModelPropsVal3);
			} else {
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_value(propValue);// 规格
				pdModelPropsValList.add(pdModelPropsVal3);
			}
		}

		// ----------------------------------------------------------------
		// 型号搜索条件
		Long[] pdIds = new Long[pd_ids.size()];
		for (int i = 0; i < pd_ids.size(); i++) {
			pdIds[i] = pd_ids.get(i);
		}
		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("pdIds", pdIds);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pePdModelList", pePdModelList);

		dynaBean.set("qcjc_count_total", qcjc_count_total);
		dynaBean.set("qcjc_money_total", qcjc_money_total);

		dynaBean.set("rcjh_count_total", rcjh_count_total);
		dynaBean.set("rcjh_money_total", rcjh_money_total);

		dynaBean.set("rcch_count_total", rcch_count_total);
		dynaBean.set("rcch_money_total", rcch_money_total);

		dynaBean.set("qmjc_count_total", qmjc_count_total);
		dynaBean.set("qmjc_money_total", qmjc_money_total);

		request.setAttribute("add_date_st", add_date_st);
		request.setAttribute("add_date_en", add_date_en);
		request.setAttribute("pdModelPropsValList", pdModelPropsValList);
		request.setAttribute("pd_id", pd_id_for_search);

		return mapping.findForward("list");
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.renderExcelWithEncoding(request, response, "GBK");
		return null;
	}

	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id_for_search = (String) dynaBean.get("pd_id");

		// 初始化列表页面的起始时间
		Date now = new Date();
		String add_date_st = DateFormatUtils.format(now, "yyyy-MM-") + "01";// 本月第一天
		String add_date_en = DateFormatUtils.format(now, "yyyy-MM-dd");// 当前日

		// ==1== 取规格
		Long prop_id = Constants.PROP_ID;// 屏幕尺寸
		PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
		pdModelPropsVal.setProp_id(prop_id);
		List<String> stringPropValueList = getFacade().getPdModelPropsValService().getPdModelPropsValGroupByList(
				pdModelPropsVal);

		List<PdModelPropsVal> pdModelPropsValList = new ArrayList<PdModelPropsVal>();
		// 总的合计信息
		BigDecimal qcjc_count_total = new BigDecimal(0);
		BigDecimal qcjc_money_total = new BigDecimal(0);
		BigDecimal rcjh_count_total = new BigDecimal(0);
		BigDecimal rcjh_money_total = new BigDecimal(0);
		BigDecimal rcch_count_total = new BigDecimal(0);
		BigDecimal rcch_money_total = new BigDecimal(0);
		BigDecimal qmjc_count_total = new BigDecimal(0);
		BigDecimal qmjc_money_total = new BigDecimal(0);

		List<Long> pd_ids = new ArrayList<Long>();// 取型号ids用于搜索条件

		// 取所有的网点的shop_id
		JxcPd jxcPdForShopId = new JxcPd();
		List<JxcPd> jxcPdForShopIdList = getFacade().getJxcPdService().getJxcPdList(jxcPdForShopId);

		for (String propValue : stringPropValueList) {
			// ==2== 取机型（管理端型号）
			PdModelPropsVal pdModelPropsVal2 = new PdModelPropsVal();
			pdModelPropsVal2.setProp_id(prop_id);
			pdModelPropsVal2.setProp_value(propValue);
			List<PdModelPropsVal> pdModelPropsValPdIdList = getFacade().getPdModelPropsValService()
					.getPdModelPropsValList(pdModelPropsVal2);// 管理端型号

			for (PdModelPropsVal pmpv : pdModelPropsValPdIdList) {
				pd_ids.add(pmpv.getPd_id());
			}

			if (StringUtils.isNotBlank(pd_id_for_search)) {
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_id(prop_id);
				pdModelPropsVal3.setProp_value(propValue);
				pdModelPropsVal3.setPd_id(new Long(pd_id_for_search));
				pdModelPropsValPdIdList = getFacade().getPdModelPropsValService().getPdModelPropsValList(
						pdModelPropsVal3);// 管理端型号
			}

			if (pdModelPropsValPdIdList.size() > 0) {
				for (int i = 0; i < pdModelPropsValPdIdList.size(); i++) {
					// 一个型号中所有网点的合计信息
					BigDecimal qcjc_count_all = new BigDecimal(0);
					BigDecimal qcjc_money_all = new BigDecimal(0);
					BigDecimal rcjh_count_all = new BigDecimal(0);
					BigDecimal rcjh_money_all = new BigDecimal(0);
					BigDecimal rcch_count_all = new BigDecimal(0);
					BigDecimal rcch_money_all = new BigDecimal(0);
					BigDecimal qmjc_count_all = new BigDecimal(0);
					BigDecimal qmjc_money_all = new BigDecimal(0);
					// 单价
					BigDecimal qcjc_price = new BigDecimal(0);
					BigDecimal rcjh_price = new BigDecimal(0);
					BigDecimal rcch_price = new BigDecimal(0);

					// 循环每一个网点取数据然后累加
					for (JxcPd jp : jxcPdForShopIdList) {
						// ==3== 网点对应的型号
						JxcPd jxcPdId = new JxcPd();
						jxcPdId.setShop_id(jp.getShop_id());
						jxcPdId.setOut_sys_id(pdModelPropsValPdIdList.get(i).getPd_id());
						jxcPdId = getFacade().getJxcPdService().getJxcPd(jxcPdId);

						// 一个型号中一个网点的合计信息
						BigDecimal qcjc_count_one = new BigDecimal(0);
						BigDecimal qcjc_money_one = new BigDecimal(0);
						BigDecimal rcjh_count_one = new BigDecimal(0);
						BigDecimal rcjh_money_one = new BigDecimal(0);
						BigDecimal rcch_count_one = new BigDecimal(0);
						BigDecimal rcch_money_one = new BigDecimal(0);
						BigDecimal qmjc_count_one = new BigDecimal(0);
						BigDecimal qmjc_money_one = new BigDecimal(0);

						if (null != jxcPdId) {

							// ==4== 取该型号对应的统计信息
							Long jxc_pd_id = jxcPdId.getId();

							// =====统计start================================================================
							JxcPd entity = new JxcPd();
							super.copyProperties(entity, form);
							entity.setShop_id(jp.getShop_id());
							// entity.getMap().put("date_st", add_date_st);
							entity.getMap().put("shop_id", jp.getShop_id());// 所有的网点中的一个
							entity.getMap().put("rcch_date_st", add_date_st);
							entity.getMap().put("rcch_date_en", add_date_en);
							entity.getMap().put("jxc_pd_id", jxc_pd_id);// 型号

							List<JxcPd> list = getFacade().getJxcPdService().getShopSellStatisticsList(entity);
							List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
							for (JxcPd jxcPd : list) {
								Long pd_id = jxcPd.getId();
								// 期初结存
								JxcPd qcjc_pd = new JxcPd();
								qcjc_pd.getMap().put("pd_id", pd_id);
								qcjc_pd.getMap().put("qcjc_date", add_date_st);
								qcjc_pd = getFacade().getJxcPdService().getQcjcForShopSellStatistics(qcjc_pd);
								BigDecimal qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
								BigDecimal qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
								if (qcjc_count == null) {
									qcjc_count = new BigDecimal("0");
								}
								if (qcjc_money == null) {
									qcjc_money = new BigDecimal("0");
								}
								jxcPd.setQcjc_pd(qcjc_pd);

								// 日常进货
								BigDecimal rcjh_count = (BigDecimal) jxcPd.getMap().get("jh_count");
								BigDecimal rcjh_sum_money = (BigDecimal) jxcPd.getMap().get("jh_sum_money");
								if (rcjh_count == null) {
									rcjh_count = new BigDecimal("0");
								}
								if (rcjh_sum_money == null) {
									rcjh_sum_money = new BigDecimal("0");
								}
								// 日常出货
								BigDecimal rcch_count = (BigDecimal) jxcPd.getMap().get("ch_count");
								BigDecimal rcch_sum_money = (BigDecimal) jxcPd.getMap().get("ch_sum_money");
								if (rcch_count == null) {
									rcch_count = new BigDecimal("0");
								}
								if (rcch_sum_money == null) {
									rcch_sum_money = new BigDecimal("0");
								}

								// 盘存、盘亏
								BigDecimal pc_count = (BigDecimal) jxcPd.getMap().get("pc_count");
								BigDecimal pc_sum_money = (BigDecimal) jxcPd.getMap().get("pc_sum_money");
								if (pc_count == null) {
									pc_count = new BigDecimal("0");
								}
								if (pc_sum_money == null) {
									pc_sum_money = new BigDecimal("0");
								}

								// 期末结存
								Long qmjc_count = Long.valueOf(qcjc_count.add(rcjh_count).add(pc_count).subtract(
										rcch_count).toString());
								Double qmjc_money = qcjc_money.doubleValue() + rcjh_sum_money.doubleValue()
										+ pc_sum_money.doubleValue() - rcch_sum_money.doubleValue();
								jxcPd.setQmjc_count(qmjc_count.doubleValue());
								jxcPd.setQmjc_money(qmjc_money);

								// 一个型号中的一个网点合计期初结存
								qcjc_count_one = qcjc_count_one.add(qcjc_count);
								qcjc_money_one = qcjc_count_one.add(qcjc_money);

								// 一个型号中的一个网点合计日常进货
								rcjh_count_one = rcjh_count_one.add(rcjh_count);
								rcjh_money_one = rcjh_money_one.add(rcjh_sum_money);

								// 一个型号中的一个网点合计日常出货
								rcch_count_one = rcch_count_one.add(rcch_count);
								rcch_money_one = rcch_money_one.add(rcch_sum_money);

								// 一个型号中的一个网点合计期末结存
								qmjc_count_one = qmjc_count_one.add(new BigDecimal(qmjc_count));
								qmjc_money_one = qmjc_money_one.add(new BigDecimal(qmjc_money));

								jxcPdList.add(jxcPd);
							}
							// ========统计end=====================================================================================
							// pdModelPropsValPdIdList.get(i).setJxcPdList(jxcPdList);// 每个型号对应的统计数据
						} else {
							// 数据全部设为0--页面处理
						}
						// 一个型号中的一个网点合计期初结存
						qcjc_count_all = qcjc_count_all.add(qcjc_count_one);
						qcjc_money_all = qcjc_count_all.add(qcjc_money_one);

						// 一个型号中的一个网点合计日常进货
						rcjh_count_all = rcjh_count_all.add(rcjh_count_one);
						rcjh_money_all = rcjh_money_all.add(rcjh_money_one);

						// 一个型号中的一个网点合计日常出货
						rcch_count_all = rcch_count_all.add(rcch_count_one);
						rcch_money_all = rcch_money_all.add(rcch_money_one);

						// 一个型号中的一个网点合计期末结存
						qmjc_count_all = qmjc_count_all.add(qmjc_count_one);
						qmjc_money_all = qmjc_money_all.add(qmjc_money_one);

						if (qcjc_count_all.doubleValue() != 0 && qcjc_money_all.doubleValue() != 0) {
							qcjc_price = qcjc_money_all.divide(qcjc_count_all, 2, BigDecimal.ROUND_HALF_UP);// 单价

						}
						if (rcjh_count_all.doubleValue() != 0 && rcjh_money_all.doubleValue() != 0) {
							rcjh_price = rcjh_money_all.divide(rcjh_count_all, 2, BigDecimal.ROUND_HALF_UP);// 单价
						}
						if (rcch_money_all.doubleValue() != 0 && rcch_count_all.doubleValue() != 0) {
							rcch_price = rcch_money_all.divide(rcch_count_all, 2, BigDecimal.ROUND_HALF_UP);// 单价
						}

					}
					// 一个型号的合计期初结存
					qcjc_count_total = qcjc_count_total.add(qcjc_count_all);
					qcjc_money_total = qcjc_money_total.add(qcjc_money_all);

					// 一个型号的合计日常进货
					rcjh_count_total = rcjh_count_total.add(rcjh_count_all);
					rcjh_money_total = rcjh_money_total.add(rcjh_money_all);

					// 一个型号的合计日常出货
					rcch_count_total = rcch_count_total.add(rcch_count_all);
					rcch_money_total = rcch_money_total.add(rcch_money_all);

					// 一个型号的合计期末结存
					qmjc_count_total = qmjc_count_total.add(qmjc_count_all);
					qmjc_money_total = qmjc_money_total.add(qmjc_money_all);

					// 每个型号对应的统计数据
					pdModelPropsValPdIdList.get(i).getMap().put("qcjc_count_all", qcjc_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("qcjc_money_all", qcjc_money_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcjh_count_all", rcjh_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcjh_money_all", rcjh_money_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcch_count_all", rcch_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("rcch_money_all", rcch_money_all);
					pdModelPropsValPdIdList.get(i).getMap().put("qmjc_count_all", qmjc_count_all);
					pdModelPropsValPdIdList.get(i).getMap().put("qmjc_money_all", qmjc_money_all);

					pdModelPropsValPdIdList.get(i).getMap().put("qcjc_price", qcjc_price);
					pdModelPropsValPdIdList.get(i).getMap().put("rcjh_price", rcjh_price);
					pdModelPropsValPdIdList.get(i).getMap().put("rcch_price", rcch_price);

				}
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_value(propValue);// 规格
				pdModelPropsVal3.setPdModelPropsValList(pdModelPropsValPdIdList);// 型号
				pdModelPropsValList.add(pdModelPropsVal3);
			} else {
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_value(propValue);// 规格
				pdModelPropsValList.add(pdModelPropsVal3);
			}
		}

		// ----------------------------------------------------------------
		// 型号搜索条件
		Long[] pdIds = new Long[pd_ids.size()];
		for (int i = 0; i < pd_ids.size(); i++) {
			pdIds[i] = pd_ids.get(i);
		}
		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("pdIds", pdIds);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pePdModelList", pePdModelList);

		dynaBean.set("qcjc_count_total", qcjc_count_total);
		dynaBean.set("qcjc_money_total", qcjc_money_total);

		dynaBean.set("rcjh_count_total", rcjh_count_total);
		dynaBean.set("rcjh_money_total", rcjh_money_total);

		dynaBean.set("rcch_count_total", rcch_count_total);
		dynaBean.set("rcch_money_total", rcch_money_total);

		dynaBean.set("qmjc_count_total", qmjc_count_total);
		dynaBean.set("qmjc_money_total", qmjc_money_total);

		request.setAttribute("add_date_st", add_date_st);
		request.setAttribute("add_date_en", add_date_en);
		request.setAttribute("pdModelPropsValList", pdModelPropsValList);
		return new ActionForward("/../jxc/KonkaJxcShopStatisticsByModel/_print_statistics_by_model_list.jsp");

	}
}
