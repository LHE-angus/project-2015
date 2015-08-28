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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
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
 <html-el:form action="/admin/KonkaMobileVisitPlan">
 <div class="rtabcont1">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td align="right"><strong class="fb">计划月份：</strong></td>
          <td>
              <html-el:select property="begin_year" styleId="begin_year" style="width:65px">
                   <html-el:option value="">-年份-</html-el:option>
                   <c:forEach begin="2014" end='3020' varStatus="vs" step="1">
		      		   <html-el:option value="${vs.current }">${vs.current }年</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
	    	  <html-el:select property="begin_month" styleId="begin_month" style="width:60px">
	    	       <html-el:option value="">-月份-</html-el:option>
	    	       <c:forEach begin="1" end='12' varStatus="vs" step="1">
		       	       <html-el:option value="${vs.current }">${vs.current }月</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
	    	  &nbsp;至&nbsp;
	    	  <html-el:select property="end_year" styleId="end_year" style="width:65px">
                   <html-el:option value="">-年份-</html-el:option>
                   <c:forEach begin="2014" end='3020' varStatus="vs" step="1">
		      		   <html-el:option value="${vs.current }">${vs.current }年</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
              <html-el:select property="end_month" styleId="end_month" style="width:60px">
	    	       <html-el:option value="">-月份-</html-el:option>
	    	       <c:forEach begin="1" end='12' varStatus="vs" step="1">
		       	       <html-el:option value="${vs.current }">${vs.current }月</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
	      </td>
	      <td align="right"><strong class="fb">分&nbsp;公&nbsp;司：</strong></td>
	      <td >
	          <html-el:select property="subcomp_name_like" styleId="subcomp_name_like" >
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${deptList}" var="cur">
	    		<html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
