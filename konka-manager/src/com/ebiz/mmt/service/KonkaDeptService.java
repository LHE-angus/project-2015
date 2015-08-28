package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaDept;


public interface KonkaDeptService {

	Long createKonkaDept(KonkaDept t);

	int modifyKonkaDept(KonkaDept t);

	int removeKonkaDept(KonkaDept t);

	KonkaDept getKonkaDept(KonkaDept t);

	List<KonkaDept> getKonkaDeptList(KonkaDept t);
	
	List<HashMap> getAjaxKonkaDeptList(KonkaDept t);

	Long getKonkaDeptCount(KonkaDept t);

	List<KonkaDept> getKonkaDeptPaginatedList(KonkaDept t);

	/**
	 * @author Xing,XiuDong
	 * @version 2011-09-01
	 */
	List<KonkaDept> getKonkaDeptListWithTreeNameAndFullName(KonkaDept t);

	/**
	 * @author Ren,Zhong
	 * @version 2011-09-02
	 */
	List<KonkaDept> getKonkaDeptTreeNameByUserForResultList(KonkaDept t);

	/**
	 * 子查父
	 */
	List<KonkaDept> getKonkaDeptByDeptId(KonkaDept t);

	List<KonkaDept> getKonkaDeptListWithTreeNameForProdUser(KonkaDept t);

	/**
	 * @desc 由下往上查询
	 * @return 父节点树状图
	 */
	List<KonkaDept> getKonkaDeptListWithParTreeName(KonkaDept t);

	/**
	 * 子查父
	 */
	KonkaDept getKonkaDeptSuperiorByDeptId(KonkaDept t);

	Long getKonkaDeptTreeNameByUserForResultCount(KonkaDept t);

	/**
	 * 父查子
	 * 
	 * @author pan,gang
	 * @date 2013-8-12
	 */
	List<KonkaDept> getKonkaDeptListForPaifa(KonkaDept t);

	/**
	 * 查指定用户的部门列表
	 * 
	 * @author Xing,Xiudong
	 * @date 2013-09-11
	 */
	List<KonkaDept> getKonkaDeptListOfUser(KonkaDept t);

	/**
	 * 
	 * 查分公司下经办信息
	 * 
	 * @author pan,gang
	 * @date 2013-11-04
	 */
	List<KonkaDept> getKonkaDeptAndJbNameList(KonkaDept t);
	
	/**
	 * 父查子
	 * 
	 * @param t
	 * @return
	 */
	List<KonkaDept> getKonkaDeptListForUser(KonkaDept t);
	
	List<HashMap> getKonkaDeptTreeNameForProdUser(KonkaDept t);
	
	/**
	 * 查dept_type =3
	 * 
	 * @param t
	 * @return
	 */
	KonkaDept getFgsByDeptId(KonkaDept t);

	/**
	 * 父查子
	 * 
	 * @param t
	 * @return
	 */
	List<KonkaDept> getKonkaDeptListWithUserDeptId(KonkaDept t);

	List<KonkaDept> selectDept();
	
}