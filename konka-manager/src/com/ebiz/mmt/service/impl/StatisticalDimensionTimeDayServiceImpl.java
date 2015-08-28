package com.ebiz.mmt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionTimeDayDao;
import com.ebiz.mmt.domain.StatisticalDimensionTimeDay;
import com.ebiz.mmt.service.StatisticalDimensionTimeDayService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-25 21:58:48
 */
@Service
public class StatisticalDimensionTimeDayServiceImpl implements StatisticalDimensionTimeDayService {

	@Resource
	private StatisticalDimensionTimeDayDao statisticalDimensionTimeDayDao;

	public Long createStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.insertEntity(t);
	}

	public StatisticalDimensionTimeDay getStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.selectEntity(t);
	}

	public Long getStatisticalDimensionTimeDayCount(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionTimeDay> getStatisticalDimensionTimeDayList(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.updateEntity(t);
	}

	public int removeStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.deleteEntity(t);
	}

	public List<StatisticalDimensionTimeDay> getStatisticalDimensionTimeDayPaginatedList(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.selectEntityPaginatedList(t);
	}

	/**
	 * 初始化时间数据,初始化上次最新时间到当前时间的后三天
	 */
	@Override
	public Long initStatisticalDimensionTimeDay() {
		StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
		entity.setIs_del(0);
		List<StatisticalDimensionTimeDay> entityList = this.getStatisticalDimensionTimeDayList(entity);
		if (null != entityList && null != entityList.get(0)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = df.format(entityList.get(0).getDay().getTime() + 1 * 24 * 60 * 60 * 1000);
			Date date = new Date();
			String edate = df.format(date.getTime() + 3 * 24 * 60 * 60 * 1000);
			return initStatisticalDimensionTimeDay(sdate, edate);
		}
		return 0l;
	}

	/**
	 * 初始化时间数据,初始化sdate时间到结束时间edate 格式yyyy-MM-dd
	 */
	public Long initStatisticalDimensionTimeDay(String sdate, String edate) {
		long result = 0l;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Long startM = sdf.parse(sdate).getTime();
			Long endM = sdf.parse(edate).getTime();
			result = (endM - startM) / (24 * 60 * 60 * 1000);
			//System.out.println("===差:" + result + "天");

			Date startDate = sdf.parse(sdate);
			Calendar startTime = Calendar.getInstance();
			startTime.clear();
			startTime.setTime(startDate);
			int day = 0;
			int week = 0;
			int month = 0;
			int quarter = 0;
			int year = 0;
			StatisticalDimensionTimeDay entity = new StatisticalDimensionTimeDay();
			for (int i = 0; i < (int) result; i++) {
				day = startTime.get(Calendar.DAY_OF_MONTH);
				week = startTime.get(Calendar.WEEK_OF_YEAR);
				month = startTime.get(Calendar.MONTH) + 1;
				year = startTime.get(Calendar.YEAR);
				if (month == 1 || month == 2 || month == 3) {
					quarter = 1;
				} else if (month == 4 || month == 5 || month == 6) {
					quarter = 2;
				} else if (month == 7 || month == 8 || month == 9) {
					quarter = 3;
				} else if (month == 10 || month == 11 || month == 12) {
					quarter = 4;
				}
				//System.out.println("===date=" + sdf.format(startTime.getTime()) + "   是第" + week + "周，第" + month
                // + "月,第" + quarter + "季度，第" + year + "年");
				entity.setDay(startTime.getTime());
				entity.setWeek(week);
				entity.setMonth(month);
				entity.setQuarter(quarter);
				entity.setYear(year);
				if (month == 5 && day == 1) {// 判断是否是5.1
					entity.setFive_p_one(1);
				} else {
					entity.setFive_p_one(0);
				}
				if (month == 5 && day == 21) {// 判断是否是5.21
					entity.setFive_p_twenty_one(1);
				} else {
					entity.setFive_p_twenty_one(0);
				}
				if (month == 10 && day == 1) {// 判断是否是10.1
					entity.setTen_p_one(1);
				} else {
					entity.setTen_p_one(0);
				}
				entity.setSpring_festival(0);
				entity.setAdd_date(new Date());
				entity.setIs_del(0);
				this.createStatisticalDimensionTimeDay(entity);
				startTime.add(Calendar.DAY_OF_YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int modifyStatisticalDimensionTimeDayByDate(StatisticalDimensionTimeDay t) {
		return this.statisticalDimensionTimeDayDao.updateStatisticalDimensionTimeDayByDate(t);
	}

}
