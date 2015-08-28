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
    <html-el:form action="/admin/KonkaShopDevelopCount" method="post">
       <html-el:hidden property="method" value="list" />
       <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="tree_param" value="${tree_param}" />    
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
           <td width="15" ></td>
           <td><strong class="fb">开拓起止日期：</strong>
            <html-el:text property="s_date" styleId="s_date" size="9" maxlength="9" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);"  ></html-el:text>
            至
           <html-el:text property="e_date" styleId="e_date" size="9"  maxlength="9" readonly="readonly" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" ></html-el:text>
           &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" />
          </td>
         </tr>
       </table>
     </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
         <tbody>
           <tr class="tabtt1">
             <td nowrap="nowrap" align="center">开拓状态</td>
             <td nowrap="nowrap" align="center">统计数量</td>
             <td nowrap="nowrap" align="center">操作</td>
           </tr>
           <tr>          
              <td align="center">待开拓的网点</td>
              <td align="center">${recordCount0 }</td>
              <td align="center" nowrap="nowrap">
                 <a href="#"  class="fblue" onclick="location.href='KonkaShopDevelopCount.do?method=itemList&develop_status=0&mod_id=${af.map.mod_id}';">查看</a>
              </td>
           </tr>
           <tr>          
              <td align="center">正在开拓的网点</td>
              <td align="center">${recordCount1 }</td>
              <td align="center" nowrap="nowrap">
                 <a href="#"  class="fblue" onclick="location.href='KonkaShopDevelopCount.do?method=itemList&develop_status=1&mod_id=${af.map.mod_id}';">查看</a>
              </td>
           </tr>
             <tr>
               <td align="center"  rowspan="2">已开拓的网点   </td>
               <td align="center" >${recordCount3+ recordCount4}</td>
                   <td align="center"  rowspan="2" nowrap="nowrap">
                   <a href="#"  class="fblue" onclick="location.href='KonkaShopDevelopCount.do?method=itemList&develop_status=2&mod_id=${af.map.mod_id}';">查看</a>
                   </td> 
             </tr>
               <tr>
               <td  align="center" >
                   R3用户：${recordCount3 }&nbsp;<a href="#"  class="fblue" onclick="location.href='KonkaShopDevelopCount.do?method=itemList&status=1&develop_status=2&mod_id=${af.map.mod_id}';">查看</a>
                 |
                  经销商：${recordCount4 } &nbsp;<a href="#"  class="fblue" onclick="location.href='KonkaShopDevelopCount.do?method=itemList&status=2&develop_status=2&mod_id=${af.map.mod_id}';">查看</a>
                  </td>
             </tr>    
        </tbody>
      </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>