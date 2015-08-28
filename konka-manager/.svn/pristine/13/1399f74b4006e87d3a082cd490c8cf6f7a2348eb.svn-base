package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.math.BigDecimal;
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
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-28
 */
public class TouchJieSuanRebatesAction extends BaseAction {

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
		String order_from = "2";// (String) dynaBean.get("order_from");
		String xd_dept_id = (String) dynaBean.get("xd_dept_id");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String pay_way_search = (String) dynaBean.get("pay_way_search");
		String r3_code = (String) dynaBean.get("r3_code");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String touch_type = (String) dynaBean.get("touch_type");
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
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue() == 140317
			        || peRoleUser.getRole_id().intValue() == 1001) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
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
			entity.getMap().put("xd_dept_id_new", user.getDept_id());

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(user.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				entity.getMap().put("is_fgs", ids);

			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
		}
		if (StringUtils.isNotBlank(xd_dept_id)) {

			entity.getMap().put("xd_dept_id_new", Long.valueOf(xd_dept_id));

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(Long.valueOf(xd_dept_id));
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				entity.getMap().put("is_fgs", ids);

			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
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
		if (StringUtils.isNotBlank(pay_way_search)) {
			entity.getMap().put("pay_way", Long.valueOf(pay_way_search));
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(touch_type)) {
			if (touch_type.equals("2")) {
				entity.getMap().put("pay_way", "9");
				dynaBean.set("touch_type", "2");
			} else if (touch_type.equals("1")) {
				entity.getMap().put("pay_way_not_eq", "9");
				dynaBean.set("touch_type", "1");
			}
		}
		entity.getMap().put("state_in", new Integer[] { 60 });
		entity.getMap().put("rebates", "1");

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);
		HashMap map = new HashMap();
		List<HashMap> list = new ArrayList<HashMap>();
		for (PshowOrdeDetails pds : entityList) {
			map = new HashMap();
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司

			String xd_dept_name = "";// 下单分公司

			PshowOrder pp1 = new PshowOrder();
			pp1.setId(pds.getOrder_id());
			pp1 = super.getFacade().getPshowOrderService().getPshowOrder(pp1);

			if (pp1.getOrder_user_id().intValue() == 128008 || (pp1.getAdd_date().getTime() < 1421683200000L)) {
				EcUser ec = new EcUser();
				ec.setId(pp1.getOrder_user_id());
				ec = super.getFacade().getEcUserService().getEcUser(ec);

				KonkaDept kd = new KonkaDept();
				kd.setDept_id(ec.getDept_id());
				kd.setDept_type(3);
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				if (kd != null) {
					xd_dept_name = kd.getDept_name();
				}

			} else {
				EcUser eu = new EcUser();
				eu.setId(pp1.getOrder_user_id());
				eu = super.getFacade().getEcUserService().getEcUser(eu);

				EcGroup ee = new EcGroup();
				ee.setId(eu.getDept_id());
				ee = super.getFacade().getEcGroupService().getEcGroup(ee);
				if (ee != null && ee.getLink_dept_id() != null) {
					KonkaDept kk = new KonkaDept();
					kk.setDept_id(ee.getLink_dept_id());
					kk = super.getFacade().getKonkaDeptService().getKonkaDept(kk);
					if (kk != null) {
						xd_dept_name = kk.getDept_name();
					}
				}

			}

			String user_r3_code = (String) pds.getEcUser().getMap().get("r3_code");// 下单分公司
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");// 订单号
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量
			BigDecimal p = (BigDecimal) pds.getMap().get("pay_way");
			Integer pay_way = p == null ? null : p.intValue();// 支付方式0：货到付款1：在线支付

			map.put("fh_dept_name", fh_dept_name);
			map.put("dh_dept_name", dh_dept_name);
			map.put("xd_dept_name", xd_dept_name);
			map.put("user_r3_code", user_r3_code);
			map.put("add_date", add_date);
			map.put("trade_index", trade_index);
			map.put("pd_sn", pd_sn);
			map.put("total_price", total_price);
			map.put("rebates", rebates);
			map.put("rebates_status", pds.getRebates_status());
			map.put("num", num);
			map.put("integral", pds.getIntegral());
			map.put("order_from", pds.getMap().get("order_from"));
			map.put("pay_way", pay_way);

			map.put("user_name", pds.getEcUser().getUser_name());// 用户名
			map.put("is_own", pds.getEcUser().getIs_own());// 是否是本人银行账号
			map.put("real_name", pds.getEcUser().getReal_name());// 真实姓名
			map.put("card_no", pds.getEcUser().getCard_no());// 工卡号
			map.put("bank_name", pds.getEcUser().getBank_name());// 开户银行
			map.put("bank_account_name", pds.getEcUser().getBank_account_name());// 开户名
			map.put("bank_account", pds.getEcUser().getBank_account()); // 银行账号

			list.add(map);
		}
		request.setAttribute("entityList", list);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		KonkaDept sysdept = new KonkaDept();
		sysdept.setDept_type(new Integer(3));
		sysdept.getMap().put("order_by_dept_name", true);
		request.setAttribute("deptInfoList", getFacade().getKonkaDeptService().getKonkaDeptList(sysdept));
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
		String order_from = "2";// (String) dynaBean.get("order_from");
		String xd_dept_id = (String) dynaBean.get("xd_dept_id");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String pay_way_search = (String) dynaBean.get("pay_way_search");
		String r3_code = (String) dynaBean.get("r3_code");
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
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
				role_id_gt_30_lt_60 = true;
			}
		}
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
		if (role_id_gt_30_lt_60) {

			entity.getMap().put("xd_dept_id_new", user.getDept_id());

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(user.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				entity.getMap().put("is_fgs", ids);

			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
		}
		if (StringUtils.isNotBlank(xd_dept_id)) {
			entity.getMap().put("xd_dept_id_new", Long.valueOf(xd_dept_id));

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(Long.valueOf(xd_dept_id));
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				entity.getMap().put("is_fgs", ids);

			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}
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
		if (StringUtils.isNotBlank(pay_way_search)) {
			entity.getMap().put("pay_way", Long.valueOf(pay_way_search));
		}
		if (!"".equals(rebates_status) && rebates_status != null) {
			entity.setRebates_status(new Integer(rebates_status));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		entity.getMap().put("state_in", new Integer[] { 60 });
		entity.getMap().put("rebates", "1");

		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(entity);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "员工姓名");
		e.setCell(2, "是否是本人银行账号");
		e.setCell(3, "开户银行");
		e.setCell(4, "银行账号");
		e.setCell(5, "银行户名");
		e.setCell(6, "交易流水号");
		e.setCell(7, "下单日期");
		e.setCell(8, "产品型号");
		e.setCell(9, "销售价格");
		e.setCell(10, "数量");
		e.setCell(11, "佣金");
		e.setCell(12, "支付方式");
		e.setCell(13, "发货分公司 ");
		e.setCell(14, "到货分公司");
		e.setCell(15, "下单分公司");
		e.setCell(16, "R3_CODE");
		e.setCell(17, "订单来源");
		e.setCell(18, "佣金发放状态");
		for (PshowOrdeDetails pds : entityList) {
			r++;
			String fh_dept_name = (String) pds.getMap().get("fh_dept_name");// 发货分公司
			String dh_dept_name = (String) pds.getMap().get("dh_dept_name");// 收货人所在分公司

			String xd_dept_name = "";// 下单分公司

			PshowOrder pp1 = new PshowOrder();
			pp1.setId(pds.getOrder_id());
			pp1 = super.getFacade().getPshowOrderService().getPshowOrder(pp1);

			if (pp1.getOrder_user_id().intValue() == 128008 || (pp1.getAdd_date().getTime() < 1421683200000L)) {
				EcUser ec = new EcUser();
				ec.setId(pp1.getOrder_user_id());
				ec = super.getFacade().getEcUserService().getEcUser(ec);

				KonkaDept kd = new KonkaDept();
				kd.setDept_id(ec.getDept_id());
				kd.setDept_type(3);
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				if (kd != null) {
					xd_dept_name = kd.getDept_name();
				}

			} else {
				EcUser eu = new EcUser();
				eu.setId(pp1.getOrder_user_id());
				eu = super.getFacade().getEcUserService().getEcUser(eu);

				EcGroup ee = new EcGroup();
				ee.setId(eu.getDept_id());
				ee = super.getFacade().getEcGroupService().getEcGroup(ee);
				if (ee != null && ee.getLink_dept_id() != null) {
					KonkaDept kk = new KonkaDept();
					kk.setDept_id(ee.getLink_dept_id());
					kk = super.getFacade().getKonkaDeptService().getKonkaDept(kk);
					if (kk != null) {
						xd_dept_name = kk.getDept_name();
					}
				}

			}

			String user_r3_code = (String) pds.getEcUser().getMap().get("r3_code");// 下单分公司
			String trade_index = (String) pds.getMap().get("trade_index");// 订单号
			Date add_date = (Date) pds.getMap().get("add_date");
			String pd_sn = (String) pds.getMap().get("pd_sn");// 产品型号
			BigDecimal total_price = pds.getTotal_price();// 销售收入（销售总价格）
			BigDecimal rebates = pds.getRebates() == null ? new BigDecimal(0.0) : pds.getRebates();// 佣金
			Long num = pds.getNum(); // 数量
			BigDecimal p = (BigDecimal) pds.getMap().get("pay_way");
			Integer pay_way = p == null ? null : p.intValue();
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getEcUser().getReal_name());
			if (pds.getEcUser().getIs_own() != null) {
				if (pds.getEcUser().getIs_own().intValue() == 1) {
					e.setCell(2, "是");
				} else {
					e.setCell(2, "否");
				}
			} else {
				e.setCell(2, "");
			}
			e.setCell(3, pds.getEcUser().getBank_name());
			e.setCell(4, pds.getEcUser().getBank_account());
			e.setCell(5, pds.getEcUser().getBank_account_name());
			e.setCell(6, trade_index);
			Calendar add_datecal = Calendar.getInstance();
			add_datecal.setTime(add_date);
			e.setCell(7, add_datecal);
			e.setCell(8, pd_sn);
			e.setCell(9, total_price.toString());
			e.setCell(10, num);
			e.setCell(11, rebates.toString());
			String pay_way_name = "";
			if (pay_way.intValue() == 0) {
				pay_way_name = "货到付款";
			} else if (pay_way.intValue() == 1) {
				pay_way_name = "银行汇款";
			} else if (pay_way.intValue() == 2) {
				pay_way_name = "支付宝";
			} else if (pay_way.intValue() == 3) {
				pay_way_name = "银联";
			} else if (pay_way.intValue() == 4) {
				pay_way_name = "财付通";
			} else if (pay_way.intValue() == 5) {
				pay_way_name = "民生银行";
			} else if (pay_way.intValue() == 9) {
				pay_way_name = "货线下处理";
			}
			e.setCell(12, pay_way_name);
			e.setCell(13, fh_dept_name);
			e.setCell(14, dh_dept_name);
			e.setCell(15, xd_dept_name);
			e.setCell(16, user_r3_code);
			String orderfrom = pds.getMap().get("order_from").toString();
			if ("1".equals(orderfrom)) {
				e.setCell(17, "工卡");
			} else if ("2".equals(orderfrom)) {
				if (pay_way.intValue() == 9) {
					e.setCell(17, "触网2");
				} else {
					e.setCell(17, "触网1");
				}
			} else if ("3".equals(orderfrom)) {
				e.setCell(17, "其他");
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
				e.setCell(18, rebates_name);
			}
		}
		// 输出
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("触网佣金表")
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

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// String id = (String) dynaBean.get("id");
		String trade_index = (String) dynaBean.get("trade_index");

		PshowOrder entity = new PshowOrder();
		// entity.setId(Long.valueOf(id));
		entity.setTrade_index(trade_index);
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		EcOrderExpressInfo ee = new EcOrderExpressInfo();
		ee.setTrade_index(entity.getTrade_index());
		ee = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);

		if (null != ee) {
			dynaBean.set("order_state", "1");
		} else {
			dynaBean.set("order_state", "0");
			if (entity.getState() == 40 || entity.getState() == 50 || entity.getState() == 60) {
				if (null != entity.getExpress_id()) {
					EcBaseExpress ec = new EcBaseExpress();
					ec.setExpress_id(entity.getExpress_id());
					ec = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ec);
					if (ec.getExpress_ui_type() == 1) {
						dynaBean.set("is_add", true);
					}
				}
			}
		}

		if (null != ee) {
			dynaBean.set("logistic_sn", ee.getLogistic_sn());
		} else {
			dynaBean.set("logistic_sn", "暂无运单号");
		}

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			request.setAttribute("p_index_name", super.getPIndexName(entity.getBuyer_p_index().toString(), ""));
		}

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(entity.getId());
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeForPDSNDetailsList(psd);

		// 取产品的增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
		}
		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(entity.getId());
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);
		// 子订单查询
		PshowOrder zOrder = new PshowOrder();
		zOrder.setPar_order_id(entity.getId());
		List<PshowOrder> zOrderlist = super.getFacade().getPshowOrderService().getPshowOrderIncludeDetailsList(zOrder);
		request.setAttribute("zOrderlist", zOrderlist);

		return mapping.findForward("view");
	}

	@SuppressWarnings("unchecked")
	public ActionForward sheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Id" });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		EcOrderExpressInfo ee = new EcOrderExpressInfo();
		ee.setTrade_index(entity.getTrade_index());
		ee = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);

		if (null != ee) {
			dynaBean.set("order_state", "1");
		} else {
			dynaBean.set("order_state", "0");
			if (entity.getState() == 40 || entity.getState() == 50 || entity.getState() == 60) {
				if (null != entity.getExpress_id()) {
					EcBaseExpress ec = new EcBaseExpress();
					ec.setExpress_id(entity.getExpress_id());
					ec = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ec);
					if (ec.getExpress_ui_type() == 1) {
						dynaBean.set("is_add", true);
					}
				}
			}
		}

		if (null != ee) {
			dynaBean.set("logistic_sn", ee.getLogistic_sn());
		} else {
			dynaBean.set("logistic_sn", "暂无运单号");
		}

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			request.setAttribute("p_index_name", super.getPIndexName(entity.getBuyer_p_index().toString(), ""));
		}
		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeForPDSNDetailsList(psd);

		// 取产品的增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
		}
		PshowOrdeAudit psa = new PshowOrdeAudit();
		psa.setOrder_id(Long.valueOf(id));
		psa.getMap().put("orderByDate", true);
		List<PshowOrdeAudit> PshowOrdeAudits = super.getFacade().getPshowOrdeAuditService().getPshowOrdeAuditList(psa);

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		request.setAttribute("PshowOrdeAudits", PshowOrdeAudits);
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/spgl/TouchJieSuanRebates/sheet.jsp");
	}
}
