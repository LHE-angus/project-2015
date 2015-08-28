package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.BasePdClazzDao;
import com.ebiz.mmt.dao.KonkaMobileCategoryDao;
import com.ebiz.mmt.dao.KonkaMobileFightReportDao;
import com.ebiz.mmt.dao.KonkaMobileReportHistoryDao;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileFightReport;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.service.KonkaMobileFightReportService;

@Service
public class KonkaMobileFightReportServiceImpl implements
		KonkaMobileFightReportService {

	@Resource
	private KonkaMobileFightReportDao konkaMobileFightReportDao;

	@Resource
	private KonkaMobileReportHistoryDao konkaMobileReportHistoryDao;
	@Resource
	private KonkaMobileCategoryDao konkaMobileCategoryDao;

	@Resource
	private BasePdClazzDao basePdClazzDao;

	public Long createKonkaMobileFightReport(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.insertEntity(t);
	}
	
	public Long createKonkaMobileFightReportWithHis(KonkaMobileFightReport t) {
		Long result = this.konkaMobileFightReportDao.insertEntity(t);

		String cls_name = "", size_name = "", brand_name = "";

		// 类别名称
		if (t.getCls_id() != null) {
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setCls_id(t.getCls_id());
			basePdClazz = basePdClazzDao.selectEntity(basePdClazz);
			cls_name = basePdClazz.getCls_name();
		}

		// 尺寸名称
		if (t.getSize_id() != null) {
			// BasePropValItem basePropValItem = new BasePropValItem();
			// basePropValItem.setProp_item_id(t.getSize_id());
			// basePropValItem =
			// basePropValItemDao.selectEntity(basePropValItem);
			// size_name = basePropValItem.getProp_item_name();

			KonkaMobileCategory kmc = new KonkaMobileCategory();
			kmc.setC_index(t.getSize_id());
			size_name = this.konkaMobileCategoryDao.selectEntity(kmc)
					.getC_name();
		}

		KonkaMobileCategory konkaMobileCategory = new KonkaMobileCategory();
		konkaMobileCategory.setC_index(t.getBrand_id());
		brand_name = konkaMobileCategoryDao.selectEntity(konkaMobileCategory)
				.getC_name();

		// 添加历史记录
		KonkaMobileReportHistory konkaMobileReportHistory = new KonkaMobileReportHistory();
		konkaMobileReportHistory.setContent("品牌：" + brand_name + " 品类："
				+ cls_name + " 尺寸：" + size_name + " 型号：" + t.getModel_id()
				+ " 销量：" + t.getNum() + " 价格：" + t.getPrice());
		konkaMobileReportHistory.setType_id(4l);
		konkaMobileReportHistory.setReport_man(t.getReport_man());
		konkaMobileReportHistory.setReport_time(new Date());
		konkaMobileReportHistoryDao.insertEntity(konkaMobileReportHistory);

		return result;
	}

	public KonkaMobileFightReport getKonkaMobileFightReport(
			KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectEntity(t);
	}

	public Long getKonkaMobileFightReportCount(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectEntityCount(t);
	}

	public List<KonkaMobileFightReport> getKonkaMobileFightReportList(
			KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectEntityList(t);
	}

	public int modifyKonkaMobileFightReport(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.updateEntity(t);
	}

	public int removeKonkaMobileFightReport(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.deleteEntity(t);
	}

	public List<KonkaMobileFightReport> getKonkaMobileFightReportPaginatedList(
			KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectEntityPaginatedList(t);
	}

	public List<KonkaMobileFightReport> getKonkaMobileFightReportPaginatedListForQuery(
			KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectKonkaMobileFightReportPaginatedListForQuery(t);
	}
	public List<KonkaMobileFightReport> getKonkaMobileFightReportPaginatedListForQueryNew(
			KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectKonkaMobileFightReportPaginatedListForQueryNew(t);
	}
	
	public List<KonkaMobileFightReport> selectSumNum(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectSumNum(t);
	}
	
	public List<KonkaMobileFightReport> selectSumPrice(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.selectSumPrice(t);
	}
	
	public Long sumKonkaMobileFightReportCount(KonkaMobileFightReport t) {
		return this.konkaMobileFightReportDao.sumKonkaMobileFightReportCount(t);
	}

}
