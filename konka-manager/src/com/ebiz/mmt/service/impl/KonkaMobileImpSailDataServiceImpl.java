package com.ebiz.mmt.service.impl;

import com.ebiz.mmt.dao.KonkaMobileImpSailDataDao;
import com.ebiz.mmt.dao.KonkaMobileSailDataDao;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.KonkaMobileImpSailData;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.service.KonkaMobileImpSailDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-08 14:56:22
 */
@Service
public class KonkaMobileImpSailDataServiceImpl implements KonkaMobileImpSailDataService {

	@Resource
	private KonkaMobileImpSailDataDao konkaMobileImpSailDataDao;

	@Resource
	private KonkaMobileSailDataDao konkaMobileSailDataDao;
	
	@Resource
	private Facade facade;

	public Long createKonkaMobileImpSailData(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.insertEntity(t);
	}

	public KonkaMobileImpSailData getKonkaMobileImpSailData(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.selectEntity(t);
	}

	public Long getKonkaMobileImpSailDataCount(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.selectEntityCount(t);
	}

	public List<KonkaMobileImpSailData> getKonkaMobileImpSailDataList(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.selectEntityList(t);
	}

	public int modifyKonkaMobileImpSailData(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.updateEntity(t);
	}

	public int removeKonkaMobileImpSailData(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.deleteEntity(t);
	}

	public List<KonkaMobileImpSailData> getKonkaMobileImpSailDataPaginatedList(KonkaMobileImpSailData t) {
		return this.konkaMobileImpSailDataDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-08
	 */
	public Long createKonkaMobileImpSailDataToView(List<KonkaMobileImpSailData> t) {
		Long c = 0L;
		// 删除已有数据
		KonkaMobileImpSailData k = new KonkaMobileImpSailData();
		this.konkaMobileImpSailDataDao.deleteEntity(k);

		// 导入新数据
		for (KonkaMobileImpSailData temp : t) {
			this.konkaMobileImpSailDataDao.insertEntity(temp);
		}

		KonkaMobileSailData entity = new KonkaMobileSailData();
		List<KonkaMobileSailData> entityList = this.konkaMobileSailDataDao.selectKonkaMobileSailDataToExlList(entity);

		if (entityList.size() > 0) {
			for(KonkaMobileSailData kd :entityList){
				c++;
				
				kd.setId(null);
				kd.setAudit_state(0);//塞入状态的字段
				this.konkaMobileSailDataDao.insertEntity(kd);
				
				try {


				Long store_id= kd.getDept_id();
				Long pd_id =kd.getModel_id();
				String md_name=kd.getModel_name();
				BigDecimal single_price=kd.getSingle_price();
				String r3_code=kd.getCustomer_r3_code();
				Long cxy_id=kd.getReport_id();
				Long num=kd.getNum();
				// 拿到进货仓库
				JBaseStore jBaseStore = new JBaseStore();
				jBaseStore.getMap().put("sale_r3_code", r3_code);
				jBaseStore.getMap().put("r3_code", r3_code);
				jBaseStore.setIs_del(0);
				List<JBaseStore> storeList = facade.getJBaseStoreService().getJBaseStoreForR3List(jBaseStore);
				if (null != storeList && storeList.size() > 0) {
					jBaseStore = storeList.get(0);
				}
				if(num>0){

					facade
					.getJxcFifoStocksService()
					.stock_out(store_id, pd_id, md_name, num,
							single_price, r3_code,jBaseStore.getStore_id(), 530, cxy_id);

				}else{

						facade
						.getJxcFifoStocksService()
						.stock_in(jBaseStore.getStore_id(), pd_id, single_price, num.intValue()*-1,
								new Date(), 60);

				}

				}catch (Exception e){
					e.printStackTrace();
				}
			}

		}

		return c;

	}

}
