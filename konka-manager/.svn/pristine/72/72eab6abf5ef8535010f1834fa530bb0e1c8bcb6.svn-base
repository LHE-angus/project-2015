package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-06-11
 */
public class JxcKonkaArticleInfoAction extends BaseClientJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		super.setNaviStringToRequestScope(form, request);

		 PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		String article_type_id = (String) dynaBean.get("article_type_id");

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		entity.getMap().put("group_user_id", peProdUser.getId());
		entity.setStates(1L);
		if (StringUtils.isNotBlank(article_type_id)) {
			entity.setArticle_type_id(Long.valueOf(article_type_id));
			dynaBean.set("article_type_id", article_type_id);
		}

		if (StringUtils.isNotBlank(st_pub_date)) {
			entity.getMap().put("st_pub_date", st_pub_date);
		}
		if (StringUtils.isNotBlank(en_pub_date)) {
			entity.getMap().put("en_pub_date", en_pub_date);
		}
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_likes", title_like);
		}
		
		entity.setIs_del(0L);

		Long recordCount = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleInfo> entityList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);


		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {

			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);

			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(new Long(id));
			attachment.setIs_del((long) 0);
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService()
					.getKonkaPeAttachmentsList(attachment));

			KonkaPeArticleInfo update = new KonkaPeArticleInfo();
			update.setId(entity.getId());
			update.setView_counts(entity.getView_counts() + 1L);
			super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);

			request.setAttribute("entity", entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
	
}
