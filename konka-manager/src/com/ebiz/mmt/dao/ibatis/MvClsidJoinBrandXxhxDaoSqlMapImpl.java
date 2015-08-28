package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvClsidJoinBrandXxhxDao;
import com.ebiz.mmt.domain.MvClsidJoinBrandXxhx;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-13 11:37:04
 */
@Service
public class MvClsidJoinBrandXxhxDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvClsidJoinBrandXxhx> implements
		MvClsidJoinBrandXxhxDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2011.5.13
	 */
	@SuppressWarnings("unchecked")
	public List<MvClsidJoinBrandXxhx> selectBrandsByClsidsXxhxList(MvClsidJoinBrandXxhx t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectBrandsByClsidsXxhxList", t);
	}
}
