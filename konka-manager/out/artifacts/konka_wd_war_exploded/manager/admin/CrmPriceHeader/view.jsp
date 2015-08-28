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
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
    <input class="but5" type="button" value="返回" onclick="history.go(-1);" />
  </div>
  
  <div class="rtabcont2">
    <html-el:form action="admin/CrmPriceHeader" method="post">
      <html-el:hidden property="method" value="save" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" width="10%">价格类型：</td>
          <td width="40%">
	          <html-el:select property="price_type" styleId="price_type" style="width:150px">
		          	<html-el:option value="ZFOR">常规价格</html-el:option>
		          	<html-el:option value="ZFGC">工程机价格</html-el:option>
		          	<html-el:option value="ZFCR">返利补差价格</html-el:option>
		          	<html-el:option value="ZFRE">退货价格</html-el:option>
		      </html-el:select>
          </td>
          <td ></td> 
          <td ></td>
        </tr>
        <tr>
          <td class="title_item" width="10%">价格编号：</td>
          <td width="40%">
          	${af.map.price_code }
          </td>
          <td class="title_item" width="10%">价格名称：</td>
           <td width="40%">
          	${af.map.price_name }
          </td>
        </tr>
		<tr>
          <td class="title_item" width="10%">部门：</td>
          <td width="40%">
          ${af.map.map.dept_name }
           </td>
          <td class="title_item" width="10%">客户群组：</td>
          <td width="40%">
          	 ${af.map.map.groupname }
        </tr>
        <tr>
          <td class="title_item" width="10%">开始时间：</td>
          <td width="40%">
          <fmt:formatDate value="${af.map.begin_date}" pattern="yyyy-MM-dd" var="_begindate" />
		       <input name="begin_date" id="begin_date" size="12" value="${_begindate}" style="width:150px" class="Wdate" type="text" />
          </td>
          <td class="title_item" width="10%">结束时间：</td>
          <td width="40%">
           <fmt:formatDate value="${af.map.end_date}" pattern="yyyy-MM-dd" var="_enddate" />
		        <input name="end_date" id="end_date" size="12" value="${_enddate}" style="width:150px" class="Wdate" type="text" />
		  </td>
        </tr>
       	<tr>
       		<td colspan="4">
	       		<div class="rtabcont1">
				    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" id="ctmtab">
				        <tr class="tabtt1">
				          <td align="center" nowrap="nowrap"  style="display:none">行ID</td>
				          <td align="center" nowrap="nowrap"  style="display:none">头ID</td>
				          <td align="center" nowrap="nowrap" >机型编码</td>
				          <td align="center" nowrap="nowrap" >机型名称</td>	
				          <td align="center" nowrap="nowrap" >供价</td>	
				          <td align="center" nowrap="nowrap" >市场价</td>	
				          <td align="center" nowrap="nowrap" >最低价</td>	
				          <td align="center" nowrap="nowrap" >提成</td>	
				          <td align="center" nowrap="nowrap" >返利</td>
				          <td align="center" nowrap="nowrap" >功能</td>	 
				          <td align="center" nowrap="nowrap" >推广政策</td>	
				        </tr>
						<c:forEach var="cur" items="${lineList}" varStatus="vs">
						        <tr>
						            <td align="center" nowrap="nowrap" style="display:none">${cur.id }</td> 
						            <td align="center" nowrap="nowrap" style="display:none">${cur.headid }</td> 
						            <td align="center" nowrap="nowrap">${cur.modelcode }</td> 
						            <td align="center" nowrap="nowrap">${cur.modelname }</td> 
						            <td align="center" nowrap="nowrap">${cur.forprice }</td> 
						            <td align="center" nowrap="nowrap">${cur.marketprice }</td>
						            <td align="center" nowrap="nowrap">${cur.lowestprice }</td> 
						            <td align="center" nowrap="nowrap">${cur.tc }</td> 
						            <td align="center" nowrap="nowrap">${cur.fl }</td>
						            <td align="center" nowrap="nowrap">${cur.func }</td> 
						            <td align="center" nowrap="nowrap">${cur.policy }</td>
						        </tr> 
						</c:forEach>
				      </table>
				</div> 
       		</td>
       	</tr>
      </table>
    </html-el:form>
    <div>
		<form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
	     	<html-el:hidden property="method" value="view" />
		     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		       <tr>
		         <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
		           <script type="text/javascript">
					  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						  pager.addHiddenInputs("id", "${af.map.id}"); 
						  pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						  pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
						  document.write(pager.toString()); 
					</script>
				</td>
		       </tr>
		     </table>
      </form>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
        
	}); 
//]]></script>
</body>
</html>
