package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FighterInfoDao;
import com.ebiz.mmt.dao.KonkaProSqDao;
import com.ebiz.mmt.dao.KonkaXxZmdAuditUserHisDao;
import com.ebiz.mmt.domain.FighterInfo;
import com.ebiz.mmt.domain.KonkaProSq;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.service.KonkaProSqService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-10-25 11:10:02
 */
@Service
public class KonkaProSqServiceImpl implements KonkaProSqService {

	@Resource
	private KonkaProSqDao konkaProSqDao;
	@Resource
	private FighterInfoDao fighterInfoDao;
	@Resource
	private KonkaXxZmdAuditUserHisDao konkaXxZmdAuditUserHisDao;

	public void modifyKonkaProSqAndHistory(KonkaProSq t) {
		this.konkaProSqDao.updateEntity(t);// 更新审核记录
		// 历史记录表
		KonkaXxZmdAuditUserHis entity = t.getKonkaXxZmdAuditUserHis();
		this.konkaXxZmdAuditUserHisDao.insertEntity(entity);
	}
	
	public Long createKonkaProSq(KonkaProSq t) {
		Long id = this.konkaProSqDao.insertEntity(t);
		List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList = t.getKonkaXxZmdAuditUserHisList();
		if (null != konkaXxZmdAuditUserHisList) {
			for (KonkaXxZmdAuditUserHis KonkaXxZmdAuditUserHis : konkaXxZmdAuditUserHisList) {
				KonkaXxZmdAuditUserHis.setZmd_user_id(id);
				this.konkaXxZmdAuditUserHisDao.insertEntity(KonkaXxZmdAuditUserHis);
			}
		}
		return id;
	}

	public KonkaProSq getKonkaProSq(KonkaProSq t) {
		return this.konkaProSqDao.selectEntity(t);
	}

	public Long getKonkaProSqCount(KonkaProSq t) {
		return this.konkaProSqDao.selectEntityCount(t);
	}

	public List<KonkaProSq> getKonkaProSqList(KonkaProSq t) {
		return this.konkaProSqDao.selectEntityList(t);
	}

	public int modifyKonkaProSq(KonkaProSq t) {
		
		if(t.getFile_state()==5){
			//增加审核记录
			List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList = t.getKonkaXxZmdAuditUserHisList();
			if (null != konkaXxZmdAuditUserHisList) {
				for (KonkaXxZmdAuditUserHis KonkaXxZmdAuditUserHis : konkaXxZmdAuditUserHisList) {
					KonkaXxZmdAuditUserHis.setZmd_user_id(Long.valueOf(t.getId()));
					this.konkaXxZmdAuditUserHisDao.insertEntity(KonkaXxZmdAuditUserHis);
				}
			}
			//重新设置审核状态为提交
			t.setFile_state(1);
			}
		
		int id = this.konkaProSqDao.updateEntity(t);
		if (null != t.getFighterInfoList()) {
			FighterInfo entity = new FighterInfo();
			entity.setPro_id(t.getId());
			entity.getMap().put("del_by_pro_id", true);
			this.fighterInfoDao.deleteEntity(entity);

			for (FighterInfo fighterInfo : t.getFighterInfoList()) {
				//System.out.println("xxxxxxxxxxxxxxxx--------->{}");
				fighterInfo.setPro_id(t.getId());
				this.fighterInfoDao.insertEntity(fighterInfo);
			}
		}
		
		
		return id;

	}

	public int removeKonkaProSq(KonkaProSq t) {
		return this.konkaProSqDao.deleteEntity(t);
	}

	public List<KonkaProSq> getKonkaProSqPaginatedList(KonkaProSq t) {
		return this.konkaProSqDao.selectEntityPaginatedList(t);
	}

	public Long getKonkaProSqNoMax(KonkaProSq t) {
		return this.konkaProSqDao.selectKonkaProSqNoMax(t);
	}

	public List<KonkaProSq> getKonkaProSqAndFighterInfoPaginatedList(KonkaProSq t) {
		return this.konkaProSqDao.selectKonkaProSqAndFighterInfoPaginatedList(t);
	}

}
