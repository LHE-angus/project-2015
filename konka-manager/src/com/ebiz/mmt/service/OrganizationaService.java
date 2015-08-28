package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.Organizationa;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 05:05:22
 */
public interface OrganizationaService {

	Long createOrganizationa(Organizationa t);

	int modifyOrganizationa(Organizationa t);

	int removeOrganizationa(Organizationa t);

	Organizationa getOrganizationa(Organizationa t);

	List<Organizationa> getOrganizationaList(Organizationa t);

	Long getOrganizationaCount(Organizationa t);

	List<Organizationa> getOrganizationaPaginatedList(Organizationa t);
}