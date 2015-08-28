package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcShoppingCartDao;
import com.ebiz.mmt.domain.EcShoppingCart;
import com.ebiz.mmt.service.EcShoppingCartService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcShoppingCartServiceImpl implements EcShoppingCartService {

	@Resource
	private EcShoppingCartDao ecShoppingCartDao;
	

	public Long createEcShoppingCart(EcShoppingCart t) {
		return this.ecShoppingCartDao.insertEntity(t);
	}

	public EcShoppingCart getEcShoppingCart(EcShoppingCart t) {
		return this.ecShoppingCartDao.selectEntity(t);
	}

	public Long getEcShoppingCartCount(EcShoppingCart t) {
		return this.ecShoppingCartDao.selectEntityCount(t);
	}

	public List<EcShoppingCart> getEcShoppingCartList(EcShoppingCart t) {
		return this.ecShoppingCartDao.selectEntityList(t);
	}

	public int modifyEcShoppingCart(EcShoppingCart t) {
		return this.ecShoppingCartDao.updateEntity(t);
	}

	public int removeEcShoppingCart(EcShoppingCart t) {
		return this.ecShoppingCartDao.deleteEntity(t);
	}

	public List<EcShoppingCart> getEcShoppingCartPaginatedList(EcShoppingCart t) {
		return this.ecShoppingCartDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-14
	 */
	public int modifyOrCreateEcShoppingCart(List<EcShoppingCart> list) {
		for (EcShoppingCart ecShoppingCart : list) {
			if(null == ecShoppingCart.getId()){
				this.ecShoppingCartDao.insertEntity(ecShoppingCart);
			} else {
				this.ecShoppingCartDao.updateEntity(ecShoppingCart);
			}
		}
		return list.size();
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-15
	 */
	public int removeEcShoppingCartWithGoodsIdAndUserId(EcShoppingCart t) {
		return this.ecShoppingCartDao.deleteEcShoppingCartWithGoodsIdAndUserId(t);
	}

}
