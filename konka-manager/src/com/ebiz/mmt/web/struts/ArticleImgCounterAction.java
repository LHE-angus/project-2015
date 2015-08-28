package com.ebiz.mmt.web.struts;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.web.Constants;


public class ArticleImgCounterAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.counter(mapping, form, request, response);
	}

	public ActionForward counter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String url = (String) dynaBean.get("url");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(url)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		ArticleImg articleImg = new ArticleImg();
		articleImg.setId(new Long(id));
		ArticleImg entity = getFacade().getArticleImgService().getArticleImg(articleImg);

		Long count = entity.getView_count();
		count = count == null ? 0 : count;
		count++;
		articleImg.setView_count(count);
		getFacade().getArticleImgService().modifyArticleImg(articleImg);

		response.sendRedirect(URLDecoder.decode(url, Constants.SYS_ENCODING));
		return null;
	}

}
