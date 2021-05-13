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
<title>Shopping cart</title>
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
			<a href="${base}/" class="cl8 hov-cl1 trans-04"> Trang chủ
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="cl4"> Đơn hàng </span>
		</div>
	</div>

	<!-- Shoping Cart -->
	<form class="bg0 p-t-75 p-b-85" action="${base}/cart/payment" method="post">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-xl-12 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Mã đơn</th>
									<th class="column-2">Ngày đặt</th>
									<th class="column-3">Thành tiền</th>
									<th class="column-4">Trạng thái</th>
									<th class="column-5"></th>
								</tr>
								
								<c:forEach items="${orderHistory}" var="item">
								<tr class="table_row">
									<td class="column-1">
										${item.code}
									</td>
									<td class="column-2">
										<fmt:formatDate value="${item.createdDate}" pattern="HH:mm:ss dd-MM-yyyy" />
									</td>
									<td class="column-3">
										<fmt:formatNumber type="number" pattern="###,###VNĐ" value="${item.total}" />
									</td>
									<td class="column-4">
										<c:if test="${item.orderStatus == 0}">
											Chờ duyệt
										</c:if>
										<c:if test="${item.orderStatus == 1}">
											<output class="text-primary">Đã duyệt</output>
										</c:if>
										<c:if test="${item.orderStatus == 2}">
											<output class="text-danger">Đang giao</output>
										</c:if>
										<c:if test="${item.orderStatus == 3}">
											<output class="text-success">Đã nhận</output>
										</c:if>
									</td>
									<td class="column-5">
									</td>
								</tr>
								</c:forEach>
							</table>
						</div>

					</div>
				</div>

			</div>
		</div>
	</form>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>
	<!-- Back to top -->
	<jsp:include page="/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>
	<!-- main js -->
	<jsp:include page="/WEB-INF/views/front-end/common/js.jsp"></jsp:include>
	
</body>
</html>