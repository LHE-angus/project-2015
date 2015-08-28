package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.CrmPriceHeader;


public interface CrmPriceHeaderService {

	Long createCrmPriceHeader(CrmPriceHeader t);

	int modifyCrmPriceHeader(CrmPriceHeader t);

	int removeCrmPriceHeader(CrmPriceHeader t);

	CrmPriceHeader getCrmPriceHeader(CrmPriceHeader t);

	List<CrmPriceHeader> getCrmPriceHeaderList(CrmPriceHeader t);

	List<CrmPriceHeader> getCrmPriceHeaderListFiterByTime(CrmPriceHeader t);

    /**
     * 获取此时间范围内有交互的数据(有可能包括在它本身的数据)
     * 
     * 
     * @param dept_id
     * @param group_code
     * @param price_type
     * @param begin_date
     * @param end_date
     * @return
     */
    List<CrmPriceHeader> getTheListOfDeptIdGroupCodePriceTypeAndTime(String dept_id,
            String group_code, String price_type, String begin_date, String end_date);

	Long getCrmPriceHeaderCount(CrmPriceHeader t);

	List<CrmPriceHeader> getCrmPriceHeaderPaginatedList(CrmPriceHeader t);

	String getPriceCode();

	/**
	 * true,重复 false,不重复
	 * 
	 * @param deptId
	 * @param priceName
	 * @return
	 */
	boolean isExitsPriceName(Long deptId, String priceName);

	/**
	 * 
	 * @param deptId
	 * @param groupCode
	 * @return
	 */
    // boolean isExitsCustomerGroupDeptID(Long deptId, String groupCode, Date startdate, Date
    // enddate);

	String getNextIdFromSeq();
}