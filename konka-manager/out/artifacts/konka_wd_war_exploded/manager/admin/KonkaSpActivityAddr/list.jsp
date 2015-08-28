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
    <html-el:form action="/admin/KonkaSpActivityAddr">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
         <td width="15"></td>
          <td>
          <strong>分公司部门：</strong>
         <html-el:select property="dept_id" styleId="dept_id" disabled="${show_fgs}" style="width:90px">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l4_dept_id" styleId="l4_dept_id" style="width:110px">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l5_dept_id" styleId="l5_dept_id" style="width:110px">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
         
          </td>
         <td>
         <strong>预约点编码：</strong>
         <html-el:text property="addr_index_like" maxlength="50"></html-el:text>
         </td>
          <td>
         <strong>客户编码：</strong>
         <html-el:text property="r3_code" maxlength="20"></html-el:text>
         </td>
        
        </tr>
        
         <tr>
         <td width="15"></td>
         <td>
         <strong>客户名称：</strong>
         <html-el:text property="customer_name_like" maxlength="30"></html-el:text>
         </td>
          <td>
              <strong>门店名称：</strong>
         <html-el:text property="store_name_like" maxlength="30"></html-el:text>
         </td>
         <td>
         <strong>预约点负责人：</strong>
         <html-el:text property="hander_user_like" maxlength="30"></html-el:text>
         </td>
         </tr>
         <tr>
          <td width="15"></td>
         <td>
    
        <strong>时&nbsp;&nbsp;&nbsp;&nbsp;间：</strong>
       <html-el:text property="add_date_start" styleId="add_date_start" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            	至
            <html-el:text property="add_date_end" styleId="add_date_end" size="7" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          
         </td>
         <td>
         <strong>状&nbsp;&nbsp;&nbsp;&nbsp;态：</strong>
       <html-el:select property="state">
       <html-el:option value="">-请选择-</html-el:option>
       <html-el:option value="0">开启</html-el:option>
       <html-el:option value="1">关闭</html-el:option>
       </html-el:select>
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
        	<input type="button" class="but2" name="add" value="新增" onclick="location.href='KonkaSpActivityAddr.do?method=add&mod_id=${af.map.mod_id}';" />
<%--        	<input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='${ctx}/manager/admin/KonkaSpList.do?method=imp&mod_id=${af.map.mod_id}'" />--%>
        </td>
        <td width="80%"></td>
         <td align="right"> <input align="right" type="button" class="but_excel" 
       	 onClick="doNeedMethod(null, 'KonkaSpActivityAddr.do', 'list',
           	  'export=true&' + $('#bottomPageForm').serialize())" value="导出" /> </td>
        
      </tr>
    </table>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" >
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="5%">分公司</td>
        <td nowrap="nowrap" align="center" width="5%">经办</td>
        <td nowrap="nowrap" align="center" width="15%">预约点编码</td>
        <td nowrap="nowrap"  align="center" width="20%">预约点标题</td>
         <td nowrap="nowrap"  align="center" width="10%">客户编码</td>
        <td nowrap="nowrap"  align="center" width="12%">客户名称</td>
        <td nowrap="nowrap"  align="center" width="12%">门店名称</td>
        <td nowrap="nowrap"  align="center" width="10%">创建人</td>
        <td nowrap="nowrap"  align="center" width="10%">创建时间</td>
        <td nowrap="nowrap"  align="center" width="10%">预约点负责人</td>
        <td nowrap="nowrap"  align="center" width="8%">预约点状态</td>
        <td nowrap="nowrap"  align="center" width="8%">预约点照片</td>
        <td nowrap="nowrap"  align="center" width="10%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">
            ${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>	
            <td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.l4_dept_name}</td>
            <td align="center" nowrap="nowrap">
            <a href="${ctx}/manager/admin/KonkaSpActivityAddr.do?method=view&id=${cur.id}"  style="text-decoration:underline;">
          ${cur.addr_index}
            </a>
            </td>
            <td align="left"nowrap="nowrap">${fn:escapeXml(cur.addr)}</td>
            <td align="center" nowrap="nowrap">${cur.r3_code}</td>
            <td align="center" nowrap="nowrap">${cur.customer_name}</td>
            <td align="center" nowrap="nowrap">${cur.store_name}</td>
            <td align="center" nowrap="nowrap">${cur.add_user_name}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"/></td>
            <td align="center" nowrap="nowrap">${cur.addr_header}</td>
            <td align="center" nowrap="nowrap">
            <c:choose>
            <c:when test="${cur.state eq 0}"><font color="green">开启</font></c:when>
             <c:when test="${cur.state eq 1}"><font color="grey">关闭</font></c:when>
             <c:otherwise>未指定</c:otherwise>
            </c:choose>
            </td>
            <td align="left" nowrap="nowrap">
            <c:if test="${not empty cur.map.fj_paths}">
           <c:set var="fapiao" value="${fn:split(cur.map.fj_paths,',')}" />
          <c:forEach items="${fapiao}" var="tt" varStatus="vs1">
         
          	<a href="${ctx}/${tt}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          </c:forEach>
          </c:if>
            </td>
            
           
            <td align="left" nowrap="nowrap">
            <span style="cursor: pointer;" class="fblue" 
            onclick="doNeedMethod(null, 'KonkaSpActivityAddr.do', 
            'edit','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改
            </span>
            <c:if test="${cur.state eq 0}">
            <span style="cursor: pointer;" class="fblue" 
            onclick="doNeedMethod(null, 'KonkaSpActivityAddr.do', 
            'stop','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">停用
            </span>
            </c:if>
            <c:if test="${cur.state eq 1}">
            <span style="cursor: pointer;" class="fblue" 
            onclick="doNeedMethod(null, 'KonkaSpActivityAddr.do', 
            'restart','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">启用
            </span>
            </c:if>
            <span style="cursor: pointer;" class="fblue" 
            onclick="showEWM(${cur.id})">二维码
            </span>
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
          </tr>
        </c:forEach>
      </tbody>
	    </table>
	<div id="showewm"
		style="display: none; top: 150px; left: 120px; position: absolute; z-index: 9999; border: 1px solid #F00;">
		<div align="right"><span id="closeewm" style="color:grey;">关闭</span></div>
	<img id="showewmimg" style="width: 200px; height: 200px;"
		src="${ctx}/images/QRCode.jpg?s=http://qdgl.konka.com/webservice/KonkaSpActivityAddr.do?param=1"
		alt="预约点" /></div>
<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSpActivityAddr.do">
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
				pager.addHiddenInputs("state", "${af.map.state}");
				pager.addHiddenInputs("addr_index_like", "${af.map.addr_index_like}");
				pager.addHiddenInputs("hander_user_like", "${af.map.hander_user_like}");
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
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}


	$("#closeewm").click(function(){
		$(this).parent().parent().hide();
		});
});

function showEWM(id){
	//alert(id);
	$("#showewmimg").attr("src","${ctx}/images/QRCode.jpg?s=http://qdgl.konka.com/webservice/KonkaSpActivityBookReport.do?param="+id);
	$("#showewm").show();
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
