package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBbYjhbDao;
import com.ebiz.mmt.domain.KonkaBbYjhb;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaBbYjhbDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaBbYjhb> implements KonkaBbYjhbDao {

	@Override
	public void insertEntityBatch(List<KonkaBbYjhb> konkaBbYjhbList) {
		for (KonkaBbYjhb t : konkaBbYjhbList) {
			super.insertEntity(t);
		}
	}

}
