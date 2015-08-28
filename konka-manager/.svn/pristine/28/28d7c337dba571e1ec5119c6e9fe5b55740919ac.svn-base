package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaSellReportTmpDao;
import com.ebiz.mmt.domain.KonkaSellReportTmp;

@Repository
public class KonkaSellReportTmpDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaSellReportTmp> implements
		KonkaSellReportTmpDao {

	public Long selectKonkaSellReportTmpPlus(KonkaSellReportTmp t)
			throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectKonkaSellReportTmpPlus", t);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectKonkaSellReportTmpForRN(
			KonkaSellReportTmp t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaSellReportTmpForRN", t);
	}

	public Long selectKonkaSellReportTmpForRNCount(KonkaSellReportTmp t)
			throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectKonkaSellReportTmpForRNCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectKonkaSellReportTmpForRNALL(
			KonkaSellReportTmp t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaSellReportTmpForRNALL", t);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectKonkaSellReportTmpForJB(
			KonkaSellReportTmp t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaSellReportTmpForJB", t);
	}

	public Long selectKonkaSellReportTmpForJBCount(KonkaSellReportTmp t)
			throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectKonkaSellReportTmpForJBCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectKonkaSellReportTmpForJBALL(
			KonkaSellReportTmp t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaSellReportTmpForJBALL", t);
	}
}
