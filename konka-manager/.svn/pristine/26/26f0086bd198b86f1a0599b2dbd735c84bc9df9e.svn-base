package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopLinkDao;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.service.KonkaR3ShopLinkService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 13:45:23
 */
@Service
public class KonkaR3ShopLinkServiceImpl implements KonkaR3ShopLinkService {

	@Resource
	private KonkaR3ShopLinkDao konkaR3ShopLinkDao;
	

	public Long createKonkaR3ShopLink(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.insertEntity(t);
	}

	public KonkaR3ShopLink getKonkaR3ShopLink(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.selectEntity(t);
	}
	
	public KonkaR3ShopLink getKonkaR3ShopLinkForCustomer(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.selectKonkaR3ShopLinkForCustomer(t);
	}

	public Long getKonkaR3ShopLinkCount(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.selectEntityCount(t);
	}

	public Long getKonkaR3ShopLinkForCustomerCount(KonkaR3ShopLink t){
		return this.konkaR3ShopLinkDao.selectKonkaR3ShopLinkForCustomerCount(t);
	}
	
	public List<KonkaR3ShopLink> getKonkaR3ShopLinkList(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.selectEntityList(t);
	}

	public int modifyKonkaR3ShopLink(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.updateEntity(t);
	}

	public int removeKonkaR3ShopLink(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.deleteEntity(t);
	}

	public List<KonkaR3ShopLink> getKonkaR3ShopLinkPaginatedList(KonkaR3ShopLink t) {
		return this.konkaR3ShopLinkDao.selectEntityPaginatedList(t);
	}
	
	public List<KonkaR3ShopLink> getKonkaR3ShopLinkPaginatedForCustomerList(KonkaR3ShopLink t){
		return this.konkaR3ShopLinkDao.selectKonkaR3ShopLinkPaginatedForCustomerList(t);
	}

}
