package com.github.abel533.echarts.samples.map;

import com.github.abel533.echarts.code.SelectedMode;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.series.Map;
import com.github.abel533.echarts.series.MapLocation;
import com.github.abel533.echarts.util.EnhancedOption;
import org.junit.Test;

/**
 * @author liuzh
 */
public class MapZhiYu {

    @Test
    public void test() {
        //地址：http://echarts.baidu.com/doc/example/map.html
        EnhancedOption option = new EnhancedOption();
        Map map = new Map("Map");
        map.mapLocation(new MapLocation(X.left, Y.top, 500));
        map.selectedMode(SelectedMode.multiple);
        map.itemStyle().normal().borderWidth(2)
                .borderColor("lightgreen").color("orange")
                .label().show(true);

        map.itemStyle().emphasis()
                .borderWidth(2).borderColor("#fff").color("#32cd32")
                .label().show(true)
                .textStyle().color("#fff");
        Data data = new Data("广东");
        data.value(Math.round(Math.random() * 1000));
        data.itemStyle().normal().color("#32cd32")
                .label().show(true).textStyle().color("#fff").fontSize(15);
        data.itemStyle().emphasis().borderColor("yellow").color("#cd5c5c")
                .label().show(false).textStyle().color("blue");

        map.data(data);
        map.markPoint().itemStyle().normal().color("skyblue");
        map.markPoint().data(new Data("天津", 350), new Data("上海", 103));

        map.geoCoord("上海", "121.4648", "31.2891").geoCoord("天津", "117.4219", "39.4189");

        option.series(map);
        option.exportToHtml("map.html");
        option.view();
    }
}
