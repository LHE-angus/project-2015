package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaDeptJbTask;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-17 11:44:15
 */
public interface KonkaDeptJbTaskDao extends EntityDao<KonkaDeptJbTask> {
	String insertKonkaDeptJbTask(List<KonkaDeptJbTask> list);

	List<KonkaDeptJbTask> selectKonkaDeptJbTaskForBackMoneyList(KonkaDeptJbTask t);
}
