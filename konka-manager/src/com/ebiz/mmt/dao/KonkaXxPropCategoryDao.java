package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPropCategory;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public interface KonkaXxPropCategoryDao extends EntityDao<KonkaXxPropCategory> {

	/**
	 * @author Hu,Hao
	 * @version 2013-04-03
	 */
	List<KonkaXxPropCategory> selectKonkaXxPropCategoryForPdNamePaginatedList(KonkaXxPropCategory t);
}
