package com.ebiz.mmt.web.struts.inter.form;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiao,GuoJian
 * @since 2014-09-23
 * @see 订单查询页面
 */
public class KonkaOrderInfoSearchForm extends BaseInterForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	List<KonkaOrderInfoSearch> konkaOrderInfoSearchList = new ArrayList<KonkaOrderInfoSearch>();

	public List<KonkaOrderInfoSearch> getKonkaOrderInfoSearchList() {
		return konkaOrderInfoSearchList;
	}

	public void setKonkaOrderInfoSearchList(
			List<KonkaOrderInfoSearch> konkaOrderInfoSearchList) {
		this.konkaOrderInfoSearchList = konkaOrderInfoSearchList;
	}
}
