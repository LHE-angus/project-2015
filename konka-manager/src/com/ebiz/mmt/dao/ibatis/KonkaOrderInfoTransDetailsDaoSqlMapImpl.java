package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoTransDetailsDao;
import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:39
 */
@Service
public class KonkaOrderInfoTransDetailsDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaOrderInfoTransDetails> implements KonkaOrderInfoTransDetailsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfoTransDetails> selectEntityPaginatedListTZD(
			KonkaOrderInfoTransDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoTransDetailsPaginatedListtzd", t);
	}

	@Override
	public Long selectKonkaOrderInfoTransDetailsPaginatedCountTZD(
			KonkaOrderInfoTransDetails t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoTransDetailsPaginatedcounttzd", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfoTransDetails> selectKonkaOrderInfoTransDetailsForConfirmList(
			KonkaOrderInfoTransDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoTransDetailsForConfirmList", t);
	}
	
	/**
	 * XiaoGuoJian
	 * 获取每单的每个型号已经发货的数量总和
	 * */
	@Override
	public Long selectKonkaOrderInfoTransDetailsForSumTransNum(
			KonkaOrderInfoTransDetails t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaOrderInfoTransDetailsForSumTransNum", t);
	}
	
	/**
	 * XiaoGuoJian
	 * 根据具体的发货单号查询即将结案的数据
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaOrderInfoTransDetails> selectKonkaOrderInfoTransDetailsForOver(
			KonkaOrderInfoTransDetails t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaOrderInfoTransDetailsForOver", t);
	}

}
