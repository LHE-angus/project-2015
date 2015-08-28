package com.ebiz.mmt.web.struts.inter.form;

import java.io.Serializable;

/**
 * @author Pan,Gang
 * @since 2014-09-24
 * @see 请求验证
 */
public class CategoryType extends BaseInterForm implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long c_index;
	private String c_name;
	private String par_index;
	private String c_comm;

	public Long getC_index() {
		return c_index;
	}

	public void setC_index(Long cIndex) {
		c_index = cIndex;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String cName) {
		c_name = cName;
	}

	public String getPar_index() {
		return par_index;
	}

	public void setPar_index(String parIndex) {
		par_index = parIndex;
	}

	public String getC_comm() {
		return c_comm;
	}

	public void setC_comm(String cComm) {
		c_comm = cComm;
	}

}