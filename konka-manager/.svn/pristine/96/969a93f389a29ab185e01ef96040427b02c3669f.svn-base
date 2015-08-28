package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseBrandInfoDao;
import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.service.BaseBrandInfoService;

/**
 * @author Jin,QingHua
 */
@Service
public class BaseBrandInfoServiceImpl implements BaseBrandInfoService {

	@Resource
	private BaseBrandInfoDao baseBrandInfoDao;

	public Long createBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.insertEntity(t);
	}

	public BaseBrandInfo getBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntity(t);
	}

	public Long getBaseBrandInfoCount(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntityCount(t);
	}

	public List<BaseBrandInfo> getBaseBrandInfoList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntityList(t);
	}

	public int modifyBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.updateEntity(t);
	}

	public int removeBaseBrandInfo(BaseBrandInfo t) {
		return this.baseBrandInfoDao.deleteEntity(t);
	}

	public List<BaseBrandInfo> getBaseBrandInfoPaginatedList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectEntityPaginatedList(t);
	}

	public List<BaseBrandInfo> getBaseBrandInfoForEntpShopList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectBaseBrandInfoForEntpShopList(t);
	}

	public List<BaseBrandInfo> getBaseBrandInfoByPdType(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectBaseBrandInfoByPdType(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-25
	 */
	public List<BaseBrandInfo> getBaseBrandInfoForjoinEntpProdList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectBaseBrandInfoForjoinEntpProdList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public BaseBrandInfo getBaseBrandInfoForjoinEntpProd(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectBaseBrandInfoForjoinEntpProd(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	public List<BaseBrandInfo> getBaseBrandInfoForPopList(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectBaseBrandInfoForPopList(t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-1-24
	 */
	public Long getBaseBrandInfoForjoinEntpProdCount(BaseBrandInfo t) {
		return this.baseBrandInfoDao.selectBaseBrandInfoForjoinEntpProdCount(t);
	}
}
