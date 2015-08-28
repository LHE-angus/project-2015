package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.util.List;

import com.ebiz.mmt.domain.KonkaPriceManager;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-08 15:45:59
 */
public interface KonkaPriceManagerService {

	Long createKonkaPriceManager(KonkaPriceManager t);

	int modifyKonkaPriceManager(KonkaPriceManager t);

	int removeKonkaPriceManager(KonkaPriceManager t);

	KonkaPriceManager getKonkaPriceManager(KonkaPriceManager t);

	List<KonkaPriceManager> getKonkaPriceManagerList(KonkaPriceManager t);

	Long getKonkaPriceManagerCount(KonkaPriceManager t);

	List<KonkaPriceManager> getKonkaPriceManagerPaginatedList(KonkaPriceManager t);
	
	Long createKonkaPriceManagerForExcel(List<KonkaPriceManager> entityList);
	
	/**
	 * @author Xiao,GuoJian
	 * @version 2014-04-09
	 * @param goods_name 型号 
	 * @param store_sn 仓库编码
	 * @param query_date 日期
	 * @return price 价格
	 */
	BigDecimal getKonkaPriceManagerForPrice(String goods_name, String store_sn, String query_date);

}