package com.ebiz.mmt.web.struts.inter.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;

import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.inter.form.InterUser;

/**
 * @author tudp
 * @version 2014-09-19
 */
public class BaseInterAction extends BaseAction {

	public InterUser getInterUser(ActionForm form, HttpServletRequest request){
		DynaBean dynaBean = (DynaBean) form; 

		String licenses_sn = (String)dynaBean.get("licenses_sn");//授权码
		String user_id = (String)dynaBean.get("user_id");//授权用户id
		String user_key = (String)dynaBean.get("user_key");//授权用户码id
		
		InterUser interUser = new InterUser();
		interUser.setLicenses_sn(licenses_sn);
        if (user_id != null) {
            interUser.setUser_id(new Long(user_id));
        }
		interUser.setUser_key(user_key);
		return interUser;
	}

    /*
     * @see com.ebiz.mmt.web.struts.customer.BaseClientJxcAction
     */
    protected String generateTradeIndex() {
        Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
        return "LSH" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;
    }

    /*
     * @see com.ebiz.mmt.web.struts.customer.BaseClientJxcAction
     */
    protected String generateReturnTradeIndex() {
        Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
        return "THH" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS") + xx;
    }

}