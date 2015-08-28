package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBillDao;
import com.ebiz.mmt.dao.JBillDetailsDao;
import com.ebiz.mmt.dao.JStocksDao;
import com.ebiz.mmt.dao.JStocksStackDao;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JStocks;
import com.ebiz.mmt.domain.JStocksStack;
import com.ebiz.mmt.service.JStocksStackService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-18 09:43:03
 */
@Service
public class JStocksStackServiceImpl implements JStocksStackService {

	@Resource
	private JStocksStackDao jStocksStackDao;

	@Resource
	private JStocksDao jStocksDao;

	@Resource
	private JBillDao jBillDao;

	@Resource
	private JBillDetailsDao jBillDetailsDao;

	public Long createJStocksStack(JStocksStack t) {
		return this.jStocksStackDao.insertEntity(t);
	}

	public JStocksStack getJStocksStack(JStocksStack t) {
		return this.jStocksStackDao.selectEntity(t);
	}

	public Long getJStocksStackCount(JStocksStack t) {
		return this.jStocksStackDao.selectEntityCount(t);
	}

	public List<JStocksStack> getJStocksStackList(JStocksStack t) {
		return this.jStocksStackDao.selectEntityList(t);
	}

	public int modifyJStocksStack(JStocksStack t) {
		return this.jStocksStackDao.updateEntity(t);
	}

	public int removeJStocksStack(JStocksStack t) {
		return this.jStocksStackDao.deleteEntity(t);
	}

