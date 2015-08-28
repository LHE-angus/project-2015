package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaEmFileContentDao;
import com.ebiz.mmt.dao.KonkaEmFileDao;
import com.ebiz.mmt.dao.KonkaEmFileReceiveUserDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaEmFile;
import com.ebiz.mmt.domain.KonkaEmFileContent;
import com.ebiz.mmt.domain.KonkaEmFileReceiveUser;
import com.ebiz.mmt.service.KonkaEmFileService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
@Service
public class KonkaEmFileServiceImpl implements KonkaEmFileService {

	@Resource
	private KonkaEmFileDao konkaEmFileDao;

	@Resource
	private KonkaEmFileContentDao konkaEmFileContentDao;

	@Resource
	private KonkaEmFileReceiveUserDao konkaEmFileReceiveUserDao;

	@Resource
	private AttachmentDao attachmentDao;

	public Long createKonkaEmFile(KonkaEmFile t) {
		return this.konkaEmFileDao.insertEntity(t);
	}

	public KonkaEmFile getKonkaEmFile(KonkaEmFile t) {
		return this.konkaEmFileDao.selectEntity(t);
	}

	public Long getKonkaEmFileCount(KonkaEmFile t) {
		return this.konkaEmFileDao.selectEntityCount(t);
	}

	public List<KonkaEmFile> getKonkaEmFileList(KonkaEmFile t) {
		return this.konkaEmFileDao.selectEntityList(t);
	}

	public int modifyKonkaEmFile(KonkaEmFile t) {
		return this.konkaEmFileDao.updateEntity(t);
	}

	public int removeKonkaEmFile(KonkaEmFile t) {
		return this.konkaEmFileDao.deleteEntity(t);
	}

	public List<KonkaEmFile> getKonkaEmFilePaginatedList(KonkaEmFile t) {
		return this.konkaEmFileDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	public Long createKonkaEmFileIncludeAll(KonkaEmFile t) {
		// 文件主体insert
		Long id = this.konkaEmFileDao.insertEntity(t);
		// 文件内容insert
		if (t.getKonkaEmFileContent() != null) {
			t.getKonkaEmFileContent().setLink_id(id);
			this.konkaEmFileContentDao.insertEntity(t.getKonkaEmFileContent());
		}
		// 下发对象insert
		if (t.getKonkaEmFileReceiveUserList() != null && t.getKonkaEmFileReceiveUserList().size() > 0) {
			for (int i = 0; i < t.getKonkaEmFileReceiveUserList().size(); i++) {
				KonkaEmFileReceiveUser u = new KonkaEmFileReceiveUser();
				u = t.getKonkaEmFileReceiveUserList().get(i);
				u.setLink_id(id);
				this.konkaEmFileReceiveUserDao.insertEntity(u);
			}
		}
		// 附件insert
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_EM_FILE");
				a.setLink_id(id);
				this.attachmentDao.insertEntity(a);
			}
		}
		return id;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	public int modifyKonkaEmFileIncludeAll(KonkaEmFile t) {
		// 先删除文件内容
		KonkaEmFileContent kc = new KonkaEmFileContent();
		kc.setLink_id(t.getId());
		this.konkaEmFileContentDao.deleteKonkaEmFileContentByLinkId(kc);
		// 文件内容insert
		if (t.getKonkaEmFileContent() != null) {
			t.getKonkaEmFileContent().setLink_id(t.getId());
			this.konkaEmFileContentDao.insertEntity(t.getKonkaEmFileContent());
		}
		// 先删除下发对象
		KonkaEmFileReceiveUser ku = new KonkaEmFileReceiveUser();
		ku.setLink_id(t.getId());
		this.konkaEmFileReceiveUserDao.deleteKonkaEmFileReceiveUserByLinkId(ku);
		// 下发对象insert
		if (t.getKonkaEmFileReceiveUserList() != null && t.getKonkaEmFileReceiveUserList().size() > 0) {
			for (int i = 0; i < t.getKonkaEmFileReceiveUserList().size(); i++) {
				KonkaEmFileReceiveUser u = new KonkaEmFileReceiveUser();
				u = t.getKonkaEmFileReceiveUserList().get(i);
				u.setLink_id(t.getId());
				this.konkaEmFileReceiveUserDao.insertEntity(u);
			}
		}
		// 附件insert
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_EM_FILE");
				a.setLink_id(t.getId());
				this.attachmentDao.insertEntity(a);
			}
		}

		// 文件主体update
		return this.konkaEmFileDao.updateEntity(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	public Long getKonkaEmFileForViewCount(KonkaEmFile t) {
		return this.konkaEmFileDao.selectKonkaEmFileForViewCount(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	public List<KonkaEmFile> getKonkaEmFileForViewPaginatedList(KonkaEmFile t) {
		return this.konkaEmFileDao.selectKonkaEmFileForViewPaginatedList(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-10-10
	 */
	public List<KonkaEmFile> getKonkaEmFileForViewList(KonkaEmFile t) {
		return this.konkaEmFileDao.selectKonkaEmFileForViewList(t);
	}

}
