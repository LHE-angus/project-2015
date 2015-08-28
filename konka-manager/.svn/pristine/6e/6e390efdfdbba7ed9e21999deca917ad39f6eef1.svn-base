package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.VADefailsOfConsumer;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,DeYu
 * @version 2013-07-09
 */

public class VADefailsOfConsumerAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		Long konkaR3Id=ui.getCust_id();
		
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		VADefailsOfConsumer entity = new VADefailsOfConsumer();
		//权限
		entity.getMap().put("konkaR3Id", konkaR3Id);
		
		// 门店
		String store_name = (String) dynaBean.get("store_name");
		// 姓名
		String buyer_name = (String) dynaBean.get("buyer_name");
		// 电话
		String buyer_tel = (String) dynaBean.get("buyer_tel");
		// 产品型号
		String model_id = (String) dynaBean.get("model_id");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String r3_kh_name = (String) dynaBean.get("r3_kh_name");
		String excel_all = (String) dynaBean.get("excel_all");

		if (StringUtils.isNotBlank(r3_kh_name)) {
			entity.getMap().put("r3_kh_name", r3_kh_name);
		}
		if (StringUtils.isNotBlank(store_name)) {
			entity.getMap().put("store_name", store_name);
		}
		if (StringUtils.isNotBlank(buyer_name)) {
			entity.getMap().put("buyer_name", buyer_name);
		}
		if (StringUtils.isNotBlank(buyer_tel)) {
			entity.getMap().put("buyer_tel", buyer_tel);
		}
		if (StringUtils.isNotBlank(model_id)) {
			model_id=model_id.toUpperCase();
			entity.getMap().put("md_name", model_id);
		}
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin + " 00:00:00");
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end + " 23:59:59");
		}

		Long recordCount = getFacade().getVADefailsOfConsumerService().getVADefailsOfConsumerCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<VADefailsOfConsumer> entityList = getFacade().getVADefailsOfConsumerService()
				.getVADefailsOfConsumerPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			entity.getRow().setCount(recordCount.intValue());
			List<VADefailsOfConsumer> entityList1 = getFacade().getVADefailsOfConsumerService()
					.getVADefailsOfConsumerPaginatedList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}
		return mapping.findForward("list");
	}
}
