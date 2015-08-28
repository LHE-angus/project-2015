package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileReportHistoryDao;
import com.ebiz.mmt.dao.KonkaMobileSailsReturnDao;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaMobileSailsReturn;
import com.ebiz.mmt.service.KonkaMobileSailsReturnService;

@Service
public class KonkaMobileSailsReturnServiceImpl implements
		KonkaMobileSailsReturnService {

	@Resource
	private KonkaMobileSailsReturnDao konkaMobileSailsReturnDao;

	@Resource
	private KonkaMobileReportHistoryDao konkaMobileReportHistoryDao;

	public Long createKonkaMobileSailsReturn(KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.insertEntity(t);
	}

	public KonkaMobileSailsReturn getKonkaMobileSailsReturn(
			KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.selectEntity(t);
	}

	public Long getKonkaMobileSailsReturnCount(KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.selectEntityCount(t);
	}

	public List<KonkaMobileSailsReturn> getKonkaMobileSailsReturnList(
			KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.selectEntityList(t);
	}

	public int modifyKonkaMobileSailsReturn(KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.updateEntity(t);
	}

	public int removeKonkaMobileSailsReturn(KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.deleteEntity(t);
	}

	public List<KonkaMobileSailsReturn> getKonkaMobileSailsReturnPaginatedList(
			KonkaMobileSailsReturn t) {
		return this.konkaMobileSailsReturnDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaMobileSailsReturnForHis(KonkaMobileSailsReturn t) {
		Long result = this.konkaMobileSailsReturnDao.insertEntity(t);

		// 添加历史记录
		KonkaMobileReportHistory konkaMobileReportHistory = new KonkaMobileReportHistory();
		konkaMobileReportHistory.setContent("品类：" + t.getCategory_name()
				+ " 尺寸：" + t.getMeasure_name() +  " 型号：" + t.getModel_name() +" 销量：" + t.getNum() + " 价格："
				+ t.getSingle_price());
		konkaMobileReportHistory.setType_id(2l);
		konkaMobileReportHistory.setReport_man(t.getReport_id());
		konkaMobileReportHistory.setReport_time(new Date());
		konkaMobileReportHistoryDao.insertEntity(konkaMobileReportHistory);

		return result;
	}
}
