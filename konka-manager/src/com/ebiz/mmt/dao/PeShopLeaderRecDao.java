package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.PeShopLeaderRec;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public interface PeShopLeaderRecDao extends EntityDao<PeShopLeaderRec> {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-23
	 */
	List<PeShopLeaderRec> selectPeShopLeaderRecWithNameList(PeShopLeaderRec t);

}
