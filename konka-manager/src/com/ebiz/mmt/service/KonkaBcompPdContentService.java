package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaBcompPdContent;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-08-21 12:02:11
 */
public interface KonkaBcompPdContentService {

	Long createKonkaBcompPdContent(KonkaBcompPdContent t);

	int modifyKonkaBcompPdContent(KonkaBcompPdContent t);

	int removeKonkaBcompPdContent(KonkaBcompPdContent t);

	KonkaBcompPdContent getKonkaBcompPdContent(KonkaBcompPdContent t);

	List<KonkaBcompPdContent> getKonkaBcompPdContentList(KonkaBcompPdContent t);

	Long getKonkaBcompPdContentCount(KonkaBcompPdContent t);

	List<KonkaBcompPdContent> getKonkaBcompPdContentPaginatedList(KonkaBcompPdContent t);

}