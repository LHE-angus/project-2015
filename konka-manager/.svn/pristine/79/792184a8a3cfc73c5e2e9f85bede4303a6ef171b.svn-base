package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseGoodsDao;
import com.ebiz.mmt.dao.JBasePartnerDao;
import com.ebiz.mmt.dao.JSubSellRecDao;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.service.JBaseGoodsInitStockService;
import com.ebiz.mmt.service.JBaseGoodsService;
import com.ebiz.mmt.service.JBaseTypeService;
import com.ebiz.mmt.service.JBillDetailsService;
import com.ebiz.mmt.service.JBillService;
import com.ebiz.mmt.service.JStocksStackService;
import com.ebiz.mmt.service.JStocksVerifyService;
import com.ebiz.mmt.service.JSubSellRecService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-17 11:19:45
 */
@Service
public class JSubSellRecServiceImpl implements JSubSellRecService {

	@Resource
	private JSubSellRecDao jSubSellRecDao;

	@Resource
	private JBillService jBillService;
	
	@Resource
	private JBillDetailsService jBillDetailsService;

	@Resource
	JBasePartnerDao jBasePartnerDao;

	@Resource
	JBaseGoodsDao jBaseGoodsDao;

	@Resource
	JStocksStackService jStocksStackService;
	
	@Resource
	JStocksVerifyService JStocksVerifyService;
	
	@Resource
	JBaseGoodsService jBaseGoodsService;
	
	@Resource
	JBaseTypeService jBaseTypeService;
	
	@Resource
	JBaseGoodsInitStockService jBaseGoodsInitStockService;

	public Long createJSubSellRec(JSubSellRec t) {
		return this.jSubSellRecDao.insertEntity(t);
	}

	public JSubSellRec getJSubSellRec(JSubSellRec t) {
		return this.jSubSellRecDao.selectEntity(t);
	}

	public Long getJSubSellRecCount(JSubSellRec t) {
		return this.jSubSellRecDao.selectEntityCount(t);
	}

	public Long getJSubSellRecCountForFourWeek(JSubSellRec t) {
		return this.jSubSellRecDao.selectJSubSellRecCountForFourWeek(t);
	}

	public List<JSubSellRec> getJSubSellRecList(JSubSellRec t) {
		return this.jSubSellRecDao.selectEntityList(t);
	}

	public int modifyJSubSellRec(JSubSellRec t) {
		return this.jSubSellRecDao.updateEntity(t);
	}

	public int removeJSubSellRec(JSubSellRec t) {
		return this.jSubSellRecDao.deleteEntity(t);
	}

	public List<JSubSellRec> getJSubSellRecPaginatedList(JSubSellRec t) {
		return this.jSubSellRecDao.selectEntityPaginatedList(t);
	}

	public int modifyJSubSellRecAndAddCGJBill(JSubSellRec t, JBill jbill) {

		this.jBillService.createJBillAndDeatails(jbill);

		t.setBuy_bill_sn(jbill.getBill_sn());
		t.setConfirm_date(new Date());
		t.setStatus(1);
		int count = this.jSubSellRecDao.updateEntity(t);

		return count;
	}

	public List<JSubSellRec> getJSubSellRecPaginatedListOfDetails(JSubSellRec t) {
		return this.jSubSellRecDao.selectJSubSellRecPaginatedListOfDetails(t);
	}

	public Long getJSubSellRecCountOfDetails(JSubSellRec t) {
		return this.jSubSellRecDao.selectJSubSellRecCountOfDetails(t);
	}

