package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-23 16:05:41
 */
public interface PeProdUserDao extends EntityDao<PeProdUser> {

	public Long selectPeProdUserWithEntpNameCount(PeProdUser t) throws DataAccessException;

	public List<PeProdUser> selectPeProdUserWithEntpNamePaginatedList(PeProdUser t) throws DataAccessException;

	public List<PeProdUser> selectpeProdUserWithPositionNameAndFullDeptNamePaginatedList(PeProdUser t)
	        throws DataAccessException;

	public List<PeProdUser> selectKonkaUserListWithDeptNameForResultList(PeProdUser t) throws DataAccessException;

	public List<PeProdUser> selectKonkaUserListWithDeptNameForLeaderResultList(PeProdUser t) throws DataAccessException;

	public Long selectKonkaUserListWithDeptNameForResultCount(PeProdUser t) throws DataAccessException;

	/**
	 * @desc 协同办公中选择用户方法
	 * @author Hui,Gang
	 * @version 2011-11-23 下午2:55:02
	 */
	public List<PeProdUser> selectPeProdUserListForSelectUser(PeProdUser t) throws DataAccessException;

	/**
	 * @desc 网点端站内信选择用户方法
	 * @author du,zhiming
	 * @version 2011-12-26
	 */
	public List<PeProdUser> selectPeProdUserWithRoleNameList(PeProdUser t) throws DataAccessException;

	/**
	 * @author Zheng,Kun
	 * @version 2012-1-9
	 */
	public List<PeProdUser> selectXxPeProdUserList(PeProdUser t) throws DataAccessException;

	/**
	 * @author Zheng,Kun
	 * @version 2012-1-9
	 */
	public List<PeProdUser> selectPeProdUserWithRoleList(PeProdUser t) throws DataAccessException;

	public List<PeProdUser> selectPeProdUserListForShowInfo(PeProdUser t) throws DataAccessException;

	public List<PeProdUser> selectPeProdUserPaginatedListForShowInfo(PeProdUser t) throws DataAccessException;

	public Long selectKonkaUserCountForShowInfo(PeProdUser t) throws DataAccessException;

	/**
	 * @author Ren,zhong
	 * @version 2012-03-16
	 */
	public PeProdUser selectPeProdUserResult(PeProdUser t) throws DataAccessException;

	/**
	 * @author wang,yang
	 * @version 2012-03-16
	 */
	public List<PeProdUser> selectDeptListForSelectUser(PeProdUser t) throws DataAccessException;

	/**
	 * @author Hu,Hao
	 * @version 2013-07-03
	 */
	List<PeProdUser> selectPeProdUserForUserNameList(PeProdUser t);

	/**
	 * @author Hu,Hao
	 * @version 2013-07-12
	 */
	List<PeProdUser> selectPeProdUserListForGroup(PeProdUser t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	List<PeProdUser> selectPeProdUserListByDeptIdAndRoleId(PeProdUser t);

	/**
	 * @author Pan,Gang
	 * @version 2013-08-24
	 */
	List<PeProdUser> selectPeProdUserPaginatedListForRoleInfo(PeProdUser t);

	public Long selectPeProdUserForRoleInfoCount(PeProdUser t);

	/**
	 * @author Pan,Gang
	 * @version 2013-08-29 统计部门的业务员，促销员，总人数
	 */
	List<PeProdUser> selectPeProdUserForYwyAndCxyCount(PeProdUser t);

	public PeProdUser selectPeProdUserForFgs(PeProdUser t) throws DataAccessException;

	List<PeProdUser> selectPeProdUserForCidList(PeProdUser t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-20
	 */
	Long selectpeProdUserWithPositionNameAndFullDeptNameCount(PeProdUser t); 
	
//	通过 登陆用户id 和部门id查找下级人员
	List<PeProdUser> selectselectPeProdUserBy_id_deptid(PeProdUser t);
	
	/**
	 * 查询促销员信息详情
	 * @author Liang,HouEn
	 * 2014-10-30
	 * @param t
	 * @return
	 */
	PeProdUser selectCXYPeProdUser(PeProdUser t);
	
	/**
	 * 根据用户部门 和角色找人
	 * @param t
	 * @return
	 */
	List<PeProdUser> selectPeProdUserByDeptIdAndRoleIdResult(PeProdUser t);

	List<PeProdUser> selectLoginUserList(PeProdUser t);
}
