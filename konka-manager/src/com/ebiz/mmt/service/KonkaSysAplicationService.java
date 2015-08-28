package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaSysAplication;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;


public interface KonkaSysAplicationService {

	Long createKonkaSysAplication(KonkaSysAplication t);

	int modifyKonkaSysAplication(KonkaSysAplication t);

	int removeKonkaSysAplication(KonkaSysAplication t);

	HashMap getKonkaSysAplication(KonkaSysAplication t);

	List<HashMap> getKonkaSysAplicationList(KonkaSysAplication t);

	Long getKonkaSysAplicationCount(KonkaSysAplication t);

	List<KonkaSysAplication> getKonkaSysAplicationPaginatedList(KonkaSysAplication t);
	
	int modifyAplicationStatus(KonkaSysAplication t);
}