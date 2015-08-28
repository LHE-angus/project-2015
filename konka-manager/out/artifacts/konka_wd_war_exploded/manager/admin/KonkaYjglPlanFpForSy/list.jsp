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
			<html-el:form action="/admin/KonkaYjglPlanFpForSy">
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
						<td width="20%"><strong class="fb">计划样机上样状态：</strong>
						</td>
						<td width="10%"> 
							<html-el:select property="sy_state" styleId="sy_state" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          					<html-el:option value="0">未确认</html-el:option> 
	          					<html-el:option value="1">待上样</html-el:option>
	          					<html-el:option value="2">已上样</html-el:option>
	          					<html-el:option value="3">上样中</html-el:option>
	          					<html-el:option value="4">已下架</html-el:option>
	          			</html-el:select>  
						</td>
						<td width="10%"></td>
					</tr>
					<tr>
					<td width="10%"><strong
							class="fb">门店：</strong></td>
					<td width="20%">
        		<html-el:text property="store_name_like" styleId="store_name_like" maxlength="30" />
				</td>
				<td width="10%"><strong
							class="fb">型号：</strong></td>
					<td width="20%">
        		<html-el:text property="pd_name_like" styleId="pd_name_like" maxlength="30" />
				</td>
				<td width="20%"><strong class="fb">计划样机分配状态：</strong>
						</td>
						<td width="10%">
							<html-el:select property="is_confirm" styleId="is_confirm" style="width:120px;" onchange="this.form.submit();">
	          				<html-el:option value="">请选择</html-el:option>
	          					<html-el:option value="0">未确认</html-el:option>
	          					<html-el:option value="1">已确认</html-el:option>
	          			</html-el:select>  
						</td>
						<td width="10%" align="center" nowrap="nowrap"><html-el:submit
								styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
					</tr>
					
				</table>
			</html-el:form>
		</div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
         <td width="4%" align="center" nowrap="nowrap">年度</td>
        <td width="6%" align="center" nowrap="nowrap">适用计划</td>
        <td align="center" width="6%" nowrap="nowrap">门店</td>
        <td align="center" width="6%" nowrap="nowrap">型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="3%" nowrap="nowrap" align="center">已上样数量</td>
        <td width="3%" nowrap="nowrap" align="center">已下样数量</td>
        <td width="3%" nowrap="nowrap" align="center">计划上样时间</td>
        <td  nowrap="nowrap" width="6%" align="center">分配人</td>
        <td  nowrap="nowrap" width="6%" align="center">计划样机分配状态</td>
        <td  nowrap="nowrap" width="6%" align="center">计划样机上样状态</td>
        <td width="6%" nowrap="nowrap" align="center">操作</td> 
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}(${cur.id}) 
          </td> 
          <td align="left" nowrap="nowrap">${cur.plan_year}</td>
          <td align="left" nowrap="nowrap">
            ${cur.map.type_name}
          </td>
          <td align="left" nowrap="nowrap">
           ${cur.map.store_name} 
          </td>
           <td align="left" nowrap="nowrap">
           ${cur.pd_name}
          </td>
          <td align="left" nowrap="nowrap">
           ${cur.num}
          </td>
           <td align="left" nowrap="nowrap">
           ${cur.map.sy_num}
          </td>
           <td align="left" nowrap="nowrap">
           ${cur.map.xy_num}  
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.sy_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="left" nowrap="nowrap">
           ${cur.map.user_name}
          </td>
          <td align="center" nowrap="nowrap">
          <c:if test="${cur.is_confirm eq 0}">未确认</c:if>
          <c:if test="${cur.is_confirm eq 1}">已确认</c:if>
          </td>
           <td align="center" nowrap="nowrap">
           <c:choose>
              <c:when test="${cur.is_confirm eq 0}"> 
            		   未确认
              </c:when>
              <c:otherwise>         
                <c:if test="${cur.map.sy_num eq 0 and (cur.num gt (cur.map.xy_num + cur.map.sy_num))}">待上样</c:if>
                <c:if test="${cur.map.sy_num gt 0 and cur.num ne cur.map.sy_num}">已上样</c:if>
                <c:if test="${cur.map.sy_num gt 0 and cur.num eq cur.map.sy_num and cur.num gt 0}">上样中</c:if>
                <c:if test="${cur.map.sy_num eq 0 and cur.num eq cur.map.xy_num}">已下架</c:if>
              </c:otherwise>
           </c:choose>
          </td>
          <td align="center" nowrap="nowrap">
           <c:choose>
           <c:when test="${fn:contains(popedom,'+8+')}">  
           <c:if test="${cur.is_confirm eq 1}">
           <c:choose>
            <c:when test="${cur.num-cur.map.sy_num-cur.map.xy_num gt 0}">
           <span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaYjglPlanFpForSy.do', 'upself','id=${cur.id}&'+$('#bottomPageForm').serialize())">计划性上样</span>
           </c:when>
            <c:otherwise>
            <span style="color: #ccc">计划性上样</span>  
            </c:otherwise>
           </c:choose>
           </c:if>
            <c:if test="${cur.is_confirm eq 0}"><span style="color: #ccc">计划性上样</span>  
             </c:if>
            </c:when>
          <c:otherwise>
          <span style="color: #ccc">计划性上样</span>
          </c:otherwise> 
           </c:choose>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaYjglPlanFpForSy.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("sy_state", "${af.map.sy_state}");								
			pager.addHiddenInputs("plan_year", "${af.map.plan_year}");
			pager.addHiddenInputs("is_confirm", "${af.map.is_confirm}");
			pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");							
			pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");	
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
