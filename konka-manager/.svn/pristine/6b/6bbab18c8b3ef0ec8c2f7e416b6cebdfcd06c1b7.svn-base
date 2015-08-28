package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPropValItemDao;
import com.ebiz.mmt.domain.KonkaXxPropValItem;
import com.ebiz.mmt.service.KonkaXxPropValItemService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
@Service
public class KonkaXxPropValItemServiceImpl implements KonkaXxPropValItemService {

	@Resource
	private KonkaXxPropValItemDao konkaXxPropValItemDao;
	

	public Long createKonkaXxPropValItem(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.insertEntity(t);
	}

	public KonkaXxPropValItem getKonkaXxPropValItem(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.selectEntity(t);
	}

	public Long getKonkaXxPropValItemCount(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.selectEntityCount(t);
	}

	public List<KonkaXxPropValItem> getKonkaXxPropValItemList(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.selectEntityList(t);
	}

	public int modifyKonkaXxPropValItem(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.updateEntity(t);
	}

	public int removeKonkaXxPropValItem(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.deleteEntity(t);
	}

	public List<KonkaXxPropValItem> getKonkaXxPropValItemPaginatedList(KonkaXxPropValItem t) {
		return this.konkaXxPropValItemDao.selectEntityPaginatedList(t);
	}

}
