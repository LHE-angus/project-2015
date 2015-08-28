package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaItemDao;
import com.ebiz.mmt.dao.KonkaItemPropertyDao;
import com.ebiz.mmt.domain.KonkaItem;
import com.ebiz.mmt.service.KonkaItemService;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
@Service
public class KonkaItemServiceImpl implements KonkaItemService {

	@Resource
	private KonkaItemDao konkaItemDao;
	
	@Resource
	private KonkaItemPropertyDao konkaItemPropertyDao;
	

	public Long createKonkaItem(KonkaItem t) {
		return this.konkaItemDao.insertEntity(t);
	}

	public KonkaItem getKonkaItem(KonkaItem t) {
		KonkaItem entity = this.konkaItemDao.selectEntity(t);
		String p_type1_name = "";
		String p_type2_name = "";
		if(null != entity.getP_type_1()){
			p_type1_name = this.konkaItemPropertyDao.selectPropertyName(entity.getP_type_1());
		}
		if(null != entity.getP_type_2()){
			p_type2_name = this.konkaItemPropertyDao.selectPropertyName(entity.getP_type_2());
		}
		entity.getMap().put("p_type1_name", p_type1_name);
		entity.getMap().put("p_type2_name", p_type2_name);
		return entity;
	}

	public Long getKonkaItemCount(KonkaItem t) {
		return this.konkaItemDao.selectEntityCount(t);
	}

	public List<KonkaItem> getKonkaItemList(KonkaItem t) {
		return this.konkaItemDao.selectEntityList(t);
	}

	public int modifyKonkaItem(KonkaItem t) {
		return this.konkaItemDao.updateEntity(t);
	}

	public int removeKonkaItem(KonkaItem t) {
		return this.konkaItemDao.deleteEntity(t);
	}

	public List<KonkaItem> getKonkaItemPaginatedList(KonkaItem t) {
		
		List<KonkaItem> entityList = this.konkaItemDao.selectEntityPaginatedList(t);
		for(KonkaItem entity : entityList){
			String p_type1_name = "";
			String p_type2_name = "";
			if(null != entity.getP_type_1()){
				p_type1_name = this.konkaItemPropertyDao.selectPropertyName(entity.getP_type_1());
			}
			if(null != entity.getP_type_2()){
				p_type2_name = this.konkaItemPropertyDao.selectPropertyName(entity.getP_type_2());
			}
			entity.getMap().put("p_type1_name", p_type1_name);
			entity.getMap().put("p_type2_name", p_type2_name);
		}
		return entityList;
		//return this.konkaItemDao.selectEntityPaginatedList(t);
	}

}
