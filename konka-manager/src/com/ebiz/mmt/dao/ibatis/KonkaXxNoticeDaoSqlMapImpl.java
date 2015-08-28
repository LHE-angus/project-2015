package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxNoticeDao;
import com.ebiz.mmt.domain.KonkaXxNotice;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-19 09:15:51
 */
@Service
public class KonkaXxNoticeDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxNotice> implements KonkaXxNoticeDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	public Long selectKonkaXxNoticeAndContentCount(KonkaXxNotice t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxNoticeAndContentCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxNotice> selectKonkaXxNoticeAndContentPaginatedList(KonkaXxNotice t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxNoticeAndContentPaginatedList", t);
	}
}
