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
  <div class="rtabcont1">
  	  <html-el:hidden property="queryStr" styleId="queryStr" value="${queryStr}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-left:20px;">客户信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>上级客户：</td>
          <td align="left" colspan="3">${par_name }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>名称：</td>
          <td align="left">
          	<c:choose>
          		<c:when test="${af.map.is_r3 eq 0}">非康佳合作客户</c:when>
          		<c:when test="${af.map.is_r3 eq 1}">是康佳合作客户</c:when>
          	</c:choose>
          	${af.map.partner_name}
          </td>
          <td nowrap="nowrap" class="title_item" align="right">编号：</td>
          <td align="left">${af.map.partner_sn}</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">所属分公司/经办：</td>
          	<td align="left">
          		${af.map.map.dept_name}&nbsp;&nbsp;&nbsp;${af.map.map.jb_name}
          	</td>
          	<td nowrap="nowrap" class="title_item" align="right">创建时间：</td>
          	<td align="left"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">业务员：</td>
          	<td align="left">${af.map.map.ywy_name}
          	</td>
          	<td nowrap="nowrap" class="title_item" align="right" colspan="2"></td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">公司电话：</td>
          	<td align="left">${af.map.partner_company_phone}</td>
          	<td nowrap="nowrap" class="title_item" align="right">公司传真：</td>
          	<td align="left">${af.map.partner_fax}</td>	
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>所在城市：</td>
          <td align="left" colspan="3">
          	${af.map.map.PROVINCE}&nbsp;&nbsp;&nbsp;${af.map.map.CITY}&nbsp;&nbsp;&nbsp;${af.map.map.COUNTRY}&nbsp;&nbsp;&nbsp;${af.map.map.TOWN}
          </td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">街道地址：</td>
        	<td align="left" colspan="3">${af.map.partner_addr}</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" align="right">收货地址：</td>
        	<td align="left" colspan="3">
        		${af.map.map._PROVINCE}${af.map.map._CITY}${af.map.map._COUNTRY}${af.map.map._TOWN}${af.map.consignee_street}
        	</td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-left:20px;">联系人信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">姓名：</td>
          <td align="left">${af.map.link_name}</td>
          <td nowrap="nowrap" class="title_item" align="right">移动电话：</td>
          <td align="left">${af.map.link_mobile}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">性别：</td>
          <td align="left">
          	<c:choose>
	          	<c:when test="${af.map.link_sex eq 1}">男</c:when>
	          	<c:when test="${af.map.link_sex eq 0}">女</c:when>
	          </c:choose>
          </td>
          <td nowrap="nowrap" class="title_item" align="right">职务：</td>
          <td align="left">${af.map.link_job}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
          <td align="left">${af.map.link_tel}</td>
          <td nowrap="nowrap" class="title_item" align="right">QQ/MSN：</td>
          <td align="left">${af.map.link_qq_msn}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">证件类型：</td>
          <td align="left">
	          <c:choose>
	          	<c:when test="${af.map.link_id_type eq 0}">身份证</c:when>
	          	<c:when test="${af.map.link_id_type eq 1}">护照</c:when>
	          	<c:when test="${af.map.link_id_type eq 2}">港澳通行证</c:when>
	          	<c:when test="${af.map.link_id_type eq 3}">台湾通行证</c:when>
	          </c:choose>
          </td>
          <td nowrap="nowrap" class="title_item" align="right">证件号码：</td>
          <td align="left">${af.map.link_id}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">联系地址：</td>
          <td align="left" colspan="3">${af.map.link_addr}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left" colspan="3">${af.map.memo}</td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-left:20px;">账户信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">*</span>&nbsp;用户名：</td>
          <td colspan="3">${af.map.map.user_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">*</span> 账户密码：</td>
          <td colspan="3"><input type="password" value="${af.map.map.pass_word }" disabled="disabled"/>（注：默认密码为“888888”）</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>是否删除：</td>
          <td align="left" colspan="3">${af.map.is_del eq 0 ? '否' : '是'}</td>
        </tr>
        <tr>
          <td colspan="4" align="center">
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back()" /></td>
        </tr>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>