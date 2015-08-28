package com.ebiz.mmt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3OrderDao;
import com.ebiz.mmt.domain.KonkaR3Order;
import com.ebiz.mmt.service.KonkaR3OrderService;


@Service
public class KonkaR3OrderServiceImpl implements KonkaR3OrderService {

	@Resource
	private KonkaR3OrderDao konkaR3OrderDao;
	

	public Long createKonkaR3Order(KonkaR3Order t) {
		return this.konkaR3OrderDao.insertEntity(t);
	}

	public KonkaR3Order getKonkaR3Order(KonkaR3Order t) {
		return this.konkaR3OrderDao.selectEntity(t);
	}

	public Long getKonkaR3OrderCount(KonkaR3Order t) {
		return this.konkaR3OrderDao.selectEntityCount(t);
	}

	public List<KonkaR3Order> getKonkaR3OrderList(KonkaR3Order t) {
		return this.konkaR3OrderDao.selectEntityList(t);
	}

	public int modifyKonkaR3Order(KonkaR3Order t) {
		return this.konkaR3OrderDao.updateEntity(t);
	}

	public int removeKonkaR3Order(KonkaR3Order t) {
		return this.konkaR3OrderDao.deleteEntity(t);
	}

	public List<KonkaR3Order> getKonkaR3OrderPaginatedList(KonkaR3Order t) {
		return this.konkaR3OrderDao.selectEntityPaginatedList(t);
	}

	@Override
	public int updateKonkaR3Order() {
		
		List<HashMap> list = new ArrayList<HashMap>();
		//获取统计数据
		list = this.konkaR3OrderDao.getOrderData();
		
		if(list.size()>0){
			//一次性清空临时表
			KonkaR3Order t = new KonkaR3Order();
			this.konkaR3OrderDao.deleteEntity(t);
			for(HashMap map : list){
				KonkaR3Order order = new KonkaR3Order();
				order.setR3_code((String)map.get("COLUMN_1"));
				order.setYear((String)map.get("LYEAR"));
				//查询某个客户在该年份是否存在记录
				Long lnum = this.konkaR3OrderDao.selectEntityCount(order);
				
				//添加数据
				int mon = Integer.valueOf(map.get("LMONTH").toString());
				double monNum = Double.valueOf(map.get("NUM").toString());
				double monVal = Double.valueOf(map.get("ACOUNT").toString());
				
				switch (mon) {
				case 1:
					order.setMonth1_num(monNum);
					order.setMonth1_val(monVal);
					break;
				case 2:
					order.setMonth2_num(monNum);
					order.setMonth2_val(monVal);
					break;
				case 3:
					order.setMonth3_num(monNum);
					order.setMonth3_val(monVal);
					break;
				case 4:
					order.setMonth4_num(monNum);
					order.setMonth4_val(monVal);
					break;	
				case 5:
					order.setMonth5_num(monNum);
					order.setMonth5_val(monVal);
					break;
				case 6:
					order.setMonth6_num(monNum);
					order.setMonth6_val(monVal);
					break;
				case 7:
					order.setMonth7_num(monNum);
					order.setMonth7_val(monVal);
					break;
				case 8:
					order.setMonth8_num(monNum);
					order.setMonth8_val(monVal);
					break;
				case 9:
					order.setMonth9_num(monNum);
					order.setMonth9_val(monVal);
					break;
				case 10:
					order.setMonth10_num(monNum);
					order.setMonth10_val(monVal);
					break;
				case 11:
					order.setMonth11_num(monNum);
					order.setMonth11_val(monVal);
					break;
				case 12:
					order.setMonth12_num(monNum);
					order.setMonth12_val(monVal);
					break;
				}
				order.setCreatedate(new Date());
				if(lnum>0){
					this.konkaR3OrderDao.updateEntity(order);
				}else{
					this.konkaR3OrderDao.insertEntity(order);
				}
			}
		}
		return 0;
	}

	/**
	 * 新查询R3订单统计方法
	 */
	@Override
	public List<HashMap> getKonkaR3OrderListNew(KonkaR3Order t) {
		
		return this.konkaR3OrderDao.selectKonkaR3OrderList(t);
	}
}
