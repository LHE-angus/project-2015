﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	  	<div class="oartop">
			<table width="400" border="0" cellpadding="0" cellspacing="0">
		    	<tr>
		        	<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
		        	<td>&nbsp;当前位置：<span id='nav'></span></td>
		      	</tr>
		    </table>
		</div>
		<div style="padding:5px;height: auto">
			<form id='fm' method="post" enctype="multipart/form-data">
			<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
				<tr class="oartop" style="margin-bottom: 5px; height: 12px; line-height: 12px;">
					<td colspan="4">申请信息</td>
				</tr>
				<tr>
					<td width="20%" align="right">
						<strong class="fb">回 执 单：</strong>
					</td>
					<td>
						<span id="hzd_id"></span>
					</td>
					<td align="right">
						<strong class="fb">申请时间：</strong>	
					</td>
					<td>
						<span id="today"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong class="fb">填 写 人：</strong>
					</td>
					<td>
						<span id="write_man"></span>
					</td>
					<td align="right">
						<strong class="fb">部&nbsp;&nbsp;&nbsp;&nbsp;门：</strong>	
					</td>
					<td>
						<span id="dept_name"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong class="fb"><span style="color:red;">* </span>申 请 人：</strong>
					</td>
					<td>
						<input class="easyui-validatebox" name="sq_name" id="sq_name"/>	
					</td>
					<td align="right">
						<strong class="fb"><span style="color:red;">* </span>申请类型：</strong>	
					</td>
					<td>
						<input id="type" name="type" style="width:150px"/>
					</td>
				</tr>
				
		        <tr>
		        	<td align="right">
				   		<strong class="fb"><span style="color:red;">* </span>标&nbsp;&nbsp;&nbsp;&nbsp;题：</strong>
		        	</td>
				   	<td colspan="3">
				   		<input class="easyui-validatebox" name="req_title" id="req_title" style="width: 400px"/>				   		
				   	</td>
				</tr>
		        <tr>
		        	<td align="right">
		          		<strong class="fb"><span style="color:red;">* </span>详&nbsp;&nbsp;&nbsp;&nbsp;情：</strong> 
		        	</td>
		          	<td colspan="3">
		          		<textarea rows="10" cols="70" id="content" style="resize:none;"></textarea>
		           	</td>
		        </tr>
		        <tr>
            		<td align="right"><strong class="fb">上传附件：</strong></td>
            		<td colspan="3">
            			<div id="divFileHidden" style="display: none;">
                			<input name="file_hidden" type="file" id="file_hidden" style="width: 500px;" />
                			<img src="../../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/>
                		</div>
              			<div id="divFile">
                			<input name="file_show" type="file" id="file_show" style="width: 500px;" />
                			<img src="../../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" />
                		</div>
            		</td>
          		</tr>
				<tr id='shlc' style="display: none">
					<td align="right">
				    	<strong class="fb"><span style="color:red;">* </span>审批流程：</strong>
					</td>
				    <td colspan="3">
				   		<input id="audit_node" name="audit_node" style="width:250px;"/>
				   	</td>
		        </tr>
				<tr id='spr' style="display: none">
					<td align="right">
				    	<strong class="fb"><span style="color:red;">* </span>审批流程：</strong>
					</td>
				    <td colspan="3">
				   		部门主管：<input type="hidden" name="audit_user_id" id="audit_user_id1" />
                      	<input type="text" name="audit_user_name" id="audit_user_name1" readonly="readonly" style="vertical-align:middle;width:20%" />
                      	<img onclick="selectUser(this,1);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" /><br/>
				   		分司领导：<input type="hidden" name="audit_user_id" id="audit_user_id2" />
                      	<input type="text" name="audit_user_name" id="audit_user_name2" readonly="readonly"  style="vertical-align:middle;width:20%" />
                      	<img onclick="selectUser(this,2);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" /><br/>
				   		总部领导：<input type="hidden" name="audit_user_id" id="audit_user_id3" />
                      	<input type="text" name="audit_user_name" id="audit_user_name3" readonly="readonly" style="vertical-align:middle;width:20%" />
                      	<img onclick="selectUser(this,3);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" /><br/>
                      	处 理 人：<input type="hidden" name="audit_user_id" id="audit_user_id4" value='1'/>
                      	<input type="text" name="audit_user_name" id="audit_user_name4" readonly="readonly" style="vertical-align:middle;width:20%" value='超级管理员'/>
                      	<img onclick="selectUser(this,4);" src="${ctx}/images/search.gif" style='cursor: pointer;vertical-align:middle;' alt='选择人员' title="选择人员" />
				   	</td>
		        </tr>
		    </table>
		    </form>
		    <br/>
		    <label style="margin-left:40%">
	            <a href="#" class="easyui-linkbutton" id="add" iconCls="icon-save">提&nbsp;&nbsp;交</a>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <a href="#" class="easyui-linkbutton" id="back" iconCls="icon-undo" onclick="history.back();return false;">返&nbsp;&nbsp;回</a>
            </label>
		</div>
	</div>	
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/public.js"></script>
	<script type="text/javascript">
		//选择审批人
		function selectUser(obj,num) {
		    var $p = $(obj).parent();
		    var returnValue = window.showModalDialog("${ctx}/manager/oa/NewDiaLog.do?azaz=" + Math.random() + "&method=selectUser&selectype=signal&selectedUsersID=" +  $("#audit_user_id"+num, $p).val()+"&num="+num, window, "dialogWidth:620px;status:no;dialogHeight:390px;scroll:no");
		    if (returnValue != null) { 
		        var names = returnValue.user_link_names, ids = returnValue.user_link_ids;
		        ids = ids.substring(0, ids.length - 1);
		        names = names.substring(0, names.length - 1);
		        $("#audit_user_name"+num, $p).val(names);
		        $("#audit_user_id"+num, $p).val(ids);
		    }
		}
	
		$(function(){
			var use_flag = GetQueryString("use_flag");
			//获取模块id
			var mod_id = GetQueryString("mod_id");
			//初始化
			$.post('${ctx}/manager/admin/SystemAplication.do?method=init&mod_id='+mod_id,function(result){
				$("#nav").text(result.local_str);
				$("#hzd_id").text(result.hzd_id);
				$("#today").text(result.today);
				$("#write_man").text(result.write_man);
				$("#dept_name").text(result.dept_name);

				if(use_flag == 'yes'){
					$("#shlc").show();
					$("#spr").hide();
					//流程初始化
					$('#audit_node').combobox({                 
						url:'${ctx}/manager/admin/SystemAplication.do?method=getAuditNode',  
					 	method:'post',              
					 	editable:false, //不可编辑状态                
					 	cache: false,               
					 	valueField:'link_id',                   
						textField:'audit_node_name'                   
					}); 
				}else{
					$("#spr").show();
					$("#shlc").hide();
				}

				//类型初始化
				$('#type').combobox({                 
					url:'${ctx}/manager/admin/SystemAplication.do?method=getType',  
				 	method:'post',              
				 	editable:false, //不可编辑状态                
				 	cache: false,               
				 	valueField:'C_INDEX',                   
					textField:'C_NAME'                   
				});
				$('#type').combobox('setValue',''); 
			},'json');

			//添加附件
			$("#imgAddTr").click(function (){
		        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
		        resizeFrameHeight();
		        $("img[id='imgDelTr']").each(function(){
		            $(this).click(function (){
		                $(this).parent().remove();
		                resizeFrameHeight();
		            });
		        });
		    });

			//提交申请
			$("#add").bind('click',function(){
				if(check()){
					var content = $("#content").val().replace(/\n/g, '_@').replace(/\r/g, '_#');
					$("#fm").attr("action", "${ctx}/manager/admin/SystemAplication.do?method=add&hzd_id="+$("#hzd_id").text()+"&content="+content+"&mod_id="+mod_id);
		    		$("#fm").submit();
				}
			});

			//校验
			function check(){
				var sq_name= $("#sq_name").val();
				var type = $("#type").combobox("getValue");
				var req_title = $("#req_title").val();
				var content = $("#content").val();

				if(sq_name==''||sq_name==null){
					getFocus('申请人不能为空！','sq_name');
					return false;
				}
				if(type==''||type==null){
					getFocus('请选择申请类型！','type');
					return false;
				}
				if(req_title==''||req_title==null){
					getFocus('标题不能为空！','req_title');
					return false;
				}
				if(content==''||content==null){
					getFocus('申请详情不能为空！','content');
					return false;
				}

				if(use_flag == 'yes'){
					var audit_node = $("#audit_node").combobox("getValue");
					if(audit_node==''||audit_node==null){
						getFocus('审批流程不能为空！','audit_node');
						return false;
					}
				}else{
					var audit_user_name1 = $("#audit_user_name1").val();
					var audit_user_name4 = $("#audit_user_name4").val();
					if(audit_user_name1==''||audit_user_name1==null){
						getFocus('审批流程中的部门主管不能为空！','audit_user_name1');
						return false;
					}
					if(audit_user_name4==''||audit_user_name4==null){
						getFocus('审批流程中的处理人不能为空！','audit_user_name4');
						return false;
					}
				}
				return true;
			}

			function resizeFrameHeight(offset, min_height) {
				$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
			}

		});
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
