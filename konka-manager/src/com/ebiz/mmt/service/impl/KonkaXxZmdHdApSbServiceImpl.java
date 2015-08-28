package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdHdApSbDao;
import com.ebiz.mmt.dao.KonkaXxZmdSpPlanResDao;
import com.ebiz.mmt.domain.KonkaXxZmdHdApSb;
import com.ebiz.mmt.domain.KonkaXxZmdSpPlanRes;
import com.ebiz.mmt.service.KonkaXxZmdHdApSbService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-19 09:52:53
 */
@Service
public class KonkaXxZmdHdApSbServiceImpl implements KonkaXxZmdHdApSbService {

	@Resource
	private KonkaXxZmdHdApSbDao konkaXxZmdHdApSbDao;

	@Resource
	private KonkaXxZmdSpPlanResDao konkaXxZmdSpPlanResDao;

	@Override
	public Long createKonkaXxZmdHdApSb(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.insertEntity(t);
	}

	@Override
	public KonkaXxZmdHdApSb getKonkaXxZmdHdApSb(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectEntity(t);
	}

	@Override
	public Long getKonkaXxZmdHdApSbCount(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaXxZmdHdApSb> getKonkaXxZmdHdApSbList(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaXxZmdHdApSb(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.updateEntity(t);
	}

	@Override
	public int removeKonkaXxZmdHdApSb(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.deleteEntity(t);
	}

	@Override
	public List<KonkaXxZmdHdApSb> getKonkaXxZmdHdApSbPaginatedList(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-19
	 */
	@Override
	public Long getKonkaXxZmdHdApSbForHdNameCount(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectKonkaXxZmdHdApSbForHdNameCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-19
	 */
	@Override
	public List<KonkaXxZmdHdApSb> getKonkaXxZmdHdApSbForHdNamePaginatedList(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectKonkaXxZmdHdApSbForHdNamePaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-26
	 */
	@Override
	public List<KonkaXxZmdHdApSb> getKonkaXxZmdHdApSbForHdNameList(KonkaXxZmdHdApSb t) {
		return this.konkaXxZmdHdApSbDao.selectKonkaXxZmdHdApSbForHdNameList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-22
	 */
	@Override
	public void createKonkaXxZmdHdApSbForRes(KonkaXxZmdHdApSb t) {
		if (null == t.getSp_hd_id() || "".equals(String.valueOf(t.getSp_hd_id()))) {
			Long id = this.konkaXxZmdHdApSbDao.insertEntity(t);

			// 删除投入资源费用
			KonkaXxZmdSpPlanRes konkaXxZmdSpPlanRes = new KonkaXxZmdSpPlanRes();
			konkaXxZmdSpPlanRes.setSp_hd_id(id);
			konkaXxZmdSpPlanRes.setSb_type(1);
			this.konkaXxZmdSpPlanResDao.deleteEntity(konkaXxZmdSpPlanRes);

			// 插入投入资源费用
			for (KonkaXxZmdSpPlanRes temp : t.getKonkaXxZmdSpPlanResList()) {
				temp.setSp_hd_id(id);
				this.konkaXxZmdSpPlanResDao.insertEntity(temp);
			}
		} else {
			// 删除投入资源费用
			KonkaXxZmdSpPlanRes konkaXxZmdSpPlanRes = new KonkaXxZmdSpPlanRes();
			konkaXxZmdSpPlanRes.setSp_hd_id(t.getSp_hd_id());
			konkaXxZmdSpPlanRes.setSb_type(1);
			this.konkaXxZmdSpPlanResDao.deleteEntity(konkaXxZmdSpPlanRes);

			// 插入投入资源费用
			for (KonkaXxZmdSpPlanRes temp : t.getKonkaXxZmdSpPlanResList()) {
				temp.setSp_hd_id(t.getSp_hd_id());
				this.konkaXxZmdSpPlanResDao.insertEntity(temp);
			}
		}
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-22
	 */
	@Override
	public void modifyKonkaXxZmdHdApSbForRes(KonkaXxZmdHdApSb t) {

		this.konkaXxZmdHdApSbDao.updateEntity(t);
		// 删除投入资源费用
		KonkaXxZmdSpPlanRes konkaXxZmdSpPlanRes = new KonkaXxZmdSpPlanRes();
		konkaXxZmdSpPlanRes.setSp_hd_id(t.getSp_hd_id());
		konkaXxZmdSpPlanRes.setSb_type(1);
		this.konkaXxZmdSpPlanResDao.deleteEntity(konkaXxZmdSpPlanRes);

		// 插入投入资源费用
		for (KonkaXxZmdSpPlanRes temp : t.getKonkaXxZmdSpPlanResList()) {
			temp.setSp_hd_id(t.getSp_hd_id());
			this.konkaXxZmdSpPlanResDao.insertEntity(temp);
		}
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-26
	 */
	@Override
	public void modifyKonkaXxZmdHdZjSbForRes(KonkaXxZmdHdApSb t) {
		this.konkaXxZmdHdApSbDao.updateEntity(t);
		// 删除投入资源费用
		KonkaXxZmdSpPlanRes konkaXxZmdSpPlanRes = new KonkaXxZmdSpPlanRes();
		konkaXxZmdSpPlanRes.setSp_hd_id(t.getSp_hd_id());
		konkaXxZmdSpPlanRes.setSb_type(2);
		this.konkaXxZmdSpPlanResDao.deleteEntity(konkaXxZmdSpPlanRes);

		// 插入投入资源费用
		for (KonkaXxZmdSpPlanRes temp : t.getKonkaXxZmdSpPlanResList()) {
			temp.setSp_hd_id(t.getSp_hd_id());
			this.konkaXxZmdSpPlanResDao.insertEntity(temp);
		}
	}
}
