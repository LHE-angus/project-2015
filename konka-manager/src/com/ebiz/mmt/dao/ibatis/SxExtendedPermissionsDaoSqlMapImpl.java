package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.SxExtendedPermissionsDao;
import com.ebiz.mmt.domain.SxExtendedPermissions;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class SxExtendedPermissionsDaoSqlMapImpl extends EntityDaoSqlMapImpl<SxExtendedPermissions> implements
		SxExtendedPermissionsDao {

	@SuppressWarnings("unchecked")
	public List<SxExtendedPermissions> selectSxExtednPermissionBrand(SxExtendedPermissions t) {
		return super.getSqlMapClientTemplate().queryForList("selectSxExtendedPermissionBrand", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SxExtendedPermissions> selectSxExtendedPermissionPd(SxExtendedPermissions t) {
		return super.getSqlMapClientTemplate().queryForList("selectSxExtendedPermissionPd", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SxExtendedPermissions> selectSxExtendedPermissionSupplier(SxExtendedPermissions t) {
		return super.getSqlMapClientTemplate().queryForList("selectSxExtendedPermissionSupplier", t);
	}

	/**
	 * @author Li,GuoHu
	 * @version 2011-07-07
	 */
	@SuppressWarnings("unchecked")
	public List<SxExtendedPermissions> selectSxExtendedPermissionWithClsJoinBrand(SxExtendedPermissions t) {
		return getSqlMapClientTemplate().queryForList("selectSxExtendedPermissionWithClsJoinBrand", t);
	}
}
