package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcGiftContent;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcGiftContentService {

	Long createEcGiftContent(EcGiftContent t);

	int modifyEcGiftContent(EcGiftContent t);

	int removeEcGiftContent(EcGiftContent t);

	EcGiftContent getEcGiftContent(EcGiftContent t);

	List<EcGiftContent> getEcGiftContentList(EcGiftContent t);

	Long getEcGiftContentCount(EcGiftContent t);

	List<EcGiftContent> getEcGiftContentPaginatedList(EcGiftContent t);

}