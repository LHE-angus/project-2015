<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td>当前位置：${naviString}</td>
      <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
    </tr>
  </table>
</div>
  <div class="rtabcont1">
  <html-el:form action="/admin/KonkaPlanRatio">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="this_year" value="${af.map.this_year}" />
    <html-el:hidden property="dept_type" value="${af.map.dept_type}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="80%" nowrap="nowrap">
          &nbsp;&nbsp;
          <strong class="fb">年&nbsp;&nbsp;度：</strong>
           <html-el:text property="year" styleId="year" size="4" maxlength="4" onfocus="javascript:setOnlyNum(this);"></html-el:text>
           &nbsp;&nbsp;
          <strong class="fb">分公司：</strong>
           <html-el:select property="fgs_sn_search" styleId="fgs_sn_search">
                       <html-el:option value="">请选择...</html-el:option>
                       <c:forEach var="cur" items="${deptList}">
                            <html-el:option value="${cur.dept_sn}">${fn:escapeXml(cur.dept_name)}</html-el:option>
                       </c:forEach>
           </html-el:select>
           &nbsp;&nbsp;
          <input class="but1" type="submit" name="Submit" value="搜索" />
        </td>
      </tr>
    </table>
  </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
	<c:if test="${af.map.dept_type eq 1}">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>
			    <logic-el:match name="popedom" value="+1+">  
			    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaPlanRatio.do?method=add&mod_id=${af.map.mod_id}&y=${af.map.this_year}&year=${af.map.year}&fgs_sn_search=${af.map.fgs_sn_search}&dept_type=${af.map.dept_type}';" />
			    </logic-el:match>
			</td>
		 </tr>
		</table>
	</c:if>
    <c:if test="${not empty totalList }">
    <div><span style="font-size:15px;">&nbsp;年度任务总额（单位：万元）</span></div>
    <div style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1" align="center">
        <td>年度</td>
        <td>1月</td>
        <td>2月</td>
        <td>3月</td>
        <td>4月</td>
        <td>5月</td>
        <td>6月</td>
        <td>7月</td>
        <td>8月</td>
        <td>9月</td>
        <td>10月</td>
        <td>11月</td>
        <td>12月</td>
        <td nowrap="nowrap">年度任务总额</td>
        <td nowrap="nowrap">已分配系数</td>
        <td nowrap="nowrap">操作</td>
	  </tr>
	  <c:forEach items="${totalList }" var="cur">
	  <tr>
		<td align="center">${cur.y }</td>
		<td align="right">${cur.map.m1 }</td>
		<td align="right">${cur.map.m2 }</td>
		<td align="right">${cur.map.m3 }</td>
		<td align="right">${cur.map.m4 }</td>
		<td align="right">${cur.map.m5 }</td>
		<td align="right">${cur.map.m6 }</td>
		<td align="right">${cur.map.m7 }</td>
		<td align="right">${cur.map.m8 }</td>
		<td align="right">${cur.map.m9 }</td>
		<td align="right">${cur.map.m10 }</td>
		<td align="right">${cur.map.m11 }</td>
		<td align="right">${cur.map.m12 }</td>
		<td align="right">${cur.m }</td>
		<td align="center">${cur.map.ratio }</td>
		<td align="center"><c:if test="${af.map.dept_type eq 1}"><a href="javascript:;" onclick="doNeedMethod(null, 'KonkaPlanRatio.do', 'edit','mod_id=${af.map.mod_id}&y=${cur.y}&id=${cur.id}&year=${af.map.year}&fgs_sn_search=${af.map.fgs_sn_search}&dept_type=${af.map.dept_type}&'+$('#bottomPageForm').serialize())">修改</a></c:if>
			<c:if test="${af.map.dept_type ne 1}"><span style="color:#ccc;">修改</span></c:if>
		</td>
	  </tr>
	  </c:forEach>
	</table>
	</div>
	<br />
	<div><span style="font-size:15px;">&nbsp;已分配任务系数：</span>
	<logic-el:match name="popedom" value="+1+">  
		<input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaPlanRatio.do?method=add_jb&mod_id=${af.map.mod_id}&y=${af.map.this_year}&year=${af.map.year}&fgs_sn_search=${af.map.fgs_sn_search }&dept_type=${af.map.dept_type}';" />
		&nbsp;
	    <input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='KonkaPlanRatio.do?method=add_excel&mod_id=${af.map.mod_id}&y=${af.map.this_year}&dept_type=${af.map.dept_type}';" />
	</logic-el:match>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		<tr class="tabtt1">
			<td width="5%" align="center" nowrap="nowrap">序号</td>
			<td width="10%" align="center" nowrap="nowrap">年度</td>
			<td width="10%" align="center" nowrap="nowrap">分公司编码</td>
			<td width="10%" align="center" nowrap="nowrap">分公司</td>
			<td width="10%" align="center" nowrap="nowrap">经办编码</td>
			<td width="10%" align="center" nowrap="nowrap">经办</td>
			<td width="10%" align="center" nowrap="nowrap">任务系数</td>
			<td width="10%" align="center" nowrap="nowrap">操作</td>
		</tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
				<td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.y)}</font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.fgs_sn)}</font></td>
				<td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.map.fgs_name)}</font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.dept_sn)}</font></td>
				<td align="left" nowrap="nowrap"><font class="blue12px">${fn:replace(cur.dept_name, ",", " &gt; ")}</font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatNumber value="${cur.ratio}" pattern="0.00000000"/></font></td>
				<td align="center" nowrap="nowrap"><c:if test="${af.map.this_year eq cur.y}">
					<c:if test="${af.map.dept_type eq 1}">
						<a href="javascript:;" onclick="doNeedMethod(null, 'KonkaPlanRatio.do', 'edit_jb','mod_id=${af.map.mod_id}&y=${cur.y}&id=${cur.id}&dept_type=${af.map.dept_type}&'+$('#bottomPageForm').serialize())">修改</a>
					</c:if>
					<c:if test="${(af.map.dept_type eq 3) and (cur.fgs_sn ne cur.dept_sn)}">
						<a href="javascript:;" onclick="doNeedMethod(null, 'KonkaPlanRatio.do', 'edit_jb','mod_id=${af.map.mod_id}&y=${cur.y}&id=${cur.id}&dept_type=${af.map.dept_type}&'+$('#bottomPageForm').serialize())">修改</a>
					</c:if>
					<c:if test="${(af.map.dept_type eq 3) and (cur.fgs_sn eq cur.dept_sn)}">
						<span style="color:#ccc;">修改</span>
					</c:if>
					</c:if>
					<c:if test="${af.map.this_year ne  cur.y}"><span style="color:#ccc;">修改</span></c:if></td>
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
		     </tr>
	    </c:forEach>
     </table>
     <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPlanRatio.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("year", "${fn:escapeXml(af.map.year)}");	
			pager.addHiddenInputs("fgs_sn_search", "${fn:escapeXml(af.map.fgs_sn_search)}");
			pager.addHiddenInputs("dept_type", "${fn:escapeXml(af.map.dept_type)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
	</c:if>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
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

		<c:if test="${af.map.dept_type eq 3}">
			$("#fgs_sn_search").attr("disabled", "true");
		</c:if>
   });     
   

 //正则表达式：只能输入数字
 function setOnlyNum(obj) {
   	$(obj).css("ime-mode", "disabled");
   	$(obj).attr("t_value", "");
   	$(obj).attr("o_value", "");
   	$(obj).bind("dragenter",function(){
   		return false;
   	});
   	$(obj).keypress(function (){
   		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
   	}).keyup(function (){
   		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
   	}).blur(function (){
   		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
   		//if(obj.value.length == 0) obj.value = "0";
   	});
 }
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>