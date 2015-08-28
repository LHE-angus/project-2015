package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaZles23ResultInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-25 15:50:31
 */
public interface KonkaZles23ResultInfoDao extends EntityDao<KonkaZles23ResultInfo> {
	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-12
	 * @desc 获取集采订单客户端记录数
	 */
	public Long selectKonkaZles23ResultInfoForCustomerCount(KonkaZles23ResultInfo t);

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-12
	 * @desc 获取集采订单客户端记录
	 */
	public List<KonkaZles23ResultInfo> selectKonkaZles23ResultInfoForCustomerPaginatedList(KonkaZles23ResultInfo t);

	/**
	 * 获取转储单单号非空并且没有完整交货的集采订单 <br>
	 * <p>
	 * EBELN IS NOT NULL AND MATNR IS NOT NULL AND EBELP IS NOT NULL and MENGE
	 * != LFIMG and BEDAT >= add_months(sysdate,-2)
	 * </p>
	 * 
	 * @return
	 */
	public List<KonkaZles23ResultInfo> selectKonkaZles23ResultInfoListWithEbeln();

	
	/**
	 * 处理集采订单进入先进先出的仓库
	 * @param resultInfo
	 * @return
	 */
	public List<KonkaZles23ResultInfo> selectKonkaZles23ResultInfoForShockInList(
			KonkaZles23ResultInfo resultInfo);

}
