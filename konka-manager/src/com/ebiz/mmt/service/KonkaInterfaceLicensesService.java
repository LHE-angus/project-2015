package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaInterfaceLicenses;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
public interface KonkaInterfaceLicensesService {

	Long createKonkaInterfaceLicenses(KonkaInterfaceLicenses t);

	int modifyKonkaInterfaceLicenses(KonkaInterfaceLicenses t);

	int removeKonkaInterfaceLicenses(KonkaInterfaceLicenses t);

	KonkaInterfaceLicenses getKonkaInterfaceLicenses(KonkaInterfaceLicenses t);

	List<KonkaInterfaceLicenses> getKonkaInterfaceLicensesList(KonkaInterfaceLicenses t);

	Long getKonkaInterfaceLicensesCount(KonkaInterfaceLicenses t);

	List<KonkaInterfaceLicenses> getKonkaInterfaceLicensesPaginatedList(KonkaInterfaceLicenses t);

}