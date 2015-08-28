package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcSelfArea;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.domain.SfhkOutStorage;
import com.ebiz.mmt.domain.SfhkRelEppOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.Order;
import com.sf.integration.warehouse.OrderCx;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class PshowOrderLookAction extends BasePshowOrderAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(calendar.getTime());
		dynaBean.set("add_time_start", day_first);

		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		String day_last = df.format(calendar.getTime());
		dynaBean.set("add_time_end", day_last);

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String trade_no_like = (String) dynaBean.get("trade_no_like");
		String order_from = (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");
		String tuihuo = (String) dynaBean.get("tuihuo");
		String order_type = (String) dynaBean.get("order_type");
		String card_no_like = (String) dynaBean.get("card_no_like");
		String prod_type = (String) dynaBean.get("prod_type");
		String is_self = (String) dynaBean.get("is_self");
		String deliver_way = (String) dynaBean.get("deliver_way");
		String logistic_sn_like = (String) dynaBean.get("logistic_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String sf_order_id_like = (String) dynaBean.get("sf_order_id_like");

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
				request.setAttribute("is_admin", "0");
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}

		PshowOrder entity = new PshowOrder();
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		if (role_id_eq_30) {
			entity.setOpr_dept_id(null);
		} else if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		} else {
			entity.setOpr_dept_id(user.getDept_id());
		}

		// 总部和分公司之外的不能查看
		// if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
		// String msg = super.getMessage(request, "konka.r3.roleError");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(logistic_sn_like)) {
			entity.getMap().put("logistic_sn_like", logistic_sn_like);
		}
		if (StringUtils.isNotBlank(order_user_name_like)) {
			entity.getMap().put("order_user_name_like", order_user_name_like);
		}
		if (StringUtils.isNotBlank(prod_type)) {
			entity.getMap().put("prod_type", prod_type);
			dynaBean.set("prod_type", prod_type);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(deliver_way)) {
			entity.setDeliver_way(Integer.valueOf(deliver_way));
		}

		if (StringUtils.isNotBlank(is_self)) {
			entity.setIs_self(Integer.valueOf(is_self));
		}
		if (StringUtils.isNotBlank(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(sf_order_id_like)) {
			entity.getMap().put("sf_order_id_like", sf_order_id_like);
		}

		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(tuihuo)) {
			if (tuihuo.equals("0")) {// 换货
				entity.getMap().put("huanhuo", true);
				dynaBean.set("tuihuo", "0");
			} else if (tuihuo.equals("1")) {// 退货
				entity.getMap().put("tuihuo", true);
				dynaBean.set("tuihuo", "1");
			}
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			if (order_from.equals("3")) {
				entity.setOrder_from(2);
				entity.getMap().put("sf_order", true);
				dynaBean.set("order_from", "3");
			} else if (order_from.equals("2")) {
				entity.setOrder_from(2);
				entity.getMap().put("cw_order", true);
				dynaBean.set("order_from", "2");
			} else {
				entity.setOrder_from(Integer.valueOf(order_from));
			}
		}
		if (StringUtils.isBlank(state)) {
			entity.getMap().put("gb_order", true);// 不显示关闭订单
		}

		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService()
				.getPshowOrdeForDeptNameAndFullNameList(entity);

		if (null != entityList && entityList.size() > 0) {
			for (PshowOrder pp : entityList) {
				int t_num = 0;
				EcOrderExpressInfo ec = new EcOrderExpressInfo();
				ec.setTrade_index(pp.getTrade_index());
				ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
				if (null != ec) {
					pp.getMap().put("in_sf", true);
				}
				PshowOrdeDetails psd = new PshowOrdeDetails();
				psd.setOrder_id(Long.valueOf(pp.getId()));
				List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
						.getPshowOrdeForPDSNDetailsList(psd);

				pp.setPshowOrdeDetailsList(pshowordedetails);

				String pd_fuwu = "";
				if (null != pshowordedetails && pshowordedetails.size() > 0) {
					for (PshowOrdeDetails ps : pshowordedetails) {
						t_num += ps.getNum();
						EcBindingPdOrdeDetails ppd = new EcBindingPdOrdeDetails();
						ppd.setDetails_id(ps.getBill_item_id());
						List<EcBindingPdOrdeDetails> ppdList = super.getFacade().getEcBindingPdOrdeDetailsService()
								.getEcBindingPdOrdeDetailsList(ppd);
						if (null != ppdList && ppdList.size() > 0) {
							for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ppdList) {
								pd_fuwu = pd_fuwu + ecBindingPdOrdeDetails.getGoods_name() + "*"
										+ ecBindingPdOrdeDetails.getNum() + "=" + ecBindingPdOrdeDetails.getPrice()
										+ ",";
							}
						}
					}
				}
				pp.getMap().put("total_num", t_num);
				pp.getMap().put("pd_fuwu", pd_fuwu);

				EcUser ee = new EcUser();
				ee.setId(pp.getOrder_user_id());
				ee = super.getFacade().getEcUserService().getEcUser(ee);
				if (null != ee && null != ee.getDept_id()) {
					if (ee.getDept_id().intValue() == 2110) {
						pp.getMap().put("is_sf", true);
					}
				}

				SfhkOutStorage sf = new SfhkOutStorage();
				sf.setEpp_order_id(pp.getId().toString());
				sf = super.getFacade().getSfhkOutStorageService().getSfhkOutStorage(sf);
				if (sf == null) {
					pp.getMap().put("is_out", 1);
				} else if (sf != null && sf.getState().intValue() == 1) {
					pp.getMap().put("is_out", 1);
				} else if (sf != null && sf.getState().intValue() == 0) {
					pp.getMap().put("is_out", 0);
				}

			}

		}
		/*
		 * int total_num = 0; BigDecimal total_p = new BigDecimal("0.00"); entity.getRow().setFirst(0);
		 * entity.getRow().setCount(recordCount.intValue()); List<PshowOrder> entityList2
		 * =super.getFacade().getPshowOrderService(). getPshowOrdeForDeptNameAndFullNameList( entity); if (null !=
		 * entityList2 && entityList2.size() > 0) { for (PshowOrder pp : entityList2) { int t_num = 0; PshowOrdeDetails
		 * psd = new PshowOrdeDetails(); psd.setOrder_id(Long.valueOf(pp.getId())); List<PshowOrdeDetails>
		 * pshowordedetails = super.getFacade().getPshowOrdeDetailsService() .getPshowOrdeForPDSNDetailsList(psd);
		 * pp.setPshowOrdeDetailsList(pshowordedetails); if (null != pshowordedetails && pshowordedetails.size() > 0) {
		 * for (PshowOrdeDetails ps : pshowordedetails) { t_num += ps.getNum(); } } total_num = total_num + t_num; if
		 * (pp.getPay_price() != null) { total_p = total_p.add(pp.getPay_price()); } } }
		 * request.setAttribute("totolCount", recordCount); request.setAttribute("total_num", total_num);
		 * request.setAttribute("total_p", total_p);
		 */

		List<PshowOrder> entityList2 = super.getFacade().getPshowOrderService().getPshowOrderAndDetailsForTj2(entity);
		if (null != entityList2 && entityList2.size() > 0) {
			request.setAttribute("totolCount", recordCount);
			request.setAttribute("total_num", entityList2.get(0).getMap().get("sale_num"));
			request.setAttribute("total_p", entityList2.get(0).getMap().get("sale_price"));
		}

		dynaBean.set("user_id", userInfo.getId());
		dynaBean.set("sync_pwd", "753");
		request.setAttribute("entityList", entityList);
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String deliver_way = (String) dynaBean.get("deliver_way");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String trade_no_like = (String) dynaBean.get("trade_no_like");
		String order_from = (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");
		String is_self = (String) dynaBean.get("is_self");
		String tuihuo = (String) dynaBean.get("tuihuo");
		String order_type = (String) dynaBean.get("order_type");
		String card_no_like = (String) dynaBean.get("card_no_like");
		String prod_type = (String) dynaBean.get("prod_type");
		String logistic_sn_like = (String) dynaBean.get("logistic_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String sf_order_id_like = (String) dynaBean.get("sf_order_id_like");

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
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		logger.info("++++++++++++++" + role_id_gt_30_lt_60);

		PshowOrder entity = new PshowOrder();
		// 总部可以查看所有订单
		// 分公司只能看到分公司的订单
		if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}
		if (role_id_eq_30) {
			entity.setOpr_dept_id(null);
		} else if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		} else {
			entity.setOpr_dept_id(user.getDept_id());
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(sf_order_id_like)) {
			entity.getMap().put("sf_order_id_like", sf_order_id_like);
		}
		if (StringUtils.isNotBlank(logistic_sn_like)) {
			entity.getMap().put("logistic_sn_like", logistic_sn_like);
		}
		if (StringUtils.isNotBlank(order_user_name_like)) {
			entity.getMap().put("order_user_name_like", order_user_name_like);
		}
		if (StringUtils.isNotBlank(prod_type)) {
			entity.getMap().put("prod_type", prod_type);
			dynaBean.set("prod_type", prod_type);
		}
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(card_no_like)) {
			entity.getMap().put("card_no_like", card_no_like);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(deliver_way)) {
			entity.setDeliver_way(Integer.valueOf(deliver_way));
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			if (order_from.equals("3")) {
				entity.setOrder_from(2);
				entity.getMap().put("sf_order", true);
				dynaBean.set("order_from", "3");
			} else if (order_from.equals("2")) {
				entity.setOrder_from(2);
				entity.getMap().put("cw_order", true);
				dynaBean.set("order_from", "2");
			} else {
				entity.setOrder_from(Integer.valueOf(order_from));
			}
		}
		if (StringUtils.isNotBlank(is_self)) {
			entity.setIs_self(Integer.valueOf(is_self));
		}
		if (StringUtils.isNotBlank(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (StringUtils.isNotBlank(tuihuo)) {
			if (tuihuo.equals("0")) {// 换货
				entity.getMap().put("huanhuo", true);
				dynaBean.set("tuihuo", "0");
			} else if (tuihuo.equals("1")) {// 退货
				entity.getMap().put("tuihuo", true);
				dynaBean.set("tuihuo", "1");
			}
		}

		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isBlank(state)) {
			entity.getMap().put("gb_order", true);// 不显示关闭订单
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		// entity.getMap().put("state_in_2", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);

		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrder> entityList = super.getFacade().getPshowOrderService()
				.getPshowOrdeForDeptNameAndFullNameList(entity);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "交易流水号");
		e.setCell(2, "订单状态");
		e.setCell(3, "当前处理的部门");
		e.setCell(4, "订单类型");
		e.setCell(5, "工卡号");
		e.setCell(6, "下单人姓名");
		e.setCell(7, "部门");
		e.setCell(8, "购买人姓名");
		e.setCell(9, "购买人地区");
		e.setCell(10, "支付单号");
		e.setCell(11, "购买人手机号码");
		e.setCell(12, "支付方式");
		e.setCell(13, "物流费用");
		e.setCell(14, "运单号");
		e.setCell(15, "订单总金额");
		e.setCell(16, "发货分公司");
		e.setCell(17, "二次配货");
		e.setCell(18, "商品型号");
		e.setCell(19, "数量");
		// e.setCell(18, "详细地址");
		e.setCell(20, "下单时间");
		e.setCell(21, "是否索要发票");
		e.setCell(22, "发票类型");
		e.setCell(23, "发票抬头");
		e.setCell(24, "发票内容");
		e.setCell(25, "发票单位");
		e.setCell(26, "订单备注");
		e.setCell(27, "客户备注");
		e.setCell(28, "嘿客订单号");
		for (PshowOrder pds : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getTrade_index());
			String s_state = "";
			if (pds.getState() == -30) {
				s_state = "已退货";
			} else if (pds.getState() == -20) {
				s_state = "审核未通过";
			} else if (pds.getState() == -10) {
				s_state = "已关闭";
			} else if (pds.getState() == 0) {
				s_state = "已预订";
			} else if (pds.getState() == 5) {
				s_state = "待确认";
			} else if (pds.getState() == 10) {
				s_state = "已确认";
			} else if (pds.getState() == 20) {
				s_state = "审核通过";
			} else if (pds.getState() == 30) {
				s_state = "下发处理";
			} else if (pds.getState() == 40) {
				s_state = "商家发货";
			} else if (pds.getState() == 50) {
				s_state = "客户已换货";
			} else if (pds.getState() == 60) {
				s_state = "确认收货";
			}
			e.setCell(2, s_state);
			e.setCell(3, (String) pds.getMap().get("dept_name"));

			EcUser ecUser = new EcUser();
			ecUser.setId(pds.getOrder_user_id());
			ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);
			if (null != ecUser) {
				if ("1".equals(pds.getOrder_from().toString())) {
					e.setCell(4, "工卡");
				} else if ("2".equals(pds.getOrder_from().toString()) && ecUser.getDept_id() != null
						&& ecUser.getDept_id() == 2110) {
					e.setCell(4, "顺丰");
				} else if ("2".equals(pds.getOrder_from().toString()) && ecUser.getDept_id() != null
						&& ecUser.getDept_id() != 2110) {
					e.setCell(4, "触网");
				} else if ("3".equals(pds.getOrder_from().toString())) {
					e.setCell(4, "其他");
				} else if ("5".equals(pds.getOrder_from().toString())) {
					e.setCell(4, "会员");
				}
			}
			e.setCell(5, ecUser.getCard_no());
			e.setCell(6, pds.getOrder_user_name());
			e.setCell(7, ecUser.getDepartment());
			e.setCell(8, pds.getBuyer_name());
			e.setCell(9, (String) pds.getMap().get("full_name") + pds.getBuyer_addr());
			e.setCell(10, pds.getTrade_no());
			e.setCell(11, pds.getBuyer_mp());
			if ("0".equals(pds.getPay_way().toString())) {
				e.setCell(12, "货到付款");
			} else if ("1".equals(pds.getPay_way().toString())) {
				e.setCell(12, "银行付款");
			} else if ("2".equals(pds.getPay_way().toString())) {
				e.setCell(12, "支付宝");
			} else if ("3".equals(pds.getPay_way().toString())) {
				e.setCell(12, "银联");
			} else if ("4".equals(pds.getPay_way().toString())) {
				e.setCell(12, "财付通");
			} else if ("5".equals(pds.getPay_way().toString())) {
				e.setCell(12, "民生银行");
			}

			BigDecimal logistic_price = (BigDecimal) pds.getMap().get("logistic_price");// 物流费用
			if (logistic_price == null) {
				logistic_price = new BigDecimal("0.0");
			}
			e.setCell(13, logistic_price.toString());
			e.setCell(14, (String) pds.getMap().get("log_sn"));
			BigDecimal pay_price = pds.getPay_price();
			if (pay_price == null) {
				pay_price = new BigDecimal("0.0");
			}
			e.setCell(15, pay_price.toString());
			e.setCell(16, (String) pds.getMap().get("dept_name"));
			String ph = "";
			if (pds.getIs_ps() == 0) {
				ph = "否";
			} else if (pds.getIs_ps() == 1) {
				ph = "是";
			}
			e.setCell(17, ph);
			String pd_sn = "";
			int num = 0;
			PshowOrdeDetails pd = new PshowOrdeDetails();
			pd.setOrder_id(pds.getId());
			List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(pd);
			if (null != pdList && pdList.size() > 0) {
				for (PshowOrdeDetails pshowOrdeDetails : pdList) {
					KonkaBcompPd kp = new KonkaBcompPd();
					kp.setId(pshowOrdeDetails.getPd_id());
					kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
					if (null != kp && null != kp.getPd_sn() && !"".equals(kp.getPd_sn())) {
						pd_sn = pd_sn + kp.getPd_sn() + ",";
					}
					num += pshowOrdeDetails.getNum();
				}
			}
			e.setCell(18, pd_sn);
			e.setCell(19, num);
			// e.setCell(18, pds.getBuyer_addr());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			e.setCell(20, sf.format(pds.getAdd_date()));
			if (null != pds.getBill_is_add()) {
				if (pds.getBill_is_add().intValue() == 0) {
					e.setCell(21, "否");
				} else if (pds.getBill_is_add().intValue() == 1) {
					e.setCell(21, "是");
				}
			}
			if (null != pds.getBill_type()) {
				if (pds.getBill_type().intValue() == 0) {
					e.setCell(22, "普通发票");
				} else if (pds.getBill_type().intValue() == 1) {
					e.setCell(22, "增值税发票");
				}
			}

			if (null != pds.getBill_head()) {
				if (pds.getBill_head().intValue() == 0) {
					e.setCell(23, "个人");
				} else if (pds.getBill_head().intValue() == 1) {
					e.setCell(23, "单位");
				}
			}

			if (null != pds.getBill_content()) {
				if (pds.getBill_content().intValue() == 0) {
					e.setCell(24, "明细");
				} else if (pds.getBill_content().intValue() == 1) {
					e.setCell(24, "办公用品");
				} else if (pds.getBill_content().intValue() == 2) {
					e.setCell(24, "电脑配件");
				} else if (pds.getBill_content().intValue() == 3) {
					e.setCell(24, "耗材");
				}
			}

			if (null != pds.getBill_company()) {
				e.setCell(25, pds.getBill_company());
			}
			if (null != pds.getRemark()) {
				e.setCell(26, pds.getRemark());
			}
			if (null != pds.getLogistic_sn()) {
				e.setCell(27, pds.getLogistic_sn());
			}

			SfhkRelEppOrder so = new SfhkRelEppOrder();
			so.setEpp_order_id(pds.getId().toString());
			so = super.getFacade().getSfhkRelEppOrderService().getSfhkRelEppOrder(so);
			if (so == null) {
				e.setCell(28, "");
			} else {
				e.setCell(28, so.getSf_order_id());
			}

		}
		// 输出
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("平台订单表")
				+ ".xls");
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Id" });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
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
				request.setAttribute("is_admin", "0");
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}

		dynaBean.set("user_id", user.getId());

		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 处理特殊字符显示乱码
		if (null != entity.getBuyer_addr() && !"".equals(entity.getBuyer_addr())) {
			String buyer_addr = entity.getBuyer_addr().replaceAll("&#40;", "（").replaceAll("&#41;", "）");
			;
			dynaBean.set("buyer_addr", buyer_addr);
		}

		EcOrderExpressInfo ee = new EcOrderExpressInfo();
		ee.setTrade_index(entity.getTrade_index());
		ee = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);

		if (null != ee) {
			// if (null != ee.getLogistic_content()) {
			// dynaBean.set("order_state", "2");
			// request.setAttribute("logistic_content",
			// ee.getLogistic_content());
			// } else {
			dynaBean.set("order_state", "1");
			// }
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

		dynaBean.set("cs_mark", entity.getLogistic_sn());

		SfhkRelEppOrder sf = new SfhkRelEppOrder();
		sf.setEpp_order_id(id);
		sf = super.getFacade().getSfhkRelEppOrderService().getSfhkRelEppOrder(sf);
		if (sf != null) {
			dynaBean.set("sfhk_order", sf.getSf_order_id());
		} else {
			dynaBean.set("sfhk_order", "");
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

		// 子订单查询
		PshowOrder zOrder = new PshowOrder();
		zOrder.setPar_order_id(new Long(id));
		List<PshowOrder> zOrderlist = super.getFacade().getPshowOrderService().getPshowOrderIncludeDetailsList(zOrder);
		request.setAttribute("zOrderlist", zOrderlist);

		// if (null != entity.getLogistic_sn() && null == entity.getWl_yf()) {
		// TestSfService sfService = new TestSfService();
		// String returnXml = sfService.xsddmx(entity.getTrade_index());
		// //System.out.println(returnXml);
		//
		// Document doc = DocumentHelper.parseText(returnXml);
		// Element rootElt = doc.getRootElement(); // 获取根节点
		// Iterator iter2 = rootElt.elementIterator("header");
		// while (iter2.hasNext()) {
		// Element order = (Element) iter2.next();
		// String waybill_no = order.elementTextTrim("waybill_no");
		// //System.out.println(waybill_no);
		// String orderFyXml = sfService.xsddfy(waybill_no);
		// //System.out.println("运单费用明细：" + orderFyXml);
		// Document doc1 = DocumentHelper.parseText(orderFyXml);
		// Element rootElt3 = doc1.getRootElement(); // 获取根节点
		// Iterator iter3 = rootElt3.elementIterator("orders");
		// while (iter3.hasNext()) {
		// Element order1 = (Element) iter3.next();
		// Iterator iter4 = order1.elementIterator("order");
		// while (iter4.hasNext()) {
		// Element order2 = (Element) iter4.next();
		// String freight = order2.elementTextTrim("freight");
		// //System.out.println(freight);
		// if (StringUtils.isNotBlank(freight)) {
		// PshowOrder pp = new PshowOrder();
		// pp.setId(entity.getId());
		// pp.setWl_yf(new BigDecimal(freight));
		// super.getFacade().getPshowOrderService().modifyPshowOrder(pp);
		// }
		// }
		// }
		// }
		// }

		return mapping.findForward("view");
	}

	public ActionForward lookSfState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);

		String ss = orderState(ec);
		super.render(response, ss, "text/x-json;charset=UTF-8");
		return null;
	}

	/**
	 * @param 批量更新订单的物流费用
	 */
	public ActionForward orderFy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ss = orderFy();
		super.renderText(response, ss);
		return null;
	}

	/**
	 * @param 批量更新订单在顺丰物流的状态
	 */
	public ActionForward updateorderState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		String ss = updateorderState(user);
		super.renderText(response, ss);
		return null;
	}

	public ActionForward sfList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
		String logistic_content = "";
		if (null != ec.getLogistic_sn() && ec.getLogistic_sn().startsWith("11")) {
			// 处理之前没有走顺丰接口的那些订单
			logistic_content = ec.getLogistic_content();
		} else {

			SfOrderService sf = new SfOrderService();
			String sxddmxOpName = "sfexpressService";
			MailnoQuery mq = new MailnoQuery();
			mq.setTracking_type("1");
			mq.setMethod_type("1");
			if (entity.getOpr_dept_id() != null && entity.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
				mq.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
			}
			mq.setTracking_number(ec.getLogistic_sn().trim());
			String xml2 = mq.toXml();
			//System.out.println("xml------------->" + xml2);
			String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
			//System.out.println("returnXml======>{}" + returnXml);

			if (null == ec.getLogistic_content() || ec.getLogistic_content().indexOf("已签收") == -1) {
				if (!"".equals(returnXml) && returnXml.indexOf("remark") != -1) {
					EcOrderExpressInfo ed = new EcOrderExpressInfo();
					ed.setId(ec.getId());
					ed.setLogistic_content(returnXml.replaceAll("&#x9", ""));
					super.getFacade().getEcOrderExpressInfoService().modifyEcOrderExpressInfo(ed);

					if (returnXml.indexOf("已签收") != -1 || ec.getLogistic_sn().indexOf("签收人是") != -1) {
						if (entity.getState().intValue() != 60 && entity.getState().intValue() != -10) {
							PshowOrder pp = new PshowOrder();
							pp.setId(Long.valueOf(id));
							pp.setState(60);
							super.getFacade().getPshowOrderService().modifyPshowOrder(pp);

							PshowOrdeAudit pa = new PshowOrdeAudit();
							pa.setOrder_id(Long.valueOf(id));
							pa.setOper_date(new Date());
							pa.setOpr_user_id(1L);
							pa.setOpr_user_real_name("超级管理员");
							pa.setState(60);
							pa.setRemark("系统自动更新");
							pa.setTotal_price(pp.getPay_price());
							super.getFacade().getPshowOrdeAuditService().createPshowOrdeAudit(pa);
						}
					}

				}
			} else if (ec.getLogistic_content().indexOf("已签收") != -1 || ec.getLogistic_content().indexOf("签收人是") != -1) {
				if (entity.getState() != 60 && entity.getState().intValue() != -10) {
					PshowOrder pp = new PshowOrder();
					pp.setId(Long.valueOf(id));
					pp.setState(60);
					super.getFacade().getPshowOrderService().modifyPshowOrder(pp);

					PshowOrdeAudit pa = new PshowOrdeAudit();
					pa.setOrder_id(Long.valueOf(id));
					pa.setOper_date(new Date());
					pa.setOpr_user_id(1L);
					pa.setOpr_user_real_name("超级管理员");
					pa.setState(60);
					pa.setRemark("系统自动更新");
					pa.setTotal_price(pp.getPay_price());
					super.getFacade().getPshowOrdeAuditService().createPshowOrdeAudit(pa);
				}
			}

			EcOrderExpressInfo ef = new EcOrderExpressInfo();
			ef.setId(ec.getId());
			ef = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ef);

			if (!"".equals(returnXml) && returnXml.indexOf("remark") != -1) {
				logistic_content = returnXml;
			} else {
				if (null != ef.getLogistic_content()) {
					logistic_content = ef.getLogistic_content();
				}
			}

		}
		// [["2014-01-19 11:54:55","已收件"],["2014-01-19 13:28:46","快件在东莞,准备送往下一站"]]
		List<HashMap> list = new ArrayList<HashMap>();
		try {
			if ("".equals(logistic_content)) {
				super.renderJson(response, "[{\"accept_time\":\"  \",\"remark\":\"顺丰接口查询超时 \"}]");
				return null;
			}
			// <?xml version='1.0' encoding='UTF-8'?><Response
			// service="RouteService"><Head>OK</Head><Body><RouteResponse
			// mailno="113740178251"><Route remark="已收件"
			// accept_time="2014-01-15 12:17:03" accept_address="东莞市"
			// opcode="50"/><Route remark="快件在东莞,准备送往下一站东莞集散中心"
			// accept_time="2014-01-15 13:23:21" accept_address="东莞市"
			// opcode="3036"/><Route remark="快件在东莞集散中心,准备送往下一站南昌集散中心"
			// accept_time="2014-01-16 00:10:19" accept_address="东莞市"
			// opcode="3036"/><Route remark="快件到达南昌集散中心"
			// accept_time="2014-01-16 15:50:53" accept_address="广州市"
			// opcode="3137"/><Route remark="快件在 鹰潭集散中心,准备送往下一站上饶"
			// accept_time="2014-01-17 13:19:14" accept_address="广州市"
			// opcode="3036"/><Route remark="快件到达上饶"
			// accept_time="2014-01-17 15:09:26" accept_address="天津市"
			// opcode="3137"/><Route remark="客户要求自取快件，待自取"
			// accept_time="2014-01-17 16:37:07" accept_address="天津市"
			// opcode="3036"/><Route remark="派件已签收"
			// accept_time="2014-01-17 18:33:42" accept_address="天津市"
			// opcode="3036"/><Route remark="签收人是：已签收"
			// accept_time="2014-01-17 18:33:59" accept_address="天津市"
			// opcode="44"/></RouteResponse></Body></Response>
			Document doc = DocumentHelper.parseText(logistic_content);
			Element rootElt = doc.getRootElement();
			Iterator<Element> iter = rootElt.elementIterator("Body");
			while (iter.hasNext()) {
				Element em = iter.next();
				Iterator<Element> iter2 = em.elementIterator("RouteResponse");
				while (iter2.hasNext()) {
					Element em2 = iter2.next();
					Iterator<Element> iter3 = em2.elementIterator("Route");
					while (iter3.hasNext()) {
						Element em3 = iter3.next();
						HashMap map = new HashMap();
						map.put("accept_time", em3.attributeValue("accept_time").trim());
						map.put("remark", em3.attributeValue("remark").replaceAll(" ", "").replaceAll(" ", "").trim());
						list.add(map);
					}
				}

			}
		} catch (Exception e) {
			// [{"accept_time":"2014-01-18 12:07:12","remark":"已收件"}
			super.renderJson(response, "[{\"accept_time\":\"  \",\"remark\":\"顺丰接口查询超时 \"}]");
			return null;
		}

		super.renderJson(response, JSON.toJSONString(list));
		//System.out.println("list----------->" + JSON.toJSONString(list));
		return null;
	}

	public ActionForward sfList2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
		String returnXml = "";

		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		MailnoQuery mq = new MailnoQuery();
		mq.setTracking_type("1");
		mq.setMethod_type("1");
		if (entity.getOpr_dept_id() != null && entity.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
			mq.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
		}
		mq.setTracking_number(ec.getLogistic_sn().trim());
		String xml2 = mq.toXml();
		//System.out.println("xml------------->" + xml2);
		returnXml = sf.sfWebService2(xml2, sxddmxOpName);
		//System.out.println("returnXml======>{}" + returnXml);

		super.renderText(response, returnXml);
		return null;
	}

	public ActionForward showPrint3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		dynaBean.set("id", id);

		PshowOrder p = new PshowOrder();
		p.setId(Long.valueOf(id));
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);
		if (null != p) {
			EcOrderExpressInfo ec = new EcOrderExpressInfo();
			ec.setTrade_index(p.getTrade_index());
			ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
			if (null == ec) {
				super.renderJavaScript(response, "window.onload=function(){alert('对不起！该订单目前还没有运单号！');history.back();}");
				return null;
			}
		}
		if (p.getOpr_dept_id().intValue() == 10) {
			dynaBean.set("sf_type", "标准快递");
		} else {
			dynaBean.set("sf_type", "顺丰特惠");
		}

		return new ActionForward("/spgl/PshowOrderLook/print3.jsp");
	}

	public ActionForward orderSend(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		PshowOrder p = new PshowOrder();
		p.setTrade_index(trade_index);
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(p.getId());
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				.getPshowOrdeForPDSNDetailsList(psd);

		int t_num = 0;
		String pd_name_num = "";// 机型*数量
		BigDecimal rebate = new BigDecimal("0.0");
		List<String> cargoList = new ArrayList<String>();// 货物名称
		List<String> cargo_count_List = new ArrayList<String>();// 货物数量
		List<String> cargo_unit_List = new ArrayList<String>();// 货物单位
		List<String> cargo_weight_List = new ArrayList<String>();// 货物重量
		List<String> cargo_amount_List = new ArrayList<String>();// 货物单价
		BigDecimal total_weight = new BigDecimal("0.00");
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();

			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(ps.getPd_id());
			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
			pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
			if (ps.getRebates() != null) {
				rebate = rebate.add(ps.getRebates());
			}

			BigDecimal weight = new BigDecimal("0.00");
			if (konkaBcompPd.getSj_weight() != null) {
				weight = konkaBcompPd.getSj_weight();
			}

			total_weight = total_weight.add(weight.multiply(new BigDecimal(ps.getNum())));

			cargoList.add(konkaBcompPd.getPd_sn());
			cargo_count_List.add(ps.getNum().toString());
			cargo_unit_List.add("台");
			cargo_weight_List.add(weight.toString());
			cargo_amount_List.add(ps.getPrice().toString());

		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		String ss = "";

		if (null != p) {

			// 取订单客户信息、下单人信息
			String kh_name = p.getBuyer_name();
			trade_index = p.getTrade_index();

			EcUser ecUser = new EcUser();
			ecUser.setId(p.getOrder_user_id());
			ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);

			String user_real_name = ecUser.getReal_name();
			String user_tel = ecUser.getLink_phone();

			EcBaseExpress ee = new EcBaseExpress();
			ee.setExpress_id(p.getExpress_id());
			ee = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ee);

			SfOrderService sf = new SfOrderService();
			String sxddmxOpName = "sfexpressService";
			Order order = new Order();
			order.setOrderid(trade_index);

			// 判断客户是否选择自提和自提点ID
			String p_index = "";
			if (p.getIs_self() == 1 && null != p.getSelf_id()) {
				EcSelfArea ecs = new EcSelfArea();
				ecs.setId(p.getSelf_id());
				ecs = super.getFacade().getEcSelfAreaService().getEcSelfArea(ecs);
				p_index = ecs.getP_index().toString();
				order.setD_address(ecs.getSelf_addr());
			} else {
				if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
					p_index = p.getBuyer_p_index().toString();
				}
				order.setD_address(p.getBuyer_addr());
			}

			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());

						BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
						baseProvince2.setP_index(Long.valueOf(p_index));
						baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince2);
						order.setD_county(baseProvince2.getP_name());
					} else if (p_index.length() == 8) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());

						BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
						baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
						baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince2);
						order.setD_county(baseProvince2.getP_name());

					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(p_index));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());
						order.setD_city("");
						order.setD_county("");
					} else if (p_index.length() == 8) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(p_index));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());
						order.setD_county("");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(p_index));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());
						order.setD_county("");

					} else if (p_index.length() == 8) {

						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());

						BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
						baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
						baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince2);
						order.setD_county(baseProvince2.getP_name());

					}
				}
			}

			// 根据不同的分公司，取发货人的联系地址和联系方式
			// if (null != user.getDept_id()) {
			// KonkaDept konkaDept = new KonkaDept();
			// konkaDept.setDept_id(user.getDept_id());
			// konkaDept =
			// super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			//
			// // 分公司取得
			// if (null != konkaDept.getDept_type() && konkaDept.getDept_type()
			// > 2) {
			// KonkaDept fgs_dept =
			// super.getSuperiorForDeptType(user.getDept_id(), 3);
			// if (null != fgs_dept) {
			// if (fgs_dept.getDept_sn().equalsIgnoreCase("KF22")) {// 合肥分公司
			// } else if (fgs_dept.getDept_sn().equalsIgnoreCase("KF46")) {//
			// 东莞分公司
			// }
			// }
			// }
			//
			// }
			if (null != p && null != p.getOpr_dept_id()) {
				if (p.getOpr_dept_id().intValue() == 13) {// 合肥分公司
					order.setJ_contact("周仁明");
					order.setJ_tel("0550-3089116");
					order.setJ_address("滁州市中都大道999号");
					order.setJ_province("安徽省");
					order.setJ_city("滁州市");
					order.setJ_county("南谯区");
					order.setJ_mobile("13655504217");
					order.setCustid("5500047967");
					order.setValue1("5500047967");
				} else if (p.getOpr_dept_id().intValue() == 6) {// 东莞分公司
					order.setJ_contact("黄生");
					order.setJ_tel("0769-82552522");
					order.setJ_address("广东东莞凤岗康佳直销中心");
					order.setJ_province("广东省");
					order.setJ_city("东莞市");
					order.setJ_county("凤岗");
					order.setCustid("7691250396");
					order.setValue1("7691250396");

				} else if (p.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
					order.setJ_contact("张帅");
					order.setJ_tel("0451-55550681");
					order.setJ_address("哈尔滨市道外区大新街75号康佳集团哈尔滨分公司");
					order.setJ_province("黑龙江省");
					order.setJ_city("哈尔滨市");
					order.setJ_county("道外区");
					order.setJ_mobile("18645145490");
					order.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");// 哈尔滨顾客编码，密码

					// 保价
					order.setS_price("0");
					order.setPrice(p.getPay_price().toString());
					order.setCustid("4512483409");
				}
			}

			order.setD_contact(p.getBuyer_name());
			order.setD_tel(p.getBuyer_tel() == null ? "" : p.getBuyer_tel());
			order.setD_mobile(p.getBuyer_mp());
			// order.setD_address(p.getBuyer_addr());

			// order.setParcel_quantity(String.valueOf(t_num));// 包裹数

			order.setParcel_quantity("1");// 包裹数

			// 货物信息，多个货物用,隔开
			order.setCargo(StringUtils.join(cargoList.toArray(), ","));
			order.setCargo_count(StringUtils.join(cargo_count_List.toArray(), ","));
			order.setCargo_unit(StringUtils.join(cargo_unit_List.toArray(), ","));
			order.setCargo_weight(StringUtils.join(cargo_weight_List.toArray(), ","));
			order.setCargo_amount(StringUtils.join(cargo_amount_List.toArray(), ","));
			order.setCargo_total_weight(total_weight.toString());
			order.setD_company("");

			order.setSendstarttime("");

			// 如果是货到付款
			if (p.getPay_way() == 0) {
				order.setPay_way("0");
				order.setValue(p.getPay_price().toString());
			}

			String xml = order.toXml();
			//System.out.println("订单xml------>{}" + xml);
			String returnXml = sf.sfWebService2(xml, sxddmxOpName);
			// ss = xml + "------------>" + returnXml;
			//System.out.println("下订单入顺丰--------->{}" + returnXml);
			try {
				Document doc = DocumentHelper.parseText(returnXml);
				Element rootElt = doc.getRootElement();
				if (rootElt.elementTextTrim("Head").equalsIgnoreCase("OK")) {
					Iterator<Element> iter = rootElt.elementIterator("Body");
					while (iter.hasNext()) {
						Element iter2 = iter.next();
						Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
						while (iter3.hasNext()) {
							Element iter4 = iter3.next();
							if (StringUtils.isNotBlank(iter4.attributeValue("mailno"))) {
								EcOrderExpressInfo ex = new EcOrderExpressInfo();
								ex.setTrade_index(p.getTrade_index());
								ex.setExpress_id(p.getExpress_id());
								ex.setExpress_name(ee.getExpress_name());
								// String[] sn =
								// iter4.attributeValue("mailno").split(",");
								// ex.setLogistic_sn(sn[0]);
								ex.setLogistic_sn(iter4.attributeValue("mailno"));
								ex.setOrder_from(iter4.attributeValue("origincode"));
								ex.setOrder_to(iter4.attributeValue("destcode"));
								ex.setAdd_date(new Date());
								super.getFacade().getEcOrderExpressInfoService().createEcOrderExpressInfo(ex);
								//System.out.println("订单入顺丰成功！！！");
								ss = "1";

								String msg = kh_name
										+ "您好,您的订单"
										+ trade_index
										+ "（"
										+ pd_name_num
										+ "）已经安排发货,顺丰的物流单号"
										+ ex.getLogistic_sn()
										+ ",请注意查收,签收时请一定开箱验货,如有屏碎或外观损坏请拒收!如您需要上门安装调试,请致电4008800016,我们将尽快与您确定上门服务时间![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827,售后问题请致电康佳大拇指服务热线4008800016]";
								String msg2 = user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
										+ pd_name_num + "）已经安排发货,顺丰的物流单号" + ex.getLogistic_sn()
										+ ",请注意查收![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-61368827]";
								if (ecUser.getUser_type().intValue() == 2) {
									if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
										msg = "【康佳&顺丰直销商城】"
												+ kh_name
												+ "您好,您的订单"
												+ trade_index
												+ "（"
												+ pd_name_num
												+ "）已经安排发货,顺丰的物流单号"
												+ ex.getLogistic_sn()
												+ ",请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有外观破损或屏碎的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-61368827，上门安装调试,请致电4008800016]";
										msg2 = "";
									} else {
										msg = "【康佳网上直销商城】"
												+ kh_name
												+ "您好,您的订单"
												+ trade_index
												+ "（"
												+ pd_name_num
												+ "）已经安排发货,顺丰的物流单号"
												+ ex.getLogistic_sn()
												+ ",请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有外观破损或屏碎的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-61368827，上门安装调试,请致电4008800016]";
										msg2 = "【康佳网上直销商城】"
												+ user_real_name
												+ "您好,"
												+ kh_name
												+ "的订单"
												+ trade_index
												+ "（"
												+ pd_name_num
												+ "）已经安排发货,顺丰的物流单号"
												+ ex.getLogistic_sn()
												+ ",请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有因运输产生的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-61368827，上门安装调试,请致电4008800016]";
									}
								}
								if (!"".equals(msg)) {
									EcMessage ecm = new EcMessage();
									ecm.setAdd_date(new Date());
									ecm.setContent(msg);
									ecm.setMobile(p.getBuyer_mp());
									ecm.setOrder_id(p.getId().toString());
									ecm.setSend_date(new Date());
									ecm.setOrder_state(40);
									if (getSendMessageResult(p.getBuyer_mp(), msg)) {
										ecm.setSend_state(0);
									} else {
										ecm.setSend_state(1);
									}
									super.getFacade().getEcMessageService().createEcMessage(ecm);
								}
								if (!"".equals(msg2)) {
									EcMessage ecm = new EcMessage();
									ecm.setAdd_date(new Date());
									ecm.setContent(msg2);
									ecm.setMobile(user_tel);
									ecm.setOrder_id(p.getId().toString());
									ecm.setSend_date(new Date());
									ecm.setOrder_state(40);
									if (getSendMessageResult(user_tel, msg2)) {// 给下单用户发短信
										ecm.setSend_state(0);
									} else {
										ecm.setSend_state(1);
									}
									super.getFacade().getEcMessageService().createEcMessage(ecm);
								}

							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("报错啦！！" + e);
				ss = "0" + returnXml + e;
			}
		}

		super.renderText(response, ss);
		return null;
	}

	public ActionForward orderSend2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		PshowOrder p = new PshowOrder();
		p.setTrade_index(trade_index);
		p = super.getFacade().getPshowOrderService().getPshowOrder(p);

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(p.getId());
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				.getPshowOrdeForPDSNDetailsList(psd);

		int t_num = 0;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		String ss = "";

		if (null != p) {

			EcBaseExpress ee = new EcBaseExpress();
			ee.setExpress_id(p.getExpress_id());
			ee = super.getFacade().getEcBaseExpressService().getEcBaseExpress(ee);

			SfOrderService sf = new SfOrderService();
			String sxddmxOpName = "sfexpressService";
			Order order = new Order();
			order.setOrderid(trade_index);

			String p_index = "";
			if (p.getIs_self() == 1 && null != p.getSelf_id()) {
				EcSelfArea ecs = new EcSelfArea();
				ecs.setId(p.getSelf_id());
				ecs = super.getFacade().getEcSelfAreaService().getEcSelfArea(ecs);
				p_index = ecs.getP_index().toString();
				order.setD_address(ecs.getSelf_addr());
			} else {
				if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
					p_index = p.getBuyer_p_index().toString();
				}
				order.setD_address(p.getBuyer_addr());
			}

			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());

						BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
						baseProvince2.setP_index(Long.valueOf(p_index));
						baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince2);
						order.setD_county(baseProvince2.getP_name());
					} else if (p_index.length() == 8) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());

						BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
						baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
						baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince2);
						order.setD_county(baseProvince2.getP_name());

					}
				} else if (p_index.endsWith("0000")) {
					if (p_index.length() == 6) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(p_index));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());
						order.setD_city("");
						order.setD_county("");
					} else if (p_index.length() == 8) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(p_index));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());
						order.setD_county("");
					}
				} else if (p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(p_index));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());
						order.setD_county("");

					} else if (p_index.length() == 8) {

						BaseProvinceListFour baseProvince = new BaseProvinceListFour();
						baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
						baseProvince = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince);
						order.setD_province(baseProvince.getP_name());

						BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
						baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
						baseProvince1 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince1);
						order.setD_city(baseProvince1.getP_name());

						BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
						baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
						baseProvince2 = super.getFacade().getBaseProvinceListFourService()
								.getBaseProvinceListFour(baseProvince2);
						order.setD_county(baseProvince2.getP_name());

					}
				}
			}

			// 根据不同的分公司，取发货人的联系地址和联系方式
			// if (null != user.getDept_id()) {
			// KonkaDept konkaDept = new KonkaDept();
			// konkaDept.setDept_id(user.getDept_id());
			// konkaDept =
			// super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			//
			// // 分公司取得
			// if (null != konkaDept.getDept_type() && konkaDept.getDept_type()
			// > 2) {
			// KonkaDept fgs_dept =
			// super.getSuperiorForDeptType(user.getDept_id(), 3);
			// if (null != fgs_dept) {
			// if (fgs_dept.getDept_sn().equalsIgnoreCase("KF22")) {// 合肥分公司
			// } else if (fgs_dept.getDept_sn().equalsIgnoreCase("KF46")) {//
			// 东莞分公司
			// }
			// }
			// }
			//
			// }
			if (null != p && null != p.getOpr_dept_id()) {
				if (p.getOpr_dept_id().intValue() == 13) {// 合肥分公司
					order.setJ_contact("周仁明");
					order.setJ_tel("0550-3089116");
					order.setJ_address("滁州市中都大道999号");
					order.setJ_province("安徽省");
					order.setJ_city("滁州市");
					order.setJ_county("南谯区");
					order.setJ_mobile("13655504217");
					order.setCustid("5500047967");
					order.setValue1("5500047967");
				} else if (p.getOpr_dept_id().intValue() == 6) {// 东莞分公司
					order.setJ_contact("黄生");
					order.setJ_tel("0769-82552522");
					order.setJ_address("广东东莞凤岗康佳直销中心");
					order.setJ_province("广东省");
					order.setJ_city("东莞市");
					order.setJ_county("凤岗");
					order.setCustid("7691250396");
					order.setValue1("7691250396");

				} else if (p.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
					order.setJ_contact("张帅");
					order.setJ_tel("0451-55550681");
					order.setJ_address("哈尔滨市道外区大新街75号康佳集团哈尔滨分公司");
					order.setJ_province("黑龙江省");
					order.setJ_city("哈尔滨市");
					order.setJ_county("道外区");
					order.setJ_mobile("18645145490");
					order.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");// 哈尔滨顾客编码，密码

					// 保价
					order.setS_price("0");
					order.setPrice(p.getPay_price().toString());
					order.setCustid("4512483409");
				}
			}

			order.setD_contact(p.getBuyer_name());
			order.setD_tel(p.getBuyer_tel());
			order.setD_mobile(p.getBuyer_mp());
			// order.setD_address(p.getBuyer_addr());

			order.setCargo("");
			order.setCargo_count("");
			order.setCargo_unit("");
			order.setCargo_weight("");
			order.setCargo_amount("");
			order.setCargo_total_weight("1");
			order.setD_company("");
			// order.setParcel_quantity(String.valueOf(t_num));// 包裹数
			order.setParcel_quantity("1");// 包裹数
			order.setSendstarttime("");

			// 如果是货到付款
			if (p.getPay_way() == 0) {
				order.setPay_way("0");
				order.setValue(p.getPay_price().toString());
			}

			String xml = order.toXml();
			//System.out.println("订单xml------>{}" + xml);
			String returnXml = sf.sfWebService2(xml, sxddmxOpName);
			//System.out.println("下订单入顺丰--------->{}" + returnXml);
			// try {
			// Document doc = DocumentHelper.parseText(returnXml);
			// Element rootElt = doc.getRootElement();
			// if (rootElt.elementTextTrim("Head").equalsIgnoreCase("OK")) {
			// Iterator<Element> iter = rootElt.elementIterator("Body");
			// while (iter.hasNext()) {
			// Element iter2 = iter.next();
			// Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
			// while (iter3.hasNext()) {
			// Element iter4 = iter3.next();
			// if (StringUtils.isNotBlank(iter4.attributeValue("mailno"))) {
			// EcOrderExpressInfo ex = new EcOrderExpressInfo();
			// ex.setTrade_index(p.getTrade_index());
			// ex.setExpress_id(p.getExpress_id());
			// ex.setExpress_name(ee.getExpress_name());
			// // String[] sn =
			// // iter4.attributeValue("mailno").split(",");
			// // ex.setLogistic_sn(sn[0]);
			// ex.setLogistic_sn(iter4.attributeValue("mailno"));
			// ex.setOrder_from(iter4.attributeValue("origincode"));
			// ex.setOrder_to(iter4.attributeValue("destcode"));
			// ex.setAdd_date(new Date());
			// super.getFacade().getEcOrderExpressInfoService().createEcOrderExpressInfo(ex);
			// //System.out.println("订单入顺丰成功！！！");
			// ss = "1";
			// }
			// }
			// }
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// System.err.println("报错啦！！" + e);
			// ss = "0" + returnXml + e;
			// }
			super.renderText(response, returnXml);
		}

		return null;
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
			if (entity.getState().intValue() == 40 || entity.getState().intValue() == 50
					|| entity.getState().intValue() == 60) {
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
		return new ActionForward("/spgl/PshowOrderLook/sheet.jsp");
	}

	public ActionForward orderFind(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		PshowOrder pp = new PshowOrder();
		pp.setTrade_index(trade_index);
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		OrderCx order = new OrderCx();
		if (pp != null && pp.getOpr_dept_id() != null && pp.getOpr_dept_id().intValue() == 10) {
			order.setCheckword("IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
			order.setCustid("kjjt");
		}
		order.setOrderid(trade_index);
		String xml = order.toXml();
		String returnXml = sf.sfWebService2(xml, sxddmxOpName);
		super.renderText(response, returnXml);
		return null;
	}

	public ActionForward selectTime(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// 默认当天
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String start_date_1 = sf.format(date) + " 00:00:00";
		String end_date_1 = sf.format(date) + " 23:59:59";

		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start_date = sf2.parse(start_date_1);
		Date end_date = sf2.parse(end_date_1);

		dynaBean.set("start_date", sf.format(date));
		dynaBean.set("end_date", sf.format(date));

		return new ActionForward("/../manager/spgl/PshowOrderLook/selectTime.jsp");
	}

	/**
	 * @param 批量同步顺丰嘿客订单
	 */
	public ActionForward syncSfhkOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String page = "1";
		String pagesize = "100";

		// 判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String ss = syncOrderNew(start_date, end_date, page, pagesize, userInfo);
		super.renderText(response, ss);
		return null;
	}

	/**
	 * @param 同步顺丰嘿客物流公司
	 */
	public ActionForward syncSfCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		String ss = syncSfCompNew(userInfo);
		super.renderText(response, ss);
		return null;
	}

	/**
	 * @param 顺丰嘿客订单出库
	 */
	public ActionForward outstock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		// 判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PshowOrder pp = new PshowOrder();
		pp.setId(Long.valueOf(id));
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		String log_sn = "";
		if (pp != null) {
			EcOrderExpressInfo ee = new EcOrderExpressInfo();
			ee.setTrade_index(pp.getTrade_index());
			ee = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ee);
			if (ee == null || ee.getLogistic_sn() == null) {
				super.renderText(response, "该订单还没有运单号，不能出库");
				return null;
			} else {
				log_sn = ee.getLogistic_sn();
			}
		} else {
			super.renderText(response, "没找到订单");
			return null;
		}

		String sf_id = "";
		SfhkRelEppOrder se = new SfhkRelEppOrder();
		se.setEpp_order_id(id);
		se = super.getFacade().getSfhkRelEppOrderService().getSfhkRelEppOrder(se);
		if (se == null) {
			super.renderText(response, "该订单不是从顺丰嘿客同步过来的，不能出库");
			return null;
		} else {
			sf_id = se.getSf_order_id();
		}

		SfhkOutStorage so = new SfhkOutStorage();
		so.setEpp_order_id(id);
		so = super.getFacade().getSfhkOutStorageService().getSfhkOutStorage(so);
		if (null != so && so.getState().intValue() == 0) {
			super.renderText(response, "该订单已经出库");
			return null;
		}

		String ss = outStockNew(sf_id, id, log_sn, userInfo);
		super.renderText(response, ss);
		return null;
	}

}
