package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserAddrsDao;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.service.EcUserAddrsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserAddrsServiceImpl implements EcUserAddrsService {

	@Resource
	private EcUserAddrsDao ecUserAddrsDao;
	

	public Long createEcUserAddrs(EcUserAddrs t) {
		return this.ecUserAddrsDao.insertEntity(t);
	}

	public EcUserAddrs getEcUserAddrs(EcUserAddrs t) {
		return this.ecUserAddrsDao.selectEntity(t);
	}

	public Long getEcUserAddrsCount(EcUserAddrs t) {
		return this.ecUserAddrsDao.selectEntityCount(t);
	}

	public List<EcUserAddrs> getEcUserAddrsList(EcUserAddrs t) {
		return this.ecUserAddrsDao.selectEntityList(t);
	}

	public int modifyEcUserAddrs(EcUserAddrs t) {
		return this.ecUserAddrsDao.updateEntity(t);
	}

	public int removeEcUserAddrs(EcUserAddrs t) {
		return this.ecUserAddrsDao.deleteEntity(t);
	}

	public List<EcUserAddrs> getEcUserAddrsPaginatedList(EcUserAddrs t) {
		return this.ecUserAddrsDao.selectEntityPaginatedList(t);
	}
 
	public int modifyEcUserAddrsForDefaultAddr(EcUserAddrs t) {
		return this.ecUserAddrsDao.modifyEcUserAddrsForDefaultAddr(t);
	}

}
