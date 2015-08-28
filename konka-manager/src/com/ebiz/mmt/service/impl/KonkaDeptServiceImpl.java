package com.ebiz.mmt.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptDao;
import com.ebiz.mmt.dao.MvOrgOfCustomerAllDao;
import com.ebiz.mmt.dao.MvOrgOfCustomerDao;
import com.ebiz.mmt.dao.MvOrgOfCxyAllDao;
import com.ebiz.mmt.dao.MvOrgOfCxyDao;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MvOrgOfCustomer;
import com.ebiz.mmt.domain.MvOrgOfCustomerAll;
import com.ebiz.mmt.domain.MvOrgOfCxy;
import com.ebiz.mmt.domain.MvOrgOfCxyAll;
import com.ebiz.mmt.service.KonkaDeptService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaDeptServiceImpl implements KonkaDeptService {

	@Resource
	private KonkaDeptDao konkaDeptDao;

	@Resource
	private MvOrgOfCxyDao mvOrgOfCxyDao;

	@Resource
	private MvOrgOfCxyAllDao mvOrgOfCxyAllDao;

	@Resource
	private MvOrgOfCustomerDao mvOrgOfCustomerDao;

	@Resource
	private MvOrgOfCustomerAllDao mvOrgOfCustomerAllDao;

	@Override
	public Long createKonkaDept(KonkaDept t) {
		return this.konkaDeptDao.insertEntity(t);
	}
	
	@Override
	public List<KonkaDept> selectDept() {
		return this.konkaDeptDao.selectDept();
	}

	@Override
	public KonkaDept getKonkaDept(KonkaDept t) {
		return this.konkaDeptDao.selectEntity(t);
	}

	@Override
	public Long getKonkaDeptCount(KonkaDept t) {
		return this.konkaDeptDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaDept> getKonkaDeptList(KonkaDept t) {
		return this.konkaDeptDao.selectEntityList(t);
	}

	@Override
	public List<HashMap> getAjaxKonkaDeptList(KonkaDept t) {
		return this.konkaDeptDao.selectAjaxKonkaDeptList(t);
	}

	@Override
	public int modifyKonkaDept(KonkaDept t) {

		this.konkaDeptDao.updateEntity(t);

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sf.format(date);
		String start_time = time + " 08:00:00";
		String end_time = time + " 08:59:59";

		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sf2.format(date);

		Calendar cc = Calendar.getInstance();
		boolean is_time = false;
		try {
			cc.setTime(sf2.parse(now));
			Long now_time = cc.getTime().getTime();
			cc.setTime(sf2.parse(start_time));
			Long s_time = cc.getTime().getTime();
			cc.setTime(sf2.parse(end_time));
			Long e_time = cc.getTime().getTime();
			if (now_time.longValue() > s_time.longValue() && now_time.longValue() < e_time.longValue()) {
				is_time = true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (is_time) {

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(t.getDept_id());
			kd = this.konkaDeptDao.selectEntity(kd);
			MvOrgOfCxy cxy = new MvOrgOfCxy();
			if (kd != null) {
				cxy.setCur_dept_id(kd.getDept_id());
				this.mvOrgOfCxyDao.deleteEntity(cxy);
			}

			cxy.getMap().put("cur_dept_id", t.getDept_id().toString());
			this.mvOrgOfCxyDao.updateMvOrgOfCxyByCxyUserId(cxy);

			MvOrgOfCxyAll call = new MvOrgOfCxyAll();
			if (kd != null) {
				call.setCur_dept_id(kd.getDept_id());
				this.mvOrgOfCxyAllDao.deleteEntity(call);
			}
			call.getMap().put("cur_dept_id", t.getDept_id().toString());
			this.mvOrgOfCxyAllDao.updateMvOrgOfCxyAllByCxyUserId(call);

			MvOrgOfCustomer mc = new MvOrgOfCustomer();
			if (kd != null) {
				mc.setCur_dept_id(kd.getDept_id());
				this.mvOrgOfCustomerDao.deleteEntity(mc);
			}
			mc.getMap().put("cur_dept_id", t.getDept_id());
			this.mvOrgOfCustomerDao.updateMvOrgOfCustomerByUserId(mc);

			MvOrgOfCustomerAll mall = new MvOrgOfCustomerAll();
			if (kd != null) {
				mall.setCur_dept_id(kd.getDept_id());
				this.mvOrgOfCustomerAllDao.deleteEntity(mall);
			}
			mall.getMap().put("cur_dept_id", t.getDept_id());
			this.mvOrgOfCustomerAllDao.updateMvOrgOfCustomerAllByUserId(mall);

		}

		return 0;
	}

	@Override
	public int removeKonkaDept(KonkaDept t) {
		return this.konkaDeptDao.deleteEntity(t);
	}

	@Override
	public List<KonkaDept> getKonkaDeptPaginatedList(KonkaDept t) {
		return this.konkaDeptDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2011-09-01
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListWithTreeNameAndFullName(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptListWithTreeNameAndFullName(t);
	}

	/**
	 * @author Ren,Zhong
	 * @version 2011-09-02
	 */
	@Override
	public Long getKonkaDeptTreeNameByUserForResultCount(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptTreeNameByUserForResultCount(t);
	}


	/**
	 * @author Ren,Zhong
	 * @version 2011-09-02
	 */
	@Override
	public List<KonkaDept> getKonkaDeptTreeNameByUserForResultList(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptTreeNameByUserForResultList(t);
	}

	/**
	 * 子查父
	 */
	@Override
	public List<KonkaDept> getKonkaDeptByDeptId(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptByDeptId(t);
	}

	/**
	 * @author Cheng,Bing
	 * @version 2011-09-29
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListWithTreeNameForProdUser(KonkaDept t) {

		String num = (String) t.getMap().get("num");
		List<KonkaDept> res = null;
		List<KonkaDept> result = new ArrayList<KonkaDept>();
		if (null != num) {
			if ("4".equals(num)) {
				// 当选择处理人时，分公司仅可选择本公司及总部人员
				t.getMap().put("aplication", "true");
			}
			res = this.konkaDeptDao.selectKonkaDeptListWithTreeNameForProdUser(t);

			if ("3".equals(num)) {
				KonkaDept en = new KonkaDept();
				en.setDept_id(0L);
				en.getMap().put("tree_name", "『多媒体事业本部』");
				en.getMap().put("full_name", ">>多媒体事业本部");
				en.getMap().put("add_user_name", "administrator");
				result.add(en);
			} else if ("4".equals(num)) {
				if (!"0".equals(t.getMap().get("dept_id"))) {
					KonkaDept en = new KonkaDept();
					en.setDept_id(0L);
					en.getMap().put("tree_name", "『多媒体事业本部』");
					en.getMap().put("full_name", ">>多媒体事业本部");
					en.getMap().put("add_user_name", "administrator");
					result.add(en);
				}
				result.addAll(res);
			} else {
				result.addAll(res);
			}
		} else {
			result = this.konkaDeptDao.selectKonkaDeptListWithTreeNameForProdUser(t);
		}

		return result;
	}

	/**
	 * @author Dou,QingRen
	 * @version 2012-03-31
	 * @desc 由下往上查询
	 * @return 父节点树状图
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListWithParTreeName(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptListWithParTreeName(t);
	}

	@Override
	public KonkaDept getKonkaDeptSuperiorByDeptId(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptSuperiorByDeptId(t);
	}

	/**
	 * @author pan,gang
	 * @date 2013-8-12
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListForPaifa(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptListForPaifa(t);
	}

	/**
	 * @author Xing,Xiudong
	 * @date 2013-09-11
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListOfUser(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptListOfUser(t);
	}

	/**
	 * @author pan,gang
	 * @date 2013-11-04
	 */
	@Override
	public List<KonkaDept> getKonkaDeptAndJbNameList(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptAndJbNameList(t);
	}

	/**
	 * 父查子
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListForUser(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptListForUser(t);
	}

	@Override
	public List<HashMap> getKonkaDeptTreeNameForProdUser(KonkaDept t) {
		
		return this.konkaDeptDao.selectKonkaDeptTreeNameForProdUser(t);
	}

	/**
	 * 上级部门查下级部门 父查子
	 */
	@Override
	public List<KonkaDept> getKonkaDeptListWithUserDeptId(KonkaDept t) {
		return this.konkaDeptDao.selectKonkaDeptListWithUserDeptId(t);
	}

	@Override
	public KonkaDept getFgsByDeptId(KonkaDept t) {
		
		return null;
	}

}
