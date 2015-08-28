package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-16 13:09:31
 */
public class KonkaVipPdManage extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long pd_id;
	
	private String pd_code;
	
	private String pd_name;
	
	private Long fgs_id;
	
	private String fgs_name;
	
	private Long pd_type;
	
	private Date add_date;

	private Long add_user_id;
	
	private String add_user_name;
	
	private String is_del;
	private String is_locked;
	private String is_hidden;
	
	private String pd_desc;
	
	

	private List<KonkaPeAttachments> peAttachmentsList;
	
	
	
	public List<KonkaPeAttachments> getPeAttachmentsList() {
		return peAttachmentsList;
	}

	public void setPeAttachmentsList(List<KonkaPeAttachments> peAttachmentsList) {
		this.peAttachmentsList = peAttachmentsList;
	}

	public KonkaVipPdManage() {

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
	 * @val 商品id
	 */
	public Long getPd_id() {
		return pd_id;
	}
	
	/**
	 * @val 商品id
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}
	
	/**
	 * @val 商品编码
	 */
	public String getPd_code() {
		return pd_code;
	}
	
	/**
	 * @val 商品编码
	 */
	public void setPd_code(String pd_code) {
		this.pd_code = pd_code;
	}
	
	/**
	 * @val 商品名称
	 */
	public String getPd_name() {
		return pd_name;
	}
	
	/**
	 * @val 商品名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	/**
	 * @val 分公司id
	 */
	public Long getFgs_id() {
		return fgs_id;
	}
	
	/**
	 * @val 分公司id
	 */
	public void setFgs_id(Long fgs_id) {
		this.fgs_id = fgs_id;
	}
	
	/**
	 * @val 分公司名称
	 */
	public String getFgs_name() {
		return fgs_name;
	}
	
	/**
	 * @val 分公司名称
	 */
	public void setFgs_name(String fgs_name) {
		this.fgs_name = fgs_name;
	}
	
	/**
	 * @val 所属分类(热销,新品等)
	 */
	public Long getPd_type() {
		return pd_type;
	}
	
	/**
	 * @val 所属分类(热销,新品等)
	 */
	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
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
	 * @val 添加人编码
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人编码
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
	 * @val 是否删除
	 */
	public String getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public String getPd_desc() {
		return pd_desc;
	}

	public void setPd_desc(String pd_desc) {
		this.pd_desc = pd_desc;
	}

	public String getIs_locked() {
		return is_locked;
	}

	public void setIs_locked(String is_locked) {
		this.is_locked = is_locked;
	}

	public String getIs_hidden() {
		return is_hidden;
	}

	public void setIs_hidden(String is_hidden) {
		this.is_hidden = is_hidden;
	}
	
}