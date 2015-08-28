package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PeModule;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-24 10:17:10
 */
public interface PeModuleDao extends EntityDao<PeModule> {

	// add by liyuan
	List<PeModule> selectPeModuleParentList(PeModule t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @date 2011-02-25
	 */
	List<PeModule> selectPeModuleAllParentList(PeModule t) throws DataAccessException;
	
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-16
	 */
	List<PeModule> selectPeModuleForPeUserList(PeModule t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @version 2011-05-13
	 */
	List<PeModule> selectPeModuleListForAuthor() throws DataAccessException;

}
