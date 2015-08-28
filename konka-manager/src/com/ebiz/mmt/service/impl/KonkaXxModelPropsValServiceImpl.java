package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxModelPropsValDao;
import com.ebiz.mmt.domain.KonkaXxModelPropsVal;
import com.ebiz.mmt.service.KonkaXxModelPropsValService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
@Service
public class KonkaXxModelPropsValServiceImpl implements KonkaXxModelPropsValService {

	@Resource
	private KonkaXxModelPropsValDao konkaXxModelPropsValDao;

	public Long createKonkaXxModelPropsVal(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.insertEntity(t);
	}

	public KonkaXxModelPropsVal getKonkaXxModelPropsVal(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.selectEntity(t);
	}

	public Long getKonkaXxModelPropsValCount(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.selectEntityCount(t);
	}

	public List<KonkaXxModelPropsVal> getKonkaXxModelPropsValList(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.selectEntityList(t);
	}

	public int modifyKonkaXxModelPropsVal(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.updateEntity(t);
	}

	public int removeKonkaXxModelPropsVal(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.deleteEntity(t);
	}

	public List<KonkaXxModelPropsVal> getKonkaXxModelPropsValPaginatedList(KonkaXxModelPropsVal t) {
		return this.konkaXxModelPropsValDao.selectEntityPaginatedList(t);
	}

	/**
	 * @auhor Hu,Hao
	 * @version 2013-04-07
	 */
	public void createKonkaXxModelPropsValList(KonkaXxModelPropsVal t) {
		this.konkaXxModelPropsValDao.deleteEntity(t);
		String md_name = t.getMd_name();
		String[] prop_ids = (String[]) t.getMap().get("prop_ids");
		String[] prop_values = (String[]) t.getMap().get("prop_values");

		for (int i = 0; i < prop_ids.length; i++) {
			KonkaXxModelPropsVal temp = new KonkaXxModelPropsVal();
			temp.setMd_name(md_name);
			temp.setProp_id(Long.valueOf(prop_ids[i]));
			int index = prop_values[i].indexOf("|||");
			if (index > 0) {
				String prop_value = (prop_values[i]).substring(index + 3, prop_values[i].length());
				String prop_item_ids = (prop_values[i]).substring(0, index);
				temp.setProp_value(prop_value);
				temp.setProp_item_ids(prop_item_ids);
			} else {
				temp.setProp_value(prop_values[i]);
			}

			this.konkaXxModelPropsValDao.insertEntity(temp);
		}
	}
}
