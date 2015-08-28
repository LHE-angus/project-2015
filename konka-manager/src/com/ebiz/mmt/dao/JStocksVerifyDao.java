package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JStocksVerifyDao extends EntityDao<JStocksVerify> {

	/**
	 * @author Ren,zhong
	 * @date 2013-06-15
	 */
	JStocksVerify selectJStocksVerifyForLastedVerify(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表2014-11-28
	 */
	Long selectJStocksVerifyForInventoryCount(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表2014-11-28
	 */
	List<JStocksVerify> selectJStocksVerifyForInventoryList(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表2014-11-28
	 */
	List<JStocksVerify> selectJStocksVerifyForInventoryPaginatedList(JStocksVerify t);

	/**
	 * 为全部盘点和结转操作的取最新的商品列表，全部盘点的添加页面，和结转的源数据2014-12-01
	 */
	List<JStocksVerify> selectJStocksVerifyForInventoryFormList(JStocksVerify t);

	/**
	 * 系统调用 月初定时器结转客户库存 2014-12-02<br/>
	 * 系统自动结转的库存都是库实相符
	 * 
	 * @param t
	 * @return
	 */
	int AutoRunUpdateStatementForInsertJStocksVerify();

	/**
	 * 查询信息带商品和仓库名称
	 * 
	 * @param t
	 * @return
	 */
	List<JStocksVerify> selectJStocksVerifyWithStoreAndGoodsNamePaginatedList(JStocksVerify t);

	long selectJStocksVerifyForManagerCount(JStocksVerify entity);

	List<JStocksVerify> selectJStocksVerifyForManagerPaginatedList(JStocksVerify entity);
}
