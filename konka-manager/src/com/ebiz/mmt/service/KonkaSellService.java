package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.util.List;

import com.ebiz.mmt.domain.KonkaSell;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-01 12:46:44
 */
public interface KonkaSellService {

	Long createKonkaSell(KonkaSell t);

	int modifyKonkaSell(KonkaSell t);

	int removeKonkaSell(KonkaSell t);

	KonkaSell getKonkaSell(KonkaSell t);

	List<KonkaSell> getKonkaSellList(KonkaSell t);

	Long getKonkaSellCount(KonkaSell t);

	List<KonkaSell> getKonkaSellPaginatedList(KonkaSell t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	Long createKonkaSellIncludeDetails(KonkaSell t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	int modifyKonkaSellIncludeDetails(KonkaSell t);
	
	/**
	 * @author Wang,Yang
	 * @version 2011-11-10
	 */
	Long getKonkaSellCountByPd(KonkaSell t);
	
	BigDecimal getKonkaSellCountCostByPd(KonkaSell t);
}