package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpActivityTypeDao;
import com.ebiz.mmt.dao.KonkaSpMdTypeDao;
import com.ebiz.mmt.domain.KonkaSpActivityType;
import com.ebiz.mmt.domain.KonkaSpMdType;
import com.ebiz.mmt.service.KonkaSpActivityTypeService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaSpActivityTypeServiceImpl implements KonkaSpActivityTypeService {

	@Resource
	private KonkaSpActivityTypeDao konkaSpActivityTypeDao;

	@Resource
	private KonkaSpMdTypeDao konkaSpMdTypeDao;

	public Long createKonkaSpActivityType(KonkaSpActivityType t) {
		Long id = this.konkaSpActivityTypeDao.insertEntity(t);

		String md_name_pls = (String) t.getMap().get("md_name_pls");
		if (null != md_name_pls) {
			String[] split_pls = StringUtils.split(md_name_pls, ",");
			for (int i = 0; i < split_pls.length; i++) {
				KonkaSpMdType entity = new KonkaSpMdType();
				entity.setLink_id(t.getId());
				entity.setType(1);
				entity.setMd_name(split_pls[i]);
				this.konkaSpMdTypeDao.insertEntity(entity);
			}
		}

		String md_name_jxs = (String) t.getMap().get("md_name_jxs");
		if (null != md_name_jxs) {
			String[] split_jxs = StringUtils.split(md_name_jxs, ",");
			for (int i = 0; i < split_jxs.length; i++) {
				KonkaSpMdType entity = new KonkaSpMdType();
				entity.setLink_id(t.getId());
				entity.setType(2);
				entity.setMd_name(split_jxs[i]);
				this.konkaSpMdTypeDao.insertEntity(entity);
			}
		}
		return id;
	}

	public KonkaSpActivityType getKonkaSpActivityType(KonkaSpActivityType t) {
		return this.konkaSpActivityTypeDao.selectEntity(t);
	}

	public Long getKonkaSpActivityTypeCount(KonkaSpActivityType t) {
		return this.konkaSpActivityTypeDao.selectEntityCount(t);
	}

	public List<KonkaSpActivityType> getKonkaSpActivityTypeList(KonkaSpActivityType t) {
		return this.konkaSpActivityTypeDao.selectEntityList(t);
	}

	public int modifyKonkaSpActivityType(KonkaSpActivityType t) {
		int id = this.konkaSpActivityTypeDao.updateEntity(t);
		
		KonkaSpMdType mdType = new KonkaSpMdType();
		mdType.setLink_id(t.getId());
		List<KonkaSpMdType> mdTypeList = this.konkaSpMdTypeDao.selectEntityList(mdType);
		if(null != mdTypeList && 0 != mdTypeList.size()) {
			Long[] pks = new Long[mdTypeList.size()];
			int i = 0;
			for(KonkaSpMdType st : mdTypeList) {
				pks[i] = st.getId();
				i++;
			}
			//删除
			mdType = new KonkaSpMdType();
			mdType.getMap().put("pks", pks);
			this.konkaSpMdTypeDao.deleteEntity(mdType);
		}
		
		String md_name_pls = (String) t.getMap().get("md_name_pls");
		if (null != md_name_pls) {
			String[] split_pls = StringUtils.split(md_name_pls, ",");
			for (int i = 0; i < split_pls.length; i++) {
				KonkaSpMdType entity = new KonkaSpMdType();
				entity.setLink_id(t.getId());
				entity.setType(1);
				entity.setMd_name(split_pls[i]);
				this.konkaSpMdTypeDao.insertEntity(entity);
			}
		}

		String md_name_jxs = (String) t.getMap().get("md_name_jxs");
		if (null != md_name_jxs) {
			String[] split_jxs = StringUtils.split(md_name_jxs, ",");
			for (int i = 0; i < split_jxs.length; i++) {
				KonkaSpMdType entity = new KonkaSpMdType();
				entity.setLink_id(t.getId());
				entity.setType(2);
				entity.setMd_name(split_jxs[i]);
				this.konkaSpMdTypeDao.insertEntity(entity);
			}
		}
		return id;
	}

	public int removeKonkaSpActivityType(KonkaSpActivityType t) {
		int id = this.konkaSpActivityTypeDao.deleteEntity(t);
		
		KonkaSpMdType mdType = new KonkaSpMdType();
		mdType.setLink_id(t.getId());
		this.konkaSpMdTypeDao.deleteEntity(mdType);
		return id;
	}

	public List<KonkaSpActivityType> getKonkaSpActivityTypePaginatedList(KonkaSpActivityType t) {
		return this.konkaSpActivityTypeDao.selectEntityPaginatedList(t);
	}

}
