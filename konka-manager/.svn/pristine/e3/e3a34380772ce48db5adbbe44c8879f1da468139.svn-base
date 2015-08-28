package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdGcysDao;
import com.ebiz.mmt.domain.KonkaXxZmdGcys;
import com.ebiz.mmt.service.KonkaXxZmdGcysService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-25 09:27:47
 */
@Service
public class KonkaXxZmdGcysServiceImpl implements KonkaXxZmdGcysService {

	@Resource
	private KonkaXxZmdGcysDao konkaXxZmdGcysDao;
	

	public Long createKonkaXxZmdGcys(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.insertEntity(t);
	}

	public KonkaXxZmdGcys getKonkaXxZmdGcys(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.selectEntity(t);
	}

	public Long getKonkaXxZmdGcysCount(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdGcys> getKonkaXxZmdGcysList(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdGcys(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.updateEntity(t);
	}

	public int removeKonkaXxZmdGcys(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.deleteEntity(t);
	}

	public List<KonkaXxZmdGcys> getKonkaXxZmdGcysPaginatedList(KonkaXxZmdGcys t) {
		return this.konkaXxZmdGcysDao.selectEntityPaginatedList(t);
	}

}
