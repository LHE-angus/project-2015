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
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：历史记录</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/admin/KonkaMobileTestDataHis">
  <html-el:hidden property="method" value="list" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <html-el:hidden property="user_id" value="${user_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">门店：</strong>
          <html-el:text property="dept_name_like" size="20" maxlength="20" styleId="dept_name_like" />
           &nbsp;&nbsp;<strong class="fb">型号：</strong>
          <html-el:text property="model_name_like" size="20" maxlength="20" styleId="model_name_like" />&nbsp;&nbsp;
          </td></tr>
        <tr>   
          <td nowrap="nowrap">&nbsp;&nbsp;
          <strong class="fb">上报时间：</strong>
          <html-el:text property="add_date_start" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          至
          <html-el:text property="add_date_end" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          &nbsp;&nbsp;&nbsp;&nbsp;
          <strong class="fb">状态：</strong>
          <html-el:select property="sj_state" onchange="this.form.submit();">
          	<html-el:option value="1">上样中</html-el:option> 
            <html-el:option value="2">已下样</html-el:option>
            <html-el:option value="3">全部</html-el:option>  
          </html-el:select>&nbsp;&nbsp;&nbsp;&nbsp;
          <input class="but1" type="submit" name="Submit" id="btn_submit" value="搜索" />&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
      </tr>
    </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
        <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" /> 
        </td>
      </tr>
    </table>
  </div> 
  <div class="rtabcont1" id="divExcel_all1" title="销售明细">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       <tbody>
          <tr class="tabtt1">
              <td width="5%" align="center" ><font class="blue">序号</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">上报人</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">门店</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">型号</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">串码</font></td>
              <td width="5%" nowrap="nowrap" align="center"><font class="blue">价格</font></td>
              <td width="5%" nowrap="nowrap"  align="center"><font class="blue">数量</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">上样时间</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">下样时间</font></td>
              <td width="10%" nowrap="nowrap" align="center"><font class="blue">上报时间</font></td>
              <td width="6%" nowrap="nowrap" align="center">状态</td>
              <td width="6%" nowrap="nowrap" align="center">类型</td>
         	  <td width="10%" nowrap="nowrap" align="center">操作</td>
          </tr>
          <c:set var="now">
      <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
    </c:set>
         <c:forEach var="cur" items="${entityList}" varStatus="vs">
           <tr>
                <td align="center"  nowrap="nowrap">${vs.count}</td>
                <td align="left"  nowrap="nowrap">${cur.report_name}</td>
                <td align="left"  nowrap="nowrap">${cur.dept_name}</td>
                <td align="left"  nowrap="nowrap">${cur.model_name}</td>
                <td align="left"  nowrap="nowrap">${cur.code}</td>
                <td align="left"  nowrap="nowrap"> <c:if test="${empty cur.price}">--</c:if>${cur.price}</td>   
                <td align="left"  nowrap="nowrap">${cur.num}</td>
                <td align="center"  nowrap="nowrap">
                <fmt:formatDate value="${cur.up_date}" pattern="yyyy-MM-dd" />
               </td>
                <td align="center"  nowrap="nowrap">
               <c:if test="${not empty cur.down_date}"><fmt:formatDate value="${cur.down_date}" pattern="yyyy-MM-dd" /></c:if>
               <c:if test="${empty cur.down_date}">--</c:if> 
                </td>
                <td align="center"  nowrap="nowrap">
                <fmt:formatDate value="${cur.report_date}" pattern="yyyy-MM-dd" />
               </td>
                <c:set var="downDate">
            	<fmt:formatDate value="${cur.down_date}" pattern="yyyy-MM-dd" />
              </c:set>
                <td align="right" nowrap="nowrap">
                <c:if test="${not empty downDate}">
          		 <c:if test="${not empty cur.map.up_self}"><span style="color:#009900;">上样中</span></c:if>
	             <c:if test="${empty cur.map.up_self}"><span style="color:#CD0000;">已下样</span></c:if>
          		</c:if>
          		<c:if test="${empty downDate}"> 
          			<c:if test="${not empty cur.map.up_self}">
          			<span style="color:#009900;">上样中</span>
          			</c:if>
          			<c:if test="${empty cur.map.up_self}"><span style="color:#CD0000;">已下样</span></c:if>   
          		</c:if>
          		</td>
          		 <td align="left"  nowrap="nowrap"><c:if test="${empty cur.plan_fp_id}">临时性上样</c:if>
          		 <c:if test="${not empty cur.plan_fp_id}">计划性上样</c:if>
          		 </td>
               <td align="center">
                  <c:if test="${not empty downDate}">
                  <c:if test="${not empty cur.map.up_self}">
               	  <span style="cursor: pointer;color: blue;" onclick="confirmUpdate(null, 'KonkaMobileTestDataHis.do', 'id=${cur.id}&mod_id=211511&' + $('#bottomPageForm').serialize())">修改</span>
              	  </c:if>
              	 <c:if test="${empty cur.map.up_self}">
              	    <span style="color:#CCC">修改</span>
              	  </c:if>
              	  </c:if>
              	  <c:if test="${empty downDate}">
              	  <span style="cursor: pointer;color: blue;" onclick="confirmUpdate(null, 'KonkaMobileTestDataHis.do', 'id=${cur.id}&mod_id=211511&' + $('#bottomPageForm').serialize())">修改</span>
          		</c:if>
               </td>
           </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
  </div> 
   <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileTestDataHis.do">
	       <input id='export_id' style="display:none"  name='excel_all' value='0' />
	      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td height="60" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
	            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("model_name_like", "${af.map.model_name_like}");
			            pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
			            pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
			            pager.addHiddenInputs("add_date_end","${af.map.add_date_end}");
			            pager.addHiddenInputs("sj_state","${af.map.sj_state}");
			            document.write(pager.toString());
			   </script></td>
	        </tr>
	      </table>
	    </form>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>-->
<!--<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>-->
<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>-->
<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> -->
<!--<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> -->
<!--<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>  -->

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//导出excel
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
		//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
