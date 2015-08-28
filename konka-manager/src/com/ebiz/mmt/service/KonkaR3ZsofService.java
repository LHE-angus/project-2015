package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Zsof;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-14 16:44:47
 */
public interface KonkaR3ZsofService {

	Long createKonkaR3Zsof(KonkaR3Zsof t);

	int modifyKonkaR3Zsof(KonkaR3Zsof t);

	int removeKonkaR3Zsof(KonkaR3Zsof t);

	KonkaR3Zsof getKonkaR3Zsof(KonkaR3Zsof t);

	List<KonkaR3Zsof> getKonkaR3ZsofList(KonkaR3Zsof t);

	Long getKonkaR3ZsofCount(KonkaR3Zsof t);

	List<KonkaR3Zsof> getKonkaR3ZsofPaginatedList(KonkaR3Zsof t);

}