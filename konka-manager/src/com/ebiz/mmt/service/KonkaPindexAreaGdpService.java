package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPindexAreaGdp;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-03 14:59:02
 */
public interface KonkaPindexAreaGdpService {

	Long createKonkaPindexAreaGdp(KonkaPindexAreaGdp t);

	int modifyKonkaPindexAreaGdp(KonkaPindexAreaGdp t);

	int removeKonkaPindexAreaGdp(KonkaPindexAreaGdp t);

	KonkaPindexAreaGdp getKonkaPindexAreaGdp(KonkaPindexAreaGdp t);

	List<KonkaPindexAreaGdp> getKonkaPindexAreaGdpList(KonkaPindexAreaGdp t);

	Long getKonkaPindexAreaGdpCount(KonkaPindexAreaGdp t);

	List<KonkaPindexAreaGdp> getKonkaPindexAreaGdpPaginatedList(KonkaPindexAreaGdp t);

	String createKonkaPindexAreaGdp(List<KonkaPindexAreaGdp> list);

}