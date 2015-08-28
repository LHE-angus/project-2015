package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdHistoryDao;
import com.ebiz.mmt.domain.KonkaXxPdHistory;
import com.ebiz.mmt.service.KonkaXxPdHistoryService;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxPdHistoryServiceImpl implements KonkaXxPdHistoryService {

	@Resource
	private KonkaXxPdHistoryDao konkaXxPdHistoryDao;
	

	public Long createKonkaXxPdHistory(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.insertEntity(t);
	}

	public KonkaXxPdHistory getKonkaXxPdHistory(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.selectEntity(t);
	}

	public Long getKonkaXxPdHistoryCount(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.selectEntityCount(t);
	}

	public List<KonkaXxPdHistory> getKonkaXxPdHistoryList(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.selectEntityList(t);
	}

	public int modifyKonkaXxPdHistory(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.updateEntity(t);
	}

	public int removeKonkaXxPdHistory(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.deleteEntity(t);
	}

	public List<KonkaXxPdHistory> getKonkaXxPdHistoryPaginatedList(KonkaXxPdHistory t) {
		return this.konkaXxPdHistoryDao.selectEntityPaginatedList(t);
	}
	
	/**
	 * 
	 * @auther Hu,Hao
	 * @version 2012-3-31
	 */
	public List<KonkaXxPdHistory> getKonkaXxPdHistoryListForHistoryId(KonkaXxPdHistory t){
		return this.konkaXxPdHistoryDao.selectKonkaXxPdHistoryListForHistoryId(t);
	}
	
}
