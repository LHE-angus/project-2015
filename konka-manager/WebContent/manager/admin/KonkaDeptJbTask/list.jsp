<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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

</head>
<body> 
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaDeptJbTask">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;
            &nbsp;&nbsp;<strong class="fb">客户R3编码：</strong>
            <html-el:text property="r3_code_like" styleId="r3_code_like" />
            &nbsp;&nbsp;
            <strong class="fb">经办名称：</strong>
            <html-el:text property="jb_name_like" styleId="jb_name_like" />
             &nbsp;&nbsp;
            <strong class="fb">时间：</strong>
            <html-el:text property="datetime" styleId="datetime" onfocus="selectMonth()" />
            &nbsp;&nbsp;<strong class="fb">分公司：</strong>
          	<html-el:select property="dept_name" styleId="dept_name">
      	  		<c:if test="${empty is_fgs}"><html-el:option value="">-请选择-</html-el:option></c:if>
      	  		<c:forEach items="${deptList}" var="cur">
      	  			<html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
      	  		</c:forEach>
      	  	</html-el:select>
            &nbsp;&nbsp;
            <input type="submit" name="" value="搜索" id="btn_submit" class="but1" />
            </td>
        </tr>
      </table>
    </html-el:form>
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='${ctx}/manager/admin/KonkaDeptJbTask.do?method=imp&mod_id=${af.map.mod_id}'" /></td>
        </tr>
      </table>
    </div>
    <div class="rtabcont1">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="5%" align="center">序号</td>
            <td align="center" width="8%" nowrap="nowrap">分公司</td>
            <td align="center" nowrap="nowrap" width="8%">ABC类</td>
            <td align="center" nowrap="nowrap" >经办名称</td>
            <td width="6%" nowrap="nowrap" align="center">年</td>
            <td width="6%" nowrap="nowrap" align="center">月</td>
            <td width="8%" nowrap="nowrap" align="center">任务系数</td>
            <td width="8%" nowrap="nowrap" align="center" >任务额</td>
            <td width="6%" nowrap="nowrap" align="center" >计数</td>
            <td width="12%" nowrap="nowrap" align="center" >客户R3编码</td>
            <td width="12%" nowrap="nowrap" align="center" >添加时间</td>
            <td width="10%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
              <td align="left" align="left">${fn:escapeXml(cur.dept_name)}</td>
              <td align="left" align="left">${fn:escapeXml(cur.jb_type_name)}</td>
              <td align="left" align="left">${fn:escapeXml(cur.jb_name)}</td>
              <td align="right" nowrap="nowrap">${cur.year}</td>
              <td align="right" nowrap="nowrap">${cur.month}</td>
              <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.task}" pattern="#0.00" /></td>
              <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.task_money}" pattern="#0.00" /></td>
              <td align="right" nowrap="nowrap">${cur.count}</td>
              <td align="center" nowrap="nowrap">${cur.r3_code}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd hh:mm"/></td>
           	  <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'KonkaDeptJbTask.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></td>	
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaDeptJbTask.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
            	var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
			            pager.addHiddenInputs("jb_name_like", "${af.map.jb_name_like}");
						pager.addHiddenInputs("datetime", "${af.map.datetime}");		
						pager.addHiddenInputs("dept_name", "${af.map.dept_name}");
				        document.write(pager.toString());
			   </script></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script> 
   function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyyMM', isShowToday: false, isShowClear: true });  
   }
   
   
   function loading(){
		jLoading("正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
	}
   $(function(){
	   $("#btn_submit").click(function(){
		   loading();
	   });
   });
   
   
</script> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>
