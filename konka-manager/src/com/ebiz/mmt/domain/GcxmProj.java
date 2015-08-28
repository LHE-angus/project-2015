package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmProj extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long proj_type;

	private String proj_code;

	private String proj_name;

	private Long fgs_id;

	private Long create_user_id;

	private String create_name;

	private Date create_date;

	private String remark;

	private Integer del_mark;

	private Integer info_state;

	private String buyer;

	private String buyer_tel;

	private Date offer_date;

	private Date delivery_date;

	private Integer is_win;

	private String size;

	private String buyer_num;

	private String budget;

	private String memo;

	private Integer is_validate;

	private List<Attachment> attachmentList;

	private List<Attachment> attachment1List;

	private List<Attachment> attachment2List;

	private List<Attachment> attachment3List;

	private GcxmProjOffer gcxmProjOffer;

	private GcxmProjSupply gcxmProjSupply;

	private List<GcxmProjCompet> gcxmProjCompetList;

	public GcxmProjSupply getGcxmProjSupply() {
		return gcxmProjSupply;
	}

	public void setGcxmProjSupply(GcxmProjSupply gcxmProjSupply) {
		this.gcxmProjSupply = gcxmProjSupply;
	}

	public GcxmProjOffer getGcxmProjOffer() {
		return gcxmProjOffer;
	}

	public void setGcxmProjOffer(GcxmProjOffer gcxmProjOffer) {
		this.gcxmProjOffer = gcxmProjOffer;
	}

	public GcxmProj() {

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
	 * @val 项目类型（政府采购、酒店采购、企业采购、其他）
	 */
	public Long getProj_type() {
		return proj_type;
	}

	/**
	 * @val 项目类型（政府采购、酒店采购、企业采购、其他）
	 */
	public void setProj_type(Long proj_type) {
		this.proj_type = proj_type;
	}

	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public String getProj_code() {
		return proj_code;
	}

	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public void setProj_code(String proj_code) {
		this.proj_code = proj_code;
	}

	/**
	 * @val 项目名称
	 */
	public String getProj_name() {
		return proj_name;
	}

	/**
	 * @val 项目名称
	 */
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	/**
	 * @val 分公司ID
	 */
	public Long getFgs_id() {
		return fgs_id;
	}

	/**
	 * @val 分公司ID
	 */
	public void setFgs_id(Long fgs_id) {
		this.fgs_id = fgs_id;
	}

	/**
	 * @val 创建人ID
	 */
	public Long getCreate_user_id() {
		return create_user_id;
	}

	/**
	 * @val 创建人ID
	 */
	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 * @val 创建人姓名
	 */
	public String getCreate_name() {
		return create_name;
	}

	/**
	 * @val 创建人姓名
	 */
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}

	/**
	 * @val 创建日期
	 */
	public Date getCreate_date() {
		return create_date;
	}

	/**
	 * @val 创建日期
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @val 删除表示
	 */
	public Integer getDel_mark() {
		return del_mark;
	}

	/**
	 * @val 删除表示
	 */
	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}

	/**
	 * @val 状态： 未提交、审核中、已完结
	 */
	public Integer getInfo_state() {
		return info_state;
	}

	/**
	 * @val 状态： 未提交、审核中、已完结
	 */
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}

	/**
	 * @val 采购|投标人
	 */
	public String getBuyer() {
		return buyer;
	}

	/**
	 * @val 采购|投标人
	 */
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	/**
	 * @val 采购|投标人电话
	 */
	public String getBuyer_tel() {
		return buyer_tel;
	}

	/**
	 * @val 采购|投标人电话
	 */
	public void setBuyer_tel(String buyer_tel) {
		this.buyer_tel = buyer_tel;
	}

	/**
	 * @val 报价|开标日期
	 */
	public Date getOffer_date() {
		return offer_date;
	}

	/**
	 * @val 报价|开标日期
	 */
	public void setOffer_date(Date offer_date) {
		this.offer_date = offer_date;
	}

	/**
	 * @val 交货日期
	 */
	public Date getDelivery_date() {
		return delivery_date;
	}

	/**
	 * @val 交货日期
	 */
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	/**
	 * @val 是否中标 1已中标,0未中标
	 */
	public Integer getIs_win() {
		return is_win;
	}

	/**
	 * @val 是否中标 1已中标,0未中标
	 */
	public void setIs_win(Integer is_win) {
		this.is_win = is_win;
	}

	/**
	 * @val 采购尺寸
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @val 采购尺寸
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @val 采购|投标人电话
	 */
	public String getBuyer_num() {
		return buyer_num;
	}

	/**
	 * @val 采购|投标人电话
	 */
	public void setBuyer_num(String buyer_num) {
		this.buyer_num = buyer_num;
	}

	/**
	 * @val 采购预算
	 */
	public String getBudget() {
		return budget;
	}

	/**
	 * @val 采购预算
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}

	/**
	 * @val 参数要求
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @val 参数要求
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public List<Attachment> getAttachment1List() {
		return attachment1List;
	}

	public void setAttachment1List(List<Attachment> attachment1List) {
		this.attachment1List = attachment1List;
	}

	public List<Attachment> getAttachment2List() {
		return attachment2List;
	}

	public void setAttachment2List(List<Attachment> attachment2List) {
		this.attachment2List = attachment2List;
	}

	public List<Attachment> getAttachment3List() {
		return attachment3List;
	}

	public void setAttachment3List(List<Attachment> attachment3List) {
		this.attachment3List = attachment3List;
	}

	public Integer getIs_validate() {
		return is_validate;
	}

	public void setIs_validate(Integer is_validate) {
		this.is_validate = is_validate;
	}

	public List<GcxmProjCompet> getGcxmProjCompetList() {
		return gcxmProjCompetList;
	}

	public void setGcxmProjCompetList(List<GcxmProjCompet> gcxmProjCompetList) {
		this.gcxmProjCompetList = gcxmProjCompetList;
	}

}