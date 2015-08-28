package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.ChannelDataImport3;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-20 11:34:07
 */
public interface ChannelDataImport3Service {

	Long createChannelDataImport3(ChannelDataImport3 t);

	int modifyChannelDataImport3(ChannelDataImport3 t);

	int removeChannelDataImport3(ChannelDataImport3 t);

	ChannelDataImport3 getChannelDataImport3(ChannelDataImport3 t);

	List<ChannelDataImport3> getChannelDataImport3List(ChannelDataImport3 t);

	Long getChannelDataImport3Count(ChannelDataImport3 t);

	List<ChannelDataImport3> getChannelDataImport3PaginatedList(ChannelDataImport3 t);
	
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-20
	 * @desc 业绩划拨
 	 */
	Long createChannelDataImport3ForImp(List<ChannelDataImport3> t,Long m);

}