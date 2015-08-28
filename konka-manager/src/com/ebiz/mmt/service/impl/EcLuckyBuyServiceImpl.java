package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcLuckyBuyDao;
import com.ebiz.mmt.dao.EcLuckyMainDao;
import com.ebiz.mmt.domain.EcLuckyBuy;
import com.ebiz.mmt.domain.EcLuckyMain;
import com.ebiz.mmt.domain.EcLuckyTime;
import com.ebiz.mmt.service.EcLuckyBuyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
@Service
public class EcLuckyBuyServiceImpl implements EcLuckyBuyService {

	@Resource
	private EcLuckyBuyDao ecLuckyBuyDao;
	
	@Resource
	private EcLuckyMainDao ecLuckyMainDao;
	

	public Long createEcLuckyBuy(EcLuckyBuy t) { 
		return this.ecLuckyBuyDao.insertEntity(t);
	}

	/**
	 * return string 0活动未开始,-1已经参与 ,else 成功
	 */
	public String createEcLuckyBuyForLucky(EcLuckyBuy t) {
		if(t.getLucky_id()!=null){
			EcLuckyMain ecLuckyMain = new EcLuckyMain();
			ecLuckyMain.setId(t.getLucky_id());
			ecLuckyMain = this.ecLuckyMainDao.selectEntity(ecLuckyMain); 
			if(ecLuckyMain.getEcLuckyTimeList()==null||ecLuckyMain.getEcLuckyTimeList().size()<1){ 
				 return "0";
			}
			EcLuckyBuy ecLuckyBuy= new EcLuckyBuy();
			ecLuckyBuy.setUser_id(t.getUser_id());
			if(t.getMap().get("is_today_buy")!=null){
				ecLuckyBuy.setMap(t.getMap());
			}
			Long count =this.ecLuckyBuyDao.selectEntityCount(ecLuckyBuy);
			if(count.intValue()>=ecLuckyMain.getLucky_num().intValue()){
				 return "-1";
			}
		}
		Long id=this.ecLuckyBuyDao.insertEntity(t); 
		return t.getTrade_index();
	}
	
	public EcLuckyBuy getEcLuckyBuy(EcLuckyBuy t) {
		return this.ecLuckyBuyDao.selectEntity(t);
	}

	public Long getEcLuckyBuyCount(EcLuckyBuy t) {
		return this.ecLuckyBuyDao.selectEntityCount(t);
	}

	public List<EcLuckyBuy> getEcLuckyBuyList(EcLuckyBuy t) {
		return this.ecLuckyBuyDao.selectEntityList(t);
	}

	public int modifyEcLuckyBuy(EcLuckyBuy t) {
		return this.ecLuckyBuyDao.updateEntity(t);
	}

	public int removeEcLuckyBuy(EcLuckyBuy t) {
		return this.ecLuckyBuyDao.deleteEntity(t);
	}

	public List<EcLuckyBuy> getEcLuckyBuyPaginatedList(EcLuckyBuy t) {
		return this.ecLuckyBuyDao.selectEntityPaginatedList(t);
	}

}
