package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaDocRecipientDao;
import com.ebiz.mmt.domain.KonkaoaDocRecipient;
import com.ebiz.mmt.service.KonkaoaDocRecipientService;

/**
 * @author Xu,ZhengYang
 * @date 2010-12-13 05:13:04
 */

@Service
public class KonkaoaDocRecipientServiceImpl implements KonkaoaDocRecipientService {

	@Resource
	private KonkaoaDocRecipientDao docRecipientDao;

	public Long createKonkaoaDocRecipient(KonkaoaDocRecipient t) {
		return this.docRecipientDao.insertEntity(t);
	}

	public int modifyKonkaoaDocRecipient(KonkaoaDocRecipient t) {
		return this.docRecipientDao.updateEntity(t);
	}

	public int removeKonkaoaDocRecipient(KonkaoaDocRecipient t) {
		return this.docRecipientDao.deleteEntity(t);
	}

	public KonkaoaDocRecipient getKonkaoaDocRecipient(KonkaoaDocRecipient t) {
		return this.docRecipientDao.selectEntity(t);
	}

	public Long getKonkaoaDocRecipientCount(KonkaoaDocRecipient t) {
		return this.docRecipientDao.selectEntityCount(t);
	}

	public List<KonkaoaDocRecipient> getKonkaoaDocRecipientList(KonkaoaDocRecipient t) {
		return this.docRecipientDao.selectEntityList(t);
	}

	public List<KonkaoaDocRecipient> getKonkaoaDocRecipientPaginatedList(KonkaoaDocRecipient t) {
		return this.docRecipientDao.selectEntityPaginatedList(t);
	}
}
