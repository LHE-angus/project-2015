package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3OrderHeaderDao;
import com.ebiz.mmt.dao.KonkaR3OrderLinesDao;
import com.ebiz.mmt.domain.KonkaR3OrderHeader;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.service.KonkaR3OrderHeaderService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by zhou
 * @date 2013-05-29 14:09:13
 */
@Service
public class KonkaR3OrderHeaderServiceImpl implements KonkaR3OrderHeaderService {

	@Resource
	private KonkaR3OrderHeaderDao konkaR3OrderHeaderDao;
	
	@Resource
	
	private KonkaR3OrderLinesDao konkaR3OrderLinesDao ;

	public Long createKonkaR3OrderHeader(KonkaR3OrderHeader t) {
		
		//头表id
		long hid = this.konkaR3OrderHeaderDao.insertEntity(t) ;
		
		//处理从表
		List<KonkaR3OrderLines> konkaR3OrderLinesList = t.getKonkaR3OrderLinesList() ;
		if(null != konkaR3OrderLinesList){
			for(KonkaR3OrderLines orderLines: konkaR3OrderLinesList){
				orderLines.setR3_order_header_id(hid);
				konkaR3OrderLinesDao.insertEntity(orderLines);
			}
		}
		
		return hid ;
	}

	public KonkaR3OrderHeader getKonkaR3OrderHeader(KonkaR3OrderHeader t) {
		return this.konkaR3OrderHeaderDao.selectEntity(t);
	}

	public Long getKonkaR3OrderHeaderCount(KonkaR3OrderHeader t) {
		return this.konkaR3OrderHeaderDao.selectEntityCount(t);
	}

	public List<KonkaR3OrderHeader> getKonkaR3OrderHeaderList(KonkaR3OrderHeader t) {
		return this.konkaR3OrderHeaderDao.selectEntityList(t);
	}

	public int modifyKonkaR3OrderHeader(KonkaR3OrderHeader t) {
		return this.konkaR3OrderHeaderDao.updateEntity(t);
	}

	public int removeKonkaR3OrderHeader(KonkaR3OrderHeader t) {
		return this.konkaR3OrderHeaderDao.deleteEntity(t);
	}

	public List<KonkaR3OrderHeader> getKonkaR3OrderHeaderPaginatedList(KonkaR3OrderHeader t) {
		return this.konkaR3OrderHeaderDao.selectEntityPaginatedList(t);
	}

}
