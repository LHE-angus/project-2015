<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="MSThemeCompatible" content="no" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<title>选择人员</title>
	<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
</head>
<body style="background-color:#fff;">                         
	<div class="oarcont" id="body_oarcont">
  		<div class="rtabcont1">
    		<html-el:form action="/oa/Dialog">
		    	<html-el:hidden property="queryString" styleId="queryString" />
		      	<html-el:hidden property="method" styleId="method" value="save" />
		      	<html-el:hidden property="mod_id" styleId="mod_id" />
		      	<html-el:hidden property="id" styleId="id" />
      <c:if test="${empty konkadeptList}">
        <html-el:hidden property="role_id" styleId="role_id" value="" />
      </c:if>
      <table style="width:444; height:430 ;margin:0; padding:0;" border="0" cellpadding="0">
        <tr>
          <td style="width:200px; height:313px; position:absolute; top:15px; ">
            <html-el:select property="role_id" styleId="role_id"  multiple="true" style="width:200px; height:313px; position:absolute; top:4px; left: 11px;" onchange="getQueryUserNames($('#key_word').val(),$('#role_id').val());">
              <c:if test="${not empty konkadeptList}">
                <c:forEach var="cur" items="${konkadeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                </c:forEach>
              </c:if>
            </html-el:select>
            &nbsp;</td>
          <td width="385" style="position:absolute; top:15px; left:225px;"><table style="width:363;height:364 ;border-spacing : 2px 0px" border="0">
              <tr>
                <td colspan="3" width="361" style="align:right">快速搜索:&nbsp;&nbsp;&nbsp;
                  <html-el:text property="key_word" styleId="key_word" size="50" maxlength="50" style="width:180px;align:left" />
                  &nbsp;&nbsp;&nbsp;
                  <a href="#" class="easyui-linkbutton" id="btn_search" iconCls="icon-search">搜   索</a>
                </td>
              </tr>
              <tr>
                <td>
                 <table style="width:365;height:360 ; border-style:double ; bordercolor:black; border-width :4px ;" border="1" cellpadding="0" >
                    <tr>
                      <td style="width:145; height:70;border-style:double ; bordercolor:#000000; border-width :4px ;"><div align="center">待选列表</div></td>
                      <td style="width:91;  height:70 ;border-style:double ; bordercolor:#000000; border-width :4px ;">&nbsp;</td>
                      <td style="width:145; height:70; border-style:double ; bordercolor:#000000; border-width :4px ;"><div align="center">已选列表</div></td>
                    </tr>
                    <tr>
                      <td style="height:220;border-style:double ; bordercolor:#000000; border-width :4px ;">&nbsp;
                        <html-el:select property="select1" multiple="true" style="width:120px;height:225px;" styleId="select1" onchange="getQueryUserName($('#select1').find('option:selected').val());">
                          <c:if test="${not empty entityList}">
                            <html-el:optionsCollection label="map.department" value="id"  name="entityList" />
                          </c:if>
                        </html-el:select>&nbsp;</td>
                      <td style="border-style:double ; bordercolor:#000000; border-width :4px ;">
                        <div>&nbsp;<html-el:button property="" value="添加》》" styleId="btn_add" />&nbsp;</div>
                        <div>&nbsp;<html-el:button property="" value="《《删除" styleId="btn_del"/>&nbsp;</div>
                      </td>
                      <td style="border-style:double ; bordercolor:#000000; border-width :4px ;">&nbsp;
                        <html-el:select property="select2" multiple="true" style="width:120px;height:225px;" styleId="select2" onchange="getQueryUserName($('#select2').find('option:selected').val());">
                          <c:if test="${not empty selectList}">
                            <html-el:optionsCollection label="map.department" value="id"  name="selectList" />
                          </c:if>
                        </html-el:select></td>
                    </tr>
                    <tr>
                      <td height="28" colspan="3"><div id="information"></div></td>
                    </tr>
                  </table>
                 </td>
              </tr>
              <tr>
                <td colspan="3" style="text-align:center;padding:10px; 0 0;">
                  	<a href="#" class="easyui-linkbutton" id="btn_back" onclick="window.close();" iconCls="icon-undo">取   消</a>
                  	&nbsp;&nbsp;
                	<a href="#" class="easyui-linkbutton" id="btn_submit" iconCls="icon-save">确   定</a>
                  	</td>
              </tr>
            </table></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#key_word").attr("autocomplete", "off");
	$("#key_word").attr("disableautocomplete", "true");
	
	$("#btn_submit").click(function(){
		<c:if test="${af.map.selectype eq 'signal'}">if ($("#select2 option").length > 1) {
			$.messager.alert('温馨提示','只能选择一个用户!','error');
		} else {
		</c:if>
		var selectedUsersId = "";
		var selectedUsersName = "";
		for(var i=0;i<f.select2.length;i++){
			selectedUsersId+=f.select2.options[i].value+",";
			selectedUsersName+=f.select2.options[i].text+",";
		}
		window.returnValue = {
				user_link_ids : selectedUsersId,
				user_link_names : selectedUsersName
		}
		window.close();
		<c:if test="${af.map.selectype eq 'signal'}">}</c:if>
	});
	
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $("#btn_search").click(function(){
    	getQueryUserNames($('#key_word').val(),$('#role_id').val());
    });
    
    $('#key_word').keypress(function (e) {
        var k = e.keyCode || e.which;
        if (k == 13) {
            return false;
        }
    });
    
    //单个添加
	$("#btn_add").click(function() {
		$("#select1 option:selected").remove().appendTo("#select2").attr("selected", false);
	});
  
	//单个删除
	$('#btn_del').click(function() {
		$('#select2 option:selected').remove().appendTo('#select1').attr("selected", false);
	});
});

function getQueryUserNames(key_word, role_id) {
   	$("#select1").empty();
   	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/oa/NewDiaLog.do",
		data: "method=" + "getQueryUserNames" + "&key_word=" + $("#key_word").val() + "&role_id=" + $("#role_id").val()+ "&selectedUsersID=" + getSelect2Value()+"&num=${num}",
		dataType: "json",
		error: function(request, settings){},
		success: function(data) {
			if (data.length > 1) {
				for(var i = 0; i < data.length - 1; i++) {
					var opt = new Option( data[i].user_name,data[i].user_id); 
					$("#select1").get(0).options.add(opt);
				}
			}
		}
	});
}

function getSelect2Value(){
	var str = "";
	var obj = document.getElementById("select2");
	
    for(var i = 0; i < obj.options.length; i++) {
    	str += obj.options[i].value + ",";
    }
	
	if(str != "") {
		return str.substr(0, str.length - 1);
	} else {
		return "";
	}
}

function getQueryUserName(user_id){
 $.ajax({
	type: "POST",
	cache: false,
	url: "${ctx}/manager/oa/DiaLog.do",
	data: "method=" + "getQueryUserName" + "&user_id=" + user_id,
	//dataType: "json",
	error: function(request, settings){},
	success: function(data) {
		if(data != null){
			$("#information").empty();
			$("#information").show();
			$("#information").append("描述：").append(data);
		}
	}
 });
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
