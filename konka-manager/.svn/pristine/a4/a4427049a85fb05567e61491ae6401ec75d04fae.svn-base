package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBackPasswordDao;
import com.ebiz.mmt.domain.EcBackPassword;
import com.ebiz.mmt.service.EcBackPasswordService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-13 14:21:32
 */
@Service
public class EcBackPasswordServiceImpl implements EcBackPasswordService {

	@Resource
	private EcBackPasswordDao ecBackPasswordDao;
	

	public Long createEcBackPassword(EcBackPassword t) {
		return this.ecBackPasswordDao.insertEntity(t);
	}

	public EcBackPassword getEcBackPassword(EcBackPassword t) {
		return this.ecBackPasswordDao.selectEntity(t);
	}

	public Long getEcBackPasswordCount(EcBackPassword t) {
		return this.ecBackPasswordDao.selectEntityCount(t);
	}

	public List<EcBackPassword> getEcBackPasswordList(EcBackPassword t) {
		return this.ecBackPasswordDao.selectEntityList(t);
	}

	public int modifyEcBackPassword(EcBackPassword t) {
		return this.ecBackPasswordDao.updateEntity(t);
	}

	public int removeEcBackPassword(EcBackPassword t) {
		return this.ecBackPasswordDao.deleteEntity(t);
	}

	public List<EcBackPassword> getEcBackPasswordPaginatedList(EcBackPassword t) {
		return this.ecBackPasswordDao.selectEntityPaginatedList(t);
	}

}
