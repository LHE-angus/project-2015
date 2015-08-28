package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcSellBillAttachments;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-08-20 16:45:34
 */
public interface JxcSellBillAttachmentsService {

	Long createJxcSellBillAttachments(JxcSellBillAttachments t);

	int modifyJxcSellBillAttachments(JxcSellBillAttachments t);

	int removeJxcSellBillAttachments(JxcSellBillAttachments t);

	JxcSellBillAttachments getJxcSellBillAttachments(JxcSellBillAttachments t);

	List<JxcSellBillAttachments> getJxcSellBillAttachmentsList(JxcSellBillAttachments t);

	Long getJxcSellBillAttachmentsCount(JxcSellBillAttachments t);

	List<JxcSellBillAttachments> getJxcSellBillAttachmentsPaginatedList(JxcSellBillAttachments t);

}