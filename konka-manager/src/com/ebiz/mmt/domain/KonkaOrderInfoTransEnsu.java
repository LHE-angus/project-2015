package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-24 17:30:53
 */
public class KonkaOrderInfoTransEnsu extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long ensu_id;
	
	private Integer trans_ensu_status;
	
	private Long trans_ensu_num;
	
	private Date trans_ensu_date;
	
	private String trans_ensu_user;
	
	private String trans_ensu_desc;
	
	private Date add_date;
	
	private Integer is_del;
	
	public KonkaOrderInfoTransEnsu() {

	}

	/**
	 * @val 签收单ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 签收单ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val ENSU_ID
	 */
	public Long getEnsu_id() {
		return ensu_id;
	}
	
	/**
	 * @val ENSU_ID
	 */
	public void setEnsu_id(Long ensu_id) {
		this.ensu_id = ensu_id;
	}
	
	/**
	 * @val 签收状态 0：未签收    1：部分签收    2：确认收货    3：全部拒收    
	 */
	public Integer getTrans_ensu_status() {
		return trans_ensu_status;
	}
	
	/**
	 * @val 签收状态 0：未签收    1：部分签收    2：确认收货    3：全部拒收    
	 */
	public void setTrans_ensu_status(Integer trans_ensu_status) {
		this.trans_ensu_status = trans_ensu_status;
	}
	
	/**
	 * @val 本次签收数量
	 */
	public Long getTrans_ensu_num() {
		return trans_ensu_num;
	}
	
	/**
	 * @val 本次签收数量
	 */
	public void setTrans_ensu_num(Long trans_ensu_num) {
		this.trans_ensu_num = trans_ensu_num;
	}
	
	/**
	 * @val 最后签收时间
	 */
	public Date getTrans_ensu_date() {
		return trans_ensu_date;
	}
	
	/**
	 * @val 最后签收时间
	 */
	public void setTrans_ensu_date(Date trans_ensu_date) {
		this.trans_ensu_date = trans_ensu_date;
	}
	
	/**
	 * @val 确认人
	 */
	public String getTrans_ensu_user() {
		return trans_ensu_user;
	}
	
	/**
	 * @val 确认人
	 */
	public void setTrans_ensu_user(String trans_ensu_user) {
		this.trans_ensu_user = trans_ensu_user;
	}
	
	/**
	 * @val 签收说明
	 */
	public String getTrans_ensu_desc() {
		return trans_ensu_desc;
	}
	
	/**
	 * @val 签收说明
	 */
	public void setTrans_ensu_desc(String trans_ensu_desc) {
		this.trans_ensu_desc = trans_ensu_desc;
	}
	
	/**
	 * @val 操作时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
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
	
}