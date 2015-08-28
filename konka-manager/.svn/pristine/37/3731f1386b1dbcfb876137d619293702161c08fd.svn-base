package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasModPermissionDao;
import com.ebiz.mmt.domain.MdasModPermission;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
@Service
public class MdasModPermissionDaoSqlMapImpl extends EntityDaoSqlMapImpl<MdasModPermission> implements
		MdasModPermissionDao {

	public Long selectAreaLimitCount(MdasModPermission t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectAreaLimitCount", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-08-26
	 */

	public int deleteMdasModPermissionNotInModIds(MdasModPermission t) {
		return super.getSqlMapClientTemplate().delete("deleteMdasModPermissionNotInModIds", t);
	}

	@SuppressWarnings("unchecked")
	public List<MdasModPermission> selectAreaLimitPaginatedList(MdasModPermission t) {
		return super.getSqlMapClientTemplate().queryForList("selectAreaLimitPaginatedList", t);
	}

	public Long selectEntityByUserCount(MdasModPermission t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectMdasModPermissionByUserNameCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<MdasModPermission> selectEntityByUserNamePaginatedList(MdasModPermission t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasModPermissionByUserName", t);
	}

	public MdasModPermission selectEntityAndName(MdasModPermission t) {
		return (MdasModPermission) super.getSqlMapClientTemplate().queryForObject("selectMdasModPermissionAndUserName",
				t);
	}

	public int deleteMdasModPermissionInModIds(MdasModPermission t) {
		return super.getSqlMapClientTemplate().delete("deleteMdasModPermissionInModIds", t);
	}

	@SuppressWarnings("unchecked")
	public List<MdasModPermission> selectMdasModPermissionWithPNameList(MdasModPermission t) {
		return this.getSqlMapClientTemplate().queryForList("selectMdasModPermissionWithPNameList", t);
	}

	@SuppressWarnings("unchecked")
	public List<MdasModPermission> selectMdasModPermissionWithBrandNameList(MdasModPermission t) {
		return this.getSqlMapClientTemplate().queryForList("selectMdasModPermissionWithBrandNameList", t);
	}

	@SuppressWarnings("unchecked")
	public List<MdasModPermission> selectMdasModPermissionWithBrandNamePaginatedList(MdasModPermission t) {
		return this.getSqlMapClientTemplate().queryForList("selectMdasModPermissionWithBrandNamePaginatedList", t);
	}

	public Long selectMdasModPermissionWithBrandNameCount(MdasModPermission t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectMdasModPermissionWithBrandNameCount", t);
	}

}
