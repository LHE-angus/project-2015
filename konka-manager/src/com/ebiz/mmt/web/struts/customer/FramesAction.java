package com.ebiz.mmt.web.struts.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.KonkaPeArticleType;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaVipPdManage;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.org.apache.commons.lang3.StringUtils;

public class FramesAction extends BaseClientJxcAction {

	final String konka_mods = "105020000,105020200,105020400,105020450,110041000,115060000,199015000,199015500,199015002,199015502";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == userInfo.getCust_id()) {
			super.renderJavaScript(response,
					"window.onload=function(){alert('" + this.getMessage(request, "popedom.check.invalid") + "');}");
			return null;
		}

		boolean role_id_eq_400 = false;
		PeRoleUser ru = new PeRoleUser();
		ru.setRole_id(400L);
		ru.setUser_id(userInfo.getId());
		if (0L < super.getFacade().getPeRoleUserService().getPeRoleUserCount(ru)) {
			role_id_eq_400 = true;
		}
		request.setAttribute("role_id_eq_400", role_id_eq_400);

		// 验证是否是专卖店返利模式
		Boolean is_zmd_100200 = false;
		if (role_id_eq_400) {
			KonkaXxZmdUsers kUsers = new KonkaXxZmdUsers();
			kUsers.setUser_id(userInfo.getId());
			List<KonkaXxZmdUsers> kList = super.getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(kUsers);
			if (kList.size() > 0) {
				KonkaXxZmd kZmd = new KonkaXxZmd();
				kZmd.setZmd_id(kList.get(0).getZmd_id());
				kZmd.setBusi_mod(100200L);
				kZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(kZmd);
				if (null != kZmd) {
					is_zmd_100200 = true;
				}
			}
		}

		// is_admin 0：表示专卖店和普通客户公有模块，1：表示普通特有客户模块，2：专卖店返利模式特有模块
		SysModule sysModule = new SysModule();
		sysModule.getMap().put("is_cust", "true");
		sysModule.setIs_del(0);
		if (!role_id_eq_400) {
			sysModule.getMap().put("is_admin_show", "0,1");// 普通客户
			// sysModule.setIs_admin(0);
		} else if (role_id_eq_400 && is_zmd_100200) {// 专卖店返利模式
			sysModule.getMap().put("is_admin_show", "0,2");
			request.setAttribute("is_zmd_100200", true);
		} else if (role_id_eq_400 && !is_zmd_100200) {// 专卖店一步价模式
			sysModule.getMap().put("is_admin_show", "0,1");
			request.setAttribute("not_zmd_100200", true);
		}

		// 验证是否是网点
		Boolean kh_flag = false;
		KonkaR3Shop krs = new KonkaR3Shop();
		krs.setId(userInfo.getCust_id());
		krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
		if (krs != null && krs.getIs_konka() == 0) {
			kh_flag = true;
		}

		List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);

		List<SysModule> sysModuleList_1 = new ArrayList<SysModule>();
		List<SysModule> sysModuleList_2 = new ArrayList<SysModule>();
		List<SysModule> sysModuleList_3 = new ArrayList<SysModule>();
		List<SysModule> sysModuleList_4 = new ArrayList<SysModule>();

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(userInfo.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		request.setAttribute("shop", shop);

		Iterator<SysModule> itr = sysModuleList.iterator();
		while (itr.hasNext()) {
			SysModule t = itr.next();

			if (null != t.getTree_level()) {
				if (1 == t.getTree_level()) {
					sysModuleList_1.add(t);
				} else if (2 == t.getTree_level()) {

					if (kh_flag && t.getMod_id() != 105020000) {
						sysModuleList_2.add(t);
					}
				} else if (3 == t.getTree_level()) {
					if (kh_flag
							&& !(t.getMod_id() == 105020450 || t.getMod_id() == 105020200 || t.getMod_id() == 105020400)) {
						sysModuleList_3.add(t);
					}
				} else if (4 == t.getTree_level()) {
					sysModuleList_4.add(t);
				}
			}

			if (null != shop && StringUtils.startsWith(shop.getR3_code(), "WLDW")
					&& ArrayUtils.contains(konka_mods.split(","), t.getMod_id().toString())) {
				itr.remove();
			}
			// if
			// (ArrayUtils.contains("105040400,105040600,105040800".split(","),
			// t.getMod_id().toString())) {
			// t.setPar_id(105000000);
			// }
		}

		if (null != shop && shop.getBranch_area_name_2() != null) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);

			if (null != dept) {
				request.setAttribute("fgsName", dept.getDept_name());
			}
		}

		session.setAttribute(Constants.CUSTOMER_USER_INFO, userInfo);

		request.setAttribute("sysModuleList", sysModuleList);
		request.setAttribute("sysModuleList_1", sysModuleList_1);
		request.setAttribute("sysModuleList_2", sysModuleList_2);
		request.setAttribute("sysModuleList_3", sysModuleList_3);
		request.setAttribute("sysModuleList_4", sysModuleList_4);

		request.setAttribute("url", url);
		String defaulturl = (String) session.getAttribute("defaulturl");
		if (StringUtils.isNotBlank(defaulturl)) {

			defaulturl = defaulturl.replace("bbbb", "&");
			defaulturl = defaulturl.replace("aaaa", "?");
			defaulturl = defaulturl.replace("cccc", "=");
			session.removeAttribute("defaulturl");
		}
		;
		request.setAttribute("defaulturl", defaulturl);

		// 资源初始化
		initResource(request);

		request.setAttribute("pdAndJzRemind", this.pdOrJzRemind(request));

		return mapping.findForward("indexFrame");
	}

	public ActionForward main2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;

		}
		KonkaR3Shop kksp = new KonkaR3Shop();
		kksp.setId(user.getCust_id());
		kksp = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kksp);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_sn(kksp.getBranch_area_name_2());
		konkaDept.setDept_type(3);
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null != konkaDeptList && konkaDeptList.size() > 0) {
			konkaDept = konkaDeptList.get(0);
		}
		List<KonkaVipPdManage> JPentityList = new ArrayList<KonkaVipPdManage>();
		KonkaVipPdManage konkavippdmanage = new KonkaVipPdManage();
		konkavippdmanage.setIs_hidden("0");
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkavippdmanage.setFgs_id(konkaDept.getDept_id());
		}

		// 精品推荐
		konkavippdmanage.setPd_type(1L);
		konkavippdmanage.getRow().setFirst(0);
		konkavippdmanage.getRow().setCount(4);
		konkavippdmanage.setPd_type(2L);
		JPentityList = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageList(konkavippdmanage);

		request.setAttribute("JPentityList", JPentityList);

		KonkaPeArticleInfo entity = new KonkaPeArticleInfo();

		// entity.setArticle_mod_id(Long.valueOf("960110"));
		entity.getMap().put("article_mod_id_in", new Long[] { 960110L, 960112L });
		entity.setIs_del(0L);
		entity.setStates(1L);

		// 1060L,601130L,601131L,601131L
		// 1030L, 1060L, 1070L, 1080L

		entity.getMap().put("article_type_id_in", new Long[] { 1030L, 1060L, 1070L, -1L });
		entity.getMap().put("cust_id", user.getCust_id().toString());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(12);
		List<KonkaPeArticleInfo> entityList = getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoPaginatedList(entity);
		for (KonkaPeArticleInfo konkaPeArticleInfo : entityList) {
			if (konkaPeArticleInfo.getArticle_type_id() != null) {
				KonkaPeArticleType kt = new KonkaPeArticleType();
				kt.setId(konkaPeArticleInfo.getArticle_type_id());
				kt = super.getFacade().getKonkaPeArticleTypeService().getKonkaPeArticleType(kt);
				if (kt != null) {
					konkaPeArticleInfo.getMap().put("type_name", kt.getType_name());
				} else {
					konkaPeArticleInfo.getMap().put("type_name", "通知");
				}

			}
		}

		request.setAttribute("GroupPeInfoList", entityList);

		// 获取IHS经理

		PeProdUser pp = new PeProdUser();
		pp.getMap().put("role_id", "402");// IHS经理
		pp.getMap().put("dept_id", konkaDept.getDept_id().toString());
		pp.getMap().put("is_show", "1");
		List<PeProdUser> userlist = super.getFacade().getPeProdUserService().getPeProdUserByDeptIdAndRoleIdResult(pp);
		if (userlist != null && userlist.size() > 0) {
			pp = userlist.get(0);
			dynaBean.set("ihs_jl_name", pp.getReal_name());
			dynaBean.set("ihs_jl_tel", pp.getLink_tel());
			dynaBean.set("ihs_jl_phone", pp.getLink_phone());
			dynaBean.set("ihs_jl_email", pp.getEmail());

			Attachment att = new Attachment();
			att.setLink_id(pp.getId());
			att.setLink_tab("KONKA_PE_PROD_USER");
			att.setFile_desc("zhaopian");
			List<Attachment> atts = super.getFacade().getAttachmentService().getAttachmentList(att);
			if (atts != null && atts.size() > 0) {
				att = atts.get(0);
				dynaBean.set("ihs_jl_pic", att.getSave_path());
			}

		}

		PeProdUser pp1 = new PeProdUser();
		pp1.getMap().put("role_id", "403");// IHS经理
		pp1.getMap().put("dept_id", konkaDept.getDept_id().toString());
		pp1.getMap().put("is_show", "1");
		List<PeProdUser> userlist1 = super.getFacade().getPeProdUserService().getPeProdUserByDeptIdAndRoleIdResult(pp1);
		if (userlist1 != null && userlist1.size() > 0) {
			pp1 = userlist1.get(0);
			dynaBean.set("ihs_zy_name", pp1.getReal_name());
			dynaBean.set("ihs_zy_tel", pp1.getLink_tel());
			dynaBean.set("ihs_zy_phone", pp1.getLink_phone());
			dynaBean.set("ihs_zy_email", pp1.getEmail());
			Attachment att = new Attachment();
			att.setLink_id(pp1.getId());
			att.setLink_tab("KONKA_PE_PROD_USER");
			att.setFile_desc("zhaopian");
			List<Attachment> atts = super.getFacade().getAttachmentService().getAttachmentList(att);
			if (atts != null && atts.size() > 0) {
				att = atts.get(0);
				dynaBean.set("ihs_zy_pic", att.getSave_path());
			}
		}

		return new ActionForward("/Frames/index.jsp");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		JSubSellRec entity = new JSubSellRec();
		entity.setStatus(0);
		entity.setBuy_partner_id(user.getCust_id());

		Long recordCount = super.getFacade().getJSubSellRecService().getJSubSellRecCount(entity);

		request.setAttribute("count", recordCount);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user.getCust_id());

		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		if (null == konkaR3Shop || null == konkaR3Shop.getB_lng() || null == konkaR3Shop.getB_lat()) {
			request.setAttribute("gotoUserPos", 1);
		}

		return mapping.findForward("mainFrame");
	}

	public void initResource(HttpServletRequest request) {
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		// 初始化 仓库 信息
		initDefaultStore(request);

		// 初始化 商品单位/商品类型 信息
		JBaseType t = new JBaseType();
		t.setC_id(user.getCust_id());
		Long counts = super.getFacade().getJBaseTypeService().getJBaseTypeCount(t);
		if (counts == 0L) {
			// 初始化 商品单位 信息
			JBaseType type_10002 = new JBaseType();
			type_10002.setPar_id(10002L);
			type_10002.setType_name("个");
			type_10002.setType_desc("小件描述单位，个、件、条");
			type_10002.setC_id(user.getCust_id());
			super.getFacade().getJBaseTypeService().createJBaseType(type_10002);

			type_10002.setType_name("台");
			type_10002.setType_desc("大件描述单位");
			super.getFacade().getJBaseTypeService().createJBaseType(type_10002);

			// 初始化 商品类型 信息
			JBaseType type_10001 = new JBaseType();
			type_10001.setPar_id(10001L);
			type_10001.setType_name("LED背光电视");
			type_10001.setType_desc("系统初始化");
			type_10001.setC_id(user.getCust_id());
			super.getFacade().getJBaseTypeService().createJBaseType(type_10001);

			type_10001.setType_name("LCD背光电视");
			super.getFacade().getJBaseTypeService().createJBaseType(type_10001);

			type_10001.setType_name("等离子电视");
			super.getFacade().getJBaseTypeService().createJBaseType(type_10001);

			type_10001.setType_name("康佳电视");
			super.getFacade().getJBaseTypeService().createJBaseType(type_10001);
		}

		// 初始化 往来单位 信息 starting....
		// 代理商 查询下级客户/网点
		KonkaBranchCategory cat_20 = new KonkaBranchCategory();
		cat_20.setKonka_r3_id(user.getCust_id());
		cat_20.setCategory_id(20L);// 10 为代理商,20 为经销商
		List<KonkaBranchCategory> cat20List = super.getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryList(cat_20);

		for (KonkaBranchCategory c : cat20List) {
			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setId(c.getJxs_r3_id());
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);

			JBasePartner partner = new JBasePartner();
			partner.setC_id(user.getCust_id());
			partner.setPartner_c_id(c.getJxs_r3_id());
			Long count = super.getFacade().getJBasePartnerService().getJBasePartnerCount(partner);

			if (count == 0L) {
				partner.setPartner_type(1); // 伙伴类型: 0-供应商 1-客户, 10,供应商和客户
				partner.setPartner_obj(1); // 伙伴对象:0-个人 1-组织/单位
				partner.setPartner_name(shop.getCustomer_name()); // 往来单位名称
				partner.setPartner_sn("WLDW-KH-ZZ-" + user.getCust_id() + "-001"); // 往来单位编号
				partner.setPartner_addr(""); // 往来单位地址
				partner.setPartner_fax(""); // 往来单位传真
				partner.setLink_name(""); // 联系人姓名
				partner.setLink_mobile(""); // 联系人移动电话
				partner.setLink_tel(""); // 联系人固定电话
				partner.setLink_email(""); // 联系人邮件
				partner.setLink_qq_msn(""); // 联系人QQ/MSN
				partner.setLink_id_type(null); // 联系人证件：0-身份证 1-护照 2-港澳通行证
				// 3-台湾通行证
				partner.setLink_id(""); // 联系人ID
				partner.setLink_addr(""); // 联系人地址
				partner.setMemo(""); // 备注

				super.getFacade().getJBasePartnerService().createJBasePartner(partner);
			}
		}

		// 经销商 查询上级客户/网点
		KonkaBranchCategory cat_10 = new KonkaBranchCategory();
		cat_10.setJxs_r3_id(user.getCust_id());
		cat_10.setCategory_id(20L);// 10 为代理商,20 为经销商
		List<KonkaBranchCategory> cat10List = super.getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryList(cat_10);

		for (KonkaBranchCategory c : cat10List) {

			KonkaR3Shop shop = new KonkaR3Shop();
			shop.setId(c.getKonka_r3_id());
			shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);

			JBasePartner partner = new JBasePartner();
			partner.setC_id(user.getCust_id());
			partner.setPartner_c_id(c.getKonka_r3_id());
			Long count = super.getFacade().getJBasePartnerService().getJBasePartnerCount(partner);

			if (count == 0L) {
				partner.setPartner_type(0); // 伙伴类型: 0-供应商 1-客户, 10,供应商和客户
				partner.setPartner_obj(1); // 伙伴对象:0-个人 1-组织/单位
				partner.setPartner_name(shop.getCustomer_name()); // 往来单位名称
				partner.setPartner_sn("WLDW-GYS-ZZ-" + user.getCust_id() + "-001"); // 往来单位编号
				partner.setPartner_addr(""); // 往来单位地址
				partner.setPartner_fax(""); // 往来单位传真
				partner.setLink_name(""); // 联系人姓名
				partner.setLink_mobile(""); // 联系人移动电话
				partner.setLink_tel(""); // 联系人固定电话
				partner.setLink_email(""); // 联系人邮件
				partner.setLink_qq_msn(""); // 联系人QQ/MSN
				partner.setLink_id_type(null); // 联系人证件：0-身份证 1-护照 2-港澳通行证
				// 3-台湾通行证
				partner.setLink_id(""); // 联系人ID
				partner.setLink_addr(""); // 联系人地址
				partner.setMemo(""); // 备注

				super.getFacade().getJBasePartnerService().createJBasePartner(partner);
			}
		}
		// 初始化 往来单位 信息 end.

		// // 初始化康佳产品库
		// KonkaR3Shop shop = new KonkaR3Shop();
		// shop.setId(user.getCust_id());
		// shop =
		// super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		//
		// JBaseGoods goods = new JBaseGoods();
		// goods.setIs_konka(1);
		// goods.setC_id(user.getCust_id());
		// Long konkaCounts =
		// super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(goods);
		// if (0L == konkaCounts && null != shop// &&
		// // StringUtils.startsWithIgnoreCase(shop.getR3_code(),
		// // "WLDW")
		// ) {
		// // 无康佳产品，系统自动初始化康佳产品
		//
		// // 获取客户商品单位ID
		// JBaseType type_10002 = new JBaseType();
		// type_10002.setPar_id(10002L);
		// type_10002.setType_name("台");
		// type_10002.setC_id(user.getCust_id());
		// type_10002 =
		// super.getFacade().getJBaseTypeService().getJBaseType(type_10002);
		// Long tai_id = null;
		// if (null == type_10002) {
		// // 初始化 商品单位 信息
		// JBaseType __type = new JBaseType();
		// __type.setPar_id(10002L);
		// __type.setType_name("台");
		// __type.setType_desc("大件描述单位");
		// __type.setC_id(user.getCust_id());
		// tai_id =
		// super.getFacade().getJBaseTypeService().createJBaseType(__type);
		// } else {
		// tai_id = type_10002.getType_id();
		// }
		//
		// // 获取客户商品类型
		// JBaseType type_10001 = new JBaseType();
		// type_10001.setPar_id(10001L);
		// type_10001.setType_name("康佳电视");
		// type_10001.setC_id(user.getCust_id());
		// type_10001 =
		// super.getFacade().getJBaseTypeService().getJBaseType(type_10001);
		// Long konka_tv_id = null;
		// if (null == type_10001) {
		// // 初始化 商品单位 信息
		// JBaseType __type = new JBaseType();
		// __type.setPar_id(10001L);
		// __type.setType_name("康佳电视");
		// __type.setType_desc("系统初始化");
		// __type.setC_id(user.getCust_id());
		// konka_tv_id =
		// super.getFacade().getJBaseTypeService().createJBaseType(__type);
		// } else {
		// konka_tv_id = type_10001.getType_id();
		// }
		//
		// // 获取客户总库仓库
		// // 如果该用户没有设置进出货仓库，系统默认创建一个“总库”作为默认进出货仓库
		// JBaseStore store = new JBaseStore();
		// store.setC_id(user.getCust_id());
		// store.setIs_del(0);
		// store.setStore_name(super.getMessage(request,
		// "konka.jbill.store.total.name"));
		// store =
		// super.getFacade().getJBaseStoreService().getJBaseStore(store);
		// Long store_id = null;
		// if (null == store) {
		//
		// JBaseStore __store = new JBaseStore();
		// __store.setC_id(user.getCust_id());
		// __store.setStore_sn("CK-" + user.getCust_id() + "-01");
		// __store.setIs_del(0);
		// __store.setIs_dft_buy_store(1);
		// __store.setIs_dft_sell_store(1);
		// __store.setStore_name(super.getMessage(request,
		// "konka.jbill.store.total.name"));
		// store_id =
		// super.getFacade().getJBaseStoreService().createJBaseStore(__store);
		// } else {
		// store_id = store.getStore_id();
		// }
		//
		// int i = 1;
		// PePdModel model = new PePdModel();
		// model.setIs_del(0);
		// List<PePdModel> pePdModelList =
		// super.getFacade().getPePdModelService().getPePdModelList(model);
		// for (PePdModel cur : pePdModelList) {
		// JBaseGoods g = new JBaseGoods();
		// g.setGoods_name(cur.getMd_name());
		// g.setSell_price(cur.getPrice_ref());
		// g.setGoods_unit(tai_id); // 商品单位
		// g.setGoods_type(konka_tv_id); // 商品类型
		// g.setIs_konka(1);
		// g.setC_id(user.getCust_id());
		// g.getMap().put("store_id", String.valueOf(store_id)); // 仓库ID
		// g.setGoods_desc(cur.getPd_desc());
		// super.getFacade().getJBaseGoodsService().createJBaseGoods(g);
		//
		// try {
		// logger.info("No.{} konka product initialized! {}", i++,
		// BeanUtils.describe(g));
		// } catch (IllegalAccessException e) {
		// } catch (InvocationTargetException e) {
		// } catch (NoSuchMethodException e) {
		// }
		// }
		// }
		// // 初始化康佳产品库 end.

		// 初始化“新品”的库存
		// Long cur_c_id = user.getCust_id();
		// List<JBaseGoods> goodsList = new ArrayList<JBaseGoods>();
		// PePdModel model = new PePdModel();
		// model.setIs_new(1);
		// model.setIs_del(0);
		// List<PePdModel> newModelList =
		// super.getFacade().getPePdModelService().getPePdModelList(model);
		// if (null != newModelList && newModelList.size() > 0) {
		// for (PePdModel cur : newModelList) {
		// JBaseGoods goods = new JBaseGoods();
		// goods.setGoods_name(cur.getMd_name());
		// goods.setBuy_price(new BigDecimal("0"));
		// goods.setC_id(cur_c_id);
		// goods.setIs_konka(1);
		// goods.setSell_price(cur.getPrice_ref());
		// goods.setGoods_desc(cur.getPd_desc());
		// goodsList.add(goods);
		// }
		// }
		//
		// for (JBaseGoods cur : goodsList) {
		// JBaseGoods g = new JBaseGoods();
		// g.setC_id(cur_c_id);
		// g.setGoods_name(cur.getGoods_name());
		// if (0L <
		// super.getFacade().getJBaseGoodsService().getJBaseGoodsCount(g)) {
		// continue;
		// }
		//
		// cur.setC_id(cur_c_id);
		//
		// // 获取客户商品单位ID
		// JBaseType type_10002 = new JBaseType();
		// type_10002.setPar_id(10002L);
		// type_10002.setType_name("台");
		// type_10002.setC_id(cur_c_id);
		// type_10002 =
		// super.getFacade().getJBaseTypeService().getJBaseType(type_10002);
		// Long tai_id = null;
		// if (null == type_10002) {
		// // 初始化 商品单位 信息
		// JBaseType __type = new JBaseType();
		// __type.setPar_id(10002L);
		// __type.setType_name("台");
		// __type.setType_desc("大件描述单位");
		// __type.setC_id(cur_c_id);
		// tai_id =
		// super.getFacade().getJBaseTypeService().createJBaseType(__type);
		// } else {
		// tai_id = type_10002.getType_id();
		// }
		// cur.setGoods_unit(tai_id);
		//
		// // 获取客户商品类型
		// if (cur.getIs_konka() == 1) {
		// JBaseType type_10001 = new JBaseType();
		// type_10001.setPar_id(10001L);
		// type_10001.setType_name("康佳电视");
		// type_10001.setC_id(cur_c_id);
		// type_10001 =
		// super.getFacade().getJBaseTypeService().getJBaseType(type_10001);
		// Long konka_tv_id = null;
		// if (null == type_10001) {
		// // 初始化 商品单位 信息
		// JBaseType __type = new JBaseType();
		// __type.setPar_id(10001L);
		// __type.setType_name("康佳电视");
		// __type.setType_desc("系统初始化");
		// __type.setC_id(cur_c_id);
		// konka_tv_id =
		// super.getFacade().getJBaseTypeService().createJBaseType(__type);
		// } else {
		// konka_tv_id = type_10001.getType_id();
		// }
		// cur.setGoods_type(konka_tv_id);
		// }
		//
		// // 获取客户总库仓库
		// // 如果该用户没有设置进出货仓库，系统默认创建一个“总库”作为默认进出货仓库
		// JBaseStore store = new JBaseStore();
		// store.setC_id(cur_c_id);
		// store.setIs_del(0);
		// store.setStore_name(super.getMessage(request,
		// "konka.jbill.store.total.name"));
		// store =
		// super.getFacade().getJBaseStoreService().getJBaseStore(store);
		// Long store_id = null;
		// if (null == store) {
		//
		// JBaseStore __store = new JBaseStore();
		// __store.setC_id(cur_c_id);
		// __store.setStore_sn("CK-" + cur_c_id + "-01");
		// __store.setIs_del(0);
		// __store.setIs_dft_buy_store(1);
		// __store.setIs_dft_sell_store(1);
		// __store.setStore_name(super.getMessage(request,
		// "konka.jbill.store.total.name"));
		// store_id =
		// super.getFacade().getJBaseStoreService().createJBaseStore(__store);
		// } else {
		// store_id = store.getStore_id();
		// }
		// cur.getMap().put("store_id", store_id);
		//
		// cur.getMap().put("opr_date", DateFormatUtils.format(new Date(),
		// "yyyy-MM-dd HH:mm:ss"));
		//
		// super.getFacade().getJBaseGoodsService().createJBaseGoods(cur);
		// }
		// 初始化“新品”的库存end.
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-08
	 * @desc 创建默认仓库 type 0-进货库，1-出货库
	 */
	protected void initDefaultStore(HttpServletRequest request) {
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		// 如果该用户没有设置进出货仓库，系统默认创建一个“总库”作为默认进出货仓库
		if (storeIsNull(user)) {
			JBaseStore store = new JBaseStore();
			store.setC_id(user.getCust_id());
			store.setStore_sn("CK-" + user.getCust_id() + "-01");
			store.setIs_del(0);
			store.setIs_dft_buy_store(1);
			store.setIs_dft_sell_store(1);
			store.setStore_name(super.getMessage(request, "konka.jbill.store.total.name"));
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.setId(user.getCust_id());
			konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
			if (null != konkaR3Shop) {
				// 拿到客户编码（售达方编码）
				if (StringUtils.isNotEmpty(konkaR3Shop.getR3_code())) {
					JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
					jBaseStoreR3.setR3_code(konkaR3Shop.getR3_code());
					jBaseStoreR3.setSale_r3_code(konkaR3Shop.getR3_code());
					jBaseStoreR3.setSale_r3_name(konkaR3Shop.getR3_code());
					jBaseStoreR3.setAdd_date(new Date());
					jBaseStoreR3.setIs_del(0);
					store.setjBaseStoreR3(jBaseStoreR3);
				}
			}

			super.getFacade().getJBaseStoreService().createJBaseStore(store);
		}
	}

	/**
	 * @version 2014-12-04
	 * @desc 判断是否月末是否全部盘点，月初是否结转 type 2:结转 4：全部盘点
	 */
	protected String pdOrJzRemind(HttpServletRequest request) {
		String result = "";
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		// 如果该用户没有设置进出货仓库，系统默认创建一个“总库”作为默认进出货仓库
		if (null != user) {
			Calendar calendar = Calendar.getInstance();
			int c_year = calendar.get(Calendar.YEAR);// 当前年
			int c_month = calendar.get(Calendar.MONTH) + 1;// 当前月
			int c_day = calendar.get(Calendar.DAY_OF_MONTH);// 当前天
			int c_last_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最后一天
			if (c_day >= 25 && c_day <= 31) {// 如果日期在每月的25-31号之间，则在全部盘点的区间内
				JStocksVerify jsv = new JStocksVerify();
				jsv.setC_id(user.getCust_id());
				jsv.setType(4);
				jsv.getRow().setCount(1);
				jsv.getMap().put("start_date", c_year + "-" + c_month + "-25 00:00:00");
				jsv.getMap().put("end_date", c_year + "-" + c_month + "-" + c_last_day + " 23:59:59");
				List<JStocksVerify> jsvlist = getFacade().getJStocksVerifyService().getJStocksVerifyList(jsv);
				if (null == jsvlist || jsvlist.size() < 1) {
					result = "本月还未全部盘点，请前往操作！";
				}
			} else if (c_day >= 1 && c_day <= 5) {// 如果日期在1-5号之间，则在库存结转区间内
				Calendar calendar1 = Calendar.getInstance();
				int c_year1 = calendar1.get(Calendar.YEAR);//
				int c_month1 = calendar1.get(Calendar.MONTH);//
				calendar1.add(Calendar.MONTH, -1);
				int c_last_day1 = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);
				JStocksVerify jsv = new JStocksVerify();
				jsv.setC_id(user.getCust_id());
				jsv.setType(4);
				jsv.getRow().setCount(1);
				jsv.getMap().put("start_date", c_year1 + "-" + c_month1 + "-25 00:00:00");
				jsv.getMap().put("end_date", c_year1 + "-" + c_month1 + "-" + c_last_day1 + " 23:59:59");
				List<JStocksVerify> jsvlist = getFacade().getJStocksVerifyService().getJStocksVerifyList(jsv);
				if (null == jsvlist || jsvlist.size() < 1) {// 先判断上月末是否盘点，然后再判断月初是否结转
					JStocksVerify jsv1 = new JStocksVerify();
					jsv1.setC_id(user.getCust_id());
					jsv1.setType(2);
					jsv1.getRow().setCount(1);
					jsv1.getMap().put("start_date", c_year + "-" + c_month + "-1 00:00:00");
					jsv1.getMap().put("end_date", c_year + "-" + c_month + "-5 23:59:59");
					List<JStocksVerify> jsvlist1 = getFacade().getJStocksVerifyService().getJStocksVerifyList(jsv1);
					if (null == jsvlist1 || jsvlist1.size() < 1) {
						result = "本月初还未结转，请前往操作！";
					}
				}
			}
		}
		return result;
	}
}
