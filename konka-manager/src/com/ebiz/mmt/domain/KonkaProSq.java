package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-10-25 12:36:17
 */
public class KonkaProSq extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String file_no;

	private Long dept_id;

	private String tbr_name;

	private Integer area_id;

	private String fzr_name;

	private String fzr_tel;

	private String fgs_name;

	private String fgs_tel;

	private String pro_name;

	private Date zb_date;

	private String remark;

	private Date add_date;
	
	//文件提交状态：0-保存，1-提交,2-审核中，3-审核通过，4-驳回,5-驳回到申请人
	private Integer file_state;

	private Integer is_del;

	private Integer bb_state;

	private Integer pro_state;

	private String sb_remark;

	private String zc_remark;

	private Long add_user_id;

	private String add_user_name;

	private List<FighterInfo> fighterInfoList;
	
	private Integer is_support;
	
	private String support_content;
	
	//审核记录
	private KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis;
	
	private List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList;

	public KonkaProSq() {

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
	 * @val 分公司
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 分公司
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 投标人名称
	 */
	public String getTbr_name() {
		return tbr_name;
	}

	/**
	 * @val 投标人名称
	 */
	public void setTbr_name(String tbr_name) {
		this.tbr_name = tbr_name;
	}

	/**
	 * @val 区域
	 */
	public Integer getArea_id() {
		return area_id;
	}

	/**
	 * @val 区域
	 */
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

	/**
	 * @val 负责人
	 */
	public String getFzr_name() {
		return fzr_name;
	}

	/**
	 * @val 负责人
	 */
	public void setFzr_name(String fzr_name) {
		this.fzr_name = fzr_name;
	}

	/**
	 * @val 联系电话（负责人）
	 */
	public String getFzr_tel() {
		return fzr_tel;
	}

	/**
	 * @val 联系电话（负责人）
	 */
	public void setFzr_tel(String fzr_tel) {
		this.fzr_tel = fzr_tel;
	}

	/**
	 * @val 分公司联系人
	 */
	public String getFgs_name() {
		return fgs_name;
	}

	/**
	 * @val 分公司联系人
	 */
	public void setFgs_name(String fgs_name) {
		this.fgs_name = fgs_name;
	}

	/**
	 * @val 联系电话（分公司联系人）
	 */
	public String getFgs_tel() {
		return fgs_tel;
	}

	/**
	 * @val 联系电话（分公司联系人）
	 */
	public void setFgs_tel(String fgs_tel) {
		this.fgs_tel = fgs_tel;
	}

	/**
	 * @val 项目名称
	 */
	public String getPro_name() {
		return pro_name;
	}

	/**
	 * @val 项目名称
	 */
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	/**
	 * @val 招标时间
	 */
	public Date getZb_date() {
		return zb_date;
	}

	/**
	 * @val 招标时间
	 */
	public void setZb_date(Date zb_date) {
		this.zb_date = zb_date;
	}

	/**
	 * @val 项目进展情况备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @val 项目进展情况备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @val 文件提交状态：0-保存，1-提交,2-审核中，3-审核通过，4-驳回
	 */
	public Integer getFile_state() {
		return file_state;
	}

	/**
	 * @val 文件提交状态：0-保存，1-提交，2-审核中，3-审核通过，4-驳回
	 */
	public void setFile_state(Integer file_state) {
		this.file_state = file_state;
	}

	/**
	 * @val 是否删除：0-否，1-是
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除：0-否，1-是
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 是否报备完成：0-否，1-是
	 */
	public Integer getBb_state() {
		return bb_state;
	}

	/**
	 * @val 是否报备完成：0-否，1-是
	 */
	public void setBb_state(Integer bb_state) {
		this.bb_state = bb_state;
	}

	/**
	 * @val 项目状态：0-报备中，1-跟踪中，2-已完结，3-已取消
	 */
	public Integer getPro_state() {
		return pro_state;
	}

	/**
	 * @val 项目状态：0-报备中，1-跟踪中，2-已完结，3-已取消
	 */
	public void setPro_state(Integer pro_state) {
		this.pro_state = pro_state;
	}

	/**
	 * @val 所需设备及其他要求
	 */
	public String getSb_remark() {
		return sb_remark;
	}

	/**
	 * @val 所需设备及其他要求
	 */
	public void setSb_remark(String sb_remark) {
		this.sb_remark = sb_remark;
	}

	/**
	 * @val 支持及其他说明
	 */
	public String getZc_remark() {
		return zc_remark;
	}

	/**
	 * @val 支持及其他说明
	 */
	public void setZc_remark(String zc_remark) {
		this.zc_remark = zc_remark;
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
	 * @val 添加人姓名
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}

	/**
	 * @val 添加人姓名
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}

	public List<FighterInfo> getFighterInfoList() {
		return fighterInfoList;
	}

	public void setFighterInfoList(List<FighterInfo> fighterInfoList) {
		this.fighterInfoList = fighterInfoList;
	}

	public KonkaXxZmdAuditUserHis getKonkaXxZmdAuditUserHis()
	{
		return konkaXxZmdAuditUserHis;
	}

	public void setKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis)
	{
		this.konkaXxZmdAuditUserHis = konkaXxZmdAuditUserHis;
	}

	public List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisList()
	{
		return konkaXxZmdAuditUserHisList;
	}

	public void setKonkaXxZmdAuditUserHisList(List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList)
	{
		this.konkaXxZmdAuditUserHisList = konkaXxZmdAuditUserHisList;
	}

	/**
	 * @val 是否需要总部支持
	 */
	public Integer getIs_support()
	{
		return is_support;
	}

	public void setIs_support(Integer is_support)
	{
		this.is_support = is_support;
	}
	/**
	 * @val 支持内容
	 */
	public String getSupport_content()
	{
		return support_content;
	}

	public void setSupport_content(String support_content)
	{
		this.support_content = support_content;
	}

}