	@Override
	public int modifyJSubAndStore(JSubSellRec t, JBill b, Long c_id, Long user_id) {
		//更新订单明细
		List<JBillDetails> jdetailsList = b.getjBillDetailsList();
		if(null!=jdetailsList){
			for(JBillDetails temp : jdetailsList){
				this.jBillDetailsService.modifyJBillDetails(temp);
				//检测是否有商品
				JBaseGoods goods1 = new JBaseGoods();
				goods1.setGoods_name(temp.getMap().get("goods_name").toString());
				goods1.setC_id(c_id);
				goods1 = this.jBaseGoodsService.getJBaseGoods(goods1);
				//未存在商品，则复制上级分销商商品信息自动初始化
				Long goods_id = null;
				if(null==goods1){
					JBaseGoods par_goods = new JBaseGoods();  //取供应商的商品数据
					par_goods.setGoods_name(temp.getMap().get("goods_name").toString());
					par_goods.setC_id(b.getC_id());
					par_goods = this.jBaseGoodsService.getJBaseGoods(par_goods);
					if(null!=par_goods){
						//复制商品单位
						JBaseType par_type = new JBaseType();  //供应商的商品单位
						par_type.setType_id(par_goods.getGoods_unit());
						par_type = this.jBaseTypeService.getJBaseType(par_type);
						
						//网点的商品单位
						JBaseType type = new JBaseType();
						type.setC_id(c_id);
						type.setType_name(par_type.getType_name());
						List<JBaseType> typelist = this.jBaseTypeService.getJBaseTypeList(type);
						if(null!=typelist){
							par_goods.setGoods_unit(typelist.get(0).getType_id());
						}else{
							par_type.setType_id(null);
							par_type.setC_id(c_id);
							par_type.setAdd_date(new Date());
							Long unit_id = this.jBaseTypeService.createJBaseType(par_type);
							par_goods.setGoods_unit(unit_id);
						}
						
						//复制商品类型
						par_type = new JBaseType();
						par_type.setType_id(par_goods.getGoods_type());
						par_type = this.jBaseTypeService.getJBaseType(par_type);
						type = new JBaseType();
						type.setC_id(c_id);
						type.setType_name(par_type.getType_name());
						List<JBaseType> typeLis = this.jBaseTypeService.getJBaseTypeList(type);
						if(null!=typeLis){
							par_goods.setGoods_type(typeLis.get(0).getType_id());
						}else{
							par_type.setType_id(null);
							par_type.setC_id(c_id);
							par_type.setAdd_date(new Date());
							Long type_id = this.jBaseTypeService.createJBaseType(par_type);
							par_goods.setGoods_type(type_id);
						}
						
						//以订单中的商品信息初始化商品数据
						par_goods.setGoods_id(null);
						par_goods.setC_id(c_id);
						par_goods.setInit_count(temp.getNum());
						par_goods.setAdd_date(new Date());
						goods_id = this.jBaseGoodsService.createJBaseGoods(par_goods);
						goods1 = par_goods;
					}
				}else{
					goods_id = goods1.getGoods_id();
				}
				
				//根据仓库id，初始化库存
				if (null != temp.getIn_store_id()) {
					JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
					initStock.setC_id(c_id);
					initStock.setGoods_id(goods_id);
					initStock.setStore_id(temp.getIn_store_id());
					initStock = this.jBaseGoodsInitStockService.getJBaseGoodsInitStock(initStock);
					if (null == initStock) {
						//初始化库存
						JBaseGoodsInitStock initStock1 = new JBaseGoodsInitStock();
						initStock1.setC_id(c_id);
						initStock1.setGoods_id(goods_id);
						initStock1.setStore_id(temp.getIn_store_id());
						initStock1.setInit_count(temp.getNum());
						initStock1.setInit_date(new Date());
						initStock1.setInit_user(user_id);
						initStock1.setInit_state(0);
						initStock1.setInit_desc("分销入库时自动初始化库存。");
						if(null!=goods1){
							initStock1.setInit_money(goods1.getSell_price());
							initStock1.setBuy_price(goods1.getSell_price());
							initStock1.setSell_price(goods1.getSell_price());
						}
						this.jBaseGoodsInitStockService.createJBaseGoodsInitStock(initStock1);
					}
				}
				
				//添加库存盘点记录
				/*JStocksVerify jsv = new JStocksVerify();
				jsv.setAdd_date(new Date());
				jsv.setBill_sn(b.getBill_sn());
				jsv.setC_id(c_id);
				jsv.setGoods_id(goods_id);
				jsv.setMemo("分销入库自动添加记录");
				jsv.setMoney(temp.getRec_money());
				jsv.setOpr_date(new Date());
				jsv.setStocks(temp.getNum());
				jsv.setStore_id(temp.getIn_store_id());
				jsv.setTrade_type(31);
				jsv.setType(1);
				jsv.setVer_money(temp.getRec_money());
				jsv.setVer_stocks(temp.getNum());
				this.JStocksVerifyService.createJStocksVerify(jsv);*/
			}
		}
		
		//网点确认
		return this.jSubSellRecDao.updateEntity(t);
	}
}
