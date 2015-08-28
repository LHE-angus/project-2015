package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGiftJfBuyDao;
import com.ebiz.mmt.dao.EcUserScoreRecDao;
import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.service.EcGiftJfBuyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 15:46:32
 */
@Service
public class EcGiftJfBuyServiceImpl implements EcGiftJfBuyService {

	@Resource
	private EcGiftJfBuyDao ecGiftJfBuyDao;
	
	@Resource
	private EcUserScoreRecDao ecUserScoreRecDao;	

	public Long createEcGiftJfBuy(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.insertEntity(t);
	}

	public EcGiftJfBuy getEcGiftJfBuy(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.selectEntity(t);
	}

	public Long getEcGiftJfBuyCount(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.selectEntityCount(t);
	}

	public List<EcGiftJfBuy> getEcGiftJfBuyList(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.selectEntityList(t);
	}

	public int modifyEcGiftJfBuy(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.updateEntity(t);
	}

	public int removeEcGiftJfBuy(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.deleteEntity(t);
	}

	public List<EcGiftJfBuy> getEcGiftJfBuyPaginatedList(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.selectEntityPaginatedList(t);
	}
	
	/**
	 * 积分充值 支付成功插入积分记录表
	 * @param t
	 * @return
	 */
	public int modifyEcGiftJfBuyForPay(EcGiftJfBuy t) {
		EcUserScoreRec rec = new EcUserScoreRec();
		EcGiftJfBuy entity = new EcGiftJfBuy();
		entity.setId(t.getId());
		entity =this.ecGiftJfBuyDao.selectEntity(entity);
		rec.setOpr_id(0L);
		rec.setOpr_type(new Integer(0));
		rec.setNote(entity.getTitle());
		rec.setTrade_index(entity.getTrade_index());
		rec.setUser_name(entity.getUser_name());
		rec.setOpr_date(new Date());
		rec.setScore(entity.getIntegral().intValue());
		rec.setAll_score(0);
		rec.setUser_id(entity.getUser_id());
		
		this.ecUserScoreRecDao.insertEntity(rec);
		return this.ecGiftJfBuyDao.updateEntity(t);
	}
	
	/**
	 * 取消订单
	 */
	public int modifyEcGiftJfBuyForCancel(EcGiftJfBuy t) {
		return this.ecGiftJfBuyDao.updateEcGiftJfBuyForCancel(t);
	}

}
