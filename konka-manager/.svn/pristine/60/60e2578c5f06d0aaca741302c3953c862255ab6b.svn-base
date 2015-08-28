package com.ebiz.mmt.web.struts.member.center;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.EcVouchersType;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcVouchersAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/member/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}
		super.copyProperties(form, ecUser);

		String totalScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserTotalScore(
		        ecUser.getId());
		request.setAttribute("totalScore", totalScore);

		// 可兑换列表
		EcVouchersType ev = new EcVouchersType();
		ev.getMap().put("lt_price", Double.valueOf(totalScore) / 100);
		List<EcVouchersType> entityList = super.getFacade().getEcVouchersTypeService().getEcVouchersTypeList(ev);

		request.setAttribute("entityList", entityList);

		// 已兑换列表
		EcVouchers ecv = new EcVouchers();
		ecv.setUser_id(ecUser.getId());
		// ecv.setInfo_state(0);// 0：未兑换 1：已兑换
		List<EcVouchers> entityList1 = super.getFacade().getEcVouchersService().getEcVouchersList(ecv);
		request.setAttribute("entityList1", entityList1);

		// 我的积分
		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
		        .getEcUserScoreRecForUserTotalScore(ecUser.getId()));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/member/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		EcVouchersType entity = new EcVouchersType();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		entity = super.getFacade().getEcVouchersTypeService().getEcVouchersType(entity);
		super.copyProperties(form, entity);

		dynaBean.set("id", id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/member/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}
		DynaBean dynaBean = (DynaBean) form;
		String price = (String) dynaBean.get("price");
		EcVouchers entity = new EcVouchers();
		entity.setAdd_date(new Date());
		entity.setUser_id(ecUser.getId());
		entity.setInfo_state(0);
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(price)) {
			entity.setPrice(new BigDecimal(price));
		}
		logger.info("price----------------->{}" + price);
		String ss = super.getFacade().getEcVouchersService().createEcVouchersAndGift(entity, ecUser);
		saveMessage(request, "errors.message", ss);

		session.setAttribute("ecUser", ecUser);
		super.renderJavaScript(response, "window.onload=function(){location.href=\"EcVouchers.do\";}");
		return null;
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/member/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}

		DynaBean dynaBean = (DynaBean) form;
		String dh_money = (String) dynaBean.get("dh_money");
		if (StringUtils.isNotBlank(dh_money)) {
			if (Integer.valueOf(dh_money) == 0) {
				String msg = "请输入大于0的金额";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
				        + "');window.history.back();}");
				return null;
			}
		}
		EcVouchers entity = new EcVouchers();
		entity.setAdd_date(new Date());
		entity.setUser_id(ecUser.getId());
		entity.setInfo_state(0);
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(dh_money)) {
			entity.setPrice(new BigDecimal(dh_money));
			entity.setTitle(dh_money + "元购物券");
		}

		String ss = super.getFacade().getEcVouchersService().createEcVouchersAndGift2(entity, ecUser);
		saveMessage(request, "errors.message", ss);

		session.setAttribute("ecUser", ecUser);
		super.renderJavaScript(response, "window.onload=function(){location.href=\"EcVouchers.do\";}");
		return null;
	}
}
