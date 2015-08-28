package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSpActivityBookReport;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-29 11:19:39
 */
public interface KonkaSpActivityBookReportDao extends EntityDao<KonkaSpActivityBookReport> {

	Long selectKonkaSpActivityBookReportYytjCount(
			KonkaSpActivityBookReport entity);

	List<KonkaSpActivityBookReport> selectKonkaSpActivityBookReportYytjPaginatedList(
			KonkaSpActivityBookReport entity);

	Long selectKonkaSpActivityBookReportYyLstjCount(KonkaSpActivityBookReport entity);

	List<KonkaSpActivityBookReport> selectKonkaSpActivityBookReportYyLstjPaginatedList(KonkaSpActivityBookReport entity);

}
