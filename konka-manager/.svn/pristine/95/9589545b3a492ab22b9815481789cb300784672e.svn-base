package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCommentInfoDao;
import com.ebiz.mmt.domain.KonkaCommentInfo;
import com.ebiz.mmt.service.KonkaCommentInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
@Service
public class KonkaCommentInfoServiceImpl implements KonkaCommentInfoService {

	@Resource
	private KonkaCommentInfoDao konkaCommentInfoDao;

	public Long createKonkaCommentInfo(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.insertEntity(t);
	}

	public KonkaCommentInfo getKonkaCommentInfo(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.selectEntity(t);
	}

	public Long getKonkaCommentInfoCount(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.selectEntityCount(t);
	}

	public List<KonkaCommentInfo> getKonkaCommentInfoList(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.selectEntityList(t);
	}

	public int modifyKonkaCommentInfo(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.updateEntity(t);
	}

	public int removeKonkaCommentInfo(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.deleteEntity(t);
	}

	public List<KonkaCommentInfo> getKonkaCommentInfoPaginatedList(KonkaCommentInfo t) {
		return this.konkaCommentInfoDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaCommentInfo> getKonkaCommentInfoListFatherAndChildren(KonkaCommentInfo entity) {
		return this.konkaCommentInfoDao.selectKonkaCommentInfoListFatherAndChildren(entity);
	}

}
