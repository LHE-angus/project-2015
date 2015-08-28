package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcSzDetails;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcSzDetailsService {

	Long createJxcSzDetails(JxcSzDetails t);

	int modifyJxcSzDetails(JxcSzDetails t);

	int removeJxcSzDetails(JxcSzDetails t);

	JxcSzDetails getJxcSzDetails(JxcSzDetails t);
	
	JxcSzDetails getJxcSzDetailsWithMoney(JxcSzDetails t);
	List<JxcSzDetails> getJxcSzDetailsList(JxcSzDetails t);

	Long getJxcSzDetailsCount(JxcSzDetails t);

	List<JxcSzDetails> getJxcSzDetailsPaginatedList(JxcSzDetails t);

}