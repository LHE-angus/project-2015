package com.ebiz.mmt.web.struts.shop;

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
import com.ebiz.mmt.domain.EcUser;
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
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		 long l=0L;
		 if(ecUser.getUser_type().intValue()==2){
			 l=100000L;
		 }
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L+l); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

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
		// entity.getMap().put("article_type_id_in", new Long[] { 2000L, 2100L, 2200L, 2300L, 2400L, 2500L });
		entity.setArticle_mod_id(Long.valueOf(905701));
		entity.setIs_del(0L);
		entity.setStates(1L);
		entity.setMsg_info_type(new Integer(2));//所属系统 0内购,1触网,2newshop,3顺丰触网
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

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.long");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
		super.copyProperties(form, entity);

		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		attachment.setIs_del((long) 0);
		request.setAttribute("attachmentList",
				getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

		if (null != entity && entity.getView_counts() != null) {
			KonkaPeArticleInfo update = new KonkaPeArticleInfo();
			update.setId(entity.getId());
			update.setView_counts(entity.getView_counts() + 1L);
			super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);
		}

		return mapping.findForward("view");
	}
	public ActionForward jsNews(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String article_type = (String) dynaBean.get("article_type");
		String str="";
		String ctx =super.getCtxPath(request);
		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
			article.setArticle_type_id(new Long(article_type));
			article.setStates(1L); // 已发布
			article.setMsg_info_type(2); // 公开
			article.setIs_del(0L);
			article.getRow().setCount(5);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article);
		
		for(int i=0;i<articleList.size();i++){
			article = new KonkaPeArticleInfo();
			article = articleList.get(i);
			if(article.getIs_link_out()!=null&&article.getIs_link_out().intValue()==1&&article.getLink_out_addr()!=null){
				str+="<p><a href=\""+article.getLink_out_addr()+"\" target=\"_blank\" >"+article.getTitle()+"</a></p>";					
			}else{
			str+="<p><a href=\""+ctx+"/shop/KonkaGroupPeArticleInfo.do?method=view&id="+article.getId()+"\"> "+article.getTitle()+"</a></p>";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('"+str+"');");
		return null;				
	}
	public ActionForward jsNewsLi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String article_type = (String) dynaBean.get("article_type");
		String str="";
		String ctx =super.getCtxPath(request);
		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
			article.setArticle_type_id(new Long(article_type));
			article.setStates(1L); // 已发布
			article.setMsg_info_type(2); // 公开
			article.setIs_del(0L);
			article.getRow().setCount(5);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article);
		
		for(int i=0;i<articleList.size();i++){
			article = new KonkaPeArticleInfo();
			article = articleList.get(i);
			if(article.getIs_link_out()!=null&&article.getIs_link_out().intValue()==1&&article.getLink_out_addr()!=null){
				str+="<li><a href=\""+article.getLink_out_addr()+"\" target=\"_blank\" >"+article.getTitle()+"</a></li>";					
			}else{
			str+="<li><a href=\""+ctx+"/shop/KonkaGroupPeArticleInfo.do?method=view&id="+article.getId()+"\"> "+article.getTitle()+"</a></li>";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("document.write('"+str+"');");
		return null;				
	}
}
