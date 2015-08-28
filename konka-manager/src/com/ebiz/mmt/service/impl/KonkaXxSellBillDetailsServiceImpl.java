package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxRewardModifyDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDetailsDao;
import com.ebiz.mmt.domain.KonkaXxRewardModify;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaXxSellBillDetailsService;
import com.ebiz.mmt.service.KonkaXxSystemMessageService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-09 21:51:01
 */
@Service
public class KonkaXxSellBillDetailsServiceImpl implements KonkaXxSellBillDetailsService {

	@Resource
	private KonkaXxSellBillDetailsDao konkaXxSellBillDetailsDao;

	@Resource
	private KonkaXxRewardModifyDao konkaXxRewardModifyDao;

	@Resource
	private KonkaXxSellBillDao konkaXxSellBillDao;

	public Long createKonkaXxSellBillDetails(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.insertEntity(t);
	}

	public KonkaXxSellBillDetails getKonkaXxSellBillDetails(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectEntity(t);
	}

	public Long getKonkaXxSellBillDetailsCount(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectEntityCount(t);
	}

	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaXxSellBillDetails(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.updateEntity(t);
	}

	public int removeKonkaXxSellBillDetails(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.deleteEntity(t);
	}

	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsPaginatedList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-10
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsWithOrderPaginatedList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsWithOrderPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-10
	 */
	public Long getKonkaXxSellBillDetailsWithOrderCount(KonkaXxSellBillDetails t) throws DataAccessException {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsWithOrderCount(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	public Long getKonkaXxSellBillDetailsForKcCount(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsForKcCount(t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-1-12
	 */
	public Long getkonkaXxSellBillDetailsForSalesOrdersCount(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectkonkaXxSellBillDetailsForSalesOrdersCount(t);
	};

	/**
	 * @author Ren,zhong
	 * @version 2012-01-11
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsForSalesOrdersForResultList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsForSalesOrdersForResultList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-12
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsWithSpecList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsWithSpecList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-12
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsWithPdTypeList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsWithPdTypeList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-01-12\
	 * @desc 辅助提交数据 数据格式 data:订单ID，明细ID，返佣值，返佣快照，手工调整数值
	 *       data:sell_bill_id,sell_bill_details_id
	 *       ,zmd_fee,formula,tzjs#sell_bill_id
	 *       ,sell_bill_details_id,zmd_fee,formula,tzjs#....
	 */
	public Long rewardJsByStringDataUser(String data, PeProdUser user) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		String[] from_values = data.split("#");

		// 处理订单明细
		KonkaXxSellBillDetails ksbd = new KonkaXxSellBillDetails();
		for (String string : from_values) {
			String[] values = string.split(",");
			String sell_bill_id = values[0];
			String sell_bill_details_id = values[1];
			String zmd_fee = values[2];
			StringBuffer formula = new StringBuffer(values[3]);
			String tzjs = values[4];

			BigDecimal bd_zmd_fee = new BigDecimal(zmd_fee);
			BigDecimal bd_tzjs = new BigDecimal(tzjs);

			if (!"0".equals(tzjs)) {
				bd_zmd_fee = bd_zmd_fee.add(bd_tzjs).setScale(4, BigDecimal.ROUND_HALF_UP);
				formula.append("<br />").append("手工调整数值：").append(bd_tzjs);

				// 记录调整数据
				KonkaXxRewardModify krm = new KonkaXxRewardModify();
				krm.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
				krm.setReward_modify_value(bd_tzjs);
				krm.setModify_user_id(user.getId());
				krm.setModify_user_name(user.getReal_name());
				krm.setModify_date(new Date());
				this.konkaXxRewardModifyDao.insertEntity(krm);
			}

			// 处理销售单总返佣
			if (null == map.get(sell_bill_id)) {
				map.put(sell_bill_id, bd_zmd_fee);
			} else {
				map.put(sell_bill_id, map.get(sell_bill_id).add(bd_zmd_fee).setScale(4, BigDecimal.ROUND_HALF_UP));
			}

			// 更新销售单详细
			ksbd.setSell_bill_details_id(Long.valueOf(sell_bill_details_id));
			ksbd.setZmd_fee(bd_zmd_fee);
			ksbd.setZmd_fee_fp(formula.toString());
			this.konkaXxSellBillDetailsDao.updateEntity(ksbd);
		}

		// 处理订单
		KonkaXxSellBill ksb = new KonkaXxSellBill();
		for (String sell_bill_id : map.keySet()) {
			ksb.setSell_bill_id(Long.valueOf(sell_bill_id));
			ksb.setJs_bill_state(1);
			ksb.setJs_bill_date(new Date());
			ksb.setJs_bill_user_id(user.getId());
			ksb.setJs_bill_user_realname(user.getReal_name());
			ksb.setJs_bill_money(map.get(sell_bill_id));

			this.konkaXxSellBillDao.updateEntity(ksb);
		}

		return 1L;
	};

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-05
	 */
	public Long getKonkaXxSellBillDetailsInRoadCount(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsInRoadCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-05
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsInRoadPaginatedList(KonkaXxSellBillDetails t) {
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsInRoadPaginatedList(t);
	}
	
	@Resource
	private KonkaXxSystemMessageService msgService;

	public int modifyKonkaXxSellBillDetailsWithSellState(KonkaXxSellBillDetails t) {
		this.konkaXxSellBillDetailsDao.updateEntity(t);
		
		//更新konkaXxSellBill中销售单状态sell_state为30（已发货）
		Long sell_bill_id = t.getSell_bill_id();
		if (null != sell_bill_id) {
			boolean flag = true;//标识位
			
			KonkaXxSellBillDetails details = new KonkaXxSellBillDetails();
			details.setSell_bill_id(sell_bill_id);
			List<KonkaXxSellBillDetails> list = this.konkaXxSellBillDetailsDao.selectEntityList(details);
			if (null != list && list.size() > 0) {
				for (KonkaXxSellBillDetails kxsbd : list) {
					if (kxsbd.getLock_stock_state() != 2) {
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
		
		return 0;
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2012-04-20
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsForJJSPaginatedList(KonkaXxSellBillDetails t){
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsForJJSPaginatedList(t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2012-04-20
	 */
	public Long getKonkaXxSellBillDetailsForJJSCount(KonkaXxSellBillDetails t){
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsForJJSCount(t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-03-23
	 */
	public List<KonkaXxSellBillDetails> getKonkaXxSellBillDetailsForInRoadList(KonkaXxSellBillDetails t){
		return this.konkaXxSellBillDetailsDao.selectKonkaXxSellBillDetailsForInRoadList(t);
	}
	
}
