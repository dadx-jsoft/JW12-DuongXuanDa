<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Paging -->
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/pagingTagLibs.tld"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Tin tức</title>
<!-- Variables -->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>
<meta charset="UTF-8">
<jsp:include page="${base}/WEB-INF/views/front-end/common/css.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="${base}/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92"
		style="background-image: url('images/bg-02.jpg');">
		<h2 class="cl0 txt-center">Tin tức</h2>
	</section>


	<!-- Content page -->
	<section class="bg0 p-t-62 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-12 p-b-80">
					<div class="p-r-45 p-r-0-lg">
					
						<!-- item blog -->
						<c:forEach items="${blogs}" var="blog">
						<div class="p-b-63">
							<a href="${base}/blog-detail" class="hov-img0 how-pos5-parent">
								<img src="${base}/upload/${blog.avatar}" alt="IMG-BLOG" width="70%">

								<div class="flex-col-c-m size-123 bg9 how-pos5">
									<span class="ltext-107 cl2 txt-center"> 
										<fmt:formatDate pattern="d" value="${blog.createdDate}" /> 
									</span> 
									<span
										class="stext-109 cl3 txt-center"> 
										<fmt:formatDate pattern="M" value="${blog.createdDate}" /> 
										<fmt:formatDate pattern="y" value="${blog.createdDate}" /> 
									</span>
								</div>
							</a>

							<div class="p-t-32">
								<h4 class="p-b-15">
									<a href="${base}/blog-detail/${blog.seo}"
										class="cl2 hov-cl1 trans-04 font-weight-bold"> ${blog.title } </a>
								</h4>

								<p class="cl6">${blog.shortDescription}</p>

								<div class="flex-w flex-sb-m p-t-18">
									<span class="flex-w flex-m stext-111 cl2 p-r-30 m-tb-10">
										<span> ${blog.views} views <span
											class="cl12 m-l-4 m-r-6">|</span>
									</span> 
									</span> <a href="${base}/blog-detail/${blog.seo}"
										class="cl2 hov-cl1 trans-04 m-tb-10"> Đọc tiếp <i class="fa fa-long-arrow-right m-l-9"></i>
									</a>
								</div>
							</div>
						</div>
						</c:forEach>
						
						<!-- Paging -->
						<c:set var="req" value="${pageContext.request}" />
						<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
						<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}" />
						<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}" />
						<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params ? '?'+=params+='&':'' }" />
						<tag:paginate offset="${blogSearch.offset }"
							count="${blogSearch.count }" uri="${pageUrl}" />
						<!-- End Paging -->
						
					</div>
				</div>

				<%-- <div class="col-md-4 col-lg-3 p-b-80">
					<div class="side-menu">
						<div class="bor17 of-hidden pos-relative">
							<input class="stext-103 cl2 plh4 size-116 p-l-28 p-r-55"
								type="text" name="search" placeholder="Search">

							<button
								class="flex-c-m size-122 ab-t-r fs-18 cl4 hov-cl1 trans-04">
								<i class="zmdi zmdi-search"></i>
							</button>
						</div>

						<div class="p-t-55">
							<h4 class="cl2 p-b-33">Danh mục blog</h4>

							<ul>
							<c:forEach var="blogType" items="${blogTypes}">
								<li class="bor18">
									<a href="${base}/blog-type/${blogType.seo}" 
									class="dis-block cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
										${blogType.name} </a>
								</li>
							</c:forEach>
							</ul>
						</div>

						<div class="p-t-65">
							<h4 class="mtext-112 cl2 p-b-33">Featured Products</h4>

							<ul>
								<li class="flex-w flex-t p-b-30"><a href="#"
									class="wrao-pic-w size-214 hov-ovelay1 m-r-20"> <img
										src="${base}/images/product-min-01.jpg" alt="PRODUCT">
								</a>

									<div class="size-215 flex-col-t p-t-8">
										<a href="#" class="stext-116 cl8 hov-cl1 trans-04"> White
											Shirt With Pleat Detail Back </a> <span
											class="stext-116 cl6 p-t-20"> $19.00 </span>
									</div></li>

								<li class="flex-w flex-t p-b-30"><a href="#"
									class="wrao-pic-w size-214 hov-ovelay1 m-r-20"> <img
										src="${base}/images/product-min-02.jpg" alt="PRODUCT">
								</a>

									<div class="size-215 flex-col-t p-t-8">
										<a href="#" class="stext-116 cl8 hov-cl1 trans-04">
											Converse All Star Hi Black Canvas </a> <span
											class="stext-116 cl6 p-t-20"> $39.00 </span>
									</div></li>

								<li class="flex-w flex-t p-b-30"><a href="#"
									class="wrao-pic-w size-214 hov-ovelay1 m-r-20"> <img
										src="${base}/images/product-min-03.jpg" alt="PRODUCT">
								</a>

									<div class="size-215 flex-col-t p-t-8">
										<a href="#" class="stext-116 cl8 hov-cl1 trans-04"> Nixon
											Porter Leather Watch In Tan </a> <span
											class="stext-116 cl6 p-t-20"> $17.00 </span>
									</div></li>
							</ul>
						</div>

						<div class="p-t-55">
							<h4 class="mtext-112 cl2 p-b-20">Archive</h4>

							<ul>
								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> July 2018 </span> <span> (9) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> June 2018 </span> <span> (39) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> May 2018 </span> <span> (29) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> April 2018 </span> <span> (35) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> March 2018 </span> <span> (22) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> February 2018 </span> <span> (32) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> January 2018 </span> <span> (21) </span>
								</a></li>

								<li class="p-b-7"><a href="#"
									class="flex-w flex-sb-m stext-115 cl6 hov-cl1 trans-04 p-tb-2">
										<span> December 2017 </span> <span> (26) </span>
								</a></li>
							</ul>
						</div>

						<div class="p-t-50">
							<h4 class="mtext-112 cl2 p-b-27">Tags</h4>

							<div class="flex-w m-r--5">
								<a href="#"
									class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
									Fashion </a> <a href="#"
									class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
									Lifestyle </a> <a href="#"
									class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
									Denim </a> <a href="#"
									class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
									Streetstyle </a> <a href="#"
									class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
									Crafts </a>
							</div>
						</div>
					</div>
				</div> --%>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>


	<!-- Back to top -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/front-end/common/js.jsp"></jsp:include>

</body>
</html>