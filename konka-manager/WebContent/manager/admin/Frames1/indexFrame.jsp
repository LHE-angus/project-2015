<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${fgs_name}管理端 - 康佳渠道管理系统</title>
<link href="${ctx}/commons/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/commons/scripts/jquery.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerWindow.js" type="text/javascript"></script>
<script src="${ctx}/commons/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script type="text/javascript">
var tab = null;
var accordion = null;
var tree = null;
$(function (){
    //布局
    $("#layout1").ligerLayout({ leftWidth: 190, height: '100%', onHeightChanged: f_heightChanged });

    var height = $(".l-layout-center").height();

    //Tab
    $("#framecenter").ligerTab({ height: height });

    //面板
    $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

    $(".l-link").hover(function (){
        $(this).addClass("l-link-over");
    }, function (){
        $(this).removeClass("l-link-over");
    });
    //树
    <c:forEach var="cur1" items="${sysModuleList}" varStatus="vs">
      <c:if test="${cur1.par_id eq 0}">
    $("#tree_${cur1.mod_id}").ligerTree({
        checkbox: false,
        slide: false,
        nodeWidth: 120,
        attribute: ['nodename', 'url'],
        onSelect: function (node)        {
            if (!node.data.url) return;
            var tabid = $(node.target).attr("tabid");
            if (!tabid)            {
                tabid = new Date().getTime();
                $(node.target).attr("tabid", tabid)
            }
            if ($(">ul >li", tab.tab.links).length >= 4)            {
                var currentTabid = $("li.l-selected", tab.tab.links).attr("tabid"); //当前选择的tabid
                if (currentTabid == "home") return;
                tab.overrideTabItem(currentTabid, { tabid: tabid, url: node.data.url, text: node.data.text });
                return;
            }
            f_addTab(tabid, node.data.text, node.data.url);
        }
    });
    </c:if>
    </c:forEach>

    tab = $("#framecenter").ligerGetTabManager();
    accordion = $("#accordion1").ligerGetAccordionManager();
    tree = $("[id^=tree]").ligerGetTreeManager();
    $("#pageloading").hide();

});
function f_heightChanged(options){
    if (tab)
        tab.addHeight(options.diff);
    if (accordion && options.middleHeight - 24 > 0)
        accordion.setHeight(options.middleHeight - 24);
}
function f_addTab(tabid,text, url){ 
    tab.addTabItem({ tabid : tabid,text: text, url: url });
} 
</script>
<style type="text/css">
body, html {
	height:100%;
}
body {
	padding:0px;
	margin:0;
	overflow:hidden;
}
.l-link {
	display:block;
	height:26px;
	line-height:26px;
	padding-left:10px;
	text-decoration:underline;
	color:#333;
}
.l-link2 {
	text-decoration:underline;
	color:white;
}
.l-layout-top {
	background:#102A49;
	color:White;
}
.l-layout-bottom {
	background:#E5EDEF;
	text-align:center;
}
#pageloading {
	position:absolute;
	left:0px;
	top:0px;
	background:white url('loading.gif') no-repeat center;
	width:100%;
	height:100%;
	z-index:99999;
}
.l-link {
	display:block;
	line-height:22px;
	height:22px;
	padding-left:20px;
	border:1px solid white;
	margin:4px;
}
.l-link-over {
	background:#FFEEAC;
	border:1px solid #DB9F00;
}
/*top*/
.topcont {
	width:100%;
	margin:0 auto;
	clear:both;
	height:63px;
	background:url(../../images/manager/kjtopbg1.gif) repeat-x;
	margin-bottom:4px;
}
.topleft {
	width:412px;
	height:63px;
	background:url(../../images/manager/kjtopbg3.gif) no-repeat top right;
}
.logo {
	padding-top:0px;
}
.toptit {
	background:url(../../images/manager/line.gif) no-repeat 10% 51%;
	padding:22px 1px 21px 40px;
	display:inline;
}
.rtopcont {
	background:url(../../images/manager/kjtopbg2.gif) no-repeat top left;
	padding:24px 20px 27px 0;
	font:normal 12px "宋体";
	color:#fff;
	text-align:right;
}
.fyel, a.fyel {
	color:#ffff99;
}
.fyel a, .fyel a:visited {
	color:#ffff99;
}
.fyel a:hover {
	color:#ff0064;
	text-decoration:underline;
}
</style>
</head>
<body style="padding:0px;">
<div id="pageloading"></div>
<div id="layout1" style="width:100%">
  <div position="top">
    <div class="topcont">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="topleft"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="logo"><img alt="康佳" src="${ctx}/images/manager/logo.gif" /></td>
                <td class="toptit"><img alt="渠道管理系统" src="${ctx}/images/manager/toptit.gif" /></td>
                <c:if test="${is_fengongsi}">
                  <td nowrap="nowrap" style="font-size: 21px;font-family: '微软雅黑';color: white;">&nbsp;
                    <c:out value="${dept_name}" /></td>
                </c:if>
              </tr>
            </table></td>
          <td class="rtopcont"><img src="${ctx}/images/manager/icon1.gif" />　欢迎您！${fgs_name} ${userInfo.user_name}&nbsp;&nbsp;<a class="fyel" href="${ctx}/manager/admin/KonkaSxOperLog.do?mod_id=906000" target="mainFrame">操作日志</a>&nbsp;&nbsp;<a class="fyel" href="${ctx}/manager/admin/TerminalFeedbackForSelf.do" target="mainFrame">意见反馈</a>&nbsp;&nbsp;<a class="fyel" href="${ctx}/manager/admin/Password.do" target="mainFrame">修改密码</a>&nbsp;&nbsp;<a class="fyel" href="${ctx}/login.do?method=logout" target="_top">[退出]</a></td>
        </tr>
      </table>
      <div class="clear"></div>
    </div>
  </div>
  <div position="left"  title="系统菜单" id="accordion1">
    <c:forEach var="cur1" items="${sysModuleList}" varStatus="vs1">
      <c:if test="${cur1.par_id eq 0}">
        <div title="${cur1.mod_name}" class="l-scroll">
          <ul id="tree_${cur1.mod_id}" style="margin-top:3px;">
            <c:forEach var="cur2" items="${sysModuleList}" varStatus="vs2">
              <c:if test="${cur1.mod_id eq cur2.par_id}">
                <c:set var="quot" value="?"/>
                <c:if var="is_quot" test="${fn:indexOf(cur2.mod_url,'?') gt -1}">
                  <c:set var="quot" value="&amp;"/>
                </c:if>
                <c:if var="is_empty_cur2_mod_url" test="${empty cur2.mod_url}">
                  <li><span>${cur2.mod_name}</span>
                    <ul>
                      <c:forEach var="cur3" items="${sysModuleList}" varStatus="vs3">
                        <c:if test="${cur2.mod_id eq cur3.par_id}">
                          <c:set var="quot" value="?"/>
                          <c:if var="is_quot" test="${fn:indexOf(cur3.mod_url,'?') gt -1}">
                            <c:set var="quot" value="&amp;"/>
                          </c:if>
                          <c:if var="is_empty_cur3_mod_url" test="${empty cur3.mod_url}">
                            <li><span>${cur3.mod_name}</span>
                              <c:set var="i" value="0"/>
                              <c:forEach var="cur4" items="${sysModuleList}" varStatus="vs4">
                                <c:if test="${cur3.mod_id eq cur4.par_id}">
                                  <c:if test="${i eq 0}"><c:out value="<ul>" escapeXml="false" /></c:if>
                                    <c:set var="quot" value="?"/>
                                    <c:if var="is_quot" test="${fn:indexOf(cur4.mod_url,'?') gt -1}">
                                      <c:set var="quot" value="&amp;"/>
                                    </c:if>
                                    <li url="${cur4.mod_url}${quot}mod_id=${cur4.mod_id}"><span>${cur4.mod_name}</span></li>
                                  <c:set var="i" value="${i + 1}"/>
                                </c:if>
                                <c:if test="${i ne 0}"><c:if test="${vs4.last}"><c:out value="</ul>" escapeXml="false" /></c:if></c:if>
                              </c:forEach>
                            </li>
                          </c:if>
                          <c:if test="${!is_empty_cur3_mod_url}">
                            <li url="${cur3.mod_url}${quot}mod_id=${cur3.mod_id}"><span>${cur3.mod_name}</span></li>
                          </c:if>
                        </c:if>
                      </c:forEach>
                    </ul>
                  </li>
                </c:if>
                <c:if test="${!is_empty_cur2_mod_url}">
                  <li url="${cur2.mod_url}${quot}mod_id=${cur2.mod_id}"><span>${cur2.mod_name}</span></li>
                </c:if>
              </c:if>
            </c:forEach>
          </ul>
        </div>
      </c:if>
    </c:forEach>
  </div>
  <div position="center" id="framecenter">
    <div tabid="home" title="我的主页" style="height:300px" >
      <iframe frameborder="0" name="mainFrame" id="mainFrame" src="Frames.do?method=main"></iframe>
    </div>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
