package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public interface KonkaoaFilesService {

	Long createKonkaoaFiles(KonkaoaFiles t);

	int modifyKonkaoaFiles(KonkaoaFiles t);

	int modifyKonkaoaFilesProperty(KonkaoaFiles t);

	int modifyKonkaoaFilesOnly(KonkaoaFiles t);

	int removeKonkaoaFiles(KonkaoaFiles t);

	KonkaoaFiles getKonkaoaFiles(KonkaoaFiles t);

	List<KonkaoaFiles> getKonkaoaFilesList(KonkaoaFiles t);

	Long getKonkaoaFilesCount(KonkaoaFiles t);

	List<KonkaoaFiles> getKonkaoaFilesPaginatedList(KonkaoaFiles t);

    // 文件,请假,费用等审批操作
	void auditFiles(KonkaoaFiles t);

	List<KonkaoaFiles> getKonkaoaFilesPaginatedListForAuditIng(KonkaoaFiles t);

    Long getKonkaoaFilesListForAuditIngCount(KonkaoaFiles t);



	List<KonkaoaFiles> getKonkaoaFilesPaginatedListFirst(KonkaoaFiles t);

	List<KonkaoaFiles> getKonkaoaFilesListForArchive(KonkaoaFiles t);

	Long getKonkaoaFilesListForArchiveCount(KonkaoaFiles t);


	List<KonkaoaFiles> getKonkaoaFilesPaginatedListForAudit(KonkaoaFiles t);

	Long getKonkaoaFilesCountForAudit(KonkaoaFiles t);

	Long getKonkaoaFilesCountForPaginatedList(KonkaoaFiles t);

	Long getKonkaoaSsuedDocumentCount(KonkaoaSsuedDocument t);

	List<KonkaoaSsuedDocument> getKonkaoaSsuedDocumentPaginatedList(KonkaoaSsuedDocument t);

	Long getKonkaoaFilesArchiveCount(KonkaoaSsuedDocument files);

	List<KonkaoaSsuedDocument> getKonkaoaFilesArchivePaginatedList(KonkaoaSsuedDocument files);

	Long getKonkaoaFilesNoMax(KonkaoaFiles t);

	KonkaoaFiles getKonkaoaFilesForExpenseClaims(KonkaoaFiles t);

	void removeKonkaFilesArchiveFiles(KonkaoaFiles t);

	/**
	 * @author Hu,Hao
	 * @version 2013-08-09
	 */
	List<KonkaoaFiles> getBaseKonkaoaFilesPaginatedList(KonkaoaFiles t);

    /**
     * 获取当前人审批过后的数据
     * 
     */
    List<KonkaoaFiles> getMyKonkaoaFilesOfAuditPaginatedList(KonkaoaFiles t);

    /**
     * 获取当前人审批过后的数据条数
     * 
     */
    Long getMyKonkaoaFilesOfAuditCount(KonkaoaFiles t);

}