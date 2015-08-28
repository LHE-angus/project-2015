package com.ebiz.mmt.web.struts.manager.spgl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class ShopInCodeAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return mapping.findForward("list");
	}

	public ActionForward iframe(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return new ActionForward("/../manager/spgl/ShopInCode/iframe.jsp");
	}

	public ActionForward ajaxSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String code = (String) dynaBean.get("code");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");

		if (!GenericValidator.isLong(code)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		EcVouchCode ec = new EcVouchCode();
		ec.setShop_id(1001L);
		ec.setCode(code);
		ec = super.getFacade().getEcVouchCodeService().getEcVouchCode(ec);
		if (ec == null) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		} else if (ec.getIs_userd().intValue() == 1 || ec.getIs_del().intValue() == 1) {
			sb = sb.append("-2");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		} else {
			EcVouchCode entity = new EcVouchCode();
			entity.setIs_userd(1);
			entity.setId(ec.getId());
			entity.setUsed_date(new Date());
			super.getFacade().getEcVouchCodeService().modifyEcVouchCode(entity);

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			sb = sb.append("1").append(",");
			sb = sb.append("\"trade_index\":\"").append(ec.getTrade_index()).append("\"");
			sb = sb.append(",\"price\":\"").append(ec.getPrice().toString()).append("\"");
			sb = sb.append(",\"num\":\"").append("1").append("\"");
			sb = sb.append(",\"add_date\":\"").append(sf.format(ec.getAdd_date())).append("\"");
			sb = sb.append("}");
			logger.info("sb {}", sb);
			super.renderJson(response, sb.toString());
			return null;
		}

	}

}
