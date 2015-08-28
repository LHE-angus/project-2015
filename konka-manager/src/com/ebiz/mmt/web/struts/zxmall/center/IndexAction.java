package com.ebiz.mmt.web.struts.zxmall.center;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcQaInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserFavotrites;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;

/**
 * @author tudp
 * @version 2013-09-09
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

		logger.info("touch---->" + request.getAttribute("touch"));

		// 总积分
		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
		        .getEcUserScoreRecForUserTotalScore(ecUser.getId()));

		// 付款积分
		String payTotalScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(ecUser.getId());
		request.setAttribute("payTotalScore", new BigDecimal(payTotalScore).setScale(0, BigDecimal.ROUND_HALF_UP));
		DecimalFormat df = new DecimalFormat(".##");
		if (ecUser.getEcBaseCardLevel() != null && ecUser.getEcBaseCardLevel().getCard_level_name() != null) {
			if (ecUser.getEcBaseCardLevel().getCard_level_name().equals("银卡会员")) {
				request.setAttribute("wd", df.format(Double.valueOf(payTotalScore) / 300) + "%");
				request.setAttribute("need_jf", new BigDecimal(30000).subtract(new BigDecimal(payTotalScore).setScale(
				        0, BigDecimal.ROUND_HALF_UP)));
			} else if (ecUser.getEcBaseCardLevel().getCard_level_name().equals("金卡会员")) {
				request.setAttribute("need_jf", new BigDecimal(100000).subtract(new BigDecimal(payTotalScore).setScale(
				        0, BigDecimal.ROUND_HALF_UP)));
				request.setAttribute("wd", df.format(Double.valueOf(payTotalScore) / 1000) + "%");
			}
		}

		// 待处理订单
		PshowOrder pShowOrder = new PshowOrder();
		pShowOrder.setOrder_user_id(ecUser.getId());
		pShowOrder.getMap().put("state_in", new Integer[] { 0, 10, 20, 30, 40 });
		request.setAttribute("pShowOrderNum", super.getFacade().getPshowOrderService().getPshowOrderCount(pShowOrder));
		// 近期订单
		pShowOrder = new PshowOrder();
		pShowOrder.setOrder_user_id(ecUser.getId());
		pShowOrder.getMap().put("last3months", "last3months");
		pShowOrder.getMap().put("state_in", new Integer[] { 0, 10, 20, 30, 40 });
		pShowOrder.getRow().setFirst(0);
		pShowOrder.getRow().setCount(5);
		request.setAttribute("pShowOrderList", super.getFacade().getPshowOrderService()
		        .getPshowOrderIncludeDetailsPaginatedList(pShowOrder));

		// 关注商品
		EcUserFavotrites ecUserFavotries = new EcUserFavotrites();
		ecUserFavotries.setUser_id(ecUser.getId());
		ecUserFavotries.setOwn_sys(5);
		ecUserFavotries.getRow().setFirst(0);
		ecUserFavotries.getRow().setCount(5);
		request.setAttribute("ecUserFavotriesList", super.getFacade().getEcUserFavotritesService()
		        .getEcUserFavotritesPaginatedList(ecUserFavotries));

		// String p_index = super.getPIndexByRequest(request);
		// if (ecUser.getUser_type().intValue() == 2) {
		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.setDept_type(3);
		// konkaDept.setDept_id(ecUser.getDept_id());
		// konkaDept =
		// super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		// }

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		// Long city = -1L; // 市
		// BaseProvinceListFour bplf = new BaseProvinceListFour();
		// bplf.getRow().setCount(10);
		// bplf.setP_index(Long.valueOf(p_index));
		// List<BaseProvinceListFour> bplfList =
		// super.getFacade().getBaseProvinceListFourService()
		// .getBaseProvinceListFourParentList(bplf);
		// for (BaseProvinceListFour baseProvinceListFour : bplfList) {
		// if (baseProvinceListFour.getP_level() == 2) // level 为是市
		// city = baseProvinceListFour.getP_index();
		// }
		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.setDept_type(3);
		// konkaDept.getRow().setCount(2);
		// // konkaDept.getMap().put("m_areas_ids_like", city);
		// List<KonkaDept> konkaDeptList =
		// getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		// String[] dept_sn_array = new String[] { "0" };
		// if (1 == konkaDeptList.size()) {
		// dept_sn_array = new String[] { "0",
		// konkaDeptList.get(0).getDept_id().toString() };
		// }

		// 按排序值或者添加时间倒序，取 热销产品 16 个
		List<KonkaBcompPd> bcomp_pd_list_2 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(own_sys, Long
		        .valueOf(p_index), 2, 16, ecUser.getDept_id(), ecUser.getCust_id(), null, ecUser.getPlat_sys());
		request.setAttribute("bcomp_pd_list_2", bcomp_pd_list_2);

		// 按排序值或者添加时间倒序，取 限时抢购产品1 个
		List<KonkaBcompPd> bcomp_pd_list_5 = this.getKonkaBcompPdWithPindexAndTypeAndCountNewList(own_sys, Long
		        .valueOf(p_index), 5, 5, ecUser.getDept_id(), ecUser.getCust_id(), null, ecUser.getPlat_sys());
		request.setAttribute("bcomp_pd_list_5", bcomp_pd_list_5);

		return mapping.findForward("index");
	}

	public ActionForward getAjaxMyEpp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		// 待处理订单
		PshowOrder pShowOrder = new PshowOrder();
		pShowOrder.setOrder_user_id(ecUser.getId());
		pShowOrder.getMap().put("state_in", new Integer[] { 0, 10, 20, 30, 40 });
		Long orderNum = super.getFacade().getPshowOrderService().getPshowOrderCount(pShowOrder);
		// 总积分
		String totalScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserTotalScore(
		        ecUser.getId());
		// 咨询回复
		EcQaInfo ecQaInfo = new EcQaInfo();
		ecQaInfo.setQ_u_id(ecUser.getId().toString());
		Long qaCount = super.getFacade().getEcQaInfoService().getEcQaInfoCount(ecQaInfo);
		// 购物券
		EcVouchers ecv = new EcVouchers();
		ecv.setUser_id(ecUser.getId());
		ecv.setInfo_state(0);
		Long ecvCount = super.getFacade().getEcVouchersService().getEcVouchersCount(ecv);
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("orderNum", orderNum.toString());
		maps.put("totalScore", totalScore);
		maps.put("qaCount", qaCount.toString());
		maps.put("ecvCount", ecvCount.toString());
		maps.put("status", "1");
		super.renderJson(response, JSON.toJSONString(maps));
		return null;
	}
}
