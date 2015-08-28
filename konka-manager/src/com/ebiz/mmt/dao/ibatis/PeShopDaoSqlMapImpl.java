package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopDao;
import com.ebiz.mmt.domain.PeShop;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeShopDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeShop> implements PeShopDao {

	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.dao.PeShopDao#selectPeShopWithShopNamePaginatedList(com.ebiz.mmt.domain.PeShop)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PeShop> selectPeShopWithShopNamePaginatedList(PeShop peShop) {
		return this.getSqlMapClientTemplate().queryForList("selectPeShopWithShopNamePaginatedList", peShop);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.dao.PeShopDao#selectPeShopWithShopNameCount(com.ebiz.mmt.domain.PeShop)
	 */
	@Override
	public Long selectPeShopWithShopNameCount(PeShop peShop) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPeShopWithShopNameCount", peShop);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-19
	 */
	public Long selectPeShopAndEntpShopCount(PeShop t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPeShopAndEntpShopCount", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-19
	 */
	@SuppressWarnings("unchecked")
	public List<PeShop> selectPeShopAndEntpShopPaginatedList(PeShop t) {
		return this.getSqlMapClientTemplate().queryForList("selectPeShopAndEntpShopPaginatedList", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-05-20
	 */
	@SuppressWarnings("unchecked")
	public List<PeShop> selectPeShopWithShopNameAndNewsIdPaginatedList(PeShop t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectPeShopWithShopNameAndNewsIdPaginatedList", t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-05-20
	 */
	public Long selectPeShopWithShopNameAndNewsIdCount(PeShop t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPeShopWithShopNameAndNewsIdCount", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-07-06
	 */
	public void updatePeShopInit(PeShop t) throws DataAccessException {
		this.getSqlMapClientTemplate().update("updatePeShopInit", t);
	}
}
