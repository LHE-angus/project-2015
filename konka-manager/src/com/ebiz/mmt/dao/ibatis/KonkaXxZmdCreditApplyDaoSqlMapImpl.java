package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdCreditApplyDao;
import com.ebiz.mmt.domain.KonkaXxZmdCreditApply;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:50
 */
@Service
public class KonkaXxZmdCreditApplyDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdCreditApply> implements KonkaXxZmdCreditApplyDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-03-28
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdCreditApply> selectKonkaXxZmdCreditApplyForTypePaginatedList(KonkaXxZmdCreditApply t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdCreditApplyForTypePaginatedList", t);
	}
}
