package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public interface KonkaXxZmdRewardSetDao extends EntityDao<KonkaXxZmdRewardSet> {

	/**
	 * @author hu,hao
	 * @version 2012-03-09
	 */
	List<KonkaXxZmdRewardSet> selectKonkaXxZmdRewardSetForTypeNameList(KonkaXxZmdRewardSet t);
}
