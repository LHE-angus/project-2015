package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseGoodsDao;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBaseGoodsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JBaseGoods> implements JBaseGoodsDao {

	/**
	 * @author Ren,zhong
	 * @date 2013-06-17
	 */
	public Long selectJBaseGoodsWithStockCount(JBaseGoods t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBaseGoodsWithStockCount", t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-17
	 */
	@SuppressWarnings("unchecked")
	public List<JBaseGoods> selectJBaseGoodsWithStockPaginatedList(JBaseGoods t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseGoodsWithStockPaginatedList", t);
	}

	/**
	 * @author Hu,hao
	 * @date 2014-03-05
	 */
	public JBaseGoods selectJBaseGoodsForNum(JBaseGoods t) {
		return (JBaseGoods) super.getSqlMapClientTemplate().queryForObject("selectJBaseGoodsForNum", t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-04-09
	 */
	public JBaseGoods selectJBaseGoodsForComeOutNum(JBaseGoods t) {
		return (JBaseGoods) super.getSqlMapClientTemplate().queryForObject("selectJBaseGoodsForComeOutNum", t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-07-09
	 */
	public JBaseGoods selectJBaseGoodsForNumNew(JBaseGoods t) {
		return (JBaseGoods) super.getSqlMapClientTemplate().queryForObject("selectJBaseGoodsForNumNew", t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-07-09
	 */
	public JBaseGoods selectJBaseGoodsForComeOutNumNew(JBaseGoods t) {
		return (JBaseGoods) super.getSqlMapClientTemplate().queryForObject("selectJBaseGoodsForComeOutNumNew", t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-07-27
	 */
	public List<JBaseGoods> selectJBaseGoodsWithOutInitList(JBaseGoods t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseGoodsWithOutInitList", t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-08-05
	 */
	public List<JBaseGoods> selectJBaseGoodsForComeOutNumWithMoney(JBaseGoods t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseGoodsForComeOutNumWithMoney", t);
	}
	
	/**
	 * @author Chen,Xiaoqi
	 * @date 2015-04-01
	 */
	public List<JBaseGoods> selectJBaseGoodsForComeOutNumWithMoney2(JBaseGoods t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseGoodsForComeOutNumWithMoney2", t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-08-19
	 */
	public List<JBaseGoods> selectJBaseGoodsForComeOutNumWithMoneyDetail(JBaseGoods t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBaseGoodsForComeOutNumWithMoneyDetail", t);
	}

}
