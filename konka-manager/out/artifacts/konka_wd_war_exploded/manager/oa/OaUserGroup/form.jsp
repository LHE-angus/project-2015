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
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />

<style>
.tip {
	background-color: yellow;
	height: 20px;
	width: 100%;
	text-align: center;
	border: 1px;
	color: red;
	border-style: solid;
	font-weight: bold;
	font-size: 14px;
}

fieldset {
	padding: 10px;
	margin: 10px;
	border: #969696 solid 1px ;
}
</style>
 
  
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  
  <div class="rtabcont2">
    <html-el:form action="oa/OaUserGroup" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="dept_name" styleId="dept_name" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />

		<fieldset>
			<legend>群组信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
				<tr>
					<td width="15%" valign="middle" nowrap="nowrap" class="title_item">群组名称：</td>
					<td width="35%">
						<html-el:text property="group_name" styleId="group_name" />
					</td>
					<td width="15%" valign="middle" nowrap="nowrap" class="title_item">部门：</td>
					<td width="35%">
						<html-el:select property="dept_id" styleId="dept_id" style="width:150px" onchange="afterChangeDeptId()">
								<html-el:option value="">请选择</html-el:option>
								<c:forEach items="${deptList}" var="cur">
									<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
								</c:forEach>
						</html-el:select>
					</td>
				</tr>
			</table>
		</fieldset>
   
   		<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0" class="rtable3" >
       		<tr>
       		<td nowrap="nowrap" class="title_item" colspan="4">
       			&nbsp;&nbsp;<input class="but2" type="button" title="添加人员" value="添加" onclick="showEditPage();" ></input>
	       		&nbsp;&nbsp;<input class="but4" type="button" value="保存" id="btn_save" />
	       		&nbsp;&nbsp;<input class="but5" type="button" value="返回" onclick="history.go(-1);" id="btn_back" />
       		</td>
       		</tr>
       	
	       	<tr>
	       		<td colspan="4">
		       		<div class="rtabcont1">
					    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" id="ctmtab">
					        <tr class="tabtt1">
					          <td align="center" nowrap="nowrap" width="10%">操作</td>
					          <td align="center" nowrap="nowrap"  style="display:none">行ID</td>
					          <td align="center" nowrap="nowrap"  style="display:none">头ID</td>
					          <td align="center" nowrap="nowrap" >姓名</td>	 
					          <td align="center" nowrap="nowrap" style="display:none">用户ID</td>	 
					        </tr>
							<c:forEach var="cur" items="${OaUserGroupLList}" varStatus="vs">
							        <tr>
							       	 	<td align="center" nowrap="nowrap" ><a href='OaUserGroup.do?method=deleteL&id=${cur.id }&header_id=${cur.head_id}'>删除人员</a></td>
							            <td align="center" nowrap="nowrap" style="display:none">${cur.id }</td> 
							            <td align="center" nowrap="nowrap" style="display:none">${cur.head_id }</td> 
							            <td align="left" nowrap="nowrap">${cur.user_name }</td> 
							            <td align="left" nowrap="nowrap" style="display:none" >${cur.user_id }</td> 
							        </tr> 
							</c:forEach>
					      </table>
					</div> 
	       		</td>
	       	</tr>
	      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	/**较验规则**/
    	$("#group_name").attr("dataType", "Require").attr("msg", "群组不能为空！");
    	$("#dept_id").attr("dataType", "Require").attr("msg", "部门不能为空！");
        $("#btn_save").click(function(){
        	if(Validator.Validate(this.form, 3)){
    			 $("#btn_save").attr("disabled", "true");
    			  this.form.submit();
        	}
        });
        //
        var id = '${af.map.id}';
        if(id){
        	$('#group_name').attr("readonly","readonly");
        }
	}); 
    
    
    
    
    // 弹出机型 挑选页面,保存操作在弹出页面执行 
    function showEditPage(){
    	var id = $("#id").val();
    	var tt = Math.random();
    	if(!id){
    		alert("先保存群组再添加人员 ! ");
    		return ;
    	} 
       window.showModalDialog("${ctx}/manager/oa/OaUserGroup.do?method=showEditPage&dept_id="+"${af.map.dept_id}"+"&header_id="+id+"&mod_id="+"${af.map.mod_id}&tt="+tt+"",window,"Height=550px,Width=900px,resizable=no,toolbar=no,menubar=no,scrollbars=no,addressbars=no, status=no,alwaysRaised=yes");
	   window.location.href=window.location.href;
   }
    
    
    function afterChangeDeptId(){
    	var deptName = $('#dept_id').find("option:selected").text();
    	if(deptName!=null && deptName!='请选择'){
    		$('#dept_name').val(deptName);
    	}
    }
    
//]]></script>
</body>
</html>
