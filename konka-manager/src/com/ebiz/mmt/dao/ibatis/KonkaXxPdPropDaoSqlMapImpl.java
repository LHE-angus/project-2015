package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdPropDao;
import com.ebiz.mmt.domain.KonkaXxPdProp;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
@Service
public class KonkaXxPdPropDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxPdProp> implements KonkaXxPdPropDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-04-05
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPdProp> selectKonkaXxPdPropWithTreeNameList(KonkaXxPdProp t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxPdPropWithTreeNameList", t);
	}
}
