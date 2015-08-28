package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSpecFbUgdetailDao;
import com.ebiz.mmt.domain.EcSpecFbUgdetail;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
@Service
public class EcSpecFbUgdetailDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcSpecFbUgdetail> implements EcSpecFbUgdetailDao {

	@SuppressWarnings("unchecked")
	public List<EcSpecFbUgdetail> selectEcSpecFbUgdetailForTjList(EcSpecFbUgdetail t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcSpecFbUgdetailForTjList", t);
	}

	@SuppressWarnings("unchecked")
	public List<EcSpecFbUgdetail> selectEcSpecFbUgdetailForJlList(EcSpecFbUgdetail t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcSpecFbUgdetailForJlList", t);
	}

	@Override
	public List<EcSpecFbUgdetail> selectEcSpecFbUgdetailForExecList(EcSpecFbUgdetail t) {
		return super.getSqlMapClientTemplate().queryForList("selectEcSpecFbUgdetailForExecList", t);
	}

	public Long selectEcSpecFbUgdetailForTjCount(EcSpecFbUgdetail t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEcSpecFbUgdetailForTjCount", t);
	}

}
