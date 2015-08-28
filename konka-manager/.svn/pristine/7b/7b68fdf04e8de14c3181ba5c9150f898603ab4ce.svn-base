package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserAddrsDao;
import com.ebiz.mmt.domain.EcUserAddrs;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserAddrsDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcUserAddrs> implements EcUserAddrsDao {

	/**
	 * @author tudp 
	 * @throws 2013-09-10
	 */
	public int modifyEcUserAddrsForDefaultAddr(EcUserAddrs t) throws DataAccessException {
		int i=this.getSqlMapClientTemplate().update("updateEcUserAddrsForDefaultAddr", t);	
		if(t.getId()!=null){
		return this.getSqlMapClientTemplate().update("updateEcUserAddrs", t);
		}else{
		return	i;
		}
	}
	
}
