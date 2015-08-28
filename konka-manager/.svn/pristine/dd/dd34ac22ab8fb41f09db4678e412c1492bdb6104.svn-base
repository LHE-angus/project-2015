package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBcompPdDao;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,zhong
 * @version 2013-07-05 10:59
 */
@Service
public class KonkaBcompPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaBcompPd> implements KonkaBcompPdDao {

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public Long selectKonkaBcompPdWithDeptAndMdCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdWithDeptAndMdCount", t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaBcompPd> selectKonkaBcompPdWithDeptAndMdPaginatedList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdWithDeptAndMdPaginatedList", t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-05
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaBcompPd> selectKonkaBcompPdWidhDeptAndMdList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdWidhDeptAndMdList", t);
	}

	public Long selectKonkaBcompPdForEcPriceCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdForEcPriceCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaBcompPd> selectKonkaBcompPdForEcPricePaginatedList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdForEcPricePaginatedList", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public Long selectKonkaBcompPdForEcPriceAndSaleCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdForEcPriceAndSaleCount", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @date 2013-09-17
	 */
	public int updateKonkaBcompPdForViewCountOrSaleNum(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().update("updateKonkaBcompPdForViewCountOrSaleNum", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaBcompPd> selectKonkaBcompPdForEcPriceAndSalePaginatedList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdForEcPriceAndSalePaginatedList", t);
	}

	@Override
	public Long selectKonkaBcompPdWithDeptAndMdNewCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdWithDeptAndMdNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaBcompPd> selectKonkaBcompPdWithDeptAndMdNewPaginatedList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdWithDeptAndMdNewPaginatedList", t);
	}

	public Long selectKonkaBcompPdForIdCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdForIdCount", t);
	}

	public Long selectKonkaBcompPdWithDeptAndMdForCustCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdWithDeptAndMdForCustCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaBcompPd> selectKonkaBcompPdWithDeptAndMdForCustPaginatedList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdWithDeptAndMdForCustPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaBcompPd> selectKonkaBcompPdNewList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdNewList", t);
	}

	@Override
	public Long selectKonkaBcompPdForEcPriceAndSaleNewCount(KonkaBcompPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaBcompPdForEcPriceAndSaleNewCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaBcompPd> selectKonkaBcompPdForEcPriceAndSaleNewPaginatedList(KonkaBcompPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaBcompPdForEcPriceAndSaleNewPaginatedList", t);
	}

}
