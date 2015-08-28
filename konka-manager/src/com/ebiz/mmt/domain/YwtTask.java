package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public class YwtTask extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long user_id;
	
	private String real_name;
	
	private Date add_date;
	
	private Long par_task_id;
	
	private String par_task_name;
	
	private String task_name;
	
	private Long priority;
	
	private Long task_type;
	
	private String content;
	
	private String remark;
	
	private Date finsh_date;
	
	private Long task_state;
	
	private Long dept_id;
	
	private Long assigned_user_id;
	
	private Long is_del;
	
	private Long del_user_id;
	
	private Date del_date;
	
	private String task_reulst;
	
	private String conclusion;
	
	private Date begin_date;
	
	private List<YwtTaskReceive> ywtTaskReceive;
	private List<Attachment> attachment;
	public List<YwtTaskReceive> getYwtTaskReceive() {
		return ywtTaskReceive;
	}

	public void setYwtTaskReceive(List<YwtTaskReceive> ywtTaskReceive) {
		this.ywtTaskReceive = ywtTaskReceive;
	}
	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	public YwtTask() {

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
	 * @val 创建人ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 创建人ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 申请人
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * @val 申请人
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	/**
	 * @val 创建日期
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 创建日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 父任务ID
	 */
	public Long getPar_task_id() {
		return par_task_id;
	}
	
	/**
	 * @val 父任务ID
	 */
	public void setPar_task_id(Long par_task_id) {
		this.par_task_id = par_task_id;
	}
	
	/**
	 * @val 父任务名称
	 */
	public String getPar_task_name() {
		return par_task_name;
	}
	
	/**
	 * @val 父任务名称
	 */
	public void setPar_task_name(String par_task_name) {
		this.par_task_name = par_task_name;
	}
	
	/**
	 * @val 任务名称
	 */
	public String getTask_name() {
		return task_name;
	}
	
	/**
	 * @val 任务名称
	 */
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	
	/**
	 * @val 优先级 高中低
	 */
	public Long getPriority() {
		return priority;
	}
	
	/**
	 * @val 优先级 高中低
	 */
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
	/**
	 * @val 任务类型
	 */
	public Long getTask_type() {
		return task_type;
	}
	
	/**
	 * @val 任务类型
	 */
	public void setTask_type(Long task_type) {
		this.task_type = task_type;
	}
	
	/**
	 * @val 任务内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 任务内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 完成期限
	 */
	public Date getFinsh_date() {
		return finsh_date;
	}
	
	/**
	 * @val 完成期限
	 */
	public void setFinsh_date(Date finsh_date) {
		this.finsh_date = finsh_date;
	}
	
	/**
	 * @val 任务状态 0：未开始/1：进行中/2：已完成
	 */
	public Long getTask_state() {
		return task_state;
	}
	
	/**
	 * @val 任务状态 0：未开始/1：进行中/2：已完成
	 */
	public void setTask_state(Long task_state) {
		this.task_state = task_state;
	}
	
	/**
	 * @val 部门ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 部门ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 任务交办人
	 */
	public Long getAssigned_user_id() {
		return assigned_user_id;
	}
	
	/**
	 * @val 任务交办人
	 */
	public void setAssigned_user_id(Long assigned_user_id) {
		this.assigned_user_id = assigned_user_id;
	}
	
	/**
	 * @val 是否删除
	 */
	public Long getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除
	 */
	public void setIs_del(Long is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 删除人
	 */
	public Long getDel_user_id() {
		return del_user_id;
	}
	
	/**
	 * @val 删除人
	 */
	public void setDel_user_id(Long del_user_id) {
		this.del_user_id = del_user_id;
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
	 * @val 办理结果
	 */
	public String getTask_reulst() {
		return task_reulst;
	}
	
	/**
	 * @val 办理结果
	 */
	public void setTask_reulst(String task_reulst) {
		this.task_reulst = task_reulst;
	}
	
	/**
	 * @val 总结
	 */
	public String getConclusion() {
		return conclusion;
	}
	
	/**
	 * @val 总结
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	/**
	 * @val 开始时间
	 */
	public Date getBegin_date() {
		return begin_date;
	}
	
	/**
	 * @val 开始时间
	 */
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	
}