package com.ebiz.mmt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxActMessageDao;
import com.ebiz.mmt.dao.KonkaXxMessageDao;
import com.ebiz.mmt.dao.KonkaXxSellBillDao;
import com.ebiz.mmt.dao.KonkaXxZmdCreditApplyDao;
import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.dao.KonkaXxZmdUsersDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.dao.PeRoleUserDao;
import com.ebiz.mmt.domain.KonkaXxActMessage;
import com.ebiz.mmt.domain.KonkaXxMessage;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdCreditApply;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.service.KonkaXxSystemMessageService;

@Service
public class KonkaXxSystemMessageServiceImpl implements KonkaXxSystemMessageService {
	
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
	private KonkaXxSellBillDao konkaXxSellBillDao;
	
	@Resource
	private PeRoleUserDao peRoleUserDao;
	
	@Resource
	private PeProdUserDao peProdUserDao;
	
	@Resource
	private KonkaXxActMessageDao konkaXxActMessageDao;
	
	@Resource
	private KonkaXxMessageDao konkaXxMessageDao;
	
	public void messageToRemindTrigger(KonkaXxSellBill t) {
		//订单流水号格式化
		KonkaXxSellBill bill = new KonkaXxSellBill();
		bill.setSell_bill_id(t.getSell_bill_id());
		bill = this.konkaXxSellBillDao.selectEntity(bill);
		

		// if ("10".equals(t.getSell_state().toString())) { //添加修改订单 → 提醒【财务】审核
		// msg_title = properties.get("konka.xx.zmd.message.dsh");
		// msg_title = StringUtils.replace(msg_title, "a", sell_bill_id);
		//			
		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setRole_id(350L); //分公司财务
		// List<PeRoleUser> roleUserList =
		// this.peRoleUserDao.selectEntityList(peRoleUser);
		// if (null != roleUserList && roleUserList.size() > 0) {
		// for (PeRoleUser peu : roleUserList) {
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(peu.getUser_id());
		// peProdUser.setDept_id(bill.getDept_id());
		// peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
		// if (null != peProdUser) {
		// String msg_content =
		// properties.get("konka.xx.zmd.message.dsh.content");
		// msg_content = StringUtils.replace(msg_content, "a",
		// peProdUser.getUser_name());
		// msg_content = StringUtils.replace(msg_content, "b", sell_bill_id);
		//						
		// this.saveMessages(peProdUser, msg_title, msg_content);
		// }
		// }
		// }
		// } else if ("20".equals(t.getSell_state().toString())) { //审核通过 →
		// ****提醒【分公司管理员】发货：修改为提醒物流****
		// msg_title = properties.get("konka.xx.zmd.message.dfh");
		// msg_title = StringUtils.replace(msg_title, "a", sell_bill_id);
		//			
		// PeRoleUser peRoleUser = new PeRoleUser();
		// // peRoleUser.setRole_id(300L); // 取出角色为[分公司管理员]的用户
		// peRoleUser.setRole_id(360L); //分公司物流
		// List<PeRoleUser> roleUserList =
		// this.peRoleUserDao.selectEntityList(peRoleUser);
		// if (null != roleUserList && roleUserList.size() > 0) {
		// for (PeRoleUser peu : roleUserList) {
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(peu.getUser_id());
		// peProdUser.setDept_id(bill.getDept_id());
		// peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
		// if (null != peProdUser) {
		// String msg_content =
		// properties.get("konka.xx.zmd.message.dfh.content");
		// msg_content = StringUtils.replace(msg_content, "a",
		// peProdUser.getUser_name());
		// msg_content = StringUtils.replace(msg_content, "b", sell_bill_id);
		//						
		// this.saveMessages(peProdUser, msg_title, msg_content);
		// }
		// }
		// }
		// } else if ("21".equals(t.getSell_state().toString())) { //审核不通过 →
		// 提醒【专卖店管理员】查看
		// msg_title = properties.get("konka.xx.zmd.message.dxg");
		// msg_title = StringUtils.replace(msg_title, "a", sell_bill_id);
		//			
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(bill.getAdd_user_id());
		// peProdUser.setIs_del(0);
		// peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
		// if (null != peProdUser) {
		// String msg_content =
		// properties.get("konka.xx.zmd.message.dxg.content");
		// msg_content = StringUtils.replace(msg_content, "a",
		// peProdUser.getUser_name());
		// msg_content = StringUtils.replace(msg_content, "b", sell_bill_id);
		//				
		// this.saveMessages(peProdUser, msg_title, msg_content);
		// }
		// } else if ("30".equals(t.getSell_state().toString())) { //已发货 →
		// ****提醒【分公司管理员】收货确认：修改为提醒物流人员****
		// msg_title = properties.get("konka.xx.zmd.message.dqrsh");
		// msg_title = StringUtils.replace(msg_title, "a", sell_bill_id);
		//			
		// PeRoleUser peRoleUser = new PeRoleUser();
		// //
		// peRoleUser.setRole_id(360L); // 分公司物流
		// List<PeRoleUser> roleUserList =
		// peRoleUserDao.selectEntityList(peRoleUser);
		// if (null != roleUserList && roleUserList.size() > 0) {
		// if (null != roleUserList && roleUserList.size() > 0) {
		// for (PeRoleUser peu : roleUserList) {
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(peu.getUser_id());
		// peProdUser.setDept_id(bill.getDept_id());
		// peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
		// if (null != peProdUser) {
		// String msg_content =
		// properties.get("konka.xx.zmd.message.dqrsh.content");
		// msg_content = StringUtils.replace(msg_content, "a",
		// peProdUser.getUser_name());
		// msg_content = StringUtils.replace(msg_content, "b", sell_bill_id);
		//							
		// this.saveMessages(peProdUser, msg_title, msg_content);
		// }
		// }
		// }
		// }
		// } else if ("40".equals(t.getSell_state().toString())) { //已收货确认 →
		// 提醒【财务】结算佣金
		// msg_title = properties.get("konka.xx.zmd.message.jsyj");
		// msg_title = StringUtils.replace(msg_title, "a", sell_bill_id);
		//
		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setRole_id(350L); // 分公司财务
		// List<PeRoleUser> roleUserList =
		// peRoleUserDao.selectEntityList(peRoleUser);
		// if (null != roleUserList && roleUserList.size() > 0) {
		// for (PeRoleUser peu : roleUserList) {
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(peu.getUser_id());
		// peProdUser.setDept_id(bill.getDept_id());
		// peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
		// if (null != peProdUser) {
		// String msg_content =
		// properties.get("konka.xx.zmd.message.jsyj.content");
		// msg_content = StringUtils.replace(msg_content, "a",
		// peProdUser.getUser_name());
		// msg_content = StringUtils.replace(msg_content, "b", sell_bill_id);
		//
		// this.saveMessages(peProdUser, msg_title, msg_content);
		// }
		// }
		// }
		// }
		
//		else if ("50".equals(t.getSell_state().toString())) { //已结算佣金 → 提醒【财务】结算物流费用
//			msg_title = properties.get("konka.xx.zmd.message.jswl");
//			msg_title = StringUtils.replace(msg_title, "a", sell_bill_id);
//			
//			PeRoleUser peRoleUser = new PeRoleUser();
//			peRoleUser.setRole_id(350L); // 分公司财务
//			List<PeRoleUser> roleUserList = peRoleUserDao.selectEntityList(peRoleUser);
//			if (null != roleUserList && roleUserList.size() > 0) {
//				for (PeRoleUser peu : roleUserList) {
//					PeProdUser peProdUser = new PeProdUser();
//					peProdUser.setId(peu.getUser_id());
//					peProdUser.setDept_id(bill.getDept_id());
//					peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
//					if (null != peProdUser) {
//						String msg_content = properties.get("konka.xx.zmd.message.jswl.content");
//						msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
//						msg_content = StringUtils.replace(msg_content, "b", sell_bill_id);
//
//						this.saveMessages(peProdUser, msg_title, msg_content);
//					}
//				}
//			}
//		}
		
	}
	
