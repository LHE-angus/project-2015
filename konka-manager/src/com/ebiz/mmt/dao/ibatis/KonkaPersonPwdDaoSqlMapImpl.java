package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPersonPwdDao;
import com.ebiz.mmt.domain.KonkaPersonPwd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,zhong
 * @version 2013-07-26 11:06
 */
@Service
public class KonkaPersonPwdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPersonPwd> implements KonkaPersonPwdDao {

	/**
	 * @author Ren,zhong
	 * @date 2013-07-30
	 */
	public int updateKonkaPersonPwdLoginCount(KonkaPersonPwd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateKonkaPersonPwdLoginCount", t);
	}

}
