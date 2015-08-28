package com.ebiz.mmt.web.struts.inter.form;

import java.io.Serializable;

/**
 * @author Tudp
 * @since 2014-09-19
 * @see 所有接口返回对象继承本接口,返回状态信息 state 0 成功,1失败
 */
public class BaseInterForm implements Serializable {

	private static final long serialVersionUID = -1L;
 
	private Integer return_state;
	private String return_msg;
	private String return_error;
	
    public BaseInterForm() {}

    public BaseInterForm(Integer return_state, String return_msg, String return_error) {
        super();
        this.return_state = return_state;
        this.return_msg = return_msg;
        this.return_error = return_error;
    }

    public Integer getReturn_state() {
		return return_state;
	}
	public void setReturn_state(Integer return_state) {
		this.return_state = return_state;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getReturn_error() {
		return return_error;
	}
	public void setReturn_error(String return_error) {
		this.return_error = return_error;
	}
}