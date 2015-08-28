package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.axis2.databinding.types.xsd.Decimal;

import com.ebiz.mmt.domain.JxcFifoStocks;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by wangkl
 * @date 2015-04-09 18:19:53
 */
public interface JxcFifoStocksService {

	Long createJxcFifoStocks(JxcFifoStocks t);

	int modifyJxcFifoStocks(JxcFifoStocks t);

	int removeJxcFifoStocks(JxcFifoStocks t);

	JxcFifoStocks getJxcFifoStocks(JxcFifoStocks t);

	List<JxcFifoStocks> getJxcFifoStocksList(JxcFifoStocks t);

	Long getJxcFifoStocksCount(JxcFifoStocks t);

	List<JxcFifoStocks> getJxcFifoStocksPaginatedList(JxcFifoStocks t);

	Long getJxcFifoStocksManagerDetailsCount(JxcFifoStocks t);
	List<JxcFifoStocks> getJxcFifoStocksManagerDetailsPaginatedList(JxcFifoStocks t) ;
	
    Long getJxcFifoStocksManagerAccountCount(JxcFifoStocks t);
	
	List<JxcFifoStocks> getJxcFifoStocksManagerAccountPaginatedList(JxcFifoStocks t) ;
	/**
	 * 进仓的方法
	 * @param r3_code   客户编码
	 * @param stock_in_batch 进货批次
	 * @param goods_model 产品型号
	 * @param single_price 产品价格
	 * @param order_num    产品数量
	 * @param order_date   产品时间
	 * @param stock_in_type 进栈类型 10、地采的进货 20、集采的进货 30、产品初始化 40、分销进货 50、库存盘盈 60、零售通退货 70、零售通数据无效 80、库存调拨 90、库存转仓 100、零售通数据导入负数
	 * @param seller_r3_code 送达方编码
	 */
	void stock_in(String r3_code, String stock_in_batch, String goods_model, BigDecimal single_price,
			int order_num, Date order_date, int stock_in_type, String seller_r3_code);
	
	/**
	 * @param stock_id 进货仓库
	 * @param goods_id 产品id
	 * @param single_price  产品价格
	 * @param order_num  产品数量
	 * @param order_date 操作时间
	 * @param stock_in_type 入仓类型
	 */
	void stock_in(Long stock_id,Long goods_id, BigDecimal single_price,int order_num, Date order_date, int stock_in_type);
	
	/**
	 * 出仓的方法
	 * @param r3_code   客户编码
	 * @param stock_in_batch 进货批次
	 * @param goods_model 产品型号
	 * @param single_price 产品价格
	 * @param order_num    产品数量
	 * @param order_date   产品时间
	 * @param stock_out_type 出栈类型 10、地采的进货 20、集采的进货 30、产品初始化 40、分销进货 50、库存盘盈 60、零售通退货 70、零售通数据无效 80、库存调拨 90、库存转仓 100、零售通数据导入负数
	 * @param seller_r3_code 送达方编码
	  */
	void stock_out(int stock_out_type, long goods_id, long store_id,
			long stock_out_num, Decimal stock_out_price);
	
	
	
	/**
	 * @param store_id 出货仓库
	 * @param goods_model 产品型号
	 * @param single_price  产品价格
	 * @param order_num  产品数量
	 * @param order_date 操作时间
	 * @param stock_in_type 出仓类型
	 */
	void stock_out(Long store_id, Long goods_id, BigDecimal price, int order_num, Date date, int stock_in_type);
	
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
	 */
	void stock_out(Long store_id, Long goods_id, String goods_model, Long num, BigDecimal single_price, String r3_code,
			Long ck_id,int stock_out_type,Long cxy_id);

	
	
	/**
	 * 地采的每日同步
	 */
	void StockInDcLastDay(String stock_in_batch);
	/**
	 * 地采的核对
	 */
	void StockInDcCheck(String stock_in_batch);

	
	
	
	/**
	 * 获取进货批次
	 * @return
	 */
	String getJxcFifoStockInBatch();

	/**
	 * 集采的每日同步
	 */
	void StockInJcLastDay(String stock_in_batch);
	/**
	 * 集采的核对
	 */
	void StockInJcCheck(String stock_in_batch);

	void stock_in(Long store_id, String goods_model, BigDecimal single_price, int order_num, Date order_date,
			int stock_in_type);

	void stock_out(Long store_id, String goods_name, BigDecimal single_price, int order_num, Date order_date,
			int stock_out_type);

	List<JxcFifoStocks> getJxcFifoStocksViewDetailsList(JxcFifoStocks entity);

	
	List<JxcFifoStocks> getJxcFifoStocksResultNumPrice(JxcFifoStocks entity);
	
	
}