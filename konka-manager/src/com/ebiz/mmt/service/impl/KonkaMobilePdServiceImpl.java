package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobilePdDao;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.mmt.service.KonkaMobilePdService;

/**
 * @author Ren,zhong
 * @version 2013-05-31 14:00
 */
@Service
public class KonkaMobilePdServiceImpl implements KonkaMobilePdService {

	@Resource
	private KonkaMobilePdDao konkaMobilePdDao;
	

	public Long createKonkaMobilePd(KonkaMobilePd t) {
		return this.konkaMobilePdDao.insertEntity(t);
	}

	public KonkaMobilePd getKonkaMobilePd(KonkaMobilePd t) {
		return this.konkaMobilePdDao.selectEntity(t);
	}

	public Long getKonkaMobilePdCount(KonkaMobilePd t) {
		return this.konkaMobilePdDao.selectEntityCount(t);
	}

	public List<KonkaMobilePd> getKonkaMobilePdList(KonkaMobilePd t) {
		return this.konkaMobilePdDao.selectEntityList(t);
	}

	public int modifyKonkaMobilePd(KonkaMobilePd t) {
		return this.konkaMobilePdDao.updateEntity(t);
	}

	public int removeKonkaMobilePd(KonkaMobilePd t) {
		return this.konkaMobilePdDao.deleteEntity(t);
	}

	public List<KonkaMobilePd> getKonkaMobilePdPaginatedList(KonkaMobilePd t) {
		return this.konkaMobilePdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-05-31
	 */
	public Long createKonkaMobilePdForImport(KonkaMobilePd t) {
		List<KonkaMobilePd> list = t.getKonkaMobilePdList();
		if (null != list && list.size() > 0) {
			for (KonkaMobilePd konkaMobilePd : list) {
				KonkaMobilePd p = new KonkaMobilePd();
				p.setBrand_id(konkaMobilePd.getBrand_id());
				p.setMd_name(konkaMobilePd.getMd_name());
				if (0L < this.konkaMobilePdDao.selectEntityCount(p)) {
					// this.konkaMobilePdDao.updateEntity(konkaMobilePd);
					continue;
				}
				
				this.konkaMobilePdDao.insertEntity(konkaMobilePd);
			}
		}
		
		return 0L;
	}

	@Override
	public List<KonkaMobilePd> getKonkaMobilePdBrandList(KonkaMobilePd t1) {
		
	return	this.konkaMobilePdDao.selectKonkaMobilePdBrandList(t1);
	}

}
