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
		<%@ include file="/commons/pages/messages.jsp"%>	
 <table  width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable2">
  <tr style="bgcolor:orange;" >
    <td nowrap="nowrap" align="center" style="background-color: #FFD39B;"   >序号</td>
    <td nowrap="nowrap" align="center"  style="background-color: #FFD39B;"  >分公司</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;"  >一级部门</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;"  >上报人</td>
    
    
    <c:if test="${af.map.is_cust_or_shop eq 'cust'}">
	    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >客户R3编码</td>
	     <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >客户名称</td>
	</c:if>
    <c:if test="${af.map.is_cust_or_shop eq 'shop'}">
	    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >终端ID</td>
	    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >终端名称</td>
    </c:if>
    
    <td  nowrap="nowrap" align="center"  style="background-color: #FFD39B;" >客户类型</td>
    
    <c:forEach begin='1' end='31' varStatus="vs" step="1">
	    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >${vs.current }日</td>
    </c:forEach>
  </tr>
  
   <c:forEach items="${allList}" var="cur" varStatus="vs">
    <tr>
    <td nowrap="nowrap" align="center">${vs.count}</td>
    <td nowrap="nowrap" align="center">${cur.map.dept_name_fgs}</td>
    <td nowrap="nowrap" align="center">${cur.map.l4_dept_name}</td>
    <td nowrap="nowrap" align="center">${cur.map.report_nae}</td>
    
    
    <c:if test="${af.map.is_cust_or_shop eq 'cust'}">
         <td nowrap="nowrap" align="center">${cur.map.r3_code}</td>
           <td nowrap="nowrap" align="center">${cur.map.customer_name}</td>
    </c:if>
    <c:if test="${af.map.is_cust_or_shop eq 'shop'}">
	    <td nowrap="nowrap" align="center">${cur.map.shop_id}</td>
        <td nowrap="nowrap" align="center">${cur.map.shop_name}</td>
    </c:if>
    
    <td nowrap="nowrap" align="center">${cur.map.par_customer_type_name}</td>
    
    
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.one_da ne 0}">${cur.map.one_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.two_da ne 0}">${cur.map.two_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.three_da ne 0}">${cur.map.three_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.four_da ne 0}">${cur.map.four_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.five_da ne 0}">${cur.map.five_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.six_da ne 0}">${cur.map.six_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.seven_da ne 0}">${cur.map.seven_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.eight_da ne 0}">${cur.map.eight_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.nine_da ne 0}">${cur.map.nine_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.ten_da ne 0}">${cur.map.ten_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.eleven_da ne 0}">${cur.map.eleven_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twelve_da ne 0}">${cur.map.twelve_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.thirteen_da ne 0}">${cur.map.thirteen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.fourteen_da ne 0}">${cur.map.fourteen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.fifteen_da ne 0}">${cur.map.fifteen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.sixteen_da ne 0}">${cur.map.sixteen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.seventeen_da ne 0}">${cur.map.seventeen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.eighteen_da ne 0}">${cur.map.eighteen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.nineteen_da ne 0}">${cur.map.nineteen_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_da ne 0}">${cur.map.twenty_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_one_da ne 0}">${cur.map.twenty_one_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_two_da ne 0}">${cur.map.twenty_two_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_three_da ne 0}">${cur.map.twenty_three_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_four_da ne 0}">${cur.map.twenty_four_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_five_da ne 0}">${cur.map.twenty_five_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_six_da ne 0}">${cur.map.twenty_six_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_seven_da ne 0}">${cur.map.twenty_seven_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_eight_da ne 0}">${cur.map.twenty_eight_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.twenty_nine_da ne 0}">${cur.map.twenty_nine_da}</c:if></td>
	<td nowrap="nowrap" align="center"><c:if test="${cur.map.thirty_da ne 0}">${cur.map.thirty_da}</c:if></td>
    <td nowrap="nowrap" align="center"><c:if test="${cur.map.thirty_one_da ne 0}">${cur.map.thirty_one_da}</c:if></td>                               
  </tr>
 </c:forEach>
    </table>
</div>
</body>
</html>
