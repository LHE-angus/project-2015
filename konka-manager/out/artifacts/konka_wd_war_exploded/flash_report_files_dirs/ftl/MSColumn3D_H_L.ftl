<?xml version="1.0" encoding="GBK"?>
<chart 
	exportFileName="${((exportFileName)!' ')?html}" 
	exportEnabled="1" 
	exportAction="download" 
	exportAtClient="0" 
	exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" 
	palette="2" 
	caption="${((caption)!' ')?html}" 
	subcaption="${((subcaption)!' ')?html}" 
	shownames="1" 
	showvalues="0" 
	showBorder="${((showBorder)!' ')?html}" 
	decimals="${((decimals)!' ')?html}" 
	numberPrefix="${((numberPrefix)!' ')?html}" 
	numberSuffix="${((numberSuffix)!' ')?html}" 
	formatNumberScale="${((formatNumberScale)!' ')?html}" 
	useRoundEdges="1" 
	legendBorderAlpha="0" 
	baseFontColor="666666" 
	baseFont="${((baseFont)!' ')?html}" 
	yAxisMinValue="-30"
	yAxisMaxValue="30"
	numDivLines="10"
	BaseFontSize ="12"
	bgColor="F3f3f3"
	bgAlpha="70"
	canvasBgColor="FFFFFF"
	showAlternateHGridColor="1"
	alternateHGridColor="f8f8f8" 
	alternateHGridAlpha="60">
	
	<categories>
	 <#list baseChartList as cur>
	    <category label="${((cur.category_label)!' ')?html}" />
	 </#list>
	</categories>
	
	<#list mSColumn3DChartChartList as cur>
	  <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0">
	     <#if cur.baseChartList??>
		    <#list cur.baseChartList as cur_base>
		      <#if cur_base.color??>
		      	  <#if cur_base.link??>
		      	  	  <set value="${((cur_base.value)!' ')?html}" color="${((cur_base.color)!' ')?html}" link="${((cur_base.link)!' ')?html}" />
		      	  <#else>
		      	  	  <set value="${((cur_base.value)!' ')?html}" color="${((cur_base.color)!' ')?html}" />
		      	  </#if>
			  <#else>
			  	  <#if cur_base.link??>
		      	  	  <set value="${((cur_base.value)!' ')?html}" link="${((cur_base.link)!' ')?html}" />
		      	  <#else>
		      	  	  <set value="${((cur_base.value)!' ')?html}" />
		      	  </#if>
			  </#if> 
		    </#list>
	      </#if> 
	  </dataset>
	</#list>
	
	<trendlines>
		<#list trendLineList as cur>
		  <line startValue="${cur.value}" color="${cur.color}" displayValue="${cur.name}" showOnTop="0" />
	    </#list>
	</trendlines>
</chart>
