package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBasePartner;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JBasePartnerService {

	Long createJBasePartner(JBasePartner t);

	int modifyJBasePartner(JBasePartner t);

	int removeJBasePartner(JBasePartner t);

	JBasePartner getJBasePartner(JBasePartner t);

	List<JBasePartner> getJBasePartnerList(JBasePartner t);
	
	

	Long getJBasePartnerCount(JBasePartner t);

	List<JBasePartner> getJBasePartnerPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2013-10-14
	 */
	List<JBasePartner> getJBasePartnerForBillList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	Long getJBasePartnerForLevelCount(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	List<JBasePartner> getJBasePartnerForLevelPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	List<JBasePartner> getJBasePartnerForSonPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	List<JBasePartner> getJBasePartnerForFgsKhPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	Long getJBasePartnerForFgsKhCount(JBasePartner t);
	
	/**
	 * @author Wang,KunLin
	 * @version 2014-03-31
	 */
	List<JBasePartner> getJBasePartnerListOnlyName(JBasePartner t);

	List<HashMap> getJBasePartnerMapList(JBasePartner t);
	
	/**
	 * 统计代理商网点总数
	 * @author Liang,HouEn
	 * 2014-9-1
	 * @param t
	 * @return
	 */
	Long getJBasePartnerNewListCount(JBasePartner t);
	
	/**
	 * 代理商网点列表新版查询
	 * @author Liang,HouEn
	 * 2014-9-1
	 * @param t
	 * @return
	 */
	List<HashMap> getJBasePartnerNewList(JBasePartner t);
	
	/**
	 * 查询网点级别
	 * @author Liang,HouEn
	 * 2014-9-12
	 * @return
	 */
	List<HashMap> getJBasePartnerLevelList();
	
	/**
	 * 根据网点编码获取业务员信息及部门信息
	 * @author Liang Houen
	 * @since 2015-07-01
	 * @param partner_sn
	 * @return
	 */
	HashMap getYWYAndDept(String partner_sn);
	
	/**
	 * 根据客户id，网点级别查询该级别下的网点数量
	 * @author Liang Houen
	 * @since 2015-7-14
	 * @param c_id
	 * @param level
	 * @return
	 */
	Long getAgentsByLevel(Long c_id, Long level);
}
