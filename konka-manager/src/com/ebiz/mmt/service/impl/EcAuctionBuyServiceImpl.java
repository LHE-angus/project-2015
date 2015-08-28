package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcAuctionBuyDao;
import com.ebiz.mmt.dao.EcAuctionMainDao;
import com.ebiz.mmt.domain.EcAuctionBuy;
import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.mmt.service.EcAuctionBuyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
@Service
public class EcAuctionBuyServiceImpl implements EcAuctionBuyService {

	@Resource
	private EcAuctionBuyDao ecAuctionBuyDao;
	
	@Resource
	private EcAuctionMainDao ecAuctionMainDao;
	

	public Long createEcAuctionBuy(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.insertEntity(t);
	}

	public EcAuctionBuy getEcAuctionBuy(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.selectEntity(t);
	}

	public Long getEcAuctionBuyCount(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.selectEntityCount(t);
	}

	public List<EcAuctionBuy> getEcAuctionBuyList(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.selectEntityList(t);
	}

	public int modifyEcAuctionBuy(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.updateEntity(t);
	}

	public int removeEcAuctionBuy(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.deleteEntity(t);
	}

	public List<EcAuctionBuy> getEcAuctionBuyPaginatedList(EcAuctionBuy t) {
		return this.ecAuctionBuyDao.selectEntityPaginatedList(t);
	}
	
	public EcAuctionBuy getEcAuctionBuyForMaxPrice(Long auction_id) {
		return this.ecAuctionBuyDao.selectEcAuctionBuyForMaxPrice(auction_id);
	}
	
	public int createEcAuctionBuyForAuction(EcAuctionBuy t) {
		int i=0;
		EcAuctionMain ecAuctionMain = new EcAuctionMain();
		ecAuctionMain.setId(t.getAuction_id());
		ecAuctionMain = this.ecAuctionMainDao.selectEntity(ecAuctionMain);
		if(ecAuctionMain!=null){
			EcAuctionBuy  ecAuctionBuy =this.ecAuctionBuyDao.selectEcAuctionBuyForMaxPrice(t.getAuction_id());
			
			//计算最低出价 如果出价小于 最低出价则出价失败
			if(ecAuctionBuy!=null&&ecAuctionBuy.getPrice()!=null&&ecAuctionBuy.getPrice().floatValue()>0.01){
				if(ecAuctionBuy.getPrice().floatValue()+ecAuctionMain.getAdd_price().floatValue()>t.getPrice().floatValue()){
					i=-1;
					return i;
				}
			}else if(ecAuctionMain.getAuction_price().floatValue()+ecAuctionMain.getAdd_price().floatValue()>t.getPrice().floatValue()){ 
				i=-1; 
				return i;
			}
			
			if(ecAuctionMain.getIs_act().intValue()>0){ 
				this.ecAuctionBuyDao.insertEntity(t); 
				//计算活动截止时间 如果活动截止时间小于 出价延迟时间 则出价延迟次数+1
				this.ecAuctionMainDao.updateEcAuctionMainDelayNum(ecAuctionMain);
				i=1;
			}
			
		}
		return i;
	}

}
