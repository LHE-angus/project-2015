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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaPartership">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">
          &nbsp;&nbsp;<strong class="fb">售达方编码：</strong>
          <html-el:text property="shoudf_id_like" size="20" maxlength="20" styleId="shoudf_id" styleClass="webinput" />
           &nbsp;&nbsp;<strong class="fb">售达方名称：</strong>
          <html-el:text property="shoudf_name_like" size="20" maxlength="20" styleId="shoudf_name" styleClass="webinput" />
          &nbsp;&nbsp;<strong class="fb">送达方编码：</strong>
          <html-el:text property="songdf_id_like" size="20" maxlength="20" styleId="songdf_id" styleClass="webinput" />
          &nbsp;&nbsp;<strong class="fb">送达方名称：</strong>
          <html-el:text property="songdf_name_like" size="20" maxlength="20" styleId="songdf_name" styleClass="webinput" />
          &nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <html-el:form action="/admin/KonkaPartership?method=delete">
  <input type="hidden" name="method" id="method" value="delete" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaPartership.do?method=add&mod_id=${af.map.mod_id}';" />
        	<input id="export_excel" class="but_excel" type="button" name="Submit2" value="导出"/>
        	<input class="but3" type="button" name=button3 id="button3" style="width:67px;" value="删除" onClick="confirmDeleteAll(this.form);" />
<!--         	<input type="button" class="but_excel" onClick="doNeedMethod(null, 'KonkaSpActivityManager.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())" value="导出" /> -->
<!--           <span style="color:#F00;">注：请先搜索再导出，因数据量过大会导致系统反应缓慢或宕机，因此这些数据将不作导出。</span>  -->
        </td>
      </tr>
    </table>
  	<div style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
      	<td width="3%" align="center" nowrap="nowrap">
        <input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" />
        </td>
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="15%" align="center" nowrap="nowrap">售达方编码</td>
        <td width="15%" align="center" nowrap="nowrap">售达方名称</td>
        <td width="10%" align="center" nowrap="nowrap">送达方编码</td>
        <td width="10%" align="center" nowrap="nowrap">送达方名称</td>
        <td width="5%" nowrap="nowrap" align="center">添加时间</td>
        <td width="8%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
            </td>
            <td align="center" nowrap="nowrap"> ${vs.count}</td>
            <td align="left" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaPartership.do','view','&id=${cur.id }&' + $('#bottomPageForm').serialize())">${cur.shoudf_id}</span></td>
            <td align="left" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaPartership.do','view','&id=${cur.id }&' + $('#bottomPageForm').serialize())">${cur.shoudf_name}</span></td>
            <td align="left" nowrap="nowrap">&nbsp;${cur.songdf_id}&nbsp;</td>
            <td align="left" nowrap="nowrap">&nbsp;${cur.songdf_name}&nbsp;</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
            <td nowrap="nowrap" align="center">
              	<span style="cursor:pointer;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaPartership.do','edit','&id=${cur.id }&' + $('#bottomPageForm').serialize())">修改</span>
                <span style="cursor:pointer;" class="fblue" onClick="confirmDelete('确定删除吗?', '${ctx}/manager/admin/KonkaPartership.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
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
      </tbody>
    </table>
    </div>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPartership.do">
      <input name="export" id="export" type="hidden"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("shoudf_id_like", "${af.map.shoudf_id_like}");
							pager.addHiddenInputs("songdf_id_like", "${af.map.songdf_id_like}");
							pager.addHiddenInputs("shoudf_name_like", "${af.map.shoudf_name_like}");
							pager.addHiddenInputs("songdf_name_like", "${af.map.songdf_name_like}");
							document.write(pager.toString());
						</script> 
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
// 	$("#total_sail_money_min").focus(setOnlyNum);
// 	$("#total_sail_money_max").focus(setOnlyNum2);
	//导出excel
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
		//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
			$("#export").val("true");
			$("#bottomPageForm").submit();
		}
		$("#export").val("");
	});
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>