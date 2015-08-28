package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAccDetailsDao;
import com.ebiz.mmt.dao.KonkaXxZmdCreditApplyDao;
import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAccDetails;
import com.ebiz.mmt.domain.KonkaXxZmdCreditApply;
import com.ebiz.mmt.service.KonkaXxSystemMessageService;
import com.ebiz.mmt.service.KonkaXxZmdCreditApplyService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
@Service
public class KonkaXxZmdCreditApplyServiceImpl implements KonkaXxZmdCreditApplyService {

	@Resource
	private KonkaXxZmdCreditApplyDao konkaXxZmdCreditApplyDao;
	

	public Long createKonkaXxZmdCreditApply(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.insertEntity(t);
	}

	public KonkaXxZmdCreditApply getKonkaXxZmdCreditApply(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.selectEntity(t);
	}

	public Long getKonkaXxZmdCreditApplyCount(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdCreditApply> getKonkaXxZmdCreditApplyList(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdCreditApply(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.updateEntity(t);
	}

	public int removeKonkaXxZmdCreditApply(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.deleteEntity(t);
	}

	public List<KonkaXxZmdCreditApply> getKonkaXxZmdCreditApplyPaginatedList(KonkaXxZmdCreditApply t) {
		return this.konkaXxZmdCreditApplyDao.selectEntityPaginatedList(t);
	}
	
	@Resource
	private KonkaXxZmdDao konkaXxZmdDao;
	
	@Resource
	private KonkaXxZmdAccDetailsDao konkaXxZmdAccDetailsDao;
	
	@Resource
	private KonkaXxSystemMessageService msgService;
	
	public int modifyKonkaXxZmdCreditApplyWithZmdResult(KonkaXxZmdCreditApply t) {
		this.konkaXxZmdCreditApplyDao.updateEntity(t);
		
		if (null !=  t.getMap().get("pkss") && t.getAudit_state() == 1) { //批量审核
			String[] acc_ids = (String[]) t.getMap().get("pkss");
			for (int i = 0; i < acc_ids.length; i++) {
				KonkaXxZmdCreditApply kxzca = new KonkaXxZmdCreditApply();
				kxzca.setAcc_id(Long.valueOf(acc_ids[i]));
				kxzca = this.konkaXxZmdCreditApplyDao.selectEntity(kxzca);
				if (null != kxzca) {
					Double newCredit = kxzca.getMoney().doubleValue();
					Double lastCredit = kxzca.getCredit_line().doubleValue();
					Double temp = newCredit - lastCredit;
					
					KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
					konkaXxZmd.setZmd_id(kxzca.getZmd_id());
					konkaXxZmd.setCur_credit_line(kxzca.getMoney());
					konkaXxZmd.getMap().put("add_total_credit_line", String.valueOf(temp));
					if(null != kxzca.getCredit_id()){
						konkaXxZmd.setCredit_id(kxzca.getCredit_id());
					}
					this.konkaXxZmdDao.updateKonkaXxZmdForCreditLine(konkaXxZmd);
					
					KonkaXxZmd kxz = new KonkaXxZmd();
					kxz.setZmd_id(kxzca.getZmd_id());
					kxz = this.konkaXxZmdDao.selectEntity(kxz);
					if (null != kxz) {
						KonkaXxZmdAccDetails accDetails = new KonkaXxZmdAccDetails();
						accDetails.setDept_id(kxzca.getDept_id());
						accDetails.setZmd_id(kxzca.getZmd_id());
						accDetails.setDo_man_user_id(kxzca.getAudit_user_id());
						accDetails.setDo_time(new Date());
						if (temp > 0) {
							accDetails.setNote_type(1);
						} else if (temp < 0) {
							accDetails.setNote_type(-1);
						}
						accDetails.setMemo((String) t.getMap().get("ms"));
						accDetails.setMoney(BigDecimal.valueOf(temp));
						accDetails.setLeft_money(kxz.getTotal_credit_line());
						this.konkaXxZmdAccDetailsDao.insertEntity(accDetails);
					}
				}
				
				msgService.messageToRemindTrigger("auditCreditLine", Long.valueOf(acc_ids[i]));
			}
		} else if (null != t.getAcc_id() && t.getAudit_state() == 1) {
			KonkaXxZmdCreditApply kxApply = new KonkaXxZmdCreditApply();
			kxApply.setAcc_id(t.getAcc_id());
			kxApply = this.konkaXxZmdCreditApplyDao.selectEntity(kxApply);
			if (null != kxApply) {
				Double newCredit = kxApply.getMoney().doubleValue();
				Double lastCredit = kxApply.getCredit_line().doubleValue();
				Double temp = newCredit - lastCredit;
				
				KonkaXxZmd kXxZmd = new KonkaXxZmd();
				kXxZmd.setZmd_id(kxApply.getZmd_id());
				kXxZmd.setCur_credit_line(kxApply.getMoney());
				if(null != kxApply.getCredit_id()){
					kXxZmd.setCredit_id(kxApply.getCredit_id());
				}
				kXxZmd.getMap().put("add_total_credit_line", String.valueOf(temp));
				this.konkaXxZmdDao.updateKonkaXxZmdForCreditLine(kXxZmd);
				
				KonkaXxZmd kxz = new KonkaXxZmd();
				kxz.setZmd_id(kxApply.getZmd_id());
				kxz = this.konkaXxZmdDao.selectEntity(kxz);
				if (null != kxz) {
					KonkaXxZmdAccDetails accDetails = new KonkaXxZmdAccDetails();
					accDetails.setZmd_id(kxApply.getZmd_id());
					accDetails.setDept_id(kxApply.getDept_id());
					accDetails.setDo_man_user_id(kxApply.getAudit_user_id());
					accDetails.setDo_time(new Date());
					if (temp > 0) {
						accDetails.setNote_type(1);
					} else if (temp < 0) {
						accDetails.setNote_type(-1);
					}
					accDetails.setMemo((String) t.getMap().get("ms"));
					accDetails.setMoney(BigDecimal.valueOf(temp));
					accDetails.setLeft_money(kxz.getTotal_credit_line());
					this.konkaXxZmdAccDetailsDao.insertEntity(accDetails);
				}
			}
			msgService.messageToRemindTrigger("auditCreditLine", t.getAcc_id());
		}
		
		if ("-1".equals(t.getAudit_state().toString())) { //审核不通过
			if (null !=  t.getMap().get("pkss")) {
				String[] ids = (String[]) t.getMap().get("pkss");
				for (int j = 0; j < ids.length; j++) {
					if ("0".equals(t.getMap().get("is_audit").toString())) {
						msgService.messageToRemindTrigger("auditCreditLine", Long.valueOf(ids[j]));
					} else {
						msgService.messageToRemindTrigger("applyCreditLine", Long.valueOf(ids[j]));
					}
				}
			} else {
				if ("0".equals(t.getMap().get("is_audit").toString())) {
					msgService.messageToRemindTrigger("auditCreditLine", t.getAcc_id());
				} else {
					msgService.messageToRemindTrigger("applyCreditLine", t.getAcc_id());
				}
			}
		}
		
		return 0;
	}
	
	public Long createKonkaXxZmdCreditApplyWithMessage(KonkaXxZmdCreditApply t) {
		Long acc_id = this.konkaXxZmdCreditApplyDao.insertEntity(t);
		
		msgService.messageToRemindTrigger("applyCreditLine", acc_id);
		return 0L;
	}

	public int modifyKonkaXxZmdCreditApplyWithMessage(KonkaXxZmdCreditApply t) {
		this.konkaXxZmdCreditApplyDao.updateEntity(t);
		
		msgService.messageToRemindTrigger("applyCreditLine", t.getAcc_id());
		return 0;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-03-28
	 */
	public List<KonkaXxZmdCreditApply> getKonkaXxZmdCreditApplyForTypePaginatedList(KonkaXxZmdCreditApply t){
		return this.konkaXxZmdCreditApplyDao.selectKonkaXxZmdCreditApplyForTypePaginatedList(t);
	}
	
}
