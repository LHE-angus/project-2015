package com.ebiz.mmt.web.struts.webservice.wap.center;

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
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcVouchersAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
		}
		
		super.copyProperties(form, ecUser);

		String totalScore = super.getFacade().getEcUserScoreRecService()
				.getEcUserScoreRecForUserTotalScore(ecUser.getId());
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
		for (EcVouchers ecVouchers : entityList1) {
			if (ecVouchers.getGoods_type() != null) {
				String goods_type = ecVouchers.getGoods_type().replace("0", "新品").replace("2", "热销").replace("3", "特惠")
						.replace("7", "精品");
				ecVouchers.getMap().put("goods_type", goods_type);
			}

			if (ecVouchers.getPd_type() != null) {
				String pd_type = ecVouchers.getPd_type().replace("0", "彩电").replace("3", "小家电").replace("5", "洗衣机");
				ecVouchers.getMap().put("pd_type", pd_type);
			}

			if (ecVouchers.getGoods_id() != null) {
				String pd_sn = "";
				String[] aa = ecVouchers.getGoods_id().split(",");
				for (String string : aa) {
					KonkaBcompPd kp = new KonkaBcompPd();
					kp.setId(Long.valueOf(string));
					kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
					pd_sn = pd_sn + kp.getPd_sn() + ",";
				}
				ecVouchers.getMap().put("pd_sn", pd_sn.substring(0, pd_sn.length() - 1));
			}

		}
		request.setAttribute("entityList1", entityList1);

		// 我的积分
		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
				.getEcUserScoreRecForUserTotalScore(ecUser.getId()));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
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
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
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

		session.setAttribute("zxmall", ecUser);
		super.renderJavaScript(response, "window.onload=function(){location.href=\"EcVouchers.do\";}");
		return null;
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
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

		session.setAttribute("zxmall", ecUser);
		super.renderJavaScript(response, "window.onload=function(){location.href=\"EcVouchers.do\";}");
		return null;
	}

	public ActionForward ajaxSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String vouchers_code = (String) dynaBean.get("vouchers_code");
		String vouchers_pwd = (String) dynaBean.get("vouchers_pwd");

		if (StringUtils.isBlank(vouchers_code)) {
			String msg = "优惠券编码不能为空";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		// if (StringUtils.isBlank(vouchers_pwd)) {
		// String msg = "优惠券密码不能为空";
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');window.history.back();}");
		// return null;
		// }

		EcVouchers ec = new EcVouchers();
		ec.setVouchers_code(vouchers_code);
		// ec.setVouchers_pwd(vouchers_pwd);
		ec.setDept_id(ecUser.getDept_id());
		ec.getMap().put("is_active", true);
		ec = super.getFacade().getEcVouchersService().getEcVouchers(ec);

		if (ec != null) {
			if (ec.getVouchers_pwd() == null) {
				ec.setUser_id(ecUser.getId());
				super.getFacade().getEcVouchersService().modifyEcVouchers(ec);
				saveMessage(request, "errors.message", "恭喜您，绑定成功！");
			} else {
				if (ec.getVouchers_pwd().equals(vouchers_pwd)) {
					ec.setUser_id(ecUser.getId());
					super.getFacade().getEcVouchersService().modifyEcVouchers(ec);
					saveMessage(request, "errors.message", "恭喜您，绑定成功！");
				} else {
					saveError(request, "errors.message", "优惠券编码和密码不匹配！");
				}
			}

		} else {
			saveError(request, "errors.message", "优惠券编码和密码不匹配！");
		}

		session.setAttribute("zxmall", ecUser);
		super.renderJavaScript(response, "window.onload=function(){location.href=\"EcVouchers.do\";}");
		return null;
	}

	public ActionForward ajaxValide(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		DynaBean dynaBean = (DynaBean) form;
		String vouchers_code = (String) dynaBean.get("vouchers_code");
		String vouchers_pwd = (String) dynaBean.get("vouchers_pwd");

		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
		}
		if (StringUtils.isBlank(vouchers_code)) {
			super.renderText(response, "0");
			return null;
		}

		// if (StringUtils.isBlank(vouchers_pwd)) {
		// super.renderText(response, "0");
		// return null;
		// }

		EcVouchers ec = new EcVouchers();
		ec.setVouchers_code(vouchers_code);
		// ec.setVouchers_pwd(vouchers_pwd);
		ec.setDept_id(ecUser.getDept_id());
		ec.getMap().put("is_active", true);
		ec = super.getFacade().getEcVouchersService().getEcVouchers(ec);

		if (ec != null) {
			if (ec.getVouchers_pwd() == null) {
				ec.setUser_id(ecUser.getId());
				super.getFacade().getEcVouchersService().modifyEcVouchers(ec);
				super.renderText(response, "1");
			} else {
				if (ec.getVouchers_pwd().equals(vouchers_pwd)) {
					ec.setUser_id(ecUser.getId());
					super.getFacade().getEcVouchersService().modifyEcVouchers(ec);
					super.renderText(response, "1");
				} else {
					super.renderText(response, "0");
				}
			}

		} else {
			super.renderText(response, "0");
		}
		return null;
	}

}
