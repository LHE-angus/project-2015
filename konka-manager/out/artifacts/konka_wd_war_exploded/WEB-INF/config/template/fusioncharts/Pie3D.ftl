<?xml version="1.0" encoding="GBK"?>
<chart exportFileName="${((exportFileName)!' ')?html}" exportEnabled="1" exportAction="download" exportAtClient="0" exportHandler="${((ctx)!' ')?html}/FusionchartExporter.do" caption="${((caption)!' ')?html}" palette="2" animation="1" subCaption="${((unit)!' ')?html}" YAxisName="Sales Achieved" showValues="0" numberPrefix="" formatNumberScale="0" showPercentInToolTip="0" showLabels="0" showLegend="1">
    <#list baseChartList as cur>
      <set value="${((cur.value)!' ')?html}" label="${((cur.label)!' ')?html}" isSliced="${((cur.is_sliced)!' ')?html}"/>
    </#list>
    <styles>
		<definition>
		    <style type="font" name="CaptionFont" color="666666" size="15" />
		    <style type="font" name="SubCaptionFont" bold="0" />
		</definition>
		<application>
		    <apply toObject="caption" styles="CaptionFont" />
		    <apply toObject="SubCaption" styles="SubCaptionFont" />
		</application>
	</styles>
</chart>