	public List<JStocksStack> getJStocksStackPaginatedList(JStocksStack t) {
		return this.jStocksStackDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-18
	 * @desc 商品出栈
	 * @param cust_id
	 *            :客户ID， store_id:仓库ID，goods_id:商品ID，bill_sn：单据编号
	 */
	public void pop(Long cust_id, Long store_id, Long goods_id, String bill_sn) {
		JStocksStack stack = new JStocksStack();
		stack.setGoods_id(goods_id);
		stack.setStore_id(store_id);
		stack.setC_id(cust_id);
		stack.setStatus(0);
		stack.getMap().put("order_by_stack_id_asc", true);
		List<JStocksStack> stackList = this.jStocksStackDao.selectEntityList(stack);

		if (null != stackList && stackList.size() > 0) {
			// 商品出栈
			JStocksStack stack_pop = stackList.get(0);
			stack_pop.setStatus(1);
			stack_pop.setBill_id_pop(bill_sn);
			this.jStocksStackDao.updateEntity(stack_pop);

			JStocks stocks = new JStocks();
			stocks.setGoods_id(goods_id);
			stocks.setStore_id(store_id);

			List<JStocks> stocksList = this.jStocksDao.selectEntityList(stocks);
			// 库存量变更
			if (null != stocksList && stocksList.size() > 0) {
				stocks = stocksList.get(0);
				stocks.setStocks(stocks.getStocks() - 1L); // 库存总量减1
				stocks.setTotal_cost(stocks.getTotal_cost().subtract(stack_pop.getGoods_cost())); // 库存商品总成本减去商品成本

				this.jStocksDao.updateEntity(stocks);
			}
		}
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-18
	 * @desc 商品入栈
	 * @param cust_id
	 *            :客户ID，
	 *            store_id:仓库ID，goods_id:商品ID，goods_cost：商品成本/价格，bill_sn：单据编号
	 */
	public void push(Long cust_id, Long store_id, Long goods_id, BigDecimal goods_cost, String bill_sn) {
		// 商品入栈
		JStocksStack stack = new JStocksStack();
		stack.setGoods_id(goods_id);
		stack.setStore_id(store_id);
		stack.setGoods_cost(goods_cost);
		stack.setBill_id_push(bill_sn);
		stack.setC_id(cust_id);
		stack.setStatus(0);
		this.jStocksStackDao.insertEntity(stack);

		JStocks stocks = new JStocks();
		stocks.setGoods_id(goods_id);
		stocks.setStore_id(store_id);

		List<JStocks> stocksList = this.jStocksDao.selectEntityList(stocks);
		// 库存量变更
		if (null != stocksList && stocksList.size() > 0) {
			stocks = stocksList.get(0);
			stocks.setStocks(stocks.getStocks() + 1L); // 库存总量加1
			if(stocks.getTotal_cost() == null){
				stocks.setTotal_cost(new BigDecimal("0").add(goods_cost)); // 库存商品总成本加上商品成本
			} else {
				stocks.setTotal_cost(stocks.getTotal_cost().add(goods_cost)); // 库存商品总成本加上商品成本
			}
			this.jStocksDao.updateEntity(stocks);
		} else {
			stocks.setStocks(1L);
			stocks.setTotal_cost(goods_cost);
			this.jStocksDao.insertEntity(stocks);
		}
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-18
	 * @desc 删除栈中单据
	 * @param cust_id
	 *            :客户ID， bill_sn：单据编号
	 */
	public void delete(Long cust_id, String bill_sn) {

		JBill bill = new JBill();
		bill.setBill_sn(bill_sn);
		List<JBill> billList = this.jBillDao.selectEntityList(bill);

		if (null != billList && billList.size() > 0) {
			Integer bill_type = billList.get(0).getBill_type();
			Long bill_id = billList.get(0).getBill_id();

			JStocksStack stack = new JStocksStack();

			// bill_type：单据类型（ 10-进货单，11-进货退货单，20-销售单，21-销售退货单）
			// 进货单，销售退货单
			if (bill_type.equals(10) || bill_type.equals(21)) {
				stack.setC_id(cust_id);
				stack.setBill_id_push(bill_sn);
				this.jStocksStackDao.deleteEntity(stack);

				// 库存量变更
				JBillDetails details = new JBillDetails();
				details.setBill_id(bill_id);
				List<JBillDetails> detailsList = this.jBillDetailsDao.selectEntityList(details);
				if (null != detailsList && detailsList.size() > 0) {
					for (JBillDetails temp : detailsList) {
						JStocks stocks = new JStocks();
						stocks.setGoods_id(temp.getGoods_id());
						stocks.setStore_id(temp.getStore_id());

						List<JStocks> stocksList = this.jStocksDao.selectEntityList(stocks);
						if (null != stocksList && stocksList.size() > 0) {
							stocks = stocksList.get(0);
							stocks.setStocks(stocks.getStocks() - temp.getNum());
							stocks.setTotal_cost(stocks.getTotal_cost().subtract(temp.getMoney()));
							this.jStocksDao.updateEntity(stocks);
						}
					}
				}
			}
			// 销售单，进货退货单
			if (bill_type.equals(20) || bill_type.equals(11)) {
				JBillDetails details = new JBillDetails();
				details.setBill_id(bill_id);
				List<JBillDetails> detailsList = this.jBillDetailsDao.selectEntityList(details);
				if (null != detailsList && detailsList.size() > 0) {
					for (JBillDetails temp : detailsList) {
						JStocks stocks = new JStocks();
						stocks.setGoods_id(temp.getGoods_id());
						stocks.setStore_id(temp.getStore_id());
						List<JStocks> stocksList = this.jStocksDao.selectEntityList(stocks);

						// 获取入库时的商品成本
						JStocksStack stack_cost = new JStocksStack();
						stack_cost.setBill_id_pop(bill_sn);
						stack_cost.setGoods_id(temp.getGoods_id());
						stack_cost.setStore_id(temp.getStore_id());
						stack_cost.setC_id(cust_id);
						List<JStocksStack> stackList = this.jStocksStackDao.selectEntityList(stack_cost);

						if (stackList.size() > 0)
							stack = stackList.get(0);

						if (null != stocksList && stocksList.size() > 0) {
							stocks = stocksList.get(0);
							stocks.setStocks(stocks.getStocks() + temp.getNum());
							if (stack.getGoods_cost() != null)
								stocks.setTotal_cost(stocks.getTotal_cost().add(
										stack.getGoods_cost().multiply(new BigDecimal(temp.getNum().toString()))));// 计算成本价
							this.jStocksDao.updateEntity(stocks);
						} else {
							stocks.setStocks(temp.getNum());
							if (stack.getGoods_cost() != null)
								stocks.setTotal_cost(stack.getGoods_cost().multiply(
										new BigDecimal(temp.getNum().toString()))); // 计算成本价
							this.jStocksDao.insertEntity(stocks);
						}
					}
				}
				// 清空销售单编号和状态
				JStocksStack stack_temp = new JStocksStack();
				stack_temp.setBill_id_pop(bill_sn);
				stack_temp.setStatus(0);
				this.jStocksStackDao.updateJStocksPopStack(stack_temp);
			}
		}
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-20
	 */
	public List<JStocksStack> getJStocksStackForSskcResultList(JStocksStack t) {
		return this.jStocksStackDao.selectJStocksStackForSskcResultList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-27
	 * @desc 销售退货，根据关联的销售单
	 * @param cust_id
	 *            :客户ID， bill_sn：销售单号，store_id：仓库ID，goods_id:商品ID
	 */
	public void rejected(Long c_id, Long store_id, Long goods_id, String bill_sn) {
		JStocksStack stack = new JStocksStack();
		stack.setBill_id_pop(bill_sn);
		stack.setGoods_id(goods_id);
		stack.setStore_id(store_id);
		stack.setStatus(1);
		stack.getMap().put("order_by_stack_id_asc", true);
		List<JStocksStack> stackList = this.jStocksStackDao.selectEntityList(stack);

		if (null != stackList && stackList.size() > 0) {
			// 清空销售单编号和状态
			JStocksStack th_stack = stackList.get(0);
			th_stack.setStatus(0);
			this.jStocksStackDao.updateJStocksPopStack(th_stack);

			// 更新实时库存
			JStocks stocks = new JStocks();
			stocks.setGoods_id(goods_id);
			stocks.setStore_id(store_id);
			List<JStocks> stocksList = this.jStocksDao.selectEntityList(stocks);

			if (null != stocksList && stocksList.size() > 0) {
				stocks = stocksList.get(0);
				stocks.setStocks(stocks.getStocks() + 1L);
				stocks.setTotal_cost(stocks.getTotal_cost().add(th_stack.getGoods_cost()));// 计算成本价
				this.jStocksDao.updateEntity(stocks);
			} else {
				stocks.setStocks(1L);
				stocks.setTotal_cost(th_stack.getGoods_cost()); // 计算成本价
				this.jStocksDao.insertEntity(stocks);
			}
		}
	}
}
