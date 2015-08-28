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
    <html-el:form action="/admin/KonkaR3ShopDev">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="isfirst" value="first"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      
      <tr>
      <td align="right">分公司：</td>
      <td align="left">   <html-el:select property="subcomp_id" styleId="subcomp_id" >
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${deptList}" var="cur">
	    		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select></td>
      <td align="right">开始日期：</td>
      <td align="left"> <html-el:text property="begin_time" styleId="begin_time"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
      <td align="right">结束日期：</td>
      <td align="left"><html-el:text property="end_time" styleId="end_time"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
      <td align="right">开拓状态：</td>
      <td align="left"> <html-el:select property="dev_state" styleId="dev_state" style="width:80px;">
	               <html-el:option value="">请选择</html-el:option>
	      		   <html-el:option value="1">开拓中</html-el:option>
	      		   <html-el:option value="3">开拓成功</html-el:option>
	      		   <html-el:option value="2">已关闭</html-el:option>
        	 </html-el:select></td>
      <td></td>
      </tr>
      
      <tr>
      <td align="right">客户名称：</td>
      <td align="left"> <html-el:text property="cust_name_like"  maxlength="30"></html-el:text></td>
      <td align="right">上报人：</td>
      <td align="left"> <html-el:text property="report_nae_like"  maxlength="30"></html-el:text></td>
      <td align="right">联系人姓名：</td>
      <td align="left"> <html-el:text property="link_man_name_like"  maxlength="30"></html-el:text></td>
      <td align="right">状态：</td>
      <td align="left">  <html-el:select property="is_del" styleId="is_del" style="width:80px;">
		           <html-el:option value="">请选择</html-el:option>
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">删除</html-el:option>
        	 </html-el:select> </td>
      <td  align="center"> <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" /></td>
      </tr></table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					&nbsp;&nbsp;<input type="button" class="but2" value="新 增" onclick="location.href='KonkaR3ShopDev.do?method=add&mod_id=${af.map.mod_id}';" />
					<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="5%" nowrap="nowrap" align="left">分公司</td>
        <td nowrap="nowrap" align="left" >创建日期</td>
        <td nowrap="nowrap" align="left" >上报人</td>
        <!--<td nowrap="nowrap" align="center" >开拓开始日期</td>
        <td  nowrap="nowrap" align="center">开拓结束日期</td>
        --><td  nowrap="nowrap" align="left">客户名称</td>
        <td  nowrap="nowrap" align="left">联系人姓名</td>
        <td  nowrap="nowrap" align="left">联系人电话</td>
        <td  nowrap="nowrap" align="left">客户地址</td>
         <td  nowrap="nowrap" align="left">开拓状态</td>
       <td  nowrap="nowrap" align="left">附件</td>
         <td  nowrap="nowrap" align="left">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="left" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td><!--
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.begin_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.end_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
         -->
         <td align="left" nowrap="nowrap">${cur.map.user_name}</td> 
         <td align="left" nowrap="nowrap">
         <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopDev.do','view' ,'cust_id=${cur.cust_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')"> ${cur.cust_name}</span>
         </td> 
          <td align="left" nowrap="nowrap">
          <c:out value="${fn:substring(cur.link_man_name, 0, fn:length(cur.link_man_name))}" />
          </td> 
           <td align="left" nowrap="nowrap">
           <c:out value="${fn:substring(cur.link_man_tel, 0, fn:length(cur.link_man_tel))}" />
           </td> 
         
         <td align="left" nowrap="nowrap" title="${cur.link_man_addr}">
          <c:choose>  
			    <c:when test="${fn:length(cur.link_man_addr) > 10}">  
			        <c:out value="${fn:substring(cur.link_man_addr, 0, 10)}......" />  
			    </c:when>  
			   <c:otherwise>  
			      <c:out value="${cur.link_man_addr}" />  
			    </c:otherwise>  
			</c:choose> 
          </td>
          <td align="left" nowrap="nowrap">
	          <c:if test="${cur.dev_state eq 1}">开拓中</c:if>
	          <c:if test="${cur.dev_state eq 3}">开拓成功</c:if>
	          <c:if test="${cur.dev_state eq 2}">已关闭</c:if>
	       </td>
	       <td> 
	       <c:if test="${not empty cur.map.file_paths}">
           <c:set var="file_paths" value="${fn:split(cur.map.file_paths,',')}" />
          <c:forEach items="${file_paths}" var="tt" varStatus="vs1">
          <c:set var="num" value="${fn:length(tt)}" />
          	<a href="${ctx}/${tt}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          </c:forEach>
          </c:if>
	       </td>
	       <td align="left">
	       	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopDev.do','view' ,'cust_id=${cur.cust_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">查看</span>&nbsp;
	       	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopDev.do','edit' ,'cust_id=${cur.cust_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">修改</span>&nbsp;
	       	<c:if test="${cur.is_del eq 0}">
            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('确定要删除吗?', 'KonkaR3ShopDev.do','delete' ,'cust_id=${cur.cust_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">删除</span>&nbsp; 
          		</c:if>
           	<c:if test="${cur.is_del eq 1}">
            	<span style="cursor:pointer;color:grey;" >删除</span>&nbsp;
          	</c:if>
          	<c:if test="${cur.dev_state eq 3}">
          		<c:if test="${empty cur.is_submit}">
	          		<a class="fblue" href="${ctx}/manager/admin/CreateCustomer.do?method=toAdd&mod_id=101005&is_new=no&cust_id=${cur.cust_id}">提交审批</a>
          		</c:if>
          		<c:if test="${cur.is_submit eq 1}">
	          		<span style="color:grey;" >提交审批</span>
          		</c:if>
          	</c:if>
	       </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopDev.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("begin_time", "${af.map.begin_time}");
								pager.addHiddenInputs("end_time", "${af.map.end_time}");
								pager.addHiddenInputs("dev_state", "${af.map.dev_state}");
								pager.addHiddenInputs("is_del", "${af.map.is_del}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("cust_name_like", "${af.map.cust_name_like}");
								pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");
								pager.addHiddenInputs("report_nae_like", "${af.map.report_nae_like}");
								pager.addHiddenInputs("link_man_name_like", "${af.map.link_man_name_like}");
								pager.addHiddenInputs("_state", "${af.map._state}");
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
<script type="text/javascript">
$(document).ready(function(){


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
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
