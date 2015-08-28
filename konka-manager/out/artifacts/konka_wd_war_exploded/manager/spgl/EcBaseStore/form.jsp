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
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcBaseStore" method="post">
      <html-el:hidden property="store_id" styleId="store_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="type1" styleId="type1" value="0"/>
      <c:if test="${not empty af.map.store_id}">
      <html-el:hidden property="store_type" value="${af.map.store_type}"/>
      </c:if>
     <c:set var="readonly" value="${empty af.map.store_id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	  <tr>
      	  	<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库类型：</td>
            <td width="88%" align="left">
            	<html-el:radio property="store_type" styleClass="store_type"  value="0" disabled="${readonly}" >全国仓</html-el:radio>
            	<html-el:radio property="store_type" styleClass="store_type"  value="1" disabled="${readonly}">区域仓</html-el:radio>
            	<html-el:radio property="store_type" styleClass="store_type"  value="2" disabled="${readonly}">分公司仓</html-el:radio>
            </td>
      	  </tr>
      	  <tr id="t1" style="display: none">
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>省份：</td>
            <td width="88%" align="left">
				<c:if test="${not empty af.map.store_id}">
          		<div style="border: 1px #ccc solid;width: 500px;  ">
       				<c:forEach items="${entityList1}" var="cur" varStatus="vs" >
       					<span>${cur.p_name},</span>
       				</c:forEach>
          		</div>
          		</c:if>
            	<input type="button" id="0" class="but_ec" value="选 择" onclick="getProvinceList('${af.map.store_id}');" style="cursor:pointer;" />
          		<span id="fuwu_table" ></span>
          		<html-el:hidden property="e_id" styleId="e_id"/>
            </td>
          </tr>	
      	  	
          <tr id="t2" style="display: none">
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
            <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id" disabled="${readonly}">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${groupList}">  
                  <html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          
          <tr id="t3" > 
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属组织：</td>   
            <td width="88%" align="left"><html-el:select property="dept_id_2" styleId="dept_id_2" disabled="${readonly}">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${groupList}">
                  <html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库名称：</td>
          <td width="88%" align="left"><html-el:text property="store_name" styleId="store_name" size="25" maxlength="15"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库编码：</td>
          <td width="88%" align="left"><html-el:text property="store_sn" styleId="store_sn" size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>仓库地址：</td>
          <td width="88%" align="left"><html-el:text property="store_addr" styleId="store_addr" size="30" maxlength="40"/>
          </td>
        </tr>
        <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属系统：</td>
            <td width="88%" align="left"><html-el:select property="own_sys" styleId="own_sys" disabled="${readonly}">
                <html-el:option value="">请选择...</html-el:option>
                  <c:if test="${is_admin eq 1}">
                  <html-el:option value="1">工卡系统</html-el:option>
                  </c:if>
                  <html-el:option value="2">触网系统</html-el:option>
              </html-el:select>
            </td>
          </tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属总部/分公司：</td>
            <td width="88%" align="left"><html-el:select property="plat_sys" styleId="plat_sys" disabled="${readonly}">
                <html-el:option value="">请选择...</html-el:option>
                 <c:if test="${is_admin eq 1}">
                  <html-el:option value="0">总部</html-el:option>
                  </c:if>
                  <html-el:option value="1">分公司</html-el:option>
              </html-el:select>
            </td>
          </tr> 
        
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
          	<html-el:textarea property="remarks" styleId="remarks" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#store_sn").attr("datatype", "Require").attr("msg", "请填写仓库编码");
	$("#store_name").attr("datatype", "Require").attr("msg", "请填写仓库名称！");
	$("#store_addr").attr("datatype", "Require").attr("msg", "请填写仓库地址！");
	$("#own_sys").attr("datatype", "Require").attr("msg", "请选择所属系统");
	$("#plat_sys").attr("datatype", "Require").attr("msg", "请选择所属总部/分公司");
	
	//alert("${af.map.store_id}");
	
	if("" != "${af.map.store_id}"){
		var temp = "${af.map.store_type}";
		if(temp == 0){
			$("#t1").hide();
			$("#t2").hide();
			$("#t3").show();
			$("#type1").val(0);
		}else if(temp == 1){
			$("#t1").show();
			$("#t2").hide();
			$("#t3").hide();
			$("#type1").val(1);
		}else if(temp == 2){
			$("#t1").hide();
			$("#t2").show();
			$("#t3").hide();
			$("#type1").val(2);
		}
	}
	
	
	$(".store_type").click(function(){
		//alert($(this).val());
		if($(this).val() == 0){
			$("#t1").hide();
			$("#t2").hide();
			$("#t3").show();
			$("#type1").val(0);
		}else if($(this).val() == 1){
			$("#t1").show();
			$("#t2").hide();
			$("#t3").hide();
			$("#type1").val(1);
		}else if($(this).val() == 2){
			$("#t1").hide();
			$("#t2").show();
			$("#t3").hide();
			$("#type1").val(2);
		}		
	});

	
	//if("" != "${af.map.store_id}"){
		//$("#province_id").click(function(){
			//var ss = $("#e_id").val();
			//var store_id = "${af.map.store_id}";
			//$.ajax({
				//type: "POST" , 
			//	url: "${ctx}/manager/spgl/EcBaseStore.do" , 
				//data:"method=getHadBindingPindexId&store_id=" + store_id,
				//dataType: "json" , 
		        //async: true, 
		       // error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
		       // success: function (result) {
			        	//for(var i = 0;i < ss.split("@").length-1;i++){
							//if((result.pindex_id).indexOf(ss.split("@")[i]) < 0){
								//temp += 1; 
							//}
						//}
		       // }
			//});
		//});
		//}
	
	$("#btn_submit").click(function(){

		if($("#type1").val() == 0){     
			//var plat_sys = $("#plat_sys").val();
			//if(plat_sys == 1){
				//var dept_id = $("#dept_id_2").val();   
				//if(""==dept_id || null==dept_id){
				//	alert("请选择分公司");
					//return;
				//}
			//} 
			$("#dept_id_2").attr("datatype", "Require").attr("msg", "请选择所属组织！");
		}
			
		if($("#type1").val() == 2){
			$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
		}
		if($("#type1").val() == 1){
			if($(".but_ec").attr("id") == 1&&"" != $("#e_id").val()){
				var isSubmit = Validator.Validate(this.form, 3);
				if (isSubmit) {
					$(":button").attr("disabled", "true");
					$(":reset").attr("disabled", "true");
					this.form.submit();
				}
			}else{
					alert("请选择关联省份");
				}
		}else{
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		}
					
	});
	
	$("#remarks").textbox({
		maxLength: 150,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
});

function getProvinceList(id){
	//var returnValue = window.showModalDialog("EcBaseStore.do?method=selectProvince&store_id="+id+"&mod_id=905105&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:300px");
	 window.open("EcBaseStore.do?method=selectProvince&store_id="+id+"&mod_id=905105&azaz=" + Math.random(),'window','height=300,width=700,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}

function set_value(returnValue){
	var e_ids = "";
	if(returnValue != null){
		$(".d1").remove();
		$("#e_id").val("");
		for(var i = 0; i < returnValue.split("##").length; i++){
			var temp = 	returnValue.split("##")[i];
			if(temp.length > 0){
			var pds = temp.split("@");
			e_ids += pds[1]+"@";
			var tr = "<span class='d1'>" + pds[0] +"\,</span>";
			$(tr).appendTo($("#fuwu_table"));
			}
		}
	}
	$("#e_id").val(e_ids);
	$(".but_ec").attr("id","1");
	window.parent.resizeFrameHeight('mainFrame', 3);

}


function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
