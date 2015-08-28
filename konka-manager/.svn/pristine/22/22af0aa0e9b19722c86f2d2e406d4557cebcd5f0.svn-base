package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.Order;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author TUDP
 * @version 2014-05-03
 */
public class TouchOrderLook2Action extends BasePshowOrderAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
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
		String order_from = "2";// (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");
		String r3_code = (String) dynaBean.get("r3_code");
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
			// entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(),
			// 3).getDept_id());
			entity.setOpr_dept_id(null);
			entity.getMap().put("fgs_dept_id_new", user.getDept_id());

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
		} else {
			entity.setOpr_dept_id(user.getDept_id());
		}

		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(order_user_name_like)) {
			entity.getMap().put("order_user_name_like", order_user_name_like);
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			// entity.setOpr_dept_id(Long.valueOf(dept_id));
			entity.getMap().put("fgs_dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(pay_way)) {
			entity.setPay_way(Integer.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			entity.setOrder_from(Integer.valueOf(order_from));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}

		if (StringUtils.isNotBlank(touch_type)) {
			if (touch_type.equals("2")) {
				entity.setPay_way(9);
				dynaBean.set("touch_type", "2");
			} else if (touch_type.equals("1")) {
				entity.getMap().put("cust_pay_way", true);
				dynaBean.set("touch_type", "1");
			}
		}

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);
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

				if (null != pshowordedetails && pshowordedetails.size() > 0) {
					for (PshowOrdeDetails ps : pshowordedetails) {
						t_num += ps.getNum();
					}
				}
				pp.getMap().put("total_num", t_num);

				if (pp.getOrder_user_id().intValue() == 128008 || (pp.getAdd_date().getTime() < 1421683200000L)) {
					pp.getMap().put("old_dept_name", true);
				} else {

					EcUser eu = new EcUser();
					eu.setId(pp.getOrder_user_id());
					eu = super.getFacade().getEcUserService().getEcUser(eu);

					EcGroup ee = new EcGroup();
					ee.setId(eu.getDept_id());
					ee = super.getFacade().getEcGroupService().getEcGroup(ee);
					if (ee != null && ee.getLink_dept_id() != null) {
						KonkaDept kk = new KonkaDept();
						kk.setDept_id(ee.getLink_dept_id());
						kk = super.getFacade().getKonkaDeptService().getKonkaDept(kk);
						if (kk != null) {
							pp.getMap().put("dept_name_new", kk.getDept_name());
						}
					}

				}

			}

		}

		request.setAttribute("entityList", entityList);
		KonkaDept sysdept = new KonkaDept();
		sysdept.setDept_type(new Integer(3));
		sysdept.getMap().put("order_by_dept_name", true);
		request.setAttribute("sybDeptInfoList", getFacade().getKonkaDeptService().getKonkaDeptList(sysdept));

		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String order_user_name_like = (String) dynaBean.get("order_user_name_like");
		String state = (String) dynaBean.get("state");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String buyer_mp_like = (String) dynaBean.get("buyer_mp_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String trade_no_like = (String) dynaBean.get("trade_no_like");
		String order_from = "2"; // (String) dynaBean.get("order_from");
		String pay_way = (String) dynaBean.get("pay_way");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String is_ps = (String) dynaBean.get("is_ps");
		String r3_code = (String) dynaBean.get("r3_code");
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
			entity.setOpr_dept_id(null);
			entity.getMap().put("fgs_dept_id_new", user.getDept_id());

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
		} else {
			entity.setOpr_dept_id(user.getDept_id());
		}

		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(order_user_name_like)) {
			entity.getMap().put("order_user_name_like", order_user_name_like);
		}
		if (StringUtils.isNotBlank(is_ps)) {
			entity.setIs_ps(Integer.valueOf(is_ps));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (StringUtils.isNotBlank(buyer_mp_like)) {
			entity.getMap().put("buyer_mp_like", buyer_mp_like);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			// entity.setOpr_dept_id(Long.valueOf(dept_id));
			entity.getMap().put("fgs_dept_id_new", dept_id);

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
		if (StringUtils.isNotBlank(trade_no_like)) {
			entity.getMap().put("trade_no_like", trade_no_like);
		}
		if (StringUtils.isNotBlank(order_from)) {
			entity.setOrder_from(Integer.valueOf(order_from));
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
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}

		if (StringUtils.isNotBlank(touch_type)) {
			if (touch_type.equals("2")) {
				entity.setPay_way(9);
				dynaBean.set("touch_type", "2");
			} else if (touch_type.equals("1")) {
				entity.getMap().put("cust_pay_way", true);
				dynaBean.set("touch_type", "1");
			}
		}

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);

		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        entity);

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
		e.setCell(3, "触网类型");
		e.setCell(4, "下单分公司");
		e.setCell(5, "下单人姓名");
		e.setCell(6, "客户R3编码");
		e.setCell(7, "购买人姓名");
		e.setCell(8, "购买人地区");
		e.setCell(9, "下单时间");
		e.setCell(10, "购买人手机");
		e.setCell(11, "支付方式");
		e.setCell(12, "二次配送");

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
			} else if (pds.getState() == 10) {
				s_state = "已付款";
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
			String touch_type1 = "";
			if (pds.getPay_way().intValue() == 9) {
				touch_type1 = "触网2";
			} else {
				touch_type1 = "触网1";
			}
			e.setCell(3, touch_type1);

			String xd_dept_name = "";
			String r3_code_1 = "";
			EcUser ec = new EcUser();
			ec.setId(pds.getOrder_user_id());
			ec = super.getFacade().getEcUserService().getEcUser(ec);

			if (pds.getOrder_user_id().intValue() == 128008 || (pds.getAdd_date().getTime() < 1421683200000L)) {
				if (null != ec) {
					KonkaDept kd = new KonkaDept();
					kd.setDept_type(3);
					kd.setDept_id(ec.getDept_id());
					kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
					if (null != kd) {
						xd_dept_name = kd.getDept_name();
					}
				}
			} else {
				if (null != ec) {
					EcGroup eg = new EcGroup();
					eg.setId(ec.getDept_id());
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null && eg.getLink_dept_id() != null) {
						KonkaDept kk = new KonkaDept();
						kk.setDept_id(eg.getLink_dept_id());
						kk = super.getFacade().getKonkaDeptService().getKonkaDept(kk);
						if (kk != null) {
							xd_dept_name = kk.getDept_name();
						}
					}
				}

			}

			if (null != ec && ec.getCust_id() != null) {
				KonkaR3Shop ks = new KonkaR3Shop();
				ks.setId(ec.getCust_id());
				ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
				if (ks != null && ks.getR3_code() != null) {
					r3_code_1 = ks.getR3_code();
				}
			}
			e.setCell(4, xd_dept_name);
			e.setCell(5, pds.getOrder_user_name());
			e.setCell(6, r3_code_1);
			e.setCell(7, pds.getBuyer_name());
			e.setCell(8, (String) pds.getMap().get("full_name") + "&nbsp;" + pds.getBuyer_addr());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			e.setCell(9, sf.format(pds.getAdd_date()));
			e.setCell(10, pds.getBuyer_mp());
			if ("0".equals(pds.getPay_way().toString())) {
				e.setCell(11, "货到付款");
			} else if ("1".equals(pds.getPay_way().toString())) {
				e.setCell(11, "银行付款");
			} else if ("2".equals(pds.getPay_way().toString())) {
				e.setCell(11, "支付宝");
			} else if ("3".equals(pds.getPay_way().toString())) {
				e.setCell(11, "银联");
			} else if ("4".equals(pds.getPay_way().toString())) {
				e.setCell(11, "财付通");
			} else if ("5".equals(pds.getPay_way().toString())) {
				e.setCell(11, "民生银行");
			} else if ("9".equals(pds.getPay_way().toString())) {
				e.setCell(11, "线下处理");
			}

			String ph = "";
			if (pds.getIs_ps() == 0) {
				ph = "否";
			} else if (pds.getIs_ps() == 1) {
				ph = "是";
			}
			e.setCell(12, ph);

		}
		// 输出
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
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

		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

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

		return new ActionForward("/spgl/TouchOrderLook2/print3.jsp");
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
			if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
				String p_index = p.getBuyer_p_index().toString();
				if (StringUtils.isNotBlank(p_index)) {
					if (!p_index.endsWith("00")) {
						if (p_index.length() == 6) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());

							BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
							baseProvince2.setP_index(Long.valueOf(p_index));
							baseProvince2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince2);
							order.setD_county(baseProvince2.getP_name());
						} else if (p_index.length() == 8) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());

							BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
							baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
							baseProvince2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince2);
							order.setD_county(baseProvince2.getP_name());

						}
					} else if (p_index.endsWith("0000")) {
						if (p_index.length() == 6) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(p_index));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());
							order.setD_city("");
							order.setD_county("");
						} else if (p_index.length() == 8) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(p_index));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());
							order.setD_county("");
						}
					} else if (p_index.endsWith("00")) {
						if (p_index.length() == 6) {
							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(p_index));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());
							order.setD_county("");

						} else if (p_index.length() == 8) {

							BaseProvinceListFour baseProvince = new BaseProvinceListFour();
							baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
							baseProvince = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince);
							order.setD_province(baseProvince.getP_name());

							BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
							baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
							baseProvince1 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince1);
							order.setD_city(baseProvince1.getP_name());

							BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
							baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
							baseProvince2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
							        baseProvince2);
							order.setD_county(baseProvince2.getP_name());

						}
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
				if (p.getOpr_dept_id() == 13) {// 合肥分公司
					order.setJ_contact("周仁明");
					order.setJ_tel("0550-3089116");
					order.setJ_address("滁州市中都大道999号");
					order.setJ_province("安徽省");
					order.setJ_city("滁州市");
					order.setJ_county("南谯区");
					order.setJ_mobile("13655504217");
					order.setCustid("5500047967");
					order.setValue1("5500047967");
				} else if (p.getOpr_dept_id() == 6) {// 东莞分公司
					order.setJ_contact("黄生");
					order.setJ_tel("0769-82552522");
					order.setJ_address("广东东莞凤岗康佳直销中心");
					order.setJ_province("广东省");
					order.setJ_city("东莞市");
					order.setJ_county("凤岗");
					order.setCustid("7691250396");
					order.setValue1("7691250396");

				}
			}

			order.setD_contact(p.getBuyer_name());
			order.setD_tel(p.getBuyer_tel());
			order.setD_mobile(p.getBuyer_mp());
			order.setD_address(p.getBuyer_addr());

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
		return new ActionForward("/spgl/TouchOrderLook2/sheet.jsp");
	}
}
