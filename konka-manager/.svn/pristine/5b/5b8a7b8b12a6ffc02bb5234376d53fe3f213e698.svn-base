package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaInterfaceLicensesDao;
import com.ebiz.mmt.domain.KonkaInterfaceLicenses;
import com.ebiz.mmt.service.KonkaInterfaceLicensesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
@Service
public class KonkaInterfaceLicensesServiceImpl implements KonkaInterfaceLicensesService {

	@Resource
	private KonkaInterfaceLicensesDao konkaInterfaceLicensesDao;
	

	public Long createKonkaInterfaceLicenses(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.insertEntity(t);
	}

	public KonkaInterfaceLicenses getKonkaInterfaceLicenses(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.selectEntity(t);
	}

	public Long getKonkaInterfaceLicensesCount(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.selectEntityCount(t);
	}

	public List<KonkaInterfaceLicenses> getKonkaInterfaceLicensesList(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.selectEntityList(t);
	}

	public int modifyKonkaInterfaceLicenses(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.updateEntity(t);
	}

	public int removeKonkaInterfaceLicenses(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.deleteEntity(t);
	}

	public List<KonkaInterfaceLicenses> getKonkaInterfaceLicensesPaginatedList(KonkaInterfaceLicenses t) {
		return this.konkaInterfaceLicensesDao.selectEntityPaginatedList(t);
	}

}
