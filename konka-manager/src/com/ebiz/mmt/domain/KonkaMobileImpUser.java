package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
public class KonkaMobileImpUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String user_name;
	
	private String user_py;
	
	private String jb_name;
	
	private String fgs_name;
	
	private String user_id;
	
	private String tel;
	
	private String email;
	
	private String job;
	
	private String zj_id;
	
	private Date add_date;
	
	private String fgs_sn;
	
	private Long opr_his_id;
	
	public KonkaMobileImpUser() {

	}

	/**
	 * @val 人员姓名
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * @val 人员姓名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 人员姓名拼音
	 */
	public String getUser_py() {
		return user_py;
	}
	
	/**
	 * @val 人员姓名拼音
	 */
	public void setUser_py(String user_py) {
		this.user_py = user_py;
	}
	
	/**
	 * @val 所属经办
	 */
	public String getJb_name() {
		return jb_name;
	}
	
	/**
	 * @val 所属经办
	 */
	public void setJb_name(String jb_name) {
		this.jb_name = jb_name;
	}
	
	/**
	 * @val 所属分公司
	 */
	public String getFgs_name() {
		return fgs_name;
	}
	
	/**
	 * @val 所属分公司
	 */
	public void setFgs_name(String fgs_name) {
		this.fgs_name = fgs_name;
	}
	
	/**
	 * @val 人员岗位ID
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 人员岗位ID
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 联系电话
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * @val 联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * @val 联系邮箱
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @val 联系邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @val 职务
	 */
	public String getJob() {
		return job;
	}
	
	/**
	 * @val 职务
	 */
	public void setJob(String job) {
		this.job = job;
	}
	
	/**
	 * @val 资金平台ID
	 */
	public String getZj_id() {
		return zj_id;
	}
	
	/**
	 * @val 资金平台ID
	 */
	public void setZj_id(String zj_id) {
		this.zj_id = zj_id;
	}
	
	/**
	 * @val 入库时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 入库时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 所属分公司编码
	 */
	public String getFgs_sn() {
		return fgs_sn;
	}
	
	/**
	 * @val 所属分公司编码
	 */
	public void setFgs_sn(String fgs_sn) {
		this.fgs_sn = fgs_sn;
	}
	
	/**
	 * @val 操作记录ID
	 */
	public Long getOpr_his_id() {
		return opr_his_id;
	}
	
	/**
	 * @val 操作记录ID
	 */
	public void setOpr_his_id(Long opr_his_id) {
		this.opr_his_id = opr_his_id;
	}
	
}