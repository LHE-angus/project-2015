package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.SfhkCompany;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-28 10:39:24
 */
public interface SfhkCompanyService {

	Long createSfhkCompany(SfhkCompany t);

	int modifySfhkCompany(SfhkCompany t);

	int removeSfhkCompany(SfhkCompany t);

	SfhkCompany getSfhkCompany(SfhkCompany t);

	List<SfhkCompany> getSfhkCompanyList(SfhkCompany t);

	Long getSfhkCompanyCount(SfhkCompany t);

	List<SfhkCompany> getSfhkCompanyPaginatedList(SfhkCompany t);

}