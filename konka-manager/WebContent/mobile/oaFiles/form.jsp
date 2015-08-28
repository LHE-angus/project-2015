<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>康佳电器</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<form id="fileSub" action="OaFiles.do" method="post">
  <input type="hidden" id="id" name="id" value="${af.map.id}" />
  <input type="hidden" id="username" name="username" value="${af.map.username}" />
  <input type="hidden" id="userpass" name="userpass" value="${af.map.userpass}" />
   <input type="hidden" id="user_id" name="user_id" value="${af.map.user_id}" />
  <input type="hidden" id="audit_result" name="audit_result" />
  <input type="hidden" id="is_node" name="is_node" value="${af.map.is_node}" />
  <input type="hidden" id="is_agent" name="is_agent" value="${af.map.is_agent}" />
  <input type="hidden" id="agent_user_id" name="agent_user_id" value="${af.map.agent_user_id}" />
  <table width="100%" border="0"  class="rtab2">
    <tr>
      <th colspan="2" width="90%" align="left" class="bno" id="filetitle">${af.map.file_title}</th>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left">申请人：${af.map.submit_user}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left" class="bno">负责人：${af.map.apply_user_name}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left">电话：${af.map.apply_user_tel}</td>
    </tr>
    <tr>
      <td colspan="2" width="90%" align="left" style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">时间：
        <fmt:formatDate value="${af.map.submit_datetime}" pattern="yyyy-MM-dd hh:mm" /></td>
    </tr>
    <tr>
      <td align="left">内容：<br />
        <label for="textarea"></label>
        ${af.map.content}
        <c:if test="${!empty attachmentList}"> 
        	<br />
        	<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
        		<span style="font-size: 12px;color: blue;">${vs.count}、<a style="font-size: 12px;color: blue;" href="OaFiles.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach>
        </c:if></td>
    </tr>
    <c:if test="${af.map.file_type eq 1}">
      <tr>
        <td colspan="2" width="90%" align="left">文件类型：费用申请</td>
      </tr>
      <tr>
        <td colspan="2" width="90%" align="left"><table width="100%" border="0" >
            <tr>
              <td width="60%" align="left">费用类别</td>
              <td width="20%" align="left">数量</td>
              <td width="20%" align="left">金额</td>
            </tr>
            <c:forEach items="${filesPropertyList}" var="cur" varStatus="vs">
              <tr>
                <td align="left">${cur.map.c_name}</td>
                <td align="left">${cur.amount}</td>
                <td align="left">${cur.cost}</td>
              </tr>
            </c:forEach>
          </table></td>
      </tr>
      <tr>
        <td colspan="2" width="90%" align="left"  style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #d5c7c9;">费用总额：${af.map.column_6}</td>
      </tr>
    </c:if>
     <c:if test="${not empty filesAuditNodeList}">
     <tr><td colspan="4">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
              <tr>
                <td width="70" align="center" nowrap="nowrap">审批状况</td>
                <td width="150" align="center" nowrap="nowrap" >审批开始时间</td>
                <td width="150" align="center" nowrap="nowrap">审批结束时间</td>
                <td align="center" nowrap="nowrap">审批意见</td>
                <td width="150" align="center" nowrap="nowrap" >审批人/部门</td>
              </tr>
              <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
                <c:set var="i" value="${vs.count}" />
                <c:if test="${cur.audit_result eq 2}">
                  <c:set var="audit_result" value="同意" />
                </c:if>
                <c:if test="${cur.audit_result eq 1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>驳回</span>" />
                </c:if>
                <c:if test="${cur.audit_result eq -1}">
                  <c:set var="audit_result" value="<span style='color:#f00;'>转发</span>" />
                </c:if>
                <c:if var="is_vs_last" test="${vs.last}">
                	<c:set var="begin_time" value="${af.map.submit_datetime}" />
                </c:if>
                <c:if test="${!is_vs_last}">
                	<c:set var="begin_time" value="${filesAuditNodeList[i].audit_datetime}" />
                </c:if>
                <tr>
                  <td align="center" width="150" >${audit_result}</td>
                  <td align="center" width="150" ><fmt:formatDate value="${begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                  <td align="center" width="150"><fmt:formatDate value="${cur.audit_datetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                  <c:set var="begin_time" value="${cur.audit_datetime}" />
                  </td>
                  <td align="center">${fn:escapeXml(cur.audit_comment)}</td>
                  <td align="center" width="150" >${cur.audit_user}</td>
                </tr>
              </c:forEach>
            </table>
             </td></tr>
          </c:if>
   
    
    <c:if test="${af.map.file_type ne 1}">
      <tr>
        <td colspan="2" width="90%" align="left">文件类型：文件提交</td>
      </tr>
    </c:if>
    <c:if test="${af.map.is_node ne 1}">
      <tr>
        <td colspan="2" width="90%" align="left">是否转发：
          <input type="radio" name="is_forward" value="0" id="is_forward_0" />
          <label for="is_forward_0"> 否 </label>
          <input type="radio" name="is_forward" value="1" id="is_forward_1" />
          <label for="is_forward_1"> 是 </label></td>
      </tr>
      <tr id="is_forward_tr" style="display:none;">
        <td>审批人：
          <input type="hidden" name="audit_user_id" id="audit_user_id" />
          <input type="text" name="audit_user_name" id="audit_user_name" style="width: 70%;" />
          <br/>
          <span style="color: red;">*输入关键字来搜索审批人</span></td>
      </tr>
    </c:if>
    <tr>
      <td align="left">审批意见：<br />
        <textarea name="audit_comment" id="audit_comment" cols="45" rows="5" class="qs1"></textarea></td>
    </tr>
  </table>
  <div class="xt_fenye1"><a id="tongyi"><img src="${ctx}/mobile/images/tongyi.jpg" width="93" height="38" /></a> <a id="bohui"><img src="${ctx}/mobile/images/bohui.jpg" width="93" height="38" /></a></div>
</form>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.form.js"></script>
<script type="text/javascript">
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	$(document).ready(function(){
		var is_node = $("#is_node").val();
		if(is_node == 0){
			$("#is_forward_0").attr("checked", true);
			
			$("#is_forward_0").click(function(){setForwardDivHide();});
			$("#is_forward_1").click(function(){setForwardDivShow();});
	
			var username = '${af.map.username}';
			var userpass = '${af.map.userpass}';
	
			
			// 定义AJAX获取的人员信息
			var pd_module_data;
		
			if(username != null && userpass != null && username != "null" && userpass != "null" && username != "" && userpass != ""){
				// 初始化人员
				pd_module_init(username, userpass);
				
			}
	
			// 搜索--判断和区分IE和非IE浏览器分别进行注册监听
			if($.browser.msie) { 
				document.getElementById("audit_user_name").attachEvent("onpropertychange", function(o){   
		            if(o.propertyName != "value") return;  //不是value改变不执行下面的操作   
		            initAutoDiv();
		        }); 
			} else { // 非IE处理
				// 型号
		    	document.getElementById("audit_user_name").addEventListener("input", initAutoDiv, false);
			}
		}
		
		// 输入关键字搜索
		function initAutoDiv(){
			// 隐藏下拉框
			if($("#auto_prompt_div"))
		        $("#auto_prompt_div").remove();
	        // 输入框有改变将型号置为空
			if($.trim($("#audit_user_name").val()).length > 0){
				
				var val = $.trim($("#audit_user_name").val());
				var count = 0;
				
				for(var i = 0; i < pd_module_data.length; i++){
					var id = pd_module_data[i].id;
					var name = pd_module_data[i].user_name;
					if(name.toLowerCase().indexOf(val.toLowerCase()) != -1)
						count++;
				}
				var top = $("#audit_user_name").offset().top;
		        var left = $("#audit_user_name").offset().left;
		        var width = $("#audit_user_name").width();
		        var auto_prompt_div = $("<div />").width(width).css("position", "absolute").css("backgroundColor", "white").css("left", left).css("top", top + $("#audit_user_name").height() + 6).css("border", "1px solid #1C86EE").attr("id", "auto_prompt_div");
		        var table = $("<table width='100%' id=\"div_table\" />").attr("cellpadding", "0").attr("cellspacing", "0");
				// 满足条件控制在30个内，如果超过30个则不显示
				
				if(count != 0){
					for(var i = 0; i < pd_module_data.length; i++){
						var id = pd_module_data[i].id;
						var name = pd_module_data[i].user_name;
						if(name.toLowerCase().indexOf(val.toLowerCase()) != -1){
		                    var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
		                        $(this).css("backgroundColor", "white").css("color", "black");
		                    }).mouseover(function(){
		                        $(this).css("backgroundColor", "#1C86EE").css("color", "white");
		                    }).click(function(){
		                        $("#audit_user_name").val($(this).find("td").eq(0).html());
		                        $("#audit_user_id").val($(this).find("td").eq(0).attr("id"));
		                	    $("#auto_prompt_div").remove();
		                    });
		                    var td = $("<td class=\"td_class\" />").html(name).css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left").attr("id", id);
		                    tr.append(td);
		                    table.append(tr);
					    }
					}
				} else {
	                var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
	                    $(this).css("backgroundColor", "white").css("color", "black");
	                }).mouseover(function(){
	                    $(this).css("backgroundColor", "#1C86EE").css("color", "white");
	                });
	                var td = $("<td />").html("提示，您搜索的用户“" + val + "”，共检索到 " + count + " 条数据，请精确搜索条件！").css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left");
	                tr.append(td);
	                table.append(tr);
				}

				auto_prompt_div.append(table);
		        $(document.body).append(auto_prompt_div);
			}
		}
		
		function pd_module_init(username, userpass){
			$.ajax({
				type: "POST",
				url: "${ctx}/OaFiles.do?method=GetList04&from_html=1",
				data: { "username":encodeURI(username), "userpass":encodeURI(userpass), "timestamp":new Date().getTime() },
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
				success: function(result) {
				  // 关闭-正在加载数据层
			  	 // load_data.close();
			     pd_module_data = result;
				}
			});
		}
		
	 $("#tongyi").click(function(){
		 var is_forward_value =$('input:radio[name="is_forward"]:checked').val();
		 
		 if(Validator.Validate($("#fileSub")[0], 1)){
			if(is_forward_value == 0){
				$("#audit_user_id").val("");
			}
			 
			$("#audit_result").val("2");
			if($("#audit_comment").val().length == 0){
				$("#audit_comment").val("同意");
			}
			
			var username = $("#username").val();
			var userpass = $("#userpass").val();

			pass(username,userpass,$("#is_node").val());
		 }
	 });
	 
	 $("#bohui").click(function(){
		 if(Validator.Validate($("#fileSub")[0], 1)){
			$("#audit_result").val("1");
			$("#audit_user_id").val("");
			if($("#audit_comment").val().length == 0){
				$("#audit_comment").val("驳回");
			}
			
			var username = $("#username").val();
			var userpass = $("#userpass").val();
				pass(username,userpass,$("#is_node").val());
		 }
	 });
});

	function setForwardDivShow(){
		$("#is_forward_tr").show();
		$("#audit_user_id").attr("dataType", "Require").attr("msg", "请选择审批人");
		$("#audit_user_name").attr("dataType", "Require").attr("msg", "请选择审批人");
	}
	
	function setForwardDivHide(){
		$("#is_forward_tr").hide();
		$("#audit_user_id").removeAttr("dataType");
		$("#audit_user_name").removeAttr("dataType");
	}
			
	function back(username,userpass) {
		var url_value =""; 

		if(is_node == 1){//存在流程定义
			url_value = "OaFiles.do?method=save1";
		} else {//无流程定义
			url_value = "OaFiles.do?method=save";
		}
		
		$.ajax( {
			type : "POST",
			url : url_value,
			cache : false,
			data : $("#fileSub").serialize(),
			error : function(msg) {
				alert(msg);
				location.href = "${ctx}/OaFiles.do?username="
						+ username + "&userpass="
						+ userpass;
			},
			success : function(msg) {
				if (msg == "success") {
					location.href = "${ctx}/OaFiles.do?username="
							+ username + "&userpass="
							+ userpass;
				} else {
					alert(msg);
				}
			}
		});
		return false;
	}

	function pass(username,userpass,is_node) {
		var url_value =""; 

		if(is_node == 1){//存在流程定义
			url_value = "OaFiles.do?method=save1";
		} else {//无流程定义
			url_value = "OaFiles.do?method=save";
		}
		
		$.ajax( {
					type : "POST",
					url : url_value,
					cache : false,
					data : $("#fileSub").serialize(),
					error : function(msg) {
						alert(msg);
						location.href = "${ctx}/OaFiles.do?username="
								+ username + "&userpass="
								+ userpass;
					},
					success : function(msg) {
						if (msg == "success") {
							location.href = "${ctx}/OaFiles.do?username="
									+ username + "&userpass="
									+ userpass;
						} else {
							alert(msg);
						}
					}
				});
		return false;
	}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>