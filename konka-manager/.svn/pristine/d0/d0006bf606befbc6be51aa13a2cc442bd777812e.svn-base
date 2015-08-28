package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAuditUserInfoDao;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-13 11:03:11
 */
@Service
public class KonkaXxZmdAuditUserInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdAuditUserInfo> implements
		KonkaXxZmdAuditUserInfoDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-04-24
	 */
	public Long selectKonkaXxZmdAuditUserInfoAndZmdCount(KonkaXxZmdAuditUserInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdAuditUserInfoAndZmdCount", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-24
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdAuditUserInfo> selectKonkaXxZmdAuditUserInfoAndZmdPaginatedList(KonkaXxZmdAuditUserInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdAuditUserInfoAndZmdPaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-02
	 */
	public Long selectKonkaXxZmdAndUserInfoCount(KonkaXxZmdAuditUserInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdAndUserInfoCount", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-02
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdAuditUserInfo> selectKonkaXxZmdAndUserInfoPaginatedList(KonkaXxZmdAuditUserInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdAndUserInfoPaginatedList", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2013-08-04
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdAuditUserInfo> selectKonkaXxZmdAuditUserInfoForRoleIdList(KonkaXxZmdAuditUserInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdAuditUserInfoForRoleIdList", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2013-09-06
	 */
	public Long selectKonkaXxZmdAndUserInfoZmdForCount(KonkaXxZmdAuditUserInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdAndUserInfoZmdForCount", t);
	}
}
