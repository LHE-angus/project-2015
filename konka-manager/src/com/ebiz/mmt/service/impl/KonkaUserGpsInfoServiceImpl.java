package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaUserGpsInfoDao;
import com.ebiz.mmt.domain.KonkaUserGpsInfo;
import com.ebiz.mmt.service.KonkaUserGpsInfoService;


/**
 * @author Jin,QingHua
 * @version 2011-05-20 19:39:56
 */
@Service
public class KonkaUserGpsInfoServiceImpl implements KonkaUserGpsInfoService {

	@Resource
	private KonkaUserGpsInfoDao konkaUserGpsInfoDao;

	public long createKonkaUserGpsInfo(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.insertEntity(t);
	}

	public long modifyKonkaUserGpsInfo(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.updateEntity(t);
	}

	public long removeKonkaUserGpsInfo(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.deleteEntity(t);
	}

	public KonkaUserGpsInfo getKonkaUserGpsInfo(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.selectEntity(t);
	}

	public long getKonkaUserGpsInfoCount(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.selectEntityCount(t);
	}

	public List<KonkaUserGpsInfo> getKonkaUserGpsInfoList(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.selectEntityList(t);
	}

	public List<KonkaUserGpsInfo> getKonkaUserGpsInfoPaginatedList(KonkaUserGpsInfo t) {
		return this.konkaUserGpsInfoDao.selectEntityPaginatedList(t);
	}

}
