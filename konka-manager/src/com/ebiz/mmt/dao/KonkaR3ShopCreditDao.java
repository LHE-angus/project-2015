package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopCredit;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-04 15:52:53
 */
public interface KonkaR3ShopCreditDao extends EntityDao<KonkaR3ShopCredit> {

	/**
	 * @author Hu,Hao
	 * @version 2013-10-30
	 */
	Long selectKonkaR3ShopCreditForRoleDataCount(KonkaR3ShopCredit t);

	/**
	 * @author Hu,Hao
	 * @version 2013-10-30
	 */
	List<KonkaR3ShopCredit> selectKonkaR3ShopCreditForRoleDataPaginatedList(KonkaR3ShopCredit t);
}
