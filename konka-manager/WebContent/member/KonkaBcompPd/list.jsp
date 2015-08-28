<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/slider.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<style type="text/css">

#topFilterSelected a {
display: inline;
float: left;
height: 18px;
line-height: 22px\9;
_line-height: 18px;
margin: 0px 4px 5px 0px;;
padding: 0 0 0 6px;
color: #fff;
white-space: nowrap;
background: url(${ctx}/images/repeatX.png) repeat-x 0 -120px;
border-left: 1px solid #fe6e0d;
border-bottom: 1px solid #ff6501;
border-radius: 3px;
}

#topFilterSelected a em {
cursor: pointer;
float: left;
font-style: normal;
}

#topFilterSelected a i {
cursor: pointer;
display: inline;
float: left;
width: 19px;
height: 19px;
background: url(${ctx}/images/topF.png) no-repeat;
background: url(${ctx}/images/topFIe.png) no-repeat\9;
}

.i {
color: #f60;
font-style: normal;
}

#resetFilter {
float: left;
margin-top: 1px;
margin-top: 3px\9;
_margin-top: 1px;
white-space: nowrap;
}

.buttonPBSearch  {
width:46px;
height:20px;
repeat-x;
font:normal 12px/20px "宋体";
text-align:center;
color:#fff;
border:1px #ccc solid;
border-left:0;
background: #F00B0B;
}
</style>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- top end -->  
<!-- first end -->
<!-- second start -->
<div class="maincont margintop10">
  <!--left-->
  <div class="listleft">
    <div class="liscont1">
      <div class="listit1">推荐热卖</div>
      <div class="listbox1">
        <ul class="listul4">
          <c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
          	<c:set var="li_class" value="" />
            <c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>
            <li style="padding:0;" class="${li_class}">
              <div class="listpic1 box_117_80" style="height:110px;">
              	<table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><img src="${ctx}/${cur.main_pic}" align="absmiddle"  width="100"/></a></td></tr></table>
              </div>
              <div class="listrxt1">
                <h3><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><font style="font-size:13px;"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}<br/><c:if test="${not empty cur.pd_size }">${fn:escapeXml(cur.pd_size)}英寸</c:if></a></h3>
                <h4>价格：￥<font style="font-size:18px;"><c:if test="${ecUser.user_type eq 1 }"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></c:if>
                <c:if test="${ecUser.user_type eq 2 }"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></c:if></font></h4>
              </div>
              <div class="clear"></div>
            </li>
          </c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <!--right-->
  <div class="listright">
    <div class="liscont2">
      <div class="listbox2">
        <div class="listbox3">
          <form id="listForm" name="listForm" method="post" action="KonkaBcompPd.do" >
            <input type="hidden" name="method" value="list" />
            <input type="hidden" name="prod_type" id="prod_type" value="${fn:escapeXml(af.map.prod_type)}"/>
            <input type="hidden" name="pd_type" id="pd_type" value="${fn:escapeXml(af.map.pd_type)}"/>
            <input type="hidden" name="pd_size_type" id="pd_size_type" value="${fn:escapeXml(af.map.pd_size_type)}"/>
            <input type="hidden" name="label_of_cate" id="label_of_cate" value="${fn:escapeXml(af.map.label_of_cate)}"/>
            <input type="hidden" name="pd_price" id="pd_price" value="${fn:escapeXml(af.map.pd_price)}"/>
            <input type="hidden" name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" value="${fn:escapeXml(af.map.pd_sn_or_pd_name_like)}"/>
            <input type="hidden" name="order_by_sale_num_desc" id="order_by_sale_num_desc"  value="${fn:escapeXml(af.map.order_by_sale_num_desc)}"/>
            <input type="hidden" name="order_by_price_desc" id="order_by_price_desc" value="${fn:escapeXml(af.map.order_by_price_desc)}" />
            <input type="hidden" name="order_by_price_asc" id="order_by_price_asc" value="${fn:escapeXml(af.map.order_by_price_asc)}"/>
            <input type="hidden" name="brand_name" id="brand_name"  value="${fn:escapeXml(af.map.brand_name)}"/>
            <h3 class="lsitit2">商品筛选    <c:if test="${not empty af.map.prod_type  }">-</c:if> <c:if test="${empty af.map.prod_type}">全部</c:if><c:if test="${af.map.prod_type eq '0'  }">电视</c:if><c:if test="${af.map.prod_type eq 1  }">白电</c:if>
            <c:if test="${af.map.prod_type eq 4  }">冰箱</c:if><c:if test="${af.map.prod_type eq 5  }">洗衣机</c:if><c:if test="${af.map.prod_type eq 6  }">空调</c:if>
            <c:if test="${af.map.prod_type eq 3  }">生活电器</c:if><c:if test="${af.map.prod_type eq 7  }">手机</c:if><c:if test="${af.map.prod_type eq 9  }">笔记本</c:if><c:if test="${af.map.prod_type eq 10  }">配件</c:if><c:if test="${af.map.prod_type eq 21  }">食品饮料</c:if><c:if test="${af.map.prod_type eq 22  }">游戏设备</c:if> </h3>
            <ul class="listul5"> 
          	 <c:if test="${af.map.prod_type eq 0 or empty af.map.prod_type }"> <li><strong>产品品牌：</strong> <a href="#" ${empty af.map.brand_name ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="brand_name_class" title="">全部</a><a href="#" ${af.map.brand_name eq 'KONKA' ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="brand_name_class" title="KONKA,康佳">KONKA</a> <a href="#" ${af.map.brand_name eq 'KKTV' ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="brand_name_class" title="KKTV,康佳">KKTV</a> <a href="#" ${af.map.brand_name eq 'HYUNDAI' ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="brand_name_class" title="HYUNDAI,康佳">韩国现代</a></li>
          	  <li><strong>产品品类：</strong> <a href="#" ${empty af.map.pd_type ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_type_class" title="">全部</a><a href="#" ${af.map.pd_type eq 4 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_type_class" title="4,4K电视">4K电视</a> <a href="#" ${af.map.pd_type eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_type_class" title="1,3D电视">3D电视</a><a href="#" ${af.map.pd_type eq 2 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_type_class" title="2,智能电视">智能电视</a> <a href="#" ${af.map.pd_type eq 9 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_type_class" title="9,其他">其他</a> </li>
              <li><strong>屏幕尺寸：</strong> 
              <a href="#" ${empty af.map.pd_size_type ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="">全部</a> <a href="#" ${af.map.pd_size_type eq 31 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="31,32英寸以下">32英寸以下</a> 
              <a href="#" ${af.map.pd_size_type eq 32 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="32,32英寸">32英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 37 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="37,37英寸">37英寸</a>
              <a href="#" ${af.map.pd_size_type eq 39 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="39,39/40英寸">39/40英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 42 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="42,42英寸">42英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 46 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="46,46英寸">46英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 47 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="47,47英寸">47英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 48 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="48,48英寸">48英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 49 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="49,49/50英寸">49/50英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 55 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="55,55英寸">55英寸</a> 
              <a href="#" ${af.map.pd_size_type eq 58 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_size_type_class" title="59,58英寸以上">58英寸以上</a> </li>
              </c:if> <li><strong>分类标签：</strong> <a href="#" ${empty af.map.label_of_cate ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_of_cate_class" title="">全部</a> 
              <a href="#" ${not empty af.map.label_of_cate and af.map.label_of_cate eq 0 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_of_cate_class" title="0,新品">新品</a>
              <a href="#" ${af.map.label_of_cate eq 7 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_of_cate_class" title="7,精品">精品</a>
              <a href="#" ${af.map.label_of_cate eq 2 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_of_cate_class" title="2,热销">热销</a>
              <a href="#" ${af.map.label_of_cate eq 3 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_of_cate_class" title="3,特惠">特惠</a> </li>
              <li><strong>价 &nbsp;&nbsp; 格：</strong> <a href="#" ${empty af.map.pd_price ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="">全部</a> <a href="#" ${af.map.pd_price eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="1,0 - 1000">0 - 1000</a> <a href="#" ${af.map.pd_price eq 2 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="2,1000 - 2000">1000 - 2000</a> <a href="#" ${af.map.pd_price eq 3 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="3,2000 - 3000">2000 - 3000</a> 
              <a href="#" ${af.map.pd_price eq 4 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="4,3000 - 5000">3000 - 5000</a> 
              <a href="#" ${af.map.pd_price eq 5 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="5,3000 - 5000">5000 - 10000</a> 
   			  <a href="#" ${af.map.pd_price eq 6 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="pd_price_class" title="6,10000以上">10000以上</a> 
              </li>
              <li><strong>排 &nbsp;&nbsp; 序：</strong> <a href="#" ${not empty af.map.order_by_sale_num_desc ? "style='background: #FF0000;color:#fff;padding:2px;'":""} class="sale_num_class" title="">销量</a>
              <a href="#" ${not empty af.map.order_by_price_asc or not empty  af.map.order_by_price_desc ? "style='background: #FF0000;color:#fff;padding:2px;'":""} class="order_price_class" title="">价格
               ${not empty af.map.order_by_price_asc ?"↑":""} ${not empty af.map.order_by_price_desc ?"↓":""}</a>
              <c:if test="${af.map.prod_type eq 9  }"><font color="blue" style="margin-left:40px;">DELL客服       电话: 0592-8185452 QQ：314132043</font></c:if>
              </li>
            </ul>
          </form>
        </div>
        <div class="clear"></div>
      </div>
    </div>
    <div class="clear"></div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabs_list2 margintop10">
      <tbody>
        <tr>
          <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <td><div class="listul2">
              <div class="rpic2 box"><a title="" href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><img alt="" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" align="absmiddle" style="max-height:216px;" width="288"/></a></div>
              <div class="ltt2" ><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"  target="_blank" title="${fn:escapeXml(cur.pd_name)}">
               <font style="font-szie:13px;">	  <c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI</c:if>
               <c:if test="${fn:contains(cur.pd_name, '戴尔')}">戴尔</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI') and not fn:contains(cur.pd_name, '戴尔') and prod_type eq 0}">康佳</c:if>
               &nbsp;${fn:escapeXml(cur.pd_sn)}  <c:if test="${not empty cur.pd_size }">&nbsp;${fn:escapeXml(cur.pd_size)}英寸</c:if><br/></font>
               <font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 18, '')}</font></a></div>
              <div class="ltt3"><c:if test="${ecUser.user_type eq 1 }">
                <p style="color:#666666;"><font color="#d40207" style="font-size:12px;">￥<a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"  target="_blank" style="color:red;font-size:18px;font-family:arial,宋体b8b\4f53;"><fmt:formatNumber value="${cur.map.price}" pattern="0" />
                </a></font>&nbsp; <del >市场价：￥<fmt:formatNumber value="${cur.map.original_price}" pattern="0" /></del></c:if>
                <c:if test="${ecUser.user_type eq 2 }">
                <p style="color:#666666;"><font color="#d40207" style="font-size:12px;">￥<a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" style="color:red;font-size:18px;font-family:arial,宋体b8b\4f53;"><fmt:formatNumber value="${cur.map.original_price}" pattern="0" />
                </a></font>&nbsp; </c:if>
                &nbsp;&nbsp;<font color="#005AA0" >销量：${cur.sale_num}件</font></p>
              </div>
            </div></td>
          <c:if test="${vs.count mod 3 eq 0 and not vs.last}">
        </tr>
        <tr></c:if>
          </c:forEach>
        </tr>
      </tbody>
    </table>
    <!--page-->
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaBcompPd.do">
      <div class="pagebox">
        <script type="text/javascript" src="${ctx}/commons/scripts/pager3.js">;</script>
        <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("pd_type", "${fn:escapeXml(af.map.pd_type)}");
			pager.addHiddenInputs("pd_size_type", "${fn:escapeXml(af.map.pd_size_type)}");
			pager.addHiddenInputs("label_of_cate", "${fn:escapeXml(af.map.label_of_cate)}");
			pager.addHiddenInputs("pd_price", "${fn:escapeXml(af.map.pd_price)}");
			pager.addHiddenInputs("pd_sn_or_pd_name_like", "${fn:escapeXml(af.map.pd_sn_or_pd_name_like)}");
			pager.addHiddenInputs("order_by_sale_num_desc", "${fn:escapeXml(af.map.order_by_sale_num_desc)}");
			pager.addHiddenInputs("order_by_price_desc", "${fn:escapeXml(af.map.order_by_price_desc)}");
			pager.addHiddenInputs("order_by_price_asc", "${fn:escapeXml(af.map.order_by_price_asc)}");
			pager.addHiddenInputs("prod_type", "${fn:escapeXml(af.map.prod_type)}");
			pager.addHiddenInputs("prod_name", "${fn:escapeXml(af.map.prod_name)}");
			pager.addHiddenInputs("brand_name", "${fn:escapeXml(af.map.brand_name)}"); 
			document.write(pager.toString());
		</script>
      </div>
    </form>
  </div>
  <div class="clear"></div>
</div>
<!-- second end -->
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var form = document.listForm;
	
	if($("#topFilter").children().length <= 2){
		$("#topFilter").attr("style","display:none");
	}else {
		$("#topFilter").attr("style","display:''");
	}
	
	//各类条件选择
	//-------------------------------------------
		//产品品类
	$(".brand_name_class").click(function(){
		$(".brand_name_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#brand_name").val(strs[0]);
		}else if(strs.length < 2){
			$("#brand_name").val("");
		}
		
		form.submit();
	});
	//产品品类
	$(".pd_type_class").click(function(){
		$(".pd_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#pd_type").val(strs[0]);
		}else if(strs.length < 2){
			$("#pd_type").val("");
		}
		
		form.submit();
	});
	
	//屏幕尺寸
	$(".pd_size_type_class").click(function(){
		$(".pd_size_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#pd_size_type").val(strs[0]);
		}else if(strs.length < 2){
			$("#pd_size_type").val("");
		}
		
		form.submit();
	});
	
	//分类标签
	$(".label_of_cate_class").click(function(){
		$(".label_of_cate_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_of_cate").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_of_cate").val("");
		}
		
		form.submit();
	});
	
	//价格
	$(".pd_price_class").click(function(){
		$(".pd_price_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#pd_price").val(strs[0]);
		}else if(strs.length < 2){
			$("#pd_price").val("");
		}
		
		form.submit();
	});
	//价格
	$(".sale_num_class").click(function(){
		$("#order_by_sale_num_desc").val("1");
		$("#order_by_price_desc").val("");
		$("#order_by_price_asc").val("");
		
		form.submit();
	});
	
	$(".order_price_class").click(function(){
		$("#order_by_sale_num_desc").val("");
		var price1=$("#order_by_price_asc").val();
		if(price1==""){
			$("#order_by_price_desc").val("");
			$("#order_by_price_asc").val("1");
		}else{
			$("#order_by_price_desc").val("1");
			$("#order_by_price_asc").val("");
		}
		form.submit();
	});
	
	//-------------------------------------------
	
	//去除已选择条件
	//----------------------------------------
	$("#filter_pd_type").click(function(){
		$("#pd_type").val("");
		form.submit();
	});
	$("#filter_pd_size_type").click(function(){
		$("#pd_size_type").val("");
		form.submit();
	});
	$("#filter_label_of_cate").click(function(){
		$("#label_of_cate").val("");
		form.submit();
	});
	$("#filter_pd_price").click(function(){
		$("#pd_price").val("");
		form.submit();
	});
	//----------------------------------------
	
	//重新筛选条件
	//----------------------------------------
	$("#resetFilter").click(function(){
		$("#pd_type").val("");
		$("#pd_size_type").val("");
		$("#label_of_cate").val("");
		$("#pd_price").val("");
		form.submit();
	});
	//----------------------------------------
});

//]]></script>
</body>
</html>
