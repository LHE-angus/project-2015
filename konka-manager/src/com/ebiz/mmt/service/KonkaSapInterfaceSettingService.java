package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSapInterfaceSetting;


public interface KonkaSapInterfaceSettingService {

	Long createKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t);

	int modifyKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t);

	int removeKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t);

	KonkaSapInterfaceSetting getKonkaSapInterfaceSetting(KonkaSapInterfaceSetting t);

	List<KonkaSapInterfaceSetting> getKonkaSapInterfaceSettingList(KonkaSapInterfaceSetting t);

	Long getKonkaSapInterfaceSettingCount(KonkaSapInterfaceSetting t);

	List<KonkaSapInterfaceSetting> getKonkaSapInterfaceSettingPaginatedList(KonkaSapInterfaceSetting t);

	/**
	 * sap指定服务是否可用
	 * 
	 * @param serviceName
	 * @return
	 */
	boolean isOpenSapInterfaceFunc(String serviceName);
	

	/**
	 * 获取可用的sap服务 如返回值不为null,则说明服务可用
	 * 
	 * @param serviceName
	 * @return
	 */
	KonkaSapInterfaceSetting getValidKonkaSapInterface(String serviceName);

}