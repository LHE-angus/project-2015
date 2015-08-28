package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdHistoryDao;
import com.ebiz.mmt.domain.KonkaXxPdHistory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxPdHistoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxPdHistory> implements KonkaXxPdHistoryDao {

	/**
	 * 
	 * @auther Hu,Hao
	 * @version 2012-3-31
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPdHistory> selectKonkaXxPdHistoryListForHistoryId(KonkaXxPdHistory t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxPdHistoryListForHistoryId", t);
	}
}
