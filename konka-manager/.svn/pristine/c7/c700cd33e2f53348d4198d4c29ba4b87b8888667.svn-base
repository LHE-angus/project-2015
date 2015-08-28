package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PdModelPropsValDao;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.mmt.service.PdModelPropsValService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:12
 */
@Service
public class PdModelPropsValServiceImpl implements PdModelPropsValService {

	@Resource
	private PdModelPropsValDao pdModelPropsValDao;

	public Long createPdModelPropsVal(PdModelPropsVal t) {
		return this.pdModelPropsValDao.insertEntity(t);
	}

	public PdModelPropsVal getPdModelPropsVal(PdModelPropsVal t) {
		return this.pdModelPropsValDao.selectEntity(t);
	}

	public Long getPdModelPropsValCount(PdModelPropsVal t) {
		return this.pdModelPropsValDao.selectEntityCount(t);
	}

	public List<PdModelPropsVal> getPdModelPropsValList(PdModelPropsVal t) {
		return this.pdModelPropsValDao.selectEntityList(t);
	}

	public int modifyPdModelPropsVal(PdModelPropsVal t) {
		return this.pdModelPropsValDao.updateEntity(t);
	}

	public int removePdModelPropsVal(PdModelPropsVal t) {
		return this.pdModelPropsValDao.deleteEntity(t);
	}

	public List<PdModelPropsVal> getPdModelPropsValPaginatedList(PdModelPropsVal t) {
		return this.pdModelPropsValDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-24
	 */
	public void createPdModelPropsValList(PdModelPropsVal t) {
		this.pdModelPropsValDao.deleteEntity(t);
		Long pd_id = t.getPd_id();
		String[] prop_ids = (String[]) t.getMap().get("prop_ids");
		String[] prop_values = (String[]) t.getMap().get("prop_values");

		for (int i = 0; i < prop_ids.length; i++) {
			PdModelPropsVal temp = new PdModelPropsVal();
			temp.setPd_id(pd_id);
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

			this.pdModelPropsValDao.insertEntity(temp);
		}
	}

	/**
	 * @author Chen,LiLin
	 * @date 2011-03-25 11:57:12
	 */
	public List<PdModelPropsVal> getPdModelPropsValWithPropInfoList(PdModelPropsVal t) {
		return pdModelPropsValDao.selectPdModelPropsValWithPropInfoList(t);
	}

	@Override
	public List<String> getPdModelPropsValGroupByList(PdModelPropsVal t) {
		return pdModelPropsValDao.selectPdModelPropsValGroupByList(t);
	}
}
