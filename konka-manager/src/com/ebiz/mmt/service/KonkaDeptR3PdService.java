package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaDeptR3Pd;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-18 15:28:01
 */
public interface KonkaDeptR3PdService {

	Long createKonkaDeptR3Pd(KonkaDeptR3Pd t);

	int modifyKonkaDeptR3Pd(KonkaDeptR3Pd t);

	int removeKonkaDeptR3Pd(KonkaDeptR3Pd t);

	KonkaDeptR3Pd getKonkaDeptR3Pd(KonkaDeptR3Pd t);

	List<KonkaDeptR3Pd> getKonkaDeptR3PdList(KonkaDeptR3Pd t);

	Long getKonkaDeptR3PdCount(KonkaDeptR3Pd t);

	List<KonkaDeptR3Pd> getKonkaDeptR3PdPaginatedList(KonkaDeptR3Pd t);

	String createKonkaDeptR3Pd(List<KonkaDeptR3Pd> list);

}