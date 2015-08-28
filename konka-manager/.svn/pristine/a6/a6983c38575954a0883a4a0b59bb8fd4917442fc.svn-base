package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxGoodsDao;
import com.ebiz.mmt.domain.KonkaXxGoods;
import com.ebiz.mmt.service.KonkaXxGoodsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxGoodsServiceImpl implements KonkaXxGoodsService {

	@Resource
	private KonkaXxGoodsDao konkaXxGoodsDao;
	

	public Long createKonkaXxGoods(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.insertEntity(t);
	}

	public KonkaXxGoods getKonkaXxGoods(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.selectEntity(t);
	}

	public Long getKonkaXxGoodsCount(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.selectEntityCount(t);
	}

	public List<KonkaXxGoods> getKonkaXxGoodsList(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.selectEntityList(t);
	}

	public int modifyKonkaXxGoods(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.updateEntity(t);
	}

	public int removeKonkaXxGoods(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.deleteEntity(t);
	}

	public List<KonkaXxGoods> getKonkaXxGoodsPaginatedList(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-01-10
	 */
	public List<KonkaXxGoods> getKonkaXxGoodsPaginatedListIncludeRelevanceInfo(KonkaXxGoods t) {
		return this.konkaXxGoodsDao.selectKonkaXxGoodsPaginatedListIncludeRelevanceInfo(t);
	}

}
