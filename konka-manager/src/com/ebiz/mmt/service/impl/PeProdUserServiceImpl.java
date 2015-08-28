package com.ebiz.mmt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaMobileSpRelationDao;
import com.ebiz.mmt.dao.KonkaR3StoreDao;
import com.ebiz.mmt.dao.KonkaSalesmanInfoDao;
import com.ebiz.mmt.dao.KonkaSalesmanInfoLogDao;
import com.ebiz.mmt.dao.KonkaStoreUserCInfoDao;
import com.ebiz.mmt.dao.KonkaXxZmdUsersDao;
import com.ebiz.mmt.dao.MvOrgOfCustomerAllDao;
import com.ebiz.mmt.dao.MvOrgOfCustomerDao;
import com.ebiz.mmt.dao.MvOrgOfCxyAllDao;
import com.ebiz.mmt.dao.MvOrgOfCxyDao;
import com.ebiz.mmt.dao.PeModPopedomDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.dao.PeRoleUserDao;
import com.ebiz.mmt.dao.PeShopDao;
import com.ebiz.mmt.dao.UserChangeInfoDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaSalesmanInfo;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.MvOrgOfCustomer;
import com.ebiz.mmt.domain.MvOrgOfCustomerAll;
import com.ebiz.mmt.domain.MvOrgOfCxy;
import com.ebiz.mmt.domain.MvOrgOfCxyAll;
import com.ebiz.mmt.domain.PeModPopedom;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PeShop;
import com.ebiz.mmt.domain.UserChangeInfo;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.service.PeProdUserService;
import com.ebiz.mmt.web.struts.Keys;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-23 16:05:41
 */
@Service
public class PeProdUserServiceImpl implements PeProdUserService {

	@Resource
	private PeProdUserDao peProdUserDao;

	@Resource
	private PeModPopedomDao peModPopedomDao;

	@Resource
	private PeRoleUserDao peRoleUserDao;

	@Resource
	private PeShopDao peShopDao;

	@Resource
	private KonkaXxZmdUsersDao konkaXxZmdUsersDao;

	@Resource
	private KonkaMobileSpRelationDao konkaMobileSpRelationDao;

	@Resource
	private UserChangeInfoDao userChangeInfoDao;

	@Resource
	private KonkaStoreUserCInfoDao konkaStoreUserCInfoDao;

	@Resource
	private KonkaR3StoreDao konkaR3StoreDao;

	@Resource
	private KonkaSalesmanInfoDao konkaSalesmanInfoDao;

	@Resource
	private KonkaSalesmanInfoLogDao konkaSalesmanInfoLogDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private MvOrgOfCxyDao mvOrgOfCxyDao;

	@Resource
	private MvOrgOfCxyAllDao mvOrgOfCxyAllDao;

	@Resource
	private MvOrgOfCustomerDao mvOrgOfCustomerDao;

	@Resource
	private MvOrgOfCustomerAllDao mvOrgOfCustomerAllDao;

