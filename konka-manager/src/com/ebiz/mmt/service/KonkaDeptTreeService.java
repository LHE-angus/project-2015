package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaDeptTree;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 18:17:19
 */
public interface KonkaDeptTreeService {

	Long createKonkaDeptTree(KonkaDeptTree t);

	int modifyKonkaDeptTree(KonkaDeptTree t);

	int removeKonkaDeptTree(KonkaDeptTree t);

	KonkaDeptTree getKonkaDeptTree(KonkaDeptTree t);

	List<KonkaDeptTree> getKonkaDeptTreeList(KonkaDeptTree t);

	Long getKonkaDeptTreeCount(KonkaDeptTree t);

	List<KonkaDeptTree> getKonkaDeptTreePaginatedList(KonkaDeptTree t);

}