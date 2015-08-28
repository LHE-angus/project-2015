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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table id="tab_1" width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td id="ceshi">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  
  
  <div class="rtabcont1">
   	  <form id="listForm" name="listForm" method="post" action="KonkaXxZmdJsRebate.do">
   	  	 <input type="hidden" name="method" value="view" />
   	  	 <input type="hidden" name="zmd_id" value="${af.map.zmd_id}" />
   	  	 <input type="hidden" name="dept_id" value="${af.map.dept_id}" />
   	  	 <input type="hidden" name="mod_id" value="${af.map.mod_id}" />
   	  	 <div style="text-align:left;margin-left:5px;margin-top:-5px;margin-bottom:5px;">
<!--   	  	 	<input type="button" class="but2" onclick="location.href='KonkaXxZmdJsRebate.do?method=add&mod_id=${af.map.mod_id}'" value="添加" />-->
   	  	 </div>
	     <table id="tab_3" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	        <tr class="tabtt1">
	          <td width="10%" nowrap="nowrap" align="center">专卖店编号</td>
	          <td width="15%" nowrap="nowrap" align="center">佣金</td>
	          <td width="20%" nowrap="nowrap" align="center">结算时间</td>
	          <!--<td width="10%" nowrap="nowrap" align="center">操作</td>-->
	        </tr>
	          <c:forEach var="cur" items="${entityList}" varStatus="vs">
	            <tr>
	              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.map.zmd_sn}</font></td>
	              <td align="right" nowrap="nowrap"><font class="blue12px"><fmt:formatNumber value="${cur.fee}" type="currency" /></font></td>
	              <td align="center" nowrap="nowrap">
	              	<font class="blue12px">
	              		<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
	              	</font>
	              </td>
	            </tr>
	          <c:if test="${vs.last eq true}">
	            <c:set var="i" value="${vs.count}" />
	          </c:if>
	        </c:forEach>
	        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
	          <tr>
	            <td>&nbsp;</td>
	            <td>&nbsp;</td>
	            <td>&nbsp;</td>
	            <!--<td>&nbsp;</td>-->
	          </tr>
	        </c:forEach>
	      </table>
      </form>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdJsRebate.do">
    	<table id="tab_4" width="98%" border="0" cellpadding="0" cellspacing="0">
      		<tr>
        		<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
	          		<script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "view");
			            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
						pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
						pager.addHiddenInputs("dept_id", "${af.map.dept_id}");	
	            		document.write(pager.toString());
	            	</script>
            	</td>
      		</tr>
    	</table>
  	</form>
  </div>
  <div class="clear"></div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
  	  <div class="ui-widget-overlay"></div>
  	  <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
    <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
	  <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#plJs").click(function(){
		form = this.form;
		var checkedCount = 0;
		if (!form.pks) {
			return;
		}
		if(!form.pks.length) {
			if (form.pks.checked == true) {
				checkedCount = 1;
			}
		}
		for (var i = 0; i < form.pks.length; i++) {
			if (form.pks[i].checked == true) {
				checkedCount++;
			}
		}
		if (checkedCount == 0) {
			alert("请选择要批量结算佣金的销售单！");
			return false;
		} else {
			if(confirm("确定要结算选中订单的佣金？")){
				this.form.submit();
			} else {
				return false;
			}
		}
	});

	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>