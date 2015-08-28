<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${naviString}</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body >
<div>
 <div class="rtabcont2" style="margin:1px; ">
  <html-el:form action="/ChannelCustomerBillingAnalysis2.do" >
        <html-el:hidden property="method" value="list"/>
        <html-el:hidden property="selectedFields" styleId="selectedFields" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
	       <table width="100%" border="0"  cellpadding="0" class="rtab2">
	         <tr>
		           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">时间：</td>
		           <td width= "35%">
		           	 <html-el:text styleClass="webinput" property="begindate" styleId="begindate" value="${af.map.begindate}" readonly="readonly" onclick="new Calendar(2010, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
			          &nbsp;=>
			          <html-el:text styleClass="webinput" property="enddate" styleId="enddate" value="${af.map.enddate}" readonly="readonly" onclick="new Calendar(2010, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
		           	  <span id="tiptext" style="display: none"><font color="red">开始日期不能为空</font></span>
		           </td>
		           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">分公司：</td>
		           <td width= "35%">
		              <html-el:select property="dept_id"  styleId="dept_id">
		                <html-el:option value="">-请选择-</html-el:option>
		              </html-el:select>
		              <html-el:select property="l4_dept_id"  styleId="l4_dept_id">
		                <html-el:option value="">-请选择-</html-el:option>
		              </html-el:select>
		              <html-el:select property="l5_dept_id"  styleId="l5_dept_id">
		                <html-el:option value="">-请选择-</html-el:option>
		              </html-el:select>
		           </td>
	         </tr>
	         
	         <tr>
	         	   <td width="15%" valign="middle" nowrap="nowrap" class="title_item">业务员：</td>
		           <td width= "35%">
			         <html-el:text property="userName" styleId="userName" />
				   </td>
		           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">客户名称：</td>
		           <td width= "35%">
		             <html-el:text property="customerName" styleId="customerName" />
		           </td>
	         </tr>
	         <tr>
	         	   <td width="15%" valign="middle" nowrap="nowrap" class="title_item">售达方：</td>
		           <td width= "35%">
			         <html-el:text property="r3code" styleId="r3code" />
				   </td>
		           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">送达方：</td>
		           <td width= "35%">
		             <html-el:text property="songdf" styleId="songdf" />
		           </td>
	         </tr>
	         <tr>
	         	   <td width="15%" valign="middle" nowrap="nowrap" class="title_item">客户类型：</td>
		           <td >
						<select name="v_customer_type" id="v_customer_type" onchange="changeCustomType();">
		          		  	<option value="">-请选择-</option>
		          		  	<c:forEach var="cur" items="${p_CategoryList}">
		                	 <option value="${cur.map.c_comm}">${cur.map.c_comm}</option>
		                	</c:forEach>
	         		 	</select>
	         		 	<select name="v_customer_type2" id="v_customer_type2">
		          		  	<option value="">-请选择-</option>
		          		  	<c:forEach var="cur" items="${konkaCategoryList}">
		          		  	<option value="${cur.c_index}">[${cur.c_comm}] ${cur.c_name}</option>
		                	</c:forEach>
	         		 	</select>
				   </td>
		           <td width="15%" valign="middle" nowrap="nowrap" class="title_item">机型编码：</td>
		           <td width= "35%">
		             <html-el:text property="modelcode" styleId="modelcode" />
		           </td>
	         </tr>
	         <tr>
	         	   <td width="15%" valign="middle" nowrap="nowrap" class="title_item">数据维度(至少一列)：</td>
		           <td colspan="3">
			          <input type="checkbox" name="DEPT_NAME" id="a" value="f.DEPT_NAME" <c:if test="${af.map.DEPT_NAME eq 'f.DEPT_NAME'}">  checked="checked" </c:if>>
			          </input>分公司&nbsp;
			          
			          <input type="checkbox" name="L4_DEPT_NAME" id="b" value="f.L4_DEPT_NAME" 
			          	<c:if test="${af.map.L4_DEPT_NAME eq 'f.L4_DEPT_NAME'}">  checked="checked" </c:if>>
			          </input>经营部&nbsp;
			          
			          <input type="checkbox" name="L5_DEPT_NAME" id="c" value="f.L5_DEPT_NAME" 
			          	<c:if test="${af.map.L5_DEPT_NAME eq 'f.L5_DEPT_NAME'}">  checked="checked" </c:if>>
			          </input>办事处&nbsp;
			          
			          <input type="checkbox" name="COLUMN_1" id="d" value="f.COLUMN_1"
			           <c:if test="${af.map.COLUMN_1 eq 'f.COLUMN_1'}">  checked="checked" </c:if>>
			           </input>售达方&nbsp;
			           
			          <input type="checkbox" name="COLUMN_5" id="e" value="f.COLUMN_5"
			           	<c:if test="${af.map.COLUMN_5 eq 'f.COLUMN_5'}">  checked="checked" </c:if>>
			           </input>送达方&nbsp;
			           
			          <input type="checkbox" name="CUSTOMER_TYPE" id="f" value="f.CUSTOMER_TYPE , f.CUSTOMER_TYPE_NAME" 
			          	<c:if test="${af.map.CUSTOMER_TYPE eq 'f.CUSTOMER_TYPE'}">  checked="checked" </c:if>>
			          </input>客户类型&nbsp;
			          
			          <input type="checkbox" name="YWY_USER_NAME" id="g" value="f.YWY_USER_NAME" 
			          	<c:if test="${af.map.YWY_USER_NAME eq 'f.YWY_USER_NAME'}">  checked="checked" </c:if>>
			          </input>业务员&nbsp;
			          
			          <input type="checkbox" name="COLUMN_11" id="h" value="f.COLUMN_11" 
			          	<c:if test="${af.map.COLUMN_11 eq 'f.COLUMN_11'}">  checked="checked" </c:if>>
			          </input>型号
			          <span id="tiptext2" style="display: none"><font color="red">至少选择一列</font></span>
			          <span ></span>
			          <input type="submit" id="search_btn" class="but1" value="搜索"/>
				   </td>
	         </tr>
         </table>  
  </html-el:form>
  </div>
   <div >
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtab2" >
    <tr >
    <td align="left">机型  <br/>数量 <br/>  总金额(含税)</td>
    <td align="center">K007(含税) <br/> RB00(含税)  <br/> 数量(上年同期)</td>
    <td align="right" >总金额(含税)(上年同期)<br/>  增长率(数量)  <br/> 增长率(金额)</td>
    </tr>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
     <tr >
    <td align="left">${cur.column_11}  <br/>${cur.c_all_amount} <br/>  ${cur.c_all_money}</td>
    <td  align="center">${cur.c_all_k007} <br/>  ${cur.c_all_rb00} <br/> ${cur.p_all_amount}</td>
    <td align="right" >${cur.p_all_money}<br/>  ${cur.p_amount} %  <br/> ${cur.p_money} %</td>
    </tr>
    </c:forEach>
    </table>
    
  	<form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelCustomerBillingAnalysis2.do">
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("begindate", "${af.map.begindate}");
            pager.addHiddenInputs("enddate", "${af.map.enddate}");
            pager.addHiddenInputs("songdf", "${af.map.songdf}");
            pager.addHiddenInputs("r3code", "${af.map.r3code}");
            pager.addHiddenInputs("username", "${af.map.username}");
            pager.addHiddenInputs("customerName", "${af.map.customerName}");
            pager.addHiddenInputs("modelcode", "${af.map.modelcode}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
            pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
            pager.addHiddenInputs("v_c_comm", "${af.map.v_c_comm}");
            pager.addHiddenInputs("v_c_name", "${af.map.v_c_name}");
            pager.addHiddenInputs("selectedFields", "${af.map.selectedFields}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	//KonkaR3PdNameForCx
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	
	Array.prototype.unique = function(){
		this.sort();	//先排序
		var res = [this[0]];
		for(var i = 1; i < this.length; i++){
			if(this[i] !== res[res.length - 1]){
				res.push(this[i]);
			}
		}
		return res;
	};
	
	
	//alert("${af.map.FGS_NAME eq 'f.FGS_NAME'}");
	
	$("#a").attr("checked","checked");
	var selected = [];
	var selectedFields = "";
	$("#search_btn").click(function(){
		if($("#begindate").val()==""){
			$("#tiptext").attr("style","display:visible");
			return false ;
		}else{
			$("#tiptext").attr("style","display:none");
		}
		selectedFields="";
		selected = [];
		$("input['checkbox']:checked").each(function(){
				selected.push($(this).val());
		});
		selectedFields = selected.unique().join(",") ;
		if(selectedFields==""){
			$("#tiptext2").attr("style","display:visible");
			return false;
		}else{
			$("#tiptext2").attr("style","display:none");
		}
		$("#selectedFields").val(selectedFields);
		
	//	alert(selectedFields);
		//render();
		
	});
	
	//render();
});


