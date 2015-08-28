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
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>
<body>
<div class="oarcont"  id="body_oarcont">
  <div class="rtabcont2">
      <html-el:form action="/spgl/EcGroup" enctype="multipart/form-data">
        <html-el:hidden property="id" styleId="id" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <html-el:hidden property="method" styleId="method" value="edit" />
        <table width="99%" border="0" align="center" cellpadding="2" cellspacing="0">
          <tr>
            <td width="50%">
              <c:if test="${af.map.operate_type eq 0}">
              <logic-el:match name="popedom" value="+1+"> 
              <a href="EcGroup.do?method=add&mod_id=${af.map.mod_id}&id=${af.map.id}"> 增加<span style="color:#FF0000;">〖${af.map.dept_name}〗</span>的子部门</a> &nbsp;&nbsp;
              </logic-el:match>
               <logic-el:match name="popedom" value="+2+">
              <a href="EcGroup.do?method=add&mod_id=${af.map.mod_id}&id=${af.map.id}&is_edit=0">修改 <span style="color:#FF0000;">〖${af.map.dept_name}〗</span>部门信息</a>
              </logic-el:match> 
              </c:if>
              <c:if test="${af.map.operate_type eq 1}">
               <logic-el:match name="popedom" value="+1+">
              <a href="EcGroup.do?method=add&mod_id=${af.map.mod_id}&par_id_for_zb=-1">增加最大组织</a> &nbsp;&nbsp; 
              </logic-el:match>
              </c:if>
            </td>
            <td width="50%" align="right" style="display: none;">
              <label for="contain_subdepts" style="width:80px;"><html-el:checkbox property="contain_subdepts" styleId="contain_subdepts" onclick="this.form.submit();" /> 查询所有子部门人员</label>
            </td>
          </tr>
        </table>
      </html-el:form>
      <%@ include file="/commons/pages/messages.jsp" %>
      <c:if test="${not empty add_dept}">
        <script> self.parent.frames["leftFrame"].document.location.reload();
        </script>
      </c:if>
      <div style="padding:5px 5px"></div>
      <!-- 部门人员列表 -->
        <table id="tblSort"  width="99%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="2%" nowrap="nowrap">序号</td>
            <td width="6%" nowrap="nowrap">用户名</td>
            <td width="10%" nowrap="nowrap">手机</td>
            <td width="10%" nowrap="nowrap">客户名称</td>
            <td width="15%" nowrap="nowrap">客户编码</td>
            <td nowrap="nowrap">所属组织</td>
            <td width="10%" nowrap="nowrap" >总部/分公司</td>
          </tr>
          <c:forEach items="${entityList}" var="cur" varStatus="vs">
            <tr>
              <td  nowrap="nowrap"  align="center">${af.map.pager.pageSize*(af.map.pager.currentPage-1)+vs.count}</td>
               <td  nowrap="nowrap" align="left"><c:out value="${cur.user_name}" /></td>
               <td  nowrap="nowrap" align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
              <td  nowrap="nowrap" align="left">
              	${cur.map.cust_name}
              </td>
              <td  nowrap="nowrap" align="left">
              	${cur.map.cust_code}
              </td>
               <td  nowrap="nowrap" align="left"> 
              	${cur.map.group_name}
              </td>
              <td  nowrap="nowrap" align="left"> 
               <c:if test="${cur.plat_sys eq 0}">总部</c:if>
               <c:if test="${cur.plat_sys eq 1}">分公司</c:if>
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
        <form id="bottomPageForm1" name="bottomPageForm1" method="post" action="EcGroup.do" target="_self">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm1, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "edit");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("id", "${af.map.id}");
	            document.write(pager.toString());
	          </script></td>
            </tr>
          </table>
        </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<%@ include file="/commons/pages/areamove.jsp"%>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
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

});

$("#dept_name").blur(function() {
	if(this.value.length > 0 && this.value != '${af.map.dept_name}'){
		$("#loading").ajaxStart(function(){$(this).show();});
		$("#loading").ajaxStop (function(){$(this).hide();});
		$("#tip").remove();
		$.ajax({
				type: "POST",
				url: "EcGroup.do",
				data: "method=validateDeptName&dept_name=" + this.value + "&dept_id=${af.map.dept_id}",
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(isExist) {
					if(isExist == 1) {
						$("#btn_submit").attr("disabled",true);
						$("#s_after").after('<span id="tip" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该部门名称已存在！<\/span>');
					} else if(isExist == 0){
						$("#btn_submit").removeAttr("disabled");
						$("#s_after").after('<span id="tip" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该部门名称可用。<\/span>');
					} 
				}
		});
	} else {
		$("#tip").remove();
	}
});

$("#dept_sn").blur(function(){
	if(this.value.length > 0 && this.value != $(this).parent().data("orig")){
		$("#loading1").ajaxStart(function(){$(this).show();});
		$("#loading1").ajaxStop (function(){$(this).hide();});
		$("#tip1").remove();
		$.ajax({
			type:"POST",
			url:"EcGroup.do",
			data: "method=validateDeptSN&dept_sn=" + this.value,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！"); },
			success: function(isExist) {
				if(isExist == 1) {
					$("#btn_submit").attr("disabled", true);
					$("#s_after1").after('<span id="tip1" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该部门编码已存在！<\/span>');
				} else if(isExist == 0){
					$("#btn_submit").removeAttr("disabled");
					$("#s_after1").after('<span id="tip1" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该部门编码可用。<\/span>');
				} 
			}
		});
	} else {
		$("#tip1").remove();
	}
});


$("#btn_submit").click(function() {
	if(Validator.Validate(this.form, 3)){
		this.form.submit();
	}
});


function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}



//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
