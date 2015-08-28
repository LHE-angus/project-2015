package com.ebiz.mmt.web.struts.touch;

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
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserFavotrites;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-11
 */
public class ArticleInfoAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		// 取新闻
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setArticle_mod_id(null); // TODO EC...模块ID，这里先为空
		entity.setStates(1L); // 已发布
		entity.setMsg_info_type(0); // 公开
		entity.setIs_del(0L);

		Long recordCount = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoCount(entity);
		pager.init(recordCount, new Integer(20), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		request.setAttribute("entityList", super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoPaginatedList(entity));

		String p_index = super.getPIndexByRequest(request);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}

		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(1, dept_sn_array, p_index_array, null, 5, true);
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);

		if (null != ecUser) {
			// 关注商品
			EcUserFavotrites ecUserFavotries = new EcUserFavotrites();
			ecUserFavotries.setUser_id(ecUser.getId());
			ecUserFavotries.setOwn_sys(ecUser.getUser_type());
			ecUserFavotries.getRow().setFirst(0);
			ecUserFavotries.getRow().setCount(2);
			request.setAttribute("ecUserFavotriesList",
					super.getFacade().getEcUserFavotritesService().getEcUserFavotritesPaginatedList(ecUserFavotries));
		}

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Key" });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		// 排行榜
		String p_index = super.getPIndexByRequest(request);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = new Long[4];
		p_index_array[0] = -1L;
		p_index_array[1] = -1L;
		p_index_array[2] = -1L;
		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.getRow().setCount(10);
		bplf.setP_index(Long.valueOf(p_index));
		List<BaseProvinceListFour> bplfList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourParentList(bplf);
		for (BaseProvinceListFour baseProvinceListFour : bplfList) {
			if (baseProvinceListFour.getP_level() == 1)
				p_index_array[0] = baseProvinceListFour.getP_index();
			if (baseProvinceListFour.getP_level() == 2)
				p_index_array[1] = baseProvinceListFour.getP_index();
			if (baseProvinceListFour.getP_level() == 3)
				p_index_array[2] = baseProvinceListFour.getP_index();
		}
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}
		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(1, dept_sn_array, p_index_array, null, 5, true);
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);

		// 关注商品
		if (null != ecUser) {
			EcUserFavotrites ecUserFavotries = new EcUserFavotrites();
			ecUserFavotries.setUser_id(ecUser.getId());
			ecUserFavotries.setOwn_sys(ecUser.getUser_type());
			ecUserFavotries.getRow().setFirst(0);
			ecUserFavotries.getRow().setCount(2);
			request.setAttribute("ecUserFavotriesList",
					super.getFacade().getEcUserFavotritesService().getEcUserFavotritesPaginatedList(ecUserFavotries));
		}

		// 取新闻内容
		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfo(entity);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		attachment.setIs_del((long) 0);
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

		KonkaPeArticleInfo update = new KonkaPeArticleInfo();
		update.setId(entity.getId());
		update.setView_counts(entity.getView_counts() + 1L);
		super.getFacade().getKonkaPeArticleInfoService().modifyKonkaPeArticleInfo(update);

		request.setAttribute("entity", entity);
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
			article.setMsg_info_type(0); // 公开
			article.setIs_del(0L);
			article.getRow().setCount(5);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService().getKonkaPeArticleInfoList(article);
		
		for(int i=0;i<articleList.size();i++){
			article = new KonkaPeArticleInfo();
			article = articleList.get(i);
			str+="<p><a href=\""+ctx+"/touch/KonkaGroupPeArticleInfo.do?method=view&id="+article.getId()+"\"> "+article.getTitle()+"</a></p>";
		}
		response.setCharacterEncoding("GBK");
		response.getWriter().println("document.write('"+str+"');");
		return null;				
	}
}