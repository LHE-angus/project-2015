package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-30 11:05:43
 */
public class KonkaR3ShopDev extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long cust_id;

	private String link_man_name;

	private String link_man_tel;

	private String link_man_mobile;

	private String link_man_addr;

	private String link_man_post;

	private String host_name;

	private String host_tel;

	private String cust_name;

	private Date add_date;

	private Long entp_type;

	private BigDecimal entp_reg_money;

	private String entp_scale;

	private Long total_area;

	private Long total_sale;

	private String entp_man_count;

	private String entp_tel;

	private Date entp_birthday;

	private String entp_fax;

	private String entp_post;

	private Long entp_p_index;

	private String entp_p_level;

	private String entp_addr;

	private String entp_www;

	private String entp_main_pds;

	private Long subcomp_id;

	private Long ywy_user_id;

	private Integer is_del;

	private Integer is_match;

	private String b_lng;

	private String b_lat;

	private String link_r3_code;

	private Integer dev_state;

	private Integer state;

	private Date begin_date;

	private Date end_date;

	private String memo; // 备注

	private Integer is_submit; // 是否提交审批，1为已提交，null为未提交

	private Long jb_id;

	private Long customer_type;

	private Long total_malls;

	private Integer is_saled;

	private Date out_date;

	private String out_reason;

	private String leave_question;

	private Integer is_new;

	private Integer audit_stat;

	private Integer is_syn;

	private Date modify_date;

	private Long add_user_id;

	private Long mody_user_id;

	private Long cur_audit_user_id;

	private String entp_sale_area;

	private String entp_info;

	private List<KonkaPeAttachments> konkaPeAttachmentsList;

	public List<KonkaPeAttachments> getKonkaPeAttachmentsList() {
		return konkaPeAttachmentsList;
	}

	public void setKonkaPeAttachmentsList(List<KonkaPeAttachments> konkaPeAttachmentsList) {
		this.konkaPeAttachmentsList = konkaPeAttachmentsList;
	}

	public KonkaR3ShopDev() {

	}

	/**
	 * @val 客户ID
	 */
	public Long getCust_id() {
		return cust_id;
	}

	/**
	 * @val 客户ID
	 */
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	/**
	 * @val +联系人姓名
	 */
	public String getLink_man_name() {
		return link_man_name;
	}

	/**
	 * @val +联系人姓名
	 */
	public void setLink_man_name(String link_man_name) {
		this.link_man_name = link_man_name;
	}

	/**
	 * @val +联系人电话
	 */
	public String getLink_man_tel() {
		return link_man_tel;
	}

	/**
	 * @val +联系人电话
	 */
	public void setLink_man_tel(String link_man_tel) {
		this.link_man_tel = link_man_tel;
	}

	/**
	 * @val +联系人手机
	 */
	public String getLink_man_mobile() {
		return link_man_mobile;
	}

	/**
	 * @val +联系人手机
	 */
	public void setLink_man_mobile(String link_man_mobile) {
		this.link_man_mobile = link_man_mobile;
	}

	/**
	 * @val +联系人地址
	 */
	public String getLink_man_addr() {
		return link_man_addr;
	}

	/**
	 * @val +联系人地址
	 */
	public void setLink_man_addr(String link_man_addr) {
		this.link_man_addr = link_man_addr;
	}

	/**
	 * @val +客户邮编
	 */
	public String getLink_man_post() {
		return link_man_post;
	}

	/**
	 * @val +客户邮编
	 */
	public void setLink_man_post(String link_man_post) {
		this.link_man_post = link_man_post;
	}

	/**
	 * @val +法人姓名
	 */
	public String getHost_name() {
		return host_name;
	}

	/**
	 * @val +法人姓名
	 */
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	/**
	 * @val +法人联系电话
	 */
	public String getHost_tel() {
		return host_tel;
	}

	/**
	 * @val +法人联系电话
	 */
	public void setHost_tel(String host_tel) {
		this.host_tel = host_tel;
	}

	/**
	 * @val 客户名称
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * @val 客户名称
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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
	 * @val 企业类型，KONKA_CATEGORY表中C_TYPE为12
	 */
	public Long getEntp_type() {
		return entp_type;
	}

	/**
	 * @val 企业类型，KONKA_CATEGORY表中C_TYPE为12
	 */
	public void setEntp_type(Long entp_type) {
		this.entp_type = entp_type;
	}

	/**
	 * @val 注册资金（万元）
	 */
	public BigDecimal getEntp_reg_money() {
		return entp_reg_money;
	}

	/**
	 * @val 注册资金（万元）
	 */
	public void setEntp_reg_money(BigDecimal entp_reg_money) {
		this.entp_reg_money = entp_reg_money;
	}

	/**
	 * @val 企业规模
	 */
	public String getEntp_scale() {
		return entp_scale;
	}

	/**
	 * @val 企业规模
	 */
	public void setEntp_scale(String entp_scale) {
		this.entp_scale = entp_scale;
	}

	/**
	 * @val 面积
	 */
	public Long getTotal_area() {
		return total_area;
	}

	/**
	 * @val 面积
	 */
	public void setTotal_area(Long total_area) {
		this.total_area = total_area;
	}

	/**
	 * @val 营业额
	 */
	public Long getTotal_sale() {
		return total_sale;
	}

	/**
	 * @val 营业额
	 */
	public void setTotal_sale(Long total_sale) {
		this.total_sale = total_sale;
	}

	/**
	 * @val 员工人数
	 */
	public String getEntp_man_count() {
		return entp_man_count;
	}

	/**
	 * @val 员工人数
	 */
	public void setEntp_man_count(String entp_man_count) {
		this.entp_man_count = entp_man_count;
	}

	/**
	 * @val 公司电话
	 */
	public String getEntp_tel() {
		return entp_tel;
	}

	/**
	 * @val 公司电话
	 */
	public void setEntp_tel(String entp_tel) {
		this.entp_tel = entp_tel;
	}

	/**
	 * @val 成立时间
	 */
	public Date getEntp_birthday() {
		return entp_birthday;
	}

	/**
	 * @val 成立时间
	 */
	public void setEntp_birthday(Date entp_birthday) {
		this.entp_birthday = entp_birthday;
	}

	/**
	 * @val 公司传真
	 */
	public String getEntp_fax() {
		return entp_fax;
	}

	/**
	 * @val 公司传真
	 */
	public void setEntp_fax(String entp_fax) {
		this.entp_fax = entp_fax;
	}

	/**
	 * @val 邮编
	 */
	public String getEntp_post() {
		return entp_post;
	}

	/**
	 * @val 邮编
	 */
	public void setEntp_post(String entp_post) {
		this.entp_post = entp_post;
	}

	/**
	 * @val 所在城市
	 */
	public Long getEntp_p_index() {
		return entp_p_index;
	}

	/**
	 * @val 所在城市
	 */
	public void setEntp_p_index(Long entp_p_index) {
		this.entp_p_index = entp_p_index;
	}

	/**
	 * @val 城市级别
	 */
	public String getEntp_p_level() {
		return entp_p_level;
	}

	/**
	 * @val 城市级别
	 */
	public void setEntp_p_level(String entp_p_level) {
		this.entp_p_level = entp_p_level;
	}

	/**
	 * @val 公司地址
	 */
	public String getEntp_addr() {
		return entp_addr;
	}

	/**
	 * @val 公司地址
	 */
	public void setEntp_addr(String entp_addr) {
		this.entp_addr = entp_addr;
	}

	/**
	 * @val 公司网址
	 */
	public String getEntp_www() {
		return entp_www;
	}

	/**
	 * @val 公司网址
	 */
	public void setEntp_www(String entp_www) {
		this.entp_www = entp_www;
	}

	/**
	 * @val 主营产品,多个用逗号隔开
	 */
	public String getEntp_main_pds() {
		return entp_main_pds;
	}

	/**
	 * @val 主营产品,多个用逗号隔开
	 */
	public void setEntp_main_pds(String entp_main_pds) {
		this.entp_main_pds = entp_main_pds;
	}

	/**
	 * @val 所属分公司
	 */
	public Long getSubcomp_id() {
		return subcomp_id;
	}

	/**
	 * @val 所属分公司
	 */
	public void setSubcomp_id(Long subcomp_id) {
		this.subcomp_id = subcomp_id;
	}

	/**
	 * @val 添加业务员USER_ID
	 */
	public Long getYwy_user_id() {
		return ywy_user_id;
	}

	/**
	 * @val 添加业务员USER_ID
	 */
	public void setYwy_user_id(Long ywy_user_id) {
		this.ywy_user_id = ywy_user_id;
	}

	/**
	 * @val 是否删除 0否1是
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除 0否1是
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 是否完成匹配 0否1是
	 */
	public Integer getIs_match() {
		return is_match;
	}

	/**
	 * @val 是否完成匹配 0否1是
	 */
	public void setIs_match(Integer is_match) {
		this.is_match = is_match;
	}

	/**
	 * @val 地理位置-经度
	 */
	public String getB_lng() {
		return b_lng;
	}

	/**
	 * @val 地理位置-经度
	 */
	public void setB_lng(String b_lng) {
		this.b_lng = b_lng;
	}

	/**
	 * @val 地理位置-纬度
	 */
	public String getB_lat() {
		return b_lat;
	}

	/**
	 * @val 地理位置-纬度
	 */
	public void setB_lat(String b_lat) {
		this.b_lat = b_lat;
	}

	/**
	 * @val 关联R3客户编码
	 */
	public String getLink_r3_code() {
		return link_r3_code;
	}

	/**
	 * @val 关联R3客户编码
	 */
	public void setLink_r3_code(String link_r3_code) {
		this.link_r3_code = link_r3_code;
	}

	/**
	 * @val 开拓状态
	 * @val 1开拓中
	 * @val 2已关闭
	 * @val 3开拓成功
	 */
	public Integer getDev_state() {
		return dev_state;
	}

	/**
	 * @val 开拓状态
	 * @val 1开拓中
	 * @val 2已关闭
	 * @val 3开拓成功
	 */
	public void setDev_state(Integer dev_state) {
		this.dev_state = dev_state;
	}

	/**
	 * @val 合作意向
	 * @val 1意向强
	 * @val 0意向一般
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 合作意向
	 * @val 1意向强
	 * @val 0意向一般
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 开拓开始时间
	 */
	public Date getBegin_date() {
		return begin_date;
	}

	/**
	 * @val 开拓开始时间
	 */
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	/**
	 * @val 开拓结束时间
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @val 开拓结束时间
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getIs_submit() {
		return is_submit;
	}

	public void setIs_submit(Integer is_submit) {
		this.is_submit = is_submit;
	}

	/**
	 * @val 所属经办ID
	 */
	public Long getJb_id() {
		return jb_id;
	}

	/**
	 * @val 所属经办ID
	 */
	public void setJb_id(Long jb_id) {
		this.jb_id = jb_id;
	}

	/**
	 * @val 客户类别
	 */
	public Long getCustomer_type() {
		return customer_type;
	}

	/**
	 * @val 客户类别
	 */
	public void setCustomer_type(Long customer_type) {
		this.customer_type = customer_type;
	}

	/**
	 * @val 当地卖场数量
	 */
	public Long getTotal_malls() {
		return total_malls;
	}

	/**
	 * @val 当地卖场数量
	 */
	public void setTotal_malls(Long total_malls) {
		this.total_malls = total_malls;
	}

	/**
	 * @val 是否销售过康佳产品，1为销售过，0为未销售过
	 */
	public Integer getIs_saled() {
		return is_saled;
	}

	/**
	 * @val 是否销售过康佳产品，1为销售过，0为未销售过
	 */
	public void setIs_saled(Integer is_saled) {
		this.is_saled = is_saled;
	}

	/**
	 * @val 退出时间
	 */
	public Date getOut_date() {
		return out_date;
	}

	/**
	 * @val 退出时间
	 */
	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	/**
	 * @val 退出原因
	 */
	public String getOut_reason() {
		return out_reason;
	}

	/**
	 * @val 退出原因
	 */
	public void setOut_reason(String out_reason) {
		this.out_reason = out_reason;
	}

	/**
	 * @val 遗留问题
	 */
	public String getLeave_question() {
		return leave_question;
	}

	/**
	 * @val 遗留问题
	 */
	public void setLeave_question(String leave_question) {
		this.leave_question = leave_question;
	}

	/**
	 * @val 是否从新开户申请添加，1为新开户申请添加，0或null为开拓客户
	 */
	public Integer getIs_new() {
		return is_new;
	}

	/**
	 * @val 是否从新开户申请添加，1为新开户申请添加，0或null为开拓客户
	 */
	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}

	/**
	 * @val 审批状态,0待审批，1审核中，2已审批，3已驳回
	 */
	public Integer getAudit_stat() {
		return audit_stat;
	}

	/**
	 * @val 审批状态,0待审批，1审核中，2已审批，3已驳回
	 */
	public void setAudit_stat(Integer audit_stat) {
		this.audit_stat = audit_stat;
	}

	/**
	 * @val 同步状态,1已同步至R3客户管理，0未同步
	 */
	public Integer getIs_syn() {
		return is_syn;
	}

	/**
	 * @val 同步状态,1已同步至R3客户管理，0未同步
	 */
	public void setIs_syn(Integer is_syn) {
		this.is_syn = is_syn;
	}

	/**
	 * @val 修改日期
	 */
	public Date getModify_date() {
		return modify_date;
	}

	/**
	 * @val 修改日期
	 */
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	/**
	 * @val 添加人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	/**
	 * @val 修改人ID
	 */
	public Long getMody_user_id() {
		return mody_user_id;
	}

	/**
	 * @val 修改人ID
	 */
	public void setMody_user_id(Long mody_user_id) {
		this.mody_user_id = mody_user_id;
	}

	/**
	 * @val 当前审批人，若未完审批，则为下一个审批人ID，若审批完成，则为最后一个审批人ID
	 */
	public Long getCur_audit_user_id() {
		return cur_audit_user_id;
	}

	/**
	 * @val 当前审批人，若未完审批，则为下一个审批人ID，若审批完成，则为最后一个审批人ID
	 */
	public void setCur_audit_user_id(Long cur_audit_user_id) {
		this.cur_audit_user_id = cur_audit_user_id;
	}

	/**
	 * @val 销售区域
	 */
	public String getEntp_sale_area() {
		return entp_sale_area;
	}

	/**
	 * @val 销售区域
	 */
	public void setEntp_sale_area(String entp_sale_area) {
		this.entp_sale_area = entp_sale_area;
	}

	/**
	 * @val 客户简介
	 */
	public String getEntp_info() {
		return entp_info;
	}

	/**
	 * @val 客户简介
	 */
	public void setEntp_info(String entp_info) {
		this.entp_info = entp_info;
	}
}