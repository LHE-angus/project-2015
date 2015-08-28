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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zhang,Xufeng
 * @version 2011-11-17
 */
public class KonkaJxcShopSellStatisticsAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String is_agent = (String) dynaBean.get("is_agent");
		String r3_code_like = (String) dynaBean.get("r3_code_like");

		String is_entp_shop = (String) dynaBean.get("is_entp_shop");// 是否买卖提网点--代理商，分销情况用
		String agent_id = (String) dynaBean.get("agent_id");// 代理商的R3 id--代理商，分销情况用---
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");// 搜索的R3网点id
		String mmt_shop_id = (String) dynaBean.get("mmt_shop_id");// 搜索的买卖提网点id

		// String start_date = (String) dynaBean.get("start_date");
		// String end_date = (String) dynaBean.get("end_date");

		// 取网点列表--默认取分公司下的非代理商（经销商）网点列表--当选择的是代理商时，再显示经销商的列表
		PeProdUser ui = super.getSessionUserInfo(request);
		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());

		if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {
			if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_FGS
					&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_YWY) {// ===分公司\\经营部、办事处===

				if (StringUtils.isNotBlank(is_entp_shop)) {
					// 买卖提网点
					if (GenericValidator.isLong(agent_id)) {
						// 是代理商，取下级经销商列表，根据代理商R3id 查询 KonkaBranchCategory中category_id=20的经销商的mmt_shop_id

						KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
						konkaBranchCategory.setCategory_id(20L);
						konkaBranchCategory.setKonka_r3_id(new Long(agent_id));
						List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
								.getKonkaBranchCategoryList(konkaBranchCategory);

						// 买卖提网点搜索条件--代理商下面的经销商
						for (KonkaBranchCategory kbc : konkaBranchCategoryList) {
							MmtEntpShop mmtEntpShop = new MmtEntpShop();
							mmtEntpShop.setShop_id(kbc.getMmt_shop_id());
							mmtEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(mmtEntpShop);
							if (null != mmtEntpShop) {
								kbc.getMap().put("jxs_name", mmtEntpShop.getShop_name());
							}
						}
						request.setAttribute("konkaBranchCategoryList", konkaBranchCategoryList);

						// 根据经销商的mmt_shop_id取网点的信息
						Long[] mmtShopIds = new Long[konkaBranchCategoryList.size()];
						if (konkaBranchCategoryList.size() > 0) {
							for (int i = 0; i < konkaBranchCategoryList.size(); i++) {
								mmtShopIds[i] = konkaBranchCategoryList.get(i).getMmt_shop_id();
							}
						}
						if (mmtShopIds.length == 0) {
							mmtShopIds = new Long[] { 0L };
						}

						MmtEntpShop mmtEntpShop = new MmtEntpShop();
						if (GenericValidator.isLong(mmt_shop_id)) {
							mmtEntpShop.setShop_id(new Long(mmt_shop_id));
						}
						mmtEntpShop.getMap().put("mmtShopIds", mmtShopIds);

						Long count = getFacade().getMmtEntpShopService()
								.getMmtEntpShopNotRepeatCount(mmtEntpShop);
						pager.init(count, 10, pager.getRequestPage());
						mmtEntpShop.getRow().setFirst(pager.getFirstRow());
						mmtEntpShop.getRow().setCount(pager.getRowCount());

						List<MmtEntpShop> konkaEntpShopList = getFacade().getMmtEntpShopService()
								.getMmtEntpShopNotRepeatPaginatedList(mmtEntpShop);
						request.setAttribute("konkaEntpShopList", konkaEntpShopList);
						if (konkaEntpShopList.size() == 0) {
							String msg = "对不起，该代理商下无经销商!";
							renderJavaScript(response, "alert('" + msg + "');history.back();");
							return null;
						}
						dynaBean.set("agent_id", agent_id.toString());
						return mapping.findForward("list");
					} else {
						this.saveError(request, "errors.long", new String[] { agent_id });
						return mapping.findForward("list");
					}
				} else {// R3网点（代理商和直供经销商）
					// R3网点搜索条件
					KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
					konkaR3Shop.getMap().put("by_shop", "true");// 【按网点】统计
					konkaR3Shop.getMap().put("konka_jxc_dept_id", ui.getDept_id());
					List<KonkaR3Shop> konkaR3ShopForSearchList = getFacade().getKonkaR3ShopService()
							.getKonkaR3ShopMatchAndAssignList(konkaR3Shop);
					request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);

					// R3网点列表
					KonkaR3Shop entity = new KonkaR3Shop();
					super.copyProperties(entity, form);

					// is_agent="0" 管辖区域的非代理商网点List
					// is_agent="1" 管辖区域的代理商List
					if (StringUtils.isBlank(is_agent)) {
						dynaBean.set("is_agent", "0");
					} else if ("0".endsWith(is_agent)) {
						dynaBean.set("is_agent", "0");
					} else {
						dynaBean.set("is_agent", "1");
						entity.getMap().put("is_agent", "true");
					}

					if (GenericValidator.isLong(r3_shop_id)) {
						entity.setId(new Long(r3_shop_id));
					}

					entity.getMap().put("konka_jxc_dept_id", ui.getDept_id());
					entity.getMap().put("r3_code_like", r3_code_like);
					entity.getMap().put("by_shop", "true");// 【按网点】统计

					Long count = getFacade().getKonkaR3ShopService().getKonkaR3ShopMatchAndAssignCount(entity);
					pager.init(count, 10, pager.getRequestPage());
					entity.getRow().setFirst(pager.getFirstRow());
					entity.getRow().setCount(pager.getRowCount());

					List<KonkaR3Shop> konkaR3ShopList = getFacade().getKonkaR3ShopService()
							.getKonkaR3ShopMatchAndAssignPaginatedList(entity);
					request.setAttribute("konkaR3ShopList", konkaR3ShopList);
				}
			}
		}
		dynaBean.set("is_entp_shop", is_entp_shop);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 
		String is_not_direct = (String) dynaBean.get("is_not_direct");// 非直供----代理商，分销情况时的参数

		// 初始化列表页面的起始时间
		Date now = new Date();
		String add_date_st = DateFormatUtils.format(now, "yyyy-MM-") + "01";// 本月第一天
		String add_date_en = DateFormatUtils.format(now, "yyyy-MM-dd");// 当前日

		if (GenericValidator.isLong(id)) {
			// 显示统计详细信息
			// 根据管理端的型号即网点的out_sys_id 和 网点的shop_id 取出jxc_pd中的型号信息

			// ==1== 取规格
			Long prop_id = Constants.PROP_ID;// 屏幕尺寸
			PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
			pdModelPropsVal.setProp_id(prop_id);
			List<String> stringPropValueList = getFacade().getPdModelPropsValService().getPdModelPropsValGroupByList(
					pdModelPropsVal);

			List<PdModelPropsVal> pdModelPropsValList = new ArrayList<PdModelPropsVal>();
			// 合计信息
			Double qcjc_count_total = 0d;
			Double qcjc_money_total = 0d;
			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;
			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;
			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;

			// 取网点id（直供，非直供）
			Long shop_id = null;
			if (StringUtils.isBlank(is_not_direct)) {
				// 经销商(直供)== R3网点统计数据
				// 取的jxc_pd中的网点shop_id--根据R3网点id查询匹配表中的mmt_shop_id
				KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
				konkaR3MmtMatch.setR3_shop_id(new Long(id));
				konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchByR3ShopId(konkaR3MmtMatch);
				if (null != konkaR3MmtMatch) {
					shop_id = konkaR3MmtMatch.getMmt_shop_id();
				} else {
					String msg = "对不起，该网点没有匹配买卖提!";
					renderJavaScript(response, "alert('" + msg + "');history.back();");
					return null;
				}
				// 取网点名称
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(new Long(id));
				konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				request.setAttribute("shop_name", konkaR3Shop.getCustomer_name());
			} else {
				// 非直供（买卖提网点统计数据）
				shop_id = new Long(id);
				// 取网点名称
				MmtEntpShop konkaEntpShop = new MmtEntpShop();
				konkaEntpShop.setShop_id(shop_id);
				konkaEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(konkaEntpShop);
				request.setAttribute("shop_name", konkaEntpShop.getShop_name());
			}

			for (String propValue : stringPropValueList) {
				// ==2== 取机型（管理端型号）
				PdModelPropsVal pdModelPropsVal2 = new PdModelPropsVal();
				pdModelPropsVal2.setProp_id(prop_id);// ------------------------
				pdModelPropsVal2.setProp_value(propValue);
				List<PdModelPropsVal> pdModelPropsValPdIdList = getFacade().getPdModelPropsValService()
						.getPdModelPropsValList(pdModelPropsVal2);// 管理端型号

				if (pdModelPropsValPdIdList.size() > 0) {

					for (int i = 0; i < pdModelPropsValPdIdList.size(); i++) {
						// ==3== 网点对应的型号
						JxcPd jxcPdId = new JxcPd();
						jxcPdId.setShop_id(shop_id);
						jxcPdId.setOut_sys_id(pdModelPropsValPdIdList.get(i).getPd_id());
						jxcPdId = getFacade().getJxcPdService().getJxcPd(jxcPdId);

						if (null != jxcPdId) {
							// ==4== 取该型号对应的统计信息
							Long jxc_pd_id = jxcPdId.getId();

							// =====统计start================================================================
							JxcPd entity = new JxcPd();
							super.copyProperties(entity, form);
							entity.setShop_id(shop_id);
							// entity.getMap().put("date_st", add_date_st);
							entity.getMap().put("shop_id", shop_id);
							entity.getMap().put("rcch_date_st", add_date_st);
							entity.getMap().put("rcch_date_en", add_date_en);
							entity.getMap().put("jxc_pd_id", jxc_pd_id);// 型号

							List<JxcPd> list = getFacade().getJxcPdService().getShopSellStatisticsList(entity);
							List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
							for (JxcPd jxcPd : list) {
								// 单价
								Double qcjc_price = 0d;
								Double rcjh_price = 0d;
								Double rcch_price = 0d;

								//
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
								if (qcjc_count.doubleValue() != 0 && qcjc_money.doubleValue() != 0) {
									qcjc_price = qcjc_money.divide(qcjc_count, 2, BigDecimal.ROUND_HALF_UP)
											.doubleValue();// 单价
								}
								qcjc_pd.getMap().put("qcjc_price", qcjc_price);
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
								if (rcjh_sum_money.doubleValue() != 0 && rcjh_count.doubleValue() != 0) {
									rcjh_price = rcjh_sum_money.divide(rcjh_count, 2, BigDecimal.ROUND_HALF_UP)
											.doubleValue();// 单价
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

								if (rcch_sum_money.doubleValue() != 0 && rcch_count.doubleValue() != 0) {
									rcch_price = rcch_sum_money.divide(rcch_count, 2, BigDecimal.ROUND_HALF_UP)
											.doubleValue();// 单价
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

								// 合计期初结存
								qcjc_count_total += qcjc_count.doubleValue();
								qcjc_money_total += qcjc_money.doubleValue();

								// 合计日常进货
								rcjh_count_total += rcjh_count.doubleValue();
								rcjh_money_total += rcjh_sum_money.doubleValue();

								// 合计日常出货
								rcch_count_total += rcch_count.doubleValue();
								rcch_money_total += rcch_sum_money.doubleValue();

								// 合计期末结存
								qmjc_count_total += qmjc_count;
								qmjc_money_total += qmjc_money;

								jxcPd.getMap().put("rcjh_price", rcjh_price);
								jxcPd.getMap().put("rcch_price", rcch_price);
								jxcPdList.add(jxcPd);
							}
							// ========统计end=====================================================================================
							pdModelPropsValPdIdList.get(i).setJxcPdList(jxcPdList);// 每个型号对应的统计数据
						} else {
							// 数据全部设为0--页面处理
						}
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

			request.setAttribute("id", id);
			request.setAttribute("is_not_direct", is_not_direct);

			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super.renderExcelWithEncoding(request, response, "GBK");
		return null;
	}

	/** 打印 */
	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 
		String is_not_direct = (String) dynaBean.get("is_not_direct");// 非直供----代理商，分销情况时的参数

		// 初始化列表页面的起始时间
		Date now = new Date();
		String add_date_st = DateFormatUtils.format(now, "yyyy-MM-") + "01";// 本月第一天
		String add_date_en = DateFormatUtils.format(now, "yyyy-MM-dd");// 当前日

		if (GenericValidator.isLong(id)) {
			// 显示统计详细信息
			// 根据管理端的型号即网点的out_sys_id 和 网点的shop_id 取出jxc_pd中的型号信息

			// ==1== 取规格
			Long prop_id = 10125L;// 屏幕尺寸
			PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
			pdModelPropsVal.setProp_id(prop_id);
			List<String> stringPropValueList = getFacade().getPdModelPropsValService().getPdModelPropsValGroupByList(
					pdModelPropsVal);

			List<PdModelPropsVal> pdModelPropsValList = new ArrayList<PdModelPropsVal>();
			// 合计信息
			Double qcjc_count_total = 0d;
			Double qcjc_money_total = 0d;
			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;
			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;
			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;

			// 取网点id（直供，非直供）
			Long shop_id = null;
			if (StringUtils.isBlank(is_not_direct)) {
				// 经销商(直供)== R3网点统计数据
				// 取的jxc_pd中的网点shop_id--根据R3网点id查询匹配表中的mmt_shop_id
				KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
				konkaR3MmtMatch.setR3_shop_id(new Long(id));
				konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchByR3ShopId(konkaR3MmtMatch);
				if (null != konkaR3MmtMatch) {
					shop_id = konkaR3MmtMatch.getMmt_shop_id();
				} else {
					String msg = "对不起，该网点没有匹配买卖提!";
					renderJavaScript(response, "alert('" + msg + "');history.back();");
					return null;
				}
				// 取网点名称
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(new Long(id));
				konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				request.setAttribute("shop_name", konkaR3Shop.getCustomer_name());
			} else {
				// 非直供（买卖提网点统计数据）
				shop_id = new Long(id);
				// 取网点名称
				MmtEntpShop konkaEntpShop = new MmtEntpShop();
				konkaEntpShop.setShop_id(shop_id);
				konkaEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(konkaEntpShop);
				request.setAttribute("shop_name", konkaEntpShop.getShop_name());
			}

			for (String propValue : stringPropValueList) {
				// ==2== 取机型（管理端型号）
				PdModelPropsVal pdModelPropsVal2 = new PdModelPropsVal();
				pdModelPropsVal2.setProp_id(prop_id);// ------------------------
				pdModelPropsVal2.setProp_value(propValue);
				List<PdModelPropsVal> pdModelPropsValPdIdList = getFacade().getPdModelPropsValService()
						.getPdModelPropsValList(pdModelPropsVal2);// 管理端型号

				if (pdModelPropsValPdIdList.size() > 0) {

					for (int i = 0; i < pdModelPropsValPdIdList.size(); i++) {
						// ==3== 网点对应的型号
						JxcPd jxcPdId = new JxcPd();
						jxcPdId.setShop_id(shop_id);
						jxcPdId.setOut_sys_id(pdModelPropsValPdIdList.get(i).getPd_id());
						jxcPdId = getFacade().getJxcPdService().getJxcPd(jxcPdId);

						if (null != jxcPdId) {
							// ==4== 取该型号对应的统计信息
							Long jxc_pd_id = jxcPdId.getId();

							// =====统计start================================================================
							JxcPd entity = new JxcPd();
							super.copyProperties(entity, form);
							entity.setShop_id(shop_id);
							// entity.getMap().put("date_st", add_date_st);
							entity.getMap().put("shop_id", shop_id);
							entity.getMap().put("rcch_date_st", add_date_st);
							entity.getMap().put("rcch_date_en", add_date_en);
							entity.getMap().put("jxc_pd_id", jxc_pd_id);// 型号

							List<JxcPd> list = getFacade().getJxcPdService().getShopSellStatisticsList(entity);
							List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
							for (JxcPd jxcPd : list) {
								// 单价
								Double qcjc_price = 0d;
								Double rcjh_price = 0d;
								Double rcch_price = 0d;

								//
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
								if (qcjc_count.doubleValue() != 0 && qcjc_money.doubleValue() != 0) {
									qcjc_price = qcjc_money.divide(qcjc_count, 2, BigDecimal.ROUND_HALF_UP)
											.doubleValue();// 单价
								}
								qcjc_pd.getMap().put("qcjc_price", qcjc_price);
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
								if (rcjh_sum_money.doubleValue() != 0 && rcjh_count.doubleValue() != 0) {
									rcjh_price = rcjh_sum_money.divide(rcjh_count, 2, BigDecimal.ROUND_HALF_UP)
											.doubleValue();// 单价
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

								if (rcch_sum_money.doubleValue() != 0 && rcch_count.doubleValue() != 0) {
									rcch_price = rcch_sum_money.divide(rcch_count, 2, BigDecimal.ROUND_HALF_UP)
											.doubleValue();// 单价
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

								// 合计期初结存
								qcjc_count_total += qcjc_count.doubleValue();
								qcjc_money_total += qcjc_money.doubleValue();

								// 合计日常进货
								rcjh_count_total += rcjh_count.doubleValue();
								rcjh_money_total += rcjh_sum_money.doubleValue();

								// 合计日常出货
								rcch_count_total += rcch_count.doubleValue();
								rcch_money_total += rcch_sum_money.doubleValue();

								// 合计期末结存
								qmjc_count_total += qmjc_count;
								qmjc_money_total += qmjc_money;

								jxcPd.getMap().put("rcjh_price", rcjh_price);
								jxcPd.getMap().put("rcch_price", rcch_price);
								jxcPdList.add(jxcPd);
							}
							// ========统计end=====================================================================================
							pdModelPropsValPdIdList.get(i).setJxcPdList(jxcPdList);// 每个型号对应的统计数据
						} else {
							// 数据全部设为0--页面处理
						}
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

		}
		return new ActionForward("/../jxc/KonkaJxcShopSellStatistics/_print_statistics_by_shop_list.jsp");
	}
}
