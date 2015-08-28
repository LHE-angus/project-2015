package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserScoreRecDao;
import com.ebiz.mmt.dao.PshowOrdeDetailsDao;
import com.ebiz.mmt.dao.PshowOrderDao;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.service.PshowOrdeDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:06:13
 */
@Service
public class PshowOrdeDetailsServiceImpl implements PshowOrdeDetailsService {

	@Resource
	private PshowOrdeDetailsDao pshowOrdeDetailsDao;
	@Resource
	private EcUserScoreRecDao ecUserScoreRecSDao;
	@Resource
	private PshowOrderDao pshowOrderDao;

	public Long createPshowOrdeDetails(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.insertEntity(t);
	}

	public PshowOrdeDetails getPshowOrdeDetails(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectEntity(t);
	}

	public Long getPshowOrdeDetailsCount(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectEntityCount(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectEntityList(t);
	}

	public int modifyPshowOrdeDetails(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.updateEntity(t);
	}

	public int removePshowOrdeDetails(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.deleteEntity(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsPaginatedList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectEntityPaginatedList(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeForPDSNDetailsList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeForPDSNDetailsList(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsForRebatesPaginatedList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsForRebatesPaginatedList(t);
	}

	public Long getPshowOrdeDetailsForRebatesCount(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsForRebatesCount(t);
	}

	public String getPshowOrdeDetailsSumRebates(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsSumRebates(t);
	}

	public Long getPshowOrdeDetailsForFgsCount(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsForFgsCount(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsForFgsPaginatedList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsForFgsPaginatedList(t);
	}

	public int modifyPshowOrdeDetailsRebates(Long id, String type, EcUser ecUser) {
		int i = 0;
		PshowOrdeDetails entity = new PshowOrdeDetails();
		entity.setBill_item_id(new Long(id));
		entity = this.pshowOrdeDetailsDao.selectEntity(entity);
		PshowOrder p = new PshowOrder();
		p.setId(entity.getOrder_id());
		p = this.pshowOrderDao.selectEntity(p);

		if (entity.getRebates_status().intValue() == 0 && p.getOrder_user_id().intValue() == ecUser.getId().intValue()) {
			if ("2".equals(type)) {
				PshowOrdeDetails t = new PshowOrdeDetails();
				t.setBill_item_id(new Long(id));
				t.setRebates_date(new Date());
				t.setRebates_status(new Integer(2));
				i = this.pshowOrdeDetailsDao.updateEntity(t);
			} else if ("3".equals(type)) {
				PshowOrdeDetails t = new PshowOrdeDetails();
				t.setBill_item_id(new Long(id));
				t.setRebates_date(new Date());
				t.setRebates_status(new Integer(3));
				i = this.pshowOrdeDetailsDao.updateEntity(t);
				EcUserScoreRec ecUserScoreRec = new EcUserScoreRec();
				ecUserScoreRec.setOpr_date(new Date());
				ecUserScoreRec.setUser_id(ecUser.getId());
				ecUserScoreRec.setOpr_type(new Integer(0));
				ecUserScoreRec.setOpr_id(0L);
				ecUserScoreRec.setScore((entity.getRebates() == null ? new BigDecimal("0") : entity.getRebates())
				        .multiply(new BigDecimal("100")).intValue());
				ecUserScoreRec.setNote("佣金返利兑换积分");
				String all_score = ecUserScoreRecSDao.selectEcUserScoreRecForUserTotalScore(ecUser.getId());
				if (all_score != null) {
					ecUserScoreRec.setAll_score(ecUserScoreRec.getScore().intValue()
					        + new Integer(all_score).intValue());
				} else {
					ecUserScoreRec.setAll_score(ecUserScoreRec.getScore());
				}
				ecUserScoreRecSDao.insertEntity(ecUserScoreRec);
			}
		}

		return i;
	}

	public Long getPshowOrdeDetailsNumByGoodsIdCount(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsNumByGoodsIdCount(t);
	}

	@Override
	public List<PshowOrdeDetails> getPshowOrdeDetailsFromUser(long id) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsFromUser(id);
	}

	@Override
	public List<PshowOrdeDetails> getPshowOrdeDetailsStatusFromUser(long id) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsStatusFromUser(id);
	}

	@Override
	public List<PshowOrdeDetails> getPshowOrdeDetailsForPayList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsForPayList(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsAndDaySellForTjList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsAndDaySellForTjList(t);
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsByProdTypeList(PshowOrdeDetails t) {
		return this.pshowOrdeDetailsDao.selectPshowOrdeDetailsByProdTypeList(t);
	}

}
