package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
public interface JfScortsDao extends EntityDao<JfScorts> {

	public List<JfScorts> selectJfScortsForScortsList(JfScorts t);

}
