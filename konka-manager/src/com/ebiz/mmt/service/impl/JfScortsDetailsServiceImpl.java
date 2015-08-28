package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfScortsDao;
import com.ebiz.mmt.dao.JfScortsDetailsDao;
import com.ebiz.mmt.dao.JfScortsDetailsHisDao;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.JfScortsDetailsHis;
import com.ebiz.mmt.service.JfScortsDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfScortsDetailsServiceImpl implements JfScortsDetailsService {

	@Resource
	private JfScortsDetailsDao jfScortsDetailsDao;

	@Resource
	private JfScortsDao jfScortsDao;

	@Resource
	private JfScortsDetailsHisDao jfScortsDetailsHisDao;

	public Long createJfScortsDetails(JfScortsDetails t) {
		Long id = this.jfScortsDetailsDao.insertEntity(t);

		// 插入历史表
		JfScortsDetailsHis his = new JfScortsDetailsHis();
		his.setDetails_id(id);
		his.setUser_sn(t.getUser_sn());
		his.setDept_id(t.getDept_id());
		his.setPd_id(t.getPd_id());
		his.setJf_cate(t.getJf_cate());
		his.setScorts(t.getScorts());
		his.setUpdate_result(t.getUpdate_result());
		his.setAdd_user_id(t.getAdd_user_id());
		his.setRemark(t.getRemark());
		his.setStatus(t.getStatus());
		his.setOut_sys_name(t.getOut_sys_name());
		his.setOut_sys_id(t.getOut_sys_id());

		this.jfScortsDetailsHisDao.insertEntity(his);

		return id;
	}

	public Long createJfScortsDetailsAndTotalScores(JfScortsDetails t) {
		Long id = this.jfScortsDetailsDao.insertEntity(t);

		// 修改总积分
		JfScorts jfScorts = new JfScorts();
		jfScorts.setUser_sn(t.getUser_sn());

		List<JfScorts> jfScortsList = this.jfScortsDao.selectEntityList(jfScorts);

		if (null == jfScortsList || jfScortsList.size() == 0) {
			jfScorts.setTotal_scorts(t.getScorts());
			this.jfScortsDao.insertEntity(jfScorts);
		} else {
			jfScorts = jfScortsList.get(0);
			jfScorts.setTotal_scorts(jfScorts.getTotal_scorts().add(t.getScorts()));
			this.jfScortsDao.updateEntity(jfScorts);
		}
		return id;
	}

	public JfScortsDetails getJfScortsDetails(JfScortsDetails t) {
		return this.jfScortsDetailsDao.selectEntity(t);
	}

	public Long getJfScortsDetailsCount(JfScortsDetails t) {
		return this.jfScortsDetailsDao.selectEntityCount(t);
	}

	public List<JfScortsDetails> getJfScortsDetailsList(JfScortsDetails t) {
		return this.jfScortsDetailsDao.selectEntityList(t);
	}

	public int modifyJfScortsDetailsAndTotalScores(JfScortsDetails t) {

		// 修改总积分
		JfScorts jfScorts = new JfScorts();
		jfScorts.setUser_sn(t.getUser_sn());

		List<JfScorts> jfScortsList = this.jfScortsDao.selectEntityList(jfScorts);

		if (null == jfScortsList || jfScortsList.size() == 0) {
			jfScorts.setTotal_scorts(t.getScorts());
			this.jfScortsDao.insertEntity(jfScorts);
		} else {
			jfScorts = jfScortsList.get(0);
			jfScorts.setTotal_scorts(jfScorts.getTotal_scorts().add(t.getScorts()));
			this.jfScortsDao.updateEntity(jfScorts);
		}

		// 插入历史表
		JfScortsDetailsHis his = new JfScortsDetailsHis();
		his.setDetails_id(t.getId());
		his.setUser_sn(t.getUser_sn());
		his.setDept_id(t.getDept_id());
		his.setPd_id(t.getPd_id());
		his.setJf_cate(t.getJf_cate());
		his.setScorts(t.getScorts());
		his.setUpdate_result(t.getUpdate_result());
		his.setAdd_user_id(t.getAdd_user_id());
		his.setRemark(t.getRemark());
		his.setStatus(t.getStatus());
		his.setOut_sys_name(t.getOut_sys_name());
		his.setOut_sys_id(t.getOut_sys_id());

		this.jfScortsDetailsHisDao.insertEntity(his);

		return this.jfScortsDetailsDao.updateEntity(t);
	}

	public int modifyJfScortsDetails(JfScortsDetails t) {
		return this.jfScortsDetailsDao.updateEntity(t);
	}

	public int removeJfScortsDetails(JfScortsDetails t) {
		return this.jfScortsDetailsDao.deleteEntity(t);
	}

	public int removeJfScortsDetailsAndTotalScores(JfScortsDetails t) {
		if (t.getStatus() == 1) {
			// 修改总积分
			JfScorts jfScorts = new JfScorts();
			jfScorts.setUser_sn(t.getUser_sn());

			List<JfScorts> jfScortsList = this.jfScortsDao.selectEntityList(jfScorts);

			if (null == jfScortsList || jfScortsList.size() == 0) {
				jfScorts.setTotal_scorts(new BigDecimal("0").subtract(t.getScorts()));
				this.jfScortsDao.insertEntity(jfScorts);
			} else {
				jfScorts = jfScortsList.get(0);
				jfScorts.setTotal_scorts(jfScorts.getTotal_scorts().subtract(t.getScorts()));
				this.jfScortsDao.updateEntity(jfScorts);
			}
		}
		// 插入历史表
		JfScortsDetailsHis his = new JfScortsDetailsHis();
		his.setDetails_id(-t.getId()); // 负数表示积分明细被删除
		his.setUser_sn(t.getUser_sn());
		his.setDept_id(t.getDept_id());
		his.setPd_id(t.getPd_id());
		his.setJf_cate(t.getJf_cate());
		his.setScorts(t.getScorts());
		his.setUpdate_result(t.getUpdate_result());
		his.setAdd_user_id(t.getAdd_user_id());
		his.setRemark(t.getRemark());
		his.setStatus(t.getStatus());
		his.setOut_sys_name(t.getOut_sys_name());
		his.setOut_sys_id(t.getOut_sys_id());

		this.jfScortsDetailsHisDao.insertEntity(his);

		return this.jfScortsDetailsDao.deleteEntity(t);
	}

	public List<JfScortsDetails> getJfScortsDetailsPaginatedList(JfScortsDetails t) {
		return this.jfScortsDetailsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-26
	 */
	public List<JfScortsDetails> getJfScortsDetailsAndDeptNameList(JfScortsDetails t) {
		return this.jfScortsDetailsDao.selectJfScortsDetailsAndDeptNameList(t);
	}

	public List<JfScortsDetails> getJfScortsDetailsAndDeptNameForMemberCardList(JfScortsDetails t) {
		return this.jfScortsDetailsDao.selectJfScortsDetailsAndDeptNameForMemberCardList(t);
	}

}
