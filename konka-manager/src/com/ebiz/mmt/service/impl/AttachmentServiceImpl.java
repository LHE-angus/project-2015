package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.service.AttachmentService;

/**
 * @author Hui,Gang
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Resource
	private AttachmentDao attachmentDao;

	public Long createAttachment(Attachment t) {
		return this.attachmentDao.insertEntity(t);
	}
	
	public Attachment getAttachment(Attachment t) {
		return this.attachmentDao.selectEntity(t);
	}

	public Long getAttachmentCount(Attachment t) {
		return this.attachmentDao.selectEntityCount(t);
	}

	public List<Attachment> getAttachmentList(Attachment t) {
		return this.attachmentDao.selectEntityList(t);
	}

	public int modifyAttachment(Attachment t) {
		return this.attachmentDao.updateEntity(t);
	}

	public int removeAttachment(Attachment t) {
		return this.attachmentDao.deleteEntity(t);
	}

	public List<Attachment> getAttachmentPaginatedList(Attachment t) {
		return this.attachmentDao.selectEntityPaginatedList(t);
	}

}
