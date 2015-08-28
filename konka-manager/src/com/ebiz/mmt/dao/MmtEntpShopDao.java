package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Hui,Gang
 * @datetime 2011-09-29 15:54:30
 */
public interface MmtEntpShopDao extends EntityDao<MmtEntpShop> {

	List<MmtEntpShop> selectMmtEntpShopPaginatedListForSelect(MmtEntpShop t) throws DataAccessException;

	List<MmtEntpShop> selectMmtEntpShopNotRepeatPaginatedList(MmtEntpShop t) throws DataAccessException;

	Long selectMmtEntpShopNotRepeatCount(MmtEntpShop t);

	Long selectMmtEntpShop2Count(MmtEntpShop t);
}