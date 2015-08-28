package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxDistAccountRec;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:56:59
 */
public interface KonkaXxDistAccountRecService {

	Long createKonkaXxDistAccountRec(KonkaXxDistAccountRec t);

	int modifyKonkaXxDistAccountRec(KonkaXxDistAccountRec t);

	int removeKonkaXxDistAccountRec(KonkaXxDistAccountRec t);

	KonkaXxDistAccountRec getKonkaXxDistAccountRec(KonkaXxDistAccountRec t);

	List<KonkaXxDistAccountRec> getKonkaXxDistAccountRecList(KonkaXxDistAccountRec t);

	Long getKonkaXxDistAccountRecCount(KonkaXxDistAccountRec t);

	List<KonkaXxDistAccountRec> getKonkaXxDistAccountRecPaginatedList(KonkaXxDistAccountRec t);
	
	/**
	 * @author Hu,Hao
	 * @version 2012-04-01
	 */
	Long createKonkaXxDistAccountRecWithSellBill(KonkaXxDistAccountRec t);
	
	/**
	 * @author Hu,Hao
	 * @version 2012-04-01
	 */
	int modifyKonkaXxDistAccountRecWithSellBill(KonkaXxDistAccountRec t);
	
	/**
	 * @author Hu Hao
	 * @version 2012-04-02
	 */
	int modifyKonkaXxDistAccountForZmd(KonkaXxDistAccountRec t);
}