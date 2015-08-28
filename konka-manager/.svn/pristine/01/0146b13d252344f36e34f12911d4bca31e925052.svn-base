package com.ebiz.mmt.web.struts.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.r3.ITR2;
import com.ebiz.mmt.r3.KCXX;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.KHYS;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.KUKLA;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZA006;
import com.ebiz.mmt.r3.ZJ98;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.r3.ZLES23;
import com.ebiz.mmt.r3.ZdmtrxCriteria;
import com.ebiz.mmt.r3.Zles29a;
import com.ebiz.mmt.r3.Zles29aCriteria;
import com.ebiz.mmt.r3.helper.DAOFactory;
import com.ebiz.mmt.web.struts.BaseAction;

public class R3InterfaceTestAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	/***
	 * r/3客户信息列表(接口支持查指定的多个客户编码,但此处为了演示做成单个的)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dnBean = (DynaBean) form;

		//System.out.println("取r/3客户信息列表");

		ReturnInfo<KNA1> info = new ReturnInfo<KNA1>();

		String indate = (String) dnBean.get("in_date");
		String bukrs = (String) dnBean.get("bukrs");
		String kunnr = (String) dnBean.get("kunnr");
		List<KNA1> list = null;
		// bukrs 不能空, kunnr 和in_date 两者不能同时为空
		dnBean.set("indate", indate);
		dnBean.set("bukrs", bukrs);
		dnBean.set("kunnr", kunnr);

		if (bukrs == null || "".equals(bukrs)) {
			return mapping.findForward("list1");
		}

		// bukrs +　kunnr　两者同时为空时
		if ((bukrs == null || "".equals(bukrs)) && (kunnr == null || "".equals(kunnr))) {
			return mapping.findForward("list1");
		}

		if (kunnr == null || kunnr.length() <= 0) {
			// get data
			info = super.getFacade().getR3WebInterfaceService().getCustomerList(indate, bukrs, null);
			list = info.getDataResult();
		} else {
			// get data
			info = super.getFacade().getR3WebInterfaceService().getCustomerList(indate, bukrs, new String[] { kunnr });
			list = info.getDataResult();
		}

		request.setAttribute("entityList", list);
		return mapping.findForward("list1");

	}

	/**
	 * 取r/3客户合作伙伴
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dnBean = (DynaBean) form;
		//System.out.println("取r/3客户合作伙伴");
		// 前三个条件不能为空
		String vkorg = (String) dnBean.get("vkorg");
		String vtweg = (String) dnBean.get("vtweg");
		String spart = (String) dnBean.get("spart");
		ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
		// kunnr 可以为空
		String kunnr = (String) dnBean.get("kunnr");

		if (vkorg == null || "".equals(vkorg)) {
			return mapping.findForward("list2");
		}
		if (vtweg == null || "".equals(vtweg)) {
			return mapping.findForward("list2");
		}
		if (spart == null || "".equals(spart)) {
			return mapping.findForward("list2");
		}
		if (kunnr == null)
			kunnr = "";

		info = super.getFacade().getR3WebInterfaceService().getKnvpsList(vkorg, vtweg, spart, kunnr);
		// get data
		List<KNVP> list = info.getDataResult();
		request.setAttribute("entityList", list);
		return mapping.findForward("list2");
	}

	/**
	 * 客户类别信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get data
		List<KUKLA> list = new ArrayList<KUKLA>();
		ReturnInfo<KUKLA> info = new ReturnInfo<KUKLA>();
		info = super.getFacade().getR3WebInterfaceService().getkuklaList();
		list = info.getDataResult();
		request.setAttribute("entityList", list);
		return mapping.findForward("list3");
	}

	/**
	 * 产品基础信息接口查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list4(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dnBean = (DynaBean) form;
		String erdat = (String) dnBean.get("erdat");

		if (erdat == null || erdat == "") {
			return mapping.findForward("list4");
		}
		// get data
		List<MARA> list = new ArrayList<MARA>();

		ReturnInfo<MARA> info = new ReturnInfo<MARA>();
		info = super.getFacade().getR3WebInterfaceService().getMaraList(erdat);
		list = info.getDataResult();

		request.setAttribute("entityList", list);
		return mapping.findForward("list4");

	}

	/**
	 * 返回客户账期信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list5(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list5");

		DynaBean dnBean = (DynaBean) form;
		String v_kkber = (String) dnBean.get("v_kkber");
		String v_spart = (String) dnBean.get("v_spart");
		String v_vkorg = (String) dnBean.get("v_vkorg");
		String kunnr = (String) dnBean.get("kunnr");

		List<KHXD> list = new ArrayList<KHXD>();

		if (v_kkber == null || "".equals(v_kkber)) {
			return mapping.findForward("list5");
		}
		if (v_spart == null || "".equals(v_spart)) {
			return mapping.findForward("list5");
		}
		// v_vkorg +　kunnr　两者同时为空时
		if ((v_vkorg == null || "".equals(v_vkorg)) && (kunnr == null || "".equals(kunnr))) {
			return mapping.findForward("list5");
		}

		ReturnInfo<KHXD> info = new ReturnInfo<KHXD>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getKhxd(v_kkber, v_vkorg, v_spart, new String[] { kunnr });
		list = info.getDataResult();

		request.setAttribute("entityList", list);
		return mapping.findForward("list5");
	}

	/**
	 * 返回客户应收回款信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list6(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list6");

		DynaBean dnBean = (DynaBean) form;
		String v_gjahr = (String) dnBean.get("v_gjahr");
		String v_monat = (String) dnBean.get("v_monat");
		String v_spart = (String) dnBean.get("v_spart");

		String v_vkorg = (String) dnBean.get("v_vkorg");
		String kunnr = (String) dnBean.get("kunnr");

		List<KHYS> list = new ArrayList<KHYS>();

		if (v_gjahr == null || "".equals(v_gjahr)) {
			return mapping.findForward("list6");
		}
		if (v_monat == null || "".equals(v_monat)) {
			return mapping.findForward("list6");
		}
		if (v_spart == null || "".equals(v_spart)) {
			return mapping.findForward("list6");
		}

		// v_vkorg +　kunnr　两者同时为空时
		if ((v_vkorg == null || "".equals(v_vkorg)) && (kunnr == null || "".equals(kunnr))) {
			return mapping.findForward("list6");
		}
		ReturnInfo<KHYS> info = new ReturnInfo<KHYS>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService()
				.getKhys(v_gjahr, v_monat, v_spart, v_vkorg, new String[] { kunnr });
		list = info.getDataResult();
		request.setAttribute("entityList", list);
		return mapping.findForward("list6");

	}

	/**
	 * 返回库存信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list7(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list7");

		DynaBean dnBean = (DynaBean) form;
		// 工厂
		String v_werks = (String) dnBean.get("v_werks");
		// 仓库地点
		String v_lgort = (String) dnBean.get("v_lgort");
		// 仓位
		String v_lgpla = (String) dnBean.get("v_lgpla");
		// 物料
		String v_matnr = (String) dnBean.get("v_matnr");

		List<KCXX> list = new ArrayList<KCXX>();

		if (v_werks == null || "".equals(v_werks)) {
			return mapping.findForward("list7");
		}
		if (v_lgort == null || "".equals(v_lgort)) {
			return mapping.findForward("list7");
		}
		if (v_lgpla == null || "".equals(v_lgpla)) {
			return mapping.findForward("list7");
		}
		// 物料号可以为空
		if (v_matnr == null || "".equals(v_matnr)) {
			v_matnr = "";
		}

		list = DAOFactory.getInstance().getKcxx(v_werks, v_lgort, v_lgpla, v_matnr);

		ReturnInfo<KCXX> info = new ReturnInfo<KCXX>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getKcxx(v_werks, v_lgort, v_lgpla, v_matnr);
		list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list7");

	}

	public ActionForward list8(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list8");

		DynaBean dnBean = (DynaBean) form;
		// 销售组织
		String v_vkorg = (String) dnBean.get("v_vkorg");
		// 分销渠道
		String v_vtweg = (String) dnBean.get("v_vtweg");
		// 产品组
		String v_spart = (String) dnBean.get("v_spart");
		// 开始日期
		String v_audat_begin = (String) dnBean.get("v_audat_begin");
		// 结束日期
		String v_audat_end = (String) dnBean.get("v_audat_end");
		// 客户编码
		String v_kunnr = (String) dnBean.get("v_kunnr");

		List<SOXX> list = new ArrayList<SOXX>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 如果结束日期为空,自动默认为今天
		if (v_audat_end == null || "".equals(v_audat_end)) {
			Calendar calendar = Calendar.getInstance();
			v_audat_end = sdf.format(calendar.getTime());
		}
		if (v_audat_end != null && v_audat_end.length() >= 0) {
			v_audat_end = StringUtils.replace(v_audat_end, "-", "");
		}

		if (v_audat_begin != null && v_audat_begin.length() >= 0) {
			v_audat_begin = StringUtils.replace(v_audat_begin, "-", "");
		}

		if (v_vkorg == null || "".equals(v_vkorg)) {
			return mapping.findForward("list8");
		}
		if (v_vtweg == null || "".equals(v_vtweg)) {
			return mapping.findForward("list8");
		}
		if (v_spart == null || "".equals(v_spart)) {
			return mapping.findForward("list8");
		}

		ReturnInfo<SOXX> info = new ReturnInfo<SOXX>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService()
				.getSoxx(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
		list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list8");

	}

	public ActionForward list9(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list9");
		DynaBean dnBean = (DynaBean) form;
		String v_matnr = (String) dnBean.get("v_matnr");
		List<ZA006> list = new ArrayList<ZA006>();
		if (v_matnr == null || "".equals(v_matnr)) {
			request.setAttribute("entityList", list);
			return mapping.findForward("list9");
		}

		ReturnInfo<ZA006> info = new ReturnInfo<ZA006>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getZa006(v_matnr);
		list = info.getDataResult();

		list = DAOFactory.getInstance().getZa006(v_matnr);
		request.setAttribute("entityList", list);
		return mapping.findForward("list9");
	}

	/** 分公司库存查询 */
	public ActionForward list10(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list10");

		DynaBean dnBean = (DynaBean) form;
		// 工厂(需要和分公司绑定) not null
		String zwerks = (String) dnBean.get("zwerks");
		// 仓位 仓库地点 not null
		String zlgort = (String) dnBean.get("zlgort");
		// 物料
		String zmatnr = (String) dnBean.get("zmatnr");

		List<StockCheckResult> list = new ArrayList<StockCheckResult>();

		if (zwerks == null || "".equals(zwerks)) {
			return mapping.findForward("list10");
		}
		if (zlgort == null || "".equals(zlgort)) {
			return mapping.findForward("list10");
		}

		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks, zlgort, zmatnr);
		list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list10");
	}

	/** 分公司库存查询(精确到机型和仓位,可用于订单提交库存用) */
	public ActionForward list11(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list11");

		DynaBean dnBean = (DynaBean) form;
		// 工厂(需要和分公司绑定) not null
		String zwerks = (String) dnBean.get("zwerks");
		// 库位 not null
		String zlgort = (String) dnBean.get("zlgort");

		String zlgpla = (String) dnBean.get("zlgpla");
		// 物料
		String zmatnr = (String) dnBean.get("zmatnr");

		List<ZLEBIN> list = new ArrayList<ZLEBIN>();

		if (zwerks == null || "".equals(zwerks)) {
			return mapping.findForward("list11");
		}
		if (zlgort == null || "".equals(zlgort)) {
			return mapping.findForward("list11");
		}

		ReturnInfo<ZLEBIN> info = new ReturnInfo<ZLEBIN>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getZles20(zwerks, zlgort, zlgpla, zmatnr);
		list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list11");
	}

	public ActionForward list12(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list12");

		DynaBean dnBean = (DynaBean) form;
		// 销售组织
		String v_vkorg = (String) dnBean.get("v_vkorg");
		// 分销渠道
		String v_vtweg = (String) dnBean.get("v_vtweg");
		// 产品组
		String v_spart = (String) dnBean.get("v_spart");
		// 开始日期
		String v_audat_begin = (String) dnBean.get("v_audat_begin");
		// 结束日期
		String v_audat_end = (String) dnBean.get("v_audat_end");
		// 客户编码
		String v_kunnr = (String) dnBean.get("v_kunnr");

		List<SOXX> list = new ArrayList<SOXX>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 如果结束日期为空,自动默认为今天
		if (v_audat_end == null || "".equals(v_audat_end)) {
			Calendar calendar = Calendar.getInstance();
			v_audat_end = sdf.format(calendar.getTime());
		}
		if (v_audat_end != null && v_audat_end.length() >= 0) {
			v_audat_end = StringUtils.replace(v_audat_end, "-", "");
		}

		if (v_audat_begin != null && v_audat_begin.length() >= 0) {
			v_audat_begin = StringUtils.replace(v_audat_begin, "-", "");
		}

		if (v_vkorg == null || "".equals(v_vkorg)) {
			return mapping.findForward("list12");
		}
		if (v_vtweg == null || "".equals(v_vtweg)) {
			return mapping.findForward("list12");
		}
		if (v_spart == null || "".equals(v_spart)) {
			return mapping.findForward("list12");
		}

		ReturnInfo<SOXX> info = new ReturnInfo<SOXX>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService()
				.getSoxxMX(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
		list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list12");
	}

	public ActionForward list13(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println("list13");
		DynaBean dnBean = (DynaBean) form;
		// 型号
		String v_matnr_begin = (String) dnBean.get("v_matnr_begin");
		// 型号
		String v_matnr_end = (String) dnBean.get("v_matnr_end");
		// 分销渠道
		String v_vtweg = (String) dnBean.get("v_vtweg");
		// 产品组
		String v_spart = (String) dnBean.get("v_spart");
		// 开始日期
		String v_bstdk_begin = (String) dnBean.get("v_bstdk_begin");
		// 结束日期
		String v_bstdk_end = (String) dnBean.get("v_bstdk_end");
		// 存销比开始
		String v_cxb_begin = (String) dnBean.get("v_cxb_begin");
		// 存销比结束
		String v_cxb_end = (String) dnBean.get("v_cxb_end");

		List<ZJ98> list = new ArrayList<ZJ98>();

		if (v_bstdk_end != null && v_bstdk_end.length() >= 0) {
			v_bstdk_end = StringUtils.replace(v_bstdk_end, "-", "");
		}
		if (v_bstdk_begin == null || "".equals(v_bstdk_begin)) {
			return mapping.findForward("list13");
		}
		if (v_bstdk_begin != null && v_bstdk_begin.length() >= 0) {
			v_bstdk_begin = StringUtils.replace(v_bstdk_begin, "-", "");
		}
		if (v_bstdk_begin == null || ("").equals(v_bstdk_begin)) {
			return mapping.findForward("list13");
		}
		if (v_vtweg == null || "".equals(v_vtweg)) {
			return mapping.findForward("list13");
		}
		if (v_spart == null || "".equals(v_spart)) {
			return mapping.findForward("list13");
		}

		list = DAOFactory.getInstance().getZlesZJ98(v_matnr_begin, v_matnr_end, v_vtweg, v_spart, v_bstdk_begin,
				v_bstdk_end, v_cxb_begin, v_cxb_end);

		// ReturnInfo<> info = new ReturnInfo<ZJ98>();
		// // v_vkorg 和　kunnr 两者可以有一个为空
		// // 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		// info =
		// super.getFacade().getR3WebInterfaceService().getZlesZJ98(v_matnr_begin,
		// v_matnr_end, v_vtweg, v_spart, v_bstdk_begin, v_bstdk_end,
		// v_cxb_begin, v_cxb_end);
		// list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list13");
	}

	public ActionForward list14(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list14");

		DynaBean dnBean = (DynaBean) form;

		// 销售片区:
		String v_bzirk_low = (String) dnBean.get("v_bzirk_low");
		String v_bzirk_high = (String) dnBean.get("v_bzirk_high");

		// 分公司名称:
		String v_class_low = (String) dnBean.get("v_class_low");
		String v_class_high = (String) dnBean.get("v_class_high");

		// 机型(*):
		String v_matnr_low = (String) dnBean.get("v_matnr_low");
		String v_matnr_high = (String) dnBean.get("v_matnr_high");

		// 物料组 :
		String v_matkl_low = (String) dnBean.get("v_matkl_low");
		String v_matkl_high = (String) dnBean.get("v_matkl_high");

		// 转储单号 (*):
		String v_ebeln_low = (String) dnBean.get("v_ebeln_low");
		String v_ebeln_high = (String) dnBean.get("v_ebeln_high");

		// 转储单日期(*):
		String v_eindt_low = (String) dnBean.get("v_eindt_low");
		String v_eindt_high = (String) dnBean.get("v_eindt_high");

		// 销售日期(*)
		String v_bstdk_low = (String) dnBean.get("v_bstdk_low");
		String v_bstdk_high = (String) dnBean.get("v_bstdk_high");

		// 分销渠道 (*):
		String v_vtweg_low = (String) dnBean.get("v_vtweg_low");
		String v_vtweg_high = (String) dnBean.get("v_vtweg_high");

		// 销售组织(*) :
		String v_vkorg_low = (String) dnBean.get("v_vkorg_low");

		if (v_matnr_low == null || "".equals(v_matnr_low)) {
			return mapping.findForward("list14");
		}
		if (v_ebeln_low == null || "".equals(v_ebeln_low)) {
			return mapping.findForward("list14");
		}
		if (v_eindt_low == null || "".equals(v_eindt_low)) {
			return mapping.findForward("list14");
		}
		if (v_bstdk_low == null || "".equals(v_bstdk_low)) {
			return mapping.findForward("list14");
		}
		if (v_vtweg_low == null || "".equals(v_vtweg_low)) {
			return mapping.findForward("list14");
		}
		if (v_vkorg_low == null || "".equals(v_vkorg_low)) {
			return mapping.findForward("list14");
		}

		Hashtable<String, String> v_BSTDK = new Hashtable<String, String>();
		if (v_bstdk_low != null && !"".equals(v_bstdk_low)) {
			v_BSTDK.put("LOW", v_bstdk_low);
		}
		if (v_bstdk_high != null && !"".equals(v_bstdk_high)) {
			v_BSTDK.put("HIGH", v_bstdk_high);
		}

		Hashtable<String, String> v_CLASS = new Hashtable<String, String>();
		if (v_class_low != null && !"".equals(v_class_low)) {
			v_CLASS.put("LOW", v_class_low);
		}
		if (v_class_high != null && !"".equals(v_class_high)) {
			v_CLASS.put("HIGH", v_class_high);
		}

		Hashtable<String, String> v_MATNR = new Hashtable<String, String>();
		if (v_matnr_low != null && !"".equals(v_matnr_low)) {
			v_MATNR.put("LOW", v_matnr_low);
		}
		if (v_matnr_high != null && !"".equals(v_matnr_high)) {
			v_MATNR.put("HIGH", v_matnr_high);
		}

		Hashtable<String, String> v_MATKL = new Hashtable<String, String>();
		if (v_matkl_low != null && !"".equals(v_matkl_low)) {
			v_MATKL.put("LOW", v_matkl_low);
		}
		if (v_matkl_high != null && !"".equals(v_matkl_high)) {
			v_MATKL.put("HIGH", v_matkl_high);
		}

		Hashtable<String, String> v_EBELN = new Hashtable<String, String>();
		if (v_ebeln_low != null && !"".equals(v_ebeln_low)) {
			v_EBELN.put("LOW", v_ebeln_low);
		}
		if (v_ebeln_high != null && !"".equals(v_ebeln_high)) {
			v_EBELN.put("HIGH", v_ebeln_high);
		}

		Hashtable<String, String> v_EINDT = new Hashtable<String, String>();
		if (v_eindt_low != null && !"".equals(v_eindt_low)) {
			v_EINDT.put("LOW", v_eindt_low);
		}
		if (v_eindt_high != null && !"".equals(v_eindt_high)) {
			v_EINDT.put("HIGH", v_eindt_high);
		}

		Hashtable<String, String> v_BZIRK = new Hashtable<String, String>();
		if (v_bzirk_low != null && !"".equals(v_bzirk_low)) {
			v_BZIRK.put("LOW", v_bzirk_low);
		}
		if (v_bzirk_high != null && !"".equals(v_bzirk_high)) {
			v_BZIRK.put("HIGH", v_bzirk_high);
		}

		Hashtable<String, String> v_VTWEG = new Hashtable<String, String>();
		if (v_vtweg_low != null && !"".equals(v_vtweg_low)) {
			v_VTWEG.put("LOW", v_vtweg_low);
		}
		if (v_vtweg_high != null && !"".equals(v_vtweg_high)) {
			v_VTWEG.put("HIGH", v_vtweg_high);
		}

		Zles29aCriteria zc = new Zles29aCriteria();
		zc.setV_BSTDK(v_BSTDK);
		zc.setV_BZIRK(v_BZIRK);
		zc.setV_CLASS(v_CLASS);
		zc.setV_EBELN(v_EBELN);
		zc.setV_EINDT(v_EINDT);
		zc.setV_MATKL(v_MATKL);
		zc.setV_MATNR(v_MATNR);
		zc.setV_VTWEG(v_VTWEG);
		zc.setV_VKORG(v_vkorg_low);

		List<Zles29a> list = new ArrayList<Zles29a>();
		// list = DAOFactory.getInstance().getZles29a(zc);

		ReturnInfo<Zles29a> info = new ReturnInfo<Zles29a>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getZles29a(zc);
		list = info.getDataResult();

		request.setAttribute("entityList", list);

		return mapping.findForward("list14");

	}

	public ActionForward list15(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//System.out.println("list15");

		DynaBean dnBean = (DynaBean) form;

		// 年度(*):V_LFGJA
		String v_lfgja_low = (String) dnBean.get("v_lfgja_low");
		String v_lfgja_high = (String) dnBean.get("v_lfgja_high");

		// 月度(*):
		String v_vlfmon_low = (String) dnBean.get("v_vlfmon_low");
		String v_vlfmon_high = (String) dnBean.get("v_vlfmon_high");

		// 销售组织(*):
		String v_vkorg_low = (String) dnBean.get("v_vkorg_low");
		String v_vkorg_high = (String) dnBean.get("v_vkorg_high");

		// 客户:
		String v_kunnr_low = (String) dnBean.get("v_kunnr_low");
		String v_kunnr_high = (String) dnBean.get("v_kunnr_high");

		// 机型:
		String v_matnr_low = (String) dnBean.get("v_matnr_low");
		String v_matnr_high = (String) dnBean.get("v_matnr_high");

		if (v_lfgja_low == null || "".equals(v_lfgja_low)) {
			return mapping.findForward("list15");
		}
		if (v_vlfmon_low == null || "".equals(v_vlfmon_low)) {
			return mapping.findForward("list15");
		}
		if (v_vkorg_low == null || "".equals(v_vkorg_low)) {
			return mapping.findForward("list15");
		}

		Hashtable<String, String> V_LFGJA = new Hashtable<String, String>();
		if (v_lfgja_low != null && !"".equals(v_lfgja_low)) {
			V_LFGJA.put("LOW", v_lfgja_low);
		}
		if (v_lfgja_high != null && !"".equals(v_lfgja_high)) {
			V_LFGJA.put("HIGH", v_lfgja_high);
		}
		Hashtable<String, String> V_LFMON = new Hashtable<String, String>();
		if (v_vlfmon_low != null && !"".equals(v_vlfmon_low)) {
			V_LFMON.put("LOW", v_vlfmon_low);
		}
		if (v_vlfmon_high != null && !"".equals(v_vlfmon_high)) {
			V_LFMON.put("HIGH", v_vlfmon_high);
		}
		Hashtable<String, String> V_VKORG = new Hashtable<String, String>();
		if (v_vkorg_low != null && !"".equals(v_vkorg_low)) {
			V_VKORG.put("LOW", v_vkorg_low);
		}
		if (v_vkorg_high != null && !"".equals(v_vkorg_high)) {
			V_VKORG.put("HIGH", v_vkorg_high);
		}
		Hashtable<String, String> V_KUNNR = new Hashtable<String, String>();
		if (v_kunnr_low != null && !"".equals(v_kunnr_low)) {
			V_KUNNR.put("LOW", v_kunnr_low);
		}
		if (v_kunnr_high != null && !"".equals(v_kunnr_high)) {
			V_KUNNR.put("HIGH", v_kunnr_high);
		}
		Hashtable<String, String> V_MATNR = new Hashtable<String, String>();
		if (v_matnr_low != null && !"".equals(v_matnr_low)) {
			V_MATNR.put("LOW", v_matnr_low);
		}
		if (v_matnr_high != null && !"".equals(v_matnr_high)) {
			V_MATNR.put("HIGH", v_matnr_high);
		}

		ZdmtrxCriteria zc = new ZdmtrxCriteria();
		zc.setV_LFGJA(V_LFGJA);
		zc.setV_LFMON(V_LFMON);
		zc.setV_VKORG(V_VKORG);
		zc.setV_KUNNR(V_KUNNR);
		zc.setV_MATNR(V_MATNR);

		List<ITR2> list = new ArrayList<ITR2>();
		list = DAOFactory.getInstance().getITR2(zc);

		// ReturnInfo<ITR2> info = new ReturnInfo<ITR2>();
		// // v_vkorg 和　kunnr 两者可以有一个为空
		// // 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		// info = super.getFacade().getR3WebInterfaceService().getITR2(zc);
		// list = info.getDataResult();

		request.setAttribute("entityList", list);
		return mapping.findForward("list15");
	}

	/** 集采数据接口 */
	public ActionForward list16(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println("list16");
		DynaBean dnBean = (DynaBean) form;
		String erdat_b = (String) dnBean.get("erdat_b");
		String erdat_e = (String) dnBean.get("erdat_e");
		if (erdat_b == null || "".equals(erdat_b)) {
			return mapping.findForward("list16");
		}
		if (erdat_e == null || "".equals(erdat_e)) {
			return mapping.findForward("list16");
		}
		List<ZLES23> list = new ArrayList<ZLES23>();

		ReturnInfo<ZLES23> info = new ReturnInfo<ZLES23>();
		// v_vkorg 和　kunnr 两者可以有一个为空
		// 接口其实支持输入多个客户编码,但这里为了演示,只输入一个
		info = super.getFacade().getR3WebInterfaceService().getZles23(erdat_b, erdat_e, null);
		list = info.getDataResult();

		request.setAttribute("entityList", list);
		return mapping.findForward("list16");
	}
}
