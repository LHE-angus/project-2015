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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.webinput {background:#f5f4f4;padding-left: 5px;height: 19px;line-height: 19px;/*font-family: Arial, Helvetica, sans-serif;*/border: #ccc solid 1px;}

.ck-li{position:relative;height:22px;padding:1px auto;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}

.navClass {background-color:#CCC;border-collapse:collapse;}
.navClass th {background:#F6F6F6;color:#C4C4C4;font-size:12px;font-weight:bold;height:20px;line-height:20px;text-align:center;padding:2px;border:1px solid #CCC;}
.navClass td {padding:3px;height:18px;background-color:#fff;border:1px solid #CCC;}

.xubox_close{position:absolute;}
.xubox_close1_0{ right:-25px; top:-16px; width:33px; height:31px; background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -182px no-repeat; cursor:pointer; overflow:hidden;}
.xubox_close1_0:hover{background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -215px no-repeat;}
.xubox_border{border-radius:5px;position:absolute;}

.main_box{position:relative;width:260px;z-index:0;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.box{position:relative;width:130px;z-index:0;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.pic_box{text-align:center;;z-index:1;background:#FFFFFF;margin:5px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">标题：</td>
        <td align="left">${fn:escapeXml(af.map.title)}</td>
      </tr>
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">活动简介：</td>
        <td align="left">${af.map.brief}</td>
      </tr>
       <tr>
        <td width="12%" class="title_item" nowrap="nowrap">拍卖介绍：</td>
        <td align="left">${af.map.content}</td>
      </tr>
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">拍品规格参数：</td>
        <td align="left">${af.map.auction_spec}</td>
      </tr>
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">活动详情：</td>
        <td align="left">${af.map.auction_memo}</td>
      </tr>
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">参考价：</td>
        <td align="left">${af.map.price}</td>
      </tr>
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">起拍价：</td>
        <td align="left">${af.map.auction_price}</td>
      </tr>
      <tr>
        <td width="12%" class="title_item" nowrap="nowrap">最低加价幅度：</td>
        <td align="left">${af.map.add_price}</td>
      </tr>
      <tr>
        <td class="title_item">主图：</td>
        <td><c:if test="${not empty af.map.id}">
            <div id="main_pic_div" class="main_box">
              <div class="pic_box"> <a id="main_pic_a" href="${ctx}/${af.map.main_pic_file}" title="用创新赢的尊严！"> <img src="${ctx}/${fn:substringBefore(af.map.main_pic_file, '.')}_240.jpg" /> </a>
                <html-el:hidden property="main_pic_hidden" styleId="main_pic_hidden" value="${af.map.main_pic_file}" />
                <!--<a class="xubox_close xubox_close1_0" href="javascript:delPic('main_pic_div');"></a>-->
              </div>
              <!--<div id="xubox_border4" class="xubox_border" style="z-index:-1;border:1px solid #CCC;top:-5px;left:-4px;width:268px;height:500px;border:1px solid rgba(0,0,0,0);-webkit-box-shadow:0 0 3px rgba(0,0,0,0.4);-moz-box-shadow:0 0 1px rgba(0,0,0,0.4);box-shadow:0 0 3px rgba(0,0,0,0.4); "></div>-->
            </div>
          </c:if>
        </td>
      </tr>
      <tr>
        <td class="title_item">图片：</td>
        <td align="left"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <c:if test="${not empty picList}">
              <!-- 已上传图片显示区 -->
              <tr>
                <td><ul style="display:block;list-style-type:none;">
                    <c:forEach items="${picList}" var="cur" varStatus="vs">
                      <li id="pic_${vs.count}_li" class="box" style="display:inline-table;margin:5px 0px 5px 20px">
                        <div class="pic_box"> <a rel="group" href="${ctx}/${cur}" title="康佳彩电"> <img src="${ctx}/${fn:substringBefore(cur, '.')}_120.jpg" /> </a>
                          <html-el:hidden property="pic_hidden" styleId="pic_hidden" value="${cur}" />
                          <!--<a class="xubox_close xubox_close1_0" href="javascript:delPic('pic_${vs.count}_li');"></a>-->
                        </div>
                      </li>
                    </c:forEach>
                  </ul></td>
              </tr>
            </c:if>
          </table></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">关联商品名称</td>
        <td align="left">${af.map.goods_name}</td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">拍卖开始时间</td>
        <td align="left"><fmt:formatDate value="${af.map.auction_start_time}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">拍卖结束时间</td>
        <td align="left"><fmt:formatDate value="${af.map.auction_end_time}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
      </tr>
       <tr>
        <td nowrap="nowrap" class="title_item">下单截止时间</td>
        <td align="left"><fmt:formatDate value="${af.map.auction_order_time}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">是否发布:</td>
        <td align="left"><c:if test="${af.map.is_pub eq 0}">未发布</c:if>
        <c:if test="${af.map.is_pub eq 1}">已发布</c:if>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">所属系统:</td>
        <td align="left"><c:if test="${af.map.own_sys eq 1}">工卡</c:if>
        <c:if test="${af.map.own_sys eq 2}">触网</c:if>
        <c:if test="${af.map.own_sys eq 3}">会员</c:if>
        <c:if test="${af.map.own_sys eq 4}">顺丰</c:if>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">排序值</td>
        <td align="left">${af.map.order_value}</td>
      </tr>
      <c:if test="${not empty ecAuctionMain.ecAuctionBuyList}">
      <tr>
      	 <td nowrap="nowrap" class="title_item" colspan="2" align="center">购买记录</td> 
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item" colspan="2">
        	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        		<tr class="tabtt1">
        			<td width="5%" nowrap="nowrap" align="center">序号</td>
        			<td width="8%" nowrap="nowrap" align="center">标题</td>
        			<td width="8%" nowrap="nowrap" align="center">所属系统</td>
        			<td width="8%" nowrap="nowrap" align="center">真实姓名</td>
        			<td width="8%" nowrap="nowrap" align="center">是否竞拍成功</td>
        			<td width="8%" nowrap="nowrap" align="center">购买时间</td>
        		</tr>
        		<c:forEach items="${ecAuctionMain.ecAuctionBuyList}" var="cur" varStatus="vs">
		        <tr>
		          <td align="center" nowrap="nowrap">${vs.count}</td>
		          <td align="center" nowrap="nowrap">${cur.title}</td>
		          <td align="center" nowrap="nowrap">
		          <c:if test="${cur.own_sys eq 1}">工卡</c:if>
		          <c:if test="${cur.own_sys eq 2}">触网</c:if>
		          <c:if test="${cur.own_sys eq 3}">会员</c:if>
		          <c:if test="${cur.own_sys eq 4}">顺丰</c:if> 
		          </td>
		           <td align="center" nowrap="nowrap">${cur.real_name}</td>
		           <td align="center" nowrap="nowrap"><c:if test="${cur.is_success eq 0}">失败</c:if>
		           <c:if test="${cur.is_success eq 1}">成功</c:if>
		           </td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		        </tr>
		        </c:forEach>
        	</table>
        </td>
      </tr>
      </c:if>
      <tr>
        <td class="title_item" nowrap="nowrap">浏览次数：</td>
        <td  align="left">${af.map.view_counts}</td>
      </tr>
      <tr>
        <td align="center" colspan="2"><input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
        </td>
      </tr>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){


});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