	public Long createPeProdUser(PeProdUser t) {
		Long n = this.peProdUserDao.insertEntity(t);

		String roleIds = (String) t.getMap().get("roleIds");
		if (null != roleIds) {
			String[] role_id = StringUtils.split(roleIds, ",");
			for (int i = 0; i < role_id.length; i++) {
				PeRoleUser _ru = new PeRoleUser();
				_ru.setUser_id(n);
				_ru.setRole_id(Long.valueOf(role_id[i]));
				this.peRoleUserDao.insertEntity(_ru);
			}
		}

		if (t.getMap().get("sales_type") != null) {
			// 保存岗位、入职日期、类型
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			KonkaSalesmanInfo ksi = new KonkaSalesmanInfo();
			ksi.setUser_job_id(t.getJob_id());
			if (t.getMap().get("sales_type") != null) {
				ksi.setSales_type(Integer.valueOf(t.getMap().get("sales_type").toString()));
			}
			if (t.getMap().get("position") != null) {
				ksi.setPosition(t.getMap().get("position").toString());
			}
			if (t.getMap().get("work_date") != null) {
				Date wdate;
				try {
					wdate = sdf.parse(t.getMap().get("work_date").toString());
					ksi.setWork_date(wdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			ksi.setAdd_date(new Date());
			ksi.setAdd_user_id(t.getAdd_e_user_id());
			this.konkaSalesmanInfoDao.insertEntity(ksi);
		}

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment att : t.getAttachmentList()) {
				att.setLink_id(n);
				att.setLink_tab("KONKA_PE_PROD_USER");
				this.attachmentDao.insertEntity(att);
			}

		}

		return n;
	}

	public PeProdUser getPeProdUser(PeProdUser t) {
		return this.peProdUserDao.selectEntity(t);
	}

	public Long getPeProdUserCount(PeProdUser t) {
		return this.peProdUserDao.selectEntityCount(t);
	}

	public List<PeProdUser> getPeProdUserList(PeProdUser t) {
		return this.peProdUserDao.selectEntityList(t);
	}

	public int modifyPeProdUser(PeProdUser t) {
		int n = this.peProdUserDao.updateEntity(t);

		if (t.getMap().get("role_id") != null) {
			PeRoleUser peRoleUser = new PeRoleUser();
			peRoleUser.setRole_id((Long) t.getMap().get("role_id"));
			peRoleUser.setUser_id(t.getId());
			this.peRoleUserDao.updateEntity(peRoleUser);
		}

		// 初始化专卖店与用户的关系
		if (null != t.getZmd_id()) {
			KonkaXxZmdUsers zmduser = new KonkaXxZmdUsers();
			zmduser.setUser_id(t.getId());
			zmduser.setZmd_id(t.getZmd_id());
			if (0L == this.konkaXxZmdUsersDao.selectEntityCount(zmduser)) {
				this.konkaXxZmdUsersDao.insertEntity(zmduser);
			}

			// 初始化专卖店用户的角色
			PeRoleUser roleuser = new PeRoleUser();
			roleuser.setUser_id(t.getId());
			roleuser.setRole_id(400L);
			if (0L == this.peRoleUserDao.selectEntityCount(roleuser)) {
				this.peRoleUserDao.insertEntity(roleuser);
			}
		}

		return n;
	}

	public int removePeProdUser(PeProdUser t) {

		String cxy_delete = (String) t.getMap().get("cxy_delete");
		if (cxy_delete != null && cxy_delete.equals("1")) {
			MvOrgOfCxy cxy = new MvOrgOfCxy();
			cxy.setCxy_user_id(t.getId());
			this.mvOrgOfCxyDao.deleteEntity(cxy);

			cxy.getMap().put("cxy_user_id", t.getId().toString());
			this.mvOrgOfCxyDao.updateMvOrgOfCxyByCxyUserId(cxy);

			MvOrgOfCxyAll call = new MvOrgOfCxyAll();
			call.setCxy_user_id(t.getId());
			this.mvOrgOfCxyAllDao.deleteEntity(call);

			call.getMap().put("cxy_user_id", t.getId().toString());
			this.mvOrgOfCxyAllDao.updateMvOrgOfCxyAllByCxyUserId(call);

			MvOrgOfCustomer mc = new MvOrgOfCustomer();
			mc.setUser_id(t.getId());
			this.mvOrgOfCustomerDao.deleteEntity(mc);

			mc.getMap().put("user_id", t.getId());
			this.mvOrgOfCustomerDao.updateMvOrgOfCustomerByUserId(mc);

			MvOrgOfCustomerAll mall = new MvOrgOfCustomerAll();
			mall.setUser_id(t.getId());
			this.mvOrgOfCustomerAllDao.deleteEntity(mall);

			mall.getMap().put("user_id", t.getId());
			this.mvOrgOfCustomerAllDao.updateMvOrgOfCustomerAllByUserId(mall);
		}

		return this.peProdUserDao.deleteEntity(t);
	}

	public List<PeProdUser> getPeProdUserPaginatedList(PeProdUser t) {
		return this.peProdUserDao.selectEntityPaginatedList(t);
	}

	public Long getPeProdUserWithEntpNameCount(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserWithEntpNameCount(t);
	}

	public Long getKonkaUserListWithDeptNameForResultCount(PeProdUser t) {
		return this.peProdUserDao.selectKonkaUserListWithDeptNameForResultCount(t);
	}

	public List<PeProdUser> getPeProdUserWithEntpNamePaginatedList(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserWithEntpNamePaginatedList(t);
	}

	/**
	 * @author Chen,ChenLin
	 * @version 2011-05-13
	 */
	public List<PeProdUser> getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(PeProdUser t) {
		return this.peProdUserDao.selectpeProdUserWithPositionNameAndFullDeptNamePaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-07-05
	 */
	public void channelSave(HttpServletRequest request, String prod_entp_id, String id, String[] exc_city_array,
			String cls_ids_select, String[] brand_ids) {
		PeModPopedom pmp = new PeModPopedom();
		pmp.setUser_id(Long.valueOf(id));
		pmp.setEntp_id(Long.valueOf(prod_entp_id));
		// 删除初始数据库中的数据
		this.peModPopedomDao.deletePeModPopedomInit(pmp);
		// 重新初始化数据到Pe_Mod_Popedom表中
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Keys.USER_INFO);
		PeModPopedom peModPopedom;
		// 修改关联区域
		if (null != exc_city_array && exc_city_array.length > 0) {
			for (int i = 0; i < exc_city_array.length; i++) {
				peModPopedom = new PeModPopedom();
				peModPopedom.setUser_id(Long.valueOf(id));
				peModPopedom.setEntp_id(Long.valueOf(prod_entp_id));
				peModPopedom.setP_index(Long.valueOf(exc_city_array[i]));
				peModPopedom.setAdd_user_id(userInfo.getId());
				this.peModPopedomDao.insertEntity(peModPopedom);
			}
		}
		// 修改关联品牌
		if (null != brand_ids && brand_ids.length > 0) {
			for (int i = 0; i < brand_ids.length; i++) {
				peModPopedom = new PeModPopedom();
				peModPopedom.setUser_id(Long.valueOf(id));
				peModPopedom.setEntp_id(Long.valueOf(prod_entp_id));
				peModPopedom.setBrand_id(Long.valueOf(brand_ids[i]));
				peModPopedom.setAdd_user_id(userInfo.getId());
				this.peModPopedomDao.insertEntity(peModPopedom);
			}
		}
		// 修改关联大类
		String[] cls_ids = null;
		if (null != cls_ids_select && cls_ids_select.length() > 0) {
			cls_ids = StringUtils.split(cls_ids_select, ',');
			if (null != cls_ids && cls_ids.length > 0) {
				for (int i = 0; i < cls_ids.length; i++) {
					peModPopedom = new PeModPopedom();
					peModPopedom.setUser_id(Long.valueOf(id));
					peModPopedom.setEntp_id(Long.valueOf(prod_entp_id));
					peModPopedom.setCls_id(Long.valueOf(cls_ids[i]));
					peModPopedom.setAdd_user_id(userInfo.getId());
					this.peModPopedomDao.insertEntity(peModPopedom);
				}
			}
		}
		// 根据初始化数据,将网点分配到该管理员下面
		PeShop peShop = new PeShop();
		peShop.getMap().put("exc_city_array", exc_city_array);
		peShop.getMap().put("cls_ids", cls_ids);
		peShop.getMap().put("brand_ids", brand_ids);
		peShop.setEntp_id(Long.valueOf(prod_entp_id));
		peShop.setLeader(Long.valueOf(id));

		this.peShopDao.updatePeShopInit(peShop);
	}

	public List<PeProdUser> getKonkaUserListWithDeptNameForResultList(PeProdUser t) {
		return this.peProdUserDao.selectKonkaUserListWithDeptNameForResultList(t);
	}

	public List<PeProdUser> getKonkaUserListWithDeptNameForLeaderResultList(PeProdUser t) {
		return this.peProdUserDao.selectKonkaUserListWithDeptNameForLeaderResultList(t);
	}

	public PeProdUser getPeProdUserWithEntpName(PeProdUser t) {
		return this.peProdUserDao.selectEntity(t);
	}

	public List<PeProdUser> getPeProdUserListForSelectUser(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserListForSelectUser(t);
	}

	@Override
	public List<PeProdUser> getPeProdUserWithRoleNameList(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserWithRoleNameList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-1-9
	 */
	public List<PeProdUser> getXxPeProdUserListUser(PeProdUser t) {
		return this.peProdUserDao.selectXxPeProdUserList(t);
	}

	/**
	 * @author Zheng,Kun
	 * @version 2012-1-9
	 */
	public List<PeProdUser> getPeProdUserWithRoleList(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserWithRoleList(t);
	}

	// 门店管理指定上报员
	public List<PeProdUser> getPeProdUserListForShowInfo(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserListForShowInfo(t);
	}

	public Long getPeProdUserCountForShowInfo(PeProdUser t) {
		return this.peProdUserDao.selectKonkaUserCountForShowInfo(t);
	}

	public List<PeProdUser> getPeProdUserPaginatedListForShowInfo(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserPaginatedListForShowInfo(t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2012-03-16
	 */
	public PeProdUser getPeProdUserResult(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserResult(t);
	}

	/**
	 * @author wang,yang
	 * @version 2012-05-17
	 */
	public List<PeProdUser> getDeptListForSelectUser(PeProdUser t) {
		return this.peProdUserDao.selectDeptListForSelectUser(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-04-18
	 */
	public Long createPeProdUserWithShop(PeProdUser t, List<KonkaMobileSpRelation> konkaMobileSpRelationList) {
		Long n = this.peProdUserDao.insertEntity(t);

		// 角色权限
		PeRoleUser _ru = new PeRoleUser();
		_ru.setUser_id(n);
		_ru.setRole_id(Long.valueOf(t.getRole_id()));
		this.peRoleUserDao.insertEntity(_ru);

		// 关联店铺
		for (KonkaMobileSpRelation konkaMobileSpRelation : konkaMobileSpRelationList) {
			konkaMobileSpRelation.setSeller_id(n);
			this.konkaMobileSpRelationDao.insertEntity(konkaMobileSpRelation);
		}

		// 保存促销员新增信息
		if (t.getMap().get("sales_type") != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			KonkaSalesmanInfo ksinfo = new KonkaSalesmanInfo();
			ksinfo.setUser_job_id(t.getJob_id());
			ksinfo.setSales_type(Integer.valueOf(t.getMap().get("sales_type").toString()));
			if (t.getMap().get("sales_stat") != null) {
				ksinfo.setSales_stat(Integer.valueOf(t.getMap().get("sales_stat").toString()));
			}
			if (t.getMap().get("work_date") != null) {
				Date wdate;
				try {
					wdate = sdf.parse(t.getMap().get("work_date").toString());
					ksinfo.setWork_date(wdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (t.getMap().get("work_age") != null) {
				ksinfo.setWork_age(Long.valueOf(t.getMap().get("work_age").toString()));
			}
			if (t.getMap().get("nation_name") != null) {
				ksinfo.setNation_name(t.getMap().get("nation_name").toString());
			}
			if (t.getMap().get("academic") != null) {
				ksinfo.setAcademic(t.getMap().get("academic").toString());
			}
			if (t.getMap().get("married") != null) {
				ksinfo.setMarried(Integer.valueOf(t.getMap().get("married").toString()));
			}
			if (t.getMap().get("emergency_man") != null) {
				ksinfo.setEmergency_man(t.getMap().get("emergency_man").toString());
			}
			if (t.getMap().get("emergency_tel") != null) {
				ksinfo.setEmergency_tel(t.getMap().get("emergency_tel").toString());
			}
			if (t.getMap().get("identity_id") != null) {
				ksinfo.setIdentity_id(t.getMap().get("identity_id").toString());
			}
			if (t.getMap().get("id_valid_date") != null) {
				Date iddate;
				try {
					iddate = sdf.parse(t.getMap().get("work_date").toString());
					ksinfo.setId_valid_date(iddate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (t.getMap().get("residence_addr") != null) {
				ksinfo.setResidence_addr(t.getMap().get("residence_addr").toString());
			}
			if (t.getMap().get("bank_name") != null) {
				ksinfo.setBank_name(t.getMap().get("bank_name").toString());
			}
			if (t.getMap().get("bank_account") != null) {
				ksinfo.setBank_account(t.getMap().get("bank_account").toString());
			}
			ksinfo.setAdd_date(new Date());
			ksinfo.setAdd_user_id(t.getAdd_e_user_id());
			this.konkaSalesmanInfoDao.insertEntity(ksinfo);
		}

		// 保存工作简历
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_PE_PROD_USER");
				a.setLink_id(n);
				this.attachmentDao.insertEntity(a);
			}
		}

		return n;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-04-18
	 */
	public int modifyPeProdUserWithShop(PeProdUser t, List<KonkaMobileSpRelation> konkaMobileSpRelationList) {
		int n = this.peProdUserDao.updateEntity(t);

		/*
		 * if (t.getMap().get("role_id") != null) { PeRoleUser peRoleUser = new PeRoleUser();
		 * peRoleUser.setRole_id(Long.valueOf(t.getRole_id())); peRoleUser.setUser_id(t.getId());
		 * this.peRoleUserDao.updateEntity(peRoleUser); }
		 */
		// 促销员绑定的门店变更
		if (null != t.getMap().get("is_change")) {
			KonkaStoreUserCInfo ksuc = new KonkaStoreUserCInfo();
			ksuc.setAdd_user_id(Long.valueOf(t.getMap().get("add_user_id").toString()));
			ksuc.setAdd_user_job_id(t.getMap().get("add_user_job_id").toString());
			ksuc.setAdd_user_name(t.getMap().get("add_user_name").toString());
			ksuc.setC_type(10);

			if (null != t.getMap().get("new_store_names")) {
				ksuc.setChange_info("此促销员绑定的门店:“" + t.getMap().get("old_store_names").toString() + "”变为“"
						+ t.getMap().get("new_store_names").toString() + "”");
			} else {
				ksuc.setChange_info("此促销员绑定的门店:“" + t.getMap().get("old_store_names").toString() + "”已经全部移除。");
			}

			ksuc.setSs_id(t.getId());
			ksuc.setSs_name(t.getUser_name());
			this.konkaStoreUserCInfoDao.insertEntity(ksuc);
		}

		// 关联店铺
		KonkaMobileSpRelation tt = new KonkaMobileSpRelation();
		tt.setSeller_id(t.getId());
		this.konkaMobileSpRelationDao.deleteEntity(tt);
		for (KonkaMobileSpRelation konkaMobileSpRelation : konkaMobileSpRelationList) {
			konkaMobileSpRelation.setSeller_id(t.getId());
			this.konkaMobileSpRelationDao.insertEntity(konkaMobileSpRelation);
		}

		// 保存促销员新增信息
		if (t.getMap().get("sales_type") != null) {
			PeProdUser temp = new PeProdUser();
			temp.setId(t.getId());
			temp = this.peProdUserDao.selectEntity(temp);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			KonkaSalesmanInfo ksinfo = new KonkaSalesmanInfo();
			ksinfo.setUser_job_id(temp.getJob_id());
			Long info_count = this.konkaSalesmanInfoDao.selectEntityCount(ksinfo);
			ksinfo.setSales_type(Integer.valueOf(t.getMap().get("sales_type").toString()));
			if (t.getMap().get("sales_stat") != null) {
				ksinfo.setSales_stat(Integer.valueOf(t.getMap().get("sales_stat").toString()));
			}
			if (t.getMap().get("work_date") != null) {
				Date wdate;
				try {
					wdate = sdf.parse(t.getMap().get("work_date").toString());
					ksinfo.setWork_date(wdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (t.getMap().get("work_age") != null) {
				ksinfo.setWork_age(Long.valueOf(t.getMap().get("work_age").toString()));
			}
			if (t.getMap().get("nation_name") != null) {
				ksinfo.setNation_name(t.getMap().get("nation_name").toString());
			}
			if (t.getMap().get("academic") != null) {
				ksinfo.setAcademic(t.getMap().get("academic").toString());
			}
			if (t.getMap().get("married") != null) {
				ksinfo.setMarried(Integer.valueOf(t.getMap().get("married").toString()));
			}
			if (t.getMap().get("emergency_man") != null) {
				ksinfo.setEmergency_man(t.getMap().get("emergency_man").toString());
			}
			if (t.getMap().get("emergency_tel") != null) {
				ksinfo.setEmergency_tel(t.getMap().get("emergency_tel").toString());
			}
			if (t.getMap().get("identity_id") != null) {
				ksinfo.setIdentity_id(t.getMap().get("identity_id").toString());
			}
			if (t.getMap().get("id_valid_date") != null) {
				Date iddate;
				try {
					iddate = sdf.parse(t.getMap().get("work_date").toString());
					ksinfo.setId_valid_date(iddate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (t.getMap().get("residence_addr") != null) {
				ksinfo.setResidence_addr(t.getMap().get("residence_addr").toString());
			}
			if (t.getMap().get("bank_name") != null) {
				ksinfo.setBank_name(t.getMap().get("bank_name").toString());
			}
			if (t.getMap().get("bank_account") != null) {
				ksinfo.setBank_account(t.getMap().get("bank_account").toString());
			}
			// 更新促销员信息
			if (info_count > 0) {
				ksinfo.setModify_date(new Date());
				ksinfo.setModify_user_id(t.getAdd_e_user_id());
				if (!(ksinfo.getUser_job_id() == null && ksinfo.getInfo_id() == null)) {
					this.konkaSalesmanInfoDao.updateEntity(ksinfo);
				}
			} else {
				ksinfo.setAdd_date(new Date());
				ksinfo.setAdd_user_id(t.getAdd_e_user_id());
				this.konkaSalesmanInfoDao.insertEntity(ksinfo);
			}
		}

		// 保存工作简历
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_PE_PROD_USER");
				a.setLink_id(t.getId());

				// 先删除后添加
				this.attachmentDao.deleteEntity(a);
				this.attachmentDao.insertEntity(a);
			}
		}

		MvOrgOfCxy cxy = new MvOrgOfCxy();
		cxy.setCxy_user_id(t.getId());
		this.mvOrgOfCxyDao.deleteEntity(cxy);

		cxy.getMap().put("cxy_user_id", t.getId().toString());
		this.mvOrgOfCxyDao.updateMvOrgOfCxyByCxyUserId(cxy);

		MvOrgOfCustomer mc = new MvOrgOfCustomer();
		mc.setUser_id(t.getId());
		this.mvOrgOfCustomerDao.deleteEntity(mc);

		mc.getMap().put("user_id", t.getId());
		this.mvOrgOfCustomerDao.updateMvOrgOfCustomerByUserId(mc);

		MvOrgOfCustomerAll mall = new MvOrgOfCustomerAll();
		mall.setUser_id(t.getId());
		this.mvOrgOfCustomerAllDao.deleteEntity(mall);

		mall.getMap().put("user_id", t.getId());
		this.mvOrgOfCustomerAllDao.updateMvOrgOfCustomerAllByUserId(mall);

		return n;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-26
	 */
	public int modifyPeProdUserWithMultiRoleUser(PeProdUser t) {

		// 如果job_id在变更记录表已经有记录，更新记录表，并插入新的记录
		PeProdUser pp1 = new PeProdUser();
		pp1.setId(t.getId());
		pp1 = this.peProdUserDao.selectEntity(pp1);

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			Attachment att = new Attachment();
			att.setLink_id(t.getId());
			att.setLink_tab("KONKA_PE_PROD_USER");
			att.setFile_desc("zhaopian");
			List<Attachment> atts = this.attachmentDao.selectEntityList(att);
			if (atts != null && atts.size() > 0) {
				for (Attachment attachment : atts) {
					this.attachmentDao.deleteEntity(attachment);
				}
			}

			for (Attachment attachment : t.getAttachmentList()) {
				attachment.setLink_id(t.getId());
				attachment.setLink_tab("KONKA_PE_PROD_USER");
				this.attachmentDao.insertEntity(attachment);
			}
		}

		UserChangeInfo u = new UserChangeInfo();
		u.setJob_id(pp1.getJob_id());
		List<UserChangeInfo> userChangeInfoList = this.userChangeInfoDao.selectUserChangeInfoByTimeList(u);

		if (null != userChangeInfoList && userChangeInfoList.size() > 0) {
			if (!userChangeInfoList.get(0).getUser_name_new().equals(t.getUser_name())
					|| !userChangeInfoList.get(0).getReal_name_new().equals(t.getReal_name())) {
				UserChangeInfo userInfo = userChangeInfoList.get(0);
				userInfo.setEnd_date(new Date());
				userInfo.setUser_id(t.getAdd_e_user_id());
				this.userChangeInfoDao.updateEntity(userInfo);

				UserChangeInfo userInfo2 = new UserChangeInfo();
				userInfo2.setJob_id(userChangeInfoList.get(0).getJob_id());
				userInfo2.setReal_name_new(t.getReal_name());
				userInfo2.setUser_name_new(t.getUser_name());
				userInfo2.setReal_name_old(userChangeInfoList.get(0).getReal_name_new());
				userInfo2.setUser_name_old(userChangeInfoList.get(0).getUser_name_new());
				userInfo2.setStart_date(new Date());
				if (null != t.getMap().get("chan_status")) {
					userInfo2.setChan_status(Integer.valueOf((String) (t.getMap().get("chan_status"))));
				}
				this.userChangeInfoDao.insertEntity(userInfo2);
			}
		} else {
			// 修改的时候，如果真实姓名 或者登录名有变化，往变更记录表插入记录
			PeProdUser pp = new PeProdUser();
			pp.setId(t.getId());
			pp = this.peProdUserDao.selectEntity(pp);
			if (!pp.getReal_name().equals(t.getReal_name()) || !pp.getUser_name().equals(t.getUser_name())) {
				PeProdUser p = new PeProdUser();
				p.setJob_id(pp.getJob_id());
				p = this.peProdUserDao.selectEntity(p);

				UserChangeInfo userInfo = new UserChangeInfo();
				userInfo.setJob_id(p.getJob_id());
				userInfo.setUser_name_old(p.getUser_name());
				userInfo.setUser_name_new(t.getUser_name());
				userInfo.setReal_name_old(p.getReal_name());
				userInfo.setReal_name_new(t.getReal_name());
				userInfo.setStart_date(new Date());
				userInfo.setUser_id(t.getAdd_e_user_id());
				if (null != t.getMap().get("chan_status")) {
					userInfo.setChan_status(Integer.valueOf((String) (t.getMap().get("chan_status"))));
				}
				this.userChangeInfoDao.insertEntity(userInfo);

			}

		}

		// 更新岗位、入职日期、类型
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		KonkaSalesmanInfo ksi = new KonkaSalesmanInfo();
		ksi.setUser_job_id(t.getJob_id());
		Long info_count = this.konkaSalesmanInfoDao.selectEntityCount(ksi);
		if (t.getMap().get("sales_type") != null) {
			ksi.setSales_type(Integer.valueOf(t.getMap().get("sales_type").toString()));
		}
		if (t.getMap().get("position") != null) {
			ksi.setPosition(t.getMap().get("position").toString());
		}
		if (t.getMap().get("work_date") != null) {
			Date wdate;
			try {
				wdate = sdf.parse(t.getMap().get("work_date").toString());
				ksi.setWork_date(wdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// 更新促销员信息
		if (info_count > 0) {
			ksi.setModify_date(new Date());
			ksi.setModify_user_id(t.getAdd_e_user_id());
			if (!(ksi.getUser_job_id() == null && ksi.getInfo_id() == null)) {
				this.konkaSalesmanInfoDao.updateEntity(ksi);
			}
		} else {
			ksi.setAdd_date(new Date());
			ksi.setAdd_user_id(t.getAdd_e_user_id());
			this.konkaSalesmanInfoDao.insertEntity(ksi);
		}

		// 如果用户的姓名发生改变，根据job_id同步更新KonkaR3Store里的信息
		if (null != pp1.getJob_id() && !"".equals(pp1.getJob_id())) {
			KonkaR3Store k2 = new KonkaR3Store();
			k2.setJbjl_job_id(pp1.getJob_id());
			List<KonkaR3Store> k2List = this.konkaR3StoreDao.selectEntityList(k2);
			if (null != k2List && k2List.size() > 0) {
				for (KonkaR3Store konkaR3Store : k2List) {
					KonkaR3Store kk = new KonkaR3Store();
					kk.setStore_id(konkaR3Store.getStore_id());
					kk.setJbjl_name(t.getReal_name());
					this.konkaR3StoreDao.updateEntity(kk);
				}
			}

			KonkaR3Store k3 = new KonkaR3Store();
			k3.setYwy_job_id(pp1.getJob_id());
			List<KonkaR3Store> k3List = this.konkaR3StoreDao.selectEntityList(k3);
			if (null != k3List && k3List.size() > 0) {
				for (KonkaR3Store konkaR3Store : k3List) {
					KonkaR3Store kk = new KonkaR3Store();
					kk.setStore_id(konkaR3Store.getStore_id());
					kk.setYwy_name(t.getReal_name());
					this.konkaR3StoreDao.updateEntity(kk);
				}
			}

			KonkaR3Store k4 = new KonkaR3Store();
			k4.setYwzg_job_id(pp1.getJob_id());
			List<KonkaR3Store> k4List = this.konkaR3StoreDao.selectEntityList(k4);
			if (null != k4List && k4List.size() > 0) {
				for (KonkaR3Store konkaR3Store : k4List) {
					KonkaR3Store kk = new KonkaR3Store();
					kk.setStore_id(konkaR3Store.getStore_id());
					kk.setYwzg_name(t.getReal_name());
					this.konkaR3StoreDao.updateEntity(kk);
				}
			}
		}

		int n = this.peProdUserDao.updateEntity(t);

		// 先删后增
		PeRoleUser deRoleUser = new PeRoleUser();
		deRoleUser.setUser_id(t.getId());
		this.peRoleUserDao.deleteEntity(deRoleUser);

		String roleIds = (String) t.getMap().get("roleIds");
		if (null != roleIds) {
			String[] role_id = StringUtils.split(roleIds, ",");
			for (int i = 0; i < role_id.length; i++) {
				PeRoleUser _ru = new PeRoleUser();
				_ru.setUser_id(t.getId());
				_ru.setRole_id(Long.valueOf(role_id[i]));
				this.peRoleUserDao.insertEntity(_ru);
			}
		}

		MvOrgOfCxy cxy = new MvOrgOfCxy();
		cxy.setCxy_user_id(t.getId());
		this.mvOrgOfCxyDao.deleteEntity(cxy);

		cxy.getMap().put("cxy_user_id", t.getId().toString());
		this.mvOrgOfCxyDao.updateMvOrgOfCxyByCxyUserId(cxy);

		MvOrgOfCustomer mc = new MvOrgOfCustomer();
		mc.setUser_id(t.getId());
		this.mvOrgOfCustomerDao.deleteEntity(mc);

		mc.getMap().put("user_id", t.getId());
		this.mvOrgOfCustomerDao.updateMvOrgOfCustomerByUserId(mc);

		MvOrgOfCustomerAll mall = new MvOrgOfCustomerAll();
		mall.setUser_id(t.getId());
		this.mvOrgOfCustomerAllDao.deleteEntity(mall);

		mall.getMap().put("user_id", t.getId());
		this.mvOrgOfCustomerAllDao.updateMvOrgOfCustomerAllByUserId(mall);

		return n;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-03
	 */
	public List<PeProdUser> getPeProdUserForUserNameList(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserForUserNameList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-12
	 */
	public List<PeProdUser> getPeProdUserListForGroup(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserListForGroup(t);
	}

	public Long createZmdUserRelation(PeProdUser t) {
		// 初始化专卖店与用户的关系
		if (null != t.getZmd_id()) {
			KonkaXxZmdUsers zmduser = new KonkaXxZmdUsers();
			zmduser.setUser_id(t.getId());
			zmduser.setZmd_id(t.getZmd_id());
			if (0L == this.konkaXxZmdUsersDao.selectEntityCount(zmduser)) {
				this.konkaXxZmdUsersDao.insertEntity(zmduser);
			}

			// 初始化专卖店用户的角色
			PeRoleUser roleuser = new PeRoleUser();
			roleuser.setUser_id(t.getId());
			roleuser.setRole_id(400L);
			if (0L == this.peRoleUserDao.selectEntityCount(roleuser)) {
				this.peRoleUserDao.insertEntity(roleuser);
			}
		}
		return null;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-09
	 */
	public List<PeProdUser> getPeProdUserListByDeptIdAndRoleId(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserListByDeptIdAndRoleId(t);
	}

	public List<PeProdUser> getPeProdUserPaginatedListForRoleInfo(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserPaginatedListForRoleInfo(t);
	}

	public Long getPeProdUserForRoleInfoCount(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserForRoleInfoCount(t);
	}

	public List<PeProdUser> getPeProdUserForYwyAndCxyCount(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserForYwyAndCxyCount(t);
	}

	public PeProdUser getPeProdUserForFgs(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserForFgs(t);
	}

	public List<PeProdUser> getPeProdUserForCidList(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserForCidList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-20
	 */
	public Long getpeProdUserWithPositionNameAndFullDeptNameCount(PeProdUser t) {
		return this.peProdUserDao.selectpeProdUserWithPositionNameAndFullDeptNameCount(t);
	}

	@Override
	public List<PeProdUser> getPeProdUserBy_id_deptid(PeProdUser t) {
		return this.peProdUserDao.selectselectPeProdUserBy_id_deptid(t);
	}

	@Override
	public PeProdUser getCXYPeProdUser(PeProdUser t) {
		return this.peProdUserDao.selectCXYPeProdUser(t);
	}

	@Override
	public List<PeProdUser> getPeProdUserByDeptIdAndRoleIdResult(PeProdUser t) {
		return this.peProdUserDao.selectPeProdUserByDeptIdAndRoleIdResult(t);
	}

	@Override
	public List<PeProdUser> getLoginUserList(PeProdUser t) {
		return this.peProdUserDao.selectLoginUserList(t);
	}

}