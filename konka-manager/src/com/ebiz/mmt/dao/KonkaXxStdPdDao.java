package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxStdPd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:02
 */
public interface KonkaXxStdPdDao extends EntityDao<KonkaXxStdPd> {

	/**
	 * @auhor Hu,Hao
	 * @version 2013-04-01
	 */
	List<KonkaXxStdPd> selectKonkaXxStdPdForPdNamePaginatedList(KonkaXxStdPd t);
}
