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
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="zmd/KonkaXxZmdUsers">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td width="15" style="height:25px;"></td>
          <td style="height:25px;"> 专卖店编号：
            <html-el:text property="zmd_sn_like" maxlength="30" />
            &nbsp;&nbsp;
                                匹配状态：
            <html-el:select property="zmd_fp">
            	<html-el:option value="1">-否-</html-el:option>
            	<html-el:option value="2">-是-</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
  <html-el:form action="zmd/KonkaXxZmdUsers">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="zmd_ids" styleId="zmd_ids" />
      
     <!--  <input type="button" class="but8" value="批量匹配" id="plFp" /> -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
       <!-- <td width="5%" align="center" nowrap="nowrap"><html-el:checkbox property="check_all" styleId="check_all" /></td> -->
        <td width="5%" nowrap="nowrap" align="center"><font class="blue">序号</font></td>
        <td width="12%" nowrap="nowrap" align="center"><font class="blue">专卖店编号</font></td>
        <td width="12%" nowrap="nowrap" align="center"><font class="blue">专卖店R3编码</font></td>
        <td nowrap="nowrap" align="center"><font class="blue">专卖店R3名称</font></td>
        <td width="12%" nowrap="nowrap" align="center"><font class="blue">管理员用户名</font></td>
        <td width="12%" nowrap="nowrap" align="center"><font class="blue">是否匹配</font></td>
        <td width="8%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
        
         <!-- <td align="center"><html-el:checkbox property="xxx_${cur.r3_id}" value="${cur.r3_id}_${cur.zmd_id}" styleClass="zmd_values" /></td> -->
          <td align="center">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
          <td align="left" nowrap="nowrap">${cur.r3_id}</td>
          <td align="left">${cur.r3_name}</td>
          <td align="left">${cur.map.user_name}</td>
          <td align="center"><c:choose>
          	<c:when test="${af.map.zmd_fp eq 1}">否</c:when>
          	<c:when test="${af.map.zmd_fp eq 2}">是</c:when>
          </c:choose></td>
          <td align="center"><c:if test="${af.map.zmd_fp eq 1}">
          		<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdUsers.do', 'zmdUserFp','zmd_id=${cur.zmd_id}&r3_id=${cur.r3_id}&'+$('#bottomPageForm').serialize())">匹配</span>
          	</c:if>
          	<c:if test="${af.map.zmd_fp eq 2}">
          		<span style="color: gray;">匹配</span>
          	</c:if>
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
        </tr>
      </c:forEach>
    </table>
    </html-el:form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdUsers.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("zmd_sn_like", "${af.map.zmd_sn_like}");
	            pager.addHiddenInputs("zmd_fp", "${af.map.zmd_fp}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#check_all").click(function(){
		var zmd_ids1 = "";
		if(this.checked) {
			$(".zmd_values").attr("checked", "checked");
			$(".zmd_values").each(function (){
				if(this.checked){
					zmd_ids1 = zmd_ids1 + "##" + this.value;
				}
			});
		} else {
			$(".zmd_values").removeAttr("checked");
			zmd_ids1 = "";
		}
		$("#zmd_ids").val(zmd_ids1);
	});

	$(".zmd_values").click(function(){
		var zmd_ids2 = $("#zmd_ids").val();
		
		if(this.checked){
			zmd_ids2 = zmd_ids2 +"##"+ this.value;
		} else {
			zmd_ids2 = zmd_ids2.replace(this.value,"");
		}
		$("#zmd_ids").val(zmd_ids2);
	});

	$("#plFp").click(function(){
		if($("#zmd_ids").val().length = 0){
			alert("请选择要匹配的专卖店！");
			return ;
		}
		$(this.form).submit();
	});
	
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
