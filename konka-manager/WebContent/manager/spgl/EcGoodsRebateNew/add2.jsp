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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<style type="text/css">
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.webinput {background:#f5f4f4;padding-left: 5px;height: 19px;line-height: 19px;/*font-family: Arial, Helvetica, sans-serif;*/border: #ccc solid 1px;}

.ck-li{position:relative;height:22px;padding:1px auto;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}

.navClass {background-color:#CCC;border-collapse:collapse;}
.navClass th {background:#F6F6F6;color:#C4C4C4;font-size:12px;font-weight:bold;height:20px;line-height:20px;text-align:center;padding:2px;border:1px solid #CCC;}
.navClass td {padding:3px;height:18px;background-color:#fff;border:1px solid #CCC;}

.xubox_close{position:absolute;}
.xubox_close1_0{ right:-25px; top:-16px; width:33px; height:31px; background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -182px no-repeat; cursor:pointer; overflow:hidden;}
.xubox_close1_0:hover{background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -215px no-repeat;}
.xubox_border{border-radius:5px;position:absolute;}

.main_box{position:relative;width:260px;z-index:0;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.box{position:relative;width:130px;z-index:0;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.pic_box{text-align:center;;z-index:1;background:#FFFFFF;margin:5px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont"> 
  <div class="rtabcont2">
    <html-el:form action="/spgl/EcGoodsRebateNew" enctype="multipart/form-data" method="post">
      <html-el:hidden property="method" value="save2" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="goods_id" value="${af.map.goods_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set value="${not empty af.map.id ? true:false}" var="readonly" ></c:set>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3"> 
        <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属组织：</td>  
            <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id" disabled="${readonly}" onchange="show_cust()">
                <html-el:option value="">--请选择--</html-el:option>
                <c:forEach var="cur" items="${sybDeptInfoList}">
                  <html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
                </c:forEach>
              </html-el:select>
              	客户：<html-el:select property="c_id" styleId="c_id" disabled="${readonly}"></html-el:select>
            </td>
          </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">商品编码：</td>
          <td align="left">${af.map.pd_sn}</td>
        </tr>
         <tr style="display: none;">
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>佣金类型：</td>
          <td width="88%" align="left">
          <html-el:select property="rebate_type" styleId="rebate_type" disabled="${readonly}">
         		<html-el:option value="">-请选择-</html-el:option>
         		 <html-el:option value="1">付款积分</html-el:option>
         		 <html-el:option value="2">奖励积分</html-el:option>
         </html-el:select>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>佣金</td>
          <td width="88%" align="left">计算方式：
          <html-el:select property="rebate_way" styleId="rebate_way" disabled="${readonly}" >
         		<html-el:option value="">-请选择-</html-el:option>
         		<html-el:option value="1">固定值</html-el:option>
         		<html-el:option value="2">比例</html-el:option>
         </html-el:select>
         	佣金:<html-el:text property="rebate_value"  styleId="rebate_value"/> <span id="point_b_type" style="font-size:15px;"></span>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><font color="red">* </font>上级佣金分成：</td>
          <td align="left">计算方式：
          <html-el:select property="super_rebate_way" styleId="super_rebate_way" disabled="${readonly}" >
         		<html-el:option value="">-请选择-</html-el:option>
         		<html-el:option value="1">固定值</html-el:option>
         		<html-el:option value="2">比例</html-el:option>
         </html-el:select>
         	佣金:<html-el:text property="super_rebate_value"  styleId="super_rebate_value"/> <span id="super_point_b_type" style="font-size:15px;"></span></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属系统：</td>
          <td width="88%" align="left">
          <html-el:select property="own_sys" styleId="own_sys" >
         		<html-el:option value="">-请选择-</html-el:option>
         		 <html-el:option value="2">触网</html-el:option>
         </html-el:select>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><font color="red">* </font>有效开始日期：</td>
          <td>
          <fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd HH:mm:ss" var="_start_date" />
             <html-el:text styleId="start_date" property="start_date" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_start_date}" />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><font color="red">* </font>有效截止日期：</td>
          <td><fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd HH:mm:ss" var="_end_date" />
             <html-el:text styleId="end_date" property="end_date" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_end_date}" />
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>佣金所属：</td>
          <td width="88%" align="left">
          <html-el:select property="plat_sys" styleId="plat_sys" styleClass="plat_sys" disabled="${readonly}">
         		<html-el:option value="">-请选择-</html-el:option>
         		<c:if test="${is_admin eq 1}">
         		 <html-el:option value="0">总部</html-el:option>
         		 </c:if>
         		 <html-el:option value="1">分公司</html-el:option>
         </html-el:select>
          </td>
        </tr> 
        <tr>
          		<td class="title_item" nowrap="nowrap" align="right">状态：</td>  
          		<td>
          		<html-el:radio property="state" styleClass="state"  value="0"  >正常</html-el:radio>
          		<html-el:radio property="state" styleClass="state"  value="1"  >失效</html-el:radio>
          		</td>
          	</tr> 
       
        <tr>
          <td align="center" colspan="2"><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){


	$("#start_date").attr("datatype", "Require").attr("msg", "请选择有效开始日期！");
	$("#end_date").attr("datatype", "Require").attr("msg", "请选择有效截止日期！");
	$("#own_sys").attr("datatype", "Require").attr("msg", "请选择所属系统！");
	$("#plat_sys").attr("datatype", "Require").attr("msg", "请选择所属总部/分公司！");
	$("#dept_id").attr("datatype", "Require").attr("msg", "请选择分公司！");
	$("#rebate_value").attr("datatype", "Require").attr("msg", "请填写佣金值！").attr("focus",setOnlyPosNum);  
	$("#rebate_way").attr("datatype", "Require").attr("msg", "请选择计算方式！");
	$("#super_rebate_value").attr("datatype", "Require").attr("msg", "请填写上级佣金分成佣金值！").attr("focus",setOnlyPosNum);  
	$("#super_rebate_way").attr("datatype", "Require").attr("msg", "请选择上级佣金分成计算方式！");
	
	$("#rebate_way").change(function(){
		if($(this).val() != "" && $(this).val() == 2){
			$("#point_b_type").text(" %");
		}else if($(this).val() != "" && $(this).val() == 1){
			$("#point_b_type").text(" 元");
		}else {
			$("#point_b_type").text("");
		}
		
	});   
	$("#super_rebate_way").change(function(){
		if($(this).val() != "" && $(this).val() == 2){
			$("#super_point_b_type").text(" %");
		}else if($(this).val() != "" && $(this).val() == 1){
			$("#super_point_b_type").text(" 元");
		}else {
			$("#super_point_b_type").text("");
		}
		
	});   
	//初始化客户
	show_cust();

	$("#btn_submit").click(function(){

		var up_date = $("#start_date").val();
		var down_date = $("#end_date").val();
		
		var _up_date = up_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		var _down_date = down_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");

		if(_down_date < _up_date){
			alert("截止时间不能小于开始时间");  
			return false; 
		}
		
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});


function setOnlyPosNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

function show_cust(){
	$("#c_id option").remove();
	$("#c_id").append("<option value=''>请选择。。</option>");
	var c_id = "${af.map.c_id}";
	var group_id =$("#dept_id").val();
	if(group_id!=""){	
		$.ajax({
			type: "POST",
			url: "EcCust.do",
			data: "method=ajaxJson&group_id=" + group_id,
			dataType: "json",
			error: function(request, settings) {},
			success: function(data) {
				if(data.length>0){
					for(var i=0;i<data.length;i++){
						var cust=data[i];
						if(c_id!="" && c_id ==cust.c_id){
							$("#c_id").append("<option value='"+cust.c_id+"' selected >"+cust.c_name+"</option>");
						}else{
							$("#c_id").append("<option value='"+cust.c_id+"' >"+cust.c_name+"</option>");
						}
					}
				}
			}
		});		
	}
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
