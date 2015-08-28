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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
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
    <html-el:form  action="/admin/KonkaMobileMdPercent">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id"/>
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <c:if test="${isHeadquarters}">
	          <td width="15"></td>
			  <td width="100"><strong class="fb">分公司/办事处：</strong></td>
	 		  <td width="120">
	   		<!-- 	<html-el:select property="dept_id" styleId="dept_id" >
	   				<html-el:option value="">请选择...</html-el:option>
	   				<html-el:optionsCollection label="dept_name" name="konkaDeptList" value="dept_id"/>
	   			</html-el:select>
	   			
	   			
	   			 -->
	   			
	   			
	   			<html-el:select property="dept_id" styleId="dept_id" >
            		<html-el:option value="">=请选择=</html-el:option>
            		<c:forEach items="${konkaDeptList}" var="cur">
            			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
            		</c:forEach>
            	</html-el:select>
	          </td>
       	  </c:if>
          <td width="15"></td>
   		  <td width="150"><strong class="fb">产品尺寸/型号：</strong></td>
   		  <td colspan="2">
           &nbsp;
	 	  <html-el:select property="size_id" styleId="size_id" >
			<html-el:option value="">-选择尺寸-</html-el:option>
			<html-el:optionsCollection label="name" name="sizeList" value="name"/>
      	  </html-el:select>
           &nbsp;
           <select name="pd_id" id="pdIds" multiple="multiple">
		          		
		   </select>
   		  </td>
   		  <td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
         </tr>
      </table>
    </html-el:form>
    </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <logic-el:match name="popedom" value="+1+">  
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaMobileMdPercent.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		</td>
	 </tr>
	</table>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td align="center" nowrap="nowrap">分公司</td>
          <td width="15%" align="center" nowrap="nowrap">型号名称</td>
          <td width="15%" align="center" nowrap="nowrap">提成类型</td>
          <td align="center" nowrap="nowrap">提成比例/金额</td>
          <td width="12%" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="center"><font class="blue12px">${cur.map.dept_name}</font></td>
              <td align="left"><font class="blue12px">${cur.map.md_name}</font></td>
              <td align="center">
              <c:choose>
              <c:when test="${cur.type eq 0}">比例</c:when>
              <c:when test="${cur.type eq 1}">定额</c:when>
              </c:choose>
              </td>
              <td align="right">${cur.percent}
              <c:if test="${cur.type eq 0}">%</c:if></td>
              <td align="center" nowrap="nowrap">
              <logic-el:match name="popedom" value="+2+"><span class="fblue" style="cursor:pointer;" onclick="doNeedMethod(null, 'KonkaMobileMdPercent.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span></logic-el:match>
              <logic-el:notMatch name="popedom" value="+2+"><span class="fblue" style="color:#ccc;">修改</span></logic-el:notMatch>
              </td>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileMdPercent.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("tree_param", "${tree_param}");
			            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			            pager.addHiddenInputs("pd_id", "${af.map.pd_id}");
			            pager.addHiddenInputs("size_id", "${af.map.size_id}");
			            document.write(pager.toString());
			   </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>  
<script type="text/javascript">//<![CDATA[
 $(document).ready(function() {
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
    var pd_id_value = '${af.map.pd_id}';
	$("#pdIds").multiselect({
		noneSelectedText: '==请选择==',
		selectedList: 1,
		multiple: false,
		minWidth:220,
		click: function(event, ui){
	       if(ui.value != ""){
	       }
		}
	}).multiselectfilter(); 

	if(pd_id_value.length > 0){
		category_id_chg();
	}

	$("#size_id").change(function(){
		$("#pdIds").multiselect({
			noneSelectedText: '==请选择==',
			selectedList: 1,
			multiple: false,
			minWidth:220,
			click: function(event, ui){
		       if(ui.value != ""){
			      // alert(ui.value);
		       }
			}
		}).multiselectfilter();
		$("#pdIds").empty();
		category_id_chg();

    });
	
 });   
 
//类别-连动-型号
function category_id_chg(){
		$.ajax({
			type: "POST" , 
			url: "${ctx}/manager/admin/KonkaMobileSailDatas.do" , 
			data:"method=getModel&size_id="+$('#size_id').val()+"&n=" + Math.random(),
			dataType: "json" , 
	        async: true, 
	        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
	        success: function (result) {
				if (result.state == 1) {
					for ( var x in result.list) {
						$("#pdIds").append('<option value="' + result.list[x].pd_id + '">' + result.list[x].md_name + '</option>');
					}
					if (""!= "${af.map.pd_id}") {//修改回显
						$("#pdIds").val("${af.map.pd_id}");
					}
					$("#pdIds").multiselect("refresh");
				}else{
					$("#pdIds").multiselect("refresh");
				}
	        }
		});
		
}




//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
