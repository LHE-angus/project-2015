package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaZles23ResultInfoDao;
import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-25 15:50:31
 */
@Service
public class KonkaZles23ResultInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaZles23ResultInfo> implements
		KonkaZles23ResultInfoDao {

	@Override
	public Long selectKonkaZles23ResultInfoForCustomerCount(KonkaZles23ResultInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaZles23ResultInfoForCustomerCount", t);
	}

	@Override
	public List<KonkaZles23ResultInfo> selectKonkaZles23ResultInfoForCustomerPaginatedList(KonkaZles23ResultInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaZles23ResultInfoForCustomerPaginatedList", t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebiz.mmt.dao.KonkaZles23ResultInfoDao#
	 * selectKonkaZles23ResultInfoListWithEbeln()
	 */
	@Override
	public List<KonkaZles23ResultInfo> selectKonkaZles23ResultInfoListWithEbeln() {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaZles23ResultInfoListWithEbeln");
	}

	@Override
	public List<KonkaZles23ResultInfo> selectKonkaZles23ResultInfoForShockInList(
			KonkaZles23ResultInfo resultInfo) {
		// TODO Auto-generated method stub
		return super.getSqlMapClientTemplate().queryForList("selectKonkaZles23ResultInfoForShockInList",resultInfo);
	}

}
