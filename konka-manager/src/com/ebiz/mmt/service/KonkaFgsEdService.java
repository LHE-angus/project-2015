package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaFgsEd;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-21 09:53:26
 */
public interface KonkaFgsEdService {

	Long createKonkaFgsEd(KonkaFgsEd t);

	int modifyKonkaFgsEd(KonkaFgsEd t);

	int removeKonkaFgsEd(KonkaFgsEd t);

	KonkaFgsEd getKonkaFgsEd(KonkaFgsEd t);

	List<KonkaFgsEd> getKonkaFgsEdList(KonkaFgsEd t);

	Long getKonkaFgsEdCount(KonkaFgsEd t);

	List<KonkaFgsEd> getKonkaFgsEdPaginatedList(KonkaFgsEd t);

	List<KonkaFgsEd> getKonkaFgsEdGroupByDeptIdList(KonkaFgsEd t);

}