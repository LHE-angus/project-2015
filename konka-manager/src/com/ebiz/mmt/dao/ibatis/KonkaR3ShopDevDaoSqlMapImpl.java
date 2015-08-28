package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaR3ShopDevDao;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-30 11:05:44
 */
@Service
public class KonkaR3ShopDevDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3ShopDev> implements KonkaR3ShopDevDao {

	@Override
	public List<KonkaR3ShopDev> selectKtUserByUserIdList(KonkaR3ShopDev v) {
		return super.getSqlMapClientTemplate().queryForList("selectKtUserByUserIdList", v);
	}

	@Override
	public List<KonkaR3ShopDev> selectKonkaR3ShopDevLBPaginatedList(
			KonkaR3ShopDev v) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3ShopDevLBPaginatedList", v);
	}

	@Override
	public Long selectKonkaR3ShopDevLBCount(KonkaR3ShopDev v) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectKonkaR3ShopDevLBCount",v);
	}

}
