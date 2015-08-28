package com.ebiz.mmt.service.impl;

import com.ebiz.mmt.dao.*;
import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.service.JxcFifoStocksService;
import org.apache.axis2.databinding.types.xsd.Decimal;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-09 18:19:53
 */
@Service
public class JxcFifoStocksServiceImpl implements JxcFifoStocksService {

	@Resource
	private JxcFifoStocksDao jxcFifoStocksDao;
	@Resource
	private KonkaSoFifoDao konkaSoFifoDao;
	@Resource
	private VOrgOfCustomerDao vorgofcustomerDao;
	@Resource
	private ChannelDataImportDao channelDataImportDao;
	@Resource
	private JBaseStoreDao jBaseStoreDao;
	@Resource
	private PePdModelDao pePdModelDao;
	@Resource
	private JxcFifoDataCheckDao jxcFifoDataCheckDao;
	@Resource
	private KonkaR3StoreDao konkaR3StoreDao;
	@Resource
	private PeProdUserDao peProdUserDao;
	@Resource
	private Facade facade;

	public Long createJxcFifoStocks(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.insertEntity(t);
	}

	public JxcFifoStocks getJxcFifoStocks(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.selectEntity(t);
	}

	public Long getJxcFifoStocksCount(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.selectEntityCount(t);
	}

	public List<JxcFifoStocks> getJxcFifoStocksList(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.selectEntityList(t);
	}

	public int modifyJxcFifoStocks(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.updateEntity(t);
	}

	public int removeJxcFifoStocks(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.deleteEntity(t);
	}

