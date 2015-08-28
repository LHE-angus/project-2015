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
<div class="oarcont">
		<div class="oartop">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
             <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg"   style="vertical-align: middle;" /></td>
			 <td>当前位置：${naviString}</td>
		</tr>
</table>
</div>
         <div class="rtabcont1">
              <html-el:form action="/admin/KonkaBranchAssign">
                <html-el:hidden property="method" value="list" />
                <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
                <html-el:hidden property="tree_param" value="${tree_param}" />
                <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
                  <tr>
                  <td width="15%" nowrap="nowrap"></td>
                    <td width="80%" nowrap="nowrap"> 网点名称：
                      <html-el:text property="customer_name_like" maxlength="40" size="50" />
                      </td>
                      <td width="100">&nbsp;&nbsp;&nbsp;<input class="but1"
							type="submit" name="Submit" value="搜索" />
						</td>
                  </tr>
                </table>
              </html-el:form>
              </div>
		<div class="rtabcont1">
			<%@ include file="/commons/pages/messages.jsp"%>
			
		</div>
		<div class="rtabcont1">
              <html-el:form action="/admin/PeShop" method="post">
                <html-el:hidden property="method" value="shopDispach" />
                <html-el:hidden property="type"/>
                <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
                <html-el:hidden property="tree_param" value="${tree_param}" /> 
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td height="30" style="padding-left:10px;">
	                  	<input name="Submit2" type="button" class="but7" value="网点分类" onclick="confirmDispatch(this.form,1);" />
	                  	<input name="button" type="button" class="but7" value="业务员分配" onclick="confirmDispatch(this.form,2);" />
	                  	</td>
	                </tr>
	              </table>
	              <table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="rtable2">
	                <tr class="tabtt1">
	                  <td nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
	                  <td nowrap="nowrap" align="center">网点名称</td>
	                  <td nowrap="nowrap" align="center">网点diqu</td>
	                  <td nowrap="nowrap" align="center">sushu</td>
	    
	   
	                  <td nowrap="nowrap" align="center">操作</td>
	                </tr>
	                <tbody>
	                <c:forEach var="cur" items="${entityList}" varStatus="vs">
	                  <tr>
	                  	<td height="28" align="center"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
	                    <td align="left">${fn:escapeXml(cur.map.customer_name)}</td>
	                    <td align="left"><font class="blue12px">${fn:escapeXml(cur.map.AREA_NAME)}</font></td>
	                    <td align="center"><font class="blue12px">${fn:escapeXml(cur.map.HANDLE)}</font></td>

	                    <td align="center" nowrap="nowrap"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeShop.do', 'shopDispach','id=${cur.id}&type=1&'+$('#bottomPageForm').serialize())">网点分类</span>
	                    |
	                    <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeShop.do', 'shopDispach','id=${cur.id}&type=2&'+$('#bottomPageForm').serialize())">业务员</span>
	                   </td>
	                  </tr>
	                </c:forEach>
	                </tbody>
	              </table>
              </html-el:form>
              <form id="bottomPageForm" name="bottomPageForm" method="post"
				action="KonkaBranchAssign.do">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td height="40"><div   style="text-align: right; padding-right: 5px;">
					<script type="text/javascript"   src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
			            document.write(pager.toString());
			            </script>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="rtabcont3"></div>
	<div class="clear"></div>
      <!-- ****** Right Frame End ****** --></td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"  src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript"  src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	$('#ck').click(function(){
		if(confirm("确定要修改网点状态吗？") == true) {
			$(this).attr("value", "正在提交...").attr("disabled", "true");
			this.form.submit();
		} else {
			return false;
		}
	});
});

function openShopState(id){
	//alert(id);
	$("#id").val(id);
	//alert($("#id").val());
	$("#pl_tbody").empty();
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		var i = "${cur.id}";
		if (id == i) {
			$('#state').val("${cur.state}");
			$("#pl_tbody").append('<tr><td height="40" width="20%" align="center"><font class="blue12px">${cur.shop_id}</font></td><td height="40" width="50%" align="center"><font class="blue12px">${cur.map.shop_name}</font></td><td height="40" width="30%" align="center"><font class="blue12px"><c:if test="${cur.state eq 1}">自己的网点</c:if><c:if test="${cur.state eq 2}">计划淘汰网点</c:if><c:if test="${cur.state eq -1}">计划开拓网点</c:if><c:if test="${cur.state eq -2}">已淘汰的网点</c:if></font></td></tr>');
		}
	</c:forEach>
	
	$("#plCkDiv").dialog({
		width: 400,
		//height: 300,
		resizable: false,
		//position:'right',
		modal : true
	}).dialog("open");
};

function confirmDispatch(form,type){
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
		alert("请至少选择一个网点！");
	} else {
		if(type == 1) {
			form.type.value = "1";
		}else if(type == 2) {
			form.type.value = "2";
		}
			form.submit();
		}
	}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
