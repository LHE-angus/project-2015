package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardApplyDao;
import com.ebiz.mmt.domain.EcBaseCardApply;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-31 11:30:18
 */
@Service
public class EcBaseCardApplyDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBaseCardApply> implements EcBaseCardApplyDao {

	public Long selectApplyCardNoCount(EcBaseCardApply t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectApplyCardNoCount", t);
	}

}
