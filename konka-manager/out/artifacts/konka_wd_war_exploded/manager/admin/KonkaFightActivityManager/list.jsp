<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaFightActivityManager">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
         <td width="15"></td>
          <td>
          <strong>分公司：</strong>
         <html-el:select property="dept_id" styleId="dept_id" disabled="${show_fgs}" style="width:90px">
                <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${deptList}" var="cur">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
              </html-el:select>
          </td>
          <td>
         <strong>客户编码：</strong>
         <html-el:text property="r3_code" maxlength="20"></html-el:text>
         </td>
         <td>
         <strong>客户名称：</strong>
         <html-el:text property="customer_name_like" maxlength="30"></html-el:text>
         </td>
        </tr>
        
         <tr>
         <td width="15"></td>
         <td>
              <strong>门店ID：</strong>
         <html-el:text property="store_id" maxlength="30"></html-el:text>
         </td>
          <td>
              <strong>门店名称：</strong>
         <html-el:text property="store_name_like" maxlength="30"></html-el:text>
         </td>
         <td>
         <strong>品牌：</strong>
         <html-el:select styleId="brand_id" property="brand_id" styleClass="text012" >
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${pdList}" var="cur">
              <html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
               </c:forEach>
         </html-el:select>
         </td>
         </tr>
         <tr>
          <td width="15"></td>
         
     <td>
              <strong>主推型号：</strong>
         <html-el:text property="model_like" maxlength="30"></html-el:text>
         </td>
         <td>
         <strong>状&nbsp;&nbsp;&nbsp;&nbsp;态：</strong>
       <html-el:select property="is_del">
       <html-el:option value="">-请选择-</html-el:option>
       <html-el:option value="0">正常</html-el:option>
       <html-el:option value="1">删除</html-el:option>
       </html-el:select>
         </td>
         <td>
        <strong>创建时间：</strong>
       <html-el:text property="add_date_start" styleId="add_date_start" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            	至
            <html-el:text property="add_date_end" styleId="add_date_end" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          
         </td>
         
          <td  align="right">
        <input class="but1" type="submit" name="Submit" value="搜索" />
         </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='KonkaFightActivityManager.do?method=add&mod_id=${af.map.mod_id}';" />
<%--        	<input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='${ctx}/manager/admin/KonkaSpList.do?method=imp&mod_id=${af.map.mod_id}'" />--%>
        </td>
 <%--        <td width="80%"></td>
         <td align="right"> <input align="right" type="button" class="but_excel" 
       	 onClick="doNeedMethod(null, 'KonkaSpActivityAddr.do', 'list',
           	  'export=true&' + $('#bottomPageForm').serialize())" value="导出" /> </td>--%>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" >
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="5%">分公司</td>
        <td nowrap="nowrap" align="center" width="5%">客户R3编码</td>
        <td nowrap="nowrap" align="center" width="15%">客户名称</td>
        <td nowrap="nowrap"  align="center" width="20%">门店ID</td>
         <td nowrap="nowrap"  align="center" width="10%">门店名称</td>
        <td nowrap="nowrap"  align="center" width="12%">品牌</td>
        <td nowrap="nowrap"  align="center" width="12%">活动名称</td>
        <td nowrap="nowrap"  align="center" width="10%">活动时间段</td>
        <td nowrap="nowrap"  align="center" width="8%">主推型号</td>
        <td nowrap="nowrap"  align="center" width="10%">实际销售量</td>
        <td nowrap="nowrap"  align="center" width="10%">实际销售额</td>
        <td nowrap="nowrap"  align="center" width="8%">创建人</td>
        <td nowrap="nowrap"  align="center" width="8%">创建时间</td>
        <td nowrap="nowrap"  align="center" width="8%">其他说明</td>
        <td nowrap="nowrap"  align="center" width="10%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            ${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>	
            <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.r3_code}</td>
            <td align="center" nowrap="nowrap">${cur.map.customer_name}</td>
            <td align="center" nowrap="nowrap">${cur.store_id}</td>
            <td align="center" nowrap="nowrap">${cur.map.store_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.brand_name}</td>
            <td align="center" nowrap="nowrap">
             <a href="${ctx}/manager/admin/KonkaFightActivityManager.do?method=view&id=${cur.id}"  
             style="text-decoration:underline;">${cur.activity_name}</a>
            </td>
            <td align="center" nowrap="nowrap">
            <fmt:formatDate value="${cur.begin_date}"
					pattern="yyyy-MM-dd" />——<fmt:formatDate value="${cur.end_date}"
					pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">${cur.model}</td>
            <td align="center" nowrap="nowrap">${cur.sale_num}</td>
            <td align="center" nowrap="nowrap">${cur.sale_money}</td>
            <td align="center" nowrap="nowrap">${cur.add_user_name}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.memo)}</td>
            <td align="left" nowrap="nowrap">
            <span style="cursor: pointer;" class="fblue" 
            onclick="doNeedMethod(null, 'KonkaFightActivityManager.do', 
            'edit','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改
            </span>
            <c:if test="${cur.is_del eq 0}">
            <span style="cursor: pointer;" class="fblue" 
            onclick="doNeedMethod(null, 'KonkaFightActivityManager.do', 
            'stop','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除
            </span>
            </c:if>
            <c:if test="${cur.is_del eq 1}">
            <span color="grey">删除 </span>
            </c:if>
            </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
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
      </tbody>
	    </table>
<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaFightActivityManager.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
				pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
				pager.addHiddenInputs("model_like", "${af.map.model_like}");
				pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
				pager.addHiddenInputs("is_del", "${af.map.is_del}");
				pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
				pager.addHiddenInputs("add_date_end", "${af.map.add_date_end}");
				pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
				pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  
  <div class="clear"></div>
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
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
