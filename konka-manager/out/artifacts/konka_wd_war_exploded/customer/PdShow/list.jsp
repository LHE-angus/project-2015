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
    <html-el:form action="/manager/PdShow">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="own_sys" value="${af.map.own_sys}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">&nbsp;商品编码：
            <html-el:text property="pd_sn_like" styleId="pd_sn_like" styleClass="webinput" />
            &nbsp;商品名称：
            <html-el:text property="pd_name_like" styleId="pd_name_like" styleClass="webinput" />
            &nbsp;&nbsp;类型：
            <html-el:select property="label_of_cate" styleId="label_of_cate">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">新品</html-el:option>
            	<html-el:option value="1">主销</html-el:option>
            	<html-el:option value="2">热销</html-el:option>
            	<html-el:option value="3">特惠</html-el:option>
            	<html-el:option value="4">工程机</html-el:option>
            	<html-el:option value="5">限时抢购</html-el:option>
            	<html-el:option value="6">团购</html-el:option>
            	<html-el:option value="7">精品</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
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
        <c:if test="${is_r3_customer ne 1}"> 
       	 <input type="button" class="but2" value="新 增" onclick="location.href='PdShow.do?method=add&own_sys=${af.map.own_sys}&mod_id=${af.map.mod_id}';" />
        </c:if>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <c:set var="now">
      <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />
    </c:set>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr>
          <td colspan="3" style="background:#f5f4f4"><div style="float:left;">康佳（KONKA）&nbsp;${cur.map.pd_name}&nbsp;${cur.pd_size}英寸&nbsp;${cur.pd_name}</div>
            <div style="float:right;">商品编码：${cur.pd_sn}</div></td>
        </tr>
        <tr>
          <td align="center" width="20%" rowspan="5" style="background:#FFFFFF;"><c:if test="${not empty cur.main_pic}">
              <c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
              <img src="http://epp.konka.com/${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" /> </c:if>
          </td>
          <td width="15%" align="left">功能分类</td>
          <td align="left"><c:if test="${cur.label_3d eq 1}">3D电视</c:if>
            &nbsp;
            <c:if test="${cur.label_int eq 1}">智能电视</c:if>
            &nbsp;
            <c:if test="${cur.label_www eq 1}">网络电视</c:if>
          </td>
        </tr>
        <tr>
          <td align="left">规格型号</td>
          <td align="left">${cur.map.md_name}</td>
        </tr>
        <tr>
          <td align="left">尺寸/分辨率</td>
          <td align="left">${cur.pd_size}英寸/${fn:split(cur.pd_res, ',')[0]}×${fn:split(cur.pd_res, ',')[1]}</td>
        </tr>
      </table>
      <c:choose>
        <c:when test="${not empty cur.down_time}">
          <c:set var="downDate">
            <fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd" />
          </c:set>
        </c:when>
        <c:otherwise>
          <c:set var="downDate"></c:set>
        </c:otherwise>
      </c:choose>
      <div style="margin:3px auto;">
        <div style="float:left;">
          <div style="float:left;">[
               <span style="color:#009900;">已上架</span>
            ]&nbsp; </div>
          <div style="float:left;">
            <ul class="ckUl" id="ul_${cur.id}">
              <li id="li_1_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_0_${cur.id}" value="0" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_0_${cur.id}"><span class="ck-default">新品</span></label>
              </li>
              <li id="li_2_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_1_${cur.id}" value="1" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_1_${cur.id}"><span class="ck-default">主销</span></label>
              </li>
              <li id="li_3_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_2_${cur.id}" value="2" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_2_${cur.id}"><span class="ck-default">热销</span></label>
              </li>
              <li id="li_4_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_3_${cur.id}" value="3" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_3_${cur.id}"><span class="ck-default">特惠</span></label>
              </li>
              <li id="li_5_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_4_${cur.id}" value="4" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_4_${cur.id}"><span class="ck-default">工程机</span></label>
              </li>
              <li id="li_6_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_5_${cur.id}" value="5" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_5_${cur.id}"><span class="ck-default">限时抢购</span></label>
              </li>
              <li id="li_7_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_6_${cur.id}" value="6" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_6_${cur.id}"><span class="ck-default">团购</span></label>
              </li>
               <li id="li_8_${cur.id}" class="ck-li">
                <input type="checkbox" name="label_of_cate_${cur.id}" id="label_of_cate_7_${cur.id}" value="7" class="hidden-accessible" style="cursor:pointer;vertical-align:middle;" />
                <label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_7_${cur.id}"><span class="ck-default">精品</span></label>
              </li>
            </ul>
          </div>
        </div>
        <div style="float:right;">
        <a style="color: blue;cursor: pointer;" onclick="location.href='EcGoodsPrice.do?mod_id=${af.map.mod_id}&goods_id=${cur.id}';"></a>
		
         <a style="color: blue" href="javascript:loading();doNeedMethod(null, 'PdShow.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">详细</a>|
         <c:if test="${empty cur.map.is_pingbi}">
		<a style="color: blue;cursor: pointer;" onclick="location.href='PdShow.do?method=pingbi&mod_id=${af.map.mod_id}&goods_id_1=${cur.id}';">屏蔽商品	</a>
		</c:if>
		<c:if test="${not empty cur.map.is_pingbi}">
		 <a style="color: blue;cursor: pointer;" onclick="location.href='PdShow.do?method=huifu&mod_id=${af.map.mod_id}&goods_id_1=${cur.id}';">恢复屏蔽</a>	
		</c:if>
      </div>
      </div>
      <c:if test="${not vs.last}">
        <div style="height:40px;"></div>
      </c:if>
    </c:forEach>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PdShow.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("pd_sn_like", "${af.map.pd_sn_like}");
								pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
								pager.addHiddenInputs("label_of_cate", "${af.map.label_of_cate}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//alert("${popedom}");

	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		var id = "${cur.id}";
		var label_of_cate = "${cur.label_of_cate}";
		showCkboxEcho("label_of_cate_" + id, label_of_cate);
	</c:forEach>
});


function showCkboxEcho(name, value){
	var obj = document.getElementsByName(name);
	for ( var i = 0; i < obj.length; i++) {
		if (value == obj[i].value) {
			obj[i].checked = true;
			//obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-visited";
			var parId = obj[i].parentNode.id;
			$("span", "#" + parId).addClass("ck-visited");
		}
	}
}

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
//]]>
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
