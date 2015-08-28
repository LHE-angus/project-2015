package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:19:58
 */
public class PshowOrder extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String uuid;

	private String trade_index;

	private Long dept_id;

	private Integer state;

	private Integer order_from;

	private Long opr_dept_id;

	private BigDecimal total_price;

	private Long order_user_id;

	private String order_user_name;

	private String buyer_name;

	private Long buyer_p_index;

	private String buyer_addr;

	private String buyer_zip;

	private String buyer_tel;

	private String buyer_mp;

	private Integer deliver_way;

	private Integer deliver_time;

	private Integer deliver_is_call;

	private Integer pay_way;

	private Integer bill_is_add;

	private Integer bill_type;

	private Integer bill_head;

	private Integer bill_content;

	private String bill_company;

	private String remark;

	private String dept_remark;

	private Date add_date;

	private Integer is_del;

	private Date del_date;

	private String trade_no;

	private Date pay_date;

	private Integer order_type;

	private Integer logistic_type;

	private String logistic_sn;

	private Long pay_account_id;

	private Long express_id;

	private BigDecimal wl_yf;// 物流运费

	private BigDecimal integral;// 奖励积分

	List<EcVouchers> ecVouchersList = new ArrayList<EcVouchers>();// 购物券

	private String code;// 机器码

	private Integer is_hdfk;

	private Long par_order_id;// 拆分订单的父订单id

	private Integer is_deduction;// 佣金抵扣

	private Integer is_r3;// 是否转R3

	private Integer is_ps;// 是否二次配送，0否1是

	private Integer is_self;// 是否自提，0否1是

	private Long self_id;// 自提点ID

	private Long ps_id;// 二次配送点ID

	private EcUser ecUser = new EcUser();

	private List<PshowOrdeExchange> pshowOrdeExchangeList = new ArrayList<PshowOrdeExchange>();

	private BigDecimal pay_integral;// 付款积分

	private Integer jianyiguajia;

	private String sfhk_order_name;// 顺丰嘿客下单人

	public Integer getJianyiguajia() {
		return jianyiguajia;
	}

	public void setJianyiguajia(Integer jianyiguajia) {
		this.jianyiguajia = jianyiguajia;
	}

	public BigDecimal getPay_integral() {
		return pay_integral;
	}

	public void setPay_integral(BigDecimal pay_integral) {
		this.pay_integral = pay_integral;
	}

	public List<PshowOrdeExchange> getPshowOrdeExchangeList() {
		return pshowOrdeExchangeList;
	}

	public void setPshowOrdeExchangeList(List<PshowOrdeExchange> pshowOrdeExchangeList) {
		this.pshowOrdeExchangeList = pshowOrdeExchangeList;
	}

	public EcUser getEcUser() {
		return ecUser;
	}

	public void setEcUser(EcUser ecUser) {
		this.ecUser = ecUser;
	}

	public Integer getIs_r3() {
		return is_r3;
	}

	public void setIs_r3(Integer isR3) {
		is_r3 = isR3;
	}

	public Integer getIs_deduction() {
		return is_deduction;
	}

	public void setIs_deduction(Integer is_deduction) {
		this.is_deduction = is_deduction;
	}

	public List<EcVouchers> getEcVouchersList() {
		return ecVouchersList;
	}

	public void setEcVouchersList(List<EcVouchers> ecVouchersList) {
		this.ecVouchersList = ecVouchersList;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getDedu_price() {
		return dedu_price;
	}

	public void setDedu_price(BigDecimal dedu_price) {
		this.dedu_price = dedu_price;
	}

	private BigDecimal dedu_price;// 抵扣金额

	private BigDecimal pay_price;// 付款金额

	public BigDecimal getPay_price() {
		return pay_price;
	}

	public void setPay_price(BigDecimal pay_price) {
		this.pay_price = pay_price;
	}

	List<PshowOrdeDetails> pshowOrdeDetailsList;

	public PshowOrder() {

	}

	/**
	 * @val 订单ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 订单ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val UUID
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @val UUID
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @val 交易流水号
	 */
	public String getTrade_index() {
		return trade_index;
	}

	/**
	 * @val 交易流水号
	 */
	public void setTrade_index(String trade_index) {
		this.trade_index = trade_index;
	}

	/**
	 * @val 所属分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 所属分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 订单状态：
	 * @val -30-已退货
	 * @val -20-审核未通过
	 * @val -10-已关闭
	 * @val 0-已预订
	 * @val 10-已付款
	 * @val 20-审核通过
	 * @val 30-下发处理
	 * @val 40-商家发货
	 * @val 50-客户已换货
	 * @val 60-确认收货
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 订单状态：
	 * @val -30-已退货
	 * @val -20-审核未通过
	 * @val -10-已关闭
	 * @val 0-已预订
	 * @val 10-已付款
	 * @val 20-审核通过
	 * @val 30-下发处理
	 * @val 40-商家发货
	 * @val 50-客户已换货
	 * @val 60-确认收货
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 正在处理分部门ID：下单的时候ID为总部，如果下发则修改为需要处理的部门ID
	 */
	public Long getOpr_dept_id() {
		return opr_dept_id;
	}

	/**
	 * @val 正在处理分部门ID：下单的时候ID为总部，如果下发则修改为需要处理的部门ID
	 */
	public void setOpr_dept_id(Long opr_dept_id) {
		this.opr_dept_id = opr_dept_id;
	}

	/**
	 * @val 订单总金额（单位：元）
	 */
	public BigDecimal getTotal_price() {
		return total_price;
	}

	/**
	 * @val 订单总金额（单位：元）
	 */
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	/**
	 * @val 下单用户ID
	 */
	public Long getOrder_user_id() {
		return order_user_id;
	}

	/**
	 * @val 下单用户ID
	 */
	public void setOrder_user_id(Long order_user_id) {
		this.order_user_id = order_user_id;
	}

	/**
	 * @val 下单人姓名
	 */
	public String getOrder_user_name() {
		return order_user_name;
	}

	/**
	 * @val 下单人姓名
	 */
	public void setOrder_user_name(String order_user_name) {
		this.order_user_name = order_user_name;
	}

	/**
	 * @val 购买人姓名
	 */
	public String getBuyer_name() {
		return buyer_name;
	}

	/**
	 * @val 购买人姓名
	 */
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	/**
	 * @val 购买人地区
	 */
	public Long getBuyer_p_index() {
		return buyer_p_index;
	}

	/**
	 * @val 购买人地区
	 */
	public void setBuyer_p_index(Long buyer_p_index) {
		this.buyer_p_index = buyer_p_index;
	}

	/**
	 * @val 购买人地址
	 */
	public String getBuyer_addr() {
		return buyer_addr;
	}

	/**
	 * @val 购买人地址
	 */
	public void setBuyer_addr(String buyer_addr) {
		this.buyer_addr = buyer_addr;
	}

	/**
	 * @val 购买人邮编
	 */
	public String getBuyer_zip() {
		return buyer_zip;
	}

	/**
	 * @val 购买人邮编
	 */
	public void setBuyer_zip(String buyer_zip) {
		this.buyer_zip = buyer_zip;
	}

	/**
	 * @val 购买人固定电话
	 */
	public String getBuyer_tel() {
		return buyer_tel;
	}

	/**
	 * @val 购买人固定电话
	 */
	public void setBuyer_tel(String buyer_tel) {
		this.buyer_tel = buyer_tel;
	}

	/**
	 * @val 购买人手机号码（*）
	 */
	public String getBuyer_mp() {
		return buyer_mp;
	}

	/**
	 * @val 购买人手机号码（*）
	 */
	public void setBuyer_mp(String buyer_mp) {
		this.buyer_mp = buyer_mp;
	}

	/**
	 * @val 发货方式
	 * @val 0：上门自提
	 * @val 1：商家配送
	 */
	public Integer getDeliver_way() {
		return deliver_way;
	}

	/**
	 * @val 发货方式
	 * @val 0：上门自提
	 * @val 1：商家配送
	 */
	public void setDeliver_way(Integer deliver_way) {
		this.deliver_way = deliver_way;
	}

	/**
	 * @val 发货时间设置
	 * @val 0：只工作日送货（双休日、假日不用送）
	 * @val 1：工作日、双休日与假日均可送货
	 * @val 2：只双休日、假日送货（工作日不送货）
	 */
	public Integer getDeliver_time() {
		return deliver_time;
	}

	/**
	 * @val 发货时间设置
	 * @val 0：只工作日送货（双休日、假日不用送）
	 * @val 1：工作日、双休日与假日均可送货
	 * @val 2：只双休日、假日送货（工作日不送货）
	 */
	public void setDeliver_time(Integer deliver_time) {
		this.deliver_time = deliver_time;
	}

	/**
	 * @val 发货前电话确认
	 * @val 0：否
	 * @val 1：是
	 */
	public Integer getDeliver_is_call() {
		return deliver_is_call;
	}

	/**
	 * @val 发货前电话确认
	 * @val 0：否
	 * @val 1：是
	 */
	public void setDeliver_is_call(Integer deliver_is_call) {
		this.deliver_is_call = deliver_is_call;
	}

	/**
	 * @val 支付方式
	 * @val 0：货到付款
	 * @val 1：在线支付
	 * @val 2: 支付宝
	 * @val 3:银联, 4：财付通
	 */
	public Integer getPay_way() {
		return pay_way;
	}

	/**
	 * @val 支付方式
	 * @val 0：货到付款
	 * @val 1：在线支付
	 * @val 2: 支付宝
	 * @val 3:银联, 4：财付通
	 */
	public void setPay_way(Integer pay_way) {
		this.pay_way = pay_way;
	}

	/**
	 * @val 发票是否索要
	 * @val 0：否
	 * @val 1：是
	 */
	public Integer getBill_is_add() {
		return bill_is_add;
	}

	/**
	 * @val 发票是否索要
	 * @val 0：否
	 * @val 1：是
	 */
	public void setBill_is_add(Integer bill_is_add) {
		this.bill_is_add = bill_is_add;
	}

	/**
	 * @val 发票类型
	 * @val 0：普通发票
	 * @val 1：增值税发票
	 */
	public Integer getBill_type() {
		return bill_type;
	}

	/**
	 * @val 发票类型
	 * @val 0：普通发票
	 * @val 1：增值税发票
	 */
	public void setBill_type(Integer bill_type) {
		this.bill_type = bill_type;
	}

	/**
	 * @val 发票抬头
	 * @val 0：单位
	 * @val 1：个人
	 */
	public Integer getBill_head() {
		return bill_head;
	}

	/**
	 * @val 发票抬头
	 * @val 0：单位
	 * @val 1：个人
	 */
	public void setBill_head(Integer bill_head) {
		this.bill_head = bill_head;
	}

	/**
	 * @val 发票内容
	 * @val 0：明细
	 * @val 1：办公用品
	 * @val 2：电脑配件
	 * @val 3：耗材
	 */
	public Integer getBill_content() {
		return bill_content;
	}

	/**
	 * @val 发票内容
	 * @val 0：明细
	 * @val 1：办公用品
	 * @val 2：电脑配件
	 * @val 3：耗材
	 */
	public void setBill_content(Integer bill_content) {
		this.bill_content = bill_content;
	}

	/**
	 * @val 发票单位
	 */
	public String getBill_company() {
		return bill_company;
	}

	/**
	 * @val 发票单位
	 */
	public void setBill_company(String bill_company) {
		this.bill_company = bill_company;
	}

	/**
	 * @val 订单备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @val 订单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @val 企业备注
	 */
	public String getDept_remark() {
		return dept_remark;
	}

	/**
	 * @val 企业备注
	 */
	public void setDept_remark(String dept_remark) {
		this.dept_remark = dept_remark;
	}

	/**
	 * @val 订单入库时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 订单入库时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
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
	 * @val 删除时间
	 */
	public Date getDel_date() {
		return del_date;
	}

	/**
	 * @val 删除时间
	 */
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	/**
	 * @val 支付单号:
	 * @val 1.当支付方式为支付宝时，支付单号为支付宝交易号；
	 * @val 2.当支付方式为银联时，支付单号为银联交易号。
	 */
	public String getTrade_no() {
		return trade_no;
	}

	/**
	 * @val 支付单号:
	 * @val 1.当支付方式为支付宝时，支付单号为支付宝交易号；
	 * @val 2.当支付方式为银联时，支付单号为银联交易号。
	 */
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	/**
	 * @val 支付时
	 */
	public Date getPay_date() {
		return pay_date;
	}

	/**
	 * @val 支付时
	 */
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	/**
	 * @val 订单来源：1-工卡，2-触网，3-会员
	 */

	public Integer getOrder_from() {
		return order_from;
	}

	/**
	 * @val 订单来源：1-工卡，2-触网，3-会员
	 */
	public void setOrder_from(Integer orderFrom) {
		order_from = orderFrom;
	}

	public List<PshowOrdeDetails> getPshowOrdeDetailsList() {
		return pshowOrdeDetailsList;
	}

	public void setPshowOrdeDetailsList(List<PshowOrdeDetails> pshowOrdeDetailsList) {
		this.pshowOrdeDetailsList = pshowOrdeDetailsList;
	}

	/**
	 * @val 订单类型：0-默认，5-限时抢购，6-团购
	 */
	public Integer getOrder_type() {
		return order_type;
	}

	/**
	 * @val 订单类型：0-默认，5-限时抢购，6-团购
	 */
	public void setOrder_type(Integer orderType) {
		order_type = orderType;
	}

	/**
	 * @val 物流类型：1-顺丰，2-EMS
	 */
	public Integer getLogistic_type() {
		return logistic_type;
	}

	/**
	 * @val 物流类型：1-顺丰，2-EMS
	 */
	public void setLogistic_type(Integer logistic_type) {
		this.logistic_type = logistic_type;
	}

	/**
	 * @val 物流单号
	 */
	public String getLogistic_sn() {
		return logistic_sn;
	}

	/**
	 * @val 物流单号
	 */
	public void setLogistic_sn(String logistic_sn) {
		this.logistic_sn = logistic_sn;
	}

	/**
	 * @val 支付id
	 */
	public Long getPay_account_id() {
		return pay_account_id;
	}

	/**
	 * @val 支付id
	 */
	public void setPay_account_id(Long payAccountId) {
		pay_account_id = payAccountId;
	}

	/**
	 * @val 快递id
	 */
	public Long getExpress_id() {
		return express_id;
	}

	/**
	 * @val 快递id
	 */
	public void setExpress_id(Long expressId) {
		express_id = expressId;
	}

	public BigDecimal getWl_yf() {
		return wl_yf;
	}

	public void setWl_yf(BigDecimal wlYf) {
		wl_yf = wlYf;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIs_hdfk() {
		return is_hdfk;
	}

	public void setIs_hdfk(Integer isHdfk) {
		is_hdfk = isHdfk;
	}

	public Long getPar_order_id() {
		return par_order_id;
	}

	public void setPar_order_id(Long parOrderId) {
		par_order_id = parOrderId;
	}

	public Integer getIs_ps() {
		return is_ps;
	}

	public void setIs_ps(Integer isPs) {
		is_ps = isPs;
	}

	public Integer getIs_self() {
		return is_self;
	}

	public void setIs_self(Integer isSelf) {
		is_self = isSelf;
	}

	public Long getSelf_id() {
		return self_id;
	}

	public void setSelf_id(Long selfId) {
		self_id = selfId;
	}

	public Long getPs_id() {
		return ps_id;
	}

	public void setPs_id(Long psId) {
		ps_id = psId;
	}

	public String getSfhk_order_name() {
		return sfhk_order_name;
	}

	public void setSfhk_order_name(String sfhkOrderName) {
		sfhk_order_name = sfhkOrderName;
	}

}