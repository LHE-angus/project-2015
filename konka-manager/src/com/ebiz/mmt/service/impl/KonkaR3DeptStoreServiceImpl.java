package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3DeptStoreDao;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.service.KonkaR3DeptStoreService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-24 11:31:51
 */
@Service
public class KonkaR3DeptStoreServiceImpl implements KonkaR3DeptStoreService {

	@Resource
	private KonkaR3DeptStoreDao konkaR3DeptStoreDao;
	

	public Long createKonkaR3DeptStore(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.insertEntity(t);
	}

	public KonkaR3DeptStore getKonkaR3DeptStore(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.selectEntity(t);
	}

	public Long getKonkaR3DeptStoreCount(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.selectEntityCount(t);
	}

	public List<KonkaR3DeptStore> getKonkaR3DeptStoreList(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.selectEntityList(t);
	}

	public int modifyKonkaR3DeptStore(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.updateEntity(t);
	}

	public int removeKonkaR3DeptStore(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.deleteEntity(t);
	}

	public List<KonkaR3DeptStore> getKonkaR3DeptStorePaginatedList(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.selectEntityPaginatedList(t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-24
	 * @desc 分公司查询 
	 */
	public List<KonkaR3DeptStore> getKonkaR3DeptStoreForFgsNameList(KonkaR3DeptStore t){
		return this.konkaR3DeptStoreDao.selectKonkaR3DeptStoreForFgsNameList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-09-24
	 * @desc 工厂查询 
	 */
	@Override
	public List<KonkaR3DeptStore> getKonkaR3DeptStoreForFacSnList(KonkaR3DeptStore t) {
		return this.konkaR3DeptStoreDao.selectKonkaR3DeptStoreForFacSnList(t);
	}
}
