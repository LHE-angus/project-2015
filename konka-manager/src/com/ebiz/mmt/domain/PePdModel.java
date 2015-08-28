package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class PePdModel extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Date add_date;// 添加时间

	private Long add_pe_user_id;// 添加人

	private Integer audit_state;// 审核状态 不使用

	private Long audit_user_id;// 审核人 不使用

	/**
	 * 114:康佳(规则未知)<br>
	 */
	private Long brand_id;// 品牌ID
	/**
	 * 115:现代(产口组为28)<br>
	 * 116:KKTV()
	 */
	private Long sub_brand_id;// 子品牌

	private Long cls_id;// 类型id 渠道系统自行维护的类型

	private Date del_date;// 删除日期

	private Date down_date;// 下架时间 不使用

	private Date in_date; // 上市时间

	private Date out_date;// 停产时间 不使用

	private Date up_date;// 上架时间 不使用

	private Integer enable_sellarea; // 销售区域

	private Long entp_id; // no use

	private Long entp_prod_id; // no use

	private Integer is_del; //

	private Integer is_locked;

	private Integer is_sell;

	private Integer is_spec_price;// 特价

	private Date last_edit_date;

	private Long lock_user_id;// no use

	private String main_pic;// 主图

	private String md_name; // led32ff

	private Integer order_value; // 排序值

	private Long par_cls_id;// 父类型ID

	private String pd_desc;// 机型名称

	private Long pd_id;// 机型ID

	private BigDecimal price_ref;// 参考价

	private Long series_id;// no use

	private BigDecimal spec_price;// 特价

	private Long v_n;

	private Long pd_src;

	private String mat_num;// 物料号

	private String md_size;// 大小

	private String md_serise;// 系列

	private String cp_group;// 产品组 sap系统重要字段10,11,18,30...

	private String mat_group;// 物料组

	private Float laeng; // 长

	private Float breit; // 宽

	private Float hoehe; // 高

	private String mat_type; // no use

	private String md_dw; // no use

	private Integer label_3d;

	private Integer label_int;

	private Integer label_www;

	private Integer size_sec;// 大小规格段

	private Integer is_parts;

	private Integer is_4k;

	private Integer is_ytv;

    private Integer is_lock;
	
	private Integer goods_name_type;
	
	public PePdModel() {

	}

	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 添加人
	 */
	public Long getAdd_pe_user_id() {
		return add_pe_user_id;
	}

	/**
	 * @val 审核状态：-1-审核不通过，0-未/待审核（默认），1-审核通过，2-二次修改（待审核）
	 */
	public Integer getAudit_state() {
		return audit_state;
	}

	/**
	 * @val 审核人：买卖提超级管理员帐号
	 */
	public Long getAudit_user_id() {
		return audit_user_id;
	}

	/**
	 * @val 品牌ID（选择）
	 */
	public Long getBrand_id() {
		return brand_id;
	}

	/**
	 * @val 产品类别（选择）
	 */
	public Long getCls_id() {
		return cls_id;
	}

	/**
	 * @val 删除时间
	 */
	public Date getDel_date() {
		return del_date;
	}

	/**
	 * @val 下架时间
	 */
	public Date getDown_date() {
		return down_date;
	}

	/**
	 * @val 停产时间
	 */
	public Date getOut_date() {
		return out_date;
	}

	/**
	 * @val 停产时间
	 */
	public void setOut_date(Date outDate) {
		out_date = outDate;
	}

	public Integer getEnable_sellarea() {
		return enable_sellarea;
	}

	/**
	 * @val 生产企业ID（选择）
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 生产企业买卖提ID（当前用户ID）
	 */
	public Long getEntp_prod_id() {
		return entp_prod_id;
	}

	/**
	 * @val 上市时间
	 */
	public Date getIn_date() {
		return in_date;
	}

	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 买卖提锁定状态:0-未锁定，1-已锁定
	 */
	public Integer getIs_locked() {
		return is_locked;
	}

	/**
	 * @val 是否上架
	 */
	public Integer getIs_sell() {
		return is_sell;
	}

	/**
	 * @val 特价标识
	 */
	public Integer getIs_spec_price() {
		return is_spec_price;
	}

	/**
	 * @val 最后修改时间
	 */
	public Date getLast_edit_date() {
		return last_edit_date;
	}

	/**
	 * @val 买卖提锁定人
	 */
	public Long getLock_user_id() {
		return lock_user_id;
	}

	/**
	 * @val 主图地址
	 */
	public String getMain_pic() {
		return main_pic;
	}

	/**
	 * @val 型号名称（选择、输入）
	 */
	public String getMd_name() {
		return md_name;
	}

	/**
	 * @val 排序
	 */
	public Integer getOrder_value() {
		return order_value;
	}

	/**
	 * @val 产品根类别（选择）
	 */
	public Long getPar_cls_id() {
		return par_cls_id;
	}

	/**
	 * @val 产品说明
	 */
	public String getPd_desc() {
		return pd_desc;
	}

	/**
	 * @val 产品型号ID
	 */
	public Long getPd_id() {
		return pd_id;
	}

	/**
	 * @val 买卖提管理员根据市场行情指定产品价格
	 */
	public BigDecimal getPrice_ref() {
		return price_ref;
	}

	/**
	 * @val 产品系列ID（选择）
	 */
	public Long getSeries_id() {
		return series_id;
	}

	public BigDecimal getSpec_price() {
		return spec_price;
	}

	/**
	 * @val 上架时间
	 */
	public Date getUp_date() {
		return up_date;
	}

	/**
	 * @val 版本号：格式：yyyyMMddHHmmss
	 */
	public Long getV_n() {
		return v_n;
	}

	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 添加人
	 */
	public void setAdd_pe_user_id(Long add_pe_user_id) {
		this.add_pe_user_id = add_pe_user_id;
	}

	/**
	 * @val 审核状态：-1-审核不通过，0-未/待审核（默认），1-审核通过，2-二次修改（待审核）
	 */
	public void setAudit_state(Integer audit_state) {
		this.audit_state = audit_state;
	}

	/**
	 * @val 审核人：买卖提超级管理员帐号
	 */
	public void setAudit_user_id(Long audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	/**
	 * @val 品牌ID（选择）
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	/**
	 * @val 产品类别（选择）
	 */
	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}

	/**
	 * @val 删除时间
	 */
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	/**
	 * @val 下架时间
	 */
	public void setDown_date(Date down_date) {
		this.down_date = down_date;
	}

	public void setEnable_sellarea(Integer enable_sellarea) {
		this.enable_sellarea = enable_sellarea;
	}

	/**
	 * @val 生产企业ID（选择）
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	/**
	 * @val 生产企业买卖提ID（当前用户ID）
	 */
	public void setEntp_prod_id(Long entp_prod_id) {
		this.entp_prod_id = entp_prod_id;
	}

	/**
	 * @val 上市时间
	 */
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 买卖提锁定状态:0-未锁定，1-已锁定
	 */
	public void setIs_locked(Integer is_locked) {
		this.is_locked = is_locked;
	}

	/**
	 * @val 是否上架
	 */
	public void setIs_sell(Integer is_sell) {
		this.is_sell = is_sell;
	}

	/**
	 * @val 特价标识
	 */
	public void setIs_spec_price(Integer is_spec_price) {
		this.is_spec_price = is_spec_price;
	}

	/**
	 * @val 最后修改时间
	 */
	public void setLast_edit_date(Date last_edit_date) {
		this.last_edit_date = last_edit_date;
	}

	/**
	 * @val 买卖提锁定人
	 */
	public void setLock_user_id(Long lock_user_id) {
		this.lock_user_id = lock_user_id;
	}

	/**
	 * @val 主图地址
	 */
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	/**
	 * @val 型号名称（选择、输入）
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	/**
	 * @val 排序
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	/**
	 * @val 产品根类别（选择）
	 */
	public void setPar_cls_id(Long par_cls_id) {
		this.par_cls_id = par_cls_id;
	}

	/**
	 * @val 产品说明
	 */
	public void setPd_desc(String pd_desc) {
		this.pd_desc = pd_desc;
	}

	/**
	 * @val 产品型号ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	/**
	 * @val 买卖提管理员根据市场行情指定产品价格
	 */
	public void setPrice_ref(BigDecimal price_ref) {
		this.price_ref = price_ref;
	}

	/**
	 * @val 产品系列ID（选择）
	 */
	public void setSeries_id(Long series_id) {
		this.series_id = series_id;
	}

	public void setSpec_price(BigDecimal spec_price) {
		this.spec_price = spec_price;
	}

	/**
	 * @val 上架时间
	 */
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	/**
	 * @val 版本号：格式：yyyyMMddHHmmss
	 */
	public void setV_n(Long v_n) {
		this.v_n = v_n;
	}

	/**
	 * 用于区分是新增的产品还是买卖提导入的产品
	 */
	public Long getPd_src() {
		return pd_src;
	}

	public void setPd_src(Long pd_src) {
		this.pd_src = pd_src;
	}

	/**
	 * @val 物料编号
	 */
	public void setMat_num(String mat_num) {
		this.mat_num = mat_num;
	}

	/**
	 * @val 物料编号
	 */
	public String getMat_num() {
		return mat_num;
	}

	/**
	 * @val 尺寸（电视）
	 */
	public void setMd_size(String md_size) {
		this.md_size = md_size;
	}

	/**
	 * @val 尺寸（电视）
	 */
	public String getMd_size() {
		return md_size;
	}

	/**
	 * @val 型号/系列（电视）
	 */
	public void setMd_serise(String md_serise) {
		this.md_serise = md_serise;
	}

	/**
	 * @val 型号/系列（电视）
	 */
	public String getMd_serise() {
		return md_serise;
	}

	/**
	 * @val 产品组
	 */
	public String getCp_group() {
		return cp_group;
	}

	/**
	 * @val 产品组
	 */
	public void setCp_group(String cp_group) {
		this.cp_group = cp_group;
	}

	/**
	 * @val 物料组
	 */
	public String getMat_group() {
		return mat_group;
	}

	/**
	 * @val 物料组
	 */
	public void setMat_group(String mat_group) {
		this.mat_group = mat_group;
	}

	/**
	 * @val 物料类型
	 */
	public String getMat_type() {
		return mat_type;
	}

	/**
	 * @val 物料类型
	 */
	public void setMat_type(String mat_type) {
		this.mat_type = mat_type;
	}

	/**
	 * @val 单位
	 */
	public String getMd_dw() {
		return md_dw;
	}

	/**
	 * @val 单位
	 */
	public void setMd_dw(String md_dw) {
		this.md_dw = md_dw;
	}

	/**
	 * @val 3D电视:0否1是
	 */
	public Integer getLabel_3d() {
		return label_3d;
	}

	/**
	 * @val 3D电视：0否1是
	 */
	public void setLabel_3d(Integer label_3d) {
		this.label_3d = label_3d;
	}

	/**
	 * @val 智能电视：0否1是
	 */
	public Integer getLabel_int() {
		return label_int;
	}

	/**
	 * @val 智能电视：0否1是
	 */
	public void setLabel_int(Integer labelInt) {
		label_int = labelInt;
	}

	/**
	 * @val 网络电视：0否1是
	 */
	public Integer getLabel_www() {
		return label_www;
	}

	/**
	 * @val 网络电视：0否1是
	 */
	public void setLabel_www(Integer labelWww) {
		label_www = labelWww;
	}

	/**
	 * @val 规格段:1：26及以下;2：32;3：37-39;4：40-43;5：46-50;6：55及以上';
	 */
	public Integer getSize_sec() {
		return size_sec;
	}

	/**
	 * @val 规格段:1：26及以下;2：32;3：37-39;4：40-43;5：46-50;6：55及以上';
	 */
	public void setSize_sec(Integer sizeSec) {
		size_sec = sizeSec;
	}

	/**
	 * @val 是否为配件：0否1是
	 */
	public Integer getIs_parts() {
		return is_parts;
	}

	/**
	 * @val 是否为配件：0否1是
	 */
	public void setIs_parts(Integer isParts) {
		is_parts = isParts;
	}

	/**
	 * @val 是否为4k：0否1是
	 */
	public Integer getIs_4k() {
		return is_4k;
	}

	/**
	 * @val 是否为4k：0否1是
	 */
	public void setIs_4k(Integer is_4k) {
		this.is_4k = is_4k;
	}

	/**
	 * @val 是否为易TV：0否1是
	 */
	public void setIs_ytv(Integer is_ytv) {
		this.is_ytv = is_ytv;
	}

	/**
	 * @val 是否为易TV：0否1是
	 */
	public Integer getIs_ytv() {
		return is_ytv;
	}

	public Float getLaeng() {
		return laeng;
	}

	public void setLaeng(Float laeng) {
		this.laeng = laeng;
	}

	public Float getBreit() {
		return breit;
	}

	public void setBreit(Float breit) {
		this.breit = breit;
	}

	public Float getHoehe() {
		return hoehe;
	}

	public void setHoehe(Float hoehe) {
		this.hoehe = hoehe;
	}

	public Long getSub_brand_id() {
		return sub_brand_id;
	}

	public void setSub_brand_id(Long sub_brand_id) {
		this.sub_brand_id = sub_brand_id;
	}
	/**
	 * @val 是否锁定 1:锁定,2解锁
	 */
	public Integer getIs_lock() {
		return is_lock;
	}
	
	/**
	 * @val 是否锁定 1:锁定,2解锁
	 */
	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}
	
	/**
	 * @val 1新品,2主销,3退市
	 */
	public Integer getGoods_name_type() {
		return goods_name_type;
	}
	
	/**
	 * @val 1新品,2主销,3退市
	 */
	public void setGoods_name_type(Integer goods_name_type) {
		this.goods_name_type = goods_name_type;
	}
}