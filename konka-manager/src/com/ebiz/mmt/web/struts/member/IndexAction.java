package com.ebiz.mmt.web.struts.member;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;

/**
 * @author TUDP
 * @version 2013-12-02
 */
public class IndexAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		long l = 0L;
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L + l); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(10); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		String p_index = super.getPIndexByRequest(request);
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
		Integer[] prod_type = new Integer[] { 0 };

		// 按排序值或者添加时间倒序，取 新品产品8 个
		List<KonkaBcompPd> bcomp_pd_list_0 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 0, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_0", bcomp_pd_list_0);

		// 按排序值或者添加时间倒序，取 精品 8个
		List<KonkaBcompPd> bcomp_pd_list_7 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 7, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_7", bcomp_pd_list_7);

		// 按排序值或者添加时间倒序，取 热销产品 8 个
		List<KonkaBcompPd> bcomp_pd_list_2 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 2, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_2", bcomp_pd_list_2);

		// 按排序值或者添加时间倒序，取 特惠产品 4个
		List<KonkaBcompPd> bcomp_pd_list_3 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 3, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_3", bcomp_pd_list_3);

		prod_type = new Integer[] { 0 };
		// 取4个产品 -工程机
		List<KonkaBcompPd> bcomp_pd_list_x = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 4, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_x", bcomp_pd_list_x);

		prod_type = new Integer[] { 4 };
		// 取4个产品 -冰箱
		List<KonkaBcompPd> bcomp_pd_list_4 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_4", bcomp_pd_list_4);

		prod_type = new Integer[] { 5 };
		// 取4个产品 -洗衣机
		List<KonkaBcompPd> bcomp_pd_list_5 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_5", bcomp_pd_list_5);

		prod_type = new Integer[] { 6 };
		// 取4个产品 -空调
		List<KonkaBcompPd> bcomp_pd_list_6 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_6", bcomp_pd_list_6);

		prod_type = new Integer[] { 7 };
		// 取4个产品 -手机
		List<KonkaBcompPd> bcomp_pd_list_9 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_9", bcomp_pd_list_9);

		prod_type = new Integer[] { 9 };
		// 取4个产品 -戴尔
		List<KonkaBcompPd> bcomp_pd_list_10 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_10", bcomp_pd_list_10);

		prod_type = new Integer[] { 3 };
		// 取8个产品 -小家电
		List<KonkaBcompPd> bcomp_pd_list_31 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_31", bcomp_pd_list_31);

		// 销量排行前8
		List<KonkaBcompPd> bcomp_pd_list_top_8 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, null,
		        8, ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_top_8", bcomp_pd_list_top_8);

		// 热卖推荐
		List<KonkaBcompPd> bcomp_pd_list_tj_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 6,
		        ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_tj_5", bcomp_pd_list_tj_5);

		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(2000L);
		article.setStates(1L); // 已发布
		article.setMsg_info_type(0); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(18);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
		        .getKonkaPeArticleInfoList(article);
		request.setAttribute("articleList", articleList);

		return mapping.findForward("list");
	}

	public ActionForward index_new(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		long l = 0L;
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(100010L + l); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		String p_index = super.getPIndexByRequest(request);
		logger.info("----p_index--->{}", p_index);
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		Integer[] prod_type = new Integer[] { 0 };

		// 取8个产品 -电视
		List<KonkaBcompPd> bcomp_pd_list_0 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 99, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_0", bcomp_pd_list_0);

		prod_type = new Integer[] { 3 };
		// 取8个产品 -小家电
		List<KonkaBcompPd> bcomp_pd_list_3 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_3", bcomp_pd_list_3);

		prod_type = new Integer[] { 4 };
		// 取4个产品 -冰箱
		List<KonkaBcompPd> bcomp_pd_list_4 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_4", bcomp_pd_list_4);

		prod_type = new Integer[] { 5 };
		// 取4个产品 -洗衣机
		List<KonkaBcompPd> bcomp_pd_list_5 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_5", bcomp_pd_list_5);

		prod_type = new Integer[] { 6 };
		// 取4个产品 -空调
		List<KonkaBcompPd> bcomp_pd_list_6 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_6", bcomp_pd_list_6);

		prod_type = new Integer[] { 0 };
		// 取4个产品 -工程机
		List<KonkaBcompPd> bcomp_pd_list_x = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 4, 4, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_x", bcomp_pd_list_x);

		// 销量排行前8
		List<KonkaBcompPd> bcomp_pd_list_top_8 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, null,
		        8, ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_top_8", bcomp_pd_list_top_8);

		// 热卖推荐
		List<KonkaBcompPd> bcomp_pd_list_tj_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,
		        ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_tj_5", bcomp_pd_list_tj_5);

		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setArticle_type_id(2000L);
		article.setStates(1L); // 已发布
		article.setMsg_info_type(0); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(18);
		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
		        .getKonkaPeArticleInfoList(article);
		request.setAttribute("articleList", articleList);

		return mapping.findForward("list");
	}

}
