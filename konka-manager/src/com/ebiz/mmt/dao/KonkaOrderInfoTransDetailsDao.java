package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:38
 */
public interface KonkaOrderInfoTransDetailsDao extends EntityDao<KonkaOrderInfoTransDetails> {

	List<KonkaOrderInfoTransDetails> selectEntityPaginatedListTZD(
			KonkaOrderInfoTransDetails t);

	Long selectKonkaOrderInfoTransDetailsPaginatedCountTZD(
			KonkaOrderInfoTransDetails t);
	/**
	 * XiaoGuoJian  取确认页面的数据
	 */
	List<KonkaOrderInfoTransDetails> selectKonkaOrderInfoTransDetailsForConfirmList(
			KonkaOrderInfoTransDetails t);

	/**
	 * XiaoGuoJian
	 * 获取每单的每个型号已经发货的数量总和
	 * */
	Long selectKonkaOrderInfoTransDetailsForSumTransNum(
			KonkaOrderInfoTransDetails t);
	
	/**
	 * XiaoGuoJian
	 * 根据具体的发货单号查询即将结案的数据
	 * */
	List<KonkaOrderInfoTransDetails> selectKonkaOrderInfoTransDetailsForOver(
			KonkaOrderInfoTransDetails t);
}
