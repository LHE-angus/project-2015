package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PePdSellareaDao;
import com.ebiz.mmt.domain.PePdSellarea;
import com.ebiz.mmt.service.PePdSellareaService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-22 14:44:58
 */
@Service
public class PePdSellareaServiceImpl implements PePdSellareaService {

	@Resource
	private PePdSellareaDao pePdSellareaDao;

	public Long createPePdSellarea(PePdSellarea t) {
		return this.pePdSellareaDao.insertEntity(t);
	}

	public PePdSellarea getPePdSellarea(PePdSellarea t) {
		return this.pePdSellareaDao.selectEntity(t);
	}

	public Long getPePdSellareaCount(PePdSellarea t) {
		return this.pePdSellareaDao.selectEntityCount(t);
	}

	public List<PePdSellarea> getPePdSellareaList(PePdSellarea t) {
		return this.pePdSellareaDao.selectEntityList(t);
	}

	public int modifyPePdSellarea(PePdSellarea t) {
		return this.pePdSellareaDao.updateEntity(t);
	}

	public int removePePdSellarea(PePdSellarea t) {
		return this.pePdSellareaDao.deleteEntity(t);
	}

	public List<PePdSellarea> getPePdSellareaPaginatedList(PePdSellarea t) {
		return this.pePdSellareaDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-21
	 */
	public void createPePdSellareaList(PePdSellarea t, String[] p_index_list) {
		this.pePdSellareaDao.deleteEntity(t);

		for (int i = 0; i < p_index_list.length; i++) {
			PePdSellarea pePdSellarea = new PePdSellarea();
			pePdSellarea.setPe_pd_id(t.getPe_pd_id());
			pePdSellarea.setP_index(Long.valueOf(p_index_list[i]));

			this.pePdSellareaDao.insertEntity(pePdSellarea);
		}

	}
}
