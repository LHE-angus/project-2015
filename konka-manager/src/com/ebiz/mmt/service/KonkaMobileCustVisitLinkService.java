package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileCustVisitLink;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-04 14:07:23
 */
public interface KonkaMobileCustVisitLinkService {

	Long createKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t);

	int modifyKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t);

	int removeKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t);

	KonkaMobileCustVisitLink getKonkaMobileCustVisitLink(KonkaMobileCustVisitLink t);

	List<KonkaMobileCustVisitLink> getKonkaMobileCustVisitLinkList(KonkaMobileCustVisitLink t);

	Long getKonkaMobileCustVisitLinkCount(KonkaMobileCustVisitLink t);

	List<KonkaMobileCustVisitLink> getKonkaMobileCustVisitLinkPaginatedList(KonkaMobileCustVisitLink t);

}