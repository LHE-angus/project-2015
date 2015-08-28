package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxDistEmployee;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:55
 */
public interface KonkaXxDistEmployeeService {

	Long createKonkaXxDistEmployee(KonkaXxDistEmployee t);

	int modifyKonkaXxDistEmployee(KonkaXxDistEmployee t);

	int removeKonkaXxDistEmployee(KonkaXxDistEmployee t);

	KonkaXxDistEmployee getKonkaXxDistEmployee(KonkaXxDistEmployee t);

	List<KonkaXxDistEmployee> getKonkaXxDistEmployeeList(KonkaXxDistEmployee t);

	Long getKonkaXxDistEmployeeCount(KonkaXxDistEmployee t);

	List<KonkaXxDistEmployee> getKonkaXxDistEmployeePaginatedList(KonkaXxDistEmployee t);

	// 获取添加人姓名
	List<KonkaXxDistEmployee> gettKonkaXxDistEmployeeForAddUserNamePaginatedList(KonkaXxDistEmployee t);
}