package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdHdSet;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetHd;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-01-11
 */
public class KonkaXxSellJsAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn = (String) dynaBean.get("zmd_sn");
		String sell_date_begin = (String) dynaBean.get("sell_date_begin");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		KonkaXxSellBill entity = new KonkaXxSellBill();

		entity.getMap().put("sell_date_begin", sell_date_begin);
		entity.getMap().put("sell_date_end", sell_date_end);
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		if (StringUtils.isNotBlank(zmd_sn)) {
			entity.setZmd_sn(zmd_sn);
		}

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_30_40 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400 && temp.getRole_id() > 300) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() < 40 && temp.getRole_id() >= 30) {
					role_id_btw_30_40 = true;
				}
			}
		}

		if (!(role_id_btw_300_400 || role_id_btw_30_40)) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else {
			request.setAttribute("role_id_true", true);
		}

		// 当前分公司
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());

		// 销售单状态 --->40用户已确认收货
		// entity.getMap().put("sell_state_ge", 70);
		entity.setSell_state(70L);
		// 财务未结算销售单
		entity.setJs_bill_state(0);
		entity.setOrder_type(1);
		entity.setStock_from(1);

		Long recordCount = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountForJs(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxSellBill> entityList = getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillForJSPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		// dynaBean.set("role_id", peRoleUser.getRole_id());

		// 查询专卖店
		KonkaXxZmd zmd = new KonkaXxZmd();
		if (kDept != null)
			zmd.setDept_id(kDept.getDept_id());

		List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

		request.setAttribute("zmdList", zmdList);

		return mapping.findForward("list");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String sell_date_begin = (String) dynaBean.get("sell_date_begin");
		String sell_date_end = (String) dynaBean.get("sell_date_end");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400 && temp.getRole_id() > 300) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_btw_300_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.getMap().put("sell_date_begin", sell_date_begin);
		entity.getMap().put("sell_date_end", sell_date_end);

		// 当前分公司
		if (kDept != null)
			entity.setDept_id(kDept.getDept_id());
		// 销售单状态 --->40用户已确认收货
		entity.getMap().put("sell_state_ge", 40);
		// 财务未结算销售单
		entity.setJs_bill_state(0);

		List<KonkaXxSellBill> entityList = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillList(entity);
		request.setAttribute("entityList", entityList);

		dynaBean.set("now_date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		return new ActionForward("/../manager/zmd/KonkaXxSellJs/export.jsp");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(konkaXxSellBill.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);

		// 处理佣金
		Map<String, String> map; // 保存查询结果
		Integer result = 0; // 临时记录循环是否正常
		BigDecimal total_reward = new BigDecimal(0); // 保存本次结算的总金额
		StringBuffer details = new StringBuffer(""); // 辅助提交数据 数据格式
		// 【sell_bill_id,sell_bill_details_id,zmd_fee,formula#sell_bill_id,sell_bill_details_id,zmd_fee,formula#....】
		for (KonkaXxSellBillDetails temp : konkaXxSellBillDetailsList) {
			// 调用处理结算方法
			map = this.getRewardBySillDetailsId(request, temp.getSell_bill_details_id());

			if ("0".equals(map.get("state"))) { // 调用出错
				result = 0;
				break;
			}

			details.append(temp.getSell_bill_id()).append(",");
			details.append(temp.getSell_bill_details_id()).append(",");
			details.append(map.get("reward")).append(",");
			details.append(map.get("formula")).append("#");

			temp.getMap().put("reward", map.get("reward"));
			temp.getMap().put("formula", map.get("formula"));
			total_reward = total_reward.add(new BigDecimal(map.get("reward")));
			result = 1; // 设置本次循环正常
		}
		if (result == 0) { // 异常报错捕捉
			String msg = super.getMessage(request, "konka.xx.zmd.sill.bill.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		details.delete(details.length() - 1, details.length());

		JSONArray json = JSONArray.fromObject(konkaXxSellBillDetailsList);
		request.setAttribute("konkaXxSellBillDetailsListJSON", json.toString());

		// 付款方式
		super.setBaseTypeListByParIdToRequest(50000L, request);

		super.setBaseTypeListByParIdToRequest(70000L, request);

		request.setAttribute("form_values", details.toString());
		request.setAttribute("total_reward", total_reward.toString());
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);

		return mapping.findForward("input");
	}

	public ActionForward ajaxGetReward(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String sell_bill_id_pks = (String) dynaBean.get("sell_bill_id_pks");
		if (!GenericValidator.isLong(sell_bill_id) && StringUtils.isBlank(sell_bill_id_pks)) {
			String msg = super.getMessage(request, "errors.param");
			super.render(response, "{\"result\":\"0\",\"msg\":\"" + msg + "\"}", "text/x-json;charset=UTF-8");
			return null;
		}

		// 保存所有结算项
		List<KonkaXxSellBillDetails> detailsList = new ArrayList<KonkaXxSellBillDetails>();

		// 单个订单
		if (GenericValidator.isLong(sell_bill_id)) {
			// 根据订单编号查询所有的订单明细
			KonkaXxSellBillDetails t1 = new KonkaXxSellBillDetails();
			t1.setSell_bill_id(Long.valueOf(sell_bill_id));
			List<KonkaXxSellBillDetails> t1List = super.getFacade().getKonkaXxSellBillDetailsService()
					.getKonkaXxSellBillDetailsList(t1);
			for (KonkaXxSellBillDetails t : t1List) {
				detailsList.add(t);
			}
		} else {
			String[] pks = sell_bill_id_pks.split(",");
			for (String string : pks) {
				// 根据订单编号查询所有的订单明细
				KonkaXxSellBillDetails t2 = new KonkaXxSellBillDetails();
				t2.setSell_bill_id(Long.valueOf(string));
				List<KonkaXxSellBillDetails> t2List = super.getFacade().getKonkaXxSellBillDetailsService()
						.getKonkaXxSellBillDetailsList(t2);
				for (KonkaXxSellBillDetails tt : t2List) {
					detailsList.add(tt);
				}
			}
		}

		KonkaXxBaseType baseType = new KonkaXxBaseType();
		baseType.setPar_id(70000L);
		baseType.setIs_del(0);
		List<KonkaXxBaseType> list = getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseTypeList(baseType);

		// 开始循环计算销售的佣金
		Integer result = 0; // 临时记录循环是否正常
		String msg = "";
		Map<String, String> map; // 保存查询结果
		BigDecimal total_reward = new BigDecimal(0);
		StringBuffer details = new StringBuffer("[");
		for (KonkaXxSellBillDetails ksbd : detailsList) {
			map = this.getRewardBySillDetailsId(request, ksbd.getSell_bill_details_id());
			if ("0".equals(map.get("state"))) { // 调用出错
				msg = map.get("msg");
				result = 0;
				break;
			}

			details.append("{").append("\"pd_cls_name\":\"").append(ksbd.getPd_cls_name()).append("\",");
			details.append("\"md_name\":\"").append(ksbd.getMd_name()).append("\",");
			details.append("\"sell_bill_details_id\":\"").append(ksbd.getSell_bill_details_id()).append("\",");
			details.append("\"sell_bill_id\":\"").append(ksbd.getSell_bill_id()).append("\",");
			details.append("\"counts\":\"").append(ksbd.getCounts()).append("\",");
			details.append("\"price\":\"").append(ksbd.getPrice()).append("\",");
			details.append("\"zmd_fee\":\"").append(map.get("reward")).append("\",");
			String pd_type = "";
			for (KonkaXxBaseType bt : list) {
				if (StringUtils.equals(ksbd.getPd_type().toString(), bt.getType_id().toString())) {
					pd_type = bt.getType_name();
					break;
				}
			}
			details.append("\"pd_type\":\"").append(pd_type).append("\",");
			details.append("\"formula\":\"").append(map.get("formula")).append("\"},");

			total_reward = total_reward.add(new BigDecimal(map.get("reward")));
			result = 1; // 设置本次循环正常
		}
		if (result == 0) { // 异常报错捕捉
			super.render(response, "{\"result\":\"0\",\"msg\":\"" + msg + "\"}", "text/x-json;charset=UTF-8");
			return null;
		}

		details.delete(details.length() - 1, details.length()).append("]");

		// 拼接返回的字符串
		StringBuffer return_json = new StringBuffer("");
		return_json.append("{");
		return_json.append("\"result\":\"1\",");
		return_json.append("\"total_reward\":\"").append(total_reward.toString()).append("\",");
		return_json.append("\"details\":").append(details.toString());
		return_json.append("}");

		super.render(response, return_json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward save_js(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		// 辅助提交数据 数据格式 :
		// sell_bill_id,sell_bill_details_id,zmd_fee,formula#sell_bill_id,sell_bill_details_id,zmd_fee,formula#....
		String form_values = (String) dynaBean.get("form_values");

		if (StringUtils.isBlank(form_values)) {
			String msg = super.getMessage(request, "errors.param");
			;
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取会话中的用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if (null == ui) {
			String msg = super.getMessage(request, "konka.xx.zmd.session.userinfo.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 辅助提交数据 数据格式 :
		// 订单ID，明细ID，返佣值，返佣快照，手工调整数值
		// sell_bill_id,sell_bill_details_id,zmd_fee,formula,tzjs#sell_bill_id,sell_bill_details_id,zmd_fee,formula,tzjs#....
		StringBuffer sb_form_values = new StringBuffer();
		// 处理调整数值
		String[] values = form_values.split("#");
		for (String string : values) {
			String[] temps = string.split(",");
			String sell_bill_id = temps[0];
			String sell_bill_details_id = temps[1];
			String zmd_fee = temps[2];
			String formula = temps[3];

			// 取得调整数值
			String tzjs = (String) dynaBean.get("tzjs_" + sell_bill_details_id);
			sb_form_values.append(sell_bill_id).append(",").append(sell_bill_details_id).append(",").append(zmd_fee)
					.append(",").append(formula).append(",").append(tzjs).append("#");
		}
		// 去除最后一个 #
		sb_form_values.delete(sb_form_values.length() - 1, sb_form_values.length());

		// 在事务中处理结算
		super.getFacade().getKonkaXxSellBillDetailsService().rewardJsByStringDataUser(sb_form_values.toString(), ui);

		saveMessage(request, "konka.entity.jies.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-01-12
	 * @desc 根据销售单中的明细ID,得到这个产品的返佣
	 * @return 
	 *         map中主要存放：state(状态:0-失败、1-成功)，msg(提示语)，reward(佣金)，formula(当时的计算公式)，
	 *         state(状态:0-失败、1-成功)
	 */
	private Map<String, String> getRewardBySillDetailsId(HttpServletRequest request, Long sill_detials_id) {
		Map<String, String> map = new HashMap<String, String>();
		if (null == sill_detials_id || 0L == sill_detials_id) {
			map.put("state", "0");
			map.put("msg", super.getMessage(request, "konka.xx.zmd.sill.bill.detail.id.lost"));
			return map;
		}

		// 查询销售单明细
		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_details_id(sill_detials_id);
		konkaXxSellBillDetails = super.getFacade().getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetails(
				konkaXxSellBillDetails);
		if (null == konkaXxSellBillDetails) {
			map.put("state", "0");
			map.put("msg", super.getMessage(request, "konka.xx.zmd.sill.bill.detail.lost"));
			return map;
		}

		// 查询销售单
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(konkaXxSellBillDetails.getSell_bill_id());
		konkaXxSellBill = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			map.put("state", "0");
			map.put("msg", super.getMessage(request, "konka.xx.zmd.sill.bill.lost"));
			return map;
		}

		// 取产品
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setDept_id(konkaXxSellBill.getDept_id());
		konkaXxPd.setMd_name(konkaXxSellBillDetails.getMd_name());
		konkaXxPd = super.getFacade().getKonkaXxPdService().getKonkaXxPd(konkaXxPd);
		if (null == konkaXxPd) {
			map.put("state", "0");
			map.put("msg", super.getMessage(request, "konka.xx.zmd.goods.lost"));
			return map;
		}

		// 取产品类型
		KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		konkaXxBaseType.setType_id(konkaXxSellBillDetails.getPd_type());
		konkaXxBaseType = super.getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseType(konkaXxBaseType);

		/*
		 * 产品反应比例优先级:专卖店活动返佣 -> 专卖店产品返佣 -> 专卖店备案返佣
		 */

		// 取专卖店的返佣设置
		KonkaXxZmdRewardSet konkaXxZmdRewardSet = new KonkaXxZmdRewardSet();
		konkaXxZmdRewardSet.setIs_enabled(1); // 只查询已经启用的
		konkaXxZmdRewardSet.setDept_id(konkaXxSellBill.getDept_id());
		konkaXxZmdRewardSet.setZmd_id(konkaXxSellBill.getZmd_id());
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
				.getKonkaXxZmdRewardSetList(konkaXxZmdRewardSet);
		if (null == konkaXxZmdRewardSetList || konkaXxZmdRewardSetList.size() == 0) {
			map.put("state", "0");
			map.put("msg", super.getMessage(request, "konka.xx.zmd.set.reward.none"));
			return map;
		}

		// 取专卖店产品返佣设置
		KonkaXxZmdRewardSetPd konkaXxZmdRewardSetPd = new KonkaXxZmdRewardSetPd();
		konkaXxZmdRewardSetPd.setDept_id(konkaXxSellBill.getDept_id());
		konkaXxZmdRewardSetPd.setZmd_id(konkaXxSellBill.getZmd_id());
		konkaXxZmdRewardSetPd.setMd_name(konkaXxPd.getMd_name());
		konkaXxZmdRewardSetPd.setReward_type(konkaXxSellBillDetails.getPd_type());
		List<KonkaXxZmdRewardSetPd> konkaXxZmdRewardSetPdList = super.getFacade().getKonkaXxZmdRewardSetPdService()
				.getKonkaXxZmdRewardSetPdList(konkaXxZmdRewardSetPd);

		// 查询是否参与活动
		KonkaXxZmdHdSet konkaXxZmdHdSet = new KonkaXxZmdHdSet();
		konkaXxZmdHdSet.setDept_id(konkaXxSellBill.getDept_id());
		konkaXxZmdHdSet.getMap().put("hd_date", konkaXxSellBill.getSell_date());
		konkaXxZmdHdSet = super.getFacade().getKonkaXxZmdHdSetService().getKonkaXxZmdHdSet(konkaXxZmdHdSet);
		List<KonkaXxZmdRewardSetHd> konkaXxZmdRewardSetHdList = new ArrayList<KonkaXxZmdRewardSetHd>();
		if (null != konkaXxZmdHdSet) { // 分公司有活动
			// 判断专卖店是否参与活动
			KonkaXxZmdRewardSetHd konkaXxZmdRewardSetHd = new KonkaXxZmdRewardSetHd();
			konkaXxZmdRewardSetHd.setHd_id(konkaXxZmdHdSet.getHd_id());
			konkaXxZmdRewardSetHd.setZmd_id(konkaXxSellBill.getZmd_id());
			konkaXxZmdRewardSetHd.setReward_type(konkaXxSellBillDetails.getPd_type());
			konkaXxZmdRewardSetHdList = super.getFacade().getKonkaXxZmdRewardSetHdService()
					.getKonkaXxZmdRewardSetHdList(konkaXxZmdRewardSetHd);
		}

		// 以下开始计算佣金 starting...

		/*
		 * 康佳专卖店-商品类型[par_id:70000]：常规机型[默认]
		 * [type_id:70100]/工程机[type_id:70200]/增值
		 * [type_id:70300]/样机[type_id:70400]/促销[type_id:70500]
		 * 康佳专卖店-反佣类型[par_id:80000]：常规零售返佣（8%）
		 * [type_id:80100]/高买部分返佣（高卖部分83%）[type_id
		 * :80200]/工程机返佣（建议按照1%—3%）[type_id:80300]/规模激励（年零售总额的1%）[type_id:80400]
		 * 承包专卖店盈利设计：常规零售返佣（8%）+ 高买部分返佣（高卖部分83%）+ 工程机返佣（建议按照1%—3%）+
		 * 规模激励（年零售总额的1%）。
		 */

		// 临时变量
		StringBuffer changgui_formula = new StringBuffer("常规零售返佣：");
		BigDecimal changgui_reward = new BigDecimal("0");
		StringBuffer gaomai_formula = new StringBuffer("高卖部分返佣：");
		BigDecimal gaomai_reward = new BigDecimal("0");
		StringBuffer pd_formula = new StringBuffer(""); // 产品类型返佣
		BigDecimal pd_reward = new BigDecimal("0"); // 产品类型返佣
		StringBuffer guimo_formula = new StringBuffer("规模激励：");
		BigDecimal guimo_reward = new BigDecimal("0");

		for (KonkaXxZmdRewardSet temp : konkaXxZmdRewardSetList) {
			if (temp.getReward_type() == 80100L) {// 常规零售返佣
				BigDecimal counts = new BigDecimal(konkaXxSellBillDetails.getCounts().toString()); // 购买数量
				BigDecimal price_ref = konkaXxPd.getPrice_ref(); // 产品参考价
				BigDecimal reward_ratio = temp.getReward_ratio().divide(new BigDecimal(100)).setScale(4,
						BigDecimal.ROUND_HALF_UP); // 存在数据库中的是按 n%存放的，这里转换成小数

				changgui_reward = counts.multiply(price_ref).multiply(reward_ratio).setScale(4,
						BigDecimal.ROUND_HALF_UP);
				changgui_formula.append(new BigDecimal(changgui_reward.toString())
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}

			if (temp.getReward_type() == 80200L) {// 高卖部分返佣
				BigDecimal counts = new BigDecimal(konkaXxSellBillDetails.getCounts().toString()); // 购买数量
				BigDecimal price_ref; // 高买价格
				if (0 >= konkaXxSellBillDetails.getPrice().compareTo(konkaXxPd.getPrice_ref())) {
					price_ref = new BigDecimal(0);
				} else {
					price_ref = konkaXxSellBillDetails.getPrice().subtract(konkaXxPd.getPrice_ref()).setScale(0,
							BigDecimal.ROUND_HALF_UP);
				}
				BigDecimal reward_ratio = temp.getReward_ratio().divide(new BigDecimal(100)).setScale(4,
						BigDecimal.ROUND_HALF_UP); // 存在数据库中的是按 n%存放的，这里转换成小数

				gaomai_reward = counts.multiply(price_ref).multiply(reward_ratio).setScale(4, BigDecimal.ROUND_HALF_UP);
				gaomai_formula.append(new BigDecimal(gaomai_reward.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString());
			}

			if (temp.getReward_type() == 80400L) {// 规模激励
				BigDecimal counts = new BigDecimal(konkaXxSellBillDetails.getCounts().toString()); // 购买数量
				BigDecimal price_ref = konkaXxPd.getPrice_ref(); // 产品参考价
				BigDecimal reward_ratio = temp.getReward_ratio().divide(new BigDecimal(100)).setScale(4,
						BigDecimal.ROUND_HALF_UP); // 存在数据库中的是按 n%存放的，这里转换成小数

				guimo_reward = counts.multiply(price_ref).multiply(reward_ratio).setScale(4, BigDecimal.ROUND_HALF_UP);
				guimo_formula.append(new BigDecimal(guimo_reward.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString());
			}
		}

		// 计算具体的产品类型返佣
		if (konkaXxZmdRewardSetHdList.size() != 0) { // 有活动返佣
			BigDecimal counts = new BigDecimal(konkaXxSellBillDetails.getCounts().toString()); // 购买数量
			BigDecimal price_ref = konkaXxPd.getPrice_ref(); // 产品参考价
			BigDecimal reward_ratio = konkaXxZmdRewardSetHdList.get(0).getReward_ratio().divide(new BigDecimal(100))
					.setScale(4, BigDecimal.ROUND_HALF_UP); // 存在数据库中的是按
			// n%存放的，这里转换成小数

			pd_reward = counts.multiply(price_ref).multiply(reward_ratio).setScale(4, BigDecimal.ROUND_HALF_UP);
			pd_formula.append(konkaXxBaseType.getType_name()).append("，");
			pd_formula.append(konkaXxZmdHdSet.getHd_title()).append("[").append(
					DateFormatUtils.format(konkaXxZmdHdSet.getStart_date(), "yyyy-MM-dd")).append("至").append(
					DateFormatUtils.format(konkaXxZmdHdSet.getEnd_date(), "yyyy-MM-dd")).append("]");
			pd_formula.append("返佣：");
			pd_formula.append(new BigDecimal(pd_reward.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		} else if (konkaXxZmdRewardSetPdList.size() != 0) { // 无活动，设置了产品返佣
			BigDecimal counts = new BigDecimal(konkaXxSellBillDetails.getCounts().toString()); // 购买数量
			BigDecimal price_ref = konkaXxPd.getPrice_ref(); // 产品参考价
			BigDecimal reward_ratio = konkaXxZmdRewardSetPdList.get(0).getReward_ratio().divide(new BigDecimal(100))
					.setScale(4, BigDecimal.ROUND_HALF_UP); // 存在数据库中的是按n%存放的，这里转换成小数

			pd_reward = counts.multiply(price_ref).multiply(reward_ratio).setScale(4, BigDecimal.ROUND_HALF_UP);
			pd_formula.append(konkaXxBaseType.getType_name()).append("，").append("产品返佣：");
			pd_formula.append(new BigDecimal(pd_reward.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		} else { // 无活动，无产品返佣，判断是否是工程机返佣，否则不给返佣点
			for (KonkaXxZmdRewardSet temp : konkaXxZmdRewardSetList) {
				if (konkaXxSellBillDetails.getPd_type() == 70200L && temp.getReward_type() == 80300L) {// 工程机返佣
					BigDecimal counts = new BigDecimal(konkaXxSellBillDetails.getCounts().toString()); // 购买数量
					BigDecimal price_ref = konkaXxPd.getPrice_ref(); // 产品参考价
					BigDecimal reward_ratio = temp.getReward_ratio().divide(new BigDecimal(100)).setScale(4,
							BigDecimal.ROUND_HALF_UP); // 存在数据库中的是按
					// n%存放的，这里转换成小数

					pd_reward = counts.multiply(price_ref).multiply(reward_ratio).setScale(4, BigDecimal.ROUND_HALF_UP);
					pd_formula.append(konkaXxBaseType.getType_name()).append("，").append("默认工程机返佣：");
					pd_formula.append(new BigDecimal(pd_reward.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
							.toString());
				}
			}
		}

		// 最后里累加拼接计算公式
		BigDecimal reward = new BigDecimal("0"); // 最后的总返佣
		StringBuffer formula = new StringBuffer(""); // 最后的总计算公式

		reward = reward.add(changgui_reward).setScale(4, BigDecimal.ROUND_HALF_UP);
		formula.append(changgui_formula.toString()).append("<br />"); // 常规零售返佣
		reward = reward.add(gaomai_reward).setScale(4, BigDecimal.ROUND_HALF_UP); // 高卖返佣
		formula.append(gaomai_formula.toString()).append("<br />"); // 高卖返佣
		reward = reward.add(pd_reward).setScale(4, BigDecimal.ROUND_HALF_UP); // 产品返佣
		if (StringUtils.isNotBlank(pd_formula.toString())) {
			formula.append(pd_formula.toString()).append("<br />"); // 产品返佣
		}
		reward = reward.add(guimo_reward).setScale(2, BigDecimal.ROUND_HALF_UP); // 规模激励
		formula.append(guimo_formula.toString()); // 规模激励

		// 计算佣金 end.

		map.put("state", "1");
		map.put("reward", reward.toString());
		map.put("formula", formula.toString());
		return map;
	}
}
