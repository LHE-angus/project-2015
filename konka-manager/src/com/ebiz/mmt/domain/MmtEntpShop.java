package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Code by AutoGenerated Builder
 * AutoGenerated Builder Version 2.1
 * @author Hui,Gang
 * @datetime 2011-09-29 15:54:29
 */
 public class MmtEntpShop extends BaseDomain implements Serializable {
 	
 	private static final long serialVersionUID = -1L;

	/**
	 * 商铺ID
	 */
    private Long shop_id;
           
    private Long entp_id;
           
	/**
	 * 商铺名称
	 */
    private String shop_name;
           
	/**
	 * 商铺公告
	 */
    private String shop_desc;
           
	/**
	 * 商铺类型SHOP_TYPE：
0：生产企业商铺
1：销售网点
2：维修网点
	 */
    private Long shop_type;
           
	/**
	 * 商铺LOGO
	 */
    private String logo_pic;
           
	/**
	 * 所属地区
	 */
    private Long p_index;
           
	/**
	 * 所属行业
	 */
    private String c_index;
           
	/**
	 * 主营产品
	 */
    private String main_pd;
           
	/**
	 * 是否是家电下乡商铺,1:是0:否
	 */
    private Long is_rural;
           
	/**
	 * 是否是以旧换新商铺,1:是0:否
	 */
    private Long is_otn;
           
	/**
	 * 0：未审核，1：审核已通过，-1：审核未通过
	 */
    private Long is_auth;
           
	/**
	 * 是否是销售网点,1:是0:否
	 */
    private Long is_sall;
           
	/**
	 * 是否是维修网点,1:是0:否
	 */
    private Long is_maint;
           
	/**
	 * 是否是回收网点,1:是0:否
	 */
    private Long is_callb;
           
	/**
	 * 信用等级
	 */
    private Long credit;
           
	/**
	 * 点击数量
	 */
    private Long access_num;
           
	/**
	 * 店主ID
	 */
    private Long host_id;
           
	/**
	 * 创建时间
	 */
    private Date add_date;
           
	/**
	 * 创建人ID
	 */
    private Long del_man2;
           
	/**
	 * 状态标志
	 */
    private Long state;
           
	/**
	 * 审核人
	 */
    private Long auditor_id;
           
	/**
	 * 审核时间
	 */
    private Date audit_date;
           
	/**
	 * 在线客服QQ
	 */
    private String online_qq;
           
	/**
	 * 支付宝账号(2+)
	 */
    private String alipay_email;
           
	/**
	 * 是否推荐
	 */
    private Long is_commend;
           
	/**
	 * 排序值
	 */
    private Long order_value;
           
	/**
	 * 单位：%
范围：0.00 ~ 99.99
	 */
    private Long royalty_rate;
           
    private String key_sequence;
           
    private Long o_count;
           
    private String login_settings;
           
    private Long p_count;
           
	/**
	 * 地理位置经度
	 */
    private BigDecimal g_lng;
           
	/**
	 * 地理位置纬度
	 */
    private BigDecimal g_lat;
           
	/**
	 * 联系人
	 */
    private String link_user;
           
	/**
	 * 联系电话
	 */
    private String link_phone;
           
	/**
	 * 商铺街道地址
	 */
    private String street_addr;
           
	/**
	 * 地区邮编
	 */
    private String post_code;
           
	/**
	 * 维修品类品牌,(PD_TYPE:BRAND_ID)*,
	 */
    private String main_pd2;
           
	/**
	 * STD_ENTP_MAIN_ID
	 */
    private Long std_entp_main_id;
           
	/**
	 * 0:普通，1：铜牌网点，2：银牌网点，3：金牌网点
	 */
    private Long shop_level;
           
	/**
	 * 临时地理位置纬度
	 */
    private BigDecimal g_lat_t;
           
	/**
	 * 临时地理位置经度
	 */
    private BigDecimal g_lng_t;
           
	/**
	 * 地理位置审核状态，0-待审核，1-审核不通过，2-审核通过，3-再次审核
	 */
    private Long g_is_audit;
           
	/**
	 * 地理位置纠错次数
	 */
    private Long g_re_count;
           
	/**
	 * MMT
	 */
    private Long mmt;
           
	public MmtEntpShop() {

	}
 
 	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	
	public Long getShop_id() {
		return shop_id;
	}
           
 	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}
	
	public Long getEntp_id() {
		return entp_id;
	}
           
 	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	public String getShop_name() {
		return shop_name;
	}
           
 	public void setShop_desc(String shop_desc) {
		this.shop_desc = shop_desc;
	}
	
	public String getShop_desc() {
		return shop_desc;
	}
           
 	public void setShop_type(Long shop_type) {
		this.shop_type = shop_type;
	}
	
	public Long getShop_type() {
		return shop_type;
	}
           
 	public void setLogo_pic(String logo_pic) {
		this.logo_pic = logo_pic;
	}
	
	public String getLogo_pic() {
		return logo_pic;
	}
           
 	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	public Long getP_index() {
		return p_index;
	}
           
 	public void setC_index(String c_index) {
		this.c_index = c_index;
	}
	
	public String getC_index() {
		return c_index;
	}
           
 	public void setMain_pd(String main_pd) {
		this.main_pd = main_pd;
	}
	
	public String getMain_pd() {
		return main_pd;
	}
           
 	public void setIs_rural(Long is_rural) {
		this.is_rural = is_rural;
	}
	
	public Long getIs_rural() {
		return is_rural;
	}
           
 	public void setIs_otn(Long is_otn) {
		this.is_otn = is_otn;
	}
	
	public Long getIs_otn() {
		return is_otn;
	}
           
 	public void setIs_auth(Long is_auth) {
		this.is_auth = is_auth;
	}
	
	public Long getIs_auth() {
		return is_auth;
	}
           
 	public void setIs_sall(Long is_sall) {
		this.is_sall = is_sall;
	}
	
	public Long getIs_sall() {
		return is_sall;
	}
           
 	public void setIs_maint(Long is_maint) {
		this.is_maint = is_maint;
	}
	
	public Long getIs_maint() {
		return is_maint;
	}
           
 	public void setIs_callb(Long is_callb) {
		this.is_callb = is_callb;
	}
	
	public Long getIs_callb() {
		return is_callb;
	}
           
 	public void setCredit(Long credit) {
		this.credit = credit;
	}
	
	public Long getCredit() {
		return credit;
	}
           
 	public void setAccess_num(Long access_num) {
		this.access_num = access_num;
	}
	
	public Long getAccess_num() {
		return access_num;
	}
           
 	public void setHost_id(Long host_id) {
		this.host_id = host_id;
	}
	
	public Long getHost_id() {
		return host_id;
	}
           
 	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
           
 	public void setDel_man2(Long del_man2) {
		this.del_man2 = del_man2;
	}
	
	public Long getDel_man2() {
		return del_man2;
	}
           
 	public void setState(Long state) {
		this.state = state;
	}
	
	public Long getState() {
		return state;
	}
           
 	public void setAuditor_id(Long auditor_id) {
		this.auditor_id = auditor_id;
	}
	
	public Long getAuditor_id() {
		return auditor_id;
	}
           
 	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	
	public Date getAudit_date() {
		return audit_date;
	}
           
 	public void setOnline_qq(String online_qq) {
		this.online_qq = online_qq;
	}
	
	public String getOnline_qq() {
		return online_qq;
	}
           
 	public void setAlipay_email(String alipay_email) {
		this.alipay_email = alipay_email;
	}
	
	public String getAlipay_email() {
		return alipay_email;
	}
           
 	public void setIs_commend(Long is_commend) {
		this.is_commend = is_commend;
	}
	
	public Long getIs_commend() {
		return is_commend;
	}
           
 	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}
	
	public Long getOrder_value() {
		return order_value;
	}
           
 	public void setRoyalty_rate(Long royalty_rate) {
		this.royalty_rate = royalty_rate;
	}
	
	public Long getRoyalty_rate() {
		return royalty_rate;
	}
           
 	public void setKey_sequence(String key_sequence) {
		this.key_sequence = key_sequence;
	}
	
	public String getKey_sequence() {
		return key_sequence;
	}
           
 	public void setO_count(Long o_count) {
		this.o_count = o_count;
	}
	
	public Long getO_count() {
		return o_count;
	}
           
 	public void setLogin_settings(String login_settings) {
		this.login_settings = login_settings;
	}
	
	public String getLogin_settings() {
		return login_settings;
	}
           
 	public void setP_count(Long p_count) {
		this.p_count = p_count;
	}
	
	public Long getP_count() {
		return p_count;
	}
           
 	public void setG_lng(BigDecimal g_lng) {
		this.g_lng = g_lng;
	}
	
	public BigDecimal getG_lng() {
		return g_lng;
	}
           
 	public void setG_lat(BigDecimal g_lat) {
		this.g_lat = g_lat;
	}
	
	public BigDecimal getG_lat() {
		return g_lat;
	}
           
 	public void setLink_user(String link_user) {
		this.link_user = link_user;
	}
	
	public String getLink_user() {
		return link_user;
	}
           
 	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}
	
	public String getLink_phone() {
		return link_phone;
	}
           
 	public void setStreet_addr(String street_addr) {
		this.street_addr = street_addr;
	}
	
	public String getStreet_addr() {
		return street_addr;
	}
           
 	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	
	public String getPost_code() {
		return post_code;
	}
           
 	public void setMain_pd2(String main_pd2) {
		this.main_pd2 = main_pd2;
	}
	
	public String getMain_pd2() {
		return main_pd2;
	}
           
 	public void setStd_entp_main_id(Long std_entp_main_id) {
		this.std_entp_main_id = std_entp_main_id;
	}
	
	public Long getStd_entp_main_id() {
		return std_entp_main_id;
	}
           
 	public void setShop_level(Long shop_level) {
		this.shop_level = shop_level;
	}
	
	public Long getShop_level() {
		return shop_level;
	}
           
 	public void setG_lat_t(BigDecimal g_lat_t) {
		this.g_lat_t = g_lat_t;
	}
	
	public BigDecimal getG_lat_t() {
		return g_lat_t;
	}
           
 	public void setG_lng_t(BigDecimal g_lng_t) {
		this.g_lng_t = g_lng_t;
	}
	
	public BigDecimal getG_lng_t() {
		return g_lng_t;
	}
           
 	public void setG_is_audit(Long g_is_audit) {
		this.g_is_audit = g_is_audit;
	}
	
	public Long getG_is_audit() {
		return g_is_audit;
	}
           
 	public void setG_re_count(Long g_re_count) {
		this.g_re_count = g_re_count;
	}
	
	public Long getG_re_count() {
		return g_re_count;
	}
           
 	public void setMmt(Long mmt) {
		this.mmt = mmt;
	}
	
	public Long getMmt() {
		return mmt;
	}
           
	@Override 
    public String toString() { 
   		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
 }