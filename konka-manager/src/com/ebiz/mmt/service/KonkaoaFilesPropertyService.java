package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaoaFilesProperty;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public interface KonkaoaFilesPropertyService {

	Long createKonkaoaFilesProperty(KonkaoaFilesProperty t);

	int modifyKonkaoaFilesProperty(KonkaoaFilesProperty t);

	int removeKonkaoaFilesProperty(KonkaoaFilesProperty t);

	KonkaoaFilesProperty getKonkaoaFilesProperty(KonkaoaFilesProperty t);

	List<KonkaoaFilesProperty> getKonkaoaFilesPropertyList(KonkaoaFilesProperty t);

	Long getKonkaoaFilesPropertyCount(KonkaoaFilesProperty t);

	List<KonkaoaFilesProperty> getKonkaoaFilesPropertyPaginatedList(KonkaoaFilesProperty t);

}