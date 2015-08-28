package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Order;
import com.ebiz.ssi.dao.EntityDao;


public interface KonkaR3OrderDao extends EntityDao<KonkaR3Order> {

	/**
	 * 获取R3订单统计数据
	 * @author Lianghouen
	 * @date 2014-6-20
	 * @return
	 */
	List<HashMap> getOrderData();
	
	/**
	 * 查询数据保存到临时表中
	 * @author Lianghouen
	 * @date 2014-6-20
	 */
	Long setOrderData();
	
	/**
	 * 新查询R3订单统计方法
	 * @author Angus
	 * @date 2014-7-24
	 * @return
	 */
	List<HashMap> selectKonkaR3OrderList(KonkaR3Order t);
}
