<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<base target="_self" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcCust.do"> 
      <html-el:hidden property="method" value="listCustomer" />
      <html-el:hidden property="fgs_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>
          	&nbsp;<strong class="fb">客户名称：</strong> 
          	<html-el:text property="customer_name_like" size="12" maxlength="12" styleId="customer_name_like" />&nbsp;
          	<strong class="fb">客户R3编码：</strong>
          	<html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code" />&nbsp;
          <html-el:submit styleClass="but1" value="搜索" /></td>  
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
          <td nowrap="nowrap" width="10%">客户R3编码</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td> 
            <td align="center" nowrap="nowrap"> 
            <a class="butbase" href="#" onclick="returnPdInfo('${cur.id}', '${cur.customer_name}', '${cur.r3_code}');" ><span class="icon-ok">选择</span></a>
           </td>
          </tr>
        </c:forEach>
      </table>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcCust.do"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "listCustomer");
					            pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");
					            pager.addHiddenInputs("fgs_id", "${fn:escapeXml(af.map.fgs_id)}");
					            pager.addHiddenInputs("r3_code", "${fn:escapeXml(af.map.r3_code)}");
					            document.write(pager.toString());
			</script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[


//function selectPd(cust_id,cust_code, cust_name){
	//window.opener.set_value(cust_id, cust_name+"[" + cust_code + "]");
	//window.close();
//}

//function setReturnValue(cust_id, cust_code, cust_name){  
	//var return_value = cust_id+"###"+cust_name+"["+cust_code+"]";
	//parent.$.returnValue = return_value;
//}  

var c_id = '${af.map.c_id}'; 

function returnPdInfo(id, customer_name, r3_code){   
	var api = frameElement.api, W = api.opener;
	W.document.getElementById("r3_code").value = r3_code;
	W.document.getElementById("r3_code_show").value = customer_name+"["+r3_code+"]";
	if(c_id==""||c_id==null){
		W.document.getElementById("cust_code").value = r3_code;
		W.document.getElementById("cust_name").value = customer_name;
	}

	api.close();
}


//]]></script>

</body>
</html>