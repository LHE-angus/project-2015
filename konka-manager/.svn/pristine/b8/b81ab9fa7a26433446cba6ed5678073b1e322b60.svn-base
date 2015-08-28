package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcFifoStocksDao;
import com.ebiz.mmt.domain.JxcFifoStocks;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-09 18:19:53
 */
@Service
public class JxcFifoStocksDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcFifoStocks> implements JxcFifoStocksDao {


	@Override
	public Long selectJxcFifoStocksManagerDetailsCount(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJxcFifoStocksManagerDetailsCount", t);
	}

	@Override
	public List<JxcFifoStocks> selectJxcFifoStocksManagerDetailsPaginatedList(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return super.getSqlMapClientTemplate().queryForList("selectJxcFifoStocksManagerDetailsPaginatedList", t);
	}

	@Override
	public Long selectJxcFifoStocksManagerAccountCount(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJxcFifoStocksManagerAccountCount", t);
	}

	@Override
	public List<JxcFifoStocks> selectJxcFifoStocksManagerAccountPaginatedList(JxcFifoStocks t) {
		// TODO Auto-generated method stub
		return super.getSqlMapClientTemplate().queryForList("selectJxcFifoStocksManagerAccountPaginatedList", t);
	}

	@Override
	public List<JxcFifoStocks> selectJxcFifoStocksViewDetailsList(JxcFifoStocks entity) {
		// TODO Auto-generated method stub
		return super.getSqlMapClientTemplate().queryForList("selectJxcFifoStocksViewDetailsList", entity);
	}

	@Override
	public List<JxcFifoStocks> selectJxcFifoStocksResultNumPrice(JxcFifoStocks entity) {
		return super.getSqlMapClientTemplate().queryForList("selectJxcFifoStocksResultNumPrice", entity);
	}
}
