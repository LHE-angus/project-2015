package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-28
 */
public class EcJieSuanRebatesAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));
		dynaBean.set("month_start", String.valueOf(calendar.get(Calendar.MONTH) + 1));
		dynaBean.set("month_end", String.valueOf(calendar.get(Calendar.MONTH) + 1));
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String year = (String) dynaBean.get("year");
		String month_start = (String) dynaBean.get("month_start");
		String month_end = (String) dynaBean.get("month_end");
		String rebates_status = (String) dynaBean.get("rebates_status");
		String dept_id = (String) dynaBean.get("dept_id");
		String order_from = (String) dynaBean.get("order_from");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String card_no_like = (String) dynaBean.get("card_no_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue()==140317 || peRoleUser.getRole_id().intValue()==1001 ) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		logger.info("++++++++++++++" + role_id_gt_30_lt_60);
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单

		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 月份区间
		if (StringUtils.isBlank(month_start)) {
			month_start = "1";
		}
		if (StringUtils.isBlank(month_end)) {
			month_start = "12";
		}
		int month_start_num = Integer.valueOf(month_start);
		int month_end_num = Integer.valueOf(month_end);
		int start_num = 0;
		int end_num = 0;
		if (month_start_num < month_end_num) {
			start_num = month_start_num;
			end_num = month_end_num;
		} else {
			start_num = month_end_num;
			end_num = month_end_num;
		}
		PshowOrdeDetails entity = new PshowOrdeDetails();
		String m_s = year + "-";
		String m_e = year + "-";
		if (start_num < 10) {
			m_s = m_s + "0" + start_num;
		} else {
			m_s = m_s + start_num;
		}
		if (end_num < 10) {
			m_e = m_e + "0" + end_num;
		} else {
			m_e = m_e + end_num;
		}
		entity.getMap().put("start_date", m_s);
		entity.getMap().put("end_date", m_e);

		if (role_id_gt_30_lt_60) {
			entity.getMap().put("fh_fgs_dept_id", user.getDept_id());
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fh_fgs_dept_id", Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(order_from)) {
			entity.getMap().put("order_from", order_from);
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}

		entity.getMap().put("state_in", new Integer[] { 60 });
		entity.getMap().put("rebates", "1");

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);

		// 工号销售结算明细总表
		// 发货分公司 收货人所在分公司 订单号 产品型号 销售收入（销售总价格） 数量 现款价（成本价） 毛利 税金（增值税金=毛利*17%）
		// 物流（接口导入） 支付费用（支付宝，民生银行） 佣金 运营费用 应划拨利润
		//
		//
		// 销售毛利=收入（销售总额）-成本
		// 增值税金=销售毛利*17%
		// 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
		// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
		HashMap map = new HashMap();
		List<HashMap> list = new ArrayList<HashMap>();
		for (PshowOrdeDetails pds : entityList) {
			map = new HashMap();
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");// 订单号
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量

			map.put("fh_dept_name", fh_dept_name);
			map.put("dh_dept_name", dh_dept_name);
			map.put("add_date", add_date);
			map.put("trade_index", trade_index);
			map.put("pd_sn", pd_sn);
			map.put("total_price", total_price);
			map.put("rebates", rebates);
			map.put("rebates_status", pds.getRebates_status());
			map.put("num", num);
			map.put("integral", pds.getIntegral());
			map.put("pay_integral", pds.getPay_integral());
			map.put("order_from", pds.getMap().get("order_from"));
			map.put("department", pds.getMap().get("department"));

			map.put("user_name", pds.getEcUser().getUser_name());// 用户名
			map.put("is_own", pds.getEcUser().getIs_own());// 是否是本人银行账号
			map.put("real_name", pds.getEcUser().getReal_name());// 真实姓名
			map.put("card_no", pds.getEcUser().getCard_no());// 工卡号
			map.put("bank_name", pds.getEcUser().getBank_name());// 开户银行
			map.put("bank_account_name", pds.getEcUser().getBank_account_name());// 开户名
			map.put("bank_account", pds.getEcUser().getBank_account()); // 银行账号
			map.put("rebates_date", pds.getRebates_date());

			list.add(map);
		}
		request.setAttribute("entityList", list);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String year = (String) dynaBean.get("year");
		String month_start = (String) dynaBean.get("month_start");
		String month_end = (String) dynaBean.get("month_end");
		String rebates_status = (String) dynaBean.get("rebates_status");
		String dept_id = (String) dynaBean.get("dept_id");
		String order_from = (String) dynaBean.get("order_from");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String card_no_like = (String) dynaBean.get("card_no_like");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if ( peRoleUser.getRole_id() < 30L ||peRoleUser.getRole_id().intValue()==140317 ||peRoleUser.getRole_id().intValue()==1001 ) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 月份区间
		if (StringUtils.isBlank(month_start)) {
			month_start = "1";
		}
		if (StringUtils.isBlank(month_end)) {
			month_end = "12";
		}
		int month_start_num = Integer.valueOf(month_start);
		int month_end_num = Integer.valueOf(month_end);
		int start_num = 0;
		int end_num = 0;
		if (month_start_num < month_end_num) {
			start_num = month_start_num;
			end_num = month_end_num;
		} else {
			start_num = month_end_num;
			end_num = month_end_num;
		}
		PshowOrdeDetails entity = new PshowOrdeDetails();
		String m_s = year + "-";
		String m_e = year + "-";
		if (start_num < 10) {
			m_s = m_s + "0" + start_num;
		} else {
			m_s = m_s + start_num;
		}
		if (end_num < 10) {
			m_e = m_e + "0" + end_num;
		} else {
			m_e = m_e + end_num;
		}
		entity.getMap().put("start_date", m_s);
		entity.getMap().put("end_date", m_e);

		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		if (role_id_gt_30_lt_60) {
			entity.getMap().put("fh_fgs_dept_id", user.getDept_id());
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("fh_fgs_dept_id", Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(order_from)) {
			entity.getMap().put("order_from", order_from);
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}
		entity.getMap().put("state_in", new Integer[] { 60 });
		entity.getMap().put("rebates", "1");
		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);

		// 工号销售结算明细总表
		// 发货分公司 收货人所在分公司 订单号 产品型号 销售收入（销售总价格） 数量 现款价（成本价） 毛利 税金（增值税金=毛利*17%）
		// 物流（接口导入） 支付费用（支付宝，民生银行） 佣金 运营费用 应划拨利润
		// 销售毛利=收入（销售总额）-成本
		// 增值税金=销售毛利*17%
		// 运营费用=（毛利-税金-物流费-支付费用-佣金）*1%
		// 应划拨利润=毛利-税金-物流费-支付费用-佣金-运营费用
		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "员工姓名");
		e.setCell(2, "工卡号");
		e.setCell(3, "是否是本人银行账号");
		e.setCell(4, "开户银行");
		e.setCell(5, "银行账号");
		e.setCell(6, "银行户名");
		e.setCell(7, "交易流水号");
		e.setCell(8, "下单日期");
		e.setCell(9, "产品型号");
		e.setCell(10, "销售价格");
		e.setCell(11, "数量");
		e.setCell(12, "佣金");
		e.setCell(13, "发货分公司 ");
		e.setCell(14, "到货分公司");
		e.setCell(15, "订单来源");
		e.setCell(16, "佣金发放状态");		
		e.setCell(17, "部门");
		e.setCell(18, "佣金发放时间");
		for (PshowOrdeDetails pds : entityList) {
			r++;
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");// 订单号
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量

			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getEcUser().getReal_name());
			e.setCell(2, pds.getEcUser().getCard_no());
			if (pds.getEcUser().getIs_own() != null) {
				if (pds.getEcUser().getIs_own().intValue() == 1) {
					e.setCell(3, "是");
				} else {
					e.setCell(3, "否");
				}
			} else {
				e.setCell(3, "");
			}
			e.setCell(4, pds.getEcUser().getBank_name());
			e.setCell(5, pds.getEcUser().getBank_account());
			e.setCell(6, pds.getEcUser().getBank_account_name());
			e.setCell(7, trade_index);
			Calendar add_datecal = Calendar.getInstance();
			add_datecal.setTime(add_date);
			e.setCell(8, add_datecal);
			e.setCell(9, pd_sn);
			e.setCell(10, total_price.toString());
			e.setCell(11, num);
			e.setCell(12, rebates.toString());
			e.setCell(13, fh_dept_name);
			e.setCell(14, dh_dept_name);
			String orderfrom = pds.getMap().get("order_from").toString();
			if ("1".equals(orderfrom)) {
				e.setCell(15, "工卡");
			} else if ("2".equals(orderfrom)) {
				e.setCell(15, "触网");
			} else if ("3".equals(orderfrom)) {
				e.setCell(15, "其他");
			}
			if (pds.getRebates_status() != null) {
				String rebates_name = "";
				if (pds.getRebates_status().intValue() == 0) {
					rebates_name = "未确认";
				} else if (pds.getRebates_status().intValue() == 1) {
					rebates_name = "提现 已发放";
				} else if (pds.getRebates_status().intValue() == 2) {
					rebates_name = "提现 未发放";
				} else if (pds.getRebates_status().intValue() == 3) {
					rebates_name = "已兑换积分";
				} else if (pds.getRebates_status().intValue() == 4) {
					rebates_name = "已抵扣货款";
				}
				e.setCell(16, rebates_name);
				//部门
				String department="";
				if (null!=pds.getMap().get("department")) {
					department=String.valueOf(pds.getMap().get("department"));
				}
				e.setCell(17, department);
				//佣金发放时间
				if (null!=pds.getRebates_date()) {
					e.setCell(18, new SimpleDateFormat("yyyy-MM-dd").format(pds.getRebates_date()));
				} else {
					e.setCell(18, "");
				}
			}
		}
		// 输出
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("购机佣金提成表")
				+ ".xls");
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	public ActionForward rebates(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String msg = "";
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			msg = super.getMessage(request, "popedom.check.invalid");
		} else {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			if (null == user) {
				msg = "登录超时";
			}
			DynaBean dynaBean = (DynaBean) form;
			String id = (String) dynaBean.get("id");
			String[] pks = request.getParameterValues("pks");
			if (id != null && !"".equals(id)) {
				PshowOrdeDetails entity = new PshowOrdeDetails();
				entity.setBill_item_id(new Long(id));
				entity.setRebates_sender(user.getReal_name());
				entity.setRebates_date(new Date());
				entity.setRebates_status(new Integer(0));
				int i = super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetails(entity);
			} else if (pks != null && pks.length > 0) {
				PshowOrdeDetails entity = new PshowOrdeDetails();
				entity.setRebates_status(new Integer(0));
				entity.setRebates_date(new Date());
				entity.getMap().put("pks", pks);
				int i = super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetails(entity);
			}

		}
		super.renderText(response, msg);
		return null;
	}
}
