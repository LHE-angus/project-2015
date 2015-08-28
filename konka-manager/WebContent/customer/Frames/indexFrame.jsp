<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${shop.customer_name}<c:if test="${role_id_eq_400}">（康佳专卖店）</c:if></title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/base.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/index.css" />
</head>
<body>
<script type="text/javascript">  
function resizeFrameHeight(iframe_id, offset, min_height) {
	var $document = $(window.frames[iframe_id].document);
	$("#" + iframe_id).height(Math.max(
			(min_height || 0), 
			$document.find("body").height(), 
			$document.children().height()) + (offset || 0)
		);
}


</script>


<div class="header clearfix">
    <div class="fl">
        <h1 class="header-logo"></h1>
        <h2 class="header-tit">康佳VIP销售服务系统</h2>
        <p class="header-wel">欢迎您：<c:if test="${not empty fgsName}">（${fgsName}）</c:if>${shop.customer_name}（${customerUserInfo.user_name}）<a href="${ctx}/customer/login.do?method=logout">【退出】</a></p>
    </div>
    <ul class="header-menu">
        <li><a class="header-menu01" href="Frames.do?method=index">系统首页</a></li>
        <li><a class="header-menu02" class="header-menu01" href="${ctx}/customer/manager/JxcPasswardUpdate.do?method=list&mod_id=199990000" target="mainFrame">修改密码</a></li>
        <li><a class="header-menu03" href="http://zx.konka.com" target="_blank">在线商城</a></li>
    </ul>
