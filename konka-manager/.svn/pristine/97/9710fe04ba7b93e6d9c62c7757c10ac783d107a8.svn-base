package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SearchFiltersDao;
import com.ebiz.mmt.domain.SearchFilters;
import com.ebiz.mmt.service.SearchFiltersService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-01 10:28:35
 */
@Service
public class SearchFiltersServiceImpl implements SearchFiltersService {

	@Resource
	private SearchFiltersDao searchFiltersDao;
	

	public Long createSearchFilters(SearchFilters t) {
		return this.searchFiltersDao.insertEntity(t);
	}

	public SearchFilters getSearchFilters(SearchFilters t) {
		return this.searchFiltersDao.selectEntity(t);
	}

	public Long getSearchFiltersCount(SearchFilters t) {
		return this.searchFiltersDao.selectEntityCount(t);
	}

	public List<SearchFilters> getSearchFiltersList(SearchFilters t) {
		return this.searchFiltersDao.selectEntityList(t);
	}

	public int modifySearchFilters(SearchFilters t) {
		return this.searchFiltersDao.updateEntity(t);
	}

	public int removeSearchFilters(SearchFilters t) {
		return this.searchFiltersDao.deleteEntity(t);
	}

	public List<SearchFilters> getSearchFiltersPaginatedList(SearchFilters t) {
		return this.searchFiltersDao.selectEntityPaginatedList(t);
	}

}
