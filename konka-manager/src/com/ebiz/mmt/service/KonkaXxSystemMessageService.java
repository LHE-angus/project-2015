package com.ebiz.mmt.service;

import com.ebiz.mmt.domain.KonkaXxSellBill;

public interface KonkaXxSystemMessageService {

	public void messageToRemindTrigger(KonkaXxSellBill t);
	
	public void messageToRemindTrigger(String type, Long accId);
	
}
