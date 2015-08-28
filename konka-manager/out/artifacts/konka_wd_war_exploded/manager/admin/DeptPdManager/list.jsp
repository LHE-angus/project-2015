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
					<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" />
					</td>
					<td>当前位置：${naviString}</td>
					<td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<html-el:form action="/admin/DeptPdManager">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
					<tr>
						<td width="15"></td>
						<td><strong class="fb">部门名：</strong>
						  <html-el:text property="dept_name_like" styleId="dept_name_like" maxlength="40" /></td>
						<td><input class="but1" type="submit" name="Submit" value="搜索" />
						</td>
					</tr>
				</table>
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<%@ include file="/commons/pages/messages.jsp"%>
		</div>
		<div class="rtabcont1">
			<form id="listForm" name="listForm" method="post" action="DeptPdManager.do?method=delete">
				<input type="hidden" name="method" id="method" value="delete" /> 
				<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
				  <tbody>
					<tr class="tabtt1">
					    <td width="6%" nowrap="nowrap" >序号</td>
						<td nowrap="nowrap" align="center">部门名</td>
						<td width="20%" nowrap="nowrap" align="center">所属事业部</td>
						<td width="15%" nowrap="nowrap" align="center">所属区域</td>
						<td width="10%" nowrap="nowrap" align="center">操作</td>
					  </tr>
					<c:forEach var="cur" items="${entityList}" varStatus="vs">
					<tr>
					    <td align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
						<td align="center">${cur.dept_name}[<span style="color:#0066FF">分公司</span>]</td>
						<td align="center" valign="middle">
						  <span style="color:#0066FF">
						  ${cur.map.par_dept_name}
          			      </span>
          			    </td>
          			    <td align="center">${cur.p_name}</td>
						<td align="center" nowrap="nowrap">
							<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'DeptPdManager.do','edit', 'dept_id=${cur.dept_id}&' + $('#bottomPageForm').serialize())">						
							 <c:if test="${role_id ne 10 && role_id ne 20}">
                                      查看产品
                             </c:if>
                             <c:if test="${role_id eq 10 || role_id eq 20}">
                                      分派产品
                             </c:if>
							 </span>
						</td>
					</tr>
					<c:if test="${vs.last eq true}">
                       <c:set var="i" value="${vs.count}" />
                    </c:if>
				    </c:forEach>
				    <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
                      <tr align="center">
                       <td>&nbsp;</td>
                       <td>&nbsp;</td>
                       <td>&nbsp;</td>
                       <td>&nbsp;</td>
                       <td>&nbsp;</td>
                     </tr>
                    </c:forEach>
				  </tbody>
				</table>
			</form>
			<form id="bottomPageForm" name="bottomPageForm" method="post" action="DeptPdManager.do">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="40">
						  <div style="text-align: right; padding-right: 5px;">
			                <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
			                <script type="text/javascript">
	                           var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
	                           pager.addHiddenInputs("method", "list");
	                           pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	                           pager.addHiddenInputs("tree_param", "${tree_param}");
	                           pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
	                           document.write(pager.toString());
	                        </script>
					      </div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clear"></div>
	</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>	
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
