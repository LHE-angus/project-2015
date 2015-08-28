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
	<html-el:form action="/paragon/KonkaParagonShowversion">
	<html-el:hidden property="method" styleId="method" value="list" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	 <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">版本代码：</strong>
            	<html-el:text property="version_code" size="20" style="width:90px;" maxlength="20" styleId="version_code" styleClass="webinput" />
          </td>
          <td><strong class="fb">版本名称：</strong>
            	<html-el:text property="version_name_like" size="20" style="width:90px;" maxlength="20" styleId="version_name_like" styleClass="webinput" />
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
	    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaParagonShowversion.do?method=add&mod_id=${af.map.mod_id}&';" />
	    <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
	</div>
	<div class="rtabcont1">
		<form id="listForm" name="listForm" method="post" action="KonkaParagonShowversion.do?method=delete">
			<input type="hidden" name="method" id="method" value="delete" />
			<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
			 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       			 <tr class="tabtt1">
					<td width="25" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
					<td nowrap="nowrap" align="center" width="100">版本代码</td>
					<td nowrap="nowrap" align="center" >版本名称</td>
					<td nowrap="nowrap" align="center" width="300">版本备注</td>
					<td nowrap="nowrap" align="center" width="100">添加人</td>
					<td nowrap="nowrap" align="center" width="100">添加时间</td>
					<td width="100" nowrap="nowrap" align="center">操作</td>
				</tr>
				<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.version_id}" /></td>
					<td align="center" nowrap="nowrap">${cur.version_code}</td>
					<td align="left">${fn:escapeXml(cur.version_name)}</td>
					<td align="left">${cur.version_memo}</td>
					<td align="center" nowrap="nowrap">${cur.map.user_name}</td>
					<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.addtime}" pattern="yyyy-MM-dd" /></td>
					<td align="center">
					<logic-el:match name="popedom" value="+2+">
						<span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaParagonShowversion.do', 'version_id=${cur.version_id}&' + $('#bottomPageForm').serialize())">修改</span>
					</logic-el:match>
					<logic-el:notMatch name="popedom" value="+2+">
						<span style="color:#CCC;">修改</span>
					</logic-el:notMatch>
					|
					<logic-el:match name="popedom" value="+4+">
						<span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaParagonShowversion.do', 'version_id=${cur.version_id}&' + $('#bottomPageForm').serialize())">删除</span>
					</logic-el:match>
					<logic-el:notMatch name="popedom" value="+4+">
						<span style="color:#CCC; margin-left:7px;">删除</span></logic-el:notMatch>
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
				</tr>
				</c:forEach>
			</table>
		</form>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonShowversion.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40">
						<div style="text-align:right; padding-right:5px;">
						<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
						<script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("version_code", "${af.map.version_code}");
							pager.addHiddenInputs("version_name_like", "${fn:escapeXml(af.map.version_name_like)}");
							pager.addHiddenInputs("st_pub_date", "${af.map.st_pub_date}");
							pager.addHiddenInputs("en_pub_date", "${af.map.en_pub_date}");
							document.write(pager.toString());
						</script>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#version_name_like").blur(function() {
		$(this).val(this.value.trim());						   
	});
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