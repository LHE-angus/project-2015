package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;


public class KonkaSysAplication extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String back_id;
	
	private Long user_id;
	
	private String write_name;
	
	private Long dept_id;
	
	private String name;
	
	private String type;
	
	private String title;
	
	private String content;
	
	private String accessory;
	
	private Long audit_id1;
	
	private String audit_name1;
	
	private Long audit_id2;
	
	private String audit_name2;
	
	private Long audit_id3;
	
	private String audit_name3;
	
	private Long audit_id4;
	
	private String audit_name4;
	
	private String link_id;
	
	private Date create_date;
	
	private Integer deal_status;
	
	private Date deal_date;
	
	private String deal_name;
	
	private String result;
	
	private Integer is_del;
	
	private String bak1;
	
	private String bak2;
	
	private String bak3;
	
	private List<KonkaPeAttachments> attachmentList;  //附件
	
	public KonkaSysAplication() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 回执单号ID
	 */
	public String getBack_id() {
		return back_id;
	}
	
	/**
	 * @val 回执单号ID
	 */
	public void setBack_id(String back_id) {
		this.back_id = back_id;
	}
	
	/**
	 * @val 提交人ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 提交人ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 填写人
	 */
	public String getWrite_name() {
		return write_name;
	}
	
	/**
	 * @val 填写人
	 */
	public void setWrite_name(String write_name) {
		this.write_name = write_name;
	}
	
	/**
	 * @val 分公司部门ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司部门ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 申请人姓名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 申请人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val 申请类型
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @val 申请类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @val 申请内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 申请内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @val 附件
	 */
	public String getAccessory() {
		return accessory;
	}
	
	/**
	 * @val 附件
	 */
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	
	/**
	 * @val 审批人ID1
	 */
	public Long getAudit_id1() {
		return audit_id1;
	}
	
	/**
	 * @val 审批人ID1
	 */
	public void setAudit_id1(Long audit_id1) {
		this.audit_id1 = audit_id1;
	}
	
	/**
	 * @val 审批人姓名1
	 */
	public String getAudit_name1() {
		return audit_name1;
	}
	
	/**
	 * @val 审批人姓名1
	 */
	public void setAudit_name1(String audit_name1) {
		this.audit_name1 = audit_name1;
	}
	
	/**
	 * @val 审批人ID2
	 */
	public Long getAudit_id2() {
		return audit_id2;
	}
	
	/**
	 * @val 审批人ID2
	 */
	public void setAudit_id2(Long audit_id2) {
		this.audit_id2 = audit_id2;
	}
	
	/**
	 * @val 审批人姓名2
	 */
	public String getAudit_name2() {
		return audit_name2;
	}
	
	/**
	 * @val 审批人姓名2
	 */
	public void setAudit_name2(String audit_name2) {
		this.audit_name2 = audit_name2;
	}
	
	/**
	 * @val 审批人ID3
	 */
	public Long getAudit_id3() {
		return audit_id3;
	}
	
	/**
	 * @val 审批人ID3
	 */
	public void setAudit_id3(Long audit_id3) {
		this.audit_id3 = audit_id3;
	}
	
	/**
	 * @val 审批人姓名3
	 */
	public String getAudit_name3() {
		return audit_name3;
	}
	
	/**
	 * @val 审批人姓名3
	 */
	public void setAudit_name3(String audit_name3) {
		this.audit_name3 = audit_name3;
	}
	
	/**
	 * @val 审批人ID4
	 */
	public Long getAudit_id4() {
		return audit_id4;
	}
	
	/**
	 * @val 审批人ID4
	 */
	public void setAudit_id4(Long audit_id4) {
		this.audit_id4 = audit_id4;
	}
	
	/**
	 * @val 审批人姓名4
	 */
	public String getAudit_name4() {
		return audit_name4;
	}
	
	/**
	 * @val 审批人姓名4
	 */
	public void setAudit_name4(String audit_name4) {
		this.audit_name4 = audit_name4;
	}
	
	/**
	 * @val 流程ID
	 */
	public String getLink_id() {
		return link_id;
	}
	
	/**
	 * @val 流程ID
	 */
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	
	/**
	 * @val 申请日期
	 */
	public Date getCreate_date() {
		return create_date;
	}
	
	/**
	 * @val 申请日期
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	/**
	 * @val 处理状态
	 */
	public Integer getDeal_status() {
		return deal_status;
	}
	
	/**
	 * @val 处理状态
	 */
	public void setDeal_status(Integer deal_status) {
		this.deal_status = deal_status;
	}
	
	/**
	 * @val 处理日期
	 */
	public Date getDeal_date() {
		return deal_date;
	}
	
	/**
	 * @val 处理日期
	 */
	public void setDeal_date(Date deal_date) {
		this.deal_date = deal_date;
	}
	
	/**
	 * @val 处理人
	 */
	public String getDeal_name() {
		return deal_name;
	}
	
	/**
	 * @val 处理人
	 */
	public void setDeal_name(String deal_name) {
		this.deal_name = deal_name;
	}
	
	/**
	 * @val 处理结果
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * @val 处理结果
	 */
	public void setResult(String result) {
		this.result = result;
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
	
	/**
	 * @val 备用字段1
	 */
	public String getBak1() {
		return bak1;
	}
	
	/**
	 * @val 备用字段1
	 */
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	
	/**
	 * @val 备用字段2
	 */
	public String getBak2() {
		return bak2;
	}
	
	/**
	 * @val 备用字段2
	 */
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
	
	/**
	 * @val 备用字段3
	 */
	public String getBak3() {
		return bak3;
	}
	
	/**
	 * @val 备用字段3
	 */
	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	public void setAttachmentList(List<KonkaPeAttachments> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public List<KonkaPeAttachments> getAttachmentList() {
		return attachmentList;
	}
	
}