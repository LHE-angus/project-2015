package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxNoticeContentDao;
import com.ebiz.mmt.domain.KonkaXxNoticeContent;
import com.ebiz.mmt.service.KonkaXxNoticeContentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-19 09:15:51
 */
@Service
public class KonkaXxNoticeContentServiceImpl implements KonkaXxNoticeContentService {

	@Resource
	private KonkaXxNoticeContentDao konkaXxNoticeContentDao;
	

	public Long createKonkaXxNoticeContent(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.insertEntity(t);
	}

	public KonkaXxNoticeContent getKonkaXxNoticeContent(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.selectEntity(t);
	}

	public Long getKonkaXxNoticeContentCount(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.selectEntityCount(t);
	}

	public List<KonkaXxNoticeContent> getKonkaXxNoticeContentList(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.selectEntityList(t);
	}

	public int modifyKonkaXxNoticeContent(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.updateEntity(t);
	}

	public int removeKonkaXxNoticeContent(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.deleteEntity(t);
	}

	public List<KonkaXxNoticeContent> getKonkaXxNoticeContentPaginatedList(KonkaXxNoticeContent t) {
		return this.konkaXxNoticeContentDao.selectEntityPaginatedList(t);
	}

}
