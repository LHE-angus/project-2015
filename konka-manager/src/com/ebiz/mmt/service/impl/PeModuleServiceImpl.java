package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeModuleDao;
import com.ebiz.mmt.domain.PeModule;
import com.ebiz.mmt.service.PeModuleService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-24 10:17:11
 */
@Service
public class PeModuleServiceImpl implements PeModuleService {

	@Resource
	private PeModuleDao peModuleDao;

	public Long createPeModule(PeModule t) {
		return this.peModuleDao.insertEntity(t);
	}

	public PeModule getPeModule(PeModule t) {
		return this.peModuleDao.selectEntity(t);
	}

	public Long getPeModuleCount(PeModule t) {
		return this.peModuleDao.selectEntityCount(t);
	}

	public List<PeModule> getPeModuleList(PeModule t) {
		return this.peModuleDao.selectEntityList(t);
	}

	public int modifyPeModule(PeModule t) {
		return this.peModuleDao.updateEntity(t);
	}

	public int removePeModule(PeModule t) {
		return this.peModuleDao.deleteEntity(t);
	}

	public List<PeModule> getPeModulePaginatedList(PeModule t) {
		return this.peModuleDao.selectEntityPaginatedList(t);
	}

	// add by liyuan
	public List<PeModule> getPeModuleParentList(PeModule t) {
		return this.peModuleDao.selectPeModuleParentList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @date 2011-02-25
	 */
	public List<PeModule> getPeModuleAllParentList(PeModule t) {
		return this.peModuleDao.selectPeModuleAllParentList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-13
	 */
	public List<PeModule> getPeModuleListForAuthor() {
		return this.peModuleDao.selectPeModuleListForAuthor();
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-16
	 */
	public List<PeModule> getPeModuleForPeUserList(PeModule t) {
		return this.peModuleDao.selectPeModuleForPeUserList(t);
	}
}
