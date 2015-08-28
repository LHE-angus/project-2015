package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,Yang
 */
public class NoticeAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date now = new Date();

		String today = dateFormat.format(now);

		KonkaPeArticleInfo entity5 = new KonkaPeArticleInfo();
		entity5.setArticle_mod_id(Long.valueOf("2025"));
		entity5.getMap().put("use_invalid_date", today);
		List<KonkaPeArticleInfo> KonkaPeArticleInfoList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(entity5);
		request.setAttribute("KonkaPeArticleInfoList", KonkaPeArticleInfoList);

		KonkaPeArticleInfo entity9 = new KonkaPeArticleInfo();
		entity9.setArticle_mod_id(Long.valueOf("5000"));
		entity9.getMap().put("use_invalid_date", today);
		//entity9.setKey_word("Notice");
		List<KonkaPeArticleInfo> entityForNoticeList = super.getFacade()
				.getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(entity9);
		if (null != entityForNoticeList && entityForNoticeList.size() > 0) {
			KonkaPeArticleInfo newInfoNotice = new KonkaPeArticleInfo();
			newInfoNotice.setId(entityForNoticeList.get(0).getId());
			newInfoNotice = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(
					newInfoNotice);
			request.setAttribute("entityForNotice", newInfoNotice);
			if (null != newInfoNotice) {
				KonkaPeAttachments attachment = new KonkaPeAttachments();
				attachment.setLink_id(newInfoNotice.getId());
				request.setAttribute("attachmentListForNotice", getFacade()
						.getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));
			}
		}

		request.setAttribute("now", new Date());
		return mapping.findForward("list");
	}
}
