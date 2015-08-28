package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.EcBaseStoreDao;
import com.ebiz.mmt.dao.EcStoreAreaDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.EcStoreArea;
import com.ebiz.mmt.service.EcBaseStoreService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcBaseStoreServiceImpl implements EcBaseStoreService {

	@Resource
	private EcBaseStoreDao ecBaseStoreDao;

	@Resource
	private EcStoreAreaDao ecStoreAreaDao;

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	public Long createEcBaseStore(EcBaseStore t) {
		return this.ecBaseStoreDao.insertEntity(t);
	}

	public EcBaseStore getEcBaseStore(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEntity(t);
	}

	public Long getEcBaseStoreCount(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEntityCount(t);
	}

	public List<EcBaseStore> getEcBaseStoreList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEntityList(t);
	}

	public int modifyEcBaseStore(EcBaseStore t) {
		return this.ecBaseStoreDao.updateEntity(t);
	}

	public int removeEcBaseStore(EcBaseStore t) {
		return this.ecBaseStoreDao.deleteEntity(t);
	}

	public List<EcBaseStore> getEcBaseStorePaginatedList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEntityPaginatedList(t);
	}

	public List<EcBaseStore> getEcBaseStoreForPdList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForPdList(t);
	}

	public Long createEcBaseStoreWithPindex(EcBaseStore t) {
		Long ec_store_id = this.ecBaseStoreDao.insertEntity(t);
		List<EcStoreArea> ecStoreAreaList = t.getEcStoreAreaList();
		if (null != ecStoreAreaList && 0 != ecStoreAreaList.size())
			for (EcStoreArea ecStoreArea : ecStoreAreaList) {
				ecStoreArea.setStore_id(ec_store_id);
				this.ecStoreAreaDao.insertEntity(ecStoreArea);
			}
		return ec_store_id;
	}

	public Long createEcBaseStoreWithPindexArea(EcBaseStore t, String e_id) {
		Long s_id = this.ecBaseStoreDao.insertEntity(t);

		if (StringUtils.isNotBlank(e_id)) {
			String[] ids = e_id.split("@");
			for (String c_id : ids) {

				EcStoreArea ea = new EcStoreArea();
				ea.setStore_id(s_id);
				ea.setPindex_id(Long.valueOf(c_id));
				BaseProvinceListFour bf = new BaseProvinceListFour();
				bf.setP_index(Long.valueOf(c_id));
				bf = this.baseProvinceListFourDao.selectEntity(bf);
				ea.setP_name(bf.getP_name());
				this.ecStoreAreaDao.insertEntity(ea);

			}
		}
		return s_id;
	}

	public int modifyEcBaseStoreAndArea(EcBaseStore t, String store_id, String e_id) {
		int id = this.ecBaseStoreDao.updateEntity(t);
		if (StringUtils.isNotBlank(e_id)) {
			EcStoreArea ea = new EcStoreArea();
			ea.setStore_id(Long.valueOf(store_id));
			List<EcStoreArea> ecs = this.ecStoreAreaDao.selectEntityList(ea);
			for (EcStoreArea ee : ecs) {
				EcStoreArea e = new EcStoreArea();
				e.setId(ee.getId());
				this.ecStoreAreaDao.deleteEntity(e);
			}

			String[] ids = e_id.split("@");
			for (String c_id : ids) {
				EcStoreArea eb = new EcStoreArea();
				eb.setStore_id(Long.valueOf(store_id));
				eb.setPindex_id(Long.valueOf(c_id));

				BaseProvinceListFour bf = new BaseProvinceListFour();
				bf.setP_index(Long.valueOf(c_id));
				bf = this.baseProvinceListFourDao.selectEntity(bf);
				eb.setP_name(bf.getP_name());
				this.ecStoreAreaDao.insertEntity(eb);
			}
		} else {
			EcStoreArea ea = new EcStoreArea();
			// 123.235.356
			ea.setStore_id(Long.valueOf(store_id));
			List<EcStoreArea> ecs = this.ecStoreAreaDao.selectEntityList(ea);
			for (EcStoreArea ee : ecs) {
				EcStoreArea e = new EcStoreArea();
				e.setId(ee.getId());
				this.ecStoreAreaDao.deleteEntity(e);
			}

		}
		return id;
	}

	public Long getEcBaseStoreForAreaCount(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForAreaCount(t);
	}

	public Long getEcBaseStoreForDeptCount(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForDeptCount(t);
	}

	public List<EcBaseStore> getEcBaseStoreForDeptNamePaginatedList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForDeptNamePaginatedList(t);
	}

	public List<EcBaseStore> getEcBaseStoreForAreaForList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForAreaForList(t);
	}

	public List<EcBaseStore> getEcBaseStoreForPindexIdForList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForPindexIdForList(t);
	}

	public List<EcBaseStore> getEcBaseStoreForPindexAndPnameList(EcBaseStore t) {
		return this.ecBaseStoreDao.selectEcBaseStoreForPindexAndPnameList(t);
	}

}
