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
	/*font-family: Arial, Helvetica, sans-serif;*/
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
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="left" >上报日期</td>
        <td nowrap="nowrap" align="left" >分公司</td>
         <td nowrap="nowrap" align="left" >上报人</td>
        <td  nowrap="nowrap" align="left">活动类型</td>
        <td  nowrap="nowrap" align="left">客户R3编码</td>
        <td  nowrap="nowrap" align="left">客户名称</td>
        <td  nowrap="nowrap" align="left">终端编码</td>
         <td  nowrap="nowrap" align="left">终端名称</td>
         <td  nowrap="nowrap" align="left">上报说明</td>
         <td  nowrap="nowrap" align="left">备注</td>
         <td  nowrap="nowrap" align="left">定位信息</td>
         <td  nowrap="nowrap" align="left">附件</td>
      </tr>
      <c:forEach items="${allList}" var="cur" varStatus="vs">
        <tr>
	        <td align="left" nowrap="nowrap">${(af.map.pager.currentPage-1) * af.map.pager.pageSize + vs.count}</td>
			<td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td nowrap="nowrap" align="left" >${cur.dept_name}</td>
			<td align="left" nowrap="nowrap">${cur.report_user_name}</td>
			<td align="left" nowrap="nowrap">${cur.map.type_tilte}</td>
			<td align="left" nowrap="nowrap">${cur.r3_code}</td>
			<td align="left" nowrap="nowrap">${cur.customer_name}</td>
			<td align="left" nowrap="nowrap">${cur.store_id}</td>
			<td align="left" nowrap="nowrap">${cur.store_name}</td>
			<td align="left" nowrap="nowrap" title="${cur.report_memo}">${fn:substring(cur.report_memo,0,20)}</td>
			<td align="left" nowrap="nowrap" title="${cur.remark}">${fn:substring(cur.remark,0,20)}</td>
			<td align="left" nowrap="nowrap" title="${cur.map.addr}">${fn:substring(cur.map.addr,0,20)}</td>
			<td width="300px;">
           <c:if test="${not empty cur.map.fj_paths}">
	          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
	          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
	          	      <a href="${ctx}/${fj_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
	          </c:forEach>
          </c:if>
	       </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>
