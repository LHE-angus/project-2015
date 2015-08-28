package com.ebiz.mmt.web.struts.member;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcBuyInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;

/**
 * @author tudp
 * @version 2013-12-12
 */
public class DzcgAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		long l = 0L;
		if (ecUser.getUser_type().intValue() == 2) {
			l = 100000L;
		}
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L + l); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(3200L);
		article.setStates(1L); // 已发布
		article.setMsg_info_type(0); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(1);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
		        .getKonkaPeArticleInfoList(article);
		if (articleList != null && articleList.size() > 0) {
			Long id = articleList.get(0).getId();
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
			request.setAttribute("entity", entity);
		}

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String verificationCode = (String) dynaBean.get("verificationCode");

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		EcBuyInfo entity = new EcBuyInfo();
		super.copyProperties(entity, form);
		if (null != ecUser) {
			entity.setEc_user_id(ecUser.getId());
			entity.setOwn_sys(ecUser.getUser_type());
		}
		entity.setAdd_date(new Date());

		String msg = "";
		if (StringUtils.isBlank(verificationCode)) {
			msg = "验证码不能为空";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		if (!verificationCode.equals((String) session.getAttribute("verificationCode"))) {
			msg = "验证码不正确";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		super.getFacade().getEcBuyInfoService().createEcBuyInfo(entity);

		msg = "信息提交成功！";
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
		        + super.getCtxPath(request) + "/member/Dzcg.do';}");
		return null;

	}
}