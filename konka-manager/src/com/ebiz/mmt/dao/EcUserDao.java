package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcUserDao extends EntityDao<EcUser> {

	public List<EcUser> selectEcUserWithPositionNameAndFullDeptNamePaginatedList(EcUser t) throws DataAccessException;

	public List<EcUser> selectEcUserPaginatedForHydjList(EcUser t) throws DataAccessException;

	public Long selectEcUserForHydjCount(EcUser t) throws DataAccessException;

	public int insertEcUser(EcUser t);

	public Long selectSubEcUserByUserNameCount(EcUser t) throws DataAccessException;

	public List<EcUser> selectSubEcUserByUserNameList(EcUser t) throws DataAccessException;

	public List<EcUser> selectEcUserNewPaginatedList(EcUser t) throws DataAccessException;

	public Long selectEcUserNewCount(EcUser t) throws DataAccessException;

	public Long selectEcUserNo(EcUser t) throws DataAccessException;

}
