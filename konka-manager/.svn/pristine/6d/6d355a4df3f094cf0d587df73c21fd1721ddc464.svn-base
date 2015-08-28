package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-16 17:19:03
 */
public class PeShopMsg extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long par_id;

	/**
	 * 发布地区
	 */
	private Long pub_p_index;

	/**
	 * 投放网点类别
	 */
	private Long shop_category_id;

	private String title;

	private String content;

	private Integer state;

	private Integer send_user_type;

	private Long send_user_id;

	private String send_user_name;

	private Date send_date;

	private String send_ip;

	private Integer receive_user_type;

	private Long receive_user_id;

	private String areas_ids;

	private String areas_names;

	private List<KonkaPePublicScope> konkaPePublicScopeList;
	
	private List<KonkaPePublicScope> konkaPePublicScopeListTemp;//临时存放 供编辑时删除此表记录使用

	public List<KonkaPePublicScope> getKonkaPePublicScopeListTemp() {
		return konkaPePublicScopeListTemp;
	}

	public void setKonkaPePublicScopeListTemp(List<KonkaPePublicScope> konkaPePublicScopeListTemp) {
		this.konkaPePublicScopeListTemp = konkaPePublicScopeListTemp;
	}

	/**
	 * 是否删除：0-未删除，1-已删除
	 */
	private Long is_del;

	public Long getIs_del() {
		return is_del;
	}

	public void setIs_del(Long is_del) {
		this.is_del = is_del;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	/**
	 * 删除时间
	 */
	private Date del_date;

	public Long getPub_p_index() {
		return pub_p_index;
	}

	public void setPub_p_index(Long pub_p_index) {
		this.pub_p_index = pub_p_index;
	}

	public Long getShop_category_id() {
		return shop_category_id;
	}

	public void setShop_category_id(Long shop_category_id) {
		this.shop_category_id = shop_category_id;
	}

	private String receive_user_name;

	private Date add_date;

	public PeShopMsg() {

	}

	/**
	 * @val 站内信ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 站内信ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 父站内信ID：首次发送为0
	 */
	public Long getPar_id() {
		return par_id;
	}

	/**
	 * @val 父站内信ID：首次发送为0
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	/**
	 * @val 信息标题：不能超过30个汉字
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @val 信息标题：不能超过30个汉字
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @val 信息内容：不超过500个汉字
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @val 信息内容：不超过500个汉字
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @val 信息状态：0-暂存，1-已发送，2-已查看，3-已回复
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 信息状态：0-暂存，1-已发送，2-已查看，3-已回复
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 发送人类型：0-网点用户，1-生产企业用户
	 */
	public Integer getSend_user_type() {
		return send_user_type;
	}

	/**
	 * @val 发送人类型：0-网点用户，1-生产企业用户
	 */
	public void setSend_user_type(Integer send_user_type) {
		this.send_user_type = send_user_type;
	}

	/**
	 * @val 发送人ID：PE_PROD_USER.ID（1-生产企业用户） 或 USER_INFO.ID（0-网点用户）
	 */
	public Long getSend_user_id() {
		return send_user_id;
	}

	/**
	 * @val 发送人ID：PE_PROD_USER.ID（1-生产企业用户） 或 USER_INFO.ID（0-网点用户）
	 */
	public void setSend_user_id(Long send_user_id) {
		this.send_user_id = send_user_id;
	}

	/**
	 * @val 发件人名称：不超过100个汉字
	 */
	public String getSend_user_name() {
		return send_user_name;
	}

	/**
	 * @val 发件人名称：不超过100个汉字
	 */
	public void setSend_user_name(String send_user_name) {
		this.send_user_name = send_user_name;
	}

	/**
	 * @val 发送时间
	 */
	public Date getSend_date() {
		return send_date;
	}

	/**
	 * @val 发送时间
	 */
	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	/**
	 * @val 发送客户端IP
	 */
	public String getSend_ip() {
		return send_ip;
	}

	/**
	 * @val 发送客户端IP
	 */
	public void setSend_ip(String send_ip) {
		this.send_ip = send_ip;
	}

	/**
	 * @val 接收人用户类型：0-网点用户，1-生产企业用户
	 */
	public Integer getReceive_user_type() {
		return receive_user_type;
	}

	/**
	 * @val 接收人用户类型：0-网点用户，1-生产企业用户
	 */
	public void setReceive_user_type(Integer receive_user_type) {
		this.receive_user_type = receive_user_type;
	}

	/**
	 * @val 接收人ID
	 */
	public Long getReceive_user_id() {
		return receive_user_id;
	}

	/**
	 * @val 接收人ID
	 */
	public void setReceive_user_id(Long receive_user_id) {
		this.receive_user_id = receive_user_id;
	}

	/**
	 * @val 接收人名称：不超过100个汉字
	 */
	public String getReceive_user_name() {
		return receive_user_name;
	}

	/**
	 * @val 接收人名称：不超过100个汉字
	 */
	public void setReceive_user_name(String receive_user_name) {
		this.receive_user_name = receive_user_name;
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

	public List<KonkaPePublicScope> getKonkaPePublicScopeList() {
		return konkaPePublicScopeList;
	}

	public void setKonkaPePublicScopeList(List<KonkaPePublicScope> konkaPePublicScopeList) {
		this.konkaPePublicScopeList = konkaPePublicScopeList;
	}

	public String getAreas_ids() {
		return areas_ids;
	}

	public void setAreas_ids(String areas_ids) {
		this.areas_ids = areas_ids;
	}

	public String getAreas_names() {
		return areas_names;
	}

	public void setAreas_names(String areas_names) {
		this.areas_names = areas_names;
	}

}