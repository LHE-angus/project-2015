package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaDeptDao extends EntityDao<KonkaDept> {

	/**
	 * @author Xing,XiuDong
	 * @version 2011-09-01
	 */
	List<KonkaDept> selectKonkaDeptListWithTreeNameAndFullName(KonkaDept t) throws DataAccessException;

	/**
	 * @author Ren,Zhong
	 * @version 2011-09-02
	 */
	List<KonkaDept> selectKonkaDeptTreeNameByUserForResultList(KonkaDept t) throws DataAccessException;

	/**
	 * 子查父部门
	 */
	List<KonkaDept> selectKonkaDeptByDeptId(KonkaDept t) throws DataAccessException;

	/**
	 * @author Cheng,Bing
	 * @version 2011-09-29
	 */
	List<KonkaDept> selectKonkaDeptListWithTreeNameForProdUser(KonkaDept t) throws DataAccessException;

	/**
	 * @author Dou,QingRen
	 * @version 2012-03-31
	 * @desc 由下往上查询
	 * @return 父节点树状图
	 */
	List<KonkaDept> selectKonkaDeptListWithParTreeName(KonkaDept t) throws DataAccessException;


	/**
	 * @author Wang,Yang
	 * @version 2011-10-10
	 */
	KonkaDept selectKonkaDeptSuperiorByDeptId(KonkaDept t) throws DataAccessException;

	Long selectKonkaDeptTreeNameByUserForResultCount(KonkaDept t) throws DataAccessException;

	/**
	 * @author Pan,Gang
	 * @version 2013-08-12
	 * @desc 父查子
	 */
	List<KonkaDept> selectKonkaDeptListForPaifa(KonkaDept t) throws DataAccessException;

	/**
	 * 查用户的部门
	 * 
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	List<KonkaDept> selectKonkaDeptListOfUser(KonkaDept t) throws DataAccessException;


	/**
	 * 查经办 dept_type = 3,4,5
	 * 
	 * @param t
	 * @return
	 */
	List<KonkaDept> selectKonkaDeptAndJbNameList(KonkaDept t);
	
	List<HashMap> selectAjaxKonkaDeptList(KonkaDept t);

	/**
	 * 父查子
	 * 
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	List<KonkaDept> selectKonkaDeptListForUser(KonkaDept t) throws DataAccessException;
	
	List<HashMap> selectKonkaDeptTreeNameForProdUser(KonkaDept t);
	
	KonkaDept selectFgsByDeptId(KonkaDept t) throws DataAccessException;
	
	
	/**
	 * @author Xing,XiuDong
	 * @version 2011-09-01
	 */
	List<KonkaDept> selectKonkaDeptListWithUserDeptId(KonkaDept t) throws DataAccessException;
	
	//查询无考勤记录的分公司
	List<KonkaDept> selectDept() throws DataAccessException;
}
