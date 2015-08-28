package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoDao;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Wu,Yang
 * @version 2011-11-25 11:00
 */
@Service
public class KonkaOrderInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfo> implements KonkaOrderInfoDao {

	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfo> selectKonkaOrderInfoResultForPaginatedList(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoResultForPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfo> selectKonkaOrderInfoPaginatedListWithShopName(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoPaginatedListWithShopName", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfo> selectKonkaOrderInfoListWithShopName(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoListWithShopName", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @date 2013-08-07
	 */
	public Long selectKonkaOrderInfoWithNoSysManCount(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoWithNoSysManCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfo> selectKonkaOrderInfoWithNoSysManPaginatedList(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoWithNoSysManPaginatedList", t);
	}

	public Long selectKonkaOrderInfoAndNextRoleCount(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoAndNextRoleCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfo> selectKonkaOrderInfoAndNextRoleResultForPaginatedList(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoAndNextRoleResultForPaginatedList", t);
	}

	public Long selectKonkaOrderInfoSearchforPdNameCount(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoSearchforPdNameCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaOrderInfo> selectKonkaOrderInfoResultForR3CodePaginatedList(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoResultForR3CodePaginatedList", t);
	}

	public Long selectKonkaOrderInfoSearchforR3CodeCount(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoSearchforR3CodeCount", t);
	}

	@Override
	public Long selectKonkaOrderInfoWithNoForFourWeekCount(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoWithForWeekCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfo> selectKonkaOrderInfoAndNextRoleResultForPaginatedList1(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate()
				.queryForList("selectKonkaOrderInfoAndNextRoleResultForPaginatedList1", t);
	}

	public Long selectKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaOrderInfoAndNextRoleResultForPaginatedListcount1", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfo> selectKonkaOrderInfoResultForR3CodeforCbPaginatedList(KonkaOrderInfo entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoResultForR3CodeforCbPaginatedList",
				entity);
	}

	@Override
	public Long selectKonkaOrderInfoSearchforR3CodeandforcbCount(KonkaOrderInfo entity) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaOrderInfoSearchforR3CodeandforcbCount", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfo> selectKonkaOrderInfoResultForR3CodeforCbList(KonkaOrderInfo entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoResultForR3CodeforCbList", entity);
	}

	@Override
	public List<HashMap> selectKonkaOrderInfoDetailsList(KonkaOrderInfo entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoDetailsMapList", entity);
	}

	@Override
	public Long selectKonkaOrderInfoDetailsCount(KonkaOrderInfo entity) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoDetailsListCount", entity);
	}

	@Override
	public List<HashMap> selectKonkaOrderInfoDetailsListCount(KonkaOrderInfo entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoDetailsListSum", entity);
	}

	/**
	 * 统计所有商品数量，所有商品总价（系统管理员）
	 * 
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	@Override
	public KonkaOrderInfo selectKonkaOrderInfoNumCountMondySum(KonkaOrderInfo entity) {
		
		return (KonkaOrderInfo) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoNumCountMondySum",
				entity);
	}

	/**
	 * 统计所有商品数量，所有商品总价（非管理员）
	 * 
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	@Override
	public KonkaOrderInfo selectKonkaOrderInfoNumCountMondySum1(KonkaOrderInfo entity) {
		
		return (KonkaOrderInfo) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoNumCountMondySum1",
				entity);
	}

	/**
	 * 统计所有商品数量，所有商品总价（详细）
	 * 
	 * @author Wang,KunLin
	 * @date 2014-9-23
	 * @param entity
	 * @return
	 */
	@Override
	public KonkaOrderInfo selectKonkaOrderInfoNumCountMondySum2(KonkaOrderInfo entity) {
		
		return (KonkaOrderInfo) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoNumCountMondySum2",
				entity);
	}

	@Override
	public List<KonkaOrderInfo> selectKonkaOrderInfoForIhsPaginatedList(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoForIhsPaginatedList", t);
	}

	@Override
	public List<HashMap> selectWaitForAuditOrderList(KonkaOrderInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectWaitForAuditOrderList", t);
	}

	@Override
	public Long selectKonkaOrderInfoForIhsCount(KonkaOrderInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoForIhsCount", t);
	}

}
