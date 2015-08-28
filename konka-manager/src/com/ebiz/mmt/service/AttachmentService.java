package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.Attachment;

/**
 * @author Hui,Gang
 */
public interface AttachmentService {

	Long createAttachment(Attachment t);

	int modifyAttachment(Attachment t);

	int removeAttachment(Attachment t);

	Attachment getAttachment(Attachment t);

	List<Attachment> getAttachmentList(Attachment t);

	Long getAttachmentCount(Attachment t);

	List<Attachment> getAttachmentPaginatedList(Attachment t);

}
