<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.devpro.shopdoda.entities.User"%>
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
<!-- Variables -->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>
<meta charset="UTF-8">
<jsp:include page="${base}/WEB-INF/views/front-end/common/css.jsp"></jsp:include>
</head>
<body class="animsition">

	<jsp:include page="${base}/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="${base}/index" class="stext-109 cl8 hov-cl1 trans-04"> Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <a href="${base}/blog" class="stext-109 cl8 hov-cl1 trans-04"> Blog
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="stext-109 cl4"> ${blog.title } </span>
		</div>
	</div>


	<!-- Content page -->
	<section class="bg0 p-t-52 p-b-20">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-lg-9 p-b-80">
					<div class="p-r-45 p-r-0-lg">
						<!--  -->
						<div class="wrap-pic-w how-pos5-parent">
							<img src="${base}/images/blog-04.jpg" alt="IMG-BLOG">

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
						</div>
						
						<div class="p-t-32">
							<span class="flex-w flex-m stext-111 cl2 p-b-19"> 
							<span> <fmt:formatDate value="${blog.createdDate}" pattern="dd-MM-yyyy" /> <span class="cl12 m-l-4 m-r-6">|</span>
							</span> <span> ${blog.views} views <span
									class="cl12 m-l-4 m-r-6">|</span>
							</span> <span> ${blog.comments.size()} Comments </span>
							</span>

							<h4 class="ltext-109 cl2 p-b-28">${blog.title}</h4>

							<p class="stext-117 cl6 p-b-26">${blog.detailDescription}</p>

						</div>

						<div class="flex-w flex-t p-t-16">
							<span class="size-216 stext-116 cl8 p-t-4"> Type </span>

							<div class="flex-w size-217">
								<a href="#"
									class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
									${blog.blogType.name} </a> 
							</div>
						</div>

						<!--  -->
						<div class="p-t-40">
							<h5 class="mtext-113 cl2 p-b-12">Leave a Comment</h5>

							<p class="stext-107 cl6 p-b-40">Your email address will not
								be published. Required fields are marked *</p>

							<form>
								<div class="bor19 m-b-20">
									<textarea class="stext-111 cl2 plh3 size-124 p-lr-18 p-tb-15" id="comment"
										name="comment" placeholder="Comment..."></textarea>
								</div>
								
								<button onclick="saveComment(<%=userId %>,${blog.id})"
									class="flex-c-m stext-101 cl0 size-125 bg3 bor2 hov-btn3 p-lr-15 trans-04">
									Post Comment</button>
							</form>
							
						</div>
						<div class="p-t-40">
						<c:forEach items="${comments}" var="comment">
							<h5 class="mtext-113 cl2 p-b-12">${comment.user.fullName }</h5>
							<p class="stext-107 cl6 p-b-40">${comment.comment }</p>
						</c:forEach>
						</div>
					</div>
				</div>

				<div class="col-md-4 col-lg-3 p-b-80">
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
							<h4 class="mtext-112 cl2 p-b-33">Categories</h4>

							<ul>
								<li class="bor18"><a href="#"
									class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
										Fashion </a></li>

								<li class="bor18"><a href="#"
									class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
										Beauty </a></li>

								<li class="bor18"><a href="#"
									class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
										Street Style </a></li>

								<li class="bor18"><a href="#"
									class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
										Life Style </a></li>

								<li class="bor18"><a href="#"
									class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
										DIY & Crafts </a></li>
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
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>

	<!-- Back to top -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/front-end/common/js.jsp"></jsp:include>
	
	<script>
		function saveComment(userId, blogId) {
			// javascript object.
			var data = {};
			data["userId"] = userId;
			data["productOrBlogId"] = blogId;
			data["message"] = $('#comment').val();
	
			// clear textarea
			$('#review').val('');
			$.ajax({
				url : "/blog/comments/add",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(data),
	
				dataType : "json",
				success : function(jsonResult) {
					if (jsonResult.statusCode == 200) {
						swal("Comment thành công!!", "Vui lòng chờ admin duyệt comment", "success");
					} else {
						alert(jsonResult.data);
					}
				},
				error : function(jqXhr, textStatus, errorMessage) { // error callback 
					
				}
			});
		}
	</script>
</body>
</html>