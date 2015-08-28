package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public class JBasePartner extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long partner_id;

	private Integer partner_type;

	private Integer partner_obj;

	private String partner_name;

	private String partner_sn;

	private String partner_addr;

	private String partner_fax;

	private String link_name;

	private String link_mobile;
	
	private String link_sex;   //性别
	
	private String link_job;  //职务

	private String link_tel;

	private String link_email;

	private String link_qq_msn;

	private Integer link_id_type;

	private String link_id;

	private String link_addr;

	private String memo;

	private Integer is_del;

	private Date add_date;

	private Long c_id;

	private Long partner_c_id;
	
	private String consignee_name;
	
	private String consignee_tel;
	
	private String consignee_p_index;
	
	private String consignee_street;
	
	private Long par_c_id;
	
	private String partner_company_phone;  //公司电话
	
	private String area_code;     //所在城市
	private Long dept_id;     //分公司ID
	private Long jb_job_id;     //经办ID
	private String ywy_job_id;     //业务员ID
	private Long p_level;     //业务员ID
	
	private Date stop_date;
	
	private Long create_user_id;  //添加人ID
	private Long modify_user_id;  //维护人ID
	private Date modify_date;  //维护时间

	public JBasePartner() {

	}

	/**
	 * @val 往来单位ID
	 */
	public Long getPartner_id() {
		return partner_id;
	}

	/**
	 * @val 往来单位ID
	 */
	public void setPartner_id(Long partner_id) {
		this.partner_id = partner_id;
	}

	/**
	 * @val 伙伴类型: 0-供应商 1-客户
	 */
	public Integer getPartner_type() {
		return partner_type;
	}

	/**
	 * @val 伙伴类型: 0-供应商 1-客户
	 */
	public void setPartner_type(Integer partner_type) {
		this.partner_type = partner_type;
	}

	/**
	 * @val 伙伴对象:0-个人 1-组织/单位
	 */
	public Integer getPartner_obj() {
		return partner_obj;
	}

	/**
	 * @val 伙伴对象:0-个人 1-组织/单位
	 */
	public void setPartner_obj(Integer partner_obj) {
		this.partner_obj = partner_obj;
	}

	/**
	 * @val 往来单位名称
	 */
	public String getPartner_name() {
		return partner_name;
	}

	/**
	 * @val 往来单位名称
	 */
	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	/**
	 * @val 往来单位编号
	 */
	public String getPartner_sn() {
		return partner_sn;
	}

	/**
	 * @val 往来单位编号
	 */
	public void setPartner_sn(String partner_sn) {
		this.partner_sn = partner_sn;
	}

	/**
	 * @val 往来单位地址
	 */
	public String getPartner_addr() {
		return partner_addr;
	}

	/**
	 * @val 往来单位地址
	 */
	public void setPartner_addr(String partner_addr) {
		this.partner_addr = partner_addr;
	}

	/**
	 * @val 往来单位传真
	 */
	public String getPartner_fax() {
		return partner_fax;
	}

	/**
	 * @val 往来单位传真
	 */
	public void setPartner_fax(String partner_fax) {
		this.partner_fax = partner_fax;
	}

	/**
	 * @val 联系人姓名
	 */
	public String getLink_name() {
		return link_name;
	}

	/**
	 * @val 联系人姓名
	 */
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	/**
	 * @val 联系人移动电话
	 */
	public String getLink_mobile() {
		return link_mobile;
	}

	/**
	 * @val 联系人移动电话
	 */
	public void setLink_mobile(String link_mobile) {
		this.link_mobile = link_mobile;
	}

	/**
	 * @val 联系人固定电话
	 */
	public String getLink_tel() {
		return link_tel;
	}

	/**
	 * @val 联系人固定电话
	 */
	public void setLink_tel(String link_tel) {
		this.link_tel = link_tel;
	}

	/**
	 * @val 联系人邮件
	 */
	public String getLink_email() {
		return link_email;
	}

	/**
	 * @val 联系人邮件
	 */
	public void setLink_email(String link_email) {
		this.link_email = link_email;
	}

	/**
	 * @val 联系人QQ/MSN
	 */
	public String getLink_qq_msn() {
		return link_qq_msn;
	}

	/**
	 * @val 联系人QQ/MSN
	 */
	public void setLink_qq_msn(String link_qq_msn) {
		this.link_qq_msn = link_qq_msn;
	}

	/**
	 * @val 联系人证件：0-身份证 1-护照 2-港澳通行证 3-台湾通行证
	 */
	public Integer getLink_id_type() {
		return link_id_type;
	}

	/**
	 * @val 联系人证件：0-身份证 1-护照 2-港澳通行证 3-台湾通行证
	 */
	public void setLink_id_type(Integer link_id_type) {
		this.link_id_type = link_id_type;
	}

	/**
	 * @val 联系人ID
	 */
	public String getLink_id() {
		return link_id;
	}

	/**
	 * @val 联系人ID
	 */
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}

	/**
	 * @val 联系人地址
	 */
	public String getLink_addr() {
		return link_addr;
	}

	/**
	 * @val 联系人地址
	 */
	public void setLink_addr(String link_addr) {
		this.link_addr = link_addr;
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
	

	public Long getPartner_c_id() {
		return partner_c_id;
	}

	public void setPartner_c_id(Long partnerCId) {
		partner_c_id = partnerCId;
	}

	public String getConsignee_name() {
		return consignee_name;
	}

	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}

	public String getConsignee_tel() {
		return consignee_tel;
	}

	public void setConsignee_tel(String consignee_tel) {
		this.consignee_tel = consignee_tel;
	}

	public String getConsignee_p_index() {
		return consignee_p_index;
	}

	public void setConsignee_p_index(String consignee_p_index) {
		this.consignee_p_index = consignee_p_index;
	}

	public String getConsignee_street() {
		return consignee_street;
	}

	public void setConsignee_street(String consignee_street) {
		this.consignee_street = consignee_street;
	}

	public void setPar_c_id(Long par_c_id) {
		this.par_c_id = par_c_id;
	}
	
	public Long getPar_c_id() {
		return par_c_id;
	}

	public void setPartner_company_phone(String partner_company_phone) {
		this.partner_company_phone = partner_company_phone;
	}

	public String getPartner_company_phone() {
		return partner_company_phone;
	}

	public void setLink_sex(String link_sex) {
		this.link_sex = link_sex;
	}

	public String getLink_sex() {
		return link_sex;
	}

	public void setLink_job(String link_job) {
		this.link_job = link_job;
	}

	public String getLink_job() {
		return link_job;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_code() {
		return area_code;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long deptId) {
		dept_id = deptId;
	}

	public Long getJb_job_id() {
		return jb_job_id;
	}

	public void setJb_job_id(Long jbJobId) {
		jb_job_id = jbJobId;
	}

	public String getYwy_job_id() {
		return ywy_job_id;
	}

	public void setYwy_job_id(String ywyJobId) {
		ywy_job_id = ywyJobId;
	}

	public void setP_level(Long p_level) {
		this.p_level = p_level;
	}

	public Long getP_level() {
		return p_level;
	}
	/**
	 * @val 停用时间
	 */
	public Date getStop_date() {
		return stop_date;
	}
	
	/**
	 * @val 停用时间
	 */
	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}

	public Long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	public Long getModify_user_id() {
		return modify_user_id;
	}

	public void setModify_user_id(Long modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

}