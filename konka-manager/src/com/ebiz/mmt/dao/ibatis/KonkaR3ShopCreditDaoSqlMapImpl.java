package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopCreditDao;
import com.ebiz.mmt.domain.KonkaR3ShopCredit;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-04 15:52:53
 */
@Service
public class KonkaR3ShopCreditDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3ShopCredit> implements
		KonkaR3ShopCreditDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-10-30
	 */
	@Override
	public Long selectKonkaR3ShopCreditForRoleDataCount(KonkaR3ShopCredit t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3ShopCreditForRoleDataCount", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-30
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaR3ShopCredit> selectKonkaR3ShopCreditForRoleDataPaginatedList(KonkaR3ShopCredit t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3ShopCreditForRoleDataPaginatedList", t);
	}

}
