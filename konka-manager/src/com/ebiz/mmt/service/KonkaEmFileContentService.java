package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaEmFileContent;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
public interface KonkaEmFileContentService {

	Long createKonkaEmFileContent(KonkaEmFileContent t);

	int modifyKonkaEmFileContent(KonkaEmFileContent t);

	int removeKonkaEmFileContent(KonkaEmFileContent t);

	KonkaEmFileContent getKonkaEmFileContent(KonkaEmFileContent t);

	List<KonkaEmFileContent> getKonkaEmFileContentList(KonkaEmFileContent t);

	Long getKonkaEmFileContentCount(KonkaEmFileContent t);

	List<KonkaEmFileContent> getKonkaEmFileContentPaginatedList(KonkaEmFileContent t);

}