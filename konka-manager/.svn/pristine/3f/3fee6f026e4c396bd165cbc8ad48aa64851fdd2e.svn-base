package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCustVisitDao;
import com.ebiz.mmt.domain.KonkaMobileCustVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class KonkaMobileCustVisitDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileCustVisit> implements KonkaMobileCustVisitDao {

	@Override
	public Long selectKonkaMobileCustVisitAndDetailCount(KonkaMobileCustVisit entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileCustVisitAndDetailCount",entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileCustVisit> selectKonkaMobileCustVisitAndDetailPaginatedList(KonkaMobileCustVisit entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileCustVisitAndDetailPaginatedList", entity);
	}

	@Override
	public Long selectKonkaMobileCustVisitLBCount(KonkaMobileCustVisit entity) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileCustVisitLBCount",entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileCustVisit> selectKonkaMobileCustVisitPaginatedLBList(
			KonkaMobileCustVisit entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileCustVisitPaginatedLBList", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileCustVisit> selectKonkaMobileCustVisitForCount(KonkaMobileCustVisit entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileCustVisitForCount", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileCustVisit> selectKonkaMobileCustVisitAcountPaginatedList(KonkaMobileCustVisit entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileCustVisitAcountPaginatedList", entity);
	}

	@Override
	public Long selectKonkaMobileCustVisitAcountPaginatedListCount(
			KonkaMobileCustVisit entity) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("getKonkaMobileCustVisitAcountPaginatedListCount",entity);
	}

	@Override
	public Long selectCustMonthVisitPaginatedListCount(
			KonkaMobileCustVisit entity) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectCustMonthVisitListCount",entity);
	}

	@Override
	public List<KonkaMobileCustVisit> selectCustMonthVisitPaginatedList(
			KonkaMobileCustVisit entity) {
		return super.getSqlMapClientTemplate().queryForList("selectCustMonthVisitList", entity);
	}

	@Override
	public Long selectShopMonthVisitPaginatedListCount(
			KonkaMobileCustVisit entity) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectShopMonthVisitListCount",entity);
	}
	
	@Override
	public List<KonkaMobileCustVisit> selectShopMonthVisitPaginatedList(
			KonkaMobileCustVisit entity) {
		return super.getSqlMapClientTemplate().queryForList("selectShopMonthVisitList", entity);
	}

	@Override
	public List<PeProdUser> selectYwyByUserList(KonkaMobileCustVisit entity) {
		return super.getSqlMapClientTemplate().queryForList("selectYwyByUserList", entity);
	}

}
