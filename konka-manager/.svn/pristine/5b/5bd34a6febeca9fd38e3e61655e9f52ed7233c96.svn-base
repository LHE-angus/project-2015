package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmPriceLinesDao;
import com.ebiz.mmt.domain.CrmPriceLines;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class CrmPriceLinesDaoSqlMapImpl extends EntityDaoSqlMapImpl<CrmPriceLines> implements CrmPriceLinesDao {

	@Override
	public Long insertEntity(CrmPriceLines t) throws DataAccessException {
		if (t.getDiscount() == null) {
			t.setDiscount(0d);
		}
		if (t.getFl() == null) {
			t.setFl(0d);
		}
		if(t.getLowestprice()==null){
			t.setLowestprice(0d);
		}
		if (t.getMarketprice() == null) {
			t.setMarketprice(0d);
		}
		
		return super.insertEntity(t);
	}

	@Override
	public int updateEntity(CrmPriceLines t) throws DataAccessException {
		if (t.getDiscount() == null) {
			t.setDiscount(0d);
		}
		if (t.getFl() == null) {
			t.setFl(0d);
		}
		if (t.getLowestprice() == null) {
			t.setLowestprice(0d);
		}
		if (t.getMarketprice() == null) {
			t.setMarketprice(0d);
		}
		return super.updateEntity(t);
	}

}
