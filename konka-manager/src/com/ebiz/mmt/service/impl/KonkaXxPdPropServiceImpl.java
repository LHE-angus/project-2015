package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdPropDao;
import com.ebiz.mmt.domain.KonkaXxPdProp;
import com.ebiz.mmt.service.KonkaXxPdPropService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
@Service
public class KonkaXxPdPropServiceImpl implements KonkaXxPdPropService {

	@Resource
	private KonkaXxPdPropDao konkaXxPdPropDao;
	

	public Long createKonkaXxPdProp(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.insertEntity(t);
	}

	public KonkaXxPdProp getKonkaXxPdProp(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.selectEntity(t);
	}

	public Long getKonkaXxPdPropCount(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.selectEntityCount(t);
	}

	public List<KonkaXxPdProp> getKonkaXxPdPropList(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.selectEntityList(t);
	}

	public int modifyKonkaXxPdProp(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.updateEntity(t);
	}

	public int removeKonkaXxPdProp(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.deleteEntity(t);
	}

	public List<KonkaXxPdProp> getKonkaXxPdPropPaginatedList(KonkaXxPdProp t) {
		return this.konkaXxPdPropDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-05
	 */
	public List<KonkaXxPdProp> getKonkaXxPdPropWithTreeNameList(KonkaXxPdProp t){
		return this.konkaXxPdPropDao.selectKonkaXxPdPropWithTreeNameList(t);
	}
	
}
