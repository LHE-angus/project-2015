<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
			<td width="5%" nowrap="nowrap" rowspan="2" align="center">序号</td>
			<td nowrap="nowrap" rowspan="2" align="center" >签到日期</td>
			<td  nowrap="nowrap" rowspan="2" align="center">分公司</td>
			<td nowrap="nowrap" rowspan="2" align="center">经办</td>
			<td nowrap="nowrap" rowspan="2" align="center" >签到人</td>
			<td nowrap="nowrap" colspan="12" align="center">上午签到签退</td>
			<td nowrap="nowrap" colspan="12" align="center" >下午签到签退</td>
	 </tr>
	<tr>
			<td nowrap="nowrap" align="center" >上午签到</td>
			<td nowrap="nowrap" align="center" >上午签到地址</td>
			<td nowrap="nowrap" align="center" >上午签到IP</td>
			<td nowrap="nowrap" align="center" >上午签到状态</td>
			<c:if test="${af.map.haveRole}">
			<td nowrap="nowrap" align="center" >上午签到手机型号</td>
			<td nowrap="nowrap" align="center" >上午签到手机IMEI</td>
			</c:if>
			<td nowrap="nowrap" align="center" >上午签退</td>
			<td nowrap="nowrap" align="center" >上午签退地址</td>
			<td nowrap="nowrap" align="center" >上午签退IP</td>
			<td nowrap="nowrap" align="center" >上午签退状态</td>
			
			<c:if test="${af.map.haveRole}">
			<td nowrap="nowrap" align="center" >上午签退手机型号</td>
			<td nowrap="nowrap" align="center" >上午签退手机IMEI</td>
			</c:if>
			
			<td nowrap="nowrap" align="center" >下午签到</td>
			<td nowrap="nowrap" align="center" >下午签到地址</td>
			<td nowrap="nowrap" align="center" >下午签到IP</td>
			<td nowrap="nowrap" align="center" >下午签到状态</td>
			<c:if test="${af.map.haveRole}">
			<td nowrap="nowrap" align="center" >下午签到手机型号</td>
			<td nowrap="nowrap" align="center" >下午签到手机IMEI</td>
			</c:if>
			<td nowrap="nowrap" align="center" >下午签退</td>
			<td nowrap="nowrap" align="center" >下午签退地址</td>
            <td nowrap="nowrap" align="center" >下午签退IP</td>
            <td nowrap="nowrap" align="center" >下午签退状态</td>
            <c:if test="${af.map.haveRole}">
            <td nowrap="nowrap" align="center" >下午签退手机型号</td>
            <td nowrap="nowrap" align="center" >下午签退手机IMEI</td>
            </c:if>
      </tr>
      <c:forEach items="${allList}" var="cur" varStatus="vs">
        <tr>
			<td align="center" nowrap="nowrap">${vs.count}</td>
			<td align="left" nowrap="nowrap">${cur.ALL_DATE}</td>
			<td align="center" nowrap="nowrap">${cur.FGS_NAME}</td>
			<td align="center" nowrap="nowrap">${cur.JB_NAME}</td>
			<td align="left" nowrap="nowrap">${cur.REAL_NAME}</td>
			<td align="left" nowrap="nowrap">${cur.AM_SIGN_IN}</td>
			<td align="left" nowrap="nowrap">${cur.AM_ADDR_IN}</td>
			<td align="center" nowrap="nowrap">${cur.AM_IP_IN}</td>
			<td align="center" nowrap="nowrap">${cur.AM_SIGN_STATE_IN}</td>
			<c:if test="${af.map.haveRole}">
			<td align="center" nowrap="nowrap">${cur.AM_PHONE_MODEL_IN}</td>
			<td align="center" nowrap="nowrap">${cur.AM_IMEI_IN}</td>
			</c:if>
			<td align="left" nowrap="nowrap">${cur.AM_SIGN_OUT}</td>
			<td align="center" nowrap="nowrap">${cur.AM_ADDR_OUT}</td>
			<td align="center" nowrap="nowrap">${cur.AM_IP_OUT}</td>
			<td align="center" nowrap="nowrap">${cur.AM_SIGN_STATE_OUT}</td>
			<c:if test="${af.map.haveRole}">
			<td align="center" nowrap="nowrap">${cur.AM_PHONE_MODEL_OUT}</td>
			<td align="center" nowrap="nowrap">${cur.AM_IMEI_OUT}</td>
			</c:if>
			
			<td align="left" nowrap="nowrap">${cur.PM_SIGN_IN}</td>
			<td align="left" nowrap="nowrap">${cur.PM_ADDR_IN}</td>
			<td align="left" nowrap="nowrap">${cur.PM_IP_IN}</td>
			<td align="center" nowrap="nowrap">${cur.PM_SIGN_STATE_IN}</td>
			<c:if test="${af.map.haveRole}">
			<td align="left" nowrap="nowrap">${cur.PM_PHONE_MODEL_IN}</td>
			<td align="center" nowrap="nowrap">${cur.PM_IMEI_IN}</td>
			</c:if>
			<td align="left" nowrap="nowrap">${cur.PM_SIGN_OUT}</td>
			<td align="left" nowrap="nowrap">${cur.PM_ADDR_OUT}</td>
            <td align="left" nowrap="nowrap">${cur.PM_IP_OUT}</td>
            <td align="center" nowrap="nowrap">${cur.PM_SIGN_STATE_OUT}</td>
            <c:if test="${af.map.haveRole}">
            <td align="left" nowrap="nowrap">${cur.PM_PHONE_MODEL_OUT}</td>
            <td align="center" nowrap="nowrap">${cur.PM_IMEI_OUT}</td>
            </c:if>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
  </div>
</body>
</html>
