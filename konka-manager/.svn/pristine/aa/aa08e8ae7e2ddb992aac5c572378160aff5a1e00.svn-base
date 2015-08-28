package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserScoreRecDao;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcUserScoreRecDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcUserScoreRec> implements EcUserScoreRecDao {

	public String selectEcUserScoreRecForUserTotalScore(Long user_id) throws DataAccessException {
		return (String) this.getSqlMapClientTemplate().queryForObject("selectEcUserScoreRecForUserTotalScore", user_id);
	}

	public String selectEcUserScoreRecForPayJf(Long userId) {
		return (String) this.getSqlMapClientTemplate().queryForObject("selectEcUserScoreRecForPayJf", userId);
	}

	public String selectEcUserScoreRecForUserXFScore(Long userId) {
		return (String) this.getSqlMapClientTemplate().queryForObject("selectEcUserScoreRecForUserXFScore", userId);
	}
	
	public String selectEcUserScoreRecForUserJLScore(Long userId) {
		return (String) this.getSqlMapClientTemplate().queryForObject("selectEcUserScoreRecForUserJLScore", userId);
	}
}
