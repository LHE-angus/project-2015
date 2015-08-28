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
<!--新版-->
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
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
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaMobileCustVisit">
      <html-el:hidden property="method" value="listForCount" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
	      <td>
	         <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l4_dept_id" styleId="l4_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
<!--              <html-el:select property="l5_dept_id" styleId="l5_dept_id">-->
<!--                <html-el:option value="">-请选择-</html-el:option>-->
<!--              </html-el:select>-->
	      </td>
	      <td>
	         <strong class="fb">统计年月： </strong>
         <html-el:select property="year" styleId="year" style="width:90px;">
        	           <html-el:option value="">请选择</html-el:option> 
        	           <html-el:option value="2020">2020</html-el:option>
        	           <html-el:option value="2019">2019</html-el:option>
        	           <html-el:option value="2018">2018</html-el:option>
        	           <html-el:option value="2017">2017</html-el:option>
        	           <html-el:option value="2016">2016</html-el:option>
        	           <html-el:option value="2015">2015</html-el:option>
        	           <html-el:option value="2014">2014</html-el:option>
        	           <html-el:option value="2013">2013</html-el:option>
        	           <html-el:option value="2012">2012</html-el:option>
        	            <html-el:option value="2011">2011</html-el:option>
        	             <html-el:option value="2010">2010</html-el:option> 
        	             <html-el:option value="2009">2009</html-el:option>
        	         </html-el:select>年
        	  <html-el:select property="month" styleId="month" style="width:70px;">
        	        <html-el:option value="">请选择</html-el:option> 
        	       
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
        	         </html-el:select>月
	      </td>
	      <td>
	      <strong class="fb">上报人：</strong>
        	 <html-el:text property="user_name_like"></html-el:text>
	      </td>
      </tr>
      <tr>
	      <td>
	           拜访任务数：
	    	 <html-el:select property="jh_visit_count" styleId="jh_visit_count" style="width:90px;">
        	           <html-el:option value="">请选择</html-el:option> 
        	           <html-el:option value="1">有任务</html-el:option>
        	           <html-el:option value="0">无任务</html-el:option>
        	         </html-el:select>
	      </td>
	        <td>
	             角色：
	    	 <html-el:select property="role_id" styleId="role_id" style="width:90px;">
      	           <html-el:option value="">请选择</html-el:option> 
      	           <html-el:option value="40">经营部经理</html-el:option>
      	           <html-el:option value="50">办事处主任</html-el:option>
      	           <html-el:option value="60">业务员</html-el:option>
      	           <html-el:option value="69">业务主管</html-el:option>
        	</html-el:select>
	      </td>
      </tr>
        <tr>
          <td height="15" align="left" style="padding-left:5px;">
		     &nbsp;&nbsp;&nbsp;  
         &nbsp;&nbsp;&nbsp;	
        	 &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        	 &nbsp;&nbsp;&nbsp;
        	 <br />
        	 <input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
        	 <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />&nbsp;&nbsp;
      </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
		<%@ include file="/commons/pages/messages.jsp"%>	
 <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
  <tr style="bgcolor:orange;" >
    <td nowrap="nowrap" align="center" style="background-color: #FFD39B;"  rowspan="2" >序号</td>
    <td nowrap="nowrap" align="center"  style="background-color: #FFD39B;" rowspan="2">分公司</td>
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;"  rowspan="2">一级部门</td>
<!--    <td  nowrap="nowrap" align="center"  style="background-color: #FFD39B;" rowspan="2">二级部门</td>-->
    <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">上报人</td>
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访客户数</td>
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访终端数</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访次数</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="2">客户拜访</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="2">终端拜访</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;"  colspan="3">客户开拓</td>
    <td nowrap="nowrap" align="center"  rowspan="2">日期区间</td>
  </tr>
  <tr >
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >完成率</td>
     <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >完成率</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >完成率</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">正常</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">重拾</td>
        <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">正常</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">重拾</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">计划</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >实际</td>
    <td nowrap="nowrap" align="center"  style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">拜访</td>
  </tr>
   <c:forEach items="${entityList}" var="cur" varStatus="vs">
    <tr>
    <td nowrap="nowrap" align="center">${vs.count}</td>
   
    <td nowrap="nowrap" align="center">${cur.dept_name_one}</td>
    <td nowrap="nowrap" align="center">${cur.dept_name_two}</td>
