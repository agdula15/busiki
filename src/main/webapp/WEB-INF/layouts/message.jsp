<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="alert alert-<tiles:getAsString name="alert"></tiles:getAsString>" role="alert" style="margin-left: 30px; margin-right: 30px; margin-top: 2%;">
	<br> <br> <br> <br>
	<h3 class="text-center">
		<tiles:getAsString name="content"></tiles:getAsString>
	</h3>
	<br> <br> <br> <br>
</div>
