<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="admin/TaskContent" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="participant_id" styleId="participant_id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr >
        	<td class="title_item" width="10%">部门：</td>
        	<td>
	           <html-el:select property="dept_id" styleId="dept_id" value="${af.map.map.dept_id }" style="width:150px" >
	           		<html-el:option value="">-请选择-</html-el:option>
	          		<c:forEach items="${deptList}" var="cur">
	          			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          		</c:forEach>
	           </html-el:select>
        	</td>
        </tr>
        <tr>
          <td class="title_item" width="10%">年度：</td>
          <td width="60%">
<%--           	<html-el:text property="task_year" styleId="task_year" onkeyup="checkNum(this)"></html-el:text> --%>
          	<html-el:select property="task_year"  styleId="task_year" style="width:150px">
          	<html-el:option value="">-请选择-</html-el:option>
          	<html-el:option value="2010">2010</html-el:option>
          	<html-el:option value="2011">2011</html-el:option>
          	<html-el:option value="2012">2012</html-el:option>
          	<html-el:option value="2013">2013</html-el:option>
          	<html-el:option value="2014">2014</html-el:option>
          	<html-el:option value="2015">2015</html-el:option>
          	<html-el:option value="2016">2016</html-el:option>
          	<html-el:option value="2017">2017</html-el:option>
          	<html-el:option value="2018">2018</html-el:option>
          	<html-el:option value="2019">2019</html-el:option>
          	<html-el:option value="2020">2020</html-el:option>
          	</html-el:select>
           </td>
           </tr> 
		<tr>
          <td class="title_item" width="10%">月份：</td>
          <td width="60%">
<%--           	<html-el:text property="task_month" styleId="task_month" onkeyup="checkNum(this)" ></html-el:text> --%>
          		<html-el:select property="task_month"  styleId="task_month" style="width:150px">
          	<html-el:option value="">-请选择-</html-el:option>
          	<html-el:option value="01">01</html-el:option>
          	<html-el:option value="02">02</html-el:option>
          	<html-el:option value="03">03</html-el:option>
          	<html-el:option value="04">04</html-el:option>
          	<html-el:option value="05">05</html-el:option>
          	<html-el:option value="06">06</html-el:option>
          	<html-el:option value="07">07</html-el:option>
          	<html-el:option value="08">08</html-el:option>
          	<html-el:option value="09">09</html-el:option>
          	<html-el:option value="10">10</html-el:option>
          	<html-el:option value="11">11</html-el:option>
          	<html-el:option value="12">12</html-el:option>
          	</html-el:select>
          </td>
        <tr>
          <td class="title_item" width="10%">任务类别：</td>
          <td width="60%">
          		<html-el:select property="task_type" styleId="task_type" style="width:150px" value="${af.map.task_p_type}" onchange="onTaskTypeChange()">
	          		<html-el:option value="customer">客户</html-el:option>
	          		<html-el:option value="ywy">业务员</html-el:option>
<%-- 	          		<html-el:option value="jb">经办</html-el:option> --%>
<%-- 	          		<html-el:option value="dept">分公司</html-el:option> --%>
	           </html-el:select>
          </td>
        </tr>
        <tr name="ywy_tr">
        	<td class="title_item" width="10%">岗位ID：</td>
        	<td>
        		<html-el:text property="ywy_id" styleId="ywy_id" value="${af.map.map.ywy_id  }"></html-el:text>
        	</td>
        </tr>
		<tr name="ywy_tr">
        	<td class="title_item" width="10%">业务员名称：</td>
        	<td>
        		<html-el:text property="ywy_name" styleId="ywy_name" value="${af.map.map.ywy_name }"></html-el:text>
        	</td>
        </tr>
        <tr name="customer_tr">
        	<td class="title_item" width="10%">客户R3编码：</td>
        	<td>
        		<html-el:text property="customer_id" styleId="customer_id" value="${af.map.map.customer_code }"></html-el:text>
        	</td>
        </tr>
		<tr name="customer_tr">
        	<td class="title_item" width="10%">客户名称：</td>
        	<td>
        		<html-el:text property="customer_name" styleId="customer_name" value="${af.map.map.customer_name }"></html-el:text>
        	</td>
        </tr> 
		<tr>
          <td class="title_item" width="10%">任务系数：</td>
          <td width="60%">
          	<html-el:text property="task_xs" styleId="task_xs" onfocus="javascript:setOnlyNum(this);"></html-el:text> &#37;
           </td>
           </tr>
		<tr>
          <td class="title_item" width="10%">任务额度(元)：</td>
          <td width="60%">
          	<html-el:text property="task_ed" styleId="task_ed" onchange="javascript:checkNum(this);"></html-el:text>
       &nbsp;&nbsp;  <input  type="button" id="get_task_ed" value="获取额度" />        
          </td>
        </tr>
		
        <tr> 
        <td class="title_item" width="10%">描述：</td>
        <td>
        	<html-el:textarea property="task_desc" style="width:300px;height:80px;"></html-el:textarea>
          </td>
        </tr>
       	<tr>
       		<td nowrap="nowrap" class="title_item" align="center" width="10%">
	       	<input class="but4" type="button" value="保存" id="btn_save" />
	       	&nbsp;<input class="but5" type="button" value="返回" onclick="javascript:history.go(-1)" />
       		</td> 
       		<td></td>
       	</tr>
      </table> 
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>


