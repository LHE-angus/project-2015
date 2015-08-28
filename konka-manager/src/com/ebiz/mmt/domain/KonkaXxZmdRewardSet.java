package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public class KonkaXxZmdRewardSet extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long dept_id;
	
	private Long zmd_id;
	
	private Long reward_type;
	
	private BigDecimal reward_ratio;
	
	private Integer is_enabled;
	
	private Integer is_locked;
	
	private Long set_user_id;
	
	private Long update_user_id;
	
	private Date last_update_time;
	
	public KonkaXxZmdRewardSet() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @val 反佣类型 详见KONKA_XX_BASE_TYPE
	 */
	public Long getReward_type() {
		return reward_type;
	}
	
	/**
	 * @val 反佣类型 详见KONKA_XX_BASE_TYPE
	 */
	public void setReward_type(Long reward_type) {
		this.reward_type = reward_type;
	}
	
	/**
	 * @val 反佣比例（%）
	 */
	public BigDecimal getReward_ratio() {
		return reward_ratio;
	}
	
	/**
	 * @val 反佣比例（%）
	 */
	public void setReward_ratio(BigDecimal reward_ratio) {
		this.reward_ratio = reward_ratio;
	}
	
	/**
	 * @val 是否启用 0-不启用 1-启用（默认）
	 */
	public Integer getIs_enabled() {
		return is_enabled;
	}
	
	/**
	 * @val 是否启用 0-不启用 1-启用（默认）
	 */
	public void setIs_enabled(Integer is_enabled) {
		this.is_enabled = is_enabled;
	}
	
	/**
	 * @val 是否锁定 0-未锁定 1-已锁定
	 */
	public Integer getIs_locked() {
		return is_locked;
	}
	
	/**
	 * @val 是否锁定 0-未锁定 1-已锁定
	 */
	public void setIs_locked(Integer is_locked) {
		this.is_locked = is_locked;
	}
	
	/**
	 * @val 设置人
	 */
	public Long getSet_user_id() {
		return set_user_id;
	}
	
	/**
	 * @val 设置人
	 */
	public void setSet_user_id(Long set_user_id) {
		this.set_user_id = set_user_id;
	}
	
	/**
	 * @val 修改人
	 */
	public Long getUpdate_user_id() {
		return update_user_id;
	}
	
	/**
	 * @val 修改人
	 */
	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}
	
	/**
	 * @val 最后修改时间
	 */
	public Date getLast_update_time() {
		return last_update_time;
	}
	
	/**
	 * @val 最后修改时间
	 */
	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}
	
}