<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/redmond/jquery.ui.all.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3MmtMatch" enctype="multipart/form-data">
      <html-el:hidden property="r3_shop_id" styleId="r3_shop_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="saveEntpShop" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC">商铺信息填写</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商铺名称：</td>
          <td><html-el:text property="shop_name" styleId="shop_name" size="40" styleClass="webinput" maxlength="100" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">商铺公告：</td>
          <td width="88%" align="left"><html-el:text property="shop_desc" size="40" maxlength="30" styleId="shop_desc" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">商铺LOGO：</td>
          <td width="88%" align="left"><html-el:text property="logo_pic" size="40" maxlength="30" styleId="logo_pic" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>所属行业：</td>
          <td><html-el:text property="c_index" styleId="c_index" size="40" styleClass="webinput" maxlength="25" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>主营产品：</td>
           <td>
           <table >
           <tr>
           <c:forEach items="${pdTypeList}" var="cur" varStatus="vs">
         	 <td> <input type=checkbox name="main_pd" id="main_pd_${cur.pd_type }" value="${cur.pd_type }"><label for="main_pd_${cur.pd_type }">${cur.pd_name }</label></input></td>
           <c:if test="${vs.count % 7 eq 0 }">
         	 </tr>
         	 <tr>
           </c:if>
          </c:forEach>
          </tr>
          </table></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>地址：</td>
          <td><html-el:text property="street_addr" styleId="street_addr" size="40" styleClass="webinput" maxlength="100" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>所在区域：</td>
          <td width="88%" align="left"><ul>
              <li style="padding-top:3px;">
                <select name="province" id="province" style="width:180px;">
                  <option value="">-请选择省/直辖市/自治区-</option>
                </select>
                &nbsp;
                <select name="city" id="city" style="width:100px;">
                  <option value="">-请选择市-</option>
                </select>
                &nbsp;
                <select name="country" id="country" style="width:100px;">
                  <option value="">-请选择县-</option>
                </select>
              </li>
            </ul></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">邮编：</td>
          <td><html-el:text property="post_code" styleId="post_code" size="40" styleClass="webinput" maxlength="10" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>联系人：</td>
          <td><html-el:text property="link_user" styleId="link_user" size="40" styleClass="webinput" maxlength="10" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>电话：</td>
          <td><html-el:text property="link_phone" styleId="link_phone" size="40" styleClass="webinput" maxlength="20" value=""/></td>
        </tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">在线客服QQ：</td>
          <td width="88%" align="left"><html-el:text property="online_qq" size="40" maxlength="30" styleId="online_qq" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">支付宝账号：</td>
          <td width="88%" align="left"><html-el:text property="alipay_email" size="40" maxlength="30" styleId="alipay_email" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="下一步" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<div id="whzb_map" style="display:none;" title="拖动（下面 ↓）地图中的点，标注客户坐标" style="width:100%;">
<div align="left"><span style="font-weight: bold">关键字:</span>
      <input type="text" size="60" id="query"/>
      <input type="button" value="查 询" id="querySub" class="bgButton" />
</div>
 <div align="left" id="results"></div>
 <div id="map_canvas" style="width :100%; height : 540px;"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/google.map.geocoder.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#shop_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#link_user").attr("dataType", "Require").attr("msg", "请填写");
	$("#c_index").attr("dataType", "Require").attr("msg", "请填写");
  	$("#street_addr").attr("dataType", "Require").attr("msg", "请填写");
	$("#link_phone").attr("dataType", "Require").attr("msg", "请填写");
	$("#online_qq").attr("Require",false).attr("dataType", "QQ").attr("msg", "请填写");
  	$("#post_code").attr("Require",false).attr("dataType", "Zip").attr("msg", "请填写");

	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "", "datatype": "Require", "msg": "请选择省名称"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": ""});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": ""});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
		 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
         $("#btn_reset").attr("disabled", "true");
         $("#btn_back").attr("disabled", "true");
		 this.form.submit();
		}
	});
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
