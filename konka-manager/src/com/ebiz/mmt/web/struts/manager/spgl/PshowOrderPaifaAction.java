package com.ebiz.mmt.web.struts.manager.spgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class PshowOrderPaifaAction extends BaseAction {

	@Override
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
		String order_from = (String) dynaBean.get("order_from");

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
		if (role_id_gt_30_lt_60) {
			entity.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}
		// 总部和分公司之外的不能查看
		if (!role_id_gt_30_lt_60 && !role_id_eq_30) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
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
		if (StringUtils.isNotBlank(order_from)) {
			entity.setOrder_from(Integer.valueOf(order_from));
		}
		entity.setState(20);// 只有审核通过的订单才可以派发
		entity.getMap().put("cust_pay_way", true);

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService()
				.getPshowOrdeForDeptNameAndFullNameList(entity);
		if (null != entityList && entityList.size() > 0) {
			for (PshowOrder pshowOrder : entityList) {
				PshowOrdeDetails pp = new PshowOrdeDetails();
				pp.setOrder_id(pshowOrder.getId());
				List<PshowOrdeDetails> ppList = super.getFacade().getPshowOrdeDetailsService()
						.getPshowOrdeForPDSNDetailsList(pp);
				pshowOrder.setPshowOrdeDetailsList(ppList);
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward paifa(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getBuyer_p_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				.getPshowOrdeForPDSNDetailsList(psd);

		for (PshowOrdeDetails pshowOrdeDetails2 : pshowordedetails) {

			KonkaBcompPd pp = new KonkaBcompPd();
			pp.setId(pshowOrdeDetails2.getPd_id());
			pp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pp);

			String zwerks = "L00E"; // 库位 not null
			String zlgort = "P046"; // 仓位
			String zlgpla = "F146ZT"; // 物料
			List<ZLEBIN> list = new ArrayList<ZLEBIN>();
			ReturnInfo<ZLEBIN> info = new ReturnInfo<ZLEBIN>();
			double dgCount = 0.00;

			if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
				// list =
				// super.getFacade().getR3WebInterfaceService().getZles20(zwerks,
				// zlgort, zlgpla, pp.getPd_sn());
				info = super.getFacade().getR3WebInterfaceService().getZles20(zwerks, zlgort, zlgpla, pp.getPd_sn());
				if (info.getSuccess() == true) {
					list = info.getDataResult();
				}
			}
			if (null != list && list.size() > 0) {
				ZLEBIN zlb = list.get(0);
				dgCount = dgCount + zlb.getVERME();
			}

			// 合肥分公司（滁州发货） // 工厂(需要和分公司绑定) not null
			String zwerks1 = "L00B"; // 仓位 仓库地点 not null
			String zlgort1 = "8190"; // 物料 8190 //F222
			List<StockCheckResult> entitylist = new ArrayList<StockCheckResult>();
			ReturnInfo<StockCheckResult> info2 = new ReturnInfo<StockCheckResult>();

			if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
				// entitylist =
				// super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks1,
				// zlgort1, pp.getPd_sn());
				info2 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks1, zlgort1, pp.getPd_sn());
				if (info2.getSuccess() == true) {
					entitylist = info2.getDataResult();
				}
			}
			double hfCount = 0.00;
			if (null != entitylist && entitylist.size() > 0) {
				StockCheckResult scr = entitylist.get(0);
				hfCount = hfCount + scr.getLamount().doubleValue();
			}

			// 武汉分公司（武汉9001仓库发货）
			String zwerks2 = "L00D";// 工厂(需要和分公司绑定) not null
			String zlgort2 = "9001";// 仓位 仓库地点 not null
			double whCount = 0.00;
			List<StockCheckResult> entitylist2 = new ArrayList<StockCheckResult>();
			ReturnInfo<StockCheckResult> info3 = new ReturnInfo<StockCheckResult>();
			if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
				// entitylist2 =
				// super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks2,
				// zlgort2, pp.getPd_sn());
				info3 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks2, zlgort2, pp.getPd_sn());
				if (info3.getSuccess() == true) {
					entitylist2 = info3.getDataResult();
				}
			}
			if (null != entitylist2 && entitylist2.size() > 0) {
				StockCheckResult scr = entitylist2.get(0);
				whCount = whCount + scr.getLamount().doubleValue();
			}

			// 武汉分公司（武汉孝感仓F113发货）
			String zwerks3 = "L00D";// 工厂(需要和分公司绑定) not null
			String zlgort3 = "F113";// 仓位 仓库地点 not null
			double whCount2 = 0.00; // 物料
			List<StockCheckResult> entitylist3 = new ArrayList<StockCheckResult>();
			ReturnInfo<StockCheckResult> info4 = new ReturnInfo<StockCheckResult>();
			if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
				// entitylist3 =
				// super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks3,
				// zlgort3, pp.getPd_sn());
				info4 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks3, zlgort3, pp.getPd_sn());
				if (info4.getSuccess() == true) {
					entitylist3 = info4.getDataResult();
				}
			}
			if (null != entitylist3 && entitylist3.size() > 0) {
				StockCheckResult scr = entitylist3.get(0);
				whCount2 = whCount2 + scr.getLamount().doubleValue();
			}

			// 哈尔滨分公司（哈尔滨9034发货）
			String zwerks4 = "L00C";// 工厂(需要和分公司绑定) not null
			String zlgort4 = "9034";// 仓位 仓库地点 not null
			double hebCount = 0.00; // 物料
			List<StockCheckResult> entitylist4 = new ArrayList<StockCheckResult>();
			ReturnInfo<StockCheckResult> info5 = new ReturnInfo<StockCheckResult>();
			if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
				info5 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks4, zlgort4, pp.getPd_sn());
				if (info5.getSuccess() == true) {
					entitylist4 = info5.getDataResult();
				}
			}
			if (null != entitylist4 && entitylist4.size() > 0) {
				StockCheckResult scr = entitylist4.get(0);
				hebCount = hebCount + scr.getLamount().doubleValue();
			}

			String goods_count = "合肥：" + hfCount + " ;东莞：" + dgCount + " ;武汉9001：" + whCount + " ;武汉孝感F113:" + whCount2
					+ " ;哈尔滨:" + hebCount;

			pshowOrdeDetails2.getMap().put("goods_count", goods_count);

		}

		// 取增值服务
		EcBindingPdOrdeDetails ec = new EcBindingPdOrdeDetails();
		ec.setTrade_index(entity.getTrade_index());
		List<EcBindingPdOrdeDetails> bddetailsList = super.getFacade().getEcBindingPdOrdeDetailsService()
				.getEcBindingPdOrdeDetailsList(ec);
		request.setAttribute("bddetailsList", bddetailsList);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		// KonkaDept kd = new KonkaDept();
		// kd.getMap().put("dept_for_paifa", entity.getDept_id());
		// List<KonkaDept> sybDeptInfoList =
		// super.getFacade().getKonkaDeptService().getKonkaDeptListForPaifa(kd);
		// request.setAttribute("sybDeptInfoList", sybDeptInfoList);

		// dynaBean.set("is_jf", "0");

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);
		// if (entity.getDept_id() == 0) {
		// 如果产品是总部发的，可以派发给分公司
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		// 总部处理，派发给白电和小家电
		KonkaDept kk = new KonkaDept();
		kk.setDept_type(1);
		List<KonkaDept> kkList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kk);
		request.setAttribute("kkList", kkList);
		// } else {
		// 如果产品是分公司发的，只能派发给本分公司处理
		/*
		 * KonkaDept kd = new KonkaDept(); kd.setDept_id(entity.getDept_id()); List<KonkaDept> sybDeptInfoList =
		 * super.getFacade().getKonkaDeptService().getKonkaDeptList(kd); request.setAttribute("sybDeptInfoList",
		 * sybDeptInfoList); }
		 */

		return new ActionForward("/spgl/PshowOrderPaifa/paifa.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String dept_id = (String) dynaBean.get("dept_id");
		String remark1 = (String) dynaBean.get("remark1");
		String type = (String) dynaBean.get("type");
		String dept_id_2 = (String) dynaBean.get("dept_id_2");
		String is_jf = (String) dynaBean.get("is_jf");

		String[] detai_ids = request.getParameterValues("detai_id");
		String[] integrals = request.getParameterValues("integral");

		logger.info("++++++++++++++" + type);

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PshowOrder p1 = new PshowOrder();
		p1.setId(Long.valueOf(id));
		p1 = super.getFacade().getPshowOrderService().getPshowOrder(p1);
		if (p1.getState() != 20) {
			super.renderJavaScript(response, "window.onload=function(){alert('订单状态已经发生改变！');history.back();}");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getBuyer_p_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFour(baseProvinceListFour);

			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(Long.valueOf(id));
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
				.getPshowOrdeDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}

		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		PshowOrder pp = new PshowOrder();
		pp.setId(Long.valueOf(id));
		pp = super.getFacade().getPshowOrderService().getPshowOrder(pp);

		BigDecimal total_int = new BigDecimal("0.00");
		if (null != detai_ids && detai_ids.length > 0) {
			for (int i = 0; i < detai_ids.length; i++) {
				PshowOrdeDetails pd = new PshowOrdeDetails();
				pd.setBill_item_id(Long.valueOf(detai_ids[i]));
				pd.setIntegral(new BigDecimal(integrals[i]));
				super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetails(pd);
				total_int = total_int.add(new BigDecimal(integrals[i]));
			}
		}
		entity.setIntegral(total_int);

		// if (StringUtils.isNotBlank(is_jf)) {
		// if (is_jf.equals("1")) {// 不给奖励积分,奖励积分清0
		// for (PshowOrdeDetails pshowOrdeDetails2 : pshowordedetails) {
		// pshowOrdeDetails2.setIntegral(new BigDecimal("0.00"));
		// super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetails(pshowOrdeDetails2);
		// }
		// entity.setIntegral(new BigDecimal("0.00"));
		// }
		// }

		PshowOrdeAudit poa = new PshowOrdeAudit();
		poa.setOper_date(new Date());
		poa.setOrder_id(Long.valueOf(id));
		poa.setRemark(remark1);
		poa.setState(30);
		poa.setOpr_user_id(user.getId());
		poa.setOpr_user_real_name(user.getUser_name());
		// java.math.BigDecimal bd1 = new java.math.BigDecimal(t_price);
		poa.setTotal_price(pp.getPay_price());

		if (type.equals("1")) {
			entity.setOpr_dept_id(Long.valueOf(dept_id));
			entity.setState(30);// state=30为派发中
			entity.setDept_id(Long.valueOf(dept_id));
			super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder(entity, poa);
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
		} else {
			entity.setOpr_dept_id(Long.valueOf(dept_id_2));
			entity.setState(30);// 总部自己处理
			entity.setDept_id(Long.valueOf(dept_id_2));
			super.getFacade().getPshowOrderService().createPshowOrdeAuditAndModifyPshowOrder(entity, poa);
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		logger.info("QueryString---->{}" + super.encodeSerializedQueryString(entity.getQueryString()));
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
