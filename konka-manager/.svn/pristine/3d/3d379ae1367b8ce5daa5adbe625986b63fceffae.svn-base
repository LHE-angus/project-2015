package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:19:59
 */
public interface PshowOrderService {

	Long createPshowOrder(PshowOrder t);

	Long createPshowOrderWithDetails(PshowOrder t);

	Long createPshowOrderWithDetailsForZxMall(PshowOrder t);

	Long createPshowOrderWithDetailsForSplit(List<PshowOrder> pshowList, String id);

	int modifyPshowOrder(PshowOrder t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-17
	 */
	int modifyPshowOrderAndSaleNum(PshowOrder t);

	int removePshowOrder(PshowOrder t);

	PshowOrder getPshowOrder(PshowOrder t);

	List<PshowOrder> getPshowOrderList(PshowOrder t);

	Long getPshowOrderCount(PshowOrder t);

	List<PshowOrder> getPshowOrderPaginatedList(PshowOrder t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-15
	 */
	List<PshowOrder> getPshowOrderIncludeDetailsList(PshowOrder t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	List<PshowOrder> getPshowOrderIncludeDetailsPaginatedList(PshowOrder t);

	/**
	 * @author Pan,Gang
	 * @version 2013-08-16
	 */
	List<PshowOrder> getPshowOrdeForDeptNameAndFullNameList(PshowOrder t);

	List<PshowOrder> getPshowOrdeForPdDetailsList(PshowOrder t);

	Long getPshowOrdeForDeptNameAndFullNameListCount(PshowOrder t);

	Long getPshowOrdeForPdDetailsListCount(PshowOrder t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-12
	 */
	BigDecimal getTotalPriceByOrderUserId(PshowOrder t);

	int modifyPshowOrderForCancel(PshowOrder t);

	int modifyPshowOrderForSwitchR3(PshowOrder t);

	public Long createPshowOrderWithDetailsForTuan(PshowOrder t);

	Long createPshowOrdeAuditAndModifyPshowOrder(PshowOrder t, PshowOrdeAudit t1) throws Exception;

	Long createPshowOrdeAuditAndModifyPshowOrderForTuan(PshowOrder t, PshowOrdeAudit t1) throws Exception;

	// 触网发货，插入销售记录已经更新库存
	Long createPshowOrdeAuditAndModifyPshowOrder3(PshowOrder t, PshowOrdeAudit t1, JBill jbill) throws Exception;

	Long createPshowOrdeAuditAndModifyPshowOrder2(PshowOrder t, PshowOrdeAudit t1);

	Long createPshowOrderForExchange(PshowOrder t, PshowOrdeAudit poa, List<PshowOrdeDetails> pshowOrdeDetailsList,
			int order_type);

	Long createPshowOrdeAuditAndModifyPshowOrderForBatch(PeProdUser ecuser, String[] pks) throws Exception;

	public Long getPshowOrderSubIncludeDetailsPaginatedListCount(PshowOrder t) throws Exception;

	public List<PshowOrder> getPshowOrderSubIncludeDetailsPaginatedList(PshowOrder t) throws Exception;

	List<PshowOrder> getPshowOrderAndDetailsForTj(PshowOrder t) throws DataAccessException;

	List<PshowOrder> getPshowOrderAndDetailsForTj2(PshowOrder t) throws DataAccessException;
}