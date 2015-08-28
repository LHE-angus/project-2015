package com.ebiz.mmt.domain;

/**
 * 客户.业务任务导入
 * 
 * @author zhou
 * @since 20140806
 */
public class TaskExcelDTO {

	private String deptsn;
	private String deptname;
	private String customercode;
	private String customername;
	private String ywyid;
	private String ywyname;
	private String tasktype;
	private String taskxs;
	private String tasked;
	private Integer year;
	private Integer month;
	private String desc;

	public String getDeptsn() {
		return deptsn;
	}

	public void setDeptsn(String deptsn) {
		this.deptsn = deptsn;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getYwyid() {
		return ywyid;
	}

	public void setYwyid(String ywyid) {
		this.ywyid = ywyid;
	}

	public String getYwyname() {
		return ywyname;
	}

	public void setYwyname(String ywyname) {
		this.ywyname = ywyname;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getTaskxs() {
		return taskxs;
	}

	public void setTaskxs(String taskxs) {
		this.taskxs = taskxs;
	}

	public String getTasked() {
		return tasked;
	}

	public void setTasked(String tasked) {
		this.tasked = tasked;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static void main(String[] args) {

		String s = "0.02";
		//System.out.println(Double.valueOf(s));

	}
}
