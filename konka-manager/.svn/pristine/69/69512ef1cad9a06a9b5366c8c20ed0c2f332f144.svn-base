package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxAuditNoteDao;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.service.KonkaXxAuditNoteService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-27 15:20:48
 */
@Service
public class KonkaXxAuditNoteServiceImpl implements KonkaXxAuditNoteService {

	@Resource
	private KonkaXxAuditNoteDao konkaXxAuditNoteDao;
	

	public Long createKonkaXxAuditNote(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.insertEntity(t);
	}

	public KonkaXxAuditNote getKonkaXxAuditNote(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.selectEntity(t);
	}

	public Long getKonkaXxAuditNoteCount(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.selectEntityCount(t);
	}

	public List<KonkaXxAuditNote> getKonkaXxAuditNoteList(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.selectEntityList(t);
	}

	public int modifyKonkaXxAuditNote(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.updateEntity(t);
	}

	public int removeKonkaXxAuditNote(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.deleteEntity(t);
	}

	public List<KonkaXxAuditNote> getKonkaXxAuditNotePaginatedList(KonkaXxAuditNote t) {
		return this.konkaXxAuditNoteDao.selectEntityPaginatedList(t);
	}

}
