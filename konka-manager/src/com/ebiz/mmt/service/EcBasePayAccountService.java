package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBasePayAccount;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-23 19:14:55
 */
public interface EcBasePayAccountService {

	Long createEcBasePayAccount(EcBasePayAccount t);

	int modifyEcBasePayAccount(EcBasePayAccount t);

	int removeEcBasePayAccount(EcBasePayAccount t);

	EcBasePayAccount getEcBasePayAccount(EcBasePayAccount t);

	List<EcBasePayAccount> getEcBasePayAccountList(EcBasePayAccount t);

	Long getEcBasePayAccountCount(EcBasePayAccount t);

	List<EcBasePayAccount> getEcBasePayAccountPaginatedList(EcBasePayAccount t);

}