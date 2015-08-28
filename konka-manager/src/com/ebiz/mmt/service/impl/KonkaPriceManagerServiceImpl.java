package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPriceManagerDao;
import com.ebiz.mmt.domain.KonkaPriceManager;
import com.ebiz.mmt.service.KonkaPriceManagerService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-08 15:45:59
 */
@Service
public class KonkaPriceManagerServiceImpl implements KonkaPriceManagerService {

	@Resource
	private KonkaPriceManagerDao konkaPriceManagerDao;

	public Long createKonkaPriceManager(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.insertEntity(t);
	}

	public KonkaPriceManager getKonkaPriceManager(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.selectEntity(t);
	}

	public Long getKonkaPriceManagerCount(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.selectEntityCount(t);
	}

	public List<KonkaPriceManager> getKonkaPriceManagerList(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.selectEntityList(t);
	}

	public int modifyKonkaPriceManager(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.updateEntity(t);
	}

	public int removeKonkaPriceManager(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.deleteEntity(t);
	}

	public List<KonkaPriceManager> getKonkaPriceManagerPaginatedList(KonkaPriceManager t) {
		return this.konkaPriceManagerDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-04-09
	 */
	public Long createKonkaPriceManagerForExcel(List<KonkaPriceManager> entityList) {
		if (entityList != null && entityList.size() > 0) {
			for (KonkaPriceManager kpm : entityList) {
				KonkaPriceManager konkaPriceManager = new KonkaPriceManager();
				konkaPriceManager.setGoods_name(kpm.getGoods_name());
				konkaPriceManager.setGoods_sn(kpm.getGoods_sn());
				konkaPriceManager.setStore_name(kpm.getStore_name());
				konkaPriceManager.setStore_sn(kpm.getStore_sn());
				konkaPriceManager.setPrice(kpm.getPrice());
				konkaPriceManager.setStart_date(kpm.getStart_date());
				konkaPriceManager.setEnd_date(kpm.getEnd_date());
				konkaPriceManager.setAdd_date(new Date());
				this.konkaPriceManagerDao.insertEntity(kpm);
			}
		}
		return null;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-04-09
	 * @param goods_name
	 *            型号
	 * @param store_sn
	 *            仓库编码
	 * @param query_date
	 *            日期
	 * @return price 价格
	 */
	public BigDecimal getKonkaPriceManagerForPrice(String goods_name, String store_sn, String query_date) {
		KonkaPriceManager entity = new KonkaPriceManager();
		if (StringUtils.isNotBlank(goods_name)) {
			entity.setGoods_name(goods_name);
		}
		if (StringUtils.isNotBlank(store_sn)) {
			entity.setStore_sn(store_sn);
		}
		if (StringUtils.isNotBlank(query_date)) {
			entity.getMap().put("query_date", query_date);
		}
		// entity = this.konkaPriceManagerDao.selectEntity(entity);
		List<KonkaPriceManager> entityList = this.konkaPriceManagerDao.selectEntityList(entity);
		if (entityList != null && entityList.size() > 0) {
			if (entityList.get(0) != null && entityList.get(0).getPrice() != null) {
				return entityList.get(0).getPrice();
			}
		}
		return new BigDecimal("0");
	}

}
