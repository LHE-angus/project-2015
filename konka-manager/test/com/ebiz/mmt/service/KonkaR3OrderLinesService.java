package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3OrderLines;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by zhou
 * @date 2013-05-29 14:09:13
 */
public interface KonkaR3OrderLinesService {

	Long createKonkaR3OrderLines(KonkaR3OrderLines t);

	int modifyKonkaR3OrderLines(KonkaR3OrderLines t);

	int removeKonkaR3OrderLines(KonkaR3OrderLines t);

	KonkaR3OrderLines getKonkaR3OrderLines(KonkaR3OrderLines t);

	List<KonkaR3OrderLines> getKonkaR3OrderLinesList(KonkaR3OrderLines t);

	Long getKonkaR3OrderLinesCount(KonkaR3OrderLines t);

	List<KonkaR3OrderLines> getKonkaR3OrderLinesPaginatedList(KonkaR3OrderLines t);

}