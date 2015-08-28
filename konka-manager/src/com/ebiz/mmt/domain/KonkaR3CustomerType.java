package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 19:52:54
 */
public class KonkaR3CustomerType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String customer_type;
	
	private String customer_type_id;
	
	private Integer is_del;
	
	private Date add_date;
	
	private Date del_date;
	
	public KonkaR3CustomerType() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 客户类别
	 */
	public String getCustomer_type() {
		return customer_type;
	}
	
	/**
	 * @val 客户类别
	 */
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	
	/**
	 * @val 客户类别ID
	 */
	public String getCustomer_type_id() {
		return customer_type_id;
	}
	
	/**
	 * @val 客户类别ID
	 */
	public void setCustomer_type_id(String customer_type_id) {
		this.customer_type_id = customer_type_id;
	}
	
	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Date getDel_date() {
		return del_date;
	}
	
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	
}