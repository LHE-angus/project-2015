<?xml version="1.0" encoding="GBK"?>
    <chart palette="2" caption="${((caption)!' ')?html}" subcaption="${((unit)!' ')?html}" shownames="1" showvalues="0" decimals="2" numberPrefix="${((moneyUnit)!' ')?html}" formatNumberScale="0" useRoundEdges="1" legendBorderAlpha="0" bgColor="99CCFF,FFFFFF" baseFontColor="666666" baseFont="${((baseFont)!' ')?html}" BaseFontSize ="12">
    <categories>
     <#list baseChartList as cur>
	    <category label="${((cur.category_label)!' ')?html}" />
     </#list>
    </categories>
    <#list mSColumn3DChartChartList as cur>
      <dataset seriesName="${((cur.series_name)!' ')?html}" showValues="0">
        <#list cur.baseChartList as cur_base>
	      <set value="${((cur_base.value)!' ')?html}" />
        </#list>
      </dataset>
    </#list>
    </chart>
