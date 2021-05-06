<%@ page session="true" %>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.eoptech.shopdoda.entities.User"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Order success</title>
<!-- Variables -->
<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/front-end/common/css.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="${base}/" class="stext-109 cl8 hov-cl1 trans-04"> Trang chủ
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="stext-109 cl4"> Order success </span>
		</div>
	</div>
	
	<div class="container mb-5 mt-5">
		<div class="row text-center">
	        <div class="col-sm-12 col-sm-offset-12">
	        <br><br> <h2 style="color:#0fad00" class="font-weight-bold">Success</h2>
	        <!--<img src="http://osmhotels.com//assets/check-true.jpg">-->
	        <p style="font-size:20px;color:#5C5C5C;">${orderSuccess}</p>
	    <br><br>
	        </div>
	        
		</div>
	</div>
	

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>
	<!-- Back to top -->
	<jsp:include page="/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>
	<!-- main js -->
	<jsp:include page="/WEB-INF/views/front-end/common/js.jsp"></jsp:include>
	
</body>
</html>