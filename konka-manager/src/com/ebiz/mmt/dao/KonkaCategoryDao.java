package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-26 09:19:45
 */
public interface KonkaCategoryDao extends EntityDao<KonkaCategory> {

	/**
	 * @author Hu,hao
	 * @version 2013-08-27
	 * @desc 客户大类
	 */
	List<KonkaCategory> selectKonkaCategoryGroupByCCommList(KonkaCategory t);

	/**
	 * 新查询客户大类
	 * 
	 * @author Angus
	 * @date 2014-7-24
	 * @param t
	 * @return
	 */
	List<HashMap> selectKonkaCategoryGroupByCCommNew(KonkaCategory t);

	/**
	 * 客户细类
	 * 
	 * @author Angus
	 * @date 2014-7-24
	 * @param t
	 * @return
	 */
	List<HashMap> selectEntityListNew(KonkaCategory t);

	/**
	 * 客户管理中的客户类型列表
	 * 
	 * @author Liang,HouEn 2014-9-17
	 * @param t
	 * @return
	 */
	List<HashMap> selectKonkaCategoryMapList(KonkaCategory t);

	List<KonkaCategory> selectKonkaCategoryAndParIndexList(KonkaCategory t);

}
