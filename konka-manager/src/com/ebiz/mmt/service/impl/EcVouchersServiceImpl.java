package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserScoreRecDao;
import com.ebiz.mmt.dao.EcVouchersDao;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.service.EcVouchersService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-25 15:36:42
 */
@Service
public class EcVouchersServiceImpl implements EcVouchersService {

	@Resource
	private EcVouchersDao ecVouchersDao;

	@Resource
	private EcUserScoreRecDao ecUserScoreRecDao;

	public Long createEcVouchers(EcVouchers t) {
		return this.ecVouchersDao.insertEntity(t);
	}

	public EcVouchers getEcVouchers(EcVouchers t) {
		return this.ecVouchersDao.selectEntity(t);
	}

	public Long getEcVouchersCount(EcVouchers t) {
		return this.ecVouchersDao.selectEntityCount(t);
	}

	public List<EcVouchers> getEcVouchersList(EcVouchers t) {
		return this.ecVouchersDao.selectEntityList(t);
	}

	public int modifyEcVouchers(EcVouchers t) {
		return this.ecVouchersDao.updateEntity(t);
	}

	public int removeEcVouchers(EcVouchers t) {
		return this.ecVouchersDao.deleteEntity(t);
	}

	public List<EcVouchers> getEcVouchersPaginatedList(EcVouchers t) {
		return this.ecVouchersDao.selectEntityPaginatedList(t);
	}

	public String createEcVouchersAndGift(EcVouchers t, EcUser ecUser) {
		String ss = "";
		String totalScore = this.ecUserScoreRecDao.selectEcUserScoreRecForUserTotalScore(ecUser.getId());
		EcUserScoreRec ec = new EcUserScoreRec();
		if ((Integer.valueOf(totalScore) - t.getPrice().intValue() * 100) >= 0) {
			this.ecVouchersDao.insertEntity(t);
			ec.setAll_score(Integer.valueOf(totalScore) - t.getPrice().intValue() * 100);
			ec.setNote(t.getMemo());
			ec.setOpr_date(new Date());
			ec.setUser_id(ecUser.getId());
			ec.setOpr_id(0L);
			ec.setOpr_type(1);
			ec.setScore(t.getPrice().intValue() * 100);
			ec.setNote("积分兑换购物券");
			this.ecUserScoreRecDao.insertEntity(ec);
			ss = "兑换成功！";
		} else {
			ss = "对不起！您的积分不够！";
		}
		return ss;
	}

	public String createEcVouchersAndGift2(EcVouchers t, EcUser ecUser) {
		String ss = "";
		String totalScore = this.ecUserScoreRecDao.selectEcUserScoreRecForUserTotalScore(ecUser.getId());
		EcUserScoreRec ec = new EcUserScoreRec();
		if ((Integer.valueOf(totalScore) - t.getPrice().intValue() * 100) >= 0) {
			this.ecVouchersDao.insertEntity(t);
			ec.setAll_score(Integer.valueOf(totalScore) - t.getPrice().intValue() * 100);
			ec.setNote(t.getMemo());
			ec.setOpr_date(new Date());
			ec.setUser_id(ecUser.getId());
			ec.setOpr_id(0L);
			ec.setOpr_type(1);
			ec.setScore(t.getPrice().intValue() * 100);
			ec.setNote("手动输入积分兑换");
			this.ecUserScoreRecDao.insertEntity(ec);
			ss = "兑换成功！";
		} else {
			ss = "对不起！您的积分不够！";
		}
		return ss;
	}

	public int modifyEcVouchersByOrderId(EcVouchers t) {
		return this.ecVouchersDao.modifyEcVouchersByOrderId(t);
	}

	@Override
	public Long createBatch(EcVouchers t) {
		return this.ecVouchersDao.insertBatch(t);
	}

}
