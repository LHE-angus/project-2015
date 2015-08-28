package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSailDataBillLoDao;
import com.ebiz.mmt.domain.KonkaMobileSailDataBillLo;
import com.ebiz.mmt.service.KonkaMobileSailDataBillLoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-22 10:56:34
 */
@Service
public class KonkaMobileSailDataBillLoServiceImpl implements KonkaMobileSailDataBillLoService {

	@Resource
	private KonkaMobileSailDataBillLoDao konkaMobileSailDataBillLoDao;
	

	public Long createKonkaMobileSailDataBillLo(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.insertEntity(t);
	}

	public KonkaMobileSailDataBillLo getKonkaMobileSailDataBillLo(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.selectEntity(t);
	}

	public Long getKonkaMobileSailDataBillLoCount(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.selectEntityCount(t);
	}

	public List<KonkaMobileSailDataBillLo> getKonkaMobileSailDataBillLoList(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.selectEntityList(t);
	}

	public int modifyKonkaMobileSailDataBillLo(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.updateEntity(t);
	}

	public int removeKonkaMobileSailDataBillLo(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.deleteEntity(t);
	}

	public List<KonkaMobileSailDataBillLo> getKonkaMobileSailDataBillLoPaginatedList(KonkaMobileSailDataBillLo t) {
		return this.konkaMobileSailDataBillLoDao.selectEntityPaginatedList(t);
	}

}
