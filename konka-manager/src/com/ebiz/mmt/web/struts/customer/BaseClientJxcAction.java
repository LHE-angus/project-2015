package com.ebiz.mmt.web.struts.customer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForward;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JStocks;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.JsStocks;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,ShangLong
 * @version 2013-06-08
 */
public class BaseClientJxcAction extends BaseAction {

	/**
	 * @author Li,Ka
	 * @version 2011-11-28 以时间方式生成流水号
	 */
	protected String generateTradeIndex() {
		Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
		return "LSH" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-03-22以时间方式生成退货号
	 */
	protected String generateReturnTradeIndex() {
		Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
		return "THH" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;
	}

	/**
	 * @author Xiao,GuoJian
	 * @param start
	 *            YR,YC,DR,DC
	 * @return
	 */
	protected String generateJxcTransIndex(String start) {
		Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
		if (StringUtils.isEmpty(start)) {
			return "YC" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;
		}
		return start + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * @desc 回显市区县乡村单个的信息
	 */
	public void setprovinceAndcityAndcountryToFrom(DynaBean dynaBean, Long p_index) {
		if (null != p_index) {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.getMap().put("shop_p_index", p_index);
			List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListAllParPindexByPindex(baseProvince);
			for (BaseProvinceList bp : baseProvinceList) {
				if (bp.getP_level().intValue() == 1) {
					dynaBean.set("province", bp.getP_index());
				}
				if (bp.getP_level().intValue() == 2) {
					dynaBean.set("city", bp.getP_index());
				}
				if (bp.getP_level().intValue() == 3) {
					dynaBean.set("country", bp.getP_index());
				}
			}
		}
	}

	/**
	 * @author du,zhiming
	 * @version 2012/2/2
	 * @desc 根据分公司部门Id，取对应的特殊流程
	 */
	protected KonkaOrderAuditProcess getSpecialProcessByFgsDeptId(Long deptId) {
		KonkaOrderAuditProcess kkoap = new KonkaOrderAuditProcess();
		kkoap.setAdd_dept_id(deptId);
		kkoap.setIs_del(0);
		kkoap.setProcess_type(2);
		return getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(kkoap);
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * @desc 根据区域编码取全称
	 */
	public String getPIndexFullName(Long p_index) {
		String fullName = "";
		if (null != p_index) {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.getMap().put("shop_p_index", p_index);
			List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListAllParPindexByPindex(baseProvince);
			for (BaseProvinceList bp : baseProvinceList) {
				if (bp.getP_level().intValue() == 1) {
					fullName = bp.getP_name();
				}
				if (bp.getP_level().intValue() == 2) {
					fullName = fullName + bp.getP_name();
				}
				if (bp.getP_level().intValue() == 3) {
					fullName = fullName + bp.getP_name();
				}
			}
		}
		return fullName;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-14
	 * @desc 根据区域编码取全称
	 */
	public String getPIndexName(Long p_index, String conectString) {
		StringBuffer fullName = new StringBuffer("");
		if (null != p_index) {
			BaseProvinceListFour baseProvince = new BaseProvinceListFour();
			baseProvince.setP_index(p_index);
			List<BaseProvinceListFour> baseProvinceList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourParentList(baseProvince);
			for (BaseProvinceListFour t : baseProvinceList)
				fullName.append(t.getP_name()).append(conectString);
		}
		return fullName.toString();
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-08
	 * @desc 获取客户往来单位信息列表
	 */
	protected List<JBasePartner> getJBasePartners(String partner_type, String partner_obj, Long user_id) {

		JBasePartner jBasePartner = new JBasePartner();
		if (StringUtils.isNotEmpty(partner_type)) {
			// jBasePartner.setPartner_type(Integer.valueOf(partner_type));
			jBasePartner.getMap().put("partner_type_value", partner_type);
		}
		if (StringUtils.isNotEmpty(partner_obj)) {
			jBasePartner.setPartner_obj(Integer.valueOf(partner_obj));
		}
		jBasePartner.setIs_del(0);
		jBasePartner.setC_id(user_id);
		List<JBasePartner> jBasePartnerList = super.getFacade().getJBasePartnerService()
				.getJBasePartnerList(jBasePartner);

		return jBasePartnerList;
	}

	/**
	 * @author Wang,Kunlin
	 * @version 2014-03-31
	 * @desc 获取客户往来单位信息列表(只要姓名)
	 */
	protected List<JBasePartner> getJBasePartnersOnlyName(String partner_type, String partner_obj, Long user_id) {

		JBasePartner jBasePartner = new JBasePartner();
		if (StringUtils.isNotEmpty(partner_type)) {
			// jBasePartner.setPartner_type(Integer.valueOf(partner_type));
			jBasePartner.getMap().put("partner_type_value", partner_type);
		}
		if (StringUtils.isNotEmpty(partner_obj)) {
			jBasePartner.setPartner_obj(Integer.valueOf(partner_obj));
		}
		// jBasePartner.setIs_del(0);
		jBasePartner.setC_id(user_id);
		// jBasePartner.getMap().put(key, value)
		List<JBasePartner> jBasePartnerList = super.getFacade().getJBasePartnerService()
				.getJBasePartnerListOnlyName(jBasePartner);

		return jBasePartnerList;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-08
	 * @desc 获取基础信息
	 * @param par_id
	 *            :品牌 10001、商品类型 10002
	 */
	protected List<JBaseType> getJBaseTypes(Long par_id, Long user_id) {

		JBaseType jBaseType = new JBaseType();
		jBaseType.setPar_id(par_id);
		jBaseType.setIs_del(0);
		jBaseType.setC_id(user_id);
		List<JBaseType> jBaseTypeList = super.getFacade().getJBaseTypeService().getJBaseTypeList(jBaseType);

		return jBaseTypeList;
	}

	/**
	 *
	 * @param par_id
	 * @param user_id
	 * @return
	 */
	protected List<JBaseType> getJBaseTypes(Long par_id, Long user_id, String except_type) {

		JBaseType jBaseType = new JBaseType();
		jBaseType.setPar_id(par_id);
		jBaseType.setIs_del(0);
		jBaseType.setC_id(user_id);
		jBaseType.getMap().put("except_type",except_type);
		List<JBaseType> jBaseTypeList = super.getFacade().getJBaseTypeService().getJBaseTypeList(jBaseType);

		return jBaseTypeList;
	}

	protected boolean storeIsNull(PeProdUser user) {
		boolean flag = false;
		JBaseStore store = new JBaseStore();
		store.setC_id(user.getCust_id());
		store.setIs_del(0);
		Long recordCount = super.getFacade().getJBaseStoreService().getJBaseStoreCount(store);

		if (recordCount == 0L) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-14
	 * @desc 创建客户总库
	 */
	protected void createZstore(HttpServletRequest request) {
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
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

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-08
	 * @desc 获取客户仓库列表 type 0-进货库，1-出货库
	 */
	protected List<JBaseStore> getJBaseStores(HttpServletRequest request, int type) {
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		// 如果该用户没有设置进出货仓库，系统默认创建一个“总库”作为默认进出货仓库
		if (storeIsNull(user)) {
			createZstore(request);
		}
		List<JBaseStore> jBaseStoreList = new ArrayList<JBaseStore>();
		JBaseStore jBaseStore = new JBaseStore();
		if (type == 0) {
			jBaseStore.getMap().put("order_by_is_dft_buy_store_desc", true);
		} else {
			jBaseStore.getMap().put("order_by_is_dft_sell_store_desc", true);
		}
		jBaseStore.setC_id(user.getCust_id());
		jBaseStore.setIs_del(0);
		jBaseStoreList = super.getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);

		return jBaseStoreList;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-08
	 * @desc 获取客户商品列表
	 */
	protected List<JBaseGoods> getJBaseGoodsList(Long user_id) {

		List<JBaseGoods> jBaseGoodsList = new ArrayList<JBaseGoods>();
		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.setGoods_stutes(0);
		jBaseGoods.setC_id(user_id);
		jBaseGoodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);

		return jBaseGoodsList;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-03-26
	 * @desc 获取客户商品列表
	 */
	protected List<JBaseGoods> getJBaseGoodsList(Long user_id, Integer status) {

		List<JBaseGoods> jBaseGoodsList = new ArrayList<JBaseGoods>();
		JBaseGoods jBaseGoods = new JBaseGoods();
		if (status != null) {
			jBaseGoods.setGoods_stutes(status);
		}
		jBaseGoods.setC_id(user_id);
		jBaseGoodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);

		return jBaseGoodsList;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-09
	 * @desc 生成单据编号 采购:CG, 采购退货:CGTH, 销售:XS, 销售退货:XSTH, 盘亏:PK, 盘盈:PY
	 */
	protected String getJBillSn(String type) {
		Date today = new Date();
		String bill_sn = type + "-" + DateFormatUtils.format(today, "yyyyMMdd") + "-";
		Long seq_jbill_id = super.getFacade().getJBillService().getSeqJBillId();
		bill_sn = bill_sn + StringUtils.leftPad(seq_jbill_id.toString(), 8, "0");

		return bill_sn;
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2013-6-14
	 * @param needGetDeptId
	 *            true 需要取得部门ids，false 不需要部门IDs
	 * @desc 获得商品是否是 代理商：10 ，经销商【直供或者非直供】：20，以及获得对应是部门ID
	 * @return r3_dept_ids 上级部门IDs：result.get("r3_dept_ids")
	 * @return result_code 信息状态 代理商10成功，经销商20成功，0失败：result.get("result_code")
	 */
	public HashMap<String, String> getKonkaDeptIdAndType(Long cust_id, boolean needGetDeptId) {
		HashMap<String, String> result = new HashMap<String, String>();
		List<KonkaR3MmtMatch> konkaR3MmtMatchList = super.getAgentKonkaR3IdByKonkaCustId(cust_id);
		String type = "0";
		String r3_dept_ids_string = "-1";
		Long r3_dept_id = -1l;
		if (null != konkaR3MmtMatchList && konkaR3MmtMatchList.size() > 0) {// 判断是否为代理商
			// 或者
			// 是经销商【直供】
			List<KonkaR3MmtMatch> konkaR3MmtMatchList_base = new ArrayList<KonkaR3MmtMatch>();

			List<KonkaR3MmtMatch> konkaR3MmtMatchList_dls = new ArrayList<KonkaR3MmtMatch>();
			for (KonkaR3MmtMatch konkaR3MmtMatch1 : konkaR3MmtMatchList) {
				KonkaBranchCategory t = new KonkaBranchCategory();
				t.setCategory_id(10l);
				t.setKonka_r3_id(konkaR3MmtMatch1.getR3_shop_id());
				t = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(t);
				if (null != t) {
					konkaR3MmtMatchList_dls.add(konkaR3MmtMatch1);
				}
			}
			if (null != konkaR3MmtMatchList_dls && konkaR3MmtMatchList_dls.size() > 0) {
				type = "10";// 代理商
				konkaR3MmtMatchList_base.addAll(konkaR3MmtMatchList_dls);
			} else {
				type = "20";// 经销商【直供】
				konkaR3MmtMatchList_base.addAll(konkaR3MmtMatchList);
			}

			if (needGetDeptId) {
				String[] r3_dept_ids = new String[konkaR3MmtMatchList_base.size()];
				int i = 0;
				for (KonkaR3MmtMatch krmm : konkaR3MmtMatchList_base) {
					r3_dept_id = super.getDeptIdByAgentAgentKonkaR3Id(krmm.getR3_shop_id());// 获取代理商上级分公司部门Id
					r3_dept_ids[i++] = r3_dept_id.toString();
				}
				r3_dept_ids_string = StringUtils.join(r3_dept_ids, ",");
			}

		} else {// 代理商下级网点，判断是否是经销商【非直供】
			Long r3_id_shop = super.getSuperiorAgentKonkaR3IdByKonkaCustId(cust_id);
			if (r3_id_shop.intValue() != -1) {
				type = "20";// 经销商【非直供】
				if (needGetDeptId) {
					r3_dept_id = super.getDeptIdByAgentAgentKonkaR3Id(r3_id_shop);// 获取网点代理商上级分公司部门Id
					r3_dept_ids_string = r3_dept_id.toString();
				}
			}
		}
		if (needGetDeptId) {
			result.put("r3_dept_ids", r3_dept_ids_string);
		}
		result.put("result_code", type);
		return result;
	}

	/**
	 * @author Li,Ka
	 * @version 2012/2/16
	 * @desc 根据KONKA_R3_ID查找客户分配
	 */
	protected BranchAssign getBranchAssignByKonkaR3Id(Long konkaR3Id) {
		BranchAssign t = new BranchAssign();
		t.setKonka_r3_id(konkaR3Id);
		t = getFacade().getBranchAssignService().getBranchAssign(t);
		return t;
	}

	/**
	 * @author Li,Ka
	 * @version 2012/2/16
	 * @desc 根据登录用户的id，取该用户
	 */
	protected PeProdUser getPeProdUserByUserId(Long userId) {
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(userId);
		peProdUser = getFacade().getPeProdUserService().getPeProdUser(peProdUser);
		return peProdUser;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-12-08
	 * @desc 根据订单ID，取订单审核信息包含角色信息列表
	 */
	protected List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditWithRoleInfoList(Long orderId) {
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(orderId);
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditWithRoleInfoList(konkaOrderInfoAudit);
		return konkaOrderInfoAuditWithRoleInfoList;
	}

	/**
	 * @author Zhang,Xufeng
	 * @version 2011-12-21
	 * @desc 根据订单ID，取对应的分公司信息
	 */
	protected KonkaDept getKonkaFGSByOrderId(Long orderId) {
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.getMap().put("order_id_for_fgs", orderId);
		return getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-09
	 * @desc 根据订单审核类型取得对应流程的最高审核级别 分公司dept_id， 订单order_id
	 */
	protected Long getKonkaOrderAuditProcessNodeMaxLevel(Long dept_id, Long order_id) {
		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.getMap().put("dept_id", dept_id);
		node.getMap().put("order_id", order_id);
		return getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeMaxLevel(node);
	}

	protected Long getKonkaShopId(KonkaOrderInfo konkaOrderInfo) {
		return null == konkaOrderInfo.getShop_id() ? konkaOrderInfo.getCust_id() : konkaOrderInfo.getShop_id();
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-19
	 * @desc 画面迁移从哪里来哪里去
	 */
	protected ActionForward toReturnUrl(String returnUrl, HttpServletResponse response) {
		try {
			response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-21
	 * @desc 获取实时库存
	 */
	protected Long getStocks(Long goods_id, Long store_id) {
		Long stocks = 0L;
		JStocks jStocks = new JStocks();
		jStocks.setGoods_id(Long.valueOf(goods_id));
		jStocks.setStore_id(Long.valueOf(store_id));
		jStocks = super.getFacade().getJStocksService().getJStocks(jStocks);
		if (null != jStocks) {
			stocks = jStocks.getStocks();
		}

		return stocks;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-21
	 * @desc 获取实时库存
	 */
	protected Long getStocks(Long goods_id, Long store_id, Long c_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Long stocks = 0L;
		JStocks jStocks = new JStocks();
		jStocks.setGoods_id(Long.valueOf(goods_id));
		jStocks.setStore_id(Long.valueOf(store_id));
		jStocks = super.getFacade().getJStocksService().getJStocks(jStocks);
		if (null != jStocks) {
			stocks = jStocks.getStocks();
		}

		JStocksVerify jsv = new JStocksVerify();
		jsv.setGoods_id(goods_id);
		jsv.setStore_id(store_id);
		jsv.getMap().put("order_by_opr_date_desc", true);
		List<JStocksVerify> jsvList = super.getFacade().getJStocksVerifyService().getJStocksVerifyList(jsv);
		if (jsvList.size() > 0) {

			KonkaMobileSailData data = new KonkaMobileSailData();
			data.setCustomer_id(c_id);
			data.setIs_del(0);
			data.getMap().put("report_date_s", sdf.format(jsvList.get(0).getOpr_date()));
			List<KonkaMobileSailData> dataList = super.getFacade().getKonkaMobileSailDataService()
					.getKonkaMobileSailDataForFgsTopCount(data);
			if (dataList.size() > 0 && null != dataList.get(0).getMap().get("num")) {
				stocks = stocks - Long.valueOf(dataList.get(0).getMap().get("num").toString());
			}
		}

		return stocks;
	}

	/**
	 * @authur xingxiudong
	 * @desc 根据客户ID查询分公司部门对象
	 */
	protected KonkaDept getKonkaDeptByCustomerId(Long cust_id) {
		if (null == cust_id) {
			return null;
		}

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(cust_id);
		s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

		if (null != s && StringUtils.isNotBlank(s.getBranch_area_name_2())) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(s.getBranch_area_name_2().trim().toUpperCase());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			return dept;
		}

		return null;
	}

	/**
	 * @authur xingxiudong
	 * @desc 根据客户ID查询业务员用户对象
	 */
	protected PeProdUser getYwyOfCustomerByCustomerId(Long cust_id) {
		if (null == cust_id) {
			return null;
		}

		logger.info("****************************** cust_id : {}", cust_id);

		BranchAssign t = new BranchAssign();
		t.setKonka_r3_id(cust_id);
		// 根据客户分配的结果查询业务员， 一个客户只能属于一个业务员
		t = getFacade().getBranchAssignService().getBranchAssign(t);

		if (null == t) {
			return null;
		}

		try {
			logger.info("****************************** User : {}", BeanUtils.describe(t));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Long user_id = t.getUser_id(); // 业务员ID
		if (null == user_id) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user_id);
		_peRoleUser.setRole_id(60L);
		if (0L == this.getFacade().getPeRoleUserService().getPeRoleUserCount(_peRoleUser)) {
			return null;
		}

		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(user_id);
		peProdUser = getFacade().getPeProdUserService().getPeProdUser(peProdUser);

		return peProdUser;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-14
	 * @desc 根据R3_CODE,STORE_ID,MD_NAME获取库存量，金额
	 */
	protected JsStocks getJsStocks(String r3_code, String md_name, Long store_id) {

		JsStocks jsStocks = new JsStocks();
		jsStocks.setMd_name(md_name);
		jsStocks.setStore_id(Long.valueOf(store_id));
		jsStocks.getMap().put("r3_code", r3_code);
		List<JsStocks> list = super.getFacade().getJsStocksService().getJsStockForTotal(jsStocks);
		if (null != list && list.size() > 0) {
			jsStocks = list.get(0);
		}
		return jsStocks;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-07-15
	 * @desc 根据R3_CODE,MD_NAME,STORE_ID检查次客户/网点中是否已经初始化商品数据或者初始化库存
	 */
	protected String checkKhContainsMd(String r3_code, String md_name, Long store_id) {
		String result = "";
		if (StringUtils.isEmpty(r3_code) || StringUtils.isEmpty(md_name)) {
			return "";
		}
		KonkaR3Shop r3shop = new KonkaR3Shop();
		r3shop.setR3_code(r3_code);
		r3shop.setIs_del(0l);
		r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
		if (null != r3shop) {
			JBaseGoods goods = new JBaseGoods();
			goods.setGoods_name(md_name);
			goods.setC_id(r3shop.getId());
			goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
			if (null == goods) {// 商品数据中没有
				result = result + "请先添加" + md_name + "商品数据!\n";
			} else {// 商品数据中存在
				if (null != store_id) {// 如果选择了仓库，则判断仓库中是否存在此商品
					JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
					initStock.setC_id(r3shop.getId());
					initStock.setGoods_id(goods.getGoods_id());
					initStock.setStore_id(store_id);
					initStock = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(initStock);
					if (null == initStock) {
						result = result + "请先初始化商品" + md_name + "库存!\n";
					}
				}
			}
		}
		return "";
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-07-15
	 * @desc 根据c_id,goods_id,store_id检查次客户/网点中是否已经初始化商品数据或者初始化库存
	 */
	protected String checkKhContainsMd(Long c_id, Long goods_id, Long store_id) {
		String result = "";
		if (null == c_id || null == goods_id) {
			return "";
		}
		JBaseGoods goods1 = new JBaseGoods();
		goods1.setGoods_id(goods_id);
		List<JBaseGoods> goodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(goods1);
		if (goodsList == null || goodsList.size() < 1) {
			return "出现未知错误，请联系管理员！";
		}
		JBaseGoods goods = new JBaseGoods();
		goods.setGoods_id(goods_id);
		goods.setC_id(c_id);
		goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
		if (null == goods) {// 商品数据中没有
			result = result + "请先添加" + goodsList.get(0).getGoods_name() + "商品数据!" + "\\n";
		} else {// 商品数据中存在
			if (null != store_id) {// 如果选择了仓库，则判断仓库中是否存在此商品
				JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
				initStock.setC_id(c_id);
				initStock.setGoods_id(goods_id);
				initStock.setStore_id(store_id);
				initStock = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(initStock);
				if (null == initStock) {
					result = result + "请先初始化商品" + goodsList.get(0).getGoods_name() + "库存!" + "\\n";
				}
			}
		}
		return result;
	}

	/**
	 * @author Liang Houen
	 * @desc 根据型号，客户id，仓库id查询是否已存在商品数据及是否初始化库存
	 * @param c_id
	 * @param goods_id
	 * @param store_id
	 * @return
	 */
	protected String checkGoodsStock(String md_name,Long par_c_id, Long store_id,JBillDetails details,PeProdUser user) {
		String result = "";
		if (null == user || null == md_name || null == par_c_id) {
			return "error";
		}
		JBaseGoods goods = new JBaseGoods();
		goods.setGoods_name(md_name);
		goods.setC_id(user.getCust_id());
		goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
		if (null == goods) {// 商品数据中没有
			JBaseGoods goods1 = new JBaseGoods();
			goods1.setGoods_name(md_name);
			goods1.setC_id(par_c_id);
			goods1 = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods1);
			if(null!=goods1){
				JBaseType type = new JBaseType();
				type.setType_id(goods1.getGoods_unit());
				type = super.getFacade().getJBaseTypeService().getJBaseType(type);
				type.setType_id(null);
				type.setAdd_date(new Date());
				type.setC_id(user.getCust_id());
				Long type_id = super.getFacade().getJBaseTypeService().createJBaseType(type);
				//初始化商品数据
				goods1.setGoods_id(null);
				goods1.setC_id(user.getCust_id());
				goods1.setInit_count(details.getNum());
				goods1.setAdd_date(new Date());
				goods1.setGoods_unit(type_id);
				
				JBaseType type1 = new JBaseType();
				type1.setType_id(goods1.getGoods_type());
				type1 = super.getFacade().getJBaseTypeService().getJBaseType(type1);
				type1.setType_id(null);
				type1.setAdd_date(new Date());
				type1.setC_id(user.getCust_id());
				Long type_id1 = super.getFacade().getJBaseTypeService().createJBaseType(type1);
				goods1.setGoods_type(type_id1);
				
				Long goods_id = super.getFacade().getJBaseGoodsService().createJBaseGoods(goods1);
				
				//初始化库存
				JBaseGoodsInitStock initStock1 = new JBaseGoodsInitStock();
				initStock1.setC_id(user.getCust_id());
				initStock1.setGoods_id(goods_id);
				initStock1.setStore_id(store_id);
				initStock1.setInit_count(details.getNum());
				initStock1.setInit_date(new Date());
				initStock1.setInit_user(user.getId());
				initStock1.setInit_state(0);
				initStock1.setInit_desc("分销入库时自动初始化库存。");
				initStock1.setInit_money(details.getPrice());
				initStock1.setBuy_price(details.getPrice());
				initStock1.setSell_price(details.getPrice());
				super.getFacade().getJBaseGoodsInitStockService().createJBaseGoodsInitStock(initStock1);
			}
		} else {// 商品数据中存在
			if (null != store_id) {// 如果选择了仓库，则判断仓库中是否存在此商品
				JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
				initStock.setC_id(user.getCust_id());
				initStock.setGoods_id(goods.getGoods_id());
				initStock.setStore_id(store_id);
				initStock = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(initStock);
				if (null == initStock) {
					//初始化库存
					JBaseGoodsInitStock initStock1 = new JBaseGoodsInitStock();
					initStock1.setC_id(user.getCust_id());
					initStock1.setGoods_id(goods.getGoods_id());
					initStock1.setStore_id(store_id);
					initStock1.setInit_count(details.getNum());
					initStock1.setInit_date(new Date());
					initStock1.setInit_user(user.getId());
					initStock1.setInit_state(0);
					initStock1.setInit_desc("分销入库时自动初始化库存。");
					initStock1.setInit_money(details.getPrice());
					initStock1.setBuy_price(details.getPrice());
					initStock1.setSell_price(details.getPrice());
					
					super.getFacade().getJBaseGoodsInitStockService().createJBaseGoodsInitStock(initStock1);
				}
			}
		}
		return result;
	}
}
