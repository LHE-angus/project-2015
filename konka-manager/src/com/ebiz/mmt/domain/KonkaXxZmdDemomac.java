package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public class KonkaXxZmdDemomac extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long zmd_id;
	
	private String md_name;
	
	private Integer counts;
	
	private Long state;
	
	private Long from_store;
	
	private String from_store_name;
	
	private Date up_date;
	
	private Date down_date;
	
	private String memo;
	
	private Long dist_user_id;
	
	private Date dist_date;
	
	public KonkaXxZmdDemomac() {

	}

	/**
	 * @val 专卖店样机ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 专卖店样机ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 专卖店ID
	 */
	public Long getZmd_id() {
		return zmd_id;
	}
	
	/**
	 * @val 专卖店ID
	 */
	public void setZmd_id(Long zmd_id) {
		this.zmd_id = zmd_id;
	}
	
	/**
	 * @val 型号
	 */
	public String getMd_name() {
		return md_name;
	}
	
	/**
	 * @val 型号
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	
	/**
	 * @val 数量
	 */
	public Integer getCounts() {
		return counts;
	}
	
	/**
	 * @val 数量
	 */
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	
	/**
	 * @val 状态 详见KONKA_XX_BASE_TYPE
	 */
	public Long getState() {
		return state;
	}
	
	/**
	 * @val 状态 详见KONKA_XX_BASE_TYPE
	 */
	public void setState(Long state) {
		this.state = state;
	}
	
	/**
	 * @val 出自仓位 仓位来自分公司的仓位
	 */
	public Long getFrom_store() {
		return from_store;
	}
	
	/**
	 * @val 出自仓位 仓位来自分公司的仓位
	 */
	public void setFrom_store(Long from_store) {
		this.from_store = from_store;
	}
	
	/**
	 * @val 出自仓位名称
	 */
	public String getFrom_store_name() {
		return from_store_name;
	}
	
	/**
	 * @val 出自仓位名称
	 */
	public void setFrom_store_name(String from_store_name) {
		this.from_store_name = from_store_name;
	}
	
	/**
	 * @val 上样时间
	 */
	public Date getUp_date() {
		return up_date;
	}
	
	/**
	 * @val 上样时间
	 */
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
	
	/**
	 * @val 下样时间
	 */
	public Date getDown_date() {
		return down_date;
	}
	
	/**
	 * @val 下样时间
	 */
	public void setDown_date(Date down_date) {
		this.down_date = down_date;
	}
	
	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * @val 分配人（分公司）
	 */
	public Long getDist_user_id() {
		return dist_user_id;
	}
	
	/**
	 * @val 分配人（分公司）
	 */
	public void setDist_user_id(Long dist_user_id) {
		this.dist_user_id = dist_user_id;
	}
	
	/**
	 * @val 分配时间
	 */
	public Date getDist_date() {
		return dist_date;
	}
	
	/**
	 * @val 分配时间
	 */
	public void setDist_date(Date dist_date) {
		this.dist_date = dist_date;
	}
	
}