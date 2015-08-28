package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaEmFileReceiveUserDao;
import com.ebiz.mmt.domain.KonkaEmFileReceiveUser;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
@Service
public class KonkaEmFileReceiveUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaEmFileReceiveUser> implements
		KonkaEmFileReceiveUserDao {
	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	public int deleteKonkaEmFileReceiveUserByLinkId(KonkaEmFileReceiveUser t) {
		return super.getSqlMapClientTemplate().delete("deleteKonkaEmFileReceiveUserByLinkId", t);
	}
}
