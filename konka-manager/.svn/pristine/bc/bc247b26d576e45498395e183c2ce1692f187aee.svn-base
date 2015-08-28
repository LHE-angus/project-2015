package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaInterfaceAccessLogDao;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.service.KonkaInterfaceAccessLogService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
@Service
public class KonkaInterfaceAccessLogServiceImpl implements KonkaInterfaceAccessLogService {

	@Resource
	private KonkaInterfaceAccessLogDao konkaInterfaceAccessLogDao;

	public Long createKonkaInterfaceAccessLog(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.insertEntity(t);
	}

	public KonkaInterfaceAccessLog getKonkaInterfaceAccessLog(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.selectEntity(t);
	}

	public Long getKonkaInterfaceAccessLogCount(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.selectEntityCount(t);
	}

	public List<KonkaInterfaceAccessLog> getKonkaInterfaceAccessLogList(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.selectEntityList(t);
	}

	public int modifyKonkaInterfaceAccessLog(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.updateEntity(t);
	}

	public int removeKonkaInterfaceAccessLog(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.deleteEntity(t);
	}

	public List<KonkaInterfaceAccessLog> getKonkaInterfaceAccessLogPaginatedList(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.selectEntityPaginatedList(t);
	}

	public List<KonkaInterfaceAccessLog> getKonkaInterfaceAccessLogForUserNamePaginatedList(KonkaInterfaceAccessLog t) {
		return this.konkaInterfaceAccessLogDao.selectKonkaInterfaceAccessLogForUserNamePaginatedList(t);
	}

}
