<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcHhApply.do" enctype="multipart/form-data">
      <html-el:hidden property="id" />
      <html-el:hidden property="brand_id" value="114" />
      <html-el:hidden property="brand_name" value="康佳" />
      <html-el:hidden property="add_user_id" />
      <html-el:hidden property="add_dept_id" styleId="add_dept_id"/>
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">添加换货申请</th>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>换货仓库：</td>
          <td><html-el:select property="hh_store_id_son" styleId="hh_store_id_son">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>产品类别：</td>
          <td><html-el:select property="pd_type_id" styleId="pd_type_id" value="${af.map.pd_type_id}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${basePdClassList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>型号：</td>
          <td><html-el:select property="pd_id" styleClass="webinput" styleId="pd_id" style="width:155px;"> </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>换货产品类型：</td>
          <td><html-el:select property="hh_type" styleId="hh_type">
              <html-el:option value="1">正品</html-el:option>
              <html-el:option value="0">残次品</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red" >*</font>产品单价：</td>
          <td><html-el:text property="price" styleId="price" maxlength="8" size="8" styleClass="webinput" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>换货数量：</td>
          <td><html-el:text property="hh_count" styleClass="webinput" styleId="hh_count" maxlength="5"/>
            <span style="color:#f00;display:none;" id="Store_state"></span></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item"><font color="red">*</font>换货原因：</td>
          <td><html-el:text property="hh_reason" style="width:60%" styleId="hh_reason" styleClass="webinput" maxlength="100"></html-el:text></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>换货日期：</td>
          <td><html-el:text property="in_date" styleId="in_date" size="9" maxlength="9" readonly="true" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">备注：</td>
          <td><html-el:text property="remark" style="width:60%" styleId="remark" styleClass="webinput" maxlength="100"></html-el:text></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html-el:button property="save" styleClass="bgButtonSave" styleId="btn_submit" value="保 存"/>
            <html-el:button property="back" value="返 回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		$("#hh_store_id_son").attr("datatype", "Require").attr("msg", "请输入换货仓库！");
		$("#pd_type_id").attr("datatype", "Require").attr("msg", "请输入产品类别！");
		$("#pd_id").attr("datatype", "Require").attr("msg", "请输入型号！");
		$("#hh_count").attr("datatype", "Number").attr("msg", "请输入换货数量,且必须是正整数！");
		$("#hh_reason").attr("datatype", "Require").attr("msg", "请输入换货原因！");
		$($("input[type='radio'][name='hh_type']").get(0)).attr("datatype", "Group").attr("msg", "请选择换货产品类型！");
		$("#price").attr("dataType","Currency").attr("msg","请填写产品单价，且只能为正数！");
		$("#in_date").attr("datatype", "Require").attr("msg", "请添加换货日期！");

		var f = document.forms[0];
		$("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(f, 3);
			var hh_count =$("#hh_count").val();
			var pd_id=$("#pd_id").val();
			var store_id = $("#hh_store_id_son").val();
			var hh_type = $("#hh_type").val();
			if (isSubmit){
				if(hh_count==0){
					alert("您所填写的换货数量为0,请重新填写!");
					$("#hh_count").focus();
					return false;
				}
				$.ajax({
					type: "POST",
					url: "CsAjax.do",
					data: "method=getStoreState&pd_id=" + pd_id + "&store_id=" + store_id + "&th_type=" + hh_type,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(Dates) {
						if(Number(Dates[0].pd_num) < Number(hh_count)){
								alert("換货数量大于库存数" + Dates[0].pd_num + ",请重新填写!");
								return false;
						}
				$(":submit").attr("value", "正在提交...").attr("disabled", "true");
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				f.submit();
			}
				})
			}
		});	
		
		if("${role_id_syb}" == ''){
			doSelectAjax("CsAjax.do", $("#pd_type_id"), "${af.map.pd_type_id}", $("#pd_id"), "${af.map.pd_id}", "getOwnPdModleList");
		}
		if("${role_id_syb}" != '' && "${role_id_syb}" =="syb"){
			doSelectAjax("CsAjax.do", $("#pd_type_id"), "${af.map.pd_type_id}", $("#pd_id"), "${af.map.pd_id}", "getOwnPdModleListToSyb");
		}

		$("#hh_store_id_son").bind("change",function(){
			$("#pd_type_id").val("");
			$("#pd_id").val("");
			resetData();	
		});
		$("#pd_type_id").bind("change",function(){
			$("#pd_id").val("");
			resetData();	
		});
		$("#pd_id").bind("change",function(){
			resetData();	
		});

});

function resetData(){//重置数据
	
	$("#price").val(0);
	$("#hh_count").val(1);
	
}

function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter",function(){
			return false;
		});
		$(this).keypress(function (){
			if(!this.value.match(/^\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
			if(!this.value.match(/^\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
			if(this.value.length == 0) this.value = "";
		});
		//this.text.selected;
	}
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
