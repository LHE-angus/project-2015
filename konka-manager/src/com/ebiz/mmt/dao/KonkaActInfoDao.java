package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaActInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-06 14:11:44
 */
public interface KonkaActInfoDao extends EntityDao<KonkaActInfo> {
	Long selectKonkaActInfoNoMax(KonkaActInfo t);

	List<KonkaActInfo> selectKonkaActInfoAndSaleDatasList(KonkaActInfo t);
}
