<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Paging -->
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/pagingTagLibs.tld"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<!-- Variables -->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>
<meta charset="UTF-8">
<jsp:include page="${base}/WEB-INF/views/front-end/common/css.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="${base}/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<!-- Menu products -->
			<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
				<div class="collapse navbar-collapse">
					<ul class="navbar-nav mr-auto">
						${menu}
					</ul>
					<form class="form-inline my-2 my-lg-0" action="${base}/product/search-all">
						<input class="form-control mr-sm-2" type="search"
							placeholder="Nhập từ khóa" aria-label="Search" name="searchText">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm</button>
					</form>
				</div>
			</nav>

			<div class="row isotope-grid">
				<c:forEach items="${products}" var="product">
						<div
							class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item  cat-${product.categories.id}">
							<!-- Block2 -->
							<div class="block2">
								<div class="block2-pic hov-img0">
									<img src="${base}/upload/${product.avatar}" alt="IMG-PRODUCT">
									<button
										class="block2-btn flex-c-m cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04" onclick="showModal('product_${product.id}')">
										Xem nhanh </button>
									<script>
									function showModal(productIdModal){
								        document.getElementById(productIdModal).classList.add("show-modal1");
								    };
								    function hideModal(productIdModal){
								    	document.getElementById(productIdModal).classList.remove("show-modal1");
									};
									</script>
								</div>

								<div class="block2-txt flex-w flex-t p-t-14">
									<div class="block2-txt-child1 flex-col-l ">
										<a href="${base}/product-detail/${product.seo}"
											class="cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
											${product.title} </a> 
											<span class="cl3 text-danger font-weight-bold">
											<fmt:formatNumber type="number" pattern="###,###VNĐ" value="${product.price}" />
											</span>
									</div>

								</div>
							</div>
						</div>
						
						<!-- Modal1 -->
						<div id="product_${product.id}" class="wrap-modal1 js-modal1 p-t-60 p-b-20">
							<div class="overlay-modal1 js-hide-modal1"></div>

							<div class="container">
								<div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
									<button class="how-pos3 hov3 trans-04 js-hide-modal1">
										<img src="${base}/images/icons/icon-close.png" alt="CLOSE">
									</button>
					
									<div class="row">
										<div class="col-md-6 col-lg-7 p-b-30">
											<div class="p-l-25 p-r-30 p-lr-0-lg">
												<div class="wrap-slick3 flex-sb flex-w">
													<div class="wrap-slick3-dots"></div>
													<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
					
													<div class="slick3 gallery-lb">
														<div class="item-slick3"
															data-thumb="${base}/upload/${product.avatar}">
															<div class="wrap-pic-w pos-relative">
																<img src="${base}/upload/${product.avatar}"
																	alt="IMG-PRODUCT"> <a
																	class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
																	href="${base}/upload/${product.avatar}"> <i
																	class="fa fa-expand"></i>
																</a>
															</div>
														</div>
					
														<c:forEach items="${product.productsImages}" var="imageProduct">
														<div class="item-slick3"
															data-thumb="${base}/upload/${imageProduct.path}">
															<div class="wrap-pic-w pos-relative">
																<img src="${base}/upload/${imageProduct.path}"
																	alt="IMG-PRODUCT"> <a
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
												<h4 class="mtext-105 cl2 js-name-detail p-b-14">
													${product.title}
												</h4>
					
												<span class="mtext-106 cl2 text-danger">
													<fmt:formatNumber type="number" pattern="###,###VNĐ" value="${product.price}" /> 
												</span>
					
												<p class="cl3 p-t-23">
													${product.shortDescription }
												</p>
												
												<!--  -->
												<div class="p-t-33">
													<div class="flex-w flex-r-m p-b-10">
														<div class="size-203 flex-c-m respon6">Color</div>
														<div class="size-204 respon6-next">
															${product.color }
														</div>
													</div>
													<div class="flex-w flex-r-m p-b-10">
														<div class="size-203 flex-c-m respon6">Material</div>
														<div class="size-204 respon6-next">
															${product.material }
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
																	type="number" name="num-product" value="1" id = "numberProduct_${product.id}" min="0">
						
																<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
																	<i class="fs-16 zmdi zmdi-plus"></i>
																</div>
															</div>
					
															<button onclick="addToCart(${product.id}, 1)"
																	class="flex-c-m cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
															Thêm vào giỏ
															</button>
														</div>
													</div>	
												</div>
					
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
				</c:forEach>
			</div>

			<!-- Pagination -->
			<div class="flex-c-m flex-w w-full p-t-45">
				<!-- <a href="#"
					class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
					Load More </a> -->
				
				<c:set var="req" value="${pageContext.request}" />
				<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
				<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}" />
				<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}" />
				<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params ? '?'+=params+='&':'' }" />
				
				<tag:paginate offset="${productSearch.offset }"
					count="${productSearch.count }" uri="${pageUrl}" />
			</div>
			<!-- End pagination -->

		</div>
	</div>

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

	</script>
</body>
</html>