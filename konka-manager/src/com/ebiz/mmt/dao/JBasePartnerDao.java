package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JBasePartnerDao extends EntityDao<JBasePartner> {

	/**
	 * @author Hu,hao
	 * @version 2013-10-14
	 */
	List<JBasePartner> selectJBasePartnerForBillList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	Long selectJBasePartnerForLevelCount(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	List<JBasePartner> selectJBasePartnerForLevelPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	List<JBasePartner> selectJBasePartnerForSonPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	List<JBasePartner> selectJBasePartnerForFgsKhPaginatedList(JBasePartner t);

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	Long selectJBasePartnerForFgsKhCount(JBasePartner t);

/**
 * @author Wang,KunLin
 * @version 2014-03-31
 */
    List<JBasePartner> selectEntityListOnlyName(JBasePartner t);

    /**
     * 
     * @author Liang,HouEn
     * 2014-9-1
     * @param t
     * @return
     */
    List<HashMap> selectJBasePartnerMapList(JBasePartner t);
    
    /**
     * 
     * @author Liang,HouEn
     * 2014-9-1
     * @param t
     * @return
     */
    List<HashMap> selectJBasePartnerNewList(JBasePartner t);
    
    /**
     * 
     * @author Liang,HouEn
     * 2014-9-1
     * @param t
     * @return
     */
    Long selectJBasePartnerNewListCount(JBasePartner t);
    
    /**
     * 获取网点级别列表
     * @author Liang,HouEn
     * 2014-9-12
     * @return
     */
    List<HashMap> selectJBasePartnerLevelList();
    
    /**
     * 根据网点编码获取业务员信息
     * @param partner_sn
     * @return
     */
    HashMap getYWYAndDept(String partner_sn);
    
}


