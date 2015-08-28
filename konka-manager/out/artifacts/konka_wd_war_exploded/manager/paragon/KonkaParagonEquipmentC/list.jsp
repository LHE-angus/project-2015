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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/paragon/KonkaParagonEquipmentC">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="etype"  />
      <html-el:hidden property="mod_id"  />
     <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
	        <td><strong class="fb">名称：</strong>
            	<html-el:text property="equipment_name_like" size="20" style="width:90px;" maxlength="10" styleId="equipment_name_like" styleClass="webinput" />
           </td>
            <td><strong class="fb">添加时间：</strong>
		      <html-el:text property="st_pub_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1949, 2029, 0).show(this);" />
		      至：
		      <html-el:text property="en_pub_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1949, 2029, 0).show(this);" />
            </td>
	        <td><html-el:submit styleClass="but1" value="搜索" styleId="btn_submit" /></td>
           </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
	    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaParagonEquipmentC.do?method=add&mod_id=${af.map.mod_id}&etype=${af.map.etype}&';" />
	    <input class="but3" type="button" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaParagonEquipmentC.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <input type="hidden" name="etype" id="etype" value="${af.map.etype}" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="25" align="center" ><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="30" align="center" >序号</td>
          <td nowrap="nowrap" align="center" >名称</td>
          <c:if test="${af.map.etype eq 2}">
          <td width="80" align="center" >样机尺寸</td>
          </c:if>
          <td width="50" align="center" >状态</td>
          <td width="70" nowrap="nowrap" align="center" >添加时间</td>
          <td width="90" nowrap="nowrap" align="center" >操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.equipment_id ne 1 && cur.equipment_id ne 2}">
            	<input name="pks" type="checkbox" id="pks" value="${cur.equipment_id}" />
			</c:if>
			<c:if test="${cur.equipment_id eq 1 || cur.equipment_id eq 2}">
            	<input name="pks" type="checkbox" id="pks" value="${cur.equipment_id}" disabled="disabled"/>
			</c:if>
            </td>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align=left nowrap="nowrap">${fn:escapeXml(cur.equipment_name)}</td>
             <c:if test="${af.map.etype eq 2}">
            <td align=center nowrap="nowrap">${cur.proto_size}</td>
            </c:if>
            <td align="center" nowrap="nowrap">
            	<c:choose>
            		<c:when test="${cur.status eq 1}">正常</c:when>
            		<c:when test="${cur.status eq 2}">锁定</c:when>
            	</c:choose>
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.addtime}" pattern="yyyy-MM-dd" /></td>
            <td align="center">
            	<logic-el:match name="popedom" value="+2+">
					<c:if test="${cur.equipment_id ne 1 && cur.equipment_id ne 2}">
	           		 	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonEquipmentC.do', 'edit','equipment_id=${cur.equipment_id}&mod_id=${af.map.mod_id}&etype=${af.map.etype}&'+$('#bottomPageForm').serialize())">修改</span>
           		 	</c:if>
					<c:if test="${cur.equipment_id eq 1 || cur.equipment_id eq 2}">
						<span style="color:#CCC;">修改</span>
					</c:if>
				</logic-el:match>
				<logic-el:notMatch name="popedom" value="+2+">
					<span style="color:#CCC;">修改</span>
				</logic-el:notMatch>
				|
				<logic-el:match name="popedom" value="+4+">
					<c:if test="${cur.equipment_id ne 1 && cur.equipment_id ne 2}">
		            	<span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaParagonEquipmentC.do', 'equipment_id=${cur.equipment_id}&mod_id=${af.map.mod_id}&etype=${af.map.etype}&'+$('#bottomPageForm').serialize())">删除</span> 
					</c:if>
					<c:if test="${cur.equipment_id eq 1 || cur.equipment_id eq 2}">
						<span style="color:#CCC;">删除</span>
					</c:if>
				</logic-el:match>
				<logic-el:notMatch name="popedom" value="+4+">
					<span style="color:#CCC;">删除</span>
				</logic-el:notMatch>	         
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
            <c:if test="${af.map.etype eq 2}">
            <td>&nbsp;</td>
            </c:if>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonEquipmentC.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("etype", "${af.map.etype}");
			pager.addHiddenInputs("equipment_name_like", "${fn:escapeXml(af.map.equipment_name_like)}");		
			pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
			pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if (this.form.st_pub_date.value != "" && this.form.en_pub_date.value != "") {
			if (this.form.en_pub_date.value < this.form.st_pub_date.value) {
				alert("结束日期不得大于开始日期,请重新选择!")
				return false;
			}
		}
		this.form.submit();
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
