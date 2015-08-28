<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>接口实验室</title>
<style type="text/css">
h1 {text-align:center; background-color: yellow; border: 1px ;color: red ; border-style: solid;}
li {line-height:30px;}
</style>
</head>
<body>
<h1>接口实验室</h1>
<ol>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list1">R/3客户基础数据</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list2">R/3客户合作伙伴信息数据</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list3">R/3客户分类查询  </a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list4">产品基础信息查询</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list5">客户账期(信贷)查询</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list6">客户应收回款查询</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list7">库存查询(KCXX)</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list8">订单明细(用于分公司-客户订单统计)</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list9">分公司现款价查询</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list10">分公司库存查询(分公司90正品仓和物流仓的库存信息,用于发货的库存)</a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list11">分公司库存查询(Y/P/Q/D) </a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list12">订单明细(包含交货明细信息) </a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list13">产品存销分析 </a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list15">产品利润分析 </a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list14">分公司调拨计划评估 </a></li>
<li><a href="${ctx}/webservice/R3InterfaceTest.do?method=list16">集采数据 </a></li>
</ol>
<div> 
使用说明：<br />
1、下载文件<a href="R3InterfaceShowPage/librfc32.dll">librfc32.dll</a>和文件<a href="R3InterfaceShowPage/sapjcorfc.dll">sapjcorfc.dll</a>；<br />
2、将下载的两个dll文件放到 C:\Windows\System32；<br />
3、<a href="R3InterfaceShowPage/lib.zip">下载jar包</a>，下载jar包解压缩到\WEB-INF\lib；<br />
4、重建应用，启动。<br />
</div>
</body>
</html>