package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-08-20 16:45:34
 */
public class JxcSellBillAttachments extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long sell_bill_id;
	
	private String file_name;
	
	private String file_desc;
	
	private String save_name;
	
	private String save_path;
	
	private Integer source;
	
	private String add_user_name;
	
	private Date add_date;
	
	private Integer is_del;
	
	public JxcSellBillAttachments() {

	}

	/**
	 * @val 附件ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 附件ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 销售单ID
	 */
	public Long getSell_bill_id() {
		return sell_bill_id;
	}
	
	/**
	 * @val 销售单ID
	 */
	public void setSell_bill_id(Long sell_bill_id) {
		this.sell_bill_id = sell_bill_id;
	}
	
	/**
	 * @val 文件名
	 */
	public String getFile_name() {
		return file_name;
	}
	
	/**
	 * @val 文件名
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	/**
	 * @val 文件说明
	 */
	public String getFile_desc() {
		return file_desc;
	}
	
	/**
	 * @val 文件说明
	 */
	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}
	
	/**
	 * @val 存储文件名
	 */
	public String getSave_name() {
		return save_name;
	}
	
	/**
	 * @val 存储文件名
	 */
	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}
	
	/**
	 * @val 存储路径
	 */
	public String getSave_path() {
		return save_path;
	}
	
	/**
	 * @val 存储路径
	 */
	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}
	
	/**
	 * @val 来源:1-买卖商通进销存，2-康佳进销存
	 */
	public Integer getSource() {
		return source;
	}
	
	/**
	 * @val 来源:1-买卖商通进销存，2-康佳进销存
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	
	/**
	 * @val 添加人
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
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
	 * @val 是否删除：0-未删除，1-已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除：0-未删除，1-已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}