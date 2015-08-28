package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PeModPopedom;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public interface PeModPopedomDao extends EntityDao<PeModPopedom> {

	/**
	 * @author Ren Zhong
	 * @date 2011-5-18
	 */
	public List<PeModPopedom> selectModPopedomResultList(PeModPopedom t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 * @date 2011-07-05
	 */
	public void deletePeModPopedomInit(PeModPopedom t) throws DataAccessException;

}
