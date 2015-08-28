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
    <html-el:form action="/admin/KonkaSpActivityBookReport">
      <html-el:hidden property="method" value="listYytj" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
         <td width="15"></td>
         <td>
         <strong>分公司：</strong>
         <html-el:select property="dept_id" styleId="dept_id">
         <html-el:option value="">-请选择-</html-el:option>
         <c:forEach items="${deptList}" var="cur">
         <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
         </c:forEach>
         </html-el:select>
         </td>
         <td>
         <strong>促销活动名称：</strong>
         <html-el:text property="sp_name_like"></html-el:text>
         </td>
          <td>
         <strong>预约点名称：</strong>
         <html-el:text property="addr_name_like"></html-el:text>
         </td>
        </tr>
        
         <tr>
         <td width="15"></td>
          <td>
         <strong>尺寸段：</strong>
      <html-el:select property="size_section" styleId="size_section" >
        <html-el:option value="">-请选择-</html-el:option>
        <c:forEach items="${sizeSecList}" var="cur" varStatus="vs">
			   <html-el:option value="${cur.field1}">${cur.type_name}</html-el:option>
		</c:forEach>
        </html-el:select>
         </td>
          <td>
             <strong>客户名称：</strong>
         <html-el:text property="customer_name_like"></html-el:text>
          </td>
           <td>
         <strong>时&nbsp;&nbsp;&nbsp;&nbsp;间：</strong>
       <html-el:text property="add_date_start" styleId="add_date_start" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            	至
            <html-el:text property="add_date_end" styleId="add_date_end" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
         </td>
        </tr>
        <tr>
        <td width="15%"></td>
        <td> <strong>门店名称：</strong>
         <html-el:text property="store_name_like"></html-el:text></td>
          <td colspan="2" align="right">
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
        <td align="left" colspan="3"> <input align="right" type="button" class="but_excel" 
       	 onClick="doNeedMethod(null, 'KonkaSpActivityBookReport.do', 'list',
           	  'export=true&' + $('#bottomPageForm').serialize())" value="导出" /> </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="10%">分公司</td>
         <td nowrap="nowrap"  align="center" width="10%">客戶编号</td>
        <td nowrap="nowrap"  align="center" width="12%">客户名称</td>
        <td nowrap="nowrap"  align="center" width="12%">门店名称</td>
        <td nowrap="nowrap"  align="center" width="10%">活动标题</td>
        <td nowrap="nowrap"  align="center" width="10%">预约点</td>
        <td nowrap="nowrap"  align="center" width="10%">尺寸段</td>
        <td nowrap="nowrap"  align="center" width="10%">预约数量</td>
        <td nowrap="nowrap"  align="center" width="8%">定金金额</td>
<!--        <td nowrap="nowrap"  align="center" width="10%">操作</td>-->
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            ${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>	
           <td align="center" nowrap="nowrap">
           ${cur.map.dept_name}
           </td>
 			<td align="center" nowrap="nowrap"> ${cur.r3_code} </td>
            <td align="center" nowrap="nowrap">${cur.map.customer_name} </td>
            <td align="center" nowrap="nowrap">${cur.store_name} </td>
            <td align="left"nowrap="nowrap">
              <a href="${ctx} /manager/admin/KonkaSpActivityManager.do?method=view&mod_id=986010&id=${cur.sp_id}"  style="text-decoration:underline;">
            ${fn:escapeXml(cur.sp_name)}</a></td>
            <td align="left"nowrap="nowrap">
              <a href="${ctx}/manager/admin/KonkaSpActivityAddr.do?method=view&id=${cur.addr_id}"  style="text-decoration:underline;">
            ${fn:escapeXml(cur.addr_name)}</a></td> 
              <td align="center"nowrap="nowrap">
              <c:forEach items="${sizeSecList}" var="sizeSec" varStatus="vs">
					<c:if test="${sizeSec.field1 eq cur.size_section}">${sizeSec.type_name}</c:if>
			  </c:forEach>
              </td> 
             <td align="center"nowrap="nowrap">${cur.num}</td> 
            <td  align="right"nowrap="nowrap">
            <font color="red"><fmt:formatNumber  value="${cur.prepay_money}" pattern="￥00.00" /></font>
           </td>
<!--            <td align="left" nowrap="nowrap">-->
<!--            <span style="cursor: pointer;" class="fblue" -->
<!--            onclick="doNeedMethod(null, 'KonkaSpActivityBookReport.do', -->
<%--            'edit','id=${cur.id}&mod_id=${af.map.mod_id}'+$('#bottomPageForm').serialize())">修改--%>
<!--            </span>-->
<!--            &nbsp;-->
<!--            <span style="cursor: pointer;" class="fblue" -->
<!--            onclick="doNeedMethod(null, 'KonkaSpActivityBookReport.do', -->
<%--            'view','id=${cur.id}&mod_id=${af.map.mod_id}'+$('#bottomPageForm').serialize())">查看--%>
<!--            </span>-->
<!--            </td>-->
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
            </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSpActivityBookReport.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "listYytj");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("size_section", "${af.map.size_section}");
				pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
				pager.addHiddenInputs("addr_name_like", "${af.map.addr_name_like}");
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				pager.addHiddenInputs("sp_name_like", "${af.map.sp_name_like}");
				pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
				pager.addHiddenInputs("add_date_end", "${af.map.add_date_end}");
				pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
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
