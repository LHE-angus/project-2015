package com.ebiz.mmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpListDao;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.service.DataSync4BIService;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class DataSync4BIServiceImpl extends EntityDaoSqlMapImpl<Object> implements DataSync4BIService {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    KonkaSpListDao konkaSpListDao;

    @Override
    public void updateStockInfo4BI() {
        super.getSqlMapClientTemplate().queryForObject("updateStockInfo4BI");

    }

    @Override
    public void updateMobileSalesData4BI() {

        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.add(Calendar.DATE, -35);
        String starttime = sdf.format(cal.getTime()) + " 00:00:00";

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("START_TIME", starttime);

        super.getSqlMapClientTemplate().queryForObject("updateMobileSalesData4BI", map);
    }


    @Override
    public void updateStoreCustomerRelData4BI() {
        long s = System.currentTimeMillis();
        super.getSqlMapClientTemplate().queryForObject("updateStoreCustomerRelData4BI");
        long e = System.currentTimeMillis();
        //System.out.println("updateStoreCustomerRelData4BI takes :" + (e - s) / 1000);
    }

    @Override
    public void updateStoreSaleInfoData4BI() {
        long s = System.currentTimeMillis();
        // 上个月月结的结束时间,如果没有,默认当前月的月初.也就是当月1日或当月开始日期
        String starttime = "";
        // 昨天
        String endtime;
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.add(Calendar.DATE, -1);
        endtime = sdf.format(cal.getTime()) + " 23:59:59";

        GregorianCalendar cal2 = (GregorianCalendar) GregorianCalendar.getInstance();
        cal2.add(Calendar.MONTH, -1);
        KonkaSpList sp = new KonkaSpList();
        sp.setYear(cal2.get(Calendar.YEAR));
        // important
        sp.setMonth(cal2.get(Calendar.MONTH) + 1);
        sp.setIs_del(0);
        sp = konkaSpListDao.selectEntity(sp);
        if (sp != null) {
            if (sp.getE_date() != null) {
                cal2.setTime(sp.getE_date());
                cal2.add(Calendar.DATE, 1);
                starttime = sdf.format(cal2.getTime()) + " 00:00:00";
            }
        } else {
            cal2.setTime(new Date());
            // important
            int i_month = cal2.get(Calendar.MONTH) + 1;
            String smonth = i_month > 10 ? String.valueOf(i_month) : "0".concat(String.valueOf(i_month));
            starttime = String.valueOf(cal2.get(Calendar.YEAR)).concat("-").concat(smonth).concat("-").concat("01") + " 00:00:00";

        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("START_TIME", starttime);
        map.put("END_TIME", endtime);

        super.getSqlMapClientTemplate().queryForObject("updateStoreSaleInfoData4BI", map);
        long e = System.currentTimeMillis();
        //System.out.println("updateStoreSaleInfoData4BI takes :" + (e - s) / 1000);
    }

    @Override
    public void updateYJGLData4BI() {

        long s = System.currentTimeMillis();

        final String starttime = "2005-01-01 00:00:00";
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String endtime = sdf.format(cal.getTime()) + " 23:59:59";

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("START_TIME", starttime);
        map.put("END_TIME", endtime);

        super.getSqlMapClientTemplate().queryForObject("updateYJGLData4BI", map);

        long e = System.currentTimeMillis();

        //System.out.println("updateYJGLData4BI takes :" + (e - s) / 1000);

    }


}
