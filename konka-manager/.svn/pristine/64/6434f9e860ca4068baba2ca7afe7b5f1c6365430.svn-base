package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaoaDocInfoDao;
import com.ebiz.mmt.dao.KonkaoaDocRecipientDao;
import com.ebiz.mmt.dao.KonkaoaFilesContentDao;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaDocRecipient;
import com.ebiz.mmt.domain.KonkaoaFilesContent;
import com.ebiz.mmt.service.KonkaoaDocInfoService;

/**
 * @author Xu,ZhengYang
 * @date 2010-12-13 05:13:04
 */

@Service
public class KonkaoaDocInfoServiceImpl implements KonkaoaDocInfoService {

	@Resource
	private KonkaoaDocInfoDao docInfoDao;

	@Resource
	private KonkaPeAttachmentsDao konkaPeAttachmentsDao;

	@Resource
	private KonkaoaDocRecipientDao docRecipientDao;

	@Resource
	private KonkaoaFilesContentDao filesContentDao;

	// private Logger logger;

	public Long createKonkaoaDocInfo(KonkaoaDocInfo t) {
		Long id = this.docInfoDao.insertEntity(t);

		String link_user_ids = (String) t.getMap().get("link_user_ids");
		String link_users = (String) t.getMap().get("link_users");

		String link_dept_ids = (String) t.getMap().get("link_dept_ids");
		String link_depts = (String) t.getMap().get("link_depts");

		String[] link_user_id = StringUtils.split(link_user_ids, ",");
		String[] link_user = StringUtils.split(link_users, ",");

		String[] link_dept_id = StringUtils.split(link_dept_ids, ",");
		String[] link_dept = StringUtils.split(link_depts, ",");

		KonkaoaFilesContent filesContent = new KonkaoaFilesContent();
		filesContent.setLink_id(id);
		filesContent.setContent(t.getContent());
		this.filesContentDao.insertEntity(filesContent);

		// 附件
		List<KonkaPeAttachments> attachmentList = t.getAttachmentList();
		if (null != attachmentList) {
			for (KonkaPeAttachments attachment : attachmentList) {
				attachment.setLink_id(id);
				attachment.setIs_del(0l);
				this.konkaPeAttachmentsDao.insertEntity(attachment);
			}
		}

		if (StringUtils.isNotBlank(link_user_ids)) {
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setLink_id(id);
			docRecipient.setIs_view(0);
			for (int i = 0; i < link_user_id.length; i++) {
				docRecipient.setReceive_id(Long.valueOf(link_user_id[i]));
				docRecipient.setReceive_user(link_user[i]);
				docRecipient.setReceive_type(0);// 接收人
				docRecipient.setReceive_user_type(0);
				this.docRecipientDao.insertEntity(docRecipient);
			}
		}

		if (StringUtils.isNotBlank(link_dept_ids)) {
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setLink_id(id);
			docRecipient.setIs_view(0);
			for (int i = 0; i < link_dept_id.length; i++) {
				docRecipient.setReceive_id(Long.valueOf(link_dept_id[i]));
				docRecipient.setReceive_user(link_dept[i]);
				docRecipient.setReceive_type(0);// 接收人
				docRecipient.setReceive_user_type(1);
				this.docRecipientDao.insertEntity(docRecipient);
			}
		}

		return id;
	}

	public int modifyKonkaoaDocInfo(KonkaoaDocInfo t) {
		int count = this.docInfoDao.updateEntity(t);
		if (null != t.getContent()) {
			KonkaoaFilesContent filesContent = new KonkaoaFilesContent();
			filesContent.setLink_id(t.getId());
			filesContent.setContent(t.getContent());
			this.filesContentDao.updateEntity(filesContent);
		}
		List<KonkaPeAttachments> attachmentList = t.getAttachmentList();
		if (null != attachmentList) {
			for (KonkaPeAttachments attachment : attachmentList) {
				attachment.setLink_id(t.getId());
				attachment.setIs_del(0l);
				this.konkaPeAttachmentsDao.insertEntity(attachment);
			}
		}
		
		String link_user_ids = (String) t.getMap().get("link_user_ids");
		String link_users = (String) t.getMap().get("link_users");
		String link_dept_ids = (String) t.getMap().get("link_dept_ids");
		String link_depts = (String) t.getMap().get("link_depts");

		String[] link_user_id = StringUtils.split(link_user_ids, ",");
		String[] link_user = StringUtils.split(link_users, ",");
		String[] link_dept_id = StringUtils.split(link_dept_ids, ",");
		String[] link_dept = StringUtils.split(link_depts, ",");
		
        //先删除原有记录
		KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
		docRecipient.setLink_id(t.getId());
		this.docRecipientDao.deleteEntity(docRecipient);
		
		//再添加新的记录
		if (StringUtils.isNotBlank(link_user_ids)) {
			KonkaoaDocRecipient _docRecipient = new KonkaoaDocRecipient();
			_docRecipient.setLink_id(t.getId());
			_docRecipient.setIs_view(0);
			for (int i = 0; i < link_user_id.length; i++) {
				_docRecipient.setReceive_id(Long.valueOf(link_user_id[i]));
				_docRecipient.setReceive_user(link_user[i]);
				_docRecipient.setReceive_type(0);// 接收人
				_docRecipient.setReceive_user_type(0);
				this.docRecipientDao.insertEntity(_docRecipient);
			}
		}

		if (StringUtils.isNotBlank(link_dept_ids)) {
			KonkaoaDocRecipient _docRecipient = new KonkaoaDocRecipient();
			_docRecipient.setLink_id(t.getId());
			_docRecipient.setIs_view(0);
			for (int i = 0; i < link_dept_id.length; i++) {
				_docRecipient.setReceive_id(Long.valueOf(link_dept_id[i]));
				_docRecipient.setReceive_user(link_dept[i]);
				_docRecipient.setReceive_type(0);// 接收人
				_docRecipient.setReceive_user_type(1);
				this.docRecipientDao.insertEntity(_docRecipient);
			}
		}

		return count;
	}

	public int removeKonkaoaDocInfo(KonkaoaDocInfo t) {
		return this.docInfoDao.deleteEntity(t);
	}

	public KonkaoaDocInfo getKonkaoaDocInfo(KonkaoaDocInfo t) {
		return this.docInfoDao.selectEntity(t);
	}

	public Long getKonkaoaDocInfoCount(KonkaoaDocInfo t) {
		return this.docInfoDao.selectEntityCount(t);
	}

	public List<KonkaoaDocInfo> getKonkaoaDocInfoList(KonkaoaDocInfo t) {
		return this.docInfoDao.selectEntityList(t);
	}

	public List<KonkaoaDocInfo> getKonkaoaDocInfoPaginatedList(KonkaoaDocInfo t) {
		return this.docInfoDao.selectEntityPaginatedList(t);
	}
	
	@Override
	public Long getKonkaoaDocInfoNoMax(KonkaoaDocInfo t) {
		return this.docInfoDao.selectKonkaoaDocInfoNoMax(t);
	}
}
