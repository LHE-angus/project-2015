package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaXxZmdRewardSetPd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
public interface KonkaXxZmdRewardSetPdDao extends EntityDao<KonkaXxZmdRewardSetPd> {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-02
	 */
	public Long selectKonkaXxZmdRewardSetPdWithZmdSnCount(KonkaXxZmdRewardSetPd t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-02
	 */
	public abstract List<KonkaXxZmdRewardSetPd> selectKonkaXxZmdRewardSetPdWithZmdSnPaginatedList(
			KonkaXxZmdRewardSetPd t) throws DataAccessException;

}
