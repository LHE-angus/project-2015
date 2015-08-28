package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
public interface EcBcompBindingDao extends EntityDao<EcBcompBinding> {

	/**
	 * @author Pan,Gang
	 * @date 2013-09-13
	 */
	List<EcBcompBinding> selectEcBcompBindingForFuWuTaoCanList(EcBcompBinding t);

	List<EcBcompBinding> selectEcBcompBindingForPdTaoCanList(EcBcompBinding t);
}
