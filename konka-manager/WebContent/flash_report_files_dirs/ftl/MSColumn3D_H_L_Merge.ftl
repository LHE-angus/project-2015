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
	
	<#list mSColumn3DChartChartList_p as cur>
	  <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0" parentYAxis="p">
	    <#if cur.baseChartList??>
	      <#list cur.baseChartList as cur_base>
		     <#if cur_base.color??>
		      	  <set value="${((cur_base.value)!' ')?html}" color="${((cur_base.color)!' ')?html}" />
			 <#else>
		      	  <set value="${((cur_base.value)!' ')?html}" />
			 </#if>
		  </#list>
	    </#if>  
	  </dataset>
	</#list>
	
	<#list mSColumn3DChartChartList_s as cur>
	  <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0" parentYAxis="s">
	    <#if cur.baseChartList??>
		    <#list cur.baseChartList as cur_base>
		      <set value="${((cur_base.value)!' ')?html}" />
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
