package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdPosRec;

/**
 * @author Ren,zhong
 * @version 2012-05-02 15:14
 */
public interface KonkaXxZmdPosRecService {

	Long createKonkaXxZmdPosRec(KonkaXxZmdPosRec t);

	int modifyKonkaXxZmdPosRec(KonkaXxZmdPosRec t);

	int removeKonkaXxZmdPosRec(KonkaXxZmdPosRec t);

	KonkaXxZmdPosRec getKonkaXxZmdPosRec(KonkaXxZmdPosRec t);

	List<KonkaXxZmdPosRec> getKonkaXxZmdPosRecList(KonkaXxZmdPosRec t);

	Long getKonkaXxZmdPosRecCount(KonkaXxZmdPosRec t);

	List<KonkaXxZmdPosRec> getKonkaXxZmdPosRecPaginatedList(KonkaXxZmdPosRec t);
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-02
	 */
	Long createKonkaXxZmdPosRecForZmd(KonkaXxZmdPosRec t);
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	int modifyKonkaXxZmdPosRecForZmd(KonkaXxZmdPosRec t);
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	Long getKonkaXxZmdPosRecForZmdSnCount(KonkaXxZmdPosRec t);
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	List<KonkaXxZmdPosRec> getKonkaXxZmdPosRecForZmdSnPaginatedList(KonkaXxZmdPosRec t);
}