package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
public class KonkaDept extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long dept_id;
	
	private String dept_sn;
	
	private String dept_name;
	
	private Long par_id;
	
	private String dept_desc;
	
	private Integer dept_type;
	
	private Integer p_area;
	
	private Long p_index;
	
	private String p_name;
	
	private Long leader_user_id;
	
	private String m_areas_ids;

	private String m_areas_names;
	
	private Integer order_value;
	
	private Long add_user_id;
	
	private Date add_date;
	
	private Long pd_id;
	
	private Integer jb_type;
	
	private Integer is_del;

	private List<TaskPara> taskParaList;
	
	public KonkaDept() {

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
	 * @val 部门编码
	 */
	public String getDept_sn() {
		return dept_sn;
	}
	
	/**
	 * @val 部门编码
	 */
	public void setDept_sn(String dept_sn) {
		this.dept_sn = dept_sn;
	}
	
	/**
	 * @val 部门名称
	 */
	public String getDept_name() {
		return dept_name;
	}
	
	/**
	 * @val 部门名称
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	/**
	 * @val 父部门，0表示总部
	 */
	public Long getPar_id() {
		return par_id;
	}
	
	/**
	 * @val 父部门，0表示总部
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}
	
	/**
	 * @val 部门描述，不超过200个汉字
	 */
	public String getDept_desc() {
		return dept_desc;
	}
	
	/**
	 * @val 部门描述，不超过200个汉字
	 */
	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}
	
	/**
	 * @val 部门性质：1-总公司；2-事业部， 3-分公司；4-经营部；5、办事处、6、代理商；7、终端；
	 * @val 层级关系：
	 * @val 1234567
	 * @val 12345
	 * @val 12346
	 * @val 12347
	 * @val 123456
	 * @val 1234567
	 */
	public Integer getDept_type() {
		return dept_type;
	}
	
	/**
	 * @val 部门性质：1-总公司；2-事业部， 3-分公司；4-经营部；5、办事处、6、代理商；7、终端；
	 * @val 层级关系：
	 * @val 1234567
	 * @val 12345
	 * @val 12346
	 * @val 12347
	 * @val 123456
	 * @val 1234567
	 */
	public void setDept_type(Integer dept_type) {
		this.dept_type = dept_type;
	}
	
	/**
	 * @val 所在地区(大区)：华东-10,山东-20,东北-30,华北-40,华南-50,西南-60，华中-70',西北-80
	 * @val 
	 */
	public Integer getP_area() {
		return p_area;
	}
	
	/**
	 * @val 所在地区(大区)：华东-10,山东-20,东北-30,华北-40,华南-50,西南-60，华中-70',西北-80
	 * @val 
	 */
	public void setP_area(Integer p_area) {
		this.p_area = p_area;
	}
	
	/**
	 * @val 所在地区
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 所在地区
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 所在地区名称
	 */
	public String getP_name() {
		return p_name;
	}
	
	/**
	 * @val 所在地区名称
	 */
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	
	/**
	 * @val 部门领导
	 */
	public Long getLeader_user_id() {
		return leader_user_id;
	}
	
	/**
	 * @val 部门领导
	 */
	public void setLeader_user_id(Long leader_user_id) {
		this.leader_user_id = leader_user_id;
	}
	

	public String getM_areas_ids() {
		return m_areas_ids;
	}

	public void setM_areas_ids(String m_areas_ids) {
		this.m_areas_ids = m_areas_ids;
	}

	public String getM_areas_names() {
		return m_areas_names;
	}

	public void setM_areas_names(String m_areas_names) {
		this.m_areas_names = m_areas_names;
	}

	/**
	 * @val 排序值，值越大越靠前
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值，值越大越靠前
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val 添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人
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
	 * @val 产品ID
	 */
	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public List<TaskPara> getTaskParaList() {
		return taskParaList;
	}

	public void setTaskParaList(List<TaskPara> taskParaList) {
		this.taskParaList = taskParaList;
	}

	/**
	 * @val 经办类型：0、非经办；1、A类型；2、B类型；3、C类型；4、其他
	 */
	public void setJb_type(Integer jb_type) {
		this.jb_type = jb_type;
	}

	/**
	 * @val 经办类型：0、非经办；1、A类型；2、B类型；3、C类型；4、其他
	 */
	public Integer getJb_type() {
		return jb_type;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	
}