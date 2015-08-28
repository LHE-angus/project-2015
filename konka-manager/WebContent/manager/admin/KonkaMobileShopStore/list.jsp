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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
	<html-el:form  action="/admin/KonkaMobileShopStore">
      	<html-el:hidden property="method" value="list" />
      	<html-el:hidden property="mod_id" styleId="mod_id"/>
     	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
     		<tr>
     			<td width="15"></td>
     			<td width="70" align="right"><strong class="fb">产品类别：</strong></td>
     			<td width="200" align="left"><html-el:text property="category_name" size="16" styleId="category_name" styleClass="webinput" /></td>
     			<td width="15"></td>
     			<td width="70" align="right"><strong class="fb">尺寸：</strong></td>
     			<td width="200" align="left"><html-el:text property="measure_name" size="16" styleId="measure_name" styleClass="webinput" /></td>
     			<td width="15"></td>
     			<td width="70" align="right"><strong class="fb">门店：</strong></td>
     			<td width="" align="left"><html-el:text property="dept_name" styleId="dept_name" size="16" styleClass="webinput" /></td>
     		</tr>
     		<tr>
          		<td width="15"></td>
          		<td width="70" align="right"><strong class="fb">型号：</strong></td>
          		<td width="200" align="left"><html-el:text property="model_name" size="16" styleId="model_name" styleClass="webinput" /></td>
          		<td width="15"></td>
          		<td width="70" align="right"><strong class="fb">存量：</strong></td>
          		<td width="200" align="left"><html-el:text property="num" size="16" styleId="num" styleClass="webinput" /></td>
          		<td width="15"></td>
          		<td width="70" align="right"><strong class="fb">上报时间：</strong></td>
          		<td width="" align="left">
          			<html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          			-
    				<html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          		</td>
          	</tr>
          	<tr>
          		<td width="15"></td>
          		<c:if test="${not empty ywyList}">
	          		<td width="70" align="right"><strong class="fb">业务员：</strong></td>
	          		<td align="left">
	          			<html-el:select property="report_id" styleId="report_id">
	          				<html-el:option value="">=请选择=</html-el:option>
	          				<c:forEach items="${ywyList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.user_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>
	          		</td>
	          		<td width="15"></td>
          		</c:if>
          		<c:if test="${not empty ywyList}"><c:set value="5" var="col" /></c:if>
          		<c:if test="${empty ywyList}"><c:set value="8" var="col" /></c:if>
          		<td colspan="${col}"><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
          	</tr>
     	</table>
	</html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="rtabcont1">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="30" nowrap="nowrap" align="center">序号</td>
	    	<td nowrap="nowrap" align="center">物料编码</td>
	    	<td nowrap="nowrap" align="center">渠道A名称</td>
	    	<td nowrap="nowrap" align="center">渠道B名称</td>
	    	<td nowrap="nowrap" align="center">机构名称</td>
	    	<td nowrap="nowrap" align="center">门店名称</td>
	    	<td nowrap="nowrap" align="center">分公司名称</td>
	    	<td nowrap="nowrap" align="center">办事处名称</td>
	    	<td nowrap="nowrap" align="center">尺寸名称</td>
	    	<td nowrap="nowrap" align="center">样机名称</td>
	    	<td nowrap="nowrap" align="center">产品类别名称</td>
	    	<td nowrap="nowrap" align="center">产品型号名称</td>
	    	<td nowrap="nowrap" align="center">数量</td>
	    	<td nowrap="nowrap" align="center">上报人名称</td>
	    	<td nowrap="nowrap" align="center">上报时间</td>
	    	<td nowrap="nowrap" align="center">数据状态</td>
	    </tr>
	  	<c:forEach var="cur" items="${entityList}" varStatus="vs">
    	<tr>
    		<td align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
    		<td>${cur.pct_code}</td>
    		<td>${cur.channel_a_name}</td>
    		<td>${cur.channel_b_name}</td>
    		<td>${cur.org_name}</td>
    		<td>${cur.dept_name}</td>
    		<td>${cur.subcomp_name}</td>
    		<td>${cur.office_name}</td>
    		<td>${cur.measure_name}</td>
    		<td>${cur.prototype_name}</td>
    		<td>${cur.category_name}</td>
    		<td>${cur.model_name}</td>
    		<td>${cur.num}</td>
    		<td>${cur.report_name}</td>
    		<td><fmt:formatDate value="${cur.report_date}" pattern="yyyy-MM-dd HH:mm" /></td>
    		<td>${cur.status}</td>
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
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	  	</c:forEach>
	  </table>
  	  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileShopStore.do">
		 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			 <tr>
				<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("c_name", "${af.map.c_name}");
						pager.addHiddenInputs("category_name", "${af.map.category_name}");
						pager.addHiddenInputs("measure_name", "${af.map.measure_name}");
						pager.addHiddenInputs("dept_name", "${af.map.dept_name}");
						pager.addHiddenInputs("model_name", "${af.map.model_name}");
						pager.addHiddenInputs("num", "${af.map.num}");
						document.write(pager.toString());
					</script>
				</td>
			</tr>
		 </table>
	  </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	subcomp_id_chg();
	
	var queryForm = document.forms[0];
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  
  
  
  
  