package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-11-25 11:00
 */
public class KonkaOrderInfo extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String trade_index;

	private Long order_num;

	private BigDecimal money;

	private BigDecimal good_discount_price;

	private Integer order_state;

	private String user_name;

	private String user_tel;

	private String user_phone;

	private String user_addr;

	private String user_zip;

	private Long user_p_index;

	// 客户名称
	private String user_shop_name;

	private String user_remark;

	private Date order_date;

	private Date pay_date;

	private Long shipping_id;

	private Date shipping_date;

	private Long receiving_id;

	private Date receiving_date;

	private String remark;

	private BigDecimal freight;

	private Integer invoice_type;

	private String invoices_payable;

	private String return_reason;// 退货原因

	private Integer invoice_status;

	private Integer pay_type;

	private Integer matflow_mode;

	// JxcKonkaOrderRegisterAction
	// konkaOrderInfo.setShop_id(shop_id);
    // 客户端：康佳客户不保存 201308前第一版进销使用
    // 此后使用cust_id

	private Long shop_id;

	private Date add_date;

	private Long add_user_id;

	private String add_user_name;

	private Long add_dept_id;

	private String add_dept_name;

	private Date update_date;

	private Long update_user_id;

	private Integer process_state;
	
	
	// 0未审核 1审核中 3已完结 4已作废 
	private Integer audit_state;

	private Long process_id;

	private Integer send_type;

	private Integer is_del;

	private Integer is_submit;

	private Integer is_end;

	// 客户的ID
	private Long cust_id; // ?

	private String sales_org;

	private String division;

	private String ag;

	private String re;

	private String rg;

	private String we;

	// 同步状态
	private Integer sync_state;
	// 同步时间
	private String sync_date;
	// 同步人
	private String sync_user;

	private Long r3_id;// r3系统返回的订单号

	private Long next_audit_role_id;

	private Long next_node_id;

	private String doc_type;// 销售凭证类型

	private Integer is_delivered;// 是否发货 0-未发货 1-已发货

	private Integer is_sh;// 收货状态0-未收货 1-已收货

	private Integer kh_confirm_state;// 客户确认状态

	private Date kh_confirm_date;// 客户确认时间

	private String third_cg_order_num;// 第三方采购订单编号

	private Date cg_order_date;// 采购订单日期

	private Integer zmd_order_flag; // 是否是专卖店订单

	private Long zmd_order_num; // 专卖店订单编号

	private KonkaXxSellBill konkaXxSellBill;// 专卖店销售单

	private String return_type;// 退货类型,下退货的时候才有

	private String return_type_remark;// 退货原因备注

    /**
     * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
     * 0在线下单<br>
     * 1图片下单<br>
     * 2触网转单<br>
     * 4从返利转<br>
     * 5DRP转入<br>
     */
    private Integer order_type;

	private Integer printCount;// 打印次数
	
	private Integer is_change;
	
	private BigDecimal sale_count_4;
	
	private BigDecimal sale_count_6;
	
	private BigDecimal sale_count_8;
	
	private Integer is_need_to_manager;
	
	private BigDecimal sale_count_01_add;
	
	private BigDecimal sale_count_02_add;
	
	private BigDecimal sale_count_03_add;
	

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer orderType) {
		order_type = orderType;
	}

	public Integer getIs_end() {
		return is_end;
	}

	public void setIs_end(Integer isEnd) {
		is_end = isEnd;
	}

	public Integer getAudit_state() {
		return audit_state;
	}

	public void setAudit_state(Integer auditState) {
		audit_state = auditState;
	}

	public Long getProcess_id() {
		return process_id;
	}

	public void setProcess_id(Long processId) {
		process_id = processId;
	}

	private List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList;

	private List<KonkaOrderInfoDetails> konkaOrderInfoDetailsListForDel;

	private List<KonkaOrderInfoDetailsAudit> KonkaOrderInfoDetailsAuditList;

	private List<KonkaOrderInfoDetailsAudit> KonkaOrderInfoDetailsAuditListForUpdate;

	private List<KonkaOrderInfoAudit> konkaOrderInfoAuditList;

	private List<KonkaOrderInfoUpdateRecord> konkaOrderInfoUpdateRecordList;

	private KonkaOrderInfoAudit konkaOrderInfoAudit;

	public List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsListForDel() {
		return konkaOrderInfoDetailsListForDel;
	}

	public void setKonkaOrderInfoDetailsListForDel(List<KonkaOrderInfoDetails> konkaOrderInfoDetailsListForDel) {
		this.konkaOrderInfoDetailsListForDel = konkaOrderInfoDetailsListForDel;
	}

	public List<KonkaOrderInfoUpdateRecord> getKonkaOrderInfoUpdateRecordList() {
		return konkaOrderInfoUpdateRecordList;
	}

	public void setKonkaOrderInfoUpdateRecordList(List<KonkaOrderInfoUpdateRecord> konkaOrderInfoUpdateRecordList) {
		this.konkaOrderInfoUpdateRecordList = konkaOrderInfoUpdateRecordList;
	}

	public KonkaOrderInfoAudit getKonkaOrderInfoAudit() {
		return konkaOrderInfoAudit;
	}

	public void setKonkaOrderInfoAudit(KonkaOrderInfoAudit konkaOrderInfoAudit) {
		this.konkaOrderInfoAudit = konkaOrderInfoAudit;
	}

	public List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsList() {
		return konkaOrderInfoDetailsList;
	}

	public void setKonkaOrderInfoDetailsList(List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList) {
		this.konkaOrderInfoDetailsList = konkaOrderInfoDetailsList;
	}

	public List<Attachment> attachmentList;

	public KonkaOrderInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrade_index() {
		return trade_index;
	}

	public void setTrade_index(String trade_index) {
		this.trade_index = trade_index;
	}

	public Long getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Long order_num) {
		this.order_num = order_num;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getGood_discount_price() {
		return good_discount_price;
	}

	public void setGood_discount_price(BigDecimal good_discount_price) {
		this.good_discount_price = good_discount_price;
	}

	public Integer getOrder_state() {
		return order_state;
	}

	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	public String getUser_zip() {
		return user_zip;
	}

	public void setUser_zip(String user_zip) {
		this.user_zip = user_zip;
	}

	public Long getUser_p_index() {
		return user_p_index;
	}

	public void setUser_p_index(Long user_p_index) {
		this.user_p_index = user_p_index;
	}

	public String getUser_shop_name() {
		return user_shop_name;
	}

	public void setUser_shop_name(String user_shop_name) {
		this.user_shop_name = user_shop_name;
	}

	public String getUser_remark() {
		return user_remark;
	}

	public void setUser_remark(String user_remark) {
		this.user_remark = user_remark;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date orderDate) {
		order_date = orderDate;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public Long getShipping_id() {
		return shipping_id;
	}

	public void setShipping_id(Long shipping_id) {
		this.shipping_id = shipping_id;
	}

	public Date getShipping_date() {
		return shipping_date;
	}

	public void setShipping_date(Date shipping_date) {
		this.shipping_date = shipping_date;
	}

	public Long getReceiving_id() {
		return receiving_id;
	}

	public void setReceiving_id(Long receiving_id) {
		this.receiving_id = receiving_id;
	}

	public Date getReceiving_date() {
		return receiving_date;
	}

	public void setReceiving_date(Date receiving_date) {
		this.receiving_date = receiving_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(Integer invoice_type) {
		this.invoice_type = invoice_type;
	}

	public String getInvoices_payable() {
		return invoices_payable;
	}

	public void setInvoices_payable(String invoices_payable) {
		this.invoices_payable = invoices_payable;
	}

	/**
	 * @val 退货原因 F01 客户退货
	 */
	public String getReturn_reason() {
		return return_reason;
	}

	/**
	 * @val 退货原因 F01 客户退货
	 */
	public void setReturn_reason(String return_reason) {
		this.return_reason = return_reason;
	}

	public Integer getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(Integer invoice_status) {
		this.invoice_status = invoice_status;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getMatflow_mode() {
		return matflow_mode;
	}

	public void setMatflow_mode(Integer matflow_mode) {
		this.matflow_mode = matflow_mode;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}

	public Long getAdd_dept_id() {
		return add_dept_id;
	}

	public void setAdd_dept_id(Long add_dept_id) {
		this.add_dept_id = add_dept_id;
	}

	public String getAdd_dept_name() {
		return add_dept_name;
	}

	public void setAdd_dept_name(String add_dept_name) {
		this.add_dept_name = add_dept_name;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditList() {
		return konkaOrderInfoAuditList;
	}

	public void setKonkaOrderInfoAuditList(List<KonkaOrderInfoAudit> konkaOrderInfoAuditList) {
		this.konkaOrderInfoAuditList = konkaOrderInfoAuditList;
	}

	public Integer getProcess_state() {
		return process_state;
	}

	public void setProcess_state(Integer processState) {
		process_state = processState;
	}

	public Integer getSend_type() {
		return send_type;
	}

	public void setSend_type(Integer sendType) {
		send_type = sendType;
	}

	public List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditList() {
		return KonkaOrderInfoDetailsAuditList;
	}

	public void setKonkaOrderInfoDetailsAuditList(List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList) {
		KonkaOrderInfoDetailsAuditList = konkaOrderInfoDetailsAuditList;
	}

	public List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditListForUpdate() {
		return KonkaOrderInfoDetailsAuditListForUpdate;
	}

	public void setKonkaOrderInfoDetailsAuditListForUpdate(
			List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditListForUpdate) {
		KonkaOrderInfoDetailsAuditListForUpdate = konkaOrderInfoDetailsAuditListForUpdate;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer isDel) {
		is_del = isDel;
	}

	public Integer getIs_submit() {
		return is_submit;
	}

	public void setIs_submit(Integer isSubmit) {
		is_submit = isSubmit;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long custId) {
		cust_id = custId;
	}

	public String getSales_org() {
		return sales_org;
	}

	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getAg() {
		return ag;
	}

	public void setAg(String ag) {
		this.ag = ag;
	}

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getWe() {
		return we;
	}

	public void setWe(String we) {
		this.we = we;
	}

	public Integer getSync_state() {
		return sync_state;
	}

	public void setSync_state(Integer sync_state) {
		this.sync_state = sync_state;
	}

	public Long getR3_id() {
		return r3_id;
	}

	public void setR3_id(Long r3_id) {
		this.r3_id = r3_id;
	}

	public Long getNext_audit_role_id() {
		return next_audit_role_id;
	}

	public void setNext_audit_role_id(Long nextAuditRoleId) {
		next_audit_role_id = nextAuditRoleId;
	}

	public Long getNext_node_id() {
		return next_node_id;
	}

	public void setNext_node_id(Long nextNodeId) {
		next_node_id = nextNodeId;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String docType) {
		doc_type = docType;
	}

	public Integer getIs_delivered() {
		return is_delivered;
	}

	public void setIs_delivered(Integer isDelivered) {
		is_delivered = isDelivered;
	}

	/**
	 * @val 客户确认状态
	 */
	public void setKh_confirm_state(Integer kh_confirm_state) {
		this.kh_confirm_state = kh_confirm_state;
	}

	/**
	 * @val 客户确认状态
	 */
	public Integer getKh_confirm_state() {
		return kh_confirm_state;
	}

	/**
	 * @val 客户确认时间
	 */
	public void setKh_confirm_date(Date kh_confirm_date) {
		this.kh_confirm_date = kh_confirm_date;
	}

	/**
	 * @val 客户确认时间
	 */
	public Date getKh_confirm_date() {
		return kh_confirm_date;
	}

	/**
	 * @val 第三方采购订单编号
	 */
	public void setThird_cg_order_num(String third_cg_order_num) {
		this.third_cg_order_num = third_cg_order_num;
	}

	/**
	 * @val 第三方采购订单编号
	 */
	public String getThird_cg_order_num() {
		return third_cg_order_num;
	}

	/**
	 * @val 采购订单日期
	 */
	public void setCg_order_date(Date cg_order_date) {
		this.cg_order_date = cg_order_date;
	}

	/**
	 * @val 采购订单日期
	 */
	public Date getCg_order_date() {
		return cg_order_date;
	}

	/**
	 * @val 是否是专卖店订单 0：否，1：是
	 */
	public void setZmd_order_flag(Integer zmd_order_flag) {
		this.zmd_order_flag = zmd_order_flag;
	}

	/**
	 * @val 是否是专卖店订单 0：否，1：是
	 */
	public Integer getZmd_order_flag() {
		return zmd_order_flag;
	}

	/**
	 * @val 专卖店订单编号
	 */
	public void setZmd_order_num(Long zmd_order_num) {
		this.zmd_order_num = zmd_order_num;
	}

	/**
	 * @val 专卖店订单编号
	 */
	public Long getZmd_order_num() {
		return zmd_order_num;
	}

	public void setKonkaXxSellBill(KonkaXxSellBill konkaXxSellBill) {
		this.konkaXxSellBill = konkaXxSellBill;
	}

	public KonkaXxSellBill getKonkaXxSellBill() {
		return konkaXxSellBill;
	}


	public String getReturn_type() {
		return return_type;
	}


	public void setReturn_type(String return_type) {
		this.return_type = return_type;
	}

	/**
	 * @val 退货类型备注：只有当退货类型为5、其他原因 时，才必须有值
	 */
	public String getReturn_type_remark() {
		return return_type_remark;
	}

	/**
	 * @val 退货类型备注：只有当退货类型为5、其他原因 时，才必须有值
	 */
	public void setReturn_type_remark(String return_type_remark) {
		this.return_type_remark = return_type_remark;
	}

	public Integer getIs_sh() {
		return is_sh;
	}

	public void setIs_sh(Integer isSh) {
		is_sh = isSh;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public Integer getIs_change() {
		return is_change;
	}

	public void setIs_change(Integer isChange) {
		is_change = isChange;
	}

	public String getSync_date() {
		return sync_date;
	}

	public void setSync_date(String sync_date) {
		this.sync_date = sync_date;
	}

	public String getSync_user() {
		return sync_user;
	}

	public void setSync_user(String sync_user) {
		this.sync_user = sync_user;
	}
	/**
	 * @val 前4周销量
	 */
	public BigDecimal getSale_count_4() {
		return sale_count_4;
	}
	
	/**
	 * @val 前4周销量
	 */
	public void setSale_count_4(BigDecimal sale_count_4) {
		this.sale_count_4 = sale_count_4;
	}
	
	/**
	 * @val 前6周销量
	 */
	public BigDecimal getSale_count_6() {
		return sale_count_6;
	}
	
	/**
	 * @val 前6周销量
	 */
	public void setSale_count_6(BigDecimal sale_count_6) {
		this.sale_count_6 = sale_count_6;
	}
	
	/**
	 * @val 前8周销量
	 */
	public BigDecimal getSale_count_8() {
		return sale_count_8;
	}
	
	/**
	 * @val 前8周销量
	 */
	public void setSale_count_8(BigDecimal sale_count_8) {
		this.sale_count_8 = sale_count_8;
	}
	
	/**
	 * @val 是否需要高层领导审核
	 */
	public Integer getIs_need_to_manager() {
		return is_need_to_manager;
	}
	
	/**
	 * @val 是否需要高层领导审核
	 */
	public void setIs_need_to_manager(Integer is_need_to_manager) {
		this.is_need_to_manager = is_need_to_manager;
	}
	
	/**
	 * @val 预留字段1(具体含义使用时候添加)
	 */
	public BigDecimal getSale_count_01_add() {
		return sale_count_01_add;
	}
	
	/**
	 * @val 预留字段1(具体含义使用时候添加)
	 */
	public void setSale_count_01_add(BigDecimal sale_count_01_add) {
		this.sale_count_01_add = sale_count_01_add;
	}
	
	/**
	 * @val 预留字段2(具体含义使用时候添加)
	 */
	public BigDecimal getSale_count_02_add() {
		return sale_count_02_add;
	}
	
	/**
	 * @val 预留字段2(具体含义使用时候添加)
	 */
	public void setSale_count_02_add(BigDecimal sale_count_02_add) {
		this.sale_count_02_add = sale_count_02_add;
	}
	
	/**
	 * @val 预留字段3(具体含义使用时候添加)
	 */
	public BigDecimal getSale_count_03_add() {
		return sale_count_03_add;
	}
	
	/**
	 * @val 预留字段3(具体含义使用时候添加)
	 */
	public void setSale_count_03_add(BigDecimal sale_count_03_add) {
		this.sale_count_03_add = sale_count_03_add;
	}
	
	
	
}