package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3OrderHeader;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by zhou
 * @date 2013-05-29 14:09:13
 */
public interface KonkaR3OrderHeaderService {
	
	/**
	 * 新增一条订单头信息
	 * @param t
	 * @return
	 */
	Long createKonkaR3OrderHeader(KonkaR3OrderHeader t);

	/**
	 * 修改一条订单头信息
	 * @param t
	 * @return
	 */
	int modifyKonkaR3OrderHeader(KonkaR3OrderHeader t);
	
	/**
	 * 删除一条订单头信息
	 * @param t
	 * @return
	 */
	int removeKonkaR3OrderHeader(KonkaR3OrderHeader t);
	
	/**
	 * 获取一条订单头信息
	 * @param t
	 * @return
	 */
	KonkaR3OrderHeader getKonkaR3OrderHeader(KonkaR3OrderHeader t);
	
	/**
	 * 获取订单头列表
	 * @param t
	 * @return
	 */
	List<KonkaR3OrderHeader> getKonkaR3OrderHeaderList(KonkaR3OrderHeader t);
	
	/**
	 * 获取订单总数
	 * @param t
	 * @return
	 */
	Long getKonkaR3OrderHeaderCount(KonkaR3OrderHeader t);

	/**
	 * 分页获取数据
	 * @param t
	 * @return
	 */
	List<KonkaR3OrderHeader> getKonkaR3OrderHeaderPaginatedList(KonkaR3OrderHeader t);

}