package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JsTimes;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
public interface JsTimesDao extends EntityDao<JsTimes> {
	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-10
	 */

	List<JsTimes> selectJsTimesListForCId(JsTimes t);
}
