package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3Target;


public interface KonkaR3TargetService {

	Long createKonkaR3Target(KonkaR3Target t);

	int modifyKonkaR3Target(KonkaR3Target t);

	int removeKonkaR3Target(KonkaR3Target t);

	KonkaR3Target getKonkaR3Target(KonkaR3Target t);

	List<KonkaR3Target> getKonkaR3TargetList(KonkaR3Target t);

	Long getKonkaR3TargetCount(KonkaR3Target t);

	List<KonkaR3Target> getKonkaR3TargetPaginatedList(KonkaR3Target t);

}