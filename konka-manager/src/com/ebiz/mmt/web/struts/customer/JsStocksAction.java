package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JsStocks;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2014-01-11
 * @desc 进销存月度结算
 */
public class JsStocksAction extends BaseClientJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		String add_date_ge = (String) dynaBean.get("add_date_ge"); // 开始时间
		String add_date_le = (String) dynaBean.get("add_date_le"); // 结束时间

		String md_name_like = (String) dynaBean.get("md_name_like");

		JsStocks entity = new JsStocks();
		entity.setC_id(user.getCust_id());

		if (StringUtils.isNotBlank(add_date_ge)) {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		if (StringUtils.isNotBlank(add_date_le)) {
			entity.getMap().put("add_date_le", add_date_le);
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}

		Long recordCount = super.getFacade().getJsStocksService().getJsStocksCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JsStocks> entityList = super.getFacade().getJsStocksService().getJsStocksPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user.getCust_id());
		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		request.setAttribute("konkaR3Shop", konkaR3Shop);

		JsStocks entity = new JsStocks();
		entity.setC_id(user.getCust_id());

		List<JsStocks> entityList = super.getFacade().getJsStocksService().getJsStocksListForFX(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("view");

	}
}