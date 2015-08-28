package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopDev;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-30 11:05:44
 */
public interface KonkaR3ShopDevService {

	Long createKonkaR3ShopDev(KonkaR3ShopDev t);

	int modifyKonkaR3ShopDev(KonkaR3ShopDev t);

	int removeKonkaR3ShopDev(KonkaR3ShopDev t);

	KonkaR3ShopDev getKonkaR3ShopDev(KonkaR3ShopDev t);

	List<KonkaR3ShopDev> getKonkaR3ShopDevList(KonkaR3ShopDev t);

	Long getKonkaR3ShopDevCount(KonkaR3ShopDev t);

	List<KonkaR3ShopDev> getKonkaR3ShopDevPaginatedList(KonkaR3ShopDev t);
	
	List<KonkaR3ShopDev> getKtUserByUserIdList(KonkaR3ShopDev v);
	
	Long selectKonkaR3ShopDevLBCount(KonkaR3ShopDev v);
	
	List<KonkaR3ShopDev> getKonkaR3ShopDevLBPaginatedList(KonkaR3ShopDev v);

}