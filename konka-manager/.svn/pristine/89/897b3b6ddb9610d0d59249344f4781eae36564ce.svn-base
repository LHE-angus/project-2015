package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGoodsPriceDao;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGoodsPriceDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcGoodsPrice> implements EcGoodsPriceDao {
	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 */
	@SuppressWarnings("unchecked")
	public List<EcGoodsPrice> selectEcGoodsPriceWithGoodsIdAndPindexList(EcGoodsPrice t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectEcGoodsPriceWithGoodsIdAndPindexList", t);
	}

	public Long selectEcGoodsPriceForDeptNameAndPdNameCount(EcGoodsPrice t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectEcGoodsPriceForDeptNameAndPdNameCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcGoodsPrice> selectEcGoodsPriceForDeptNameAndPdNameList(EcGoodsPrice t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcGoodsPriceForDeptNameAndPdNameList", t);
	}

	public Long selectEcGoodsPriceAndAreaCount(EcGoodsPrice t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectEcGoodsPriceAndAreaCount", t);
	}

	@SuppressWarnings("unchecked")
	public EcGoodsPrice selectEcGoodsPriceForCoustomerPrice(EcGoodsPrice t) {
		return (EcGoodsPrice) this.getSqlMapClientTemplate().queryForObject("selectEcGoodsPriceForCoustomerPrice", t);
	}

	@Override
	public Long selectEcGoodsPriceForDeptNameAndPdNameNewCount(EcGoodsPrice t) {
		return (Long) this.getSqlMapClientTemplate()
		        .queryForObject("selectEcGoodsPriceForDeptNameAndPdNameNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcGoodsPrice> selectEcGoodsPriceForDeptNameAndPdNameNewList(EcGoodsPrice t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcGoodsPriceForDeptNameAndPdNameNewList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EcGoodsPrice> selectEcGoodsPriceWithGoodsIdAndPindexNewList(EcGoodsPrice t) {
		return this.getSqlMapClientTemplate().queryForList("selectEcGoodsPriceWithGoodsIdAndPindexNewList", t);
	}

}
