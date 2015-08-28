package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class KonkaBbZj98Import extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	// VTWEG 分销渠道
	// MATKL 物料组
	// MATNR 物料号
	// MENGE 销售数量
	// SAAMT 发票额
	// NETPR 净价
	// COAMT 交货额
	// UNIT_CO 单位成本
	// UNIT_ML 毛利
	// ML 单位毛利
	// MLV 毛利率
	// GCB 销售构成比
	// GXMLV 贡献毛利率
	// LBKUM 当前库存数量
	// LABSFK 分康库存
	// LABS2 分公司中央仓库存
	// LABS3 分公司P仓
	// LABS4 报废库存
	// LABS5 大客户仓库存
	// LABS6 分公司BQDZ仓
	// LABS7 区域调拨中心
	// LABS10 售前待修机总计（分公司售前机+分康售前机）
	// LABS11 分公司E仓
	// LABS12 分公司ET仓
	// LABS13 基地库存
	// LABS14 Q仓库存
	// LABS15 分公司非限库存
	// LABS16 样机仓数量
	// LABS17 分公司在途库存
	// LABS18 分公司D仓库存

	// LABSDF 售前待发机
	// LABSF 分公司有效库存
	// LABSZ Z仓库存
	// VERPR 移动平均价格/周期单价
	// SALK3 当前库存价值
	// STOCK_GCB 当前库存构成比
	// STOCK_ML 当前毛利
	// STOCK_MLV 当前毛利率
	// STOCK_CXB 当前的存销比

	private Long id;
	
	private String vtweg;
	
	private String matkl;
	
	private String matnr;
	
	private Double menge;
	
	private Double saamt;
	
	private Double netpr;
	
	private Double coamt;
	
	private Double unit_co;
	
	private Double unit_ml;
	
	private Double ml;
	
	private Double mlv;
	
	private Double gcb;
	
	private Double gxmlv;
	
	private Double lbkum;
	
	private Double labsfk;
	
	private Double labs2;
	
	private Double labs3;
	
	private Double labs4;
	
	private Double labs5;
	
	private Double labs6;
	
	private Double labs7;
	
	private Double labs10;
	
	private Double labs11;
	
	private Double labs12;
	
	private Double labs13;
	
	private Double labs14;
	
	private Double labs15;
	
	private Double labs16;
	
	private Double labs17;
	
	private Double labs18;
	
	private Double labsdf;
	
	private Double labsf;
	
	private Double labsz;
	
	private Double verpr;
	
	private Double salk3;
	
	private Double stock_gcb;
	
	private Double stock_ml;
	
	private Double stock_mlv;
	
	private Double stock_cxb;
	
	private Long year;
	
	private Long month;
	
	private Long day;

	private Date sync_time;
	
	public KonkaBbZj98Import() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getVtweg() {
		return vtweg;
	}
	
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	
	public String getMatkl() {
		return matkl;
	}
	
	public void setMatkl(String matkl) {
		this.matkl = matkl;
	}
	
	public String getMatnr() {
		return matnr;
	}
	
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	
	public Double getMenge() {
		return menge;
	}
	
	public void setMenge(Double menge) {
		this.menge = menge;
	}
	
	public Double getSaamt() {
		return saamt;
	}
	
	public void setSaamt(Double saamt) {
		this.saamt = saamt;
	}
	
	public Double getNetpr() {
		return netpr;
	}
	
	public void setNetpr(Double netpr) {
		this.netpr = netpr;
	}
	
	public Double getCoamt() {
		return coamt;
	}
	
	public void setCoamt(Double coamt) {
		this.coamt = coamt;
	}
	
	public Double getUnit_co() {
		return unit_co;
	}
	
	public void setUnit_co(Double unit_co) {
		this.unit_co = unit_co;
	}
	
	public Double getUnit_ml() {
		return unit_ml;
	}
	
	public void setUnit_ml(Double unit_ml) {
		this.unit_ml = unit_ml;
	}
	
	public Double getMl() {
		return ml;
	}
	
	public void setMl(Double ml) {
		this.ml = ml;
	}
	
	public Double getMlv() {
		return mlv;
	}
	
	public void setMlv(Double mlv) {
		this.mlv = mlv;
	}
	
	public Double getGcb() {
		return gcb;
	}
	
	public void setGcb(Double gcb) {
		this.gcb = gcb;
	}
	
	public Double getGxmlv() {
		return gxmlv;
	}
	
	public void setGxmlv(Double gxmlv) {
		this.gxmlv = gxmlv;
	}
	
	public Double getLbkum() {
		return lbkum;
	}
	
	public void setLbkum(Double lbkum) {
		this.lbkum = lbkum;
	}
	
	public Double getLabsfk() {
		return labsfk;
	}
	
	public void setLabsfk(Double labsfk) {
		this.labsfk = labsfk;
	}
	
	public Double getLabs2() {
		return labs2;
	}
	
	public void setLabs2(Double labs2) {
		this.labs2 = labs2;
	}
	
	public Double getLabs3() {
		return labs3;
	}
	
	public void setLabs3(Double labs3) {
		this.labs3 = labs3;
	}
	
	public Double getLabs4() {
		return labs4;
	}
	
	public void setLabs4(Double labs4) {
		this.labs4 = labs4;
	}
	
	public Double getLabs5() {
		return labs5;
	}
	
	public void setLabs5(Double labs5) {
		this.labs5 = labs5;
	}
	
	public Double getLabs6() {
		return labs6;
	}
	
	public void setLabs6(Double labs6) {
		this.labs6 = labs6;
	}
	
	public Double getLabs7() {
		return labs7;
	}
	
	public void setLabs7(Double labs7) {
		this.labs7 = labs7;
	}
	
	public Double getLabs10() {
		return labs10;
	}
	
	public void setLabs10(Double labs10) {
		this.labs10 = labs10;
	}
	
	public Double getLabs11() {
		return labs11;
	}
	
	public void setLabs11(Double labs11) {
		this.labs11 = labs11;
	}
	
	public Double getLabs12() {
		return labs12;
	}
	
	public void setLabs12(Double labs12) {
		this.labs12 = labs12;
	}
	
	public Double getLabs13() {
		return labs13;
	}
	
	public void setLabs13(Double labs13) {
		this.labs13 = labs13;
	}
	
	public Double getLabs14() {
		return labs14;
	}
	
	public void setLabs14(Double labs14) {
		this.labs14 = labs14;
	}
	
	public Double getLabs15() {
		return labs15;
	}
	
	public void setLabs15(Double labs15) {
		this.labs15 = labs15;
	}
	
	public Double getLabs16() {
		return labs16;
	}
	
	public void setLabs16(Double labs16) {
		this.labs16 = labs16;
	}
	
	public Double getLabs17() {
		return labs17;
	}
	
	public void setLabs17(Double labs17) {
		this.labs17 = labs17;
	}
	
	public Double getLabs18() {
		return labs18;
	}
	
	public void setLabs18(Double labs18) {
		this.labs18 = labs18;
	}
	
	public Double getLabsdf() {
		return labsdf;
	}
	
	public void setLabsdf(Double labsdf) {
		this.labsdf = labsdf;
	}
	
	public Double getLabsf() {
		return labsf;
	}
	
	public void setLabsf(Double labsf) {
		this.labsf = labsf;
	}
	
	public Double getLabsz() {
		return labsz;
	}
	
	public void setLabsz(Double labsz) {
		this.labsz = labsz;
	}
	
	public Double getVerpr() {
		return verpr;
	}
	
	public void setVerpr(Double verpr) {
		this.verpr = verpr;
	}
	
	public Double getSalk3() {
		return salk3;
	}
	
	public void setSalk3(Double salk3) {
		this.salk3 = salk3;
	}
	
	public Double getStock_gcb() {
		return stock_gcb;
	}
	
	public void setStock_gcb(Double stock_gcb) {
		this.stock_gcb = stock_gcb;
	}
	
	public Double getStock_ml() {
		return stock_ml;
	}
	
	public void setStock_ml(Double stock_ml) {
		this.stock_ml = stock_ml;
	}
	
	public Double getStock_mlv() {
		return stock_mlv;
	}
	
	public void setStock_mlv(Double stock_mlv) {
		this.stock_mlv = stock_mlv;
	}
	
	public Double getStock_cxb() {
		return stock_cxb;
	}
	
	public void setStock_cxb(Double stock_cxb) {
		this.stock_cxb = stock_cxb;
	}
	
	public Long getYear() {
		return year;
	}
	
	public void setYear(Long year) {
		this.year = year;
	}
	
	public Long getMonth() {
		return month;
	}
	
	public void setMonth(Long month) {
		this.month = month;
	}
	
	public Date getSync_time() {
		return sync_time;
	}
	
	public void setSync_time(Date sync_time) {
		this.sync_time = sync_time;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}
	
}