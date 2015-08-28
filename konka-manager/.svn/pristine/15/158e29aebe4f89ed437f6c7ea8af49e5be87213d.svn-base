package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class CrmPriceHeader extends BaseDomain implements Serializable {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupcode == null) ? 0 : groupcode.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((price_code == null) ? 0 : price_code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CrmPriceHeader other = (CrmPriceHeader) obj;
        if (groupcode == null) {
            if (other.groupcode != null) return false;
        } else if (!groupcode.equals(other.groupcode)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (price_code == null) {
            if (other.price_code != null) return false;
        } else if (!price_code.equals(other.price_code)) return false;
        return true;
    }

    private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long dept_id;
	
	private String groupcode;
	
	private String price_code;
	
	private String price_name;
	
	private Date begin_date;
	
	private Date end_date;
	
	private Long created_by;
	
	private Date created_date;
	
	private Long updated_by;
	
	private Date updated_date;
	
	private Integer isdel;

	// 价格类型
	private String price_type;

	public CrmPriceHeader() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getGroupcode() {
		return groupcode;
	}
	
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	
	public String getPrice_code() {
		return price_code;
	}
	
	public void setPrice_code(String price_code) {
		this.price_code = price_code;
	}
	
	public String getPrice_name() {
		return price_name;
	}
	
	public void setPrice_name(String price_name) {
		this.price_name = price_name;
	}
	
	public Date getBegin_date() {
		return begin_date;
	}
	
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	
	public Date getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public Long getCreated_by() {
		return created_by;
	}
	
	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}
	
	public Date getCreated_date() {
		return created_date;
	}
	
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	public Long getUpdated_by() {
		return updated_by;
	}
	
	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}
	
	public Date getUpdated_date() {
		return updated_date;
	}
	
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	
}