package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcSallareaTemplate;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 10:43:11
 */
public interface EcSallareaTemplateService {

	Long createEcSallareaTemplate(EcSallareaTemplate t);

	int modifyEcSallareaTemplate(EcSallareaTemplate t);

	int removeEcSallareaTemplate(EcSallareaTemplate t);

	EcSallareaTemplate getEcSallareaTemplate(EcSallareaTemplate t);

	List<EcSallareaTemplate> getEcSallareaTemplateList(EcSallareaTemplate t);

	Long getEcSallareaTemplateCount(EcSallareaTemplate t);

	List<EcSallareaTemplate> getEcSallareaTemplatePaginatedList(EcSallareaTemplate t);

}