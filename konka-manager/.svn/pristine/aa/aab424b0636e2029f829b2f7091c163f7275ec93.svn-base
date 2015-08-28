package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Jin,QingHua
 */
public class SysModule extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;

	/**
	 * 模块ID<br />
	 * 1位：用户类型代码<br />
	 * 2，3位：一级目录代码<br />
	 * 4，5位：二级目录代码<br />
	 * 6，7位模块代码<br />
	 */
	private Integer mod_id;

	/**
	 * 父模块ID
	 */
	private Integer par_id;

	/**
	 * 模块名称
	 */
	private String mod_name;

	/**
	 * 模块说明
	 */
	private String mod_desc;

	/**
	 * 链接地址
	 */
	private String mod_url;

	/**
	 * 存放栏目可选的权限
	 */
	private Integer ppdm_code;

	/**
	 * 排序值
	 */
	private Integer order_value;

	/**
	 * 是否公共<br />
	 * 0：私有，必须设定权限后才能访问<br />
	 * 1：公共，对所有用户都有权限<br />
	 * 9：管理，管理员默认有权限的模块<br />
	 */

	private Integer is_admin;

	private Integer is_public;

	/**
	 * 是否留言<br />
	 * 0：否<br />
	 * 1：是<br />
	 */
	private Integer is_msg;

	/**
	 * 是否锁定<br />
	 * 0：未锁定<br />
	 * 1：已锁定，不能删除，即不能将IS_DEL设置为1<br />
	 */
	private Integer is_lock;

	/**
	 * 是否删除
	 */
	private Integer is_del;

	private Integer tree_level;

	private Integer root_id;

	private Integer is_view;

	private Integer level_by_dept;

	private Integer fgs_auth_flag;// 是否给分公司授权节点的权限

	/**
	 * 图标地址
	 */
	private String icon_path;

	public String getIcon_path() {
		return icon_path;
	}

	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}

	private List<BasePopedom> basePopedomList;

	public SysModule() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMod_id() {
		return mod_id;
	}

	public void setMod_id(Integer mod_id) {
		this.mod_id = mod_id;
	}

	public Integer getPar_id() {
		return par_id;
	}

	public void setPar_id(Integer par_id) {
		this.par_id = par_id;
	}

	public String getMod_name() {
		return mod_name;
	}

	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}

	public String getMod_desc() {
		return mod_desc;
	}

	public void setMod_desc(String mod_desc) {
		this.mod_desc = mod_desc;
	}

	public String getMod_url() {
		return mod_url;
	}

	public void setMod_url(String mod_url) {
		this.mod_url = mod_url;
	}

	public Integer getPpdm_code() {
		return ppdm_code;
	}

	public void setPpdm_code(Integer ppdm_code) {
		this.ppdm_code = ppdm_code;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public Integer getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(Integer isAdmin) {
		is_admin = isAdmin;
	}

	public Integer getIs_public() {
		return is_public;
	}

	public void setIs_public(Integer is_public) {
		this.is_public = is_public;
	}

	public Integer getIs_msg() {
		return is_msg;
	}

	public void setIs_msg(Integer isMsg) {
		is_msg = isMsg;
	}

	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public List<BasePopedom> getBasePopedomList() {
		return basePopedomList;
	}

	public void setBasePopedomList(List<BasePopedom> basePopedomList) {
		this.basePopedomList = basePopedomList;
	}

	public Integer getTree_level() {
		return tree_level;
	}

	public void setTree_level(Integer tree_level) {
		this.tree_level = tree_level;
	}

	public Integer getRoot_id() {
		return root_id;
	}

	public void setRoot_id(Integer root_id) {
		this.root_id = root_id;
	}

	/**
	 * @val 分公司可操作标识：0-否 1-是
	 */
	public Integer getIs_view() {
		return is_view;
	}

	/**
	 * @val 分公司可操作标识：0-否 1-是
	 */
	public void setIs_view(Integer isView) {
		is_view = isView;
	}

	/**
	 * @val 可视数据层级（部门）:-1:全部（所有部门）; 0:所在部门及以下; n(n>0):指定层级部门及以下;
	 */
	public Integer getLevel_by_dept() {
		return level_by_dept;
	}

	/**
	 * @val 可视数据层级（部门）:-1:全部（所有部门）; 0:所在部门及以下; n(n>0):指定层级部门及以下;
	 */
	public void setLevel_by_dept(Integer levelByDept) {
		level_by_dept = levelByDept;
	}

	/**
	 * @val 分公司授权标识：0-否 1-是
	 */
	public Integer getFgs_auth_flag() {
		return fgs_auth_flag;
	}

	/**
	 * @val 分公司授权标识：0-否 1-是
	 */
	public void setFgs_auth_flag(Integer fgsAuthFlag) {
		fgs_auth_flag = fgsAuthFlag;
	}

}