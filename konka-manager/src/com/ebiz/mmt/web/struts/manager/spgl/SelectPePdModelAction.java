package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

public class SelectPePdModelAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	// 取订单的产品明细
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String key_word = (String) dynaBean.get("key_word");
		String id = (String) dynaBean.get("id");

		String selects = (String) dynaBean.get("selects");
		PshowOrdeDetails entity = new PshowOrdeDetails();
		entity.setOrder_id(Long.valueOf(id));
		if (StringUtils.isNotBlank(selects)) {
			if (!selects.endsWith(",")) {
				entity.getMap().put("selects", selects);
			} else {
				entity.getMap().put("selects", selects.substring(0, selects.length() - 1));
			}
			entity.getMap().put("OrderByMd", 1);
			List<PshowOrdeDetails> selectList = super.getFacade().getPshowOrdeDetailsService()
					.getPshowOrdeForPDSNDetailsList(entity);
			request.setAttribute("selectList", selectList);

		}
		// 交易流水号
		// Date now = new Date();
		// String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss") +
		// StringUtils.substring(String.valueOf(now.getTime()), 7);

		request.getSession().setAttribute("selects", selects);

		PshowOrdeDetails p1 = new PshowOrdeDetails();
		p1.setOrder_id(Long.valueOf(id));

		if (StringUtils.isNotBlank(selects)) {// 筛选掉已经选择的
			if (!selects.endsWith(",")) {
				p1.getMap().put("not_selects", selects);
			} else {
				p1.getMap().put("not_selects", selects.substring(0, selects.length() - 1));
			}
		}
		if (StringUtils.isNotBlank(key_word)) {
			p1.getMap().put("md_name_like", key_word);
		}
		logger.info("=========selects{}:" + selects);

		List<PshowOrdeDetails> pePdModelList = getFacade().getPshowOrdeDetailsService().getPshowOrdeForPDSNDetailsList(
				p1);
		request.setAttribute("entityList", pePdModelList);

		return mapping.findForward("list");
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key_word = (String) dynaBean.get("key_word");
		String cls_id = (String) dynaBean.get("cls_id");
		String selects = (String) request.getSession().getAttribute("selects");

		PePdModel pePdModel = new PePdModel();
//		pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
		pePdModel.setIs_del(0);
		if (StringUtils.isNotBlank(selects)) {
			pePdModel.getMap().put("not_selects", selects);
		}
		if (StringUtils.isNotBlank(key_word)) {
			pePdModel.getMap().put("md_name_like", key_word);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			pePdModel.getMap().put("cls_id_par", cls_id);
		}

		List<PePdModel> entityList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		StringBuffer sb = new StringBuffer("[");
		for (PePdModel t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getPd_id()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getMd_name()) + "\"},");
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}
}
