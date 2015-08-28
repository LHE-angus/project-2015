package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCollectionDao;
import com.ebiz.mmt.dao.KonkaMobileStocksHisDao;
import com.ebiz.mmt.domain.KonkaMobileCollection;
import com.ebiz.mmt.domain.KonkaMobileStocksHis;
import com.ebiz.mmt.service.KonkaMobileStocksHisService;

@Service
public class KonkaMobileStocksHisServiceImpl implements
		KonkaMobileStocksHisService {

	@Resource
	private KonkaMobileStocksHisDao konkaMobileStocksHisDao;
	@Resource
	private KonkaMobileCollectionDao konkaMobileCollectionDao;

	public Long createKonkaMobileStocksHis(KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.insertEntity(t);
	}

	public KonkaMobileStocksHis getKonkaMobileStocksHis(KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.selectEntity(t);
	}

	public Long sendRecKonkaMobileStocksHis(KonkaMobileStocksHis t) {
		KonkaMobileCollection _t = new KonkaMobileCollection();
		_t.setColl_id(t.getColl_id());
		_t = this.konkaMobileCollectionDao.selectEntity(_t);
		if (t.getOp_type() == 0l)
			_t.setBusi_num(_t.getBusi_num().subtract(t.getOp_num()));
		else if (t.getOp_type() == 1l)
			_t.setBusi_num(_t.getBusi_num().add(t.getOp_num()));
		this.konkaMobileCollectionDao.updateEntity(_t);
		return this.konkaMobileStocksHisDao.insertEntity(t);
	}

	public Long resetKonkaMobileStocksHis(KonkaMobileStocksHis t,
			BigDecimal old_c_num, BigDecimal busi_num) {
		KonkaMobileCollection _t = new KonkaMobileCollection();
		_t.setColl_id(t.getColl_id());
		_t.setBusi_num(busi_num.subtract(old_c_num.subtract(t.getOp_num())));
		this.konkaMobileCollectionDao.updateEntity(_t);
		return this.konkaMobileStocksHisDao.insertEntity(t);
	}

	public Long getKonkaMobileStocksHisCount(KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.selectEntityCount(t);
	}

	public List<KonkaMobileStocksHis> getKonkaMobileStocksHisList(
			KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.selectEntityList(t);
	}

	public int modifyKonkaMobileStocksHis(KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.updateEntity(t);
	}

	public int removeKonkaMobileStocksHis(KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.deleteEntity(t);
	}

	public List<KonkaMobileStocksHis> getKonkaMobileStocksHisPaginatedList(
			KonkaMobileStocksHis t) {
		return this.konkaMobileStocksHisDao.selectEntityPaginatedList(t);
	}

}
