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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<style type="text/css">
	.spancss{ 
width:60px;     
height:20px; 
border:1px solid #000; 
display:-moz-inline-box; /* css注释：for ff2 */ 
display:inline-block; 
text-align:center; 
line-height:20px; 
background-color: burlywood; 
}
	
</style>   
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
    <html-el:form action="/spgl/EcVouchersGl" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="prod_types" styleId="prod_types" value="${af.map.prod_types}"/>
      <html-el:hidden property="pd_name_hid" styleId="pd_name_hid" value="${af.map.pd_name_hid}"/>
      <html-el:hidden property="goods_types_hid" styleId="goods_types_hid" value="${af.map.goods_types_hid}"/>
      <html-el:hidden property="type_name_hid" styleId="type_name_hid" value="${af.map.type_name_hid}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>购物券名称：</td>
          <td width="88%" align="left"><html-el:text property="title" styleId="title" size="25" maxlength="15"/>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>金额：</td>
          <td width="88%" align="left"><html-el:text property="price" styleId="price" size="30" maxlength="6"/></td>
        </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item"  align="right"><font color="red">* </font>有效开始时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd HH:mm:ss" var="_start_date" />
             <html-el:text styleId="start_date" property="start_date" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_start_date}" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"  align="right"><font color="red">* </font>有效截止时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.effective_date}" pattern="yyyy-MM-dd HH:mm:ss" var="_effective_date" />
          	<html-el:text styleId="effective_date" property="effective_date" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_effective_date}" />
          </td>
        </tr>
        <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用产品类别：</td>
        <td align="left" width="88%">
        
        <input type="text" name="prod_name" maxlength="125" value="${af.map.prod_name}" onclick="openEntpChild()" readonly="readonly" id="prod_name" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
         &nbsp;
         <a class="butbase" onclick="openEntpChild()"><span class="spancss">选择</span></a>
       </td>
      </tr>
       <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用属性类别：</td>  
        <td align="left" width="88%">
        
        <input type="text" name="goods_types" maxlength="125" value="${af.map.goods_types}" onclick="openTypeChild()" readonly="readonly" id="goods_types" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
         &nbsp;
         <a class="butbase" onclick="openTypeChild()"><span class="spancss">选择</span></a>
       </td>
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
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>价格所属：</td>
          <td width="88%" align="left">
          <html-el:select property="plat_sys" styleId="plat_sys" styleClass="plat_sys" >
         		<html-el:option value="">-请选择-</html-el:option>
         		<c:if test="${is_admin eq 1}">
         		 <html-el:option value="0">总部</html-el:option>
         		 </c:if>
         		 <html-el:option value="1">分公司</html-el:option>
         </html-el:select>
          </td>
        </tr>
          <tr id="t4" >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所属组织：</td>
            <td width="88%" align="left"><html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${groupList}">
                  <html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
      <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">满多少元：</td> 
          <td width="88%" align="left"><html-el:text property="m_menoy" styleId="m_menoy" size="10" maxlength="8"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">满多少数量：</td> 
          <td width="88%" align="left"><html-el:text property="m_num" styleId="m_num" size="10" maxlength="6"/></td>
        </tr>
       <tr>
          		<td class="title_item" nowrap="nowrap">是否可以叠加使用：</td>
          		<td>
          		<html-el:radio property="is_other" styleClass="is_other"  value="0"  >可以</html-el:radio>
          		<html-el:radio property="is_other" styleClass="is_other"  value="1"  >不可以</html-el:radio>
          		</td>
          	</tr> 
       <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
          	<html-el:textarea property="memo" styleId="memo" cols="5" style="width:450px;height:70px;" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#title").attr("datatype", "Require").attr("msg", "请填写购物券名称！");
	$("#price").attr("datatype", "Require").attr("msg", "请填写金额！").focus(function(){setOnlyInt(this);});
	$("#own_sys").attr("datatype", "Require").attr("msg", "请选择所属系统！");
	$("#plat_sys").attr("datatype", "Require").attr("msg", "请选择所属总部/分公司！");
	$("#dept_id").attr("datatype", "Require").attr("msg", "请选择所属组织！");
	$("#m_menoy" ).attr("focus",setOnlyPosNum); 
	$("#m_num" ).focus(function(){setOnlyInt(this);});
	
	var id = '${af.map.id}';
	if(id =="" || id ==null){
		$("#start_date").attr("datatype", "Require").attr("msg", "请选择有效开始时间");
		$("#effective_date").attr("datatype", "Require").attr("msg", "请选择有效截止时间");
	}
	
	
	$("#memo").textbox({
		maxLength: 450,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
	
});


function openEntpChild(){
	$.dialog({
		title:  "选择产品类别",
		width:  536,
		height: 295,
        lock:true ,
		content:"url:${ctx}/manager/spgl/EcVouchersGl.do?method=chooseProdType"
	});
}

function openTypeChild(){
	$.dialog({
		title:  "选择属性类别",
		width:  536,
		height: 295,
        lock:true ,
		content:"url:${ctx}/manager/spgl/EcVouchersGl.do?method=chooseGoodType"
	});
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

//正数
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

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
