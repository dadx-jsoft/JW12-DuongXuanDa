<%@ page session="true" %>
<%@page import="java.math.BigDecimal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@page import="com.eoptech.shopdoda.entities.User"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%
String username = null;
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if (principal instanceof UserDetails) {
	username = ((User)principal).getUsername();
}
%>
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
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<% if(username != null) {%>
						<span>
						<a href="${base}/order/history" class="btn btn-primary">Lịch sử mua hàng</a>
						</span>
						<%} %>
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Sản phẩm</th>
									<th class="column-2">Tên sản phẩm</th>
									<th class="column-3">Giá</th>
									<th class="column-4">Số lượng</th>
									<th class="column-5"></th>
								</tr>
								
								<c:forEach items="${cartItems}" var="item">
								<tr class="table_row" id="row_${item.productId}">
									<td class="column-1">
										<div class="how-itemcart1">
											<img src="${base}/upload/${item.productAvatar}" alt="IMG">
										</div>
									</td>
									<td class="column-2">
									<a href="${base}/product-detail/${item.productSeo}"
											class="cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
											${item.productName} </a>
									</td>
									<td class="column-3">
										<span class="text-danger"><fmt:formatNumber type="number" pattern="###,###" value="${item.priceUnit}" /></span> VNĐ
									</td>
									<td class="column-4">
										<div class="wrap-num-product flex-w m-l-auto m-r-0">
											<div onclick="UpdateCart(${item.productId},-1)"
												class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>

											<input id="numberOfProduct_${item.productId}" class="mtext-104 cl3 txt-center num-product"
												type="number" name="num-product1" value="${item.quantity}" min="1" disabled="disabled">

											<div onclick="UpdateCart(${item.productId},1)"
												class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>
									</td>
									<td class="column-5">
										<!-- <a href="#" class="text-center">Xóa</a> -->
										<div onclick="DeleteItemInCart(${item.productId})" class="btn btn-danger"><i class="fs-16 zmdi zmdi-delete"></i></div>
									</td>
								</tr>
								</c:forEach>
							</table>
						</div>

					</div>
				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div
						class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="cl2 p-b-30">Thông tin đơn hàng</h4>
						<c:if test="${not empty errorMessage}">
						<h5 class="alert alert-warning">${errorMessage}</h5>
						</c:if>

						<div class="flex-w flex-t bor12 p-b-13"></div>

						<div class="flex-w flex-t bor12 p-t-15 p-b-30">
							<div class="size-208 w-full-ssm">
								<span class="cl2"> Vận chuyển: </span>
							</div>

							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
								<p class="cl6 p-t-2">Miễn phí ship cho mọi đơn hàng.</p>
							</div>
							<br>
							<div class="size-208 w-full-ssm">
								<span class="cl2"> Thông tin người nhận: </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
								<div class="p-t-15">
									
									<%
									boolean isLogined = false;
									if (principal instanceof UserDetails) {
										isLogined = true;
									}
									%>
									<%if(!isLogined){ %>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="text"
											name="customerName" placeholder="Họ tên" required="required">
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="text"
											name="customerAddress" placeholder="Địa chỉ" required="required">
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="text"
											name="customerPhone" placeholder="Số điện thoại" required="required">
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="email"
											name="customerEmail" placeholder="Email" required="required">
									</div>
									<%} else { 
										String fullName = ((User)principal).getFullName();
										String address = ((User)principal).getAddress();
										String phone = ((User)principal).getPhone();
										String email = ((User)principal).getEmail();
									%>
									<div class="bor8 bg0 m-b-12">
										
										<input class="cl8 plh3 size-111 p-lr-15" type="text"
											name="customerName" placeholder="<%= fullName %>" readonly>
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="text"
											name="customerAddress" placeholder="<%= address%>" readonly>
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="text"
											name="customerPhone" placeholder="<%= phone %>" readonly>
									</div>
									<div class="bor8 bg0 m-b-12">
										<input class="cl8 plh3 size-111 p-lr-15" type="email"
											name="customerEmail" placeholder="<%= email %>" readonly>
									</div>
									<%} %>

								</div>
							</div>
							
						</div>

						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="cl2"> Tổng: </span>
							</div>

							<div class="size-209 p-t-1">
								<%
									Double totalPrice = (Double) session.getAttribute("totalPrice");
								%>
								<%-- <span id="totalPrice" class="mtext-110 cl2 text-danger"> 
									<fmt:formatNumber type="number" pattern="###,###VNĐ" value="${totalPrice}" />
								</span> --%>
								<input id="totalPrice" value="${totalPrice}" readonly="readonly" class="text-danger font-weight-bold"/> <span>VNĐ</span>
							</div>
						</div>

						<button type = "submit"
							class="flex-c-m cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
							Đặt hàng
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
	<!-- main js -->
	<jsp:include page="/WEB-INF/views/front-end/common/js.jsp"></jsp:include>
	
	<script>
		function UpdateCart(productId, difference){
			var numberOfProductId = '#numberOfProduct_' + productId;
			// javascript object.
			var data = {};
			data["productId"] = productId;
			 
			data["quantity"] = parseInt($(numberOfProductId).val()) + difference; // vì sự kiện onclick lấy value hiện tại
			
			$.ajax({
				url : "/cart/update",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(data),
	
				dataType : "json",
				success : function(jsonResult) {
					//$("#totalItemsInCart").html(jsonResult.data)
					
					$('#totalPrice').val(jsonResult.data[1])

					// Một cách siêu củ chuối để update number của cart trên giao diện @@
					var base = '${base}'
					$("#totalItemsInCart").after(
						"<div id='totalItemsInCart1' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='${base}/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
					)
					$("#totalItemsInCart").remove()
					$("#totalItemsInCart1").after(
						"<div id='totalItemsInCart' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
					)
					$("#totalItemsInCart1").remove()
					
					// Mobile 
					$("#totalItemsInCartMobile").after(
						"<div id='totalItemsInCartMobile1' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='${base}/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
					)
					$("#totalItemsInCartMobile").remove()
					$("#totalItemsInCartMobile1").after(
						"<div id='totalItemsInCartMobile' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
					)
					$("#totalItemsInCartMobile1").remove()
				},
				error : function(jqXhr, textStatus, errorMessage) { // error callback 
	
				}
			});
		}
	</script>
	<script type="text/javascript">
	function DeleteItemInCart(productId){
		// javascript object.
		var data = {};
		data["productId"] = productId;
		var numberOfProductId = '#numberOfProduct_' + productId;
		var rowDeleted = '#row_' + productId;
		data["quantity"] = 0;
		$.ajax({
			url : "/cart/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(data),

			dataType : "json",
			success : function(jsonResult) {
				$('#totalPrice').val(jsonResult.data[1])

				// Một cách siêu củ chuối để update number của cart trên giao diện @@
				var base = '${base}'
				$("#totalItemsInCart").after(
					"<div id='totalItemsInCart1' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='${base}/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCart").remove()
				$("#totalItemsInCart1").after(
					"<div id='totalItemsInCart' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCart1").remove()
				
				// Mobile 
				$("#totalItemsInCartMobile").after(
					"<div id='totalItemsInCartMobile1' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='${base}/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCartMobile").remove()
				$("#totalItemsInCartMobile1").after(
					"<div id='totalItemsInCartMobile' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data[0]+"'> <a href='/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCartMobile1").remove()

				// xóa dòng muốn xóa
				$(rowDeleted).remove();
			},
			error : function(jqXhr, textStatus, errorMessage) { // error callback 

			}
		});
	}
	</script>
</body>
</html>