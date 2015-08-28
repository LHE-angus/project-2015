package com.ebiz.mmt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcLuckyMainDao;
import com.ebiz.mmt.dao.EcLuckyTimeDao;
import com.ebiz.mmt.domain.EcLuckyMain;
import com.ebiz.mmt.domain.EcLuckyTime;
import com.ebiz.mmt.service.EcLuckyMainService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
@Service
public class EcLuckyMainServiceImpl implements EcLuckyMainService {

	@Resource
	private EcLuckyMainDao ecLuckyMainDao;

	@Resource
	private EcLuckyTimeDao eLuckyTimeDao;

	public Long createEcLuckyMain(EcLuckyMain t) {
		return this.ecLuckyMainDao.insertEntity(t);
	}

	public EcLuckyMain getEcLuckyMain(EcLuckyMain t) {
		return this.ecLuckyMainDao.selectEntity(t);
	}

	public Long getEcLuckyMainCount(EcLuckyMain t) {
		return this.ecLuckyMainDao.selectEntityCount(t);
	}

	public List<EcLuckyMain> getEcLuckyMainList(EcLuckyMain t) {
		return this.ecLuckyMainDao.selectEntityList(t);
	}

	public int modifyEcLuckyMain(EcLuckyMain t) {
		return this.ecLuckyMainDao.updateEntity(t);
	}

	public int removeEcLuckyMain(EcLuckyMain t) {
		return this.ecLuckyMainDao.deleteEntity(t);
	}

	public List<EcLuckyMain> getEcLuckyMainPaginatedList(EcLuckyMain t) {
		return this.ecLuckyMainDao.selectEntityPaginatedList(t);
	}

	public Long createEcLuckyMain(EcLuckyMain t, String eId) {
		Long id = this.ecLuckyMainDao.insertEntity(t);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != eId && eId.length() > 0) {
			String[] times = eId.split("@@");
			for (String time : times) {
				String[] time2 = time.split("##");
				EcLuckyTime ect = new EcLuckyTime();
				ect.setLucky_id(id);
				try {
					ect.setStart_date(sf.parse(time2[0]));
					ect.setEnd_date(sf.parse(time2[1]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				this.eLuckyTimeDao.insertEntity(ect);
			}
		}

		return id;
	}

	public int modifyEcLuckyMain(EcLuckyMain t, String[] hadIds, String eId) {
		this.ecLuckyMainDao.updateEntity(t);

		if (null == hadIds || hadIds.length == 0) {
			EcLuckyTime ect = new EcLuckyTime();
			ect.setLucky_id(t.getId());
			List<EcLuckyTime> ectList = this.eLuckyTimeDao.selectEntityList(ect);
			if (null != ectList && ectList.size() > 0) {
				for (EcLuckyTime ecLuckyTime : ectList) {
					eLuckyTimeDao.deleteEntity(ecLuckyTime);
				}
			}

		} else {
			List<String> has_ids = new ArrayList<String>();
			for (String has_id : hadIds) {
				EcLuckyTime ect = new EcLuckyTime();
				ect.setId(Long.valueOf(has_id));
				ect = this.eLuckyTimeDao.selectEntity(ect);

				EcLuckyTime ect2 = new EcLuckyTime();
				ect2.setStart_date(ect.getStart_date());
				ect2.setEnd_date(ect.getEnd_date());
				ect2.setLucky_id(t.getId());
				Long id = this.eLuckyTimeDao.insertEntity(ect2);
				has_ids.add(String.valueOf(id));
			}
			EcLuckyTime ect = new EcLuckyTime();
			ect.setLucky_id(t.getId());
			ect.getMap().put("ids_not_in", has_ids);
			List<EcLuckyTime> ectList = this.eLuckyTimeDao.selectEntityList(ect);
			if (null != ectList && ectList.size() > 0) {
				for (EcLuckyTime ecLuckyTime : ectList) {
					eLuckyTimeDao.deleteEntity(ecLuckyTime);
				}
			}

		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 新增活动时间表
		if (null != eId && eId.length() > 0) {
			String[] times = eId.split("@@");
			for (String time : times) {
				String[] time2 = time.split("##");
				EcLuckyTime ect = new EcLuckyTime();
				ect.setLucky_id(t.getId());
				try {
					ect.setStart_date(sf.parse(time2[0]));
					ect.setEnd_date(sf.parse(time2[1]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				this.eLuckyTimeDao.insertEntity(ect);
			}
		}

		return 0;
	}
	
	public int modifyEcLuckyMainViewCounts(EcLuckyMain t) {
		return this.ecLuckyMainDao.updateEcLuckyMainViewCounts(t);
	}

}
