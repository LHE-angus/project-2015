package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-29 11:19:39
 */
public class KonkaSpActivityAddr extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String addr_index;
	
	private String addr;
	
	private String addr_memo;
	
	private String addr_header;
	
	private String r3_code;
	
	private String customer_name;
	
	private Integer state;
	
	private Long add_user_id;
	
	private String add_user_name;
	
	private String update_user_name;
	
	private Date add_date;
	
	private Date update_date;
	
	
	private Long store_id;
	
	private String store_name;
	
	
	private List<Attachment> attachmentList;
	
	public KonkaSpActivityAddr() {

	}

	/**
	 * @val 主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 预约点编码
	 */
	public String getAddr_index() {
		return addr_index;
	}
	
	/**
	 * @val 预约点编码
	 */
	public void setAddr_index(String addr_index) {
		this.addr_index = addr_index;
	}
	
	/**
	 * @val 预约点地址
	 */
	public String getAddr() {
		return addr;
	}
	
	/**
	 * @val 预约点地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	/**
	 * @val 预约点描述
	 */
	public String getAddr_memo() {
		return addr_memo;
	}
	
	/**
	 * @val 预约点描述
	 */
	public void setAddr_memo(String addr_memo) {
		this.addr_memo = addr_memo;
	}
	
	/**
	 * @val 预约点负责人
	 */
	public String getAddr_header() {
		return addr_header;
	}
	
	/**
	 * @val 预约点负责人
	 */
	public void setAddr_header(String addr_header) {
		this.addr_header = addr_header;
	}
	
	/**
	 * @val 所属客户编码
	 */
	public String getR3_code() {
		return r3_code;
	}
	
	/**
	 * @val 所属客户编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	
	/**
	 * @val 所属客户名称
	 */
	public String getCustomer_name() {
		return customer_name;
	}
	
	/**
	 * @val 所属客户名称
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	/**
	 * @val 启用状态
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @val 启用状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * @val 添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 添加人姓名
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人姓名
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	/**
	 * @val 修改人姓名
	 */
	public String getUpdate_user_name() {
		return update_user_name;
	}
	
	/**
	 * @val 修改人姓名
	 */
	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 修改时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 修改时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	/**
	 * @val 门店ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	/**
	 * @val 门店ID
	 */
	public Long getStore_id() {
		return store_id;
	}
	/**
	 * @val 门店名称
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	/**
	 * @val 门店名称
	 */
	public String getStore_name() {
		return store_name;
	}

	
	
}