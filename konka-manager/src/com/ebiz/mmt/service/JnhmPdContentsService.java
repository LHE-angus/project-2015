package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JnhmPdContents;

/**
 * @author Li,Ka
 * @version 2012-07-30 19:32
 */
public interface JnhmPdContentsService {

	Long createJnhmPdContents(JnhmPdContents t);

	int modifyJnhmPdContents(JnhmPdContents t);

	int removeJnhmPdContents(JnhmPdContents t);

	JnhmPdContents getJnhmPdContents(JnhmPdContents t);

	List<JnhmPdContents> getJnhmPdContentsList(JnhmPdContents t);

	Long getJnhmPdContentsCount(JnhmPdContents t);

	List<JnhmPdContents> getJnhmPdContentsPaginatedList(JnhmPdContents t);

}