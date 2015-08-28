package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPindexAreaGdpDao;
import com.ebiz.mmt.domain.KonkaPindexAreaGdp;
import com.ebiz.mmt.service.KonkaPindexAreaGdpService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-03 14:59:02
 */
@Service
public class KonkaPindexAreaGdpServiceImpl implements KonkaPindexAreaGdpService {

	@Resource
	private KonkaPindexAreaGdpDao konkaPindexAreaGdpDao;

	public Long createKonkaPindexAreaGdp(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.insertEntity(t);
	}

	public KonkaPindexAreaGdp getKonkaPindexAreaGdp(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.selectEntity(t);
	}

	public Long getKonkaPindexAreaGdpCount(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.selectEntityCount(t);
	}

	public List<KonkaPindexAreaGdp> getKonkaPindexAreaGdpList(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.selectEntityList(t);
	}

	public int modifyKonkaPindexAreaGdp(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.updateEntity(t);
	}

	public int removeKonkaPindexAreaGdp(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.deleteEntity(t);
	}

	public List<KonkaPindexAreaGdp> getKonkaPindexAreaGdpPaginatedList(KonkaPindexAreaGdp t) {
		return this.konkaPindexAreaGdpDao.selectEntityPaginatedList(t);
	}

	public String createKonkaPindexAreaGdp(List<KonkaPindexAreaGdp> list) {
		return this.konkaPindexAreaGdpDao.insertEcBaseCardNo(list);
	}

}