	public List<JxcFifoStocks> getJxcFifoStocksPaginatedList(JxcFifoStocks t) {
		return this.jxcFifoStocksDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getJxcFifoStocksManagerDetailsCount(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return this.jxcFifoStocksDao.selectJxcFifoStocksManagerDetailsCount(t);
	}

	@Override
	public List<JxcFifoStocks> getJxcFifoStocksManagerDetailsPaginatedList(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return this.jxcFifoStocksDao.selectJxcFifoStocksManagerDetailsPaginatedList(t);
	}

	@Override
	public Long getJxcFifoStocksManagerAccountCount(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return this.jxcFifoStocksDao.selectJxcFifoStocksManagerAccountCount(t);
	}

	@Override
	public List<JxcFifoStocks> getJxcFifoStocksManagerAccountPaginatedList(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return this.jxcFifoStocksDao.selectJxcFifoStocksManagerAccountPaginatedList(t);
	}

	// @Override
	// public Long stock_in(int stock_in_type, String stock_in_batch, long
	// goods_id, String R3_code, long stock_in_num,
	// Decimal stock_in_price) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/**
	 * @param store_id
	 *            进货仓库
	 * @param goods_model
	 *            产品型号
	 * @param single_price
	 *            产品价格
	 * @param order_num
	 *            产品数量
	 * @param order_date
	 *            操作时间
	 * @param stock_in_type
	 *            入仓类型
	 */
	@Override
	public void stock_in(Long store_id, String goods_model, BigDecimal single_price, int order_num, Date order_date,
			int stock_in_type) {
		// TODO Auto-generated method stub

		JBaseStore jbasestore = new JBaseStore();
		jbasestore.setStore_id(store_id);
		jbasestore = facade.getJBaseStoreService().getJBaseStoreAndDetails(jbasestore);
		String r3_code = (String) jbasestore.getMap().get("r3_code");
		String sale_r3_code = (String) jbasestore.getMap().get("sale_r3_code");
		String stock_in_batch = getJxcFifoStockInBatch();

		// 调用标准的方法
		stock_in(r3_code, stock_in_batch, goods_model, single_price, order_num, order_date, stock_in_type, sale_r3_code);

	}

	/**
	 * @param store_id
	 *            进货仓库
	 * @param single_price
	 *            产品价格
	 * @param order_num
	 *            产品数量
	 * @param order_date
	 *            操作时间
	 * @param stock_in_type
	 *            入仓类型
	 */
	@Override
	public void stock_in(Long store_id, Long goods_id, BigDecimal single_price, int order_num, Date order_date,
			int stock_in_type) {
		// TODO Auto-generated method stub

		JBaseStore jbasestore = new JBaseStore();
		jbasestore.setStore_id(store_id);
		jbasestore = facade.getJBaseStoreService().getJBaseStoreAndDetails(jbasestore);
		String r3_code = (String) jbasestore.getMap().get("r3_code");
		String sale_r3_code = (String) jbasestore.getMap().get("sale_r3_code");
		String stock_in_batch = getJxcFifoStockInBatch();

		String goods_model = "";

		if (stock_in_type == 60 || stock_in_type == 70) {// 零售退货或者无效化
			PePdModel pepdmodel = new PePdModel();
			pepdmodel.setPd_id(goods_id);
			pepdmodel.setIs_del(0);
			pepdmodel = pePdModelDao.selectEntity(pepdmodel);
			goods_model = pepdmodel.getMd_name();
		} else {
			JBaseGoods jbasegoods = new JBaseGoods();
			jbasegoods.setGoods_id(goods_id);
			jbasegoods = facade.getJBaseGoodsService().getJBaseGoods(jbasegoods);
			goods_model = jbasegoods.getGoods_name();
		}
		// 调用标准的方法
		stock_in(r3_code, stock_in_batch, goods_model, single_price, order_num, order_date, stock_in_type, sale_r3_code);

	}

	/**
	 * 进仓的方法
	 *
	 * @param r3_code
	 *            客户编码
	 * @param stock_in_batch
	 *            进货批次
	 * @param goods_model
	 *            产品型号
	 * @param single_price
	 *            产品价格
	 * @param order_num
	 *            产品数量
	 * @param order_date
	 *            产品时间
	 * @param stock_in_type
	 *            进栈类型 10、地采的进货 20、集采的进货 30、产品初始化 40、分销进货 50、库存盘盈 60、零售通退货
	 *            70、零售通数据无效 80、库存调拨 90、库存转仓 100、零售通数据导入负数
	 * @param seller_r3_code
	 *            送达方编码
	 */
	public void stock_in(String r3_code, String stock_in_batch, String goods_model, BigDecimal single_price,
			int order_num, Date order_date, int stock_in_type, String seller_r3_code) {
		try {
			// 拿到进货仓库
			JBaseStore jBaseStore = new JBaseStore();
			if (null != seller_r3_code) {
				jBaseStore.getMap().put("sale_r3_code", seller_r3_code);
			} else {
				jBaseStore.getMap().put("sale_r3_code", r3_code);
			}
			jBaseStore.getMap().put("r3_code", r3_code);
			jBaseStore.setIs_del(0);
			List<JBaseStore> storeList = jBaseStoreDao.selectJBaseStoreForR3List(jBaseStore);
			if (null != storeList && storeList.size() > 0) {
				jBaseStore = storeList.get(0);
			} else {
				jBaseStore = new JBaseStore();
				jBaseStore.getMap().put("r3_code", r3_code);
				jBaseStore.setIs_del(0);
				jBaseStore.setStore_name("总库");
				storeList = jBaseStoreDao.selectJBaseStoreForR3List(jBaseStore);
				if (null != storeList && storeList.size() > 0) {
					jBaseStore = storeList.get(0);
				}
			}

			// 根据客户编码拿到分公司，经办，业务员
			VOrgOfCustomer vorgofcustomer = new VOrgOfCustomer();
			vorgofcustomer.setR3_code(r3_code);
			vorgofcustomer = vorgofcustomerDao.selectEntity(vorgofcustomer);

			// 根据goods_model拿到goods_id
			PePdModel pepdmodel = new PePdModel();
			pepdmodel.setMd_name(goods_model);
			pepdmodel.setIs_del(0);
			pepdmodel = pePdModelDao.selectEntity(pepdmodel);

			// 进货
			JxcFifoStocks jxcfifostocks = new JxcFifoStocks();
			jxcfifostocks.setStock_carry_over(0);
			jxcfifostocks.setStock_in_batch(stock_in_batch);// 进货批次
			jxcfifostocks.setStock_in_date(order_date);// 进仓时间
			jxcfifostocks.setStock_in_price(single_price);// 进仓单价
			jxcfifostocks.setR3_code(r3_code);// 售达方编码
			// 如果找不到客户信息？就是售达方达方编码错误？
			if (null != vorgofcustomer) {
				if (null != vorgofcustomer.getDept_id())
					jxcfifostocks.setSubcomp_id(Long.valueOf(vorgofcustomer.getDept_id().toString()));// 分公司id
				if (null != vorgofcustomer.getDept_name())
					jxcfifostocks.setSubcomp_name(vorgofcustomer.getDept_name());// 分公司名称

				jxcfifostocks.setDept_id(vorgofcustomer.getL5_dept_id());// 部门
				jxcfifostocks.setDept_name(vorgofcustomer.getL5_dept_name());// 部门名称
				jxcfifostocks.setYwy_job_id(vorgofcustomer.getUser_id() + "");// 业务员id
				jxcfifostocks.setYwy_name(vorgofcustomer.getYwy_user_name());// 业务员姓名
				jxcfifostocks.setCustomer_name(vorgofcustomer.getCustomer_name());// 售达方名称
			}
			jxcfifostocks.setStock_in_type(stock_in_type);// 1、进货类型
			jxcfifostocks.setStock_in_opr_date(new Date());// 操作入库的时间
			jxcfifostocks.setStock_state(10);// 状态，待售
			jxcfifostocks.setGoods_model(goods_model);// 产品型号
			jxcfifostocks.setStock_in_store(jBaseStore.getStore_id());
			if (null != pepdmodel && null != pepdmodel.getPd_id()) {
				jxcfifostocks.setGoods_id(pepdmodel.getPd_id());// 产品id
			}
			if (order_num > 0) {
				JxcFifoStocks jxcfifostocks_30 = new JxcFifoStocks();
				jxcfifostocks_30.setR3_code(r3_code);
				jxcfifostocks_30.setGoods_model(goods_model);// 产品型号
				// jxcfifostocks_30.setStock_out_num(1l);
				jxcfifostocks_30.setStock_state(30);//
				jxcfifostocks_30.getRow().setCount(order_num);
				jxcfifostocks_30.setStock_in_store(jBaseStore.getStore_id());// 看下是不是有负卖的，有负卖先补上
				List<JxcFifoStocks> jxcfifostocksList = jxcFifoStocksDao.selectEntityList(jxcfifostocks_30);

				if (null == jxcfifostocksList || jxcfifostocksList.size() == 0) {

					// 没有负卖的东西
					for (int i = 0; i < order_num; i++) {
						jxcfifostocks.setStock_in_num(1l);
						jxcFifoStocksDao.insertEntity(jxcfifostocks);
					}
				} else if (order_num > jxcfifostocksList.size()) {
					for (JxcFifoStocks one : jxcfifostocksList) {
						one.setStock_in_num(1l);
						one.setStock_state(20);// 变成已经售出
						one.setStock_in_batch(stock_in_batch);// 进货批次
						one.setStock_in_date(order_date);// 进仓时间
						one.setStock_in_price(single_price);// 进仓单价

						one.setStock_in_type(stock_in_type);// 1、进货类型
						one.setStock_in_opr_date(new Date());// 操作入库的时间
						jxcFifoStocksDao.updateEntity(one);
					}
					for (int i = 0; i < order_num - jxcfifostocksList.size(); i++) {
						jxcfifostocks.setStock_in_num(1l);
						jxcFifoStocksDao.insertEntity(jxcfifostocks);
					}
				} else if (order_num == jxcfifostocksList.size()) {
					for (JxcFifoStocks one : jxcfifostocksList) {
						one.setStock_in_num(1l);
						one.setStock_state(20);// 变成已经售出
						one.setStock_in_batch(stock_in_batch);// 进货批次
						one.setStock_in_date(order_date);// 进仓时间
						one.setStock_in_price(single_price);// 进仓单价

						one.setStock_in_type(stock_in_type);// 1、进货类型
						one.setStock_in_opr_date(new Date());// 操作入库的时间
						jxcFifoStocksDao.updateEntity(one);
					}
				}

				// for (int i = 0; i < order_num; i++) {
				// JxcFifoStocks jxcfifostocks_30 = new JxcFifoStocks();
				// jxcfifostocks_30.setR3_code(r3_code);
				// jxcfifostocks_30.setGoods_model(goods_model);// 产品型号
				// jxcfifostocks_30.setStock_out_num(1l);
				// jxcfifostocks_30.setStock_state(30);//
				// jxcfifostocks_30.setStock_in_store(jBaseStore.getStore_id());//看下是不是有负卖的，有负卖先补上
				// jxcfifostocks_30.getRow().setCount(1);
				// List<JxcFifoStocks> jxcfifostocksList =
				// jxcFifoStocksDao.selectEntityList(jxcfifostocks_30);
				// if (null != jxcfifostocksList && jxcfifostocksList.size() >
				// 0) {
				// jxcfifostocks_30 = jxcfifostocksList.get(0);
				// jxcfifostocks_30.setStock_in_num(1l);
				// jxcfifostocks_30.setStock_state(20);//变成已经售出
				// jxcfifostocks_30.setStock_in_batch(stock_in_batch);// 进货批次
				// jxcfifostocks_30.setStock_in_date(order_date);// 进仓时间
				// jxcfifostocks_30.setStock_in_price(single_price);// 进仓单价
				//
				// jxcfifostocks_30.setStock_in_type(stock_in_type);// 1、进货类型
				// jxcfifostocks_30.setStock_in_opr_date(new Date());// 操作入库的时间
				// jxcFifoStocksDao.updateEntity(jxcfifostocks_30);
				// } else {
				//
				//
				// }
				// }
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

	/**
	 * 出仓的方法
	 *
	 * @param r3_code
	 *            客户编码
	 * @param goods_model
	 *            产品型号
	 * @param single_price
	 *            产品价格
	 * @param order_num
	 *            产品数量
	 * @param order_date
	 *            产品时间
	 * @param stock_out_type
	 *            出栈类型 10、地采的进货 20、集采的进货 30、产品初始化 40、分销进货 50、库存盘盈 60、零售通退货
	 *            70、零售通数据无效 80、库存调拨 90、库存转仓 100、零售通数据导入负数
	 * @param seller_r3_code
	 *            送达方编码
	 */
	public void stock_out(String r3_code, String goods_model, BigDecimal single_price, int order_num, Date order_date,
			int stock_out_type, String seller_r3_code) {
		try {
			// 拿到进货仓库
			JBaseStore jBaseStore = new JBaseStore();
			if (null != seller_r3_code) {
				jBaseStore.getMap().put("sale_r3_code", seller_r3_code);
			} else {
				jBaseStore.getMap().put("sale_r3_code", r3_code);
			}
			jBaseStore.getMap().put("r3_code", r3_code);
			jBaseStore.setIs_del(0);
			List<JBaseStore> storeList = jBaseStoreDao.selectJBaseStoreForR3List(jBaseStore);
			if (null != storeList && storeList.size() > 0) {
				jBaseStore = storeList.get(0);
			} else {
				jBaseStore = new JBaseStore();
				jBaseStore.getMap().put("r3_code", r3_code);
				jBaseStore.setIs_del(0);
				jBaseStore.setStore_name("总库");
				storeList = jBaseStoreDao.selectJBaseStoreForR3List(jBaseStore);
				if (null != storeList && storeList.size() > 0) {
					jBaseStore = storeList.get(0);
				}
			}

			// 根据客户编码拿到分公司，经办，业务员
			VOrgOfCustomer vorgofcustomer = new VOrgOfCustomer();
			vorgofcustomer.setR3_code(r3_code);
			vorgofcustomer = vorgofcustomerDao.selectEntity(vorgofcustomer);

			// 根据goods_model拿到goods_id
			PePdModel pepdmodel = new PePdModel();
			pepdmodel.setMd_name(goods_model);
			pepdmodel.setIs_del(0);
			pepdmodel = pePdModelDao.selectEntity(pepdmodel);

			if (order_num > 0) {
				JxcFifoStocks jxcfifostocks = new JxcFifoStocks();
				jxcfifostocks.setR3_code(r3_code);
				jxcfifostocks.setGoods_model(goods_model);// 产品型号
				jxcfifostocks.setStock_in_num(1l);
				jxcfifostocks.setStock_state(10);// 待出货状态
				jxcfifostocks.setStock_in_store(jBaseStore.getStore_id());// 出货的仓库必须是进货的仓库
				jxcfifostocks.getRow().setCount(order_num);// 查需要出仓数量的代售
				List<JxcFifoStocks> jxcfifostocksList = jxcFifoStocksDao.selectEntityList(jxcfifostocks);
				if (null == jxcfifostocksList || jxcfifostocksList.size() == 0) {

					// 一个待出售都没有，所有的记录都是负卖
					JxcFifoStocks stocks_out = new JxcFifoStocks();
					stocks_out.setStock_carry_over(0);
					stocks_out.setR3_code(r3_code);
					stocks_out.setCustomer_name(vorgofcustomer.getCustomer_name());
					stocks_out.setSubcomp_id((Long) vorgofcustomer.getDept_id());
					stocks_out.setSubcomp_name(vorgofcustomer.getDept_name());
					stocks_out.setGoods_model(goods_model);// 产品型号
					stocks_out.setStock_in_store(jBaseStore.getStore_id());// 负买的时候进货仓库就是出货的仓库
					stocks_out.setStock_in_price(single_price);// 负买的时候进货价是出货家，但是没有进货数量
					stocks_out.setStock_in_num(-1l);
					stocks_out.setStock_out_num(1l);
					stocks_out.setStock_out_price(single_price);
					stocks_out.setStock_out_date(order_date);
					stocks_out.setStock_out_type(stock_out_type);
					stocks_out.setStock_out_opr_date(new Date());
					stocks_out.setStock_out_store(jBaseStore.getStore_id());
					stocks_out.setStock_state(30);// 负卖售

					for (int i = 0; i < order_num; i++) {
						jxcFifoStocksDao.insertEntity(stocks_out);
					}

				} else if (order_num == jxcfifostocksList.size()) {
					// 待出售的刚好和需要出售的数据一致
					for (JxcFifoStocks one : jxcfifostocksList) {
						one.setStock_out_date(order_date);
						one.setStock_out_opr_date(new Date());
						one.setStock_out_num(1l);
						one.setStock_out_price(single_price);
						one.setStock_out_type(stock_out_type);// 地采的退货
						one.setStock_state(20);// 已售
						one.setStock_out_type(stock_out_type);
						one.setStock_out_store(jBaseStore.getStore_id());
						jxcFifoStocksDao.updateEntity(one);
					}
				} else if (order_num > jxcfifostocksList.size()) {
					/* 待售的不够，先将代售的售出，不够 的进行负卖 */
					for (JxcFifoStocks one : jxcfifostocksList) {
						one.setStock_out_date(order_date);
						one.setStock_out_opr_date(new Date());
						one.setStock_out_num(1l);
						one.setStock_out_price(single_price);
						one.setStock_out_type(stock_out_type);// 地采的退货
						one.setStock_state(20);// 已售
						one.setStock_out_type(stock_out_type);
						one.setStock_out_store(jBaseStore.getStore_id());
						jxcFifoStocksDao.updateEntity(one);
					}

					JxcFifoStocks stocks_out = new JxcFifoStocks();
					stocks_out.setStock_carry_over(0);
					stocks_out.setR3_code(r3_code);
					stocks_out.setCustomer_name(vorgofcustomer.getCustomer_name());
					stocks_out.setSubcomp_id((Long) vorgofcustomer.getDept_id());
					stocks_out.setSubcomp_name(vorgofcustomer.getDept_name());
					stocks_out.setGoods_model(goods_model);// 产品型号
					stocks_out.setStock_in_store(jBaseStore.getStore_id());// 负买的时候进货仓库就是出货的仓库
					stocks_out.setStock_in_price(single_price);// 负买的时候进货价是出货家，但是没有进货数量
					stocks_out.setStock_in_num(-1l);
					stocks_out.setStock_out_num(1l);
					stocks_out.setStock_out_price(single_price);
					stocks_out.setStock_out_date(order_date);
					stocks_out.setStock_out_type(stock_out_type);
					stocks_out.setStock_out_opr_date(new Date());
					stocks_out.setStock_out_store(jBaseStore.getStore_id());
					stocks_out.setStock_state(30);// 负卖售

					for (int i = 0; i < order_num - jxcfifostocksList.size(); i++) {
						jxcFifoStocksDao.insertEntity(stocks_out);
					}
				}
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

	@Override
	public void stock_out(int stock_out_type, long goods_id, long store_id, long stock_out_num, Decimal stock_out_price) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stock_out(Long store_id, String goods_name, BigDecimal single_price, int order_num, Date order_date,
			int stock_out_type) {
		// TODO Auto-generated method stub
		JBaseStore jbasestore = new JBaseStore();
		jbasestore.setStore_id(store_id);
		jbasestore = facade.getJBaseStoreService().getJBaseStoreAndDetails(jbasestore);
		String r3_code = (String) jbasestore.getMap().get("r3_code");
		String sale_r3_code = (String) jbasestore.getMap().get("sale_r3_code");
		String stock_in_batch = getJxcFifoStockInBatch();

		// JBaseGoods jbasegoods = new JBaseGoods();
		// jbasegoods.setGoods_id(goods_id);
		// jbasegoods = facade.getJBaseGoodsService().getJBaseGoods(jbasegoods);
		// String goods_model = jbasegoods.getGoods_name();

		stock_out(r3_code, goods_name, single_price, order_num, order_date, stock_out_type, sale_r3_code);
	}

	@Override
	public void stock_out(Long store_id, Long goods_id, BigDecimal single_price, int order_num, Date order_date,
			int stock_out_type) {
		// TODO Auto-generated method stub
		JBaseStore jbasestore = new JBaseStore();
		jbasestore.setStore_id(store_id);
		jbasestore = facade.getJBaseStoreService().getJBaseStoreAndDetails(jbasestore);
		String r3_code = (String) jbasestore.getMap().get("r3_code");
		String sale_r3_code = (String) jbasestore.getMap().get("sale_r3_code");
		String stock_in_batch = getJxcFifoStockInBatch();

		JBaseGoods jbasegoods = new JBaseGoods();
		jbasegoods.setGoods_id(goods_id);
		jbasegoods = facade.getJBaseGoodsService().getJBaseGoods(jbasegoods);
		String goods_model = jbasegoods.getGoods_name();

		stock_out(r3_code, goods_model, single_price, order_num, order_date, stock_out_type, sale_r3_code);
	}

	/**
	 * @param store_id
	 *            门店ID
	 * @param goods_id
	 *            产品id
	 * @param goods_model
	 *            产品型号
	 * @param num
	 *            数量
	 * @param single_price
	 *            价格
	 * @param r3_code
	 *            R3编码
	 * @param ck_id
	 *            出货仓
	 * @param stock_out_type
	 *            出仓类型
	 * @param cxy_id
	 *            促销员ID
	 */
	@Override
	public void stock_out(Long store_id, Long goods_id, String goods_model, Long num, BigDecimal single_price,
			String r3_code, Long ck_id, int stock_out_type, Long cxy_id) {
		// TODO Auto-generated method stub
		try {

			KonkaR3Store r3store = new KonkaR3Store();
			r3store.setStore_id(store_id);
			r3store = konkaR3StoreDao.selectEntity(r3store);
			String store_name = r3store.getStore_name();// 门店名称
			String cxy_job_id = cxy_id + "";// 促销员ID
			PeProdUser user = new PeProdUser();
			user.setId(cxy_id);
			user = peProdUserDao.selectEntity(user);

			JxcFifoStocks jxcfifostocks = new JxcFifoStocks();
			jxcfifostocks.setGoods_model(goods_model);
			// jxcfifostocks.setGoods_id(goods_id);
			jxcfifostocks.setStock_in_store(r3store.getCk_id());// 出货的必须是那个进货仓
			jxcfifostocks.setStock_in_num(1l);
			jxcfifostocks.setStock_state(10);// 状态，待售
			jxcfifostocks.getRow().setCount(num.intValue());
			List<JxcFifoStocks> jxcfifostockslist = jxcFifoStocksDao.selectEntityList(jxcfifostocks);

			if (null == jxcfifostockslist || jxcfifostockslist.size() == 0) {

				// 根据客户编码拿到分公司，经办，业务员
				VOrgOfCustomer vorgofcustomer = new VOrgOfCustomer();
				vorgofcustomer.setR3_code(r3_code);
				vorgofcustomer = vorgofcustomerDao.selectEntity(vorgofcustomer);
				for (int i = 0; i < num.intValue(); i++) {
					// 没有待售

					JxcFifoStocks stocks_out = new JxcFifoStocks();
					stocks_out.setStock_carry_over(0);
					stocks_out.setGoods_model(goods_model);
					stocks_out.setGoods_id(goods_id);
					stocks_out.setR3_code(r3_code);
					stocks_out.setCustomer_name(vorgofcustomer.getCustomer_name());
					stocks_out.setSubcomp_id((Long) vorgofcustomer.getDept_id());
					stocks_out.setSubcomp_name(vorgofcustomer.getDept_name());
					stocks_out.setCxy_job_id(cxy_job_id);
					stocks_out.setCxy_job_id(cxy_job_id);
					stocks_out.setStock_in_store(ck_id);// 负买的时候进货仓库就是出货的仓库
					stocks_out.setStock_in_price(single_price);// 负买的时候进货价是出货家，但是没有进货数量
					stocks_out.setStock_in_num(-1l);
					stocks_out.setStock_out_num(1l);
					stocks_out.setStock_out_price(single_price);
					stocks_out.setStock_out_date(new Date());
					stocks_out.setStock_out_type(stock_out_type);
					stocks_out.setStock_out_opr_date(new Date());
					stocks_out.setStock_out_store(ck_id);
					stocks_out.setStock_state(30);//负卖
					jxcFifoStocksDao.insertEntity(stocks_out);
				}

			} else if (num.intValue() > jxcfifostockslist.size()) {
				for (JxcFifoStocks one : jxcfifostockslist) {
					one.setStock_out_date(new Date());
					one.setStock_out_num(1l);
					one.setStock_out_opr_date(new Date());
					one.setStock_out_price(single_price);
					one.setStock_out_store(ck_id);
					one.setCxy_job_id(cxy_job_id);
					one.setStock_state(20);// 状态，已售
					one.setStock_out_type(stock_out_type);

					one.setCxy_nmae(user.getUser_name());
					jxcFifoStocksDao.updateEntity(one);
				}
				VOrgOfCustomer vorgofcustomer = new VOrgOfCustomer();
				vorgofcustomer.setR3_code(r3_code);
				vorgofcustomer = vorgofcustomerDao.selectEntity(vorgofcustomer);
				JxcFifoStocks stocks_out = new JxcFifoStocks();
				stocks_out.setStock_carry_over(0);
				stocks_out.setGoods_model(goods_model);
				stocks_out.setGoods_id(goods_id);
				stocks_out.setR3_code(r3_code);
				stocks_out.setCustomer_name(vorgofcustomer.getCustomer_name());
				stocks_out.setSubcomp_id((Long) vorgofcustomer.getDept_id());
				stocks_out.setSubcomp_name(vorgofcustomer.getDept_name());
				stocks_out.setCxy_job_id(cxy_job_id);
				stocks_out.setCxy_job_id(cxy_job_id);
				stocks_out.setStock_in_store(ck_id);// 负买的时候进货仓库就是出货的仓库
				stocks_out.setStock_in_price(single_price);// 负买的时候进货价是出货家，但是没有进货数量
				stocks_out.setStock_in_num(-1l);
				stocks_out.setStock_out_num(1l);
				stocks_out.setStock_out_price(single_price);
				stocks_out.setStock_out_date(new Date());
				stocks_out.setStock_out_type(stock_out_type);
				stocks_out.setStock_out_opr_date(new Date());
				stocks_out.setStock_out_store(ck_id);
				stocks_out.setStock_state(30);// 负卖
				for (int i = 0; i < num.intValue() - jxcfifostockslist.size(); i++) {
					jxcFifoStocksDao.insertEntity(stocks_out);
				}
			} else if (num.intValue() == jxcfifostockslist.size()) {
				for (JxcFifoStocks one : jxcfifostockslist) {
					one.setStock_out_date(new Date());
					one.setStock_out_num(1l);
					one.setStock_out_opr_date(new Date());
					one.setStock_out_price(single_price);
					one.setStock_out_store(ck_id);
					one.setCxy_job_id(cxy_job_id);
					one.setStock_state(20);// 状态，已售
					one.setStock_out_type(stock_out_type);

					one.setCxy_nmae(user.getUser_name());
					jxcFifoStocksDao.updateEntity(one);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getJxcFifoStockInBatch() {

		Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
		return "JHPC" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;

	}

	// 1、取前一天的地采数据并入仓
	public void StockInDcLastDay(String stock_in_batch) {
		// 1、取前一天的数据并入仓
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date_begin = format.format(cal.getTime());

		ChannelDataImport channelDataImport = new ChannelDataImport();
		channelDataImport.getMap().put("s_date", date_begin + " 00:00:00");
		channelDataImport.getMap().put("e_date", date_begin + " 23:59:59");
		channelDataImport.getMap().put("num_equal", true);
		channelDataImport.getMap().put("not_exists_kf", true);

		List<ChannelDataImport> channelDataImportList = channelDataImportDao
				.selectChannelDataImportListForFifo(channelDataImport);
		if (null != channelDataImportList && channelDataImportList.size() > 0) {
			for (ChannelDataImport entity : channelDataImportList) {
				int order_num = entity.getColumn_27().intValue();// 订单数量
				String r3_code = entity.getColumn_1();// 售达方编码
				String order_type = entity.getColumn_9();// 订单类型
				String goods_model = entity.getColumn_11();// 订单机型
				BigDecimal single_price = entity.getColumn_13();// 订单商品单价
				Date order_date = entity.getColumn_7();// 订单交货时间
				String seller_r3_code = entity.getColumn_5();// 送达方

				if (StringUtils.isNotBlank(order_type)) {
					if ("ZFRE".equals(order_type)) {
						// 退货出仓
						stock_out(r3_code, goods_model, single_price, order_num, order_date, 510, seller_r3_code);
					} else {
						// 进货压舱
						stock_in(r3_code, stock_in_batch, goods_model, single_price, order_num, order_date, 10,
								seller_r3_code);
					}

				}
			}
		}
	}

	/**
	 * 地采每日核对
	 */
	@Override
	public void StockInDcCheck(String stock_in_batch) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String date_begin = format.format(cal.getTime());
		// channelDataImport.getMap().put("s_date", date_begin + " 00:00:00)");
		// channelDataImport.getMap().put("e_date", date_begin + " 23:59:59)");

		JxcFifoDataCheck jxcFifoDataCheck = new JxcFifoDataCheck();
		jxcFifoDataCheck.setLink_type(1);// 地采
		List<JxcFifoDataCheck> jxcFifoDataCheckList = facade.getJxcFifoDataCheckService()
				.getJxcFifoDataCheckForStockList(jxcFifoDataCheck);
		if (null != jxcFifoDataCheckList && jxcFifoDataCheckList.size() > 0) {
			for (JxcFifoDataCheck entity : jxcFifoDataCheckList) {
				String r3_code = (String) entity.getMap().get("r3_code");
				String goods_model = (String) entity.getMap().get("goods_model");
				String seller_r3_code = (String) entity.getMap().get("seller_r3_code");
				Date order_date = (Date) entity.getMap().get("order_date");
				BigDecimal single_price = (BigDecimal) entity.getMap().get("single_price");
				int diff_num = (Integer) entity.getMap().get("diff_num");
				if (diff_num > 0) {
					if ("ZFRE".equals(entity.getMap().get("doc_type"))) {
						// 退货出仓
						stock_out(r3_code, goods_model, single_price, diff_num, order_date, 510, seller_r3_code);
					} else {
						// 退货出仓
						// 进货压舱
						stock_in(r3_code, stock_in_batch, goods_model, single_price, diff_num, order_date, 10,
								seller_r3_code);
					}
				}
			}
		}
		facade.getJxcFifoDataCheckService().removeJxcFifoDataCheck(jxcFifoDataCheck);
		facade.getJxcFifoDataCheckService().AutoRunUpdateFifoCheckByChannelDataImport();
	}

	@Override
	public void StockInJcLastDay(String stock_in_batch) {
		// TODO Auto-generated method stub
		// 1、取前一天的数据并入仓
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date_begin = format.format(cal.getTime());

		KonkaZles23ResultInfo resultInfo = new KonkaZles23ResultInfo();
		resultInfo.getMap().put("s_date", date_begin + " 00:00:00");
		resultInfo.getMap().put("e_date", date_begin + " 23:59:59");

		List<KonkaZles23ResultInfo> entitylist = facade.getKonkaZles23ResultInfoService()
				.getKonkaZles23ResultInfoForShockInList(resultInfo);

		if (null != entitylist && entitylist.size() > 0) {
			for (KonkaZles23ResultInfo entity : entitylist) {
				int order_num = entity.getLfimg().intValue();// 订单数量
				String r3_code = entity.getKunnr();// 售达方编码
				// String order_type = entity.getColumn_9();// 订单类型
				String goods_model = entity.getMatnr();// 订单机型
				BigDecimal single_price = new BigDecimal("0");
				if (null != entity.getMap().get("single_price")) {
					single_price = (BigDecimal) entity.getMap().get("single_price");
				}

				Date order_date = entity.getErdat();// 订单交货时间
				String seller_r3_code = entity.getKunnr();// 送达方
				stock_in(r3_code, stock_in_batch, goods_model, single_price, order_num, order_date, 20, seller_r3_code);
			}
		}

	}

	// 集采的核对功能
	@Override
	public void StockInJcCheck(String stock_in_batch) {
		// TODO Auto-generated method stub

		JxcFifoDataCheck jxcFifoDataCheck = new JxcFifoDataCheck();
		jxcFifoDataCheck.setLink_type(2);// 集采

		List<JxcFifoDataCheck> jxcFifoDataCheckList = facade.getJxcFifoDataCheckService()
				.getJxcFifoDataCheckForStockList(jxcFifoDataCheck);
		if (null != jxcFifoDataCheckList && jxcFifoDataCheckList.size() > 0) {
			for (JxcFifoDataCheck entity : jxcFifoDataCheckList) {
				String r3_code = (String) entity.getMap().get("r3_code");
				String goods_model = (String) entity.getMap().get("goods_model");
				String seller_r3_code = (String) entity.getMap().get("seller_r3_code");
				Date order_date = (Date) entity.getMap().get("order_date");
				BigDecimal single_price = (BigDecimal) entity.getMap().get("single_price");
				int diff_num = ((BigDecimal) entity.getMap().get("diff_num")).intValue();
				if (diff_num > 0) {
					stock_in(r3_code, stock_in_batch, goods_model, single_price, diff_num, order_date, 20,
							seller_r3_code);
				}
			}
		}

		facade.getJxcFifoDataCheckService().removeJxcFifoDataCheck(jxcFifoDataCheck);
		facade.getJxcFifoDataCheckService().AutoRunUpdateFifoCheckByZlesDataImport();
	}

	/**
	 * 该方法是为了查看详细进或者销的内容
	 */
	@Override
	public List<JxcFifoStocks> getJxcFifoStocksViewDetailsList(JxcFifoStocks entity) {
		// TODO Auto-generated method stub
		return jxcFifoStocksDao.selectJxcFifoStocksViewDetailsList(entity);
	}

	@Override
	public List<JxcFifoStocks> getJxcFifoStocksResultNumPrice(JxcFifoStocks entity) {
		return jxcFifoStocksDao.selectJxcFifoStocksResultNumPrice(entity);
	}
}
