package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3DeptStockInfoDao;
import com.ebiz.mmt.domain.KonkaR3DeptStockInfo;
import com.ebiz.mmt.service.KonkaR3DeptStockInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-11 12:22:48
 */
@Service
public class KonkaR3DeptStockInfoServiceImpl implements KonkaR3DeptStockInfoService {

	@Resource
	private KonkaR3DeptStockInfoDao konkaR3DeptStockInfoDao;

	public Long createKonkaR3DeptStockInfo(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.insertEntity(t);
	}

	public KonkaR3DeptStockInfo getKonkaR3DeptStockInfo(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectEntity(t);
	}

	public Long getKonkaR3DeptStockInfoCount(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectEntityCount(t);
	}

	public List<KonkaR3DeptStockInfo> getKonkaR3DeptStockInfoList(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectEntityList(t);
	}

	public int modifyKonkaR3DeptStockInfo(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.updateEntity(t);
	}

	public int removeKonkaR3DeptStockInfo(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.deleteEntity(t);
	}

	public List<KonkaR3DeptStockInfo> getKonkaR3DeptStockInfoPaginatedList(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-13
	 */
	public List<KonkaR3DeptStockInfo> getKonkaR3DeptStockInfoListForClass(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectKonkaR3DeptStockInfoListForClass(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-13
	 */
	public List<KonkaR3DeptStockInfo> getKonkaR3DeptStockInfoListForZzl(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectKonkaR3DeptStockInfoListForZzl(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-16
	 */
	public List<KonkaR3DeptStockInfo> getKonkaR3DeptStockInfoListForFgsZzl(KonkaR3DeptStockInfo t) {
		return this.konkaR3DeptStockInfoDao.selectKonkaR3DeptStockInfoListForFgsZzl(t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-16
	 */
	public List<KonkaR3DeptStockInfo> getKonkaR3DeptStockInfoListForMaxDate(KonkaR3DeptStockInfo t){
		return this.konkaR3DeptStockInfoDao.selectKonkaR3DeptStockInfoListForMaxDate(t);
	}
}
