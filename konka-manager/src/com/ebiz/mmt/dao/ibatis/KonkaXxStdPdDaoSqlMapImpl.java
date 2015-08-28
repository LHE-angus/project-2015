package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxStdPdDao;
import com.ebiz.mmt.domain.KonkaXxStdPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxStdPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxStdPd> implements KonkaXxStdPdDao {
	
	/**
	 * @auhor Hu,Hao
	 * @version 2013-04-01
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxStdPd> selectKonkaXxStdPdForPdNamePaginatedList(KonkaXxStdPd t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxStdPdForPdNamePaginatedList", t);
	}
}
