<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
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
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
      <html-el:form action="/admin/TaskContent">
      <html-el:hidden property="method" value="listYwyReport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="deptid" value="${af.map.deptid}" />
        <html-el:hidden property="current_fgs_code" styleId="current_fgs_code" value="${current_fgs_code}" />
      	<html-el:hidden property="current_dept_code" styleId="current_dept_code" value="${current_dept_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
	          <td align="right"><strong class="fb">分公司：</strong></td>
						<td><c:if test="${show_fgs and show_fgs_jb and show_fgs_gr}">
							<html-el:select property="dept_id" styleId="dept_id" disabled="${show_fgs}" style="width:90px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select>
						</c:if>
						<c:if test="${!(show_fgs and show_fgs_jb and show_fgs_gr)}">
						<html-el:select property="dept_id" styleId="dept_id" disabled="${show_fgs}" style="width:90px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select> <html-el:select property="l4_dept_id" disabled="${show_fgs_jb}" styleId="l4_dept_id" style="width:110px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select> <html-el:select property="l5_dept_id" styleId="l5_dept_id" style="width:110px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select>
							</c:if></td>
	          
	          
	          
	           <td width="15%" align="right" nowrap="nowrap" class="title_item">业务员：</td>
	           <td width= "35%">
	           	<html-el:text property="ywy_name" styleId="ywy_name"></html-el:text>
	           </td>
	         
         </tr>
         <tr>
         	   <td width="15%" align="right" nowrap="nowrap" class="title_item">年度：</td>
	           <td width= "35%">
	           	<html-el:text property="task_year" styleId="task_year" onkeyup="checkNum(this)"></html-el:text>
	           </td>
         	   <td width="15%" align="right" nowrap="nowrap" class="title_item">月份：</td>
         	   <td width= "35%">
	           	<html-el:text property="task_month" styleId="task_month" onkeyup="checkNum(this)"></html-el:text>
	           </td>
         </tr> 
         <tr>
         <td></td>
               <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
	           <td colspan="2"></td>
         </tr>
    </html-el:form>
  </div>
    
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
<!--     <table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!--       <tr> -->
<!--         <td> -->
<%-- 		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='TaskContent.do?method=add&deptid=${af.map.deptid }&mod_id=${af.map.mod_id}';" /> --%>
<!-- 			&nbsp;<input class="but_excel" type="button" name="Submit3" value="导入" onclick="gotoPage()" /> -->
<!-- 		</td> -->
<!-- 	 </tr> -->
<!-- 	</table> -->
  </div>
    
  <div class="rtabcont1" id="list_div">
    	
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
        <td></td>
          <td align="center" nowrap="nowrap" >分公司</td>	
          <td align="center" nowrap="nowrap" >经营处</td>
          <td align="center" nowrap="nowrap" >办事处</td>	
          <td align="center" nowrap="nowrap" >业务员</td>	
          <td align="center" nowrap="nowrap" >年度</td>	
          <td align="center" nowrap="nowrap" >月份</td>	
          <td align="center" nowrap="nowrap" >任务系数</td>	
          <td align="center" nowrap="nowrap" >任务额度</td>
          <td align="center" nowrap="nowrap" >实际结算额</td>
          <td align="center" nowrap="nowrap" >实际汇款额</td>
          <td align="center" nowrap="nowrap" >任务完成率</td>
          <td align="center" nowrap="nowrap" >排名</td>
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<tr>
				  <td></td>
		            <td align="center" nowrap="nowrap">${cur.map.dept_name }</td> 
		            <td align="center" nowrap="nowrap">${cur.map.l4_dept_name }</td> 
		            <td align="center" nowrap="nowrap">${cur.map.l5_dept_name }</td> 
		            <td align="center" nowrap="nowrap">${cur.map.ywy_name }</td>
		            <td align="center" nowrap="nowrap">${cur.task_year }</td>
		            <td align="center" nowrap="nowrap">${cur.task_month}</td>
		            <td align="center" nowrap="nowrap">${cur.task_xs}</td>
		            <td align="center" nowrap="nowrap">${cur.task_ed}</td>
		            <td align="center" nowrap="nowrap">${cur.map.cur_cls_money}</td>
		            <td align="center" nowrap="nowrap">${cur.map.cur_back_money}</td>
		            <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.map.complete_rate*100}" pattern="#00.00#"></fmt:formatNumber>%</td>
		            <td align="center" nowrap="nowrap">${cur.map.ywypm}</td>
		            
		        </tr>
		</c:forEach>
      </table>
     <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
	     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	       <tr>
	         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
	           <script type="text/javascript">
				  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
					  pager.addHiddenInputs("method", "listYwyReport");
					  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					  pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
					  pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
					  pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
					  pager.addHiddenInputs("ywy_name", "${af.map.ywy_name}");
					  pager.addHiddenInputs("task_year", "${af.map.task_year}");
					  pager.addHiddenInputs("task_month", "${af.map.task_month}");
					  document.write(pager.toString());
				</script>
			</td>
	       </tr> 
	     </table>
     </form>
  <div class="clear"></div>
