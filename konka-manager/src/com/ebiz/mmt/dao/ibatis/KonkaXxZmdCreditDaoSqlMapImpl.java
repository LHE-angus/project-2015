package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdCreditDao;
import com.ebiz.mmt.domain.KonkaXxZmdCredit;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-27 09:57:28
 */
@Service
public class KonkaXxZmdCreditDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdCredit> implements KonkaXxZmdCreditDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-03-29
	 */
	public Long updateKonkaXxZmdCreditForZmdPro(KonkaXxZmdCredit t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("updateKonkaXxZmdCreditForZmdPro" ,t);
	}
}
