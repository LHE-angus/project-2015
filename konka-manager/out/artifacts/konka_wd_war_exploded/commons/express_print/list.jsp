<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<script src="../../commons/express_print/mootools.js" content-type="text/javascript"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
<div align="center"><div id="dly_printer" style="height:${heigth_str}px;width:${width_str}px">
</div></div></td>
  </tr>
</table>


<script>
var swf = new Swiff('../../commons/express_print/images/printermode.swf?1378191485', {
	width:  '100%',
	height: '100%',
	params:{wMode:false},
	id:'dly_printer_flash',
	container: $('dly_printer'),
	vars:{xml:'${xml_str}',data:'${data_str}',bg:'../../commons/express_print/${img_str}',copyright:'shopex'}
});
</script>


</html>