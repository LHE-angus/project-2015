package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSapInterfaceSettingDao;
import com.ebiz.mmt.domain.KonkaSapInterfaceSetting;
import com.ebiz.mmt.service.KonkaSapInterfaceSettingService;


@Service
public class KonkaSapInterfaceSettingServiceImpl implements KonkaSapInterfaceSettingService {

	@Resource
	private KonkaSapInterfaceSettingDao konkaSapInterfaceSettingDao;
	

	@Override
	public Long createKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.insertEntity(t);
	}

	@Override
	public KonkaSapInterfaceSetting getKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.selectEntity(t);
	}

	@Override
	public Long getKonkaSapInterfaceSettingCount(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaSapInterfaceSetting> getKonkaSapInterfaceSettingList(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.updateEntity(t);
	}

	@Override
	public int removeKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.deleteEntity(t);
	}

	@Override
	public List<KonkaSapInterfaceSetting> getKonkaSapInterfaceSettingPaginatedList(KonkaSapInterfaceSetting t) {
		return this.konkaSapInterfaceSettingDao.selectEntityPaginatedList(t);
	}

	@Override
	public boolean isOpenSapInterfaceFunc(String serviceName) {

		KonkaSapInterfaceSetting h = new KonkaSapInterfaceSetting();
		h.setService(serviceName);
		h.setStatus("0");
		return this.konkaSapInterfaceSettingDao.selectEntityCount(h) > 0;
	}

	@Override
	public KonkaSapInterfaceSetting getValidKonkaSapInterface(String serviceName) {
		KonkaSapInterfaceSetting h = new KonkaSapInterfaceSetting();
		h.setService(serviceName);
		h.setStatus("0");
		return this.konkaSapInterfaceSettingDao.selectEntity(h);
	}

}
