package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public interface KonkaoaFilesDao extends EntityDao<KonkaoaFiles> {

	List<KonkaoaFiles> selectKonkaoaFilesPaginatedListForAuditIng(KonkaoaFiles files) throws DataAccessException;

	List<KonkaoaFiles> selectKonkaoaFilesPaginatedListFirst(KonkaoaFiles files) throws DataAccessException;

	Long selectKonkaoaFilesListForAuditIngCount(KonkaoaFiles files) throws DataAccessException;

	List<KonkaoaFiles> selectKonkaoaFilesListForArchive(KonkaoaFiles files) throws DataAccessException;

	Long selectKonkaoaFilesListForArchiveCount(KonkaoaFiles files) throws DataAccessException;

	List<KonkaoaFiles> selectKonkaoaFilesPaginatedListForAudit(KonkaoaFiles files) throws DataAccessException;

	Long selectKonkaoaFilesCountForAudit(KonkaoaFiles files) throws DataAccessException;

	Long selectKonkaoaFilesCountForPaginatedList(KonkaoaFiles files) throws DataAccessException;

	Long selectKonkaoaSsuedDocumentCount(KonkaoaSsuedDocument files) throws DataAccessException;

	List<KonkaoaSsuedDocument> selectKonkaoaSsuedDocumentPaginatedList(KonkaoaSsuedDocument files)
			throws DataAccessException;

	Long selectKonkaoaFilesArchiveCount(KonkaoaSsuedDocument files) throws DataAccessException;

	List<KonkaoaSsuedDocument> selectKonkaoaFilesArchivePaginatedList(KonkaoaSsuedDocument files)
			throws DataAccessException;

	Long selectKonkaoaFilesNoMax(KonkaoaFiles files) throws DataAccessException;

	KonkaoaFiles selectKonkaoaFilesForExpenseClaims(KonkaoaFiles files) throws DataAccessException;

	/**
	 * @author Hu,Hao
	 * @version 2013-08-09
	 */
	List<KonkaoaFiles> selectBaseKonkaoaFilesPaginatedList(KonkaoaFiles t);

    /**
     * 根据指定的过滤参数,查询某个人审核过的历史数据
     * 
     * @param map
     * @return
     */
    List<KonkaoaFiles> selectMyAuditOfKonkaoaFilesPaginatedList(KonkaoaFiles t);

    /**
     * 根据指定的过滤参数,查询某个人审核过的历史数据条数
     * 
     * @param map
     * @return
     */
    Long selectMyAuditOfKonkaoaFilesCount(KonkaoaFiles t);
}