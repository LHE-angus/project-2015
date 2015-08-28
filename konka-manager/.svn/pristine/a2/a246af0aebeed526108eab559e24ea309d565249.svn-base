package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaFgsCkSnDao;
import com.ebiz.mmt.domain.KonkaFgsCkSn;
import com.ebiz.mmt.service.KonkaFgsCkSnService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-21 09:53:26
 */
@Service
public class KonkaFgsCkSnServiceImpl implements KonkaFgsCkSnService {

	@Resource
	private KonkaFgsCkSnDao konkaFgsCkSnDao;
	

	public Long createKonkaFgsCkSn(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.insertEntity(t);
	}

	public KonkaFgsCkSn getKonkaFgsCkSn(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.selectEntity(t);
	}

	public Long getKonkaFgsCkSnCount(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.selectEntityCount(t);
	}

	public List<KonkaFgsCkSn> getKonkaFgsCkSnList(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.selectEntityList(t);
	}

	public int modifyKonkaFgsCkSn(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.updateEntity(t);
	}

	public int removeKonkaFgsCkSn(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.deleteEntity(t);
	}

	public List<KonkaFgsCkSn> getKonkaFgsCkSnPaginatedList(KonkaFgsCkSn t) {
		return this.konkaFgsCkSnDao.selectEntityPaginatedList(t);
	}

}
