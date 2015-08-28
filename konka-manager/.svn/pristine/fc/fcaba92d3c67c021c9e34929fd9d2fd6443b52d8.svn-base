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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>  
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
		<html-el:form action="/paragon/KonkaParagonShowinfo">
		<html-el:hidden property="method" styleId="method" value="list" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	 <tr>
       	    <td width="15"></td>
 			<td><strong class="fb">门店代码：</strong><html-el:text property="shop_order_like" size="15" maxlength="20" styleId="shop_order_like"  /></td>
 			<td><strong class="fb">门店名称：</strong><html-el:text property="shop_name_like" size="15" maxlength="20" styleId="shop_name_like"  /></td>
           <c:if test="${af.map.map.dept_type eq 1 || af.map.map.dept_type eq 2 }">
          	<td nowrap="nowrap">
	        	<strong class="fb">区域　：</strong>
        		<html-el:select property="area_id" styleId="area_id" >
	                <html-el:option value="">请选择...</html-el:option>
	                <html-el:option value="10">华东</html-el:option>
	                <html-el:option value="20">山东</html-el:option>
	                <html-el:option value="30">东北</html-el:option>
	                <html-el:option value="40">华北</html-el:option>
	                <html-el:option value="50">华南</html-el:option>
	                <html-el:option value="60">西南</html-el:option>
	                <html-el:option value="70">华中</html-el:option>
	                <html-el:option value="80">西北</html-el:option>
              </html-el:select>
			</td>
			</c:if>
		<c:if test="${af.map.map.dept_type ne 1 && af.map.map.dept_type ne 2 }">
          	<td nowrap="nowrap">
	        	<strong class="fb">区域　：</strong>
        		<html-el:select property="area_id" styleId="area_id" value="${af.map.area_id_like}" disabled="true">
	                <html-el:option value="">请选择...</html-el:option>
	                <html-el:option value="10">华东</html-el:option>
	                <html-el:option value="20">山东</html-el:option>
	                <html-el:option value="30">东北</html-el:option>
	                <html-el:option value="40">华北</html-el:option>
	                <html-el:option value="50">华南</html-el:option>
	                <html-el:option value="60">西南</html-el:option>
	                <html-el:option value="70">华中</html-el:option>
	                <html-el:option value="80">西北</html-el:option>
              </html-el:select>
			</td>
			</c:if>
	        </tr>
	        <tr>
	        <td width="15"></td>
	     <c:if test="${af.map.map.dept_type eq 1 || af.map.map.dept_type eq 2}">
	        <td><strong class="fb">分公司　：</strong>
	        	<html-el:select property="part_company_id" styleId="part_company_id">
		        	<html-el:option value="">请选择...</html-el:option>
		        	<html-el:optionsCollection name="deptInfoList" label="dept_name" value="dept_id" />
	        	</html-el:select></td>
         </c:if>
          <c:if test="${af.map.map.dept_type ne 1 && af.map.map.dept_type ne 2}">
	        <td><strong class="fb">分公司　：</strong>
	        	<html-el:select property="part_company_id" styleId="part_company_id" value="${af.map.par_company_id_like}" disabled="true">
		        	<html-el:option value="">请选择...</html-el:option>
		        	<html-el:optionsCollection name="deptInfoList" label="dept_name" value="dept_id" />
	        	</html-el:select></td>
         </c:if>
	        <td>
	        	<strong class="fb">经办　　：</strong>
	        	<html-el:text property="channel_name_like" size="15" maxlength="20" styleId="channel_name_like"  />
          	</td>
	        <td><html-el:submit styleClass="but1" value="搜索" /></td>
           </tr>
       </table>
		</html-el:form>
	</div>
	 <div class="rtabcont1">
	<%@ include file="/commons/pages/messages.jsp" %>
	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <logic-el:match name="popedom" value="+1+">  
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaParagonShowinfo.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		    <logic-el:match name="popedom" value="+4+">
		    <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		    </logic-el:match>
		</td>
	 </tr>
	</table>
  </div>
  <div class="rtabcont1">
		<form id="listForm" name="listForm" method="post" action="KonkaParagonShowinfo.do?method=delete">
		<input type="hidden" name="method" id="method" value="delete" />
		<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	       <tr class="tabtt1">
					<td width="25" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
					<td nowrap="nowrap" align="center">门店代码</td>
					<td nowrap="nowrap" align="center">门店名称</td>
					<td nowrap="nowrap" align="center">区域</td>
					<td nowrap="nowrap" align="center">分公司</td>
					<td nowrap="nowrap" align="center">经办</td>
					<td nowrap="nowrap" align="center">客户名</td>
					<td nowrap="nowrap" align="center">客户类别</td>
					<td nowrap="nowrap" align="center">有无直销员</td>
					<td width="90" nowrap="nowrap" align="center">形象图片</td>
					<td width="120" nowrap="nowrap" align="center">操作</td>
				</tr>
				<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.show_shop_id}" /></td>
					<td align="center" nowrap="nowrap"><a href="KonkaParagonShowinfo.do?method=view&mod_id=${af.map.mod_id}&show_shop_id=${cur.show_shop_id}" title="点击查看门店详细信息">${cur.show_shop_code}</a></td>
					<td align="left">${cur.show_shop_name}</td>
					<td align="center" nowrap="nowrap">
						<c:choose>
							<c:when test="${cur.area_id eq 10}">华东</c:when>
							<c:when test="${cur.area_id eq 20}">山东</c:when>
							<c:when test="${cur.area_id eq 30}">东北</c:when>
							<c:when test="${cur.area_id eq 40}">华北</c:when>
							<c:when test="${cur.area_id eq 50}">华南</c:when>
							<c:when test="${cur.area_id eq 60}">西南</c:when>
							<c:when test="${cur.area_id eq 70}">华中</c:when>
							<c:when test="${cur.area_id eq 80}">西北</c:when>
						</c:choose>
					</td>
					<td align="center" nowrap="nowrap">${cur.map.part_name}</td>
					<td align="center" nowrap="nowrap">${cur.channel_name}</td>
					<td align="center">${cur.custom_name}</td>
					<td align="center" nowrap="nowrap">
					<c:choose>
						<c:when test="${cur.custom_type eq 1}">连锁</c:when>
						<c:when test="${cur.custom_type eq 2}">超市</c:when>
						<c:when test="${cur.custom_type eq 3}">县乡客户群</c:when>
						<c:when test="${cur.custom_type eq 4}">城市客户群</c:when>
						<c:otherwise>城市专卖店</c:otherwise>
					</c:choose>
					</td>
					<td align="center" nowrap="nowrap">
					<c:if test="${cur.if_seller eq 0 }">无</c:if>
					<c:if test="${cur.if_seller eq 1 }">有</c:if>
					</td>
					<td align="center">
					<c:if test="${cur.map.picnum eq 0 }"><span style="color:red;">形象图片</span> <a style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaParagonShowimg.do', 'list','mod_id=${af.map.mod_id}&show_shop_code=${cur.show_shop_code}&'+$('#bottomPageForm').serialize())">添加</a></c:if>
					<c:if test="${cur.map.picnum ne 0 }"><span style="cursor:pointer;color:blue;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonShowimg.do', 'list','mod_id=${af.map.mod_id}&show_shop_code=${cur.show_shop_code}&'+$('#bottomPageForm').serialize())">形象图片</span></c:if>
					</td>
					<td align="center">
					<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonShowinfo.do', 'view','mod_id=${af.map.mod_id}&show_shop_id=${cur.show_shop_id}&'+$('#bottomPageForm').serialize())">查看</span>
					|
					<logic-el:match name="popedom" value="+2+">
						<span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaParagonShowinfo.do', 'mod_id=${af.map.mod_id}&show_shop_id=${cur.show_shop_id}&' + $('#bottomPageForm').serialize())">修改</span>
					</logic-el:match>
					<logic-el:notMatch name="popedom" value="+2+">
						<span style="color:#CCC;">修改</span>
					</logic-el:notMatch>
					|
					<logic-el:match name="popedom" value="+4+">
						<span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaParagonShowinfo.do', 'mod_id=${af.map.mod_id}&show_shop_id=${cur.show_shop_id}&' + $('#bottomPageForm').serialize())">删除</span>
					</logic-el:match>
					<logic-el:notMatch name="popedom" value="+4+">
						<span style="color:#CCC;">删除</span></logic-el:notMatch>
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
				</tr>
				</c:forEach>
			</table>
		</form>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonShowinfo.do">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40" align="center">
						<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
						<script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
							pager.addHiddenInputs("shop_order_like", "${af.map.shop_order_like}");
							pager.addHiddenInputs("channel_name_like", "${af.map.channel_name_like}");
							pager.addHiddenInputs("part_company_id", "${af.map.part_company_id}");
							pager.addHiddenInputs("area_id", "${af.map.area_id}");
							document.write(pager.toString());
						</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	/*
	if($("#part_company_id").val()!=""){
		 $("#part_company_id").change();
	}
	 $("#part_company_id").change( function() {
			var dept_id = $("#part_company_id").val();
			$("#channel_id").empty();

			if(""==dept_id){
		   		var opt1 = new Option( "请选择...",""); 
				$("#channel_id").get(0).options.add(opt1);
			   	}
		   	if(dept_id!=""){
			   	$.ajax({
					type: "POST",
					cache: false,
					url: "${ctx}/manager/admin/CsAjax.do",
					data: "method=getChannelId&part_company_id=" + $("#part_company_id").val(),
					dataType: "json",
					error: function(request, settings){},
					success: function(data) {
						if (data.length >= 1) {
							var opt1 = new Option( "请选择...",""); 
							$("#channel_id").get(0).options.add(opt1);
							
							for(var i = 0; i < data.length - 1; i++) {
								var opt = new Option( data[i].name,data[i].id); 
								$("#channel_id").get(0).options.add(opt);
							}
							
							<c:if test="${not empty af.map.channel_id }">$("#channel_id").val("${af.map.channel_id}");$("#channel_id").change();</c:if>
							<c:if test="${empty af.map.channel_id}"></c:if>
						}
					}
				});
		   	}
		});
	*/
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>