</div>
<div class="main clearfix">
    <div class="sidebar">
        <ul class="sidebar-menu">
            <li>
	                <div class="sidebar-menu-nav pinkbg">
	                    <h3><a href="../Indexs/index_buy.jsp" target="mainFrame" ><img src="../../styles/customer/images/menu01.png"/><p><font color="white">进货管理</font></p></a></h3>  
	                </div>
	                <div class="submenu-box">
	                    <div class="submenu">
	                        <div class="opa-submenu"></div>
	                        <h4><table width="100%"><tr><td><a href="##">康佳产品</a></td><td align="right" style="padding-right: 10px"><img class="menu_close" src="../../commons/ckeditor/skins/moono/images/close.png"/></td></tr></table></h4>
	                        <ul class="submenu-list clearfix">
	                            <li><a href="JxcKonkaOrderRegister.do?method=add&mod_id=105020200" target="mainFrame">在线下单</a></li> 
	                            <li><a href="JxcKonkaOrderRegister.do?mod_id=105020400" target="mainFrame">进货订单</a></li>
	                            <li><a href="JxcKonkaOrderRegister.do?is_th=1&mod_id=105020600"  target="mainFrame">退货订单</a></li>
	                            <li><a href="KonkaZles23ResultInfo.do?mod_id=105020700" target="mainFrame">集采查询</a></li>
	                            <li><a href="ChannelDataImport.do?mod_id=105020710" target="mainFrame">地采查询</a></li>
	                            <li><a href="KonkaJxcBaseAddr.do?mod_id=105020800" target="mainFrame">收货地址</a></li>
	                            <li><a href="../KonkaOrderDetails/list.jsp?mod_id=105020900" target="mainFrame">订单明细</a></li>
	                            <li><a href="KonkaOrderInfoTrans.do?mod_id=105020910" target="mainFrame">发货确认</a></li>
	                            <li><a href="KonkaOrderPlan.do?mod_id=105020920" target="mainFrame">进货计划</a></li>
	                        </ul>
	                        <h4><a href="##">其他产品</a></h4>
	                        <ul class="submenu-list clearfix">
	                            <li><a href="JBill.do?bill_type=10&mod_id=105040400" target="mainFrame">进货登记</a></li>
	                            <li><a href="JBill.do?method=listForOther&mod_id=105040600&bill_type=10" target="mainFrame">进货查询</a></li>
	                            <li><a href="JBill.do?bill_type=11&mod_id=105040800" target="mainFrame">退货登记</a></li>
	                            <li><a href="JBill.do?method=listForOther&mod_id=105040900&bill_type=11" target="mainFrame">退货查询</a></li>
	                        </ul>
	                    </div>
	                </div>
            </li>
            <li>
                <div class="sidebar-menu-nav redbg">
                    <h3><a href="../Indexs/index_jbill.jsp" target="mainFrame" ><img src="../../styles/customer/images/menu02.png" /><p><font color="white">销售管理</font></p></a></h3>
                </div>
                <div class="submenu-box">
                    <div class="submenu">
                        <div class="opa-submenu"></div>
                        <h4><table width="100%"><tr><td><a href="##">代理分销</a></td><td align="right" style="padding-right: 10px"><img class="menu_close" src="../../commons/ckeditor/skins/moono/images/close.png"/></td></tr></table></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="JSubSellRec.do?bill_type=40&mod_id=110020200" target="mainFrame">分销登记</a></li>
                            <li><a href="JSubSellRec.do?bill_type=42&mod_id=110020210" target="mainFrame">分销退货</a></li>
                            <li><a href="JSubSellConfirm.do?mod_id=110020400" target="mainFrame">分销入库</a></li>
                            <li><a href="JSubSellRec.do?method=list&mod_id=110020600" target="mainFrame">分销明细</a></li>
                            <li><a href="JSubSellRec.do?method=listForPrint&mod_id=110020800" target="mainFrame">分销查询</a></li>
                            <li><a href="JSubSellRec.do?method=listForConfirm&mod_id=110020500" target="mainFrame">入库查询</a></li>
                        </ul>
                        <h4><a href="##">零售管理</a></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="JBill.do?bill_type=20&mod_id=110040400" target="mainFrame">零售登记</a></li>
                            <li><a href="JBill.do?bill_type=21&mod_id=110040800" target="mainFrame">零售退货</a></li>
                            <li><a href="JBill.do?method=list&mod_id=110040600" target="mainFrame">零售查询</a></li>
                            <li><a href="JBill.do?method=listForPrint&mod_id=110040500" target="mainFrame">零售明细</a></li>
                            <li><a href="KonkaMobileSailData.do?mod_id=110041000" target="mainFrame">零售数据</a></li>
                        </ul>
                        <c:if test="${role_id_eq_400}">
	                       <h4><a href="##">专卖店管理</a></h4>
	                       <ul class="submenu-list clearfix">
	                           <li><a href="KonkaXxZmdAddSalesOrder.do?mod_id=110990200" target="mainFrame">销售开单</a></li>
	                           <li><a href="KonkaXxZmdSearchSalesOrder.do?mod_id=110990400" target="mainFrame">销售查询</a></li>
	                           <li><a href="KonkaXxZmdDztzSearch.do?mod_id=110990600" target="mainFrame">台账查询</a></li>
	                           <li><a href="KonkaXxPrintReceipt.do?mod_id=110990800" target="mainFrame">打印收款单</a></li>
	                           <li><a href="KonkaXxSellBillCstSatform.do?mod_id=110991000" target="mainFrame">回访确认</a></li>
	                           <li><a href="KonkaXxZmdSellJsSearch.do?mod_id=110991200" target="mainFrame">佣金查询</a></li>
	                       </ul>
                        </c:if>
                    </div>
                </div>
            </li>
            <li>
            	
                <div class="sidebar-menu-nav greenbg">
                    <h3><a href="../Indexs/index_customer.jsp" target="mainFrame" ><img src="../../styles/customer/images/menu03.png" /><p><font color="white">客户管理</font></p></a></h3>
                </div>
                <div class="submenu-box">
                    <div class="submenu">
                        <div class="opa-submenu"></div>
                        <h4><table width="100%"><tr><td><a href="##">客户管理</a></td><td align="right" style="padding-right: 10px"><img class="menu_close" src="../../commons/ckeditor/skins/moono/images/close.png"/></td></tr></table></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="KonkaR3Store.do?mod_id=103020000" target="mainFrame">我的门店</a></li>
                            <li><a href="AgentsList.do?mod_id=103060000" target="mainFrame">我的网点</a></li>
                            <li><a href="JBasePartner.do?mod_id=103080000" target="mainFrame">我的供应商</a></li>
                            <li><a href="ConsumerInfo.do?mod_id=103040000" target="mainFrame">消费者信息库</a></li>
                            <li><a href="KonkaR3Target.do?mod_id=103090000" target="mainFrame">目标管理</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <div class="sidebar-menu-nav bluebg">
                    <h3><img src="../../styles/customer/images/menu04.png" /><p>库存管理</p></h3>
                </div>
                <div class="submenu-box">
                    <div class="submenu">
                        <div class="opa-submenu"></div>
                        <h4><table width="100%"><tr><td><a href="##">库存管理</a></td><td align="right" style="padding-right: 10px"><img class="menu_close" src="../../commons/ckeditor/skins/moono/images/close.png"/></td></tr></table></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="KonkaStockInventory.do?mod_id=115020000" target="mainFrame">库存盘点</a></li>
                            <li><a href="JStockInventory.do?mod_id=115030000" target="mainFrame">月度库存盘点</a></li>
                            <li><a href="KonkaR3ShopStock.do?method=view_new&mod_id=115070000" target="mainFrame">库存查询</a></li>
                            <li><a href="JBaseGoodsInitStock.do?mod_id=115080000" target="mainFrame">初始库存</a></li>
                            <li><a href="JxcOutInDetail.do?type=10&mod_id=115090100" target="mainFrame">库存转仓</a></li>
                            <li><a href="JxcOutInDetail.do?type=1&mod_id=115090200" target="mainFrame">库存调拨</a></li>
                            <li><a href="JxcOutInDetail.do?method=confirmList&type=1&mod_id=115090300" target="mainFrame">调拨确认</a></li>
                            <li><a href="JxcFifoStocks.do?method=listAccount&mod_id=115070000" target="mainFrame">库存查询（先进）</a></li>
                            <li><a href="JxcFifoStocks.do?method=listDetails&mod_id=115070000" target="mainFrame">库存明细（先进）</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <div class="sidebar-menu-nav redbg">
                    <h3><img src="../../styles/customer/images/menu05.png" /><p>统计报表</p></h3>
                </div>
                <div class="submenu-box">
                    <div class="submenu">
                        <div class="opa-submenu"></div> 
                        <h4><table width="100%"><tr><td><a href="##">统计报表</a></td><td align="right" style="padding-right: 10px"><img class="menu_close" src="../../commons/ckeditor/skins/moono/images/close.png"/></td></tr></table></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="JxcXsReport.do?flag_type=100&mod_id=120020000" target="mainFrame">销售毛利</a></li>
                            <li><a href="JxcXsReport.do?flag_type=200&mod_id=120040000"  target="mainFrame">客户销售</a></li>  
                            <li><a href="JxcXsReport.do?flag_type=300&mod_id=120060000" target="mainFrame">产品销售</a></li>
                            <li><a href="JxcXsReport.do?flag_type=400&mod_id=120080000" target="mainFrame">采购支出</a></li>
                            <li><a href="JxcXsReport.do?flag_type=500&mod_id=120100000" target="mainFrame">交易明细</a></li>
                            <li><a href="JxcXsReport.do?flag_type=600&mod_id=120120000" target="mainFrame">下级网点</a></li>
                            <li><a href="JsStocks.do?mod_id=199022000" target="mainFrame">结算库存</a></li>
                            <li><a href="JsSells.do?mod_id=199023000" target="mainFrame">结算销售</a></li>
                        </ul>
                    </div>
                </div>
            </li>
            <!-- <li>
                <div class="sidebar-menu-nav yellowbg">
                    <h3><img src="../../styles/customer/images/menu06.png" /><p>平台管理</p></h3>
                </div>
                <div class="submenu-box">
                    <div class="submenu">
                        <div class="opa-submenu"></div>
                        <h4><a href="##">平台管理</a></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="PdShow.do?own_sys=2&mod_id=200000100" target="mainFrame">触网商品管理</a></li>
                            <li><a href="PshowOrderSend2.do?mod_id=200000402" target="mainFrame">订单明细查询</a></li>
                            <li><a href="PshowOrderConfirm.do?mod_id=200000500" target="mainFrame">订单确认收货</a></li>
                            <li><a href="PshowOrderTuihuo.do?mod_id=200000600" target="mainFrame">商品退换货</a></li>
                            <li><a href="TouchJieSuan.do?mod_id=200000700" target="mainFrame">订单结算</a></li>
                            <li><a href="TouchJieSuanRebates.do?mod_id=200000800" target="mainFrame">佣金查询</a></li>
                            <li><a href="UpDateBank.do?mod_id=200000900" target="mainFrame">个人信息修改</a></li>
                        </ul>
                    </div>
                </div>
            </li> -->
            <li>
                <div class="sidebar-menu-nav violetbg">
                    <h3><img src="../../styles/customer/images/menu07.png" /><p>账户设置</p></h3>
                </div>
                <div class="submenu-box">
                    <div class="submenu">
                        <div class="opa-submenu"></div>
                        <h4><table width="100%"><tr><td><a href="##">账号设置</a></td><td align="right" style="padding-right: 10px"><img class="menu_close" src="../../commons/ckeditor/skins/moono/images/close.png"/></td></tr></table></h4>
                        <ul class="submenu-list clearfix">
                            <li><a href="JBaseGoods.do?mod_id=199010100" target="mainFrame">商品数据</a></li>
                            <li><a href="JBaseType.do?method=list&par_id=10002&mod_id=199010200" target="mainFrame">商品单位</a></li>
                            <li><a href="JBaseType.do?method=list&par_id=10001&mod_id=199010300" target="mainFrame">商品类型</a></li>
                            <li><a href="JBaseStore.do?mod_id=199010500" target="mainFrame">仓库维护</a></li>
                            <li><a href="JsTimes.do?mod_id=199021000" target="mainFrame">结算时间</a></li>
                            <li><a href="JxcUserInfoUpdate.do?mod_id=199980000" target="mainFrame">我的资料</a></li>
                            <li><a href="JxcUserPos.do?mod_id=199981000" target="mainFrame">我的位置</a></li>
                            <li><a href="JxcPasswardUpdate.do?mod_id=199990000" target="mainFrame">修改密码</a></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <div class="content-box" style="height: 633px;">               
          <iframe name="mainFrame" id="mainFrame" src="${ctx}/customer/manager/Frames.do?method=main2" frameborder="0" width="100%" height="100%" scrolling="auto" style="margin:0;padding:0;" onload="resizeFrameHeight('mainFrame', -2, $(window).height());" onresize="this.onload();" ></iframe>  
    </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script>
