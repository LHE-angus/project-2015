<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
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
<table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
  <tr style="bgcolor:orange;" >
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2" >序号</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">分公司</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">R3编码</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">客户名称</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">客户类型</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">客户状态</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">区域信息</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">合并编码</td>
     <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">上报人</td>
    
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">结算</td>
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">回款</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">零售</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访次数</td>
    
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" rowspan="2">当月计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" rowspan="2">当月完成率</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" rowspan="2">预警</td>
    <td nowrap="nowrap" align="center"  rowspan="2">日期区间</td>
  </tr>
  <tr >
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">上上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">上上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">上上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上上月拜访次数</td>
     <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月拜访次数</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月拜访次数</td>
    
  </tr>
   <c:forEach items="${allList}" var="cur" varStatus="vs">
    <tr>
    <td nowrap="nowrap" align="center">${vs.count}</td>
     <td align="center" nowrap="nowrap">
     	${cur.BRANCH_AREA_NAME}
     </td>
     <td align="left">${fn:escapeXml(cur.R3_CODE)}</td>
     <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
     <td align="center" nowrap="nowrap">${fn:escapeXml(cur.PAR_CUSTOMER_TYPE_NAME)}
       <c:if test="${empty cur.PAR_CUSTOMER_TYPE_NAME}"><span style="color:#ccc;">未指定</span></c:if>
     </td>
     <td align="left">
     	<c:if test="${cur.SHOP_STATUS eq 1}">
     		新客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 2}">
     		正式客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 3}">
     		静止客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 4}">
     		无效客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 5}">
     		终端网点
     	</c:if>
     	<c:if test="${empty cur.SHOP_STATUS}"><span style="color:#ccc;">未指定</span></c:if>
     </td>  
     <td align="left" nowrap="nowrap">
     	${fn:escapeXml(cur.AREA_NAME)}-${fn:escapeXml(cur.BRANCH_AREA_NAME)}</td>
     <td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUSTOMER_CODE)}</td>
     <td align="left" nowrap="nowrap">${fn:escapeXml(cur.YWY_USER_NAME)}</td>
     
     
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_CLS_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.cur.LAST_ONE_CLS_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"> <fmt:formatNumber value="${cur.CUR_CLS_MONEY}" pattern=".00"/> </td>
    
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_BACK_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_ONE_BACK_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.CUR_BACK_MONEY}" pattern=".00"/>  </td>
   
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_SAIL_MONERY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_ONE_SAIL_MONERY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.CUR_SAIL_MONERY}" pattern=".00"/>  </td>
    
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_CUST_VISIT_COUNT}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_ONE_CUST_VISIT_COUNT}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.CUR_CUST_VISIT_COUNT}" pattern=".00"/></td>
      
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${map.CUR_CUST_PLAN_VISIT_COUNT}" pattern=".00"/></td>
     <td nowrap="nowrap" align="center"><fmt:formatNumber value="${map.MONTH_CUST_VISIT_BFB}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"></td>
    <c:if test="${not empty cur.ADD_DATE}">
    <td nowrap="nowrap" align="center">${cur.ADD_DATE}</td>
    </c:if>
    <c:if test="${empty cur.ADD_DATE}">
     <td nowrap="nowrap" align="center">未指定区间</td>
   </c:if>
  </tr>
 </c:forEach>
     </table>
</body>
</html>
