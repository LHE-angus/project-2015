package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Ren,zhong
 * @version 2013-07-05 10:59
 */
public interface KonkaBcompPdDao extends EntityDao<KonkaBcompPd> {

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public Long selectKonkaBcompPdWithDeptAndMdCount(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public List<KonkaBcompPd> selectKonkaBcompPdWithDeptAndMdPaginatedList(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public Long selectKonkaBcompPdWithDeptAndMdNewCount(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public List<KonkaBcompPd> selectKonkaBcompPdWithDeptAndMdNewPaginatedList(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-05
	 */
	public List<KonkaBcompPd> selectKonkaBcompPdWidhDeptAndMdList(KonkaBcompPd t);

	/**
	 * @author Pan,Gang
	 * @date 2013-09-09
	 */
	public List<KonkaBcompPd> selectKonkaBcompPdForEcPricePaginatedList(KonkaBcompPd t);

	public Long selectKonkaBcompPdForEcPriceCount(KonkaBcompPd t);

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public Long selectKonkaBcompPdForEcPriceAndSaleCount(KonkaBcompPd t);

	/**
	 * @author Jiang,JiaYong
	 * @date 2013-09-17
	 */
	public int updateKonkaBcompPdForViewCountOrSaleNum(KonkaBcompPd t);

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public List<KonkaBcompPd> selectKonkaBcompPdForEcPriceAndSalePaginatedList(KonkaBcompPd t);

	/**
	 * 获取主键id
	 * 
	 * @author Pan,Gang
	 * @date 2013-10-08
	 */
	public Long selectKonkaBcompPdForIdCount(KonkaBcompPd t);

	public List<KonkaBcompPd> selectKonkaBcompPdWithDeptAndMdForCustPaginatedList(KonkaBcompPd t);

	public Long selectKonkaBcompPdWithDeptAndMdForCustCount(KonkaBcompPd t);

	public List<KonkaBcompPd> selectKonkaBcompPdNewList(KonkaBcompPd t);

	public Long selectKonkaBcompPdForEcPriceAndSaleNewCount(KonkaBcompPd t);

	public List<KonkaBcompPd> selectKonkaBcompPdForEcPriceAndSaleNewPaginatedList(KonkaBcompPd t);

}
