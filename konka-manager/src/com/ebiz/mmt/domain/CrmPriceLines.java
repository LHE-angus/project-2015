package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;


public class CrmPriceLines extends BaseDomain implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelcode == null) ? 0 : modelcode.hashCode());
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
		CrmPriceLines other = (CrmPriceLines) obj;
		if (modelcode == null) {
			if (other.modelcode != null)
				return false;
		} else if (!modelcode.equals(other.modelcode))
			return false;
		return true;
	}

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long headid;
	
	private String modelcode;
	
	private String modelname;
	private String func;// 机型功能 安卓、3D、网络
	
	private Double forprice;// 供价
	
	private Double marketprice;// 市场价
	
	private Double lowestprice;// 最低价
	// 0或负数
	private Double discount;// 折扣
	
	private String att1;
	
	private String att2;
	
	private String att3;
	
	private Double tc;// 提成
	
	private String policy;// 推广政策
	
	private String remark;
	
	
	private Double fl;// 返利
	
	public CrmPriceLines() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getHeadid() {
		return headid;
	}
	
	public void setHeadid(Long headid) {
		this.headid = headid;
	}
	
	public String getModelcode() {
		return modelcode;
	}
	
	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}
	
	public String getModelname() {
		return modelname;
	}
	
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	
	public Double getForprice() {
		return forprice;
	}
	
	public void setForprice(Double forprice) {
		this.forprice = forprice;
	}
	
	public Double getMarketprice() {
		return marketprice;
	}
	
	public void setMarketprice(Double marketprice) {
		this.marketprice = marketprice;
	}
	
	public Double getLowestprice() {
		return lowestprice;
	}
	
	public void setLowestprice(Double lowestprice) {
		this.lowestprice = lowestprice;
	}
	
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getAtt1() {
		return att1;
	}
	
	public void setAtt1(String att1) {
		this.att1 = att1;
	}
	
	public String getAtt2() {
		return att2;
	}
	
	public void setAtt2(String att2) {
		this.att2 = att2;
	}
	
	public String getAtt3() {
		return att3;
	}
	
	public void setAtt3(String att3) {
		this.att3 = att3;
	}
	
	public Double getTc() {
		return tc;
	}
	
	public void setTc(Double tc) {
		this.tc = tc;
	}
	
	public String getPolicy() {
		return policy;
	}
	
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getFunc() {
		return func;
	}
	
	public void setFunc(String func) {
		this.func = func;
	}
	
	public Double getFl() {
		return fl;
	}
	
	public void setFl(Double fl) {
		this.fl = fl;
	}
	
}