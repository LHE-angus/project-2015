package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaStockDao;
import com.ebiz.mmt.domain.KonkaStock;
import com.ebiz.mmt.service.KonkaStockService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaStockServiceImpl implements KonkaStockService {

	@Resource
	private KonkaStockDao konkaStockDao;

	public Long createKonkaStock(KonkaStock t) {
		return this.konkaStockDao.insertEntity(t);
	}

	public KonkaStock getKonkaStock(KonkaStock t) {
		return this.konkaStockDao.selectEntity(t);
	}

	public Long getKonkaStockCount(KonkaStock t) {
		return this.konkaStockDao.selectEntityCount(t);
	}

	public List<KonkaStock> getKonkaStockList(KonkaStock t) {
		return this.konkaStockDao.selectEntityList(t);
	}

	public int modifyKonkaStock(KonkaStock t) {
		return this.konkaStockDao.updateEntity(t);
	}

	public int removeKonkaStock(KonkaStock t) {
		return this.konkaStockDao.deleteEntity(t);
	}

	public List<KonkaStock> getKonkaStockPaginatedList(KonkaStock t) {
		return this.konkaStockDao.selectEntityPaginatedList(t);
	}

}
