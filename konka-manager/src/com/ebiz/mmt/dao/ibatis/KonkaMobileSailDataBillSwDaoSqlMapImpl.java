package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSailDataBillSwDao;
import com.ebiz.mmt.domain.KonkaMobileSailDataBillSw;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-18 10:36:59
 */
@Service
public class KonkaMobileSailDataBillSwDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileSailDataBillSw> implements
		KonkaMobileSailDataBillSwDao {

	@Override
	public Long selectKonkaMobileSailDataBillSwAndBillCount(KonkaMobileSailDataBillSw entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileSailDataBillSwAndBillCount",
				entity);
	}

	@Override
	public List<KonkaMobileSailDataBillSw> selectKonkaMobileSailDataBillSwAndBillPaginatedList(
			KonkaMobileSailDataBillSw entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataBillSwAndBillPaginatedList",
				entity);
	}
	//已转订单查询分页计数
	@Override
	public Long selectKonkaMobileSailDataBillSwAndBillForSwitchedCount(
			KonkaMobileSailDataBillSw entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileSailDataBillSwAndBillForSwitchedCount",
				entity);
	}
	//已转订单查询PageList
	@Override
	public List<KonkaMobileSailDataBillSw> selectKonkaMobileSailDataBillSwAndBillForSwitchedPaginatedList(
			KonkaMobileSailDataBillSw entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataBillSwAndBillForSwitchedPaginatedList",
				entity);
	}

}
