package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ebiz.mmt.domain.ChannelDataImport2;

public interface ChannelDataImport2Service {

	Long createChannelDataImport2(ChannelDataImport2 t);

	int modifyChannelDataImport2(ChannelDataImport2 t);

	int removeChannelDataImport2(ChannelDataImport2 t);

	ChannelDataImport2 getChannelDataImport2(ChannelDataImport2 t);

	// 无分页
	List<ChannelDataImport2> getChannelDataImport2List(ChannelDataImport2 t);

	Long getChannelDataImport2Count(ChannelDataImport2 t);

	// 有分页
	List<ChannelDataImport2> getChannelDataImport2PaginatedList(ChannelDataImport2 t);

	HashMap<String, BigDecimal> getChannelDataImport2AllCountAndAllMoney(ChannelDataImport2 t);

	void createChannelDataImport2List(List<ChannelDataImport2> t);

	void modifyChannelDataImport2List(List<ChannelDataImport2> t);


	/**
	 * 同步大客户,总部-分公司的订单明细
	 */
	HashMap<String, Long> createOrModifySyncChannelDataForzbTj(Set<String> syncVkorgList, String v_vtweg,
			String v_spart, String v_audat_begin, String v_audat_end, Set<String> ctmList) throws SQLException;

	/**
	 * 同步总部与分公司/客户订单明细数据(有KF交货单等明细数据)(有可能要弃用,新的接口以后可能用于实时的作查询用)
	 * 
	 * 一个分公司为单位的客户,一个月的同步
	 */
	@Deprecated
	HashMap<String, Long> createOrModifySyncChannelDataForzbMx(Set<String> syncVkorgList, String v_vtweg,
			String v_spart, String v_audat_begin, String v_audat_end, Set<String> ctmList) throws SQLException;

	/**
	 * 同步分公司/客户的订单明细数据(有KF交货单等明细数据)(有可能要弃用,新的接口以后可能用于实时的作查询用)
	 * 
	 * 一个销售组织一个月的同步
	 */
	@Deprecated
	HashMap<String, Long> createOrModifySyncChannelDataForfgsMx(Set<String> syncVkorgList, String v_vtweg,
			String v_spart, String v_audat_begin, String v_audat_end, String v_kunnr) throws SQLException;
	
	/**
	 * 彩电综合业绩排名 不含业绩划拨
	 * 
	 * @author Hu,Hao
	 * @version 2013-11-18
	 */
	List<ChannelDataImport2> getChannelDataImport2ForFgsTop(ChannelDataImport2 t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-23
	 */
	List<ChannelDataImport2> getChannelDataImport2ForFgsTop2(ChannelDataImport2 t);
	
	/**
	 * 彩电综合业绩排名 含业绩划拨
	 * 
	 * @author ZHOU
	 * @param t
	 * @return
	 */
	List<ChannelDataImport2> getChannelDataImport2ForFgsTop3(ChannelDataImport2 t);

}