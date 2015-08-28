package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Xing,XiuDong
 * @Date 2013-09-02
 */
public class QueryCustomerAccountAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.view(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");

		if (StringUtils.isBlank(r3_code)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("success");
		}

		KonkaR3Shop s = new KonkaR3Shop();
		s.setR3_code(r3_code);
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
		if (null == s) {
			super.saveError(request, "konka.r3.customer.none");
			return mapping.findForward("success");
		}

		request.setAttribute("customer", s);

		logger.info("Call r3 interface with r3_code '{}' starting...", r3_code);
		// List<KHXD> khxdList =
		// getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
		// new String[] { r3_code });

		ReturnInfo<KHXD> info = new ReturnInfo<KHXD>();
		List<KHXD> khxdList = new ArrayList<KHXD>();
		info = getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
				new String[] { r3_code });
		if (info.getSuccess() == true)
			khxdList = info.getDataResult();

		if (null != khxdList && khxdList.size() > 0) {
			logger.info("{}", BeanUtils.describe(khxdList.get(0)));
			super.copyProperties(form, khxdList.get(0));
			return mapping.findForward("view");
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('很抱歉，未查询到客户 " + r3_code + " 的帐期记录。');history.back();}");
			return null;
		}

		
	}
}
