<%@page import="java.math.BigDecimal"%>
<%@page import="com.devpro.shopdoda.entities.User"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<!-- Variables -->
<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/front-end/common/css.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- Cart -->
	<jsp:include page="/WEB-INF/views/front-end/common/cart.jsp"></jsp:include>

	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home" class="stext-109 cl8 hov-cl1 trans-04"> Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="stext-109 cl4"> Shoping Cart </span>
		</div>
	</div>


	<!-- Shoping Cart -->
	<form class="bg0 p-t-75 p-b-85" action="${base}/cart/payment" method="post">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Product</th>
									<th class="column-2"></th>
									<th class="column-3">Price</th>
									<th class="column-4">Quantity</th>
									<th class="column-5">Total</th>
								</tr>
								
								<c:set var="total" value="${0}"/>
								<c:forEach items="${cartItems}" var="item">
								<tr class="table_row">
									<td class="column-1">
										<div class="how-itemcart1">
											<img src="${base}/upload/${item.productAvatar}" alt="IMG">
										</div>
									</td>
									<td class="column-2">
									<a href="${base}/product-detail/${item.productSeo}"
											class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
											${item.productName} </a>
									</td>
									<td class="column-3">${item.priceUnit}</td>
									<td class="column-4">
										<div class="wrap-num-product flex-w m-l-auto m-r-0">
											<div
												class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>

											<input class="mtext-104 cl3 txt-center num-product"
												type="number" name="num-product1" value="${item.quantity}">

											<div
												class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>
									</td>
									<td class="column-5 text-danger">${item.priceUnit*item.quantity}</td>
									<c:set var="total" value="${total + item.priceUnit*item.quantity}" />
								</tr>
								</c:forEach>
							</table>
						</div>

						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							<div class="flex-w flex-m m-r-20 m-tb-5">
								<input
									class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5"
									type="text" name="coupon" placeholder="Coupon Code">

								<div
									class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
									Apply coupon</div>
							</div>

							<div
								class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
								Update Cart</div>
						</div>
					</div>
				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div
						class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">Cart Totals</h4>
						<c:if test="${not empty errorMessage}">
						<h5 class="alert alert-warning">${errorMessage}</h5>
						</c:if>

						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2"> Subtotal: </span>
							</div>

							<div class="size-209">
								<span class="mtext-110 cl2 text-danger"> ${total } </span>
							</div>
						</div>

						<div class="flex-w flex-t bor12 p-t-15 p-b-30">
							<div class="size-208 w-full-ssm">
								<span class="stext-110 cl2"> Shipping: </span>
							</div>

							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
								<p class="stext-111 cl6 p-t-2">Free ship cho mọi đơn hàng.</p>
							</div>
							<br>
							<div class="size-208 w-full-ssm">
								<span class="stext-110 cl2"> Thông tin người nhận: </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
								<div class="p-t-15">
									<!-- <span class="stext-112 cl8"> Mời quý khách nhập thông tin</span> -->

									<!-- <div class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9">
										<select class="js-select2" name="time">
											<option>Select a country...</option>
											<option>USA</option>
											<option>UK</option>
										</select>
										<div class="dropDownSelect2"></div>
									</div> -->

									<!-- <div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="state" placeholder="State /  country">
									</div> -->

									<!-- <div class="bor8 bg0 m-b-22">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="postcode" placeholder="Postcode / Zip">
									</div> -->
									
									<%
									boolean isLogined = false;
									Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
									if (principal instanceof UserDetails) {
										isLogined = true;
									}
									%>
									<%if(!isLogined){ %>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="customerName" placeholder="Họ tên" required="required">
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="customerAddress" placeholder="Địa chỉ" required="required">
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="customerPhone" placeholder="Số điện thoại" required="required">
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="email"
											name="customerEmail" placeholder="Email" required="required">
									</div>
									<%} else { 
										String fullName = ((User)principal).getFullName();
										String address = ((User)principal).getAddress();
										String phone = ((User)principal).getPhone();
										String email = ((User)principal).getEmail();
									%>
									<div class="bor8 bg0 m-b-12">
										
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="customerName" placeholder="<%= fullName %>" readonly>
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="customerAddress" placeholder="<%= address%>" readonly>
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"
											name="customerPhone" placeholder="<%= phone %>" readonly>
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="email"
											name="customerEmail" placeholder="<%= email %>" readonly>
									</div>
									<%} %>
									<div class="flex-w">
										<div
											class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer">
											Update Totals</div>
									</div>

								</div>
							</div>
							
						</div>

						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2"> Total: </span>
							</div>

							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2 text-danger"> ${total} </span>
							</div>
						</div>

						<button type = "submit"
							class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
							Proceed to Checkout	
						</button>
						
					</div>
				</div>
			</div>
		</div>
	</form>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>

	<!-- Back to top -->
	<jsp:include page="/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>

	<jsp:include page="/WEB-INF/views/front-end/common/js.jsp"></jsp:include>

</body>
</html>