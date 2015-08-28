package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsDao;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfScortsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JfScorts> implements JfScortsDao {

	@SuppressWarnings("unchecked")
	public List<JfScorts> selectJfScortsForScortsList(JfScorts t) {
		return super.getSqlMapClientTemplate().queryForList("selectJfScortsForScortsList", t);
	}

}
