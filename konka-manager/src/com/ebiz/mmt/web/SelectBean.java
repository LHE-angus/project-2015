package com.ebiz.mmt.web;

import java.util.List;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-26
 */
public class SelectBean implements java.io.Serializable {

	private static final long serialVersionUID = -7144687799065299835L;

	String sell_date_start;// 开始时间

	String sell_date_end; // 结束时间

	String dept_id;// 分公司

	String l4_dept_id;// 经营部

	String l5_dept_id;// 办事处

	String ywy_user_name;// 业务员

	String customer_name;// 客户

	String size_sec;// 产品规格

	String model_name;// 产品型号

	String label_db;// 是否大板

	String label_int;// 是否智能

	// 分组标示
	// aa_1:分公司 , aa_2:经营部, aa_3:办事处 , aa_4:业务员 , aa_5:客户
	// bb_1:年度 , bb_2:季度 , bb_3:月度 , bb_4:周 , bb_5:日
	// cc_1:尺寸规格, cc_2:型号 , cc_3:是否大板, cc_4:是否智能
	// dd_1:客户分类大类, dd_2:客户分类明细
	String[] group_by_field;

	// 显示字段
	// ZD_1:R3销售量, ZD_2:R3销售额, ZD_3:零售量, ZD_4:零售额
	String[] show_field;

	String[] contrast;// 1:同比, 2:环比

	String group_flag_string = "";

	String show_field_string = "";

	List<?> entityList;

	int show_num = 0;

	public String getSell_date_start() {
		return sell_date_start;
	}

	public void setSell_date_start(String sell_date_start) {
		this.sell_date_start = sell_date_start;
	}

	public String getSell_date_end() {
		return sell_date_end;
	}

	public void setSell_date_end(String sell_date_end) {
		this.sell_date_end = sell_date_end;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getL4_dept_id() {
		return l4_dept_id;
	}

	public void setL4_dept_id(String l4_dept_id) {
		this.l4_dept_id = l4_dept_id;
	}

	public String getL5_dept_id() {
		return l5_dept_id;
	}

	public void setL5_dept_id(String l5_dept_id) {
		this.l5_dept_id = l5_dept_id;
	}

	public String getYwy_user_name() {
		return ywy_user_name;
	}

	public void setYwy_user_name(String ywy_user_name) {
		this.ywy_user_name = ywy_user_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getSize_sec() {
		return size_sec;
	}

	public void setSize_sec(String size_sec) {
		this.size_sec = size_sec;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getLabel_db() {
		return label_db;
	}

	public void setLabel_db(String label_db) {
		this.label_db = label_db;
	}

	public String getLabel_int() {
		return label_int;
	}

	public void setLabel_int(String label_int) {
		this.label_int = label_int;
	}

	public String[] getGroup_by_field() {
		return group_by_field;
	}

	public void setGroup_by_field(String[] group_by_field) {
		this.group_by_field = group_by_field;
	}

	public String[] getShow_field() {
		return show_field;
	}

	public void setShow_field(String[] show_field) {
		this.show_field = show_field;
	}

	public String[] getContrast() {
		return contrast;
	}

	public void setContrast(String[] contrast) {
		this.contrast = contrast;
	}

	public String getGroup_flag_string() {
		return group_flag_string;
	}

	public void setGroup_flag_string(String group_flag_string) {
		this.group_flag_string = group_flag_string;
	}

	public String getShow_field_string() {
		return show_field_string;
	}

	public void setShow_field_string(String show_field_string) {
		this.show_field_string = show_field_string;
	}

	public int getShow_num() {
		return show_num;
	}

	public void setShow_num(int show_num) {
		this.show_num = show_num;
	}

	public List<?> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<?> entityList) {
		this.entityList = entityList;
	}

}