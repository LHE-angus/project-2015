package com.ebiz.mmt.web.struts.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.GlobalIpGeoLib;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcBaseAddr;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaVipPdManage;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

public class CeppOrderAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		return this.listPic(mapping, form, request, response);
	}

	/**
	 * @author Wang,KunLin
	 * @date 2014-04-02
	 */
	public ActionForward listPic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
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
		List<KonkaVipPdManage> entityList = new ArrayList<KonkaVipPdManage>();
		List<KonkaVipPdManage> XPentityList = new ArrayList<KonkaVipPdManage>();
		List<KonkaVipPdManage> JPentityList = new ArrayList<KonkaVipPdManage>();
		List<KonkaVipPdManage> RXentityList = new ArrayList<KonkaVipPdManage>();
		List<KonkaVipPdManage> THentityList = new ArrayList<KonkaVipPdManage>();
		KonkaVipPdManage konkavippdmanage = new KonkaVipPdManage();
		konkavippdmanage.setIs_hidden("0");
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkavippdmanage.setFgs_id(konkaDept.getDept_id());
		}

		// 所有商品
		entityList = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageList(konkavippdmanage);
		// 新品首发
		konkavippdmanage.setPd_type(1L);
		XPentityList = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageList(konkavippdmanage);
		// 精品推荐
		konkavippdmanage.setPd_type(2L);
		JPentityList = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageList(konkavippdmanage);
		// 热销商品
		konkavippdmanage.setPd_type(3L);
		RXentityList = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageList(konkavippdmanage);
		// 特惠商品
		konkavippdmanage.setPd_type(4L);
		THentityList = super.getFacade().getKonkaVipPdManageService().getKonkaVipPdManageList(konkavippdmanage);
		request.setAttribute("XPentityList", XPentityList);
		request.setAttribute("JPentityList", JPentityList);
		request.setAttribute("RXentityList", RXentityList);
		request.setAttribute("THentityList", THentityList);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/../customer/CeppOrder/listPic.jsp");
	}

	/**
	 * @author tudp
	 * @version 2013-09-10
	 * @desc 根据地区编码查询指定类型指定数量的商品信息
	 * @desc 
	 *       label_of_cate-->关联表【EC_BCOMP_TYPE】0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6
	 *       -团购
	 * @desc own_sys:1-工卡，2-触网，3-会员
	 * @desc dept_sn_array[总部ID, 分公司ID, R3用户ID]只有3个值
	 */
	public List<KonkaBcompPd> getKonkaBcompPdWithPindexAndTypeAndCountList(Integer own_sys, Long p_index,
			Integer label_of_cate, Integer count, Long dept_id, Long c_id, Integer prod_types[]) {
		// 处理地区信息
		Long[] p_index_array = new Long[4];
		p_index_array[0] = -1L;
		p_index_array[1] = -1L;
		p_index_array[2] = -1L;
		p_index_array[3] = p_index;
		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.getRow().setCount(10);
		bplf.setP_index(p_index);
		List<BaseProvinceListFour> bplfList = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourParentList(bplf);
		for (BaseProvinceListFour baseProvinceListFour : bplfList) {
			if (baseProvinceListFour.getP_level() == 1)
				p_index_array[0] = baseProvinceListFour.getP_index();
			if (baseProvinceListFour.getP_level() == 2)
				p_index_array[1] = baseProvinceListFour.getP_index();
			if (baseProvinceListFour.getP_level() == 3)
				p_index_array[2] = baseProvinceListFour.getP_index();
		}

		// 查询需要的产品信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		if (own_sys.intValue() == 1) {// 根据用户类型（工卡、触网） 取不同系统商品
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		} else if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			konkaBcompPd.getMap().put("own_sys_in_02", "1");
			konkaBcompPd.getMap().put("dept_id", dept_id);
			konkaBcompPd.getMap().put("c_id", c_id);
		}
		konkaBcompPd.setLabel_of_cate(label_of_cate);
		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("is_upSelf", true);
		if (prod_types != null) {
			konkaBcompPd.getMap().put("prod_type_in", prod_types);
		}
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService()
				.getKonkaBcompPdList(konkaBcompPd);
		if (null == konkaBcompPdList || 0 == konkaBcompPdList.size())
			return null;

		// 处理价格
		for (KonkaBcompPd t : konkaBcompPdList) {
			// 多图处理，主图为第一个
			t.setPicArray(StringUtils.split(t.getMain_pic(), ","));
			t.setMain_pic(StringUtils.split(t.getMain_pic(), ",")[0]);

			List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsId(own_sys, t.getId(),
					p_index_array, dept_id, c_id);
			if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
				t.setEcGoodsPrice(ecGoodsPriceList.get(0));
			}
		}

		return konkaBcompPdList;
	}

	public String getPIndexByRequest(HttpServletRequest request) {
		// 获取请求地区
		HttpSession session = request.getSession();
		String p_index = request.getParameter("p_index");
		if (StringUtils.isEmpty(p_index) || !GenericValidator.isLong(p_index)) {
			p_index = (String) session.getAttribute("p_index");
		}
		if (StringUtils.isEmpty(p_index) || p_index.length() != 6) {
			EcUser ecUser = (EcUser) session.getAttribute("ecUser");
			if (ecUser != null && ecUser.getUser_type().intValue() == 2) {
				p_index = ecUser.getP_index().toString();
				return p_index;
			}
			// 获取请求的IP
			String ip = super.getIpAddr(request);
			// 处理本地IP
			if ("0:0:0:0:0:0:0:1".equals(ip) || ip.indexOf("192.") != -1) {
				p_index = "440300"; // 本地开发，默认为深圳
			} else {
				// 根据请求的IP ，转换成p_index, 例会 p_index = 34
				String[] ipArr = ip.split("\\.");
				Long ip1 = Long.valueOf(ipArr[0]);
				Long ip2 = Long.valueOf(ipArr[1]);
				Long ip3 = Long.valueOf(ipArr[2]);
				Long ip4 = Long.valueOf(ipArr[3]);

				Long ipValue = ip1 * 255 * 255 * 255 + ip2 * 255 * 255 + ip3 * 255 + ip4;

				GlobalIpGeoLib globalIpGeoLib = new GlobalIpGeoLib();
				globalIpGeoLib.getMap().put("ipValue", ipValue);
				List<GlobalIpGeoLib> globalIpGeoLibList = super.getFacade().getGlobalIpGeoLibService()
						.getGlobalIpGeoLibForIndexList(globalIpGeoLib);

				if (null != globalIpGeoLibList && globalIpGeoLibList.size() > 0) {
					p_index = String.valueOf(globalIpGeoLibList.get(0).getMap().get("p_index"));
				} else { // IP为空则转换为深圳
					p_index = "440300";
				}
			}
		}

		// 异常判断处理
		if (!GenericValidator.isLong(p_index)) {
			p_index = "440300";
		}
		logger.info("last_p_index---->{}", p_index);
		session.setAttribute("p_index", p_index);
		return p_index;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 * @desc own_sys:1-工卡,2-触网,3-会员
	 * @desc p_index_array[省、市、县、镇]数组，镇可以为空
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public List<EcGoodsPrice> getEcGoodsPriceListWithPindexAndGoodsId(Integer own_sys, Long goods_id,
			Long[] p_index_array, String[] dept_id_array) {
		List<EcGoodsPrice> ecGoodsPriceList = new ArrayList<EcGoodsPrice>();
		// 处理价格 type:0-全国价格，1-区域价格，2-分公司价格，3-地市区域价格
		EcGoodsPrice ecGoodsPrice = new EcGoodsPrice();
		ecGoodsPrice.getRow().setCount(10);
		ecGoodsPrice.setGoods_id(goods_id);
		ecGoodsPrice.getMap().put("pindex_type_2_in", new Long[] { p_index_array[1], p_index_array[2] });
		ecGoodsPrice.getMap().put("pindex_type_3_in",
				new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		if (2 == own_sys && null != dept_id_array) { // 处理触网的价格
			// dept_sn_array[总部ID,分公司ID,R3用户ID]
			ecGoodsPrice.getMap().put("touch_r3_user_id", dept_id_array[2]);
			ecGoodsPrice.getMap().put("touch_fgs_id", dept_id_array[1]);
		}
		ecGoodsPriceList = super.getFacade().getEcGoodsPriceService()
				.getEcGoodsPriceWithGoodsIdAndPindexList(ecGoodsPrice);
		return ecGoodsPriceList;
	}

	/**
	 * @author Tudp
	 * @version 2013-09-10
	 * @desc own_sys:1-工卡,2-触网,3-会员
	 * @desc p_index_array[省、市、县、镇]数组，镇可以为空
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public List<EcGoodsPrice> getEcGoodsPriceListWithPindexAndGoodsId(Integer own_sys, Long goods_id,
			Long[] p_index_array, Long dept_id, Long c_id) {
		List<EcGoodsPrice> ecGoodsPriceList = new ArrayList<EcGoodsPrice>();
		// 处理价格 type:0-全国价格，1-区域价格，2-分公司价格，3-地市区域价格
		EcGoodsPrice ecGoodsPrice = new EcGoodsPrice();
		ecGoodsPrice.getRow().setCount(10);
		ecGoodsPrice.setGoods_id(goods_id);
		if (own_sys.intValue() == 1) {// 根据用户类型（工卡、触网） 取不同系统商品
			ecGoodsPrice.getMap().put("own_sys_in_01", "1");
		} else if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			ecGoodsPrice.getMap().put("own_sys_in_02", "1");
			ecGoodsPrice.getMap().put("dept_id", dept_id);
			ecGoodsPrice.getMap().put("c_id", c_id);
		}
		ecGoodsPrice.getMap().put("pindex_type_2_in", new Long[] { p_index_array[1], p_index_array[2] });
		ecGoodsPrice.getMap().put("pindex_type_3_in",
				new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		ecGoodsPriceList = super.getFacade().getEcGoodsPriceService()
				.getEcGoodsPriceWithGoodsIdAndPindexList(ecGoodsPrice);
		return ecGoodsPriceList;
	}

	/**
	 * @author Wang,KunLin
	 * @date 2014-04-02
	 */

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);
		String goods_id = request.getParameter("goods_id");

		DynaBean dynaBean = (DynaBean) form;
		String cust_id = (String) dynaBean.get("cust_id");
		String[] pd_ids = request.getParameterValues("pd_ids");

		String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
		String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		if (GenericValidator.isLong(CUSTID)) {
			user.getRow().setCount(1);
			user.setCust_id(Long.valueOf(CUSTID));
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null == userList || 0 == userList.size()) {
			}
			user = userList.get(0);
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		} else {
			user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		}
		if (null == user) {
			return null;
		}

		// 取收货地址
		KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
		konkaJxcBaseAddr.setIs_del(0);

		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setId(user.getCust_id());
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if (null != r3shop) {
			konkaJxcBaseAddr.setR3_id(r3shop.getId());
			konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
		}

		List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService()
				.getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
		request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

		if (null != konkaJxcBaseAddrList && konkaJxcBaseAddrList.size() > 0) {
			for (KonkaJxcBaseAddr kba : konkaJxcBaseAddrList) {
				if (kba.getIs_default() == 0) {
					dynaBean.set("send_type", 2);
					dynaBean.set("user_name", kba.getUser_name());
					dynaBean.set("user_tel", kba.getUser_tel());
					dynaBean.set("user_phone", kba.getUser_phone());
					if (StringUtils.isNotBlank(kba.getUser_p_index().toString())) {
						String p_index = kba.getUser_p_index().toString();
						if (!p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", p_index);
							} else if (p_index.length() == 8) {
								dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
								dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
								dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
								dynaBean.set("town", p_index);
							}
						} else if (p_index.endsWith("0000")) {
							dynaBean.set("province", p_index);
						} else if (p_index.endsWith("00")) {
							dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
							dynaBean.set("city", p_index);
						}
					}
					dynaBean.set("user_addr", kba.getUser_addr());
					dynaBean.set("user_remark", kba.getUser_remark());
				}
			}

		}
		Long current_cust_id = null;

		if (null != user.getCust_id()) {
			// 当前登录人是客户
			current_cust_id = user.getCust_id();
		} else {
			// 当前登录人不是客户
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(user.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_eq_60 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 60L) {
					role_id_eq_60 = true;
				}
			}
			if (user.getCust_id() == null && role_id_eq_60) {
				if (!GenericValidator.isLong(cust_id)) {
					return null;
				}
				current_cust_id = Long.valueOf(cust_id);
			}
		}

		if (null == current_cust_id) {
			return null;
		}

		request.setAttribute("cust_id", current_cust_id);

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(current_cust_id);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null == konkaR3Shop) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none")
					+ "');history.back();");
			return null;
		}

		// 取订单流程
		KonkaDept dept = super.getKonkaDeptByCustomerId(current_cust_id);
		KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
		ap.getMap().put("par_add_dept_id", dept.getDept_id());
		ap.setIs_del(0);
		List<KonkaOrderAuditProcess> auditProcesseList = getFacade().getKonkaOrderAuditProcessService()
				.getKonkaOrderAuditProcessList(ap);

		if (null == auditProcesseList || auditProcesseList.size() == 0) {
			// 没有分公司自定义的流程取统一流程，即分公司优先级高
			KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
			ap_public.setAdd_dept_id(0L);
			ap_public.setIs_del(0);
			List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService()
					.getKonkaOrderAuditProcessList(ap_public);
			auditProcesseList.addAll(ap_publicauditProcesseList);
		}

		request.setAttribute("processList", auditProcesseList);

		// 查询客户用户信息
		// PeProdUser u = new PeProdUser();
		// u.setCust_id(current_cust_id);
		// u = super.getFacade().getPeProdUserService().getPeProdUser(u);
		//
		// if (null != u) {
		// request.setAttribute("u", u);
		// if (null != u.getP_index()) {
		// super.setprovinceAndcityAndcountryToFrom(dynaBean, u.getP_index());
		// }
		// }

		dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
		dynaBean.set("trade_index", "LSH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
		dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));// 订单日期
		dynaBean.set("cg_order_date", new Date());// 订单日期
		dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
		dynaBean.set("r3_code", konkaR3Shop.getR3_code());

		dynaBean.set("userName", user.getReal_name());
		// dynaBean.set("userAddr", user.getLink_addr());

		// dynaBean.set("userZip", entp.getPostcode());
		// dynaBean.set("userAddr", entp.getAddr());
		// dynaBean.set("userTel", entp.getTel());
		// dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

		String fgsSN = konkaR3Shop.getBranch_area_name_2();
		String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
		request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

		/** 取网点业务员 */
		BranchAssign bagn = new BranchAssign();
		bagn.setKonka_r3_id(current_cust_id);
		bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
		if (null != bagn && null != bagn.getUser_id()) {
			PeProdUser ywy = new PeProdUser();
			ywy.setId(bagn.getUser_id());
			ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
			request.setAttribute("ywy_user_name", ywy.getReal_name());
		}

		String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;

		ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
		info = super.getFacade().getR3WebInterfaceService()
				.getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
		List<KNVP> knvpList = info.getDataResult();

		// AG:售达方
		// RE:出票方
		// RG:付款方
		// WE:送达方

		List<KNVP> agList = new ArrayList<KNVP>();
		List<KNVP> reList = new ArrayList<KNVP>();
		List<KNVP> rgList = new ArrayList<KNVP>();
		List<KNVP> weList = new ArrayList<KNVP>();

		if (null != knvpList) {
			for (KNVP cur : knvpList) {
				if ("AG".equalsIgnoreCase(cur.getPARVW())) {
					agList.add(cur);
				} else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
					reList.add(cur);
				} else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
					rgList.add(cur);
				} else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
					weList.add(cur);
				}
			}
		}

		request.setAttribute("agList", agList);
		request.setAttribute("reList", reList);
		request.setAttribute("rgList", rgList);
		request.setAttribute("weList", weList);

		if (agList.size() == 0) {
			request.setAttribute("ag", konkaR3Shop.getR3_code());
		}
		if (reList.size() == 0) {
			request.setAttribute("re", konkaR3Shop.getR3_code());
		}
		if (rgList.size() == 0) {
			request.setAttribute("rg", konkaR3Shop.getR3_code());
		}
		if (weList.size() == 0) {
			KonkaMobileImpStore s = new KonkaMobileImpStore();
			s.setR3_shdf_sn(konkaR3Shop.getR3_code());
			List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService()
					.getKonkaMobileImpStoreListForDistinctSdf(s);

			List<KNVP> __weList = new ArrayList<KNVP>();
			for (KonkaMobileImpStore cur : sList) {
				KNVP k = new KNVP();
				k.setKUNN2(cur.getR3_sdf_sn());
				__weList.add(k);
			}
			request.setAttribute("weList", __weList);
			if (__weList.size() == 0) {
				request.setAttribute("we", konkaR3Shop.getR3_code());
			}
		}

		request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

		Long dept_id_1 = 0L;
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		if (null != shop && shop.getBranch_area_name_2() != null) {
			KonkaDept dept1 = new KonkaDept();
			dept1.setDept_sn(shop.getBranch_area_name_2());
			dept1 = super.getFacade().getKonkaDeptService().getKonkaDept(dept1);

			if (null != dept1) {
				dept_id_1 = dept1.getDept_id();
			}
		}
		String p_index = getPIndexByRequest(request);
		// 取商品信息

		List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();

		if (StringUtils.isNotBlank(goods_id)) {

			KonkaOrderInfoDetails konkaorderinfodeatils = new KonkaOrderInfoDetails();
			// 取型号、大类信息
			PePdModel pePdModel = new PePdModel();
			pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
			pePdModel.setIs_del(0);
			pePdModel.setPd_id(Long.valueOf(goods_id));
			pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);

			if (null != pePdModel) {

				konkaorderinfodeatils.setPd_id(Long.valueOf(goods_id));
				konkaorderinfodeatils.setPd_name(pePdModel.getMd_name());
				konkaorderinfodeatils.setBrand_id(pePdModel.getBrand_id());
				konkaorderinfodeatils.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaorderinfodeatils.setPd_type_id(pePdModel.getCls_id());
				konkaorderinfodeatils.setGood_price(BigDecimal.valueOf(super.getPdmodelPrice(pePdModel.getMd_name(),
						konkaR3Shop.getR3_code())));
				konkaorderinfodeatils.setGood_sum_price(BigDecimal.valueOf(super.getPdmodelPrice(
						pePdModel.getMd_name(), konkaR3Shop.getR3_code())));

				if (null != pePdModel.getCls_id() && "".equals(pePdModel.getCls_id())
						&& null != super.getBasePdClazz(pePdModel.getCls_id()))
					konkaorderinfodeatils.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
			}
			konkaorderinfodeatils.setGood_count(1);
			konkaorderinfodeatils.setGood_unit("台");
			konkaorderinfodeatils.setPd_remark("");
			konkaOrderInfoDetailsList.add(konkaorderinfodeatils);
		}
		if (null != pd_ids && pd_ids.length > 0) {
			for (String pd_is : pd_ids) {
				KonkaOrderInfoDetails konkaorderinfodeatils = new KonkaOrderInfoDetails();
				//System.out.println(pd_is + "哈哈哈哈哈");
				PePdModel pePdModel = new PePdModel();
				pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
				pePdModel.setIs_del(0);
				pePdModel.setPd_id(Long.valueOf(pd_is));
				pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (null != pePdModel) {

					konkaorderinfodeatils.setPd_id(Long.valueOf(pd_is));
					konkaorderinfodeatils.setPd_name(pePdModel.getMd_name());

					//System.out.println(pd_is + pePdModel.getMd_name());
					konkaorderinfodeatils.setBrand_id(pePdModel.getBrand_id());
					konkaorderinfodeatils.setBrand_name(Constants.KONKA_BRAND_NAME);
					konkaorderinfodeatils.setPd_type_id(pePdModel.getCls_id());

					konkaorderinfodeatils.setGood_price(BigDecimal.valueOf(super.getPdmodelPrice(
							pePdModel.getMd_name(), konkaR3Shop.getR3_code())));
					konkaorderinfodeatils.setGood_sum_price(BigDecimal.valueOf(super.getPdmodelPrice(
							pePdModel.getMd_name(), konkaR3Shop.getR3_code())));

					if (null != pePdModel.getCls_id() && "".equals(pePdModel.getCls_id())
							&& null != super.getBasePdClazz(pePdModel.getCls_id()))
						konkaorderinfodeatils
								.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
				}

				konkaorderinfodeatils.setGood_count(1);
				konkaorderinfodeatils.setGood_unit("台");
				konkaorderinfodeatils.setPd_remark("");
				konkaOrderInfoDetailsList.add(konkaorderinfodeatils);
			}
		}

		request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);
		return new ActionForward("/../customer/CeppOrder/input.jsp");
	}

	/**
	 * @author Tudp
	 * @version 2013-09-13
	 * @desc 根据地区信息和商品ID取商品详细信息
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public KonkaBcompPd getKonkaBcompPdAllDataWithPindexAndGoodsId(Integer own_sys, Long goods_id, Long p_index,
			String[] dept_id_array, Long dept_id, Long c_id) {
		if (null == p_index || null == goods_id)
			return null;

		// 处理地区信息
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		// 查询需要的产品信息
		KonkaBcompPd entity = new KonkaBcompPd();
		entity.getRow().setCount(2);
		entity.setId(Long.valueOf(goods_id));
		entity.getMap().put("today", true);
		entity.getMap().put("is_upSelf", true);
		if (own_sys.intValue() == 1) {// 根据用户类型（工卡、触网） 取不同系统商品
			entity.getMap().put("own_sys_in_01", "1");
		} else if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			entity.getMap().put("own_sys_in_02", "1");
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("c_id", c_id);
		}
		List<KonkaBcompPd> entityList = getFacade().getKonkaBcompPdService().getKonkaBcompPdList(entity);
		if (null == entityList || 1 != entityList.size())
			return null;
		entity = entityList.get(0); // 赋值

		// 处理图片列表
		String[] picArray = StringUtils.split(entity.getMain_pic(), ",");
		entity.setPicArray(picArray);
		entity.setMain_pic(picArray[0]);

		// 查询介绍规格参数等
		KonkaBcompPdContent kbpc = new KonkaBcompPdContent();
		kbpc.setKbp_id(entity.getId());
		List<KonkaBcompPdContent> kbpcList = getFacade().getKonkaBcompPdContentService().getKonkaBcompPdContentList(
				kbpc);
		entity.setKonkaBcompPdContentList(kbpcList);

		// 查询绑定服务
		EcBindingPd ecBindingPd = new EcBindingPd();
		ecBindingPd.setBinding_type(0);
		ecBindingPd.getMap().put("goods_id", entity.getId());
		List<EcBindingPd> ecBindingPdListForService = getFacade().getEcBindingPdService()
				.getEcBindingPdWithGoodsIdAndTypeList(ecBindingPd);
		entity.setEcBindingPdListForService(ecBindingPdListForService);

		// 查询绑定套餐
		ecBindingPd.setBinding_type(1);
		List<EcBindingPd> ecBindingPdListForPackages = getFacade().getEcBindingPdService()
				.getEcBindingPdWithGoodsIdAndTypeList(ecBindingPd);
		entity.setEcBindingPdListForPackages(ecBindingPdListForPackages);

		// 查询商品佣金、返利
		EcBcompPdRebates ecBcompPdRebates = new EcBcompPdRebates();
		ecBcompPdRebates.setIs_del(0);
		ecBcompPdRebates.setGoods_id(entity.getId());
		ecBcompPdRebates.setOwn_sys(own_sys);
		if (own_sys.intValue() == 2) {
			ecBcompPdRebates.setDept_id(dept_id);
			ecBcompPdRebates.setDept_id(c_id);
		}
		ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);
		entity.setEcBcompPdRebates(ecBcompPdRebates);

		// 查询价格
		List<EcGoodsPrice> ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsId(own_sys, entity.getId(),
				p_index_array, dept_id, c_id);
		if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size())
			entity.setEcGoodsPrice(ecGoodsPriceList.get(0));

		// 查询库存
		List<EcStocks> ecStocksList = getEcStocksListWithPindexAndGoodsId(own_sys, entity.getId(), p_index_array,
				dept_id_array);
		if (null != ecStocksList && 0 != ecStocksList.size()) {
			entity.setEcStocks(ecStocksList.get(0));
		}

		// 查询快递到达天数
		if (null != ecStocksList && 0 != ecStocksList.size()) {
			EcBaseExpressReachDay eberd = new EcBaseExpressReachDay();
			eberd.setIs_del(0);
			eberd.getRow().setCount(10);
			eberd.setStore_id(ecStocksList.get(0).getStore_id());
			eberd.getMap().put("order_by_area_code_desc", true);
			eberd.getMap().put("area_code_in", new Long[] { p_index_array[3], p_index_array[2], p_index_array[1] }); // 查询地区、县、市倒序排列
			List<EcBaseExpressReachDay> eberdList = getFacade().getEcBaseExpressReachDayService()
					.getEcBaseExpressReachDayList(eberd);
			if (null != eberdList && 0 != eberdList.size())
				entity.setEcBaseExpressReachDay(eberdList.get(0));
		}

		return entity;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 * @desc own_sys:1-工卡,2-触网,3-会员
	 * @desc p_index_array[省、市、县、镇]数组，镇可以为空
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public List<EcStocks> getEcStocksListWithPindexAndGoodsId(Integer own_sys, Long goods_id, Long[] p_index_array,
			String[] dept_id_array) {
		// 处理库存，type：0-全国总仓，1-区域大仓，2-分公司仓
		EcStocks entity = new EcStocks();
		entity.getRow().setCount(10);
		entity.setGoods_id(goods_id);
		entity.getMap().put("pindex_type_1_in", new Long[] { p_index_array[0], p_index_array[1] });
		entity.getMap().put("pindex_type_2_in", new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksWithGoodsIdAndPindexList(entity);
		return entityList;
	}

	public Long[] getPindexArrayByPindex(Long p_index) {
		if (null == p_index)
			return null;
		Long[] p_index_array = new Long[4];
		p_index_array[0] = -1L;
		p_index_array[1] = -1L;
		p_index_array[2] = -1L;
		p_index_array[3] = p_index;
		BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
		baseProvinceListFour.getRow().setCount(10);
		baseProvinceListFour.setP_index(p_index);
		List<BaseProvinceListFour> baseProvinceListFourList = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourParentList(baseProvinceListFour);
		for (BaseProvinceListFour tt : baseProvinceListFourList) {
			if (tt.getP_level() == 1)
				p_index_array[0] = tt.getP_index();
			if (tt.getP_level() == 2)
				p_index_array[1] = tt.getP_index();
			if (tt.getP_level() == 3)
				p_index_array[2] = tt.getP_index();
		}
		return p_index_array;
	}
}