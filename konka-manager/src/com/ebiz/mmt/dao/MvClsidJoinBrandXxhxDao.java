package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.MvClsidJoinBrandXxhx;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-13 11:37:04
 */
public interface MvClsidJoinBrandXxhxDao extends EntityDao<MvClsidJoinBrandXxhx> {

	/**
	 * @author Wu,ShangLong
	 * @version 2011.5.13
	 */
	List<MvClsidJoinBrandXxhx> selectBrandsByClsidsXxhxList(MvClsidJoinBrandXxhx t) throws DataAccessException;
}
