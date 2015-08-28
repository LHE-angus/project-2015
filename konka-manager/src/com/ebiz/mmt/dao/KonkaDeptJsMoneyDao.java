package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaDeptJsMoney;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-17 14:56:37
 */
public interface KonkaDeptJsMoneyDao extends EntityDao<KonkaDeptJsMoney> {

	/**
	 * @author Hu,Hao
	 * @version 2013-01-18
	 */
	List<KonkaDeptJsMoney> selectKonkaDeptJsMoneyToR3List(KonkaDeptJsMoney t);

}
