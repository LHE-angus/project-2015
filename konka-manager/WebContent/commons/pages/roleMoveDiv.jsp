<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色选择</title>
<style type="text/css">
.areause1 {}
.areause1 td{ border-bottom: 0px solid #E3E3E3;border-right: 0px solid #E3E3E3; padding: 0px 0px 0px;}
#areaList0 {}
#areaList1 {}
#areaList0 td{border-bottom: 2px dotted #FFDCB9;padding-left:5px;}
#areaList1 td{border-bottom: 2px dotted #FFDCB9;padding-left:5px;}
</style>
</head>
<body>
  <table class="areause1" >
     <tbody>
        <tr>
         <td>
          <span id="area_name_0">供选择角色列表</span>
          <div style="width:300px;height:180px;overflow-x:hidden;overflow-y:auto;border:1px solid black;">
             <table width="100%" class="areause2" ><tbody id="areaList0"></tbody></table>
          </div>
        </td>
        <td width="20"></td>
        <td>
          <span id="area_name_1">已选择角色列表</span>
          <div style="width:300px;height:180px;overflow-x:hidden;overflow-y:auto;border:1px solid black;">
             <table width="100%" class="areause2" ><tbody id="areaList1"></tbody></table>
          </div>
        </td>
      </tr>
      <tr><td colspan="2" id="notice"><font color="red">*&nbsp;列表项可以通过双击在两个区域间移动</font></td></tr>
     </tbody>
   </table>
</body>
</html>