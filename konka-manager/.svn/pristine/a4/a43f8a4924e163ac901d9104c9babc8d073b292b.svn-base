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
  <html-el:form action="/admin/KonkaPriceManager">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="80%" nowrap="nowrap">
          &nbsp;&nbsp;
          <strong class="fb">型&nbsp;&nbsp;号：</strong>
           <html-el:text property="goods_name" styleId="goods_name" size="20" maxlength="40"></html-el:text>
           &nbsp;&nbsp;
          <strong class="fb">仓&nbsp;&nbsp;库：</strong>
           <html-el:text property="store_sn" styleId="store_sn" size="20" maxlength="40"></html-el:text>
           &nbsp;&nbsp;
          <input class="but1" type="submit" name="Submit" value="搜索" />
        </td>
      </tr>
    </table>
  </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
	<logic-el:match name="popedom" value="+1+">  
		<input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		&nbsp;
	    <input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='KonkaPriceManager.do?method=add_excel&mod_id=${af.map.mod_id}';" />
	</logic-el:match>
	</div>
<form id="listForm" name="listForm" method="post" action="KonkaPriceManager.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		<tr class="tabtt1">
		 	<td width="3%" align="center" nowrap="nowrap">
		 	<input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
		 	</td>
			<td width="5%" align="center" nowrap="nowrap">序号</td>
			<td width="10%" align="center" nowrap="nowrap">型号</td>
			<td width="10%" align="center" nowrap="nowrap">仓库</td>
			<td width="10%" align="center" nowrap="nowrap">价格</td>
			<td width="10%" align="center" nowrap="nowrap">开始时间</td>
			<td width="10%" align="center" nowrap="nowrap">结束时间</td>
			<td width="10%" align="center" nowrap="nowrap">操作</td>
		</tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
				<td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
				<td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.goods_name)}</font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.store_sn)}</font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatNumber value="${cur.price}" pattern="##.##" minFractionDigits="0" /></font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.start_date }" pattern="yyyy-MM-dd"/></font></td>
				<td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.end_date }" pattern="yyyy-MM-dd"/></font></td>
				<td align="center" nowrap="nowrap">
					<a href="KonkaPriceManager.do?method=edit&mod_id=${af.map.mod_id}&id=${cur.id}">修改</a>
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
		     </tr>
	    </c:forEach>
     </table>
     </form>
     <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPriceManager.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("goods_name", "${fn:escapeXml(af.map.goods_name)}");
			pager.addHiddenInputs("store_sn", "${fn:escapeXml(af.map.store_sn)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
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

// 		<c:if test="${af.map.dept_type eq 3}">
// 			$("#dept_sn").attr("disabled", "true");
// 		</c:if>
   });     
   
   function confirmDeleteAll(form) {
		form.action = 'KonkaPriceManager.do';
		form.method.value = 'delete';
		var checkedCount = 0;
		if (!form.pks) {
			return;
		}
		if(!form.pks.length) {
			if (form.pks.checked == true) {
				checkedCount = 1;
			}
		}
		for (var i = 0; i < form.pks.length; i++) {
			if (form.pks[i].checked == true) {
				checkedCount++;
			}
		}
		if (checkedCount == 0) {
			alert("请至少选择一个！");
		} else {
				form.submit();
		}
	}  
   

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