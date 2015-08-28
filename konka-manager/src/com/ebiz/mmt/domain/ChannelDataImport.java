package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class ChannelDataImport extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	/**
	 * 导入时间
	 */
	private Date import_date;

	/**
	 * 导入用户ID
	 */
	private Long import_uid;

	/**
	 * 售达方
	 */
	private String column_1;

	/**
	 * 分
	 */
	private String column_2;

	/**
	 * 分类描述
	 */
	private String column_3;

	/**
	 * 名称(售)
	 */
	private String column_4;

	/**
	 * 送达方
	 */
	private String column_5;

	/**
	 * 名称（送）
	 */
	private String column_6;

	/**
	 * R/3订单创建日期
	 */
	private Date column_7;

	/**
	 * R/3订单凭证日期
	 */
	private Date column_26;

	/**
	 * 订单号
	 */
	private String column_8;

	/**
	 * 订单类型
	 */
	private String column_9;

	/**
	 * 订单行项目号
	 */
	private String column_10;

	/**
	 * 机型
	 */
	private String column_11;

	    /**
     * 订单行机型的数量==>交货单数量 (由于业务需要做出别扭调整 column_12-->column_27) <br>
     * 现在column_12表示 交货单数量
     */
	private String column_12;

	/**
	 * 单价（含税）
	 */
	private BigDecimal column_13;

	/**
	 * 总金额（含税）
	 */
	private BigDecimal column_14;

	/**
	 * K007（含税）
	 */
	private BigDecimal column_15;

	/**
	 * RB00(含税)
	 */
	private BigDecimal column_16;

	/**
	 * 总净值金额(含税)
	 */
	private BigDecimal column_17;

	/**
	 * 交货日期
	 */
	private Date column_18;

	/**
	 * KF交货单
	 */
	private String column_19;

	/**
	 * 物流交货单
	 */
	private String column_20;

	/**
	 * 发货仓位
	 */
	private String column_21;

	/**
	 * 采购订单编号
	 */
	private String column_22;

	/**
	 * 物料组
	 */
	private String column_23;

	/**
	 * 办事处
	 */
	private String column_24;

	/**
	 * 销售组织
	 */
	private String column_25;

    /**
     * 交货单数量==>订单行机型的数量 (由于业务需要做出别扭调整 column_27-->column_12)<br>
     * 现在column_27是订单行机型的数量
     */
	private BigDecimal column_27;
	/**
	 * 已发货数量
	 */
	private BigDecimal column_28;
	/**
	 * 开发票数量
	 */
	private BigDecimal column_29;


	private BigDecimal column_30;

	public ChannelDataImport() {

	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setImport_date(Date import_date) {
		this.import_date = import_date;
	}

	public Date getImport_date() {
		return import_date;
	}

	public void setImport_uid(Long import_uid) {
		this.import_uid = import_uid;
	}

	public Long getImport_uid() {
		return import_uid;
	}

	public void setColumn_1(String column_1) {
		this.column_1 = column_1;
	}

	public String getColumn_1() {
		return column_1;
	}

	public void setColumn_2(String column_2) {
		this.column_2 = column_2;
	}

	public String getColumn_2() {
		return column_2;
	}

	public void setColumn_3(String column_3) {
		this.column_3 = column_3;
	}

	public String getColumn_3() {
		return column_3;
	}

	public void setColumn_4(String column_4) {
		this.column_4 = column_4;
	}

	public String getColumn_4() {
		return column_4;
	}

	public void setColumn_5(String column_5) {
		this.column_5 = column_5;
	}

	public String getColumn_5() {
		return column_5;
	}

	public void setColumn_6(String column_6) {
		this.column_6 = column_6;
	}

	public String getColumn_6() {
		return column_6;
	}

	public void setColumn_7(Date column_7) {
		this.column_7 = column_7;
	}

	public Date getColumn_7() {
		return column_7;
	}

	public void setColumn_8(String column_8) {
		this.column_8 = column_8;
	}

	public String getColumn_8() {
		return column_8;
	}

	public void setColumn_9(String column_9) {
		this.column_9 = column_9;
	}

	public String getColumn_9() {
		return column_9;
	}

	public void setColumn_10(String column_10) {
		this.column_10 = column_10;
	}

	public String getColumn_10() {
		return column_10;
	}

	public void setColumn_11(String column_11) {
		this.column_11 = column_11;
	}

	public String getColumn_11() {
		return column_11;
	}

	public void setColumn_12(String column_12) {
		this.column_12 = column_12;
	}

	public String getColumn_12() {
		return column_12;
	}

	public BigDecimal getColumn_13() {
		return column_13;
	}

	public void setColumn_13(BigDecimal column_13) {
		this.column_13 = column_13;
	}

	public BigDecimal getColumn_14() {
		return column_14;
	}

	public void setColumn_14(BigDecimal column_14) {
		this.column_14 = column_14;
	}

	public BigDecimal getColumn_15() {
		return column_15;
	}

	public void setColumn_15(BigDecimal column_15) {
		this.column_15 = column_15;
	}

	public BigDecimal getColumn_16() {
		return column_16;
	}

	public void setColumn_16(BigDecimal column_16) {
		this.column_16 = column_16;
	}

	public BigDecimal getColumn_17() {
		return column_17;
	}

	public void setColumn_17(BigDecimal column_17) {
		this.column_17 = column_17;
	}

	public void setColumn_18(Date column_18) {
		this.column_18 = column_18;
	}

	public Date getColumn_18() {
		return column_18;
	}

	public void setColumn_19(String column_19) {
		this.column_19 = column_19;
	}

	public String getColumn_19() {
		return column_19;
	}

	public void setColumn_20(String column_20) {
		this.column_20 = column_20;
	}

	public String getColumn_20() {
		return column_20;
	}

	public void setColumn_21(String column_21) {
		this.column_21 = column_21;
	}

	public String getColumn_21() {
		return column_21;
	}

	public void setColumn_22(String column_22) {
		this.column_22 = column_22;
	}

	public String getColumn_22() {
		return column_22;
	}

	public void setColumn_23(String column_23) {
		this.column_23 = column_23;
	}

	public String getColumn_23() {
		return column_23;
	}

	public void setColumn_24(String column_24) {
		this.column_24 = column_24;
	}

	public String getColumn_24() {
		return column_24;
	}

	public void setColumn_25(String column_25) {
		this.column_25 = column_25;
	}

	public String getColumn_25() {
		return column_25;
	}

	public Date getColumn_26() {
		return column_26;
	}

	public void setColumn_26(Date column_26) {
		this.column_26 = column_26;
	}

	public BigDecimal getColumn_27() {
		return column_27;
	}

	public void setColumn_27(BigDecimal column_27) {
		this.column_27 = column_27;
	}

	public BigDecimal getColumn_28() {
		return column_28;
	}

	public void setColumn_28(BigDecimal column_28) {
		this.column_28 = column_28;
	}

	public BigDecimal getColumn_29() {
		return column_29;
	}

	public void setColumn_29(BigDecimal column_29) {
		this.column_29 = column_29;
	}

	public BigDecimal getColumn_30() {
		return column_30;
	}

	public void setColumn_30(BigDecimal column_30) {
		this.column_30 = column_30;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column_1 == null) ? 0 : column_1.hashCode());
		result = prime * result + ((column_12 == null) ? 0 : column_12.hashCode());
		result = prime * result + ((column_13 == null) ? 0 : column_13.hashCode());
		result = prime * result + ((column_14 == null) ? 0 : column_14.hashCode());
		result = prime * result + ((column_25 == null) ? 0 : column_25.hashCode());
		result = prime * result + ((column_26 == null) ? 0 : column_26.hashCode());
		result = prime * result + ((column_7 == null) ? 0 : column_7.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((import_date == null) ? 0 : import_date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelDataImport other = (ChannelDataImport) obj;
		if (column_1 == null) {
			if (other.column_1 != null)
				return false;
		} else if (!column_1.equals(other.column_1))
			return false;
		if (column_12 == null) {
			if (other.column_12 != null)
				return false;
		} else if (!column_12.equals(other.column_12))
			return false;
		if (column_13 == null) {
			if (other.column_13 != null)
				return false;
		} else if (!column_13.equals(other.column_13))
			return false;
		if (column_14 == null) {
			if (other.column_14 != null)
				return false;
		} else if (!column_14.equals(other.column_14))
			return false;
		if (column_25 == null) {
			if (other.column_25 != null)
				return false;
		} else if (!column_25.equals(other.column_25))
			return false;
		if (column_26 == null) {
			if (other.column_26 != null)
				return false;
		} else if (!column_26.equals(other.column_26))
			return false;
		if (column_7 == null) {
			if (other.column_7 != null)
				return false;
		} else if (!column_7.equals(other.column_7))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (import_date == null) {
			if (other.import_date != null)
				return false;
		} else if (!import_date.equals(other.import_date))
			return false;
		return true;
	}
}