<!--    <td nowrap="nowrap" align="center">${cur.dept_name_three}</td>-->
     <td nowrap="nowrap" align="center">${cur.user_name}</td>
    <td nowrap="nowrap" align="center">${cur.jh_visit_cust_count}</td>
    <td nowrap="nowrap" align="center">${cur.bf_cust_count}</td>
    <td nowrap="nowrap" align="center">
    <c:if test="${cur.jh_visit_cust_count ne 0}"><!--
    <fmt:formatNumber  type="percent"  value="${cur.bf_cust_count / cur.jh_visit_cust_count}" />
    -->
   
     <fmt:formatNumber value="${cur.custbfb*100}" pattern="#0"/>%
     <!-- 
    ${cur.custbfb}
    --></c:if>
      <c:if test="${cur.jh_visit_cust_count eq 0}">
      没做计划
    </c:if>
    </td>
    <td nowrap="nowrap" align="center">${cur.jh_visit_shop_count}</td>
     <td nowrap="nowrap" align="center">${cur.bf_shop_count}</td>
    <td nowrap="nowrap" align="center">
    <c:if test="${cur.jh_visit_shop_count ne 0}"><!--
    ${(cur.bf_shop_count / cur.jh_visit_shop_count)*100}%
    -->
    <fmt:formatNumber value="${cur.shopbfb*100}" pattern="#0"/>%
     <!-- 
    ${cur.shopbfb}
    --></c:if>
      <c:if test="${cur.jh_visit_shop_count eq 0}">
      没做计划
    </c:if>
    </td>
    
    <td nowrap="nowrap" align="center">${cur.jh_visit_count}</td>
    <td nowrap="nowrap" align="center">${cur.bf_count}</td>
    <td nowrap="nowrap" align="center">
    <c:if test="${cur.jh_visit_count ne 0}">
    
    <fmt:formatNumber value="${cur.bf_bfb*100}" pattern="#0"/>%
        <!--${(cur.bf_count / cur.jh_visit_count)*100}%-->
    </c:if>
     <c:if test="${cur.jh_visit_count eq 0}">
      没做计划
    </c:if>
     </td>
    <td nowrap="nowrap" align="center">${cur.zc_cust_visit_count}</td>
    <td nowrap="nowrap" align="center">${cur.cs_cust_visit_count}</td>
     <td nowrap="nowrap" align="center">${cur.zc_shop_visit_count}</td>
    <td nowrap="nowrap" align="center">${cur.cs_shop_visit_count}</td>
     <td nowrap="nowrap" align="center">${cur.jh_dev_cust_count}</td>
    <td nowrap="nowrap" align="center">${cur.kt_cust_count}</td>
    <td nowrap="nowrap" align="center">${cur.kt_cust_visit_count}</td>
    <c:if test="${not empty year_month}">
    <td nowrap="nowrap" align="center">${year_month}</td>
    </c:if>
    <c:if test="${empty year_month}">
     <td nowrap="nowrap" align="center">未指定区间</td>
   </c:if>
  </tr>
 </c:forEach>
     </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCustVisit.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
    <input id='synchronize' style="display:none"  name='synchronize' />
     <input id='update_year' style="display:none"  name='update_year' />
      <input id='update_month' style="display:none"  name='update_month' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "listForCount");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
								pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
								//pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
								pager.addHiddenInputs("year", "${af.map.year}");
								pager.addHiddenInputs("month", "${af.map.month}");
								pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
								pager.addHiddenInputs("jh_visit_count", "${af.map.jh_visit_count}");
								pager.addHiddenInputs("role_id", "${af.map.role_id}");
								pager.addHiddenInputs("isfirst", "${af.map.isfirst}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	//$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l4_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
});
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
	//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});

$("#button").click(function(){
	var year=$("#year").val(),month=$("#month").val();
	if(year==""||month==""){
		 alert("时间不能为空！");
         return false;
	}
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});
//同步最新数据查询
$("#synchronize_submit").click(function(){
	var year=$("#year").val(),month=$("#month").val();
	if(year==""||month==""){
		 alert("时间不能为空！");
        return false;
	}
	$("#update_year").val(year);
	$("#update_month").val(month);
	
	$("#synchronize").val('synchronize');
	$("#bottomPageForm").submit();
	$("#synchronize").val('');
	$("#update_year").val('');
	$("#update_month").val('');
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
