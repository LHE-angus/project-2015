package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopCreditDao;
import com.ebiz.mmt.domain.KonkaR3ShopCredit;
import com.ebiz.mmt.service.KonkaR3ShopCreditService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-04 15:52:53
 */
@Service
public class KonkaR3ShopCreditServiceImpl implements KonkaR3ShopCreditService {

	@Resource
	private KonkaR3ShopCreditDao konkaR3ShopCreditDao;

	public Long createKonkaR3ShopCredit(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.insertEntity(t);
	}

	public KonkaR3ShopCredit getKonkaR3ShopCredit(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.selectEntity(t);
	}

	public Long getKonkaR3ShopCreditCount(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.selectEntityCount(t);
	}

	public List<KonkaR3ShopCredit> getKonkaR3ShopCreditList(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.selectEntityList(t);
	}

	public int modifyKonkaR3ShopCredit(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.updateEntity(t);
	}

	public int removeKonkaR3ShopCredit(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.deleteEntity(t);
	}

	public List<KonkaR3ShopCredit> getKonkaR3ShopCreditPaginatedList(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-04
	 */
	public void modifyKonkaR3ShopCreditForTb(List<KonkaR3ShopCredit> t) {
		for (KonkaR3ShopCredit temp : t) {
			if (StringUtils.isNotBlank(temp.getKunnr())) {
				KonkaR3ShopCredit konkaR3ShopCredit = new KonkaR3ShopCredit();
				konkaR3ShopCredit.setKunnr(temp.getKunnr());
				Long count = this.konkaR3ShopCreditDao.selectEntityCount(konkaR3ShopCredit);

				if (count == 0) {// 不存在客户
					temp.setLast_update_time(new Date());
					this.konkaR3ShopCreditDao.insertEntity(temp);
				} else {
					temp.setLast_update_time(new Date());
					this.konkaR3ShopCreditDao.updateEntity(temp);
				}
			}
		}
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-30
	 */
	public Long getKonkaR3ShopCreditForRoleDataCount(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.selectKonkaR3ShopCreditForRoleDataCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-30
	 */
	public List<KonkaR3ShopCredit> getKonkaR3ShopCreditForRoleDataPaginatedList(KonkaR3ShopCredit t) {
		return this.konkaR3ShopCreditDao.selectKonkaR3ShopCreditForRoleDataPaginatedList(t);
	}

}
