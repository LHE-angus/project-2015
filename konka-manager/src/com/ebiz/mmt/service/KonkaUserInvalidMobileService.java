package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaUserInvalidMobile;


/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
public interface KonkaUserInvalidMobileService {

	long createKonkaUserInvalidMobile(KonkaUserInvalidMobile t);

	long modifyKonkaUserInvalidMobile(KonkaUserInvalidMobile t);

	long removeKonkaUserInvalidMobile(KonkaUserInvalidMobile t);

	KonkaUserInvalidMobile getKonkaUserInvalidMobile(KonkaUserInvalidMobile t);

	List<KonkaUserInvalidMobile> getKonkaUserInvalidMobileList(KonkaUserInvalidMobile t);

	long getKonkaUserInvalidMobileCount(KonkaUserInvalidMobile t);

	List<KonkaUserInvalidMobile> getKonkaUserInvalidMobilePaginatedList(KonkaUserInvalidMobile t);

}
