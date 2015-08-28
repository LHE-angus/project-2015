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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaPlanRatio">
      <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save_jb" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="this_year" value="${af.map.this_year}" />
      <html-el:hidden property="year" value="${af.map.year}" />
      <html-el:hidden property="fgs_sn_search" value="${af.map.fgs_sn_search}" />
      <html-el:hidden property="dept_type" value="${af.map.dept_type}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <th height="28" colspan="2">您正在${empty af.map.id ? '创建' : '编辑'}${af.map.year}年度任务系数信息</th>
        </tr>
        <tr class="oartop">
          <td colspan="2">任务系数信息</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">年度：</td>
          <td><html-el:text property="y" styleId="y" size="10" maxlength="4" readonly="true" /></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">分公司：</td>
          <td><html-el:select property="fgs_sn" styleId="fgs_sn" onchange="changeFgsSn();">
          	<html-el:option value="">请选择分公司</html-el:option>
          	<c:forEach var="cur" items="${deptList}">
                 <html-el:option value="${cur.dept_sn}">${fn:escapeXml(cur.dept_name)}</html-el:option>
            </c:forEach>
          </html-el:select>
          <c:if test="${empty af.map.id and af.map.dept_type eq 3}">
          		<html-el:hidden property="fgs_sn" value="${af.map.fgs_sn}" />
          </c:if>
          </td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">一级部门：</td>
          <td><html-el:select property="dept_sn" styleId="dept_sn" onchange="changeCurrentSn(this.value, $('#dept_sn_2'), '');">
          	<html-el:option value="">请选择部门</html-el:option>
          </html-el:select></td>
        </tr>
        <tr style="display:none;">
          <td height="28" width="15%" nowrap="nowrap" class="title_item">二级部门：</td>
          <td><html-el:select property="dept_sn_2" styleId="dept_sn_2" onchange="changeCurrentSn(this.value, $('#dept_sn_3'), '');">
          	<html-el:option value="">请选择部门</html-el:option>
          </html-el:select></td>
        </tr>
        <tr style="display:none;">
          <td height="28" width="15%" nowrap="nowrap" class="title_item">三级部门：</td>
          <td><html-el:select property="dept_sn_3" styleId="dept_sn_3" onchange="changeCurrentSn(this.value, $('#dept_sn_4'), '');">
          	<html-el:option value="">请选择部门</html-el:option>
          </html-el:select></td>
        </tr>
        <tr style="display:none;">
          <td height="28" width="15%" nowrap="nowrap" class="title_item">四级部门：</td>
          <td><html-el:select property="dept_sn_4" styleId="dept_sn_4" onchange="">
          	<html-el:option value="">请选择部门</html-el:option>
          </html-el:select></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">任务系数：</td>
          <td><html-el:text property="ratio" styleId="ratio" size="20" maxlength="10" onfocus="javascript:setOnlyNum(this);" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" value="提交" id="send"/>
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label>
          </td>
        </tr>
      </table>
    </html-el:form> 
  </div> 

</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 如果ratio是0会显示成科学计数法0E-8，解决此问题
	if($("#ratio" ).val()=="0E-8"){
		$("#ratio" ).val(0);
	}
	$("#ratio" ).attr("dataType" , "Require").attr("msg" , "请填写任务系数！");
	$("#fgs_sn" ).attr("dataType" , "Require").attr("msg" , "请选择分公司！");
	//<c:if test="${af.map.dept_type eq 3}">
		//$("#dept_sn" ).attr("dataType" , "Require").attr("msg" , "请选择经办！");
	//</c:if>
	
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});

	changeFgsSn();
	changeCurrentSn("${af.map.map.dept_sn}", $("#dept_sn_2"),"${af.map.map.dept_sn_2}");
	changeCurrentSn("${af.map.map.dept_sn_2}", $("#dept_sn_3"),"${af.map.map.dept_sn_3}");
	changeCurrentSn("${af.map.map.dept_sn_3}", $("#dept_sn_4"),"${af.map.map.dept_sn_4}");
	
	<c:if test="${not empty af.map.id}">
		$("#dept_sn").parent().parent().show();
		$("#dept_sn_2").parent().parent().show();
		$("#dept_sn_3").parent().parent().show();
		$("#dept_sn_4").parent().parent().show();
		$("#fgs_sn").attr("disabled", "true");
		$("#dept_sn").attr("disabled", "true");
		$("#dept_sn_2").attr("disabled", "true");
		$("#dept_sn_3").attr("disabled", "true");
		$("#dept_sn_4").attr("disabled", "true");
	</c:if>

	<c:if test="${af.map.dept_type eq 3}">
		$("#fgs_sn").attr("disabled", "true");
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

