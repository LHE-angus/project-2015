package com.ebiz.mmt.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:19:59
 */
public interface PshowOrderDao extends EntityDao<PshowOrder> {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-15
	 */
	public abstract List<PshowOrder> selectPshowOrderIncludeDetailsList(PshowOrder t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	public abstract List<PshowOrder> selectPshowOrderIncludeDetailsPaginatedList(PshowOrder t)
	        throws DataAccessException;

	/**
	 * @author Pan,Gang
	 * @version 2013-08-16
	 */
	public abstract List<PshowOrder> selectPshowOrdeForDeptNameAndFullNameList(PshowOrder t) throws DataAccessException;

	public abstract List<PshowOrder> selectPshowOrdeForPdDetailsList(PshowOrder t) throws DataAccessException;

	public abstract Long selectPshowOrdeForDeptNameAndFullNameListCount(PshowOrder t) throws DataAccessException;

	public abstract Long selectPshowOrdeForPdDetailsListCount(PshowOrder t) throws DataAccessException;

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-12
	 */
	public abstract BigDecimal selectTotalPriceByOrderUserId(PshowOrder t) throws DataAccessException;

	public int updatePshowOrderForCancel(PshowOrder t) throws DataAccessException;

	public int updatePshowOrderForSwitchR3(PshowOrder t) throws DataAccessException;

	public Long selectPshowOrderSubIncludeDetailsPaginatedListCount(PshowOrder t) throws DataAccessException;

	public abstract List<PshowOrder> selectPshowOrderSubIncludeDetailsPaginatedList(PshowOrder t)
	        throws DataAccessException;

	public abstract List<PshowOrder> selectPshowOrderAndDetailsForTj(PshowOrder t) throws DataAccessException;

	public abstract List<PshowOrder> selectPshowOrderAndDetailsForTj2(PshowOrder t) throws DataAccessException;
}
