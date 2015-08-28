package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVoteRecordDao;
import com.ebiz.mmt.domain.EcVoteRecord;
import com.ebiz.mmt.service.EcVoteRecordService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-29 14:02:25
 */
@Service
public class EcVoteRecordServiceImpl implements EcVoteRecordService {

	@Resource
	private EcVoteRecordDao ecVoteRecordDao;
	

	public Long createEcVoteRecord(EcVoteRecord t) {
		return this.ecVoteRecordDao.insertEntity(t);
	}

	public EcVoteRecord getEcVoteRecord(EcVoteRecord t) {
		return this.ecVoteRecordDao.selectEntity(t);
	}

	public Long getEcVoteRecordCount(EcVoteRecord t) {
		return this.ecVoteRecordDao.selectEntityCount(t);
	}

	public List<EcVoteRecord> getEcVoteRecordList(EcVoteRecord t) {
		return this.ecVoteRecordDao.selectEntityList(t);
	}

	public int modifyEcVoteRecord(EcVoteRecord t) {
		return this.ecVoteRecordDao.updateEntity(t);
	}

	public int removeEcVoteRecord(EcVoteRecord t) {
		return this.ecVoteRecordDao.deleteEntity(t);
	}

	public List<EcVoteRecord> getEcVoteRecordPaginatedList(EcVoteRecord t) {
		return this.ecVoteRecordDao.selectEntityPaginatedList(t);
	}
	
	public Long createEcVoteRecordForVote(EcVoteRecord t) {
		//判断是否有最大投票数限制
		Long vote_all_num =(Long)t.getMap().get("vote_all_num");
		if(vote_all_num!=null&&vote_all_num.intValue()>0){ 		
			EcVoteRecord entity= new EcVoteRecord();
			entity.setVote_id(t.getVote_id());
			entity.setIs_del(new Integer(0));
			entity.getMap().put("is_today", "1");
			Long r_count =this.ecVoteRecordDao.selectEntityCount(entity);
			if(r_count.intValue()<=vote_all_num.intValue()){
				return this.ecVoteRecordDao.insertEntity(t);
			}else{
				return null;
			}
		}else{
			return this.ecVoteRecordDao.insertEntity(t);		
		}
	}
}
