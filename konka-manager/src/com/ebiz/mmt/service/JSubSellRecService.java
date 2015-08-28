package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JSubSellRec;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-17 11:19:45
 */
public interface JSubSellRecService {

	Long createJSubSellRec(JSubSellRec t);

	int modifyJSubSellRec(JSubSellRec t);

	int removeJSubSellRec(JSubSellRec t);

	JSubSellRec getJSubSellRec(JSubSellRec t);

	List<JSubSellRec> getJSubSellRecList(JSubSellRec t);

	Long getJSubSellRecCount(JSubSellRec t);

	Long getJSubSellRecCountForFourWeek(JSubSellRec t);

	List<JSubSellRec> getJSubSellRecPaginatedList(JSubSellRec t);

	int modifyJSubSellRecAndAddCGJBill(JSubSellRec t, JBill jbill);

	List<JSubSellRec> getJSubSellRecPaginatedListOfDetails(JSubSellRec t);

	Long getJSubSellRecCountOfDetails(JSubSellRec t);
	
	/**
	 * 分销确认，更新库存
	 * @author Liang Houen
	 * @since 2015-6-24
	 * @param t
	 * @param b
	 * @return
	 */
	int modifyJSubAndStore(JSubSellRec t, JBill b, Long c_id, Long user_id);
}