package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsDao;
import com.ebiz.mmt.dao.MemberChangeCardInfoDao;
import com.ebiz.mmt.dao.MemberInfoDao;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.MemberChangeCardInfo;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.service.MemberInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-26 11:10:27
 */
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

	@Resource
	private MemberInfoDao memberInfoDao;

	@Resource
	private MemberChangeCardInfoDao memberChangeCardInfoDao;

	@Resource
	private JfScortsDao jfScortsDao;

	public Long createMemberInfo(MemberInfo t) {
		return this.memberInfoDao.insertEntity(t);
	}

	public MemberInfo getMemberInfo(MemberInfo t) {
		return this.memberInfoDao.selectEntity(t);
	}

	public Long getMemberInfoCount(MemberInfo t) {
		return this.memberInfoDao.selectEntityCount(t);
	}

	public List<MemberInfo> getMemberInfoList(MemberInfo t) {
		return this.memberInfoDao.selectEntityList(t);
	}

	public int modifyMemberInfo(MemberInfo t) {
		return this.memberInfoDao.updateEntity(t);
	}

	public int removeMemberInfo(MemberInfo t) {
		return this.memberInfoDao.deleteEntity(t);
	}

	public List<MemberInfo> getMemberInfoPaginatedList(MemberInfo t) {
		return this.memberInfoDao.selectEntityPaginatedList(t);
	}

	public List<MemberInfo> getMemberInfoForTotalScortsPaginatedList(MemberInfo t) {
		return this.memberInfoDao.selectMemberInfoForTotalScortsPaginatedList(t);
	}

	public void createMemberCardChang(String id, String now_user_sn) {
		MemberInfo mb = new MemberInfo();
		mb.setId(Long.valueOf(id));
		mb = this.memberInfoDao.selectEntity(mb);

		MemberChangeCardInfo mci = new MemberChangeCardInfo();
		mci.setNow_user_sn(mb.getUser_sn());

		Long count = this.memberChangeCardInfoDao.selectEntityCount(mci);
		if (count > 0) {
			List<MemberChangeCardInfo> list = this.memberChangeCardInfoDao.selectEntityList(mci);
			for (MemberChangeCardInfo mi : list) {
				mi.setNow_user_sn(now_user_sn);
				this.memberChangeCardInfoDao.updateEntity(mi);
			}

			MemberChangeCardInfo mci1 = new MemberChangeCardInfo();
			if (StringUtils.isNotBlank(now_user_sn)) {
				mci1.setNow_user_sn(now_user_sn);
			}
			mci1.setUser_sn(now_user_sn);
			mci1.setOld_user_sn(mb.getUser_sn());
			this.memberChangeCardInfoDao.insertEntity(mci1);
		} else {
			// 如果是第一次换卡
			MemberChangeCardInfo mci2 = new MemberChangeCardInfo();
			if (StringUtils.isNotBlank(now_user_sn)) {
				mci2.setNow_user_sn(now_user_sn);
			}
			mci2.setUser_sn(now_user_sn);
			mci2.setOld_user_sn(mb.getUser_sn());
			this.memberChangeCardInfoDao.insertEntity(mci2);
		}

		// 停用原会员卡
		MemberInfo mb2 = new MemberInfo();
		mb2.setId(Long.valueOf(id));
		mb2.setUser_state(1);
		this.memberInfoDao.updateEntity(mb2);

		// 新增一张会员卡记录
		MemberInfo mb3 = new MemberInfo();
		// super.copyProperties(mb3, mb);
		mb3.setAddr(mb.getAddr());
		mb3.setBirthday(mb.getBirthday());
		mb3.setEmail(mb.getEmail());
		mb3.setHobby(mb.getHobby());
		mb3.setId_card(mb.getId_card());
		mb3.setIncome(mb.getIncome());
		mb3.setMarriage(mb.getMarriage());
		mb3.setMp(mb.getMp());
		mb3.setP_index(mb.getP_index());
		mb3.setSex(mb.getSex());
		mb3.setStop_date(mb.getStop_date());
		mb3.setStop_reason(mb.getStop_reason());
		mb3.setUser_level(mb.getUser_level());
		mb3.setUser_name(mb.getUser_name());
		mb3.setUser_state(mb.getUser_state());
		mb3.setUser_type(mb.getUser_type());
		mb3.setAdd_date(new Date());
		mb3.setUser_sn(now_user_sn);
		this.memberInfoDao.insertEntity(mb3);

		JfScorts jf = new JfScorts();
		jf.setUser_sn(mb.getUser_sn());
		jf = this.jfScortsDao.selectEntity(jf);

		// 根据原卡号 停用原积分
		JfScorts jfScorts = new JfScorts();
		jfScorts.setId(jf.getId());
		jfScorts.setUser_sn(mb.getUser_sn());
		jfScorts.setIs_del(1);
		this.jfScortsDao.updateEntity(jfScorts);

		// 获取原卡的总积分
		JfScorts jfScorts1 = new JfScorts();
		jfScorts1.setUser_sn(mb.getUser_sn());
		List<JfScorts> list = this.jfScortsDao.selectEntityList(jfScorts1);

		// 新增一个总积分记录
		JfScorts jfScorts2 = new JfScorts();
		jfScorts2.setUser_sn(now_user_sn);
		jfScorts2.setIs_del(0);
		if (null != list && list.size() > 0) {
			jfScorts2.setTotal_scorts(list.get(0).getTotal_scorts());
		}
		this.jfScortsDao.insertEntity(jfScorts2);
	}

}
