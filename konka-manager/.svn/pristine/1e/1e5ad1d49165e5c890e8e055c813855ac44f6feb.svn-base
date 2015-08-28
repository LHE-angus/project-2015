package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseStoreDao;
import com.ebiz.mmt.dao.JBaseStoreR3Dao;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.service.JBaseStoreService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBaseStoreServiceImpl implements JBaseStoreService {

	@Resource
	private JBaseStoreDao jBaseStoreDao;
	@Resource
	private JBaseStoreR3Dao jBaseStoreR3Dao;

	public Long createJBaseStore(JBaseStore t) {
		if (t.getIs_dft_buy_store() == 1) {
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.setIs_dft_buy_store(0);
			jBaseStore.setC_id(t.getC_id());
			this.jBaseStoreDao.updateEntity(jBaseStore);
		}

		if (t.getIs_dft_sell_store() == 1) {
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.setIs_dft_sell_store(0);
			jBaseStore.setC_id(t.getC_id());
			this.jBaseStoreDao.updateEntity(jBaseStore);
		}
		Long store_id = this.jBaseStoreDao.insertEntity(t);
		if (null != t.getjBaseStoreR3()) {
			JBaseStoreR3 jBaseStoreR3 = new JBaseStoreR3();
			jBaseStoreR3 = t.getjBaseStoreR3();
			jBaseStoreR3.setStore_id(store_id);
			this.jBaseStoreR3Dao.insertEntity(jBaseStoreR3);
		}
		return store_id;
	}

	public JBaseStore getJBaseStore(JBaseStore t) {
		return this.jBaseStoreDao.selectEntity(t);
	}

	public Long getJBaseStoreCount(JBaseStore t) {
		return this.jBaseStoreDao.selectEntityCount(t);
	}

	public List<JBaseStore> getJBaseStoreList(JBaseStore t) {
		return this.jBaseStoreDao.selectEntityList(t);
	}

	public int modifyJBaseStore(JBaseStore t) {
		if (t.getIs_dft_buy_store() == 1) {
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.setIs_dft_buy_store(0);
			jBaseStore.setC_id(t.getC_id());
			this.jBaseStoreDao.updateEntity(jBaseStore);
		}

		if (t.getIs_dft_sell_store() == 1) {
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.setIs_dft_sell_store(0);
			jBaseStore.setC_id(t.getC_id());
			this.jBaseStoreDao.updateEntity(jBaseStore);
		}
		t.setC_id(null);
		return this.jBaseStoreDao.updateEntity(t);
	}

	public int removeJBaseStore(JBaseStore t) {
		return this.jBaseStoreDao.deleteEntity(t);
	}

	public List<JBaseStore> getJBaseStorePaginatedList(JBaseStore t) {
		return this.jBaseStoreDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<JBaseStoreR3> getStoreSnWeForList(JBaseStoreR3 jBaseStoreR3) {
		
		return this.jBaseStoreDao.selectStoreSnWeForList(jBaseStoreR3);
	}

	@Override
	public JBaseStore getJBaseStoreAndDetails(JBaseStore entity) {
		
		return this.jBaseStoreDao.selectJBaseStoreAndDetails(entity);
	}

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	@Override
	public List<JBaseStore> getJBaseStoreForR3PaginatedList(JBaseStore jBaseStore) {
		return this.jBaseStoreDao.selectJBaseStoreForR3PaginatedList(jBaseStore);
	}

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	@Override
	public List<JBaseStore> getJBaseStoreForR3List(JBaseStore jBaseStore) {
		return this.jBaseStoreDao.selectJBaseStoreForR3List(jBaseStore);
	}

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore flag true送达方可以为空，false 送达方不可以为空
	 */
	public List<JBaseStore> getJBaseStoreForR3WithSdfNullOrNotList(JBaseStore jBaseStore, boolean flag) {
		if (jBaseStore != null && jBaseStore.getC_id() != null) {
			if (flag == false) {
				jBaseStore.getMap().put("sale_r3_code_is_null", false);
			}
			List<JBaseStore> list = getJBaseStoreForR3List(jBaseStore);
			return list;
		}
		return null;
	}

	@Override
	public List<HashMap> getJBaseStoreMapList(JBaseStore jBaseStore) {
		return this.jBaseStoreDao.selectJBaseStoreMapList(jBaseStore);
	}

	@Override
	public void clearStockUseR3code(HashMap map) {
		this.jBaseStoreDao.clearStockUseR3code(map);

	}

}
