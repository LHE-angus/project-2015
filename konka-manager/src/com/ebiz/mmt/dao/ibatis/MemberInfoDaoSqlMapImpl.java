package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MemberInfoDao;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-26 11:10:27
 */
@Service
public class MemberInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<MemberInfo> implements MemberInfoDao {

	@SuppressWarnings("unchecked")
	public List<MemberInfo> selectMemberInfoForTotalScortsPaginatedList(MemberInfo t) {

		return super.getSqlMapClientTemplate().queryForList("selectMemberInfoForTotalScortsPaginatedList", t);
	}

}
