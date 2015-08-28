﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="MSThemeCompatible" content="no" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<title>申请详情</title>
	<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
	<style type="text/css">
		.rtable1 td {
			padding:0px 0px;
		}
	</style>
</head>
<body >
	<div class="oarcont">
		<div style="padding:5px;height: auto">
			<form id='fm' method="post">
			<table width="550px" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				<tr style="height: 30px; line-height: 15px;">
		        	<td align="right" width="100px">
		          		<strong class="fb"><span style="color:red;">* </span><span id='con_text'>申请原因：</span></strong> 
		        	</td>
		          	<td>
						<textarea rows="8" cols="50" id="content" style="resize:none;" ></textarea>
					</td>
				</tr>
				<tr id="ope_name" style="display: none;height: 30px; line-height: 15px;">
					<td align="right" width="100px"><strong class="fb">申请人：</strong></td>
					<td><span id='real_name'></span></td>
				</tr>
				<tr><td>&nbsp;</td><td><span style="color:red;">注：申请原因不得少于10个中文字符！</span></td></tr>
				<tr style="height: 6px"><td colspan="2"></td></tr>
				<tr align="center">
					<td colspan="2">
			            <a href="#" class="easyui-linkbutton" id="add" iconCls="icon-save">提&nbsp;&nbsp;交</a>
			            <a href="#" class="easyui-linkbutton" id="ok" iconCls="icon-ok" style="display: none">同&nbsp;&nbsp;意</a>
			             &nbsp;&nbsp;&nbsp;&nbsp;
			            <a href="#" class="easyui-linkbutton" id="back" iconCls="icon-no">驳&nbsp;&nbsp;回</a>
			            <a href="#" class="easyui-linkbutton" id="close" iconCls="icon-no">返&nbsp;&nbsp;回</a>
					</td>
				</tr>
				<tr style="height: 5px"><td colspan="2"></td></tr>
		    </table>
		    </form>
		</div>
	</div>	
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript">
		var api = frameElement.api;
		var W = api.opener;
		
		var id = GetQueryString("id");
		var flag = GetQueryString("flag");
		var r3_code = GetQueryString("r3_code");
		var type = GetQueryString("type");
		var sure = GetQueryString("sure");
	
		$(document).ready(function(){
			if('sure'==sure){//确认申请
				$("#ok").show();
				$("#add").hide();
				$("#close").hide();
				$("#ope_name").show();
	            $("#content").attr("disabled", "true");
				$.post('${ctx}/manager/admin/WaitAuditList.do?method=getAudit&id='+id,function(result){
					$("#content").val(result.oper_reason);
					$("#real_name").text(result.map.real_name);
				},'json');
				
				//同意申请
				$("#ok").bind('click',function(){
					$.post('${ctx}/manager/admin/WaitAuditList.do?method=sureAudit&s_type=0&id='+id,function(result){
						if(result.flag=='suc'){
							alert("确认成功！");
							if('agent'==type){
								W.document.getElementById("search").click();
							}else{
								W.document.forms[0].submit();
							}
						}else if(result.flag=='error'){
							alert("操作发生错误，确认失败！请联系系统管理员。");
						}
						api.close();
					},'json');
				});
				//驳回申请
				$("#back").bind('click',function(){
					$.post('${ctx}/manager/admin/WaitAuditList.do?method=sureAudit&s_type=1&id='+id,function(result){
						if('agent'==type){
							W.document.getElementById("search").click();
						}else{
							W.document.forms[0].submit();
						}
						api.close();
					},'json');
				});
			}else{//新增申请
				$("#back").hide();
				$("#close").bind('click',function(){
					api.close();
				});
				//查询是否已有申请
				$.post('${ctx}/manager/admin/WaitAuditList.do?method=checkAudit&id='+id+'&flag='+flag+'&r3_code='+r3_code+'&type='+type,function(result){
					if(result.have=='1'){
						alert("已存在未确认的申请，请分公司管理员确认后再重新提交");
						api.close();
					}
				},'json');
				
				//提交申请
				$("#add").bind('click',function(){
					var val = $.trim($("#content").val());
					
					if(val!=''){
						var getBLen = lengofstr(val);
						if(getBLen<20){
							alert("原因不得少于10个字符！");
							return;
						}
						val = encodeURI(encodeURI(val)); 
						$.post('${ctx}/customer/manager/CustomerAudit.do?method=save&id='+id+'&flag='+flag+'&r3_code='+r3_code+'&type='+type+'&content='+val,function(result){
							if(result.res=='success'){
								if(!result.role){
									alert("成功提交申请,请等待分公司管理员审批！");
								}
								if('agent'==type){
									W.document.getElementById("search").click();
								}else{
									W.document.forms[0].submit();
								}
							}else if(result.res=='error'){
								alert("操作发生错误，提交申请失败！请联系系统管理员。");
							}
							api.close();
						},'json');
					}else{
						alert("请填写申请原因");
					}
				});
			}
		});
		
		function lengofstr(str) {
		  if (str == null) return 0;
		  if (typeof str != "string"){
		    str += "";
		  }
		  return str.replace(/[^x00-xff]/g,"01").length;
		}
	</script>
</body>
</html>
