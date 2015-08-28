package com.ebiz.mmt.web.struts.jxc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaJxcStoreQckc;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;

public class KonkaJxcStockAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		// add_date_st = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		String add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
		dynaBean.set("add_date_st", add_date_st);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
		dynaBean.set("add_date_en", add_date_en);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 获取用户角色
		PeRoleInfo role = new PeRoleInfo();
		role = super.getPeRoleInfoByUserId(peProdUser.getId());
		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();

		if (null != role) {
			if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
					&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
				basePdClassList = super.getBaseAllPdClazzList();
				request.setAttribute("role_id_syb", "syb");
			} else {// 分公司
				basePdClassList = super.getBasePdClazzListByDeptId(peProdUser.getDept_id());
			}

		}
		request.setAttribute("basePdClassList", basePdClassList);
		return mapping.findForward("list");
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
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");
		String pd_type = (String) dynaBean.get("pd_type_id");
		String name_like = (String) dynaBean.get("name_like");
		String show_by_store_id = (String) dynaBean.get("show_by_store_id");
		String is_show_0_store = (String) dynaBean.get("is_show_0_store");

		boolean show_by_store = false;
		if (StringUtils.isNotBlank(show_by_store_id)) {
			show_by_store = true;
		}

		request.setAttribute("not_validate_record", "true");

		if (StringUtils.isBlank(add_date_st)) {
			// add_date_st = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			add_date_st = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd"); // 取当月第一天
			dynaBean.set("add_date_st", add_date_st);
		}
		if (StringUtils.isBlank(add_date_en)) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			add_date_en = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd");
			dynaBean.set("add_date_en", add_date_en);
		}

		Date date_st = DateUtils.parseDate(add_date_st, new String[] { "yyyy-MM-dd" });

		Double qcjc_count_total = 0d;
		Double qcjc_money_total = 0d;
		Double rcjh_count_total = 0d;
		Double rcjh_money_total = 0d;
		Double rcch_count_total = 0d;
		Double rcch_money_total = 0d;
		Double pc_count_total = 0d;
		Double pc_money_total = 0d;
		// Double thzp_count_total = 0d;
		// Double thzp_money_total = 0d;
		Double thccp_count_total = 0d;
		Double thccp_money_total = 0d;
		Double qmjc_count_total = 0d;
		Double qmjc_money_total = 0d;

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		Long dept_id = user.getDept_id();// 部门id

		List<KonkaJxcStoreState> list = new ArrayList<KonkaJxcStoreState>();
		KonkaJxcStoreState entity = new KonkaJxcStoreState();
		super.copyProperties(entity, form);

		entity.getMap().put("dept_id", dept_id);
		entity.getMap().put("pd_type", pd_type);
		entity.getMap().put("name_like", name_like);
		entity.getMap().put("add_date_st", add_date_st);
		entity.getMap().put("add_date_en", add_date_en);

		if (show_by_store) {
			list = getFacade().getKonkaJxcStoreStateService().selectStockRcjhAndRcfhListForStore(entity);
		} else {
			list = getFacade().getKonkaJxcStoreStateService().selectStockRcjhAndRcfhList(entity);
		}

		List<KonkaJxcStoreState> konkaJxcStoreStateList = new ArrayList<KonkaJxcStoreState>();
		for (KonkaJxcStoreState temp : list) {
			BigDecimal qcjc_count = new BigDecimal(0);
			BigDecimal qcjc_money = new BigDecimal(0);
			BigDecimal qcjc_price = new BigDecimal(0);// 单价
			BigDecimal rcjh_price = new BigDecimal(0);
			BigDecimal rcch_price = new BigDecimal(0);
			BigDecimal pc_price = new BigDecimal(0);
			BigDecimal qmjc_price = new BigDecimal(0);

			Long pd_id = temp.getPd_id();
			Long store_id = temp.getStore_id();

			// 期初结存
			KonkaJxcStoreState qcjc_pd = new KonkaJxcStoreState();
			KonkaJxcStoreQckc qckc_pd = new KonkaJxcStoreQckc();// 期初库存记录

			qckc_pd.setPd_id(pd_id);// 型号
			qckc_pd.setDept_id(dept_id);// 部门
			if (show_by_store) {
				if (null != store_id) {// 仓库
					qckc_pd.setStore_id(store_id);
				} else {
					qckc_pd.getMap().put("store_id_is_null", "true");
				}
			}
			qckc_pd.setQc_date(date_st);
			List<KonkaJxcStoreQckc> qckc_pd_list = getFacade().getKonkaJxcStoreQckcService().getKonkaJxcStoreQckcList(
					qckc_pd);// 从期初库存记录表里面查询

			if (null != qckc_pd_list && qckc_pd_list.size() > 0) {
				for (KonkaJxcStoreQckc kjs_qckc : qckc_pd_list) {
					BigDecimal qcjc_count_sub = new BigDecimal(kjs_qckc.getNum());
					BigDecimal qcjc_money_sub = kjs_qckc.getPrice_sum();
					qcjc_count = qcjc_count.add(qcjc_count_sub);
					qcjc_money = qcjc_money.add(qcjc_money_sub);
				}
			} else if (!date_st.after(new Date())) {
				// 没有期初的库存记录，并且搜索的日期小于等于当前的时间，才插入一条数据
				qcjc_pd.getMap().put("pd_id", pd_id);
				qcjc_pd.getMap().put("dept_id", dept_id);
				qcjc_pd.getMap().put("qcjc_date", add_date_st);
				if (show_by_store) {
					if (null != store_id) {// 仓库
						qcjc_pd.getMap().put("store_id", store_id);
					} else {
						qcjc_pd.getMap().put("store_id_is_null", "true");
					}
				}
				qcjc_pd = super.getFacade().getKonkaJxcStoreStateService().selectQcjcForStockState(qcjc_pd);
				qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
				qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
				if (qcjc_count.longValue() != 0) {
					qcjc_price = qcjc_money.divide(qcjc_count, 2, BigDecimal.ROUND_HALF_EVEN);// 期初单价
				}

				// 添加期初库存记录
				KonkaJxcStoreQckc store_qckc = new KonkaJxcStoreQckc();// 期初库存记录
				store_qckc.setPd_id(pd_id);// 型号
				store_qckc.setDept_id(dept_id);// 部门
				if (null != temp.getStore_id()) {// 仓库
					store_qckc.setStore_id(temp.getStore_id());
				}
				store_qckc.setQc_date(new SimpleDateFormat("yyyy-MM-dd").parse(add_date_st));
				store_qckc.setNum(qcjc_count.longValue());
				store_qckc.setPrice(qcjc_price);
				store_qckc.setPrice_sum(qcjc_money);
				super.getFacade().getKonkaJxcStoreQckcService().createKonkaJxcStoreQckc(store_qckc);
			}
			temp.getMap().put("qcjc_count", qcjc_count);
			temp.getMap().put("qcjc_money", qcjc_money);
			temp.getMap().put("qcjc_price", qcjc_price);

			// 日常进货
			BigDecimal rcjh_count = (BigDecimal) temp.getMap().get("jh_count");
			BigDecimal rcjh_sum_money = (BigDecimal) temp.getMap().get("jh_sum_money");
			if (rcjh_count == null) {
				rcjh_count = new BigDecimal("0");
			}
			if (rcjh_sum_money == null) {
				rcjh_sum_money = new BigDecimal("0");
			}

			// 退货正品
			BigDecimal thzp_count = (BigDecimal) temp.getMap().get("thzp_count");
			BigDecimal thzp_sum_money = (BigDecimal) temp.getMap().get("thzp_sum_money");
			if (thzp_count == null) {
				thzp_count = new BigDecimal("0");
			}
			if (thzp_sum_money == null) {
				thzp_sum_money = new BigDecimal("0");
			}

			if (rcjh_count.longValue() != 0) {
				rcjh_price = rcjh_sum_money.divide(rcjh_count, 2, BigDecimal.ROUND_HALF_EVEN);// 进货单价
			}

			// 日常出货
			BigDecimal rcch_count = (BigDecimal) temp.getMap().get("ch_count");
			BigDecimal rcch_sum_money = (BigDecimal) temp.getMap().get("ch_sum_money");
			if (rcch_count == null) {
				rcch_count = new BigDecimal("0");
			}
			if (rcch_sum_money == null) {
				rcch_sum_money = new BigDecimal("0");
			}
			if (rcch_count.longValue() != 0) {
				rcch_price = rcch_sum_money.divide(rcch_count, 2, BigDecimal.ROUND_HALF_EVEN);// 发货单价
			}

			// 退货残次品
			BigDecimal thccp_count = (BigDecimal) temp.getMap().get("thzp_count");
			BigDecimal thccp_sum_money = (BigDecimal) temp.getMap().get("thzp_sum_money");
			if (thccp_count == null) {
				thccp_count = new BigDecimal("0");
			}
			if (thccp_sum_money == null) {
				thccp_sum_money = new BigDecimal("0");
			}

			// 盘存、盘亏
			BigDecimal pc_count = (BigDecimal) temp.getMap().get("pc_count");
			BigDecimal pc_sum_money = (BigDecimal) temp.getMap().get("pc_sum_money");
			if (pc_count == null) {
				pc_count = new BigDecimal("0");
			}
			if (pc_sum_money == null) {
				pc_sum_money = new BigDecimal("0");
			}
			if (pc_count.longValue() != 0) {
				pc_price = pc_sum_money.divide(pc_count, 2, BigDecimal.ROUND_HALF_EVEN);// 盘存单价
			}

			// 期末结存
			BigDecimal qmjc_count = qcjc_count.add(rcjh_count).add(pc_count).add(thzp_count).subtract(rcch_count);
			BigDecimal qmjc_money = qcjc_money.add(rcjh_sum_money).add(pc_sum_money).add(thzp_sum_money).subtract(
					rcch_sum_money);
			temp.getMap().put("qmjc_count", qmjc_count);
			temp.getMap().put("qmjc_money", qmjc_money);
			if (qmjc_count.longValue() != 0) {
				qmjc_price = qmjc_money.divide(qmjc_count, 2, BigDecimal.ROUND_HALF_EVEN);// 期末单价
			}

			temp.getMap().put("qcjc_price", qcjc_price);
			temp.getMap().put("rcjh_price", rcjh_price);
			temp.getMap().put("rcch_price", rcch_price);
			temp.getMap().put("pc_price", pc_price);
			temp.getMap().put("qmjc_price", qmjc_price);

			// 合计期初结存
			qcjc_count_total += qcjc_count.doubleValue();
			qcjc_money_total += qcjc_money.doubleValue();

			// 合计日常进货
			rcjh_count_total += rcjh_count.doubleValue();
			rcjh_money_total += rcjh_sum_money.doubleValue();

			// 合计日常出货
			rcch_count_total += rcch_count.doubleValue();
			rcch_money_total += rcch_sum_money.doubleValue();

			// 合计盘存盘亏
			pc_count_total += pc_count.doubleValue();
			pc_money_total += pc_sum_money.doubleValue();

			// 合计退货正品
			rcjh_count_total += thzp_count.doubleValue();
			rcjh_money_total += thzp_sum_money.doubleValue();

			// 合计退货残次品
			thccp_count_total += thccp_count.doubleValue();
			thccp_money_total += thccp_sum_money.doubleValue();

			// 合计期末结存
			qmjc_count_total += qmjc_count.doubleValue();
			qmjc_money_total += qmjc_money.doubleValue();

			if (!StringUtils.isNotBlank(is_show_0_store)) {// 显示零库存产品
				if (qmjc_count.doubleValue() != 0) {
					konkaJxcStoreStateList.add(temp);
				}
			} else {
				konkaJxcStoreStateList.add(temp);
			}
		}

		dynaBean.set("qcjc_count_total", qcjc_count_total);
		dynaBean.set("qcjc_money_total", qcjc_money_total);
		dynaBean.set("rcjh_count_total", rcjh_count_total);
		dynaBean.set("rcjh_money_total", rcjh_money_total);
		dynaBean.set("rcch_count_total", rcch_count_total);
		dynaBean.set("rcch_money_total", rcch_money_total);
		dynaBean.set("pc_count_total", pc_count_total);
		dynaBean.set("pc_money_total", pc_money_total);
		// dynaBean.set("thzp_count_total", thzp_count_total);
		// dynaBean.set("thzp_money_total", thzp_money_total);
		dynaBean.set("pc_count_total", pc_count_total);
		dynaBean.set("pc_money_total", pc_money_total);
		dynaBean.set("thccp_count_total", thccp_count_total);
		dynaBean.set("thccp_money_total", thccp_money_total);
		dynaBean.set("qmjc_count_total", qmjc_count_total);
		dynaBean.set("qmjc_money_total", qmjc_money_total);

		dynaBean.set("dept_id", dept_id);

		request.setAttribute("entityList", konkaJxcStoreStateList);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 获取用户角色
		PeRoleInfo role = new PeRoleInfo();
		role = super.getPeRoleInfoByUserId(peProdUser.getId());
		// 产品类型
		List<BasePdClazz> basePdClassList = new ArrayList<BasePdClazz>();

		if (null != role) {
			if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
					&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
				basePdClassList = super.getBaseAllPdClazzList();
				request.setAttribute("role_id_syb", "syb");
			} else {// 分公司
				basePdClassList = super.getBasePdClazzListByDeptId(peProdUser.getDept_id());
			}
		}
		request.setAttribute("basePdClassList", basePdClassList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		String pd_type_id = (String) dynaBean.get("pd_type_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String add_date_st = (String) dynaBean.get("add_date_st");
		String add_date_en = (String) dynaBean.get("add_date_en");
		String show_by_store_id = (String) dynaBean.get("show_by_store_id");
		boolean show_by_store = false;
		String store_id = null;
		if (StringUtils.isNotBlank(show_by_store_id)) {
			show_by_store = true;
			store_id = (String) dynaBean.get("store_id");
			/*
			 * KonkaJxcStoreInfo jxc_store = new KonkaJxcStoreInfo(); jxc_store.setId(Long.valueOf(store_id)); jxc_store
			 * = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(jxc_store); if(null != jxc_store){
			 * dynaBean.set("store_name", jxc_store.getStore_name()); }
			 */
		}
		PePdModel pdpemodel = new PePdModel();
		pdpemodel.setPd_id(Long.valueOf(pd_id));
		pdpemodel = super.getFacade().getPePdModelService().getPePdModel(pdpemodel);
		if (null != pdpemodel) {
			dynaBean.set("pd_name", pdpemodel.getMd_name());
		}
		BasePdClazz pd_clazz = new BasePdClazz();
		pd_clazz.setCls_id(Long.valueOf(pd_type_id));
		pd_clazz = super.getFacade().getBasePdClazzService().getBasePdClazz(pd_clazz);
		if (null != pd_clazz) {
			dynaBean.set("pd_type_name", pd_clazz.getCls_name());
		}

		// 获取期初 期初结存
		BigDecimal qcjc_count = new BigDecimal(0);
		BigDecimal qcjc_money = new BigDecimal(0);
		BigDecimal qcjc_price = new BigDecimal(0);

		KonkaJxcStoreState qcjc_pd = new KonkaJxcStoreState();
		KonkaJxcStoreQckc qckc_pd = new KonkaJxcStoreQckc();// 期初库存记录

		qckc_pd.setPd_id(Long.valueOf(pd_id));// 型号
		qckc_pd.setDept_id(Long.valueOf(dept_id));// 部门
		if (show_by_store) {
			if (null != store_id) {// 仓库
				qckc_pd.setStore_id(Long.valueOf(store_id));
			} else {
				qckc_pd.getMap().put("store_id_is_null", "true");
			}
		}
		qckc_pd.setQc_date(new SimpleDateFormat("yyyy-MM-dd").parse(add_date_st));
		List<KonkaJxcStoreQckc> qckc_pd_list = getFacade().getKonkaJxcStoreQckcService().getKonkaJxcStoreQckcList(
				qckc_pd);// 从期初库存记录表里面查询
		if (null != qckc_pd_list && qckc_pd_list.size() > 0) {
			for (KonkaJxcStoreQckc kjs_qckc : qckc_pd_list) {
				BigDecimal qcjc_count_sub = new BigDecimal(kjs_qckc.getNum());
				BigDecimal qcjc_money_sub = kjs_qckc.getPrice_sum();
				qcjc_count = qcjc_count.add(qcjc_count_sub);
				qcjc_money = qcjc_money.add(qcjc_money_sub);
			}
		} else {// 没有
			qcjc_pd.getMap().put("pd_id", pd_id);
			qcjc_pd.getMap().put("dept_id", dept_id);
			qcjc_pd.getMap().put("qcjc_date", add_date_st);
			if (show_by_store) {
				if (null != store_id) {// 仓库
					qcjc_pd.getMap().put("store_id", store_id);
				} else {
					qcjc_pd.getMap().put("store_id_is_null", "true");
				}
			}
			qcjc_pd = super.getFacade().getKonkaJxcStoreStateService().selectQcjcForStockState(qcjc_pd);
			qcjc_count = (BigDecimal) qcjc_pd.getMap().get("qcjc_count");
			qcjc_money = (BigDecimal) qcjc_pd.getMap().get("qcjc_money");
			if (qcjc_count.longValue() != 0) {
				qcjc_price = qcjc_money.divide(qcjc_count, 2, BigDecimal.ROUND_HALF_EVEN);// 期初单价
			}

			// 添加期初库存记录
			KonkaJxcStoreQckc store_qckc = new KonkaJxcStoreQckc();// 期初库存记录
			store_qckc.setPd_id(Long.valueOf(pd_id));// 型号
			store_qckc.setDept_id(Long.valueOf(dept_id));// 部门
			if (null != store_id) {// 仓库
				store_qckc.setStore_id(Long.valueOf(store_id));
			}
			store_qckc.setQc_date(new SimpleDateFormat("yyyy-MM-dd").parse(add_date_st));
			store_qckc.setNum(qcjc_count.longValue());
			store_qckc.setPrice(qcjc_price);
			store_qckc.setPrice_sum(qcjc_money);
			super.getFacade().getKonkaJxcStoreQckcService().createKonkaJxcStoreQckc(store_qckc);
		}
		dynaBean.set("qc_count", qcjc_count);
		dynaBean.set("qc_price", qcjc_price);
		dynaBean.set("qc_money", qcjc_money);

		// 获取进货、发货、退货、盘存信息
		KonkaJxcStoreState entity = new KonkaJxcStoreState();
		entity.getMap().put("dept_id", dept_id);
		entity.getMap().put("pd_id", pd_id);
		entity.getMap().put("date_st", add_date_st);
		entity.getMap().put("date_en", add_date_en);
		if (StringUtils.isNotBlank(show_by_store_id)) {// 根据仓库显示
			if (StringUtils.isNotBlank(store_id)) {
				entity.getMap().put("store_id", store_id);
			} else {
				entity.getMap().put("store_id_is_null", "true");
			}
		}
		List<KonkaJxcStoreState> stockList = super.getFacade().getKonkaJxcStoreStateService()
				.selectKonkaJXCStockDetailsList(entity);
		BigDecimal jh_count_total = new BigDecimal(0);
		BigDecimal jh_money_total = new BigDecimal(0);
		BigDecimal th_count_total = new BigDecimal(0);
		BigDecimal th_money_total = new BigDecimal(0);
		BigDecimal fh_count_total = new BigDecimal(0);
		BigDecimal fh_money_total = new BigDecimal(0);
		BigDecimal pc_count_total = new BigDecimal(0);
		BigDecimal pc_money_total = new BigDecimal(0);
		BigDecimal qm_count_total = qcjc_count;
		BigDecimal qm_money_total = qcjc_money;
		BigDecimal qm_price = new BigDecimal(0); // 期末单价
		if (null != stockList && stockList.size() > 0) {
			for (KonkaJxcStoreState temp : stockList) {
				/*
				 * if(!show_by_store){ if(null != temp.getStore_id()){ KonkaJxcStoreInfo jxc_store = new
				 * KonkaJxcStoreInfo(); jxc_store.setId(temp.getStore_id()); jxc_store =
				 * super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(jxc_store); if(null !=
				 * jxc_store){ temp.getMap().put("store_name", jxc_store.getStore_name()); } } }
				 */
				BigDecimal type = (BigDecimal) temp.getMap().get("type");
				BigDecimal count = (BigDecimal) temp.getMap().get("count");
				BigDecimal money = (BigDecimal) temp.getMap().get("money");
				if (type.intValue() == 0) {// 进货
					jh_count_total = jh_count_total.add(count);
					jh_money_total = jh_money_total.add(money);

					qm_count_total = qm_count_total.add(count);
					qm_money_total = qm_money_total.add(money);
					if (null != qm_count_total && qm_count_total.intValue() != 0) {
						qm_price = qm_money_total.divide(qm_count_total, 2, BigDecimal.ROUND_HALF_EVEN);
					}
				}
				if (type.intValue() == 1) {// 发货
					fh_count_total = fh_count_total.add(count);
					fh_money_total = fh_money_total.add(money);

					qm_count_total = qm_count_total.subtract(count);
					qm_money_total = qm_money_total.subtract(money);
					if (null != qm_count_total && qm_count_total.intValue() != 0) {
						qm_price = qm_money_total.divide(qm_count_total, 2, BigDecimal.ROUND_HALF_EVEN);
					}
				}
				if (type.intValue() == 2) {// 退货
					th_count_total = th_count_total.add(count);
					th_money_total = th_money_total.add(money);

					qm_count_total = qm_count_total.add(count);
					qm_money_total = qm_money_total.add(money);
					if (null != qm_count_total && qm_count_total.intValue() != 0) {
						qm_price = qm_money_total.divide(qm_count_total, 2, BigDecimal.ROUND_HALF_EVEN);
					}
				}
				if (type.intValue() == 3) {// 盘存
					pc_count_total = pc_count_total.add(count);
					pc_money_total = pc_money_total.add(money);

					qm_count_total = qm_count_total.add(count);
					qm_money_total = qm_money_total.add(money);
					if (null != qm_count_total && qm_count_total.intValue() != 0) {
						qm_price = qm_money_total.divide(qm_count_total, 2, BigDecimal.ROUND_HALF_EVEN);
					}
				}
				temp.getMap().put("qm_count", qm_count_total);
				temp.getMap().put("qm_price", qm_price);
				temp.getMap().put("qm_money", qm_money_total);
			}
			dynaBean.set("qm_count_total", stockList.get(stockList.size() - 1).getMap().get("qm_count"));
			dynaBean.set("qm_money_total", stockList.get(stockList.size() - 1).getMap().get("qm_money"));
			dynaBean.set("jh_count_total", jh_count_total);
			dynaBean.set("jh_money_total", jh_money_total);
			dynaBean.set("fh_count_total", fh_count_total);
			dynaBean.set("fh_money_total", fh_money_total);
			dynaBean.set("th_count_total", th_count_total);
			dynaBean.set("th_money_total", th_money_total);
			dynaBean.set("pc_count_total", pc_count_total);
			dynaBean.set("pc_money_total", pc_money_total);
		} else {
			dynaBean.set("qm_count_total", qm_count_total);
			dynaBean.set("qm_money_total", qm_money_total);
		}
		request.setAttribute("stockList", stockList);
		return mapping.findForward("view");
	}
}
