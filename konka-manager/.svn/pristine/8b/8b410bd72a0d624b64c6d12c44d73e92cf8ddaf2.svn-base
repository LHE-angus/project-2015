package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ChannelDataImport3Dao;
import com.ebiz.mmt.domain.ChannelDataImport3;
import com.ebiz.mmt.service.ChannelDataImport3Service;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-20 11:34:07
 */
@Service
public class ChannelDataImport3ServiceImpl implements ChannelDataImport3Service {

	@Resource
	private ChannelDataImport3Dao channelDataImport3Dao;

	public Long createChannelDataImport3(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.insertEntity(t);
	}

	public ChannelDataImport3 getChannelDataImport3(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.selectEntity(t);
	}

	public Long getChannelDataImport3Count(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.selectEntityCount(t);
	}

	public List<ChannelDataImport3> getChannelDataImport3List(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.selectEntityList(t);
	}

	public int modifyChannelDataImport3(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.updateEntity(t);
	}

	public int removeChannelDataImport3(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.deleteEntity(t);
	}

	public List<ChannelDataImport3> getChannelDataImport3PaginatedList(ChannelDataImport3 t) {
		return this.channelDataImport3Dao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-20
	 * @desc 业绩划拨
	 */
	public Long createChannelDataImport3ForImp(List<ChannelDataImport3> t, Long m) {
		Long i = 0L;
		
		//删除以前当月存在的数据
		ChannelDataImport3 c = new ChannelDataImport3();
		c.setImport_uid(m);
		this.channelDataImport3Dao.deleteEntity(c);
		
		//插入新数据
		for (ChannelDataImport3 temp : t) {
			temp.setImport_uid(m);
			temp.setImport_date(new Date());
			i++;
			this.channelDataImport3Dao.insertEntity(temp);
		}
		return i;
	}

}
