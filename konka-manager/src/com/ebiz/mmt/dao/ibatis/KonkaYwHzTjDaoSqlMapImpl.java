package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYwHzTjDao;
import com.ebiz.mmt.domain.KonkaYwHzTj;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-23 17:18:32
 */
@Service
public class KonkaYwHzTjDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaYwHzTj> implements KonkaYwHzTjDao {

	@Override
	public void insertKonkaYwHzTjForTongJi(KonkaYwHzTj v) {
		super.getSqlMapClientTemplate().insert ("insertKonkaYwHzTjForTongJi", v);
	}

	@Override
	public HashMap selectLastUpdateTime(KonkaYwHzTj v) {
		return (HashMap)super.getSqlMapClientTemplate().queryForObject("selectLastUpdateTime", v);
	}

}
