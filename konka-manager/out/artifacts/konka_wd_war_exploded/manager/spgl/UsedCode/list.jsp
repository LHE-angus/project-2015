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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/UsedCode">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;客户姓名  ： <html-el:text property="real_name_like" styleId="real_name_like" styleClass="webinput" maxlength="30"/>
					&nbsp;&nbsp;交易流水号  ： <html-el:text property="trade_index_like" styleId="trade_index_like" styleClass="webinput" maxlength="30"/>&nbsp;&nbsp;
                     
                        状态：<html-el:select property="is_userd" styleId="is_userd" >
                    <html-el:option value="3">全部</html-el:option>
                    <html-el:option value="1">已使用</html-el:option>
                    <html-el:option value="0">未使用</html-el:option> 
                    </html-el:select> 
                   
          </td>
          </tr>
          <tr> 
           <td height="36" align="left" style="padding-left:5px;">
            <c:if test="${role_id_eq_10}"> 所属商家：
          <html-el:select property="shop_id" styleId="shop_id" >
                    <html-el:option value="3">全部</html-el:option>
                    <html-el:option value="0">康佳集团</html-el:option>
                    <html-el:option value="1001">西北印象</html-el:option>
                    <html-el:option value="1002">等待入驻</html-el:option>
                    </html-el:select>
          </c:if>  
           下单日期：
        		<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;
            使用日期：
        		<html-el:text property="used_time_start" styleId="used_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="used_time_end" styleId="used_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;
             
           
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
          	 <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left:50px;" /> 
           </td>
        </tr>
      </table>
    </html-el:form>
  </div> 
  <div class="rtabcont1" style="overflow: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >订单流水号</td>
        <td nowrap="nowrap" width="10%" align="center">所属商家</td>
        <td width="10%" nowrap="nowrap" align="center">金额</td>
        <td width="10%" nowrap="nowrap" align="center">数量</td>
        <td width="10%" nowrap="nowrap" align="center">商品名称</td>
        <td width="10%" nowrap="nowrap" align="center">客户姓名</td>
        <td width="10%" nowrap="nowrap" align="center">是否使用</td>
        <td width="10%" nowrap="nowrap" align="center">下单时间</td>
        <td width="10%" nowrap="nowrap" align="center">使用时间</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="center" nowrap="nowrap">${cur.trade_index}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.shop_id eq 1001}">西北印象</c:if>
          <c:if test="${cur.shop_id eq 0}">康佳集团</c:if>
           <c:if test="${cur.shop_id eq 1002}">等待入驻</c:if>  
          </td>
          <td align="right" nowrap="nowrap">${cur.price} </td>
          <td align="right" nowrap="nowrap">1</td> 
            <td align="left" nowrap="nowrap">${cur.map.pd_name}</td> 
          <td align="left" nowrap="nowrap">${cur.real_name}</td>
           <td align="left" nowrap="nowrap"> <c:if test="${cur.is_userd eq 0}">未使用</c:if>
           <c:if test="${cur.is_userd eq 1}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'UsedCode.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">已使用</span></c:if>
           </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="center" nowrap="nowrap">
           <c:if test="${not empty cur.used_date}">
           <fmt:formatDate value="${cur.used_date}"  pattern="yyyy-MM-dd"></fmt:formatDate>
          </c:if>
          <c:if test="${empty cur.used_date}">  
         	  暂无
          </c:if> 
          </td>
          
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="UsedCode.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("real_name_like", "${af.map.real_name_like}");
								pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
								pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
								<c:if test="${role_id_eq_10}"> 
								pager.addHiddenInputs("shop_id", "${af.map.shop_id}");
								</c:if>
								pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
								pager.addHiddenInputs("used_time_start", "${af.map.used_time_start}");
								pager.addHiddenInputs("used_time_end", "${af.map.used_time_end}");
								pager.addHiddenInputs("is_userd", "${af.map.is_userd}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/UsedCode.do?method=excel";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/UsedCode.do";
    	 this.value="导出";
    	 this.disabled=false;
    });
	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