	@Resource
	private KonkaXxZmdCreditApplyDao konkaXxZmdCreditApplyDao;
	
	@Resource
	private KonkaXxZmdDao konkaXxZmdDao;
	
	@Resource
	private KonkaXxZmdUsersDao konkaXxZmdUsersDao;
	
	public void messageToRemindTrigger(String type, Long id) {
		
		String msg_title = "";
		String msg_content = "";
			
		if ("applyCreditLine".equals(type)) { //申请信用额度：提醒财务审核
			KonkaXxZmdCreditApply apply = new KonkaXxZmdCreditApply();
			apply.setAcc_id(id);
			apply = this.konkaXxZmdCreditApplyDao.selectEntity(apply);
			
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(apply.getZmd_id());
			zmd.setIs_del(0);
			zmd = this.konkaXxZmdDao.selectEntity(zmd);
			
			PeRoleUser peRoleUser = new PeRoleUser();
			peRoleUser.setRole_id(350L); // 分公司财务
			List<PeRoleUser> roleUserList = peRoleUserDao.selectEntityList(peRoleUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser peu : roleUserList) {
					PeProdUser peProdUser = new PeProdUser();
					peProdUser.setId(peu.getUser_id());
					peProdUser.setDept_id(apply.getDept_id());
					peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
					if (null != peProdUser) {
						msg_title = properties.get("konka.xx.zmd.message.credit.dsh");
						msg_title = StringUtils.replace(msg_title, "a", zmd.getZmd_sn());
						
						msg_content = properties.get("konka.xx.zmd.message.credit.dsh.content");
						msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
						msg_content = StringUtils.replace(msg_content, "b", zmd.getZmd_sn());
						
						this.saveMessages(peProdUser, msg_title, msg_content);
					}
				}
			}
		} else if ("auditCreditLine".equals(type)) { //财务审核信用额度申请：提醒【分公司管理员】【专卖店管理员】查看
			KonkaXxZmdCreditApply apply = new KonkaXxZmdCreditApply();
			apply.setAcc_id(id);
			apply = this.konkaXxZmdCreditApplyDao.selectEntity(apply);
			
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(apply.getZmd_id());
			zmd.setIs_del(0);
			zmd = this.konkaXxZmdDao.selectEntity(zmd);
			
			if ("-1".equals(apply.getAudit_state().toString())) { // 审核未通过
				PeProdUser peProdUser = new PeProdUser();
				peProdUser.setId(apply.getApply_user_id());
				peProdUser.setIs_del(0);
				peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
				if (null != peProdUser) {
					msg_title = properties.get("konka.xx.zmd.message.credit.dxg");
					msg_title = StringUtils.replace(msg_title, "a", zmd.getZmd_sn());
					
					msg_content = properties.get("konka.xx.zmd.message.credit.dxg.content");
					msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
					msg_content = StringUtils.replace(msg_content, "b", zmd.getZmd_sn());
					
					this.saveMessages(peProdUser, msg_title, msg_content);
				}
			} else if ("1".equals(apply.getAudit_state().toString())) { //审核通过
				PeProdUser peProdUser = new PeProdUser();
				peProdUser.setId(apply.getApply_user_id());
				peProdUser.setIs_del(0);
				peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
				if (null != peProdUser) {
					msg_title = properties.get("konka.xx.zmd.message.credit.audit.success");
					msg_title = StringUtils.replace(msg_title, "a", zmd.getZmd_sn());
					
					msg_content = properties.get("konka.xx.zmd.message.credit.audit.success.content");
					msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
					msg_content = StringUtils.replace(msg_content, "b", zmd.getZmd_sn());
					
					this.saveMessages(peProdUser, msg_title, msg_content);
				}
				
				KonkaXxZmdUsers zmdUsers = new KonkaXxZmdUsers();
				zmdUsers.setZmd_id(apply.getZmd_id());
				List<KonkaXxZmdUsers> zmdUsersList = this.konkaXxZmdUsersDao.selectEntityList(zmdUsers);
				if (null != zmdUsersList && zmdUsersList.size() > 0) {
					for (KonkaXxZmdUsers zu : zmdUsersList) {
						PeProdUser user = new PeProdUser();
						user.setId(zu.getUser_id());
						user.setIs_del(0);
						user = this.peProdUserDao.selectPeProdUserResult(user);
						if (null != user) {
							msg_title = properties.get("konka.xx.zmd.message.credit.audit.success.for.zmd");
							
							msg_content = properties.get("konka.xx.zmd.message.credit.audit.success.for.zmd.content");
							msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
							this.saveMessages(user, msg_title, msg_content);
						}
					}
				}
			}
		} else if ("applyZmd".equals(type)) {
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(id);
			zmd.setIs_del(0);
			zmd = this.konkaXxZmdDao.selectEntity(zmd);
			
			PeRoleUser peRoleUser = new PeRoleUser();
			peRoleUser.setRole_id(200L); // 总部管理员
			List<PeRoleUser> roleUserList = peRoleUserDao.selectEntityList(peRoleUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser peu : roleUserList) {
					PeProdUser peProdUser = new PeProdUser();
					peProdUser.setId(peu.getUser_id());
					peProdUser.setIs_del(0);
					peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
					if (null != peProdUser) {
						msg_title = properties.get("konka.xx.zmd.message.zmd.apply.dsh");
						msg_title = StringUtils.replace(msg_title, "a", zmd.getZmd_sn());
						
						msg_content = properties.get("konka.xx.zmd.message.zmd.apply.dsh.content");
						msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
						msg_content = StringUtils.replace(msg_content, "b", zmd.getZmd_sn());
						
						saveMessages(peProdUser, msg_title, msg_content);
					}
				}
			}
		} else if ("auditZmd".equals(type)) {
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(id);
			zmd.setIs_del(0);
			zmd = this.konkaXxZmdDao.selectEntity(zmd);
			
			if ("20300".equals(zmd.getArc_state().toString())) { //审核备案通过
				PeProdUser peProdUser = new PeProdUser();
				peProdUser.setId(zmd.getWrite_man_id()); //拟制人：分公司管理员
				peProdUser.setIs_del(0);
				peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
				if (null != peProdUser) {
					msg_title = properties.get("konka.xx.zmd.message.zmd.audit.success");
					msg_title = StringUtils.replace(msg_title, "a", zmd.getZmd_sn());
					
					msg_content = properties.get("konka.xx.zmd.message.zmd.audit.success.content");
					msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
					msg_content = StringUtils.replace(msg_content, "b", zmd.getZmd_sn());
					
					saveMessages(peProdUser, msg_title, msg_content);
				}
			} else if ("20200".equals(zmd.getArc_state().toString())) { //审核备案不通过
				PeProdUser peProdUser = new PeProdUser();
				peProdUser.setId(zmd.getWrite_man_id()); //拟制人：分公司管理员
				peProdUser.setIs_del(0);
				peProdUser = this.peProdUserDao.selectPeProdUserResult(peProdUser);
				if (null != peProdUser) {
					msg_title = properties.get("konka.xx.zmd.message.zmd.audit.false");
					msg_title = StringUtils.replace(msg_title, "a", zmd.getZmd_sn());
					
					msg_content = properties.get("konka.xx.zmd.message.zmd.audit.false.content");
					msg_content = StringUtils.replace(msg_content, "a", peProdUser.getUser_name());
					msg_content = StringUtils.replace(msg_content, "b", zmd.getZmd_sn());
					
					saveMessages(peProdUser, msg_title, msg_content);
				}
			}
		}
	}
	
	private void saveMessages(PeProdUser user, String msg_title, String msg_content){
		KonkaXxActMessage actMessage = new KonkaXxActMessage();
		actMessage.setMsg_title(msg_title);// 消息标题
		actMessage.setMsg_content(msg_content);
		actMessage.setMsg_type(0);
		actMessage.setSender_id(0L); //
		actMessage.setRec_user_id(user.getId()); //
		actMessage.setAdd_date(new Date());
		actMessage.setState(0L);
		Long Aid = this.konkaXxActMessageDao.insertEntity(actMessage);

		KonkaXxMessage message = new KonkaXxMessage();
		message.setMsg_title(msg_title);
		message.setMsg_content(msg_content);
		message.setSender_id(0L);
		message.setAdd_date(new Date());
		message.setRec_user_id(user.getId());
		message.setState(0);
		message.setMsg_type(0);
		message.setOut_id(Aid);
		this.konkaXxMessageDao.insertEntity(message);
	}

}
