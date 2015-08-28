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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/GiftInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
           <strong class="fb">赠品名称：</strong>
            <html-el:text property="gift_name_like" styleId="gift_name_like" size="16" maxlength="20"></html-el:text>
           &nbsp;&nbsp;
           <strong class="fb">赠品类别：</strong>
           	<html-el:select property="type_id" styleId="type_id">
           		<html-el:option value="">-请选择-</html-el:option>
           		<c:forEach items="${kList}" var="cur">
           			<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">停用状态：</strong>
           	<html-el:select property="status" styleId="status">
           		<html-el:option value="0">未停用</html-el:option>
           		<html-el:option value="1">已停用</html-el:option>
           	</html-el:select>
           &nbsp;&nbsp;
           <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <logic-el:match name="popedom" value="+1+">
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='GiftInfo.do?method=add&mod_id=${af.map.mod_id}&is_admin=${is_admin}&tree_param=${tree_param}';" />
          </logic-el:match>	
          <input type="button" value="导出" id="export_excel" class="but_excel" style="margin-left: 10px;" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">赠品类别</td>
        <td nowrap="nowrap"  align="center" >赠品名称</td>
        <td nowrap="nowrap"  align="center" width="12%">参考单价</td>
        <td nowrap="nowrap"  align="center" width="12%">添加时间</td>
        <td nowrap="nowrap"  align="center" width="8%">是否停用</td>
         <td nowrap="nowrap"  align="center" width="8%">排序值</td>
        <td nowrap="nowrap"  align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.c_name)}</td>
            <td align="left">${fn:escapeXml(cur.gift_name)}</td>
            <td align="right"><fmt:formatNumber value="${cur.ref_price}" pattern="#0.00" /></td>
            <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td align="center"><c:choose>
            	<c:when test="${cur.status eq 0}">未停用</c:when>
            	<c:otherwise>已停用</c:otherwise>
            </c:choose></td>
            <td align="right">${cur.order_value}</td>
            <td align="center"><logic-el:match name="popedom" value="+2+">
            <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'GiftInfo.do', 'edit','gift_id=${cur.gift_id}&is_admin=${is_admin}&'+$('#bottomPageForm').serialize())">修改</span>
            	|<c:if test="${cur.status eq 0}"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'GiftInfo.do', 'reStop','gift_id=${cur.gift_id}&'+$('#bottomPageForm').serialize())">停用</span></c:if>
            	 <c:if test="${cur.status ne 0}"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'GiftInfo.do', 'reStart','gift_id=${cur.gift_id}&'+$('#bottomPageForm').serialize())">启用</span></c:if>
              	</logic-el:match>
            </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
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
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="GiftInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("gift_name_like", "${af.map.gift_name_like}");
				pager.addHiddenInputs("type_id", "${af.map.type_id}");
				pager.addHiddenInputs("status", "${af.map.status}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
   <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="赠品信息">
   	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">赠品类别</td>
        <td nowrap="nowrap"  align="center" >赠品名称</td>
        <td nowrap="nowrap"  align="center" width="12%">参考单价</td>
        <td nowrap="nowrap"  align="center" width="12%">添加时间</td>
        <td nowrap="nowrap"  align="center" width="8%">是否停用</td>
         <td nowrap="nowrap"  align="center" width="8%">排序值</td>
      </tr>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.c_name)}</td>
            <td align="left">${fn:escapeXml(cur.gift_name)}</td>
            <td align="right"><fmt:formatNumber value="${cur.ref_price}" pattern="#0.00" /></td>
            <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td align="center"><c:choose>
            	<c:when test="${cur.status eq 0}">未停用</c:when>
            	<c:otherwise>已停用</c:otherwise>
            </c:choose></td>
            <td align="right">${cur.order_value}</td>
          </tr>
        </c:forEach>
     </table>   
   </div>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	 // 导出excel
    $("#export_excel").click(function(){
    	if(confirm("提示，您确认导出数据？")){
    		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
    		$("#bottomPageForm").submit();
    	}
    });

    var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '${ctx}/manager/admin/GiftInfo.do?method=toExcel');
	}
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
