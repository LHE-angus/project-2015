<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
input {
	font-family:Microsoft YAHEI;
	font-size:12px;
}
.tableClass tr td{
	height:30px;
	font-size:12px;
	color:#3e3e3e;
	border: 0px solid #CCC;
	
}
.tableClass {
	bordercolor:#fff;
    background-color: #fff;    
}

input.bgButtonSave {
  background: url("../../images/manager/btn_update.jpg") no-repeat;
  height: 30px;
  width: 60px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width: 100%;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <c:if test="${pe_prod_user_session.user_type eq 0}">
      <c:set value="true" var="is_admin" />
    </c:if>
    <div align="left">
    <div style="BORDER: #f6f6f6 2px solid;flot:left;">
      <table  class="tableClass"  style="float: left; width:30%;BORDER: #f6f6f6 3px solid;flot:left;">
        <tr>
          <td colspan="2" style="text-align:center;color:#e60012;font-size:14px;">客户资料</td>
          
        </tr>
        <tr>
          <td  align="right" style="width:150px;">客户编码：</td>
          <td><c:out value="${konkaR3Shop.r3_code}" />
            <c:if test="${empty konkaR3Shop.r3_code}"><span style="color:#999;">未同步</span></c:if></td>
        </tr>
        <tr>
          <td  align="right">客户名称：</td>
          <td>
          	<c:out value="${konkaR3Shop.customer_name}" />
            <c:if test="${empty konkaR3Shop.customer_name}">
            	<span style="color:#999;">未同步</span>
            </c:if>
         </td>
        </tr>
        <tr>
          <td align="right">客户类型：</td>
          <td><c:out value="${cust_type}" />
            <c:if test="${empty konkaR3Shop.customer_type}"><span style="color:#999;">未同步</span></c:if></td>
        </tr>
        </table>
        
        
        <table class="tableClass"  style="width:70%;BORDER: #f6f6f6 2px solid;flot:left;"  >
         <tr>
          <td colspan="2" style="text-align:center;color:#e60012;font-size:14px;">业务员资料</td>
          <td>&nbsp;</td>
          <td width="45%">&nbsp;</td>
        </tr>
        <tr>
          <td  align="right" style="width:200px;">业务员姓名：</td>
          <td >
          	<c:if test="${konkaR3Shop.is_konka eq 0 }">
          		<c:out value="${wd_ywyinfo.REAL_NAME}" />
            	<c:if test="${empty wd_ywyinfo.REAL_NAME}"><span style="color:#999;">未填写</span></c:if>
          	</c:if>
          	<c:if test="${konkaR3Shop.is_konka eq 1 }">
          		<c:out value="${salesman.real_name}" />
            	<c:if test="${empty salesman.real_name}"><span style="color:#999;">未填写</span></c:if>
            </c:if>
          </td>
          <td ></td>
        </tr>
        <tr>
          <td  align="right">业务员手机：</td>
          <td colspan="4">
          	<c:if test="${konkaR3Shop.is_konka eq 0 }">
          		<c:out value="${wd_ywyinfo.LINK_PHONE}" />
            	<c:if test="${empty wd_ywyinfo.LINK_PHONE}"><span style="color:#999;">未填写</span></c:if>
          	</c:if>
          	<c:if test="${konkaR3Shop.is_konka eq 1 }">
          		<c:out value="${salesman.link_tel}" />
            	<c:if test="${empty salesman.link_tel}"><span style="color:#999;">未填写</span></c:if>
            </c:if>
          </td>
      	 <td ></td>
        </tr>
        <tr>
          <td  align="right">业务员部门：</td>
          <td colspan="4">
          	<c:if test="${konkaR3Shop.is_konka eq 0 }">
          		<c:out value="${wd_ywyinfo.DEPT_NAME}" />
            	<c:if test="${empty wd_ywyinfo.DEPT_NAME}"><span style="color:#999;">未填写</span></c:if>
          	</c:if>
          	<c:if test="${konkaR3Shop.is_konka eq 1 }">
          		<c:out value="${salesman.department}" />
            <c:if test="${empty salesman.department}"><span style="color:#999;">未填写</span></c:if>
            </c:if>
          </td>
       	<td ></td>
        </tr>
        </table>
        </div>
        <br />
   		<div style="BORDER: #f6f6f6 2px solid;flot:left;">
        <table width="100%" class="tableClass" style="flot:left;;width:30%;">
        <tr>
          <td colspan="4" style="text-align:center;color:#e60012;font-size:14px;">个人信息</td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td  align="right" style="width:150px;">姓名：</td>
          <td colspan="3"><c:out value="${af.map.real_name}" />
            <c:if test="${empty af.map.real_name}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">性别：</td>
          <td colspan="3"><c:out value="${af.map.sex eq 0 ? '男' : (af.map.sex eq 1 ? '女' : '保密')}" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">居住地：</td>
          <td colspan="3"><c:out value="${p_index_name}" />
            <c:if test="${empty p_index_name}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">联系地址：</td>
          <td colspan="3"><c:out value="${af.map.link_addr}" />
            <c:if test="${empty af.map.link_addr}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">邮编：</td>
          <td colspan="3"><c:out value="${af.map.link_post}" />
            <c:if test="${empty af.map.link_post}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">手机：</td>
          <td colspan="3"><c:out value="${af.map.link_phone}" />
            <c:if test="${empty af.map.link_phone}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">电话：</td>
          <td colspan="3"><c:out value="${af.map.link_tel}" />
            <c:if test="${empty af.map.link_tel}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">邮箱：</td>
          <td colspan="3"><c:out value="${af.map.email}" />
            <c:if test="${empty af.map.email}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">QQ：</td>
          <td colspan="3"><c:out value="${af.map.link_qq}" />
            <c:if test="${empty af.map.link_qq}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" align="right">MSN：</td>
          <td colspan="3"><c:out value="${af.map.link_msn}" />
            <c:if test="${empty af.map.link_msn}"><span style="color:#999;">未填写</span></c:if></td>
        </tr>
        <tr>
          <td colspan="4" style="height:60px;"  align="center">
             <input class="bgButtonSave" type="button" name="Submit5" value=" " onclick="location.href='JxcUserInfoUpdate.do?method=edit&user_id=${af.map.id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
          </td>
        </tr>
         
      </table>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>