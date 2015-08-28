package com.ebiz.mmt.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobilePdDao;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,zhong
 * @version 2013-05-31 14:00
 */
@Service
public class KonkaMobilePdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobilePd> implements KonkaMobilePdDao {

	@Override
	public List<KonkaMobilePd> selectKonkaMobilePdBrandList(KonkaMobilePd t1) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobilePdOnlyBrandList",t1);
	}

}