<!--	    	 <html-el:text property="subcomp_name_like" styleId="subcomp_name_like" maxlength="25" size="26"/>-->
	      </td>
	      
	       <td align="right"><strong class="fb">状态：</strong></td>
	      <td >
	    	  <html-el:select property="is_del" styleId="is_del" style="width:220px" value="${af.map.is_del}">
		    	   <html-el:option value="">-请选择-</html-el:option>
		    		  <html-el:option value="0">正常</html-el:option>
		    		   <html-el:option value="1">删除</html-el:option>
		      </html-el:select>
	      </td>
      </tr>
      <tr>
	  	  <td align="right"><strong class="fb">上&nbsp;报&nbsp;人：</strong></td>
	  	  <td>
	    	  <html-el:text property="report_nae_like" styleId="report_nae_like" maxlength="43"/>
	      </td>
	      <td align="right"><strong class="fb">拜访客户：</strong></td>
		  <td>
		  	  <html-el:select property="r3_code" styleId="r3_code" style="width:165px">
		    	   <html-el:option value="">-请选择-</html-el:option>
		    		<c:forEach items="${custList}" var="cur">
		    		  <html-el:option value="${cur.map.customer_code}">${cur.map.customer_name}</html-el:option>
		    		</c:forEach>
		      </html-el:select>
		  </td>
	      <td align="right"><strong class="fb">拜访终端：</strong></td>
	      <td>
		     <html-el:select property="shop_id" styleId="shop_id" >
	    	  <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${storeList}" var="cur">
	    		<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	    		</c:forEach>
	    	 </html-el:select>
	      </td>
        </tr>
        <tr>
           <td align="right"> 数据来源：</td>
        <td align="left">
           <html-el:select property="data_source" styleId="data_source" style="width:165px" value="${af.map.data_source}">
		    	   <html-el:option value="">-请选择-</html-el:option>
		    		  <html-el:option value="1">手机端</html-el:option>
		    		   <html-el:option value="2">web端</html-el:option>
		      </html-el:select>
        </td>
        <td colspan="3"></td>
        <td  align="left">  
        <input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        </tr>
      </table>
  </div>
  </html-el:form>
    <div style="text-align: left;padding-left: 10px">
  <input type="button" class="but2" value="新 增" onclick="location.href='KonkaMobileVisitPlan.do?method=add&mod_id=${af.map.mod_id}';" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
  </div>
  <div class="rtabcont1">
	  <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >计划月份</td>
        <td  nowrap="nowrap" align="left">分公司</td>
        <td nowrap="nowrap" align="left" title="业务员JOBID">上报人</td>
        <td nowrap="nowrap" align="right">计划拜访次数</td>
        <td nowrap="nowrap" align="right" >计划拜访客户数</td>
        <td nowrap="nowrap" align="right" >计划拜访终端数</td>
        <td nowrap="nowrap" align="right" >计划开拓数</td>
        <td  nowrap="nowrap" width="150px;" align="left">拜访客户</td>
        <td nowrap="nowrap" width="150px;" align="left">拜访终端</td>
        <td nowrap="nowrap" align="left" >计划说明</td>
        <td nowrap="nowrap" align="left" >创建时间</td>
         <td nowrap="nowrap" align="left" >最后更新日期</td>
        <td nowrap="nowrap" align="left">状态</td>
        <td nowrap="nowrap" align="left">数据来源</td>
        <td  nowrap="nowrap" align="left">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
         <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="center" nowrap="nowrap">
          	<fmt:formatDate value="${cur.plan_begin_date}" pattern="yyyy-MM"></fmt:formatDate>
          </td>
          <td nowrap="nowrap" align="left">${cur.map.subcomp_name}</td>
          <td nowrap="nowrap" align="left">${cur.report_nae}</td>
          <td nowrap="nowrap" align="right">${cur.plan_of_access}</td>
          <td nowrap="nowrap" align="right">${cur.plan_of_access_cust}</td>
          <td nowrap="nowrap" align="right">${cur.plan_of_access_shop}</td>
          <td nowrap="nowrap" align="right">${cur.plan_of_dev_cust}</td>
          <td nowrap="nowrap" align="left" title="${cur.map.customer_names}">
             <c:choose>  
			    <c:when test="${fn:length(cur.map.customer_names) > 10}">  
			        <c:out value="${fn:substring(cur.map.customer_names, 0, 10)}......" />  
			    </c:when>  
			   <c:otherwise>  
			      <c:out value="${cur.map.customer_names}" />  
			    </c:otherwise>
			</c:choose> 
          </td>
          <td nowrap="nowrap" align="left"  title="${cur.map.shop_names}">
            <c:choose>  
			    <c:when test="${fn:length(cur.map.shop_names) > 10}">  
			        <c:out value="${fn:substring(cur.map.shop_names, 0, 10)}......" />  
			    </c:when>  
			   <c:otherwise>  
			      <c:out value="${cur.map.shop_names}" />  
			    </c:otherwise>  
			</c:choose> 
          </td>
          <td nowrap="nowrap" align="left" title="${cur.plan_desc}">
            <c:choose>  
			    <c:when test="${fn:length(cur.plan_desc) > 10}">  
			        <c:out value="${fn:substring(cur.plan_desc, 0, 10)}......" />  
			    </c:when>  
			   <c:otherwise>  
			      <c:out value="${cur.plan_desc}" />  
			    </c:otherwise>  
			</c:choose> 
           </td>
           <td align="left" nowrap="nowrap">
            	<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
           </td>
            <td align="left" nowrap="nowrap">
            	<fmt:formatDate value="${cur.update_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
           </td>
           <td nowrap="nowrap" align="left">
             <c:if test="${cur.is_del eq 0}">正常</c:if>
             <c:if test="${cur.is_del eq 1}">删除</c:if>
           </td>
           <td nowrap="nowrap" align="left">
             <c:if test="${cur.data_source eq 0}">手机端</c:if>
	      	 <c:if test="${cur.data_source eq 1}">手机端</c:if>
             <c:if test="${cur.data_source eq 2}">web端</c:if>
             <c:if test="${cur.data_source eq 3}">手机端</c:if>
           </td>
           <td align="left" nowrap="nowrap"> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileVisitPlan.do','view' ,'plan_id=${cur.plan_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">查看</span>
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileVisitPlan.do','edit' ,'plan_id=${cur.plan_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">修改</span>
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'KonkaMobileVisitPlan.do','delete' ,'plan_id=${cur.plan_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">删除</span> 
           </td> 
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileVisitPlan.do">
      <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("begin_year", "${af.map.begin_year}");
								pager.addHiddenInputs("end_year", "${af.map.end_year}");
								pager.addHiddenInputs("begin_month", "${af.map.begin_month}");
								pager.addHiddenInputs("end_month", "${af.map.end_month}");
								pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
								pager.addHiddenInputs("shop_id", "${af.map.shop_id}");
								pager.addHiddenInputs("report_nae_like", "${af.map.report_nae_like}");
								pager.addHiddenInputs("subcomp_name_like", "${af.map.subcomp_name_like}");
								pager.addHiddenInputs("isfirst", "${af.map.isfirst}");
								pager.addHiddenInputs("is_del", "${af.map.is_del}");
								pager.addHiddenInputs("data_source", "${af.map.data_source}");
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
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
$("#shop_id").attr("Require","false").attr("dataType", "Number").attr("msg", "终端名称必须填写数字");
$("#begin_month").attr("dataType","Require").attr("msg", "月份不能为空");
$("#end_month").attr("dataType","Require").attr("msg","月份不能为空 ");
$("#button").click(function(){
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});

$("#r3_code").multiselect({
	noneSelectedText: '==请选择==',
	selectedList: 1,
	multiple: false,
	minWidth:150,
	click: function(event, ui){
       if(ui.value != ""){
    	   $("#r3_code").val(ui.value);
       }
	}
}).multiselectfilter();

$("#shop_id").multiselect({
	noneSelectedText: '==请选择==',
	selectedList: 1,
	multiple: false,
	minWidth:150,
	click: function(event, ui){
       if(ui.value != ""){
    	   $("#shop_id").val(ui.value);
       }
	}
}).multiselectfilter();

});

//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		//CNZZ统计代码
	//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
