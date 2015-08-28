package com.ebiz.mmt.web;

import java.io.Serializable;

public class ZmdRole implements Serializable{

	private static final long serialVersionUID = -4597924314934286624L;

	//分公司业务员
	public static final Long FGS_YWY_ROLE_ID = 390L;
	
	//分公司财务
	public static final Long FGS_CW_ROLE_ID = 350L;
	
	//分公司管理员
	public static final Long FGS_GLY_ROLE_ID = 300L;
	
	//总部业务员
	public static final Long ZB_YWY_ROLE_ID = 250L;
	
	//总部负责人
	public static final Long ZB_FZR_ROLE_ID = 230L;
	
	//总部管理员
	public static final Long ZB_GLY_ROLE_ID = 200L;
	
	
	//资源投资表
	public static final String res_name[] = {"墙体+形象墙：","门楣灯箱：","主推位云电视灯箱：","主推位地柜：","地 柜：","收银台：","灯箱广告画：","室内外照明灯：","门头招牌：","LED发光字：","LED显示屏飞字：","其他："};

	public static final String res_num[] = {"1001","1002","1003","1004","1005","1006","1007","1008","1009","1010","1011","1012"};

}
