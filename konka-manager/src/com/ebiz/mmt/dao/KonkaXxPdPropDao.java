package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPdProp;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public interface KonkaXxPdPropDao extends EntityDao<KonkaXxPdProp> {

	/**
	 * @author Hu,Hao
	 * @version 2013-04-05
	 */
	List<KonkaXxPdProp> selectKonkaXxPdPropWithTreeNameList(KonkaXxPdProp t);
}
