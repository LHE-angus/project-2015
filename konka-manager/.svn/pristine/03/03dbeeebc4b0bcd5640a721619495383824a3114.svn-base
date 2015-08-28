package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxStdPdDao;
import com.ebiz.mmt.domain.KonkaXxStdPd;
import com.ebiz.mmt.service.KonkaXxStdPdService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxStdPdServiceImpl implements KonkaXxStdPdService {

	@Resource
	private KonkaXxStdPdDao konkaXxStdPdDao;
	

	public Long createKonkaXxStdPd(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.insertEntity(t);
	}

	public KonkaXxStdPd getKonkaXxStdPd(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.selectEntity(t);
	}

	public Long getKonkaXxStdPdCount(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.selectEntityCount(t);
	}

	public List<KonkaXxStdPd> getKonkaXxStdPdList(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.selectEntityList(t);
	}

	public int modifyKonkaXxStdPd(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.updateEntity(t);
	}

	public int removeKonkaXxStdPd(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.deleteEntity(t);
	}

	public List<KonkaXxStdPd> getKonkaXxStdPdPaginatedList(KonkaXxStdPd t) {
		return this.konkaXxStdPdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @auhor Hu,Hao
	 * @version 2013-04-01
	 */
	public List<KonkaXxStdPd> getKonkaXxStdPdForPdNamePaginatedList(KonkaXxStdPd t){
		return this.konkaXxStdPdDao.selectKonkaXxStdPdForPdNamePaginatedList(t);
	}
	
}
