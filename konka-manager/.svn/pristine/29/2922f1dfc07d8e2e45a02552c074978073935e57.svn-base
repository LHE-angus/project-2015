package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopLinkDao;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 13:45:23
 */
@Service
public class KonkaR3ShopLinkDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3ShopLink> implements KonkaR3ShopLinkDao {

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-08-29
	 * @return 删除客户联系人信息
	 */
	@Override
	public void deleteKonkaR3ShopLinkForR3(KonkaR3ShopLink t) {
		this.getSqlMapClientTemplate().update("deleteKonkaR3ShopLinkForR3", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaR3ShopLink> selectKonkaR3ShopLinkPaginatedForCustomerList(
			KonkaR3ShopLink t) {
		
		return this.getSqlMapClientTemplate().queryForList("selectKonkaR3ShopLinkPaginatedForCustomerList", t);
	}
	
	public Long selectKonkaR3ShopLinkForCustomerCount(KonkaR3ShopLink t){
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectKonkaR3ShopLinkForCustomerCount",t);
	}
	
	public KonkaR3ShopLink selectKonkaR3ShopLinkForCustomer(KonkaR3ShopLink t) {
		return (KonkaR3ShopLink) this.getSqlMapClientTemplate().queryForObject("selectKonkaR3ShopLinkForCustomer",t);
	}
}
