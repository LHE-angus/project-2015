<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#div1 { width:100px; height:50px; position:relative; margin:0px auto 0px;}
#div1 img{width:100px; height:50px;}
#div1 span { width:10px; height:10px; background:red; left:0px;top:0px; position:absolute; display:none; filter:alpha(opacity:20); opacity:0.2;}
.show { width:100%; height:100%; background:white; position:absolute; z-index:9999px; filter:alpha(opacity:10); opacity:0.1; left:0px; top:0px; }
#div2 {width:300px; height:200px; position:absolute;z-index:9999px;; display:none; overflow:hidden; margin-left:100px;}
#img1 { position:absolute;}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：进销存 > 销售管理 > 零售数据 >发票清单详细</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobileSailDataBill">
			<html-el:hidden property="method" value="save_audit" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="id" styleId="id" value="${af.map.id}"/>
	      	<html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
        <td class="title_item" colspan="4" style="text-align:center;font-size:16px;font-weight:bold;color:#74685F">销售信息</td>
        </tr>
        <tr>
	    <td  align="center" nowrap="nowrap" class="title_item">上报门店</td>
	    <td  align="left" nowrap="nowrap" colspan="3">
	    	${entity.dept_name}
	    </td>
	     </tr>
	     <tr>
	    <td  align="center" nowrap="nowrap" width="20%" class="title_item">所属客户</td>
	    <td  align="left" nowrap="nowrap" width="30%" >
	    	${entity.customer_name}
	    </td>
	       <td  align="center" nowrap="nowrap" width="20%" class="title_item">上报人</td>
	    <td  align="left" nowrap="nowrap" width="30%" >
	    	${entity.report_name}
	    </td> 
	   </tr>
	    <tr>
	    <td  align="center" nowrap="nowrap" width="20%" class="title_item">型号</td>
	    <td  align="left" nowrap="nowrap" width="30%" >
	    	${entity.model_name}
	    </td>
	       <td  align="center" nowrap="nowrap" width="20%" class="title_item">数量</td>
	    <td  align="left" nowrap="nowrap" width="30%" >
	    	${entity.num}
	    </td> 
	   </tr>  
       <tr>
	    <td  align="center" nowrap="nowrap" class="title_item">单价</td>
	    <td  align="left" nowrap="nowrap">
	    	${entity.single_price}
	    </td>
	       <td  align="center" nowrap="nowrap" class="title_item">金额</td>
	    <td  align="left" nowrap="nowrap">
	    	${entity.all_price}
	    </td> 
	   </tr>  
	    <tr>
		<td colspan="4" style="text-align:left;font-size:14px;font-weight:bold;color:#74685F">顾客信息</td>
		</tr> 
       <tr>
	    <td  align="center" nowrap="nowrap" class="title_item">顾客姓名</td>
	    <td  align="left" nowrap="nowrap">
	    	${entity.realname}
	    </td>
	       <td  align="center" nowrap="nowrap" class="title_item">顾客电话</td>
	    <td  align="left" nowrap="nowrap">
	  ${entity.phonenum} 
	    </td> 
	   </tr> 
	   <tr>
	    <td  align="center" nowrap="nowrap" class="title_item">顾客身份证</td>
	    <td  align="left" nowrap="nowrap">
	    	${entity.mastercode}
	    </td>
	       <td  align="center" nowrap="nowrap" class="title_item">顾客地址</td>
	    <td  align="left" nowrap="nowrap">
	  ${entity.addresss} 
	    </td> 
	   </tr> 
	   <tr>
		<td colspan="4" style="text-align:left;font-size:14px;font-weight:bold;color:#74685F">
		发票信息
		</td>
		</tr> 
		<tr>
		<td colspan="4">
	      	<table width="90%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	      	<tr>
	      	<td align="center">附件</td>
	      	<td align="center">发票编号</td>
	      	<td align="center">申请金额</td>
	      	<td align="center" >初审金额</td>
	      	<td align="center" >终审金额</td>
	      	<td align="center">备注</td>
	      	<td align="center">是否参与提成核算</td>
	      	<td align="center">发票状态</td>
	      	</tr>
	      	<c:forEach items="${konkaMobileSailDataBillList}" var="cur" varStatus="vs">
	      	<tr>
	      	<td style="display:none;"><html-el:hidden property="bill_id" value="${cur.bill_id}" styleId="bill_id"/></td>
	      	<td align="center">
	      	<c:if test="${not empty cur.map.save_name}">
<!--	      	<img src="${ctx}/${cur.map.save_path}" style="width:300px;height:100px;"/>-->
	      	 <div id="div1">
	         <img src="${ctx}/${cur.map.save_path}" />
	         <span></span> </div>
	     <a href="${ctx}/MobileList.do?method=downloadFile1&save_name=${cur.map.save_name}" target="_blank">${cur.map.file_name}</a>
	      	</c:if>
	      	<c:if test="${empty cur.map.save_name}">
	      	未上传
	      	</c:if>
	      	</td>
	      	<td align="center">${cur.bill_no}</td>
	      	<td align="right">${cur.dec_money}</td>
	      	<td align="right">${cur.audit_money}</td>
	      	<td align="right">${cur.final_audit_money}</td>
	      	<td align="center">${cur.bill_mem}</td>
	      	<td align="center">
	      	<c:if test="${cur.is_valid_for_pay eq 0}">是</c:if>
	      	<c:if test="${cur.is_valid_for_pay eq 1}">否</c:if>
	      	</td>
	      	
	      	<td align="center">
	      	 <c:if test="${cur.state eq 0}">上传</c:if>
	        <c:if test="${cur.state eq 2}">初审合格</c:if>
	      	<c:if test="${cur.state eq 4}">初审不合格</c:if>
	      	<c:if test="${cur.state eq 6}">转单</c:if>
	      	<c:if test="${cur.state eq 8}">终审合格</c:if>
	      	<c:if test="${cur.state eq 10}">终审不合格</c:if>
	      </td>
	      	</tr>
	      	</c:forEach>
	      	</table>
		</td>
		</tr>
        <tr>
          <td colspan="7" align="center">
         
           <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />
            </td>
          
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript" src="${ctx}/manager/admin/KonkaMobileSailDataBill/out.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
		$(".audit_money").attr("datatype", "Require").attr("msg", "请填写初审提成金额").attr("focus",setOnlyNum);
		$(".audit_money").blur(function(){ 
            if(parseFloat(this.value)< 0 ){
         	$(this).val(0);
                }
			});
	
	$("#btn_submit").click(function(){
	var isSubmit = Validator.Validate(this.form, 3);
	if(isSubmit){
	this.form.submit();
		}
});
	function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter",function(){
			return false;
		});
		$(this).keypress(function (){
			if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
			if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
			if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
			if(this.value.length == 0) this.value = "0";
		});
		//this.text.selected;

}

});
//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
