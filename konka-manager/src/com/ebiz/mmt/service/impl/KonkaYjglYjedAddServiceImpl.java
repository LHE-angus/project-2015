package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglYjedAddDao;
import com.ebiz.mmt.dao.KonkaYjglYjedDao;
import com.ebiz.mmt.domain.KonkaYjglYjed;
import com.ebiz.mmt.domain.KonkaYjglYjedAdd;
import com.ebiz.mmt.service.KonkaYjglYjedAddService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglYjedAddServiceImpl implements KonkaYjglYjedAddService {

	@Resource
	private KonkaYjglYjedAddDao konkaYjglYjedAddDao;

	@Resource
	private KonkaYjglYjedDao konkaYjglYjedDao;

	public Long createKonkaYjglYjedAdd(KonkaYjglYjedAdd t) {

		KonkaYjglYjed ky = new KonkaYjglYjed();
		ky.setDept_id(t.getDept_id());
		ky.setYjed_year(t.getYjed_year());
		ky = this.konkaYjglYjedDao.selectEntity(ky);

		if (ky == null) {
			KonkaYjglYjed ky_new = new KonkaYjglYjed();
			ky_new.setDept_id(t.getDept_id());
			ky_new.setIs_del(0);
			ky_new.setRemark(t.getRemark());
			ky_new.setUpdate_time(new Date());
			ky_new.setUpdate_user_id(t.getAdd_user_id());

			int a = t.getYjed_type().intValue();
			switch (a) {
			case 0:
				ky_new.setYjed_jz(t.getYjed_num());
				ky_new.setYjed_ls(new BigDecimal("0.00"));
				ky_new.setYjed_zx(new BigDecimal("0.00"));
				break;
			case 1:
				ky_new.setYjed_ls(t.getYjed_num());
				ky_new.setYjed_zx(new BigDecimal("0.00"));
				ky_new.setYjed_jz(new BigDecimal("0.00"));
				break;
			case 2:
				ky_new.setYjed_zx(t.getYjed_num());
				ky_new.setYjed_ls(new BigDecimal("0.00"));
				ky_new.setYjed_jz(new BigDecimal("0.00"));
				break;
			default:
				break;
			}

			ky_new.setYjed_used(new BigDecimal("0.00"));// 已用额度，从r3系统查看
			ky_new.setYjed_total(ky_new.getYjed_jz().add(ky_new.getYjed_ls()).add(ky_new.getYjed_zx()));
			ky_new.setYjed_not_use(ky_new.getYjed_total().subtract(ky_new.getYjed_used()));
			ky_new.setYjed_year(t.getYjed_year());
			this.konkaYjglYjedDao.insertEntity(ky_new);

		} else {
			int a = t.getYjed_type().intValue();
			switch (a) {
			case 0:
				ky.setYjed_jz(ky.getYjed_jz().add(t.getYjed_num()));
				break;
			case 1:
				ky.setYjed_ls(ky.getYjed_ls().add(t.getYjed_num()));
				break;
			case 2:
				ky.setYjed_zx(ky.getYjed_zx().add(t.getYjed_num()));
				break;
			default:
				break;
			}
			ky.setRemark(t.getRemark());
			ky.setUpdate_time(new Date());
			ky.setUpdate_user_id(t.getAdd_user_id());
			ky.setYjed_total(ky.getYjed_jz().add(ky.getYjed_ls()).add(ky.getYjed_zx()));
			ky.setYjed_not_use(ky.getYjed_total().subtract(ky.getYjed_used()));
			this.konkaYjglYjedDao.updateEntity(ky);

		}

		return this.konkaYjglYjedAddDao.insertEntity(t);
	}

	public KonkaYjglYjedAdd getKonkaYjglYjedAdd(KonkaYjglYjedAdd t) {
		return this.konkaYjglYjedAddDao.selectEntity(t);
	}

	public Long getKonkaYjglYjedAddCount(KonkaYjglYjedAdd t) {
		return this.konkaYjglYjedAddDao.selectEntityCount(t);
	}

	public List<KonkaYjglYjedAdd> getKonkaYjglYjedAddList(KonkaYjglYjedAdd t) {
		return this.konkaYjglYjedAddDao.selectEntityList(t);
	}

	public int modifyKonkaYjglYjedAdd(KonkaYjglYjedAdd t) {
		return this.konkaYjglYjedAddDao.updateEntity(t);
	}

	public int removeKonkaYjglYjedAdd(KonkaYjglYjedAdd t) {
		return this.konkaYjglYjedAddDao.deleteEntity(t);
	}

	public List<KonkaYjglYjedAdd> getKonkaYjglYjedAddPaginatedList(KonkaYjglYjedAdd t) {
		return this.konkaYjglYjedAddDao.selectEntityPaginatedList(t);
	}

}
