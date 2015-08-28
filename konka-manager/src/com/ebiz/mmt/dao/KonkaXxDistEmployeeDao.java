package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:54
 */
public interface KonkaXxDistEmployeeDao extends EntityDao<KonkaXxDistEmployee> {

	// 获取添加人姓名
	public List<KonkaXxDistEmployee> selectKonkaXxDistEmployeeForAddUserNamePaginatedList(KonkaXxDistEmployee t);
}
