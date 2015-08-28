package com.ebiz.mmt.domain.chart;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 */
public class StackedColumn3DChart extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1482092324728295042L;

	private String series_name;

	private String color;

	private List<BaseChart> baseChartList;

	public String getSeries_name() {
		return series_name;
	}

	public void setSeries_name(String series_name) {
		this.series_name = series_name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<BaseChart> getBaseChartList() {
		return baseChartList;
	}

	public void setBaseChartList(List<BaseChart> baseChartList) {
		this.baseChartList = baseChartList;
	}

}