function render(){
	if($("#a").attr("checked")){
		$("#ah").attr("style","display:visible");
		$("#al").attr("style","display:visible");
	}else{
		$("#ah").attr("style","display:none");
		$("#al").attr("style","display:none");
	}

	if($("#b").attr("checked")){
		$("#bh").attr("style","display:visible");
		$("#bl").attr("style","display:visible");
	}else{
		$("#bh").attr("style","display:none");
		$("#bl").attr("style","display:none");
	}

	if($("#c").attr("checked")){
		$("#ch").attr("style","display:visible");
		$("#cl").attr("style","display:visible");
	}else{
		$("#ch").attr("style","display:none");
		$("#cl").attr("style","display:none");
	}

	if($("#d").attr("checked")){
		$("#dh").attr("style","display:visible");
		$("#dl").attr("style","display:visible");
	}else{
		$("#dh").attr("style","display:none");
		$("#dl").attr("style","display:none");
	}

	if($("#e").attr("checked")){
		$("#eh").attr("style","display:visible");
		$("#el").attr("style","display:visible");
	}else{
		$("#eh").attr("style","display:none");
		$("#el").attr("style","display:none");
	}

	if($("#f").attr("checked")){
		$("#fh").attr("style","display:visible");
		$("#fl").attr("style","display:visible");
	}else{
		$("#fh").attr("style","display:none");
		$("#fl").attr("style","display:none");
	}

	if($("#g").attr("checked")){
		$("#gh").attr("style","display:visible");
		$("#gl").attr("style","display:visible");
	}else{
		$("#gh").attr("style","display:none");
		$("#gl").attr("style","display:none");
	}

	if($("#h").attr("checked")){
		$("#hh").attr("style","display:visible");
		$("#hl").attr("style","display:visible");
	}else{
		$("#hh").attr("style","display:none");
		$("#hl").attr("style","display:none");
	}
	
}


function  changeCustomType(){
	//取得客户大类
	var $customer_type = $("#v_customer_type").val();
	if($customer_type !=""){
		$.ajax({
			type: "post",
			url: "${ctx}/manager/admin/KonkaCategoryStatistics.do?method=getCategoryListByParent",
			data: {"v_customer_type" : $customer_type},
			dataType: "json",
			error: function(request, settings) {
				var html = "<option value=''>-请选择-</option>";
				$("#v_customer_type2").empty();
				$("#v_customer_type2").html(html);
				}, 
			success: function(result) {
				var html = "<option value=''>-请选择-</option>";
				for(var i = 0 ;i<result.length ;i++){
					if(result[i] == "${af.map.v_customer_type2}"){
						html += "<option selected='selected' value='"+result[i].c_index+"'>"+result[i].c_name+"</option>";
						}else{
							html += "<option value='"+result[i].c_index+"'>"+result[i].c_name+"</option>";
							}
				}
				$("#v_customer_type2").empty();
				$("#v_customer_type2").html(html);
			}
		});
	}else{
		var html = "<option value=''>-请选择-</option>";
		$("#v_customer_type2").empty();
		$("#v_customer_type2").html(html);
	}
}



//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
