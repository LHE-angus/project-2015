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
			<td width="12%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
			<td width="88%" align="left">${af.map.dept_name}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">年度：</td>
			<td align="left">
				${af.map.yjed_year}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">样机总额：</td>
			<td align="left">${af.map.yjed_total}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">已使用额度：</td>
			<td align="left">${af.map.yjed_used}</td> 
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">剩余额度：</td>
			<td align="left">${af.map.yjed_not_use}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">基准额度：</td>
			<td align="left">${af.map.yjed_jz}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">专项额度：</td>
			<td align="left">${af.map.yjed_zx}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">临时额度：</td>
			<td align="left">${af.map.yjed_ls}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">备注：</td>
			<td align="left">${af.map.remark}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">额度明细：</td>
			<td align="left">
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr><td>序号</td>
					<td>追加类型</td>
					<td>追加金额</td>
					<td>追加人</td>
					<td>追加时间</td>
					<td>备注</td>
					</tr>
					<c:forEach items="${kaList}" var="cur" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td><c:if test="${cur.yjed_type eq 0}">基准额度</c:if>
							<c:if test="${cur.yjed_type eq 1}">临时额度</c:if>
							<c:if test="${cur.yjed_type eq 2}">专项额度</c:if> 
							</td>
							<td>${cur.yjed_num}</td>
							<td>${cur.real_name}</td>
							<td> <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /> </td>
							<td>${cur.remark}</td>
						</tr>
					</c:forEach>
				
				</table>
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