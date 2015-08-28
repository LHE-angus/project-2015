package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JStocksVerify;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JStocksVerifyService {

	Long createJStocksVerify(JStocksVerify t);

	int modifyJStocksVerify(JStocksVerify t);

	int removeJStocksVerify(JStocksVerify t);

	JStocksVerify getJStocksVerify(JStocksVerify t);

	List<JStocksVerify> getJStocksVerifyList(JStocksVerify t);

	Long getJStocksVerifyCount(JStocksVerify t);

	List<JStocksVerify> getJStocksVerifyPaginatedList(JStocksVerify t);

	/**
	 * 查询信息带商品和仓库名称
	 * 
	 * @param t
	 * @return
	 */
	List<JStocksVerify> getJStocksVerifyWithStoreAndGoodsNamePaginatedList(JStocksVerify t);

	/**
	 * @author Ren,zhong
	 * @date 2013-06-15
	 */
	JStocksVerify getJStocksVerifyForLastedVerify(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表2014-11-28
	 */
	Long getJStocksVerifyForInventoryCount(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表2014-11-28
	 */
	List<JStocksVerify> getJStocksVerifyForInventoryList(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表2014-11-28
	 */
	List<JStocksVerify> getJStocksVerifyForInventoryPaginatedList(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表，全部盘点的添加页面，和结转的源数据2014-12-01
	 */
	List<JStocksVerify> getJStocksVerifyForInventoryFormList(JStocksVerify t);

	/**
	 * 系统调用 月初定时器结转客户库存 2014-12-02<br/>
	 * 系统自动结转的库存都是库实相符<br/>
	 * 返回1表示正常运行，0表示运行出错
	 * 
	 * @param t
	 * @return
	 */
	int AutoRunUpdateStatementForInsertJStocksVerify();

	long getJStocksVerifyForManagerCount(JStocksVerify entity);

	List<JStocksVerify> getJStocksVerifyForManagerPaginatedList(JStocksVerify entity);
}