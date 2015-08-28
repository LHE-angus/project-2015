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
<div class="oarcont" id="body_oarcont">

  
  <div class="rtabcont1" style="overflow-x:auto;">
		<%@ include file="/commons/pages/messages.jsp"%>	
 <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
  <tr style="bgcolor:orange;" >
    <td nowrap="nowrap" align="center" style="background-color: #FFD39B;"  rowspan="2" >序号</td>
    <td nowrap="nowrap" align="center"  style="background-color: #FFD39B;" rowspan="2">分公司</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;"  rowspan="2">一级部门</td>
    <td  nowrap="nowrap" align="center"  style="background-color: #FFD39B;" rowspan="2">二级部门</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">上报人</td>
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访客户数</td>
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访终端数</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访次数</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="2">客户拜访</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="2">终端拜访</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;"  colspan="3">客户开拓</td>
    <td nowrap="nowrap" align="center"  rowspan="2">日期区间</td>
  </tr>
  <tr >
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >完成率</td>
     <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >完成率</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >完成率</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">正常</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">重拾</td>
        <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">正常</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">重拾</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">计划</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">拜访</td>
  </tr>
   <c:forEach items="${allList}" var="cur" varStatus="vs">
    <tr>
    <td nowrap="nowrap" align="center">${vs.count}</td>
   
    <td nowrap="nowrap" align="center">${cur.map.dept_name_one}</td>
    <td nowrap="nowrap" align="center">${cur.map.dept_name_two}</td>
    <td nowrap="nowrap" align="center">${cur.map.dept_name_three}</td>
     <td nowrap="nowrap" align="center">${cur.map.user_name}</td>
    <td nowrap="nowrap" align="center">${cur.map.jh_visit_cust_count}</td>
    <td nowrap="nowrap" align="center">${cur.map.bf_cust_count}</td>
    <td nowrap="nowrap" align="center">
    <c:if test="${cur.map.jh_visit_cust_count ne 0}"><!--
    <fmt:formatNumber  type="percent"  value="${cur.map.bf_cust_count / cur.map.jh_visit_cust_count}" />
    -->
   
     <fmt:formatNumber value="${cur.map.custbfb*100}" pattern="#0"/>%
     <!-- 
    ${cur.map.custbfb}
    --></c:if>
      <c:if test="${cur.map.jh_visit_cust_count eq 0}">
      没做计划
    </c:if>
    </td>
    <td nowrap="nowrap" align="center">${cur.map.jh_visit_shop_count}</td>
     <td nowrap="nowrap" align="center">${cur.map.bf_shop_count}</td>
    <td nowrap="nowrap" align="center">
    <c:if test="${cur.map.jh_visit_shop_count ne 0}"><!--
    ${(cur.map.bf_shop_count / cur.map.jh_visit_shop_count)*100}%
    -->
    <fmt:formatNumber value="${cur.map.shopbfb*100}" pattern="#0"/>%
     <!-- 
    ${cur.map.shopbfb}
    --></c:if>
      <c:if test="${cur.map.jh_visit_shop_count eq 0}">
      没做计划
    </c:if>
    </td>
    
    <td nowrap="nowrap" align="center">${cur.map.jh_visit_count}</td>
    <td nowrap="nowrap" align="center">${cur.map.bf_count}</td>
    <td nowrap="nowrap" align="center">
    <c:if test="${cur.map.jh_visit_count ne 0}">
<!--    ${(cur.map.bf_count / cur.map.jh_visit_count)*100}%-->
  <fmt:formatNumber value="${cur.map.bf_bfb*100}" pattern="#0"/>%
    </c:if>
     <c:if test="${cur.map.jh_visit_count eq 0}">
      没做计划
    </c:if>
     </td>
    <td nowrap="nowrap" align="center">${cur.map.zc_cust_visit_count}</td>
    <td nowrap="nowrap" align="center">${cur.map.cs_cust_visit_count}</td>
     <td nowrap="nowrap" align="center">${cur.map.zc_shop_visit_count}</td>
    <td nowrap="nowrap" align="center">${cur.map.cs_shop_visit_count}</td>
     <td nowrap="nowrap" align="center">${cur.map.jh_dev_cust_count}</td>
    <td nowrap="nowrap" align="center">${cur.map.kt_cust_count}</td>
    <td nowrap="nowrap" align="center">${cur.map.kt_cust_visit_count}</td>
    <c:if test="${not empty year_month}">
    <td nowrap="nowrap" align="center">${year_month}</td>
    </c:if>
    <c:if test="${empty year_month}">
     <td nowrap="nowrap" align="center">未指定区间</td>
   </c:if>
  </tr>
 </c:forEach>
     </table>
</div>
</body>
</html>
