package com.ebiz.mmt.web.struts.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;

/**
 * @author TUDP
 * @version 2013-12-02
 */
public class TvPdAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		long l = 0L;

		Integer[] prod_type = new Integer[] { 0 };

		String p_index = super.getPIndexByRequest(request);
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

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

		// 按排序值或者添加时间倒序，取 特惠产品 8个
		List<KonkaBcompPd> bcomp_pd_list_3 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        Long.valueOf(p_index), 3, 8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_3", bcomp_pd_list_3);

		// 销量排行前8
		List<KonkaBcompPd> bcomp_pd_list_top_8 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, null,
		        8, ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
		request.setAttribute("bcomp_pd_list_top_8", bcomp_pd_list_top_8);

		// 热卖推荐
		List<KonkaBcompPd> bcomp_pd_list_tj_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 6,
		        ecUser.getDept_id(), ecUser.getCust_id(), prod_type);
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