</div>
	<div>
	<div id="error_div" class="rtabcont1" >
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" bgcolor="#FFFFCC">
	  		<tr align="center"><font color="red">以下excel导入数据有问题:</font></tr>
	        <tr class="tabtt1">
	          <td align="center" nowrap="nowrap" width="5%">序号</td>
	          <td align="center" nowrap="nowrap" >分公司</td>	
	          <td align="center" nowrap="nowrap" >岗位ID</td>
	          <td align="center" nowrap="nowrap" >业务员</td>	
	          <td align="center" nowrap="nowrap" >客户编码</td>	
	          <td align="center" nowrap="nowrap" >客户名称</td>
	          <td align="center" nowrap="nowrap" >任务类型</td>	
	          <td align="center" nowrap="nowrap" >年度</td>	
	          <td align="center" nowrap="nowrap" >月份</td>	
	          <td align="center" nowrap="nowrap" >任务系数</td>	
	          <td align="center" nowrap="nowrap" >任务额度</td>	
	          <td align="center" nowrap="nowrap" >错误描述</td>	
	        </tr>
			<c:forEach var="cur" items="${errorList}" varStatus="vs">
					<tr>
			        	<td align="center">${vs.count}</td>
			            <td align="center" nowrap="nowrap">${cur.deptsn }(${cur.deptname })</td> 
			            <td align="center" nowrap="nowrap">${cur.ywyid}</td>
			            <td align="center" nowrap="nowrap">${cur.ywyname }</td>
			            <td align="center" nowrap="nowrap">${cur.customercode }</td>
			            <td align="center" nowrap="nowrap">${cur.customername }</td>
			            <td align="center" nowrap="nowrap">${cur.tasktype }</td>
			            <td align="center" nowrap="nowrap">${cur.year}</td>
			            <td align="center" nowrap="nowrap">${cur.month}</td>
			            <td align="center" nowrap="nowrap">${cur.taskxs }</td>
			            <td align="center" nowrap="nowrap">${cur.tasked}</td>
			            <td align="center" nowrap="nowrap" style="color:red">${cur.desc}</td>
			        </tr>
			</c:forEach>
	      </table>
	  </div>
	</div>
  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		var errorSize = '${errorSize}';
		if(errorSize<1){
			$("#error_div").hide(500);
			$("#list_div").show(1000);
		}else{
			$("#list_div").hide(500);
			$("#error_div").show(1000);
		}
		
		
		$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
		$("#dept_id").change();

		//默认当前分公司
		var fgs = $("#current_fgs_code").val();
		var fgs_dept = $("#current_dept_code").val();
		if(fgs!=""){
			$("#dept_id").val(fgs);
			$("#dept_id").change();
		}
		if(fgs_dept!=""){
			$("#l4_dept_id").val(fgs_dept);
			$("#l4_dept_id").change();
		}
		
		
	}); 
	
	function checkNum(s){
  		var p = /^[0-9]*[1-9][0-9]*$/ ;
  		if(!p.test(s.value)){
  			s.value= '';
  			s.focus() ;
  		}
  	}
	var tt = Math.random();
	 function gotoPage(){
	    	doNeedMethod(null, 'TaskContent.do', 'add_excel','tt='+tt);
	  }
	
	
//]]>
</script>
</body>
</html>