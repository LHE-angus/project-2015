package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileReportHistoryDao;
import com.ebiz.mmt.dao.KonkaMobileSailDatasDao;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaMobileSailDatas;
import com.ebiz.mmt.service.KonkaMobileSailDatasService;

@Service
public class KonkaMobileSailDatasServiceImpl implements KonkaMobileSailDatasService {

	@Resource
	private KonkaMobileSailDatasDao KonkaMobileSailDatasDao;

	@Resource
	private KonkaMobileReportHistoryDao konkaMobileReportHistoryDao;

	public Long createKonkaMobileSailDatas(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.insertEntity(t);
	}

	public KonkaMobileSailDatas getKonkaMobileSailDatas(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.selectEntity(t);
	}

	public Long getKonkaMobileSailDatasCount(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.selectEntityCount(t);
	}

	public List<KonkaMobileSailDatas> getKonkaMobileSailDatasList(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.selectEntityList(t);
	}

	public int modifyKonkaMobileSailDatas(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.updateEntity(t);
	}

	public int removeKonkaMobileSailDatas(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.deleteEntity(t);
	}

	public List<KonkaMobileSailDatas> getKonkaMobileSailDatasPaginatedList(KonkaMobileSailDatas t) {
		return this.KonkaMobileSailDatasDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaMobileSailDatasForHis(KonkaMobileSailDatas t) {
		Long result = this.KonkaMobileSailDatasDao.insertEntity(t);

		// 添加历史记录
		KonkaMobileReportHistory konkaMobileReportHistory = new KonkaMobileReportHistory();
		konkaMobileReportHistory.setContent("品类：" + t.getCategory_name() + " 尺寸：" + t.getMeasure_name() + " 型号："
		        + t.getModel_name() + " 销量：" + t.getNum() + " 价格：" + t.getSingle_price());
		konkaMobileReportHistory.setType_id(1l);
		konkaMobileReportHistory.setReport_man(t.getReport_id());
		konkaMobileReportHistory.setReport_time(new Date());
		konkaMobileReportHistoryDao.insertEntity(konkaMobileReportHistory);

		return result;
	}

}
