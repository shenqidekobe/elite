<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div style="margin-top:10px;">
	<div><span style="font-size: 18px;font-weight: bold;">累计收益:${totalIncome}</span></div>
	<div style="margin-top:10px;"><span style="font-size: 16px;">直接人才收益:<span style="font-weight: bold;">${projectIncome}</span></span></div>
	<div style="margin-top:10px;"><span style="font-size: 16px;">直接渠道收益:<span style="font-weight: bold;">${channelIncome}</span></span></div>
	<div style="margin-top:10px;"><span style="font-size: 16px;">间接渠道收益:<span style="font-weight: bold;">${indirectIncome}</span></span></div>
</div>