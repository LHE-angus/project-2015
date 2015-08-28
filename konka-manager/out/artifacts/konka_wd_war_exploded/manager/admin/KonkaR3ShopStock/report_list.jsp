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
     <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
           <td nowrap="nowrap" align="center">客户类型</td>
          <td nowrap="nowrap" align="center">细分类型</td>
          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
          <td nowrap="nowrap" width="10%">经办名称</td>
          <td nowrap="nowrap" width="10%">分公司所在地名称</td>
          <td nowrap="nowrap" width="10%">合并客户编码</td>
          <td nowrap="nowrap" width="10%">上月是否全部盘点</td>
          <td nowrap="nowrap" width="10%">是否结转</td>
        </tr>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
             <td align="left" nowrap="nowrap">${cur.map.par_cust_type_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.cust_type_name}</td>
            <td align="center" nowrap="nowrap">${cur.customer_type}</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" >${fn:escapeXml(cur.map.ext_handle_name)}</td>
            <td align="left" >${fn:escapeXml(cur.branch_area_name)}</td>
            <td align="left" >${fn:escapeXml(cur.customer_code)}</td>
            <td align="left" >
            	<c:if test="${cur.map.is_pd eq 1}">是</c:if>
            	<c:if test="${cur.map.is_pd eq 0}">否</c:if>
            </td>
            <td align="left" >
            	<c:if test="${cur.map.is_jz eq 1}">是</c:if>
            	<c:if test="${cur.map.is_jz eq 0}">否</c:if>
            </td>
          </tr>
        </c:forEach>
      </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
  </div>
</body>
</html>
