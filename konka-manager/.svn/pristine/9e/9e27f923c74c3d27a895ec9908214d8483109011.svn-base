package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxZmdRemitRecDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdRemitRec;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaXxZmdRemitRecService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-22 22:22:29
 */
@Service
public class KonkaXxZmdRemitRecServiceImpl implements KonkaXxZmdRemitRecService {

	@Resource
	private KonkaXxZmdRemitRecDao konkaXxZmdRemitRecDao;
	
	@Resource
	private KonkaXxSellBillDao konkaXxSellBillDao;
	

	// @Resource
	// private KonkaXxZmdDao konkaXxZmdDao;
	//	
	// @Resource
	// private KonkaXxZmdAccDetailsDao konkaXxZmdAccDetailsDao;
	
	@Resource
	private PeProdUserDao peProdUserDao;

	public Long createKonkaXxZmdRemitRec(KonkaXxZmdRemitRec t) {
		Long id = this.konkaXxZmdRemitRecDao.insertEntity(t);
		String bill_ids = (String) t.getMap().get("bill_ids");
		String[] ary = bill_ids.split("#");
		for (int i = 0; i < ary.length; i++) {
			String sell_bill_id = ary[i].split("_")[0];
			KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
			konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
			konkaXxSellBill.setRemit_rec_id(id);
			konkaXxSellBill.setCheckout_zmd_state(0);
			this.konkaXxSellBillDao.updateEntity(konkaXxSellBill);
		}
		 return id;
	}

	public KonkaXxZmdRemitRec getKonkaXxZmdRemitRec(KonkaXxZmdRemitRec t) {
		return this.konkaXxZmdRemitRecDao.selectEntity(t);
	}

	public Long getKonkaXxZmdRemitRecCount(KonkaXxZmdRemitRec t) {
		return this.konkaXxZmdRemitRecDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdRemitRec> getKonkaXxZmdRemitRecList(KonkaXxZmdRemitRec t) {
		return this.konkaXxZmdRemitRecDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdRemitRec(KonkaXxZmdRemitRec t) {
		
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setRemit_rec_id(t.getRemit_rec_id());
		List<KonkaXxSellBill> konkaXxSellBillList = this.konkaXxSellBillDao.selectEntityList(konkaXxSellBill);
		for(KonkaXxSellBill temp :konkaXxSellBillList){
			KonkaXxSellBill entity = new KonkaXxSellBill();
			//entity.setRemit_date(new Date());
			entity.setCheckout_state(0);
			entity.setSell_bill_id(temp.getSell_bill_id());
			this.konkaXxSellBillDao.updateEntity(entity);
		}
		
		return this.konkaXxZmdRemitRecDao.updateEntity(t);
	}
	
	/**
	 * @author Hu Hao
	 * @version 2012-3-27
	 */
	
	public int modifyKonkaXxZmdRemitRecForZmd(KonkaXxZmdRemitRec t) {
		
		//DecimalFormat Format = new DecimalFormat("0000000000");
		
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(t.getConfirm_user_id());
		peProdUser = this.peProdUserDao.selectEntity(peProdUser);//取操作用户的信息
		
		KonkaXxZmdRemitRec konkaXxZmdRemitRec = new KonkaXxZmdRemitRec();
		konkaXxZmdRemitRec.setRemit_rec_id(t.getRemit_rec_id());
		konkaXxZmdRemitRec = this.konkaXxZmdRemitRecDao.selectEntity(konkaXxZmdRemitRec);
		

		// KonkaXxZmd konkaXxZmd = new KonkaXxZmd(); // 查询专卖店可用额度，并更新可用额度
		// konkaXxZmd.setZmd_id(konkaXxZmdRemitRec.getZmd_id());
		// konkaXxZmd = this.konkaXxZmdDao.selectEntity(konkaXxZmd);
		// konkaXxZmd.setTotal_credit_line(konkaXxZmdRemitRec.getTotal_money().add(konkaXxZmd.getTotal_credit_line()));
		// this.konkaXxZmdDao.updateEntity(konkaXxZmd);
		//		
		// KonkaXxZmdAccDetails konkaXxZmdAccDetails = new KonkaXxZmdAccDetails();//更新专卖店账户收入支出表
		// konkaXxZmdAccDetails.setDept_id(konkaXxZmd.getDept_id());
		// konkaXxZmdAccDetails.setZmd_id(konkaXxZmd.getZmd_id());
		// konkaXxZmdAccDetails.setDo_man_user_id(t.getConfirm_user_id());
		// konkaXxZmdAccDetails.setDo_time(new Date());
		// konkaXxZmdAccDetails.setDo_man(peProdUser.getReal_name());
		// konkaXxZmdAccDetails.setMoney(konkaXxZmdRemitRec.getTotal_money());
		// konkaXxZmdAccDetails.setLeft_money(konkaXxZmd.getTotal_credit_line());
		// konkaXxZmdAccDetails.setMemo("汇款流水号ZH"+Format.format(t.getRemit_rec_id())+"总金额回款至"+konkaXxZmd.getZmd_sn()+"专卖店账户");
		
		//konkaXxZmdAccDetails.setNote_type(1);
		//this.konkaXxZmdAccDetailsDao.insertEntity(konkaXxZmdAccDetails);
		
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill(); //更新账单结算状态
		konkaXxSellBill.setRemit_rec_id(konkaXxZmdRemitRec.getRemit_rec_id());
		List<KonkaXxSellBill> konkaXxSellBillList = this.konkaXxSellBillDao.selectEntityList(konkaXxSellBill);
		for(KonkaXxSellBill temp :konkaXxSellBillList){
			KonkaXxSellBill entity = new KonkaXxSellBill();
			if(temp.getPay_way() == 3){//货到付款
			if(temp.getCheckout_dist_state() == 1){
			entity.setCheckout_state(1);
			entity.setCheckout_date(new Date());
			}}else{//全额现金支付
				entity.setCheckout_state(1);
				entity.setCheckout_date(new Date());
			}
			entity.setCheckout_zmd_date(new Date());
			entity.setCheckout_zmd_state(1);
			entity.setSell_bill_id(temp.getSell_bill_id());
			entity.setRemit_rec_id(t.getRemit_rec_id());
			this.konkaXxSellBillDao.updateEntity(entity);
		}
		
		return this.konkaXxZmdRemitRecDao.updateEntity(t);
	}

	public int removeKonkaXxZmdRemitRec(KonkaXxZmdRemitRec t) {
		return this.konkaXxZmdRemitRecDao.deleteEntity(t);
	}

	public List<KonkaXxZmdRemitRec> getKonkaXxZmdRemitRecPaginatedList(KonkaXxZmdRemitRec t) {
		return this.konkaXxZmdRemitRecDao.selectEntityPaginatedList(t);
	}
     
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-03-27
	 */
	public Long getKonkaXxZmdRemitRecForZmdSnCount(KonkaXxZmdRemitRec t){
		return this.konkaXxZmdRemitRecDao.selectKonkaXxZmdRemitRecForZmdSnCount(t);
	}
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-03-27
	 */
	public List<KonkaXxZmdRemitRec>  getKonkaXxZmdRemitRecForPaZmdSnginatedList(KonkaXxZmdRemitRec t){
		return this.konkaXxZmdRemitRecDao.selectKonkaXxZmdRemitRecForPaZmdSnginatedList(t);
	}
}
