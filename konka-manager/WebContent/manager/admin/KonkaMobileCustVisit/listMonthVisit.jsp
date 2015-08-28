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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
.rtable1 td {
			padding:2px 5px;
		}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
   <html-el:form action="/admin/KonkaMobileCustVisit">
   <div class="rtabcont1">
      <html-el:hidden property="method" value="listMonthVisitReport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td align="right"><strong class="fb">报表类型：</strong></td>
          <td>
              <html-el:select property="is_cust_or_shop" styleId="is_cust_or_shop" style="width:80px">
                <html-el:option value="cust">客&nbsp;&nbsp;&nbsp;户</html-el:option>
                <html-el:option value="shop">终&nbsp;&nbsp;&nbsp;端</html-el:option>
              </html-el:select>
          </td>
          <td align="right"><strong class="fb">分&nbsp;公&nbsp;司：</strong></td>
          <td>
              <html-el:select property="dept_id_fgs" styleId="dept_id_fgs">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l4_dept_id" styleId="l4_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <!-- <html-el:select property="l5_dept_id" styleId="l5_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select> -->
          </td>
          <td align="right"><strong class="fb">时&nbsp;&nbsp;&nbsp;&nbsp;间：</strong></td>
          <td>
              <html-el:select property="year" styleId="year"  value="${af.map.year}">
                   <html-el:option value="">-请选择-</html-el:option>
                   <c:forEach begin='2014' end='2020' varStatus="vs" step="1">
		      		   <html-el:option value="${vs.current }">${vs.current }</html-el:option>
                   </c:forEach>
	    	</html-el:select>年
	    	<html-el:select property="month" styleId="month" value="${af.map.month}">
	    	   <html-el:option value="">-请选择-</html-el:option>
	    	    <c:forEach begin='1' end='12' varStatus="vs" step="1">
		      		<html-el:option value="${vs.current }">${vs.current }</html-el:option>
                </c:forEach>
	    	</html-el:select>月	
	      </td>
        </tr>
        <tr>
          <td align="right"><strong class="fb">上&nbsp;报&nbsp;人：</strong></td>
          <td>
        	  <html-el:text property="peport_name_like" style="width:80px" maxlength="20"></html-el:text>
          </td>
          <td align="right">
                 客户编码
        </td>
        <td>
        <html-el:text property="r3_code_like" maxlength="25"></html-el:text>
        </td>
          <td id="shop1" style="display: none;" align="right"><strong class="fb">门&nbsp;&nbsp;&nbsp;&nbsp;店：</strong></td>
          <td id="shop1_text" style="display: none;">
		      <html-el:text property="shop_name_like" style="width:80px" maxlength="30"></html-el:text>
		  </td>
		  <td align="right" ><strong class="fb">客户类型：</strong></td>
       		<td align="left">
       			<html-el:select property="par_customer_type" styleId="par_customer_type" style="width:140px" value="${af.map.par_customer_type}">
              		<html-el:option value="">－请选择－</html-el:option>
              		<c:forEach var="cur" items="${konkaCategoryList}">
                		<html-el:option value="${cur.PAR_INDEX}">${cur.C_COMM}</html-el:option>
              		</c:forEach>
            	</html-el:select>
        	</td>
        </tr>
        <tr>
         <td id="cust1" align="right"><strong class="fb">客户名称：</strong></td>
          <td id="cust1_text">
          	  <html-el:text property="customer_name_like" style="width:80px" maxlength="30"></html-el:text>
          </td>
        </tr>
      </table>
  </div>
  <div style="text-align: right;padding-right: 15px">
  	<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;margin-left: 20px" />&nbsp;&nbsp;
    <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
  </div>
  </html-el:form>
  <div class="rtabcont1" style="overflow-x:auto;">
		<%@ include file="/commons/pages/messages.jsp"%>	
 <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
  <tr style="bgcolor:orange;" >
    <td nowrap="nowrap" align="center" style="background-color: #FFD39B;"   >序号</td>
    <td nowrap="nowrap" align="center"  style="background-color: #FFD39B;"  >分公司</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;"  >一级部门</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;"  >上报人</td>
    
    
    <c:if test="${af.map.is_cust_or_shop eq 'cust'}">
	    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >客户R3编码</td>
	     <td  nowrap="nowrap" align="left" style="background-color: #FFD39B;" >客户名称</td>
	</c:if>
    <c:if test="${af.map.is_cust_or_shop eq 'shop'}">
	    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >终端ID</td>
	    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" >终端名称</td>
    </c:if>
    
    <td  nowrap="nowrap" align="center"  style="background-color: #FFD39B;" >客户类型</td>
    
    <c:forEach begin='1' end='31' varStatus="vs" step="1">
	    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >${vs.current }日</td>
    </c:forEach>
  </tr>
  
   <c:forEach items="${entityList}" var="cur" varStatus="vs">
    <tr>
    <td nowrap="nowrap" align="center">${vs.count}</td>
    <td nowrap="nowrap" align="center">${cur.map.dept_name_fgs}</td>
    <td nowrap="nowrap" align="center">${cur.map.l4_dept_name}</td>
    <td nowrap="nowrap" align="center">${cur.map.report_nae}</td>
    
    
    <c:if test="${af.map.is_cust_or_shop eq 'cust'}">
         <td nowrap="nowrap" align="center">${cur.map.r3_code}</td>
           <td nowrap="nowrap" align="left">${cur.map.customer_name}</td>
    </c:if>
    <c:if test="${af.map.is_cust_or_shop eq 'shop'}">
	    <td nowrap="nowrap" align="center">${cur.map.shop_id}</td>
        <td nowrap="nowrap" align="center">${cur.map.shop_name}</td>
    </c:if>
    
    <td nowrap="nowrap" align="center">${cur.map.par_customer_type_name}</td>
    
    
	<td nowrap="nowrap" align="center">${cur.map.one_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.two_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.three_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.four_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.five_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.six_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.seven_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.eight_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.nine_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.ten_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.eleven_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twelve_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.thirteen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.fourteen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.fifteen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.sixteen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.seventeen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.eighteen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.nineteen_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_one_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_two_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_three_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_four_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_five_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_six_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_seven_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_eight_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.twenty_nine_da}</td>
	<td nowrap="nowrap" align="center">${cur.map.thirty_da}</td>
    <td nowrap="nowrap" align="center">${cur.map.thirty_one_da}</td>                               
  </tr>
 </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCustVisit.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "listMonthVisitReport");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("dept_id_fgs", "${af.map.dept_id_fgs}");
								pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
								//pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
								pager.addHiddenInputs("peport_name_like", "${af.map.peport_name_like}");
								pager.addHiddenInputs("is_cust_or_shop", "${af.map.is_cust_or_shop}");
								pager.addHiddenInputs("year", "${af.map.year}");
								pager.addHiddenInputs("month", "${af.map.month}");
								pager.addHiddenInputs("isfirst", "${af.map.isfirst}");
								pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
								pager.addHiddenInputs("shop_name_like", "${af.map.shop_name_like}");
								pager.addHiddenInputs("par_customer_type", "${af.map.par_customer_type}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	$("#dept_id_fgs").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id_fgs}"});
	$("#l4_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	//$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	//$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id_fgs").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id_fgs").change();
});

$("#is_cust_or_shop").change(function(){
	var cust_or_shop=$("#is_cust_or_shop").val();
	if(cust_or_shop=='cust'){
		$("#cust1").show();
		$("#cust1_text").show();
		$("#shop1").hide();
		$("#shop1_text").hide();
	}else if(cust_or_shop=='shop'){
		$("#shop1").show();
		$("#shop1_text").show();
		$("#cust1").hide();
		$("#cust1_text").hide();
	}
});

$("#button").click(function(){
	var year=$("#year").val();
	var month=$("#month").val();
	if(year!=""&&month==""){alert("请选择月");return false;}
	if(month!=""&&year==""){alert("请选择年");return false;}
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
