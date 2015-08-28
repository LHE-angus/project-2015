package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-25 15:36:04
 */
public class EcGroupBuyMain extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String title;

	private String brief;

	private String content;

	private String auction_spec;

	private String auction_memo;

	private String main_pic;

	private Integer view_counts;

	private Long goods_id;

	private String goods_name;

	private Date auction_start_time;

	private Date auction_end_time;

	private Long shop_id;

	private String shop_name;

	private String addr;

	private String tel;

	private Integer need_addr;

	private Integer need_mobile;

	private Integer auction_state;

	private String auction_idea;

	private Long p_index;

	private Integer order_value;

	private Date add_date;

	private Long add_user_id;

	private Integer is_del;

	private Integer is_pub;

	private Integer own_sys;

	private Date exp_date;// 有效期

	public EcGroupBuyMain() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuction_spec() {
		return auction_spec;
	}

	public void setAuction_spec(String auction_spec) {
		this.auction_spec = auction_spec;
	}

	public String getAuction_memo() {
		return auction_memo;
	}

	public void setAuction_memo(String auction_memo) {
		this.auction_memo = auction_memo;
	}

	public String getMain_pic() {
		return main_pic;
	}

	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	public Integer getView_counts() {
		return view_counts;
	}

	public void setView_counts(Integer view_counts) {
		this.view_counts = view_counts;
	}

	public Long getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Date getAuction_start_time() {
		return auction_start_time;
	}

	public void setAuction_start_time(Date auction_start_time) {
		this.auction_start_time = auction_start_time;
	}

	public Date getAuction_end_time() {
		return auction_end_time;
	}

	public void setAuction_end_time(Date auction_end_time) {
		this.auction_end_time = auction_end_time;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public Integer getNeed_addr() {
		return need_addr;
	}

	public void setNeed_addr(Integer need_addr) {
		this.need_addr = need_addr;
	}

	public Integer getNeed_mobile() {
		return need_mobile;
	}

	public void setNeed_mobile(Integer need_mobile) {
		this.need_mobile = need_mobile;
	}

	public Integer getAuction_state() {
		return auction_state;
	}

	public void setAuction_state(Integer auction_state) {
		this.auction_state = auction_state;
	}

	public String getAuction_idea() {
		return auction_idea;
	}

	public void setAuction_idea(String auction_idea) {
		this.auction_idea = auction_idea;
	}

	public Long getP_index() {
		return p_index;
	}

	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
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

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Integer getIs_pub() {
		return is_pub;
	}

	public void setIs_pub(Integer is_pub) {
		this.is_pub = is_pub;
	}

	public Integer getOwn_sys() {
		return own_sys;
	}

	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date expDate) {
		exp_date = expDate;
	}

}