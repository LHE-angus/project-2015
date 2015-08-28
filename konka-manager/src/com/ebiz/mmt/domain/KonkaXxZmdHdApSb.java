package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-20 23:11:05
 */
public class KonkaXxZmdHdApSb extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long sp_hd_id;

	private Integer sb_type;

	private Long hd_id;

	private Long dept_id;

	private Long zmd_id;

	private String hd_addr;

	private BigDecimal plan_money;

	private Long plan_res_num;

	private Date plan_start_date;

	private Date plan_end_date;

	private BigDecimal plan_outputs_money;

	private BigDecimal real_money;

	private Long real_res_num;

	private BigDecimal real_outputs_money;

	private Long add_user_id;

	private Date add_date;

	private Date real_start_date;

	private Date real_end_date;

	private String plan_user_name;

	private String plan_user_tel;

	private String hd_name;

	private List<KonkaXxZmdSpPlanRes> konkaXxZmdSpPlanResList;

	public KonkaXxZmdHdApSb() {

	}

	/**
	 * @val 上报活动ID
	 */
	public Long getSp_hd_id() {
		return sp_hd_id;
	}

	/**
	 * @val 上报活动ID
	 */
	public void setSp_hd_id(Long sp_hd_id) {
		this.sp_hd_id = sp_hd_id;
	}

	/**
	 * @val 上报类型
	 */
	public Integer getSb_type() {
		return sb_type;
	}

	/**
	 * @val 上报类型
	 */
	public void setSb_type(Integer sb_type) {
		this.sb_type = sb_type;
	}

	/**
	 * @val 活动ID
	 */
	public Long getHd_id() {
		return hd_id;
	}

	/**
	 * @val 活动ID
	 */
	public void setHd_id(Long hd_id) {
		this.hd_id = hd_id;
	}

	/**
	 * @val 分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 专卖店ID
	 */
	public Long getZmd_id() {
		return zmd_id;
	}

	/**
	 * @val 专卖店ID
	 */
	public void setZmd_id(Long zmd_id) {
		this.zmd_id = zmd_id;
	}

	/**
	 * @val 活动地点
	 */
	public String getHd_addr() {
		return hd_addr;
	}

	/**
	 * @val 活动地点
	 */
	public void setHd_addr(String hd_addr) {
		this.hd_addr = hd_addr;
	}

	/**
	 * @val 实际投入总费用
	 */
	public BigDecimal getPlan_money() {
		return plan_money;
	}

	/**
	 * @val 实际投入总费用
	 */
	public void setPlan_money(BigDecimal plan_money) {
		this.plan_money = plan_money;
	}

	/**
	 * @val 实际投入资源ID
	 */
	public Long getPlan_res_num() {
		return plan_res_num;
	}

	/**
	 * @val 实际投入资源ID
	 */
	public void setPlan_res_num(Long plan_res_num) {
		this.plan_res_num = plan_res_num;
	}

	/**
	 * @val 活动计划起始时间
	 */
	public Date getPlan_start_date() {
		return plan_start_date;
	}

	/**
	 * @val 活动计划起始时间
	 */
	public void setPlay_start_date(Date plan_start_date) {
		this.plan_start_date = plan_start_date;
	}

	/**
	 * @val 活动计划结束时间
	 */
	public Date getPlan_end_date() {
		return plan_end_date;
	}

	/**
	 * @val 活动计划结束时间
	 */
	public void setPlay_end_date(Date plan_end_date) {
		this.plan_end_date = plan_end_date;
	}

	/**
	 * @val 计划产出
	 */
	public BigDecimal getPlan_outputs_money() {
		return plan_outputs_money;
	}

	/**
	 * @val 计划产出
	 */
	public void setPlan_outputs_money(BigDecimal plan_outputs_money) {
		this.plan_outputs_money = plan_outputs_money;
	}

	/**
	 * @val 实际投入总费用
	 */
	public BigDecimal getReal_money() {
		return real_money;
	}

	/**
	 * @val 实际投入总费用
	 */
	public void setReal_money(BigDecimal real_money) {
		this.real_money = real_money;
	}

	/**
	 * @val 实际投入资源ID
	 */
	public Long getReal_res_num() {
		return real_res_num;
	}

	/**
	 * @val 实际投入资源ID
	 */
	public void setReal_res_num(Long real_res_num) {
		this.real_res_num = real_res_num;
	}

	/**
	 * @val 实际产出
	 */
	public BigDecimal getReal_outputs_money() {
		return real_outputs_money;
	}

	/**
	 * @val 实际产出
	 */
	public void setReal_outputs_money(BigDecimal real_outputs_money) {
		this.real_outputs_money = real_outputs_money;
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
	 * @val 活动实际起始时间
	 */
	public Date getReal_start_date() {
		return real_start_date;
	}

	/**
	 * @val 活动实际起始时间
	 */
	public void setReal_start_date(Date real_start_date) {
		this.real_start_date = real_start_date;
	}

	/**
	 * @val 活动实际结束时间
	 */
	public Date getReal_end_date() {
		return real_end_date;
	}

	/**
	 * @val 活动实际结束时间
	 */
	public void setReal_end_date(Date real_end_date) {
		this.real_end_date = real_end_date;
	}

	/**
	 * @val 计划负责人
	 */
	public String getPlan_user_name() {
		return plan_user_name;
	}

	/**
	 * @val 计划负责人
	 */
	public void setPlan_user_name(String plan_user_name) {
		this.plan_user_name = plan_user_name;
	}

	/**
	 * @val 计划负责人电话
	 */
	public String getPlan_user_tel() {
		return plan_user_tel;
	}

	/**
	 * @val 计划负责人电话
	 */
	public void setPlan_user_tel(String plan_user_tel) {
		this.plan_user_tel = plan_user_tel;
	}

	public void setKonkaXxZmdSpPlanResList(List<KonkaXxZmdSpPlanRes> konkaXxZmdSpPlanResList) {
		this.konkaXxZmdSpPlanResList = konkaXxZmdSpPlanResList;
	}

	public List<KonkaXxZmdSpPlanRes> getKonkaXxZmdSpPlanResList() {
		return konkaXxZmdSpPlanResList;
	}

	/**
	 * @val 活动名称
	 */
	public void setHd_name(String hd_name) {
		this.hd_name = hd_name;
	}

	/**
	 * @val 活动名称
	 */
	public String getHd_name() {
		return hd_name;
	}

}