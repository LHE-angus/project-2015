<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oarcont" id="body_oarcont">
		<div class="oartop">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
					<td nowrap="nowrap">当前位置：${naviString} &gt;
						${konkaR3Shop.customer_name} &gt; 用户管理</td>
					<td width="60"><img src="${ctx}/images/manager/help.gif"
						style="vertical-align: middle;" /> <span id="span_help"
						style="cursor: pointer;">帮助</span></td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<html-el:form action="/admin/CustomerUsers.do">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="cust_id" />
				<html-el:hidden property="mod_id" />
				<table width="100%" border="0" cellspacing="5" cellpadding="0"
					class="rtable1">
					<tr style="height: 25px;">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">姓名：</strong> <html-el:text
								property="name_like" styleId="name_like" style="width:200px;" />
							<strong class="fb">是否停用：</strong> <html-el:select
								property="is_del" styleId="is_del" style="width:70px;">
								<html-el:option value="0">未停用</html-el:option>
								<html-el:option value="1">已停用</html-el:option>
							</html-el:select> &nbsp;&nbsp; <input class="but1" type="submit" name="Submit"
							value="搜索" />
						</td>
					</tr>
				</table>
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<%@ include file="/commons/pages/messages.jsp"%>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><logic-el:match name="popedom" value="+1+">
							<input class="but2" type="submit" name="Submit2" value="新增"
								onclick="location.href='CustomerUsers.do?method=add&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}';return false;" />
						</logic-el:match></td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<form id="listForm" name="listForm" method="post"
				action="CustomerUsers.do">
				<input type="hidden" name="method" id="method" value="delete" /> <input
					type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="rtable2">
					<tbody>
						<tr class="tabtt1">
							<td width="5%" nowrap="nowrap"><font class="blue">序号</font></td>
							<td width="8%" nowrap="nowrap"><font class="blue">客户R3编码</font></td>
							<td nowrap="nowrap"width="22%"><font class="blue">客户名称</font></td>
							<td width="10%" nowrap="nowrap"><font class="blue">登录名</font></td>
							<td width="8%" nowrap="nowrap"><font class="blue">姓名</font></td>
							<td width="8%" nowrap="nowrap" align="center"><font
								class="blue">手机/电话</font></td>
							<td width="8%" nowrap="nowrap" align="left"><font
								class="blue">邮箱</font></td>
							<td width="8%" nowrap="nowrap" align="left"><font
								class="blue">添加时间</font></td>

							<td width="8%" nowrap="nowrap" align="left"><font
								class="blue">添加人</font></td>

							<td width="10%" align="center"><font class="blue">操作</font></td>
						</tr>
						<c:forEach var="cur" items="${entityList}" varStatus="vs">
							<tr>
								<td height="28" align="center">${vs.count}</td>
								<td align="left"><c:out value="${konkaR3Shop.r3_code}" /></td>
								<td align="left">[${empty konkaCategory.c_name ? '无客户类型' :
									konkaCategory.c_name}]<c:out
										value="${konkaR3Shop.customer_name}" />
								</td>
								<td align="left"><c:out value="${cur.user_name}" /></td>
								<td align="left" valign="middle"><a
									style="text-decoration: underline;"
									href="CustomerUsers.do?method=view&user_id=${cur.id}&role_id=${cur.map.role_id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}">${cur.real_name}</a></td>
								<td align="center"><c:out
										value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
								<td align="center">${cur.email}</td>
								<td align="center"><fmt:formatDate value="${cur.add_date}"
										pattern="yyyy-MM-dd" /></td>
								<td align="center">${cur.map.add_user_name}</td>
								<td align="center" nowrap="nowrap"><c:if
										test="${cur.is_del eq 0}">
										<span style="cursor: pointer;" class="fblue"
											onclick="if(confirm('是否重置？')){doNeedMethod(null, 'CustomerUsers.do', 'initPassword','user_id=${cur.id}&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}&')}"
											title="重置密码">重置</span>
										<span style="cursor: pointer;" class="fblue"
											onclick="doNeedMethod(null, 'CustomerUsers.do', 'userLogin','user_id=${cur.id}&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}&')">登陆</span>
										<span style="cursor: pointer;" class="fblue"
											onclick="doNeedMethod(null, 'CustomerUsers.do', 'edit','user_id=${cur.id}&mod_id=${af.map.mod_id}&')">修改</span>
										<span style="cursor: pointer;" class="fblue"
											onclick="confirmDelete(null, 'CustomerUsers.do','user_id=${cur.id}&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}&')">停用</span>
									</c:if> <c:if test="${cur.is_del eq 1}">
										<span style="color: #ccc" title="该用户已被停用，请先启用该用户">重置</span>
										<span style="color: #CCC;" title="该用户已被停用，请先启用该用户">登录</span>
										<span style="color: #CCC;" title="该用户已被停用，请先启用该用户">修改</span>
										<span style="cursor: pointer;" class="fblue"
											onclick="doNeedMethod('确定启用吗？', 'CustomerUsers.do', 'reStart','user_id=${cur.id}&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}&')">启用</span>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">
		;
	</script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript">
		//<![CDATA[
		$(document).ready(function() {
		});
		//]]>
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
