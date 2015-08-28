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
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />

<style>

fieldset {
	padding: 10px;
	margin: 10px;
	border: #969696 solid 1px ;
}


</style>
  
</head>
<body>
<div class="oarcont">
  <div >
    <html-el:form action="oa/OaUserGroup" method="post"  >
      <html-el:hidden property="method" value="saveL" styleId="method"  />
      <html-el:hidden property="header_id" styleId="header_id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" styleId="dept_id" />
		<fieldset>
			<legend>人员信息录入</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
				<tr>
		            <td height="28" width="15%" nowrap="nowrap" class="title_item">人员：</td>
		            <td colspan="3">
			            姓名:<input name="user_name_like" id="user_name_like" type="text"  maxlength="12" size="20" />
			        	<input class="but8" type="button" value="获取" id="btn_get_user" />
			        	<input type="button" id="btn_save" class="but4" value="提交" style="margin-left: 15px"/>
			            <table class="areause1" >
			              <tbody>
			                <tr>
			                  <td>
			                    <span id="area_name_0">待选列表</span><br />
			                    <html-el:select property="select1" multiple="false" style="width:200px;height:300px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);">
			                    	<c:forEach var="cur" items="${userList}"> 
			 	                  		<option value="${cur.id}" >${cur.user_name }</option> 
			 	                	</c:forEach>
			                    </html-el:select></td>
			                  <td width="20"></td>
			                  <td>
			                  	<span id="area_name_1">已选列表</span><br />
			                    <html-el:select property="select2" multiple="false" style="width:200px;height:300px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
			                    </html-el:select>
			                    <html-el:hidden property="user_ids" styleId="userIdsHidden" />
			                    <html-el:hidden property="user_names" styleId="userNamesHidden"/>
			                  </td>
			                </tr>
			              </tbody>
			            </table>
		            </td>
             </tr>
			<tr>
			</tr>
			</table>
		</fieldset>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<%-- <script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2side.js"></script> --%>
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	/**较验规则**/
        $("#btn_save").click(function(){
        	var user_ids = "";
        	var user_names="";
			$("#select2 option").each(function(){
				user_ids = user_ids + $(this).val()+",";
				user_names = user_names+$(this).text()+",";
			});
			var _user_ids = user_ids.substring(0, user_ids.length-1);
			var _user_names = user_names.substring(0, user_names.length-1);
			$("#userIdsHidden").val(_user_ids);
			$("#userNamesHidden").val(_user_names);
        	if(Validator.Validate(this.form, 3)){
    			 $("#btn_save").attr("disabled", "true");
    			 //this.form.submit();
    			 //ajax 提交
    			 $.ajax({
    	  				type: "POST",
    	  				url: "OaUserGroup.do?method=saveL",
    	  				data:$('form').serialize(), 
    	  				dataType: "json",
    	  				error: function(request, settings) { alert('操作错误'); },
    	  				success: function(data) {
    	  					alert(data);
    	  			    }
    	  		 	});
    		}
        });
        
        //get user by name 
        $("#btn_get_user").click(function(){
        	var $user_name_like = $('#user_name_like');
  		  	var v_user_name = $user_name_like.val();
        	if(!v_user_name){
        		$user_name_like.css({'border-color':'#FF0000','border-width':'1px','height':'20px'}).focus() ;
        		return false;
        	}
        	$.ajax({
  				type: "POST",
  				url: "OaUserGroup.do",
  				data: "method=" + "ajaxGetUsersByName" + "&user_name_like=" + v_user_name +"&dept_id="+${af.map.dept_id}+ "&header_id="+$("#header_id").val()+"&t=" + (new Date()).getTime(),
  				dataType: "json",
  				error: function(request, settings) { alert('查询错误'); },
  				success: function(data) {
  					$("#select1").empty();
  					var values =  data;
					for(var i = 0; i < values.length; i++) {
						var opt = new Option(values[i].user_name,values[i].id); 
						$("#select1").get(0).options.add(opt);
					}
  			    }
  		 	});
  		});
	}); 
    
    function moveSelected(sourceSelect, targetSelect, isDelete){
    	var cachOptionsArray = new Array();
    	var index = 0;
    	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
    		if (sourceSelect.options[i].selected){
    			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
    			if(isDelete==undefined || isDelete==true){
    				sourceSelect.options[i] = null;
    			}
    			index++;
    		}
    	}
    	var exist = false;
    	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
    		exist = false;
    		for (var j = 0; j < targetSelect.options.length; j++){
    			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
    				exist = true; 
    				break;
    			}
    		}
    		if (!exist){
    			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
    		}
    	}
    }
    
//]]></script>
</body>
</html>
