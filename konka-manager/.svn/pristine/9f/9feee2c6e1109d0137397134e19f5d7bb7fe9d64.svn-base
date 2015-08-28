package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.dao.KonkaXxZmdUsersDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.dao.PeRoleUserDao;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.service.KonkaXxZmdUsersService;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 17:41:31
 */
@Service
public class KonkaXxZmdUsersServiceImpl implements KonkaXxZmdUsersService {

	@Resource
	private KonkaXxZmdUsersDao konkaXxZmdUsersDao;

	@Resource
	private PeProdUserDao peProdUserDao;

	@Resource
	private KonkaXxZmdDao konkaXxZmdDao;
	
	
	@Resource
	private PeRoleUserDao peRoleUserDao;
	
	public Long createKonkaXxZmdUsers(KonkaXxZmdUsers t) {
		String zmd_ids = (String) t.getMap().get("zmd_ids");

		if (null != zmd_ids) {
			// delete all from zmd...
			KonkaXxZmdUsers users = new KonkaXxZmdUsers();
			users.setUser_id(t.getUser_id());
			this.konkaXxZmdUsersDao.deleteEntity(users);

			for (String zmd_id : zmd_ids.split(",")) {
				KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
				konkaXxZmdUsers.setUser_id(t.getUser_id());
				konkaXxZmdUsers.setZmd_id(Long.valueOf(zmd_id));
				this.konkaXxZmdUsersDao.insertEntity(konkaXxZmdUsers);
			}
			return null;
		} else {
			return this.konkaXxZmdUsersDao.insertEntity(t);
		}
	}

	public KonkaXxZmdUsers getKonkaXxZmdUsers(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.selectEntity(t);
	}

	public Long getKonkaXxZmdUsersCount(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdUsers> getKonkaXxZmdUsersList(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.selectEntityList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-12
	 * @return
	 */
	public void modifyKonkaXxZmdUsers(KonkaXxZmdUsers t) {
		KonkaXxZmdUsers k = new KonkaXxZmdUsers();
		k.setUser_id(t.getUser_id());
		this.konkaXxZmdUsersDao.deleteEntity(k);
		String store_ids = (String) t.getMap().get("store_ids");

		if (store_ids != null) {
			String[] ary = store_ids.split(",");
			for (int i = 0; i < ary.length; i++) {

				k.setZmd_id(Long.valueOf(ary[i]));
				k.setUser_id(t.getUser_id());
				this.konkaXxZmdUsersDao.insertEntity(t);
			}
		}

	}

	public int removeKonkaXxZmdUsers(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.deleteEntity(t);
	}

	public List<KonkaXxZmdUsers> getKonkaXxZmdUsersPaginatedList(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	public List<KonkaXxZmdUsers> getKonkaXxZmdForClerkManPaginatedList(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.selectKonkaXxZmdForClerkManPaginatedList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-01-10
	 */
	public List<KonkaXxZmdUsers> getKonkaXxZmdForWithUserList(KonkaXxZmdUsers t) {
		return this.konkaXxZmdUsersDao.selectKonkaXxZmdForWithUserList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-20
	 */
	public void modifyKonkaXxZmdUsersForZmd(List<KonkaXxZmd> t) {

	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-20
	 * @throws Exception 
	 */
	public void modifyKonkaXxZmdUsersForZmdFp(KonkaXxZmdUsers t, Long cust_id) throws Exception {
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setCust_id(cust_id);
		List<PeProdUser> peProdUserList = this.peProdUserDao.selectEntityList(peProdUser);

		// 客户已建用户
		if (peProdUserList.size() > 0 && null != peProdUserList) {
			//将账户设置为专卖店账户
			PeProdUser peProdUser1 = new PeProdUser();
			peProdUser1.setId(peProdUserList.get(0).getId());
			//peProdUser1.setUser_type(3);
			this.peProdUserDao.updateEntity(peProdUser1);
			
			//角色表
			PeRoleUser peRoleUser =  new PeRoleUser();
			peRoleUser.setRole_id(400L);
			peRoleUser.setUser_id(peProdUserList.get(0).getId());
			Long count1 = this.peRoleUserDao.selectEntityCount(peRoleUser);
			if(count1 == 0){
				this.peRoleUserDao.insertEntity(peRoleUser);
			}
			
			//查看客户是否与ID绑定
			BranchAssign branchAssign = new BranchAssign();
			branchAssign.setKonka_r3_id(peProdUserList.get(0).getId());
			
			
			//分配专卖店
			KonkaXxZmdUsers entity1 = new KonkaXxZmdUsers();
			entity1.setUser_id(t.getZmd_id());
			entity1.setUser_id(peProdUserList.get(0).getId());
			this.konkaXxZmdUsersDao.insertEntity(entity1);
		} else {// 客户未建账户
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.setZmd_id(t.getZmd_id());
			konkaXxZmd = this.konkaXxZmdDao.selectEntity(konkaXxZmd);
			
			//获取用户名
			String user_name = konkaXxZmd.getHost_name();
			for(int i=1;i<100; i++){
				PeProdUser peProdUser2 = new PeProdUser();
				peProdUser2.setUser_name(user_name);
				Long count = this.peProdUserDao.selectEntityCount(peProdUser2);
				if(count == 0){
					break;
				}else{
					user_name = user_name +""+i;
				}
			}
			//创建用户
			DESPlus des = new DESPlus();
			PeProdUser peProdUser0 = new PeProdUser();
			peProdUser0.setUser_name(user_name);
			peProdUser0.setUser_type(1);
			peProdUser0.setReal_name(konkaXxZmd.getHost_name());
			peProdUser0.setCust_id(cust_id);
			peProdUser0.setDept_id(konkaXxZmd.getDept_id());
			peProdUser0.setIs_del(0);
			peProdUser0.setPass_word(des.encrypt("0"));
			peProdUser0.setLink_phone(konkaXxZmd.getHost_phone());
			peProdUser0.setProd_entp_id(185L);
			Long user_id = this.peProdUserDao.insertEntity(peProdUser0);
			
			//角色表
			PeRoleUser peRoleUser =  new PeRoleUser();
			peRoleUser.setRole_id(400L);
			peRoleUser.setUser_id(user_id);
			Long count1 = this.peRoleUserDao.selectEntityCount(peRoleUser);
			if(count1 == 0){
				this.peRoleUserDao.insertEntity(peRoleUser);
			}
			
			//分配专卖店
			KonkaXxZmdUsers entity = new KonkaXxZmdUsers();
			entity.setUser_id(user_id);
			entity.setZmd_id(t.getZmd_id());
			this.konkaXxZmdUsersDao.insertEntity(entity);
		}

	}
}
