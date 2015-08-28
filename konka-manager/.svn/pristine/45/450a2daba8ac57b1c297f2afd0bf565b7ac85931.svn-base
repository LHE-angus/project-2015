package com.ebiz.mmt.web.struts.touch;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.GlobalIpGeoLib;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.PshowOrder;
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
	 * @author Jiang,JiaYong
	 * @version 2013-09-13
	 * @desc 根据地区信息和商品ID取商品详细信息
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public KonkaBcompPd getKonkaBcompPdAllDataWithPindexAndGoodsId(Integer own_sys, Long goods_id, Long p_index,
	        String[] dept_id_array) {
		if (null == p_index || null == goods_id)
			return null;

		// 处理地区信息
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		// 查询需要的产品信息
		KonkaBcompPd entity = new KonkaBcompPd();
		entity.getRow().setCount(2);
		entity.setId(Long.valueOf(goods_id));
		entity.getMap().put("today", true);
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
		ecBcompPdRebates = getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(ecBcompPdRebates);
		entity.setEcBcompPdRebates(ecBcompPdRebates);

		// 查询价格
		List<EcGoodsPrice> ecGoodsPriceList = getEcGoodsPriceListWithPindexAndGoodsId(own_sys, entity.getId(),
		        p_index_array, dept_id_array);
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
	 * @author Tudp
	 * @version 2013-09-13
	 * @desc 根据地区信息和商品ID取商品详细信息
	 * @desc dept_id_array[总部ID,分公司ID,R3用户ID]只有3个值，此参数处理触网平台，其它平台可为空
	 */
	public KonkaBcompPd getKonkaBcompPdAllDataWithPindexAndGoodsId(String ctx,Integer own_sys, Long goods_id, Long p_index,
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
		KonkaBcompPd pd = new KonkaBcompPd();//获取r3库存
		pd.setId(new Long(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		int r3store_num=0;
		if(pd.getProd_type()!=null&&pd.getProd_type().intValue()==0){
		try{
			if(pd!=null){
				if (null != ecStocksList && 0 != ecStocksList.size()&&ecStocksList.get(0)!=null) {
					EcStocks s=(EcStocks)ecStocksList.get(0);
					Integer stock_type = new Integer(s.getMap().get("store_type").toString()); 
					if(stock_type.intValue()!=2){
						r3store_num=super.getStockCount(ctx,pd.getPd_sn()).intValue();
						r3store_num=ecStocksList.get(0).getStocks().intValue()+r3store_num; 
						EcStocks ecStocks = new EcStocks();
						ecStocks = ecStocksList.get(0);
						ecStocks.setStocks(new Long(ecStocks.getStocks().intValue() + r3store_num));
						entity.setEcStocks(ecStocks);
					}else if(stock_type.intValue()==2&&s.getStocks().intValue()<1&&ecStocksList.size()>1){
						r3store_num=ecStocksList.get(1).getStocks().intValue()+r3store_num; 
						EcStocks ecStocks = new EcStocks();
						ecStocks = ecStocksList.get(1);
						ecStocks.setStocks(new Long(ecStocks.getStocks().intValue() + r3store_num));
						entity.setEcStocks(ecStocks);
					}else{
						EcStocks ecStocks = new EcStocks();
						ecStocks = ecStocksList.get(0);
						entity.setEcStocks(ecStocks);
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}; 	
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
	 * @desc 根据地区编码查询指定类型指定数量的商品信息
	 * @desc 
	 *       label_of_cate-->关联表【EC_BCOMP_TYPE】0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6
	 *       -团购
	 * @desc own_sys:1-工卡，2-触网，3-会员
	 * @desc dept_sn_array[总部ID, 分公司ID, R3用户ID]只有3个值
	 */
	public List<KonkaBcompPd> getKonkaBcompPdWithPindexAndTypeAndCountList(Integer own_sys, Long p_index,
	        Integer label_of_cate, Integer count, String[] dept_sn_array) {
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
		konkaBcompPd.getMap().put("dept_sn_in", StringUtils.join(dept_sn_array, ","));
		konkaBcompPd.setOwn_sys(own_sys);
		if (2 == own_sys && null != dept_sn_array) { // 触网项目产品处理
			konkaBcompPd.getMap().put("goods_id_not_in_touch_by_deptids", dept_sn_array);
			konkaBcompPd.getMap().put("type", "1"); // 只查询屏蔽
		}
		konkaBcompPd.setLabel_of_cate(label_of_cate);
		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("is_upSelf", true);
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null == konkaBcompPdList || 0 == konkaBcompPdList.size())
			return null;

		// 处理价格
		for (KonkaBcompPd t : konkaBcompPdList) {
			// 多图处理，主图为第一个
			t.setPicArray(StringUtils.split(t.getMain_pic(), ","));
			t.setMain_pic(StringUtils.split(t.getMain_pic(), ",")[0]);

			List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsId(own_sys, t.getId(),
			        p_index_array, dept_sn_array);
			if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
				t.setEcGoodsPrice(ecGoodsPriceList.get(0));
			}
		}

		return konkaBcompPdList;
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
	        Integer label_of_cate, Integer count, Long dept_id, Long c_id ,Integer prod_types[]) {
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
		if(prod_types!=null){
			konkaBcompPd.getMap().put("prod_type_in", prod_types); 
			}
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
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
		ecGoodsPriceList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceWithGoodsIdAndPindexList(
		        ecGoodsPrice);
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
		ecGoodsPriceList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceWithGoodsIdAndPindexList(
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
	        String[] dept_id_array) {
		// 处理库存，type：0-全国总仓，1-区域大仓，2-分公司仓
		EcStocks entity = new EcStocks();
		entity.getRow().setCount(10);
		entity.setGoods_id(goods_id);
		entity.getMap().put("own_sys", new Integer(2));
		entity.getMap().put("pindex_type_1_in", new Long[] { p_index_array[0], p_index_array[1] });
		entity.getMap().put("pindex_type_2_in", new Long[] { p_index_array[0], p_index_array[1], p_index_array[2] });
		//entity.getMap().put("dept_id_array", dept_id_array);//限定分公司
		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksWithGoodsIdAndPindexList(entity);
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
			EcUser ecUser = (EcUser) session.getAttribute("ecUser");
			if (ecUser != null &&ecUser.getP_index()!=null && ecUser.getUser_type().intValue() == 2) {
				p_index = ecUser.getP_index().toString();
				if(p_index.length()>6){
					p_index=p_index.substring(0,6);
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
	 * @version 2013-09-13
	 * @desc 得到指定类型和指定数量的销量排行商品
	 * @desc dept_sn_array[总部ID,分公司ID,R3用户ID]只有3个值
	 */
	public List<KonkaBcompPd> getKonkaBcompTopList(Integer own_sys, String[] dept_sn_array, Long[] p_index_array,
	        Integer label_of_cate, Integer count, Boolean need_price) {
		// 查询需要的产品信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		konkaBcompPd.setOwn_sys(own_sys);
		konkaBcompPd.getMap().put("dept_sn_in", StringUtils.join(dept_sn_array, ",")); // 查询总部，所属
		// 分公司，R3客户自己添加的产品
		if (2 == own_sys && null != dept_sn_array) { // 触网项目产品处理
			konkaBcompPd.getMap().put("goods_id_not_in_touch_by_deptids", dept_sn_array);
			konkaBcompPd.getMap().put("type", "1"); // 只查询屏蔽
		}
		konkaBcompPd.getMap().put("order_by_sale_num_desc", true);
		konkaBcompPd.setLabel_of_cate(label_of_cate);
		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null != konkaBcompPdList && 0 != konkaBcompPdList.size()) {
			for (KonkaBcompPd konkaBcompPd2 : konkaBcompPdList) {
				// 处理图片列表
				String[] picArray = StringUtils.split(konkaBcompPd2.getMain_pic(), ",");
				konkaBcompPd2.setPicArray(picArray);
				konkaBcompPd2.setMain_pic(picArray[0]);

				if (need_price && null != p_index_array) {
					List<EcGoodsPrice> ecGoodsPriceList = this.getEcGoodsPriceListWithPindexAndGoodsId(own_sys,
					        konkaBcompPd2.getId(), p_index_array, dept_sn_array);
					if (null != ecGoodsPriceList && 0 != ecGoodsPriceList.size()) {
						konkaBcompPd2.setEcGoodsPrice(ecGoodsPriceList.get(0));
					}
				}
			}
		}
		return konkaBcompPdList;
	}

	/**
	 * @author Tudp
	 * @version 2013-11-19
	 * @desc 得到指定类型和指定数量的销量排行商品
	 * @desc dept_sn_array[总部ID,分公司ID,R3用户ID]只有3个值
	 */
	public List<KonkaBcompPd> getKonkaBcompTopList(Integer own_sys, Long[] p_index_array, Integer label_of_cate,
	        Integer count, Long dept_id, Long c_id) {
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
		konkaBcompPd.getMap().put("is_upSelf", true);
		konkaBcompPd.getMap().put("order_by_sale_num_desc", true);
		if (label_of_cate != null && label_of_cate.intValue() == 1) {
			konkaBcompPd.setIs_tj(new Integer(1));
		} else {
			konkaBcompPd.setLabel_of_cate(label_of_cate);
		}
		konkaBcompPd.getRow().setCount(count);
		konkaBcompPd.getMap().put("today", true);
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

	/**
	 * @author Pan,Gang
	 * @version 2013-12-30
	 * @desc 根据用户ID查询用户付款积分用来晋级
	 */

	public Long getIntegralByUserIdForJj(Long user_id) {

		String total_price = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserTotalScore(user_id);
		if (StringUtils.isBlank(total_price)) {
			return 0L;
		}
		return new Long(total_price);

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

	// 旧的剩余积分方法，查询state > 0 的销售额
	public Long getIntegralByUserId_old(Long user_id) {
		PshowOrder entity = new PshowOrder();
		// 所属系统：1-工卡，2-触网，3-会员
		entity.setOrder_from(1);
		entity.setOrder_user_id(user_id);
		// 订单状态：-30-已退货 -20-审核未通过 -10-已关闭 0-已预订
		// 10-已兑换 20-审核通过 30-下发处理 40-商家发货 50-客户已换货 60-确认收货
		entity.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });

		BigDecimal total_price = super.getFacade().getPshowOrderService().getTotalPriceByOrderUserId(entity);

		Long total_score = 0L;// 获得总积分
		Long used_score = 0L;// 已使用积分
		if (total_price.compareTo(new BigDecimal(10000)) == -1) {
			total_score = 0L;
		} else if (total_price.compareTo(new BigDecimal(50000)) == -1) {
			total_score = total_price.add(new BigDecimal(-10000)).multiply(new BigDecimal(1.2)).longValue();
		} else {
			total_score = 48000 + total_price.add(new BigDecimal(-50000)).multiply(new BigDecimal(1.5)).longValue();
		}

		EcUserScoreRec e = new EcUserScoreRec();
		e.setUser_id(user_id);
		List<EcUserScoreRec> ecUserScoreRecList = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecList(e);
		if (null != ecUserScoreRecList && ecUserScoreRecList.size() > 0) {
			for (EcUserScoreRec t : ecUserScoreRecList) {
				if (t.getOpr_type() == 1) {
					// 使用积分
					used_score += t.getScore().longValue();
				} else if (t.getOpr_type() == 0) {
					// 退货情况，返还积分
					used_score -= t.getScore().longValue();
				}
			}
		}

		return total_score - used_score;
	}

	protected EcUser checkUser(String username, String userpass) throws Exception {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			return null;
		}

		logger.info("User_name:{}, Pass_word:{}", username, userpass);

		EcUser user = new EcUser();
		user.setUser_name(username.trim());
		user.setIs_del(0);
		user.setPass_word(new DESPlus().encrypt(userpass));
		return getFacade().getEcUserService().getEcUser(user);
	}

	// private static EcUserService ecUserService;

	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {
	// ApplicationContext cxt = new
	// ClassPathXmlApplicationContext("spring-context.xml");
	// ecUserService = (EcUserService) cxt.getBean("ecUserServiceImpl");
	// }
	//
	// @Test
	// public void testGetData() throws Exception {
	// EcUser user = new EcUser();
	// user.setUser_name("kk4740");// K2233,k2233
	// user.setIs_del(0);
	// user.setPass_word(new DESPlus().encrypt("1"));
	// EcUser u = ecUserService.getEcUser(user);
	// //System.out.println(u.getUser_name());
	// }

	protected EcUser checkUser2(String username, String userpass) throws Exception {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			return null;
		}

		logger.info("User_name:{}, Pass_word:{}", username, userpass);

		EcUser user = new EcUser();
		user.setUser_name(username.trim());
		user.setIs_del(0);
		user.setPass_word(new DESPlus().encrypt(userpass));
		return getFacade().getEcUserService().getEcUser(user);
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
		//System.out.println("xml------------->" + xml2);
		String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
		//System.out.println("returnXml======>{}" + returnXml);
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
				// for (int i = 0; i < remarkList.size(); i++) {
				// if (remarkList.get(i) == null || remarkList.get(i) == "") {
				// continue;
				// }
				// sb.append(remarkList.get(i));
				// }
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
}