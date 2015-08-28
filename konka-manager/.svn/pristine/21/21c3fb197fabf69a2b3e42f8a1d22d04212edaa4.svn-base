package com.ebiz.mmt.web.struts.manager.admin;

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
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoMessageSend;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Zsof;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.ExcuteMsg;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZSOF;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.opensymphony.oscache.util.StringUtil;


public class KonkaOrderSearchHaveAuditAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.getModPopeDom(form, request);// 权限判断
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String kh_confirm_state = (String) dynaBean.get("kh_confirm_state");
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String my_audit_state = (String) dynaBean.get("my_audit_state");
		String or_audit_state = (String) dynaBean.get("or_audit_state");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String is_delivered = (String) dynaBean.get("is_delivered");
		String ag_like = (String) dynaBean.get("ag_like");// 订单表里的ag 就是客户的R3编码
		String r3_id = (String) dynaBean.get("r3_id");
		String sync_state = (String) dynaBean.get("sync_state");
		String excel_all = (String) dynaBean.get("excel_all");
		String doc_type = (String) dynaBean.get("doc_type");
		String order_type = (String) dynaBean.get("order_type");
		String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货
		String vbedl_like = (String) dynaBean.get("vbedl_like");// 26单号
		String vbedl_null = (String) dynaBean.get("vbedl_null");
		String customer_type_index = (String) dynaBean.get("customer_type_index");
		String dd_result = (String) dynaBean.get("dd_result");

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		dynaBean.set("user_id", userInfo.getId());// 用户

		boolean role_id_lt_30 = false; // 是否为系统管理员
		// boolean role_id_eq_30 = false; // 是否为分公司管理员
		// boolean role_id_eq_34 = false; // 是否为分公司总经理
		boolean role_id_eq_38 = false; // 是否为分公司产品会计
		// boolean role_id_eq_39 = false; // 是否为分公司财务经理
		boolean role_id_eq_41 = false; // 是否为青岛产品会计
		// boolean role_id_eq_41 = false; // 是否为青岛产品会计
		boolean role_id_eq_33 = false; // 是否为泉州产品会计
		boolean role_id_eq_54 = false; // 是否为乐山产品会计
		boolean role_id_eq_55 = false; // 是否为西昌产品会计
		boolean role_id_eq_56 = false; // 是否为广元产品会计
		boolean role_id_eq_43 = false; // 是否为万州产品会计
		boolean role_id_eq_44 = false; // 是否为芜湖产品会计
		boolean role_id_eq_46 = false; // 是否为温州产品会计

		boolean role_id_eq_57 = false; // 是否为分公司物流经理
		boolean role_id_eq_30 = false; // 是否为分公司管理员

		/*
		 * 1 33 分公司职务-厦门 泉州产品会计 2014-04-02 17:48:57 权限 | 人员 | 授权 | 修改 | 删除 2 38
		 * 系统职务 分公司产品会计 2013-08-20 09:58:50 权限 | 人员 | 授权 | 修改 | 删除 3 41 系统职务
		 * 青岛产品会计 2013-10-31 17:54:56 权限 | 人员 | 授权 | 修改 | 删除 4 54 分公司职务-成都
		 * 乐山产品会计 2014-03-28 10:42:31 权限 | 人员 | 授权 | 修改 | 删除 5 55 分公司职务-成都
		 * 西昌产品会计 2014-03-28 10:42:51 权限 | 人员 | 授权 | 修改 | 删除 6 56 分公司职务-成都
		 * 广元产品会计
		 */

		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
				role_id_lt_30 = true;
			}
			// if (peRoleUser.getRole_id() == 30L) {
			// role_id_eq_30 = true;
			// }
			// if (peRoleUser.getRole_id() == 34L) {
			// role_id_eq_34 = true;
			// }
			if (peRoleUser.getRole_id() == 38L) {
				role_id_eq_38 = true;
			}
			// if (peRoleUser.getRole_id() == 39L) {
			// role_id_eq_39 = true;
			// }
			if (peRoleUser.getRole_id() == 41L) {
				role_id_eq_41 = true;
			}

			if (peRoleUser.getRole_id() == 33L) {
				role_id_eq_33 = true;
			}
			if (peRoleUser.getRole_id() == 54L) {
				role_id_eq_54 = true;
			}
			if (peRoleUser.getRole_id() == 55L) {
				role_id_eq_55 = true;
			}
			if (peRoleUser.getRole_id() == 56L) {
				role_id_eq_56 = true;
			}
			if (peRoleUser.getRole_id() == 43L) {
				role_id_eq_43 = true;
			}
			if (peRoleUser.getRole_id() == 44L) {
				role_id_eq_44 = true;
			}
			if (peRoleUser.getRole_id() == 46L) {
				role_id_eq_46 = true;
			}
			if (peRoleUser.getRole_id() == 57L) {
				role_id_eq_57 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}

		}

		request.setAttribute("role_id_eq_33", role_id_eq_33);
		request.setAttribute("role_id_eq_54", role_id_eq_54);
		request.setAttribute("role_id_eq_55", role_id_eq_55);
		request.setAttribute("role_id_eq_56", role_id_eq_56);

		request.setAttribute("role_id_eq_38", role_id_eq_38);
		request.setAttribute("role_id_eq_41", role_id_eq_41);
		request.setAttribute("role_id_eq_43", role_id_eq_43);

		request.setAttribute("role_id_eq_44", role_id_eq_44);
		request.setAttribute("role_id_eq_46", role_id_eq_46);

		request.setAttribute("role_id_eq_30", role_id_eq_30);
		request.setAttribute("role_id_eq_57", role_id_eq_57);

		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		// calendar.add(Calendar.DATE, -30);
		calendar.add(Calendar.MONTH, -1);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);

		if (StringUtils.isNotBlank(order_date_start) && StringUtils.isNotBlank(order_date_end)) {
			Date start_date = DateUtils.parseDate(order_date_start, new String[] { "yyyy-MM-dd" });
			Date end_date = DateUtils.parseDate(order_date_end, new String[] { "yyyy-MM-dd" });
			// 判断下单日期间隔是否小于90天
			if ((end_date.getTime() - start_date.getTime()) > 90 * 24 * 60 * 60 * 1000L) { // 超过90天
				String msg = super.getMessage(request, "order.date.over.90.day");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
			dynaBean.set("order_date_end", day_last);
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		entity.setIs_del(0);// 0:表示未删除；1：表示删除

		entity.getMap().put("hava_audit_user_id", userInfo.getId());

		if (("1").equals(vbedl_null)) {
			entity.getMap().put("vbedl_null", vbedl_null);
		}
		if (("0").equals(vbedl_null)) {
			entity.getMap().put("vbedl_not_null", vbedl_null);
		}

		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start + " 00:00:00");
		} else {
			entity.getMap().put("start_date", day_first + " 00:00:00");
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end + " 23:59:59");
		} else {
			entity.getMap().put("end_date", day_last + " 23:59:59");
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(r3_id)) {
			entity.setR3_id(Long.valueOf(r3_id));
		}
		if (StringUtils.isNotBlank(sync_state)) {
			entity.setSync_state(Integer.valueOf(sync_state));
		}
		if (is_th != null && is_th.equals("1")) {
			entity.setDoc_type("ZFRE");
		} else {
			if (StringUtils.isNotBlank(doc_type)) {
				entity.setDoc_type(doc_type);
			}
		}

        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
		if (StringUtils.isNotBlank(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(vbedl_like)) {
			entity.getMap().put("vbedl_like", vbedl_like);
		}
		if (StringUtils.isNotBlank(is_delivered)) {
			entity.setIs_delivered(Integer.valueOf(is_delivered));
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}
		if (GenericValidator.isInt(or_audit_state)) {
			entity.setAudit_state(Integer.valueOf(or_audit_state));
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
		}
		
		//审核结果
		if (StringUtils.isNotBlank(dd_result)) {
			if("0".equals(dd_result)){
				entity.getMap().put("dd_result_b", true);
			}else{
				entity.getMap().put("dd_result_t", true);
			}
		}

		if (GenericValidator.isInt(my_audit_state)) {
			// 按订单状态查询
			int state = Integer.valueOf(my_audit_state);
			switch (state) {
			case 10: // 系统管理员-审核中
				entity.getMap().put("audit_state_lt", 3);
				break;
			case 11: // 系统管理员-已完成
				entity.getMap().put("audit_state_eq", 3);
				break;
			case 20: // 非系统管理员-待审核
				entity.getMap().put("audit_state_2", my_audit_state);
				entity.getMap().put("audit_state_eq_20", my_audit_state);
				break;
			case 21: // 非系统管理员-已审核
				entity.getMap().put("audit_state_2", my_audit_state);
				entity.getMap().put("audit_state_eq_21", my_audit_state);
				break;
			}
		}

		if (StringUtils.isNotBlank(kh_confirm_state)) {
			entity.setKh_confirm_state(Integer.valueOf(kh_confirm_state));
		}
		if (StringUtils.isNotBlank(customer_type_index)) {
			entity.getMap().put("customer_type_index", customer_type_index);
		}

		if (role_id_lt_30) { // 系统管理员登录
			dynaBean.set("dept_type", "1");
			Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setCount(pager.getRowCount());
			entity.getRow().setFirst(pager.getFirstRow());

			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);

			// 从R3得到物流发货信息--ldy
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
				if (r3_order_id != null) {
					KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
					konkaR3Zsof.setVbeln(r3_order_id);
					konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
					if (konkaR3Zsof != null) {
						konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
						if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
							konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
							if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
								konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
							}
						}
					}
				}
			}

			// 得到分公司
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				if (null != konkaOrderInfo.getAdd_dept_id()) {
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
					konkaDept.getMap().put("dept_type_eq", 3);
					konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
					if (konkaDept != null) {
						if (null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
							konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
						}
					}
				}
			}

			// 得到经办
			for (KonkaOrderInfo konkaOrderInfo2 : entityList) {
				if (null != konkaOrderInfo2.getAdd_dept_id()) {
					KonkaDept konkaDept2 = new KonkaDept();
					konkaDept2.setDept_id(konkaOrderInfo2.getAdd_dept_id());
					konkaDept2.getMap().put("dept_type_eq", 4);
					konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
					if (null != konkaDept2 && !("".equals(konkaDept2))) {
						String deptName = konkaDept2.getDept_name();
						if (null != deptName && !("".equals(deptName))) {
							konkaOrderInfo2.getMap().put("jbName", konkaDept2.getDept_name());
						}
					} else {
						KonkaDept konkaDept3 = new KonkaDept();
						konkaDept3.setDept_id(konkaOrderInfo2.getAdd_dept_id());
						konkaDept3.getMap().put("dept_type_eq", 5);
						konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
						if (konkaDept3 != null && null != konkaDept3.getDept_name()
								&& !("".equals(konkaDept3.getDept_name()))) {
							String deptName = konkaDept3.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo2.getMap().put("jbName", konkaDept3.getDept_name());
							}
						}
					}
				}
			}

			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 5000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
							+ "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + EncryptUtils.encodingFileName("订单查询数据") + ".xls");
				entity.getRow().setCount(recordCount.intValue());
				List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService()
						.getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);
				dynaBean.set("excel_all", excel_all);

				// 从R3得到物流发货信息--ldy
				for (KonkaOrderInfo konkaOrderInfo : entityList) {
					Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
					if (r3_order_id != null) {
						KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
						konkaR3Zsof.setVbeln(r3_order_id);
						konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
						if (konkaR3Zsof != null) {
							konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
							if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
								konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
								if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
									konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
								}
							}
						}
					}
				}

				// 得到分公司
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept6 = new KonkaDept();
						konkaDept6.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept6.getMap().put("dept_type_eq", 3);
						konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
						if (null != konkaDept6 && null != konkaDept6.getDept_name()
								&& !"".equals(konkaDept6.getDept_name())) {
							konkaOrderInfo4.getMap().put("fgsName", konkaDept6.getDept_name());
						}
					}
				}

				// 得到经办
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept4 = new KonkaDept();
						konkaDept4.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept4.getMap().put("dept_type_eq", 4);
						konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
						if (null != konkaDept4 && !("".equals(konkaDept4))) {
							String deptName = konkaDept4.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo4.getMap().put("jbName", konkaDept4.getDept_name());
							}
						} else {
							KonkaDept konkaDept5 = new KonkaDept();
							konkaDept5.setDept_id(konkaOrderInfo4.getAdd_dept_id());
							konkaDept5.getMap().put("dept_type_eq", 5);
							konkaDept5 = super.getFacade().getKonkaDeptService()
									.getKonkaDeptSuperiorByDeptId(konkaDept5);
							if (null != konkaDept5 && !("".equals(konkaDept5))) {
								String deptName = konkaDept5.getDept_name();
								if (null != deptName && !("".equals(deptName))) {
									konkaOrderInfo4.getMap().put("jbName", konkaDept5.getDept_name());
								}
							}
						}
					}
				}
				if (null != entityList1) {
					for (KonkaOrderInfo orderInfo : entityList1) {
						// 0： 待审核 1: 已审核
						orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
					}
				}
				request.setAttribute("allList", entityList1);
				if (is_th != null && is_th.equals("1")) {
					return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
				} else {
					return new ActionForward("/admin/KonkaOrderSearch/listForReport.jsp");
				}
			}

			for (KonkaOrderInfo konkaorderinfo : entityList) {

				KonkaOrderInfoMessageSend konkaorderinfomessage = new KonkaOrderInfoMessageSend();
				konkaorderinfomessage.setOrder_id(konkaorderinfo.getId());
				Long messagecount = super.getFacade().getKonkaOrderInfoMessageSendService()
						.getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
				konkaorderinfo.getMap().put("messagecount", messagecount);
				if (messagecount != 0L) {
					List<KonkaOrderInfoMessageSend> KonkaOrderInfoMessageSendList = super.getFacade()
							.getKonkaOrderInfoMessageSendService()
							.getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
					if (KonkaOrderInfoMessageSendList != null) {
						konkaorderinfo.getMap().put("KonkaOrderInfoMessageSendList", KonkaOrderInfoMessageSendList);
					}

				}
			}
			request.setAttribute("entityList", entityList);

		} else { // 非系统管理员登录
			dynaBean.set("dept_type", "2");

			// if (StringUtils.isBlank(audit_state) && (role_id_eq_30 ||
			// role_id_eq_34 || role_id_eq_38 || role_id_eq_39)) {
			// //
			// 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
			// KonkaDept fgs_dept =
			// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			// if (null != fgs_dept) {
			// entity.getMap().put("par_or_children_dept_id",
			// fgs_dept.getDept_id());
			// }
			// } else {
			// entity.getMap().put("no_sys_man_user_id", userInfo.getId()); //
			// 表示需要当前用户角色审核的订单
			// entity.getMap().put("par_or_children_dept_id",
			// userInfo.getDept_id());
			// }

			// 数据级别判断开始
			Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			request.setAttribute("max_dlevel", max_dlevel);
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				break;
			case 0:
				break;
			default:
				// 出错
			}
			// 数据级别判断结束
			entity.getMap().put("session_user_id", userInfo.getId());// 获取当前客户所查看的数据部门
			entity.getMap().put("par_or_children_dept_id", __dept_id);

			if (StringUtils.isBlank(my_audit_state) && (max_dlevel >= 7)) {
				// 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
				// KonkaDept fgs_dept =
				// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
				// if (null != fgs_dept) {
				// entity.getMap().put("par_or_children_dept_id",
				// fgs_dept.getDept_id());
				// }
			} else {
				entity.getMap().put("no_sys_man_user_id", userInfo.getId()); // 表示需要当前用户角色审核的订单
				// entity.getMap().put("par_or_children_dept_id",
				// userInfo.getDept_id());
			}

			Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoSysManCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setCount(pager.getRowCount());
			entity.getRow().setFirst(pager.getFirstRow());

			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoWithNoSysManPaginatedList(entity);
			// 从R3得到物流发货信息--ldy
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
				if (r3_order_id != null) {
					KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
					konkaR3Zsof.setVbeln(r3_order_id);
					konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
					if (konkaR3Zsof != null) {
						konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
						if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
							konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
							if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
								konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
							}
						}
					}
				}
			}

			// 得到分公司
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				if (null != konkaOrderInfo.getAdd_dept_id()) {
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
					konkaDept.getMap().put("dept_type_eq", 3);
					konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
					if (null != konkaDept && null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
						konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
					}
				}
			}

			// 得到经办
			for (KonkaOrderInfo konkaOrderInfo2 : entityList) {
				if (null != konkaOrderInfo2.getAdd_dept_id()) {
					KonkaDept konkaDept2 = new KonkaDept();
					konkaDept2.setDept_id(konkaOrderInfo2.getAdd_dept_id());
					konkaDept2.getMap().put("dept_type_eq", 4);
					konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
					if (null != konkaDept2 && !("".equals(konkaDept2))) {
						String deptName = konkaDept2.getDept_name();
						if (null != deptName && !("".equals(deptName))) {
							konkaOrderInfo2.getMap().put("jbName", konkaDept2.getDept_name());
						}
					} else {
						KonkaDept konkaDept3 = new KonkaDept();
						konkaDept3.setDept_id(konkaOrderInfo2.getAdd_dept_id());
						konkaDept3.getMap().put("dept_type_eq", 5);
						konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
						if (null != konkaDept3 && !"".equals(konkaDept3)) {
							String deptName = konkaDept3.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo2.getMap().put("jbName", konkaDept3.getDept_name());
							}
						}
					}
				}
			}

			if (null != entityList) {
				for (KonkaOrderInfo orderInfo : entityList) {
					// 0： 待审核 1: 已审核
					orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
				}
			}

			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 5000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
							+ "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + EncryptUtils.encodingFileName("订单查询数据") + ".xls");
				entity.getRow().setCount(recordCount.intValue());
				List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService()
						.getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);
				dynaBean.set("excel_all", excel_all);

				// 从R3得到物流发货信息--ldy
				for (KonkaOrderInfo konkaOrderInfo : entityList) {
					Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
					if (r3_order_id != null) {
						KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
						konkaR3Zsof.setVbeln(r3_order_id);
						konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
						if (konkaR3Zsof != null) {
							konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
							if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
								konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
								if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
									konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
								}
							}
						}
					}
				}

				// 得到分公司
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept6 = new KonkaDept();
						konkaDept6.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept6.getMap().put("dept_type_eq", 3);
						konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
						if (null != konkaDept6 && null != konkaDept6.getDept_name()
								&& !"".equals(konkaDept6.getDept_name())) {
							konkaOrderInfo4.getMap().put("fgsName", konkaDept6.getDept_name());
						}
					}
				}

				// 得到经办
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept4 = new KonkaDept();
						konkaDept4.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept4.getMap().put("dept_type_eq", 4);
						konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
						if (null != konkaDept4 && !("".equals(konkaDept4))) {
							String deptName = konkaDept4.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo4.getMap().put("jbName", konkaDept4.getDept_name());
							}
						} else {
							KonkaDept konkaDept5 = new KonkaDept();
							konkaDept5.setDept_id(konkaOrderInfo4.getAdd_dept_id());
							konkaDept5.getMap().put("dept_type_eq", 5);
							konkaDept5 = super.getFacade().getKonkaDeptService()
									.getKonkaDeptSuperiorByDeptId(konkaDept5);
							if (null != konkaDept5 && !("".equals(konkaDept5))) {
								String deptName = konkaDept5.getDept_name();
								if (null != deptName && !("".equals(deptName))) {
									konkaOrderInfo4.getMap().put("jbName", konkaDept5.getDept_name());
								}
							}
						}
					}
				}
				if (null != entityList1) {
					for (KonkaOrderInfo orderInfo : entityList1) {
						// 0： 待审核 1: 已审核
						orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
					}
				}
				request.setAttribute("allList", entityList1);
				if (is_th != null && is_th.equals("1")) {
					return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
				} else {
					return new ActionForward("/admin/KonkaOrderSearch/listForReport.jsp");
				}
			}
			for (KonkaOrderInfo konkaorderinfo : entityList) {
				// 查待审核角色名称
				KonkaOrderAuditProcessNode kapn = new KonkaOrderAuditProcessNode();
				if (konkaorderinfo.getNext_node_id() != null && !"".equals(konkaorderinfo.getNext_node_id())) {
					kapn.setId(konkaorderinfo.getNext_node_id());
					kapn = super.getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(kapn);
					if (kapn != null) {
						konkaorderinfo.getMap().put("next_audit_role_name", kapn.getRole_name());
					}
				}
				KonkaOrderInfoMessageSend konkaorderinfomessage = new KonkaOrderInfoMessageSend();
				konkaorderinfomessage.setOrder_id(konkaorderinfo.getId());
				Long messagecount = super.getFacade().getKonkaOrderInfoMessageSendService()
						.getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
				konkaorderinfo.getMap().put("messagecount", messagecount);
				if (messagecount != 0L) {
					List<KonkaOrderInfoMessageSend> KonkaOrderInfoMessageSendList = super.getFacade()
							.getKonkaOrderInfoMessageSendService()
							.getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
					if (KonkaOrderInfoMessageSendList != null) {
						konkaorderinfo.getMap().put("KonkaOrderInfoMessageSendList", KonkaOrderInfoMessageSendList);
					}
				}
			}
			request.setAttribute("entityList", entityList);
		}

		// 获取流程列表
		KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
		if (!role_id_lt_30) {
			// 分公司之可见自己的流程
			KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		} else {
			// 超级管理员
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);
			request.setAttribute("processList", processList);
		}
		if (is_th != null && is_th.equals("1")) {
			return new ActionForward("/admin/KonkaOrderSearch/listTH.jsp");
		}
		// 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
		
		request.setAttribute("cs_par_id", userInfo.getDept_id());
        // 分公司下显示的是从分公司下面经营部级别开始的
		KonkaDeptTree t = new KonkaDeptTree();
        t.setDept_id(userInfo.getDept_id());
        t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
        if (null != t) {
            request.setAttribute("cs_par_id",t.getRoot_id());
        }

		return mapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");// 查询类型

		// 设置订单进度条
		setOrderProgress(form, request);

		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.valueOf(order_id));
		order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(order.getCust_id());
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('未查询到网点！');history.back();");
			return null;
		}
		dynaBean.set("r3_code", konkaR3Shop.getR3_code());
		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(Long.valueOf(order_id));
		konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
		if (null == konkaOrderInfo) {
			super.renderJavaScript(response, "alert('未查询到订单！');history.back();");
			return null;
		}

		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(Long.valueOf(order_id));
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditAndRoleList(konkaOrderInfoAudit);
		if (null != konkaOrderInfoAuditWithRoleInfoList && konkaOrderInfoAuditWithRoleInfoList.size() > 0) {
			konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditWithRoleInfoList);
		}
		// 审核流程列表
		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.setAudit_proces_id(konkaOrderInfo.getProcess_id());
		List<KonkaOrderAuditProcessNode> nodeList = super.getFacade().getKonkaOrderAuditProcessNodeService()
				.getKonkaOrderAuditProcessNodeList(node);
		request.setAttribute("nodeList", nodeList);

		super.copyProperties(form, konkaOrderInfo);

		// dynaBean.set("fullName",
		// super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
		dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));

		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

		double good_price_total = 0.00;// 单价总和
		double good_sum_price_total = 0.00;// 金额总和
		double good_discount_total = 0.00;// 折让总和
		double good_discount_price_total = 0.00;// 单台折让金额总和
		double good_discount_pricr_total_all = 0.00;// 折让总金额求和
		for (KonkaOrderInfoDetails ks : konkaOrderInfoDetailsList) {
			if (ks.getGood_price() != null) {
				good_price_total += ks.getGood_price().doubleValue();
			}
			if (ks.getGood_discount() != null) {
				good_discount_total += ks.getGood_discount().doubleValue();
			}
			if (ks.getGood_discount_price() != null) {
				good_discount_price_total += ks.getGood_discount_price().doubleValue();
			}
			if (ks.getGood_sum_price() != null) {
				good_sum_price_total += ks.getGood_sum_price().doubleValue();
			}
			if (ks.getGood_discount_price() != null) {
				good_discount_pricr_total_all += (ks.getGood_discount_price().doubleValue());
			}
		}
		request.setAttribute("good_price_total", good_price_total);
		request.setAttribute("good_sum_price_total", good_sum_price_total);
		request.setAttribute("good_discount_total", good_discount_total);
		request.setAttribute("good_discount_price_total", good_discount_price_total);
		request.setAttribute("good_discount_pricr_total_all", good_discount_pricr_total_all);
		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

		// 取订单流程
		if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(order.getCust_id()));
			if (dept != null) {
				KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
				process.getMap().put("par_add_dept_id", dept.getDept_id());
				List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
						.getKonkaOrderAuditProcessList(process);

				KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
				ap_public.setAdd_dept_id(0L);
				List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
				processList.addAll(ap_publicauditProcesseList);
				request.setAttribute("processList", processList);
			}
		} else {
			KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(order.getCust_id()));
			if (dept != null) {
				KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
				process.getMap().put("par_add_dept_id", dept.getDept_id());
				process.setIs_del(0);
				List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
						.getKonkaOrderAuditProcessList(process);

				KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
				ap_public.setAdd_dept_id(0L);
				ap_public.setIs_del(0);
				List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
						.getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
				processList.addAll(ap_publicauditProcesseList);
				request.setAttribute("processList", processList);
			}
		}

		dynaBean.set("userName", order.getAdd_user_name());
		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(order.getCust_id());
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		if (super.isCallR3Interface(request)) {
			String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
			// List<KNVP> knvpList = super
			// .getFacade()
			// .getR3WebInterfaceService()
			// .getKnvpsList(sales_org, Constants.default_vtweg,
			// Constants.default_spart, konkaR3Shop.getR3_code());
			//
			List<KNVP> knvpList = new ArrayList<KNVP>();
			ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
			info = super
					.getFacade()
					.getR3WebInterfaceService()
					.getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
			if (info.getSuccess() == true) {
				knvpList = info.getDataResult();
			}

			// AG:售达方
			// RE:出票方
			// RG:付款方
			// WE:送达方
			List<KNVP> agList = new ArrayList<KNVP>();
			List<KNVP> reList = new ArrayList<KNVP>();
			List<KNVP> rgList = new ArrayList<KNVP>();
			List<KNVP> weList = new ArrayList<KNVP>();

			if (null != knvpList) {
				for (KNVP cur : knvpList) {
					if ("AG".equalsIgnoreCase(cur.getPARVW())) {
						agList.add(cur);
					} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
						reList.add(cur);
					} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
						rgList.add(cur);
					} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
						weList.add(cur);
					}
				}
			}
			request.setAttribute("agList", agList);
			request.setAttribute("reList", reList);
			request.setAttribute("rgList", rgList);
			request.setAttribute("weList", weList);
		} else {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
			request.setAttribute("re", konkaR3Shop.getR3_code());
			request.setAttribute("rg", konkaR3Shop.getR3_code());

			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
					.getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				weList.add(k);
			}
			request.setAttribute("weList", weList);
		}

		Attachment attachment = new Attachment();
		attachment.setLink_id(Long.valueOf(order_id));
		attachment.setDel_mark(Short.valueOf("0"));
		List<Attachment> attachmentList = getFacade().getAttachmentService().getAttachmentList(attachment);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

		// 获取审核修改记录
		KonkaOrderInfoUpdateRecord updateRecord = new KonkaOrderInfoUpdateRecord();
		updateRecord.setTrade_index(order.getTrade_index());
		List<KonkaOrderInfoUpdateRecord> updateRecordGroupList = super.getFacade()
				.getKonkaOrderInfoUpdateRecordService().getKonkaOrderInfoUpdateRecordGroupList(updateRecord);

		if (null != updateRecordGroupList && updateRecordGroupList.size() > 0) {

			for (KonkaOrderInfoUpdateRecord temp : updateRecordGroupList) {
				KonkaOrderInfoUpdateRecord _record = new KonkaOrderInfoUpdateRecord();
				_record.setAdd_date(temp.getAdd_date());
				_record.setTrade_index(order.getTrade_index());
				_record.getMap().put("order_by_pd_id_asc", true);
				List<KonkaOrderInfoUpdateRecord> recordList = super.getFacade().getKonkaOrderInfoUpdateRecordService()
						.getKonkaOrderInfoUpdateRecordList(_record);
				for (KonkaOrderInfoUpdateRecord rr : recordList) {
					double aa = 0f;
					if (!this.eq_0(rr.getPrice_after().doubleValue())) {
						aa = rr.getDiscount_after().doubleValue() / rr.getPrice_after().doubleValue() * 10000 / 100;
					}
					BigDecimal bb = new BigDecimal(aa);
					rr.setGood_discount_after(bb);
				}
				temp.getMap().put("recordList", recordList);
			}

			request.setAttribute("updateRecordGroupList", updateRecordGroupList);

			if (null != updateRecordGroupList.get(0).getMap().get("recordList")) {
				List<KonkaOrderInfoUpdateRecord> applyRecordList = (ArrayList<KonkaOrderInfoUpdateRecord>) updateRecordGroupList
						.get(0).getMap().get("recordList");
				List<KonkaOrderInfoDetails> applyDetailsList = new ArrayList<KonkaOrderInfoDetails>();
				for (KonkaOrderInfoUpdateRecord temp : applyRecordList) {
					KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
					details.setPd_name(temp.getPd_name());
					details.setGood_count(Integer.valueOf(temp.getNum_before().toString()));
					details.setGood_price(temp.getPrice_before());
					details.setGood_discount_price(temp.getDiscount_before());

					applyDetailsList.add(details);
				}

				request.setAttribute("applyDetailsList", applyDetailsList);
			}

		} else {
			KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
			details.setOrder_id(Long.valueOf(order_id));
			details.getMap().put("order_by_pd_id_asc", true);
			List<KonkaOrderInfoDetails> applyDetailsList = super.getFacade().getKonkaOrderInfoDetailsService()
					.getKonkaOrderInfoDetailsList(details);

			request.setAttribute("applyDetailsList", applyDetailsList);

		}
		if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
			return new ActionForward("/../manager/admin/KonkaOrderSearch/viewReturn.jsp");
		}

		return mapping.findForward("view");
	}

	/**
	 * <p>
	 * 此时，订单已经保存，检查订单上的产品的库存,ajax
	 * </p>
	 * <p>
	 * 没有计算今天的库存,因为订单还未和R3同步
	 * </p>
	 * 
	 */
	public ActionForward checkStockForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isCallR3Interface(request)) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");// 查询类型
		String sales_org = (String) dynaBean.get("sales_org");// 销售组织
		KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
		konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

		// List<KonkaR3OrderLines> itemList = new
		// ArrayList<KonkaR3OrderLines>();
		// for (KonkaOrderInfoDetails konkaOrderInfoDetails2 :
		// konkaOrderInfoDetailsList) {
		// KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
		// konkaR3OrderLines.setMaterial_code(konkaOrderInfoDetails2.getPd_name());
		// itemList.add(konkaR3OrderLines);
		// }
		// List<StockCheckResult> checkResult =
		// getFacade().getR3WebInterfaceService().checkStock(sales_org,
		// itemList);
		// logger.info("json string : {}", JSON.toJSONString(checkResult));
		// super.renderJson(response, JSON.toJSONString(checkResult));

		// add by zhou
		List<StockCheckResult> checkResult2 = new ArrayList<StockCheckResult>();
		List<StockCheckResult> cResult = new ArrayList<StockCheckResult>();
				ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		for (KonkaOrderInfoDetails line : konkaOrderInfoDetailsList) {
			if (line.getStore_key() != null && !"".equals(line.getStore_key().trim())) {
				int beginIndex = line.getStore_key().indexOf("[");
				int endIndex = line.getStore_key().indexOf("]");
				String storeAndLoc = line.getStore_key().substring(beginIndex + 1, endIndex);
				String s[] = storeAndLoc.split("-");
				String store = s[0];
				String loc = s[1];
				//cResult = getFacade().getR3WebInterfaceService().checkStock2(store, loc, line.getPd_name());
				
				info = getFacade().getR3WebInterfaceService().checkStock2(store, loc, line.getPd_name());
				if (info.getSuccess())
					cResult = info.getDataResult();
				
				if (cResult.size() > 0) {
					checkResult2.add(cResult.get(0));
				}
			} else {
				List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
				KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
				konkaR3OrderLines.setMaterial_code(line.getPd_name());
				itemList.add(konkaR3OrderLines);
				// cResult =
				// getFacade().getR3WebInterfaceService().checkStock(sales_org,
				// itemList);
				info = getFacade().getR3WebInterfaceService().checkStock(sales_org, itemList);
				if (info.getSuccess())
					cResult = info.getDataResult();

				if (cResult.size() > 0) {
					checkResult2.add(cResult.get(0));
				}
			}
		}
		logger.info("json string : {}", JSON.toJSONString(checkResult2));
		super.renderJson(response, JSON.toJSONString(checkResult2));
		// add by zhou
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String order_id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(order_id)) {
			saveError(request, "errors.long", new String[] { order_id });
			return mapping.findForward("list");
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setId(Long.valueOf(order_id));
		entity.setIs_del(1);
		super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * @author Wang,Kunlin
	 * @date 2014-03-31
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String order_id = (String) dynaBean.get("id");
		String r3_id = (String) dynaBean.get("r3_id");
		String cust_id = (String) dynaBean.get("cust_id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (!GenericValidator.isLong(order_id)) {
			saveError(request, "errors.long", new String[] { order_id });
			return mapping.findForward("list");
		}
		if (StringUtil.isEmpty(r3_id)) {
			KonkaOrderInfo entity = new KonkaOrderInfo();
			entity.setId(Long.valueOf(order_id));
			entity.setAudit_state(4);
			entity.setCust_id(Long.valueOf(cust_id));
			super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);
			saveMessage(request, "entity.cancel.success");
		} else {
			// 已经存在61号
			String opername = userInfo.getUser_name();
			if (StringUtil.isEmpty(opername)) {
				saveError(request, "errors.long", new String[] { opername });
				return mapping.findForward("list");
			}
			ReturnInfo returninfo = super.getFacade().getR3WebInterfaceService()
					.saveKonkaOrderInfoDestory(r3_id, opername);
			if (returninfo != null) {
				if (returninfo.getSuccess()) {
					KonkaOrderInfo entity = new KonkaOrderInfo();
					entity.setId(Long.valueOf(order_id));
					entity.setAudit_state(4);
					entity.setCust_id(Long.valueOf(cust_id));
					entity.setSync_date(sdf.format(new Date()));
					entity.setSync_user(userInfo.getUser_name());
					entity.setSync_state(1);
					super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);
					saveMessage(request, "entity.cancel.success");
					String errorMsg = "";
					for (ExcuteMsg msg : (List<ExcuteMsg>) returninfo.getMsgList()) {
						errorMsg += msg.getType() + ":" + msg.getMessage() + "\n";
					}
					saveMessage(request, errorMsg);
				} else {
					String errorMsg = "";
					for (ExcuteMsg msg : (List<ExcuteMsg>) returninfo.getMsgList()) {
						errorMsg += msg.getType() + ":" + msg.getMessage() + "\n";
					}
					saveMessage(request, errorMsg);
				}
			}
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward odrerTb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String order_id = (String) dynaBean.get("id");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (!GenericValidator.isLong(order_id)) {
			saveError(request, "errors.long", new String[] { order_id });
			return mapping.findForward("list");
		}

		// 潜在bug
		// 2 614315056 LSH20140416132003769
		// 3 614319074 LSH140419144501959221
		// 2 614320629 LSH140421112550896189
		// 2 614324486 LSH140423154927145539
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.valueOf(order_id));
		order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);

		// 2014/08/04
		// if (null != order.getR3_id()) {
		// super.renderJavaScript(response,
		// "alert('该订单已经同步，请不要重复提交！');history.back();");
		// return null;
		// }

		// 取客户的销售组织
		if (null != order.getCust_id()) {
			KonkaR3Shop kR3Shop = new KonkaR3Shop();
			kR3Shop.setId(order.getCust_id());
			kR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kR3Shop);
			if (kR3Shop != null)
				order.setSales_org(kR3Shop.getR3_sales_org_code());
		}

		KonkaOrderInfoDetails d = new KonkaOrderInfoDetails();
		d.setOrder_id(order.getId());
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = super.getFacade().getKonkaOrderInfoDetailsService()
				.getKonkaOrderInfoDetailsList(d);

		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList2 = new ArrayList<KonkaOrderInfoDetails>();
		// 商品数量为0的产品行记录不同步到R3中--begin
		for (KonkaOrderInfoDetails konkaorderinfodetails : konkaOrderInfoDetailsList) {
			if (konkaorderinfodetails.getGood_count() == 0) {
				konkaOrderInfoDetailsList2.add(konkaorderinfodetails);
			}
		}
		konkaOrderInfoDetailsList.removeAll(konkaOrderInfoDetailsList2);
		// 商品数量为0的产品行记录不同步到R3中--end

		// 生成订单行项目号10,20,30
		// 订单保存入口太多.所以在此处理
		// 为订单数量不为0的订单行生成连续的项目号
		if (konkaOrderInfoDetailsList.size() > 0) {
			long i = 0;
			for (KonkaOrderInfoDetails k : konkaOrderInfoDetailsList) {
				i = i + 10;
				if (k.getItemno() == null || k.getItemno() == 0L) {
					k.setItemno(i);
					super.getFacade().getKonkaOrderInfoDetailsService().modifyKonkaOrderInfoDetails(k);
				}
			}
			for (KonkaOrderInfoDetails ko : konkaOrderInfoDetailsList2) {
				i = i + 10;
				if (ko.getItemno() == null || ko.getItemno() == 0L) {
					ko.setItemno(i);
					super.getFacade().getKonkaOrderInfoDetailsService().modifyKonkaOrderInfoDetails(ko);
				}
			}
		}

		// 重新过滤订单数量为0的数据
		konkaOrderInfoDetailsList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(d);
		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList3 = new ArrayList<KonkaOrderInfoDetails>();
		// 商品数量为0的产品行记录不同步到R3中--begin
		for (KonkaOrderInfoDetails konkaorderinfodetails : konkaOrderInfoDetailsList) {
			if (konkaorderinfodetails.getGood_count() == 0) {
				konkaOrderInfoDetailsList3.add(konkaorderinfodetails);
			}
		}
		konkaOrderInfoDetailsList.removeAll(konkaOrderInfoDetailsList3);
		order.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

		order.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		ReturnInfo ret = new ReturnInfo();
		// 把订单写R3订单接口
		if (order.getR3_id() != null && order.getR3_id() != 0) {
			// 修改订单
			ret = super.getFacade().getR3WebInterfaceService()
					.modifyKonkaOrderInfo(order, String.valueOf(order.getR3_id()), userInfo.getUser_name());
		} else {
			if ("ZFRE".equals(order.getDoc_type())) {
				// 新增退货订单 ZFRE
				ret = super.getFacade().getR3WebInterfaceService()
						.saveKonkaOrderInfoReturn(order, userInfo.getUser_name());
			} else {
				// 新增订单ZFOR,ZFGC,ZFCR
				ret = super.getFacade().getR3WebInterfaceService().saveKonkaOrderInfo(order, userInfo.getUser_name());
			}
		}

		if (ret.getSuccess()) {
			// 客户订单
			KonkaOrderInfo updated = new KonkaOrderInfo();
			updated.setId(order.getId());
			updated.setR3_id(Long.valueOf(ret.getItemNO()));
			updated.setSync_state(1);
			updated.setSync_date(sdf.format(new Date()));
			updated.setSync_user(userInfo.getUser_name());

			if (order.getZmd_order_flag() == 1) {
				// 专卖店订单
				KonkaXxSellBill entity = new KonkaXxSellBill();
				entity.setSell_bill_id(order.getZmd_order_num());
				PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

				entity.setAudit_date(new Date());
				entity.setAudit_user_id(ui.getId());
				entity.setAudit_real_name(ui.getReal_name());

				entity.setDist_date(new Date());
				entity.setSell_state(20L);

				updated.setKonkaXxSellBill(entity);
			}
			super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(updated);
			saveDirectlyMessage(request, "成功同步订单:" + ret.getItemNO());
		} else {
			// 无法生成r3订单,返回异常信息!
			List<ExcuteMsg> msgList = ret.getMsgList();
			StringBuilder sb = new StringBuilder();
			int i = 0;
			if (null != msgList) {
				for (ExcuteMsg e : msgList) {
					sb.append(++i).append(". [").append(e.getType()).append("] ").append(e.getMessage())
							.append("<br />");
				}
			}
			super.saveError(request, "error.order.r3.unupload", sb.toString());
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public boolean eq_0(double f) {
		double EPSINON = 0.0000000001f;
		return f < EPSINON && f > -EPSINON;
	}

	/**
	 * 同步所有订单物流信息
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
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		Boolean role_id_lt_30 = false;
		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
				role_id_lt_30 = true;
			}
		}

		// SELECT t.id,
		// t.IS_SH,
		// t.IS_DELIVERED,
		// t.SYNC_STATE,
		// t.TRADE_INDEX,
		// t.R3_ID
		// FROM KONKA_ORDER_INFO t
		// WHERE t.R3_ID IS NOT NULL AND t.IS_SH <> 1 AND t.SYNC_STATE = 1

		// 拿到需要同步的订单
		KonkaOrderInfo entity = new KonkaOrderInfo();

		// 已同步但没有收货的订单
		List<KonkaOrderInfo> entityList = new ArrayList<KonkaOrderInfo>();
		if (!role_id_lt_30) {// 如果不是系统管理员
			entity.getMap().put("par_dept_id", userInfo.getDept_id());
			entity.getMap().put("r3_id_not_null", "y");
			entity.setSync_state(1);
			entity.setIs_sh(0);
			entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoList(entity);
		} else {// 如果不是系统管理员
			entity.getMap().put("r3_id_not_null", "y");
			entity.setSync_state(1);
			entity.setIs_sh(0);
			entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoList(entity);
		}

		// 从R3得到物流发货信息--ldy
		for (KonkaOrderInfo konkaOrderInfo : entityList) {
			Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
			if (r3_order_id != null) {
				// 得到本地ZSOF数据：
				KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
				konkaR3Zsof.setVbeln(r3_order_id);
				konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);

				ZSOF zsof = new ZSOF();
				// can be null
				zsof = super.getFacade().getR3WebInterfaceService().getR3Delivery(r3_order_id);

				// 本地无记录:
				// zsof.getVBEDL() 物流号
				if (konkaR3Zsof == null && zsof != null && zsof.getVBEDL() != null) {
					KonkaR3Zsof konkaR3Zsof2 = new KonkaR3Zsof();
					konkaR3Zsof2.setVbedl(zsof.getVBEDL());
					if (!("0000-00-00").equals(zsof.getLFDAT())) {
						konkaR3Zsof2.setLfdat(zsof.getLFDAT());
					}
					if (!("0000-00-00").equals(zsof.getSHDAT())) {
						konkaR3Zsof2.setShdat(zsof.getSHDAT());
					}
					konkaR3Zsof2.setErdat(zsof.getERDAT());
					konkaR3Zsof2.setGjahr(zsof.getGJAHR());
					konkaR3Zsof2.setKunnr(zsof.getKUNNR());
					konkaR3Zsof2.setMonat(zsof.getMONAT());
					konkaR3Zsof2.setName1(zsof.getNAME1());
					konkaR3Zsof2.setVbeln(zsof.getVBELN());
					konkaR3Zsof2.setVkorg(zsof.getVKORG());
					konkaR3Zsof2.setVtext(zsof.getVTEXT());
					super.getFacade().getKonkaR3ZsofService().createKonkaR3Zsof(konkaR3Zsof2);
				}

				// 本地已经有记录:
				if (konkaR3Zsof != null && zsof != null && zsof.getVBEDL() != null) {
					konkaR3Zsof.setVbedl(zsof.getVBEDL());
					if (!("0000-00-00").equals(zsof.getLFDAT())) {
						konkaR3Zsof.setLfdat(zsof.getLFDAT());
					}
					if (!("0000-00-00").equals(zsof.getSHDAT())) {
						konkaR3Zsof.setShdat(zsof.getSHDAT());
					}
					konkaR3Zsof.setVbeln(r3_order_id);
					konkaR3Zsof.setErdat(zsof.getERDAT());
					konkaR3Zsof.setGjahr(zsof.getGJAHR());
					konkaR3Zsof.setKunnr(zsof.getKUNNR());
					konkaR3Zsof.setMonat(zsof.getMONAT());
					konkaR3Zsof.setName1(zsof.getNAME1());
					konkaR3Zsof.setVbeln(zsof.getVBELN());
					konkaR3Zsof.setVkorg(zsof.getVKORG());
					konkaR3Zsof.setVtext(zsof.getVTEXT());
					super.getFacade().getKonkaR3ZsofService().modifyKonkaR3Zsof(konkaR3Zsof);
				}
			}
		}

		int count = 0;
		for (KonkaOrderInfo konkaOrderInfo : entityList) {
			KonkaR3Zsof of = new KonkaR3Zsof();
			of.setVbeln(konkaOrderInfo.getR3_id());
			of = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(of);
			if (of != null && konkaOrderInfo.getId() != null) {
				KonkaOrderInfo k = new KonkaOrderInfo();
				k.setId(konkaOrderInfo.getId());
				k.setR3_id(konkaOrderInfo.getR3_id());
				if (of.getLfdat() != null && !"".equals(of.getLfdat())) {
					k.setIs_delivered(1);
				}
				if (of.getShdat() != null && !"".equals(of.getShdat())) {
					k.setIs_sh(1);
				}
				super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(k);
				count++;
			}
		}
		saveDirectlyMessage(request, "订单物流信息更新成功,条数为:" + count);
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	// 单条同步物流信息
	public ActionForward orderWl(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String order_id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(order_id)) {
			saveError(request, "errors.long", new String[] { order_id });
			return mapping.findForward("list");
		}
		KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
		konkaOrderInfo.setId(Long.valueOf(order_id));
		konkaOrderInfo = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

		Long r3_order_id = konkaOrderInfo.getR3_id();
		// 得到本地ZSOF数据：
		KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
		konkaR3Zsof.setVbeln(r3_order_id);
		konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
		// 得到R3物流数据
		ZSOF zsof = new ZSOF();
		zsof = super.getFacade().getR3WebInterfaceService().getR3Delivery(r3_order_id);
		// 本地无记录:
		// zsof.getVBEDL() 物流号
		if (konkaR3Zsof == null && zsof != null && zsof.getVBEDL() != null) {
			KonkaR3Zsof konkaR3Zsof2 = new KonkaR3Zsof();
			konkaR3Zsof2.setVbedl(zsof.getVBEDL());
			if (!("0000-00-00").equals(zsof.getLFDAT())) {
				konkaR3Zsof2.setLfdat(zsof.getLFDAT());
			}
			if (!("0000-00-00").equals(zsof.getSHDAT())) {
				konkaR3Zsof2.setShdat(zsof.getSHDAT());
			}
			konkaR3Zsof2.setErdat(zsof.getERDAT());
			konkaR3Zsof2.setGjahr(zsof.getGJAHR());
			konkaR3Zsof2.setKunnr(zsof.getKUNNR());
			konkaR3Zsof2.setMonat(zsof.getMONAT());
			konkaR3Zsof2.setName1(zsof.getNAME1());
			konkaR3Zsof2.setVbeln(zsof.getVBELN());
			konkaR3Zsof2.setVkorg(zsof.getVKORG());
			konkaR3Zsof2.setVtext(zsof.getVTEXT());
			super.getFacade().getKonkaR3ZsofService().createKonkaR3Zsof(konkaR3Zsof2);
		}

		// 本地已经有记录:
		if (konkaR3Zsof != null && zsof != null && zsof.getVBEDL() != null) {
			konkaR3Zsof.setVbedl(zsof.getVBEDL());
			if (!("0000-00-00").equals(zsof.getLFDAT())) {
				konkaR3Zsof.setLfdat(zsof.getLFDAT());
			}
			if (!("0000-00-00").equals(zsof.getSHDAT())) {
				konkaR3Zsof.setShdat(zsof.getSHDAT());
			}
			konkaR3Zsof.setErdat(zsof.getERDAT());
			konkaR3Zsof.setGjahr(zsof.getGJAHR());
			konkaR3Zsof.setKunnr(zsof.getKUNNR());
			konkaR3Zsof.setMonat(zsof.getMONAT());
			konkaR3Zsof.setName1(zsof.getNAME1());
			konkaR3Zsof.setVbeln(zsof.getVBELN());
			konkaR3Zsof.setVkorg(zsof.getVKORG());
			konkaR3Zsof.setVtext(zsof.getVTEXT());
			super.getFacade().getKonkaR3ZsofService().modifyKonkaR3Zsof(konkaR3Zsof);
		}

		int count = 0;
		// 重新取物流数据
		KonkaR3Zsof of = new KonkaR3Zsof();
		of.setVbeln(konkaOrderInfo.getR3_id());
		of = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(of);
		if (of != null && konkaOrderInfo.getId() != null) {
			KonkaOrderInfo k = new KonkaOrderInfo();
			k.setId(konkaOrderInfo.getId());
			k.setR3_id(konkaOrderInfo.getR3_id());
			if (of.getLfdat() != null && !"".equals(of.getLfdat())) {
				k.setIs_delivered(1);
			}
			if (of.getShdat() != null && !"".equals(of.getShdat())) {
				k.setIs_sh(1);
			}
			super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(k);
			count++;
		}
		saveDirectlyMessage(request, "订单物流信息更新成功,条数为:" + count);
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward sendmsg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");
		if (StringUtil.isEmpty(order_id)) {
			return null;
		}
		// 判断是否要发短信
		KonkaOrderInfo order1 = new KonkaOrderInfo();
		order1.setId(Long.valueOf(order_id));
		order1 = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order1);
		/** 取网点业务员 */

		KonkaOrderInfoMessageSend t = new KonkaOrderInfoMessageSend();
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(order1.getCust_id());
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser sender = new PeProdUser();
			sender.setId(bagn.getUser_id());
			sender = getFacade().getPeProdUserService().getPeProdUserResult(sender);
			t.setSender_name(sender.getReal_name());
			t.setSender_code(sender.getId().toString());
			t.setSender_phone(sender.getLink_phone());
		}
		t.setOrder_id(Long.valueOf(order_id));
		PeProdUser receiver = new PeProdUser();
		receiver.setId(order1.getAdd_user_id());
		receiver = super.getFacade().getPeProdUserService().getPeProdUserResult(receiver);
		t.setReceiver_name(receiver.getReal_name());
		t.setReceiver_code(receiver.getId().toString());
		t.setReceiver_phone(receiver.getLink_phone());
		t.setSend_date(new Date());
		if (null != receiver.getLink_phone()) {
			SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy年MM月dd日");
			String date = dateformat2.format(new Date());
			String msg = receiver.getReal_name() + "您好,您的订单[" + order1.getTrade_index() + "]已于[" + date
					+ " ]进行内容变更，请使用您的账号登陆查询![http://vip.konka.com]。若有疑问，请及时联系康佳业务员（" + t.getSender_name() + "）["
					+ t.getSender_phone() + "]";
			if (getSendMessageResult(receiver.getLink_phone(), msg)) {
				super.getFacade().getKonkaOrderInfoMessageSendService().createKonkaOrderInfoMessageSend(t);

                // -1订单已被修改（等待客户确认），
                // 0订单未被修改（初始状态），
                // 1客户已确认.此状态在客户撤回后需要重置

				order1.setKh_confirm_state(1);
				super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(order1);
				EcMessage ecm = new EcMessage();
				ecm.setAdd_date(new Date());
				ecm.setContent(msg);
				ecm.setMobile(receiver.getLink_phone());
				ecm.setOrder_id(order1.getId().toString());
				ecm.setSend_date(new Date());
				ecm.setOrder_state(1);
				ecm.setSend_state(0);
				super.getFacade().getEcMessageService().createEcMessage(ecm);
			}
		}

		JSONObject jonsObject = new JSONObject();
		KonkaOrderInfoMessageSend konkaorderinfomessage = new KonkaOrderInfoMessageSend();
		konkaorderinfomessage.setOrder_id(Long.valueOf(order_id));
		Long messagecount = super.getFacade().getKonkaOrderInfoMessageSendService()
				.getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
		if (messagecount != null) {
			jonsObject.put("messagecount", messagecount);
			List<KonkaOrderInfoMessageSend> KonkaOrderInfoMessageSendList = super.getFacade()
					.getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
			if (KonkaOrderInfoMessageSendList != null) {
				jonsObject.put("konkaorderinfomessagesendlist", KonkaOrderInfoMessageSendList);

			}
		}
		super.renderJson(response, jonsObject.toString());
		return null;

	}

	public ActionForward listdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String kh_confirm_state = (String) dynaBean.get("kh_confirm_state");
		String order_date_start = (String) dynaBean.get("order_date_start");
		String order_date_end = (String) dynaBean.get("order_date_end");
		String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
		String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
		String process_id = (String) dynaBean.get("process_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String pay_type = (String) dynaBean.get("pay_type");
		String send_type = (String) dynaBean.get("send_type");
		String audit_state = (String) dynaBean.get("audit_state");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String is_delivered = (String) dynaBean.get("is_delivered");
		String ag_like = (String) dynaBean.get("ag_like");// 订单表里的ag 就是客户的R3编码
		String r3_id = (String) dynaBean.get("r3_id");
		String sync_state = (String) dynaBean.get("sync_state");
		String excel_all = (String) dynaBean.get("excel_all");
		String doc_type = (String) dynaBean.get("doc_type");
		String order_type = (String) dynaBean.get("order_type");
		String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货
		String vbedl_like = (String) dynaBean.get("vbedl_like");// 26单号
		String vbedl_null = (String) dynaBean.get("vbedl_null");

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		dynaBean.set("user_id", userInfo.getId());// 用户

		boolean role_id_lt_30 = false; // 是否为系统管理员
		// boolean role_id_eq_30 = false; // 是否为分公司管理员
		// boolean role_id_eq_34 = false; // 是否为分公司总经理
		boolean role_id_eq_38 = false; // 是否为分公司产品会计
		// boolean role_id_eq_39 = false; // 是否为分公司财务经理
		boolean role_id_eq_41 = false; // 是否为青岛产品会计
		// boolean role_id_eq_41 = false; // 是否为青岛产品会计
		boolean role_id_eq_33 = false; // 是否为泉州产品会计
		boolean role_id_eq_54 = false; // 是否为乐山产品会计
		boolean role_id_eq_55 = false; // 是否为西昌产品会计
		boolean role_id_eq_56 = false; // 是否为广元产品会计
		boolean role_id_eq_43 = false; // 是否为万州产品会计
		boolean role_id_eq_44 = false; // 是否为芜湖产品会计
		boolean role_id_eq_46 = false; // 是否为温州产品会计

		boolean role_id_eq_57 = false; // 是否为分公司物流经理
		boolean role_id_eq_30 = false; // 是否为分公司管理员

		/*
		 * 1 33 分公司职务-厦门 泉州产品会计 2014-04-02 17:48:57 权限 | 人员 | 授权 | 修改 | 删除 2 38
		 * 系统职务 分公司产品会计 2013-08-20 09:58:50 权限 | 人员 | 授权 | 修改 | 删除 3 41 系统职务
		 * 青岛产品会计 2013-10-31 17:54:56 权限 | 人员 | 授权 | 修改 | 删除 4 54 分公司职务-成都
		 * 乐山产品会计 2014-03-28 10:42:31 权限 | 人员 | 授权 | 修改 | 删除 5 55 分公司职务-成都
		 * 西昌产品会计 2014-03-28 10:42:51 权限 | 人员 | 授权 | 修改 | 删除 6 56 分公司职务-成都
		 * 广元产品会计
		 */

		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
				role_id_lt_30 = true;
			}
			// if (peRoleUser.getRole_id() == 30L) {
			// role_id_eq_30 = true;
			// }
			// if (peRoleUser.getRole_id() == 34L) {
			// role_id_eq_34 = true;
			// }
			if (peRoleUser.getRole_id() == 38L) {
				role_id_eq_38 = true;
			}
			// if (peRoleUser.getRole_id() == 39L) {
			// role_id_eq_39 = true;
			// }
			if (peRoleUser.getRole_id() == 41L) {
				role_id_eq_41 = true;
			}

			if (peRoleUser.getRole_id() == 33L) {
				role_id_eq_33 = true;
			}
			if (peRoleUser.getRole_id() == 54L) {
				role_id_eq_54 = true;
			}
			if (peRoleUser.getRole_id() == 55L) {
				role_id_eq_55 = true;
			}
			if (peRoleUser.getRole_id() == 56L) {
				role_id_eq_56 = true;
			}
			if (peRoleUser.getRole_id() == 43L) {
				role_id_eq_43 = true;
			}
			if (peRoleUser.getRole_id() == 44L) {
				role_id_eq_44 = true;
			}
			if (peRoleUser.getRole_id() == 46L) {
				role_id_eq_46 = true;
			}
			if (peRoleUser.getRole_id() == 57L) {
				role_id_eq_57 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}

		}

		request.setAttribute("role_id_eq_33", role_id_eq_33);
		request.setAttribute("role_id_eq_54", role_id_eq_54);
		request.setAttribute("role_id_eq_55", role_id_eq_55);
		request.setAttribute("role_id_eq_56", role_id_eq_56);

		request.setAttribute("role_id_eq_38", role_id_eq_38);
		request.setAttribute("role_id_eq_41", role_id_eq_41);
		request.setAttribute("role_id_eq_43", role_id_eq_43);

		request.setAttribute("role_id_eq_44", role_id_eq_44);
		request.setAttribute("role_id_eq_46", role_id_eq_46);

		request.setAttribute("role_id_eq_30", role_id_eq_30);
		request.setAttribute("role_id_eq_57", role_id_eq_57);

		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		// calendar.add(Calendar.DATE, -30);
		calendar.add(Calendar.MONTH, -1);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);

		if (StringUtils.isNotBlank(order_date_start) && StringUtils.isNotBlank(order_date_end)) {
			Date start_date = DateUtils.parseDate(order_date_start, new String[] { "yyyy-MM-dd" });
			Date end_date = DateUtils.parseDate(order_date_end, new String[] { "yyyy-MM-dd" });
			// 判断下单日期间隔是否小于90天
			if ((end_date.getTime() - start_date.getTime()) > 90 * 24 * 60 * 60 * 1000L) { // 超过90天
				String msg = super.getMessage(request, "order.date.over.90.day");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		} else {
			dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
			dynaBean.set("order_date_end", day_last);
		}

		KonkaOrderInfo entity = new KonkaOrderInfo();
		entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
		entity.setIs_del(0);// 0:表示未删除；1：表示删除

		if (("1").equals(vbedl_null)) {
			entity.getMap().put("vbedl_null", vbedl_null);
		}
		if (("0").equals(vbedl_null)) {
			entity.getMap().put("vbedl_not_null", vbedl_null);
		}

		if (StringUtils.isNotBlank(order_date_start)) {
			entity.getMap().put("start_date", order_date_start + " 00:00:00");
		} else {
			entity.getMap().put("start_date", day_first + " 00:00:00");
		}
		if (StringUtils.isNotBlank(order_date_end)) {
			entity.getMap().put("end_date", order_date_end + " 23:59:59");
		} else {
			entity.getMap().put("end_date", day_last + " 23:59:59");
		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if (StringUtils.isNotBlank(process_id)) {
			entity.setProcess_id(Long.valueOf(process_id));
		}
		if (StringUtils.isNotBlank(user_shop_name_like)) {
			entity.getMap().put("user_shop_name_like", user_shop_name_like);
		}
		if (StringUtils.isNotBlank(pay_type)) {
			entity.setPay_type(Integer.valueOf(pay_type));
		}
		if (StringUtils.isNotBlank(r3_id)) {
			entity.setR3_id(Long.valueOf(r3_id));
		}
		if (StringUtils.isNotBlank(sync_state)) {
			entity.setSync_state(Integer.valueOf(sync_state));
		}
		if (is_th != null && is_th.equals("1")) {
			entity.setDoc_type("ZFRE");
		} else {
			if (StringUtils.isNotBlank(doc_type)) {
				entity.setDoc_type(doc_type);
			}
		}

        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
		if (StringUtils.isNotBlank(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (StringUtils.isNotBlank(send_type)) {
			entity.setSend_type(Integer.valueOf(send_type));
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(vbedl_like)) {
			entity.getMap().put("vbedl_like", vbedl_like);
		}
		if (StringUtils.isNotBlank(is_delivered)) {
			entity.setIs_delivered(Integer.valueOf(is_delivered));
		}
		if (StringUtils.isNotBlank(ag_like)) {
			entity.getMap().put("ag_like", ag_like);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("par_dept_id", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("par_dept_id", l4_dept_id);
		} else if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("par_dept_id", dept_id);
		}

		if (GenericValidator.isInt(audit_state)) {
			// 按订单状态查询
			int state = Integer.valueOf(audit_state);
			switch (state) {
			case 10: // 系统管理员-审核中
				entity.getMap().put("audit_state_lt", 3);
				break;
			case 11: // 系统管理员-已完成
				entity.getMap().put("audit_state_eq", 3);
				break;
			case 20: // 非系统管理员-待审核
				entity.getMap().put("audit_state_2", audit_state);
				entity.getMap().put("audit_state_eq_20", audit_state);
				break;
			case 21: // 非系统管理员-已审核
				entity.getMap().put("audit_state_2", audit_state);
				entity.getMap().put("audit_state_eq_21", audit_state);
				break;
			case 31: // 系统管理员-已作废
				entity.getMap().put("audit_state_eq", 4);
				break;
			}
		}

		if (StringUtils.isNotBlank(kh_confirm_state)) {
			entity.setKh_confirm_state(Integer.valueOf(kh_confirm_state));
		}

		if (role_id_lt_30) { // 系统管理员登录
			dynaBean.set("dept_type", "1");
			Long recordCount = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setCount(pager.getRowCount());
			entity.getRow().setFirst(pager.getFirstRow());

			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoAndNextRoleResultForPaginatedList1(entity);
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				BranchAssign bagn = new BranchAssign();
				bagn.setKonka_r3_id(konkaOrderInfo.getCust_id());
				bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
				if (null != bagn && null != bagn.getUser_id()) {
					PeProdUser ywy = new PeProdUser();
					ywy.setId(bagn.getUser_id());
					ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
					konkaOrderInfo.getMap().put("ywy", ywy.getReal_name());
				}

			}

			// 从R3得到物流发货信息--ldy
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
				if (r3_order_id != null) {
					KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
					konkaR3Zsof.setVbeln(r3_order_id);
					konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
					if (konkaR3Zsof != null) {
						konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
						if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
							konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
							if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
								konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
							}
						}
					}
				}
			}

			// 得到分公司
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				if (null != konkaOrderInfo.getAdd_dept_id()) {
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
					konkaDept.getMap().put("dept_type_eq", 3);
					konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
					if (konkaDept != null) {
						if (null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
							konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
						}
					}
				}
			}

			// 得到经办
			for (KonkaOrderInfo konkaOrderInfo2 : entityList) {
				if (null != konkaOrderInfo2.getAdd_dept_id()) {
					KonkaDept konkaDept2 = new KonkaDept();
					konkaDept2.setDept_id(konkaOrderInfo2.getAdd_dept_id());
					konkaDept2.getMap().put("dept_type_eq", 4);
					konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
					if (null != konkaDept2 && !("".equals(konkaDept2))) {
						String deptName = konkaDept2.getDept_name();
						if (null != deptName && !("".equals(deptName))) {
							konkaOrderInfo2.getMap().put("jbName", konkaDept2.getDept_name());
						}
					} else {
						KonkaDept konkaDept3 = new KonkaDept();
						konkaDept3.setDept_id(konkaOrderInfo2.getAdd_dept_id());
						konkaDept3.getMap().put("dept_type_eq", 5);
						konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
						if (konkaDept3 != null && null != konkaDept3.getDept_name()
								&& !("".equals(konkaDept3.getDept_name()))) {
							String deptName = konkaDept3.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo2.getMap().put("jbName", konkaDept3.getDept_name());
							}
						}
					}
				}
			}

			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 5000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
							+ "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + EncryptUtils.encodingFileName("订单查询数据") + ".xls");
				entity.getRow().setCount(recordCount.intValue());
				List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService()
						.getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);
				dynaBean.set("excel_all", excel_all);

				// 从R3得到物流发货信息--ldy
				for (KonkaOrderInfo konkaOrderInfo : entityList) {
					Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
					if (r3_order_id != null) {
						KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
						konkaR3Zsof.setVbeln(r3_order_id);
						konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
						if (konkaR3Zsof != null) {
							konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
							if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
								konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
								if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
									konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
								}
							}
						}
					}
				}

				// 得到分公司
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept6 = new KonkaDept();
						konkaDept6.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept6.getMap().put("dept_type_eq", 3);
						konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
						if (null != konkaDept6 && null != konkaDept6.getDept_name()
								&& !"".equals(konkaDept6.getDept_name())) {
							konkaOrderInfo4.getMap().put("fgsName", konkaDept6.getDept_name());
						}
					}
				}

				// 得到经办
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept4 = new KonkaDept();
						konkaDept4.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept4.getMap().put("dept_type_eq", 4);
						konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
						if (null != konkaDept4 && !("".equals(konkaDept4))) {
							String deptName = konkaDept4.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo4.getMap().put("jbName", konkaDept4.getDept_name());
							}
						} else {
							KonkaDept konkaDept5 = new KonkaDept();
							konkaDept5.setDept_id(konkaOrderInfo4.getAdd_dept_id());
							konkaDept5.getMap().put("dept_type_eq", 5);
							konkaDept5 = super.getFacade().getKonkaDeptService()
									.getKonkaDeptSuperiorByDeptId(konkaDept5);
							if (null != konkaDept5 && !("".equals(konkaDept5))) {
								String deptName = konkaDept5.getDept_name();
								if (null != deptName && !("".equals(deptName))) {
									konkaOrderInfo4.getMap().put("jbName", konkaDept5.getDept_name());
								}
							}
						}
					}
				}
				if (null != entityList1) {
					for (KonkaOrderInfo orderInfo : entityList1) {
						// 0： 待审核 1: 已审核
						orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
					}
				}
				request.setAttribute("allList", entityList1);
				if (is_th != null && is_th.equals("1")) {
					return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
				} else {
					return new ActionForward("/admin/KonkaOrderSearch/listForReport.jsp");
				}
			}
			request.setAttribute("entityList", entityList);

		} else { // 非系统管理员登录
			dynaBean.set("dept_type", "2");

			// if (StringUtils.isBlank(audit_state) && (role_id_eq_30 ||
			// role_id_eq_34 || role_id_eq_38 || role_id_eq_39)) {
			// //
			// 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
			// KonkaDept fgs_dept =
			// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			// if (null != fgs_dept) {
			// entity.getMap().put("par_or_children_dept_id",
			// fgs_dept.getDept_id());
			// }
			// } else {
			// entity.getMap().put("no_sys_man_user_id", userInfo.getId()); //
			// 表示需要当前用户角色审核的订单
			// entity.getMap().put("par_or_children_dept_id",
			// userInfo.getDept_id());
			// }

			// 数据级别判断开始
			Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			request.setAttribute("max_dlevel", max_dlevel);
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				break;
			case 0:
				break;
			default:
				// 出错
			}
			// 数据级别判断结束

			entity.getMap().put("par_or_children_dept_id", __dept_id);

			if (StringUtils.isBlank(audit_state) && (max_dlevel >= 7)) {
				// 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
				// KonkaDept fgs_dept =
				// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
				// if (null != fgs_dept) {
				// entity.getMap().put("par_or_children_dept_id",
				// fgs_dept.getDept_id());
				// }
			} else {
				entity.getMap().put("no_sys_man_user_id", userInfo.getId()); // 表示需要当前用户角色审核的订单
				// entity.getMap().put("par_or_children_dept_id",
				// userInfo.getDept_id());
			}

			Long recordCount = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setCount(pager.getRowCount());
			entity.getRow().setFirst(pager.getFirstRow());

			List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService()
					.getKonkaOrderInfoAndNextRoleResultForPaginatedList1(entity);

			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				BranchAssign bagn = new BranchAssign();
				bagn.setKonka_r3_id(konkaOrderInfo.getCust_id());
				bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
				if (null != bagn && null != bagn.getUser_id()) {
					PeProdUser ywy = new PeProdUser();
					ywy.setId(bagn.getUser_id());
					ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
					konkaOrderInfo.getMap().put("ywy", ywy.getReal_name());
				}

			}

			// 从R3得到物流发货信息--ldy
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
				if (r3_order_id != null) {
					KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
					konkaR3Zsof.setVbeln(r3_order_id);
					konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
					if (konkaR3Zsof != null) {
						konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
						if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
							konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
							if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
								konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
							}
						}
					}
				}
			}

			// 得到分公司
			for (KonkaOrderInfo konkaOrderInfo : entityList) {
				if (null != konkaOrderInfo.getAdd_dept_id()) {
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
					konkaDept.getMap().put("dept_type_eq", 3);
					konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
					if (null != konkaDept && null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
						konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
					}
				}
			}

			// 得到经办
			for (KonkaOrderInfo konkaOrderInfo2 : entityList) {
				if (null != konkaOrderInfo2.getAdd_dept_id()) {
					KonkaDept konkaDept2 = new KonkaDept();
					konkaDept2.setDept_id(konkaOrderInfo2.getAdd_dept_id());
					konkaDept2.getMap().put("dept_type_eq", 4);
					konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
					if (null != konkaDept2 && !("".equals(konkaDept2))) {
						String deptName = konkaDept2.getDept_name();
						if (null != deptName && !("".equals(deptName))) {
							konkaOrderInfo2.getMap().put("jbName", konkaDept2.getDept_name());
						}
					} else {
						KonkaDept konkaDept3 = new KonkaDept();
						konkaDept3.setDept_id(konkaOrderInfo2.getAdd_dept_id());
						konkaDept3.getMap().put("dept_type_eq", 5);
						konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
						if (null != konkaDept3 && !"".equals(konkaDept3)) {
							String deptName = konkaDept3.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo2.getMap().put("jbName", konkaDept3.getDept_name());
							}
						}
					}
				}
			}

			if (null != entityList) {
				for (KonkaOrderInfo orderInfo : entityList) {
					// 0： 待审核 1: 已审核
					orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
				}
			}

			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 5000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
							+ "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + EncryptUtils.encodingFileName("订单查询数据") + ".xls");
				entity.getRow().setCount(recordCount.intValue());
				List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService()
						.getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);
				dynaBean.set("excel_all", excel_all);

				// 从R3得到物流发货信息--ldy
				for (KonkaOrderInfo konkaOrderInfo : entityList) {
					Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
					if (r3_order_id != null) {
						KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
						konkaR3Zsof.setVbeln(r3_order_id);
						konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
						if (konkaR3Zsof != null) {
							konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
							if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
								konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
								if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
									konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
								}
							}
						}
					}
				}

				// 得到分公司
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept6 = new KonkaDept();
						konkaDept6.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept6.getMap().put("dept_type_eq", 3);
						konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
						if (null != konkaDept6 && null != konkaDept6.getDept_name()
								&& !"".equals(konkaDept6.getDept_name())) {
							konkaOrderInfo4.getMap().put("fgsName", konkaDept6.getDept_name());
						}
					}
				}

				// 得到经办
				for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
					if (null != konkaOrderInfo4.getAdd_dept_id()) {
						KonkaDept konkaDept4 = new KonkaDept();
						konkaDept4.setDept_id(konkaOrderInfo4.getAdd_dept_id());
						konkaDept4.getMap().put("dept_type_eq", 4);
						konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
						if (null != konkaDept4 && !("".equals(konkaDept4))) {
							String deptName = konkaDept4.getDept_name();
							if (null != deptName && !("".equals(deptName))) {
								konkaOrderInfo4.getMap().put("jbName", konkaDept4.getDept_name());
							}
						} else {
							KonkaDept konkaDept5 = new KonkaDept();
							konkaDept5.setDept_id(konkaOrderInfo4.getAdd_dept_id());
							konkaDept5.getMap().put("dept_type_eq", 5);
							konkaDept5 = super.getFacade().getKonkaDeptService()
									.getKonkaDeptSuperiorByDeptId(konkaDept5);
							if (null != konkaDept5 && !("".equals(konkaDept5))) {
								String deptName = konkaDept5.getDept_name();
								if (null != deptName && !("".equals(deptName))) {
									konkaOrderInfo4.getMap().put("jbName", konkaDept5.getDept_name());
								}
							}
						}
					}
				}
				if (null != entityList1) {
					for (KonkaOrderInfo orderInfo : entityList1) {
						// 0： 待审核 1: 已审核
						orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
					}
				}
				request.setAttribute("allList", entityList1);
				if (is_th != null && is_th.equals("1")) {
					return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
				} else {
					return new ActionForward("/admin/KonkaOrderSearch/listForReport.jsp");
				}
			}

			request.setAttribute("entityList", entityList);
		}

		// 获取流程列表
		KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
		if (!role_id_lt_30) {
			// 分公司之可见自己的流程
			KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			process.getMap().put("par_add_dept_id", dept.getDept_id());
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);

			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			processList.addAll(ap_publicauditProcesseList);
			request.setAttribute("processList", processList);
		} else {
			// 超级管理员
			process.setIs_del(0);
			List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(process);
			request.setAttribute("processList", processList);
		}
		if (is_th != null && is_th.equals("1")) {
			return new ActionForward("/admin/KonkaOrderSearch/listTH.jsp");
		}

		return new ActionForward("/admin/KonkaOrderSearch/listdetails.jsp");
	}
}