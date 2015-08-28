package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BuyerInfoDao;
import com.ebiz.mmt.dao.MmtShopCustomerDao;
import com.ebiz.mmt.domain.BuyerInfo;
import com.ebiz.mmt.domain.MmtShopCustomer;
import com.ebiz.mmt.service.BuyerInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-06-02 12:48:04
 */
@Service
public class BuyerInfoServiceImpl implements BuyerInfoService {

	@Resource
	private BuyerInfoDao buyerInfoDao;
	
	@Resource
	private MmtShopCustomerDao mmtShopCustomerDao;

	public Long createBuyerInfo(BuyerInfo t) {

		BuyerInfo bi = new BuyerInfo();
		bi.setBuyer_id(t.getBuyer_id());
		bi.setBuyer_id_type(t.getBuyer_id_type());
		Long biCount = this.buyerInfoDao.selectEntityCount(bi);

		if (null == biCount || biCount == 0L) {
			return this.buyerInfoDao.insertEntity(t);
		} else if (biCount == 1L) {
			if (null != t.getBuyer_id_type() && null != t.getBuyer_id()) {
				this.buyerInfoDao.updateEntity(t);
			}
			return 0L;
		} else {
			return 0L;
		}
	}

	public BuyerInfo getBuyerInfo(BuyerInfo t) {
		return this.buyerInfoDao.selectEntity(t);
	}

	public Long getBuyerInfoCount(BuyerInfo t) {
		return this.buyerInfoDao.selectEntityCount(t);
	}

	public List<BuyerInfo> getBuyerInfoList(BuyerInfo t) {
		return this.buyerInfoDao.selectEntityList(t);
	}

	public int modifyBuyerInfo(BuyerInfo t) {
		return this.buyerInfoDao.updateEntity(t);
	}

	public int removeBuyerInfo(BuyerInfo t) {
		return this.buyerInfoDao.deleteEntity(t);
	}

	public List<BuyerInfo> getBuyerInfoPaginatedList(BuyerInfo t) {
		return this.buyerInfoDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-11
	 */
	public Long getBuyerInfoCountWithShopId(BuyerInfo t) {
		return this.buyerInfoDao.selectBuyerInfoCountWithShopId(t);
	}

	public List<BuyerInfo> getBuyerInfoPaginatedListWithShopId(BuyerInfo t) {
		return this.buyerInfoDao.selectBuyerInfoPaginatedListWithShopId(t);
	}
	
	/**
	 * @author Ren, Zhong
	 * @version 2011-03-30
	 */
	public Long createBuyerInfoAndMMTShopCustomer(BuyerInfo t) {
		Long id;
		Long count = 0l;
		
		BuyerInfo bi = new BuyerInfo();
		bi.setBuyer_id(t.getBuyer_id());
		count = buyerInfoDao.selectEntityCount(bi);
		
		if (null == count || count == 0l) {
			id = buyerInfoDao.insertEntity(t);
			
			MmtShopCustomer msc = new MmtShopCustomer();
			msc.setCustomer(id);
			msc.setMmt_shop((Long) t.getMap().get("shop_id"));
			return mmtShopCustomerDao.insertEntity(msc);
		} else if (count == 1l) {
			this.buyerInfoDao.updateEntity(t);
			bi = buyerInfoDao.selectEntity(bi);
			if (null != bi) {
				MmtShopCustomer c = new MmtShopCustomer();
				c.setMmt_shop((Long) t.getMap().get("shop_id"));
				c.setCustomer(bi.getId());
				Long cc = this.mmtShopCustomerDao.selectEntityCount(c);
				if (cc == 0l || cc == null) {
					this.mmtShopCustomerDao.insertEntity(c);
				} 
			}
			return 0l;
		} else {
			return 0l;
		}
	}

}