$(function(){
    /*top show or hide*/
    if($("body").innerWidth()<1230){
        $(".header-tit").css("display","none");
    }
    $(window).resize(function(){
        $(".sidebar").css("height",$(".main").height());//sidebar height
        if($("body").innerWidth()<1230){
            $(".header-tit").css("display","none");
        }else{
            $(".header-tit").css("display","block");
        }
    });
    /*sidebar*/
    $(".sidebar").css("height",$(".main").height());//sidebar height
    var $docHeight = $("body").outerHeight();
    var $liHeight = $(".sidebar li").height();
    $(".submenu").each(function(){
        var $thisHeight = $(this).outerHeight();
        var $thisTop = $(this).offset().top;
        if($thisHeight+$thisTop>$docHeight){
            $(this).parent().css({"top":$liHeight-$thisHeight+"px"});
            if($(this).parent().offset().top<0){
                $(this).parent().css({"top":"0"});
            }
        }
    });
    var $submenuBoxW = $(".submenu").outerWidth()+"px";
    $(".sidebar-menu").children("li").hover(function(){
        $(this).addClass("current").find(".submenu-box").stop().animate({"width":$submenuBoxW},200);
    },function(){
        $(this).removeClass("current").find(".submenu-box").stop().animate({"width":"0"},100);
    });

    $(".menu_close").click(function(){
        $(".current").removeClass("current").find(".submenu-box").stop().animate({"width":"0"},100);
    });
});
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
