<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" ></meta>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv=Expires content=0 />
<meta http-equiv=Cache-Control content=no-cache />
<meta http-equiv=Pragma content=no-cache />
<title>竞品活动上报</title>
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/wap/common.css" />
<link href="mobiscroll.custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="conlist">
  <ul>
  <li>
  <html-el:form action="/KonkaFightActivityManager">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" styleId="queryString" />  
  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  	   <tr>
	    <td width="35%" align="right">起始日期：</td>
	    <td >
	    <html-el:text property="begin_date_start" styleId="begin_date_start" styleClass="input-txt" size="8" maxlength="10" readonly="true"  style="cursor:pointer;"  />
	    </td>
	  </tr>
	   <tr>
	    <td width="6%" align="right">截止日期：</td>
	    <td >
	   <html-el:text property="begin_date_end" styleId="begin_date_end" styleClass="input-txt" size="8" maxlength="10" readonly="true"  style="cursor:pointer;"  />
	    </td>
	  </tr>
	  <tr>
		 <td align="right">门店名称：</td>
         <td >
         <html-el:text property="store_name_like" maxlength="30" styleClass="input-txt"></html-el:text>
         </td>
	  </tr>
	  <tr>
		 <td align="right">活动名称：</td>
         <td >
         <html-el:text property="activity_name_like" maxlength="30" styleClass="input-txt"></html-el:text>
         </td>
	  </tr>
	  <tr>
	    <td align="right">品&nbsp;&nbsp;&nbsp;&nbsp;牌：</td>
	    <td >
	    <html-el:select styleId="brand_id" property="brand_id" styleClass="input-txt" >
              <html-el:option value="">--请选择--</html-el:option>
              <c:forEach items="${pdList}" var="cur">
              <html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
               </c:forEach>
         </html-el:select>
	    </td>
	  </tr>
	     <tr>
	    <td align="right">&nbsp;</td>
	    <td><input class="websub" value="查 询" type="submit" /></td>  
	    </tr>
	</table>
   </html-el:form>
</li>
<c:forEach var="cur" items="${entityList}" varStatus="vs"> 
	<li>
	<p onclick="goview('${cur.id}')"><strong>分公司：</strong>${cur.map.dept_name}</a></p>
	<p onclick="goview('${cur.id}')"><strong>门店名称：</strong>${cur.map.store_name}</p>
	<span class="r"><span class="webbut" onclick="goedit('${cur.id}')" >修改</span></span>             
	<p ><strong>活动名称：</strong><a style="color: blue;cursor: pointer;" onclick="goview('${cur.id}')">${cur.activity_name}</a></p>
	<p onclick="goview('${cur.id}')"><strong>品牌：</strong>${cur.map.brand_name}</p>
	<p onclick="goview('${cur.id}')"><strong>实际销售量：</strong>${cur.sale_num}</p>
	<p onclick="goview('${cur.id}')"><strong>实际销售额：</strong>${cur.sale_money}</p>
	</li>
</c:forEach>        
  <li>
  <div style="margin：0 auto;text-align: center;">
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaFightActivityManager.do"> 
      <table width="98%" border="0" cellpadding="0" cellspacing="0">  
        <tr>
          <td align="center">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager4.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("begin_date_start", "${af.map.begin_date_start}");
	            pager.addHiddenInputs("begin_date_end", "${af.map.begin_date_end}");
	            pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
	            pager.addHiddenInputs("activity_name_like", "${af.map.activity_name_like}");
	            pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
	            pager.addHiddenInputs("user_id", "${user_id}");
	            pager.addHiddenInputs("userpass", "${userpass}");
	            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
    </div>
  </li>
  </ul>  
</div>
 <div class="clear"></div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script src="mobiscroll.custom-2.6.2.min.js" type="text/javascript"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	 var curr = new Date().getFullYear();
     var opt = {

     }

    opt.date = {preset : 'date'};
	opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5 }; 
	opt.time = {preset : 'time'};
	opt.tree_list = {preset : 'list', labels: ['Region', 'Country', 'City']};
	opt.image_text = {preset : 'list', labels: ['Cars']};
	opt.select = {preset : 'select'};

    $('#begin_date_start').scroller($.extend(opt['date'], { theme: 'default', mode: 'scroller', display: 'modal', lang: 'zh', dateFormat: 'yy-mm-dd',startYear:curr-20,   
        endYear:curr + 10 }));

    $('#begin_date_end').scroller($.extend(opt['date'], { theme: 'default', mode: 'scroller', display: 'modal', lang: 'zh', dateFormat: 'yy-mm-dd',startYear:curr-20,   
        endYear:curr + 10 }));
	
});

function goedit(id){
	window.location.href= "${ctx}/webservice/KonkaFightActivityManager.do?method=edit&id="+ id +"&user_id=${user_id}&userpass=${userpass}";
}
function goview(id){
	window.location.href= "${ctx}/webservice/KonkaFightActivityManager.do?method=view&id="+ id +"&user_id=${user_id}&userpass=${userpass}";
}
//]]>
</script>

</body>
</html>
