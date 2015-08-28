package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaFgsEd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-21 10:27:05
 */
public interface KonkaFgsEdDao extends EntityDao<KonkaFgsEd> {
	List<KonkaFgsEd> selectKonkaFgsEdGroupByDeptIdList(KonkaFgsEd t);
}
