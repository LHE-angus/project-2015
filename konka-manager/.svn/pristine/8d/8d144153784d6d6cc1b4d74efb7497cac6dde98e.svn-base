package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaMobileCustVisitDao;
import com.ebiz.mmt.dao.KonkaMobileCustVisitDetailDao;
import com.ebiz.mmt.dao.KonkaMobileCustVisitGpsDao;
import com.ebiz.mmt.dao.KonkaMobileCustVisitTypeDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaMobileCustVisit;
import com.ebiz.mmt.domain.KonkaMobileCustVisitDetail;
import com.ebiz.mmt.domain.KonkaMobileCustVisitGps;
import com.ebiz.mmt.domain.KonkaMobileCustVisitType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaMobileCustVisitService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class KonkaMobileCustVisitServiceImpl implements KonkaMobileCustVisitService {

	@Resource
	private KonkaMobileCustVisitDao konkaMobileCustVisitDao;
	@Resource
	private KonkaMobileCustVisitDetailDao konkaMobileCustVisitDetailDao;
	@Resource
	private AttachmentDao attachmentDao;
	@Resource
	private KonkaMobileCustVisitTypeDao konkaMobileCustVisitTypetDao;

	@Resource
	private KonkaMobileCustVisitGpsDao konkaMobileCustVisitGpsDao;

	public Long createKonkaMobileCustVisit(KonkaMobileCustVisit t) {
		Long visit_id = this.konkaMobileCustVisitDao.insertEntity(t);
		KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = t.getKonkaMobileCustVisitDetail();
		List<Attachment> attachmentList = t.getAttachmentsList();
		List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = t.getKonkaMobileCustVisitTypeList();
		if (null != konkaMobileCustVisitDetail) {// 插入客户数据
			konkaMobileCustVisitDetail.setVisit_id(visit_id);
			this.konkaMobileCustVisitDetailDao.insertEntity(konkaMobileCustVisitDetail);
		}
		if (null != attachmentList && attachmentList.size() > 0) {// 插入附件
			for (Attachment attachment : attachmentList) {
				attachment.setLink_id(visit_id);
				this.attachmentDao.insertEntity(attachment);
			}
		}
		if (null != konkaMobileCustVisitTypeList && konkaMobileCustVisitTypeList.size() > 0) {// 插入附件
			for (KonkaMobileCustVisitType konkaMobileCustVisitTypet : konkaMobileCustVisitTypeList) {
				konkaMobileCustVisitTypet.setVisit_id(visit_id);
				this.konkaMobileCustVisitTypetDao.insertEntity(konkaMobileCustVisitTypet);
			}
		}
		// 客户GPS表插入记录
		KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
		if (null != t.getMap().get("position_x") && !"".equals(t.getMap().get("position_x"))) {
			try {
				gps.setPosition_x(new BigDecimal((String) t.getMap().get("position_x")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != t.getMap().get("position_y") && !"".equals(t.getMap().get("position_y"))) {
			try {
				gps.setPosition_y(new BigDecimal((String) t.getMap().get("position_y")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != t.getMap().get("opr_user_id") && !"".equals(t.getMap().get("opr_user_id")) && GenericValidator.isLong((String) t.getMap().get("opr_user_id")) ) {
			gps.setOpr_user_id(Long.valueOf((String) t.getMap().get("opr_user_id")));
		}
		if (null != t.getMap().get("addr") && !"".equals(t.getMap().get("addr"))) {
			gps.setAddr((String) t.getMap().get("addr"));
		}
		gps.setLink_id(visit_id);
		gps.setLink_tab("KONKA_MOBILE_CUST_VISIT");
		gps.setOpr_date(new Date());
		gps.setPosition_type(0);// 0 百度地图 1 google地图 2 其他地图
		this.konkaMobileCustVisitGpsDao.insertEntity(gps);

		return visit_id;
	}

	public KonkaMobileCustVisit getKonkaMobileCustVisit(KonkaMobileCustVisit t) {
		return this.konkaMobileCustVisitDao.selectEntity(t);
	}

	public Long getKonkaMobileCustVisitCount(KonkaMobileCustVisit t) {
		return this.konkaMobileCustVisitDao.selectEntityCount(t);
	}

	public List<KonkaMobileCustVisit> getKonkaMobileCustVisitList(KonkaMobileCustVisit t) {
		return this.konkaMobileCustVisitDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCustVisit(KonkaMobileCustVisit t) {
		Long visit_id = t.getVisit_id();

		KonkaMobileCustVisitDetail konkaMobileCustVisitDetail = t.getKonkaMobileCustVisitDetail();

		if (null != konkaMobileCustVisitDetail) {// 先删除再插入客户数据
			KonkaMobileCustVisitDetail konkaMobileCustVisitDetail1 = new KonkaMobileCustVisitDetail();
			konkaMobileCustVisitDetail1.setVisit_id(visit_id);
			List<KonkaMobileCustVisitDetail> konkaMobileCustVisitDetaillist = konkaMobileCustVisitDetailDao
			        .selectEntityList(konkaMobileCustVisitDetail1);
			if (konkaMobileCustVisitDetaillist.size() > 0) {
				for (KonkaMobileCustVisitDetail details : konkaMobileCustVisitDetaillist) {
					this.konkaMobileCustVisitDetailDao.deleteEntity(details);
				}
			}
			konkaMobileCustVisitDetail.setVisit_id(visit_id);
			this.konkaMobileCustVisitDetailDao.insertEntity(konkaMobileCustVisitDetail);
		}

		List<Attachment> attachmentList = t.getAttachmentsList();
		if (null != attachmentList && attachmentList.size() > 0) {
			/*
			 * KonkaPeAttachments attachmentdel =new KonkaPeAttachments();
			 * attachmentdel.setLink_id(visit_id);
			 * attachmentdel.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			 * List<KonkaPeAttachments>
			 * attachmentListdel=this.konkaPeAttachmentsDao
			 * .selectEntityList(attachmentdel); if(null!=attachmentListdel &&
			 * attachmentListdel.size()>0){//删除原有附件 for(KonkaPeAttachments
			 * attachment:attachmentListdel){ attachment.setLink_id(visit_id);
			 * this.konkaPeAttachmentsDao.deleteEntity(attachment); } }
			 */
			for (Attachment attachment : attachmentList) {// 插入附件
				attachment.setLink_id(visit_id);
				this.attachmentDao.insertEntity(attachment);
			}
		}
		List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeList = t.getKonkaMobileCustVisitTypeList();
		if (null != konkaMobileCustVisitTypeList && konkaMobileCustVisitTypeList.size() > 0) {
			KonkaMobileCustVisitType konkaMobileCustVisitTypedel = new KonkaMobileCustVisitType();
			konkaMobileCustVisitTypedel.setVisit_id(visit_id);
			List<KonkaMobileCustVisitType> konkaMobileCustVisitTypeListdel = this.konkaMobileCustVisitTypetDao
			        .selectEntityList(konkaMobileCustVisitTypedel);
			if (null != konkaMobileCustVisitTypeListdel && konkaMobileCustVisitTypeListdel.size() > 0) {// 删除类型细类
				for (KonkaMobileCustVisitType konkaMobileCustVisitTypet : konkaMobileCustVisitTypeListdel) {
					konkaMobileCustVisitTypet.setVisit_id(visit_id);
					this.konkaMobileCustVisitTypetDao.deleteEntity(konkaMobileCustVisitTypet);
				}
			}
			for (KonkaMobileCustVisitType konkaMobileCustVisitTypet : konkaMobileCustVisitTypeList) {// 插入类型细类
				konkaMobileCustVisitTypet.setVisit_id(visit_id);
				this.konkaMobileCustVisitTypetDao.insertEntity(konkaMobileCustVisitTypet);
			}
		}
		
		// 客户GPS表插入记录
	        	boolean is_update=false;
				KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
				gps.setLink_id(visit_id);
				gps.setLink_tab("KONKA_MOBILE_CUST_VISIT");
			    List<KonkaMobileCustVisitGps> gpslist=this.konkaMobileCustVisitGpsDao.selectEntityList(gps);
				if(null!=gpslist && gpslist.size() > 0){
					gps=gpslist.get(0);
					is_update=true;
				}
				if (null != t.getMap().get("position_x") && !"".equals(t.getMap().get("position_x"))) {
					try {
						gps.setPosition_x(new BigDecimal((String) t.getMap().get("position_x")));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if (null != t.getMap().get("position_y") && !"".equals(t.getMap().get("position_y"))) {
					try {
						gps.setPosition_y(new BigDecimal((String) t.getMap().get("position_y")));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (null != t.getMap().get("opr_user_id") && !"".equals(t.getMap().get("opr_user_id"))) {
					gps.setOpr_user_id(Long.valueOf((String) t.getMap().get("opr_user_id")));
				}
				if (null != t.getMap().get("addr") && !"".equals(t.getMap().get("addr"))) {
					gps.setAddr((String) t.getMap().get("addr"));
				}
				if(is_update){
					this.konkaMobileCustVisitGpsDao.updateEntity(gps);
				}else{
				gps.setLink_id(visit_id);
				gps.setLink_tab("KONKA_MOBILE_CUST_VISIT");
				gps.setOpr_date(new Date());
				gps.setPosition_type(0);// 0 百度地图 1 google地图 2 其他地图
				this.konkaMobileCustVisitGpsDao.insertEntity(gps);
				}
		return this.konkaMobileCustVisitDao.updateEntity(t);
	}

	public int removeKonkaMobileCustVisit(KonkaMobileCustVisit t) {
		return this.konkaMobileCustVisitDao.deleteEntity(t);
	}

	public List<KonkaMobileCustVisit> getKonkaMobileCustVisitPaginatedList(KonkaMobileCustVisit t) {
		return this.konkaMobileCustVisitDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaMobileCustVisitAndDetailCount(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitAndDetailCount(entity);

	}

	@Override
	public List<KonkaMobileCustVisit> getKonkaMobileCustVisitAndDetailPaginatedList(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitAndDetailPaginatedList(entity);

	}

	@Override
	public Long getKonkaMobileCustVisitLBCount(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitLBCount(entity);
	}

	@Override
	public List<KonkaMobileCustVisit> getKonkaMobileCustVisitPaginatedLBList(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitPaginatedLBList(entity);
	}

	@Override
	public List<KonkaMobileCustVisit> getKonkaMobileCustVisitForCount(KonkaMobileCustVisit entity) {
		
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitForCount(entity);
	}

	@Override
	public List<KonkaMobileCustVisit> getKonkaMobileCustVisitAcountPaginatedList(KonkaMobileCustVisit entity) {
		
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitAcountPaginatedList(entity);
	}

	@Override
	public Long getKonkaMobileCustVisitAcountPaginatedListCount(KonkaMobileCustVisit entity) {
		
		return this.konkaMobileCustVisitDao.selectKonkaMobileCustVisitAcountPaginatedListCount(entity);
	}

	/**
	 * 客户月度计划报表
	 */
	@Override
	public List<KonkaMobileCustVisit> getCustMonthVisitPaginatedList(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectCustMonthVisitPaginatedList(entity);
	}

	@Override
	public Long getCustMonthVisitPaginatedListCount(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectCustMonthVisitPaginatedListCount(entity);
	}

	/**
	 * 门店月度计划报表
	 */
	@Override
	public List<KonkaMobileCustVisit> getShopMonthVisitPaginatedList(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectShopMonthVisitPaginatedList(entity);
	}

	@Override
	public Long getShopMonthVisitPaginatedListCount(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectShopMonthVisitPaginatedListCount(entity);
	}

	/**
	 * 通过当前登陆 用户获取业务员列表
	 */
	@Override
	public List<PeProdUser> getYwyByUserList(KonkaMobileCustVisit entity) {
		return this.konkaMobileCustVisitDao.selectYwyByUserList(entity);
	}

}
