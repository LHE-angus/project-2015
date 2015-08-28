package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaMobileReportHistoryDao;
import com.ebiz.mmt.dao.KonkaMobileSailDataBillDao;
import com.ebiz.mmt.dao.KonkaMobileSailDataDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaMobileSailDataBill;
import com.ebiz.mmt.domain.KonkaStoreTaskFinishReport;
import com.ebiz.mmt.service.KonkaMobileSailDataService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

@Service
public class KonkaMobileSailDataServiceImpl implements KonkaMobileSailDataService {

	@Resource
	private KonkaMobileSailDataDao konkaMobileSailDataDao;

	@Resource
	private KonkaMobileReportHistoryDao konkaMobileReportHistoryDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	KonkaMobileSailDataBillDao konkaMobileSailDataBillDao;

	@Override
	public Long createKonkaMobileSailData(KonkaMobileSailData t) {
		Long id = this.konkaMobileSailDataDao.insertEntity(t);
		return id;
	}

	@Override
	public Long createKonkaMobileSailData(KonkaMobileSailData t, Long lid) {
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
				a.setLink_id(lid);
				this.attachmentDao.insertEntity(a);
			}
		}
		return 0L;
	}

	@Override
	public KonkaMobileSailData getKonkaMobileSailData(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectEntity(t);
	}

	@Override
	public Long getKonkaMobileSailDataCount(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectEntityCount(t);
	}

	@Override
	public Long getKonkaMobileSailDataCountForFourWeek(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataCountForFourWeek(t);
	}

	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaMobileSailData(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.updateEntity(t);
	}

	@Override
	public int removeKonkaMobileSailData(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.deleteEntity(t);
	}

	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataPaginatedList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long createKonkaMobileSailDataForHis(KonkaMobileSailData t) {
		Long result = this.konkaMobileSailDataDao.insertEntity(t);

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

	@Override
	public Long createKonkaMobileSailDataList(List<KonkaMobileSailData> t) {
		Long counts = 0L;

		if (null != t && t.size() > 0) {
			for (KonkaMobileSailData cur : t) {
				Long id = this.konkaMobileSailDataDao.insertEntity(cur);
				if (cur.getAttachmentList() != null && cur.getAttachmentList().size() > 0) {
					for (Attachment a : cur.getAttachmentList()) {
						a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
						a.setLink_id(id);
						this.attachmentDao.insertEntity(a);
					}
				}
			}
			counts = new Long(t.size());
		}

		return counts;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataInR3InfoPaginatedList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataInR3InfoPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-09-14
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataForFgsTopCount(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataForFgsTopCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	@Override
	public Long getKonkaMobileSailDataToJsonCount(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataToJsonCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-20
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataToJsonPaginatedList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataToJsonPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	@Override
	public Long getKonkaMobileSailDataToJson3Count(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataToJson3Count(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-21
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataToJson3PaginatedList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataToJson3PaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-08
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataToExlList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataToExlList(t);
	}

	/**
	 * @author Pan,Gang
	 * @version 2013-12-08
	 * @desc 手机零售上报上传图片保存
	 */
	@Override
	public Long createKonkaMobileSailDataForAttachment(KonkaMobileSailData t, String link_id, String link_tab) {

		// 附件insert
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				if (StringUtils.isNotBlank(link_id) && GenericValidator.isLong(link_id)) {
					a.setLink_id(Long.valueOf(link_id));
					if (StringUtils.isNotBlank(link_tab)) {
						a.setLink_tab(link_tab);
					}
					this.attachmentDao.insertEntity(a);
				}
			}
		}
		return 0L;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-06-17
	 * @desc 获取销售总数量和总金额
	 */
	@Override
	public KonkaMobileSailData getKonkaMobileSailDataForTotal(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataForTotal(t);
	}

	@Override
	public Long getStarOfBanCount(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectStarOfBanCount(t);
	}

	@Override
	public List<HashMap> getStarOfBanList(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectStarOfBanList(t);
	}

	@Override
	public HashMap getAllMoneyAndNum(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectAllMoneyAndNum(t);
	}

	//
	@Override
	public List<KonkaStoreTaskFinishReport> getKonkaStoreTaskFinishReport(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectAllStoreTaskFinish(t);
	}

	//
	@Override
	public Long getKonkaStoreTaskFinishReportForCount(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectAllStoreTaskFinish4count(t);
	}

	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataForExportList(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataForExportList(t);
	}

	@Override
	public Long createKonkaMobileSailDataAndBill(KonkaMobileSailData t, Long lid) {
		if (t.getKonkaMobileSailDataBillList() != null && t.getKonkaMobileSailDataBillList().size() > 0) {
			for (KonkaMobileSailDataBill konkaMobileSailDataBill : t.getKonkaMobileSailDataBillList()) {
				if (null != konkaMobileSailDataBill.getBill_id()) {
					if (null != konkaMobileSailDataBill.getAttachment()) {
						Attachment del = new Attachment();
						// del.setLink_tab("KONKA_MOBILE_SAIL_DATA");
						del.setId(konkaMobileSailDataBill.getAdjunct_link_id());
						this.attachmentDao.deleteEntity(del);
						Attachment a = konkaMobileSailDataBill.getAttachment();
						a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
						a.setLink_id(lid);
						Long adjunct_link_id = this.attachmentDao.insertEntity(a);
						konkaMobileSailDataBill.setAdjunct_link_id(adjunct_link_id);
					}
					this.konkaMobileSailDataBillDao.updateEntity(konkaMobileSailDataBill);
				} else {
					if (null != konkaMobileSailDataBill.getAttachment()) {
						Attachment a = konkaMobileSailDataBill.getAttachment();
						a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
						a.setLink_id(lid);
						Long adjunct_link_id = this.attachmentDao.insertEntity(a);
						konkaMobileSailDataBill.setAdjunct_link_id(adjunct_link_id);
						this.konkaMobileSailDataBillDao.insertEntity(konkaMobileSailDataBill);
					}
				}
			}
		}
		return 0L;

	}

	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataAndBillPaginatedList(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataAndBillPaginatedList(t);
	}

	@Override
	public List<KonkaMobileSailData> getkonkaMobileSailDataAndBillForSwitchList(KonkaMobileSailData entity) {
		
		return this.konkaMobileSailDataDao.selectkonkaMobileSailDataAndBillForSwitchList(entity);
	}

	@Override
	public void getkonkaMobileSailDataAndBillForSwitchSave(KonkaMobileSailData entity) {
		
		this.konkaMobileSailDataDao.selectkonkaMobileSailDataAndBillForSwitchSave(entity);
	}

	@Override
	public Long getkonkaMobileSailDataAndBillForSalaryCount(KonkaMobileSailData entity) {
		
		return this.konkaMobileSailDataDao.selectkonkaMobileSailDataAndBillForSalaryCount(entity);
	}

	@Override
	public List<HashMap> getkonkaMobileSailDataAndBillForSalaryList(KonkaMobileSailData entity) {
		
		return this.konkaMobileSailDataDao.selectkonkaMobileSailDataAndBillForSalaryList(entity);
	}

	@Override
	public Long getKonkaMobileSailDataAndBillCount(KonkaMobileSailData entity) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataAndBillCount(entity);
	}

	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataBillFileList(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataBillFileList(t);
	}

	/**
	 * 统计所有数据的总商品数量和总价格
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataNumAndMoneyCount(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataNumAndMoneyCount(t);
	}

	@Override
	public List<HashMap<String, Object>> getKonkaMobileSailDataListByR3JobId(KonkaMobileSailData entity) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataListByR3JobId(entity);
	}

	@Override
	public List<HashMap<String, Object>> getKonkaMobileSailDataListByFgsCode(HashMap<String, Object> map) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataListByFgsCode(map);
	}

	@Override
	public List<HashMap<String, Object>> getKonkaMobileSailDataForTerminalMap(KonkaMobileSailData entity) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataForTerminalMap(entity);
	}

	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataForTop10List(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataForTop10List(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebiz.mmt.service.KonkaMobileSailDataService#
	 * getKonkaMobileSailDataForSaleNumList
	 * (com.ebiz.mmt.domain.KonkaMobileSailData)
	 */
	@Override
	public List<KonkaMobileSailData> getKonkaMobileSailDataForSaleNumList(KonkaMobileSailData t) {
		
		return this.konkaMobileSailDataDao.selectKonkaMobileSailDataForSaleNumList(t);
	}

	@Override
	public List<HashMap> getCxModelList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectCxModelList(t);
	}

	@Override
	public List<HashMap> getPMForDeptList(KonkaMobileSailData t) {
		return this.konkaMobileSailDataDao.selectPMForDeptList(t);
	}

}
