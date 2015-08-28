package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileMdPercent;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
public interface KonkaMobileMdPercentService {

	Long createKonkaMobileMdPercent(KonkaMobileMdPercent t);

	int modifyKonkaMobileMdPercent(KonkaMobileMdPercent t);

	int removeKonkaMobileMdPercent(KonkaMobileMdPercent t);

	KonkaMobileMdPercent getKonkaMobileMdPercent(KonkaMobileMdPercent t);

	List<KonkaMobileMdPercent> getKonkaMobileMdPercentList(KonkaMobileMdPercent t);

	Long getKonkaMobileMdPercentCount(KonkaMobileMdPercent t);

	List<KonkaMobileMdPercent> getKonkaMobileMdPercentPaginatedList(KonkaMobileMdPercent t);

}