package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfGiftInfoDao;
import com.ebiz.mmt.domain.JfGiftInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * 
 * @author pangang
 * @date 2013-6-26 
 */
@Service
public class JfGiftInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<JfGiftInfo> implements JfGiftInfoDao {

//	@Override
//	public Long SelectJfGiftInfoNum(JfGiftInfoDao t) {
//		return (Long) super.getSqlMapClientTemplate().queryForObject("SelectJfGiftInfoNum");
//	}

}
