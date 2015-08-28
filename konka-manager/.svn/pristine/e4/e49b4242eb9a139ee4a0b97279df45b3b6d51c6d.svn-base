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

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author du,zhiming
 * @version 2011-11-21
 */
public class KonkaJxcShopStatisticsByAreaAction extends BaseJxcAction {
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
		PeProdUser ui = super.getSessionUserInfo(request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		if (ui == null) {// 用户为空
			return null;
		}
		KonkaDept konkaDept = super.getKonkaDeptById(ui.getDept_id());
		String area_names = konkaDept.getM_areas_names();
		String area_codes = konkaDept.getM_areas_ids();
		List<String[]> area_list = new ArrayList<String[]>();
		if (StringUtils.isNotBlank(area_codes) && area_codes.length() > 0) {// 根据部门获取区域列表
			String[] area_code = area_codes.split(",");
			String[] area_name = area_names.split(",");
			for (int i = 0; i < area_code.length; i++) {
				String[] area_str = { area_code[i], area_name[i] };
				area_list.add(area_str);
			}

		}
		request.setAttribute("area_list", area_list);
		String area_code_search = (String) dynaBean.get("area_code_search");// 区域代码

		String r3_code_like = (String) dynaBean.get("r3_code_like");// r3编码
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String search_flag = (String) dynaBean.get("search_flag");
		request.setAttribute("search_flag", search_flag);
		String is_agent = (String) dynaBean.get("is_agent");
		String agent_id = (String) dynaBean.get("agent_id");// 代理商r3Id

		// 初始化列表页面的起始时间
		Date now = new Date();
		if (StringUtils.isBlank(start_date)) {
			start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(end_date)) {
			end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
		dynaBean.set("area_code_search", area_code_search);
		dynaBean.set("is_agent", is_agent);
		dynaBean.set("agent_id", agent_id);

		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());
		if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {// 管理端角色
			if (StringUtils.isNotBlank(agent_id) && StringUtils.equals("1", is_agent)) {// 获取非直营网点 （由买买提开拓而来）
				if (GenericValidator.isLong(agent_id)) {// 是代理商，取下级经销商列表，根据代理商R3id 查询
					// KonkaBranchCategory中category_id=20的经销商的mmt_shop_id
					KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
					konkaBranchCategory.setCategory_id(20L);
					konkaBranchCategory.setKonka_r3_id(new Long(agent_id));
					List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
							.getKonkaBranchCategoryList(konkaBranchCategory);

					// 根据经销商的mmt_shop_id取网点的信息
					Long[] mmtShopIds = new Long[konkaBranchCategoryList.size()];
					if (konkaBranchCategoryList.size() > 0) {
						for (int i = 0; i < konkaBranchCategoryList.size(); i++) {
							if (konkaBranchCategoryList.get(i).getMmt_shop_id() != null) {// 开拓经销商
								mmtShopIds[i] = konkaBranchCategoryList.get(i).getMmt_shop_id();
							}

						}
					}
					if (mmtShopIds.length == 0) {
						mmtShopIds = new Long[] { 0L };
					}
					MmtEntpShop mmtEntpShop = new MmtEntpShop();
					mmtEntpShop.getMap().put("mmtShopIds", mmtShopIds);
					mmtEntpShop.getMap().put("p_index", area_code_search);
					List<MmtEntpShop> konkaEntpShopList_no_page = getFacade().getMmtEntpShopService()
							.getMmtEntpShopList(mmtEntpShop);
					request.setAttribute("konkaEntpShopList_no_page", konkaEntpShopList_no_page);
					Long count = getFacade().getMmtEntpShopService().getMmtEntpShopNotRepeatCount(mmtEntpShop);// getKonkaEntpShopNotRepeatCount
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
					return new ActionForward("/KonkaJxcShopStatisticsByArea/list_fzgShop.jsp");
				} else {
					this.saveError(request, "errors.long", new String[] { agent_id });
					return mapping.findForward("list");
				}

			} else {// 代理商、经销商（直供）
				List<KonkaR3Shop> konkaR3ShopList = new ArrayList<KonkaR3Shop>();

				// 搜索条件
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.getMap().put("konka_jxc_dept_id", ui.getDept_id());
				List<KonkaR3Shop> konkaR3ShopForSearchList = getFacade().getKonkaR3ShopService()
						.getKonkaR3ShopMatchAndAssignList(konkaR3Shop);
				request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);
				if (StringUtils.isNotBlank(search_flag)) {
					KonkaR3Shop entity = new KonkaR3Shop();
					super.copyProperties(entity, form);
					entity.getMap().put("by_area", "true");// 按区域统计
					entity.getMap().put("konka_jxc_dept_id", ui.getDept_id());
					if (GenericValidator.isLong(area_code_search)) {
						entity.getMap().put("area_code_search", area_code_search);
					}
					if (StringUtils.isNotBlank(r3_code_like)) {
						entity.getMap().put("r3_code_like", r3_code_like);
					}
					if (StringUtils.isNotBlank(start_date)) {
						entity.getMap().put("start_date", start_date);
					}
					if (StringUtils.isNotBlank(end_date)) {
						entity.getMap().put("end_date", end_date);
					}

					if (StringUtils.isNotBlank(is_agent) && StringUtils.equals("1", is_agent)) {// 是否代理商
						entity.getMap().put("is_agent", is_agent);
						dynaBean.set("is_agent", is_agent);
					}
					Long count = getFacade().getKonkaR3ShopService().getKonkaR3ShopMatchAndAssignCount(entity);
					pager.init(count, 10, pager.getRequestPage());
					entity.getRow().setFirst(pager.getFirstRow());
					entity.getRow().setCount(pager.getRowCount());

					konkaR3ShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopMatchAndAssignPaginatedList(
							entity);
				}

				request.setAttribute("konkaR3ShopList", konkaR3ShopList);
				return mapping.findForward("list");
			}

		}
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// KonkaR3网点的id 或 mmt网点 shop_id
		String start_date = "";// (String) dynaBean.get("start_date");
		String end_date = ""; // (String) dynaBean.get("end_date");
		String is_agent = (String) dynaBean.get("is_agent");
		// 日期为当月第一天 到当前日期
		Date now = new Date();
		start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
		String shop_name = (String) dynaBean.get("shop_name");
		request.setAttribute("id", id);
		request.setAttribute("shop_name", shop_name);
		request.setAttribute("end_date", end_date);
		request.setAttribute("start_date", start_date);
		if (GenericValidator.isLong(id)) {
			// 1、取规格 从数据表konka_pd_model_props_val 读初始化 Long prop_id = Constants.PROP_ID;
			Long prop_id = Constants.PROP_ID;
			PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
			pdModelPropsVal.setProp_id(prop_id);
			List<String> stringPropValueList = getFacade().getPdModelPropsValService().getPdModelPropsValGroupByList(
					pdModelPropsVal);
			List<PdModelPropsVal> pdModelPropsValList = new ArrayList<PdModelPropsVal>();

			/************************** 合计信息字段 start *************/
			Double qcjc_count_total = 0d;
			Double qcjc_money_total = 0d;

			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;

			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;

			Double pc_count_total = 0d;
			Double pc_money_total = 0d;

			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;
			/************************** 合计信息字段 end *************/
			for (String propValue : stringPropValueList) {
				// 2 、取机型
				PdModelPropsVal pdModelPropsVal2 = new PdModelPropsVal();
				pdModelPropsVal2.setProp_id(prop_id);// ------------------------
				pdModelPropsVal2.setProp_value(propValue);
				List<PdModelPropsVal> pdModelPropsValPdIdList = getFacade().getPdModelPropsValService()
						.getPdModelPropsValList(pdModelPropsVal2);// 管理端型号
				Long shop_id = new Long(id);
				if (StringUtils.isNotBlank(is_agent)) {// R3网点 转换为MMt shop_id 对应Jxc客户端shop_id
					// 取的jxc_pd中的网点shop_id--根据R3网点id查询匹配表中的mmt_shop_id
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					konkaR3MmtMatch.setR3_shop_id(new Long(id));
					konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchByR3ShopId(
							konkaR3MmtMatch);
					if (null != konkaR3MmtMatch) {
						shop_id = konkaR3MmtMatch.getMmt_shop_id();
					}
				}

				if (pdModelPropsValPdIdList.size() > 0) {
					for (int i = 0; i < pdModelPropsValPdIdList.size(); i++) {
						// 3、 网点对应的型号
						JxcPd jxcPdId = new JxcPd();
						jxcPdId.setShop_id(shop_id);
						jxcPdId.setOut_sys_id(pdModelPropsValPdIdList.get(i).getPd_id());// 管理端型号统一转化为网点端型号
						jxcPdId = getFacade().getJxcPdService().getJxcPd(jxcPdId);
						if (null != jxcPdId) {
							// 4、 取该型号对应的统计信息
							Long jxc_pd_id = jxcPdId.getId();

							/**************************************** 统计 start ********************************************************/
							JxcPd entity = new JxcPd();
							super.copyProperties(entity, form);

							entity.getMap().put("shop_id", shop_id);
							entity.getMap().put("rcch_date_st", start_date);
							entity.getMap().put("rcch_date_en", end_date);
							entity.getMap().put("jxc_pd_id", jxc_pd_id);// 型号

							List<JxcPd> list = getFacade().getJxcPdService().getShopSellStatisticsList(entity);
							List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
							for (JxcPd jxcPd : list) {
								// 单价
								Double qcjc_price = 0d;
								Double rcjh_price = 0d;
								Double rcch_price = 0d;
								Long pd_id = jxcPd.getId();
								// 期初结存
								JxcPd qcjc_pd = new JxcPd();
								qcjc_pd.getMap().put("pd_id", pd_id);
								qcjc_pd.getMap().put("qcjc_date", start_date);
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
							BasePdType basePdType = new BasePdType();
							basePdType.setDel_mark(new Short((short) 0));
							basePdType.setIs_model(new Short((short) 1));
							List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService()
									.getBasePdTypeList(basePdType);
							request.setAttribute("basePdTypeList", basePdTypeList);
							/**************************************** 统计end= ********************************************************/
							pdModelPropsValPdIdList.get(i).setJxcPdList(jxcPdList);// 每个型号对应的统计数据
						} else {
							// 数据全部设为0--页面处理
						}
					}
				}
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_value(propValue);// 规格
				pdModelPropsVal3.setPdModelPropsValList(pdModelPropsValPdIdList);// 型号
				pdModelPropsValList.add(pdModelPropsVal3);

			}

			request.setAttribute("pdModelPropsValList", pdModelPropsValList);
			/************************** 合计信息字段 start *************/
			dynaBean.set("qcjc_count_total", qcjc_count_total);
			dynaBean.set("qcjc_money_total", qcjc_money_total);
			dynaBean.set("rcjh_count_total", rcjh_count_total);
			dynaBean.set("rcjh_money_total", rcjh_money_total);
			dynaBean.set("rcch_count_total", rcch_count_total);
			dynaBean.set("rcch_money_total", rcch_money_total);
			dynaBean.set("pc_count_total", pc_count_total);
			dynaBean.set("pc_money_total", pc_money_total);
			dynaBean.set("qmjc_count_total", qmjc_count_total);
			dynaBean.set("qmjc_money_total", qmjc_money_total);
			/************************** 合计信息字段end *************/
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

	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// KonkaR3网点的id 或 mmt网点 shop_id
		String start_date = "";// (String) dynaBean.get("start_date");
		String end_date = ""; // (String) dynaBean.get("end_date");
		String is_agent = (String) dynaBean.get("is_agent");
		// 日期为当月第一天 到当前日期
		Date now = new Date();
		start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
		String shop_name = (String) dynaBean.get("shop_name");
		request.setAttribute("shop_name", shop_name);
		request.setAttribute("end_date", end_date);
		request.setAttribute("start_date", start_date);
		if (GenericValidator.isLong(id)) {
			// 1、取规格 从数据表konka_pd_model_props_val 读初始化 Long prop_id = Constants.PROP_ID;
			Long prop_id = Constants.PROP_ID;
			PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
			pdModelPropsVal.setProp_id(prop_id);
			List<String> stringPropValueList = getFacade().getPdModelPropsValService().getPdModelPropsValGroupByList(
					pdModelPropsVal);
			List<PdModelPropsVal> pdModelPropsValList = new ArrayList<PdModelPropsVal>();

			/************************** 合计信息字段 start *************/
			Double qcjc_count_total = 0d;
			Double qcjc_money_total = 0d;

			Double rcjh_count_total = 0d;
			Double rcjh_money_total = 0d;

			Double rcch_count_total = 0d;
			Double rcch_money_total = 0d;

			Double pc_count_total = 0d;
			Double pc_money_total = 0d;

			Double qmjc_count_total = 0d;
			Double qmjc_money_total = 0d;
			/************************** 合计信息字段 end *************/
			for (String propValue : stringPropValueList) {
				// 2 、取机型
				PdModelPropsVal pdModelPropsVal2 = new PdModelPropsVal();
				pdModelPropsVal2.setProp_id(prop_id);// ------------------------
				pdModelPropsVal2.setProp_value(propValue);
				List<PdModelPropsVal> pdModelPropsValPdIdList = getFacade().getPdModelPropsValService()
						.getPdModelPropsValList(pdModelPropsVal2);// 管理端型号
				Long shop_id = new Long(id);
				if (StringUtils.isNotBlank(is_agent)) {// R3网点 转换为MMt shop_id 对应Jxc客户端shop_id
					// 取的jxc_pd中的网点shop_id--根据R3网点id查询匹配表中的mmt_shop_id
					KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
					konkaR3MmtMatch.setR3_shop_id(new Long(id));
					konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchByR3ShopId(
							konkaR3MmtMatch);
					if (null != konkaR3MmtMatch) {
						shop_id = konkaR3MmtMatch.getMmt_shop_id();
					}
				}

				if (pdModelPropsValPdIdList.size() > 0) {
					for (int i = 0; i < pdModelPropsValPdIdList.size(); i++) {
						// 3、 网点对应的型号
						JxcPd jxcPdId = new JxcPd();
						jxcPdId.setShop_id(shop_id);
						jxcPdId.setOut_sys_id(pdModelPropsValPdIdList.get(i).getPd_id());// 管理端型号统一转化为网点端型号
						jxcPdId = getFacade().getJxcPdService().getJxcPd(jxcPdId);
						if (null != jxcPdId) {
							// 4、 取该型号对应的统计信息
							Long jxc_pd_id = jxcPdId.getId();

							/**************************************** 统计 start ********************************************************/
							JxcPd entity = new JxcPd();
							super.copyProperties(entity, form);

							entity.getMap().put("shop_id", shop_id);
							entity.getMap().put("rcch_date_st", start_date);
							entity.getMap().put("rcch_date_en", end_date);
							entity.getMap().put("jxc_pd_id", jxc_pd_id);// 型号

							List<JxcPd> list = getFacade().getJxcPdService().getShopSellStatisticsList(entity);
							List<JxcPd> jxcPdList = new ArrayList<JxcPd>();
							for (JxcPd jxcPd : list) {
								// 单价
								Double qcjc_price = 0d;
								Double rcjh_price = 0d;
								Double rcch_price = 0d;
								Long pd_id = jxcPd.getId();
								// 期初结存
								JxcPd qcjc_pd = new JxcPd();
								qcjc_pd.getMap().put("pd_id", pd_id);
								qcjc_pd.getMap().put("qcjc_date", start_date);
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
							BasePdType basePdType = new BasePdType();
							basePdType.setDel_mark(new Short((short) 0));
							basePdType.setIs_model(new Short((short) 1));
							List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService()
									.getBasePdTypeList(basePdType);
							request.setAttribute("basePdTypeList", basePdTypeList);
							/**************************************** 统计end= ********************************************************/
							pdModelPropsValPdIdList.get(i).setJxcPdList(jxcPdList);// 每个型号对应的统计数据
						} else {
							// 数据全部设为0--页面处理
						}
					}
				}
				PdModelPropsVal pdModelPropsVal3 = new PdModelPropsVal();
				pdModelPropsVal3.setProp_value(propValue);// 规格
				pdModelPropsVal3.setPdModelPropsValList(pdModelPropsValPdIdList);// 型号
				pdModelPropsValList.add(pdModelPropsVal3);

			}

			request.setAttribute("pdModelPropsValList", pdModelPropsValList);
			/************************** 合计信息字段 start *************/
			dynaBean.set("qcjc_count_total", qcjc_count_total);
			dynaBean.set("qcjc_money_total", qcjc_money_total);
			dynaBean.set("rcjh_count_total", rcjh_count_total);
			dynaBean.set("rcjh_money_total", rcjh_money_total);
			dynaBean.set("rcch_count_total", rcch_count_total);
			dynaBean.set("rcch_money_total", rcch_money_total);
			dynaBean.set("pc_count_total", pc_count_total);
			dynaBean.set("pc_money_total", pc_money_total);
			dynaBean.set("qmjc_count_total", qmjc_count_total);
			dynaBean.set("qmjc_money_total", qmjc_money_total);
			/************************** 合计信息字段end *************/
		}
		return new ActionForward("/../jxc/KonkaJxcShopStatisticsByArea/_print_statistics_by_area_list.jsp");

	}

}
