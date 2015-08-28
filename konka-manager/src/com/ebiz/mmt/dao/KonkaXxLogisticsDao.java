package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaXxLogistics;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:50
 */
public interface KonkaXxLogisticsDao extends EntityDao<KonkaXxLogistics> {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-06
	 */
	Long selectKonkaXxLogisticsWithPNameCount(KonkaXxLogistics t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-06
	 */
	List<KonkaXxLogistics> selectKonkaXxLogisticsWithPNamePaginatedList(KonkaXxLogistics t) throws DataAccessException;

}
