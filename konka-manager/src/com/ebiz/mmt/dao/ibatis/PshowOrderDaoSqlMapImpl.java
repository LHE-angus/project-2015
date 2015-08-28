package com.ebiz.mmt.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PshowOrderDao;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:19:59
 */
@Service
public class PshowOrderDaoSqlMapImpl extends EntityDaoSqlMapImpl<PshowOrder> implements PshowOrderDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-15
	 */
	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrderIncludeDetailsList(PshowOrder t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrderIncludeDetailsList", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrderIncludeDetailsPaginatedList(PshowOrder t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrderIncludeDetailsPaginatedList", t);
	}

	/**
	 * @author Pan,Gang
	 * @version 2013-08-19
	 */
	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrdeForDeptNameAndFullNameList(PshowOrder t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeForDeptNameAndFullNameList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrdeForPdDetailsList(PshowOrder t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectPshowOrdeForPdDetailsList", t);
	}

	public Long selectPshowOrdeForDeptNameAndFullNameListCount(PshowOrder t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate()
		        .queryForObject("selectPshowOrdeForDeptNameAndFullNameListCount", t);
	}

	public Long selectPshowOrdeForPdDetailsListCount(PshowOrder t) throws DataAccessException {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectPshowOrdeForPdDetailsListCount", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-12
	 */
	public BigDecimal selectTotalPriceByOrderUserId(PshowOrder t) throws DataAccessException {
		return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("selectTotalPriceByOrderUserId", t);
	}

	/**
	 * @author tudp
	 * @version 2013-09-14
	 */
	public int updatePshowOrderForCancel(PshowOrder t) throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updatePshowOrderForCancel", t);
	}

	/**
	 * @author WangKunLin
	 * @version 2014-04-16
	 */
	public int updatePshowOrderForSwitchR3(PshowOrder t) throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updatePshowOrderForSwitchR3", t);
	}

	@Override
	public Long selectPshowOrderSubIncludeDetailsPaginatedListCount(PshowOrder t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
		        "selectPshowOrderSubIncludeDetailsPaginatedListCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrderSubIncludeDetailsPaginatedList(PshowOrder t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPshowOrderSubIncludeDetailsPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrderAndDetailsForTj(PshowOrder t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPshowOrderAndDetailsForTj", t);
	}

	@SuppressWarnings("unchecked")
	public List<PshowOrder> selectPshowOrderAndDetailsForTj2(PshowOrder t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectPshowOrderAndDetailsForTj2", t);
	}

}
