package com.ebiz.mmt.web.struts.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.EcBcompPdUp;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class PshowOrderPanicBuyingAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		String p_index = super.getPIndexByRequest(request);
		logger.info("----p_index--->{}", p_index);

		// 得到当前请求的地址
		request.setAttribute("p_full_name", super.getPIndexName(p_index, ""));

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
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

		// 查询省份信息
		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_level(1);
		bplf.setDel_mark(0);
		List<BaseProvinceListFour> bplfList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(bplf);
		for (BaseProvinceListFour t : bplfList) {
			if (StringUtils.substring(t.getP_index().toString(), 0, 2).equals(StringUtils.substring(p_index, 0, 2))) {
				request.setAttribute("province_name", t.getP_name());
				request.setAttribute("province_index", t.getP_index());
			}
		}
		request.setAttribute("baseProvinceListFourList", bplfList);

		// 销量排行前5
		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,
		        ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);

		List<KonkaBcompPd> entityList = super.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(),
		        new Long(p_index), 5, null, ecUser.getDept_id(), ecUser.getC_id(), null);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (null != entityList && entityList.size() > 0) {
			for (KonkaBcompPd t : entityList) {
				EcBcompPdUp ep = new EcBcompPdUp();
				ep.setBcomp_pd_id(t.getId());
				ep.setOwn_sys(ecUser.getUser_type());
				if (ecUser.getUser_type().intValue() == 2) {
					ep.setAdded_dept_id(ecUser.getDept_id());
				}
				List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);

				if (ecBcompPdUpList != null && ecBcompPdUpList.size() > 0) {

					// 取限时抢购商品价格的结束时间作为抢购结束时间
					EcGoodsPrice ecg = new EcGoodsPrice();
					ecg.setGoods_id(t.getId());
					ecg.setOwn_sys(1);
					ecg.setPrice_type(0);
					ecg = super.getFacade().getEcGoodsPriceService().getEcGoodsPrice(ecg);

					Calendar c = Calendar.getInstance();
					// c.setTime(ecBcompPdUpList.get(0).getDown_time());
					if (ecg != null && ecg.getEnd_time() != null) {
						c.setTime(ecg.getStart_time());// 
					}
					// t.setTimeRemains(c.getTimeInMillis());//

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());

					t.setTimeRemains(c.getTimeInMillis() - calendar.getTimeInMillis());// 离抢购开始还剩多久

					t.getMap().put("start_time", sf.format(ecg.getStart_time()).substring(11, 16));
					t.getMap().put("end_time", sf.format(ecg.getEnd_time()).substring(11, 16));
				}
			}
		}

		request.setAttribute("entityList", entityList);

		// request.setAttribute("today", calendar.getTimeInMillis());

		// List<KonkaBcompPd> pd_list_cate5 =
		// getKonkaBcompTopList2(ecUser.getUser_type(), p_index_array, 5, 1,
		// ecUser
		// .getDept_id(), ecUser.getC_id(), null);
		// request.setAttribute("pd_list_cate5", pd_list_cate5);

		// 新闻资讯
		/*
		 * KonkaPeArticleInfo konkaPeArticleInfo = new KonkaPeArticleInfo();
		 * konkaPeArticleInfo.setArticle_type_id(2000L);
		 * konkaPeArticleInfo.setArticle_mod_id(Long.valueOf(905701));
		 * konkaPeArticleInfo.setIs_del(0L); konkaPeArticleInfo.setStates(1L);
		 * konkaPeArticleInfo.getRow().setCount(5); List<KonkaPeArticleInfo>
		 * konkaPeArticleInfoList =
		 * super.getFacade().getKonkaPeArticleInfoService()
		 * .getKonkaPeArticleInfoList(konkaPeArticleInfo);
		 * request.setAttribute("konkaPeArticleInfoList",
		 * konkaPeArticleInfoList);
		 */
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		String p_index = super.getPIndexByRequest(request);
		logger.info("----p_index--->{}", p_index);

		// 得到当前请求的地址
		request.setAttribute("p_full_name", super.getPIndexName(p_index, ""));

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
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

		// 销量排行前5
		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5,
		        ecUser.getDept_id(), ecUser.getCust_id(), null);
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);

		// 查询省份信息
		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_level(1);
		bplf.setDel_mark(0);
		List<BaseProvinceListFour> bplfList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(bplf);
		for (BaseProvinceListFour t : bplfList) {
			if (StringUtils.substring(t.getP_index().toString(), 0, 2).equals(StringUtils.substring(p_index, 0, 2))) {
				request.setAttribute("province_name", t.getP_name());
				request.setAttribute("province_index", t.getP_index());
			}
		}
		request.setAttribute("baseProvinceListFourList", bplfList);

		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (GenericValidator.isLong(goods_id)) {
			KonkaBcompPd entity = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request), ecUser
			        .getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array, ecUser.getDept_id(),
			        ecUser.getCust_id());

			if (null != entity) {
				EcBcompPdUp ep = new EcBcompPdUp();
				ep.setBcomp_pd_id(new Long(goods_id));
				ep.setOwn_sys(ecUser.getUser_type());
				if (ecUser.getUser_type().intValue() == 2) {
					ep.setAdded_dept_id(ecUser.getDept_id());
				}
				List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);
				// Calendar c = Calendar.getInstance();
				if (ecBcompPdUpList != null && ecBcompPdUpList.size() > 0) {
					EcGoodsPrice ecg = new EcGoodsPrice();
					ecg.setGoods_id(Long.valueOf(goods_id));
					ecg.setOwn_sys(1);
					ecg.setPrice_type(0);
					ecg = super.getFacade().getEcGoodsPriceService().getEcGoodsPrice(ecg);

					Calendar c = Calendar.getInstance();
					// c.setTime(ecBcompPdUpList.get(0).getDown_time());
					if (ecg != null && ecg.getEnd_time() != null) {
						c.setTime(ecg.getStart_time());// 
					}// 
					// t.setTimeRemains(c.getTimeInMillis());//

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());

					entity.setTimeRemains(c.getTimeInMillis() - calendar.getTimeInMillis());// 离抢购结束还剩多久

					entity.getMap().put("start_time", sf.format(ecg.getStart_time()).substring(11, 16));
					entity.getMap().put("end_time", sf.format(ecg.getEnd_time()).substring(11, 16));

				}
			}
			request.setAttribute("konkaBcompPd", entity);

			// Calendar calendar = Calendar.getInstance();
			// calendar.setTime(new Date());
			// request.setAttribute("today", calendar.getTimeInMillis());

			// List<KonkaBcompPd> pd_list_cate5 =
			// getKonkaBcompTopList2(ecUser.getUser_type(), p_index_array, 5, 1,
			// ecUser
			// .getDept_id(), ecUser.getC_id(), null);
			// request.setAttribute("pd_list_cate5", pd_list_cate5);

			// 新闻资讯
			/*
			 * KonkaPeArticleInfo konkaPeArticleInfo = new KonkaPeArticleInfo();
			 * konkaPeArticleInfo.setArticle_type_id(2000L);
			 * konkaPeArticleInfo.setArticle_mod_id(Long.valueOf(905701));
			 * konkaPeArticleInfo.setIs_del(0L);
			 * konkaPeArticleInfo.setStates(1L);
			 * konkaPeArticleInfo.getRow().setCount(5); List<KonkaPeArticleInfo>
			 * konkaPeArticleInfoList =
			 * super.getFacade().getKonkaPeArticleInfoService()
			 * .getKonkaPeArticleInfoList(konkaPeArticleInfo);
			 * request.setAttribute("konkaPeArticleInfoList",
			 * konkaPeArticleInfoList);
			 */
		} else {
			this.saveError(request, "errors.long", new String[] { goods_id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		Date now = new Date();
		long now_date = now.getTime();

		// 取全国仓库库存
		EcStocks es = new EcStocks();
		es.setGoods_id(Long.valueOf(goods_id));
		List<EcStocks> esList = super.getFacade().getEcStocksService().getEcStocksList(es);
		int count = 0;
		if (null != esList && esList.size() > 0) {
			for (EcStocks ecStocks : esList) {
				EcBaseStore ebs = new EcBaseStore();
				ebs.setStore_id(ecStocks.getStore_id());
				ebs = super.getFacade().getEcBaseStoreService().getEcBaseStore(ebs);
				if (null != ebs && null != ebs.getOwn_sys() && null != ebs.getStore_type())
					if (ebs.getOwn_sys().intValue() == 1 && ebs.getStore_type().intValue() == 0) {
						count = ecStocks.getStocks().intValue();
					}
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('对不起，该商品已被抢购完！');location.href='"
			        + super.getCtxPath(request) + "/member/PshowOrderPanicBuying.do';}");
			return null;
		}
		logger.info("count--->" + count);

		if (count <= 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('对不起，该商品已被抢购完！');location.href='"
			        + super.getCtxPath(request) + "/member/PshowOrderPanicBuying.do';}");
			return null;
		}

		// 取上下架时间
		EcGoodsPrice ecg = new EcGoodsPrice();
		ecg.setGoods_id(Long.valueOf(goods_id));
		ecg.setOwn_sys(1);
		ecg.setPrice_type(0);
		List<EcGoodsPrice> ecList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceList(ecg);

		if (null != ecList && ecList.size() > 0) {
			ecg = ecList.get(0);
			if (ecg.getStart_time().getTime() > now_date) {
				super.renderJavaScript(response, "window.onload=function(){alert('对不起，还没到抢购时间！');location.href='"
				        + super.getCtxPath(request) + "/member/PshowOrderPanicBuying.do';}");
				return null;
			}
			if (ecg.getEnd_time().getTime() < now_date) {
				super.renderJavaScript(response, "window.onload=function(){alert('对不起，抢购已经结束！');location.href='"
				        + super.getCtxPath(request) + "/member/PshowOrderPanicBuying.do';}");
				return null;
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('网络异常，请稍后重试！');location.href='"
			        + super.getCtxPath(request) + "/member/PshowOrderPanicBuying.do';}");
			return null;
		}

		if (GenericValidator.isLong(goods_id)) {
			// 取当前请求数据的用户所在地
			String p_index = super.getPIndexByRequest(request);
			request.setAttribute("p_index", p_index);

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
			logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));

			// 查询商品详细信息
			KonkaBcompPd konkaBcompPd = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request), ecUser
			        .getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array, ecUser.getDept_id(),
			        ecUser.getC_id());

			if (null == konkaBcompPd) {
				String msg = super.getMessage(request, "ec.bcomp.pd.none");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			request.setAttribute("konkaBcompPd", konkaBcompPd);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Long today = calendar.getTimeInMillis();
			EcBcompPdUp ep = new EcBcompPdUp();
			ep.setBcomp_pd_id(new Long(goods_id));
			ep.setOwn_sys(ecUser.getUser_type());
			if (ecUser.getUser_type().intValue() == 2) {
				ep.setAdded_dept_id(ecUser.getDept_id());
			}
			List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);
			if (ecBcompPdUpList != null && ecBcompPdUpList.size() > 0) {
				calendar.setTime(ecBcompPdUpList.get(0).getDown_time());
			}
			Long down_time = calendar.getTimeInMillis();

			if (today.compareTo(down_time) == 1) {
				String msg = super.getMessage(request, "ec.bcomp.pd.down");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

		} else {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		return mapping.findForward("input");

	}

}