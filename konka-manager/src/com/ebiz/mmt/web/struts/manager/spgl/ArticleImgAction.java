package com.ebiz.mmt.web.struts.manager.spgl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Jiang,JiaYong
 * @version 2012-09-15
 */
public class ArticleImgAction extends BaseAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String st_pub_date = (String) dynaBean.get("st_pub_date");
		String en_pub_date = (String) dynaBean.get("en_pub_date");
		String news_module = (String) dynaBean.get("news_module");

		
		KonkaCategory cate = new KonkaCategory();
		cate.setC_type(14);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(cate);
		request.setAttribute("sysModuleWebList", konkaCategoryList);

		ArticleImg entity = new ArticleImg();
		super.copyProperties(entity, form);
		entity.getMap().put("c_type_eq", 14);
		entity.getMap().put("st_pub_date", st_pub_date);
		entity.getMap().put("en_pub_date", en_pub_date);
		if(GenericValidator.isLong(news_module))
			entity.setNews_module(Long.valueOf(news_module));
		
		Long recordCount = super.getFacade().getArticleImgService().getArticleImgCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<ArticleImg> entityList = super.getFacade().getArticleImgService().getArticleImgPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// the 2 lines below is added for showing @author Xing,XiuDong
		// 2009.06.10
		String channel = (String) dynaBean.get("channel");
		String news_module = (String) dynaBean.get("news_module");
		dynaBean.set("tunnel", channel);
		dynaBean.set("news_module", news_module);

		PeProdUser userInfo = super.getSessionUserInfo(request);

		KonkaCategory cate = new KonkaCategory();
		cate.setC_type(14);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(cate);
		request.setAttribute("sysModuleWebList", konkaCategoryList);

		dynaBean.set("order_value", 0);
		dynaBean.set("priority", 99);
		dynaBean.set("info_state", 1);
		String web_name = super.getMessage(request, "web.name");
		dynaBean.set("info_source", web_name);
		dynaBean.set("author", web_name);
		dynaBean.set("pub_user_name", userInfo.getUser_name());
		dynaBean.set("pub_user_id", userInfo.getId());
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {

			ArticleImg articleImg = new ArticleImg();
			articleImg.setId(new Long(id));
			ArticleImg entity = getFacade().getArticleImgService().getArticleImg(articleImg);
			super.copyProperties(form, entity);

			KonkaCategory cate = new KonkaCategory();
			cate.setC_type(14);
			List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(
					cate);
			request.setAttribute("sysModuleWebList", konkaCategoryList);

			return mapping.findForward("view");
		} else {
			saveMessage(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward viewByChannel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String channel = (String) dynaBean.get("channel");
		String news_module = (String) dynaBean.get("news_module");

		KonkaCategory cate = new KonkaCategory();
		cate.setC_type(14);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(cate);
		request.setAttribute("sysModuleWebList", konkaCategoryList);

		if (StringUtils.isNotBlank(news_module)) {
			ArticleImg entity = new ArticleImg();
			super.copyProperties(entity, form);
			if (StringUtils.isNotBlank(channel)) {
				entity.setTunnel(channel);
			}
			entity.setNews_module(Long.valueOf(news_module));
			entity.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			entity.setInfo_state(new Short("1"));
			entity.getRow().setCount(5);
			List<ArticleImg> entityList = super.getFacade().getArticleImgService().getArticleImgList(entity);
			request.setAttribute("entityList", entityList);

			String s_json = StringHelper.getPptJsonString(entityList, "image_path", "title", "id", "Index.do?");
			request.setAttribute("imgList", s_json);
		}

		dynaBean.set("channel", channel);
		dynaBean.set("news_module", news_module);
		return new ActionForward("/admin/ArticleImg/listChannel.jsp");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaCategory cate = new KonkaCategory();
		cate.setC_type(14);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(cate);
		request.setAttribute("sysModuleWebList", konkaCategoryList);

		ArticleImg entity = super.getFacade().getArticleImgService().getArticleImg(new ArticleImg(Long.valueOf(id)));

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		entity.setQueryString(super.serialize(request, "id", "mod_id"));

		String url = entity.getImage_url();
		if (StringUtils.isNotBlank(url) && StringUtils.contains(url, "url=")) {
			url = StringUtils.substringAfter(url, "url=");
			entity.setImage_url(URLDecoder.decode(url, Constants.SYS_ENCODING));
		}

		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String image_url = (String) dynaBean.get("image_url");
		String page_flag = (String) dynaBean.get("page_flag");

		Date now = new Date();

		PeProdUser userInfo = super.getSessionUserInfo(request);

		ArticleImg entity = new ArticleImg();
		super.copyProperties(entity, form);

		entity.setPub_user_id(userInfo.getId());
		entity.setPub_user_name(userInfo.getUser_name());
		entity.setModify_date(now);

		if (null == entity.getPub_date()) {
			entity.setPub_date(new Date());
		}
		entity.setPub_user_id(userInfo.getId());
		entity.setPub_user_name(userInfo.getUser_name());

		List<UploadFile> uploadFileList = super.uploadFile(form, "files/lunbotu/", true, 0, new int[] { 60, 240, 400,
				480, 720, 800 });
		// List<UploadFile> uploadFileList = super.uploadFile(form, "files/lunbotu/", true, 0, new int[] {});
		for (UploadFile uploadFile : uploadFileList) {
			if ("image_path".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setImage_path(uploadFile.getFileSavePath());
			}
		}

		StringBuilder url = new StringBuilder();
		url.append("/ArticleImgCounter.do?");

		if (StringUtils.isBlank(id)) {
			if (null == entity.getPub_date()) {
				entity.setPub_date(now);
			}
			entity.setView_count(new Long(0));
			Long r_id = super.getFacade().getArticleImgService().createArticleImg(entity);

			url.append("id=").append(r_id);
			url.append("&url=").append(URLEncoder.encode(image_url, Constants.SYS_ENCODING));

			ArticleImg updateURL = new ArticleImg();
			updateURL.setId(r_id);
			updateURL.setImage_url(url.toString());
			super.getFacade().getArticleImgService().modifyArticleImg(updateURL);

			saveMessage(request, "entity.inserted");
		} else {
			if (!GenericValidator.isLong(id)) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}

			if ("".equals(entity.getImage_path())) {
				entity.setImage_path(null);
			}

			url.append("id=").append(id);
			url.append("&url=").append(URLEncoder.encode(image_url, Constants.SYS_ENCODING));
			entity.setImage_url(url.toString());

			super.getFacade().getArticleImgService().modifyArticleImg(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(page_flag)) {
			return new ActionForward("/admin/ArticleImg.do?method=viewByChannel&mod_id=" + mod_id + "&"
					+ "&news_module=" + entity.getNews_module(), true);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String page_flag = (String) dynaBean.get("page_flag");
		String news_module = (String) dynaBean.get("news_module");
		String channel = (String) dynaBean.get("channel");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			ArticleImg entity = new ArticleImg();
			entity.setId(new Long(id));
			getFacade().getArticleImgService().removeArticleImg(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			ArticleImg entity = new ArticleImg();
			entity.getMap().put("pks", pks);
			super.getFacade().getArticleImgService().removeArticleImg(entity);
		}

		if (StringUtils.isNotBlank(page_flag)) {
			return new ActionForward("/admin/ArticleImg.do?method=viewByChannel&mod_id=" + mod_id + "&channel="
					+ channel, true);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append("news_module=").append(news_module).append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * @author Xing,XiuDong
	 */
	public ActionForward updateOrderValue(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String order_value = (String) dynaBean.get("order_value");

		if (!GenericValidator.isShort(order_value) || !GenericValidator.isLong(id)) {
			super.render(response, "{ 'status' : '500' }", "text/x-json;charset=UTF-8");
			return null;
		}

		ArticleImg entity = new ArticleImg();
		entity.setId(Long.valueOf(id));
		entity.setOrder_value(Short.valueOf(order_value));
		super.getFacade().getArticleImgService().modifyArticleImg(entity);

		super.render(response, "{ \"status\" : \"200\" }", "text/x-json;charset=UTF-8");
		return null;
	}

}
