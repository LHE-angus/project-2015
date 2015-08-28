<?xml version="1.0" encoding="utf-8"?>
<mobileconfig>
    <return-info>
       <return-code><#if (ret_code)??>${ret_code}</#if></return-code>
       <msg><#if (msg)??>${msg}</#if></msg>
    </return-info>

<#if (servers)??>
    <mobile-no>${mobile.mobile_no}</mobile-no>

	<confirm>
       <rx>${mobile.entp_crc}</rx>
       <ry>${mobile.ywy_crc}</ry>	
       <confirm-url>http://${servers.config_url}/GPSConfig.do</confirm-url>
	</confirm>

	<config-update>
       <cx>${config.setplan_crc}</cx>
       <cy>${config.version}</cy>
	   <config-update-url>http://${servers.config_url}/GPSConfig.do</config-update-url>
       <time>8:30,13:30</time>
    </config-update>

	<soft-update>
       <version><#if (softversion)??>${softversion}<#else>1.0</#if></version>
       <update-url>http://${servers.soft_url}</update-url>
       <description><#if (softdescription)??>${softdescription}<#else>2012</#if></description>
    </soft-update>

    <location>
       <send-year>${config.year?c}</send-year>
       <send-month>${config.month}</send-month>
       <send-date><#if (config.date_str)??>${config.date_str}</#if></send-date>
       <send-time><#if (config.time_str)??>${config.time_str}</#if></send-time>
       <time_interval>${config.time_interval?c}</time_interval>
       <manual>true</manual>
       <send-url>http://${servers.gps_url}/GPSBack.do</send-url>
    </location>

</#if>
    <company-info>
       <page-url>http://konka.mymyty.com/mobile/login.do</page-url>
    </company-info>

</mobileconfig>