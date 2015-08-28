package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasBuyerInfoDao;
import com.ebiz.mmt.domain.MdasBuyerInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-05 08:43:57
 */
@Service
public class MdasBuyerInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<MdasBuyerInfo> implements MdasBuyerInfoDao {

	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoSumList(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoSumList", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForXsCount(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoListForXsCount", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-08
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForBrandXsCount(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoListForBrandXsCount", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-09
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForMsg(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoListForMsg", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-10
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoCountList(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoCountList", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-11
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoForShopCount(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoForShopCount", t);
	}

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-08
	 */
	public Long selectEntpShopStatisticForTable(MdasBuyerInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectEntpShopStatisticForTable", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-15
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoListForBrandXsSales(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoListForBrandXsSales", t);
	}

	/**
	 * @author li,Yuan
	 * @version 2010-09-20
	 */
	public MdasBuyerInfo selectSumPrice(MdasBuyerInfo t) {
		return (MdasBuyerInfo) super.getSqlMapClientTemplate().queryForObject("selectSumPrice", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-26
	 */
	@SuppressWarnings("unchecked")
	public List<MdasBuyerInfo> selectMdasBuyerInfoForPdCount(MdasBuyerInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectMdasBuyerInfoForPdCount", t);
	}
}
