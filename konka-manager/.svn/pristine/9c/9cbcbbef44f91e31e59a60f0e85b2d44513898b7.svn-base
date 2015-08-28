package com.ebiz.mmt.web.struts.sfmall;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-12-12
 */
public class HdztAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		// 取资讯
	    KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		DynaBean dynaBean = (DynaBean) form;
		String in_id = (String) dynaBean.get("id");
		//如果有参数，则按照指定的获取，否则获取世界杯专题
		if(StringUtils.isNotEmpty(in_id) && NumberUtils.isNumber(in_id)){
			article.setId(Long.valueOf(in_id));//
		}else{
			article.setArticle_mod_id(915701L);//活动专题
		}  
		article.setStates(1L); // 已发布
		article.setMsg_info_type(3); 
		article.setIs_del(0L);
		article.getRow().setCount(1);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article); 
		if(articleList!=null&&articleList.size()>0){
			Long id=articleList.get(0).getId() ;
			KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
			entity.setId(Long.valueOf(id)); 
			entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);
			request.setAttribute("entity", entity);
		}			 
		return mapping.findForward("list"); 
	} 
}