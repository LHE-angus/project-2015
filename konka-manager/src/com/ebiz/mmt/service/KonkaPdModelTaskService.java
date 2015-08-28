package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaPdModelTask;


public interface KonkaPdModelTaskService {

	Long createKonkaPdModelTask(KonkaPdModelTask t);

	int modifyKonkaPdModelTask(KonkaPdModelTask t);

	int removeKonkaPdModelTask(KonkaPdModelTask t);

	KonkaPdModelTask getKonkaPdModelTask(KonkaPdModelTask t);

	List<KonkaPdModelTask> getKonkaPdModelTaskList(KonkaPdModelTask t);

	Long getKonkaPdModelTaskCount(KonkaPdModelTask t);

	List<KonkaPdModelTask> getKonkaPdModelTaskPaginatedList(KonkaPdModelTask t);

	String createKonkaPdModelTask(List<KonkaPdModelTask> list);

	List<HashMap<String, String>> getKonkaPdModelXCLY(KonkaPdModelTask t);

	List<HashMap<String, Object>> getKonkaPdModelXCLYHZ(KonkaPdModelTask t);
}