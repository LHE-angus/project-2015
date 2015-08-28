package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ExOrderLines;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-29 14:09:13
 */
public interface KonkaR3ExOrderLinesService {

	Long createKonkaR3ExOrderLines(KonkaR3ExOrderLines t);

	int modifyKonkaR3ExOrderLines(KonkaR3ExOrderLines t);

	int removeKonkaR3ExOrderLines(KonkaR3ExOrderLines t);

	KonkaR3ExOrderLines getKonkaR3ExOrderLines(KonkaR3ExOrderLines t);

	List<KonkaR3ExOrderLines> getKonkaR3ExOrderLinesList(KonkaR3ExOrderLines t);

	Long getKonkaR3ExOrderLinesCount(KonkaR3ExOrderLines t);

	List<KonkaR3ExOrderLines> getKonkaR3ExOrderLinesPaginatedList(KonkaR3ExOrderLines t);

}