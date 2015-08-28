package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdRewardSetHd;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
public interface KonkaXxZmdRewardSetHdService {

	Long createKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t);

	int modifyKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t);

	int removeKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t);

	KonkaXxZmdRewardSetHd getKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t);

	List<KonkaXxZmdRewardSetHd> getKonkaXxZmdRewardSetHdList(KonkaXxZmdRewardSetHd t);

	Long getKonkaXxZmdRewardSetHdCount(KonkaXxZmdRewardSetHd t);

	List<KonkaXxZmdRewardSetHd> getKonkaXxZmdRewardSetHdPaginatedList(KonkaXxZmdRewardSetHd t);

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-05
	 */
	Long getKonkaXxZmdRewardSetHdWithZmdAndHdCount(KonkaXxZmdRewardSetHd t);

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-05
	 */
	List<KonkaXxZmdRewardSetHd> getKonkaXxZmdRewardSetHdWithZmdAndHdPaginatedList(KonkaXxZmdRewardSetHd t);

}