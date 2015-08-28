<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
</head>
<frameset rows="30,*" cols="*">
  <frame src="KonkaDept.do?method=showNavi&mod_id=${af.map.mod_id}" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="170,*">
	  <frame src="KonkaDept.do?method=showDeptInfoTree&mod_id=${af.map.mod_id}&dept_types_Y=${af.map.dept_types_Y}" name="leftFrame" marginwidth="0" marginheight="0" id="leftFrame" title="leftFrame"/>
	  <frame src="KonkaDept.do?method=edit&amp;par_id=-1&amp;id=0&amp;&mod_id=${af.map.mod_id}" name="editFrame" marginwidth="0" marginheight="0" scrolling="yes" id="editFrame" title="editFrame"/>
	</frameset>
</frameset>
</html>