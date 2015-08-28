package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.mmt.domain.KonkaBcompPd;

/**
 * @author Ren,zhong
 * @version 2013-07-05 10:59
 */
public interface KonkaBcompPdService {

	Long createKonkaBcompPd(KonkaBcompPd t);

	int modifyKonkaBcompPd(KonkaBcompPd t);

	int removeKonkaBcompPd(KonkaBcompPd t);

	KonkaBcompPd getKonkaBcompPd(KonkaBcompPd t);

	List<KonkaBcompPd> getKonkaBcompPdList(KonkaBcompPd t);

	Long getKonkaBcompPdCount(KonkaBcompPd t);

	List<KonkaBcompPd> getKonkaBcompPdPaginatedList(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	Long getKonkaBcompPdWithDeptAndMdCount(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	List<KonkaBcompPd> getKonkaBcompPdWithDeptAndMdPaginatedList(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-05
	 */
	List<KonkaBcompPd> getKonkaBcompPdWidhDeptAndMdList(KonkaBcompPd t);

	/**
	 * @author Pan, Gang
	 * @date 2013-08-21
	 */
	int createKonkaBcompPdIncludeContent(KonkaBcompPd t, String e_id, String e_id2);

	int createKonkaBcompPdIncludeContent2(KonkaBcompPd t, String e_id, String e_id2, List<EcExtend> ecList,
	        List<EcExtend> ecList2);

	/**
	 * @author Jiang,JiaYong
	 * @date 2013-09-17
	 */
	int modifyKonkaBcompPdForViewCountOrSaleNum(KonkaBcompPd t);

	/**
	 * @author Pan, Gang
	 * @date 2013-08-21
	 */
	int modifyKonkaBcompPdIncludeContent(KonkaBcompPd t, String e_id, String e_id2);

	int modifyKonkaBcompPdIncludeContent2(KonkaBcompPd t, String e_id, String e_id2, List<EcExtend> list,
	        List<EcExtend> list2);

	public List<KonkaBcompPd> getKonkaBcompPdForEcPricePaginatedList(KonkaBcompPd t);

	public Long getKonkaBcompPdForEcPriceCount(KonkaBcompPd t);

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public Long getKonkaBcompPdForEcPriceAndSaleCount(KonkaBcompPd t);

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public List<KonkaBcompPd> getKonkaBcompPdForEcPriceAndSalePaginatedList(KonkaBcompPd t);

	public Long getKonkaBcompPdForIdCount(KonkaBcompPd t);

	public List<KonkaBcompPd> getKonkaBcompPdWithDeptAndMdForCustPaginatedList(KonkaBcompPd t);

	public Long getKonkaBcompPdWithDeptAndMdForCustCount(KonkaBcompPd t);

	public Long getKonkaBcompPdWithDeptAndMdNewCount(KonkaBcompPd t);

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public List<KonkaBcompPd> getKonkaBcompPdWithDeptAndMdNewPaginatedList(KonkaBcompPd t);

	int modifyKonkaBcompPdForRule(KonkaBcompPd t);

	public List<KonkaBcompPd> getKonkaBcompPdNewList(KonkaBcompPd t);

	public Long getKonkaBcompPdForEcPriceAndSaleNewCount(KonkaBcompPd t);

	public List<KonkaBcompPd> getKonkaBcompPdForEcPriceAndSaleNewPaginatedList(KonkaBcompPd t);
}