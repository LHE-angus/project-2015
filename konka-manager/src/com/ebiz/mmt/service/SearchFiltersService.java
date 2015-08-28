package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.SearchFilters;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-01 10:28:35
 */
public interface SearchFiltersService {

	Long createSearchFilters(SearchFilters t);

	int modifySearchFilters(SearchFilters t);

	int removeSearchFilters(SearchFilters t);

	SearchFilters getSearchFilters(SearchFilters t);

	List<SearchFilters> getSearchFiltersList(SearchFilters t);

	Long getSearchFiltersCount(SearchFilters t);

	List<SearchFilters> getSearchFiltersPaginatedList(SearchFilters t);

}