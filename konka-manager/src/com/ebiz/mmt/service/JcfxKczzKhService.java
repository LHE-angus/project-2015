package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.JcfxKczzKh;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.mmt.domain.KonkaR3Shop;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:26
 */
public interface JcfxKczzKhService {

	Long createJcfxKczzKh(JcfxKczzKh t);

	int modifyJcfxKczzKh(JcfxKczzKh t);

	int removeJcfxKczzKh(JcfxKczzKh t);

	JcfxKczzKh getJcfxKczzKh(JcfxKczzKh t);

	List<JcfxKczzKh> getJcfxKczzKhList(JcfxKczzKh t);

	Long getJcfxKczzKhCount(JcfxKczzKh t);

	List<JcfxKczzKh> getJcfxKczzKhPaginatedList(JcfxKczzKh t);

	public Long getKonkaR3ShopForYwyCount(KonkaR3Shop t);
		
	List<KonkaR3Shop>  getKonkaR3ShopForYwyPaginatedList(KonkaR3Shop v);
		
	List<Map<String, String>> getJcfxCwbkczzlPaginatedList(JcfxKczzKh v);
	 
	List<Map<String, String>> getJcfxQglsqdzzqkfgspmPaginatedList(JcfxKczzKh v);
}