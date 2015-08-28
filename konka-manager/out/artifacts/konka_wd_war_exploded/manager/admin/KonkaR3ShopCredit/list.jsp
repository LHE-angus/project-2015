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
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3ShopCredit.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td align="right">

          	<strong class="fb">销售组织：</strong>
          </td>
          <td>
          	<html-el:text property="vkorg" size="20" maxlength="20" styleId="vkorg" />
          </td>
          <td align="right">
          	<strong class="fb">客户编码：</strong>
          </td>
          <td>
          	<html-el:text property="kunnr_like" size="20" maxlength="20" styleId="kunnr_like" />
          </td>
          <td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">客户类型：</td>
		    <td >
				<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
		   	</td>
        </tr>
        <tr>
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">客户名称：</td>
		   	<td>
		   		<html-el:text property="kh_name_like" styleId="kh_name_like" />
		   	</td>
          	<td>
          		<html-el:submit styleClass="but1" value="搜索" />
          	</td>
          	<td colspan="3"></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <table width="400px" border="0" cellspacing="0" cellpadding="0">
    <!-- +1+ 表示新增  在系统设置对这个URL 的授权必须选上 新增 选项 -->
	  <logic-el:match name="popedom" value="+1+">
	      <tr>
	        <td style="padding-left:50px" width="40%">
	        	<input style="cursor: pointer;" type="button" class="btn9" id="btn9" value="数据同步" /></td>
	        <td id="tip_msg" style="float: left ; display:none"><font color="red">正在同步,请稍候...</font></td>
	        <td width="60%"><input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" /></td>
	      </tr>
	    </logic-el:match>
    </table>
    <div id="progressbar"></div>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td  nowrap="nowrap" align="center">序号</td>
          <td  nowrap="nowrap" align="center">销售组织</td>
          <td  nowrap="nowrap" align="center">客户编码</td>
          <td  nowrap="nowrap" align="center">客户名称</td>
          <td align="center" nowrap="nowrap">客户类型</td>
          <td align="center" nowrap="nowrap">客户细分类型</td>
          <td  nowrap="nowrap" align="center">风险类型</td>
		  <td nowrap="nowrap" align="center">原始分配金额</td>
          <td nowrap="nowrap" align="center">当月分配金额</td>	
          <td nowrap="nowrap" align="center">信用账期额度</td>
          <td nowrap="nowrap" align="center">总经理担保额度</td>
          <td nowrap="nowrap" align="center">信贷限额</td>
          
          <td nowrap="nowrap" align="center">信贷风险总额</td>
          <td nowrap="nowrap" align="center">剩余额度</td>
          
          <td nowrap="nowrap" align="center">使用的信贷限额</td>
          <td nowrap="nowrap" align="center">余额(应收) </td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td nowrap="nowrap" align="left">${cur.vkorg}</td>
            <td align="left" nowrap="nowrap">${cur.kunnr}</td>
            <td nowrap="nowrap" align="left">${cur.map.customer_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
            <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.ctlpc)}</td>
            
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.klime)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.klimg)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.dbekr)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.zlimt)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.klimk)}</td>
            
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.oblig)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.syed)}</td>
            
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.klprz)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.skfor)}</td>
          </tr>
        </c:forEach>
      </table>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopCredit.do">
      <input type="hidden" name="excel_to_all" value="${af.map.excel_to_all}" id="excel_to_all" />
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="80" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("kunnr_like","${af.map.kunnr_like}");
            pager.addHiddenInputs("vkorg","${af.map.vkorg}");
            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
            pager.addHiddenInputs("kh_name_like", "${af.map.kh_name_like}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script src="${ctx}/commons/scripts/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script>
	$(function(){
		$("#btn9").click(function(){
			 var pwd = prompt("请输入密码");
		       if(pwd == "951"){
		    	   var isExecute = doSyncMethod("推荐由系统自动进行数据同步,你确认要继续?", 'KonkaR3ShopCredit.do','tbData' ,$('#bottomPageForm').serialize());
					if( isExecute == true){
						$("#btn9").attr("disabled","disabled");
						$("#tip_msg").removeAttr("style");
					}
		       }else{
					 alert("对不起！你没有权限操作");
					 return false;	
			   }
			
			
		});
		
	var value = '${af.map.excel_to_all}';
		
		//导出
	$("#export_22").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
			$("#excel_to_all").val("1");
    		$("#bottomPageForm").submit();
    		$("#excel_to_all").val("");	
    		$("#export_22").attr("disabled","disabled");
		}
					
	});
	
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
		
	});
	
	
	function doSyncMethod(msg, page, method, queryString) {
		if(msg != null) {
			if(!confirm(msg))
				return false;
		}
		page = page || "?";
		page = page.indexOf("?") != -1 ? page : (page + "?");
		location.href = page  + "method=" + method + "&" + encodeURI(queryString);
		return true;
	}
	
	
</script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>