package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcBaseAddrDao;
import com.ebiz.mmt.domain.KonkaJxcBaseAddr;
import com.ebiz.mmt.service.KonkaJxcBaseAddrService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-25 10:27:11
 */
@Service
public class KonkaJxcBaseAddrServiceImpl implements KonkaJxcBaseAddrService {

	@Resource
	private KonkaJxcBaseAddrDao konkaJxcBaseAddrDao;
	

	public Long createKonkaJxcBaseAddr(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.insertEntity(t);
	}

	public KonkaJxcBaseAddr getKonkaJxcBaseAddr(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.selectEntity(t);
	}

	public Long getKonkaJxcBaseAddrCount(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.selectEntityCount(t);
	}

	public List<KonkaJxcBaseAddr> getKonkaJxcBaseAddrList(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.selectEntityList(t);
	}

	public int modifyKonkaJxcBaseAddr(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.updateEntity(t);
	}

	public int removeKonkaJxcBaseAddr(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.deleteEntity(t);
	}

	public List<KonkaJxcBaseAddr> getKonkaJxcBaseAddrPaginatedList(KonkaJxcBaseAddr t) {
		return this.konkaJxcBaseAddrDao.selectEntityPaginatedList(t);
	}

}
