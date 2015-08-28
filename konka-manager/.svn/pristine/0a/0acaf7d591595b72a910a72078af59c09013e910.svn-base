package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaInterfaceIpDao;
import com.ebiz.mmt.domain.KonkaInterfaceIp;
import com.ebiz.mmt.service.KonkaInterfaceIpService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
@Service
public class KonkaInterfaceIpServiceImpl implements KonkaInterfaceIpService {

	@Resource
	private KonkaInterfaceIpDao konkaInterfaceIpDao;
	

	public Long createKonkaInterfaceIp(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.insertEntity(t);
	}

	public KonkaInterfaceIp getKonkaInterfaceIp(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.selectEntity(t);
	}

	public Long getKonkaInterfaceIpCount(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.selectEntityCount(t);
	}

	public List<KonkaInterfaceIp> getKonkaInterfaceIpList(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.selectEntityList(t);
	}

	public int modifyKonkaInterfaceIp(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.updateEntity(t);
	}

	public int removeKonkaInterfaceIp(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.deleteEntity(t);
	}

	public List<KonkaInterfaceIp> getKonkaInterfaceIpPaginatedList(KonkaInterfaceIp t) {
		return this.konkaInterfaceIpDao.selectEntityPaginatedList(t);
	}

}
