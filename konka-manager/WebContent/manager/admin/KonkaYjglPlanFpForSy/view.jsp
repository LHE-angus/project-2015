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
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
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
			<td nowrap="nowrap" class="title_item" align="right">门店：</td>
			<td align="left">
				${af.map.store_name}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">待上架：</td>
			<td align="left">
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr><td>序号</td>
					<td>型号</td>
					<td>数量</td>
					<td>操作</td>
					</tr>
					<c:forEach items="${kfList}" var="cur" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td>${cur.pd_name}</td>
							<td>${cur.num}</td>
							<td><span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaYjglPlanFpForSy.do', 'upGoods','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">上样</span></td>
						</tr>
					</c:forEach>
				
				</table>
			</td>
		</tr> 
		<tr>
	          <td align="center" colspan="2">
				<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="doNeedMethod(null, 'KonkaYjglPlanFpForSy.do', 'list','mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" />  
	          </td>
	    </tr>		
  	</table>
  </div>
  
</div>
</body>
</html>