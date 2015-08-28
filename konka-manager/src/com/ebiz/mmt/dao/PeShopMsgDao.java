package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PeShopMsg;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-16 15:54:18
 */
public interface PeShopMsgDao extends EntityDao<PeShopMsg> {

	/**
	 * @author Zhang,ShiMing
	 * @version 2011-5-18 9:47:37
	 */
	List<PeShopMsg> selectPeShopMsgWithPar(PeShopMsg entity);

	/**
	 * @author Hui,Gang
	 * @version 2011-10-28 下午4:11:48
	 */
	Long selectPeShopMsgCountForReceive(PeShopMsg t) throws DataAccessException;

	/**
	 * @author Hui,Gang
	 * @version 2011-10-28 下午4:11:48
	 */
	List<PeShopMsg> selectPeShopMsgPaginatedListForReceive(PeShopMsg t) throws DataAccessException;
}
