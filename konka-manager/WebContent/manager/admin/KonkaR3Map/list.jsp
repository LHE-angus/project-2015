<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/map/images/yi_base.css" type=text/css rel=stylesheet />
<link href="${ctx}/styles/map/images/yi_index.css" type=text/css rel=stylesheet />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td align="left">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <div id=wrapper><!-- header end-->
      <div id=main>
        <div id=map>
          <h1 style="font-size: 14px; margin: 90px 0px 0px 260px">请选择分公司：</h1>
          <h1 class=mapr style="margin: 140px 0px 0px 380px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=2">北京 </a></h1>
          <h1 class=mapr style="margin: 100px 0px 0px 446px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=3">长春 </a></h1>
          <h1 class=mapr style="margin: 165px 0px 0px 305px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=15">呼和浩特 </a></h1>
          <h1 class=mapr style="margin: 230px 0px 0px 250px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=815">德阳 </a></h1>
          <h1 class=mapr style="margin: 218px 0px 0px 265px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=815">绵阳 </a></h1>
          <h1 class=mapr style="margin: 230px 0px 0px 275px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=972">南充 </a></h1>
          <h1 class=mapr style="margin: 265px 0px 0px 285px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=42">重庆 </a></h1>
          <h1 class=mapr style="margin: 265px 0px 0px 260px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=21">内江 </a></h1>
          <h1 class=mapr style="margin: 145px 0px 0px 435px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=799">大连 </a></h1>
          <h1 class=mapr style="margin: 330px 0px 0px 380px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=8">广州 </a></h1>
          <h1 class=mapr style="margin: 295px 0px 0px 295px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=9">贵阳 </a></h1>
          <h1 class=mapr style="margin: 369px 0px 0px 348px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=11">海口 </a></h1>
          <h1 class=mapr style="margin: 85px 0px 0px 440px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=10">哈尔滨 </a></h1>
          <h1 class=mapr style="margin: 242px 0px 0px 458px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=12">杭州 </a></h1>
          <h1 class=mapr style="margin: 260px 0px 0px 452px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=34">温州 </a></h1>
          <h1 class=mapr style="margin: 310px 0px 0px 237px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=19">昆明 </a></h1>
          <h1 class=mapr style="margin: 190px 0px 0px 255px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=20">兰州 </a></h1>
          <h1 class=mapr style="margin: 245px 0px 0px 260px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=5">成都 </a></h1>
          <h1 class=mapr style="margin: 245px 0px 0px 350px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=18">荆州</a></h1>
          <h1 class=mapr style="margin: 285px 0px 0px 385px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=14">衡阳</a></h1>
          <h1 class=mapr style="margin: 282px 0px 0px 355px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=979">邵阳</a></h1>
          <h1 class=mapr style="margin: 260px 0px 0px 405px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=22">南昌 </a></h1>
          <h1 class=mapr style="margin: 219px 0px 0px 394px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=13">合肥 </a></h1>
          <h1 class=mapr style="margin: 229px 0px 0px 418px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=36">芜湖 </a></h1>
          <h1 class=mapr style="margin: 214px 0px 0px 423px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=23">南京 </a></h1>
          <h1 class=mapr style="z-index: 99; margin: 320px 0px 0px 390px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=6">东莞 </a></h1>
          <h1 class=mapr style="margin: 225px 0px 0px 460px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=27">上海 </a></h1>
          <h1 class=mapr style="margin: 326px 0px 0px 415px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=28">深圳 </a></h1>
          <h1 class=mapr style="margin: 120px 0px 0px 440px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=29">沈阳 </a></h1>
          <h1 class=mapr style="margin: 110px 0px 0px 400px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=17">锦州 </a></h1>
          <h1 class=mapr style="margin: 191px 0px 0px 355px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=41">郑州 </a></h1>
          <h1 class=mapr style="margin: 180px 0px 0px 328px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=32">太原 </a></h1>
          <h1 class=mapr style="margin: 155px 0px 0px 400px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=33">天津 </a></h1>
          <h1 class=mapr style="margin: 155px 0px 0px 365px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=30">石家庄 </a></h1>
          <h1 class=mapr style="margin: 170px 0px 0px 380px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=826">邯郸 </a></h1>
          <h1 class=mapr style="margin: 140px 0px 0px 355px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=48">保定 </a></h1>
          <h1 class=mapr style="margin: 145px 0px 0px 125px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=35">乌鲁木齐 </a></h1>
          <h1 class=mapr style="margin: 240px 0px 0px 379px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=37">武汉 </a></h1>
          <h1 class=mapr style="margin: 265px 0px 0px 370px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=4">长沙 </a></h1>
          <h1 class=mapr style="margin: 198px 0px 0px 300px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=38">西安 </a></h1>
          <h1 class=mapr style="margin: 305px 0px 0px 445px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=39">厦门 </a></h1>
          <h1 class=mapr style="margin: 203px 0px 0px 438px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=25">南通 </a></h1>
          <h1 class=mapr style="margin: 189px 0px 0px 418px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=40">徐州 </a></h1>
          <h1 class=mapr style="margin: 205px 0px 0px 330px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=26">南阳 </a></h1>
          <h1 class=mapr style="margin: 342px 0px 0px 370px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=930">佛山 </a></h1>
          <h1 class=mapr style="margin: 170px 0px 0px 406px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=16">济南 </a></h1>
          <h1 class=mapr style="margin: 282px 0px 0px 455px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=7">福州 </a></h1>
          <h1 class=mapr style="margin: 335px 0px 0px 315px"><a href="${ctx}/manager/admin/KonkaR3MmtMatch.do?mod_id=101010&dept_id=24">南宁 </a></h1>
        </div>
      </div>
    </div>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
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
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>