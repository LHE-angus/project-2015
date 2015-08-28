package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport2;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptJsMoney;
import com.ebiz.mmt.domain.KonkaSalesDept2;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.TaskPara;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class ChannelDataImport2Action extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1.检查当前菜单连接是否有权限
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		// 2.导航信息
		setNaviStringToRequestScope(form, request);

		// 3.条件过滤 ,菜单用处： menu = 1 ?总部订单查询用:分公司订单查询用
		DynaBean dynaBean = (DynaBean) form;

		String menu = (String) dynaBean.get("menu");

		String salesCode = (String) dynaBean.get("salesCode");
		String keyword = (String) dynaBean.get("keyword");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String column_8_like = (String) dynaBean.get("column_8_like");
		// 单据类型
		String column_9 = (String) dynaBean.get("column_9");
		// 订单日期
		String s_date = (String) dynaBean.get("s_date");
		String e_date = (String) dynaBean.get("e_date");
		// 凭证日期
		String startDate1 = (String) dynaBean.get("startDate1");
		String endDate1 = (String) dynaBean.get("endDate1");
		// 判断是否是第一次查询
		String is_first = (String) dynaBean.get("is_first");
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
		// 是否excel
		String excel_all = (String) dynaBean.get("excel_all");

		ChannelDataImport2 entity = new ChannelDataImport2();
		entity.getMap().put("count1", true);

		if (menu.equals("1")) {
			// 查总部与分公司结算订单的明细,与大客户的订单明细
			entity.getMap().put("doc_type_1", "zb");
		} else {
			// 查分公司结算订单明细
			entity.getMap().put("doc_type_2", "fgs");
		}

		dynaBean.set("menu", menu);

		if (StringUtils.isNotBlank(salesCode)) {
			entity.setColumn_25(salesCode.toLowerCase());
		}
		if (StringUtils.isNotBlank(keyword)) {
			entity.getMap().put("keyword", keyword);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(column_9)) {
			entity.setColumn_9(column_9.toUpperCase());
		}
		if (StringUtils.isNotBlank(column_8_like)) {
			entity.getMap().put("column_8_like", column_8_like);
		}
		if (StringUtils.isNotBlank(s_date)) {
			entity.getMap().put("s_date", s_date + " 00:00:00");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("s_date", ChannelDataImport2Action.theFirstDayOfCurrentMonth() + " 00:00:00");
				dynaBean.set("s_date", ChannelDataImport2Action.theFirstDayOfCurrentMonth());
			}
		}

		if (StringUtils.isNotBlank(e_date)) {
			entity.getMap().put("e_date", e_date + " 23:59:59");
		} else {
			if (StringUtils.isBlank(is_first)) {
				entity.getMap().put("e_date", fmt1.format(new Date()) + " 23:59:59");
				dynaBean.set("e_date", fmt1.format(new Date()));
			}
		}

		if (StringUtils.isNotBlank(startDate1)) {
			entity.getMap().put("startDate1", startDate1 + " 00:00:00");
		}
		// else {
		// if (StringUtils.isBlank(is_first)) {
		// entity.getMap().put("startDate1",
		// ChannelDataImport2Action.theFirstDayOfCurrentMonth() + " 00:00:00");
		// dynaBean.set("startDate1",
		// ChannelDataImport2Action.theFirstDayOfCurrentMonth());
		// }
		// }

		if (StringUtils.isNotBlank(endDate1)) {
			entity.getMap().put("endDate1", endDate1 + " 23:59:59");
		}
		// else {
		// if (StringUtils.isBlank(is_first)) {
		// entity.getMap().put("endDate1", fmt1.format(new Date()) +
		// " 23:59:59");
		// dynaBean.set("endDate1", fmt1.format(new Date()));
		// }
		// }

		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		// menu =1 总部明细,menu=2 分公司明细 均有kf交货单明细等信息
		// menu =3 用于总部的统计
		if (menu.equals("1")) {
			// 总部的销售组织

		} else if (menu.equals("2")) {
			// 分公司的销售组织
			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

			// 数据级别判断开始
			Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			request.setAttribute("max_dlevel", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", __dept_id);
					entity.getMap().put("count1", null);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("count1", null);
				break;
			case 0:
				__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
				entity.getMap().put("count1", null);
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", ui.getId());
			// 数据级别判断结束
		} else {

			// 总部订单明细,用于统计
			//System.out.println("menu=" + menu);

		}

		Long recordCount = super.getFacade().getChannelDataImport2Service().getChannelDataImport2Count(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<ChannelDataImport2> entityList = super.getFacade().getChannelDataImport2Service()
				.getChannelDataImport2PaginatedList(entity);

		if (entityList != null && entityList.size() > 0) {
			Double pagerCount = 0d;
			BigDecimal pagerMoney = new BigDecimal(0);
			for (ChannelDataImport2 t : entityList) {
				if (t.getColumn_12() != null) {
					pagerCount = pagerCount + Double.valueOf(t.getColumn_12());
				}
				pagerMoney = pagerMoney.add(t.getColumn_14() == null ? BigDecimal.valueOf(0f) : t.getColumn_14());
			}
			request.setAttribute("pagerCount", pagerCount);
			request.setAttribute("pagerMoney", pagerMoney);

			// 统计总数
			HashMap<String, BigDecimal> map = super.getFacade().getChannelDataImport2Service()
					.getChannelDataImport2AllCountAndAllMoney(entity);

		/*	if (map.get("ALL_COUNT") != null && map.get("ALL_MONEY") != null) {
				request.setAttribute("allCount", map.get("ALL_COUNT").toString());
				request.setAttribute("allMoney", map.get("ALL_MONEY").toString());
			}
			if (map.get("ALL_COUNT") == null && map.get("ALL_MONEY") == null) {
				request.setAttribute("allCount", 0.0);
				request.setAttribute("allMoney", 0.0);
			}
			*/
			
			request.setAttribute("allCount", null==map.get("ALL_COUNT")?0:map.get("ALL_COUNT"));
			request.setAttribute("allMoney", null==map.get("ALL_MONEY")?0.0:map.get("ALL_MONEY"));	
			
			
			
		} else {
			request.setAttribute("pagerCount", 0);
			request.setAttribute("pagerMoney", 0);
			request.setAttribute("allCount", 0.0);
			request.setAttribute("allMoney", 0.0);
		}

		// excel导出处理
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("R3订单明细（总部）") + ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<ChannelDataImport2> entityList1 = super.getFacade().getChannelDataImport2Service()
					.getChannelDataImport2PaginatedList(entity);
			request.setAttribute("entityList1", entityList1);

			return mapping.findForward("view");
		}

		request.setAttribute("entityList", entityList);
		request.setAttribute("recordCount", recordCount);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String column_8 = (String) dynaBean.get("column_8");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			ChannelDataImport2 entity = new ChannelDataImport2();
			entity.setId(new Long(id));
			super.getFacade().getChannelDataImport2Service().removeChannelDataImport2(entity);
			super.createSysOperLog(request, "CHANNEL_DATA_IMPORT_2", entity.getId(), mod_id, "删除:删除了一条订单号为" + column_8
					+ "的数据", BeanUtils.describe(entity).toString());
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			TaskPara entity = new TaskPara();
			entity.getMap().put("pks", pks);
			super.getFacade().getTaskParaService().removeTaskPara(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super
				.encodeSerializedQueryString(super.serialize(request, "id", "pks", "column_8", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	/***
	 * 总部与分公司/大客户的订单同步
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ZbSync(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession(false);
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String menu = (String) dynaBean.get("menu");
		String mod_id = (String) dynaBean.get("mod_id");

		// 重置数据标记
		String resetflag = null;// 是否重置数据

		int days = Integer.valueOf((String) dynaBean.get("days"));
		if (days > 30) {
			resetflag = "resetflag";
		}

		Long isize = 0l;
		Long msize = 0l;

		// 需要同步销售组织集合(此处只做总部-分公司的订单明细用来做统计.分公司-客户的订单明细在ChannelDataImportAction!
		// 唉,这需求)
		Set<String> syncVkorgList = new HashSet<String>();

		// 需要同步的客户集合(此处只做总部-分公司的订单明细用来做统计)
		Set<String> ctmList = new HashSet<String>();

		int roleId = 0;
		if (userInfo.getRole_id() != null && userInfo.getRole_id().length() > 0) {
			roleId = Integer.valueOf(userInfo.getRole_id());
		}
		// 只有总部人员 角色 0<= roleid <30 ,200<= roleid <300
		if ((roleId >= 30 && roleId < 200) || (roleId >= 300)) {
			super.renderJavaScript(response, "alert('对不起，您没有权限操作此模块！');history.back();");
			return null;
		}

		// step 1 :取出总部销售组织 1001 一个 ,另外是以分公司为单位的客户 300多个
		List<KonkaSalesDept2> ksList = new ArrayList<KonkaSalesDept2>();
		KonkaSalesDept2 ks = new KonkaSalesDept2();
		ks.setIs_del(0);
		ks.setOrg_type(1);// 康佳集团
		ksList = super.getFacade().getKonkaSalesDept2Service().getKonkaSalesDept2List(ks);
		for (KonkaSalesDept2 kd : ksList) {
			syncVkorgList.add(kd.getSales_org_code());// 为了去重,减少数据操作
		}

		// step 2 :客户集合 orgType = 2||3 大客户或分公司
		ks.setOrg_type(2);
		ksList.clear();
		ksList = super.getFacade().getKonkaSalesDept2Service().getKonkaSalesDept2List(ks);
		for (KonkaSalesDept2 kd : ksList) {
			ctmList.add(kd.getSales_org_code());// 为了去重,减少数据操作
		}
		ks.setOrg_type(3);
		ksList.clear();
		ksList = super.getFacade().getKonkaSalesDept2Service().getKonkaSalesDept2List(ks);
		for (KonkaSalesDept2 kd : ksList) {
			ctmList.add(kd.getSales_org_code());// 为了去重,减少数据操作
		}

		// 处理客户编码10位,前面不足补0
		List<String> _tempList = new ArrayList<String>();
		_tempList.addAll(ctmList);
		ctmList.clear();
		for (String s : _tempList) {
			ctmList.add(StringUtils.leftPad(s, 10, '0'));
		}
		// 执行结果
		Map<String, Long> map = new HashMap<String, Long>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 计算当前日期往前推30天的时间
		Calendar c = Calendar.getInstance();
		String year = c.get(Calendar.YEAR) + "";
		c.add(Calendar.DATE, -days);
		Date s_date = c.getTime();

		// =========================同步参数 正式 start=============================//
		// 产品组
		String v_vtweg = "10";
		// 分销渠道
		String v_spart = "10";
		// 同步开始时间
		String v_audat_begin = sdf.format(s_date);
		// 同步截止时间
		String v_audat_end = sdf.format(new Date());
		// =========================同步参数 正式 end=============================//

		/** 1. product start **/
		// 系统稳定后,使用定时器同步,只会同步当前日期,再往前推一个月得到区间数据 **/
		if (resetflag == null || resetflag.equals("")) {
			// test code
			// syncVkorgList.clear();
			// syncVkorgList.add("1001");
			// v_audat_begin = "2013-11-13";
			// v_audat_end = "2013-11-13";
			// ctmList.clear();
			// ctmList.add("0001003968");
			// test code

			// 1,总部对分公司对大客户的订单明细
			map = super.getFacade().getChannelDataImport2Service().createOrModifySyncChannelDataForzbTj(syncVkorgList,
					v_vtweg, v_spart, v_audat_begin, v_audat_end, ctmList);

			isize += map.get("isize");
			msize += map.get("msize");

			// release
			map.clear();
			syncVkorgList.clear();
		}
		/** product end */

		/** 2. reset start 重置2年数据 */
		if (resetflag != null && (resetflag.length() > 0)) {
			// 同步两年数据 ---> 1月份,所有客户数据 : 2月份,所有客户客户数据 ....
			v_audat_begin = "";
			v_audat_end = "";
			LinkedHashMap<String, String> dateMap = getDateMap(Integer.parseInt(year) - 1, Integer.parseInt(year));
			// 1,总部与分公司/大客户数据重置
			// 月份
			for (Entry<String, String> s : dateMap.entrySet()) {

				v_audat_begin = s.getKey();
				v_audat_end = s.getValue();

				for (String korg : syncVkorgList) {
					Set<String> korgList = new HashSet<String>();
					korgList.clear();
					korgList.add(korg);
					// 处理数据
					map = super.getFacade().getChannelDataImport2Service().createOrModifySyncChannelDataForzbTj(
							korgList, v_vtweg, v_spart, v_audat_begin, v_audat_end, ctmList);
					// 统计被影响的数据
					isize += map.get("isize");
					msize += map.get("msize");

				}
			}

		}
		/** reset end */

		// 记录操作
		createOpLog((isize + msize), userInfo);

		saveMessage(request, "prodadmin.md.tb.success", new String(String.valueOf(isize + msize)));

        return mapping.findForward("list");

	}

	public ActionForward to_tb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date day = new Date();

		KonkaDeptJsMoney kdjMoney = new KonkaDeptJsMoney();
		kdjMoney.getMap().put("this_day_s", format1.format(day) + "-01 00:00:00");
		kdjMoney.getMap().put("this_day_e", format2.format(day) + " 23:59:59");

		List<KonkaDeptJsMoney> kdjList = super.getFacade().getKonkaDeptJsMoneyService().getKonkaDeptJsMoneyToR3List(
				kdjMoney);

		if (kdjList.size() > 0) {
			for (KonkaDeptJsMoney temp : kdjList) {
				if (null != temp.getMap().get("dept_name_"))
					temp.setDept_name(temp.getMap().get("dept_name_").toString());
				if (null != temp.getMap().get("dept_id_"))
					temp.setDept_id(Long.valueOf(temp.getMap().get("dept_id_").toString()));
				if (null != temp.getMap().get("dept_sn_"))
					temp.setDept_sn(temp.getMap().get("dept_sn_").toString());
				if (null != temp.getMap().get("js_money_"))
					temp.setJs_money(new BigDecimal(temp.getMap().get("js_money_").toString()));
				temp.setType(20);
				temp.setJs_date(day);
				temp.setAdd_date(day);
				super.getFacade().getKonkaDeptJsMoneyService().createKonkaDeptJsMoney(temp);
			}
		}

		return null;
	}

	private void createOpLog(long effectNumber, PeProdUser userInfo) {

		OperLog t = new OperLog();
		t.setPpdm_name("进销存-进货管理-R3订单");
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip("0:0:0:0:0:0:0:1");

		t.setOper_desc(effectNumber + "条数据被影响.[user]");
		t.setLink_tab("CHANNEL_DATA_IMPORT_2");
		t.setLink_id(0l);
		t.setOper_type("INSERT");
		t.setOper_uid(userInfo.getId());
		t.setOper_uname(userInfo.getUser_name());
		getFacade().getOperLogService().createOperLog(t);
	}

	public static synchronized LinkedHashMap<String, String> getDateMap(int... year) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < year.length; i++) {// year
			if (year[i] <= 1970)
				throw new IllegalArgumentException("the year that you input can not le 1970");
			if (year[i] >= 9999)
				throw new IllegalArgumentException("the year that you input can not gt 9999");
			for (int j = 0; j <= 12; j++) {// month
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year[i]);
				c.set(Calendar.MONTH, j);
				String y = String.valueOf(year[i]);
				String m = "";
				String d = "";// the first day of a month
				String d2 = "";// the last day of a month
				if (c.get(Calendar.MONTH) + 1 >= 10) {
					m = String.valueOf(c.get(Calendar.MONTH) + 1);
				} else {
					m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
				}
				d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
				d2 = "" + c.getActualMaximum(Calendar.DAY_OF_MONTH);

				String start_day = y + "-" + m + "-" + d;
				String last_day = y + "-" + m + "-" + d2;
				map.put(start_day, last_day);
			}
		}
		return map;
	}

	private static String theFirstDayOfCurrentMonth() {
		Calendar c = Calendar.getInstance();

		String y = String.valueOf(c.get(Calendar.YEAR));
		String m = "";
		String d = "";// the first day of a month
		if (c.get(Calendar.MONTH) + 1 >= 10) {
			m = String.valueOf(c.get(Calendar.MONTH) + 1);
		} else {
			m = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		}
		d = "0" + c.getActualMinimum(Calendar.DAY_OF_MONTH);
		String start_day = y + "-" + m + "-" + d;
		return start_day;
	}

}
