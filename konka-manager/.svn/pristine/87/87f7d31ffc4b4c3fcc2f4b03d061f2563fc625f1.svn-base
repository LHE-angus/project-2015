package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.BranchAssignDao;
import com.ebiz.mmt.dao.KonkaDeptDao;
import com.ebiz.mmt.dao.KonkaR3ShopDao;
import com.ebiz.mmt.dao.KonkaR3ShopDevDao;
import com.ebiz.mmt.dao.KonkaR3ShopLinkDao;
import com.ebiz.mmt.dao.KonkaR3ShopNewDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.KonkaR3ShopNew;
import com.ebiz.mmt.service.KonkaR3ShopNewService;


@Service
public class KonkaR3ShopNewServiceImpl implements KonkaR3ShopNewService {

	@Resource
	private KonkaR3ShopNewDao konkaR3ShopNewDao;
	
	@Resource
	private KonkaR3ShopDevDao konkaR3ShopDevDao;
	
	@Resource
	private AttachmentDao attachmentDao;
	
	@Resource
	private KonkaDeptDao konkaDeptDao;
	
	@Resource
	private KonkaR3ShopDao konkaR3ShopDao;
	
	@Resource
	private BranchAssignDao branchAssignDao;
	
	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;
	
	@Resource
	private KonkaR3ShopLinkDao konkaR3ShopLinkDao;
	

	public Long createKonkaR3ShopNew(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.insertEntity(t);
	}

	public KonkaR3ShopNew getKonkaR3ShopNew(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.selectEntity(t);
	}

	public Long getKonkaR3ShopNewCount(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.selectEntityCount(t);
	}

	public List<KonkaR3ShopNew> getKonkaR3ShopNewList(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.selectEntityList(t);
	}

