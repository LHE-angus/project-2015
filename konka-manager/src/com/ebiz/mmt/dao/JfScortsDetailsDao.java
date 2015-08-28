package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
public interface JfScortsDetailsDao extends EntityDao<JfScortsDetails> {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-26
	 */
	List<JfScortsDetails> selectJfScortsDetailsAndDeptNameList(JfScortsDetails t);

	/**
	 * 
	 * @author pan,gang
	 * @date 2013-8-2
	 */
	List<JfScortsDetails> selectJfScortsDetailsAndDeptNameForMemberCardList(JfScortsDetails t);
}
