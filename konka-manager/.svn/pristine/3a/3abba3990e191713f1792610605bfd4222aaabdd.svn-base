package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardApplyDao;
import com.ebiz.mmt.dao.EcBaseCardNoDao;
import com.ebiz.mmt.dao.EcCustDao;
import com.ebiz.mmt.dao.EcCustRelUserDao;
import com.ebiz.mmt.dao.EcUserDao;
import com.ebiz.mmt.domain.EcBaseCardApply;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.domain.EcCustRelUser;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.service.EcBaseCardApplyService;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-31 11:30:18
 */
@Service
public class EcBaseCardApplyServiceImpl implements EcBaseCardApplyService {

	@Resource
	private EcBaseCardApplyDao ecBaseCardApplyDao;

	@Resource
	private EcBaseCardNoDao ecBaseCardNoDao;

	@Resource
	private EcUserDao ecUserDao;

	@Resource
	private EcCustDao ecCustDao;

	@Resource
	private EcCustRelUserDao ecCustRelUserDao;

	public Long createEcBaseCardApply(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.insertEntity(t);
	}

	public EcBaseCardApply getEcBaseCardApply(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.selectEntity(t);
	}

	public Long getEcBaseCardApplyCount(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.selectEntityCount(t);
	}

	public List<EcBaseCardApply> getEcBaseCardApplyList(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.selectEntityList(t);
	}

	public int modifyEcBaseCardApply(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.updateEntity(t);
	}

	public int removeEcBaseCardApply(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.deleteEntity(t);
	}

	public List<EcBaseCardApply> getEcBaseCardApplyPaginatedList(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.selectEntityPaginatedList(t);
	}

	public int modifyEcBaseCardApply(EcBaseCardApply t, List<String> cardNoList) {
		int id = this.ecBaseCardApplyDao.updateEntity(t);
		DESPlus des;
		try {
			des = new DESPlus();
			EcUser ec1 = new EcUser();
			ec1.setUser_name(t.getApply_user_name());
			List<EcUser> ecList = this.ecUserDao.selectEntityList(ec1);
			if (null != ecList && ecList.size() > 0) {
				ec1 = ecList.get(0);
			}
			if (null != cardNoList && cardNoList.size() > 0) {
				for (String cardno : cardNoList) {
					EcBaseCardNo en = new EcBaseCardNo();
					en.setCard_no(cardno);
					en.setCard_level(802L);
					en.setCard_sender(ec1.getCard_no());
					this.ecBaseCardNoDao.insertEcBaseCardNo(en);

					EcUser ec = new EcUser();
					ec.setUser_type(1);
					ec.setCard_no(cardno);
					ec.setPass_word(des.encrypt("1"));
					ec.setPay_pwd(null);
					ec.setAdd_date(new Date());
					ec.setIs_chapter(0);
					ec.setIs_del(0);
					ec.setPosition_id(0L);
					ec.setLogin_count(0L);
					ec.setLast_login_time(new Date());
					ec.setOrder_value(0);
					ec.setIs_xx_user(0);
					ec.setUser_name(cardno);
					ec.setIs_act(1);
					ec.setDept_id(Long.valueOf(t.getApply_dept()));
					ec.setIs_epp_fgs(1);
					this.ecUserDao.insertEcUser(ec);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public int modifyEcBaseCardApply(EcBaseCardApply t, List<String> usernameList, List<String> custcodeList,
			List<String> custnameList) {
		int id = this.ecBaseCardApplyDao.updateEntity(t);
		DESPlus des;
		try {
			des = new DESPlus();
			EcUser ec1 = new EcUser();
			ec1.setUser_name(t.getApply_user_name());
			List<EcUser> ecList = this.ecUserDao.selectEntityList(ec1);
			if (null != ecList && ecList.size() > 0) {
				ec1 = ecList.get(0);
			}
			Long user_id = (Long) t.getMap().get("user_id");
			if (null != usernameList && usernameList.size() > 0) {
				for (int i = 0; i < usernameList.size(); i++) {

					// EcCust ecCust = new EcCust();
					// ecCust.setAdd_date(new Date());
					// ecCust.setAdd_user_id(user_id);
					// ecCust.setCust_code(custcodeList.get(i));
					// ecCust.setCust_name(custnameList.get(i));
					// ecCust.setDel_mark(0);
					// ecCust.setCust_type(1);// 0--r3客户 1--虚拟客户
					// ecCust.setGroup_id(Long.valueOf(t.getApply_dept()));
					// Long cust_id = this.ecCustDao.insertEntity(ecCust);

					Long cust_id = ec1.getCust_id();

					EcUser ec = new EcUser();
					ec.setUser_type(2);
					ec.setPass_word(des.encrypt("1"));
					ec.setPay_pwd(null);
					ec.setAdd_date(new Date());
					ec.setIs_chapter(0);
					ec.setIs_del(0);
					ec.setPosition_id(0L);
					ec.setPlat_sys(1);
					ec.setIs_allowed(1);
					ec.setAdd_user_id(user_id);
					ec.setLink_user_name(t.getApply_user_name());
					ec.setLogin_count(0L);
					ec.setLast_login_time(new Date());
					ec.setOrder_value(0);
					ec.setIs_xx_user(0);
					ec.setUser_name(usernameList.get(i));
					ec.setIs_act(1);
					ec.setDept_id(Long.valueOf(t.getApply_dept()));
					ec.setIs_epp_fgs(0);
					ec.setCust_id(cust_id);

					String user_no = "";
					Long no = this.ecUserDao.selectEcUserNo(ec);
					user_no = no.toString();
					ec.setUser_no(user_no);
					ec.setCard_no(user_no);

					Long ecuser_id = this.ecUserDao.insertEntity(ec);

					EcCustRelUser eu = new EcCustRelUser();
					eu.setCust_id(cust_id);
					eu.setUser_id(ecuser_id);
					this.ecCustRelUserDao.insertEntity(eu);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;

	}

	public Long getApplyCardNoCount(EcBaseCardApply t) {
		return this.ecBaseCardApplyDao.selectApplyCardNoCount(t);
	}

}
