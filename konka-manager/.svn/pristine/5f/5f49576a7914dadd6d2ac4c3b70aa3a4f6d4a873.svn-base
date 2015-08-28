package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3OrderDao;
import com.ebiz.mmt.domain.KonkaR3Order;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaR3OrderDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3Order> implements KonkaR3OrderDao {
	@Override
	public List<HashMap> getOrderData() {
		
		return super.getSqlMapClientTemplate().queryForList("selectR3Order");
	}

	@Override
	public Long setOrderData() {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("changeR3DataPro");
	}

	@Override
	public List<HashMap> selectKonkaR3OrderList(KonkaR3Order t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectR3OrderList",t);
	}
}
