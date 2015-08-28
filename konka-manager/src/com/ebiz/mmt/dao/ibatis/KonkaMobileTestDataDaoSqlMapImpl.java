package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileTestDataDao;
import com.ebiz.mmt.domain.KonkaMobileTestData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:58
 */
@Service
public class KonkaMobileTestDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileTestData> implements
        KonkaMobileTestDataDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileTestData> selectKonkaMobileTestDataAndSailDatasPaginatedList(KonkaMobileTestData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileTestDataAndSailDatasPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileTestData> selectKonkaMobileTestDataAndCodeList(KonkaMobileTestData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileTestDataAndCodeList", t);
	}
	
	
	public List<KonkaMobileTestData> selectKonkaMobileTestDataAndCode(KonkaMobileTestData t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileTestDataAndCode", t);
	}

}
