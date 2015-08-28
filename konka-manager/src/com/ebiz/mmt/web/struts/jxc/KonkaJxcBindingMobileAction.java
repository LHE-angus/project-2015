package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaJxcBindingMobile;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;

/**
 * @author Zhang,Xufeng
 * @version 2011-12-20
 */
public class KonkaJxcBindingMobileAction extends BaseJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		StdEntpMain stdEntpMain = user.getStdEntpMain();
		if (null == stdEntpMain) {
			return null;
		}
		Long shop_id = stdEntpMain.getShop_id();

		if (null == shop_id) {
			return null;
		}

		// 根据shop_id查询是否绑定手机
		KonkaJxcBindingMobile konkaJxcBindingMobile = new KonkaJxcBindingMobile();
		konkaJxcBindingMobile.setShop_id(shop_id);
		konkaJxcBindingMobile.setIs_del(0);
		konkaJxcBindingMobile = getFacade().getKonkaJxcBindingMobileService().getKonkaJxcBindingMobile(
				konkaJxcBindingMobile);
		if (null == konkaJxcBindingMobile) {// 没有绑定
			stdEntpMain.getMap().put("is_binding", "0");
		} else {
			stdEntpMain.getMap().put("is_binding", "1");
			request.setAttribute("konkaJxcBindingMobile", konkaJxcBindingMobile);
		}

		request.setAttribute("user", user);// 取登录用户名
		request.setAttribute("stdEntpMain", stdEntpMain);// 取商铺信息
		dynaBean.set("keySeq", keySeq);
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		String mobile = (String) dynaBean.get("mobile");

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		StdEntpMain stdEntpMain = user.getStdEntpMain();
		if (null == stdEntpMain) {
			return null;
		}
		Long shop_id = stdEntpMain.getShop_id();
		if (GenericValidator.isLong(id)) {// 更换手机号码
			KonkaJxcBindingMobile entity = new KonkaJxcBindingMobile();
			entity.setId(new Long(id));
			entity.setMobile(mobile);
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(user.getUser_id());
			super.getFacade().getKonkaJxcBindingMobileService().modifyKonkaJxcBindingMobile(entity);
			saveError(request, "entity.updated", id);
			return new ActionForward("KonkaJxcBindingMobile.do?method=list&keySeq=" + keySeq, true);
		} else {// 添加手机号码
			KonkaJxcBindingMobile entity = new KonkaJxcBindingMobile();
			entity.setShop_id(shop_id);
			entity.setMobile(mobile);
			entity.setEntp_name(stdEntpMain.getEntp_name());
			super.getFacade().getKonkaJxcBindingMobileService().createKonkaJxcBindingMobile(entity);
			saveError(request, "entity.updated", id);
			return new ActionForward("KonkaJxcBindingMobile.do?method=list&keySeq=" + keySeq, true);
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");

		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		StdEntpMain stdEntpMain = user.getStdEntpMain();
		if (null == stdEntpMain) {
			return null;
		}
		if (GenericValidator.isLong(id)) {
			KonkaJxcBindingMobile entity = new KonkaJxcBindingMobile();
			entity.setId(new Long(id));
			super.getFacade().getKonkaJxcBindingMobileService().removeKonkaJxcBindingMobile(entity);
			saveError(request, "entity.deleted", id);
		} else {
			saveMessage(request, "entity.missing");
		}
		return new ActionForward("KonkaJxcBindingMobile.do?method=list&keySeq=" + keySeq, true);
	}
}
