package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSxOperLogDao;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaSxOperLogService;
import com.ebiz.org.apache.commons.lang3.StringUtils;


@Service
public class KonkaSxOperLogServiceImpl implements KonkaSxOperLogService {

	@Resource
	private KonkaSxOperLogDao konkaSxOperLogDao;

	public Long createKonkaSxOperLog(OperLog t) {
		return this.konkaSxOperLogDao.insertEntity(t);
	}

	public OperLog getKonkaSxOperLog(OperLog t) {
		return this.konkaSxOperLogDao.selectEntity(t);
	}

	public Long getKonkaSxOperLogCount(OperLog t) {
		return this.konkaSxOperLogDao.selectEntityCount(t);
	}

	public List<OperLog> getKonkaSxOperLogList(OperLog t) {
		return this.konkaSxOperLogDao.selectEntityList(t);
	}

	public int modifyKonkaSxOperLog(OperLog t) {
		return this.konkaSxOperLogDao.updateEntity(t);
	}

	public int removeKonkaSxOperLog(OperLog t) {
		return this.konkaSxOperLogDao.deleteEntity(t);
	}

	public List<OperLog> getKonkaSxOperLogPaginatedList(OperLog t) {
		return this.konkaSxOperLogDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long createKonkaSxOperLog(PeProdUser user,String link_id,String tab_Name, String oper_ip, String oper_desc) {
		
		OperLog  operlog=new OperLog();
		if(StringUtils.isNotEmpty(link_id)&&GenericValidator.isLong(link_id)){
			operlog.setLink_id(Long.valueOf(link_id));
		}
		if(StringUtils.isNotEmpty(tab_Name)){
			operlog.setLink_tab(tab_Name);	
		}
		if(StringUtils.isNotEmpty(oper_ip)){
			operlog.setOper_ip(oper_ip);
		}
		if(StringUtils.isNotEmpty(oper_desc)){
			operlog.setOper_desc(oper_desc);
		}
		
		operlog.setOper_time(new Date());
		if(null!=user){
			if(null!=user.getId()){
				operlog.setOper_uid(user.getId());
			}
			if(null!=user.getUser_name()){
				operlog.setOper_uname(user.getUser_name());
				operlog.setPpdm_name(user.getUser_name()+"修改拜访计划");
			}
			if (null!=user.getId()&&null!=user.getPass_word()) {
				operlog.setOper_type("账户名："+user.getId()+",密码："+user.getPass_word());
			}
		}
	return this.konkaSxOperLogDao.insertEntity(operlog);
	}

}
