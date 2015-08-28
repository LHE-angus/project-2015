package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPersonPwd;

/**
 * @author Ren,zhong
 * @version 2013-07-26 11:06
 */
public interface KonkaPersonPwdService {

	Long createKonkaPersonPwd(KonkaPersonPwd t);

	int modifyKonkaPersonPwd(KonkaPersonPwd t);

	int removeKonkaPersonPwd(KonkaPersonPwd t);

	KonkaPersonPwd getKonkaPersonPwd(KonkaPersonPwd t);

	List<KonkaPersonPwd> getKonkaPersonPwdList(KonkaPersonPwd t);

	Long getKonkaPersonPwdCount(KonkaPersonPwd t);

	List<KonkaPersonPwd> getKonkaPersonPwdPaginatedList(KonkaPersonPwd t);
	
	/**
	 * @author Ren,zhong
	 * @date 2013-07-30
	 */
	int modifyKonkaPersonPwdLoginCount(KonkaPersonPwd t);

}