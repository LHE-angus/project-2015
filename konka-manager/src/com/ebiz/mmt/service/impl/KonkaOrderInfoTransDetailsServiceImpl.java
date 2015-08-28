package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoTransDetailsDao;
import com.ebiz.mmt.dao.KonkaOrderInfoTransEnsuDao;
import com.ebiz.mmt.domain.KonkaOrderInfoTrans;
import com.ebiz.mmt.domain.KonkaOrderInfoTransDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;
import com.ebiz.mmt.service.KonkaOrderInfoTransDetailsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:39
 */
@Service
public class KonkaOrderInfoTransDetailsServiceImpl implements KonkaOrderInfoTransDetailsService {

	@Resource
	private KonkaOrderInfoTransDetailsDao konkaOrderInfoTransDetailsDao;
	
	@Resource
	private KonkaOrderInfoTransEnsuDao konkaOrderInfoTransEnsuDao;
	

	public Long createKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.insertEntity(t);
	}

	@Override
	public List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsPaginatedListTZD(
			KonkaOrderInfoTransDetails entity) {
		
		return this.konkaOrderInfoTransDetailsDao.selectEntityPaginatedListTZD(entity);
	}

	public KonkaOrderInfoTransDetails getKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoTransDetailsCount(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsList(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoTransDetails(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsPaginatedList(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaOrderInfoTransDetailsPaginatedCountTZD(
			KonkaOrderInfoTransDetails t) {
		
		return this.konkaOrderInfoTransDetailsDao.selectKonkaOrderInfoTransDetailsPaginatedCountTZD(t);
	}

	@Override
	public List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsForConfirmList(
			KonkaOrderInfoTransDetails entity) {
		return this.konkaOrderInfoTransDetailsDao.selectKonkaOrderInfoTransDetailsForConfirmList(entity);
	}
	
	/**
	 * XiaoGuoJian
	 * 确认页面数据保存，Details的更新，和Ensu的插入
	 * */
	@Override
	public int modifyKonkaOrderInfoTransDetailsForEnsu(List<KonkaOrderInfoTransDetails> detailsList, KonkaOrderInfoTrans konkaOrderInfoTrans) {
		if(null != detailsList && detailsList.size()>0){
			for(KonkaOrderInfoTransDetails details:detailsList){
				Date date = new Date();
				//-------插入确认数据 start-------------
				KonkaOrderInfoTransEnsu ensu = new KonkaOrderInfoTransEnsu();
				ensu.setEnsu_id(details.getEnsu_id());
//				details.setTrans_ensured_num(1l);
				details.setTrans_ensured_num(this.konkaOrderInfoTransEnsuDao.selectKonkaOrderInfoTransEnsurSumEnsured(ensu) + details.getTrans_ensu_num());//更新已经签收的数据
				ensu.setTrans_ensu_user(details.getTrans_ensu_user());
				ensu.setTrans_ensu_status(details.getTrans_ensu_status());
				ensu.setTrans_ensu_desc(details.getTrans_ensu_desc());
				ensu.setTrans_ensu_num(details.getTrans_ensu_num());
				ensu.setAdd_date(date);
				ensu.setTrans_ensu_date(details.getTrans_ensu_date());
				ensu.setIs_del(0);
				this.konkaOrderInfoTransEnsuDao.insertEntity(ensu);
				//-------插入确认数据 end-------------
				details.setTrans_ensu_date(date);
				this.modifyKonkaOrderInfoTransDetails(details);
			}
		}
		return 0;
	}
	
	/**
	 * XiaoGuoJian
	 * 获取每单的每个型号已经发货的数量总和
	 * */
	@Override
	public Long getKonkaOrderInfoTransDetailsForSumTransNum(KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.selectKonkaOrderInfoTransDetailsForSumTransNum(t);
	}
	
	/**
	 * XiaoGuoJian
	 * 根据具体的发货单号查询即将结案的数据
	 * */
	@Override
	public List<KonkaOrderInfoTransDetails> getKonkaOrderInfoTransDetailsListForOver(
			KonkaOrderInfoTransDetails t) {
		return this.konkaOrderInfoTransDetailsDao.selectKonkaOrderInfoTransDetailsForOver(t);
	}

	
	
}
