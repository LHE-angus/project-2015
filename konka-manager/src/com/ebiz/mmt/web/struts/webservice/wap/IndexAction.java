package com.ebiz.mmt.web.struts.webservice.wap;

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
import com.ebiz.mmt.domain.EcArticleInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.wap.BaseMemberAction;

/**
 * @author TUDP
 * @version 2013-12-02
 */
public class IndexAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String p_index = null;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute(Constants.EPP_USER);

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
				p_index = "440300";
			}
		}
		long l = 0L;
		ArticleImg img = new ArticleImg();
		if (ecUser.getUser_type().intValue() == 1) {
			img.setNews_module(2100L);
		} else {
			img.setNews_module(2111L);
			img.setDept_id(ecUser.getDept_id());
		}  
		img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5);
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);

		for (int i = 0; i < imgList.size(); i++) {
			ArticleImg a = (ArticleImg) imgList.get(i);
			String url = a.getImage_url();
			if (url != null) {
				String[] dd = url.split("%2C");
				if(dd.length>1){ 
				for (int z = 0; z < dd.length; z++) {
					if ("productDetail".equals(dd[z]) && z + 1 < dd.length) {
						url = "PdShow.do?goods_id=" + dd[z + 1];
						a.getMap().put("url", url);
						imgList.set(i, a);
					}
				}
				}else if(url.indexOf("webservice")!=-1){
					a.getMap().put("url", url);
				}
			}
		}
		request.setAttribute("imgList", imgList);
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
		Integer[] prod_type = new Integer[] { 0 };
		if (ecUser.getUser_type().intValue() == 1 & ecUser.getIs_epp_fgs() != null && ecUser.getIs_epp_fgs().intValue() == 0) {
			List<KonkaBcompPd> bcomp_pd_list_0 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), 0, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_0", bcomp_pd_list_0);

			List<KonkaBcompPd> bcomp_pd_list_7 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), 7, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_7", bcomp_pd_list_7);
			List<KonkaBcompPd> bcomp_pd_list_2 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), 2, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_2", bcomp_pd_list_2);

			List<KonkaBcompPd> bcomp_pd_list_3 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), 3, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_3", bcomp_pd_list_3);

			prod_type = new Integer[] { 0 };
			List<KonkaBcompPd> bcomp_pd_list_x = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), 4, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_x", bcomp_pd_list_x);

			prod_type = new Integer[] { 4 };
			List<KonkaBcompPd> bcomp_pd_list_4 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_4", bcomp_pd_list_4);

			prod_type = new Integer[] { 5 };
			List<KonkaBcompPd> bcomp_pd_list_5 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_5", bcomp_pd_list_5);

			prod_type = new Integer[] { 6 };
			List<KonkaBcompPd> bcomp_pd_list_6 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_6", bcomp_pd_list_6);

			prod_type = new Integer[] { 3 };
			List<KonkaBcompPd> bcomp_pd_list_31 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), null, 3, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_31", bcomp_pd_list_31);
			List<KonkaBcompPd> bcomp_pd_list_tj_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5, ecUser.getDept_id(), ecUser.getCust_id(), null);
			request.setAttribute("bcomp_pd_list_tj_5", bcomp_pd_list_tj_5);

		} else {
			List<KonkaBcompPd> bcomp_pd_list_0 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), 0, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_0", bcomp_pd_list_0);

			List<KonkaBcompPd> bcomp_pd_list_7 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), 7, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_7", bcomp_pd_list_7);

			List<KonkaBcompPd> bcomp_pd_list_2 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), 2, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_2", bcomp_pd_list_2);
			List<KonkaBcompPd> bcomp_pd_list_3 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), 3, 8, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_3", bcomp_pd_list_3);

			prod_type = new Integer[] { 0 };
			List<KonkaBcompPd> bcomp_pd_list_x = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), 4, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_x", bcomp_pd_list_x);

			prod_type = new Integer[] { 4 };
			List<KonkaBcompPd> bcomp_pd_list_4 = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type);
			request.setAttribute("bcomp_pd_list_4", bcomp_pd_list_4);

			prod_type = new Integer[] { 5 };
			List<KonkaBcompPd> bcomp_pd_list_5 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_5", bcomp_pd_list_5);

			prod_type = new Integer[] { 6 };
			List<KonkaBcompPd> bcomp_pd_list_6 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), null, 4, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_6", bcomp_pd_list_6);

			prod_type = new Integer[] { 3 };
			List<KonkaBcompPd> bcomp_pd_list_31 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(ecUser.getUser_type(), Long.valueOf(p_index), null, 3, ecUser.getDept_id(), ecUser.getCust_id(),
					prod_type, ecUser.getPlat_sys());
			request.setAttribute("bcomp_pd_list_31", bcomp_pd_list_31);

			List<KonkaBcompPd> bcomp_pd_list_tj_5 = super.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5, ecUser.getDept_id(), ecUser.getCust_id(), null);
			request.setAttribute("bcomp_pd_list_tj_5", bcomp_pd_list_tj_5);
		}

		EcArticleInfo ecArticleInfo = new EcArticleInfo();
		ecArticleInfo.setDel_mark(0);
		ecArticleInfo.setInfo_state(new Integer(1));
		ecArticleInfo.setOwn_sys(ecUser.getUser_type());
		ecArticleInfo.setPlat_sys(ecUser.getPlat_sys());
		ecArticleInfo.setDept_id(ecUser.getDept_id());
		List<EcArticleInfo> ecArticleInfoList = super.getFacade().getEcArticleInfoService().getEcArticleInfoList(ecArticleInfo);
		if (ecArticleInfoList != null && ecArticleInfoList.size() > 0) {
			ecArticleInfo = ecArticleInfoList.get(0);
			request.setAttribute("ecArticleInfo", ecArticleInfo);
		} else {
			ecArticleInfo = new EcArticleInfo();
			ecArticleInfo.setDel_mark(0);
			ecArticleInfo.setInfo_state(new Integer(1));
			ecArticleInfo.setOwn_sys(2);
			ecArticleInfo.setPlat_sys(0);
			ecArticleInfo.setDept_id(0L);
			ecArticleInfoList = super.getFacade().getEcArticleInfoService().getEcArticleInfoList(ecArticleInfo);
			if (ecArticleInfoList != null && ecArticleInfoList.size() > 0) {
				request.setAttribute("ecArticleInfo", ecArticleInfoList.get(0));
			}
		}

		return mapping.findForward("list");
	}
}