function changeFgsSn(){
	// 一级部门置空
	$("#dept_sn").val("");
	$("#dept_sn").change();//触发onchange事件
	var fgs_sn = $("#fgs_sn").val();
	if(fgs_sn != ""){
		$.ajax({
			type: "POST",
			url: "${ctx}/manager/admin/KonkaPlanRatio.do",
			data: "method=getKonkaDeptForFgsSn&fgs_sn=" + fgs_sn,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！");},
			success: function(ret) {
				if(ret){
					$("#dept_sn").empty();
					var html = "<option value=''>请选择经办</option>";
					for(var i=0; i<ret.list.length; i++){			
						if(ret.list[i].dept_sn == "${af.map.map.dept_sn}"){
							html += "<option value='" + ret.list[i].dept_sn + "' selected='selected'>" + ret.list[i].dept_name +"</option>";
						}else {
							html += "<option value='" + ret.list[i].dept_sn + "'>" + ret.list[i].dept_name +"</option>";
						}
						
					}
					$("#dept_sn").html(html);
				}
				
			}
		});
	}else {
		$("#dept_sn").empty();
		var html = "<option value=''>请选择经办</option>";
		$("#dept_sn").html(html);
		
	}
	
}

//修改上级部门获取下级部门列表
function changeCurrentSn(upper_sn, next_dept_obj, defaultValue){
	// 下级部门先置空
	next_dept_obj.val("");
	next_dept_obj.change();//触发onchange事件
	next_dept_obj.parent().parent().hide();
	
	if(upper_sn != ""){
		$.ajax({
			type: "POST",
			url: "${ctx}/manager/admin/KonkaPlanRatio.do",
			data: "method=getKonkaDeptForUpperSn&upper_sn=" + upper_sn,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！");},
			success: function(ret) {
				if(ret){
					next_dept_obj.empty();
					var html = "<option value=''>请选择部门</option>";
					for(var i=0; i<ret.list.length; i++){			
						if(ret.list[i].dept_sn == defaultValue){
							html += "<option value='" + ret.list[i].dept_sn + "' selected='selected'>" + ret.list[i].dept_name +"</option>";
						}else {
							html += "<option value='" + ret.list[i].dept_sn + "'>" + ret.list[i].dept_name +"</option>";
						}
						
					}
					next_dept_obj.html(html);
					if(ret.list.length>0){
						next_dept_obj.parent().parent().show();
					}else{
						next_dept_obj.parent().parent().hide();
					}
				}
				
			}
		});
	}else {
		next_dept_obj.empty();
		var html = "<option value=''>请选择部门</option>";
		next_dept_obj.html(html);
		
	}
	
}

function changeDeptSn(){
	var dept_sn = $("#dept_sn").val();
	if(dept_sn != ""){
		$.ajax({
			type: "POST",
			url: "${ctx}/manager/admin/KonkaPlanRatio.do",
			data: "method=CheckKonkaDeptForDeptSn&dept_sn=" + dept_sn,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！");},
			success: function(ret) {
				if(ret==0){
					$("#send").removAattr("disabled");
				}else {
					alert("该部门的任务系数已存在，请重新选择部门！")
					$("#send").attr("disabled", "true");
				}
				
			}
		});
	}else {
		$("#send").removAattr("disabled");
	}
	
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>