package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSpRelationDao;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.service.KonkaMobileSpRelationService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-15 00:43:15
 */
@Service
public class KonkaMobileSpRelationServiceImpl implements KonkaMobileSpRelationService {

	@Resource
	private KonkaMobileSpRelationDao konkaMobileSpRelationDao;

	public Long createKonkaMobileSpRelation(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.insertEntity(t);
	}

	public KonkaMobileSpRelation getKonkaMobileSpRelation(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.selectEntity(t);
	}

	public Long getKonkaMobileSpRelationCount(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.selectEntityCount(t);
	}

	public List<KonkaMobileSpRelation> getKonkaMobileSpRelationList(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.selectEntityList(t);
	}

	public int modifyKonkaMobileSpRelation(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.updateEntity(t);
	}

	public int removeKonkaMobileSpRelation(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.deleteEntity(t);
	}

	public List<KonkaMobileSpRelation> getKonkaMobileSpRelationPaginatedList(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-04-18
	 */
	public List<KonkaMobileSpRelation> getKonkaMobileSpRelationInShopNameList(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.selectKonkaMobileSpRelationInShopNameList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-27
	 */
	public List<KonkaMobileSpRelation> getKonkaMobileSpRelationForCxyIdList(KonkaMobileSpRelation t) {
		return this.konkaMobileSpRelationDao.selectKonkaMobileSpRelationForCxyIdList(t);
	}

	public List<KonkaMobileSpRelation> getKonkaMobileSpRelationForCxyList(KonkaMobileSpRelation t)
	        throws DataAccessException {
		return this.konkaMobileSpRelationDao.selectKonkaMobileSpRelationForCxyList(t);
	}

}
