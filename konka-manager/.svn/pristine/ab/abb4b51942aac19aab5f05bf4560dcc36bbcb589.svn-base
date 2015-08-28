package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileImpHisDao;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.mmt.service.KonkaMobileImpHisService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
@Service
public class KonkaMobileImpHisServiceImpl implements KonkaMobileImpHisService {

	@Resource
	private KonkaMobileImpHisDao konkaMobileImpHisDao;
	

	public Long createKonkaMobileImpHis(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.insertEntity(t);
	}

	public KonkaMobileImpHis getKonkaMobileImpHis(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.selectEntity(t);
	}

	public Long getKonkaMobileImpHisCount(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.selectEntityCount(t);
	}

	public List<KonkaMobileImpHis> getKonkaMobileImpHisList(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.selectEntityList(t);
	}

	public int modifyKonkaMobileImpHis(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.updateEntity(t);
	}

	public int removeKonkaMobileImpHis(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.deleteEntity(t);
	}

	public List<KonkaMobileImpHis> getKonkaMobileImpHisPaginatedList(KonkaMobileImpHis t) {
		return this.konkaMobileImpHisDao.selectEntityPaginatedList(t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	public Long modifyKonkaMobileImpDAtaPro(KonkaMobileImpHis t){
		return this.konkaMobileImpHisDao.updateKonkaMobileImpDAtaPro(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	public List<KonkaMobileImpHis> getKonkaMobileImpHisWithUserNameList(KonkaMobileImpHis t){
		return this.konkaMobileImpHisDao.selectKonkaMobileImpHisWithUserNameList(t);
	}
}
