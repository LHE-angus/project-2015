package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcSellBillAttachmentsDao;
import com.ebiz.mmt.domain.JxcSellBillAttachments;
import com.ebiz.mmt.service.JxcSellBillAttachmentsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-08-20 16:45:34
 */
@Service
public class JxcSellBillAttachmentsServiceImpl implements JxcSellBillAttachmentsService {

	@Resource
	private JxcSellBillAttachmentsDao jxcSellBillAttachmentsDao;
	

	public Long createJxcSellBillAttachments(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.insertEntity(t);
	}

	public JxcSellBillAttachments getJxcSellBillAttachments(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.selectEntity(t);
	}

	public Long getJxcSellBillAttachmentsCount(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.selectEntityCount(t);
	}

	public List<JxcSellBillAttachments> getJxcSellBillAttachmentsList(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.selectEntityList(t);
	}

	public int modifyJxcSellBillAttachments(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.updateEntity(t);
	}

	public int removeJxcSellBillAttachments(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.deleteEntity(t);
	}

	public List<JxcSellBillAttachments> getJxcSellBillAttachmentsPaginatedList(JxcSellBillAttachments t) {
		return this.jxcSellBillAttachmentsDao.selectEntityPaginatedList(t);
	}

}
