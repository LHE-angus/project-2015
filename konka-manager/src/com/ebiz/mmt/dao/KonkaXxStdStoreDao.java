package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:02
 */
public interface KonkaXxStdStoreDao extends EntityDao<KonkaXxStdStore> {

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	List<KonkaXxStdStore> selectKonkaXxFactoryIdList(KonkaXxStdStore t);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	List<KonkaXxStdStore> selectKonkaXxStoreIdList(KonkaXxStdStore t);

}
