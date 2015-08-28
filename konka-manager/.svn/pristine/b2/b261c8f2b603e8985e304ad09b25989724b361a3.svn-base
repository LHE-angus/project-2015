package com.ebiz.mmt.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmPriceHeaderDao;
import com.ebiz.mmt.domain.CrmPriceHeader;
import com.ebiz.mmt.service.CrmPriceHeaderService;

@Service
public class CrmPriceHeaderServiceImpl implements CrmPriceHeaderService {

	@Resource
	private CrmPriceHeaderDao crmPriceHeaderDao;

	@Override
	public Long createCrmPriceHeader(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.insertEntity(t);
	}

	@Override
	public CrmPriceHeader getCrmPriceHeader(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.selectEntity(t);
	}

	@Override
	public Long getCrmPriceHeaderCount(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.selectEntityCount(t);
	}

	@Override
	public List<CrmPriceHeader> getCrmPriceHeaderList(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.selectEntityList(t);
	}

	@Override
	public int modifyCrmPriceHeader(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.updateEntity(t);
	}

	@Override
	public int removeCrmPriceHeader(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.deleteEntity(t);
	}

	@Override
	public List<CrmPriceHeader> getCrmPriceHeaderPaginatedList(CrmPriceHeader t) {
		return this.crmPriceHeaderDao.selectEntityPaginatedList(t);
	}

	@Override
	public String getPriceCode() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return "P" + sdf.format(date) + (new Random().nextInt(1000));
	}

	@Override
	public boolean isExitsPriceName(Long deptId, String priceName) {
		CrmPriceHeader t = new CrmPriceHeader();
		t.setDept_id(Long.valueOf(deptId));
		// t.setPrice_name(priceName);
		t.getMap().put("price_name_eq", priceName);
		return this.crmPriceHeaderDao.selectEntityCount(t) > 0;
	}

	@Override
	public String getNextIdFromSeq() {
		return this.crmPriceHeaderDao.getNextSeqId();
	}

    // @Override
    // public boolean isExitsCustomerGroupDeptID(Long deptId, String groupCode, Date startdate,
    // Date enddate) {
    //
    // CrmPriceHeader t = new CrmPriceHeader();
    // t.setDept_id(Long.valueOf(deptId));
    // t.setIsdel(0);
    // t.setGroupcode(groupCode);
    //
    // t.getMap().put("begin_date", startdate);
    // t.getMap().put("end_date", enddate);
    //
    // List<CrmPriceHeader> list = new ArrayList<CrmPriceHeader>();
    // try {
    // list = this.crmPriceHeaderDao.selectEntityListFiterByTime(t);
    // } catch (DataAccessException e) {
    // e.printStackTrace();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    //
    // return list.size() > 0;
    // }

    @Override
    public List<CrmPriceHeader> getCrmPriceHeaderListFiterByTime(CrmPriceHeader t) {
        t.getMap().put("filter_time", "y");
        try {
            return this.crmPriceHeaderDao.selectEntityListFiterByTime(t);
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<CrmPriceHeader>();
    }

    @Override
    public List<CrmPriceHeader> getTheListOfDeptIdGroupCodePriceTypeAndTime(String dept_id,
            String group_code, String price_type, String begin_date, String end_date) {
        List<CrmPriceHeader> list = new ArrayList<CrmPriceHeader>();
        if (dept_id == null || "".equals(dept_id)) {
            return list;
        }
        if (group_code == null || "".equals(group_code)) {
            return list;
        }
        if (price_type == null || "".equals(price_type)) {
            return list;
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("dept_id", dept_id);
        map.put("group_code", group_code);
        map.put("price_type", price_type);
        map.put("begin_date", begin_date);
        map.put("end_date", end_date);


        list = this.crmPriceHeaderDao.selectListOfDeptIdGroupCodePriceTypeAndTime(map);
        return list;

    }

}
