package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileSailDataBillDao;
import com.ebiz.mmt.domain.KonkaMobileSailDataBill;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-22 10:56:34
 */
@Service
public class KonkaMobileSailDataBillDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileSailDataBill> implements
		KonkaMobileSailDataBillDao {

	@Override
	public List<KonkaMobileSailDataBill> selectKonkaMobileSailDataBillAndAttachmentList(KonkaMobileSailDataBill t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataBillAndAttachmentList", t);
	}

	@Override
	public List<KonkaMobileSailDataBill> selectKonkaMobileSailDataBillAndAttachmentBySailList(KonkaMobileSailDataBill t) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileSailDataBillAndAttachmentBySailList", t);
	}

}
