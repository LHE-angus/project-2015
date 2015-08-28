package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVoteOptionDao;
import com.ebiz.mmt.domain.EcVoteMain;
import com.ebiz.mmt.domain.EcVoteOption;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-29 14:02:24
 */
@Service
public class EcVoteOptionDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcVoteOption> implements EcVoteOptionDao {

	/**
	 * @author tudp
	 * @version 2014-10-30
	 */
	public int updateEcVoteOptionViewCounts(EcVoteOption t)throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateEcVoteOptionViewCounts", t);
	}
 
}
