package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public class EcUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

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

	private String department;

	private String chargeman;// 审核人姓名

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

	private Long login_count;

	private String last_login_ip;

	private String bank_account;

	private String social_number;

	private Date last_login_time;

	private Integer order_value;

	private Integer is_xx_user;

	private Long c_id;// 客户端登陆用户的id

	private String payroll_card;

	private String bank_name;

	private String bank_account_name;

	private String card_no;

	private String pay_pwd;

	private Integer is_own;// 是否是本人银行账号

	private Long cust_id;// R3客户ID

	private Date audit_date;// 审核时间

	private Integer is_epp_fgs;// 是否是分公司EPP 0否1是

	private Integer plat_sys;// 所属总部.分公司

	private String link_user_name;// 关联上级会员

	private Integer is_allowed;// 是否允许发展下级会员

	private String user_no;// 会员编码
	
	private String link_code;// 关联码

	public String getLink_code() {
		return link_code;
	}

	public void setLink_code(String link_code) {
		this.link_code = link_code;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public Integer getIs_own() {
		return is_own;
	}

	public void setIs_own(Integer is_own) {
		this.is_own = is_own;
	}

	private EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel();

	public EcBaseCardLevel getEcBaseCardLevel() {
		return ecBaseCardLevel;
	}

	public void setEcBaseCardLevel(EcBaseCardLevel ecBaseCardLevel) {
		this.ecBaseCardLevel = ecBaseCardLevel;
	}

	public String getPay_pwd() {
		return pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}

	private Integer is_act;// 是否激活1否0是

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account_name() {
		return bank_account_name;
	}

	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}

	public String getPayroll_card() {
		return payroll_card;
	}

	public void setPayroll_card(String payroll_card) {
		this.payroll_card = payroll_card;
	}

	public EcUser() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 用户类型：0-企业管理员，1-企业用户，2-康佳客户，3-门店用户
	 */
	public Integer getUser_type() {
		return user_type;
	}

	/**
	 * @val 用户类型：0-企业管理员，1-企业用户，2-康佳客户，3-门店用户
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
	 * @val 添加人：USER_INFO.ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 添加人：USER_INFO.ID
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

	/**
	 * @val 所属部门（填写）
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @val 所属部门（填写）
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @val 负责人（填写）
	 */
	public String getChargeman() {
		return chargeman;
	}

	/**
	 * @val 负责人（填写）
	 */
	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}

	/**
	 * @val 合同盖章：1-是，0-否
	 */
	public Integer getIs_chapter() {
		return is_chapter;
	}

	/**
	 * @val 合同盖章：1-是，0-否
	 */
	public void setIs_chapter(Integer is_chapter) {
		this.is_chapter = is_chapter;
	}

	/**
	 * @val 所属部门
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 所属部门
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 父部门
	 */
	public Long getPar_dept_id() {
		return par_dept_id;
	}

	/**
	 * @val 父部门
	 */
	public void setPar_dept_id(Long par_dept_id) {
		this.par_dept_id = par_dept_id;
	}

	/**
	 * @val 部门负责人：PE_PROD_USER.ID
	 */
	public Long getLeader_id() {
		return leader_id;
	}

	/**
	 * @val 部门负责人：PE_PROD_USER.ID
	 */
	public void setLeader_id(Long leader_id) {
		this.leader_id = leader_id;
	}

	/**
	 * @val 职位：默认为0，表示"业务员"
	 */
	public Long getPosition_id() {
		return position_id;
	}

	/**
	 * @val 职位：默认为0，表示"业务员"
	 */
	public void setPosition_id(Long position_id) {
		this.position_id = position_id;
	}

	/**
	 * @val 性别：0-男，1-女，2-保密
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @val 性别：0-男，1-女，2-保密
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @val 出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @val 出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @val 居住地（含乡镇）
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 居住地（含乡镇）
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 邮编
	 */
	public String getLink_post() {
		return link_post;
	}

	/**
	 * @val 邮编
	 */
	public void setLink_post(String link_post) {
		this.link_post = link_post;
	}

	/**
	 * @val 手机
	 */
	public String getLink_phone() {
		return link_phone;
	}

	/**
	 * @val 手机
	 */
	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}

	/**
	 * @val QQ
	 */
	public String getLink_qq() {
		return link_qq;
	}

	/**
	 * @val QQ
	 */
	public void setLink_qq(String link_qq) {
		this.link_qq = link_qq;
	}

	/**
	 * @val MSN
	 */
	public String getLink_msn() {
		return link_msn;
	}

	/**
	 * @val MSN
	 */
	public void setLink_msn(String link_msn) {
		this.link_msn = link_msn;
	}

	/**
	 * @val 企业添加人：PE_PROD_USER.ID
	 */
	public Long getAdd_e_user_id() {
		return add_e_user_id;
	}

	/**
	 * @val 企业添加人：PE_PROD_USER.ID
	 */
	public void setAdd_e_user_id(Long add_e_user_id) {
		this.add_e_user_id = add_e_user_id;
	}

	/**
	 * @val 登录次数
	 */
	public Long getLogin_count() {
		return login_count;
	}

	/**
	 * @val 登录次数
	 */
	public void setLogin_count(Long login_count) {
		this.login_count = login_count;
	}

	/**
	 * @val 最后登录IP
	 */
	public String getLast_login_ip() {
		return last_login_ip;
	}

	/**
	 * @val 最后登录IP
	 */
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	/**
	 * @val 最后登录时间
	 */
	public Date getLast_login_time() {
		return last_login_time;
	}

	/**
	 * @val 最后登录时间
	 */
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	/**
	 * @val 排序值
	 */
	public Integer getOrder_value() {
		return order_value;
	}

	/**
	 * @val 排序值
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	/**
	 * @val 0-原系统用户 1-新兴渠道用户 2-完美终端用户
	 */
	public Integer getIs_xx_user() {
		return is_xx_user;
	}

	/**
	 * @val 0-原系统用户 1-新兴渠道用户 2-完美终端用户
	 */
	public void setIs_xx_user(Integer is_xx_user) {
		this.is_xx_user = is_xx_user;
	}

	/**
	 * @val 客户ID
	 */
	public Long getC_id() {
		return c_id;
	}

	/**
	 * @val 客户ID
	 */
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getSocial_number() {
		return social_number;
	}

	public void setSocial_number(String social_number) {
		this.social_number = social_number;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String cardNo) {
		card_no = cardNo;
	}

	public Integer getIs_act() {
		return is_act;
	}

	public void setIs_act(Integer isAct) {
		is_act = isAct;
	}

	public Date getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(Date auditDate) {
		audit_date = auditDate;
	}

	public Integer getIs_epp_fgs() {
		return is_epp_fgs;
	}

	public void setIs_epp_fgs(Integer isEppFgs) {
		is_epp_fgs = isEppFgs;
	}

	public Integer getPlat_sys() {
		return plat_sys;
	}

	public void setPlat_sys(Integer platSys) {
		plat_sys = platSys;
	}

	public String getLink_user_name() {
		return link_user_name;
	}

	public void setLink_user_name(String linkUserName) {
		link_user_name = linkUserName;
	}

	public Integer getIs_allowed() {
		return is_allowed;
	}

	public void setIs_allowed(Integer isAllowed) {
		is_allowed = isAllowed;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String userNo) {
		user_no = userNo;
	}

}