package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdAccouts;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
public interface KonkaXxZmdAccoutsDao extends EntityDao<KonkaXxZmdAccouts> {

	/**
	 * @author hu,hao
	 * @version 2012-03-05
	 */
	Long selectKonkaXxZmdAccoutsForZmdAndDeptCount(KonkaXxZmdAccouts t);

	/**
	 * @author hu,hao
	 * @version 2012-03-05
	 */
	List<KonkaXxZmdAccouts> selectKonkaXxZmdAccoutsForZmdAndDeptPaginatedList(KonkaXxZmdAccouts t);
}
