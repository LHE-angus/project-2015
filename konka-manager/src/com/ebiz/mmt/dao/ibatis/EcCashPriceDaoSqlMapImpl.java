package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcCashPriceDao;
import com.ebiz.mmt.domain.EcCashPrice;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:42
 */
@Service
public class EcCashPriceDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcCashPrice> implements EcCashPriceDao {

	/**
	 * @author tudp
	 * @version 2013-12-20
	 */
	public String  insertEcCashPrice(List<EcCashPrice> list)throws DataAccessException { 
		String msg ="";
		for(int i=0;i<list.size();i++){
			EcCashPrice t=list.get(i); 
		    this.getSqlMapClientTemplate().insert("insertEcCashPrice", t);
			 
		}
		return msg;
	}
}
