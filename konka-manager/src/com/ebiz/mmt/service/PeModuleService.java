package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.PeModule;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-24 10:17:11
 */
public interface PeModuleService {

	Long createPeModule(PeModule t);

	int modifyPeModule(PeModule t);

	int removePeModule(PeModule t);

	PeModule getPeModule(PeModule t);

	List<PeModule> getPeModuleList(PeModule t);

	Long getPeModuleCount(PeModule t);

	List<PeModule> getPeModulePaginatedList(PeModule t);

	// add by liyuan
	List<PeModule> getPeModuleParentList(PeModule t);

	/**
	 * @author Jiang,JiaYong
	 * @date 2011-02-25
	 */
	List<PeModule> getPeModuleAllParentList(PeModule t);
	
	
	/**
	 * @author Jiang,JiaYong
	 * @version 2011-05-16
	 */
	List<PeModule> getPeModuleForPeUserList(PeModule t);

	/**
	 * @author Li,Yuan
	 * @version 2011-05-13
	 */
	List<PeModule> getPeModuleListForAuthor();

}