package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-23 16:05:40
 */
public class PeProdUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long prod_entp_id;

	private Integer user_type;

	private String user_name;

	private String pass_word;

	private String real_name;

	private String link_tel;

	private String link_addr;

	private String email;

	private Date add_date;

	private Long add_user_id;

	private Integer is_del;

	private EntpProd entpProd;

	private String department;

	private String chargeman;

	private Integer is_chapter;

	private Long dept_id;

	private Long par_dept_id;

	private Long leader_id;

	private Long position_id;

	private Integer sex;

	private Date birthday;

	private Long p_index;

	private String link_post;

	private String link_phone;

	private String link_qq;

	private String link_msn;

	private Long add_e_user_id;

	private String role_id;

	private String job_id;

	private Long zmd_id;

	private Long mobile_user_type; // 10 领导（管理版），20 业务员（客户版）， 30 促销员（导购版）

	private String r3_job_id;

	private List<PeRoleUser> peRoleUserList;

	private KonkaDept konkaDept;

	private List<Attachment> attachmentList;// 附件

	private Date modify_date;

	private Long modify_user_id;

	/**
	 * 登录次数
	 */
	private Long login_count;

	/**
	 * 最后登录IP
	 */
	private String last_login_ip;

	/**
	 * 最后登录时间
	 */
	private Date last_login_time;

	/**
	 * 排序值
	 */
	private Long order_value;

	/**
	 * 是否是新兴渠道用户 0-否 1-是
	 */
	private Long is_xx_user;

	/**
	 * 客户ID
	 */
	private Long cust_id;

	/**
	 * 公共密码
	 */
	private String public_pwd;

	private Integer is_show;// 客户端显示图片

	public PeProdUser() {

	}

	public Integer getIs_show() {
		return is_show;
	}

	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}

	public Long getLogin_count() {
		return login_count;
	}

	public void setLogin_count(Long login_count) {
		this.login_count = login_count;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 生产企业ID:STD_ENTP_PROD.ID
	 */
	public Long getProd_entp_id() {
		return prod_entp_id;
	}

	/**
	 * @val 生产企业ID:STD_ENTP_PROD.ID
	 */
	public void setProd_entp_id(Long prod_entp_id) {
		this.prod_entp_id = prod_entp_id;
	}

	/**
	 * @val 用户类型：0-企业超级管理员，1-企业管理员
	 */
	public Integer getUser_type() {
		return user_type;
	}

	/**
	 * @val 用户类型：0-企业超级管理员，1-企业管理员
	 */
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	/**
	 * @val 用户名
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @val 用户名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @val 密码
	 */
	public String getPass_word() {
		return pass_word;
	}

	/**
	 * @val 密码
	 */
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	/**
	 * @val 用户真实姓名
	 */
	public String getReal_name() {
		return real_name;
	}

	/**
	 * @val 用户真实姓名
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	/**
	 * @val 联系电话
	 */
	public String getLink_tel() {
		return link_tel;
	}

	/**
	 * @val 联系电话
	 */
	public void setLink_tel(String link_tel) {
		this.link_tel = link_tel;
	}

	/**
	 * @val 联系地址
	 */
	public String getLink_addr() {
		return link_addr;
	}

	/**
	 * @val 联系地址
	 */
	public void setLink_addr(String link_addr) {
		this.link_addr = link_addr;
	}

	/**
	 * @val 联系Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @val 联系Email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @val 是否删除
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public EntpProd getEntpProd() {
		return entpProd;
	}

	public void setEntpProd(EntpProd entpProd) {
		this.entpProd = entpProd;
	}

	/**
	 * @val 所属部门
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @val 所属部门
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @val 负责人
	 */
	public String getChargeman() {
		return chargeman;
	}

	/**
	 * @val 负责人
	 */
	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}

	public Integer getIs_chapter() {
		return is_chapter;
	}

	public void setIs_chapter(Integer is_chapter) {
		this.is_chapter = is_chapter;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long deptId) {
		dept_id = deptId;
	}

	public Long getPar_dept_id() {
		return par_dept_id;
	}

	public void setPar_dept_id(Long parDeptId) {
		par_dept_id = parDeptId;
	}

	public Long getLeader_id() {
		return leader_id;
	}

	public void setLeader_id(Long leaderId) {
		leader_id = leaderId;
	}

	public Long getPosition_id() {
		return position_id;
	}

	public void setPosition_id(Long positionId) {
		position_id = positionId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getP_index() {
		return p_index;
	}

	public void setP_index(Long pIndex) {
		p_index = pIndex;
	}

	public String getLink_post() {
		return link_post;
	}

	public void setLink_post(String linkPost) {
		link_post = linkPost;
	}

	public String getLink_phone() {
		return link_phone;
	}

	public void setLink_phone(String linkPhone) {
		link_phone = linkPhone;
	}

	public String getLink_qq() {
		return link_qq;
	}

	public void setLink_qq(String linkQq) {
		link_qq = linkQq;
	}

	public String getLink_msn() {
		return link_msn;
	}

	public void setLink_msn(String linkMsn) {
		link_msn = linkMsn;
	}

	public Long getAdd_e_user_id() {
		return add_e_user_id;
	}

	public void setAdd_e_user_id(Long addEUserId) {
		add_e_user_id = addEUserId;
	}

	public Long getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}

	public Long getIs_xx_user() {
		return is_xx_user;
	}

	public void setIs_xx_user(Long is_xx_user) {
		this.is_xx_user = is_xx_user;
	}

	/**
	 * @val 岗位ID
	 */
	public String getJob_id() {
		return job_id;
	}

	/**
	 * @val 岗位ID
	 */
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long custId) {
		cust_id = custId;
	}

	public Long getZmd_id() {
		return zmd_id;
	}

	public void setZmd_id(Long zmd_id) {
		this.zmd_id = zmd_id;
	}

	public String getPublic_pwd() {
		return public_pwd;
	}

	public void setPublic_pwd(String publicPwd) {
		public_pwd = publicPwd;
	}

	public KonkaDept getKonkaDept() {
		return konkaDept;
	}

	public void setKonkaDept(KonkaDept konkaDept) {
		this.konkaDept = konkaDept;
	}

	public List<PeRoleUser> getPeRoleUserList() {
		return peRoleUserList;
	}

	public void setPeRoleUserList(List<PeRoleUser> peRoleUserList) {
		this.peRoleUserList = peRoleUserList;
	}

	public Long getMobile_user_type() {
		return mobile_user_type;
	}

	public void setMobile_user_type(Long mobile_user_type) {
		this.mobile_user_type = mobile_user_type;
	}

	/**
	 * r3岗位编号
	 * 
	 * @return
	 */
	public String getR3_job_id() {
		return r3_job_id;
	}

	public void setR3_job_id(String r3JobId) {
		r3_job_id = r3JobId;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Long getModify_user_id() {
		return modify_user_id;
	}

	public void setModify_user_id(Long modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

}