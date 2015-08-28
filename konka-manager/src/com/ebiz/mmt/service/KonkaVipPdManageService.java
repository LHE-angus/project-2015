package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaVipPdManage;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-16 13:09:31
 */
public interface KonkaVipPdManageService {

	Long createKonkaVipPdManage(KonkaVipPdManage t);

	int modifyKonkaVipPdManage(KonkaVipPdManage t);

	int removeKonkaVipPdManage(KonkaVipPdManage t);

	KonkaVipPdManage getKonkaVipPdManage(KonkaVipPdManage t);

	List<KonkaVipPdManage> getKonkaVipPdManageList(KonkaVipPdManage t);

	Long getKonkaVipPdManageCount(KonkaVipPdManage t);

	List<KonkaVipPdManage> getKonkaVipPdManagePaginatedList(KonkaVipPdManage t);

}