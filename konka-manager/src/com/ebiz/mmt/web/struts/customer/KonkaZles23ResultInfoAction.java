package com.ebiz.mmt.web.struts.customer;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaZles23ResultInfoAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");// R3客户编码
		String matnr_like = (String) dynaBean.get("matnr_like");// 型号
		String lgort_like = (String) dynaBean.get("lgort_like");// 收货仓库
		String vbeln_like = (String) dynaBean.get("vbeln_like");// 发机单号
		String reslo_like = (String) dynaBean.get("reslo_like");// 发货仓库
		String budat1_s = (String) dynaBean.get("budat1_s");// 发货日期
		String budat1_e = (String) dynaBean.get("budat1_e");// 发货日期
		String budat2_s = (String) dynaBean.get("budat2_s");// 收货日期
		String budat2_e = (String) dynaBean.get("budat2_e");// 收货日期

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		KonkaZles23ResultInfo entity = new KonkaZles23ResultInfo();

		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(matnr_like)) {
			entity.getMap().put("matnr_like", matnr_like);
		}
		if (StringUtils.isNotBlank(lgort_like)) {
			entity.getMap().put("lgort_like", lgort_like);
		}
		if (StringUtils.isNotBlank(vbeln_like)) {
			entity.getMap().put("vbeln_like", vbeln_like);
		}
		if (StringUtils.isNotBlank(reslo_like)) {
			entity.getMap().put("reslo_like", reslo_like);
		}
		if (StringUtils.isNotBlank(budat1_s)) {
			entity.getMap().put("budat1_s", budat1_s);
		}
		if (StringUtils.isNotBlank(budat1_e)) {
			entity.getMap().put("budat1_e", budat1_e);
		}
		if (StringUtils.isNotBlank(budat2_s)) {
			entity.getMap().put("budat2_s", budat2_s);
		}
		if (StringUtils.isNotBlank(budat2_e)) {
			entity.getMap().put("budat2_e", budat2_e);
		}

		entity.getMap().put("customer_id", user.getCust_id());
		Long recordCount = getFacade().getKonkaZles23ResultInfoService().getKonkaZles23ResultInfoForCustomerCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaZles23ResultInfo> entityList = super.getFacade().getKonkaZles23ResultInfoService()
				.getKonkaZles23ResultInfoForCustomerPaginatedList(entity);

        // 20150331
		if (entityList != null && entityList.size() > 0) {
			for (KonkaZles23ResultInfo info : entityList) {
				if (info != null && StringUtils.isNotBlank(info.getMatnr()) && StringUtils.isNotBlank(info.getLgort())
						&& info.getBudat1() != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					info.getMap().put(
							"price",
							super.getFacade()
									.getKonkaPriceManagerService()
									.getKonkaPriceManagerForPrice(info.getMatnr(), info.getLgort(),
											format.format(info.getBudat1())));
				}
			}
		}

		if (entityList != null) {
			request.setAttribute("entityList", entityList);
		}
		return mapping.findForward("list");
	}
}