package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaoaFilesDao;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
@Repository
public class KonkaoaFilesDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaoaFiles> implements KonkaoaFilesDao {

	@SuppressWarnings("unchecked")
	public List<KonkaoaFiles> selectKonkaoaFilesPaginatedListForAuditIng(KonkaoaFiles files) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesPaginatedListForAuditIng", files);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaoaFiles> selectKonkaoaFilesPaginatedListFirst(KonkaoaFiles files) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesPaginatedListFirst", files);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaoaFiles> selectKonkaoaFilesListForArchive(KonkaoaFiles files) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesListForArchive", files);

	}

	public Long selectKonkaoaFilesListForArchiveCount(KonkaoaFiles files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesListForArchiveCount", files);
	}

	public Long selectKonkaoaFilesListForAuditIngCount(KonkaoaFiles files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesListForAuditIngCount", files);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaoaFiles> selectKonkaoaFilesPaginatedListForAudit(KonkaoaFiles files) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesPaginatedListForAudit", files);
	}

	public Long selectKonkaoaFilesCountForAudit(KonkaoaFiles files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesCountForAudit", files);
	}

	public Long selectKonkaoaFilesCountForPaginatedList(KonkaoaFiles files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesCountForPaginatedList", files);
	}

	@Override
	public Long selectKonkaoaSsuedDocumentCount(KonkaoaSsuedDocument files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaSsuedDocumentCount", files);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaoaSsuedDocument> selectKonkaoaSsuedDocumentPaginatedList(KonkaoaSsuedDocument files)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaSsuedDocumentPaginatedList", files);
	}

	@Override
	public Long selectKonkaoaFilesArchiveCount(KonkaoaSsuedDocument files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesArchiveCount", files);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaoaSsuedDocument> selectKonkaoaFilesArchivePaginatedList(KonkaoaSsuedDocument files)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesArchivePaginatedList", files);
	}

	@Override
	public Long selectKonkaoaFilesNoMax(KonkaoaFiles files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesNoMax", files);
	}

	public KonkaoaFiles selectKonkaoaFilesForExpenseClaims(KonkaoaFiles files) throws DataAccessException {
		return (KonkaoaFiles) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaFilesForExpenseClaims",
				files);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-08-09
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaoaFiles> selectBaseKonkaoaFilesPaginatedList(KonkaoaFiles t) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseKonkaoaFilesPaginatedList", t);
	}

    @Override
    public List<KonkaoaFiles> selectMyAuditOfKonkaoaFilesPaginatedList(KonkaoaFiles t) {
        return super.getSqlMapClientTemplate().queryForList("selectMyAuditOfKonkaoaFilesPaginatedList", t);
    }

    @Override
    public Long selectMyAuditOfKonkaoaFilesCount(KonkaoaFiles t) {
        return (Long) super.getSqlMapClientTemplate().queryForObject("selectMyAuditOfKonkaoaFilesCount", t);
    }
}