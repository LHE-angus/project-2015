package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSailDataBillDao;
import com.ebiz.mmt.domain.KonkaMobileSailDataBill;
import com.ebiz.mmt.service.KonkaMobileSailDataBillService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-22 10:56:34
 */
@Service
public class KonkaMobileSailDataBillServiceImpl implements KonkaMobileSailDataBillService {

	@Resource
	private KonkaMobileSailDataBillDao konkaMobileSailDataBillDao;

	public Long createKonkaMobileSailDataBill(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.insertEntity(t);
	}

	public KonkaMobileSailDataBill getKonkaMobileSailDataBill(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.selectEntity(t);
	}

	public Long getKonkaMobileSailDataBillCount(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.selectEntityCount(t);
	}

	public List<KonkaMobileSailDataBill> getKonkaMobileSailDataBillList(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.selectEntityList(t);
	}

	public int modifyKonkaMobileSailDataBill(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.updateEntity(t);
	}

	public int removeKonkaMobileSailDataBill(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.deleteEntity(t);
	}

	public List<KonkaMobileSailDataBill> getKonkaMobileSailDataBillPaginatedList(KonkaMobileSailDataBill t) {
		return this.konkaMobileSailDataBillDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaMobileSailDataBill> getKonkaMobileSailDataBillAndAttachmentBySailList(KonkaMobileSailDataBill t) {
		
		return this.konkaMobileSailDataBillDao.selectKonkaMobileSailDataBillAndAttachmentBySailList(t);
	}

	@Override
	public List<KonkaMobileSailDataBill> getKonkaMobileSailDataBillAndAttachmentList(KonkaMobileSailDataBill t) {
		
		return this.konkaMobileSailDataBillDao.selectKonkaMobileSailDataBillAndAttachmentList(t);
	}

}
