package com.ebiz.mmt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDstDao;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.service.KonkaXxSellBillDetailsDstService;
import com.ebiz.mmt.service.KonkaXxSystemMessageService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxSellBillDetailsDstServiceImpl implements KonkaXxSellBillDetailsDstService {
	
	private static HashMap<String, String> properties = new HashMap<String, String>();
	
	static {
		InputStream inputStream = InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
				"i18n/messages.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object key : p.keySet()) {
			properties.put((String) key, (String) p.get(key));
		}
	}
	
	@Resource
	private KonkaXxSystemMessageService msgService;

	@Resource
	private KonkaXxSellBillDetailsDstDao konkaXxSellBillDetailsDstDao;
	
	@Resource
	private KonkaXxSellBillDetailsDao konkaXxSellBillDetailsDao;
	
	@Resource
	private KonkaXxSellBillDao konkaXxSellBillDao;
	
	public Long createKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.insertEntity(t);
	}

	public KonkaXxSellBillDetailsDst getKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.selectEntity(t);
	}

	public Long getKonkaXxSellBillDetailsDstCount(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.selectEntityCount(t);
	}

	public List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstList(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.selectEntityList(t);
	}

	public int modifyKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.updateEntity(t);
	}

	public int removeKonkaXxSellBillDetailsDst(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.deleteEntity(t);
	}

	public List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstPaginatedList(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2011-01-12
	 */
	public Long createKonkaXxSellBillDetailsDstForPdStock(KonkaXxSellBillDetailsDst t) {
		
		Long sell_bill_id = t.getSell_bill_id();
		Long sell_bill_details_id = t.getSell_bill_details_id();
		//先删后增
		if (null != sell_bill_details_id && sell_bill_details_id > 0) {
			KonkaXxSellBillDetailsDst k = new KonkaXxSellBillDetailsDst();
			k.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
			this.konkaXxSellBillDetailsDstDao.deleteEntity(k);
		}
		
		List<KonkaXxSellBillDetailsDst> list = t.getKonkaXxSellBillDetailsDstList();
		if (null != list && list.size() > 0) {
			for (KonkaXxSellBillDetailsDst kxsbdd : list) {
				//商品发货
				this.konkaXxSellBillDetailsDstDao.insertEntity(kxsbdd);
				
				//商品解锁
				KonkaXxSellBillDetails kxbd = kxsbdd.getKonkaXxSellBillDetails();
				this.konkaXxSellBillDetailsDao.updateEntity(kxbd);
			}
		}
		
		//更新konkaXxSellBill中销售单状态sell_state为30（已发货）
		if (null != sell_bill_id) {
			boolean flag = true; //标识位
			
			KonkaXxSellBillDetails billDetails = new KonkaXxSellBillDetails();
			billDetails.setSell_bill_id(sell_bill_id);
			List<KonkaXxSellBillDetails> billDetailsList = konkaXxSellBillDetailsDao.selectEntityList(billDetails);
			if (null != billDetailsList && billDetailsList.size() > 0) {
				for (KonkaXxSellBillDetails kd : billDetailsList) {
					if (kd.getLock_stock_state() != 2) { //库存锁定标识：0-未锁定 1-已锁定  2-已解锁
						flag = false;
						break;
					}
				}
			}
			
			if (flag) {
				KonkaXxSellBill kxsb = new KonkaXxSellBill();
				kxsb.setSell_bill_id(Long.valueOf(sell_bill_id));
				kxsb.setDist_date(new Date()); //最后发货时间
				kxsb.setSell_state(30L);
				this.konkaXxSellBillDao.updateEntity(kxsb);
				
				//添加消息提醒【提醒分公司管理员收获确认】
				msgService.messageToRemindTrigger(kxsb);
			}
			
		}
		
		return 0L;
	}

	/**
	 * @author Ren,zhong
	 * @version 2011-04-09
	 */
	public List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstForPrintOutOrders(KonkaXxSellBillDetailsDst t) {
		return this.konkaXxSellBillDetailsDstDao.selectKonkaXxSellBillDetailsDstForPrintOutOrders(t);
	}
	
}
