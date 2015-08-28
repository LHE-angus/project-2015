package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MemberChangeCardInfoDao;
import com.ebiz.mmt.domain.MemberChangeCardInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-26 10:16:20
 */
@Service
public class MemberChangeCardInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<MemberChangeCardInfo> implements
		MemberChangeCardInfoDao {

	@SuppressWarnings("unchecked")
	public List<MemberChangeCardInfo> selectMemberChangeCardInfoHistoryList(MemberChangeCardInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMemberChangeCardInfoHistoryList", t);
	}

}
