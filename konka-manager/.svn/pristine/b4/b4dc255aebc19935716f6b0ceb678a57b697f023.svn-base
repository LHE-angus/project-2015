package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxDistAccountRecDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.domain.KonkaXxDistAccountRec;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaXxDistAccountRecService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:56:59
 */
@Service
public class KonkaXxDistAccountRecServiceImpl implements KonkaXxDistAccountRecService {

	@Resource
	private KonkaXxDistAccountRecDao konkaXxDistAccountRecDao;

	@Resource
	private KonkaXxSellBillDao konkaXxSellBillDao;

//	@Resource
//	private KonkaXxZmdDao konkaXxZmdDao;

//	@Resource
//	private KonkaXxZmdAccDetailsDao konkaXxZmdAccDetailsDao;

	@Resource
	private PeProdUserDao peProdUserDao;

	public Long createKonkaXxDistAccountRec(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.insertEntity(t);
	}

	public KonkaXxDistAccountRec getKonkaXxDistAccountRec(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.selectEntity(t);
	}

	public Long getKonkaXxDistAccountRecCount(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.selectEntityCount(t);
	}

	public List<KonkaXxDistAccountRec> getKonkaXxDistAccountRecList(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.selectEntityList(t);
	}

	public int modifyKonkaXxDistAccountRec(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.updateEntity(t);
	}

	public int removeKonkaXxDistAccountRec(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.deleteEntity(t);
	}

	public List<KonkaXxDistAccountRec> getKonkaXxDistAccountRecPaginatedList(KonkaXxDistAccountRec t) {
		return this.konkaXxDistAccountRecDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-04-01
	 */
	public Long createKonkaXxDistAccountRecWithSellBill(KonkaXxDistAccountRec t) {
		Long id = this.konkaXxDistAccountRecDao.insertEntity(t);
		String bill_ids = (String) t.getMap().get("bill_ids");
		String[] ary = bill_ids.split("#");
		for (int i = 0; i < ary.length; i++) {
			String sell_bill_id = ary[i].split("_")[0];
			KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
			konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
			konkaXxSellBill.setDist_id(id);
			konkaXxSellBill.setCheckout_dist_state(0);
			this.konkaXxSellBillDao.updateEntity(konkaXxSellBill);
		}
		return id;
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-04-01
	 */
	public int modifyKonkaXxDistAccountRecWithSellBill(KonkaXxDistAccountRec t) {

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setDist_id(t.getDist_id());
		List<KonkaXxSellBill> konkaXxSellBillList = this.konkaXxSellBillDao.selectEntityList(konkaXxSellBill);
		for (KonkaXxSellBill temp : konkaXxSellBillList) {
			KonkaXxSellBill entity = new KonkaXxSellBill();
			entity.setCheckout_state(0);
			entity.setSell_bill_id(temp.getSell_bill_id());
			this.konkaXxSellBillDao.updateEntity(entity);
		}
		return this.konkaXxDistAccountRecDao.updateEntity(t);
	}

	/**
	 * @author Hu Hao
	 * @version 2012-04-02
	 */
	public int modifyKonkaXxDistAccountForZmd(KonkaXxDistAccountRec t) {

		//DecimalFormat Format = new DecimalFormat("0000000000");

		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(t.getConfirm_user_id());
		peProdUser = this.peProdUserDao.selectEntity(peProdUser);// 取操作用户的信息

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setDist_id(t.getDist_id());
		List<KonkaXxSellBill> konkaXxSellBillList = this.konkaXxSellBillDao.selectEntityList(konkaXxSellBill);
		for (KonkaXxSellBill temp : konkaXxSellBillList) {
			KonkaXxSellBill entity = new KonkaXxSellBill();
			if (temp.getCheckout_zmd_state() == 1) {
				entity.setCheckout_state(1);
				entity.setCheckout_date(new Date());
			}
			entity.setSell_bill_id(temp.getSell_bill_id());
			entity.setDist_date(new Date());
			entity.setDist_id(t.getDist_id());
			entity.setCheckout_dist_date(new Date());
			entity.setCheckout_dist_state(1);
			this.konkaXxSellBillDao.updateEntity(entity);

			// KonkaXxZmd konkaXxZmd = new KonkaXxZmd(); //更新专卖店的当前可用额度
			// konkaXxZmd.setZmd_id(temp.getZmd_id());
			// konkaXxZmd = this.konkaXxZmdDao.selectEntity(konkaXxZmd);
			// konkaXxZmd.setTotal_credit_line(konkaXxZmd.getTotal_credit_line().add(temp.getTotal_money()).subtract(temp.getMoney_of_deposit()));
			// this.konkaXxZmdDao.updateEntity(konkaXxZmd);
			//			
			// KonkaXxZmdAccDetails konkaXxZmdAccDetails = new KonkaXxZmdAccDetails();//更新专卖店账户收入支出表
			// konkaXxZmdAccDetails.setDept_id(konkaXxZmd.getDept_id());
			// konkaXxZmdAccDetails.setZmd_id(konkaXxZmd.getZmd_id());
			// konkaXxZmdAccDetails.setDo_man_user_id(t.getConfirm_user_id());
			// konkaXxZmdAccDetails.setDo_time(new Date());
			// konkaXxZmdAccDetails.setMoney(temp.getTotal_money().subtract(temp.getMoney_of_deposit()));
			// konkaXxZmdAccDetails.setDo_man(peProdUser.getReal_name());
			// konkaXxZmdAccDetails.setNote_type(1);
			// konkaXxZmdAccDetails.setLeft_money(konkaXxZmd.getTotal_credit_line());
			// konkaXxZmdAccDetails.setMemo("物流结算单WS"+Format.format(t.getDist_id())+"回款至"+konkaXxZmd.getZmd_sn()+"专卖店用户");
			// this.konkaXxZmdAccDetailsDao.insertEntity(konkaXxZmdAccDetails);
			//			
			// this.konkaXxZmdDao.updateEntity(konkaXxZmd);//更新当前可用信用额度
		}

		return this.konkaXxDistAccountRecDao.updateEntity(t);
	}

}
