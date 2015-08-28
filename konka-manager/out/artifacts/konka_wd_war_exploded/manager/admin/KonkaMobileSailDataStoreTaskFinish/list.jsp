<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
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
    <html-el:form  action="/admin/KonkaMobileSailDataStoreTaskFinish">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id"/>
      <table width="100%" border="0" cellspacing="4" cellpadding="0" class="rtable1">
          <tr>
          	<td width="8%" valign="middle" nowrap="nowrap" class="title_item">分公司</td>
          	<td width="25%" >
	           <html-el:select property="dept_id" styleId="dept_id" style="width:150px;" >
				<html-el:option value="">请选择</html-el:option>
				<c:forEach var="cur" items="${deptList}" varStatus="vs">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			   </html-el:select> 
			</td>
			
			<td width="8%" valign="middle" nowrap="nowrap" class="title_item">门店名称</td>
            <td width="25%" >
           		<html-el:text property="store_name"></html-el:text>
            </td>
            
			<td width="8%" valign="middle" nowrap="nowrap" class="title_item">门店类型</td>
            <td width="15%" >
              <html-el:select property="store_type" styleId="store_type" style="width:150px;" >
           		<html-el:option value="">X(未知)</html-el:option>
				<html-el:option value="A">A类</html-el:option>
				<html-el:option value="B">B类</html-el:option>
				<html-el:option value="C">C类</html-el:option>
			  </html-el:select> 
            </td>
   		</tr>
   		
   		<tr>
	   		<td width="8%" valign="middle" nowrap="nowrap" class="title_item">时间范围</td>
          	<td colspan="2"> 
          	<html-el:select property="year1" styleId="year1">
              <c:forEach items="${yearList}" var="cur">
                <html-el:option value="${cur}">${cur}年</html-el:option>
              </c:forEach>
            </html-el:select>
            <html-el:select property="month1" styleId="month1" >
              <html-el:option value="01">1月</html-el:option>
              <html-el:option value="02">2月</html-el:option>
              <html-el:option value="03">3月</html-el:option>
              <html-el:option value="04">4月</html-el:option>
              <html-el:option value="05">5月</html-el:option>
              <html-el:option value="06">6月</html-el:option>
              <html-el:option value="07">7月</html-el:option>
              <html-el:option value="08">8月</html-el:option>
              <html-el:option value="09">9月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select> --
            <html-el:select property="year2" styleId="year2">
              <c:forEach items="${yearList}" var="cur">
                <html-el:option value="${cur}">${cur}年</html-el:option>
              </c:forEach>
            </html-el:select>
            <html-el:select property="month2" styleId="month2" >
              <html-el:option value="01">1月</html-el:option>
              <html-el:option value="02">2月</html-el:option>
              <html-el:option value="03">3月</html-el:option>
              <html-el:option value="04">4月</html-el:option>
              <html-el:option value="05">5月</html-el:option>
              <html-el:option value="06">6月</html-el:option>
              <html-el:option value="07">7月</html-el:option>
              <html-el:option value="08">8月</html-el:option>
              <html-el:option value="09">9月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select>
		  	</td>
   		    <td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
   		    <td></td>
   		    <td></td>
   		</tr>
      </table>
    </html-el:form>
    </div>
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
	          <td width="8%" align="center">分公司</td>
	          <td width="8%" nowrap="nowrap" align="center" >门店ID</td>
	          <td width="6%" nowrap="nowrap" align="center" >门店名称</td>
	          <td width="12%" nowrap="nowrap" align="center" >门店类型</td>
	          <td width="10%" nowrap="nowrap" align="center" >任务额</td>
	          <td width="8%" nowrap="nowrap" align="center" >销售金额（元）</td>
	          <td width="5%" nowrap="nowrap" align="center" >销售数量（台）</td>
	          <td width="10%" nowrap="nowrap" align="center" >完成率</td>
	          <td width="8%" nowrap="nowrap" align="center" >排名</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
          <td align="center" nowrap="nowrap">${cur.deptName}</td>
          <td align="center" nowrap="nowrap">${cur.storeId}</td>
          <td align="left" nowrap="nowrap">${cur.storeName}</td>
          <td align="left" nowrap="nowrap">${cur.storeType}</td>
          <td align="right" nowrap="nowrap">
          <font color="red">
          <fmt:formatNumber type="currency" value="${cur.taskMoney}" />
          </font>
          </td>
          <td align="right" nowrap="nowrap">
          <font color="red">
           <fmt:formatNumber type="currency" value="${cur.prices}" />
          </font>
          </td>
          <td align="right" nowrap="nowrap">${cur.nums}</td>
         
          <c:if test="${cur.taskMoney ne 0}">
           <td align="right" nowrap="nowrap">
          ${cur.tfp*100}%
            </td>
          </c:if>
          
          <c:if test="${cur.taskMoney eq 0}">
           <td align="center" nowrap="nowrap">
           <font color="grey">--</font>
           </td>
          </c:if>
        
          <td align="right" nowrap="nowrap">${cur.storerank}</td>
          </tr>
        </c:forEach>
      </table>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("store_name", "${af.map.store_name}");
            pager.addHiddenInputs("store_type", "${af.map.store_type}");
            
            pager.addHiddenInputs("year1", "${af.map.year1}");
            pager.addHiddenInputs("month1", "${af.map.month1}");
            pager.addHiddenInputs("year2", "${af.map.year2}");
            pager.addHiddenInputs("month2", "${af.map.month2}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//
	
	$("#year1").val("9");
   });
//]]></script>
</body>
</html>
