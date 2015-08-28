<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
}

span.sel-tab {
	height: 30px;
	display: block;
	float : right;
	padding: 0px 10px;
	margin-top: 2px;
	margin-right: 10px;
	border-radius:3px 3px 0px 0px;
}
span.sel-tab-active {
	border-bottom : 0px;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
	background-color: #FFF;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align: middle;" /> <span id="span_help" style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
			<html-el:form action="/admin/KonkaYjglPlan">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="mod_id" styleId="mod_id" />
				<table width="100%" border="0" cellspacing="1" cellpadding="5"
					class="rtable1">
					<tr>
						<td width="10%" nowrap="nowrap" ><strong
							class="fb">部门：</strong></td> 
						<td width="20%"><html-el:select property="dept_id" styleId="dept_id" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>
						</td>
						<td width="10%"><strong class="fb">年度：</strong>
						</td>
						<td width="20%">
							<html-el:select property="plan_year" styleId="plan_year" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          					<html-el:option value="2013">2013</html-el:option>
	          					<html-el:option value="2014">2014</html-el:option>
	          					<html-el:option value="2015">2015</html-el:option>
	          					<html-el:option value="2016">2016</html-el:option>
	          					<html-el:option value="2017">2017</html-el:option>
	          					<html-el:option value="2018">2018</html-el:option>
	          			</html-el:select>
						</td>
						
					</tr>
					<tr>
					<td width="10%"><strong
							class="fb">下发时间：</strong></td>
					<td width="30%">
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
					</td>
					<td width="10%"></td> 
						<td width="30%" align="center" nowrap="nowrap"><html-el:submit
								styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
					</tr>
					
				</table>
			</html-el:form>
		</div>
 <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
            <c:if test="${fn:contains(popedom,'+1+')}">
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaYjglPlan.do?method=add&mod_id=${af.map.mod_id}';" /> 
			</c:if>
		</td>
	 </tr>
	</table>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">年度</td>
        <td align="center" width="6%" nowrap="nowrap">类型</td>
        <td align="center" width="6%" nowrap="nowrap">额度(万元)</td>
        <td width="3%" nowrap="nowrap" align="center">下发时间</td>
         <td width="3%" nowrap="nowrap" align="center">计划上样时间</td>
        <td  nowrap="nowrap" align="center">备注</td>
        <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.plan_year}</td>
          <td align="left" nowrap="nowrap">
           ${cur.map.type_name}
          </td>
          <td align="right" nowrap="nowrap">
          <fmt:formatNumber value="${cur.plan_ed}" pattern="#0.00"></fmt:formatNumber>
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.xf_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.plan_cy_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap">${cur.remark}</td>
          <td align="center" nowrap="nowrap">
           <c:choose>
           <c:when test="${fn:contains(popedom,'+2+')}">
          		<span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaYjglPlan.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
           </c:when>
          <c:otherwise>
          <span style="color: #ccc">修改</span>
          </c:otherwise>
           </c:choose>
          |<span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaYjglPlan.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span></td>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td> 
        </tr>
      </c:forEach>
    </table>
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaYjglPlan.do"> 
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");							
			pager.addHiddenInputs("plan_year", "${af.map.plan_year}");
			pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
			pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");							
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	//$(".list-tr td").each(function(){
		//var text = $(this).html();
		//if($.trim(text).length == 0) {
			//$(this).html("<span style='color:#CCC;'>未填写</span>");
		//}
	//});

	
	
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
