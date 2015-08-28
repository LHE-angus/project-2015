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
<form id="fileSub">
</form>

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
	$(document).ready(
			function() {
				$.ajax( {//ajax 取数据：
							type : "POST",
							url : "OaFile.do",
							data : "method=detail&username="
									+ getQueryString("username") + "&userpass="
									+ getQueryString("userpass") + "&id="
									+ getQueryString("id") + "&is_agent="
									+ getQueryString("is_agent")
									+ "&agent_user_id="
									+ getQueryString("agent_user_id")
									+ "&file_type="
									+ getQueryString("file_type"),
							dataType : "text",
							error : function(request, settings) {
								alert();
							},
							success : function(Datas) {
								$("#fileSub").html(Datas);
							}
						});
			});
	function back(username) {
		$("#audit_result").val("1");
		$.ajax( {
			type : "POST",
			url : "OaFile.do?method=save",
			cache : false,
			data : $("#fileSub").serialize(),
			error : function(msg) {
				alert(msg);
				location.href = "${ctx}/OaFile.do?username="
						+ '${af.map.user_name}' + "&userpass="
						+ getQueryString("userpass");
			},
			success : function(msg) {
				if (msg == "success") {
					location.href = "${ctx}/OaFile.do?username="
							+ '${af.map.user_name}' + "&userpass="
							+ getQueryString("userpass");
				} else {
					alert(msg);
				}
			}
		});
		return false;
	}

	function pass(username) {
		$("#audit_result").val("2");
		$.ajax( {
					type : "POST",
					url : "OaFile.do?method=save",
					cache : false,
					data : $("#fileSub").serialize(),
					error : function(msg) {
						alert(msg);
						location.href = "${ctx}/OaFile.do?username="
								+ '${af.map.user_name}' + "&userpass="
								+ getQueryString("userpass");
					},
					success : function(msg) {
						if (msg == "success") {
							location.href = "${ctx}/OaFile.do?username="
									+ '${af.map.user_name}' + "&userpass="
									+ getQueryString("userpass");
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