package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfGiftInfoDao;
import com.ebiz.mmt.domain.JfGiftInfo;
import com.ebiz.mmt.service.JfGiftInfoService;

/**
 * 
 * @author pangang
 * @date 2013-6-26 
 */
@Service
public class JfGiftInfoServiceImpl implements JfGiftInfoService {

	@Resource
	private JfGiftInfoDao jfGiftInfoDao;
	

	public Long createJfGiftInfo(JfGiftInfo t) {
		return this.jfGiftInfoDao.insertEntity(t);
	}

	public JfGiftInfo getJfGiftInfo(JfGiftInfo t) {
		return this.jfGiftInfoDao.selectEntity(t);
	}

	public Long getJfGiftInfoCount(JfGiftInfo t) {
		return this.jfGiftInfoDao.selectEntityCount(t);
	}

	public List<JfGiftInfo> getJfGiftInfoList(JfGiftInfo t) {
		return this.jfGiftInfoDao.selectEntityList(t);
	}

	public int modifyJfGiftInfo(JfGiftInfo t) {
		return this.jfGiftInfoDao.updateEntity(t);
	}

	public int removeJfGiftInfo(JfGiftInfo t) {
		return this.jfGiftInfoDao.deleteEntity(t);
	}

	public List<JfGiftInfo> getJfGiftInfoPaginatedList(JfGiftInfo t) {
		return this.jfGiftInfoDao.selectEntityPaginatedList(t);
	}

}
