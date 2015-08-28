package com.ebiz.mmt.web.struts.customer;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleType;
import com.ebiz.mmt.domain.KonkaPeArticleUserGroup;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaGroupPeArticleInfoAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String mod_code = (String) dynaBean.get("mod_id");
		String type_name = (String) dynaBean.get("type_name");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		String states = (String) dynaBean.get("states");
		String title = (String) dynaBean.get("title");
		String article_type_id = (String) dynaBean.get("article_type_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		// entity.setArticle_mod_id(Long.valueOf("960110"));

		entity.getMap().put("article_mod_id_in", new Long[] { 960110L, 960112L });

		if (StringUtils.isNotBlank(st_pub_date)) {
			entity.getMap().put("st_pub_date", st_pub_date);
		}
		if (StringUtils.isNotBlank(en_pub_date)) {
			entity.getMap().put("en_pub_date", en_pub_date);
		}
		if (StringUtils.isNotBlank(type_name)) {
			entity.getMap().put("type_name", type_name);
		}

		if (StringUtils.isNotBlank(title)) {
			entity.setTitle(title);
		}

		if (StringUtils.isNotBlank(article_type_id)) {
			if (article_type_id.equals("-1")) {
				entity.setArticle_type_id(Long.valueOf(article_type_id));
			} else {
				entity.setArticle_type_id(Long.valueOf(article_type_id));

				// 1060L,601130L,601131L,601131L
				// 1030L, 1060L, 1070L, 1080L

				entity.getMap().put("article_type_id_in", new Long[] { 1030L, 1060L, 1070L });
			}
		} else {
			entity.getMap().put("article_type_id_in", new Long[] { 1030L, 1060L, 1070L, -1L });
		}

		entity.setIs_del(0L);
		entity.setStates(1L);

		entity.getMap().put("cust_id", user.getCust_id().toString());

		Long recordCount = getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleInfo> entityList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("https", getCtxPath(request));

		// 资讯类别列表
		KonkaPeArticleType konkaPeArticleType = new KonkaPeArticleType();
		konkaPeArticleType.setIs_del(0);
		konkaPeArticleType.getMap().put("ids_in", new Long[] { 1030L, 1060L, 1070L });
		List<KonkaPeArticleType> peArticleTypeList = super.getFacade().getKonkaPeArticleTypeService()
				.getKonkaPeArticleTypeList(konkaPeArticleType);
		request.setAttribute("peArticleTypeList", peArticleTypeList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// PeProdUser ui = (PeProdUser)
		// request.getSession().getAttribute(Constants.USER_INFO);
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		super.copyProperties(form, entity);

		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		attachment.setIs_del((long) 0);
		request.setAttribute("attachmentList",
				getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

		if (null != entity.getArticle_type_id()) {
			KonkaPeArticleType konkaPeArticleType = new KonkaPeArticleType();
			konkaPeArticleType.setId(entity.getArticle_type_id());
			konkaPeArticleType = super.getFacade().getKonkaPeArticleTypeService()
					.getKonkaPeArticleType(konkaPeArticleType);
			String type_name = "";
			if (null != konkaPeArticleType) {
				type_name = konkaPeArticleType.getType_name();
			}
			request.setAttribute("type_name", type_name);
		}

		if (null != entity.getGroup_id()) {
			/*
			 * KonkaPeArticleUserGroup konkaPeArticleUserGroup = new KonkaPeArticleUserGroup();
			 * konkaPeArticleUserGroup.setGroup_id(entity.getGroup_id()); konkaPeArticleUserGroup =
			 * super.getFacade().getKonkaPeArticleUserGroupService()
			 * .getKonkaPeArticleUserGroup(konkaPeArticleUserGroup); if (null != konkaPeArticleUserGroup) {
			 * request.setAttribute("group_name", konkaPeArticleUserGroup.getGroup_name()); }
			 */
		}

		return mapping.findForward("view");
	}
}