package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 13:45:23
 */
public interface KonkaR3ShopLinkDao extends EntityDao<KonkaR3ShopLink> {

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-08-29
	 * @return 删除客户联系人信息
	 */
	void deleteKonkaR3ShopLinkForR3(KonkaR3ShopLink t);

	List<KonkaR3ShopLink> selectKonkaR3ShopLinkPaginatedForCustomerList(
			KonkaR3ShopLink t);

	Long selectKonkaR3ShopLinkForCustomerCount(KonkaR3ShopLink t);

	KonkaR3ShopLink selectKonkaR3ShopLinkForCustomer(KonkaR3ShopLink t);


}
