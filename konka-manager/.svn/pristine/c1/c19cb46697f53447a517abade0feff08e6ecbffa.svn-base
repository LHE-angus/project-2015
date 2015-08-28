package com.ebiz.mmt.web.struts.zxmall;

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
import com.ebiz.mmt.domain.EcHomeFloor;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;

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
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		String p_index = null;
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
		if (p_index == null && ecUser != null && ecUser.getUser_type().intValue() == 2 && ecUser.getP_index() != null) {
			p_index = ecUser.getP_index().toString();
		}
		if (p_index == null || "".equals(p_index)) {
			try {
				p_index = super.getPIndexByRequest(request);
			} catch (Exception e) {
				p_index = "440300";// 默认等于深圳
			}
		}

		String requestUrl = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}

		if (ecUser.getIs_act() != null && ecUser.getIs_act().intValue() != 0) {
			if (requestUrl.indexOf("zxmall/center/RegUser.do") == -1
			        && requestUrl.indexOf("KonkaGroupPeArticleInfo") == -1) {
				response.sendRedirect(contextPath + "/zxmall/center/RegUser.do?");
				return null;
			}
		}

		Integer own_sys = ecUser.getUser_type();
		if (ecUser.getUser_type().intValue() == 1 && ecUser.getDept_id().intValue() > 0) {
			own_sys = new Integer(5);
		}
		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(200010L); // 在线商城首页轮播图
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.setDept_id(ecUser.getDept_id());
		img.getRow().setCount(10); // 数量
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		// String p_index = super.getPIndexByRequest(request);
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index)); 
		// 热卖推荐
		List<KonkaBcompPd> bcomp_pd_list_tj_5 = super.getKonkaBcompTopNewList(own_sys, p_index_array, 1, 6, ecUser
		        .getDept_id(), ecUser.getCust_id(), null, ecUser.getPlat_sys());
		request.setAttribute("bcomp_pd_list_tj_5", bcomp_pd_list_tj_5); 
		 
		//取楼层信息
		EcHomeFloor ecHomeFloor= new EcHomeFloor();
		ecHomeFloor.setPlat_sys(ecUser.getPlat_sys().longValue());
		ecHomeFloor.setOwn_sys(ecUser.getUser_type().longValue());
		ecHomeFloor.setDept_id(ecUser.getDept_id()); 
		ecHomeFloor.setInfo_state(new Integer(1));
		ecHomeFloor.setIs_del(0L);
		List<EcHomeFloor> ecHomeFloorList = super.getFacade().getEcHomeFloorService().getEcHomeFloorList(ecHomeFloor);
		request.setAttribute("ecHomeFloorList", ecHomeFloorList); 

		return mapping.findForward("list");
	}
}
