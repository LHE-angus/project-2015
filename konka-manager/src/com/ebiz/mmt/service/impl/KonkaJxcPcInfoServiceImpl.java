package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.dao.KonkaJxcPcInfoDao;
import com.ebiz.mmt.dao.KonkaJxcStoreStateDao;
import com.ebiz.mmt.dao.KonkaJxcStoreUpdateRecordDao;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.KonkaJxcPcInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaJxcStoreUpdateRecord;
import com.ebiz.mmt.service.KonkaJxcPcInfoService;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcPcInfoServiceImpl implements KonkaJxcPcInfoService {

	@Resource
	private KonkaJxcStoreStateDao konkaJxcStoreStateDao;

	@Resource
	private KonkaJxcPcInfoDao konkaJxcPcInfoDao;

	@Resource
	private JxcPdDao jxcPdDao;

	@Resource
	private KonkaJxcStoreUpdateRecordDao konkaJxcStoreUpdateRecordDao;

	public Long createKonkaJxcPcInfo(KonkaJxcPcInfo t) {
		if (t.getPc_type() == 0) {
			JxcPd sbd = new JxcPd();// 网点端更新库存状态
			sbd.setCount(t.getPc_num());
			sbd.setId(t.getPd_id());
			jxcPdDao.updateEntity(sbd);
		}
		if (t.getPc_type() == 1) {
			KonkaJxcStoreState kjss = t.getKonkaJxcStoreState();// 管理端更新库存状态
			if (null != t.getPd_id()) {
				kjss.getMap().put("store_id", kjss.getStore_id());
				kjss.getMap().put("pd_id", kjss.getPd_id());
				kjss.getMap().put("dept_id", kjss.getDept_id());
				konkaJxcStoreStateDao.updateEntity(kjss);

				// 插入库存更新记录
				KonkaJxcStoreUpdateRecord konkaJxcStoreUpdateRecord = new KonkaJxcStoreUpdateRecord();
				konkaJxcStoreUpdateRecord.setAdd_user_id(t.getAdd_user_id());
				konkaJxcStoreUpdateRecord.setDept_id(kjss.getDept_id());
				
				konkaJxcStoreUpdateRecord.setPd_type_id(kjss.getPd_type_id());// 品类
				konkaJxcStoreUpdateRecord.setPd_type_name(kjss.getPd_type_name());
				konkaJxcStoreUpdateRecord.setBrand_id(Constants.KONKA_BRAND_ID);
				konkaJxcStoreUpdateRecord.setBrand_name(Constants.KONKA_BRAND_NAME);
				konkaJxcStoreUpdateRecord.setPd_id(kjss.getPd_id());// 型号
				konkaJxcStoreUpdateRecord.setPd_name(kjss.getPd_name());
				konkaJxcStoreUpdateRecord.setStore_id(kjss.getStore_id());
				konkaJxcStoreUpdateRecord.setStore_name(kjss.getStore_name());
				konkaJxcStoreUpdateRecord.setPrice(kjss.getCur_cost_price());
				konkaJxcStoreUpdateRecord.setPrice_sum(kjss.getCur_cost_price().multiply(new BigDecimal(t.getPy_pk_num().longValue())));
				
				konkaJxcStoreUpdateRecord.setStore_state_id(kjss.getId());
				konkaJxcStoreUpdateRecord.setUpdate_num(t.getPy_pk_num());
				konkaJxcStoreUpdateRecord.setUpdate_type(3);
				konkaJxcStoreUpdateRecordDao.insertEntity(konkaJxcStoreUpdateRecord);
			}
		}
		return this.konkaJxcPcInfoDao.insertEntity(t);
	}

	public KonkaJxcPcInfo getKonkaJxcPcInfo(KonkaJxcPcInfo t) {
		return this.konkaJxcPcInfoDao.selectEntity(t);
	}

	public Long getKonkaJxcPcInfoCount(KonkaJxcPcInfo t) {
		return this.konkaJxcPcInfoDao.selectEntityCount(t);
	}

	public List<KonkaJxcPcInfo> getKonkaJxcPcInfoList(KonkaJxcPcInfo t) {
		return this.konkaJxcPcInfoDao.selectEntityList(t);
	}

	public int modifyKonkaJxcPcInfo(KonkaJxcPcInfo t) {
		return this.konkaJxcPcInfoDao.updateEntity(t);
	}

	public int removeKonkaJxcPcInfo(KonkaJxcPcInfo t) {
		return this.konkaJxcPcInfoDao.deleteEntity(t);
	}

	public List<KonkaJxcPcInfo> getKonkaJxcPcInfoPaginatedList(KonkaJxcPcInfo t) {
		return this.konkaJxcPcInfoDao.selectEntityPaginatedList(t);
	}

}
