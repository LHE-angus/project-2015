package com.ebiz.mmt.web.struts.zxmall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcLogoInfo;
import com.ebiz.mmt.domain.EcNavInfo;
import com.ebiz.mmt.domain.EcSpecFbTeamList;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaPeArticleContent;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleType;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-18
 */
public class KonkaGroupPeArticleInfoAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		if (ecUser == null) {
			ecUser = new EcUser();
			ecUser.setUser_name("游客");
			ecUser.setUser_type(new Integer(2));
			ecUser.setId(new Long(1));
			ecUser.setDept_id(0L);
			ecUser.setPlat_sys(new Integer(0));
			ecUser.setCust_id(0L);
			ecUser.setPlat_sys(0);
		}
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		String article_type_id = (String) dynaBean.get("article_type_id");

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		// 内购资讯
		// entity.setArticle_type_id(2000L);
		if (StringUtils.isNotBlank(article_type_id)) {
			entity.setArticle_type_id(new Long(article_type_id));
		} else {
			entity.setArticle_type_id(2000L);
		}
		// entity.getMap().put("article_type_id_in", new Long[] { 2000L, 2100L,
		// 2200L, 2300L, 2400L, 2500L });
		entity.setDept_id(ecUser.getDept_id());
		entity.setArticle_mod_id(Long.valueOf(906109));
		entity.setIs_del(0L);
		entity.setStates(1L);
		entity.setMsg_info_type(new Integer(1));// 所属系统
		// 0内购,1触网,2newshop,3顺丰触网5直销平台

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
		List<KonkaPeArticleType> peArticleTypeList = super.getFacade().getKonkaPeArticleTypeService()
				.getKonkaPeArticleTypeList(konkaPeArticleType);
		request.setAttribute("peArticleTypeList", peArticleTypeList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");

		if (ecUser == null) {
			ecUser = new EcUser();
			ecUser.setUser_name("游客");
			ecUser.setUser_type(new Integer(2));
			ecUser.setId(new Long(1));
			ecUser.setDept_id(0L);
			ecUser.setPlat_sys(new Integer(0));
			ecUser.setCust_id(0L);
			ecUser.setPlat_sys(0);

		}

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.long");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		List<KonkaPeArticleInfo> kinfoList = new ArrayList<KonkaPeArticleInfo>();
		if (id.equals("999999")) {// 常见问题
			KonkaPeArticleInfo kinfo = new KonkaPeArticleInfo();
			kinfo.setDept_id(ecUser.getDept_id());
			kinfo.setMsg_info_type(1);
			kinfo.setArticle_type_id(2500L);
			kinfo.setStates(1L);
			kinfo.setIs_del(0L);
			kinfoList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(kinfo);

		} else if (id.equals("888888")) {// 客户服务
			KonkaPeArticleInfo kinfo = new KonkaPeArticleInfo();
			kinfo.setDept_id(ecUser.getDept_id());
			kinfo.setMsg_info_type(1);
			kinfo.setArticle_type_id(2400L);
			kinfo.setStates(1L);
			kinfo.setIs_del(0L);
			kinfoList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(kinfo);
		}

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		if (id.equals("888888") || id.equals("999999")) {
			if (kinfoList != null && kinfoList.size() > 0) {
				entity = kinfoList.get(0);
			}
		} else {
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		}

		if (null != entity) {
			super.copyProperties(form, entity);
			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(new Long(id));
			attachment.setIs_del((long) 0);
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService()
					.getKonkaPeAttachmentsList(attachment));

			if (null != entity && entity.getView_counts() != null) {
				KonkaPeArticleInfo update = new KonkaPeArticleInfo();
				update.setId(entity.getId());
				update.setView_counts(entity.getView_counts() + 1L);
				super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);
			}
		} else {// 特殊处理球队介绍
			EcSpecFbTeamList es = new EcSpecFbTeamList();
			es.setId(Long.valueOf(id));
			es = super.getFacade().getEcSpecFbTeamListService().getEcSpecFbTeamList(es);

			if (es == null) {
				String msg = super.getMessage(request, "errors.long");
				renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			KonkaPeArticleInfo kp = new KonkaPeArticleInfo();
			kp.setTitle(es.getTeam_name());
			List<KonkaPeArticleInfo> kpList = super.getFacade().getKonkaPeArticleInfoService()
					.getKonkaPeArticleInfoList(kp);
			if (null != kpList && kpList.size() > 0) {
				kp = kpList.get(0);
			} else {
				String msg = super.getMessage(request, "errors.long");
				renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			super.copyProperties(form, kp);
			KonkaPeAttachments attachment = new KonkaPeAttachments();
			attachment.setLink_id(kp.getId());
			attachment.setIs_del((long) 0);
			request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService()
					.getKonkaPeAttachmentsList(attachment));

			if (null != kp && kp.getView_counts() != null) {
				KonkaPeArticleInfo update = new KonkaPeArticleInfo();
				update.setId(kp.getId());
				update.setView_counts(kp.getView_counts() + 1L);
				super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);
			}

			KonkaPeArticleContent kc = new KonkaPeArticleContent();
			kc.setId(kp.getId());
			kc = super.getFacade().getKonkaPeArticleContentService().getKonkaPeArticleContent(kc);

			if (null != kc && null != kc.getContent()) {
				dynaBean.set("content", kc.getContent());
			} else {
				dynaBean.set("content", "");
			}

		}

		return mapping.findForward("view");
	}

	public ActionForward jsNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");

		if (ecUser == null) {
			ecUser = new EcUser();
			ecUser.setUser_name("游客");
			ecUser.setUser_type(new Integer(2));
			ecUser.setId(new Long(1));
			ecUser.setDept_id(0L);
			ecUser.setPlat_sys(new Integer(0));
			ecUser.setCust_id(0L);
			ecUser.setPlat_sys(0);
		}

		String article_type = (String) dynaBean.get("article_type");
		String str = "";
		String ctx = super.getCtxPath(request);
		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(new Long(article_type));
		article.setStates(1L); // 已发布
		article.setMsg_info_type(1); // 触网商城
		article.setIs_del(0L);
		article.setDept_id(ecUser.getDept_id());
		article.getRow().setCount(5);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(article);

		for (int i = 0; i < articleList.size(); i++) {
			article = new KonkaPeArticleInfo();
			article = articleList.get(i);
			if (article.getIs_link_out() != null && article.getIs_link_out().intValue() == 1
					&& article.getLink_out_addr() != null) {
				str += "<p><a href=\"" + article.getLink_out_addr() + "\" target=\"_blank\" >" + article.getTitle()
						+ "</a></p>";
			} else {
				str += "<p><a href=\"" + ctx + "/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=" + article.getId()
						+ "\"> " + article.getTitle() + "</a></p>";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('" + str + "');");
		return null;
	}

	public ActionForward jsNewsdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");

		if (ecUser == null) {
			ecUser = new EcUser();
			ecUser.setUser_name("游客");
			ecUser.setUser_type(new Integer(2));
			ecUser.setId(new Long(1));
			ecUser.setDept_id(0L);
			ecUser.setPlat_sys(new Integer(0));
			ecUser.setCust_id(0L);
			ecUser.setPlat_sys(0);
		}

		String article_type = (String) dynaBean.get("article_type");
		String str = "";
		String ctx = super.getCtxPath(request);
		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(new Long(article_type));
		article.setStates(1L); // 已发布
		article.setMsg_info_type(1); // 触网商城
		article.setIs_del(0L);
		article.setDept_id(ecUser.getDept_id());
		article.getRow().setCount(5);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(article);

		for (int i = 0; i < articleList.size(); i++) {
			article = new KonkaPeArticleInfo();
			article = articleList.get(i);
			if (article.getIs_link_out() != null && article.getIs_link_out().intValue() == 1
					&& article.getLink_out_addr() != null) {
				str += "<dd><a href=\"" + article.getLink_out_addr() + "\" target=\"_blank\" >" + article.getTitle()
						+ "</a></dd>";
			} else {
				str += "<dd><a href=\"" + ctx + "/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=" + article.getId()
						+ "\"> " + article.getTitle() + "</a></dd>";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('" + str + "');");
		return null;
	}

	public ActionForward jsNavInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");

		if (ecUser == null) {
			ecUser = new EcUser();
			ecUser.setUser_name("游客");
			ecUser.setUser_type(new Integer(2));
			ecUser.setId(new Long(1));
			ecUser.setDept_id(0L);
			ecUser.setPlat_sys(new Integer(0));
			ecUser.setCust_id(0L);
			ecUser.setPlat_sys(0);
		}

		String str = "";
		String ctx = super.getCtxPath(request);
		// 取资讯

		EcNavInfo en = new EcNavInfo();
		en.setDel_mark(0);
		en.setDept_id(ecUser.getDept_id());
		en.setIs_show(0);
		en.setOwn_sys(2);
		en.setPlat_sys(ecUser.getPlat_sys());
		en.getRow().setCount(6);
		List<EcNavInfo> articleList = super.getFacade().getEcNavInfoService().getEcNavInfoList(en);

		for (int i = 0; i < articleList.size(); i++) {
			en = new EcNavInfo();
			en = articleList.get(i);
			if (en.getLogo_url() != null) {
				str += "<li><a href=\"" + en.getLogo_url() + "\" >" + en.getTitle() + "</a></li>";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('" + str + "');");
		return null;
	}

	public ActionForward jsLogInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");

		if (ecUser == null) {
			ecUser = new EcUser();
			ecUser.setUser_name("游客");
			ecUser.setUser_type(new Integer(2));
			ecUser.setId(new Long(1));
			ecUser.setDept_id(0L);
			ecUser.setPlat_sys(new Integer(0));
			ecUser.setCust_id(0L);
			ecUser.setPlat_sys(0);
		}

		String str = "";
		String ctx = super.getCtxPath(request);
		// 取Log

		EcLogoInfo einfo = new EcLogoInfo();
		einfo.setDel_mark(0);
		einfo.setDept_id(ecUser.getDept_id());
		einfo.setOwn_sys(2);
		einfo.setPlat_sys(ecUser.getPlat_sys());
		List<EcLogoInfo> infoList = super.getFacade().getEcLogoInfoService().getEcLogoInfoList(einfo);
		if (infoList != null && infoList.size() > 0) {
			einfo = infoList.get(0);
			// <img
			// src="http://192.168.2.218:18080/files/lunbotu/2015/01/11/c8317bc5-5b19-464f-b6a0-4e5a18e7ebd5.jpg"
			// height="65">
			if (einfo.getLogo_url() != null) {
				str += "<img src=\"" + einfo.getLogo_url() + "\" height=\"65\" >";
			}
		}

		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('" + str + "');");
		return null;
	}

	public ActionForward jsNewsLi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String article_type = (String) dynaBean.get("article_type");
		String str = "";
		String ctx = super.getCtxPath(request);
		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(new Long(article_type));
		article.setStates(1L); // 已发布
		article.setMsg_info_type(5); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(5);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(article);

		for (int i = 0; i < articleList.size(); i++) {
			article = new KonkaPeArticleInfo();
			article = articleList.get(i);
			if (article.getIs_link_out() != null && article.getIs_link_out().intValue() == 1
					&& article.getLink_out_addr() != null) {
				str += "<li><a href=\"" + article.getLink_out_addr() + "\" target=\"_blank\" >" + article.getTitle()
						+ "</a></li>";
			} else {
				str += "<li><a href=\"" + ctx + "/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=" + article.getId()
						+ "\"> " + article.getTitle() + "</a></li>";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('" + str + "');");
		return null;
	}
}
