package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaYjglYjed;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
public interface KonkaYjglYjedService {

	Long createKonkaYjglYjed(KonkaYjglYjed t);

	int modifyKonkaYjglYjed(KonkaYjglYjed t);

	int removeKonkaYjglYjed(KonkaYjglYjed t);

	KonkaYjglYjed getKonkaYjglYjed(KonkaYjglYjed t);

	List<KonkaYjglYjed> getKonkaYjglYjedList(KonkaYjglYjed t);

	Long getKonkaYjglYjedCount(KonkaYjglYjed t);

	List<KonkaYjglYjed> getKonkaYjglYjedPaginatedList(KonkaYjglYjed t);

	public List<KonkaYjglYjed> getKonkaYjglYjedAndDeptNamePaginatedList(KonkaYjglYjed t);

	public Long getKonkaYjglYjedAndDeptNameCount(KonkaYjglYjed t);

}