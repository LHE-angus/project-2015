package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcBindingMobile;

/**
 * @author Wu,Yang
 * @version 2011-12-20 16:03
 */
public interface KonkaJxcBindingMobileService {

	Long createKonkaJxcBindingMobile(KonkaJxcBindingMobile t);

	int modifyKonkaJxcBindingMobile(KonkaJxcBindingMobile t);

	int removeKonkaJxcBindingMobile(KonkaJxcBindingMobile t);

	KonkaJxcBindingMobile getKonkaJxcBindingMobile(KonkaJxcBindingMobile t);

	List<KonkaJxcBindingMobile> getKonkaJxcBindingMobileList(KonkaJxcBindingMobile t);

	Long getKonkaJxcBindingMobileCount(KonkaJxcBindingMobile t);

	List<KonkaJxcBindingMobile> getKonkaJxcBindingMobilePaginatedList(KonkaJxcBindingMobile t);

}