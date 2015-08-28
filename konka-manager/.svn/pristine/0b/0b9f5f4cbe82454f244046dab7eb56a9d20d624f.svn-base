package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardLevelDao;
import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.service.EcBaseCardLevelService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
@Service
public class EcBaseCardLevelServiceImpl implements EcBaseCardLevelService {

	@Resource
	private EcBaseCardLevelDao ecBaseCardLevelDao;
	

	public Long createEcBaseCardLevel(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.insertEntity(t);
	}

	public EcBaseCardLevel getEcBaseCardLevel(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.selectEntity(t);
	}

	public Long getEcBaseCardLevelCount(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.selectEntityCount(t);
	}

	public List<EcBaseCardLevel> getEcBaseCardLevelList(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.selectEntityList(t);
	}

	public int modifyEcBaseCardLevel(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.updateEntity(t);
	}

	public int removeEcBaseCardLevel(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.deleteEntity(t);
	}

	public List<EcBaseCardLevel> getEcBaseCardLevelPaginatedList(EcBaseCardLevel t) {
		return this.ecBaseCardLevelDao.selectEntityPaginatedList(t);
	}

}
