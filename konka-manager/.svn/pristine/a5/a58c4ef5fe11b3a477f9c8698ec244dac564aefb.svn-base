package com.ebiz.mmt.web.struts.zxmall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPeArticleInfo;

/**
 * @author tudp
 * @version 2014-07-09
 */
public class FlvPlayerAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String imgUrl = (String) dynaBean.get("imgUrl");
		String flvUrl = (String) dynaBean.get("flvUrl");
		String width = (String) dynaBean.get("width");
		String height = (String) dynaBean.get("height");
		
		if(!"".equals(imgUrl)&&imgUrl!=null){
			imgUrl=imgUrl.trim();
			request.setAttribute("imgUrl", imgUrl);
		}
		if(!"".equals(flvUrl)&&flvUrl!=null){
			flvUrl=flvUrl.trim();
			flvUrl=java.net.URLDecoder.decode(flvUrl,"utf-8");
			request.setAttribute("flvUrl", flvUrl);
		}
		if(!"".equals(width)&&width!=null){
			width=width.trim();
			request.setAttribute("width", width);
		}
		if(!"".equals(height)&&height!=null){
			height=height.trim();
			request.setAttribute("width", width);
		}
		
		
		return mapping.findForward("list"); 
		//return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		DynaBean dynaBean = (DynaBean) form; 
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();  
		article.setArticle_type_id(new Long(4001));
		article.setStates(1L); // 已发布
		article.setMsg_info_type(3); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(1);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article); 			 
		if(articleList.size()>0){
			article=articleList.get(0);
			String imgUrl = article.getImg_path();
			String flvUrl =article.getLink_out_addr();
			String width = (String) dynaBean.get("width");
			String height = (String) dynaBean.get("height");
			request.setAttribute("flvUrl", flvUrl);
			request.setAttribute("imgUrl", imgUrl);
			request.setAttribute("width", width);
			request.setAttribute("height", height); 
		}else{
			return null;
		}
		return mapping.findForward("list"); 
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		DynaBean dynaBean = (DynaBean) form; 
		String id= (String)dynaBean.get("id");
		KonkaPeArticleInfo article = new KonkaPeArticleInfo(); 
		article.setId(new Long(id));
		article.setArticle_type_id(new Long(4001)); 
		article.setMsg_info_type(3); 
		article.setIs_del(0L);
		article.getRow().setCount(1);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article); 			 
		if(articleList.size()>0){
			article=articleList.get(0);
			String imgUrl = article.getImg_path();
			String flvUrl =article.getLink_out_addr();
			String width = (String) dynaBean.get("width");
			String height = (String) dynaBean.get("height");
			request.setAttribute("flvUrl", flvUrl);
			request.setAttribute("imgUrl", imgUrl);
			request.setAttribute("width", width);
			request.setAttribute("height", height); 
		}else{
			return null;
		}
		return mapping.findForward("list"); 
	}
	
	public ActionForward showImg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		DynaBean dynaBean = (DynaBean) form; 
		String id= (String)dynaBean.get("id");
		KonkaPeArticleInfo article = new KonkaPeArticleInfo(); 
		article.setId(new Long(id));
		article.setArticle_type_id(new Long(4001)); 
		article.setMsg_info_type(3); 
		article.setIs_del(0L);
		article.getRow().setCount(1);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article); 			 
		if(articleList.size()>0){
			article=articleList.get(0);
			String imgUrl = article.getImg_path();
			String flvUrl =article.getLink_out_addr();
			String width = (String) dynaBean.get("width");
			String height = (String) dynaBean.get("height");
			request.setAttribute("flvUrl", flvUrl);
			request.setAttribute("imgUrl", imgUrl);
			request.setAttribute("width", width);
			request.setAttribute("height", height); 
		}else{
			return null;
		}
		return mapping.findForward("view"); 
	} 
}