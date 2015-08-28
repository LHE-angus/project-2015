package com.ebiz.mmt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-23 16:05:41
 */
public interface PeProdUserService {

	Long createPeProdUser(PeProdUser t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-04-18
	 */
	Long createPeProdUserWithShop(PeProdUser t, List<KonkaMobileSpRelation> konkaMobileSpRelationList);

	int modifyPeProdUser(PeProdUser t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-04-18
	 */
	int modifyPeProdUserWithShop(PeProdUser t, List<KonkaMobileSpRelation> konkaMobileSpRelationList);

	int removePeProdUser(PeProdUser t);

	PeProdUser getPeProdUser(PeProdUser t);

	List<PeProdUser> getPeProdUserList(PeProdUser t);

	Long getPeProdUserCount(PeProdUser t);

	List<PeProdUser> getPeProdUserPaginatedList(PeProdUser t);

	Long getPeProdUserWithEntpNameCount(PeProdUser t);

	Long getKonkaUserListWithDeptNameForResultCount(PeProdUser t);

	List<PeProdUser> getPeProdUserWithEntpNamePaginatedList(PeProdUser t);

	List<PeProdUser> getPeProdUserWithRoleNameList(PeProdUser t);

	/**
	 * @author Chen,ChenLin
	 * @version 2011-05-13
	 */
	List<PeProdUser> getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(PeProdUser t);

	/**
	 * @author Li,Yuan
	 * @version 2011-07-05
	 */
	void channelSave(HttpServletRequest request, String prod_entp_id, String id, String[] exc_city_array,
	        String cls_ids_select, String[] brand_ids);

	List<PeProdUser> getKonkaUserListWithDeptNameForResultList(PeProdUser t);

	List<PeProdUser> getKonkaUserListWithDeptNameForLeaderResultList(PeProdUser t);

	List<PeProdUser> getPeProdUserListForSelectUser(PeProdUser t);

	/**
	 * @author Zheng,Kun
	 * @version 2012-1-9
	 */
	List<PeProdUser> getXxPeProdUserListUser(PeProdUser t);

	/**
	 * @author Zheng,Kun
	 * @version 2012-1-9
	 */
	List<PeProdUser> getPeProdUserWithRoleList(PeProdUser t);

	// 门店指定数据上报员
	List<PeProdUser> getPeProdUserListForShowInfo(PeProdUser t);

	Long getPeProdUserCountForShowInfo(PeProdUser t);

	List<PeProdUser> getPeProdUserPaginatedListForShowInfo(PeProdUser t);

	/**
	 * @author Ren,zhong
	 * @version 2012-03-16
	 */
	PeProdUser getPeProdUserResult(PeProdUser t);

	/**
	 * @author wang,yang
	 * @version 2012-05-17
	 */
	List<PeProdUser> getDeptListForSelectUser(PeProdUser t);

	/**
	 * @author Ren,zhong
	 * @date 2013-06-26
	 */
	int modifyPeProdUserWithMultiRoleUser(PeProdUser t);

	/**
	 * @author Hu,Hao
	 * @version 2013-07-03
	 */
	List<PeProdUser> getPeProdUserForUserNameList(PeProdUser t);

	/**
	 * @author Hu,Hao
	 * @version 2013-07-12
	 */
	List<PeProdUser> getPeProdUserListForGroup(PeProdUser t);

	/**
	 * @author Xing,XiuDong
	 * @version 2013-07-27
	 */
	Long createZmdUserRelation(PeProdUser t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	List<PeProdUser> getPeProdUserListByDeptIdAndRoleId(PeProdUser t);

	/**
	 * @author Pan,Gang
	 * @version 2013-08-24
	 */
	List<PeProdUser> getPeProdUserPaginatedListForRoleInfo(PeProdUser t);

	Long getPeProdUserForRoleInfoCount(PeProdUser t);

	List<PeProdUser> getPeProdUserForYwyAndCxyCount(PeProdUser t);

	public PeProdUser getPeProdUserForFgs(PeProdUser t);

	List<PeProdUser> getPeProdUserForCidList(PeProdUser t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-20
	 */
	public Long getpeProdUserWithPositionNameAndFullDeptNameCount(PeProdUser t);
	
	List<PeProdUser> getPeProdUserBy_id_deptid(PeProdUser t);
	
	/**
	 * 获取促销员信息详情
	 * @author Liang,HouEn
	 * 2014-10-30
	 * @param t
	 * @return
	 */
	PeProdUser getCXYPeProdUser(PeProdUser t);

    /**
     * 根据用户部门 和角色找人
     * @param t
     * @return
     */
    List<PeProdUser> getPeProdUserByDeptIdAndRoleIdResult(PeProdUser t);

	/**
	 * 登录校验用户数
	 * @author  Liang Houen
	 * @date 2015-08-05
	 * @param t
	 * @return
	 */
	List<PeProdUser> getLoginUserList(PeProdUser t);
	
}