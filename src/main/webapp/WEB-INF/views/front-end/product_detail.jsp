<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Variables -->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>

<%@page import="com.eoptech.shopdoda.entities.User"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%
Integer userId = 0;
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if (principal instanceof UserDetails) {
	userId = ((User)principal).getId();
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="UTF-8">

<jsp:include page="./common/css.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="./common/header.jsp"></jsp:include>

	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="${base}/" class="cl8 hov-cl1 trans-04"> Trang chủ
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <a href="${base}/category/${pro_detail.categories.seo}" class="cl8 hov-cl1 trans-04">
				${pro_detail.categories.name} <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="cl4"> ${pro_detail.title} </span>
		</div>
	</div>

	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

							<div class="slick3 gallery-lb">
								<div class="item-slick3"
									data-thumb="${base}/upload/${pro_detail.avatar}">
									<div class="wrap-pic-w pos-relative">
										<img src="${base}/upload/${pro_detail.avatar}" alt="IMG-PRODUCT">

										<a
											class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
											href="${base}/upload/${pro_detail.avatar}"> <i
											class="fa fa-expand"></i>
										</a>
									</div>
								</div>
								<c:forEach items="${listImages}" var="imageProduct">
								<div class="item-slick3"
									data-thumb="${base}/upload/${imageProduct.path}">
									<div class="wrap-pic-w pos-relative">
										<img src="${base}/upload/${imageProduct.path}" alt="IMG-PRODUCT">

										<a
											class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
											href="${base}/upload/${imageProduct.path}"> <i
											class="fa fa-expand"></i>
										</a>
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<h4 class="mtext-105 cl2 js-name-detail p-b-14">${pro_detail.title}</h4>

						<span class="mtext-106 cl2 text-danger font-weight-bold"> 
						<fmt:formatNumber type="number" pattern="###,###VNĐ" value="${pro_detail.price}" /> 
						</span>

						<p class="cl3 p-t-23">${pro_detail.shortDescription}</p>

						<!--  -->
						<div class="p-t-33">
							<div class="flex-w flex-r-m p-b-10">
								<div class="size-203 flex-c-m respon6">Color</div>
								<div class="size-204 respon6-next">
									${pro_detail.color }
								</div>
							</div>
							<div class="flex-w flex-r-m p-b-10">
								<div class="size-203 flex-c-m respon6">Material</div>
								<div class="size-204 respon6-next">
									${pro_detail.material }
								</div>
							</div>

							<div class="flex-w flex-r-m p-b-10">
								<div class="size-204 flex-w flex-m respon6-next">
									<div class="wrap-num-product flex-w m-r-20 m-tb-10">
										<div
											class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
											<i class="fs-16 zmdi zmdi-minus"></i>
										</div>

										<input class="mtext-104 cl3 txt-center num-product"
											type="number" name="num-product" value="1" id = "numberProduct_${pro_detail.id}" min="0">

										<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
											<i class="fs-16 zmdi zmdi-plus"></i>
										</div>
									</div>

									<button onclick="addToCart(${pro_detail.id}, 1)"
										class="flex-c-m cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
										Thêm vào giỏ</button>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<!-- Tab01 -->
				<div class="tab01">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10"><a class="nav-link active"
							data-toggle="tab" href="#description" role="tab">Mô tả</a>
						</li>

						<li class="nav-item p-b-10"><a class="nav-link"
							data-toggle="tab" href="#reviews" role="tab">Đánh giá</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content p-t-43">
						<!-- - -->
						<div class="tab-pane fade show active" id="description"
							role="tabpanel">
							<div class="how-pos2 p-lr-15-md">
								<p class="cl6">${pro_detail.detailDescription}</p>
							</div>
						</div>

						<div class="tab-pane fade" id="reviews" role="tabpanel">
							<div class="row">
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
									<div class="p-b-30 m-lr-15-sm">
										<!-- Review -->
										<c:forEach items="${comments}" var="comment">
										<div class="flex-w flex-t p-b-68">
											<div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
												<img src="${base}/upload/${comment.user.avatar}" alt="AVATAR">
											</div>

											<div class="size-207">
												<div class="flex-w flex-sb-m p-b-17">
													<span class="cl2 p-r-20"> ${comment.user.fullName} </span> 
												</div>

												<p class="cl6">${comment.message}</p>
												<i><fmt:formatDate value="${comment.createdDate}" pattern="HH:mm dd-MM-yyyy" /></i>
											</div>
										</div>
										</c:forEach>
										
										<!-- Add review -->
										<!-- <form class="w-full"> -->
											<h5 class="mtext-108 cl2 p-b-7">Đánh giá</h5>
											<i>Chú ý: Vui lòng đăng nhập để đánh giá *</i>
											<div class="row p-b-25">
												<div class="col-12 p-b-5">
													<textarea
														class="size-110 bor8 cl2 p-lr-20 p-tb-10"
														id="review" name="review"></textarea>
												</div>
											</div>

											<button onclick="saveReview(<%=userId %>,${pro_detail.id})"
												class="flex-c-m cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
												Đăng</button>
										<!-- </form> -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">
			<span class="cl6 p-lr-25"> Loại: </span> <span
				class="cl6 p-lr-25"> ${pro_detail.categories.name } </span>
		</div>
	</section>

	
	
	<!-- Related Products -->
	<section class="sec-relate-product bg0 p-t-45 p-b-105">
		<div class="container">
			<div class="p-b-45">
				<h3 class="cl5 txt-center">Sản phẩm cùng danh mục</h3>
			</div>

			<!-- Slide2 -->
			<div class="wrap-slick2">
				<div class="slick2">
					<c:forEach items="${relatedProducts}" var="relatedProduct">
					<div class="item-slick2 p-l-15 p-r-15 p-t-15 p-b-15">
						<!-- Block2 -->
						<div class="block2">
							<div class="block2-pic hov-img0">
								<img src="${base}/upload/${relatedProduct.avatar}" alt="IMG-PRODUCT"> 
							</div>

							<div class="block2-txt flex-w flex-t p-t-14">
								<div class="block2-txt-child1 flex-col-l ">
									<a href="${base}/product-detail/${relatedProduct.seo}"
										class="cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
										${relatedProduct.title} </a> 
										<span class="cl3 text-danger font-weight-bold">
										<fmt:formatNumber type="number" pattern="###,###VNĐ" value="${relatedProduct.price}" /> 
										</span>
								</div>

								<div class="block2-txt-child2 flex-r p-t-3">
									<a href="#"
										class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
										<img class="icon-heart1 dis-block trans-04"
										src="${base}/images/icons/icon-heart-01.png" alt="ICON"> <img
										class="icon-heart2 dis-block trans-04 ab-t-l"
										src="${base}/images/icons/icon-heart-02.png" alt="ICON">
									</a>
								</div>
							</div>
						</div>
					</div>
					
					</c:forEach>


				</div>
			</div>
		</div>
	</section>


	<!-- Footer -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>


	<!-- Back to top -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/front-end/common/js.jsp"></jsp:include>

	<!--===============================================================================================-->
	<script src="${base}/vendor/daterangepicker/moment.min.js"></script>
	<script src="${base}/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="${base}/vendor/slick/slick.min.js"></script>
	<script src="${base}/js/slick-custom.js"></script>
	<!--===============================================================================================-->
	<script src="${base}/vendor/parallax100/parallax100.js"></script>
	<script>
		$('.parallax100').parallax100();
	</script>
	<!--===============================================================================================-->
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
				delegate : 'a', // the selector for gallery item
				type : 'image',
				gallery : {
					enabled : true
				},
				mainClass : 'mfp-fade'
			});
		});
	</script>
	<!--===============================================================================================-->
	<script src="${base}/vendor/isotope/isotope.pkgd.min.js"></script>
	<!--===============================================================================================-->
	<script src="${base}/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2, .js-addwish-detail').on('click', function(e) {
			e.preventDefault();
		});

		$('.js-addwish-b2').each(
				function() {
					var nameProduct = $(this).parent().parent().find(
							'.js-name-b2').html();
					$(this).on('click', function() {
						swal(nameProduct, "is added to wishlist !", "success");

						$(this).addClass('js-addedwish-b2');
						$(this).off('click');
					});
				});

		$('.js-addwish-detail').each(
				function() {
					var nameProduct = $(this).parent().parent().parent().find(
							'.js-name-detail').html();

					$(this).on('click', function() {
						swal(nameProduct, "is added to wishlist !", "success");

						$(this).addClass('js-addedwish-detail');
						$(this).off('click');
					});
				});

		/*---------------------------------------------*/
		function saveReview(userId, productId) {
			// javascript object.
			var data = {};
			data["userId"] = userId;
			data["productOrBlogId"] = productId;
			data["message"] = $('#review').val();

			// clear textarea
			$('#review').val('');
			$.ajax({
				url : "/reviews/add",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(data),
	
				dataType : "json",
				success : function(jsonResult) {
					var message = jsonResult.data;
					if (jsonResult.statusCode == 200) {
						swal(message, "Thông báo");
					} else {
						alert(message);
					}
				},
				error : function(jqXhr, textStatus, errorMessage) { // error callback 
					
				}
			});
		}
	</script>

</body>
</html>