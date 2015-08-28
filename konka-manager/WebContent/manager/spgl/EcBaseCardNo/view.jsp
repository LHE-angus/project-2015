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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
  	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
  		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">会员卡号：</td>
			<td width="88%" align="left">${af.map.card_no}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">发卡日期：</td>
			<td align="left">
				<fmt:formatDate value="${af.map.card_pub_date}"  pattern="yyyy-MM-dd"></fmt:formatDate>
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">客户：</td>
			<td align="left">${af.map.card_sender}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">发卡人部门：</td>
			<td align="left">
				${af.map.card_sender_dept}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员卡分类：</td>
			<td align="left">
				${af.map.ecBaseCardType.card_type_name}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员卡等级：</td>
			<td align="left">
				${af.map.ecBaseCardLevel.card_level_name}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员姓名：</td>
			<td align="left">
				${af.map.member_name}
			</td>
		</tr>  
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员手机号码：</td>
			<td align="left">
				${af.map.member_tel}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员身份证：</td>
			<td align="left">
				${af.map.member_id}
			</td>
		</tr> 
		<tr><td nowrap="nowrap" class="title_item" align="right">会员卡激活有效期：</td>
			<td align="left"><fmt:formatDate value="${af.map.card_act_valid_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
		</tr> 
		<tr><td nowrap="nowrap" class="title_item" align="right">会员卡激活时间：</td>
			<td align="left"><fmt:formatDate value="${af.map.card_act_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员卡制作人：</td>
			<td align="left">
				${af.map.card_creater}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">制作人部门：</td>
			<td align="left">
				${af.map.card_create_dept}
			</td>
		</tr> 
		<tr><td nowrap="nowrap" class="title_item" align="right">会员卡有效期开始：</td>
			<td align="left"><fmt:formatDate value="${af.map.card_limit_start}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
		</tr>
		<tr><td nowrap="nowrap" class="title_item" align="right">会员卡有效期结束：</td>
			<td align="left"><fmt:formatDate value="${af.map.card_limit_end}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">会员卡备注：</td>
			<td align="left">
				${af.map.card_memo}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">是否允许多次激活：</td>
			<td align="left">
				<c:if test="${af.map.card_allow_mul_act eq 0}">不允许</c:if>
				<c:if test="${af.map.card_allow_mul_act eq 1}">允许</c:if>
			</td>
		</tr> 
		<tr>
	          <td align="center" colspan="2">
				<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
	          </td>
	    </tr>		
  	</table>
  </div>
  
</div>
</body>
</html>