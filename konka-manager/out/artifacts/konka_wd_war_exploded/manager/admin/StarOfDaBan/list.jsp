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
<body >
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" >
    <html-el:form action="/admin/StarOfDaBan.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="list"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
        <tr>
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">分公司：</td>
		   	<td>
		   		<html-el:select property="dept_id" styleId="dept_id" style="width:100px;">
                	<html-el:option value="">-请选择-</html-el:option>
              	</html-el:select>
		   	</td>
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
        		<strong class="fb">门店ID：</strong>
        	</td>
        	<td>
	            <html-el:text property="store_id" styleId="store_id" maxlength="18" />
        	</td>
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
        		<strong class="fb">门店名称：</strong>
        	</td>
        	<td>
	            <html-el:text property="store_name" styleId="store_name" maxlength="20"/>
        	</td>
		</tr>
		<tr>
			<td valign="middle" nowrap="nowrap" class="title_item" align="right">门店类型：</td>
		    <td >
				<html-el:select property="store_type" styleId="store_type" >
				<html-el:option value="">-请选择-</html-el:option>
                <html-el:option value="A">A类店</html-el:option>
                <html-el:option value="B">B类店</html-el:option>
                <html-el:option value="C">C类店</html-el:option>
              </html-el:select>
		   	</td>
		   	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
		   		数据类别：
		   	</td>
		   	<td>
		   		<html-el:select property="data_type" styleId="data_type" style="width:100px;">
	                 <html-el:option value="49">49寸及以上</html-el:option>
	                 <html-el:option value="YTV">易TV</html-el:option>
	                 <html-el:option value="">全部</html-el:option>
	            </html-el:select>
		   	</td>
		   	<td valign="middle" nowrap="nowrap" class="title_item" align="right">促销员：</td>
		   	<td>
		   		<html-el:text property="cxy_name" styleId="cxy_name" maxlength="20"/>
		   	</td>
        </tr>
        <tr>
        	<td align="right"><strong class="fb">时间：</strong></td>
          	<td>
          		<html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            	-
            	<html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
           	</td>
           	<td align="right" valign="top"><strong class="fb">型号：</strong></td>
           	<td>
		   		<html-el:text property="model_name_like" styleId="model_name_like" />
		   	</td>
        	<td align="right">
            	<input class="but1" type="submit" name="Submit" value="搜索" />
            </td>
            <td style="padding-left:20px">
   				<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" /> 
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1" style="font-weight:700;color:#F00;">
  	零售额：<fmt:formatNumber value="${all_price}" pattern="#,##0.00"/>&nbsp;元,零售量：<fmt:formatNumber value="${all_num}" pattern="#,###"/>&nbsp;台
  </div>
  <div class="rtabcont1" style="font-weight:normal;color:#A8A8A8;">注意：查询数据范围以您被授权查看数据的部门为基础. </div>
  <div class="rtabcont1" style="overflow-x: auto;" id="divExcel_all">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr>
  			<td width="5%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">排名</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">分公司</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店ID</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店名称</td>  
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">门店类型</td>
        	<td width="3%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">促销员</td>
        	<td width="6%" nowrap="nowrap" align="center" style="background-color: #97FFFF;">销售量</td>  
        	<td width="4%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">销售额</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">省</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">市</td>
        	<td align="center" nowrap="nowrap" style="background-color: #97FFFF;">县</td>
        	<td width="6%" align="center" nowrap="nowrap" style="background-color: #97FFFF;">镇</td>
  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${cur.STORE_RANK}</td>
  				<td align="left" nowrap="nowrap">${cur.SUBCOMP_NAME}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_ID}</td>
  				<td align="left" nowrap="nowrap">${cur.DEPT_NAME}</td>  	
  				<td align="left" nowrap="nowrap">
  					<c:if test="${not empty cur.STORE_TYPE}">${cur.STORE_TYPE}类店</c:if>
  				</td>
  				<td align="left" nowrap="nowrap">${cur.CXY_NAME }</td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ALL_PRICE}" pattern="#,##0.00"/></td>
  				<td align="left" nowrap="nowrap">${cur.PROVINCE}</td>
  				<td align="left" nowrap="nowrap">${cur.CITY}</td>  	
  				<td align="left" nowrap="nowrap">${cur.COUNTRY}</td>
  				<td align="left" nowrap="nowrap">${cur.TOWN}</td>
  			</tr>
  		</c:forEach>
  	</table>
  	<br />
  </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="StarOfDaBan.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");	
            pager.addHiddenInputs("dept_id","${af.map.dept_id}");	
            pager.addHiddenInputs("store_id","${af.map.store_id}");	
            pager.addHiddenInputs("store_name","${af.map.store_name}");	
            pager.addHiddenInputs("store_type","${af.map.store_type}");	
            pager.addHiddenInputs("data_type","${af.map.data_type}");	
            pager.addHiddenInputs("cxy_name","${af.map.cxy_name}");	
            pager.addHiddenInputs("date_begin","${af.map.date_begin}");	
            pager.addHiddenInputs("date_end","${af.map.date_end}");	
            pager.addHiddenInputs("model_name_like","${af.map.model_name_like}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		$("#store_id").atter("focus",setOnlyNum);
		var is_kh = '${af.map.is_kh}';
		if(is_kh == 1){
			$("#r3_code").attr("disabled","disabled");
			$("#is_kh").append("<input type='hidden' name='r3_code' value='"+'${af.map.r3_code}'+"' />");
			$("#r3moneyTb").hide();
		}
		
		var f = document.forms["af"];
		String.prototype.isPath=function() { return /[^\n\r\t]*\.[x|X][l|L][s|S]/i.test(this); };
	
	
		//导出
		$("#export_excel").click(function(){
			if(confirm("提示，您确认导出数据？")){
				$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
	    		$("#bottomPageForm").submit();
	    		$("#excel_to_all").val("");	
			}
		});

		//初始化分公司列表
		$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	
		//客户类型初始化
		//$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
		//$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
	
		//$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
		//$("#v_customer_type1").change();
	});
	function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter",function(){
			return false;
		});
		$(this).keypress(function (){
			if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).keyup(function (){
			if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
		}).blur(function (){
			if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^$/))this.value=0;this.o_value=this.value};
			if(this.value.length == 0) this.value = "0";
		});
		//this.text.selected;
	}
	
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
