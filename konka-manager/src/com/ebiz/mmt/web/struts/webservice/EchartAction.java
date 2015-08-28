package com.ebiz.mmt.web.struts.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.web.struts.BaseAction;
import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.Polar;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.data.ScatterData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.samples.bar.BarTest1;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Gauge;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Radar;
import com.github.abel533.echarts.series.Scatter;
import com.github.abel533.echarts.series.gauge.Detail;
import com.github.abel533.echarts.series.gauge.Pointer;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.util.EnhancedOption;

/**
 * @author TUDP
 * @version 2014-11-06
 * @desc echart 实例
 */
public class EchartAction extends BaseAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		return this.Bar(mapping, form, request, response);
	}
	
	public ActionForward Bar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//BarTest1 t = new BarTest1();
		//request.setAttribute("option", t.toString()); 
		
        //地址：http://echarts.baidu.com/doc/example/bar1.html
        EnhancedOption option = new EnhancedOption();
        option.title().text("某地区蒸发量和降水量").subtext("纯属虚构");
        option.tooltip().trigger(Trigger.axis);
        option.legend("蒸发量", "降水量");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        option.xAxis(new CategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"));
        option.yAxis(new ValueAxis());

        Bar bar = new Bar("蒸发量");
        bar.data(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3);
        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        Bar bar2 = new Bar("降水量");
        bar2.data(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3);
        bar2.markPoint().data(new PointData("年最高", 182.2).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2.3).xAxis(11).yAxis(3));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        option.series(bar, bar2);
        
        String optionStr = GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "Bar"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/bar1.html"); 
		return mapping.findForward("view");
	}
	
	public ActionForward Line(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 EnhancedOption option = new EnhancedOption();
	        option.title("某楼盘销售情况", "纯属虚构");
	        option.tooltip().trigger(Trigger.axis);
	        option.legend("意向", "预购", "成交");
	        option.toolbox().show(true).feature(Tool.mark,
	                Tool.dataView,
	                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
	                Tool.restore,
	                Tool.saveAsImage);
	        option.calculable(true);
	        option.xAxis(new CategoryAxis().boundaryGap(false).data("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
	        option.yAxis(new ValueAxis());

	        Line l1 = new Line("成交");
	        l1.smooth(true).itemStyle().normal();
	        l1.data(10, 12, 21, 54, 260, 830, 710);

	        Line l2 = new Line("预购");
	        l2.smooth(true).itemStyle().normal();
	        l2.data(30, 182, 434, 791, 390, 30, 10);

	        Line l3 = new Line("意向");
	        l3.smooth(true).itemStyle().normal();
	        l3.data(1320, 1132, 601, 234, 120, 90, 20);

	        option.series(l1, l2, l3);
        String optionStr = GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "Line"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/line.html"); 
		return mapping.findForward("view");
	}
	public ActionForward Gauge(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		EnhancedOption option = new EnhancedOption();
	    option.tooltip().formatter("{a} <br/>{b} : {c}%");
	    option.toolbox().show(true).feature(Tool.mark, Tool.restore, Tool.saveAsImage); 
	    option.series(new Gauge("业务指标").startAngle(180).endAngle(0).detail(new Detail().formatter("{value}%")).data(new Data("完成率", 75)));
        
        String optionStr = GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "Gauge"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/gauge1.html"); 
		return mapping.findForward("view");
	}
	public ActionForward Radar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		   //地址：http://echarts.baidu.com/doc/example/radar1.html
        EnhancedOption option = new EnhancedOption();
        option.title("预算 vs 开销（Budget vs spending）", "纯属虚构");
        option.tooltip(Trigger.axis);
        option.legend().orient(Orient.vertical).x(X.right).y(Y.bottom).data("预算分配（Allocated Budget）","实际开销（Actual Spending）");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, Tool.restore, Tool.saveAsImage);
        option.calculable(true);

        Polar polar = new Polar();
        polar.indicator(new Data().text("销售（sales）").max(6000),
                new Data().text("管理（Administration）").max(16000),
                new Data().text("信息技术（Information Techology）").max(30000),
                new Data().text("客服（Customer Support）").max(38000),
                new Data().text("研发（Development）").max(52000),
                new Data().text("市场（Marketing）").max(25000));
        option.polar(polar);

        Radar radar = new Radar("预算 vs 开销（Budget vs spending）");
        radar.data(
                new Data().name("预算分配（Allocated Budget）").value(4300, 10000, 28000, 35000, 50000, 19000),
                new Data().name("实际开销（Actual Spending）").value(5000, 14000, 28000, 31000, 42000, 21000));
        option.series(radar);
        
        String optionStr = GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "Radar"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/radar1.html"); 
		return mapping.findForward("view");
	}
	
	
	public ActionForward Scatter(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 //地址：http://echarts.baidu.com/doc/example/scatter2.html
        EnhancedOption option = new EnhancedOption();
        option.tooltip(new Tooltip()
                .trigger(Trigger.axis)
                .showDelay(0)
                .axisPointer(new AxisPointer().type(PointerType.cross)
                        .lineStyle(new LineStyle()
                                .type(LineType.dashed).width(1))));
        option.legend("scatter1", "scatter2");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataZoom, Tool.dataView, Tool.restore, Tool.saveAsImage);
        ValueAxis valueAxis = new ValueAxis().power(1).splitNumber(4).scale(true);
        option.xAxis(valueAxis);
        option.yAxis(valueAxis);
        //注：这里的结果是一种圆形一种方形，是因为默认不设置形状时，会循环形状数组
        option.series(
                new Scatter("scatter1").symbolSize("function (value){" +
                        "                return Math.round(value[2] / 5);" +
                        "            }").data(randomDataArray())
                , new Scatter("scatter2").symbolSize("function (value){" +
                        "                return Math.round(value[2] / 5);" +
                        "            }").data(randomDataArray()));
        
        String optionStr = GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "Scatter"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/scatter2.html"); 
		return mapping.findForward("view");
	}
	
	public ActionForward map9(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();		
		sb.append("{	\n");
		sb.append("	    title : {	\n");
		sb.append("	        text: '全国主要城市空气质量（pm2.5）',	\n");
		sb.append("	        subtext: 'data from PM25.in',	\n");
		sb.append("	        sublink: 'http://www.pm25.in',	\n");
		sb.append("	        x:'center'	\n");
		sb.append("	    },	\n");
		sb.append("	    tooltip : {	\n");
		sb.append("	        trigger: 'item'	\n");
		sb.append("	    },	\n");
		sb.append("	    legend: {	\n");
		sb.append("	        orient: 'vertical',	\n");
		sb.append("	        x:'left',	\n");
		sb.append("	        data:['pm2.5']	\n");
		sb.append("	    },	\n");
		sb.append("	    dataRange: {	\n");
		sb.append("	        min : 0,	\n");
		sb.append("	        max : 500,	\n");
		sb.append("	        calculable : true,	\n");
		sb.append("	        color: ['maroon','purple','red','orange','yellow','lightgreen']	\n");
		sb.append("	    },	\n");
		sb.append("	    toolbox: {	\n");
		sb.append("	        show : true,	\n");
		sb.append("	        orient : 'vertical',	\n");
		sb.append("	        x: 'right',	\n");
		sb.append("	        y: 'center',	\n");
		sb.append("	        feature : {	\n");
		sb.append("	            mark : {show: true},	\n");
		sb.append("	            dataView : {show: true, readOnly: false},	\n");
		sb.append("	            restore : {show: true},	\n");
		sb.append("	            saveAsImage : {show: true}	\n");
		sb.append("	        }	\n");
		sb.append("	    },	\n");
		sb.append("	    series : [	\n");
		sb.append("	        {	\n");
		sb.append("	            name: 'pm2.5',	\n");
		sb.append("	            type: 'map',	\n");
		sb.append("	            mapType: 'china',	\n");
		sb.append("	            hoverable: false,	\n");
		sb.append("	            roam:true,	\n");
		sb.append("	            data : [],	\n");
		sb.append("	            markPoint : {	\n");
		sb.append("	                symbolSize: 5,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2	\n");
		sb.append("	                itemStyle: {	\n");
		sb.append("	                    normal: {	\n");
		sb.append("	                        borderColor: '#87cefa',	\n");
		sb.append("	                        borderWidth: 1,            // 标注边线线宽，单位px，默认为1	\n");
		sb.append("	                        label: {	\n");
		sb.append("	                            show: false	\n");
		sb.append("	                        }	\n");
		sb.append("	                    },	\n");
		sb.append("	                    emphasis: {	\n");
		sb.append("	                        borderColor: '#1e90ff',	\n");
		sb.append("	                        borderWidth: 5,	\n");
		sb.append("	                        label: {	\n");
		sb.append("	                            show: false	\n");
		sb.append("	                        }	\n");
		sb.append("	                    }	\n");
		sb.append("	                },	\n");
		sb.append("	                data : [	\n");
		sb.append("	                    {name: \"海门\", value: 9},	\n");
		sb.append("	                    {name: \"鄂尔多斯\", value: 12},	\n");
		sb.append("	                    {name: \"招远\", value: 12},	\n");
		sb.append("	                    {name: \"舟山\", value: 12},	\n");
		sb.append("	                    {name: \"齐齐哈尔\", value: 14},	\n");
		sb.append("	                    {name: \"盐城\", value: 15},	\n");
		sb.append("	                    {name: \"赤峰\", value: 16},	\n");
		sb.append("	                    {name: \"青岛\", value: 18},	\n");
		sb.append("	                    {name: \"乳山\", value: 18},	\n");
		sb.append("	                    {name: \"金昌\", value: 19},	\n");
		sb.append("	                    {name: \"泉州\", value: 21},	\n");
		sb.append("	                    {name: \"莱西\", value: 21},	\n");
		sb.append("	                    {name: \"日照\", value: 21},	\n");
		sb.append("	                    {name: \"胶南\", value: 22},	\n");
		sb.append("	                    {name: \"南通\", value: 23},	\n");
		sb.append("	                    {name: \"拉萨\", value: 24},	\n");
		sb.append("	                    {name: \"云浮\", value: 24},	\n");
		sb.append("	                    {name: \"梅州\", value: 25},	\n");
		sb.append("	                    {name: \"文登\", value: 25},	\n");
		sb.append("	                    {name: \"上海\", value: 25},	\n");
		sb.append("	                    {name: \"攀枝花\", value: 25},	\n");
		sb.append("	                    {name: \"威海\", value: 25},	\n");
		sb.append("	                    {name: \"承德\", value: 25},	\n");
		sb.append("	                    {name: \"厦门\", value: 26},	\n");
		sb.append("	                    {name: \"汕尾\", value: 26},	\n");
		sb.append("	                    {name: \"潮州\", value: 26},	\n");
		sb.append("	                    {name: \"丹东\", value: 27},	\n");
		sb.append("	                    {name: \"太仓\", value: 27},	\n");
		sb.append("	                    {name: \"曲靖\", value: 27},	\n");
		sb.append("	                    {name: \"烟台\", value: 28},	\n");
		sb.append("	                    {name: \"福州\", value: 29},	\n");
		sb.append("	                    {name: \"瓦房店\", value: 30},	\n");
		sb.append("	                    {name: \"即墨\", value: 30},	\n");
		sb.append("	                    {name: \"抚顺\", value: 31},	\n");
		sb.append("	                    {name: \"玉溪\", value: 31},	\n");
		sb.append("	                    {name: \"张家口\", value: 31},	\n");
		sb.append("	                    {name: \"阳泉\", value: 31},	\n");
		sb.append("	                    {name: \"莱州\", value: 32},	\n");
		sb.append("	                    {name: \"湖州\", value: 32},	\n");
		sb.append("	                    {name: \"汕头\", value: 32},	\n");
		sb.append("	                    {name: \"昆山\", value: 33},	\n");
		sb.append("	                    {name: \"宁波\", value: 33},	\n");
		sb.append("	                    {name: \"湛江\", value: 33},	\n");
		sb.append("	                    {name: \"揭阳\", value: 34},	\n");
		sb.append("	                    {name: \"荣成\", value: 34},	\n");
		sb.append("	                    {name: \"连云港\", value: 35},	\n");
		sb.append("	                    {name: \"葫芦岛\", value: 35},	\n");
		sb.append("	                    {name: \"常熟\", value: 36},	\n");
		sb.append("	                    {name: \"东莞\", value: 36},	\n");
		sb.append("	                    {name: \"河源\", value: 36},	\n");
		sb.append("	                    {name: \"淮安\", value: 36},	\n");
		sb.append("	                    {name: \"泰州\", value: 36},	\n");
		sb.append("	                    {name: \"南宁\", value: 37},	\n");
		sb.append("	                    {name: \"营口\", value: 37},	\n");
		sb.append("	                    {name: \"惠州\", value: 37},	\n");
		sb.append("	                    {name: \"江阴\", value: 37},	\n");
		sb.append("	                    {name: \"蓬莱\", value: 37},	\n");
		sb.append("	                    {name: \"韶关\", value: 38},	\n");
		sb.append("	                    {name: \"嘉峪关\", value: 38},	\n");
		sb.append("	                    {name: \"广州\", value: 38},	\n");
		sb.append("	                    {name: \"延安\", value: 38},	\n");
		sb.append("	                    {name: \"太原\", value: 39},	\n");
		sb.append("	                    {name: \"清远\", value: 39},	\n");
		sb.append("	                    {name: \"中山\", value: 39},	\n");
		sb.append("	                    {name: \"昆明\", value: 39},	\n");
		sb.append("	                    {name: \"寿光\", value: 40},	\n");
		sb.append("	                    {name: \"盘锦\", value: 40},	\n");
		sb.append("	                    {name: \"长治\", value: 41},	\n");
		sb.append("	                    {name: \"深圳\", value: 41},	\n");
		sb.append("	                    {name: \"珠海\", value: 42},	\n");
		sb.append("	                    {name: \"宿迁\", value: 43},	\n");
		sb.append("	                    {name: \"咸阳\", value: 43},	\n");
		sb.append("	                    {name: \"铜川\", value: 44},	\n");
		sb.append("	                    {name: \"平度\", value: 44},	\n");
		sb.append("	                    {name: \"佛山\", value: 44},	\n");
		sb.append("	                    {name: \"海口\", value: 44},	\n");
		sb.append("	                    {name: \"江门\", value: 45},	\n");
		sb.append("	                    {name: \"章丘\", value: 45},	\n");
		sb.append("	                    {name: \"肇庆\", value: 46},	\n");
		sb.append("	                    {name: \"大连\", value: 47},	\n");
		sb.append("	                    {name: \"临汾\", value: 47},	\n");
		sb.append("	                    {name: \"吴江\", value: 47},	\n");
		sb.append("	                    {name: \"石嘴山\", value: 49},	\n");
		sb.append("	                    {name: \"沈阳\", value: 50},	\n");
		sb.append("	                    {name: \"苏州\", value: 50},	\n");
		sb.append("	                    {name: \"茂名\", value: 50},	\n");
		sb.append("	                    {name: \"嘉兴\", value: 51},	\n");
		sb.append("	                    {name: \"长春\", value: 51},	\n");
		sb.append("	                    {name: \"胶州\", value: 52},	\n");
		sb.append("	                    {name: \"银川\", value: 52},	\n");
		sb.append("	                    {name: \"张家港\", value: 52},	\n");
		sb.append("	                    {name: \"三门峡\", value: 53},	\n");
		sb.append("	                    {name: \"锦州\", value: 54},	\n");
		sb.append("	                    {name: \"南昌\", value: 54},	\n");
		sb.append("	                    {name: \"柳州\", value: 54},	\n");
		sb.append("	                    {name: \"三亚\", value: 54},	\n");
		sb.append("	                    {name: \"自贡\", value: 56},	\n");
		sb.append("	                    {name: \"吉林\", value: 56},	\n");
		sb.append("	                    {name: \"阳江\", value: 57},	\n");
		sb.append("	                    {name: \"泸州\", value: 57},	\n");
		sb.append("	                    {name: \"西宁\", value: 57},	\n");
		sb.append("	                    {name: \"宜宾\", value: 58},	\n");
		sb.append("	                    {name: \"呼和浩特\", value: 58},	\n");
		sb.append("	                    {name: \"成都\", value: 58},	\n");
		sb.append("	                    {name: \"大同\", value: 58},	\n");
		sb.append("	                    {name: \"镇江\", value: 59},	\n");
		sb.append("	                    {name: \"桂林\", value: 59},	\n");
		sb.append("	                    {name: \"张家界\", value: 59},	\n");
		sb.append("	                    {name: \"宜兴\", value: 59},	\n");
		sb.append("	                    {name: \"北海\", value: 60},	\n");
		sb.append("	                    {name: \"西安\", value: 61},	\n");
		sb.append("	                    {name: \"金坛\", value: 62},	\n");
		sb.append("	                    {name: \"东营\", value: 62},	\n");
		sb.append("	                    {name: \"牡丹江\", value: 63},	\n");
		sb.append("	                    {name: \"遵义\", value: 63},	\n");
		sb.append("	                    {name: \"绍兴\", value: 63},	\n");
		sb.append("	                    {name: \"扬州\", value: 64},	\n");
		sb.append("	                    {name: \"常州\", value: 64},	\n");
		sb.append("	                    {name: \"潍坊\", value: 65},	\n");
		sb.append("	                    {name: \"重庆\", value: 66},	\n");
		sb.append("	                    {name: \"台州\", value: 67},	\n");
		sb.append("	                    {name: \"南京\", value: 67},	\n");
		sb.append("	                    {name: \"滨州\", value: 70},	\n");
		sb.append("	                    {name: \"贵阳\", value: 71},	\n");
		sb.append("	                    {name: \"无锡\", value: 71},	\n");
		sb.append("	                    {name: \"本溪\", value: 71},	\n");
		sb.append("	                    {name: \"克拉玛依\", value: 72},	\n");
		sb.append("	                    {name: \"渭南\", value: 72},	\n");
		sb.append("	                    {name: \"马鞍山\", value: 72},	\n");
		sb.append("	                    {name: \"宝鸡\", value: 72},	\n");
		sb.append("	                    {name: \"焦作\", value: 75},	\n");
		sb.append("	                    {name: \"句容\", value: 75},	\n");
		sb.append("	                    {name: \"北京\", value: 79},	\n");
		sb.append("	                    {name: \"徐州\", value: 79},	\n");
		sb.append("	                    {name: \"衡水\", value: 80},	\n");
		sb.append("	                    {name: \"包头\", value: 80},	\n");
		sb.append("	                    {name: \"绵阳\", value: 80},	\n");
		sb.append("	                    {name: \"乌鲁木齐\", value: 84},	\n");
		sb.append("	                    {name: \"枣庄\", value: 84},	\n");
		sb.append("	                    {name: \"杭州\", value: 84},	\n");
		sb.append("	                    {name: \"淄博\", value: 85},	\n");
		sb.append("	                    {name: \"鞍山\", value: 86},	\n");
		sb.append("	                    {name: \"溧阳\", value: 86},	\n");
		sb.append("	                    {name: \"库尔勒\", value: 86},	\n");
		sb.append("	                    {name: \"安阳\", value: 90},	\n");
		sb.append("	                    {name: \"开封\", value: 90},	\n");
		sb.append("	                    {name: \"济南\", value: 92},	\n");
		sb.append("	                    {name: \"德阳\", value: 93},	\n");
		sb.append("	                    {name: \"温州\", value: 95},	\n");
		sb.append("	                    {name: \"九江\", value: 96},	\n");
		sb.append("	                    {name: \"邯郸\", value: 98},	\n");
		sb.append("	                    {name: \"临安\", value: 99},	\n");
		sb.append("	                    {name: \"兰州\", value: 99},	\n");
		sb.append("	                    {name: \"沧州\", value: 100},	\n");
		sb.append("	                    {name: \"临沂\", value: 103},	\n");
		sb.append("	                    {name: \"南充\", value: 104},	\n");
		sb.append("	                    {name: \"天津\", value: 105},	\n");
		sb.append("	                    {name: \"富阳\", value: 106},	\n");
		sb.append("	                    {name: \"泰安\", value: 112},	\n");
		sb.append("	                    {name: \"诸暨\", value: 112},	\n");
		sb.append("	                    {name: \"郑州\", value: 113},	\n");
		sb.append("	                    {name: \"哈尔滨\", value: 114},	\n");
		sb.append("	                    {name: \"聊城\", value: 116},	\n");
		sb.append("	                    {name: \"芜湖\", value: 117},	\n");
		sb.append("	                    {name: \"唐山\", value: 119},	\n");
		sb.append("	                    {name: \"平顶山\", value: 119},	\n");
		sb.append("	                    {name: \"邢台\", value: 119},	\n");
		sb.append("	                    {name: \"德州\", value: 120},	\n");
		sb.append("	                    {name: \"济宁\", value: 120},	\n");
		sb.append("	                    {name: \"荆州\", value: 127},	\n");
		sb.append("	                    {name: \"宜昌\", value: 130},	\n");
		sb.append("	                    {name: \"义乌\", value: 132},	\n");
		sb.append("	                    {name: \"丽水\", value: 133},	\n");
		sb.append("	                    {name: \"洛阳\", value: 134},	\n");
		sb.append("	                    {name: \"秦皇岛\", value: 136},	\n");
		sb.append("	                    {name: \"株洲\", value: 143},	\n");
		sb.append("	                    {name: \"石家庄\", value: 147},	\n");
		sb.append("	                    {name: \"莱芜\", value: 148},	\n");
		sb.append("	                    {name: \"常德\", value: 152},	\n");
		sb.append("	                    {name: \"保定\", value: 153},	\n");
		sb.append("	                    {name: \"湘潭\", value: 154},	\n");
		sb.append("	                    {name: \"金华\", value: 157},	\n");
		sb.append("	                    {name: \"岳阳\", value: 169},	\n");
		sb.append("	                    {name: \"长沙\", value: 175},	\n");
		sb.append("	                    {name: \"衢州\", value: 177},	\n");
		sb.append("	                    {name: \"廊坊\", value: 193},	\n");
		sb.append("	                    {name: \"菏泽\", value: 194},	\n");
		sb.append("	                    {name: \"合肥\", value: 229},	\n");
		sb.append("	                    {name: \"武汉\", value: 273},	\n");
		sb.append("	                    {name: \"大庆\", value: 279}	\n");
		sb.append("	                ]	\n");
		sb.append("	            },	\n");
		sb.append("	            geoCoord: {	\n");
		sb.append("	                \"海门\":[121.15,31.89],	\n");
		sb.append("	                \"鄂尔多斯\":[109.781327,39.608266],	\n");
		sb.append("	                \"招远\":[120.38,37.35],	\n");
		sb.append("	                \"舟山\":[122.207216,29.985295],	\n");
		sb.append("	                \"齐齐哈尔\":[123.97,47.33],	\n");
		sb.append("	                \"盐城\":[120.13,33.38],	\n");
		sb.append("	                \"赤峰\":[118.87,42.28],	\n");
		sb.append("	                \"青岛\":[120.33,36.07],	\n");
		sb.append("	                \"乳山\":[121.52,36.89],	\n");
		sb.append("	                \"金昌\":[102.188043,38.520089],	\n");
		sb.append("	                \"泉州\":[118.58,24.93],	\n");
		sb.append("	                \"莱西\":[120.53,36.86],	\n");
		sb.append("	                \"日照\":[119.46,35.42],	\n");
		sb.append("	                \"胶南\":[119.97,35.88],	\n");
		sb.append("	                \"南通\":[121.05,32.08],	\n");
		sb.append("	                \"拉萨\":[91.11,29.97],	\n");
		sb.append("	                \"云浮\":[112.02,22.93],	\n");
		sb.append("	                \"梅州\":[116.1,24.55],	\n");
		sb.append("	                \"文登\":[122.05,37.2],	\n");
		sb.append("	                \"上海\":[121.48,31.22],	\n");
		sb.append("	                \"攀枝花\":[101.718637,26.582347],	\n");
		sb.append("	                \"威海\":[122.1,37.5],	\n");
		sb.append("	                \"承德\":[117.93,40.97],	\n");
		sb.append("	                \"厦门\":[118.1,24.46],	\n");
		sb.append("	                \"汕尾\":[115.375279,22.786211],	\n");
		sb.append("	                \"潮州\":[116.63,23.68],	\n");
		sb.append("	                \"丹东\":[124.37,40.13],	\n");
		sb.append("	                \"太仓\":[121.1,31.45],	\n");
		sb.append("	                \"曲靖\":[103.79,25.51],	\n");
		sb.append("	                \"烟台\":[121.39,37.52],	\n");
		sb.append("	                \"福州\":[119.3,26.08],	\n");
		sb.append("	                \"瓦房店\":[121.979603,39.627114],	\n");
		sb.append("	                \"即墨\":[120.45,36.38],	\n");
		sb.append("	                \"抚顺\":[123.97,41.97],	\n");
		sb.append("	                \"玉溪\":[102.52,24.35],	\n");
		sb.append("	                \"张家口\":[114.87,40.82],	\n");
		sb.append("	                \"阳泉\":[113.57,37.85],	\n");
		sb.append("	                \"莱州\":[119.942327,37.177017],	\n");
		sb.append("	                \"湖州\":[120.1,30.86],	\n");
		sb.append("	                \"汕头\":[116.69,23.39],	\n");
		sb.append("	                \"昆山\":[120.95,31.39],	\n");
		sb.append("	                \"宁波\":[121.56,29.86],	\n");
		sb.append("	                \"湛江\":[110.359377,21.270708],	\n");
		sb.append("	                \"揭阳\":[116.35,23.55],	\n");
		sb.append("	                \"荣成\":[122.41,37.16],	\n");
		sb.append("	                \"连云港\":[119.16,34.59],	\n");
		sb.append("	                \"葫芦岛\":[120.836932,40.711052],	\n");
		sb.append("	                \"常熟\":[120.74,31.64],	\n");
		sb.append("	                \"东莞\":[113.75,23.04],	\n");
		sb.append("	                \"河源\":[114.68,23.73],	\n");
		sb.append("	                \"淮安\":[119.15,33.5],	\n");
		sb.append("	                \"泰州\":[119.9,32.49],	\n");
		sb.append("	                \"南宁\":[108.33,22.84],	\n");
		sb.append("	                \"营口\":[122.18,40.65],	\n");
		sb.append("	                \"惠州\":[114.4,23.09],	\n");
		sb.append("	                \"江阴\":[120.26,31.91],	\n");
		sb.append("	                \"蓬莱\":[120.75,37.8],	\n");
		sb.append("	                \"韶关\":[113.62,24.84],	\n");
		sb.append("	                \"嘉峪关\":[98.289152,39.77313],	\n");
		sb.append("	                \"广州\":[113.23,23.16],	\n");
		sb.append("	                \"延安\":[109.47,36.6],	\n");
		sb.append("	                \"太原\":[112.53,37.87],	\n");
		sb.append("	                \"清远\":[113.01,23.7],	\n");
		sb.append("	                \"中山\":[113.38,22.52],	\n");
		sb.append("	                \"昆明\":[102.73,25.04],	\n");
		sb.append("	                \"寿光\":[118.73,36.86],	\n");
		sb.append("	                \"盘锦\":[122.070714,41.119997],	\n");
		sb.append("	                \"长治\":[113.08,36.18],	\n");
		sb.append("	                \"深圳\":[114.07,22.62],	\n");
		sb.append("	                \"珠海\":[113.52,22.3],	\n");
		sb.append("	                \"宿迁\":[118.3,33.96],	\n");
		sb.append("	                \"咸阳\":[108.72,34.36],	\n");
		sb.append("	                \"铜川\":[109.11,35.09],	\n");
		sb.append("	                \"平度\":[119.97,36.77],	\n");
		sb.append("	                \"佛山\":[113.11,23.05],	\n");
		sb.append("	                \"海口\":[110.35,20.02],	\n");
		sb.append("	                \"江门\":[113.06,22.61],	\n");
		sb.append("	                \"章丘\":[117.53,36.72],	\n");
		sb.append("	                \"肇庆\":[112.44,23.05],	\n");
		sb.append("	                \"大连\":[121.62,38.92],	\n");
		sb.append("	                \"临汾\":[111.5,36.08],	\n");
		sb.append("	                \"吴江\":[120.63,31.16],	\n");
		sb.append("	                \"石嘴山\":[106.39,39.04],	\n");
		sb.append("	                \"沈阳\":[123.38,41.8],	\n");
		sb.append("	                \"苏州\":[120.62,31.32],	\n");
		sb.append("	                \"茂名\":[110.88,21.68],	\n");
		sb.append("	                \"嘉兴\":[120.76,30.77],	\n");
		sb.append("	                \"长春\":[125.35,43.88],	\n");
		sb.append("	                \"胶州\":[120.03336,36.264622],	\n");
		sb.append("	                \"银川\":[106.27,38.47],	\n");
		sb.append("	                \"张家港\":[120.555821,31.875428],	\n");
		sb.append("	                \"三门峡\":[111.19,34.76],	\n");
		sb.append("	                \"锦州\":[121.15,41.13],	\n");
		sb.append("	                \"南昌\":[115.89,28.68],	\n");
		sb.append("	                \"柳州\":[109.4,24.33],	\n");
		sb.append("	                \"三亚\":[109.511909,18.252847],	\n");
		sb.append("	                \"自贡\":[104.778442,29.33903],	\n");
		sb.append("	                \"吉林\":[126.57,43.87],	\n");
		sb.append("	                \"阳江\":[111.95,21.85],	\n");
		sb.append("	                \"泸州\":[105.39,28.91],	\n");
		sb.append("	                \"西宁\":[101.74,36.56],	\n");
		sb.append("	                \"宜宾\":[104.56,29.77],	\n");
		sb.append("	                \"呼和浩特\":[111.65,40.82],	\n");
		sb.append("	                \"成都\":[104.06,30.67],	\n");
		sb.append("	                \"大同\":[113.3,40.12],	\n");
		sb.append("	                \"镇江\":[119.44,32.2],	\n");
		sb.append("	                \"桂林\":[110.28,25.29],	\n");
		sb.append("	                \"张家界\":[110.479191,29.117096],	\n");
		sb.append("	                \"宜兴\":[119.82,31.36],	\n");
		sb.append("	                \"北海\":[109.12,21.49],	\n");
		sb.append("	                \"西安\":[108.95,34.27],	\n");
		sb.append("	                \"金坛\":[119.56,31.74],	\n");
		sb.append("	                \"东营\":[118.49,37.46],	\n");
		sb.append("	                \"牡丹江\":[129.58,44.6],	\n");
		sb.append("	                \"遵义\":[106.9,27.7],	\n");
		sb.append("	                \"绍兴\":[120.58,30.01],	\n");
		sb.append("	                \"扬州\":[119.42,32.39],	\n");
		sb.append("	                \"常州\":[119.95,31.79],	\n");
		sb.append("	                \"潍坊\":[119.1,36.62],	\n");
		sb.append("	                \"重庆\":[106.54,29.59],	\n");
		sb.append("	                \"台州\":[121.420757,28.656386],	\n");
		sb.append("	                \"南京\":[118.78,32.04],	\n");
		sb.append("	                \"滨州\":[118.03,37.36],	\n");
		sb.append("	                \"贵阳\":[106.71,26.57],	\n");
		sb.append("	                \"无锡\":[120.29,31.59],	\n");
		sb.append("	                \"本溪\":[123.73,41.3],	\n");
		sb.append("	                \"克拉玛依\":[84.77,45.59],	\n");
		sb.append("	                \"渭南\":[109.5,34.52],	\n");
		sb.append("	                \"马鞍山\":[118.48,31.56],	\n");
		sb.append("	                \"宝鸡\":[107.15,34.38],	\n");
		sb.append("	                \"焦作\":[113.21,35.24],	\n");
		sb.append("	                \"句容\":[119.16,31.95],	\n");
		sb.append("	                \"北京\":[116.46,39.92],	\n");
		sb.append("	                \"徐州\":[117.2,34.26],	\n");
		sb.append("	                \"衡水\":[115.72,37.72],	\n");
		sb.append("	                \"包头\":[110,40.58],	\n");
		sb.append("	                \"绵阳\":[104.73,31.48],	\n");
		sb.append("	                \"乌鲁木齐\":[87.68,43.77],	\n");
		sb.append("	                \"枣庄\":[117.57,34.86],	\n");
		sb.append("	                \"杭州\":[120.19,30.26],	\n");
		sb.append("	                \"淄博\":[118.05,36.78],	\n");
		sb.append("	                \"鞍山\":[122.85,41.12],	\n");
		sb.append("	                \"溧阳\":[119.48,31.43],	\n");
		sb.append("	                \"库尔勒\":[86.06,41.68],	\n");
		sb.append("	                \"安阳\":[114.35,36.1],	\n");
		sb.append("	                \"开封\":[114.35,34.79],	\n");
		sb.append("	                \"济南\":[117,36.65],	\n");
		sb.append("	                \"德阳\":[104.37,31.13],	\n");
		sb.append("	                \"温州\":[120.65,28.01],	\n");
		sb.append("	                \"九江\":[115.97,29.71],	\n");
		sb.append("	                \"邯郸\":[114.47,36.6],	\n");
		sb.append("	                \"临安\":[119.72,30.23],	\n");
		sb.append("	                \"兰州\":[103.73,36.03],	\n");
		sb.append("	                \"沧州\":[116.83,38.33],	\n");
		sb.append("	                \"临沂\":[118.35,35.05],	\n");
		sb.append("	                \"南充\":[106.110698,30.837793],	\n");
		sb.append("	                \"天津\":[117.2,39.13],	\n");
		sb.append("	                \"富阳\":[119.95,30.07],	\n");
		sb.append("	                \"泰安\":[117.13,36.18],	\n");
		sb.append("	                \"诸暨\":[120.23,29.71],	\n");
		sb.append("	                \"郑州\":[113.65,34.76],	\n");
		sb.append("	                \"哈尔滨\":[126.63,45.75],	\n");
		sb.append("	                \"聊城\":[115.97,36.45],	\n");
		sb.append("	                \"芜湖\":[118.38,31.33],	\n");
		sb.append("	                \"唐山\":[118.02,39.63],	\n");
		sb.append("	                \"平顶山\":[113.29,33.75],	\n");
		sb.append("	                \"邢台\":[114.48,37.05],	\n");
		sb.append("	                \"德州\":[116.29,37.45],	\n");
		sb.append("	                \"济宁\":[116.59,35.38],	\n");
		sb.append("	                \"荆州\":[112.239741,30.335165],	\n");
		sb.append("	                \"宜昌\":[111.3,30.7],	\n");
		sb.append("	                \"义乌\":[120.06,29.32],	\n");
		sb.append("	                \"丽水\":[119.92,28.45],	\n");
		sb.append("	                \"洛阳\":[112.44,34.7],	\n");
		sb.append("	                \"秦皇岛\":[119.57,39.95],	\n");
		sb.append("	                \"株洲\":[113.16,27.83],	\n");
		sb.append("	                \"石家庄\":[114.48,38.03],	\n");
		sb.append("	                \"莱芜\":[117.67,36.19],	\n");
		sb.append("	                \"常德\":[111.69,29.05],	\n");
		sb.append("	                \"保定\":[115.48,38.85],	\n");
		sb.append("	                \"湘潭\":[112.91,27.87],	\n");
		sb.append("	                \"金华\":[119.64,29.12],	\n");
		sb.append("	                \"岳阳\":[113.09,29.37],	\n");
		sb.append("	                \"长沙\":[113,28.21],	\n");
		sb.append("	                \"衢州\":[118.88,28.97],	\n");
		sb.append("	                \"廊坊\":[116.7,39.53],	\n");
		sb.append("	                \"菏泽\":[115.480656,35.23375],	\n");
		sb.append("	                \"合肥\":[117.27,31.86],	\n");
		sb.append("	                \"武汉\":[114.31,30.52],	\n");
		sb.append("	                \"大庆\":[125.03,46.58]	\n");
		sb.append("	            }	\n");
		sb.append("	        },	\n");
		sb.append("	        {	\n");
		sb.append("	            name: 'Top5',	\n");
		sb.append("	            type: 'map',	\n");
		sb.append("	            mapType: 'china',	\n");
		sb.append("	            data:[],	\n");
		sb.append("	            markPoint : {	\n");
		sb.append("	                symbol:'emptyCircle',	\n");
		sb.append("	                symbolSize : function (v){	\n");
		sb.append("	                    return 10 + v/100	\n");
		sb.append("	                },	\n");
		sb.append("	                effect : {	\n");
		sb.append("	                    show: true,	\n");
		sb.append("	                    shadowBlur : 0	\n");
		sb.append("	                },	\n");
		sb.append("	                itemStyle:{	\n");
		sb.append("	                    normal:{	\n");
		sb.append("	                        label:{show:false}	\n");
		sb.append("	                    }	\n");
		sb.append("	                },	\n");
		sb.append("	                data : [	\n");
		sb.append("	                    {name: \"廊坊\", value: 193},	\n");
		sb.append("	                    {name: \"菏泽\", value: 194},	\n");
		sb.append("	                    {name: \"合肥\", value: 229},	\n");
		sb.append("	                    {name: \"武汉\", value: 273},	\n");
		sb.append("	                    {name: \"大庆\", value: 279}	\n");
		sb.append("	                ]	\n");
		sb.append("	            }	\n");
		sb.append("	        }	\n");
		sb.append("	    ]	\n");
		sb.append("	};	\n");

        String optionStr = sb.toString();//GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "map9"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/map9.html"); 
		return mapping.findForward("view");
	}
 
	public ActionForward mix3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();		
		sb.append("{\n");
		sb.append("    title : {\n");
		sb.append("        text: '2011全国GDP（亿元）',\n");
		sb.append("        subtext: '数据来自国家统计局'\n");
		sb.append("     },\n");
		sb.append("    tooltip : {\n");
		sb.append("        trigger: 'item'\n");
		sb.append("    },\n");
		sb.append("    legend: {\n");
		sb.append("       x:'right',\n");
		sb.append("        selectedMode:false,\n");
		sb.append("        data:['北京','上海','广东']\n");
		sb.append("    },\n");
		sb.append("    dataRange: {\n");
		sb.append("        orient: 'horizontal',\n");
		sb.append("		        min: 0,\n");
		sb.append("		        max: 55000,\n");
		sb.append("		        text:['高','低'],           // 文本，默认为数值文本\n");
		sb.append("		        splitNumber:0\n");
		sb.append("		    },\n");
		sb.append("		    toolbox: {\n");
		sb.append("		        show : true,\n");
		sb.append("		        orient: 'vertical',\n");
		sb.append("		        x:'right',\n");
		sb.append("		        y:'center',\n");
		sb.append("		        feature : {\n");
		sb.append("		            mark : {show: true},\n");
		sb.append("		            dataView : {show: true, readOnly: false}\n");
		sb.append("		        }\n");
		sb.append("		    },\n");
		sb.append("		    series : [\n");
		sb.append("		        {\n");
		sb.append("		            name: '2011全国GDP分布',\n");
		sb.append("		            type: 'map',\n");
		sb.append("		            mapType: 'china',\n");
		sb.append("		            mapLocation: {\n");
		sb.append("		                x: 'left'\n");
		sb.append("		            },\n");
		sb.append("		            selectedMode : 'multiple',\n");
		sb.append("		            itemStyle:{\n");
		sb.append("		                normal:{label:{show:true}},\n");
		sb.append("		                emphasis:{label:{show:true}}\n");
		sb.append("		            },\n");
		sb.append("		            data:[\n");
		sb.append("		                {name:'西藏', value:605.83},\n");
		sb.append("		                {name:'青海', value:1670.44},\n");
		sb.append("		                {name:'宁夏', value:2102.21},\n");
		sb.append("		                {name:'海南', value:2522.66},\n");
		sb.append("		                {name:'甘肃', value:5020.37},\n");
		sb.append("		                {name:'贵州', value:5701.84},\n");
		sb.append("		                {name:'新疆', value:6610.05},\n");
		sb.append("		                {name:'云南', value:8893.12},\n");
		sb.append("		                {name:'重庆', value:10011.37},\n");
		sb.append("		                {name:'吉林', value:10568.83},\n");
		sb.append("		                {name:'山西', value:11237.55},\n");
		sb.append("		                {name:'天津', value:11307.28},\n");
		sb.append("		                {name:'江西', value:11702.82},\n");
		sb.append("		                {name:'广西', value:11720.87},\n");
		sb.append("		                {name:'陕西', value:12512.3},\n");
		sb.append("		                {name:'黑龙江', value:12582},\n");
		sb.append("		                {name:'内蒙古', value:14359.88},\n");
		sb.append("		                {name:'安徽', value:15300.65},\n");
		sb.append("		                {name:'北京', value:16251.93, selected:true},\n");
		sb.append("		                {name:'福建', value:17560.18},\n");
		sb.append("		                {name:'上海', value:19195.69, selected:true},\n");
		sb.append("		                {name:'湖北', value:19632.26},\n");
		sb.append("		                {name:'湖南', value:19669.56},\n");
		sb.append("		                {name:'四川', value:21026.68},\n");
		sb.append("		                {name:'辽宁', value:22226.7},\n");
		sb.append("		                {name:'河北', value:24515.76},\n");
		sb.append("		                {name:'河南', value:26931.03},\n");
		sb.append("		                {name:'浙江', value:32318.85},\n");
		sb.append("		                {name:'山东', value:45361.85},\n");
		sb.append("		                {name:'江苏', value:49110.27},\n");
		sb.append("		                {name:'广东', value:53210.28, selected:true}\n");
		sb.append("		            ]\n");
		sb.append("		        },\n");
		sb.append("		        {\n");
		sb.append("		            name:'2011全国GDP对比',\n");
		sb.append("		            type:'pie',\n");
		sb.append("		            roseType : 'area',\n");
		sb.append("		            tooltip: {\n");
		sb.append("		                trigger: 'item',\n");
		sb.append("		                formatter: \"{a} <br/>{b} : {c} ({d}%)\"\n");
		sb.append("		            },\n");
		sb.append("		            center: [document.getElementById('main').offsetWidth - 250, 225],\n");
		sb.append("		            radius: [30, 120],\n");
		sb.append("		            data:[\n");
		sb.append("		                {name: '北京', value: 16251.93},\n");
		sb.append("		                {name: '上海', value: 19195.69},\n");
		sb.append("		                {name: '广东', value: 53210.28}\n");
		sb.append("		            ]\n");
		sb.append("		        }\n");
		sb.append("		    ],\n");
		sb.append("		    animation: false\n");
		sb.append("		}; \n");
		sb.append("	myChart.on(echarts.config.EVENT.MAP_SELECTED, function (param){\n");
		sb.append("		    var selected = param.selected;\n");
		sb.append("		    var mapSeries = option.series[0];\n");
		sb.append("		    var data = [];\n");
		sb.append("		    var legendData = [];\n");
		sb.append("		    var name;\n");
		sb.append("		    for (var p = 0, len = mapSeries.data.length; p < len; p++) {\n");
		sb.append("		        name = mapSeries.data[p].name;\n");
		sb.append("		        //mapSeries.data[p].selected = selected[name];\n");
		sb.append("		        if (selected[name]) {\n");
		sb.append("		            data.push({\n");
		sb.append("		                name: name,\n");
		sb.append("		                value: mapSeries.data[p].value\n");
		sb.append("		            });\n");
		sb.append("		            legendData.push(name);\n");
		sb.append("		        }\n");
		sb.append("		    }\n");
		sb.append("		    option.legend.data = legendData;\n");
		sb.append("		    option.series[1].data = data;\n");
		sb.append("		  myChart.setOption(option, true);\n");
		sb.append("		;});\n");
        
        String optionStr = sb.toString();//GsonUtil.format(option);
        request.setAttribute("option", optionStr); 
        request.setAttribute("title", "mix3"); 
        request.setAttribute("url", "http://echarts.baidu.com/doc/example/mix3.html"); 
		return mapping.findForward("view");
	}
 
	
	private ScatterData[] randomDataArray() {
        ScatterData[] scatters = new ScatterData[100];
        for (int i = 0; i < scatters.length; i++) {
            scatters[i] = new ScatterData(random(), random(), Math.abs(random()));
        }
        return scatters;
    }

    private int random() {
        int i = (int) Math.round(Math.random() * 100);
        return (i * (i % 2 == 0 ? 1 : -1));
    }
 
}
