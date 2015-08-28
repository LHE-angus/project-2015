package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3StoreTask;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-06 14:47:57
 */
public interface KonkaR3StoreTaskService {

	Long createKonkaR3StoreTask(KonkaR3StoreTask t);

	int modifyKonkaR3StoreTask(KonkaR3StoreTask t);

	int removeKonkaR3StoreTask(KonkaR3StoreTask t);

	KonkaR3StoreTask getKonkaR3StoreTask(KonkaR3StoreTask t);

	List<KonkaR3StoreTask> getKonkaR3StoreTaskList(KonkaR3StoreTask t);

	Long getKonkaR3StoreTaskCount(KonkaR3StoreTask t);

	List<KonkaR3StoreTask> getKonkaR3StoreTaskPaginatedList(KonkaR3StoreTask t);

	String createKonkaR3StoreTask(List<KonkaR3StoreTask> list);
}