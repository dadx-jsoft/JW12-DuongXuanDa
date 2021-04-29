<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Paging -->
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/pagingTagLibs.tld"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="UTF-8">

<jsp:include page="${base}/WEB-INF/views/front-end/common/css.jsp"></jsp:include>

<!-- Variables -->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="${base}/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- Slider -->
	<section class="section-slide">
		<div class="wrap-slick1">
			<div class="slick1">
				<div class="item-slick1"
					style="background-image: url(${base}/images/slide-001.jpg);">
					<div class="container h-full">
						<div class="flex-col-l-m h-full p-t-100 p-b-30 respon5">
							<div class="layer-slick1 animated visible-false"
								data-appear="fadeInDown" data-delay="0">
								<span class="ltext-101 cl0 respon2"> Leather Collection
									2021 </span>
							</div>

							<div class="layer-slick1 animated visible-false"
								data-appear="fadeInUp" data-delay="800">
								<h2 class="ltext-201 cl0 p-t-19 p-b-43 respon1">NEW SEASON
								</h2>
							</div>

							<div class="layer-slick1 animated visible-false"
								data-appear="zoomIn" data-delay="1600">
								<a href="${base}/products"
									class="flex-c-m cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
									Xem </a>
							</div>
						</div>
					</div>
				</div>

				<div class="item-slick1"
					style="background-image: url(${base}/images/slide-002.jpg);">
					<div class="container h-full">
						<div class="flex-col-l-m h-full p-t-100 p-b-30 respon5">
							<div class="layer-slick1 animated visible-false"
								data-appear="rollIn" data-delay="0">
								<span class="ltext-101 cl0 respon2"> Leather New-Season </span>
							</div>

							<div class="layer-slick1 animated visible-false"
								data-appear="lightSpeedIn" data-delay="800">
								<h2 class="ltext-201 cl0 p-t-19 p-b-43 respon1">Wallet & Strap</h2>
							</div>

							<div class="layer-slick1 animated visible-false"
								data-appear="slideInUp" data-delay="1600">
								<a href="${base}/products"
									class="flex-c-m cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
									Xem </a>
							</div>
						</div>
					</div>
				</div>

				<div class="item-slick1"
					style="background-image: url(${base}/images/slide-003.jpg);">
					<div class="container h-full">
						<div class="flex-col-l-m h-full p-t-100 p-b-30 respon5">
							<div class="layer-slick1 animated visible-false"
								data-appear="rotateInDownLeft" data-delay="0">
								<span class="ltext-101 cl0 respon2"> New Collection 2021
								</span>
							</div>

							<div class="layer-slick1 animated visible-false"
								data-appear="rotateInUpRight" data-delay="800">
								<h2 class="ltext-201 cl0 p-t-19 p-b-43 respon1">New
									arrivals</h2>
							</div>

							<div class="layer-slick1 animated visible-false"
								data-appear="rotateIn" data-delay="1600">
								<a href="${base}/products"
									class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
									Xem </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- Banner -->
	<div class="sec-banner bg0 p-t-80 p-b-50">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="${base}/images/category2.jpg" alt="IMG-BANNER"> <a
							href="${base}/category/day-da-dong-ho"
							class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8"> Strap
								</span> <span class="block1-info stext-102 trans-04"> Spring
									2021 </span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">Xem</div>
							</div>
						</a>
					</div>
				</div>

				<div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="${base}/images/category3.jpg" alt="IMG-BANNER"> <a
							href="${base}/category/vi-da"
							class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8"> Wallet </span>

								<span class="block1-info stext-102 trans-04"> Spring 2021
								</span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">Xem</div>
							</div>
						</a>
					</div>
				</div>

				<div class="col-md-6 col-xl-4 p-b-30 m-lr-auto">
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="${base}/images/phu-kien.jpg" alt="IMG-BANNER"> <a
							href="${base}/product"
							class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">
									Accessories </span> <span class="block1-info stext-102 trans-04">
									New Trend </span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">Xem</div>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<section class="bg0 p-t-23 p-b-140">
		<div class="container">
			<div class="p-b-10">
				<h3 class="cl5 font-weight-bold text-uppercase mb-3">Sản phẩm mua nhiều nhất</h3>
			</div>

			<div class="row isotope-grid">

				<c:forEach items="${bestSellingProducts}" var="product" varStatus="count">
						<div
							class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item cat-${product.categories.id}">
							<!-- Block2 -->
							<div class="block2">
								<div class="block2-pic hov-img0">
									<img src="${base}/upload/${product.avatar}" alt="IMG-PRODUCT">
									<button
										class="block2-btn flex-c-m cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04" onclick="showModal('product_${product.id}')">
										Quick View </button>
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
										<img src="images/icons/icon-close.png" alt="CLOSE">
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
															Add to cart
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

		</div>
	</section>

	<!-- Product -->
	<section class="bg0 p-t-23 p-b-140">
		<div class="container">
			<div class="p-b-10">
				<h3 class="cl5 font-weight-bold text-uppercase mb-3">Sản phẩm mới</h3>
			</div>

			<div class="row isotope-grid">

				<c:forEach items="${products}" var="product" varStatus="count">
						<div
							class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item cat-${product.categories.id}">
							<!-- Block2 -->
							<div class="block2">
								<div class="block2-pic hov-img0">
									<img src="${base}/upload/${product.avatar}" alt="IMG-PRODUCT">
									<button
										class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04" onclick="showModal('product_${product.id}')">
										Quick View </button>
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
										<img src="images/icons/icon-close.png" alt="CLOSE">
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
					
												<p class="stext-102 cl3 p-t-23">
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
																	class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04">
															Add to cart
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

			<div class="flex-c-m flex-w w-full p-t-45">
				<a href="${base}/products" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
					Xem thêm
				</a>
			</div>
			
		</div>
	</section>
	
	<!-- Footer -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>


	<!-- Back to top -->
	<jsp:include
		page="${base}/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>

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
		$('.js-addwish-b2').on('click', function(e) {
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
	</script>

</body>
</html>