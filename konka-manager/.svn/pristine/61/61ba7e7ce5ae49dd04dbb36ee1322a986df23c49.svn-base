package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsDetailsDao;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfScortsDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JfScortsDetails> implements JfScortsDetailsDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-26
	 */
	@SuppressWarnings("unchecked")
	public List<JfScortsDetails> selectJfScortsDetailsAndDeptNameList(JfScortsDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJfScortsDetailsAndDeptNameList", t);
	}

	@SuppressWarnings("unchecked")
	public List<JfScortsDetails> selectJfScortsDetailsAndDeptNameForMemberCardList(JfScortsDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectJfScortsDetailsAndDeptNameForMemberCardList", t);
	}
}
