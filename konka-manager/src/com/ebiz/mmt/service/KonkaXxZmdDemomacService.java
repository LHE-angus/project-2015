package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdDemomac;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public interface KonkaXxZmdDemomacService {

	Long createKonkaXxZmdDemomac(KonkaXxZmdDemomac t);

	int modifyKonkaXxZmdDemomac(KonkaXxZmdDemomac t);

	int removeKonkaXxZmdDemomac(KonkaXxZmdDemomac t);

	KonkaXxZmdDemomac getKonkaXxZmdDemomac(KonkaXxZmdDemomac t);

	List<KonkaXxZmdDemomac> getKonkaXxZmdDemomacList(KonkaXxZmdDemomac t);

	Long getKonkaXxZmdDemomacCount(KonkaXxZmdDemomac t);

	List<KonkaXxZmdDemomac> getKonkaXxZmdDemomacPaginatedList(KonkaXxZmdDemomac t);

}