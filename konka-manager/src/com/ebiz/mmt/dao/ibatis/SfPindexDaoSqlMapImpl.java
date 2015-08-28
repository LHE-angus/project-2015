package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SfPindexDao;
import com.ebiz.mmt.domain.SfPindex;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-22 17:20:16
 */
@Service
public class SfPindexDaoSqlMapImpl extends EntityDaoSqlMapImpl<SfPindex> implements SfPindexDao {
	public String insertSfPindexList(List<SfPindex> list) throws DataAccessException {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			SfPindex t = list.get(i);
			this.getSqlMapClientTemplate().insert("insertSfPindex", t);
		}
		return msg;
	}
}
