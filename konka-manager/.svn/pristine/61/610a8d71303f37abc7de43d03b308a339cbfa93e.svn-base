package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaEmFileDao;
import com.ebiz.mmt.domain.KonkaEmFile;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
@Service
public class KonkaEmFileDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaEmFile> implements KonkaEmFileDao {
	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	public Long selectKonkaEmFileForViewCount(KonkaEmFile t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaEmFileForViewCount", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaEmFile> selectKonkaEmFileForViewPaginatedList(KonkaEmFile t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaEmFileForViewPaginatedList", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-10-10
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaEmFile> selectKonkaEmFileForViewList(KonkaEmFile t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaEmFileForViewList", t);
	}
}
