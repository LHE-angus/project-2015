package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxZmdPosRecDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmdPosRec;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaXxZmdPosRecService;

/**
 * @author Ren,zhong
 * @version 2012-05-02 15:14
 */
@Service
public class KonkaXxZmdPosRecServiceImpl implements KonkaXxZmdPosRecService {

	@Resource
	private KonkaXxZmdPosRecDao konkaXxZmdPosRecDao;
	
	@Resource
	private KonkaXxSellBillDao konkaXxsellBillDao;
	
	@Resource
	private PeProdUserDao peProdUserDao;
	

	// @Resource
	// private KonkaXxZmdDao konkaXxZmdDao;
	//	
	// @Resource
	// private KonkaXxZmdAccDetailsDao konkaXxZmdAccDetailsDao;

	public Long createKonkaXxZmdPosRec(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.insertEntity(t);
	}

	public KonkaXxZmdPosRec getKonkaXxZmdPosRec(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.selectEntity(t);
	}

	public Long getKonkaXxZmdPosRecCount(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdPosRec> getKonkaXxZmdPosRecList(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdPosRec(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.updateEntity(t);
	}

	public int removeKonkaXxZmdPosRec(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.deleteEntity(t);
	}

	public List<KonkaXxZmdPosRec> getKonkaXxZmdPosRecPaginatedList(KonkaXxZmdPosRec t) {
		return this.konkaXxZmdPosRecDao.selectEntityPaginatedList(t);
	}
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	public Long getKonkaXxZmdPosRecForZmdSnCount(KonkaXxZmdPosRec t){
		return this.konkaXxZmdPosRecDao.selectKonkaXxZmdPosRecForZmdSnCount(t);
	}
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	public List<KonkaXxZmdPosRec> getKonkaXxZmdPosRecForZmdSnPaginatedList(KonkaXxZmdPosRec t){
		return this.konkaXxZmdPosRecDao.selectKonkaXxZmdPosRecForZmdSnPaginatedList(t);
	}

	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-02
	 */
	public Long createKonkaXxZmdPosRecForZmd(KonkaXxZmdPosRec t){
		Long id = this.konkaXxZmdPosRecDao.insertEntity(t);
		String bill_ids = (String) t.getMap().get("bill_ids");
		String user_id = (String) t.getMap().get("user_id");
		String[] ary = bill_ids.split("#");
		for (int i = 0; i < ary.length; i++) {
			String sell_bill_id = ary[i].split("_")[0];
			KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
			konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
			konkaXxSellBill.setPos_id(id);
			konkaXxSellBill.setCheckout_pos_state(1);
			konkaXxSellBill.setCheckout_pos_date(new Date());
			konkaXxSellBill.setCheckout_pos_real_name(t.getMan());
			konkaXxSellBill.setCheckout_pos_user_id(Long.valueOf(user_id));
			this.konkaXxsellBillDao.updateEntity(konkaXxSellBill);
		}
		 return id;
	}
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	public int modifyKonkaXxZmdPosRecForZmd(KonkaXxZmdPosRec t){
		
		//DecimalFormat Format = new DecimalFormat("0000000000");
		
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(t.getConfirm_user_id());
		peProdUser = this.peProdUserDao.selectEntity(peProdUser);//取操作用户的信息
		
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setPos_id(t.getPos_id());
		List<KonkaXxSellBill> konkaXxSellBillList = this.konkaXxsellBillDao.selectEntityList(konkaXxSellBill);
		for(KonkaXxSellBill temp :konkaXxSellBillList){
			KonkaXxSellBill entity = new KonkaXxSellBill();
			entity.setSell_bill_id(temp.getSell_bill_id());
			entity.setPos_id(t.getPos_id());
	        entity.setCheckout_date(new Date());
	        entity.setCheckout_state(1);
			this.konkaXxsellBillDao.updateEntity(entity);
			
			KonkaXxZmdPosRec konkaXxZmdPosRec = new KonkaXxZmdPosRec();
			konkaXxZmdPosRec.setPos_id(t.getPos_id());
			konkaXxZmdPosRec = this.konkaXxZmdPosRecDao.selectEntity(konkaXxZmdPosRec);
			

			// KonkaXxZmd konkaXxZmd = new KonkaXxZmd(); // 查询专卖店可用额度，并更新可用额度
			// konkaXxZmd.setZmd_id(konkaXxZmdPosRec.getZmd_id());
			// konkaXxZmd = this.konkaXxZmdDao.selectEntity(konkaXxZmd);
			// konkaXxZmd.setTotal_credit_line(konkaXxZmdPosRec.getMoney().add(konkaXxZmd.getTotal_credit_line()));
			// this.konkaXxZmdDao.updateEntity(konkaXxZmd);//更新当前可用信用额度
			//			
			// KonkaXxZmdAccDetails konkaXxZmdAccDetails = new KonkaXxZmdAccDetails();//更新专卖店账户收入支出表
			// konkaXxZmdAccDetails.setDept_id(konkaXxZmdPosRec.getDept_id());
			// konkaXxZmdAccDetails.setZmd_id(konkaXxZmdPosRec.getZmd_id());
			// konkaXxZmdAccDetails.setDo_man_user_id(konkaXxZmdPosRec.getConfirm_user_id());
			// konkaXxZmdAccDetails.setDo_time(new Date());
			// konkaXxZmdAccDetails.setMoney(konkaXxZmdPosRec.getMoney());
			// konkaXxZmdAccDetails.setDo_man(peProdUser.getReal_name());
			// konkaXxZmdAccDetails.setNote_type(1);
			// konkaXxZmdAccDetails.setLeft_money(konkaXxZmd.getTotal_credit_line());
			// konkaXxZmdAccDetails.setMemo("Pos机日账单PJ"+Format.format(t.getPos_id())+"回款至"+konkaXxZmd.getZmd_sn()+"专卖店用户");
			// this.konkaXxZmdAccDetailsDao.insertEntity(konkaXxZmdAccDetails);
		}
		
		return this.konkaXxZmdPosRecDao.updateEntity(t);
	}
}
