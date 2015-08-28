package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopMsgDao;
import com.ebiz.mmt.domain.PeShopMsg;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-16 15:54:19
 */
@Service
public class PeShopMsgDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeShopMsg> implements PeShopMsgDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PeShopMsg> selectPeShopMsgWithPar(PeShopMsg entity) {
		return this.getSqlMapClientTemplate().queryForList("selectPeShopMsgWithParPaginatedList", entity);
	}

	public Long selectPeShopMsgCountForReceive(PeShopMsg t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPeShopMsgCountForReceive", t);
	}

	@SuppressWarnings("unchecked")
	public List<PeShopMsg> selectPeShopMsgPaginatedListForReceive(PeShopMsg t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectPeShopMsgPaginatedListForReceive", t);
	}

}
