<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="header-v4">
	<!-- Header desktop -->
	<div class="container-menu-desktop">
		<!-- Topbar -->
		<div class="top-bar">
			<div class="content-topbar flex-sb-m h-full container">
				<div class="left-top-bar">
					Hotline: 0961010169
				</div>

				<div class="right-top-bar flex-w h-full">
					<%
						if(username!=null){
					%>
						<a href="" class="flex-c-m trans-04 p-lr-25"> <%= username %> </a>
						<a href="${base}/logout" class="flex-c-m trans-04 p-lr-25"> Đăng xuất </a>
					<% } else{ %>
						<a href="${base}/login" class="flex-c-m trans-04 p-lr-25"> Đăng nhập </a>
					<% } %>
					<!-- <a href="#" class="flex-c-m trans-04 p-lr-25"> EN </a> 
					<a href="#" class="flex-c-m trans-04 p-lr-25"> USD </a> -->
				</div>
			</div>
		</div>

		<div class="wrap-menu-desktop how-shadow1">
			<nav class="limiter-menu-desktop container">

				<!-- Logo desktop -->
				<a href="/" class="logo"> <img src="/images/icons/logo.png"
					alt="IMG-LOGO">
				</a>

				<!-- Menu desktop -->
				<div class="menu-desktop">
					<ul class="main-menu">
						<li><a href="/">Trang chủ</a></li>

						<li><a href="${base}/products">Shop</a></li>

						<%-- <li class="label1" data-label1="hot"><a
							href="${base}/shopping-cart">Features</a></li> --%>

						<li><a href="${base}/blog">Tin tức</a></li>

						<%-- <li><a href="${base}/about">About</a></li> --%>

						<li><a href="${base}/contact">Liên hệ</a></li>
					</ul>
				</div>

				<!-- Icon header -->
				<div class="wrap-icon-header flex-w flex-r-m">

					<div id="totalItemsInCart"
						class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti"
						data-notify="${totalItems>0?totalItems:0}">
						<!-- js-show-cart -->
						
						<a href="${base}/cart/view"><i class="zmdi zmdi-shopping-cart"></i></a>
					</div>
				</div>
			</nav>
		</div>
	</div>

	<!-- Header Mobile -->
	<div class="wrap-header-mobile">
		<!-- Logo moblie -->
		<div class="logo-mobile">
			<a href="/"><img src="${base}/images/icons/logo.png" alt="IMG-LOGO"></a>
		</div>

		<!-- Icon header -->
		<div class="wrap-icon-header flex-w flex-r-m m-r-15">

			<div id="totalItemsInCartMobile"
				class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti"
				data-notify="${totalItems>0?totalItems:0}">
				<i class="zmdi zmdi-shopping-cart"></i>
			</div>

		</div>

		<!-- Button show menu -->
		<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
			<span class="hamburger-box"> <span class="hamburger-inner"></span>
			</span>
		</div>
	</div>


	<!-- Menu Mobile -->
	<div class="menu-mobile">
		<ul class="topbar-mobile">
			<li>
				<div class="left-top-bar">Free shipping for standard order
					over $100</div>
			</li>

			<li>
				<div class="right-top-bar flex-w h-full">
					 <a	href="#" class="flex-c-m p-lr-10 trans-04"> My Account </a> 
					 <a href="#" class="flex-c-m p-lr-10 trans-04"> EN </a> 
					 <a href="#" class="flex-c-m p-lr-10 trans-04"> USD </a>
				</div>
			</li>
		</ul>

		<ul class="main-menu-m">
			<li><a href="/">Home</a></li>

			<li><a href="${base}/products">Shop</a></li>

			<!-- <li><a href="shopping-cart" class="label1 rs1" data-label1="hot">Features</a></li> -->

			<li><a href="${base}/blog">Blog</a></li>

			<li><a href="${base}/about">About</a></li>

			<li><a href="${base}/contact">Contact</a></li>
		</ul>
	</div>

</header>