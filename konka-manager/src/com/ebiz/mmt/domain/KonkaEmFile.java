package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
public class KonkaEmFile extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String file_no;

	private String file_title;

	private Integer file_type;// 10:3C证书、20:获奖证书、30:检测报告、40:产品参数、50:库存、60:生产计划、70:其他

	private Integer file_xf_type;

	private Integer order_value;

	private Integer is_del;

	private Long add_user_id;

	private String add_user_name;

	private Date add_time;

	private Long mod_id;

	private List<Attachment> attachmentList;

	private KonkaEmFileContent konkaEmFileContent;

	private List<KonkaEmFileReceiveUser> konkaEmFileReceiveUserList;

	public KonkaEmFile() {

	}

	/**
	 * @val 序号
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 序号
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 文件编号
	 */
	public String getFile_no() {
		return file_no;
	}

	/**
	 * @val 文件编号
	 */
	public void setFile_no(String file_no) {
		this.file_no = file_no;
	}

	/**
	 * @val 文件标题
	 */
	public String getFile_title() {
		return file_title;
	}

	/**
	 * @val 文件标题
	 */
	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	/**
	 * @val 文件类型
	 */
	public Integer getFile_type() {
		return file_type;
	}

	/**
	 * @val 文件类型
	 */
	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	/**
	 * @val 下发类型：0-全部，1-角色，2-个人
	 */
	public Integer getFile_xf_type() {
		return file_xf_type;
	}

	/**
	 * @val 下发类型：0-全部，1-角色，2-个人
	 */
	public void setFile_xf_type(Integer file_xf_type) {
		this.file_xf_type = file_xf_type;
	}

	/**
	 * @val 排序值
	 */
	public Integer getOrder_value() {
		return order_value;
	}

	/**
	 * @val 排序值
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
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
	public Date getAdd_time() {
		return add_time;
	}

	/**
	 * @val 添加时间
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	/**
	 * @val 模块ID
	 */
	public Long getMod_id() {
		return mod_id;
	}

	/**
	 * @val 模块ID
	 */
	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public KonkaEmFileContent getKonkaEmFileContent() {
		return konkaEmFileContent;
	}

	public void setKonkaEmFileContent(KonkaEmFileContent konkaEmFileContent) {
		this.konkaEmFileContent = konkaEmFileContent;
	}

	public List<KonkaEmFileReceiveUser> getKonkaEmFileReceiveUserList() {
		return konkaEmFileReceiveUserList;
	}

	public void setKonkaEmFileReceiveUserList(List<KonkaEmFileReceiveUser> konkaEmFileReceiveUserList) {
		this.konkaEmFileReceiveUserList = konkaEmFileReceiveUserList;
	}

}