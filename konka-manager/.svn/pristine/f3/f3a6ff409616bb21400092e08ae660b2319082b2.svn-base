package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaoaDocInfoDao;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,ZhengYang
 * @date 2010-12-13 05:13:03
 */
@Repository
public class KonkaoaDocInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaoaDocInfo> implements KonkaoaDocInfoDao {
	@Override
	public Long selectKonkaoaDocInfoNoMax(KonkaoaDocInfo files) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaDocInfoNoMax", files);
	}
}
