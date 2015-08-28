package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.MemberChangeCardInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-26 10:16:20
 */
public interface MemberChangeCardInfoDao extends EntityDao<MemberChangeCardInfo> {

	public List<MemberChangeCardInfo> selectMemberChangeCardInfoHistoryList(MemberChangeCardInfo t);

}
