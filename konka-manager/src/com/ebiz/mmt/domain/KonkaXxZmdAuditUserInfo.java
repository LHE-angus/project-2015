package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-14 16:04:02
 */
public class KonkaXxZmdAuditUserInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long zmd_user_id;

	private Long dept_id;

	private String user_name;

	private Integer sex;

	private Date birthday;

	private String edu_bg;

	private Integer marriage;

	private String resources;

	private String last_unit;

	private String last_post;

	private Integer is_stores;

	private String sell_work_year;

	private String tv_wor_year;

	private String stores_addr;

	private String com_addr;

	private Long post_code;

	private String email;

	private String tel;

	private Date reg_date;

	private BigDecimal reg_money;

	private String business_scope;

	private String reg_num;

	private String business_brand;

	private String ope_sto_addr;

	private Integer is_r3;

	private Integer is_e_subsidy;

	private String in_stores_addr;

	private String target;

	private String eva_performance;

	private String audit_opinion;

	private Date add_date;

	private Date audit_date;

	private Long audit_status;

	private String mainly_resume;

	private Long audit_id;

	private String audit_name;

	private Long add_user_id;

	private String add_user_name;

	private String zmd_sn;
	
	private Integer is_del;

	private KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis;
	
	private List<KonkaPeAttachments> attachmentList;

	private List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList;

	public KonkaXxZmdAuditUserInfo() {

	}

	/**
	 * @val 客户ID
	 */
	public Long getZmd_user_id() {
		return zmd_user_id;
	}

	/**
	 * @val 客户ID
	 */
	public void setZmd_user_id(Long zmd_user_id) {
		this.zmd_user_id = zmd_user_id;
	}

	/**
	 * @val 分公司
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 分公司
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 姓名
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @val 姓名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @val 性别 0-保密，1-男，2-女，3-不详
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @val 性别 0-保密，1-男，2-女，3-不详
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @val 出生年月
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @val 出生年月
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @val 教育背景
	 */
	public String getEdu_bg() {
		return edu_bg;
	}

	/**
	 * @val 教育背景
	 */
	public void setEdu_bg(String edu_bg) {
		this.edu_bg = edu_bg;
	}

	/**
	 * @val 婚姻状况 0-未婚，1-已婚
	 */
	public Integer getMarriage() {
		return marriage;
	}

	/**
	 * @val 婚姻状况 0-未婚，1-已婚
	 */
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	/**
	 * @val 投入的资金、资源
	 */
	public String getResources() {
		return resources;
	}

	/**
	 * @val 投入的资金、资源
	 */
	public void setResources(String resources) {
		this.resources = resources;
	}

	/**
	 * @val 上一个工作单位
	 */
	public String getLast_unit() {
		return last_unit;
	}

	/**
	 * @val 上一个工作单位
	 */
	public void setLast_unit(String last_unit) {
		this.last_unit = last_unit;
	}

	/**
	 * @val 上一个工作职务
	 */
	public String getLast_post() {
		return last_post;
	}

	/**
	 * @val 上一个工作职务
	 */
	public void setLast_post(String last_post) {
		this.last_post = last_post;
	}

	/**
	 * @val 是否有自有门店 0-否，1-是
	 */
	public Integer getIs_stores() {
		return is_stores;
	}

	/**
	 * @val 是否有自有门店 0-否，1-是
	 */
	public void setIs_stores(Integer is_stores) {
		this.is_stores = is_stores;
	}

	/**
	 * @val 从事销售行业年限
	 */
	public String getSell_work_year() {
		return sell_work_year;
	}

	/**
	 * @val 从事销售行业年限
	 */
	public void setSell_work_year(String sell_work_year) {
		this.sell_work_year = sell_work_year;
	}

	/**
	 * @val 彩电从业年限
	 */
	public String getTv_wor_year() {
		return tv_wor_year;
	}

	/**
	 * @val 彩电从业年限
	 */
	public void setTv_wor_year(String tv_wor_year) {
		this.tv_wor_year = tv_wor_year;
	}

	/**
	 * @val 自有门店地址
	 */
	public String getStores_addr() {
		return stores_addr;
	}

	/**
	 * @val 自有门店地址
	 */
	public void setStores_addr(String stores_addr) {
		this.stores_addr = stores_addr;
	}

	/**
	 * @val 通讯地址
	 */
	public String getCom_addr() {
		return com_addr;
	}

	/**
	 * @val 通讯地址
	 */
	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}

	/**
	 * @val 邮政编码
	 */
	public Long getPost_code() {
		return post_code;
	}

	/**
	 * @val 邮政编码
	 */
	public void setPost_code(Long post_code) {
		this.post_code = post_code;
	}

	/**
	 * @val 电子邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @val 电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @val 工商注册时间
	 */
	public Date getReg_date() {
		return reg_date;
	}

	/**
	 * @val 工商注册时间
	 */
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	/**
	 * @val 注册资本（万元）
	 */
	public BigDecimal getReg_money() {
		return reg_money;
	}

	/**
	 * @val 注册资本（万元）
	 */
	public void setReg_money(BigDecimal reg_money) {
		this.reg_money = reg_money;
	}

	/**
	 * @val 营业执照经营范围
	 */
	public String getBusiness_scope() {
		return business_scope;
	}

	/**
	 * @val 营业执照经营范围
	 */
	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	/**
	 * @val 营业执照注册号
	 */
	public String getReg_num() {
		return reg_num;
	}

	/**
	 * @val 营业执照注册号
	 */
	public void setReg_num(String reg_num) {
		this.reg_num = reg_num;
	}

	/**
	 * @val 目前正在经营品牌
	 */
	public String getBusiness_brand() {
		return business_brand;
	}

	/**
	 * @val 目前正在经营品牌
	 */
	public void setBusiness_brand(String business_brand) {
		this.business_brand = business_brand;
	}

	/**
	 * @val 正在经营门店的地址
	 */
	public String getOpe_sto_addr() {
		return ope_sto_addr;
	}

	/**
	 * @val 正在经营门店的地址
	 */
	public void setOpe_sto_addr(String ope_sto_addr) {
		this.ope_sto_addr = ope_sto_addr;
	}

	/**
	 * @val 是否已有R3编码 0-否，1-是
	 */
	public Integer getIs_r3() {
		return is_r3;
	}

	/**
	 * @val 是否已有R3编码 0-否，1-是
	 */
	public void setIs_r3(Integer is_r3) {
		this.is_r3 = is_r3;
	}

	/**
	 * @val 能否参加节能补贴 0-否，1-是
	 */
	public Integer getIs_e_subsidy() {
		return is_e_subsidy;
	}

	/**
	 * @val 能否参加节能补贴 0-否，1-是
	 */
	public void setIs_e_subsidy(Integer is_e_subsidy) {
		this.is_e_subsidy = is_e_subsidy;
	}

	/**
	 * @val 意向门店地址
	 */
	public String getIn_stores_addr() {
		return in_stores_addr;
	}

	/**
	 * @val 意向门店地址
	 */
	public void setIn_stores_addr(String in_stores_addr) {
		this.in_stores_addr = in_stores_addr;
	}

	/**
	 * @val 个人目标与事业目标
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @val 个人目标与事业目标
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @val 自我评价及工作业绩
	 */
	public String getEva_performance() {
		return eva_performance;
	}

	/**
	 * @val 自我评价及工作业绩
	 */
	public void setEva_performance(String eva_performance) {
		this.eva_performance = eva_performance;
	}

	/**
	 * @val 总部审批意见
	 */
	public String getAudit_opinion() {
		return audit_opinion;
	}

	/**
	 * @val 总部审批意见
	 */
	public void setAudit_opinion(String audit_opinion) {
		this.audit_opinion = audit_opinion;
	}

	/**
	 * @val 提交日期
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 提交日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 审核日期
	 */
	public Date getAudit_date() {
		return audit_date;
	}

	/**
	 * @val 审核日期
	 */
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}

	/**
	 * @val 审核状态
	 */
	public Long getAudit_status() {
		return audit_status;
	}

	/**
	 * @val 审核状态
	 */
	public void setAudit_status(Long audit_status) {
		this.audit_status = audit_status;
	}

	/**
	 * @val 主要简历
	 */
	public String getMainly_resume() {
		return mainly_resume;
	}

	/**
	 * @val 主要简历
	 */
	public void setMainly_resume(String mainly_resume) {
		this.mainly_resume = mainly_resume;
	}

	/**
	 * @val 审批人ID
	 */
	public Long getAudit_id() {
		return audit_id;
	}

	/**
	 * @val 审批人ID
	 */
	public void setAudit_id(Long audit_id) {
		this.audit_id = audit_id;
	}

	/**
	 * @val 审批人
	 */
	public String getAudit_name() {
		return audit_name;
	}

	/**
	 * @val 审批人
	 */
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
	}

	/**
	 * @val 申请人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 申请人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	/**
	 * @val 申请人
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}

	/**
	 * @val 申请人
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}

	/**
	 * @val 专卖店编号
	 */
	public String getZmd_sn() {
		return zmd_sn;
	}

	/**
	 * @val 专卖店编号
	 */
	public void setZmd_sn(String zmd_sn) {
		this.zmd_sn = zmd_sn;
	}

	/**
	 * @val 附件
	 */
	public void setAttachmentList(List<KonkaPeAttachments> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * @val 附件
	 */
	public List<KonkaPeAttachments> getAttachmentList() {
		return attachmentList;
	}

	/**
	 * @val 专卖店审核记录表
	 */
	public void setKonkaXxZmdAuditUserHisList(List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList) {
		this.konkaXxZmdAuditUserHisList = konkaXxZmdAuditUserHisList;
	}

	/**
	 * @val 专卖店审核记录表
	 */
	public List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisList() {
		return konkaXxZmdAuditUserHisList;
	}

	/**
	 * @val 专卖店审核记录
	 */
	public void setKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis) {
		this.konkaXxZmdAuditUserHis = konkaXxZmdAuditUserHis;
	}

	/**
	 * @val 专卖店审核记录
	 */
	public KonkaXxZmdAuditUserHis getKonkaXxZmdAuditUserHis() {
		return konkaXxZmdAuditUserHis;
	}

	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}

}