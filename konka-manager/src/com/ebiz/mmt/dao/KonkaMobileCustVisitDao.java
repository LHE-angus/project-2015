package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileCustVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
public interface KonkaMobileCustVisitDao extends EntityDao<KonkaMobileCustVisit> {

	Long selectKonkaMobileCustVisitAndDetailCount(KonkaMobileCustVisit entity);

	List<KonkaMobileCustVisit> selectKonkaMobileCustVisitAndDetailPaginatedList(KonkaMobileCustVisit entity);

	Long selectKonkaMobileCustVisitLBCount(KonkaMobileCustVisit entity);
	
    List<KonkaMobileCustVisit> selectKonkaMobileCustVisitPaginatedLBList(KonkaMobileCustVisit entity);

	List<KonkaMobileCustVisit> selectKonkaMobileCustVisitForCount(KonkaMobileCustVisit entity);

	Long selectKonkaMobileCustVisitAcountPaginatedListCount(KonkaMobileCustVisit entity);
	
	List<KonkaMobileCustVisit> selectKonkaMobileCustVisitAcountPaginatedList(KonkaMobileCustVisit entity);
	
	Long selectCustMonthVisitPaginatedListCount(KonkaMobileCustVisit entity);
	
	List<KonkaMobileCustVisit> selectCustMonthVisitPaginatedList(KonkaMobileCustVisit entity);
	
    Long selectShopMonthVisitPaginatedListCount(KonkaMobileCustVisit entity);
	
	List<KonkaMobileCustVisit> selectShopMonthVisitPaginatedList(KonkaMobileCustVisit entity);
	
	List<PeProdUser> selectYwyByUserList(KonkaMobileCustVisit entity);
	
	
}
