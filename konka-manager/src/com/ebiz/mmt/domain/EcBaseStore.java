package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:24
 */
public class EcBaseStore extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long store_id;

	private Long dept_id;

	private Integer store_type;

	private String store_sn;

	private String store_name;

	private String store_addr;

	private String remarks;

	private Integer is_del;

	private Date add_date;

	private List<EcStoreArea> ecStoreAreaList;

	private Integer own_sys;// 所属系统 1工卡 2触网

	private Integer plat_sys;

	public EcBaseStore() {

	}

	public Integer getPlat_sys() {
		return plat_sys;
	}

	public void setPlat_sys(Integer platSys) {
		plat_sys = platSys;
	}

	/**
	 * @val 仓库ID
	 */
	public Long getStore_id() {
		return store_id;
	}

	/**
	 * @val 仓库ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	/**
	 * @val 分公司ID：如果是分公司仓库则不为空，存分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 分公司ID：如果是分公司仓库则不为空，存分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 仓库类型：0-全国总仓，1-区域大仓，2-分公司仓
	 */
	public Integer getStore_type() {
		return store_type;
	}

	/**
	 * @val 仓库类型：0-全国总仓，1-区域大仓，2-分公司仓
	 */
	public void setStore_type(Integer store_type) {
		this.store_type = store_type;
	}

	/**
	 * @val 仓库编号
	 */
	public String getStore_sn() {
		return store_sn;
	}

	/**
	 * @val 仓库编号
	 */
	public void setStore_sn(String store_sn) {
		this.store_sn = store_sn;
	}

	/**
	 * @val 仓库名称
	 */
	public String getStore_name() {
		return store_name;
	}

	/**
	 * @val 仓库名称
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	/**
	 * @val 仓库地址
	 */
	public String getStore_addr() {
		return store_addr;
	}

	/**
	 * @val 仓库地址
	 */
	public void setStore_addr(String store_addr) {
		this.store_addr = store_addr;
	}

	/**
	 * @val 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @val 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @val 是否删除:0-未删除 1-已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除:0-未删除 1-已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
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

	public List<EcStoreArea> getEcStoreAreaList() {
		return ecStoreAreaList;
	}

	public void setEcStoreAreaList(List<EcStoreArea> ecStoreAreaList) {
		this.ecStoreAreaList = ecStoreAreaList;
	}

	public Integer getOwn_sys() {
		return own_sys;
	}

	public void setOwn_sys(Integer ownSys) {
		own_sys = ownSys;
	}

}