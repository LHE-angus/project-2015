package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Order;


public interface KonkaR3OrderService {

	Long createKonkaR3Order(KonkaR3Order t);

	int modifyKonkaR3Order(KonkaR3Order t);

	int removeKonkaR3Order(KonkaR3Order t);

	KonkaR3Order getKonkaR3Order(KonkaR3Order t);

	List<KonkaR3Order> getKonkaR3OrderList(KonkaR3Order t);

	Long getKonkaR3OrderCount(KonkaR3Order t);

	List<KonkaR3Order> getKonkaR3OrderPaginatedList(KonkaR3Order t);

	/**
	 * 处理R3订单数据
	 * @author Lianghouen
	 * @date 2014-6-20
	 * @return
	 */
	int updateKonkaR3Order();
	
	/**
	 * 新查询R3订单统计方法
	 * @author Angus
	 * @date 2014-7-24
	 * @param t
	 * @return
	 */
	List<HashMap> getKonkaR3OrderListNew(KonkaR3Order t);
}