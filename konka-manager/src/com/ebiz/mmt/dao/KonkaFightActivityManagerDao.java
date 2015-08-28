package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaFightActivityManager;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-16 11:42:03
 */
public interface KonkaFightActivityManagerDao extends EntityDao<KonkaFightActivityManager> {

	Long selectKonkaFightActivityManagerMainCount(
			KonkaFightActivityManager entity);

	List<KonkaFightActivityManager> selectKonkaFightActivityManagerMainPaginatedList(
			KonkaFightActivityManager entity);

}
