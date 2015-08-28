package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptJsMoneyDao;
import com.ebiz.mmt.domain.KonkaDeptJsMoney;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-17 14:56:37
 */
@Service
public class KonkaDeptJsMoneyDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaDeptJsMoney> implements KonkaDeptJsMoneyDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-01-18
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaDeptJsMoney> selectKonkaDeptJsMoneyToR3List(KonkaDeptJsMoney t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaDeptJsMoneyToR3List", t);
	}
}
