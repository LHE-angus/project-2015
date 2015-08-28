package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-30 11:05:44
 */
public interface KonkaR3ShopDevDao extends EntityDao<KonkaR3ShopDev> {
	
	List<KonkaR3ShopDev> selectKtUserByUserIdList(KonkaR3ShopDev v);
	
	Long selectKonkaR3ShopDevLBCount(KonkaR3ShopDev v);
	
	List<KonkaR3ShopDev> selectKonkaR3ShopDevLBPaginatedList(KonkaR3ShopDev v);
}
