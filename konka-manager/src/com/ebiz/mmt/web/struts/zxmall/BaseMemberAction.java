package com.ebiz.mmt.web.struts.zxmall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.GlobalIpGeoLib;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-10
 */
public class BaseMemberAction extends BaseAction {
	/**
	 * @author Tudp
	 * @version 2013-09-13
	 * @desc 根据地区信息和商品ID取商品详细信息
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public KonkaBcompPd getKonkaBcompPdAllDataWithPindexAndGoodsId(String ctx, Integer own_sys, Long goods_id,
	        Long p_index, String[] dept_id_array, Long dept_id, Long c_id) {
		if (null == p_index || null == goods_id) {
			return null;
		}
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
		} else if (own_sys.intValue() == 2) {// 触网用户
			// 根据客户ID(c_id),分公司ID（dept_id)屏蔽商品
			entity.getMap().put("own_sys_in_02", "1");
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("c_id", c_id);
		} else if (own_sys.intValue() == 5) {
			entity.getMap().put("own_sys_in_05", "1");
			entity.getMap().put("dept_id", dept_id);
			entity.setOwn_sys(own_sys);// 只取分公司工卡商品
		}
		List<KonkaBcompPd> entityList = getFacade().getKonkaBcompPdService().getKonkaBcompPdList(entity);
		if (null == entityList || 1 != entityList.size()) {
			return null;
		}
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

		// 查询商品佣金、返利
		EcBcompPdRebates ecBcompPdRebates = new EcBcompPdRebates();
		ecBcompPdRebates.setIs_del(0);
		ecBcompPdRebates.setGoods_id(entity.getId());
		ecBcompPdRebates.setOwn_sys(own_sys);
		if (own_sys.intValue() == 2) {
			ecBcompPdRebates.setDept_id(dept_id);
			ecBcompPdRebates.setC_id(c_id);
		} else if (own_sys.intValue() == 5) {// 分公司
			ecBcompPdRebates.setOwn_sys(1);
			ecBcompPdRebates.setDept_id(dept_id);
		}
		ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);
		entity.setEcBcompPdRebates(ecBcompPdRebates);

		// 查询价格
		List<EcGoodsPrice> ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsId(own_sys, entity.getId(),
		        p_index_array, dept_id, c_id);
		if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
			entity.setEcGoodsPrice(ecGoodsPriceList.get(0));
		}

		// 查询库存
		List<EcStocks> ecStocksList = getEcStocksListWithPindexAndGoodsId(own_sys, entity.getId(), p_index_array,
		        dept_id);
		KonkaBcompPd pd = new KonkaBcompPd();// 获取r3库存
		pd.setId(new Long(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		int r3store_num = 0;
		if (pd.getProd_type() != null && pd.getProd_type().intValue() == 0) {
			try {
				if (pd != null) {
					r3store_num = super.getStockCount(ctx, pd.getPd_sn()).intValue();
				}
			} catch (Exception e) {
			}
		}
		if (null != ecStocksList && 0 != ecStocksList.size()) {
			EcStocks ecStocks = new EcStocks();
			ecStocks = ecStocksList.get(0);
			ecStocks.setStocks(new Long(ecStocks.getStocks().intValue() + r3store_num));
			entity.setEcStocks(ecStocks);
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

	public KonkaBcompPd getKonkaBcompPdAllDataWithPindexAndGoodsIdNew(String ctx, Integer own_sys, Long goods_id,
	        Long p_index, String[] dept_id_array, Long dept_id, Long c_id, Integer plat_sys) {
		if (null == p_index || null == goods_id) {
			return null;
		}
		// 处理地区信息
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));
		// 查询需要的产品信息
		KonkaBcompPd entity = new KonkaBcompPd();
		entity.setState(1);
		entity.getRow().setCount(2);
		entity.setId(Long.valueOf(goods_id));
		// entity.getMap().put("today", true);
		// entity.getMap().put("is_upSelf", true);
		if (own_sys.intValue() == 2) {// 触网用户
			// 根据客户ID(c_id),分公司ID（dept_id)屏蔽商品
			if (plat_sys != null) {
				if (plat_sys.intValue() == 1) {
					entity.getMap().put("own_sys_new_in_02", "1");
				} else if (plat_sys.intValue() == 0) {
					entity.getMap().put("own_sys_new_in_02_for_zb", "1");
				}
			}
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("c_id", c_id);
		}
		List<KonkaBcompPd> entityList = getFacade().getKonkaBcompPdService().getKonkaBcompPdNewList(entity);
		if (null == entityList || 1 != entityList.size()) {
			return null;
		}
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

		// 查询商品佣金、返利
		EcBcompPdRebates ecBcompPdRebates = new EcBcompPdRebates();
		ecBcompPdRebates.setIs_del(0);
		ecBcompPdRebates.setGoods_id(entity.getId());
		ecBcompPdRebates.setOwn_sys(own_sys);
		if (own_sys.intValue() == 2) {
			ecBcompPdRebates.setDept_id(dept_id);
			ecBcompPdRebates.setC_id(c_id);
		} else if (own_sys.intValue() == 5) {// 分公司
			ecBcompPdRebates.setOwn_sys(1);
			ecBcompPdRebates.setDept_id(dept_id);
		}
		ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);
		entity.setEcBcompPdRebates(ecBcompPdRebates);

		// 查询价格
		List<EcGoodsPrice> ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsIdNew(own_sys, entity.getId(),
		        p_index_array, dept_id, c_id, plat_sys);
		if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
			entity.setEcGoodsPrice(ecGoodsPriceList.get(0));
		}

		// 查询库存
		List<EcStocks> ecStocksList = getEcStocksListWithPindexAndGoodsIdNew(own_sys, entity.getId(), p_index_array,
		        dept_id, plat_sys);
		KonkaBcompPd pd = new KonkaBcompPd();// 获取r3库存
		pd.setId(new Long(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);

		// if (pd.getProd_type() != null && pd.getProd_type().intValue() == 0) {
		// try {
		// if (pd != null) {
		// r3store_num = super.getStockCount(ctx, pd.getPd_sn()).intValue();
		// }
		// } catch (Exception e) {
		// }
		// }
		if (null != ecStocksList && 0 != ecStocksList.size()) {
			EcStocks ecStocks = new EcStocks();
			ecStocks = ecStocksList.get(0);

			// BigDecimal total_stocks = (BigDecimal)
			// ecStocks.getMap().get("total_stocks");
			// Long num = 0L;
			// if (total_stocks != null) {
			// num = total_stocks.longValue();
			// }
			// logger.info("num---->" + num);
			// ecStocks.setStocks(num);
			entity.setEcStocks(ecStocks);
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
		} else if (own_sys.intValue() == 5) {
			konkaBcompPd.getMap().put("own_sys_in_05", "1");
			konkaBcompPd.getMap().put("dept_id", dept_id);
			konkaBcompPd.setOwn_sys(own_sys);// 只取分公司工卡商品
		}
		if (label_of_cate != null && label_of_cate.intValue() == 99) {
			konkaBcompPd.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3, 7 });
		} else {
			konkaBcompPd.setLabel_of_cate(label_of_cate);
		}
		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("is_upSelf", true);
		if (prod_types != null) {
			konkaBcompPd.getMap().put("prod_type_in", prod_types);
		} else {// 特殊处理荔枝抢购prod_type=8，不出现在抢购里面
			konkaBcompPd.getMap().put("prod_type_not_eq", true);
		}
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null == konkaBcompPdList || 0 == konkaBcompPdList.size()) {
			return null;
		}
		// 处理价格
		for (KonkaBcompPd t : konkaBcompPdList) {// 多图处理，主图为第一个
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

	public List<KonkaBcompPd> getKonkaBcompPdWithPindexAndTypeAndCountNewList(Integer own_sys, Long p_index,
	        Integer label_of_cate, Integer count, Long dept_id, Long c_id, Integer prod_types[], Integer plat_sys) {
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
		konkaBcompPd.setState(1);// 正常
		if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			if (plat_sys != null) {
				if (plat_sys.intValue() == 1) {
					konkaBcompPd.getMap().put("own_sys_new_in_02", "1");
					konkaBcompPd.getMap().put("dept_id", dept_id);
					konkaBcompPd.getMap().put("c_id", c_id);
				} else if (plat_sys.intValue() == 0) {
					konkaBcompPd.getMap().put("own_sys_new_in_02_for_zb", "1");
					konkaBcompPd.getMap().put("dept_id", dept_id);
					konkaBcompPd.getMap().put("c_id", c_id);
				}
			}
		}
		if (label_of_cate != null && label_of_cate.intValue() == 99) {
			konkaBcompPd.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3, 7 });
		} else {
			konkaBcompPd.setLabel_of_cate(label_of_cate);
		}
		konkaBcompPd.getRow().setCount(count);
		// konkaBcompPd.getMap().put("today", true);
		// konkaBcompPd.getMap().put("is_upSelf", true);
		if (prod_types != null) {
			konkaBcompPd.getMap().put("prod_type_in", prod_types);
		} else {// 特殊处理荔枝抢购prod_type=8，不出现在抢购里面
			konkaBcompPd.getMap().put("prod_type_not_eq", true);
		}
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdNewList(
		        konkaBcompPd);
		if (null == konkaBcompPdList || 0 == konkaBcompPdList.size()) {
			return null;
		}
		// 处理价格
		for (KonkaBcompPd t : konkaBcompPdList) {// 多图处理，主图为第一个
			t.setPicArray(StringUtils.split(t.getMain_pic(), ","));
			t.setMain_pic(StringUtils.split(t.getMain_pic(), ",")[0]);
			List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsIdNew(own_sys, t.getId(),
			        p_index_array, dept_id, c_id, plat_sys);
			if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
				t.setEcGoodsPrice(ecGoodsPriceList.get(0));
			}
		}
		return konkaBcompPdList;
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
		} else if (own_sys.intValue() == 5) {
			ecGoodsPrice.getMap().put("own_sys_in_05", "1");
			ecGoodsPrice.getMap().put("dept_id", dept_id);
		}
		ecGoodsPrice.getMap().put("pindex_type_2_in", new Long[] { p_index_array[1], p_index_array[2] });
		ecGoodsPrice.getMap().put("pindex_type_3_in",
		        new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		ecGoodsPriceList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceWithGoodsIdAndPindexList(
		        ecGoodsPrice);
		return ecGoodsPriceList;
	}

	public List<EcGoodsPrice> getEcGoodsPriceListWithPindexAndGoodsIdNew(Integer own_sys, Long goods_id,
	        Long[] p_index_array, Long dept_id, Long c_id, Integer plat_sys) {
		List<EcGoodsPrice> ecGoodsPriceList = new ArrayList<EcGoodsPrice>();
		// 处理价格 type:0-全国价格，1-区域价格，2-分公司价格，3-地市区域价格
		EcGoodsPrice ecGoodsPrice = new EcGoodsPrice();
		ecGoodsPrice.getRow().setCount(20);
		ecGoodsPrice.setGoods_id(goods_id);
		if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id) ,分公司ID（dept_id)屏蔽商品
			if (plat_sys != null) {
				if (plat_sys.intValue() == 1) {
					ecGoodsPrice.getMap().put("plat_sys_eq_1", "1");
					ecGoodsPrice.getMap().put("own_sys_in_02", "1");
					ecGoodsPrice.getMap().put("dept_id", dept_id);
					EcGroup eg = new EcGroup();
					eg.setId(dept_id);
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null && eg.getLink_dept_id() != null) {
						ecGoodsPrice.getMap().put("dept_id_new", eg.getLink_dept_id());
					}
					ecGoodsPrice.getMap().put("c_id", c_id);
				} else if (plat_sys.intValue() == 0) {
					ecGoodsPrice.getMap().put("plat_sys_eq_0", "1");
					ecGoodsPrice.getMap().put("own_sys_in_02", "1");
					ecGoodsPrice.getMap().put("dept_id", dept_id);
					EcGroup eg = new EcGroup();
					eg.setId(dept_id);
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null && eg.getLink_dept_id() != null) {
						ecGoodsPrice.getMap().put("dept_id_new", eg.getLink_dept_id());
					}
				}

			}

			// ecGoodsPrice.getMap().put("c_id", c_id);
		}
		ecGoodsPrice.getMap().put("pindex_type_2_in", new Long[] { p_index_array[1], p_index_array[2] });
		ecGoodsPrice.getMap().put("pindex_type_3_in",
		        new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		ecGoodsPriceList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceWithGoodsIdAndPindexNewList(
		        ecGoodsPrice);
		return ecGoodsPriceList;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 * @desc own_sys:1-工卡,2-触网,3-会员
	 * @desc p_index_array[省、市、县、镇]数组，镇可以为空
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public List<EcStocks> getEcStocksListWithPindexAndGoodsId(Integer own_sys, Long goods_id, Long[] p_index_array,
	        Long dept_id) {
		// 处理库存，type：0-全国总仓，1-区域大仓，2-分公司仓
		EcStocks entity = new EcStocks();
		entity.getRow().setCount(10);
		entity.setGoods_id(goods_id);
		entity.getMap().put("own_sys", own_sys);
		if (own_sys.intValue() == 5) {
			entity.getMap().put("own_sys", new Integer(1));
			entity.getMap().put("dept_id", dept_id);
		}
		entity.getMap().put("pindex_type_1_in", new Long[] { p_index_array[0], p_index_array[1] });
		entity.getMap().put("pindex_type_2_in", new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksWithGoodsIdAndPindexList(entity);
		return entityList;
	}

	public List<EcStocks> getEcStocksListWithPindexAndGoodsIdNew(Integer own_sys, Long goods_id, Long[] p_index_array,
	        Long dept_id, Integer plat_sys) {
		// 处理库存，type：0-全国总仓，1-区域大仓，2-分公司仓
		EcStocks entity = new EcStocks();
		entity.getRow().setCount(10);
		entity.setGoods_id(goods_id);
		if (plat_sys.intValue() == 0) {
			entity.getMap().put("plat_sys_eq_0", "1");
		} else if (plat_sys.intValue() == 1) {
			entity.getMap().put("plat_sys_eq_1", "1");
		}
		entity.getMap().put("own_sys", own_sys);
		entity.getMap().put("dept_id", dept_id);
		entity.getMap().put("pindex_type_1_in", new Long[] { p_index_array[0], p_index_array[1] });
		entity.getMap().put("pindex_type_2_in", new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksWithGoodsIdAndPindexNewList(
		        entity);
		return entityList;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 * @desc 根据Request得到Pindex值
	 */
	public String getPIndexByRequest(HttpServletRequest request) {
		// 获取请求地区
		HttpSession session = request.getSession();
		String p_index = request.getParameter("p_index");
		if (StringUtils.isEmpty(p_index) || !GenericValidator.isLong(p_index)) {
			p_index = (String) session.getAttribute("p_index");
		}
		if (StringUtils.isEmpty(p_index) || p_index.length() != 6) {
			EcUser ecUser = (EcUser) session.getAttribute("zxmall");
			if (ecUser != null && ecUser.getP_index() != null && ecUser.getUser_type().intValue() == 2) {
				p_index = ecUser.getP_index().toString();
				if (p_index.length() > 6) {
					p_index = p_index.substring(0, 6);
				}
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
				try {
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
				} catch (Exception e) {
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
	 * @author Tudp
	 * @version 2013-11-19
	 * @desc 得到指定类型和指定数量的销量排行商品
	 * @desc dept_sn_array[总部ID,分公司ID,R3用户ID]只有3个值
	 */
	public List<KonkaBcompPd> getKonkaBcompTopList(Integer own_sys, Long[] p_index_array, Integer label_of_cate,
	        Integer count, Long dept_id, Long c_id, Integer prod_types[]) {
		// 查询需要的产品信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		if (own_sys.intValue() == 1) {// 根据用户类型（工卡、触网） 取不同系统商品
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		} else if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			konkaBcompPd.getMap().put("own_sys_in_02", "1");
			konkaBcompPd.getMap().put("dept_id", dept_id);
			konkaBcompPd.getMap().put("c_id", c_id);
		} else if (own_sys.intValue() == 5) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			konkaBcompPd.getMap().put("own_sys_in_05", "1");
			konkaBcompPd.getMap().put("dept_id", dept_id);
			konkaBcompPd.setOwn_sys(own_sys);// 只取分公司工卡商品
		}
		konkaBcompPd.getMap().put("is_upSelf", true);
		konkaBcompPd.getMap().put("order_by_sale_num_desc", true);
		if (label_of_cate != null && label_of_cate.intValue() == 1) {
			konkaBcompPd.setIs_tj(new Integer(1));
		} else {
			konkaBcompPd.setLabel_of_cate(label_of_cate);
		}
		if (prod_types != null) {
			konkaBcompPd.getMap().put("prod_type_in", prod_types);
		}

		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("labe_of_cat_not_eq_5", true);
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null != konkaBcompPdList && 0 != konkaBcompPdList.size()) {
			for (KonkaBcompPd konkaBcompPd2 : konkaBcompPdList) {
				// 处理图片列表
				String[] picArray = StringUtils.split(konkaBcompPd2.getMain_pic(), ",");
				konkaBcompPd2.setPicArray(picArray);
				konkaBcompPd2.setMain_pic(picArray[0]);
				if (null != p_index_array) {
					List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsId(own_sys,
					        konkaBcompPd2.getId(), p_index_array, dept_id, c_id);
					if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
						konkaBcompPd2.setEcGoodsPrice(ecGoodsPriceList.get(0));
					}
				}
			}
		}
		return konkaBcompPdList;
	}

	public List<KonkaBcompPd> getKonkaBcompTopNewList(Integer own_sys, Long[] p_index_array, Integer label_of_cate,
	        Integer count, Long dept_id, Long c_id, Integer prod_types[], Integer plat_sys) {
		// 查询需要的产品信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			if (plat_sys.intValue() == 1) {
				konkaBcompPd.getMap().put("own_sys_new_in_02", "1");
				konkaBcompPd.getMap().put("dept_id", dept_id);
				konkaBcompPd.getMap().put("c_id", c_id);
			} else if (plat_sys.intValue() == 0) {
				konkaBcompPd.getMap().put("own_sys_new_in_02_for_zb", "1");
				konkaBcompPd.getMap().put("dept_id", dept_id);
				konkaBcompPd.getMap().put("c_id", c_id);
			}
		}
		// konkaBcompPd.getMap().put("is_upSelf", true);
		konkaBcompPd.getMap().put("order_by_sale_num_desc", true);
		if (label_of_cate != null && label_of_cate.intValue() == 1) {
			konkaBcompPd.setIs_tj(new Integer(1));
		} else {
			konkaBcompPd.setLabel_of_cate(label_of_cate);
		}
		if (prod_types != null) {
			konkaBcompPd.getMap().put("prod_type_in", prod_types);
		}

		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.setState(1);
		// konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("labe_of_cat_not_eq_5", true);
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdNewList(
		        konkaBcompPd);
		if (null != konkaBcompPdList && 0 != konkaBcompPdList.size()) {
			for (KonkaBcompPd konkaBcompPd2 : konkaBcompPdList) {
				// 处理图片列表
				String[] picArray = StringUtils.split(konkaBcompPd2.getMain_pic(), ",");
				konkaBcompPd2.setPicArray(picArray);
				konkaBcompPd2.setMain_pic(picArray[0]);
				if (null != p_index_array) {
					List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsIdNew(own_sys,
					        konkaBcompPd2.getId(), p_index_array, dept_id, c_id, plat_sys);
					if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
						konkaBcompPd2.setEcGoodsPrice(ecGoodsPriceList.get(0));
					}
				}
			}
		}
		return konkaBcompPdList;
	}

	/**
	 * 
	 * @param own_sys
	 * @param p_index_array
	 * @param label_of_cate
	 * @param count
	 * @param dept_id
	 * @param c_id
	 * @param prod_types为限时抢购
	 *            ，特别处理
	 * @return
	 */
	public List<KonkaBcompPd> getKonkaBcompTopListForQg(Integer own_sys, Long[] p_index_array, Integer label_of_cate,
	        Integer count, Long dept_id, Long c_id, Integer prod_types[]) {
		// 查询需要的产品信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		if (own_sys.intValue() == 1) {// 根据用户类型（工卡、触网） 取不同系统商品
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		} else if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			// ,分公司ID（dept_id)屏蔽商品
			konkaBcompPd.getMap().put("own_sys_in_02", "1");
			konkaBcompPd.getMap().put("dept_id", dept_id);
			konkaBcompPd.getMap().put("c_id", c_id);
		} else if (own_sys.intValue() == 5) {// 触网用户
			// 根据客户ID(c_id),分公司ID（dept_id)屏蔽商品
			konkaBcompPd.getMap().put("own_sys_in_05", "1");
			konkaBcompPd.getMap().put("dept_id", dept_id);
			konkaBcompPd.setOwn_sys(own_sys);// 只取分公司工卡商品
		}
		konkaBcompPd.getMap().put("is_upSelf", true);
		konkaBcompPd.getMap().put("order_by_sale_num_desc", true);
		if (label_of_cate != null && label_of_cate.intValue() == 1) {
			konkaBcompPd.setIs_tj(new Integer(1));
		} else {
			konkaBcompPd.setLabel_of_cate(label_of_cate);
		}
		if (prod_types != null) {
			konkaBcompPd.getMap().put("prod_type_in", prod_types);
		}
		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
		// konkaBcompPd.getMap().put("labe_of_cat_not_eq_5", true);
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null != konkaBcompPdList && 0 != konkaBcompPdList.size()) {
			for (KonkaBcompPd konkaBcompPd2 : konkaBcompPdList) {
				// 处理图片列表
				String[] picArray = StringUtils.split(konkaBcompPd2.getMain_pic(), ",");
				konkaBcompPd2.setPicArray(picArray);
				konkaBcompPd2.setMain_pic(picArray[0]);
				if (null != p_index_array) {
					List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsId(own_sys,
					        konkaBcompPd2.getId(), p_index_array, dept_id, c_id);
					if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
						konkaBcompPd2.setEcGoodsPrice(ecGoodsPriceList.get(0));
					}
				}
			}
		}
		return konkaBcompPdList;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-12
	 * @desc 根据用户ID查询用户当前积分
	 */

	public Long getIntegralByUserId(Long user_id) {
		String total_price = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserTotalScore(user_id);
		if (StringUtils.isBlank(total_price)) {
			return 0L;
		}
		return new Long(total_price);
	}

	public Long[] getPindexArrayByPindex(Long p_index) {
		if (null == p_index) {
			return null;
		}
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

	protected EcUser checkUser(String username, String userpass) throws Exception {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			return null;
		}
		logger.info("User_name:{}, Pass_word:{}", username, userpass);

		EcUser user = new EcUser();
		EcUser user1;
		user.setUser_name(username.trim());
		user.setIs_del(0);
		user.setIs_act(0);
		/**
		 * 0-审核通过 1-待完善资料 2-待审核 3-审核不通过
		 */
		user.setPass_word(new DESPlus().encrypt(userpass));
		user1 = getFacade().getEcUserService().getEcUser(user);

		// 如果没有找到的话，找工卡验证
		if (user1 == null || user1.getId() == null) {
			user = new EcUser();
			user.setUser_name(null);
			user.setCard_no(username);
			user.setPass_word(new DESPlus().encrypt(userpass));
			user1 = getFacade().getEcUserService().getEcUser(user);
			if (user1 == null) {
				user = new EcUser();
				user.setUser_name(null);
				user.setCard_no(username);
				user.setPay_pwd(new DESPlus().encrypt(userpass));
				user1 = getFacade().getEcUserService().getEcUser(user);
			}
		}

		// 如果没有找到的话，找移动电话验证
		if (user1 == null || user1.getId() == null) {
			user = new EcUser();
			user.setUser_name(null);
			user.setLink_phone(username);
			user.setCard_no(null);
			user.setPass_word(new DESPlus().encrypt(userpass));
			user1 = getFacade().getEcUserService().getEcUser(user);
			if (user1 == null) {
				user = new EcUser();
				user.setUser_name(null);
				user.setLink_phone(username);
				user.setPay_pwd(new DESPlus().encrypt(userpass));
				user1 = getFacade().getEcUserService().getEcUser(user);
			}
		}

		if (user1 != null && user1.getCust_id() != null) {
			KonkaR3Shop r3shop = new KonkaR3Shop();
			r3shop.setId(user1.getCust_id());

			r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
			if (r3shop != null && r3shop.getWeb_type() != null) {
				user1.getMap().put("web_type", r3shop.getWeb_type());
			}
		}
		return user1;
	}

	public String orderState(EcOrderExpressInfo ec) {
		StringBuffer oper = new StringBuffer("{\"result\":");
		SfOrderService sf = new SfOrderService();
		String sxddmxOpName = "sfexpressService";
		MailnoQuery mq = new MailnoQuery();
		mq.setTracking_type("1");
		mq.setMethod_type("1");
		mq.setTracking_number(ec.getLogistic_sn().trim());
		String xml2 = mq.toXml();
		String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
		if (returnXml.equals("查询超时")) {
			oper.append("\"" + "查询超时" + "\"");
			oper.append("}");
		}
		try {
			Document doc = DocumentHelper.parseText(returnXml);
			Element rootElt = doc.getRootElement();
			Iterator<Element> iter = rootElt.elementIterator("Body");
			List<String> remarkList = new ArrayList<String>();
			while (iter.hasNext()) {
				Element em = iter.next();
				Iterator<Element> iter2 = em.elementIterator("RouteResponse");
				while (iter2.hasNext()) {
					Element em2 = iter2.next();
					Iterator<Element> iter3 = em2.elementIterator("Route");
					while (iter3.hasNext()) {
						Element em3 = iter3.next();
						remarkList.add(em3.attributeValue("remark"));
					}
				}
			}
			String sb = "";
			if (null != remarkList && remarkList.size() > 0) {
				sb = remarkList.get(remarkList.size() - 1);
			} else {
				sb = "暂时没有数据!";
			}
			oper.append("\"" + sb + "\"");
			oper.append("}");
		} catch (Exception e) {
			oper.append("\"" + "查询超时" + "\"");
			oper.append("}");
		}
		return oper.toString();
	}

	/**
	 * 
	 * 判断是否符合购买条件map.flg 1不能提交订单，2需增加费用
	 */
	public List<HashMap> getEcReule(Integer own_sys, Facade facade, Long goods_id, Integer num, String p_index,
	        EcUser ecUser) {
		EcRule ecRule = new EcRule();
		ecRule.setOwn_sys(own_sys);
		ecRule.getMap().put("goods_id", goods_id);
		ecRule.setInfo_state(new Integer(1));
		ecRule.setIs_del(new Integer(0));
		if (own_sys.intValue() == 2) {
			ecRule.setDept_id(ecUser.getDept_id());
		}
		int flg = 0;
		List<EcRule> list = facade.getEcRuleService().getEcRuleForGoodsList(ecRule);
		List<HashMap> rMaplist = new ArrayList<HashMap>();
		if (list != null) {
			EcRule entity = new EcRule();
			for (int i = 0; i < list.size(); i++) {
				entity = list.get(i);
				HashMap rMap = new HashMap();
				if (entity.getRule_type() != null && entity.getRule_type().intValue() == 1) {// rule_type
					// 1
					// 限购数量
					// 每个用户限购，如果每一个月限购则需没一个月设置一次限购时间
					if (entity.getRule_num_max() != null) {
						PshowOrdeDetails t = new PshowOrdeDetails();
						t.setPd_id(goods_id);
						t.getMap().put("user_id", ecUser.getId());
						t.getMap().put("add_date_start", entity.getRule_start_date());
						t.getMap().put("add_date_end", entity.getRule_end_date());
						Long baynum = super.getFacade().getPshowOrdeDetailsService()
						        .getPshowOrdeDetailsNumByGoodsIdCount(t);
						if (num.intValue() > entity.getRule_num_max()) {
							flg = 1;
							String rule_msg = entity.getRule_msg();
							rMap.put("flg", "" + flg);
							rMap.put("id", entity.getId());
							rMap.put("rule_title", entity.getRule_title());
							rMap.put("rule_msg", rule_msg);
							rMap.put("error", "");
						} else {
							if (baynum.intValue() + num.intValue() > entity.getRule_num_max()) {
								flg = 1;
								String error = "您已经购买过" + baynum.intValue() + "件该产品。";
								String rule_msg = entity.getRule_msg();
								rMap.put("id", entity.getId());
								rMap.put("rule_title", entity.getRule_title());
								rMap.put("flg", "" + flg);
								rMap.put("rule_msg", rule_msg);
								rMap.put("error", error);
							}
						}
					}
				}
				// 允许区域之外增加费用、设置允许购买区域和购买需增加费用
				if (entity.getRule_type() != null && entity.getRule_type().intValue() == 4) {// rule_type
					// 4
					// 增加费用
					// RULE_PRICE允许购买区域之外需增加费用RULE_AREA_ALLOW
					boolean is_allow = false;
					if (entity.getRule_area_allow() != null && !"".equals(entity.getRule_area_allow())) {
						String[] area_allows = entity.getRule_area_allow().split(",");
						if (area_allows != null && area_allows.length > 0) {
							for (int x = 0; x < area_allows.length; x++) {// 比较p_index是否在允许购买区域之内
								if (p_index.substring(0, 2).equals(area_allows[x].substring(0, 2))
								        && new Long(p_index).intValue() >= new Long(area_allows[x]).intValue()) {
									is_allow = true;
								}
							}
						}
					}
					// 不在允许购买区域之内 需增加费用，如果没有启用费用则不能购买
					if (!is_allow) {
						if (entity.getIs_price() != null && entity.getIs_price().intValue() == 1
						        && entity.getRule_price() != null) { // 不在允许购买区域，如果增加费用则可以购买
							flg = 2;
							rMap.put("flg", "" + flg);
							rMap.put("id", entity.getId());
							rMap.put("rule_title", entity.getRule_title());
							rMap.put("rule_msg", entity.getRule_msg());
							rMap.put("rule_price", entity.getRule_price());
							rMap.put("error", "");
						} else {
							flg = 1;
							rMap.put("flg", "" + flg);
							rMap.put("id", entity.getId());
							rMap.put("rule_title", entity.getRule_title());
							rMap.put("rule_msg", entity.getRule_msg());
							rMap.put("rule_price", entity.getRule_price());
							rMap.put("error", "");
						}
					}
				}
				if (flg != 0) {
					rMaplist.add(rMap);
				}
			}
		}
		return rMaplist;
	}
}