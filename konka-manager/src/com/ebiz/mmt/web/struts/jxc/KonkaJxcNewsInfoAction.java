package com.ebiz.mmt.web.struts.jxc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;

/**
 * @author Wu,Yang
 * @version 2011-10-13
 */
public class KonkaJxcNewsInfoAction extends BaseJxcAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setIs_del(0l);
		entity.setStates(1l);// 已发布
		entity.setMmt_audit(1l);// 已审核
		entity.getRow().setCount(10);
		List<KonkaPeArticleInfo> konkaPeArticleInfoList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(entity);
		request.setAttribute("konkaPeArticleInfoList", konkaPeArticleInfoList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		if (null == entity) {
			return null;
		}
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		attachment.setIs_del((long) 0);
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));

		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

}
