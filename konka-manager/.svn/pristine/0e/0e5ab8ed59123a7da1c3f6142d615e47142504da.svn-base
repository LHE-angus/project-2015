package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCommentInfoDao;
import com.ebiz.mmt.domain.KonkaCommentInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
@Service
public class KonkaCommentInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaCommentInfo> implements KonkaCommentInfoDao {

	@SuppressWarnings("unchecked")
	public List<KonkaCommentInfo> selectKonkaCommentInfoListFatherAndChildren(KonkaCommentInfo entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaCommentInfoListFatherAndChildren", entity);
	}

}
