package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSpList;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 12:08:34
 */
public interface KonkaSpListService {

	Long createKonkaSpList(KonkaSpList t);

	int modifyKonkaSpList(KonkaSpList t);

	int removeKonkaSpList(KonkaSpList t);

	KonkaSpList getKonkaSpList(KonkaSpList t);

	List<KonkaSpList> getKonkaSpListList(KonkaSpList t);

	Long getKonkaSpListCount(KonkaSpList t);

	List<KonkaSpList> getKonkaSpListPaginatedList(KonkaSpList t);

}