	/**
	 * 修改开户信息
	 */
	public int modifyKonkaR3ShopNew(KonkaR3ShopNew t) throws Exception{
		
		//更新附件关联记录
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_R3_SHOP_NEW");
				a.setLink_id(t.getCust_id());
				
				//先删除后添加
				this.attachmentDao.deleteEntity(a);
				this.attachmentDao.insertEntity(a);
			}
		}
		
		return this.konkaR3ShopNewDao.updateEntity(t);
	}

	public int removeKonkaR3ShopNew(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.deleteEntity(t);
	}

	public List<KonkaR3ShopNew> getKonkaR3ShopNewPaginatedList(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.selectEntityPaginatedList(t);
	}
	
	@Override
	public Long saveNewCustomer(KonkaR3ShopNew t) throws Exception{
		String source_flag = (String) t.getMap().get("source_flag");
		Long dev_cust_id = 0L;
		if("dev".equals(source_flag)){
			dev_cust_id = t.getCust_id();
			t.setCust_id(null);
			t.setIs_new(1);
		}else{
			t.setIs_new(0);
		}
		t.setAdd_date(new Date());
		t.setIs_del(0);
		t.setAudit_stat(0);
		
		//当前审批角色ID
		t.setCur_audit_user_id(35L);
		Long cust_id=this.konkaR3ShopNewDao.insertEntity(t);
		
		//保存附件关联记录
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_R3_SHOP_NEW");
				a.setLink_id(cust_id);
				this.attachmentDao.insertEntity(a);
			}
		}
		
		//判断是否从客户开拓提交过来的
		if("dev".equals(source_flag)){
			if(cust_id>0){
				KonkaR3ShopDev entity = new KonkaR3ShopDev();
				entity.setCust_id(dev_cust_id);
				entity.setIs_submit(1);
				this.konkaR3ShopDevDao.updateEntity(entity);
			}
		}
		
		return cust_id;
	}

	@Override
	public Long getWaitAuditCustCount(KonkaR3ShopNew t) {
		return this.konkaR3ShopNewDao.selectWaitAuditCustCount(t);
	}

	@Override
	public List<HashMap> getWaitAuditCustList(KonkaR3ShopNew t) {
		
		List<HashMap> result = this.konkaR3ShopNewDao.selectWaitAuditCustList(t);
		
		// 处理省市县镇
		/*if (null != result && result.size() > 0) {
			for (HashMap c : result) {
				if (c.get("ENTP_P_INDEX") != null && String.valueOf(c.get("ENTP_P_INDEX")).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						c.put("PROVINCE", b.getP_name());
					}
					if(!(String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 2) + "0000").equals(String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 4) + "00")){
						// 市
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("CITY", b.getP_name());
						}
					}
					if(!String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 6).equals(String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 4) + "00")){
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("COUNTRY", b.getP_name());
						}
					}
					if(!String.valueOf(c.get("ENTP_P_INDEX")).substring(0, 6).equals(String.valueOf(c.get("ENTP_P_INDEX")))){
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("ENTP_P_INDEX"))));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("TOWN", b.getP_name());
						}
					}
				}
			}
		}*/
		
		return result;
	}

	@Override
	public int addToKonkaR3Shop(KonkaR3ShopNew t) {
		int result = 0;
		try{
			
			KonkaR3ShopNew entity = this.konkaR3ShopNewDao.selectKonkaR3ShopNewByCustId(t);
			
			KonkaR3Shop krs = new KonkaR3Shop();
			krs.setR3_code(t.getLink_r3_code());
			krs = this.konkaR3ShopDao.selectKonkaR3ShopByR3Code(krs);
			
			if(null==krs){
				return result;
			}
			krs.setCustomer_type(entity.getCustomer_type().toString());
			
			//机构信息
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(entity.getSubcomp_id());
			kd = konkaDeptDao.selectEntity(kd);
			krs.setBranch_area_name_2(kd.getDept_sn());
			krs.setBranch_area_name(kd.getDept_name());
			
			krs.setR3_desc(entity.getMemo());
			krs.setCustomer_name(entity.getCust_name());
			krs.setImport_user_id((Long)(entity.getMap().get("syn_user_id")));
			krs.setLink_man_name(entity.getLink_man_name());
			krs.setLink_man_tel(entity.getLink_man_tel());
			krs.setLink_man_mobile(entity.getLink_man_mobile());
			krs.setLink_man_addr(entity.getLink_man_addr());
			krs.setLink_man_post(entity.getLink_man_post());
			krs.setHost_name(entity.getHost_name());
			krs.setHost_tel(entity.getHost_tel());
			krs.setCreate_date(new Date());
			krs.setEntp_reg_money(entity.getEntp_reg_money());
			krs.setEntp_scale(entity.getEntp_scale());
			krs.setEntp_man_count(entity.getEntp_man_count());
			krs.setEntp_tel(entity.getEntp_tel());
			krs.setEntp_birthday(entity.getEntp_birthday());
			krs.setEntp_fax(entity.getEntp_fax());
			krs.setEntp_post(entity.getEntp_post());
			krs.setEntp_p_level(entity.getEntp_p_level());
			krs.setEntp_addr(entity.getEntp_addr());
			krs.setEntp_www(entity.getEntp_www());
			krs.setEntp_main_pds(entity.getEntp_main_pds());
			krs.setEntp_type(entity.getEntp_type());
			krs.setEntp_p_index(entity.getEntp_p_index());
			krs.setIs_konka(1);
			krs.setStatus(2);
			krs.setModify_user_id((Long)(entity.getMap().get("syn_user_id")));
			krs.setAdd_name((String)(entity.getMap().get("syn_user_name")));
			
			int r3 = this.konkaR3ShopDao.updateEntity(krs);
			
			if(r3>0){
				//关联业务员
				BranchAssign ba = new BranchAssign();
				ba.setKonka_r3_id(krs.getId());
				ba.setBranch_type(1);
				
				Long bnum = this.branchAssignDao.selectEntityCount(ba);
				
				//未分配业务员则同步
				if(bnum<1){
					ba.setFgs_id(entity.getSubcomp_id());
					ba.setUser_id(entity.getAdd_user_id());
					ba.setAdd_date(new Date());
					ba.setAdd_user_id(Long.valueOf(t.getMap().get("syn_user_id").toString()));
					this.branchAssignDao.insertEntity(ba);
				}
				
				//同步联系人
				//查询是否已有默认联系人
				KonkaR3ShopLink link1 = new KonkaR3ShopLink();
				link1.setR3_shop_id(krs.getId());
				link1.setIs_default("0");
				
				Long count = this.konkaR3ShopLinkDao.selectEntityCount(link1);
				
				if(count>0){
					link1.setIs_default("1");
				}
				link1.setReal_name(entity.getHost_name());
				link1.setAdd_user_id(Long.valueOf(t.getMap().get("syn_user_id").toString()));
				link1.setAdd_date(new Date());
				link1.setIs_valid("0");
				link1.setPosition("4");
				this.konkaR3ShopLinkDao.insertEntity(link1);
				
				String[] link_name = entity.getLink_man_name().split(",");
				String[] link_tel = entity.getLink_man_tel().split(",");
				
				for(int i=0;i<link_name.length;i++){
					link1 = new KonkaR3ShopLink();
					link1.setR3_shop_id(krs.getId());
					link1.setReal_name(link_name[i]);
					link1.setAdd_user_id(Long.valueOf(t.getMap().get("syn_user_id").toString()));
					link1.setAdd_date(new Date());
					link1.setIs_valid("0");
					link1.setIs_default("1");
					link1.setPosition("1");
					if(i+1<=link_tel.length){
						link1.setTel(link_tel[i]);
					}
					this.konkaR3ShopLinkDao.insertEntity(link1);
				}
				
				entity.setIs_syn(1);
				entity.setLink_r3_code(krs.getR3_code());
				result = this.konkaR3ShopNewDao.updateEntity(entity);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
