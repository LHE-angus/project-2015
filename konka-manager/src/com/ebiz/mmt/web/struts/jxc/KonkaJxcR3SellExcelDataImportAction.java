package com.ebiz.mmt.web.struts.jxc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Li,Ka
 * @version 2010-10-11
 */
@Deprecated
public class KonkaJxcR3SellExcelDataImportAction extends BaseJxcAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		int recordCount = 0;// 总记录数
		int insertCount = 0;// 插入记录数
		int invalidCount = 0;// 无效记录数
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("insertCount", insertCount);
		request.setAttribute("invalidCount", invalidCount);
		super.encodeCharacterForGetMethod(dynaBean, request);
		return mapping.findForward("list");

	}


}