<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	//默认是客户选项
    	var task_type = '${af.map.task_p_type}' ;
    	if(task_type=="" || task_type=="customer"){
   			$("tr[name='customer_tr']").show();
    		$("tr[name='ywy_tr']").hide();
    	}else{
    		$("tr[name='ywy_tr']").show();
   			$("tr[name='customer_tr']").hide();
    	}
    	
    	$("#task_type").change(
		   	function(){
		   		if(this.value=="customer"){
		   			$("tr[name='ywy_tr']").hide();
		   			$("tr[name='customer_tr']").show();
		   		}else{
		   			$("tr[name='ywy_tr']").show();
		   			$("tr[name='customer_tr']").hide();
		   		}		
		   	}
    	);
    	
    	/**较验规则**/
    	$("#dept_id").attr("dataType", "Require").attr("msg", "部门不能为空！");
    	$("#task_year").attr("dataType", "Require").attr("msg", "年度不能为空！");
    	$("#task_month").attr("dataType", "Require").attr("msg", "月度不能为空！");
    	
        $("#btn_save").click(function(){
        	var task_xs = $("#task_xs").val();
        	var task_ed = $("#task_ed").val();
        	if(task_xs=="" && task_ed==""){
        		alert("任务系数和任务额度 只允许一个为空!");
        		return false;
        	}
        	
        	var task_year = $("#task_year").val();
        	var task_month = $("#task_month").val();
        	if(task_year<=2000){
        		alert("年度不对,有效值为>=2000");
        		return false;
        	}
        	if(task_month>12 || task_month<1){
        		alert("月份不对,有效值为1~12");
        		return false;
        	}
        	
        	if(Validator.Validate(this.form, 3)){
    			 $("#btn_submit").attr("disabled", "true");
    			 this.form.submit();
    		}
        });
        
        
      $("#get_task_ed").click(function(){
    	  $("#task_xs").attr("dataType", "Require").attr("msg", "任务系数不能为空！");
    	  var dept_id=$("#dept_id").val();
    	  var task_year=$("#task_year").val();
    	  var task_month=$("#task_month").val();
    	  var task_xs=$("#task_xs").val();
    	  if(Validator.Validate(this.form, 3)){
    	  $.ajax({
    		  type: "POST",
    		url: "${ctx}/manager/admin/TaskContent.do?method=getTaskEd",
    		data: {
    			"task_year":task_year, "task_month":task_month,"dept_id":dept_id,"task_xs":task_xs
    		},
    		dataType: "jsonp",
    		jsonp: "jsonpcallback",
    		//sync: false, // jsonp不支持同步
    		error: function (xhr, ajaxOptions, thrownError) {alert("获取任务额失败，请确定已经维护总部任务额，任务系数！")},
    		success: function(result) {
    			 $("#task_ed").val(result);
    		}
    		});
    	  }
      });
        
        
        
        
        
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
    		if(!obj.value.match(/^[\+]?\d*?\.?\d*?$/)){obj.value=obj.t_value;}
    		else {
    			obj.t_value=obj.value;
    		}
    		if(obj.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/)){
    			obj.o_value=obj.value;
    		}
    		if(obj.value.length == 0){
    			 obj.value = "0";
    		}
    	}).keyup(function (){
    		if(!obj.value.match(/^[\+]?\d*?\.?\d*?$/)){obj.value=obj.t_value;}
    		else {
    			obj.t_value=obj.value;
    		}
    		if(obj.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/)){
    			obj.o_value=obj.value;
    		}
    		if(obj.value.length == 0){
    			 obj.value = "0";
    		}
    		if(obj.value > 100){
    			obj.value = "0";
    		}
    	}).blur(function (){
    		if(!obj.value.match(/^[\+]?\d*?\.?\d*?$/)){obj.value=obj.t_value;}
    		else {
    			obj.t_value=obj.value;
    		}
    		if(obj.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/)){
    			obj.o_value=obj.value;
    		}
    		if(obj.value.length == 0){
    			 obj.value = "0";
    		}
    		if(obj.value > 100){
    			obj.value = "0";
    		}
    	});
    }
  	
  	function checkNum(s){
  		// /^[0-9]*[1-9][0-9]*$/
  		var p = /^\d{1,12}(?:\.\d{1,4})?$/;
  		if(!p.test(s.value)){
  			s.value= '';
  			s.focus();
  		}
  	}

  
    
//]]></script>
</body>
</html>
