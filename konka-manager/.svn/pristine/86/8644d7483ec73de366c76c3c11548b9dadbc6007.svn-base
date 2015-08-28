package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdDao;
import com.ebiz.mmt.dao.KonkaXxPdHistoryDao;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxPdHistory;
import com.ebiz.mmt.service.KonkaXxPdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxPdServiceImpl implements KonkaXxPdService {

	@Resource
	private KonkaXxPdDao konkaXxPdDao;

	@Resource
	private KonkaXxPdHistoryDao konkaXxPdHistoryDao;

	public Long createKonkaXxPd(KonkaXxPd t) {
		return this.konkaXxPdDao.insertEntity(t);
	}

	public KonkaXxPd getKonkaXxPd(KonkaXxPd t) {
		return this.konkaXxPdDao.selectEntity(t);
	}

	public Long getKonkaXxPdCount(KonkaXxPd t) {
		return this.konkaXxPdDao.selectEntityCount(t);
	}

	public List<KonkaXxPd> getKonkaXxPdList(KonkaXxPd t) {
		return this.konkaXxPdDao.selectEntityList(t);
	}

	public int modifyKonkaXxPd(KonkaXxPd t) {
		return this.konkaXxPdDao.updateEntity(t);
	}

	public int removeKonkaXxPd(KonkaXxPd t) {
		return this.konkaXxPdDao.deleteEntity(t);
	}

	public List<KonkaXxPd> getKonkaXxPdPaginatedList(KonkaXxPd t) {
		return this.konkaXxPdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	public Long getKonkaXxPdCountForDemo(KonkaXxPd t) {
		return this.konkaXxPdDao.selectKonkaXxPdCountForDemo(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	public List<KonkaXxPd> getKonkaXxPdPaginatedListForDemo(KonkaXxPd t) {
		return this.konkaXxPdDao.selectKonkaXxPdPaginatedListForDemo(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	public List<KonkaXxPd> getKonkaXxPdToExcelList(KonkaXxPd t) {
		return this.konkaXxPdDao.selectKonkaXxPdToExcelList(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-1-9
	 */
	public List<KonkaXxPd> getKonkaXxWithUsersPdPaginatedList(KonkaXxPd t) {
		return this.konkaXxPdDao.selectKonkaXxWithUsersPdPaginatedList(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-1-9
	 */
	public Long getKonkaXxPdWithUserCount(KonkaXxPd t) {
		return this.konkaXxPdDao.selectKonkaXxPdWithUserCount(t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-3-2
	 */
	public void modifyKonkaXxPdForHistory(KonkaXxPd t) {
		this.modifyKonkaXxPd(t);
		KonkaXxPdHistory konkaXxPdHistory = new KonkaXxPdHistory();
		konkaXxPdHistory.setDept_id(t.getDept_id());
		konkaXxPdHistory.setDept_pd_id(t.getDept_pd_id());
		konkaXxPdHistory.setAdd_date(t.getAdd_date());
		konkaXxPdHistory.setAdd_user_id(t.getAdd_user_id());
		konkaXxPdHistory.setDown_time(t.getDown_time());
		konkaXxPdHistory.setMd_name(t.getMd_name());
		konkaXxPdHistory.setPd_cls(t.getPd_cls());
		konkaXxPdHistory.setPd_cls_name(t.getPd_cls_name());
		konkaXxPdHistory.setPrice_min(t.getPrice_min());
		konkaXxPdHistory.setPrice_ref(t.getPrice_ref());
		konkaXxPdHistory.setRemarks(t.getRemarks());
		konkaXxPdHistory.setUp_time(t.getUp_time());
		konkaXxPdHistory.setFac_sn(t.getFac_sn());
		konkaXxPdHistory.setStore_sn(t.getStore_sn());
		konkaXxPdHistory.setFac_desc(t.getFac_desc());
		
		this.konkaXxPdHistoryDao.insertEntity(konkaXxPdHistory);
	}

	public void createKonkaXxPdForHistory(KonkaXxPd t) {
		this.createKonkaXxPd(t);
		KonkaXxPdHistory konkaXxPdHistory = new KonkaXxPdHistory();
		konkaXxPdHistory.setDept_id(t.getDept_id());
		konkaXxPdHistory.setDept_pd_id(t.getDept_pd_id());
		konkaXxPdHistory.setAdd_date(t.getAdd_date());
		konkaXxPdHistory.setAdd_user_id(t.getAdd_user_id());
		konkaXxPdHistory.setDown_time(t.getDown_time());
		konkaXxPdHistory.setMd_name(t.getMd_name());
		konkaXxPdHistory.setPd_cls(t.getPd_cls());
		konkaXxPdHistory.setPd_cls_name(t.getPd_cls_name());
		konkaXxPdHistory.setPrice_min(t.getPrice_min());
		konkaXxPdHistory.setPrice_ref(t.getPrice_ref());
		konkaXxPdHistory.setRemarks(t.getRemarks());
		konkaXxPdHistory.setUp_time(t.getUp_time());
		konkaXxPdHistory.setFac_sn(t.getFac_sn());
		konkaXxPdHistory.setStore_sn(t.getStore_sn());
		konkaXxPdHistory.setFac_desc(t.getFac_desc());
		
		this.konkaXxPdHistoryDao.insertEntity(konkaXxPdHistory);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-26
	 */
	public List<KonkaXxPd> getKonkaXxPdForMdNameList(KonkaXxPd t){
		return this.konkaXxPdDao.selectKonkaXxPdForMdNameList(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-04-28
	 */
	public Long createKonkaXxPdForExcel(List<KonkaXxPd> entityList) {
		Long size = 0l;
		if(entityList != null && entityList.size()>0){
			for(KonkaXxPd konkaXxPd: entityList){
				this.createKonkaXxPd(konkaXxPd);
				size++;
			}
		}
		return size;
	}
}
