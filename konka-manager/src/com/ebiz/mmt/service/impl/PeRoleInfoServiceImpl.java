package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaRoleDataLevelDao;
import com.ebiz.mmt.dao.PeRoleInfoDao;
import com.ebiz.mmt.domain.KonkaRoleDataLevel;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.service.PeRoleInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeRoleInfoServiceImpl implements PeRoleInfoService {

	@Resource
	private PeRoleInfoDao PeRoleInfoDao;

	@Resource
	private KonkaRoleDataLevelDao konkaRoleDataLevelDao;

	@Override
	public Long createPeRoleInfo(PeRoleInfo t) {
		return this.PeRoleInfoDao.insertEntity(t);
	}

	@Override
	public Long createPeRoleInfoWithoutAutoKey(PeRoleInfo t) {

		List<KonkaRoleDataLevel> kLevelList = t.getkLevelLsit();

		// 数据权限
		for (KonkaRoleDataLevel temp : kLevelList) {
			this.konkaRoleDataLevelDao.insertEntity(temp);
		}

		return this.PeRoleInfoDao.insertPeRoleInfoWithoutAutoKey(t);
	}

	@Override
	public PeRoleInfo getPeRoleInfo(PeRoleInfo t) {
		return this.PeRoleInfoDao.selectEntity(t);
	}

	@Override
	public Long getPeRoleInfoCount(PeRoleInfo t) {
		return this.PeRoleInfoDao.selectEntityCount(t);
	}

	@Override
	public List<PeRoleInfo> getPeRoleInfoList(PeRoleInfo t) {
		return this.PeRoleInfoDao.selectEntityList(t);
	}

	@Override
	public int modifyPeRoleInfo(PeRoleInfo t) {
		return this.PeRoleInfoDao.updateEntity(t);
	}

	@Override
	public int removePeRoleInfo(PeRoleInfo t) {
		return this.PeRoleInfoDao.deleteEntity(t);
	}

	@Override
	public List<PeRoleInfo> getPeRoleInfoPaginatedList(PeRoleInfo t) {
		return this.PeRoleInfoDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	@Override
	public Long getPeRoleInfoWithNameCount(PeRoleInfo t) throws DataAccessException {
		return this.PeRoleInfoDao.selectPeRoleInfoWithNameCount(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-16
	 */
	@Override
	public List<PeRoleInfo> getPeRoleInfoWithNamePaginatedList(PeRoleInfo t) throws DataAccessException {
		return this.PeRoleInfoDao.selectPeRoleInfoWithNamePaginatedList(t);
	}

	@Override
	public List<PeRoleInfo> getPeRoleInfoByUserIdsList(PeRoleInfo t) throws DataAccessException {
		return this.PeRoleInfoDao.selectPeRoleInfoByUserIdsList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-25
	 */
	@Override
	public List<PeRoleInfo> getPeRoleInfoForDeptNamePaginatedList(PeRoleInfo t) throws DataAccessException {
		return this.PeRoleInfoDao.selectPeRoleInfoForDeptNamePaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-25
	 */
	@Override
	public void modifyPeRoleInfoForDept(PeRoleInfo t) {

		KonkaRoleDataLevel kDataLevel = new KonkaRoleDataLevel();
		kDataLevel.setRole_id(t.getRole_id());
		kDataLevel.getMap().put("is_role_del", true);
		this.konkaRoleDataLevelDao.deleteEntity(kDataLevel);

		List<KonkaRoleDataLevel> kDataLevelList = t.getkLevelLsit();
		for (KonkaRoleDataLevel temp : kDataLevelList) {
			this.konkaRoleDataLevelDao.insertEntity(temp);
		}
		this.PeRoleInfoDao.updateEntity(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-01-13
	 */
	public PeRoleInfo getPeRoleInfoForRoleNames(PeRoleInfo t) {
		return this.PeRoleInfoDao.selectPeRoleInfoForRoleNames(t);
	}
}
