package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:37
 */
public class JdxxEntpSell extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long pd_number;

	private Integer pd_state;

	private String identification;

	private String pd_serial;

	private Long entp_id;

	private Long pd_type;

	private String pd_name;

	private Long pd_id;

	private Long sell_entp_id;

	private BigDecimal pd_quote;

	private BigDecimal pd_price;

	private Long sell_man_id;

	private Date sell_date;

	private String sell_p_index;

	private String invoice_no;

	private String buyer_name;

	private String buyer_id;

	private String buyer_addr;

	private String buyer_link;

	private String buyer_mobile;

	private Long village_p_index;

	private Long sell_log_man;

	private String sell_log_memo;

	private Date allow_date;

	private Long allow_ac_man;

	private Integer allow_sign;

	private String buyer_bank_name;

	private String buyer_bank_user;

	private String buyer_bank_account;

	private String rpr_number;

	private String housemaster;

	private String kin;

	private BigDecimal beh_allow_money;

	private BigDecimal real_allow_money;

	private String buysb_name;

	private String buysb_id;

	private String buysb_addr;

	private String buysb_link;

	private String buysb_mobile;

	private Integer back_sign;

	private Integer allow_non;

	private BigDecimal sub_scale;

	private Integer adv_pay_sign;

	private Date return_date;

	private String allow_memo;

	private Date add_date;

	private Integer is_sale_helper;

	private Integer states;

	private Date sync_date;

	private Date last_modify_date;

	private Integer is_beian;

	private ShopPd shopPd;

	private BuyerInfo buyerInfo;

	private StdEntpMain stdEntpMain;

	private MmtShopCustomer mmtShopCustomer;

	public BuyerInfo getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(BuyerInfo buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public StdEntpMain getStdEntpMain() {
		return stdEntpMain;
	}

	public void setStdEntpMain(StdEntpMain stdEntpMain) {
		this.stdEntpMain = stdEntpMain;
	}

	public MmtShopCustomer getMmtShopCustomer() {
		return mmtShopCustomer;
	}

	public void setMmtShopCustomer(MmtShopCustomer mmtShopCustomer) {
		this.mmtShopCustomer = mmtShopCustomer;
	}

	public ShopPd getShopPd() {
		return shopPd;
	}

	public void setShopPd(ShopPd shopPd) {
		this.shopPd = shopPd;
	}

	public Integer getIs_beian() {
		return is_beian;
	}

	public void setIs_beian(Integer isBeian) {
		is_beian = isBeian;
	}

	public JdxxEntpSell() {

	}

	public Integer getIs_sale_helper() {
		return is_sale_helper;
	}

	public void setIs_sale_helper(Integer isSaleHelper) {
		is_sale_helper = isSaleHelper;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public Date getSync_date() {
		return sync_date;
	}

	public void setSync_date(Date syncDate) {
		sync_date = syncDate;
	}

	public Date getLast_modify_date() {
		return last_modify_date;
	}

	public void setLast_modify_date(Date lastModifyDate) {
		last_modify_date = lastModifyDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPd_number() {
		return pd_number;
	}

	public void setPd_number(Long pd_number) {
		this.pd_number = pd_number;
	}

	public Integer getPd_state() {
		return pd_state;
	}

	public void setPd_state(Integer pd_state) {
		this.pd_state = pd_state;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getPd_serial() {
		return pd_serial;
	}

	public void setPd_serial(String pd_serial) {
		this.pd_serial = pd_serial;
	}

	public Long getEntp_id() {
		return entp_id;
	}

	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public Long getSell_entp_id() {
		return sell_entp_id;
	}

	public void setSell_entp_id(Long sell_entp_id) {
		this.sell_entp_id = sell_entp_id;
	}

	public BigDecimal getPd_quote() {
		return pd_quote;
	}

	public void setPd_quote(BigDecimal pd_quote) {
		this.pd_quote = pd_quote;
	}

	public BigDecimal getPd_price() {
		return pd_price;
	}

	public void setPd_price(BigDecimal pd_price) {
		this.pd_price = pd_price;
	}

	public Long getSell_man_id() {
		return sell_man_id;
	}

	public void setSell_man_id(Long sell_man_id) {
		this.sell_man_id = sell_man_id;
	}

	public Date getSell_date() {
		return sell_date;
	}

	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}

	public String getSell_p_index() {
		return sell_p_index;
	}

	public void setSell_p_index(String sell_p_index) {
		this.sell_p_index = sell_p_index;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBuyer_addr() {
		return buyer_addr;
	}

	public void setBuyer_addr(String buyer_addr) {
		this.buyer_addr = buyer_addr;
	}

	public String getBuyer_link() {
		return buyer_link;
	}

	public void setBuyer_link(String buyer_link) {
		this.buyer_link = buyer_link;
	}

	public String getBuyer_mobile() {
		return buyer_mobile;
	}

	public void setBuyer_mobile(String buyer_mobile) {
		this.buyer_mobile = buyer_mobile;
	}

	public Long getVillage_p_index() {
		return village_p_index;
	}

	public void setVillage_p_index(Long village_p_index) {
		this.village_p_index = village_p_index;
	}

	public Long getSell_log_man() {
		return sell_log_man;
	}

	public void setSell_log_man(Long sell_log_man) {
		this.sell_log_man = sell_log_man;
	}

	public String getSell_log_memo() {
		return sell_log_memo;
	}

	public void setSell_log_memo(String sell_log_memo) {
		this.sell_log_memo = sell_log_memo;
	}

	public Date getAllow_date() {
		return allow_date;
	}

	public void setAllow_date(Date allow_date) {
		this.allow_date = allow_date;
	}

	public Long getAllow_ac_man() {
		return allow_ac_man;
	}

	public void setAllow_ac_man(Long allow_ac_man) {
		this.allow_ac_man = allow_ac_man;
	}

	public Integer getAllow_sign() {
		return allow_sign;
	}

	public void setAllow_sign(Integer allow_sign) {
		this.allow_sign = allow_sign;
	}

	public String getBuyer_bank_name() {
		return buyer_bank_name;
	}

	public void setBuyer_bank_name(String buyer_bank_name) {
		this.buyer_bank_name = buyer_bank_name;
	}

	public String getBuyer_bank_user() {
		return buyer_bank_user;
	}

	public void setBuyer_bank_user(String buyer_bank_user) {
		this.buyer_bank_user = buyer_bank_user;
	}

	public String getBuyer_bank_account() {
		return buyer_bank_account;
	}

	public void setBuyer_bank_account(String buyer_bank_account) {
		this.buyer_bank_account = buyer_bank_account;
	}

	public String getRpr_number() {
		return rpr_number;
	}

	public void setRpr_number(String rpr_number) {
		this.rpr_number = rpr_number;
	}

	public String getHousemaster() {
		return housemaster;
	}

	public void setHousemaster(String housemaster) {
		this.housemaster = housemaster;
	}

	public String getKin() {
		return kin;
	}

	public void setKin(String kin) {
		this.kin = kin;
	}

	public BigDecimal getBeh_allow_money() {
		return beh_allow_money;
	}

	public void setBeh_allow_money(BigDecimal beh_allow_money) {
		this.beh_allow_money = beh_allow_money;
	}

	public BigDecimal getReal_allow_money() {
		return real_allow_money;
	}

	public void setReal_allow_money(BigDecimal real_allow_money) {
		this.real_allow_money = real_allow_money;
	}

	public String getBuysb_name() {
		return buysb_name;
	}

	public void setBuysb_name(String buysb_name) {
		this.buysb_name = buysb_name;
	}

	public String getBuysb_id() {
		return buysb_id;
	}

	public void setBuysb_id(String buysb_id) {
		this.buysb_id = buysb_id;
	}

	public String getBuysb_addr() {
		return buysb_addr;
	}

	public void setBuysb_addr(String buysb_addr) {
		this.buysb_addr = buysb_addr;
	}

	public String getBuysb_link() {
		return buysb_link;
	}

	public void setBuysb_link(String buysb_link) {
		this.buysb_link = buysb_link;
	}

	public String getBuysb_mobile() {
		return buysb_mobile;
	}

	public void setBuysb_mobile(String buysb_mobile) {
		this.buysb_mobile = buysb_mobile;
	}

	public Integer getBack_sign() {
		return back_sign;
	}

	public void setBack_sign(Integer back_sign) {
		this.back_sign = back_sign;
	}

	public Integer getAllow_non() {
		return allow_non;
	}

	public void setAllow_non(Integer allow_non) {
		this.allow_non = allow_non;
	}

	/**
	 * @val 补贴比例
	 */
	public BigDecimal getSub_scale() {
		return sub_scale;
	}

	/**
	 * @val 补贴比例
	 */
	public void setSub_scale(BigDecimal sub_scale) {
		this.sub_scale = sub_scale;
	}

	/**
	 * @val 先行垫付标识
	 * @val 0：否[默认]
	 * @val 1：是
	 */
	public Integer getAdv_pay_sign() {
		return adv_pay_sign;
	}

	/**
	 * @val 先行垫付标识
	 * @val 0：否[默认]
	 * @val 1：是
	 */
	public void setAdv_pay_sign(Integer adv_pay_sign) {
		this.adv_pay_sign = adv_pay_sign;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date returnDate) {
		return_date = returnDate;
	}

	public String getAllow_memo() {
		return allow_memo;
	}

	public void setAllow_memo(String allowMemo) {
		allow_memo = allowMemo;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date addDate) {
		add_date = addDate;
	}

}