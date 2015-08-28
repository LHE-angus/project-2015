package com.ebiz.mmt.web.struts.m.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wang Hao
 */
public class TerminalMaterialReportAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		List<KonkaMobileCategory> wuliaoList = getFacade().getKonkaMobileCategoryService().getWuliaoCategoryList() ;
		request.setAttribute("wuliaoList", wuliaoList);

		return mapping.findForward("list");
	}
	public ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean lazyForm = (DynaBean) form;
		
		String message_type = (String) lazyForm.get("message_type");
		String sales_count = (String) lazyForm.get("sales_count");
		
		PeProdUser ui = super.getSessionUserInfo(request);
		
		KonkaMobileTerminalFeedback feedBack = new KonkaMobileTerminalFeedback();
		
		if(("").equals(message_type)){
			super.renderText(response, "请选择留言类别");
			return null;
		}else{
			feedBack.setMessage_type(Integer.valueOf(message_type));
		}
		
		if(("留言内容").equals(sales_count)){
			super.renderText(response, "请填写留言内容");
			return null;
		}else{
			feedBack.setContent(sales_count);
		}
		feedBack.setQuestion_person(ui.getReal_name());
		feedBack.setAdd_date(new Date());
		
		getFacade().getKonkaMobileTerminalFeedbackService().createKonkaMobileTerminalFeedback(feedBack);
		
		super.renderText(response, "success");
		return null;
	}
}
