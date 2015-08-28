package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSpActivityManager;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
public interface KonkaSpActivityManagerDao extends EntityDao<KonkaSpActivityManager> {

	List<KonkaSpActivityManager> selectEntityListForExcel(KonkaSpActivityManager t);

	List<KonkaSpActivityManager> selectKonkaSpActivityManagerListForBookReport(
			KonkaSpActivityManager entity);

}
