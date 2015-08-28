package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGiftCommDao;
import com.ebiz.mmt.domain.EcGiftComm;
import com.ebiz.mmt.service.EcGiftCommService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-03 15:49:24
 */
@Service
public class EcGiftCommServiceImpl implements EcGiftCommService {

	@Resource
	private EcGiftCommDao ecGiftCommDao;
	

	public Long createEcGiftComm(EcGiftComm t) {
		return this.ecGiftCommDao.insertEntity(t);
	}

	public EcGiftComm getEcGiftComm(EcGiftComm t) {
		return this.ecGiftCommDao.selectEntity(t);
	}

	public Long getEcGiftCommCount(EcGiftComm t) {
		return this.ecGiftCommDao.selectEntityCount(t);
	}

	public List<EcGiftComm> getEcGiftCommList(EcGiftComm t) {
		return this.ecGiftCommDao.selectEntityList(t);
	}

	public int modifyEcGiftComm(EcGiftComm t) {
		return this.ecGiftCommDao.updateEntity(t);
	}

	public int removeEcGiftComm(EcGiftComm t) {
		return this.ecGiftCommDao.deleteEntity(t);
	}

	public List<EcGiftComm> getEcGiftCommPaginatedList(EcGiftComm t) {
		return this.ecGiftCommDao.selectEntityPaginatedList(t);
	}

}
