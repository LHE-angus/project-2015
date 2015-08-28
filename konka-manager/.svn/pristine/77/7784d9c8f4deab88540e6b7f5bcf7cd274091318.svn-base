package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopDao;
import com.ebiz.mmt.domain.PeShop;
import com.ebiz.mmt.service.PeShopService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeShopServiceImpl implements PeShopService {

	@Resource
	private PeShopDao peShopDao;

	public Long createPeShop(PeShop t) {
		return this.peShopDao.insertEntity(t);
	}

	public PeShop getPeShop(PeShop t) {
		return this.peShopDao.selectEntity(t);
	}

	public Long getPeShopCount(PeShop t) {
		return this.peShopDao.selectEntityCount(t);
	}

	public List<PeShop> getPeShopList(PeShop t) {
		return this.peShopDao.selectEntityList(t);
	}

	public int modifyPeShop(PeShop t) {
		return this.peShopDao.updateEntity(t);
	}

	public int removePeShop(PeShop t) {
		return this.peShopDao.deleteEntity(t);
	}

	public List<PeShop> getPeShopPaginatedList(PeShop t) {
		return this.peShopDao.selectEntityPaginatedList(t);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.service.PeShopService#getPeShopWithShopNamePaginatedList(com.ebiz.mmt.domain.PeShop)
	 */
	@Override
	public List<PeShop> getPeShopWithShopNamePaginatedList(PeShop peShop) {
		return this.peShopDao.selectPeShopWithShopNamePaginatedList(peShop);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.service.PeShopService#getPeShopWithShopNameCount(com.ebiz.mmt.domain.PeShop)
	 */
	@Override
	public Long getPeShopWithShopNameCount(PeShop peShop) {
		return this.peShopDao.selectPeShopWithShopNameCount(peShop);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-19
	 */
	public Long getPeShopAndEntpShopCount(PeShop t) {
		return this.peShopDao.selectPeShopAndEntpShopCount(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-19
	 */
	public List<PeShop> getPeShopAndEntpShopPaginatedList(PeShop t) {
		return this.peShopDao.selectPeShopAndEntpShopPaginatedList(t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-05-20
	 */
	public Long getPeShopWithShopNameAndNewsIdCount(PeShop t) {
		return this.peShopDao.selectPeShopWithShopNameAndNewsIdCount(t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-05-20
	 */
	public List<PeShop> getPeShopWithShopNameAndNewsIdPaginatedList(PeShop t) {
		return this.peShopDao.selectPeShopWithShopNameAndNewsIdPaginatedList(t);
	}
}
